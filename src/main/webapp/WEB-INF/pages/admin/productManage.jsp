<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Quản lý sản phẩm</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
        <div class="section__content section__content--p30" style="left: 300px">
            <div class="container-fluid">
                <div class="row m-t-30">
                    <div class="row">
                        <div class="col-md-10">
                            <!-- DATA TABLE -->
                            <h3 class="title-5 m-b-35">Quản lý sản phẩm</h3>
                            <div class="table-data__tool">

                                    <div class="table-data__tool-left">
                                        <div class="form-group form-inline">
                                            <label for="sel1">THÊM MỚI SẢN PHẨM (THEO DANH MỤC): </label> <select
                                                id="danhMucDropdown" class="form-control">
                                            <c:forEach var="cate" items="${listCategory}">
                                                <option value="${cate.id }">${cate.name }</option>
                                            </c:forEach>
                                        </select>
                                        </div>
                                        <hr>

                                        <form class="form-inline" id="searchForm" name="searchObject">
                                            <div class="form-group">
                                                <select class="form-control" name="categoryId" id="danhMuc">
                                                    <option value="">Tất cả </option>
                                                    <c:forEach var="cate" items="${listCategory }">
                                                        <option value="${cate.id}">${cate.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="form-group">
                                                <select class="form-control" name="brandId" id="hangSanXuat">
                                                    <option value="">Tất cả thương hiệu</option>
                                                    <c:forEach var="brand" items="${listBrand }">
                                                        <option value="${brand.id}">${brand.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <select class="form-control" name="recAgeId" id="recAge">
                                                    <option value="">Tất cả tuổi</option>
                                                    <c:forEach var="rec" items="${listRecAge }">
                                                        <option value="${rec.id}">${rec.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <select class="form-control" name="price" id="price" style="display: none">
                                                    <option value="">Tất cả giá</option>
                                                    <option value="duoi 100.000">Dưới 100.000</option>
                                                    <option value="tu-100.000-den-200.000">100.000 đến 200.000</option>
                                                    <option value="tu-200.000-den-500.000">100.000 đến 500.000</option>
                                                    <option value="tren-500.000">trên 500.000</option>
                                                </select>
                                            </div>

                                            <div class="form-group">
                                                <select class="form-control" name="sortByPrice">
                                                    <option value="asc">Sắp xếp theo giá tăng dần</option>
                                                    <option value="desc">Sắp xếp theo giá giảm dần</option>
                                                </select>
                                            </div>

                                            &nbsp;&nbsp;
                                            <button type="button" class="btn btn-primary" id="btnDuyetSanPham">Duyệt
                                                sản phẩm</button>
                                        </form>
                                    </div>

                                <div class="table-data__tool-right">
<%--                                    <button class="au-btn au-btn-icon au-btn--green au-btn--small" id="addBtn">--%>
<%--                                        <i class="fa fa-plus"></i>add item</button>--%>
<%--                                    <div class="rs-select2--dark rs-select2--sm rs-select2--dark2">--%>
<%--                                    </div>--%>
                                </div>
                            </div>
                            <div class="table-responsive table-responsive-data2">
                                <table class="table table-data2 productTable">
                                    <thead>
                                    <tr>
                                        <th>Ảnh</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Thể loại</th>
                                        <th>Thương hiệu</th>
                                        <th>Độ tuổi</th>
                                        <th>Đơn giá</th>
                                        <th>Số lượng</th>
                                        <th></th>
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
            <form id="lapTopForm" class="lapTopForm">
                <div class="modal fade lapTopModal" tabindex="-1" role="dialog"
                     data-keyboard="false" data-backdrop="static">
                    <div class="modal-dialog modal-lg" role="document"
                         style="width: 60%;">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Thêm mới/ Cập
                                    nhật Laptop</h5>
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div>
                                        <input type="hidden" class="form-control" name="danhMucId"
                                               id="idDanhMucLaptop">
                                    </div>
                                    <div>
                                        <input type="hidden" class="form-control" name="id"
                                               id="idSanPhamLapTop" readonly>
                                    </div>
                                    <div class="form-group col-md-7">
                                        <label for="inputPassword4">Tên sản phẩm</label> <input
                                            type="text" class="form-control" name="tenSanPham">
                                    </div>
                                    <div class="form-group col-md-5">
                                        <label for="inputPassword4">Đơn giá</label> <input
                                            type="number" class="form-control" name="donGia" min="0"
                                            value="0" id="test">
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="form-group col-md-4">
                                        <label for="inputState">Hãng Sản Xuất</label> <select
                                            name="nhaSXId" class="form-control" id="nhaSXId">
                                        <c:forEach var="nhanHieu" items="${listNhanHieu }">
                                            <option value="${nhanHieu.id}">${nhanHieu.tenHangSanXuat}</option>
                                        </c:forEach>
                                    </select>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="inputPassword4">Hệ điều hành</label> <input
                                            type="text" class="form-control" name="heDieuHanh">
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label for="inputEmail4">Ram</label> <input type="text"
                                                                                    class="form-control" name="ram" required="required">
                                    </div>

                                </div>

                                <div class="row">

                                    <div class="form-group col-md-4">
                                        <label for="inputPassword4">Màn hình</label> <input type="text"
                                                                                            class="form-control" name="manHinh" required="required">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="inputEmail4">CPU</label> <input type="text"
                                                                                    class="form-control" name="cpu">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="inputPassword4">Thiết kế</label> <input type="text"
                                                                                            class="form-control" name="thietKe" required="required">
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="form-group col-md-12">
                                        <label for="inputPassword4">Mô tả chung</label>
                                        <textarea class="form-control" name="thongTinChung"
                                                  placeholder="" rows="2" required="required"></textarea>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="form-group col-md-4">
                                        <label for="inputEmail4">Dung lượng Pin</label> <input
                                            type="text" class="form-control" name="dungLuongPin"
                                            required="required">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="inputEmail4">Thông tin bảo hành</label> <input
                                            type="text" class="form-control" name="thongTinBaoHanh"
                                            required="required">
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label for="inputPassword4">Số lượng</label> <input
                                            type="number" class="form-control" name="donViKho" min="0"
                                            required="required" value="0" id="test2">
                                    </div>
                                </div>
                                <div>
                                    <label for="inputEmail4">Hình ảnh</label> <input type="file"
                                                                                     class="form-control" id="inputEmail4" name="hinhAnh">
                                </div>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">Hủy</button>
                                <input class="btn btn-primary" id="btnSubmit" type="button"
                                       value="Xác nhận" />
                            </div>

                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
            </form>
        </div></div>
        <div class="row col-md-6">
            <form class="otherForm" id="otherForm">
                <div class="modal fade otherModal" tabindex="-1" role="dialog">
                    <div class="modal-dialog modal-lg" role="document"
                         style="width: 60%;">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel"></h5>
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div>
                                        <input type="hidden" class="form-control" name='id_category'
                                               id="idDanhMucKhac">
                                    </div>
                                    <div>
                                        <input type="hidden" class="form-control" name="id"
                                               id="idSanPhamKhac" readonly="true">
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="inputPassword4">Tên sản phẩm</label> <input
                                            type="text" class="form-control" name="name">
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="form-group col-md-6">
                                        <label for="inputState">Hãng Sản Xuất</label> <select
                                            name="id_brand" id="nhaSXIdKhac" class="form-control">
                                        <c:forEach var="brand" items="${listBrand }">
                                            <option value="${brand.id }">${brand.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                        </div>
                                    <div class="form-group col-md-6">
                                        <label for="inputState">Độ tuổi</label> <select
                                            name="id_recAge" id="idRecAge" class="form-control">
                                        <c:forEach var="rec" items="${listRecAge }">
                                            <option value="${rec.id }">${rec.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                            </div>
                                <div class="row">
                                    <div class="form-group col-md-4">
                                        <label for="inputEmail4">Chủ đề</label> <input
                                            type="text" class="form-control" id="inputEmail4"
                                            name="topic">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="inputEmail4">Xuất xứ</label> <input
                                            type="text" class="form-control" id="inputEmail4"
                                            name="origin">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="inputEmail4">Vật liệu</label> <input
                                            type="text" class="form-control" id="inputEmail4"
                                            name="material">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-md-12">
                                        <label for="inputPassword4">Mô tả chung</label>
                                        <textarea class="form-control" id="inputPassword4"
                                                  placeholder="" rows="2" name="description"></textarea>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-md-6">
                                        <label for="inputPassword4">Đơn giá</label> <input
                                            type="number" class="form-control" name="price" min="0"
                                            value="0" id="donGiaKhac">
                                    </div>


                                    <div class="form-group col-md-6">
                                        <label for="inputPassword4">Số lượng</label> <input
                                            type="number" class="form-control" name="inStorage" min="0"
                                            value="0">
                                    </div>
                                </div>
                                <div>
                                    <label for="inputEmail4">Hình ảnh</label> <input type="file"
                                                                                     class="form-control" name="image">
                                </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">Hủy</button>
                                <button class="btn btn-primary" id="btnSubmit" type="submit">Xác
                                    nhận</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
            </form>
        </div></div>
<div class="row col-md-10">
    <form class="chiTietForm">
        <div class="modal fade" id="chiTietModal" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog " role="document" style="width: 60%">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3 class="title" style="text-align: center; font-weight: bolder;">Chi tiết sản phẩm</h3>
                        <button type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="card">
                            <div class="container-fliud">
                                <div class="wrapper row">
                                    <div class="preview col-md-6">

                                        <div class="preview-pic tab-content">
                                            <div class="tab-pane active" id="pic-1">
                                                <img style="width: 350px; height: 350px" class="hinhAnh" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="details col-md-6" style="font-size: 16px">
                                        <p class="maSanPham"></p>
                                        <p class="tenSanPham"></p>
                                        <p class="hangSangXuat"></p>
                                        <p class="cpu"></p>
                                        <p class="ram"></p>
                                        <p class="thietKe"></p>
                                        <p class="heDieuHanh"></p>
                                        <p class="manHinh"></p>
                                        <p class="dungLuongPin"></p>
                                        <p class="thongTinChung"></p>
                                        <p class="baoHanh"></p>
                                        <p class="donGia"></p>
                                        <p class="donViKho"></p>
                                        <p class="donViBan"></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary"
                                data-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
    </div>
</div>

<jsp:include page="include/footer.jsp"></jsp:include>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery.serializeJSON/2.9.0/jquery.serializejson.js"></script>
<script src="<c:url value='/js/productAjax.js'/>"></script>
</body>
</html>