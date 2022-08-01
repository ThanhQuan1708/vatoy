$(document).ready(function() {

    // load first when coming page
    ajaxGet(1);

    // do get
    function ajaxGet(page){
        // prepare data
        var data = $('#searchForm').serialize();
        $.ajax({
            type: "GET",
            data: data,
            contentType : "application/json",
            url: "http://localhost:3010/toystore/api/product/all" + '?page=' + page,
            success: function(result){
                $.each(result.content, function(i, product){
                    var sanPhamRow = '<tr>' +
                        '<td>' + '<img src="/toystore/img/'+product.id+'.png" class="img-responsive" style="height: 50px; width: 50px" />'+'</td>' +
                        '<td>' + product.name + '</td>' +
                        '<td>' + product.category.name + '</td>' +
                        '<td>' + product.brand.name + '</td>' +
                        '<td>' + product.recAge.name + '</td>' +
                        '<td>' + product.price + '</td>' +
                        '<td>' + product.inStorage + '</td>' +
                        '<td width="0%" style="padding: 0px">'+'<input type="hidden" id="sanPhamId" value=' + product.id + '>'+ '</td>' +
                        '<td style="padding: 0px"> <button class="btn btn-warning btnChiTiet" style="margin-right: 6px"><i class="fa fa-eye"/></button>' ;

                    sanPhamRow += '<button class="item btn btn-primary btnCapNhatOther" ><i class="fa fa-pencil"/></button>';
                    sanPhamRow += '  <button class="item btn btn-danger btnXoaSanPham"><i class="fa fa-remove"/></button></td>'+'</tr>';
                    $('.productTable tbody').append(sanPhamRow);
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

    // event khi click vào dropdown chọn danh mục thêm sản phẩm
    $('#danhMucDropdown').mouseup(function() {
        var open = $(this).data("isopen");
        if (open) {
            var label = $('#danhMucDropdown option:selected').text();
                $('.otherModal').modal('show');
                $("#idDanhMucKhac").val($(this).val());
                $('#otherForm').removeClass().addClass("addOtherForm");
                $('#otherForm #btnSubmit').removeClass().addClass("btn btn-primary btnSaveOtherForm");
            // }
            $(".modal-title").text("Thêm mới sản phẩm danh mục "+ label);

        }
        $(this).data("isopen", !open);
    });

    $(document).on('click', '#btnDuyetSanPham', function (event) {
        event.preventDefault();
        $('.productTable tbody tr').remove();
        $('.pagination li').remove();
        ajaxGet(1);

    });


    // event khi ẩn modal form
    $('.lapTopModal, .otherModal').on('hidden.bs.modal', function(e) {
        e.preventDefault();
        $("#idDanhMucLaptop, #idDanhMucKhac").val("");
        $("#idSanPhamLapTop, #idSanPhamKhac").val("");

        // $('#lapTopForm').removeClass().addClass("lapTopForm");
        // $('#lapTopForm #btnSubmit').removeClass().addClass("btn btn-primary");
        // $('#lapTopForm').trigger("reset");

        $('#otherForm').removeClass().addClass("otherForm");
        $('#otherForm #btnSubmit').removeClass().addClass("btn btn-primary");
        $('#otherForm').trigger("reset");
        $('input, textarea').next().remove();
    });


    // btnSaveOtherForm event click
    $(document).on('click', '.btnSaveOtherForm', function (event) {
        event.preventDefault();
        ajaxPostOther();
        resetData();
    });

    function ajaxPostOther() {
        // PREPATEE DATA
        var form = $('.addOtherForm')[0];
        var data = new FormData(form);
        console.log(data);
        // do post
        $.ajax({
            async:false,
            type : "POST",
            contentType : "application/json",
            url : "http://localhost:3010/toystore/api/product/save",
            enctype: 'multipart/form-data',
            data : data,

            // prevent jQuery from automatically transforming the data into a
            // query string
            processData: false,
            contentType: false,
            cache: false,
            timeout: 1000000,

            success : function(response) {
                if(response.status == "success"){
                    $('.otherModal').modal('hide');
                    alert("Thêm thành công");
                } else {
                    $('input, textarea').next().remove();
                    $.each(response.errorMessages, function(key,value){
                        if(key != "id"){
                            $('input[name='+ key +']').after('<span class="error">'+value+'</span>');
                            $('textarea[name='+ key +']').after('<span class="error">'+value+'</span>');
                        };
                    });
                }

            },
            error : function(e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });
    }



    // xu ly nut cap nhat
    $(document).on("click",".btnCapNhatOther", function(event){
        event.preventDefault();
        var sanPhamId = $(this).parent().prev().children().val();
        $('#otherForm').removeClass().addClass("updateOtherForm");
        $('#otherForm #btnSubmit').removeClass().addClass("btn btn-primary btnUpdateOtherForm");

        var href = "http://localhost:3010/toystore/api/product/"+sanPhamId;
        $.get(href, function(sanPham) {
            populate('.updateOtherForm', sanPham);
            $("#idDanhMucKhac").val(sanPham.category.id);
            var hangSXId = sanPham.brand.id;
            $("#nhaSXIdKhac").val(hangSXId);
        });
        removeElementsByClass("error");
        $('.updateOtherForm .otherModal').modal();
    });

    // btnUpdateOtherForm event click
    $(document).on('click', '.btnUpdateOtherForm', function (event) {
        event.preventDefault();
        ajaxPutOther();
        resetData();
    });

    function ajaxPutOther() {
        // PREPARE DATA
        var form = $('.updateOtherForm')[0];
        var data = new FormData(form);
        // do put
        $.ajax({
            async:false,
            type : "POST",
            contentType : "application/json",
            url : "http://localhost:3010/toystore/api/product/save",
            enctype: 'multipart/form-data',
            data : data,

            // prevent jQuery from automatically transforming the data into a
            // query string
            processData: false,
            contentType: false,
            cache: false,
            timeout: 1000000,

            success : function(response) {
                if(response.status == "success"){
                    $('.otherModal').modal('hide');
                    alert("Cập nhật thành công");
                } else {
                    $('input, textarea').next().remove();
                    $.each(response.errorMessages, function(key,value){
                        if(key != "id"){
                            $('input[name='+ key +']').after('<span class="error">'+value+'</span>');
                            $('textarea[name='+ key +']').after('<span class="error">'+value+'</span>');
                        };
                    });
                }

            },
            error : function(e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });
    }


    // click vào button xóa
    $(document).on("click",".btnXoaSanPham", function() {

        var sanPhamId = $(this).parent().prev().children().val();
        var workingObject = $(this);

        var confirmation = confirm("Bạn chắc chắn xóa sản phẩm này ?");
        if(confirmation){
            $.ajax({
                async:false,
                type : "DELETE",
                url : "http://localhost:3010/toystore/api/product/delete/" + sanPhamId,
                success: function(resultMsg){
                    resetDataForDelete();
                    alert("Xóa thành công");
                },
                error : function(e) {
                    console.log("ERROR: ", e);
                }
            });
        }
        resetData();
    });

    // click vào button chi tiết
    $(document).on("click",".btnChiTiet", function() {

        var sanPhamId = $(this).parent().prev().children().val();
        console.log(sanPhamId);

        var href = "http://localhost:3010/toystore/api/product/"+sanPhamId;
        $.get(href, function(sanPham) {
            $('.hinhAnh').attr("src", "/toystore/img/"+sanPham.id+".png");
            $('.tenSanPham').html("<span style='font-weight: bold'>Tên sản phẩm: </span> "+ sanPham.name);
            $('.maSanPham').html("<span style='font-weight: bold'> Mã sản phẩm: </span>"+ sanPham.id);
            $('.hangSangXuat').html("<span style='font-weight: bold'>Thương hiệu: </span>"+ sanPham.brand.name);
            $('.thongTinChung').html("<span style='font-weight: bold'>Thông tin chung: </span>"+ sanPham.description);
            $('.donGia').html("<span style='font-weight: bold'>Đơn giá: </span>"+ sanPham.price +" VNĐ");
            $('.baoHanh').html("<span style='font-weight: bold'>Vật liệu: </span>"+ sanPham.material);
            $('.donViKho').html("<span style='font-weight: bold'>Đơn vị trong kho: </span>"+ sanPham.inStorage);
            $('.donViBan').html("<span style='font-weight: bold'>Xuất xứ: </span>"+ sanPham.origin);

        });

        $('#chiTietModal').modal('show');

    });

    // reset table after delete
    function resetDataForDelete(){
        var count = $('.productTable tbody').children().length;
        $('.productTable tbody tr').remove();
        var page = $('li.active').children().text();
        $('.pagination li').remove();
        console.log(page);
        if(count == 1){
            ajaxGet(page -1 );
        } else {
            ajaxGet(page);
        }

    };

    // reset table after post, put, filter
    function resetData(){
        var page = $('li.active').children().text();
        $('.productTable tbody tr').remove();
        $('.pagination li').remove();
        ajaxGet(page);
    };

    // event khi click vào phân trang Sản phẩm
    $(document).on('click', '.pageNumber', function (event){
        event.preventDefault();
        var page = $(this).text();
        $('.productTable tbody tr').remove();
        $('.pagination li').remove();
        ajaxGet(page);
    });


    // event khi click vào nhấn phím vào ô tìm kiếm sản phẩm theo tên
    // $(document).on('keyup', '#searchById', function (event){
    //     event.preventDefault();
    //     var sanPhamId = $('#searchById').val();
    //     console.log(sanPhamId);
    //     if(sanPhamId != ''){
    //         $('.productTable tbody tr').remove();
    //         $('.pagination li').remove();
    //         var href = "http://localhost:8080/laptopshop/api/san-pham/"+sanPhamId;
    //         $.get(href, function(sanPham) {
    //             var sanPhamRow = '<tr>' +
    //                 '<td>' + '<img src="/laptopshop/img/'+sanPham.id+'.png" class="img-responsive" style="height: 50px; width: 50px" />'+'</td>' +
    //                 '<td>' + sanPham.tenSanPham + '</td>' +
    //                 '<td>' + sanPham.danhMuc.tenDanhMuc + '</td>' +
    //                 '<td>' + sanPham.hangSanXuat.tenHangSanXuat + '</td>' +
    //                 '<td>' + sanPham.donGia + '</td>' +
    //                 '<td>' + sanPham.donViKho + '</td>' +
    //                 '<td width="0%">'+'<input type="hidden" id="sanPhamId" value=' + sanPham.id + '>'+ '</td>' +
    //                 '<td><button class="btn btn-warning btnChiTiet" style="margin-right: 6px">Chi tiết</button>'  ;
    //
    //             var checkTenDanhMuc = (sanPham.danhMuc.tenDanhMuc.toLowerCase()).indexOf("Laptop".toLowerCase());
    //             sanPhamRow += ( checkTenDanhMuc != -1)?'  <button class="btn btn-primary btnCapNhatLapTop" >Cập nhật</button>':'<button class="btn btn-primary btnCapNhatOther" >Cập nhật</button>';
    //             sanPhamRow += ' <button class="btn btn-danger btnXoaSanPham">Xóa</button></td>'+'</tr>';
    //             $('.productTable tbody').append(sanPhamRow);
    //         });
    //     } else {
    //         resetData();
    //     }
    // });

    // fill input form với JSon Object
    function populate(frm, data) {
        $.each(data, function(key, value){
            $('[name='+key+']', frm).val(value);
        });
    }

    // event khi ẩn modal chi tiết
    $('#chiTietModal').on('hidden.bs.modal', function(e) {
        e.preventDefault();
        $(".chiTietForm p").text(""); // reset text thẻ p
    });

    // remove element by class name
    function removeElementsByClass(className){
        var elements = document.getElementsByClassName(className);
        while(elements.length > 0){
            elements[0].parentNode.removeChild(elements[0]);
        }
    }

    (function ($) {
        $.fn.serializeFormJSON = function () {

            var o = {};
            var a = this.serializeArray();
            $.each(a, function () {
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        };
    })(jQuery);

});