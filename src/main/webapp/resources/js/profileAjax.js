$(document).ready(function(){

    // click event button Cap nhật thông tin
    $('.updateInfoBtn').on("click", function(event) {
        event.preventDefault();
        var userId = $(".userId").val();

        var href = "http://localhost:3010/toystore/api/profile/"+userId;
        $.get(href, function(user, status) {
            populate('.updateForm', user);
        });

        $('.updateForm #updateModal').modal();
    });

    // fill input form với JSon Object
    function populate(form, data) {
        $.each(data, function(key, value){
            if(key != "id"){
                $('[name='+key+']', form).val(value);
            }
        });
    }

    $('.changePassBtn').on("click", function(event) {
        event.preventDefault();
        removeElementsByClass("error");
        $('.changePassForm #changePassModal').modal();
    });

    $(document).on('click', '#btnSubmitCP', function(event) {
        event.preventDefault();
        removeElementsByClass("error");
        ajaxPostChangePass();
    });

    function ajaxPostChangePass() {
        // PREPATEE DATA
        var data = $('.changePassForm').serializeFormJSON();
        // do post
        $.ajax({
            async:false,
            type : "POST",
            contentType : "application/json",
            url : "http://localhost:3010/toystore/api/profile/change-password",
            data : JSON.stringify(data),
            success : function(response) {
                if(response.status == "success"){
                    $('#changePassModal').modal('hide');
                    alert("Đổi mật khẩu thành công. Bạn phải đăng nhập lại để xác nhận");
                    location.href = "http://localhost:3010/toystore/logout";
                } else {
                    $('input').next().remove();
                    $.each(response.errorMessages, function(key,value){
                        $('input[name='+ key +']').after('<span class="error">'+value+'</span>');
                    });
                }
            },
            error : function(e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });
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

    function removeElementsByClass(className){
        var elements = document.getElementsByClassName(className);
        while(elements.length > 0){
            elements[0].parentNode.removeChild(elements[0]);
        }
    }
})