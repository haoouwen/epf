 var timer ;//停止定时器
 $(document).ready(function(){
 	sele();
 	var cart_num=0;
				if($.cookie("ccn")!=null){
					var cart_cookieNum =parseInt( $.cookie("ccn"));
					for(var i=1;i<=cart_cookieNum;i++){
						cart_num+=parseInt($.cookie("buy_nums"+i));
			    	}
				}
            	 $.ajax({  	 
				          type: "post",    	     
				          url: "/mall/goods!getCarNumCount.action",
				          datatype:"json",
				          success: function(data){
				          		if(cart_num!=null){
				          			cart_num=parseInt(data)+cart_num;
				          		}else{
				          				$.cookie("ccn", null, { expires: 7, path: '/' });
				          		}
				          		if(cart_num ==null || cart_num=='' || cart_num<0){
									$("#cart_id_num").html("0");
									$(".numi").html("0");
								}else{
									$("#cart_id_num").html(cart_num);
									$(".numi").html(cart_num);
								}
				          }
				      });  
});
 //加入收藏
			function addFavorite() {   
			   if (document.all) {
			      window.external.addFavorite(location.href,document.title);   
			   } else if (window.sidebar) {   
			   	 $(".store").attr("rel","sidebar");
			     window.sidebar.addPanel(document.title,location.href, "");   
			   } else{
			 	 alert("加入收藏失败，请使用Ctrl+D进行添加");
			   }
			   }
//找回密码通过email或手机发送邮箱
function send(){
	var sel=$('input[name="c_t"]:checked').val();
	if(sel=='1'){
	var cphone = $("#phone").val();
		if(cphone==""){
			alert("手机号码不正确，请选择电子邮件验证");
			return false;
		}
		$.ajax({
		type:"post", 
		url:"/mall/member!sendPhoneCodePWD.action?phone=" + cphone+"&defag="+cphone+"&cp_type=3", 
		datatype:"json",
		async:false, 
		success:function (data) {
				alert("发送成功,请接到信息后输入验证码!");
				$("#cpc").attr('disabled',true);
				$("#timeMinus").val("60");
				timer=window.setInterval("run();", 1000);
		  	}
		});
	}else if(sel=='2'){
		var email = $("#email").val();
		if(email==""){
			alert("电子邮箱不正确，请选择手机验证");
			return false;
		}
		$.ajax({
		type:"post", 
		url:"/mall/member!sendEmailCodePWD.action?email=" + email, 
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
    if ($.trim($("#password").val()) == "") {
		$("#passwdError").html("密码不能为空!");
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
//验证码功能
function changeValidateCode(obj) {   
        //获取当前的时间作为参数，无具体意义   
	var timenow = new Date().getTime();   
        //每次请求需要一个不同的参数，否则可能会返回同样的验证码
        //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
	obj.src = "/imgrand.action?d=" + timenow;
}
function subRet() {
	if(PasswdIsNull()&&Confirm_pwdIsNull()){
		 $("#forms").submit();
	}
	
 }