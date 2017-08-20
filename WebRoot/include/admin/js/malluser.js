function ajaxSubmit(){
	$.ajax({
          type: "post",
          data:{'type':"joinus"},		      
          url: "/malluser!setIs_joinus.action",
          datatype:"json",
          async:false,
          success: function(data){ 
          	 $("#malluser").submit();
          },error:function(){
          	jNotify("过滤失败!");
          }
	});  
}          