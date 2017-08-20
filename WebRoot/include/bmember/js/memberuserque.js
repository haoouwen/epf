 var showstate=0;
 function checkinput(){	
		//验证所填内容不能为空
		if($("#flag").val()=="")
		$("#flag").attr("value","0");
		if($("#flag").val()==0){
			if($("#passwd_ques").val()==""|| $("#passwd_ques").val()==null||$("#passwd_ques").val()=='-------请选择-------'){
		       		jAlert("请选择密保问题!","系统提示");
		       		return false;
		     }
	     }
	    if($("#flag").val()==1){ 
	       if($("#passwd_selfques").val()==""|| $("#passwd_selfques").val()==null){
	       		jAlert("请输入密保问题!","系统提示");
	       		return false;
	       }
	     }  
        if($("#apasswd_answer_s").val()==""|| $("#apasswd_answer_s").val()==null){
       		jAlert("请输入密保答案!","系统提示");
       		return false;
       }
 		return true;
 }
	
function showque(showstate){
  //showstate:0:显示系统问题  1：显示自定义问题
  if(showstate=="1"){
  //只能使用一种提示问题方式，另一种值为空，并隐藏文本框
	 if(document.getElementById('passwd_selfques')!=null||document.getElementById('passwd_selfques')==null||document.getElementById('passwd_selfques').value==''){
	 document.getElementById('passwd_selfques').value=null;
	 }
	 $("#flag").attr("value","0");
	  $("#zidingyiwenti").hide();
	  $("#xitongwenti").show();
  }else if(showstate=="0"){
	 if(document.getElementById('passwd_ques')!=null||document.getElementById('passwd_ques')==null||document.getElementById('passwd_ques').value==''){
	 document.getElementById('passwd_ques').value=null;
	 }
	  $("#flag").attr("value","1");
	  $("#xitongwenti").hide();
	   $("#zidingyiwenti").show();
  }
}


