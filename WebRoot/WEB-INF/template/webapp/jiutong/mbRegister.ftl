<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}—注册</title>

<meta name="author" content="久通宏达科贸（北京）有限公司">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">           
<meta http-equiv="Pragma" content="no-cache">
<link href="/malltemplate/jiutong/css/mbPublic.css" rel="stylesheet" type="text/css"></link>
<link href="/malltemplate/jiutong/css/mbRegister.css" rel="stylesheet" type="text/css"></link>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>

<body>

<div class="top">
      <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>会员注册<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
  <#if step_flag?if_exists=="1">
     <@s.form action="" method="post" name="registerFirstForm" id="registerFirstForm">
		<!--注册第1步-->
		<div class="registerDiv">
		   
		   <#if registertype?if_exists=="1">
		      		 <p class="pRegister">您当前所在区域： ${area_attr?if_exists}-${custnum?if_exists}号店</p>
		      		 <input type="hidden" id="custnum" name="custnum" value="${custnum?if_exists}"/>
		      		 <input type="hidden" id="registertype" name="registertype" value="${registertype?if_exists}"/>
		  <#elseif registertype?if_exists=="2">
		             <p class="pRegister">您当前所在： 直营店&nbsp;${custnum?if_exists}号店</p>
		             <input type="hidden" id="custnum" name="custnum" value="${custnum?if_exists}"/>
		      		 <input type="hidden" id="registertype" name="registertype" value="${registertype?if_exists}"/>
		  <#else>
		      <p class="pRegister"> <@s.select id="membernum"  name="membernum"  list="areaList" listValue="area_name"
						       listKey="area_number" cssClass="regSelect"  headerKey="" headerValue="请选择地区" value=""/>
			   </p>
		  </#if>    
		   <p id="areaError" style="color:red;"></p>
		    <p class="pRegister"><@s.textfield id="phone" name="phone" cssClass="regText"  onfocus="CellphoneForm();" onblur="CellphoneIsNull();"  maxlength="15" placeholder="请输您的手机号码" /></p>
		  <p id="phoneError" style="color:red;"></p>
		   <p class="pRegister"><@s.textfield id="passwd" name="passwd" cssClass="regText"  onfocus="PasswordForm();" maxLength="20" onblur="PasswdIsNull();" placeholder="请输入密码" /></p>
		  <p id="passwdError" style="color:red;"></p>
		  <p class="pRegister"><@s.textfield id="confirm_pwd" name="confirm_pwd" cssClass="regText" onfocus="Confirm_pwdForm();" onblur="Confirm_pwdIsNull();" maxLength="20" placeholder="请再次输入密码" /></p>
		  <p id="confirm_pwdError" style="color:red;"></p>     
		  <p class="pRegister">
		      <@s.textfield name="commentrand" maxLength="4" cssClass="regText" id="commentrand" onfocus="RandsForm();" onblur="RandsIsNull();" placeholder="请输入验证码" />
		      <span class="spanValidationR"><img src="/imgrand.action"  onclick="changeValidateCode(this)" id="validateCode"/></span>
		  </p>
		  <p id="randsError" style="color:red;"></p>
		  <p class="pRegister"><@s.textfield id="cp_check" name="cp_check"  maxlength="6" cssClass="regText"  placeholder="请输入短信验证码" /><span class="spanValidation">
		  <a id="cpc"  onclick="sendcode();" >获取短信验证码</a></span></p>
	      <p id="cpError" style="color:red;"></p>
		  <p><input type="checkbox" id="agreeId" class="checkBox" checked="checked"/>同意${cfg_webname?if_exists}注册协议</p>
		  <p><a href="javascript:void(0);" class="aRegister" onclick="registerFirst();">立即注册</a></p>
		  <input type="hidden" id="timeMinus" value="0"/>
		  <p class="pForgot"><a href="/webapp/memberuser!pwd.action" class="">忘记密码？</a></p>
		</div>
     </@s.form> 
	<#elseif step_flag?if_exists=="3">
	     <!--注册第3步 登陆成功-->
		<div class="registerDiv">
		    <div class="sucRegister">
		       <p><center style="color:green">注册成功</center></p>
		       <!--<p><span id="secondSuccess"></span>秒后自动跳转到首页，如未跳转请点击下面继续按钮操作！</p>-->
		       <p style="color:black">&bull;关注我们的微信公众号</p>
		       <p><img style="" src="/uploads/images/20150928/120112592828.jpg" width="85" height="85"></p>
		       <p style="color:black">&bull;下载App</p>
		       <p><a href="http://www.epff.cc/webapp/appdown.html" style="color:blue">点击下载App</a></p>
		       <p><a href="/webapp/memberuser!webappUserLogin.action?memberuser.user_name=${user_name?if_exists}&memberuser.passwd=${login_passwd?if_exists}&login_state=2" class="aRegister">回到首页</a></p>
		    </div>
		</div>
  </#if>

<!--尾部-->
<@s.hidden   name="user_name" id="user_name"/>
<@s.hidden name="login_passwd" id="login_passwd"/>
<@s.hidden name="step_flag" id="step_flag"/>
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">
</body>
<input type="hidden" id="logincenter" value="1" />
<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/register.js" type="text/javascript"></script>
</html>
