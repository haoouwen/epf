 var timer ;//停止定时器
 $(document).ready(function(){
 	
});
 
//找回密码通过email或手机发送邮箱
function send(){
	var cphone = $("#phone").val();
	var commentrand = $("#commentrand").val();
		if(cphone==""){
			alert("手机号码不正确");
			return false;
		}
	if(commentrand=='' ||commentrand==null){
	    alert("请输入图形验证码");
	    return false;
	}	
		$.ajax({
		type:"post", 
		url:"/webapp/memberuser!sendPhoneCodePWD.action?phone=" + cphone+"&defag="+cphone+"&commentrand="+commentrand, 
		datatype:"json",
		async:false, 
		success:function (data) {
		       if(data=='1'){
		            alert("发送成功,请接到信息后输入验证码!");
					$("#cpc").attr('disabled',true);
					$("#timeMinus").val("60");
					timer=window.setInterval("run();", 1000);
		       }else if(data=='0'){
		            alert("请输入正确的图形验证码");
		       }
				
		  	}
		});
}


//定时发送验证码
function run(){
	var s = $("#timeMinus").val();
	var is=s*1;
	if(is>=1){
		if(is == 1){
			$("#timeMinus").val(is - 1);
			$("#cpc").attr('disabled',false);
			$("#cpc").val("获取验证码");
			clearInterval(timer);
		}else{
			$("#timeMinus").val(is - 1);
			$("#cpc").val("还有"+(is-1)+"秒");
			$("#haiyou").html(is-1);
		}
	}
}




//失去焦点验证密码
function PasswdIsNull(){
    $("#passwdError").html("");
    $("#passwdError").removeClass();
    var reg_passwd=/^(\w){6,20}$/;//只能输入6-20个字母、数字、下划线
    if ($.trim($("#password").val()) == "") {
		$("#passwdError").html("密码不能为空!");
		$("#passwdError").addClass("not");
		return false;
	}else if(!reg_passwd.test($("#password").val())){
	    $("#passwdError").html("密码只能由6-20个字母、数字、下划线组成!");
	    $("#passwdError").addClass("not");
		return false;
	}else{
	    $("#passwdError").html("");
	    $("#passwdError").addClass("yes");
	    return true;
	}
}

//密码获取焦点时
function PasswordForm(){
   $("#passwdError").html("");
   $("#passwdError").removeClass();
   if($.trim($("#password").val()) == ""){
      $("#passwdError").html("请输入6-20个英文字母、数字或符号!");
      $("#passwdError").addClass("warning");
   }
}
//失去焦点验证确认密码
function Confirm_pwdIsNull(){
    $("#confirm_pwdError").html("");
    $("#confirm_pwdError").removeClass("warning");
    if($.trim($("#rep_pass").val()) == ""){
	    $("#confirm_pwdError").html("确认密码不能为空!");
	    $("#confirm_pwdError").addClass("not");
	    return false;
	}else if($.trim($("#password").val()) != $.trim($("#rep_pass").val())) {
	    $("#confirm_pwdError").html("密码与确认密码不一致!");
	    $("#confirm_pwdError").addClass("not");
		return false;
    }else{
        $("#confirm_pwdError").html("");
	    $("#confirm_pwdError").addClass("yes");
		return true;
    }
}
//获取焦点验证确认密码
function Confirm_pwdForm(){
   $("#confirm_pwdError").html("");
   $("#confirm_pwdError").removeClass();
   if($.trim($("#rep_pass").val()) == ""){
     $("#confirm_pwdError").html("请重复输入密码!");
     $("#confirm_pwdError").addClass("warning");
   }
}

function subRet() {
	if(PasswdIsNull()&&Confirm_pwdIsNull()){
		 $("#form3").submit();
	}
	
 }
 
 //获取后台生成的验证码
function changeValidateCode(obj) {   
        //获取当前的时间作为参数，无具体意义   
	var timenow = new Date().getTime();   
        //每次请求需要一个不同的参数，否则可能会返回同样的验证码
        //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
	obj.src = "/imgrand.action?d=" + timenow;
}
 