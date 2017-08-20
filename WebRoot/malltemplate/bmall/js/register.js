$(document).ready(function(){
	//同意协议
	$(".proSpan").toggle(function(){
			$(".proDiv").show();
	  },function(){
		    $(".proDiv").hide();
	  }
	)
	//验证账号信息
	$(".regul li:last").hide();
	$(".yxyz").click(function(){
	   $(".regul li:first").hide();
	   $(".regul li:last").show();
	   $("#is_phone_email").val("1");
	   $("#selway").val("1");
	})
	$(".sjyz").click(function(){
	   $(".regul li:first").show();
	    $(".regul li:last").hide();
	    $("#is_phone_email").val("0");
	    $("#selway").val("3");
	})
})

var timer ;//停止定时器
var isto_seng=true;
//获取焦点验证用户名
function UserNameForm(){
   $("#user_nameError").html("");
   $("#user_nameError").removeClass();
   if($.trim($("#user_name").val()) == ""){
    //  $("#user_nameError").html("请输入以字母开头，4-20位的字母或数字!");
      $("#user_nameError").addClass("warning");
   }
}

function AreaNameIsNull(){
   $("#areaError").html("");
   $("#areaError").removeClass();
   if($("#membernum").val() == ""){
      $("#areaError").html("请选择地区!");
      $("#areaError").addClass("not");
      return false;
   }
   return true;
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
	}
	else if(!checkUserName()){
	    $("#user_nameError").addClass("not");
	    return false;
	}else{
	    $("#user_nameError").html("");
	    $("#user_nameError").addClass("yes");
	    return true;
	}
}

//验证手机和短信验证码、用户，密码
function checkMsgForm(){
   var flag = true;
   var sel=$("#selway").val();
	var cp_check="";
   if(!cpIsNull()){
      flag = false;
   }
    if(!AreaNameIsNull()){
      flag = false;
   }
   if(!UserNameIsNull()){
      flag = false;
   }
   if(!PasswdIsNull()){
      flag = false;
   }
   if(!Confirm_pwdIsNull()){
      flag = false;
   }
   if(sel=='0'){
     if(!RandsPhoneIsNull()){
	   fage=false;
	 }
	 if(!CellphoneIsNull()){
 		flag = false;
	 }
	}
	if(sel=='1'){
	    if(!RandsMailIsNull()){
		  flag = false;
	 	}
		if(!EmailIsNull()){
  		    flag = false;
	     }
	}
   if(!agreement()){
      flag = false;
   }
   
   return flag;

}

