<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}—修改密码</title>

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
<link href="/malltemplate/jiutong/css/mbRegister.css" rel="stylesheet" type="text/css"></link>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<@s.form action="/webapp/memberuser!updatePassword.action"  method="post" name="formshopcongif" validate="true" id="formshopcongif">
<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>修改密码<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<!--步骤显示-->
<div class="pswStep">
    <span><i <#if !isok>class="selI"</#if>>1</i>修改登录密码${isok}</span>
    <span><i <#if isok>class="selI"</#if>>2</i>完成</span>
    <br class="clear"/>
</div>
<#if !isok>
<!---->
<div class="forgetPswDiv">
   <p><b>输入旧密码</b></p>
   <p class="forgetP">
   <input type="password" class="forgetText"  name="oldpasswd" value="请输入密码">
   </p>
   <p>
     <@s.fielderror><@s.param>oldpasswd</@s.param></@s.fielderror>
   </p>
   <p><b>输入新密码</b></p>
   <p class="forgetP">
   <input type="password" class="forgetText" name="newpasswd" onkeyup="javascript:checkpass(this)"  id="pass" onblur="setpswstrong(this)"  value="请输入新密码">
   </p>
   <p>
     <span id="password_label"><b class="lowb"></b></span><span><@s.fielderror><@s.param>newpasswd</@s.param></@s.fielderror></span>
   </p>
    <p><b>再次输入新密码</b></p>
   <p class="forgetP">
   <input type="password" class="forgetText" value="请再次输入密码" name="confirmpasswd">
   </p>
    <p>
     <span><@s.fielderror><@s.param>confirmpasswd</@s.param></@s.fielderror></span>
   </p>
   <p><a href="###" onclick="document.getElementById('formshopcongif').submit();" class="forgetBut">提交</a></p>
</div>
<#else>
<!--完成-->
<div class="registerDiv">
    <div class="sucRegister">
       <p><b>密码修改成功</b></p>
       <p>您的密码已经修改成功，请记住您刚修改的密码</p>
       <p><a href="/webapplogin.html" class="aRegister">重新登录</a></p>
    </div>
</div>

</#if>
<!--尾部-->
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">

<@s.hidden name="memberuser.pass_strength" value="0" id="psw_strong" />
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>  
 </@s.form>
</body>
<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
<script type="text/javascript" src="/malltemplate/jiutong/js/menberuser.js"></script>
</html>
