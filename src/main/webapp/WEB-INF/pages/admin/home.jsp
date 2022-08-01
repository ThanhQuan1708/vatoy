<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
        <title>ADMIN</title>

        <!-- Main CSS-->
        <link href="<c:url value='/css/themes.css'></c:url> " rel="stylesheet" media="all">
    </head>
    <body>
        <jsp:include page="include/header.jsp"></jsp:include>
        <div style="margin: 350px;margin-top: 150px" >
            <a href='<c:url value="/admin/contact" /> '><p style="text-align: center">You have ${todoList.newContactQuantity} Emails</p></a>
            <br>
            <a href='<c:url value="/admin/order" /> '> <p style="text-align: center">You have ${todoList.newOrderQuantity} Order</p></a>

        </div>

        <jsp:include page="include/footer.jsp"></jsp:include>
        <jsp:include page="include/sidebar.jsp"></jsp:include>
    </body>
</html>
