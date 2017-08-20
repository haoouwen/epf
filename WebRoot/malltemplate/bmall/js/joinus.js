 var timer ;//停止定时器
//获取焦点验证用户名
function UserNameForm(){
   $("#user_nameError").html("");
   $("#user_nameError").removeClass();
   if($.trim($("#user_name").val()) == ""){
      $("#user_nameError").html("请输入以字母开头，4-20位的字母或数字!");
      $("#user_nameError").addClass("tipform");
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
		$("#user_nameError").addClass("error");
		return false;
	}else if($("#user_name").val().length	<4){
	    $("#user_nameError").html("用户名不能小于4个字符!");
	    $("#user_nameError").addClass("error");
		return false;
	}else if(reg_usernamenum.test($("#user_name").val())){
	    $("#user_nameError").html("请输入以字母开头");
	    $("#user_nameError").addClass("error");
		return false;
	}else if(!reg_username.test($("#user_name").val())){
	    $("#user_nameError").html("用户名只能由数字、字母或者下划线组成");
	    $("#user_nameError").addClass("error");
		return false;
	}else if(!checkUserName()){
	    $("#user_nameError").addClass("error");
	    return false;
	}else{
	    $("#user_nameError").html("");
	    $("#user_nameError").addClass("success_form");
	    return true;
	}
}

//验证所属地区
function Areafouce(){
    var areacount=0;	
    //验证所属地区的选择下拉框是否已选择		           
    if($("select[id^=arealevel]").length>0){
        $("select[id^=arealevel]").each(function(){	
              if($(this).val()=="0"){ 
                  areacount+=1;
			}
        });
        if(areacount>0){
			$("#arealistError").html("请选择所属地区!");
			$("#arealistError").addClass("error");
			return false;
        } else{				        
             $("#arealistError").html("");
             $("#arealistError").addClass("success_form");
             return true;
        }
    }
}

