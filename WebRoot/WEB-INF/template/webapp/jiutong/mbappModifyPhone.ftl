<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}—修改手机验证</title>

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
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>修改手机验证 <span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<#if step_flag?if_exists=='1'>
 <@s.form action="" method="post" name="verifyFirstForm" id="verifyFirstForm">
<!--步骤显示-->
<div class="forgetStep">
    <span><i class="selI">1</i>验证身份</span>
    <span><i>2</i>修改手机验证</span>
    <span><i>3</i>完成</span>
    <br class="clear"/>
</div>
<!---->
<div class="forgetPswDiv">
   <p><b>已验证手机：</b>
      <b class="redB">
            ${(memberuser.cellphone)?if_exists}
      </b>
   </p>
   <p id="phoneError"></p>
   <p><input type="button" onclick="sendcode('${(memberuser.cellphone)?if_exists}');" class="getYzm" value="获取短验证码"></p>
   <p class="gray">如在120秒内还没有收到短信验证码，请重新获取验证码</p>
   <p class="forgetP"><input type="text" id="cp_check" name="cp_check" class="forgetText"></p>
   <p id="cpError"></p>
   <p class="gray">若该手机号码一直无法接收验证短信，请联系客服或者到账号管理中修改手机验证</p>
   <p><a href="javascript:void(0);" onclick="verifyFirst('${(memberuser.cellphone)?if_exists}');" class="forgetBut">下一步</a></p>
</div>
</@s.form>
<#elseif step_flag?if_exists=='2'>

 <@s.form action="" method="post" name="verifySecondForm" id="verifySecondForm">
<!--步骤显示-->
<div class="forgetStep">
    <span><i>1</i>验证身份</span>
    <span><i class="selI">2</i>修改手机验证</span>
    <span><i>3</i>完成</span>
    <br class="clear"/>
</div>
<!---->
<div class="forgetPswDiv">
   <p><b>新的手机号码</b></p>
   <p class="forgetP"><input type="text" id="new_mobile" name="new_mobile" class="forgetText" value="请输入手机号码"></p>
   <p id="phoneError"></p>
   <p><input type="button" onclick="getcode();" class="getYzm" value="获取短验证码"/></p>
   <p><b>输入验证码</b></p>
   <p class="forgetP"><input type="text" id="secondCode" name="secondCode" class="forgetText" value="请输入验证码"></p>
   <p id="codeError"></p>
   <p><a href="javascript:void(0);" onclick="verifyThird();" class="forgetBut">下一步</a></p>
</div>
</@s.form>
<#elseif step_flag?if_exists=='3'>
<!--步骤显示-->
<div class="forgetStep">
    <span><i>1</i>验证身份</span>
    <span><i>2</i>修改手机验证</span>
    <span><i class="selI">3</i>完成</span>
    <br class="clear"/>
</div>
<!--完成-->
<div class="registerDiv">
    <div class="sucRegister">
       <p><b>手机验证成功</b></p>
       <p>您重新设置的手机号码是：<b>${(memberuser.cellphone)?if_exists}</b></p>
    </div>
</div>
</#if>
<input type="hidden" id="timeMinus" value="0"/>
<!--尾部-->
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">


</body>

<script src="/wro/webapp_common.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/menberuser.js" type="text/javascript"></script>
</html>
