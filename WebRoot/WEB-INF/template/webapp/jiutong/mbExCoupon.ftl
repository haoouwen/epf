<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}—兑换优惠券</title>

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
<@s.form action="/webapp/comsumer!exCoupon.action"  method="post" name="formshopcongif" validate="true" id="formshopcongif">
<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>兑换优惠券<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<!---->
<div class="forgetPswDiv">
   <p><b>输入优惠券号码</b></p>
   <p class="forgetP">
   <input type="text" class="forgetText"  name="coupon_no">
   </p>
   <p>
     <@s.fielderror><@s.param>coupon_no</@s.param></@s.fielderror>
   </p>
   <p><a href="###" onclick="document.getElementById('formshopcongif').submit();" class="forgetBut">提交</a></p>
</div>
<!--尾部-->
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">
 </@s.form>
</body>
<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
<script type="text/javascript" src="/malltemplate/jiutong/js/menberuser.js"></script>
</html>
