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

                            <h3 class="title-5 m-b-35">data table</h3>
                            <form class="form-inline" id="searchForm" name="searchObject" style="visibility: hidden">

                                <select class="form-control" id="trangThai">
                                    <option value="Đang chờ giao">Đang chờ giao</option>
                                    <option value="Đang giao">Đang giao</option>
                                    <option value="Chờ duyệt">Chờ duyệt</option>
                                    <option value="Hoàn thành">Hoàn thành</option>
                                    <option value="Đã bị hủy">Đã bị hủy</option>
                                    <!-- <option value="">Tất cả</option> -->
                                </select>

                                <div class="form-group">
                                    <input class="form-control" type="text" id="fromDate"
                                           placeholder="Từ ngày">
                                </div>

                                <div class="form-group">
                                    <input class="form-control" type="text" id="toDate"
                                           placeholder="Đến ngày">
                                </div>
                                &nbsp;&nbsp; &nbsp;&nbsp;
                                <button type="button" class="btn btn-primary" id="btnDuyetDonHang">Duyệt
                                    Đơn</button>
                                <div class="form-group" style="float: right; margin-right: 20px;visibility: hidden">
                                    <input  class="form-control" type="number" id="searchById"
                                           placeholder="Nhập mã để tìm nhanh"> <span
                                        class="glyphicon glyphicon-search" aria-hidden="true"
                                        style="left: -30px; top: 4px"></span>
                                </div>
                            </form>
                            <div class="table-responsive table-responsive-data2">
                                <table class="table table-data2 donHangTable">
                                    <thead>
                                    <tr>
                                        <th>Mã</th>
                                        <th>Người nhận</th>
                                        <th>Status</th>
                                        <th>Thành tiền</th>
                                        <th>Ngày đặt</th>
                                        <th>Ngày giao</th>
                                        <th>Ngày nhận</th>
                                        <%--                                            <th>price</th>--%>
                                        <th>Thao tác</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <tr class="spacer"></tr>
                                    </tbody>
                                </table>
                                <ul class="pagination"></ul>
                            </div>
                            <!-- END DATA TABLE -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row col-md-6">
            <form class="chiTietForm">
                <div class="modal fade" id="chiTietModal" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document" style="width: 700px;">
                        <div class="modal-content">
                            <div class="modal-header">
                                <p class="h4 modal-title" id="maDonHang"></p>
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="card" style="padding-left: 40px;padding-right: 40px">

                                            <div class="row pb-5 p-5">
                                                <div class="col-md-6">
                                                    <h5 class="font-weight-bold mb-4">
                                                        <strong>Thông tin khách</strong>
                                                    </h5>
                                                    <p class="mb-1" id="name"></p>
                                                    <p class="mb-1" id="address"></p>
                                                    <p class="mb-1" id="phone"></p>
                                                </div>

                                                <div class="col-md-6 text-right"
                                                     style="text-align: left; padding-left: 100px">
                                                    <h5 class="font-weight-bold mb-4">
                                                        <strong>Thông tin đơn hàng</strong>
                                                    </h5>
                                                    <p class="mb-1" id="status"></p>
                                                    <p class="mb-1" id="orderDate"></p>
                                                    <p class="mb-1" id="deliveryDate"></p>
                                                    <p class="mb-1" id="reciveDate"></p>
                                                </div>
                                            </div>
                                            <hr />
                                            <div class="row p-5">
                                                <div class="col-md-12">
                                                    <table class="table chiTietTable"
                                                           style="text-align: center;">
                                                        <thead>
                                                        <tr>
                                                            <th
                                                                    class="border-0 text-uppercase small font-weight-bold">STT</th>
                                                            <th
                                                                    class="border-0 text-uppercase small font-weight-bold">Tên
                                                                sản phẩm</th>

                                                            <th
                                                                    class="border-0 text-uppercase small font-weight-bold">Đơn
                                                                giá</th>
                                                            <th
                                                                    class="border-0 text-uppercase small font-weight-bold">Số
                                                                lượng đặt</th>

                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>

                                            <div class="d-flex flex-row-reverse bg-dark text-white p-4">
                                                <div class="py-3 px-5 text-right">
                                                    <div class="mb-2">
                                                        <p id="tongTien"></p>
                                                    </div>
                                                </div>
                                            </div>
                                            <hr />

                                            <div class="col-md-6">
                                                <h5 class="font-weight-bold mb-4">
                                                    <strong>Thông tin khác</strong>
                                                </h5>
                                                <p class="mb-1" id="shipper"></p>
                                                <p class="mb-1" id="customer"></p>
                                                <p id="ghiChu"></p>
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
<script type="text/javascript">
    $(function() {

        if (${param.trangThai != null}){
            $("#trangThai").val('${param.trangThai}');
            console.log('${param.trangThai}');
        };

        var from = $("#fromDate").datepicker({
            dateFormat : "dd-mm-yy",
            changeMonth : true
        }).on("change", function() {
            to.datepicker("option", "minDate", getDate(this));
        }), to = $("#toDate").datepicker({
            dateFormat : "dd-mm-yy",
            changeMonth : true
        }).on("change", function() {
            from.datepicker("option", "maxDate", getDate(this));
        });

        function getDate(element) {
            var date;
            var dateFormat = "dd-mm-yy";
            try {
                date = $.datepicker.parseDate(dateFormat, element.value);
            } catch (error) {
                date = null;
            }

            return date;
        }
    });
</script>
<%--    <jsp:include page="include/sidebar.jsp"></jsp:include>--%>
<script src="<c:url value='/js/orderAjax.js'/>"></script>
<script src="<c:url value='js/main.js'/> "></script>
<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>