//验证手机验证码的正确性
function cpIsNull(){
	$("#cpError").html("");
	$("#cpError").removeClass("error");
	var tips=true;
	var cp_check=$("#cp_check").val();
	if(cp_check==""){
		$("#cpError").html("验证码不能为空!");
		$("#cpError").addClass("error");
		return false;
	}
	var sel=$("#selway").val();
	var phone="";
	if(sel=='0'){
	  phone=$("#phone").val();
	}else if(sel='1'){
	  phone=$("#u_email").val();
	}
	$.ajax({
		type:"post", 
		url:"/mall/member!checkPhoneCode.action?phone=" + phone+"&cp_check="+cp_check, 
		datatype:"json",
		async:false, 
		success:function (data) {
				if(data==1){
					tips=false;
					$("#cpError").html("验证码输入错误，请重新输入!");
				    $("#cpError").addClass("error");
				}else if(data==2){
					tips=false;
					$("#cpError").html("验证码已过时，请重新获取!");
				    $("#cpError").addClass("error");
				}else{
				    tips=true;
					$("#cpc").val("获取验证码!");
					$("#cpError").addClass("success_form");
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
		$("#phoneError").addClass("error");
		return false;
	}else if(value!='' && !reg_phone.test(value)){
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




//验证身份证
function checkCard(){
      var idcard=$("#card").val()
      var bo=/^(\d{6})(18|19|20)?(\d{2})([01]\d)([0123]\d)(\d{3})(\d|X)?$/.test(idcard);
      var year = idcard. substr(6,4);
      var month = idcard. substr(10,2);
      var day = idcard. substr(12,2);
      if(bo==false||month>12||day>31){
	       $("#cardError").removeClass("rightform");
	       $("#cardError").html("请输入正确的身份证号码！");
		   $("#cardError").addClass("error");
      	   return false;
      }
      return true;
}

//清除身份证样式
function clearcard(){
	  $("#cardError").html("");
	  $("#cardError").removeClass("error");
}


//发送验证码
function sendcode(){
	var sel=$("#selway").val();
	if(sel=='0'){
	var cphone = $("#phone").val();
		$.ajax({
		type:"post", 
		url:"/mall/member!sendPhoneCode.action?phone=" + cphone+"&defag="+cphone, 
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
		var email = $("#u_email").val();
		$.ajax({
		type:"post", 
		url:"/mall/member!sendEmailCode.action?email=" + email, 
		datatype:"json",
		async:false, 
		success:function (data) {
				alert("发送成功,请查收邮箱后输入验证码!");
				$("#cpc").attr('disabled',true);
				$("#timeMinus").val("60");
				timer=window.setInterval("run();", 1000);
		  	}
		});
	}
}
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
				$("#cpc").attr('disabled',true);
				$("#timeMinus").val("60");
				timer=window.setInterval("run();", 1000);
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
			clearInterval(timer);
		}else{
			$("#timeMinus").val(is - 1);
			$("#cpc").val("还有"+(is-1)+"秒");
		}
	}
}


//失去焦点验证密码
function PasswdIsNull(){
    $("#passwdError").html("");
    $("#passwdError").removeClass();
    var reg_passwd=/^(\w){6,20}$/;//只能输入6-20个字母、数字、下划线
    if ($.trim($("#passwd").val()) == "") {
		$("#passwdError").html("密码不能为空!");
		$("#passwdError").addClass("error");
		return false;
	}else if($.trim($("#passwd").val()) == ($.trim($("#user_name").val()))){
	    $("#passwdError").html("密码不能与用户名一样!");
	    $("#passwdError").addClass("error");
		return false;
	}else{
	    $("#passwdError").html("");
	    $("#passwdError").addClass("success_form");
	    return true;
	}
}

//密码获取焦点时
function PasswordForm(){
   $("#passwdError").html("");
   $("#passwdError").removeClass("error");
   if($.trim($("#passwd").val()) == ""){
      $("#passwdError").html("请输入6-20个英文字母、数字或符号!");
      $("#passwdError").addClass("tipform");
   }
}
//失去焦点验证确认密码
function Confirm_pwdIsNull(){
    $("#confirm_pwdError").html("");
    $("#confirm_pwdError").removeClass("tipform");
    if($.trim($("#confirm_pwd").val()) == ""){
	    $("#confirm_pwdError").html("确认密码不能为空!");
	    $("#confirm_pwdError").addClass("error");
	    return false;
	}else if($.trim($("#passwd").val()) != $.trim($("#confirm_pwd").val())) {
	    $("#confirm_pwdError").html("密码与确认密码不一致!");
	    $("#confirm_pwdError").addClass("error");
		return false;
    }else{
        $("#confirm_pwdError").html("");
	    $("#confirm_pwdError").addClass("success_form");
		return true;
    }
}

//获取焦点验证确认密码
function Confirm_pwdForm(){
   $("#confirm_pwdError").html("");
   $("#confirm_pwdError").removeClass("error");
   if($.trim($("#confirm_pwd").val()) == ""){
     $("#confirm_pwdError").html("请重复输入密码!");
     $("#confirm_pwdError").addClass("tipform");
   }
}

//失去焦点验证电子邮件
function EmailIsNull(){
   $("#emailError").html("");
   $("#emailError").removeClass();
   var reg_email = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;//电子邮箱正则表达式
   if ($.trim($("#email").val()) == "") {
		$("#emailError").html("电子邮箱不能为空");
		$("#emailError").addClass("error");
		return false;
	}else if(!reg_email.test($("#email").val())){
	    $("#emailError").html("电子邮箱格式不正确!");
	    $("#emailError").addClass("error");
		return false;
	}else if(!checkEmail()){
	    $("#emailError").addClass("error");
	    return false;
	}
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
     $("#emailError").html("请输入常用的电子邮箱,便于日后找回密码!");
     $("#emailError").addClass("tipform");
   }
}

//失去焦点验证码
function RandsIsNull(){
    $("#randsError").html("");
    $("#randsError").removeClass("tipform");
    if ($.trim($("#rands").val()) == "") {
		$("#randsError").html("验证码不能为空!");
		$("#randsError").addClass("error");
		return false;
	}else if(!validateCode()){
    	$("#randsError").addClass("error");
	    return false;
	}else{
	    $("#randsError").html("");
	    $("#randsError").addClass("success_form");
	    return true;
	}
}

//获取焦点验证码
function RandsForm(value){
   $("#randsError").html("");
   $("#randsError").removeClass("error");
   if($.trim($("#rands").val()) == ""){
     $("#randsError").html("请输入验证码!");
     $("#randsError").addClass("tipform");
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
   }
   if(flag){
   		document.forms[0].action = "/mall/member!mallregister.action";
	 	document.forms[0].submit();
   }else{
   		return false;
   }
}

//验证表单所有元素
function checkFromAll(){
   var flag = true;
   if(!UserNameIsNull()){
      flag = false;
   }
   if(!PasswdIsNull()){
      flag = false;
   }
   if(!Confirm_pwdIsNull()){
      flag = false;
   }
   if(!EmailIsNull()){
      flag = false;
   }
   if(!CellphoneIsNull()){
     flag=false;
   }
   //if(!Areafouce()){
   //   flag = false;
  // }
   if(!RandsIsNull()){
   	  flag = false;
   }
   return flag;
}

//提交表单
function registerForm(){
  if(checkFromAll()){
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
	obj.src = "/imgrand.action?d=" + timenow;
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
    var password_label = document.getElementById('password_label');
    if(score == -6)    {
        password_label.innerHTML = '';
    }else{
        var color = score < 34 ? '#AD1818' : (score < 68 ? '#ede3ab' : '#d3edab');
        var text = score < 34 ? '弱' : (score < 68 ? '中' : '强');
        var width = score+127 + 'px';
        password_label.innerHTML = "<span style='width:"+width+";display:block;overflow:hidden;height:20px;line-height:20px;background:"+color+";text-align:center;'>"+text+"</span>";
    }
}

function setpswstrong(passwd){
    var score = testpass(passwd.value);
    var pswstrong = score < 34 ? '0' : (score < 68 ? '1' : '2');
    document.getElementById('psw_strong').value=pswstrong;
    PasswdIsNull();
}


