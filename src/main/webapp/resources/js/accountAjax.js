$(document).ready(function() {

    // load first when coming page
    ajaxGet(1);

    function ajaxGet(page){
        var data = { name:$("#roles").val()};
        $.ajax({
            type: "GET",
            data: data,
            contentType : "application/json",
            url: "http://localhost:3010/toystore/api/account/all" + '?page=' + page,
            success: function(result){
                $.each(result.content, function(i, account){
                    var row = '<tr>' +
                        '<td>' + account.id+ '</td>' +
                        '<td>' + account.name + '</td>' +
                        '<td>' + account.email + '</td>' +
                        '<td>' + account.tel + '</td>' +
                        '<td>' + account.address + '</td>'+ '<td>';

                    $.each(account.role, function(i, role){
                        row += role.name;
                        row += "<br>";
                    });

                    row +='</td>' +
                        '<td width="0%">'+'<input type="hidden" id="accountId" value=' + account.id + '>'+ '</td>'+
                        '<td><button class="btn btn-danger btnXoa" >Xóa</button></td>';

                    $('.accountTable tbody').append(row);

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
    $('#roles').mouseup(function() {
        var open = $(this).data("isopen");
        if (open) {
            resetData();
        }
        $(this).data("isopen", !open);
    });

    // click thêm tài khoản
    $(document).on('click', '.addAccountBtn', function (event) {
        event.preventDefault();
        $("#accountModal").modal();
    });

    // xác nhận thêm tài khoản
    $(document).on('click', '#btnSubmit', function (event) {
        event.preventDefault();
        ajaxPost();
        resetData();
    });

    function ajaxPost() {
        var data = JSON.stringify($('.accountForm').serializeJSON());
        console.log(data);

        // do post
        $.ajax({
            async:false,
            type : "POST",
            contentType : "application/json",
            url : "http://localhost:3010/toystore/api/account/save",
            enctype: 'multipart/form-data',
            data : data,
            success : function(response) {
                if(response.status == "success"){
                    $('#accountModal').modal('hide');
                    alert("Thêm thành công");
                } else {
                    $('input').next().remove();
                    $.each(response.errorMessages, function(key,value){
                        if(key != "id"){
                            $('input[name='+ key +']').after('<span class="error">'+value+'</span>');
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

    // delete request
    $(document).on("click",".btnXoa", function() {

        var accId = $(this).parent().prev().children().val();
        var confirmation = confirm("Bạn chắc chắn xóa tài khoản này ?");
        if(confirmation){
            $.ajax({
                type : "DELETE",
                url : "http://localhost:3010/toystore/api/account/delete/" + accId,
                success: function(resultMsg){
                    resetData();
                },
                error : function(e) {
                    console.log("ERROR: ", e);
                }
            });
        }
    });

    // event khi ẩn modal form
    $('#accountModal').on('hidden.bs.modal', function(e) {
        e.preventDefault();
        $('.accountForm input').next().remove();
    });

    // reset table after post, put, filter
    function resetData(){
        var page = $('li.active').children().text();
        $('.accountTable tbody tr').remove();
        $('.pagination li').remove();
        ajaxGet(page);
    };

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

    // remove element by class name
    function removeElementsByClass(className){
        var elements = document.getElementsByClassName(className);
        while(elements.length > 0){
            elements[0].parentNode.removeChild(elements[0]);
        }
    }
});