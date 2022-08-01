<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Quản lý độ tuổi</title>
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
    <link type="text/css" href="<c:url value="/css/themes.css"></c:url> " rel="stylesheet" media="all">
</head>
<body class="animsition">
<div class="page-wrapper">
    <jsp:include page="include/header.jsp"></jsp:include>
    <jsp:include page="include/sidebar.jsp"></jsp:include>
    <div class="main-content">
        <div class="section__content section__content--p30" style="left: 300px">
            <div class="container-fluid">
                <div class="row m-t-30">
                    <div class="row">
                        <div class="col-md-9">
                            <!-- DATA TABLE -->
                            <h3 class="title-5 m-b-35">data table</h3>
                            <div class="table-responsive table-responsive-data2">
                                <table class="table table-data2">
                                    <thead>
                                    <tr>
                                        <th>Mã</th>
                                        <th>Email</th>
                                        <th>Ngày gửi</th>
                                        <th>Status</th>
                                        <th>Ngày trả lời</th>
                                        <%--                                            <th>price</th>--%>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr class="tr-shadow">
                                        <td>Lori Lynch</td>
                                        <td>
                                            <span class="block-email">lori@example.com</span>
                                        </td>
                                        <td>2018-09-27 02:12</td>
                                        <td>
                                            <span class="status--process">Processed</span>
                                        </td>
                                        <td>2018-09-27 02:12</td>
                                        <%--                                            <td>$679.00</td>--%>
                                        <td>
                                            <div class="table-data-feature">
                                                <button class="item" data-toggle="tooltip" data-placement="top" title="Detail">
                                                    <i class="fa fa-eye"></i>
                                                </button>
                                                <button class="item" data-toggle="tooltip" data-placement="top" title="Reply">
                                                    <i class="zmdi zmdi-edit"></i>
                                                </button>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr class="spacer"></tr>
                                    </tbody>
                                </table>
                            </div>
                            <!-- END DATA TABLE -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value='js/main.js'/> "></script>
<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>