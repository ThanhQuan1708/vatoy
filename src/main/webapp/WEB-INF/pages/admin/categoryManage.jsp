<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Quản lý thể loại</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <!-- <%--    <meta name="author" content="Hau Nguyen">--%> -->
    <meta name="keywords" content="au theme template">

    <!-- Fontfaces CSS-->
    <link href="css/font-face.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
    <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

    <!-- Bootstrap CSS-->
    <link href="vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

    <!-- Vendor CSS-->
    <link href="vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
    <link href="vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
    <link href="vendor/wow/animate.css" rel="stylesheet" media="all">
    <link href="vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
    <link href="vendor/slick/slick.css" rel="stylesheet" media="all">
    <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="<c:url value='/css/themes.css'></c:url> " rel="stylesheet" media="all">
</head>
<body class="animsition">
<div class="page-wrapper">
    <jsp:include page="include/header.jsp"></jsp:include>
    <jsp:include page="include/sidebar.jsp"></jsp:include>
    <div class="main-content">
        <div class="section__content section__content--p30"style="left: 300px">
            <div class="container-fluid">
                <div class="row m-t-30">
                    <div class="row">
                        <div class="col-md-6">
                            <h3 class="title-5 m-b-35">Quản lý thể loại</h3>
                            <div class="table-data__tool">
                                <div class="table-data__tool-right">
                                    <button class="au-btn au-btn-icon au-btn--green au-btn--small addCateBtn">
                                        <i class="fa fa-plus"></i>add item</button>
                                    <div class="rs-select2--dark rs-select2--sm rs-select2--dark2">
                                    </div>
                                </div>
                            </div>
                            <div class="table-responsive table-responsive-data2">
                                <table class="table table-data2 categoryTable">
                                    <thead>
                                    <tr>
                                        <th>Mã</th>
                                        <th>Tên loại</th>
                                        <th>Thao tác</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <tr class="spacer"></tr>
                                    </tbody>
                                </table>
                                <ul class="pagination" style="text-align: center">
                                </ul>
                            </div>
                            <!-- END DATA TABLE -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row col-md-6">
            <form class="cateForm" id="form">
                <div>
                    <div class="modal fade" id="cateModal" tabindex="-1"
                         role="dialog" aria-labelledby="exampleModalLabel"
                         aria-hidden="true" data-backdrop="static" data-keyboard="false">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h2 class="modal-title" id="exampleModalLabel">Tạo mới / Cập nhật</h2>
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="id">ID:</label> <input type="text"
                                                                           class="form-control" id="id" disabled/>
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Tên:</label> <input type="text"
                                                                              class="form-control" id="cateName" placeholder="Nhập vào tên loại" required />
                                    </div>

                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary"
                                                data-dismiss="modal">Hủy</button>
                                        <input class="btn btn-primary" id="btnSubmit" type="button"
                                               value="Xác nhận"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="<c:url value='js/main.js'/> "></script>
<script src="<c:url value='/js/categoryAjax.js'/> "></script>
<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>