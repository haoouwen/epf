$(document).ready(function(){
   selectRadio();
});


function selectRadio(){
 $("input[name='vname']").each(function(){
		isCheckd = $(this).attr("checked");
		if(isCheckd==true){
		 if($(this).val()=="0"){
		   $("#checkEmail").show();
		   $("#checkPhone").hide();
		 }else{
		   $("#checkPhone").show();
		   $("#checkEmail").hide();
		 }
		}
	});
}
//发送验证码
function sendcode(){
	var cphone = $("#phone").val();
	if(cphone==""){
		alert("请填写手机号码!");
	}else if(!CellphoneIsNull()){
	}else{
		$.ajax({
		type:"post", 
		url:"/mall/member!sendPhoneCode.action?phone=" + cphone+"&c_type=3", 
		datatype:"json",
		async:false, 
		success:function (data) {
				alert("发送成功,请接到信息后输入验证码!");
				$("#timeMinus").val("60");
				window.setInterval("run();", 1000);
		  	}
		});
	}
}
//发送验证码
function sendcodepasswd(){
	var cphone = $("#phone").val();
    var commentrand = $("#commentrand").val();
	if(cphone==""){
		alert("请填写手机号码!");
	}else if(!CellphoneIsNull()){
	}else{
		$.ajax({
		type:"post", 
		url:"/mall/member!sendPhoneCodePasswd.action?phone=" + cphone+"&c_type=3&commentrand=" + commentrand, 
		datatype:"json",
		async:false, 
		success:function (data) {
		         if(data==2){
		        alert("图形验证码输入错误，请重新输入!");
		        }else{
				alert("发送成功,请接到信息后输入验证码!");
				$("#timeMinus").val("60");
				window.setInterval("run();", 1000);
				}
		  	}
		});
	}
}


//发送验证码
function sendEmailcode(){
	var email = $("#email").val();
	if(email==""){
		alert("请填写邮箱号码!");
	}else if(!EmailIsNull()){
	}else{
		$.ajax({
		type:"post", 
		url:"/mall/member!sendEmailCode.action?email=" + email+"&c_type=4", 
		datatype:"json",
		async:false, 
		success:function (data) {
				alert("发送成功,请接到信息后输入验证码!");
				$("#timeMinus").val("60");
				window.setInterval("run();", 1000);
		  	}
		});
	}
}

//发送验证码
function sendEmailcodePasswd(){
	var email = $("#email").val();
	if(email==""){
		alert("请填写邮箱号码!");
	}else if(!EmailIsNull()){
	}else{
		$.ajax({
		type:"post", 
		url:"/mall/member!sendEmailCodePasswd.action?email=" + email+"&c_type=4", 
		datatype:"json",
		async:false, 
		success:function (data) {
		        alert("发送成功,请接到信息后输入验证码!");
				$("#timeMinus").val("60");
				window.setInterval("run();", 1000);
		  	}
		});
	}
}



//验证手机号码正确性与唯一性
function CellphoneIsNull(){
   var reg_phone = /^0{0,1}(1[0-9]{10})$/;//手机格式正则表达式
   var value = $("#phone").val();
   if(value!='' && !reg_phone.test(value)){
	    alert("手机号码格式不正确!");
		return false;
   }else if(reg_phone.test(value)){
	    var fp=true;
	    if(fp==true){
	       if($("#timeMinus").val()>0){
				$("#cpc").attr('disabled',true);
			}else{
				$("#cpc").attr('disabled',false);
			}
	    }
	    return fp;
   }else{
	    alert("手机号码格式不正确!");
	    $("#phoneError").addClass("error");
		return false;
	}
	
	//判断定时器
	if($("#timeMinus").val()>0){
		$("#cpc").attr('disabled',true);
	}else{
		$("#cpc").attr('disabled',false);
	}
}

//失去焦点验证电子邮件
function EmailIsNull(){
   var reg_email = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;//电子邮箱正则表达式
   if ($.trim($("#email").val()) == "") {
		alert("电子邮箱不能为空");
		return false;
	}else if(!reg_email.test($("#email").val())){
	    alert("电子邮箱格式不正确!");
		return false;
	}//else if(!checkEmail()){
	    //$("#emailError").addClass("error");
	   // return false;
	//}
	else{
	    return true;
	}
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
		}else{
			$("#timeMinus").val(is - 1);
			$("#cpc").val("还有"+(is-1)+"秒");
		}
	}
}

//邮箱验证
function emailSubmit(){
 $("#pass").attr("action","/bmall_Memberfund_checkEmailCode.action");
 $("#pass").submit();
}

//手机验证
function phoneSubmit(){
 $("#pass").attr("action","/bmall_Memberfund_checkPhoneCode.action");
 $("#pass").submit();
}