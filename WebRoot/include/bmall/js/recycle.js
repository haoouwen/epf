//列表批量删除操作
function revertInfo(field_name,actionName){
	//值拼串
	var filedVal = '';	
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			if(checks[i].value!=""){
				filedVal += checks[i].value+',';
			}
		}
	}	
	if(filedVal.indexOf(',') > -1){
		filedVal = filedVal.substring(0,filedVal.length-1);
	}	
	if(filedVal==''){
		art.dialog({
			title: '系统提示',
		    content: '请至少选择一条记录!'
		    
		});
		return false;
	}else{
		art.dialog({
			title: '系统提示',
			content:'<div class="decorate">'+'确定要还原?'+'</div>',
			okValue: '确定',
			width: '238px',
			cancelValue: '取消',
		    ok: function () {
		        document.getElementById('commonForm').action=actionName;
		    	document.getElementById('commonText').name = "chb_id";
		    	document.getElementById('commonText').value = filedVal;
		    	document.getElementById('commonForm').submit();	  
		    },
		    cancel: function () {
				return true;
		    }
		});
	}
}