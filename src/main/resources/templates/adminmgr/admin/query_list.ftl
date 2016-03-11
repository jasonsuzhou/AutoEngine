<div class="modal fade" id="adminDeleteConfirmModal" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">${delete_modal_title}</h4>
        </div>
        <div class="modal-body">
          <p>${delete_modal_body}</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default pull-left" data-dismiss="modal">${button_cancel}</button>
          <button type="button" id="adminDeleteOKButton" class="btn btn-danger">${button_ok}</button>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="adminEditModal" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">${modify_modal_title}</h4>
        </div>
        <div class="modal-body" id="adminEditModalBody">
          
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default pull-left" data-dismiss="modal">${button_cancel}</button>
          <button type="button" id="adminEditOKButton" class="btn btn-success">${button_savechange}</button>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="box">
  <div class="box-header">
    <h3 class="box-title">${admin_list_table_title}</h3>
  </div><!-- /.box-header -->
  <div class="box-body">
    <table id="admin_list" class="table table-bordered table-striped">
      <thead>
        <tr>
        <#list adminColumnNameList>
        <#items as adminColumnName>
          <th>${adminColumnName}</th>
        </#items>
        </#list>
        </tr>
      </thead>
      <tbody>
      <#list adminList>
      <#items as admin>
        <tr>
          <td>
              <button type="button" class="btn btn-danger btn-xs" data-toggle="modal" data-target="#adminDeleteConfirmModal">${operation_delete}</button>
              <button type="button" onclick="showAdminEditModal(${admin.id})" class="btn btn-info btn-xs">${operation_edit}</button>
          </td>
          <td>${admin.id}</td>
          <td>${admin.username}</td>
          <td>${admin.password}</td>
          <td>${admin.salt!}</td>
        </tr>
      </#items>
      </#list>
      </tbody>
      <tfoot>
        <tr>
        <#list adminColumnNameList>
        <#items as adminColumnName>
          <th>${adminColumnName}</th>
        </#items>
        </#list>
        </tr>
      </tfoot>
    </table>
  </div><!-- /.box-body -->
</div><!-- /.box -->
<script>
  function showAdminEditModal(id) {
    $.ajax({
		  type:'get',
		  url:'editAdmin/'+id,
		  dataType:'HTML',
		  success:function(data) {
		    var adminEditModalBody = $('#adminEditModalBody');
		    adminEditModalBody.empty();
		    adminEditModalBody.append(data);
		  },
		  error:function(data) {
			alert(data);
		  }
	});
    $('#adminEditModal').modal('show');
  };
  $(function(){
  	  var deleteKey = -1;
  	  var currentRow = null;
      var adminListTable = $("#admin_list").DataTable(
        {
          "dom": '<"toolbar">frt<"bottom"lip<"clear">>',
          "pagingType": "full_numbers",
          "bScrollCollapse":true,
          "sScrollX":"100%",
          "bAutoWidth":false,
          "language":${jquery_data_table_language}
        }
      );
      adminListTable.column(0).visible(false);
      adminListTable.column(1).visible(false);
      $("div.toolbar").html('<div class="btn-group">' +
                               '<button id="adminListEditButton" type="button" class="btn btn-default">${operation_edit}</button>' +
                               '<button id="adminListAddButton" type="button" class="btn btn-default">${operation_add}</button>' +
                             '</div>');
  	  $("#adminListEditButton").on('click', function(){
        adminListTable.column(0).visible(!adminListTable.column(0).visible());
      });
      $('#adminListAddButton').on('click', function(){
        requestServer('addAdmin');
      });
      $('#admin_list tbody').on( 'click', 'tr', function () {
        currentRow = adminListTable.row(this);
        var rowData = currentRow.data();
        deleteKey = rowData[1];
      });
      
      $('#adminDeleteOKButton').on('click', function(){
        $.ajax({
		  type:'get',
		  url:'delAdmin/'+deleteKey,
		  dataType:'json',
		  success:function(data) {
		    if(data.status == 200) {
			  currentRow.remove();
			  adminListTable.draw();
			} else {
			  alert(data.message);
			}
		  },
		  error:function(data) {
			alert(data);
		  }
	    });
        $('#adminDeleteConfirmModal').modal('hide');
      });
  });
</script>