<div class="box box-primary">
  <div class="box-header with-border">
    <div id="alert-result" class="alert alert-success alert-dismissible" style="display:none">
      <button type="button" class="close" aria-hidden="true" onclick="$('#alert-result').hide()">&times;</button>
      <h4><i class="icon fa fa-check"></i> <label id="info-label"></label></h4>
    </div>
    <h3 class="box-title">${label_operation} ${label_modal_name}</h3>
  </div>
  <!-- /.box-header -->
  <!-- form start -->
  <form role="form" id="adminForm" name="adminForm">
    <div class="box-body">
      <div class="form-group">
        <label for="adminUsername">${field_admin_username}</label>
        <input type="email" name="username" required="required" class="form-control" id="adminUsername" placeholder="Enter user name">
      </div>
      <div class="form-group">
        <label for="adminPassword">${field_admin_password}</label>
        <input type="password" name="password" required="required" class="form-control" id="adminPassword" placeholder="Enter password">
      </div>
      <div class="form-group">
        <label for="adminConfirmPassword">${field_admin_confirm_password}</label>
        <input type="password" name="confimPassword" required="required" class="form-control" id="adminConfirmPassword" placeholder="Enter password again">
      </div>
    </div>
  <!-- /.box-body -->
  <div class="box-footer">
    <button type="submit" class="btn btn-primary">${button_submit}</button>
  </div>
  <!-- /.box-footer -->
  </form>
</div>
<!-- /.box -->
<script>
$(function(){
	$('#adminConfirmPassword').on('focus',function(){
		$('#adminConfirmPassword').parent().removeClass('has-error');
		var errorLabel = $('#adminConfirmPassword').next();
		if (errorLabel != null) {
			errorLabel.remove();
		}
	});
});
$('#adminForm').on('submit', function(event){
	event.preventDefault();
	var password = $('form[name="adminForm"] input[name="password"]').val();
	var confirmPassword = $('form[name="adminForm"] input[name="confimPassword"]').val();
	if (password != confirmPassword) {
		$('#adminConfirmPassword').parent().addClass('has-error');
		$('#adminConfirmPassword').after('<label class="control-label" for="adminConfirmPassword"><i class="fa fa-times-circle-o"></i> 确认密码与密码不一致</label>');
		return false;
	}
	ajaxSubmitFormJson(this, '${action_url}');
});
</script>