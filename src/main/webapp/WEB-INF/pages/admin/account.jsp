<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Quản lý tài khoản</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value='/css/themes.css'></c:url> " rel="stylesheet" media="all">
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
                            <h3 class="title-5 m-b-35">Quản lý tài khoản</h3>
                            <div class="table-data__tool">
                                <div class="table-data__tool-left">
                                    <button class="au-btn au-btn-icon au-btn--green au-btn--small addAccountBtn">
                                        <i class="fa fa-plus"></i>add item</button>
                                    <div class="rs-select2--dark rs-select2--sm rs-select2--dark2">
                                    </div>

                                </div>
                                <div class="form-group form-inline table-data__tool-right">
                                    <label for="sel1"><strong>Lọc tài khoản:</strong> </label> <select
                                        id="roles" class="form-control">
                                    <c:forEach var="roles" items="${listRole}">
                                        <option value="${roles.name }">${roles.name }</option>
                                    </c:forEach>
                                </select>
                                </div>
                            </div>

                            <div class="table-responsive table-responsive-data2">
                                <table class="table table-data2 accountTable">
                                    <thead>
                                    <tr>
                                        <th>Mã</th>
                                        <th>Họ tên</th>
                                        <th>Email</th>
                                        <th>Số điện thoại</th>
                                        <th>Địa chỉ</th>
                                        <th>Vai trò</th>
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
    </div>
</div>
<div class="row col-md-6">
    <form class="accountForm" id="form">
        <div>
            <div class="modal fade" id="accountModal" tabindex="-1"
                 role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true" data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Tạo mới tài
                                khoản</h5>
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="name">Email</label> <input type="email"
                                                                       class="form-control" name="email" required />
                            </div>
                            <div class="form-group">
                                <label for="name">Mật khẩu:(8-32 ký tự)</label> <input
                                    type="password" class="form-control" name="password" required />
                            </div>
                            <div class="form-group">
                                <label for="name">Nhắc lại mật khẩu:</label> <input type="password"
                                                                                    class="form-control" name="confirmPassword" required />
                            </div>

                            <div class="form-group">
                                <label for="name">Chọn vai trò:</label>
                                <c:forEach var="roles" items="${listRole}">
                                    <label class="radio-inline">
                                        <input type="radio"
                                               name="roleName" value="${roles.name}" checked="checked">
                                            ${roles.name}
                                    </label>
                                </c:forEach>
                            </div>

                            <div class="form-group">
                                <label for="name">Họ tên:</label> <input type="text"
                                                                         class="form-control" name="name" required />
                            </div>
                            <div class="form-group">
                                <label for="name">Số điện thoại:</label> <input type="text"
                                                                                class="form-control" name="tel" required />
                            </div>
                            <div class="form-group">
                                <label for="name">Địa chỉ:</label> <input type="text"
                                                                          class="form-control" name="address" required />
                            </div>


                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary"
                                        data-dismiss="modal">Hủy</button>
                                <input class="btn btn-primary" id="btnSubmit" type="button"
                                       value="Xác nhận" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</div>

<jsp:include page="include/footer.jsp"></jsp:include>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery.serializeJSON/2.9.0/jquery.serializejson.js"></script>
<script src="<c:url value='/js/accountAjax.js'/>"></script>
</body>
</html>