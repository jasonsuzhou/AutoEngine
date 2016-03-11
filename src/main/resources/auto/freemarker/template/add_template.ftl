<div class="box box-primary">
  <div class="box-header with-border">
    <div id="alert-result" class="alert alert-success alert-dismissible" style="display:none">
      <button type="button" class="close" aria-hidden="true" onclick="$('#alert-result').hide()">&times;</button>
      <h4><i class="icon fa fa-check"></i> <label id="info-label"></label></h4>
    </div>
    <h3 class="box-title">${add_title}</h3>
  </div>
  <!-- /.box-header -->
  <!-- form start -->
  <form role="form" id="${lowerSimpleClassName}AddForm" name="${lowerSimpleClassName}AddForm">
    <div class="box-body">
      <#list fieldList!>
      <#items as field>
      <div class="form-group">
        <label for="${field.name}">${field.desc}</label>
        <input type="${field.type}" ${field.required!} id="${field.name}" id="${field.name}">
        <p id="help-block-${field.name}"></p>
      </div>
      </#items>
      </#list>
    </div>
  <!-- /.box-body -->
  <div class="box-footer">
    <button type="submit" class="btn btn-primary">${button_title}</button>
  </div>
  <!-- /.box-footer -->
  </form>
</div>
<!-- /.box -->
<script>
$(function(){
  $('#${lowerSimpleClassName}AddForm').on('submit', function(event){
	event.preventDefault();
	ajaxSubmitFormJson(this, 'add${simpleClassName}Submit');
  });
});
</script>