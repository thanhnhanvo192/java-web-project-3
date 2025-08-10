<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 7/27/2025
  Time: 7:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingListURL" value="/admin/building-list" />
<c:url var="buildingAPI" value="/api/building" />
<html>
<head>
    <title>Danh sách toà nhà</title>
</head>
<body>
<div class="main-content">
  <div class="main-content-inner">
    <div class="breadcrumbs" id="breadcrumbs">
breadcrumbs      <script type="text/javascript">
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
    <!--page-header-->

    <div class="row">
      <div class="col-xs-12">
        <div class="widget-box">
          <div class="widget-header">
            <h5 class="widget-title">Tìm kiếm</h5>

            <div class="widget-toolbar">
              <a href="#" data-action="collapse">
                <i class="ace-icon fa fa-chevron-up"></i>
              </a>
            </div>
          </div>

          <div
                  class="widget-body"
                  style="font-family: 'Times New Roman', Times, serif"
          >
            <div class="widget-main">
              <form:form id="listForm" action="${buildingListURL}" modelAttribute="buildingSearchRequest" method="GET">
              <div class="row">
                <div class="form-group">
                  <div class="col-xs-12">
                    <div class="col-xs-6">
                      <label class="name">Tên toà nhà</label>
                      <form:input class="form-control" path="name" />
                    </div>
                    <div class="col-xs-6">
                      <label class="name">Diện tích sàn</label>
                      <form:input class="form-control" path="floorArea" />
                    </div>
                  </div>

                  <div class="col-xs-12">
                    <div class="col-xs-2">
                      <label class="name">Quận</label>
                      <form:select class="form-control" path="district">
                        <form:option value="">--- Chọn quận ---</form:option>
                        <form:options items="${districts}"/>
                      </form:select>
                    </div>
                    <div class="col-xs-5">
                      <label class="name">Phường</label>
                      <form:input class="form-control" path="ward" />
                    </div>
                    <div class="col-xs-5">
                      <label class="name">Đường</label>
                      <form:input class="form-control" path="street" />
                    </div>
                  </div>

                  <div class="col-xs-12">
                    <div class="col-xs-4">
                      <label class="name">Số tầng hầm</label>
                      <form:input class="form-control" path="numberOfBasement" />
                    </div>
                    <div class="col-xs-4">
                      <label class="name">Hướng</label>
                      <form:input class="form-control" path="direction" />
                    </div>
                    <div class="col-xs-4">
                      <label class="name">Hạng</label>
                      <form:input class="form-control" path="level" />
                    </div>
                  </div>

                  <div class="col-xs-12">
                    <div class="col-xs-3">
                      <label class="name">Diện tích từ</label>
                      <form:input class="form-control" path="areaFrom" />
                    </div>
                    <div class="col-xs-3">
                      <label class="name">Diện tích đến</label>
                      <form:input class="form-control" path="areaTo" />
                    </div>
                    <div class="col-xs-3">
                      <label class="name">Giá thuê từ</label>
                      <form:input class="form-control" path="rentPriceFrom" />
                    </div>
                    <div class="col-xs-3">
                      <label class="name">Giá thuê đến</label>
                      <form:input class="form-control" path="rentPriceTo" />
                    </div>
                  </div>

                  <div class="col-xs-12">
                    <div class="col-xs-5">
                      <label class="name">Tên quản lí</label>
                      <form:input class="form-control" path="managerName" />
                    </div>
                    <div class="col-xs-5">
                      <label class="name">SĐT quản lí</label>
                      <form:input class="form-control" path="managerPhone" />
                    </div>
                    <div class="col-xs-2">
                      <label class="name">Nhân viên</label>
                      <form:select class="form-control" path="staffId">
                        <form:option value="">--- Chọn nhân viên ---</form:option>
                        <form:options items="${staffs}" />
                      </form:select>
                    </div>
                  </div>
                  <div class="col-xs-12">
                    <div class="col-xs-6">
                        <form:checkboxes items="${typeCodes}" path="typeCode"/>
                    </div>
                  </div>
                  <div class="col-xs-12">
                    <div class="col-xs-6">
                      <button
                              type="button"
                              class="btn btn-xs btn-danger"
                              id="btnSearchBuilding"
                      >
                        <svg
                                xmlns="http://www.w3.org/2000/svg"
                                width="16"
                                height="16"
                                fill="currentColor"
                                class="bi bi-search"
                                viewBox="0 0 16 16"
                        >
                          <path
                                  d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"
                          ></path>
                        </svg>
                        Tìm kiếm
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </form:form>

            </div>
          </div>

          <div class="pull-right">
            <a href="/admin/building-edit">
              <button class="btn btn-info" title="Thêm toà nhà">
                <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="16"
                        height="16"
                        fill="currentColor"
                        class="bi bi-building-add"
                        viewBox="0 0 16 16"
                >
                  <path
                          d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"
                  />
                  <path
                          d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"
                  />
                  <path
                          d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"
                  />
                </svg>
              </button>
            </a>
            <button class="btn btn-danger" title="Xoá toà nhà" id="btnDeleteBuilding">
              <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="16"
                      height="16"
                      fill="currentColor"
                      class="bi bi-building-dash"
                      viewBox="0 0 16 16"
              >
                <path
                        d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"
                />
                <path
                        d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"
                />
                <path
                        d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"
                />
              </svg>
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Bảng danh sách -->
    <div class="row">
      <div class="col-xs-12">
        <table
                style="margin: 3em 0 1.5em"
                id="tableList"
                class="table table-striped table-bordered table-hover"
        >
          <thead>
          <tr>
            <th class="center">
              <label class="pos-rel">
                <input type="checkbox" class="ace" name="checkList" value=""/>
                <span class="lbl"></span>
              </label>
            </th>
            <th>Tên toà nhà</th>
            <th>Địa chỉ</th>
            <th>Số tầng hầm</th>
            <th>Tên quản lí</th>
            <th>SĐT quản lí</th>
            <th>Diện tích sàn</th>
            <th>Diện tích trống</th>
            <th>Diện tích thuê</th>
            <th>Giá thuê</th>
            <th>Phí dịch vụ</th>
            <th>Phí môi giới</th>
            <th>Thao tác</th>
          </tr>
          </thead>

          <tbody>
          <c:forEach items="${buildingSearchResponseList}" var="item">
          <tr>
            <td class="center">
              <label class="pos-rel">
                <input type="checkbox" class="ace" name="checkList" value="${item.id}"/>
                <span class="lbl"></span>
              </label>
            </td>

            <td>${item.name}</td>
            <td>${item.address}</td>
            <td>${item.numberOfBasement}</td>
            <td>${item.managerName}</td>
            <td>${item.managerPhone}</td>
            <td>${item.floorArea}</td>
            <td>${item.emptyArea}</td>
            <td>${item.rentArea}</td>
            <td>${item.rentPrice}</td>
            <td>${item.serviceFee}</td>
            <td>${item.brokerageFee}</td>

            <td>
              <div class="hidden-sm hidden-xs btn-group">
                <button
                        class="btn btn-xs btn-success"
                        title="Giao toà nhà"
                        onclick="assignmentBuilding(${item.id})"
                >
                  <i class="ace-icon glyphicon glyphicon-align-justify"></i>
                </button>

                <a href="/admin/building-edit-${item.id}" class="btn btn-xs btn-info" title="Sửa toà nhà">
                  <i class="ace-icon fa fa-pencil bigger-120"></i>
                </a>

                <button class="btn btn-xs btn-danger" title="Xoá toà nhà" onclick="deleteBuilding(${item.id})">
                  <i class="ace-icon fa fa-trash-o bigger-120"></i>
                </button>
              </div>
            </td>
          </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
      <!-- /.span -->
    </div>
  </div>
