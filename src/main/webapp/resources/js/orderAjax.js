$(document).ready(function() {

    // load first when coming page
    ajaxGet(1);

    function ajaxGet(page){
        var data = { status : $('#trangThai').val(), from: $('#fromDate').val(), to: $('#toDate').val()  }
        $.ajax({
            type: "GET",
            data: data,
            contentType : "application/json",
            url: "http://localhost:3010/toystore/api/order/all" + '?page=' + page,
            success: function(result){
                $.each(result.content, function(i, order){
                    // tính giá trị đơn hàng\
                    var sum = 0;
                    var check = order.status == "Hoàn thành" || order.status == "Chờ duyệt";
                    if(check){
                        $.each(order.orderDetails, function(i, detail){
                            sum += detail.price * detail.receiveQuantity;
                        });
                    } else {
                        $.each(order.orderDetails, function(i, detail){
                            sum += detail.price * detail.orderQuantity;
                        });
                    }

                    var donHangRow = '<tr>' +
                        '<td>' + order.id+ '</td>' +
                        '<td>' + order.name + '</td>' +
                        '<td>' + order.status + '</td>' +
                        '<td>' + sum + '</td>' +
                        '<td>' + order.orderDate + '</td>' +
                        '<td>' + order.deliveryDate + '</td>' +
                        '<td>' + order.reciveDate + '</td>' +
                        '<td width="0%">'+'<input type="hidden" class="donHangId" value=' + order.id + '>'+ '</td>'+
                        '<td><button class="btn btn-warning btnChiTiet" ><i class="fa fa-eye"/></button>';
                    if(order.status == "Đang chờ giao" || order.status == "Đang giao"){
                        donHangRow += ' &nbsp;<button class="btn btn-danger btnHuy"><i class="fa fa-remove"></button>' ;
                    } else if (order.status == "Chờ duyệt"){
                        // donHangRow += ' &nbsp;<button class="btn btn-primary btnCapNhat" >Cập Nhật</button> </td>';
                    }

                    $('.donHangTable tbody').append(donHangRow);

                    $('td').each( function(i){
                        if ($(this).html() == 'null'){
                            $(this).html('');
                        }
                    });
                });

                if(result.totalPages > 1 ){
                    for(var numberPage = 1; numberPage <= result.totalPages; numberPage++) {
                        var li = '<li class="page-item "><a class="pageNumber">'+numberPage+'</a></li>';
                        $('.pagination').append(li);
                    };

                    // active page pagination
                    $(".pageNumber").each(function(index){
                        if($(this).text() == page){
                            $(this).parent().removeClass().addClass("page-item active");
                        }
                    });
                };
            },
            error : function(e){
                alert("Error: ",e);
                console.log("Error" , e );
            }
        });
    };


    // event khi click vào button Chi tiết đơn
    $(document).on('click', '.btnPhanCong', function (event){
        event.preventDefault();
        var donHangId = $(this).parent().prev().children().val();
        $("#donHangId").val(donHangId);
        console.log(donHangId);
        $("#phanCongModal").modal();
    });

    $(document).on('click', '#btnPhanCongSubmit', function (event) {
        var email = $("select[name=shipper]").val();
        var donHangId = $("#donHangId").val();
        console.log(donHangId);
        ajaxPostPhanCongDonHang(email, donHangId)

    });

    function ajaxPostPhanCongDonHang(email, donHangId) {

        $.ajax({
            async:false,
            type : "POST",
            contentType : "application/json",
            url : "http://localhost:3010/toystore/api/don-hang/assign?shipper="+email+"&donHangId="+donHangId,
            enctype: 'multipart/form-data',

            success : function(response) {
                alert("Phân công đơn hàng thành công");
                var trangThai = $('#trangThai').val();
                location.href = window.location.href;
            },
            error : function(e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });
    }

    $(document).on('click', '#btnDuyetDonHang', function (event) {
        event.preventDefault();
        resetData();
    });

    // reset table after post, put, filter
    function resetData(){
        var page = $('li.active').children().text();
        $('.donHangTable tbody tr').remove();
        $('.pagination li').remove();
        ajaxGet(page);
    };

    // event khi click vào phân trang Đơn hàng
    $(document).on('click', '.pageNumber', function (event){
//		event.preventDefault();
        var page = $(this).text();
        $('.donHangTable tbody tr').remove();
        $('.pagination li').remove();
        ajaxGet(page);
    });

    // event khi click vào button Chi tiết đơn
    $(document).on('click', '.btnChiTiet', function (event){
        event.preventDefault();

        var donHangId = $(this).parent().prev().children().val();
//		console.log(donHangId);
        var href = "http://localhost:3010/toystore/api/order/"+donHangId;
        $.get(href, function(order) {
            $('#maDonHang').text("Mã đơn hàng: "+ order.id);
            $('#hoTenNguoiNhan').text("Người nhận: "+ order.name);
            $('#sdtNhanHang').text("SĐT: "+ order.phone);
            $('#diaChiNhan').text("Địa chỉ: "+ order.delivery);
            $('#trangThaiDonHang').text("Trạng thái đơn: "+ order.status);
            $("#ngayDatHang").text("Ngày đặt: "+ order.orderDate);

            if(order.ngayGiaoHang != null){
                $("#ngayShipHang").text("Ngày giao: "+ order.deliveryDate);
            }

            if(order.ngayNhanHang != null){
                $("#ngayNhanHang").text("Ngày nhận: "+ order.reciveDate);
            }

            if(order.ghiChu != null){
                $("#ghiChu").html("<strong>Ghi chú</strong>:<br> "+ order.note);
            }

            if(order.nguoiDat != null){
                $("#nguoiDat").html("<strong>Người đặt</strong>:  "+ order.customer.name);
            }

            if(order.shipper != null){
                $("#shipper").html("<strong>Shipper</strong>: "+ order.shipper.name);
            }

            var check = order.status == "Hoàn thành" || order.status == "Chờ duyệt" ;
            if(check){
                $('.chiTietTable').find('thead tr').append('<th id="soLuongNhanTag" class="border-0 text-uppercase small font-weight-bold"> SỐ LƯỢNG NHẬN </th>');
            }
            // thêm bảng:
            var sum = 0; // tổng giá trị đơn
            var stt = 1;
            $.each(order.orderDetails, function(i, d){
                var chiTietRow = '<tr>' +
                    '<td>' + stt + '</td>' +
                    '<td>' + d.product.name + '</td>' +
                    '<td>' + d.price + '</td>'+
                    '<td>' + d.orderQuantity+ '</td>';

                if(check){
                    chiTietRow += '<td>' + d.receiveQuantity + '</td>';
                    sum += d.price * d.receiveQuantity;
                } else {
                    sum += d.price * d.orderQuantity;
                }

                $('.chiTietTable tbody').append(chiTietRow);
                stt++;
            });

            $("#tongTien").text("Tổng : "+ sum+" VND");
        });
        $("#chiTietModal").modal();
    });


    // post request xác nhận hoàn thành đơn hàng
    function ajaxPostXacNhanHoanThanh() {
        $.ajax({
            async:false,
            type : "POST",
            contentType : "application/json",
            url : "http://localhost:3010/toystore/api/order/update?donHangId="+$("#idDonHangXacNhan").val()+"&ghiChu="+$("#ghiChuAdmin").val(),
            enctype: 'multipart/form-data',
            success : function(response) {
                $("#capNhatTrangThaiModal").modal('hide');
                alert("Xác nhận hoàn thành đơn hàng thành công");
            },
            error : function(e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });
    }

    $(document).on('click', '.btnHuy', function (event) {
        event.preventDefault();
        var donHangId = $(this).parent().prev().children().val();
        var confirmation = confirm("Bạn chắc chắn hủy đơn hàng này ?");
        if(confirmation){
            ajaxPostHuyDon(donHangId);
            resetData();
        }
    });

    // post request xác nhận hủy đơn hàng
    function ajaxPostHuyDon(donHangId) {
        $.ajax({
            async:false,
            type : "POST",
            contentType : "application/json",
            url : "http://localhost:3010/toystore/api/order/cancel?orderId="+donHangId,
            success : function(response) {
                alert("Hủy đơn hàng thành công");
            },
            error : function(e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });
    }

    // event khi ẩn modal chi tiết
    $('#chiTietModal,#capNhatTrangThaiModal').on('hidden.bs.modal', function(e) {
        e.preventDefault();
        $("#chiTietForm p").html(""); // reset text thẻ p
        $("#capNhatTrangThaiForm h4").text("");
        $("#ghiChuAdmin").text("");
        $('.chiTietTable #soLuongNhanTag').remove();
        $('.chiTietTable tbody tr').remove();
        $('.chiTietCapNhatTable tbody tr').remove();
    });
});