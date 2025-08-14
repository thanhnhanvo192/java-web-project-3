<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 7/27/2025
  Time: 7:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building" />
<html>
<head>
    <title>Thêm toà nhà</title>
</head>
<body>
<div class="main-content" id="main-container">
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check("breadcrumbs", "fixed");
                    } catch (e) {}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>
                    <li class="active">Dashboard</li>
                </ul>
                <!-- /.breadcrumb -->
            </div>

            <div class="page-content">
                <!-- /.ace-settings-container -->

                <div class="page-header">
                    <h1>
                        Dashboard
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            overview &amp; stats
                        </small>
                    </h1>
                </div>

                <div class="row">
                    <div class="col-xs-12"></div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <form:form class="form-horizontal" id="form-edit" modelAttribute="buildingEdit" method="GET">
                            <div class="form-group">
                                <label class="col-xs-3">Thêm toà nhà</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="name" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Quận</label>
                                <div class="col-xs-3">
                                    <form:select class="form-control" path="district">
                                        <form:option value="">--- Chọn quận ---</form:option>
                                        <form:options items="${districts}"/>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phường</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="ward"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Đường</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="street"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Kết cấu</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="structure"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Số tầng hầm</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="numberOfBasement"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Diện tích sàn</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="floorArea"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Hướng</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="direction"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Hạng</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="level"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Diện tích thuê</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="rentArea"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Giá thuê</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="rentPrice"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Mô tả giá</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="rentPriceDescription"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phí dịch vụ</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="serviceFee"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phí ô tô</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="carFee"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phí mô tô</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="motoFee"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phí ngoài giờ</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="overtimeFee"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Tiền điện</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="electricityFee"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Đặt cọc</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="deposit"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Thanh toán</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="payment"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Thời hạn thuê</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="rentTime"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Thời gian trang trí</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="decorationTime" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Tên quản lí</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="managerName" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">SĐT quản lí</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="managerPhone" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phí môi giới</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="brokerageFee" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Loại toà nhà</label>
                                <div class="col-xs-9">
                                    <form:checkboxes items="${typeCodes}" path="typeCode"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Ghi chú</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="note" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3"></label>
                                <div class="col-xs-9">
                                <c:if test="${empty buildingEdit.id}"><button type="button" class="btn btn-info" id="btnAddBuilding">
                                        Thêm toà nhà
                                    </button>
                                </c:if>
                                <c:if test="${not empty buildingEdit.id}"><button type="button" class="btn btn-info" id="btnUpdateBuilding">
                                        Sửa toà nhà
                                    </button>
                                </c:if>
                                    <button type="button" class="btn btn-danger" id="btnCancel">
                                        Huỷ thao tác
                                    </button>
                                </div>
                            </div>
                            <form:hidden path="id" id="buildingId" />
                        </form:form>
                    </div>
                </div>
            </div>
            <!--page-header-->
        </div>
    </div>
    <!-- /.main-content -->
    <a
            href="#"
            id="btn-scroll-up"
            class="btn-scroll-up btn btn-sm btn-inverse"
    >
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div>
<!-- /.main-container -->

<script>
    function saveBuilding() {
        var data = {};
        var typeCode = [];
        var formData = $("#form-edit").serializeArray();
        $.each(formData, function (i, v) {
            if (v.name != "typeCode") {
                data["" + v.name + ""] = v.value;
            } else {
                typeCode.push(v.value);
            }
        });
        data["typeCode"] = typeCode;
        $.ajax({
            type : "POST",
            url : "${buildingAPI}/addOrUpdate",
            data : JSON.stringify(data),
            contentType : "application/json",
            dataType : "JSON",
            success : function(respond) {
                console.log("Success");
            },
            error : function(respond) {
                console.log("failed");
                console.log(respond);
            }
        });
    }
    $('#btnAddBuilding, #btnUpdateBuilding').click(saveBuilding);

    $("#btnCancel").click(function () {
        window.location.href = "/admin/building-list";
    });
</script>
</body>
</html>
