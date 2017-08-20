var isto_seng=true;
/***********************触屏版商城注册第一步，验证用户名，密码，验证码，注册协议***************************************/

function AreaNameIsNull(){
   
   $("#areaError").html("");
   $("#areaError").removeClass();
   if($.trim($("#membernum").val()) == ""){
      $("#areaError").html("请选择地区，注册后地区不可修改!");
      $("#areaError").addClass("not");
      return false;
   }
   return true;
}

//获取焦点验证用户名
function UserNameForm(){
   //$("#user_name").val("");
   $("#user_nameError").html("");
   $("#user_nameError").removeClass();
   if($.trim($("#user_name").val()) == ""){
      $("#user_nameError").addClass("warning");
   }
}

//失去焦点验证用户名
function UserNameIsNull(){
    $("#user_nameError").html("");
	$("#user_nameError").removeClass();
     var reg_username = /^[a-zA-Z][a-zA-Z0-9|_]{3,20}$/;//由数字、26个英文字母或者下划线组成的字符串
     var reg_usernamelen=/^(\w){3,20}$/;//只能输入6-20个字母、数字、下划线
     var reg_usernamenum=/^[0-9][a-zA-Z0-9|_]{3,20}$/;//只能输入6-20个字母、数字、下划线
    if ($.trim($("#user_name").val()) == "") {
		$("#user_nameError").html("用户名不能为空!");
		$("#user_nameError").addClass("not");
		return false;
	}else if($("#user_name").val().length	<4){
	    $("#user_nameError").html("用户名不能小于4个字符!");
	    $("#user_nameError").addClass("not");
		return false;
	}else if(!checkUserName()){
	    $("#user_nameError").addClass("not");
	    return false;
	}else{
	    $("#user_nameError").html("");
	    $("#user_nameError").addClass("yes");
	    return true;
	}
}

//当在用户名文本输入框失去焦点的时候，进行异步验证
function checkUserName() {  
        var tips=true;
	    var user_name = $("#user_name").val();
		$.ajax({
		type:"post", 
		url:"/webapp/memberuser!checkUserName.action?user_name=" + user_name, 
		datatype:"json",
		async:false, 
		success:function (data) {
			if (data == "1") { 
				$("#user_nameError").html("该用户名已经被注册了，请重新输入!");
				tips=false;
			}else if(data == "2"){
                $("#user_nameError").html("本系统不允许此类用户名注册，请重新输入!");
                tips = false;
             }else{
			   $("#user_nameError").html("");
			   tips=true;
			}
		  }
	   });
	   return tips;
}

//密码获取焦点时
function PasswordForm(){
   $("#passwd").attr("Type","password");
   $("#passwdError").html("");
   $("#passwdError").removeClass();
   if($.trim($("#passwd").val()) == ""){
      $("#passwdError").html("请输入6-20个英文字母、数字或符号!");
      $("#passwdError").addClass("warning");
   }
}

//失去焦点验证密码
function PasswdIsNull(){
    $("#passwdError").html("");
    $("#passwdError").removeClass();
   // var reg_passwd=/^(\w){6,20}$/;//只能输入6-20个字母、数字、下划线
    if ($.trim($("#passwd").val()) == "") {
		$("#passwdError").html("密码不能为空!");
		$("#passwdError").addClass("not");
		return false;
	}
//	else if(!reg_passwd.test($("#passwd").val())){
//	    $("#passwdError").html("密码只能由6-20个字母、数字、下划线组成!");
//	    $("#passwdError").addClass("not");
//		return false;
//	}
	else if($.trim($("#passwd").val()) == ($.trim($("#user_name").val()))){
	    $("#passwdError").html("密码不能与用户名一样!");
	    $("#passwdError").addClass("not");
		return false;
	}else{
	    $("#passwdError").html("");
	    $("#passwdError").addClass("yes");
	    return true;
	}
}