//验证手机验证码的正确性
function cpIsNull(){
	var sel=$("#selway").val();
	$("#cpError").html("");
	$("#cpError").removeClass("not");
	$("#ceError").html("");
	$("#ceError").removeClass("not");
	var eid="";
	var tips=true;
	var cp_check="";
	var ce_check="";
	var email='';
	var phone="";
	var cp_="";
	if(sel=='0'){
		 cp_check=$("#cp_check").val();
		 eid="#cpError";
		 cp_="#cpc";
	}
	if(sel=='1'){
		 cp_check=$("#ce_check").val();
		  eid="#ceError";
		   cp_="#cpe";
	}
	if(sel=='3'){
		 ce_check=$("#ce_check").val();
		 cp_check=$("#cp_check").val();
		   phone=$("#phone").val();
		    email=$("#email").val();
		 if(email!=""&&(ce_check==null||ce_check=='')){
			   eid="#ceError";
			    cp_="#cpe";
			    $(eid).html("验证码不能为空!");
			$(eid).addClass("not");
		return false;
		 }else if((cp_check==null||cp_check=='')&&phone!=""){
		 	 eid="#cpError";
		 	  cp_="#cpc";
		 	  $(eid).html("验证码不能为空!");
		$(eid).addClass("not");
		return false;
		 }
	}else{
		if(cp_check==""){
			$(eid).html("验证码不能为空!");
			$(eid).addClass("not");
			return false;
		}
	}
   if(email!=""){
    	eid="#ceError";
	    cp_="#cpe";
	     cp_check= ce_check;
    }
    if(phone!=""){
    	 eid="#cpError";
 		  cp_="#cpc";
    }
	if(sel=='0'){
	  phone=$("#phone").val();
	}else if(sel=='1'){
	  phone=$("#email").val();
	}
	if(sel=='3'){
	 phone=$("#email").val();
	 if(phone==null||phone==''){
		  phone=$("#phone").val();
	 }
	}
	$.ajax({
		type:"post", 
		url:"/mall/member!checkPhoneCode.action?phone=" + phone+"&cp_check="+cp_check, 
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
   if ($.trim(value) == "") {
		$("#phoneError").html("手机号码不能为空!");
		$("#phoneError").addClass("not");
		return false;
	}else if(value!='' && !reg_phone.test(value)){
	    $("#phoneError").html("手机号码格式不正确!");
	    $("#phoneError").addClass("not");
		return false;
   }
   else if(reg_phone.test(value)){
	    var fp=true;
	    if(!onlyphone(value)){
		   $("#phoneError").html("该手机已经注册!");
		   $("#phoneError").addClass("not");
		    fp=false;
	   }else{
	    	$("#phoneError").html("");
	    	$("#phoneError").addClass("yes");
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
	    $("#phoneError").addClass("not");
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
	$("#email").val("");
	$("#ce_check").val("");
    if($("#phone").val() == ""){
        $("#phoneError").html("");
		$("#phoneError").removeClass();
	    $("#phoneError").html("手机号码格式以13/14/15/18等开头!");
	    $("#phoneError").addClass("warning");
	    return false;
	}
}

//发送验证码
function sendcode(){
	if(!isto_seng){
		return false;
	}
	var sel=$("#selway").val();
	var phone=$("#phone").val();
	var email=$("#email").val();
	var user_name = $("#user_name").val();
	var password = $("#passwd").val();
	var confirm_pwd = $("#confirm_pwd").val();
	var membernum = $("#membernum").val();
	var commentrand_phone = $("#commentrand_phone").val();
	var commentrand_email = $("#commentrand").val();
	var fage=true;
   if(!UserNameIsNull()){
      fage=false;
   }
   if(!PasswdIsNull()){
       fage=false;
   }
   if(!Confirm_pwdIsNull()){
       fage=false;
   }
    if(!AreaNameIsNull()){
       fage=false;
   }
	if(sel=='0'||sel=='3'){
	    if(!CellphoneIsNull()){
	      fage=false;
	    }
		if(!RandsPhoneIsNull()){
		   fage=false;
	 	}
	 	if(fage==true){
	 	  $.ajax({
			type:"post", 
			url:"/mall/member!sendPhoneCode.action?phone=" + phone+"&defag="+phone+"&c_type="+1+"&user_name="+user_name+"&password="+password+"&confirm_pwd="+confirm_pwd+"&membernum="+membernum+"&commentrand="+commentrand_phone, 
			datatype:"json",
			async:false, 
			success:function (data) {
				if(data=='1'){
				    alert("发送成功,请接到信息后输入验证码!");
					isto_seng=false;
					$("#timeMinus").val("60");
					timer=window.setInterval("run();", 1000);
					return true;
				}else if(data=='0'){
				    alert("验证码获取失败,请正确填写注册信息");
				    isto_seng=true;
				    return false;
				}
			  }
			});
	 	}
	}else if(sel=='1'){
	    if(!RandsMailIsNull()){
		    fage=false;
	 	}
		if(!EmailIsNull()){
  		     fage=false;
	     }
	     if(fage==true){
			$.ajax({
			type:"post", 
			url:"/mall/member!sendEmailCode.action?email=" + email+"&user_name="+user_name+"&password="+password+"&confirm_pwd="+confirm_pwd+"&membernum="+membernum+"&commentrand="+commentrand_email, 
			datatype:"json",
			async:false, 
			success:function (data) {
			       if(data=='1'){
			        alert("发送成功,请查收邮箱后输入验证码!");
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
}

//找回密码通过email或手机发送邮箱
function send(){
	var sel=$("#aa").val();
	if(sel=='0'){
	var cphone = $("#phone").val();
		$.ajax({
		type:"post", 
		url:"/portal/member!sendPhoneCodePWD.action?phone=" + cphone+"&defag="+cphone+"&cp_type=3", 
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
		url:"/portal/member!sendEmailCodePWD.action?email=" + email, 
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

//定时发送验证码
function run(){
	var sel=$("#selway").val();
	var cp_="";
	if(sel=='0'){
		 cp_="#cpc";
	}
	if(sel=='1'){
		   cp_="#cpe";
	}
	if(sel=='3'){
		cp_=$("#email").val();
		 if(cp_==null||cp_==''){
			cp_="#cpc";
		 }else{
		 	 cp_="#cpe";
		 }
	}
	var s = $("#timeMinus").val();
	var is=s*1;
	if(is>=1){
		if(is == 1){
			$("#timeMinus").val(is - 1);
			isto_seng=true;
		   $(cp_).html("获取短信验证码");
			clearInterval(timer);
		}else{
			$("#timeMinus").val(is - 1)
			$(cp_).html((is-1)+"秒后重新获取");
		}
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

//验证码功能
function changeValidateCode(obj) {   
        //获取当前的时间作为参数，无具体意义   
	var timenow = new Date().getTime();   
		if(obj=='validateCode'){
		obj=$("#validateCode");
		obj.attr('src','/imgrand.action?d='+ timenow);
	}else{
		obj.src = "/imgrand.action?d=" + timenow;
	}
        //每次请求需要一个不同的参数，否则可能会返回同样的验证码
        //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
	
}

//当在用户名文本输入框失去焦点的时候，进行异步验证
function checkUserName() {  
        var tips=true;
	    var user_name = $("#user_name").val();
		$.ajax({
		type:"post", 
		url:"/mall/member!checkUserName.action?user_name=" + user_name, 
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

//提交表单
function registerForm(){
  //验证是否为空
  if(checkMsgForm()){
  	$.ajax({
          type: "post",
          data:{'type':"joinus"},		      
          url: "/malluser!setIs_joinus.action",
          datatype:"json",
          async:false,
          success: function(data){ 
          	  document.forms[0].action = "/mall/member!validateReg.action";
			  document.forms[0].submit();
          },error:function(){
          	jNotify("过滤失败!");
          }
	});  
    
	 return true;
  }else{
    return false;   
  }
}


//验证表单所有元素
function checkFromAll(){
   var flag = true;
    if(!AreaNameIsNull()){
      flag = false;
   }
   if(!UserNameIsNull()){
      flag = false;
   }
   if(!PasswdIsNull()){
      flag = false;
   }
   if(!Confirm_pwdIsNull()){
      flag = false;
   }
//   if(!EmailIsNull()){
//      flag = false;
//   }
//   if(!CellphoneIsNull()){
//     flag=false;
//   }
   //if(!Areafouce()){
   //   flag = false;
  // }
//   if(!RandsIsNull()){
//   	  flag = false;
//   }
   if(!agreement()){
      flag = false;
   }
   return flag;
}

//失去焦点验证码
function RandsPhoneIsNull(){
    $("#randsphoneError").html("");
    $("#randsphoneError").removeClass("warning");
    if ($.trim($("#commentrand_phone").val()) == "") {
		$("#randsphoneError").html("验证码不能为空!");
		$("#randsphoneError").addClass("not");
		return false;
	}else if(!validateCodeR("commentrand_phone")){
    	$("#randsphoneError").addClass("not");
	    return false;
	}else{
	    $("#randsphoneError").html("");
	    $("#randsphoneError").addClass("yes");
	    return true;
	}
}

//失去焦点验证码
function RandsMailIsNull(){
    $("#randsmailError").html("");
    $("#randsmailError").removeClass("warning");
    if ($.trim($("#commentrand").val()) == "") {
		$("#randsmailError").html("验证码不能为空!");
		$("#randsmailError").addClass("not");
		return false;
	}else if(!validateCodeR("commentrand")){
    	$("#randsmailError").addClass("not");
	    return false;
	}else{
	    $("#randsmailError").html("");
	    $("#randsmailError").addClass("yes");
	    return true;
	}
}


//获取焦点验证码
function RandsForm(value){
   $("#randsError").html("");
   $("#randsError").removeClass("not");
   if($.trim($("#rands").val()) == ""){
     $("#randsError").html("请输入验证码!");
     $("#randsError").addClass("warning");
   }
}

function agreement(){
   var isChecked = $("#agreeId").attr("checked");
	if(isChecked){
		return true;
	}
	else{
	    jNotify("请同意服务协议!");
		return false;
	}
}


//失去焦点验证密码
function PasswdIsNull(){
    $("#passwdError").html("");
    $("#passwdError").removeClass();
    var reg_passwd=/^(\w){6,20}$/;//只能输入6-20个字母、数字、下划线
    if ($.trim($("#passwd").val()) == "") {
		$("#passwdError").html("密码不能为空!");
		$("#passwdError").addClass("not");
		return false;
	}else if($.trim($("#passwd").val()) == ($.trim($("#user_name").val()))){
	    $("#passwdError").html("密码不能与用户名一样!");
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
   $("#passwdError").removeClass("not");
   if($.trim($("#passwd").val()) == ""){
      $("#passwdError").html("请输入6-20个英文字母、数字或符号!");
      $("#passwdError").addClass("warning");
   }
}
//失去焦点验证确认密码
function Confirm_pwdIsNull(){
    $("#confirm_pwdError").html("");
    $("#confirm_pwdError").removeClass("warning");
    if($.trim($("#confirm_pwd").val()) == ""){
	    $("#confirm_pwdError").html("确认密码不能为空!");
	    $("#confirm_pwdError").addClass("not");
	    return false;
	}else if($.trim($("#passwd").val()) != $.trim($("#confirm_pwd").val())) {
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
   $("#confirm_pwdError").removeClass("not");
   if($.trim($("#confirm_pwd").val()) == ""){
     $("#confirm_pwdError").html("请重复输入密码!");
     $("#confirm_pwdError").addClass("warning");
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

//当验证码输入框失去焦点时，进行异步验证
function validateCode() {
	    var tips=true;
	    var commentrand = $("#rands").val();
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
    
 //密码强度
 function testpass(password){
    var score = 0;
    if (password.length < 6 ) { return -6; }
    score += password.length * 4;
    score += ( repeat(1,password).length - password.length ) * 1;
    score += ( repeat(2,password).length - password.length ) * 1;
    score += ( repeat(3,password).length - password.length ) * 1;
    score += ( repeat(4,password).length - password.length ) * 1;
    if (password.match(/(.*[0-9].*[0-9].*[0-9])/)){ score += 5;}
    if (password.match(/(.*[!,@,#,$,%,^,&,*,?,_,~].*[!,@,#,$,%,^,&,*,?,_,~])/)){ score += 5 ;}
    if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)){ score += 10;}
    if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/)){ score += 15;}
    if (password.match(/([!,@,#,$,%,^,&,*,?,_,~])/) && password.match(/([0-9])/)){ score += 15;}
    if (password.match(/([!,@,#,$,%,^,&,*,?,_,~])/) && password.match(/([a-zA-Z])/)){score += 15;}
    if (password.match(/^\w+$/) || password.match(/^\d+$/) ){ score -= 10;}
    if ( score < 0 ){score = 0;}
    if ( score > 100 ){ score = 100;}
    return score;
    
    function repeat(len,str){
    var res = "";
    for (var i = 0; i < str.length; i++ ){
        var repeated = true;
        for (var j = 0, max = str.length - i - len; j < len && j < max; j++){
            repeated = repeated && (str.charAt(j + i) == str.charAt(j + i + len));
        }
        if (j < len) repeated = false;
        if (repeated) {
            i += len - 1;
            repeated = false;
        }else{
            res += str.charAt(i);
        }
    }
    return res;
    }
}
function checkpass(passwd){
    var score = testpass(passwd.value);
     var pclass = score < 34 ? 'lowb' : (score < 68 ? 'midb' : 'heightb');
    $("#pswState") .show();
    $("#ps_s").removeClass();
         $("#ps_s").addClass(pclass);
         if(passwd.value.length<1){
         $("#pswState") .hide();
         }
}

function setpswstrong(passwd){
    var score = testpass(passwd.value);
    var pswstrong = score < 34 ? '0' : (score < 68 ? '1' : '2');
    document.getElementById('psw_strong').value=pswstrong;
    PasswdIsNull();
}

//合作登录注册
function subregist(){
   var flag = true;
   if(!CellphoneIsNull()){
       flag=false;
   }
   if($("#selarea").val()=="" || $("#selarea").val()==null || $("#selarea").val()== 'undefined'){
     $("#areaError").html("请选择地区!");
     $("#areaError").addClass("not");
     flag = false;
   }
   if(!agreement()){
      flag = false;
   }
   if(flag){
    //提交表单
    $("#registForm").submit();
   }
}

