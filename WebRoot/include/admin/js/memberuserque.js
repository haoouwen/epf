function showque(showstate){
  //showstate:0:显示系统问题  1：显示自定义问题
  if(showstate=="1"){
  //只能使用一种提示问题方式，另一种值为空，并隐藏文本框
	 if(document.getElementById('passwd_selfques')!=null||document.getElementById('passwd_selfques')==null||document.getElementById('passwd_selfques').value==''){
	 document.getElementById('passwd_selfques').value=null;
	 }
	  $("#zidingyiwenti").hide();
	  $("#xitongwenti").show();
  }else if(showstate=="0"){
	 if(document.getElementById('passwd_ques')!=null||document.getElementById('passwd_ques')==null||document.getElementById('passwd_ques').value==''){
	 document.getElementById('passwd_ques').value=null;
	 }
	  $("#xitongwenti").hide();
	   $("#zidingyiwenti").show();
  }
}
//验证所填内容不能为空
 function checkinput(obj)
 {
	if (obj.value==''||obj.value=='请选择')
	  {
	    if(isNaN(obj.value)){
		   obj.value="";
	       obj.focus();
	       jNotify("未填写,请正确输入!"); 
	       return false;
	    }
	 }
 } 

