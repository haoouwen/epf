function selectissend(obj,temp_code,type){
	var is_enable='0';
	if(obj.checked==false){
		 is_enable='1';     
	}
	$.ajax({
          type: "post",
          data:{'type':type,'msg_code':temp_code,'selcheck_val':is_enable},		      
          url: "/messagealert!upstate.action",
          datatype:"json",
          async:false,
          success: function(data){ 
          	 if(is_enable=='0'){
          	 	jSuccess("启用信息模板成功!");
          	 }else{
          	 	jSuccess("禁用信息模板成功!");
          	 }
          },error:function(){
          	jNotify("修改失败!");
          }
	});  
}          