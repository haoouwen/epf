<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><#if integral_state?if_exists=='0'>账户余额<#else>积分</#if>支付结果-${cfg_webname?if_exists}</title>
<meta name="author" content="久通宏达科贸（北京）有限公司">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">           
<meta http-equiv="Pragma" content="no-cache">
<link href="/malltemplate/jiutong/css/mbPublic.css" rel="stylesheet" type="text/css"></link>
<link href="/malltemplate/jiutong/css/mbShopProcess.css" rel="stylesheet" type="text/css"></link>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="top">
      <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>订单付款成功<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<!--支付订单信息-->
<div class="payInfor">
   订单号为:${order_id_str?if_exists}的订单支付成功，付款<#if integral_state?if_exists=='0'>金额为${total_balance?if_exists}元<#else>积分为${integral_total_amount?if_exists}</#if><br/>
</div>

<div class="payInfor">
   <p><a href="/mindex.html">返回首页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/webapp/orderDetail/${(goodsorder.order_id)?if_exists}.html">查看订单</a></p>
</div>
<!--尾部-->
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">
</body>
<script src="/wro/webapp_common.js" type="text/javascript"></script>
</html>