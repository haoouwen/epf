<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}充值</title>

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
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>充值<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<@s.form id="goConfirmPay" action="" method="post">
<@s.hidden id="recharge_platform" name="payment_code" value="${cfg_defaultpaymentMobile?if_exists}"/>
<@s.hidden  name="pa_MP" id="pa_MP" value="${session_cust_id?if_exists}-${session_user_id?if_exists}-0_2" /> 
<@s.hidden name="is_recharge" value="0"/>
<div class="forgetPswDiv">
   <p>账号名称：${memberuser.user_name}</p>
   <p>
         充值平台：
         <#list paymentList as plist>
	         <#if cfg_defaultpaymentMobile==plist.pay_code>
	              ${(plist.pay_name)?if_exists}
	         </#if>
		 </#list>
   </p>
   <p class="forgetP"><input type="text" class="forgetText" id="recharge_amount" name="recharge_amount" placeholder="请输入充值金额"></p>
   <p>请注意：我们目前支持国内主流银行储蓄卡充值，在线支付成功后，充值金额会在5分钟内到账；</p>
   <p>客服电话：${servicetel?if_exists}</p>
   <p><a href="javascript:void(0);" onclick="memberRecharge();" class="forgetBut">下一步</a></p>
</div>
</@s.form>
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">

</body>
<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/payment.js" type="text/javascript"></script>
</html>
