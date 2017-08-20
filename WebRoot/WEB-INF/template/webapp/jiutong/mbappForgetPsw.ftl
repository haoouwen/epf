<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}—忘记密码</title>

<meta name="author" content="久通宏达科贸（北京）有限公司">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">           
<meta http-equiv="Pragma" content="no-cache">
<link href="/wro/webapp_common.css" rel="stylesheet" type="text/css"/>
<link href="/malltemplate/jiutong/css/mbClass.css" rel="stylesheet" type="text/css"></link>
<link href="/malltemplate/jiutong/css/mbAcount.css" rel="stylesheet" type="text/css"></link>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>

<body>


<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>忘记密码<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<!--步骤显示-->
<div class="forgetStep">
    <span><i <#if r_psw_num=='1'||r_psw_num=='2'>class="selI"</#if>>1</i>验证身份</span>
    <span><i <#if r_psw_num=='3'>class="selI"</#if>>2</i>修改密码</span>
    <span><i <#if r_psw_num=='4'>class="selI"</#if>>3</i>完成</span>
    <br class="clear"/>
</div>
<!---->
<#if r_psw_num=='1'>
<@s.form id="form1" action="/webapp/memberuser!checkCode.action" method="post">
<div class="forgetPswDiv">
   <p><b>请输入已验证手机：</b></p>
   <p class="forgetP"><input type="text" class="forgetText" value="请输入已验证手机" id="username" name="username" ></p>
   <p <#if i_u=='0'>class="not"<#elseif i_u=='1'>class="yes"</#if>>
	<@s.fielderror><@s.param>username</@s.param></@s.fielderror>
   </p>
   <p><a href="###" onclick="document.getElementById('form1').submit();" class="forgetBut">下一步</a></p>
</div>
 </@s.form> 
 </#if>
<!---->
<#if r_psw_num=='2'>
<@s.form id="form2" action="/webapp/memberuser!passReset.action" method="post">
<div class="forgetPswDiv">
    <p><b>已验证手机：</b><b class="redB"> ${phone[0..2]?if_exists}****${phone[7..10]?if_exists}</b></p>
   <p><input type="buton" id="cpc" class="getYzm" onclick="send();" value="获取短验证码"/></p>
   <p class="gray">如在60秒内还没有收到短信验证码，请重新获取验证码</p>
   <p><b>请填写短信验证码</b></p>
   <p class="forgetP"><input type="text" class="forgetText" value="请输入验证码" id="checkcode" name="checkcode"></p>
   <p <#if cc=='0'>class="not"<#elseif cc=='1'>class="yes"></#if>>
	   <@s.fielderror><@s.param>checkcode</@s.param></@s.fielderror>
	</p>
   <p class="gray">若该手机号码一直无法接收验证短信，请联系客服或者到账号管理中修改手机验证</p>
   <p><a href="###" onclick="sub();" class="forgetBut">下一步</a></p>
</div>
<@s.hidden name="username"/>
<@s.hidden id="phone" name="phone" />
</@s.form> 
 </#if>
<!---->
<#if r_psw_num=='3'>
<@s.form id="form3" action="/webapp/memberuser!updatePass.action" method="post">
<div class="forgetPswDiv">
   <p><b>输入新密码</b></p>
   <p class="forgetP"><input id="password" name="password" type="password" class="forgetText" onfocus="PasswordForm()" onblur="PasswdIsNull()" value="请输入新密码"></p>
   <p><b>重新输入密码</b></p>
   <p class="forgetP"><input id="rep_pass" name="rep_pass" type="password" class="forgetText" onblur="Confirm_pwdIsNull();" onfocus="Confirm_pwdForm()" value="请再次输入密码"></p>
   <p <#if rp=='0'>class="not"<#elseif rp=='1'>class="yes"</#if>id="confirm_pwdError">
	  	<@s.fielderror><@s.param>rep_pass</@s.param></@s.fielderror>
	</p>
   <p><a onclick="document.getElementById('form3').submit();" class="forgetBut">下一步</a></p>
</div>
<@s.hidden name="username"/>
</@s.form> 
 </#if>
<!--完成-->
<#if r_psw_num=='4'>
<div class="registerDiv">
    <div class="sucRegister">
       <p><b>密码重置成功</b></p>
       <p>请记住您的密码？不要泄露您的信息</p>
       <p><a href="/webapplogin.html" class="aRegister">登录</a></p>
    </div>
</div>
</#if>

<!--尾部-->
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">


</body>

<script src="/wro/webapp_common.js" type="text/javascript"></script>
<script type="text/javascript" src="/malltemplate/jiutong/js/updatepass.js"></script>
 <script src="/malltemplate/jiutong/js/payment.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){ 
	     var un=$("#username");
	     var un_conent="手机号";
		 if($(un).val()=='' || $(un).val()==un_conent){
		     $(un).val(un_conent);
		     $(un).addClass("pasttext");
		 }	
		 //获得焦点事件
		 $(un).focus(function(){	
			 if($(un).val()=='' || $(un).val()==un_conent){	     
			     $(un).val("");
			      $(un).removeClass("error");
			  }
		 });	
		 	
		 //失去焦点事件
		 $(un).blur(function(){
		     if($(un).val()==''){
			    $(un).val(un_conent);
			    $(un).addClass("pasttext");
			 }
		 });		 
   });
function sub() {
	var check=$("#checkcode").val();
	if(check==''){
		alert("请输入验证码！");
		return false;
	}
	 $("#form2").submit();
 }



</script> 
<@s.hidden name="sl" />
<input type="hidden" id="timeMinus" value="0"/>
</html>
