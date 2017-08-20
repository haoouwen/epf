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
<@s.form id="indexForm" action="" method="post">
<@s.hidden id="recharge_platform" name="payment_code" value="${cfg_defaultpaymentMobile?if_exists}"/> 
<@s.hidden name="order_id" id="order_id"/>
<@s.hidden name="is_recharge" value="0"/>
<div class="forgetPswDiv">
   <p>账号名称：${memberuser.user_name}</p>
   <p>
         充值卡充值
   </p>
   <p class="forgetP">
   <input type="text" class="aifcardText" style="width:59px;" name="amount" maxLength="4" onkeyup="checkNum(this);" id="id_num"> -
   <input type="text" class="aifcardText" style="width:59px;" name="amount2" maxLength="4" onkeyup="checkNum(this);" id="id_num2"> -
   <input type="text" class="aifcardText" style="width:59px;" name="amount3" maxLength="4" onkeyup="checkNum(this);" id="id_num3"> -
   <input type="text" class="aifcardText" style="width:59px;" name="amount4" maxLength="4" onkeyup="checkNum(this);" id="id_num4">
               <font color="red"><span id="tipcard" style="display: none;">(请输入16位卡号)</span></font>
   </p>
   <p>请注意：充值后查看下帐号余额，确保充值卡充值成功，如果有问题联系我们网站客服咨询。</p>
   <p>客服电话：${servicetel?if_exists}</p>
   <p><a href="javascript:void(0);" onclick="cardSubComPay();" class="forgetBut">点击充值</a></p>
</div>
<@s.hidden name="cardskey" id="cardskey"/>
</@s.form>
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">

</body>
<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/payment.js" type="text/javascript"></script>
</html>