//获取焦点验证确认密码
function Confirm_pwdForm(){
   $("#confirm_pwd").attr("Type","password");
   $("#confirm_pwdError").html("");
   if($.trim($("#confirm_pwd").val()) == ""){
     $("#confirm_pwdError").html("请重复输入密码!");
   }
}

//失去焦点验证确认密码
function Confirm_pwdIsNull(){
    $("#confirm_pwdError").html("");
    $("#confirm_pwdError").removeClass("warning");
    if($.trim($("#confirm_pwd").val()) == ""){
	    $("#confirm_pwdError").html("确认密码不能为空!");
	    return false;
	}else if($.trim($("#passwd").val()) != $.trim($("#confirm_pwd").val())) {
	    $("#confirm_pwdError").html("密码与确认密码不一致!");
		return false;
    }else{
        $("#confirm_pwdError").html("");
		return true;
    }
}


//获取焦点验证码
function RandsForm(){
   $("#randsError").html("");
   
}

//失去焦点验证码
function RandsIsNull(){
    $("#randsError").html("");
    if ($.trim($("#commentrand").val()) == "") {
		$("#randsError").html("图形验证码不能为空!");
		return false;
	}else if(!validateCode()){
	    return false;
	}else{
	    return true;
	}
}

//当验证码输入框失去焦点时，进行异步验证
function validateCode() {
	    var tips=true;
	    var commentrand = $("#commentrand").val();
		$.ajax({
		   type:"post", 
		   url:"/mall/member!validateCode.action?commentrand=" + commentrand+"&ajaxRequestRandom="+Math.random(), 
		   datatype:"json", 
		   async:false, 
		   success:function (data) {
			if (data == "1") {
				$("#randsError").html("验证码不正确，请重新输入!");
				tips=false;
			}else{
			    $("#randsError").html("");
			    tips=true;
			}
		}});
		return tips;
}

//获取后台生成的验证码
function changeValidateCode(obj) {   
        //获取当前的时间作为参数，无具体意义   
	var timenow = new Date().getTime();   
        //每次请求需要一个不同的参数，否则可能会返回同样的验证码
        //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
	obj.src = "/imgrand.action?d=" + timenow;
}


//同意商城注册协议
function agreement(){
   var isChecked = $("#agreeId").attr("checked");
	if(isChecked){
		return true;
	}
	else{
	    alert("请同意服务协议!");
		return false;
	}
}

//验证表单所有元素
function checkFromFirst(){
   var flag = true;
   var registertype = $("#registertype").val();
   if(registertype=="" || registertype==null || registertype=="undefined"){
       if(!AreaNameIsNull()){
          flag = false;
       }
       
   }
   if(!CellphoneIsNull()){
  		 flag = false;
   }
   if(!PasswdIsNull()){
      flag = false;
   }
   if(!Confirm_pwdIsNull()){
      flag = false;
   }
   
   if(!RandsIsNull()){
       flag = false;
   }
   if(!cpIsNull()){
      flag = false;
   }
   
   if(!agreement()){
      flag = false;
   }
   return flag;
}

//注册第一步：提交表单
function registerFirst(){
  if(checkFromFirst()){
     document.forms[0].action = "/webapp/memberuser!registerSecond.action";
	 document.forms[0].submit();
	 return true;
  }else{
    return false;   
  }
}


/******************触屏版注册第二步，手机，手机校验码，验证*******************************************/
var timer ;//停止定时器

//验证手机号错误提示
function CellphoneForm(){

}


//验证手机号码正确性与唯一性
function CellphoneIsNull(){
   $("#phoneError").html("");
   $("#phoneError").removeClass("not");
   var reg_phone = /^0{0,1}(1[0-9]{10})$/;//手机格式正则表达式
   var value = $("#phone").val();
   if ($.trim(value) == "") {
		$("#phoneError").html("手机号码不能为空!");
		$("#phoneError").addClass("not");
		return false;
	}else if(value!='' && !reg_phone.test(value)){
	    $("#phoneError").html("手机号码格式不正确!");
	    $("#phoneError").addClass("not");
		return false;
   }else if(!onlyphone(value)){
	    $("#user_nameError").addClass("not");
	    return false;
   }else{
	    $("#phoneError").html("");
	    $("#phoneError").addClass("yes");
		return true;
	}
}

