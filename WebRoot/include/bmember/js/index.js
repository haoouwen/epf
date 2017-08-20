$(document).ready(function(){
	//搜索关键字拼音
	$('#keyword').bind('keyup', function(){   
	        var keyword=$('#keyword').val();  
	        if(keyword==undefined){
	           keyword= "";
	        } 
	        var getword=Turnpingyin(keyword)
	        var en_word="";
	        if(getword.length>50){
	           en_word=getword.substring(0,49);
			}else{
			   en_word=getword;
			}
	    	$('#en_word').val(en_word);
  	}) ;
})

//清除搜索框
function cleartt(){
	default_value=$("#keyword").val();
	$("#keyword").val("");
}

//搜索框失去焦点事件
function addtt(){
	if($("#keyword").val()==""){
		$("#keyword").val(default_value);
	}
}

//商品搜索
function sgSubmit(){
     var keyword=$("#keyword").val();
	 var en_wd=$("#en_word").val();
	 var sel = encodeURIComponent(encodeURIComponent(keyword));
     if( keyword!=null&&keyword!=""){
		window.location.href="/Search.html?en_wd="+en_wd+"&wd="+sel;
     }
}	

//会员后台强制设置支付密码
  function checkPayPsw(){
	   var psw=$("#newpasswd").val();
	   var conPsw=$("#confirmpasswd").val();
	   // 密码正则表达式对象
		var repass = new RegExp("[a-zA-Z_0-9]{6,16}", "");
		// 验证 密码是否刚好匹配
		var ispass= repass.test(psw);
	   if(psw==null||psw==""||psw.length<6){
		   alert("请输入6位以上密码!");
		   return false;
	   }
   if(conPsw==null||conPsw==""){
	  	alert("请输入确认密码!");
	  	return false;
   }
   if(!ispass){
        alert("密码应由6-16位字母数字下划线组成！");
        return false;
   }
   if(conPsw!=psw){
   		alert("两次密码输入不一致！");
   		return false;
   }
  }
