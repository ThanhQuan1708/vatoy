<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<aside class="menu-sidebar d-none d-lg-block">
    <div class="logo">
        <a href="<c:url value='/admin'/>">
            <img src="../img/logo.png" alt="VATOY" />
        </a>
    </div>
    <div class="menu-sidebar__content js-scrollbar1">
        <nav class="navbar-sidebar">
            <ul class="list-unstyled navbar__list">
                <li>
                    <a href="<c:url value='/admin/order' /> ">
                        <i class="fa fa-table"></i>Quản lý đơn hàng</a>
                </li>
                <li>
                    <a href="<c:url value='/admin/category' />">
                        <i class="fa fa-table"></i>Quản lý thể loại</a>
                </li>
                <li>
                    <a href="<c:url value='/admin/brand' />">
                        <i class="fa fa-table"></i>Quản lý thương hiệu</a>
                </li>
                <li>
                    <a href="<c:url value='/admin/rec-age' />">
                        <i class="fa fa-table"></i>Quản lý độ tuổi</a>
                </li>
                <li>
                    <a href="<c:url value='/admin/product' />">
                        <i class="fa fa-table"></i>Quản lý sản phẩm</a>
                </li>
                <li>
                    <a href="<c:url value='/admin/contact' />">
                        <i class="fa fa-table"></i>Quản lý liên hệ</a>
                </li>
                <li>
                    <a href="<c:url value='/admin/account' />">
                        <i class="fa fa-table"></i>Quản lý tài khoản </a>
                </li>
                <li>
                    <a href="<c:url value='/admin/profile' />">
                        <i class="fa fa-table"></i>Thông tin tài khoản</a>
                </li>
            </ul>
        </nav>
    </div>
</aside>
<script src="<c:url value='/js/main.js'/>" ></script>