//验证手机的唯一性
function onlyphone(p_val){
 	var tips=true;
	$.ajax({
		type:"post", 
		url:"/webapp/memberuser!checkPhone.action?phone=" + p_val, 
		datatype:"json",
		async:false, 
		success:function (data) {
				if (data == "1") { 
				    $("#phoneError").html("该手机号码已经注册了，请重新输入!");
					tips=false;
				}else{
				   $("#phoneError").html("");
				   tips=true;
				}
		  	}
		});
	return tips;
}


//发送验证码
function sendcode(){
	if(!isto_seng){
		return false;
	}
	 var password = $("#passwd").val();
	 var confirm_pwd = $("#confirm_pwd").val();
	 var commentrand = $("#commentrand").val();
     var phone=$("#phone").val();
     var fage=true;
	   if(!PasswdIsNull()){
	       fage=false;
	   }
	   if(!Confirm_pwdIsNull()){
	       fage=false;
	   }
	   if(!CellphoneIsNull()){
	      fage=false;
	    }
		if(!RandsIsNull()){
		   fage=false;
	 	}
	 if(fage==true){
		 $.ajax({
		 type:"post", 
		 url:"/webapp/memberuser!sendPhoneCode.action?phone=" + phone+"&password="+password+"&confirm_pwd="+confirm_pwd+"&commentrand="+commentrand+"&c_type=1", 
		 datatype:"json",
		 async:false, 
		 success:function (data) {
	          if(data=='1'){
	              alert("发送成功,请收到信息后输入验证码，30分钟内有效。");
		          isto_seng=false;
				  $("#timeMinus").val("60");
				  timer=window.setInterval("run();", 1000);
	          }else if(data=='0'){
	              alert("验证码获取失败,请正确填写注册信息");
	              isto_seng=true;
	          }
		  	}
		 });
	 }
}

//定时发送验证码
function run(){
	var s = $("#timeMinus").val();
	var is=s*1;
	if(is>=1){
		if(is == 1){
			$("#timeMinus").val(is - 1);
		//	$("#cpc").attr('disabled',false);
			//$("#cpc").val("获取验证码");
			isto_seng=true;
		   $("#cpc").html("获取短信验证码");
			clearInterval(timer);
		}else{
			$("#timeMinus").val(is - 1)
			//$("#cpc").attr('disabled',true);
			//$("#cpc").val("还有"+(is-1)+"秒");
			 $("#cpc").html((is-1)+"秒后重新获取");
		}
	}
}


//验证手机验证码的正确性
function cpIsNull(){
	$("#cpError").html("");
	var eid="#cpError";
	var cp_="#cpc";
	var tips=true;
	var cp_check=$("#cp_check").val();
	var phone=$("#phone").val();
	if(cp_check==""){
		$("#cpError").html("验证码不能为空!");
		return false;
	}
	
	$.ajax({
		type:"post", 
		url:"/webapp/memberuser!checkPhoneCode.action?phone=" + phone+"&cp_check="+cp_check, 
		datatype:"json",
		async:false, 
		success:function (data) {
				if(data==1){
					tips=false;
					$(eid).html("验证码输入错误，请重新输入!");
				    $(eid).addClass("not");
				}else if(data==2){
					tips=false;
					$(eid).html("验证码已过时，请重新获取!");
				    $(eid).addClass("not");
				}else{
				    tips=true;
					$(cp_).val("获取验证码!");
					$(eid).addClass("yes");
						return true;
				}
		  	}
		});
	return tips;
}



