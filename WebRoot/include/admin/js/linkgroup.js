function updateLinkgroup(actionName){  // 值拼串
	var admin_group_id='';
    var str="";
	$("input[name='link_group.id']:checkbox").each(function(){ 
	    if($(this).attr("checked")){
	     var ckid= $(this).val();
	     admin_group_id+=ckid+",";
	     str+=$("#"+ckid).val()+",";	        
	    }
	})
	// 删除最后一个字符
	admin_group_id = deleteLastChar(admin_group_id,",");
	if(admin_group_id==''){
		art.dialog({
			title: '系统提示',
		    content: '请至少选择一条修改信息!'
		});
		return false;
	}else{
         art.dialog({
			title: '系统信息提示',
		    content: '<div class="decorate">您确定要修改吗？</div>',
		    width: '15%',
		    okValue: '确定',
			cancelValue: '取消',
		    ok: function () {
					str = deleteLastChar(str,",");
		            document.getElementById('admin_linkgroup_id').value = admin_group_id;//用于隐藏所有的ID
					document.getElementById('name').value = str;
					document.getElementById('indexForm').action=actionName;
					document.getElementById('indexForm').submit();
		            return false;
		    },
		   cancel: true //为true等价于function(){}
		    })
      }
  }
      
   
      
      
      function check(){ 
 	    if($("#groupname").val()==""){
	 	$("#nameerror").html("友情链接分组不能为空,请认真填写!");
	 	return false;
       }
       return true;
     }