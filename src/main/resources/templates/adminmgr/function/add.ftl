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
  <form role="form" id="functionAddForm" name="functionAddForm">
    <div class="box-body">
      <div class="form-group">
        <label for="functionName">${field_function_name}</label>
        <input type="input" name="name" required="required" class="form-control" id="functionName" placeholder="Enter function name">
      </div>
      <div class="form-group">
        <label for="functionLink">${field_function_link}</label>
        <input type="input" name="link" class="form-control" id="functionLink" placeholder="Enter function link">
      </div>
      <div class="form-group" id="function-level">
        <label>
        	<input type="radio" name="level" class="minimal" value="1"> ${function_level_1}
        </label>
        <label>
        	<input type="radio" name="level" class="minimal" value="2"> ${function_level_2}
        </label>
        <label>
        	<input type="radio" name="level" class="minimal" value="3"> ${function_level_3}
        </label>
      </div>
      <div class="form-group" id="select-level-2" style="display:none">
        <label>${label_select_2}</label>
        <select class="form-control select2" style="width: 100%;">
          
        </select>
      </div>
      <div class="form-group" id="select-level-3" style="display:none">
        <label>${label_select_3}</label>
        <select class="form-control select2" style="width: 100%;">
          
        </select>
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
  $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
      checkboxClass: 'icheckbox_minimal-blue',
      radioClass: 'iradio_minimal-blue'
  });
  $('#functionAddForm').on('submit', function(event){
	event.preventDefault();
	ajaxSubmitFormJson(this, 'addFunctionSubmit');
  });
  $('form[name="functionAddForm"] input[name="level"]').each(function(){
    $(this).on('ifChecked', function(){
        var level = $(this).val();
        if(level == 3) {
          $('#select-level-2 select').empty();
          $('#select-level-2 select').attr('name', 'parent');
          $('#select-level-2').show(); 
          $('#select-level-3 select').empty(); 
          $('#select-level-3 select').removeAttr('name'); 
          $('#select-level-3').hide();
          $.ajax({
            type:'GET',
            dataType:'JSON',
            url:'getFunctionList/2',
            success:function(data){
              $(data).each(function(i, item){
                var value = item.id;
                var name = item.name;
                $('#select-level-2 select').append('<option value="' + value + '">' + name + '</option>');
              });
            },
            error:function(data){
              alert(data);
            }
          });
        } else if (level == 2) {
          $('#select-level-3 select').empty();
          $('#select-level-3 select').attr('name', 'parent');
          $('#select-level-3').show(); 
          $('#select-level-2 select').empty(); 
          $('#select-level-2 select').removeAttr('name'); 
          $('#select-level-2').hide();
          $.ajax({
            type:'GET',
            dataType:'JSON',
            url:'getFunctionList/1',
            success:function(data){
              $(data).each(function(i, item){
                var value = item.id;
                var name = item.name;
                $('#select-level-3 select').append('<option value="' + value + '">' + name + '</option>');
              });
            },
            error:function(data){
              alert(data);
            }
          });
        }
    });
  });
});
</script>