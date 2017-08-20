

//失去焦点验证码
function RandsIsNull(){
    $("#randError").html("");
	$("#randError").removeClass("error");
    if ($.trim($("#commentrand").val()) == "") {
		$("#randError").html("图形验证码不能为空!");
		$("#randError").addClass("error");
		return false;
	}else if(!validateCodeR("commentrand")){
    	$("#randError").addClass("error");
	    return false;
	}else{
	    $("#randError").html("");
	    return true;
	}
}

//当验证码输入框失去焦点时，进行异步验证
function validateCodeR(id) {
	    var tips=true;
	    var commentrand = $("#"+id+"").val();
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


//验证手机验证码的正确性
function cpIsNull(){
	$("#cpError").html("");
	$("#cpError").removeClass("error");
	var tips=true;
	var cp_type=$("#cp_type").val();
	var phone="";
	if(cp_type=="3"){
      phone=$("#phone").val();
	}else{
	  phone=$("#email").val();
	}
	var cp_check=$("#cp_check").val();
	if(cp_check==""){
		$("#cpError").html("验证码不能为空!");
		$("#cpError").addClass("error");
		return false;
	}
	if(phone==""){
		return false;
	}
	var cp_check = $("#cp_check").val();
	$.ajax({
		type:"post", 
		url:"/msgcheck!checkPhoneCode.action?phone=" + phone+"&cp_check="+cp_check, 
		datatype:"json",
		async:false, 
		success:function (data) {
				if(data == "1") {
				    tips=false;
					$("#cpError").html("验证码已过时，请重新获取!");
				    $("#cpError").addClass("error");
				} else if(data == "2") {
				    tips=true;
					$("#cpc").val("验证码成功!");
					$("#cpError").addClass("success_form");
				} else {
				   tips=false;
					$("#cpError").html("验证码错误，请重新输入正确验证码!");
				    $("#cpError").addClass("error");
				}
		  	}
		});
	return tips;
}


//验证手机号码正确性与唯一性
function CellphoneIsNull(){
   $("#phoneError").html("");
   $("#phoneError").removeClass();
   var reg_phone = /^0{0,1}(1[0-9]{10})$/;//手机格式正则表达式
   var value = $("#phone").val();
   if(value == ""){
  	   $("#phoneError").html("手机号码不能为空!");
       $("#phoneError").addClass("error");
   } else if(value!='' && !reg_phone.test(value)){
	    $("#phoneError").html("手机号码格式不正确!");
	    $("#phoneError").addClass("error");
		return false;
   }else if(reg_phone.test(value)){
	    var fp=true;
	    if(!onlyphone(value)){
		    $("#phoneError").html("该手机已经注册!");
		    $("#phoneError").addClass("error");
		    fp=false;
	    }else{
	    	$("#phoneError").html("");
	    	$("#phoneError").addClass("success_form");
	    	fp=true;
	    }
	    if(fp==true){
	       if($("#timeMinus").val()>0){
				$("#cpc").attr('disabled',true);
			}else{
				$("#cpc").attr('disabled',false);
			}
	    }
	    return fp;
   }else{
	    $("#phoneError").html("手机号码格式不正确!");
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

//验证手机的唯一性
function onlyphone(p_val){
 	var tips=true;
	$.ajax({
		type:"post", 
		url:"/mall/member!checkPhone.action?phone=" + p_val, 
		datatype:"json",
		async:false, 
		success:function (data) {
				if (data == "1") { 
					tips=false;
				}else{
				   $("#phoneError").html("");
				   tips=true;
				}
		  	}
		});
	return tips;
}


//验证手机号错误提示
function CellphoneForm(){
    if($("#phone").val() == ""){
        $("#phoneError").html("");
		$("#phoneError").removeClass();
	    $("#phoneError").html("手机号码格式以13/14/15/18等开头!");
	    $("#phoneError").addClass("tipform");
	    return false;
	}
}



//发送验证码
function sendcode(){
	var cphone = $("#phone").val();
	var commentrand = $("#commentrand").val();
	var cp_type = $("#cp_type").val();
	if(cphone==""){
		$("#phoneError").html("请填写手机号码!");
	}else if(!CellphoneIsNull()){
	}else{
	  if($("#commentrand").val() == ""){
	   $("#randError").html("请先填写验证码!");
	   return false;
 	}
		$.ajax({
		type:"post", 
		url:"/mall/member!sendPhoneCodeCheck.action?phone=" + cphone+"&c_type="+cp_type+"&commentrand="+commentrand, 
		datatype:"json",
		async:false, 
		success:function (data) {
		        if(data=='1'){
		           $("#randError").html("");
		           alert("发送成功,请接到信息后输入验证码!");
				   $("#cpc").attr('disabled',true);
				   $("#timeMinus").val("60");
				   window.setInterval("run();", 1000);
		        }else if(data=='0'){
		           alert("请填写正确的信息");
		        }
				
		  	}
		});
	}
}
//发送验证码
function sendEmailcode(){
	var email = $("#email").val();
	var cp_type = $("#cp_type").val();
	var commentrand = $("#commentrand").val();
	if(email==""){
		$("#phoneError").html("请填写邮箱号码!");
		return false;
	}else if(!EmailIsNull()){
	    $("#phoneError").html("请填写正确的电子邮箱!");
	    return false;
	}else if(commentrand=='' || commentrand==null){
	    $("#phoneError").html("请填写邮箱号码!");
	    return false;
	}else{
		$.ajax({
		type:"post", 
		url:"/mall/member!sendEmailCodeCheck.action?email=" + email+"&c_type="+cp_type+"&commentrand="+commentrand, 
		datatype:"json",
		async:false, 
		success:function (data) {
		        if(data=='1'){
		            alert("发送成功,请接到信息后输入验证码!");
					$("#cpc").attr('disabled',true);
					$("#timeMinus").val("60");
					window.setInterval("run();", 1000);
		        }else if(data=='0'){
		            alert("请填写正确的信息!");
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
			$("#cpc").attr('disabled',false);
			$("#cpc").val("获取验证码");
		}else{
			$("#timeMinus").val(is - 1);
			$("#cpc").val("还有"+(is-1)+"秒");
		}
	}
}



//失去焦点验证电子邮件
function EmailIsNull(){
   $("#emailError").html("");
   $("#emailError").removeClass("tipform");
   var reg_email = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;//电子邮箱正则表达式
   if ($.trim($("#email").val()) == "") {
		$("#emailError").html("邮箱号码不能为空");
		$("#emailError").addClass("error");
		return false;
	}else if(!reg_email.test($("#email").val())){
	    $("#emailError").html("邮箱号码格式不正确!");
	    $("#emailError").addClass("error");
		return false;
	}//else if(!checkEmail()){
	    //$("#emailError").addClass("error");
	   // return false;
	//}
	else{
	    $("#emailError").html("");
	    $("#emailError").addClass("success_form");
	    return true;
	}
}
//获取焦点验证邮箱
function EmailForm(value){
   $("#emailError").html("");
   $("#emailError").removeClass("error");
   if($.trim($("#email").val()) == ""){
     $("#emailError").html("请输入常用的邮箱号码,便于找回密码!");
     $("#emailError").addClass("tipform");
   }
}

//验证手机和短信验证码
function checkMsgForm(){
   var flag = true;
   if(!CellphoneIsNull()){
      flag = false;
   }
   if(!cpIsNull()){
      flag = false;
   }if(!RandsIsNull()){
      flag = false;
   }
   if(flag){
	 	document.forms[0].submit();
   }else{
   		return false;
   }
}

//验证手机和短信验证码
function checkEmailForm(){
   var flag = true;
   if(!EmailIsNull()){
      flag = false;
   }
   if(!RandsIsNull()){
      flag = false;
   }
   if(!cpIsNull()){
      flag = false;
   }
   if(flag){
	 	document.forms[0].submit();
   }else{
   		return false;
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

//验证码功能
function changeValidateCode(obj) {   
        //获取当前的时间作为参数，无具体意义   
	var timenow = new Date().getTime();   
        //每次请求需要一个不同的参数，否则可能会返回同样的验证码
        //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
	obj.src = "imgrand.action?d=" + timenow;
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
               $("#emailError").html("邮箱号码已被占用，请重新输入!");
               tips = false;
            }else{
               $("#emailError").html("");
			   tips = true;
            }
        }
      });
      return tips;
 }
  //验证码功能
         function changeValidateCode(obj) {
             //获取当前的时间作为参数，无具体意义   
             var timenow = new Date().getTime();
             //每次请求需要一个不同的参数，否则可能会返回同样的验证码
             //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
             obj.src = "/imgrand.action?d=" + timenow;
         }
 