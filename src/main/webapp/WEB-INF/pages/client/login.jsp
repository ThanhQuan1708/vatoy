<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <meta name="author" content="Hau Nguyen">
    <meta name="keywords" content="au theme template">

    <!-- Title Page-->
    <title>Login</title>

    <!-- Fontfaces CSS-->
    <link href="<c:url value='css/font-face.css'/>   " rel="stylesheet" media="all">
    <link href="<c:url value='vendor/font-awesome-4.7/css/font-awesome.min.css'/> " rel="stylesheet" media="all">
    <link href="<c:url value='vendor/font-awesome-5/css/fontawesome-all.min.css'/> " rel="stylesheet" media="all">
    <link href="<c:url value='vendor/mdi-font/css/material-design-iconic-font.min.css'/> " rel="stylesheet" media="all">

    <!-- Bootstrap CSS-->
    <link href="<c:url value='vendor/bootstrap-4.1/bootstrap.min.css'/> " rel="stylesheet" media="all">

    <!-- Vendor CSS-->
    <link href="<c:url value='vendor/animsition/animsition.min.css'/>" rel="stylesheet" media="all">
    <link href="<c:url value='vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css'/> " rel="stylesheet" media="all">
    <link href="<c:url value='vendor/wow/animate.css'/> " rel="stylesheet" media="all">
    <link href="<c:url value='vendor/css-hamburgers/hamburgers.min.css' />" rel="stylesheet" media="all">
    <link href="<c:url value='vendor/slick/slick.css'/> " rel="stylesheet" media="all">
    <link href="<c:url value='vendor/select2/select2.min.css'/> " rel="stylesheet" media="all">
    <link href="<c:url value='vendor/perfect-scrollbar/perfect-scrollbar.css'/> " rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="<c:url value='css/themes.css'/> " rel="stylesheet" media="all">

</head>

<body class="animsition">
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="page-wrapper">
    <div class="page-content--bge5">
        <div class="container">
            <div class="login-wrap">
                <div class="login-content">
                    <div class="login-logo">
                        <h2 style="text-align: center">LOGIN</h2>
                    </div>
                    <div class="login-form">
                        <form class="login-form" action="${contextPath}/login" method="POST">
                            <div class="form-group">
                                <label>Email Address</label>
                                <input class="au-input au-input--full" type="email" name="email" required="required" placeholder="Email">
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input class="au-input au-input--full" type="password" name="password" required="required" placeholder="Password">
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <div class="login-checkbox">
                                <label>
                                    <input type="checkbox" class="form-check-input" id="" name="remember-me"> Remember me.
                                </label>
                            </div>
                            <button id="submit" class="au-btn au-btn--block au-btn--green m-b-20" type="submit">sign in</button>
                        </form>
                        <div class="register-link">
                            <p>
                                Don't you have account?
                                <a href="<c:url value='/register'/> ">Sign Up Here</a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<!-- Jquery JS-->
<script src="<c:url value='vendor/jquery-3.2.1.min.js'/> "></script>
<!-- Bootstrap JS-->
<script src="<c:url value='vendor/bootstrap-4.1/popper.min.js'/> "></script>
<script src="<c:url value='vendor/bootstrap-4.1/bootstrap.min.js'/> "></script>
    <!-- Vendor JS       -->
    <script src="<c:url value='vendor/slick/slick.min.js'/> ">
    </script>
    <script src="<c:url value='vendor/wow/wow.min.js'/> "></script>
    <script src="<c:url value='vendor/animsition/animsition.min.js' />"></script>
    <script src="<c:url value='vendor/bootstrap-progressbar/bootstrap-progressbar.min.js'/> ">
    </script>
    <script src="<c:url value='vendor/counter-up/jquery.waypoints.min.js'/> "></script>
    <script src="<c:url value='vendor/counter-up/jquery.counterup.min.js'/> ">
    </script>
    <script src="<c:url value='vendor/circle-progress/circle-progress.min.js'/> "></script>
    <script src="<c:url value='vendor/perfect-scrollbar/perfect-scrollbar.js'/> "></script>
    <script src="<c:url value='vendor/chartjs/Chart.bundle.min.js'/> "></script>
    <script src="<c:url value='vendor/select2/select2.min.js'/> ">
    </script>

<!-- Main JS-->
<script src="<c:url value='js/main.js'/> "></script>

</body>

</html>
<!-- end document-->