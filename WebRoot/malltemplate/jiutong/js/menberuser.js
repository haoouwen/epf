
var timer ;//停止定时器
//发送验证码
function sendcode(phone){
	 if(phone=="" || phone==null){
		   alert("您还未进行过手机验证");
		   return false;
	 }
	 $.ajax({
	 type:"post", 
	 url:"/webapp/memberuser!sendCheckPhone.action?phone=" + phone, 
	 datatype:"json",
	 async:false, 
	 success:function (data) {
	      if(data=="1"){
	          alert("发送成功,请收到信息后输入验证码，30分钟内有效。");
			  $(".getYzm").attr('disabled',true);
			  $("#timeMinus").val("120");
			  timer=window.setInterval("run();", 1000);
		  }else if(data=="0"){
		      $("#phoneError").html("手机号码格式不正确!");
		      return false;
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
			$(".getYzm").attr('disabled',false);
			$(".getYzm").val("获取验证码");
			clearInterval(timer);
		}else{
			$("#timeMinus").val(is - 1)
			$(".getYzm").attr('disabled',true);
			$(".getYzm").val("还有"+(is-1)+"秒");
		}
	}
}


//验证手机验证码的正确性
function cpIsNull(mobile_value){
	$("#cpError").html("");
	var eid="#cpError";
	var cp_=".getYzm";
	var tips=true;
	var cp_check=$("#cp_check").val();
    var new_mobile="";
	if(cp_check==""){
		$(eid).html("验证码不能为空!");
		return false;
	}
	
	if(mobile_value=="" || mobile_value==null){
	   new_mobile = $("#new_mobile").val();
	}else{
	   new_mobile = mobile_value;
	}
	
	$.ajax({
		type:"post", 
		url:"/webapp/memberuser!checkPhoneCode.action?phone=" + new_mobile+"&cp_check="+cp_check, 
		datatype:"json",
		async:false, 
		success:function (data) {
				if(data==1){
					tips=false;
					$(eid).html("验证码输入错误，请重新输入!");
				}else if(data==2){
					tips=false;
					$(eid).html("验证码已过时，请重新获取!");
				}else{
				    tips=true;
					$(cp_).val("获取短信验证码!");
				}
		  	}
		});
	return tips;
}

function verifyFirst(mobile){
    var flag = true;
    if(!cpIsNull(mobile)){
        flag = false;
    }
    
    if(flag == true){
        document.forms[0].action = "/webapp/memberuser!webappModifyPhoneSecond.action";
		document.forms[0].submit();
		return true;
    } 
    
}

//验证手机号码正确性与唯一性
function CellphoneIsNull(){
   $("#phoneError").html("");
   $("#phoneError").removeClass();
   var reg_phone = /^0{0,1}(1[0-9]{10})$/;//手机格式正则表达式
   var value = $("#new_mobile").val();
   if ($.trim(value) == "") {
		$("#phoneError").html("手机号码不能为空!");
		$("#phoneError").addClass("not");
		return false;
	}else if(value!='' && !reg_phone.test(value)){
	    $("#phoneError").html("手机号码格式不正确!");
	    $("#phoneError").addClass("not");
		return false;
   }
//   else if(!onlyphone(value)){
//	    $("#user_nameError").addClass("not");
//	    return false;
//   }
   else{
	    $("#phoneError").html("");
	    $("#phoneError").addClass("yes");
		return true;
	}
}

//验证手机的唯一性
function onlyphone(){
    var mobile = $("#new_mobile").val();
 	var tips=true;
	$.ajax({
		type:"post", 
		url:"/webapp/memberuser!checkPhone.action?phone=" + mobile, 
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
function getcode(){
       var reg_phone = /^0{0,1}(1[0-9]{10})$/;//手机格式正则表达式
	   var mobile = $("#new_mobile").val();
	   if ($.trim(mobile) == "") {
			$("#phoneError").html("手机号码不能为空!");
			return false;
		}else if(mobile!='' && !reg_phone.test(mobile)){
		    $("#phoneError").html("手机号码格式不正确!");
			return false;
	   }
	 $.ajax({
	 type:"post", 
	 url:"/webapp/memberuser!sendCheckPhone.action?phone=" + mobile, 
	 datatype:"json",
	 async:false, 
	 success:function (data) {
	      if(data=="1"){
	          alert("发送成功,请收到信息后输入验证码，30分钟内有效。");
			  $(".getYzm").attr('disabled',true);
			  $("#timeMinus").val("120");
			  timer=window.setInterval("run();", 1000);
	      }else if(data=="0"){
	          $("#phoneError").html("手机号码格式不正确!");
	          return false;
	      }
          
	  	}
	 });
}

//验证手机验证码的正确性
function verifycpIsNull(){
	$("#codeError").html("");
	var eid="#codeError";
	var mobileid="#phoneError";
	var cp_=".getYzm";
	var tips=true;
	var cp_check=$("#secondCode").val();
    var mobile_value= $("#new_mobile").val();
	
	
	if(mobile_value=="" || mobile_value==null){
		$(mobileid).html("手机号码不能为空!");
		return false;
	}
	
	if(cp_check==""){
		$(eid).html("验证码不能为空!");
		return false;
	}
	
	$.ajax({
		type:"post", 
		url:"/webapp/memberuser!checkPhoneCode.action?phone=" + mobile_value+"&cp_check="+cp_check, 
		datatype:"json",
		async:false, 
		success:function (data) {
				if(data==1){
					tips=false;
					$(eid).html("验证码输入错误，请重新输入!");
				}else if(data==2){
					tips=false;
					$(eid).html("验证码已过时，请重新获取!");
				}else{
				    tips=true;
					$(cp_).val("获取短信验证码!");
				}
		  	}
		});
	return tips;
}

function verifyThird(){
    var flag = true;
    
    if(!CellphoneIsNull()){
        flag = false;
    }
    if(!onlyphone()){

        flag = false;
    }
    if(!verifycpIsNull()){
        flag = false;
    }
    
    if(flag == true){
        document.forms[0].action = "/webapp/memberuser!webappModifyPhoneThird.action";
		document.forms[0].submit();
		return true;
    } 
    
}

//验证手机
function verifyMobile(){
    var flag = true;
    
    if(!CellphoneIsNull()){
        flag = false;
    }
    if(!cpIsNull("")){
        flag = false;
    }
    
    if(flag == true){
        document.forms[0].action = "/webapp/memberuser!webappVerifyMobile.action";
		document.forms[0].submit();
		
    }else{
       return false;
    } 
    
}

