<!-- form start -->
<form role="form" id="adminForm" name="adminForm">
  <input type="hidden" name="id" value="${admin.id}"
  <div class="box-body">
    <div class="form-group">
      <label for="adminUsername">${field_admin_username}</label>
      <input type="email" name="username" value="${admin.username}" required="required" class="form-control" id="adminUsername" placeholder="Enter user name">
    </div>
  </div>
</form>
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