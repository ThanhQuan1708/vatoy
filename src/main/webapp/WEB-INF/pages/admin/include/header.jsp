    <%@ page language="java" contentType="text/html; charset=utf-8"
             pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>
        <link rel='stylesheet' href='http://cdnjs.cloudflare.com/ajax/libs/animate.css/3.2.3/animate.min.css'>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="<c:url value='client/css/style.css' />" />
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    </head>
    <body>
    <div class="page-container">
        <header class="header-desktop">
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <div class="header-wrap">
                        <form class="form-header" action="" method="POST" style="visibility: hidden">
                            <input class="au-input au-input--xl" type="text" name="search" placeholder="Search for datas &amp; reports..." />
                            <button class="au-btn--submit" type="submit">
                                <i class="zmdi zmdi-search"></i>
                            </button>
                        </form>
                        <div class="header-button">
                            <div class="noti-wrap">
                                <div class="noti__item js-item-menu">
                                    <i class="fa fa-envelope"></i>
                                    <span class="quantity">${todoList.newContactQuantity}</span>
                                    <div class="email-dropdown js-dropdown">
                                        <div class="email__title">
                                            <p>You have ${todoList.newContactQuantity} New Emails</p>
                                        </div>
                                        <div class="email__footer">
                                            <a href='<c:url value="/admin/contact" /> '>See all emails</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="noti__item js-item-menu">
                                    <i class="fa fa-bell"></i>
                                    <span class="quantity">${toDoList.newOrderQuantity}</span>
                                    <div class="notifi-dropdown js-dropdown">
                                        <div class="notifi__title">
                                            <p>You have ${toDoList.newOrderQuantity} Notifications</p>
                                        </div>
                                        <div class="notifi__footer">
                                            <a href='<c:url value="/admin/order" /> '>All notifications</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="account-wrap">
                                <div class="account-item clearfix js-item-menu">
                                    <div class="content">
<%--                                        <p></p>--%>
                                        <a class="js-acc-btn" href="<c:url value='/admin/profile'/> ">Xin chào, ${loggedInUser.name} <i class="fa fa-angle-down"></i></a>
                                        <a href="<c:url value='/logout'/>">
                                            <i class="fa fa-power-off"></i>Logout</a>
                                    </div>
                                    <div class="account-dropdown js-dropdown">
                                        <div class="info clearfix">
                                            <div class="content"> <a href="<c:url value='/admin/profile'/> ">
                                                <span class="email">${loggedInUser.email}</span></a>
                                            </div>
                                            <a href="<c:url value='/logout'/>">
                                                <i class="fa fa-power-off"></i>Logout</a>

                                        </div>
                                        <div class="account-dropdown__body">
                                            <div class="account-dropdown__item">
                                                <a href="<c:url value='/admin/profile'/> ">
                                                    <i class="fa fa-user"></i>Account</a>
                                            </div>
                                        </div>
                                        <div class="account-dropdown__footer">
                                            <a href="<c:url value='/logout'/>">
                                                <i class="fa fa-power-off"></i>Logout</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    </div>
    <!-- Main JS-->
    <script src="<c:url value='js/main.js'/> "></script>
<%--    </body>--%>

<%--</body>--%>
<%--</html>--%>