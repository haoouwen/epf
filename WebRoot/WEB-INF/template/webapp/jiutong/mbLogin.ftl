<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}—登录</title>

<meta name="author" content="久通宏达科贸（北京）有限公司">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">           
<meta http-equiv="Pragma" content="no-cache">
<link href="/malltemplate/jiutong/css/mbPublic.css" rel="stylesheet" type="text/css"></link>
<link href="/malltemplate/jiutong/css/mbLogin.css" rel="stylesheet" type="text/css"></link>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="top">
      <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>会员登录<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<@s.form action="/webapp/memberuser!webappUserLogin.action"  method="post"  validate="true"  id="webapplogin">
<!---->
<div class="loginDiv">
  <p class="pLogin"><@s.textfield id="user_name" name="memberuser.user_name" maxLength="20" cssClass="loginText"/></p>
  <p class="pTip" id="user_name_checkInfo"></p>
  <p class="pLogin"><@s.password id="passwd" name="memberuser.passwd" maxLength="20"  cssClass="loginText"/></p>
  <p class="pTip" id="password_checkInfo"></p>
  <p class="pLogin"><@s.textfield id="commentrand" name="commentrand" maxLength="4" cssClass="loginText"/><span class="spanValidation"><img src="/imgrand.action" style="vertical-align:middle;" onclick="changeValidateCode(this)"/></span></p>
  <p class="pTip" id="rand_checkInfo"></p>
  <p>
	    <span <#if i_username=='1'|| i_username=='2'> class="prompt"</#if> >
	         <@s.fielderror><@s.param>memberuser.user_name</@s.param></@s.fielderror>
	          <@s.fielderror><@s.param>login_limit</@s.param></@s.fielderror>
	    </span>
	    <span <#if i_password=='1' > class="prompt"</#if>    > 	
	        <@s.fielderror><@s.param>memberuser.passwd</@s.param></@s.fielderror> 
	    </span>
        <#if i_c=='1'><@s.fielderror><@s.param>commentrand</@s.param></@s.fielderror></#if>  
  </p>
  <p><input type="hidden" name="loc" id="refloc" value=""/>
  <a href="###" class="aLogin" onClick="webapp_login();">登录</a>
  </p>
  <p class="pForgot"><a href="/webapp/memberuser!pwd.action" class="">忘记密码？</a></p>
</div>

<@s.hidden  name="isAutoLogin" id="isAutoLogin"/>
<input type="hidden" id="custnum" name="custnum" value="${custnum?if_exists}"/>
<input type="hidden" id="registertype" name="registertype" value="${registertype?if_exists}"/>
</@s.form>
<footer class="botDiv">
     <div class="both2">
        <span class="f_left"><script src="/include/iswebapplogin.jsp?custnum=${custnum?if_exists}&registertype=${registertype?if_exists}"></script></span>
        <span class="f_right"><a href="#">返回顶部</a></span>
     </div>
     <div class="botCont">
       <p>${cfg_powerby?if_exists} <br/> ${cfg_beian?if_exists}</p>
     </div>
  </footer>
<#include "/a/webapp/mbCommon.html">
</body>
<input type="hidden" id="logincenter" value="1" />
<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/login.js" type="text/javascript"></script>
</html>