//注册第二步：手机和手机验证码
function registerSecond(){
   var flag = true;
   var cp_check="";
   if(!CellphoneIsNull()){
  		 flag = false;
   }
   if(!RandsIsNull()){
       flag = false;
   }
   if(!cpIsNull()){
      flag = false;
   }
//   if(flag){
//   		document.forms[0].action = "/webapp/memberuser!registerSecond.action";
//	 	document.forms[0].submit();
//	 	return true;
//   }else{
   		return flag;
//   }
}

/******************触屏版注册第三步完成*******************************************/


/*********************************触屏版商城注册 结束*************************************************************/

//找回密码通过email或手机发送邮箱
function send(){
	var sel=$("#aa").val();
	if(sel=='0'){
	var cphone = $("#phone").val();
		$.ajax({
		type:"post", 
		url:"/portal/member!sendPhoneCode.action?phone=" + cphone+"&defag="+cphone, 
		datatype:"json",
		async:false, 
		success:function (data) {
				alert("发送成功,请接到信息后输入验证码!");
				$("#cpc").attr('disabled',true);
				$("#timeMinus").val("60");
				timer=window.setInterval("run();", 1000);
		  	}
		});
	}else if(sel=='1'){
		var email = $("#email").val();
		$.ajax({
		type:"post", 
		url:"/portal/member!sendEmailCode.action?email=" + email, 
		datatype:"json",
		async:false, 
		success:function (data) {
				alert("发送成功,请查收邮箱后输入验证码!");
				$("#cpe").attr('disabled',true);
				$("#timeMinus").val("60");
				timer=window.setInterval("run();", 1000);
		  	}
		});
	}
}
//失去焦点验证电子邮件
function EmailIsNull(){
   $("#emailError").html("");
   $("#emailError").removeClass();
   var reg_email = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;//电子邮箱正则表达式
   if ($.trim($("#email").val()) == "") {
		$("#emailError").html("电子邮箱不能为空");
		$("#emailError").addClass("not");
		return false;
	}else if(!reg_email.test($("#email").val())){
	    $("#emailError").html("电子邮箱格式不正确!");
	    $("#emailError").addClass("not");
		return false;
	}
	/*
	else if(!checkEmail()){
	    $("#emailError").addClass("not");
	    return false;
	}*/
	else{
	    $("#emailError").html("");
	    $("#emailError").addClass("yes");
	    return true;
	}
}

//获取焦点验证邮箱
function EmailForm(value){
	$("#phone").val("");
	$("#cp_check").val("");
   $("#emailError").html("");
   $("#emailError").removeClass("not");
   if($.trim($("#email").val()) == ""){
     $("#emailError").html("请输入常用的电子邮箱,便于日后找回密码!");
     $("#emailError").addClass("warning");
   }
}
//清空错误文本内容
function ClearErrorInfo() {
    $("#user_nameError").html("");
	$("#passwdError").html("");
	$("#confirm_pwdError").html("");
	$("#emailError").html("");
	$("#randsError").html("");
}
	
//异步验证邮箱是否已被注册过
function checkEmail(){
      var tips = true;
      var  email = $("#email").val();
      $.ajax({
        type:"post",
        url:"/mall/member!checkEmail.action?email="+email,
        datatype:"json",
        async:false,
        success:function(data){
            if(data == "1"){
               $("#emailError").html("邮箱已被占用，请重新输入!");
               tips = false;
            }else{
               $("#emailError").html("");
			   tips = true;
            }
        }
      });
      return tips;
 }

//$(document).ready(function(){
//	var step_flag = $("#step_flag").val();
//	if(step_flag=="3"){
//	   auto_show(); 
//	}                   
//});


 var timeout = 10;
 function auto_show() {
     var showbox = $("#secondSuccess");
     var user_name = $("#user_name").val();
     var passwd = $("#login_passwd").val();
     showbox.html(timeout);
     timeout--;
     if (timeout == 0) {
         //window.opener = null;
         window.location.href = "/webapp/memberuser!webappUserLogin.action?memberuser.user_name=" + user_name+"&memberuser.passwd="+passwd+"&login_state=2";
    }
    else {
        setTimeout("auto_show()", 1000);
    }
}