</div>
<!-- /.main-content -->
<!-- Modal -->
<div
      class="modal fade"
      id="assignmentBuildingModal"
      role="dialog"
      style="font-family: 'Times New Roman', Times, serif"
    >
      <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">
              &times;
            </button>
            <h4 class="modal-title">Danh sách nhân viên</h4>
          </div>
          <div class="modal-body">
            <table
              id="staffList"
              class="table table-striped table-bordered table-hover"
            >
              <thead>
                <tr>
                  <th class="center">Chọn</th>
                  <th>Tên nhân viên</th>
                </tr>
              </thead>

              <tbody>
              </tbody>

            </table>
            <input type="hidden" id="buildingId" value=""/>
          </div>

          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-default"
              id="btnAssignmentBuilding"
            >
              Giao toà nhà
            </button>
            <button type="button" class="btn btn-default" data-dismiss="modal">
              Đóng
            </button>
          </div>
        </div>
      </div>
    </div>

<script>
  $('#btnSearchBuilding').click(function (e) {
    e.preventDefault();
    // Lấy dữ liệu từ FORM
    var data = {};
    $('#listForm').serializeArray().forEach(function (field){
      if (data[field.name]) {
        if (!Array.isArray(data[field.name])) {
          data[field.name] = [data[field.name]];
        }
        data[field.name].push(field.value)
      }
      else {
        data[field.name] = field.value;
      }
    });

    $.ajax({
      type: 'POST',
      url: '${buildingAPI}/search',
      data: JSON.stringify(data),
      contentType: 'application/json',
      dataType: 'json',
      success: function (response) {
    // response chính là danh sách toà nhà dạng mảng JSON
    $('#tableList tbody').empty();
    $.each(response, function (index, building){
        var row = '<tr>';
        row += '<td class="center">';
        row += '<label class="pos-rel">';
        row += '<input type="checkbox" class="ace" name="checkList" value="' + building.id + '"/>';
        row += '<span class="lbl"></span>';
        row += '</label>';
        row += '</td>';

        row += '<td>' + building.name + '</td>';
        row += '<td>' + building.address + '</td>';
        row += '<td>' + building.numberOfBasement + '</td>';
        row += '<td>' + building.managerName + '</td>';
        row += '<td>' + building.managerPhone + '</td>';
        row += '<td>' + building.floorArea + '</td>';
        row += '<td>' + building.emptyArea + '</td>';
        row += '<td>' + building.rentArea + '</td>';
        row += '<td>' + building.rentPrice + '</td>';
        row += '<td>' + building.serviceFee + '</td>';
        row += '<td>' + building.brokerageFee + '</td>';
        row += '<td>';
        row += '<div class="hidden-sm hidden-xs btn-group">';
        row += '<button ';
        row += 'class="btn btn-xs btn-success" title="Giao toà nhà" onClick="assignmentBuilding(' + building.id +')">';
        row += '<i class="ace-icon glyphicon glyphicon-align-justify"/>';
        row += '</button>';
        row += '<a href="/admin/building-edit-' + building.id + '" class="btn btn-xs btn-info" title="Sửa toà nhà">';
        row += '<i class="ace-icon fa fa-pencil bigger-120"></i>';
        row += '</a>';
        row += '<button class="btn btn-xs btn-danger" title="Xoá toà nhà" onClick="deleteBuilding(building.id)">';
        row += '<i class="ace-icon fa fa-trash-o bigger-120"></i>';
        row += '</button>';
        row += '</div>';
        row += '</td>';
        row += '</tr>';

        $('#tableList tbody').append(row);
    });
},
      error: function (response) {
        console.log('failed');
        console.log(response);
      }
    })
  });


  function assignmentBuilding(buildingId) {
    $("#assignmentBuildingModal").modal("show");
    loadStaff(buildingId);
    $('#buildingId').val(buildingId);
  }

  function loadStaff(buildingId) {
    $.ajax({
      type: "GET",
      url: "${buildingAPI}/" + buildingId + "/staffs",
      // data: JSON.stringify(data),
      contentType: "application/json",
      dataType: "JSON",
      success: function (response) {
        var row = '';
        $.each(response.data, function (index, item) {
          row += '<tr>';
          row += '<td class="text-center">';
          row += '<input type="checkbox" value="' + item.staffId + '"' + ' id="checkbox_' + item.staffId + '" ' + item.checked + '/>';
          row += '</td>';
          row += '<td>';
          row += item.fullName;
          row += '</td>';
          row += '</tr>';
        });
         $('#staffList tbody').append(row);
        console.log("Success");
      },
      error: function (response) {
        console.log("failed"),
        console.log(response);
      }
    });
  }

  $('#btnAssignmentBuilding').click(function (e) {
    e.preventDefault();
    var data = {};
    data['buildingId'] = $('#buildingId').val();
    var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function(){
      return $(this).val();
    }).get();
    data['staffs'] = staffs;
    if(data['staffs'] != '') {
      assignment(data);
    }

  });
  function assignment(data) {
    $.ajax({
      type: "POST",
      url: "${buildingAPI}/assignment",
      data: JSON.stringify(data),
      contentType: "application/json",
      dataType: "JSON",
      success: function (response){
        console.log("success");
      },
      error: function (response) {
        console.info("Giao không thành công");
        window.location.href = "<c:url value="/admin/building-list?message=error" />";
        console.log(response);
      }
    });
  }

  function deleteBuilding(id) {
    var buildingId = [id];
    deleteBuildings(buildingId);
  }
  $('#btnDeleteBuilding').click(function(e) {
    e.preventDefault();
    var buildingIds = $('#tableList').find('tbody input[type = checkbox]:checked').map(function(){
    return $(this).val();
    }).get();
    deleteBuildings(buildingIds);
  });

  function deleteBuildings(data) {
    $.ajax({
      type : "DELETE",
      url : "${buildingAPI}/" + data,
      data : JSON.stringify(data),
      contentType : "application/json",
      dataType : "JSON",
      success : function (respond) {
        console.log("Success");
      },
      error : function (respond) {
        console.log("failed");
        console.log(respond);
      }
    });
  }

</script>
</body>
</html>
