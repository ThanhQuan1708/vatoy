$(document).ready(function(){

    // add button event
    $('.addCateBtn').on("click", function(event) {
        event.preventDefault();
        $('.cateForm #cateModal').modal();
        $('.cateForm #id').prop("disabled", true);
        $('#form').removeClass().addClass("addForm");
        $('#form #btnSubmit').removeClass().addClass("btn btn-primary btnSaveForm");
    });

    // event khi hide modal
    $('#cateModal').on('hidden.bs.modal', function () {
        $('#form').removeClass().addClass("cateForm");
        $('#form #btnSubmit').removeClass().addClass("btn btn-primary");
        resetText();
    });

    // reset text trong form
    function resetText(){
        $("#id").val("");
        $("#cateName").val("");
    };


    ajaxGet(1);

    // do get
    function ajaxGet(page){
        $.ajax({
            type: "GET",
            url: "http://localhost:3010/toystore/api/category/all" + "?page=" + page,
            success: function(result){
                $.each(result.content, function(i, cate){
                    var cateRow = '<tr style="text-align: center;">' +
                        '<td width="20%">' + cate.id + '</td>' +
                        '<td>' + cate.name + '</td>' +
                        '<td>'
                        +'<div class="table-data-feature">'+
                        '<input type="hidden" value=' + cate.id + '>'+
                        '<button class="item updateBtn"'+ 'data-toggle="tooltip" data-placement="top" title="Edit">'+
                        '<i class="fa fa-edit"></i>'+
                        '</button>'+
                        '<button class="item deleteBtn"'+ 'data-toggle="tooltip" data-placement="top" title="Delete">'+
                        '<i class="fa fa-remove"></i>'+
                        '</button>'
                    '</div>'
                    '</tr>';
                    $('.categoryTable tbody').append(cateRow);
                });

                if(result.totalPages > 1 ){
                    for(var numberPage = 1; numberPage <= result.totalPages; numberPage++) {
                        var li = '<li class="page-item "><a class="pageNumber">'+numberPage+'</a></li>';
                        $('.pagination').append(li);
                    };

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

    $(document).on('click', '.btnSaveForm', function (event) {
        event.preventDefault();
        ajaxPost();
        resetData();
    });

    function ajaxPost(){
        var formData = { name : $("#cateName").val() } ;
        // DO POST
        $.ajax({
            async:false,
            type : "POST",
            contentType : "application/json",
            url : "http://localhost:3010/toystore/api/category/save",
            data : JSON.stringify(formData),
            success : function(response) {
                if(response.status == "success"){
                    $('#cateModal').modal('hide');
                    alert("Thêm thành công");
                } else {
                    $('input').next().remove();
                    $.each(response.errorMessages, function(key,value){
                        $('input[id='+ key +']').after('<span class="error">'+value+'</span>');
                    });
                }

            },
            error : function(e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });
    };

    // click edit button event
    $(document).on("click",".updateBtn",function(){
        event.preventDefault();
        $('.cateForm #id').prop("disabled", true);
        var cateId = $(this).parent().find('input').val();
        $('#form').removeClass().addClass("updateForm");
        $('#form #btnSubmit').removeClass().addClass("btn btn-primary btnUpdateForm");
        var href = "http://localhost:3010/toystore/api/category/"+cateId;
        $.get(href, function(cate, status) {
            $('.updateForm #id').val(cate.id);
            $('.updateForm #cateName').val(cate.name);
        });

        removeElementsByClass("error");

        $('.updateForm #cateModal').modal();
    });

    // put request
    $(document).on('click', '.btnUpdateForm', function (event) {
        event.preventDefault();
        ajaxPut();
        resetData();
    });


    function ajaxPut(){
        var formData = {
            id : $('#id').val(),
            name : $("#cateName").val(),
        }
        // DO PUT
        $.ajax({
            async:false,
            type : "PUT",
            contentType : "application/json",
            url : "http://localhost:3010/toystore/api/category/update",
            data : JSON.stringify(formData),
            success : function(response) {

                if(response.status == "success"){
                    $('#cateModal').modal('hide');
                    alert("Cập nhật thành công");
                } else {
                    $('input').next().remove();
                    $.each(response.errorMessages, function(key,value){
                        $('input[id='+ key +']').after('<span class="error">'+value+'</span>');
                    });
                }
            },
            error : function(e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });
    };

    // delete request
    $(document).on("click",".deleteBtn", function() {

        var cateId = $(this).parent().find('input').val();
        var confirmation = confirm("Bạn chắc chắn xóa danh mục này ?");
        if(confirmation){
            $.ajax({
                type : "DELETE",
                url : "http://localhost:3010/toystore/api/category/delete/" + cateId,
                success: function(resultMsg){
                    resetDataForDelete();
                    alert("Xóa thành công");
                },
                error : function(e) {
                    alert("Không thể xóa danh mục này ! Hãy kiểm tra lại");
                    console.log("ERROR: ", e);
                }
            });
        }
    });

    function resetData(){
        $('.categoryTable tbody tr').remove();
        var page = $('li.active').children().text();
        $('.pagination li').remove();
        ajaxGet(page);
    };

    // reset table after delete
    function resetDataForDelete(){
        var count = $('.categoryTable tbody').children().length;
        console.log("số cột " + count);
        $('.categoryTable tbody tr').remove();
        var page = $('li.active').children().text();
        $('.pagination li').remove();
        console.log(page);
        if(count == 1){
            ajaxGet(page -1 );
        } else {
            ajaxGet(page);
        }

    };

    // phan trang
    $(document).on('click', '.pageNumber', function (event){
        var page = $(this).text();
        $('.categoryTable tbody tr').remove();
        $('.pagination li').remove();
        ajaxGet(page);
    });


    function removeElementsByClass(className){
        var elements = document.getElementsByClassName(className);
        while(elements.length > 0){
            elements[0].parentNode.removeChild(elements[0]);
        }
    }
});