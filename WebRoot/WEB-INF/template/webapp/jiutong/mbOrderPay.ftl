<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}—订单支付</title>

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
<@s.form id="goConfirmPay" action="/webapp/order!useNumPay.action" method="post">
<@s.hidden id="order_id_str" name="order_id_str"/>
<@s.hidden id="total_price" name="total_price" value="${total_amount?if_exists}"/>
<@s.hidden id="sub_total_price" name="sub_total_price"/>
<@s.hidden id="checked_num" name="checked_num"/>
<@s.hidden id="use_num" name="use_num" value="${(memberfund.use_num)?if_exists}"/>
<@s.hidden id="use_num_pay" name="use_num_pay" />
<@s.hidden id="total_price" name="v_amount" value="${total_amount?if_exists}"/>
 <@s.hidden id="total_amount" name="total_amount" value="${total_amount?if_exists}"/>
<@s.hidden  name="remark1"  value="${order_id_str?if_exists}" />
<@s.hidden  name="remark2"  value="${session_cust_id?if_exists},${session_user_id?if_exists},1" />
<@s.hidden  name="pa_MP" id="pa_MP" value="${session_cust_id?if_exists}-${session_user_id?if_exists}-0_1" />
<@s.hidden  name="is_virtual"/>
<@s.hidden id="session_cust_id" name="session_cust_id" value="${session_cust_id?if_exists}"/>
<@s.hidden id="order_type" name="order_type"/>
<@s.hidden name="is_recharge" value="1"/>
<@s.hidden id="type" name="type" value="${type?if_exists}"/>
<@s.hidden id="pay_terminal" name="pay_terminal" value="${pay_terminal?if_exists}"/>

<div class="top">
      <a href="/webapp/goodsorder!webappAllOrder.action" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>订单付款<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<!--支付订单信息-->
<div class="payInfor">
   <p><b>订单提交成功，请您尽快支付</b></p>
   <p>订单编号：${(order_id_str)?if_exists}</p>
   <p>应付款金额：<b>￥${total_amount?if_exists}</b></p>
</div>
<div class="payInfor">
   <p>请您在提交订单后24小时内完成支付,否则订单自动取消</p>
</div>

<!--支付,配送方式及发票信息-->
<div class="way">
   <div class="wayh2">请选择支付方式<span id="tipno"></span></div>
   <div class="wayDiv">
     <ul>
       <li id="alipay_id"><a href="javascript:void(0);" onclick="webappPay('alipaywap');"><h3 class="zfbh3">无需开通网银即可支付</h3></a></li>
       <li><a href="javascript:void(0);" onclick="webappPay('unionpaywap');"><h3 class="chinapayh3">无需开通网银即可支付</h3></a></li>
       <li id="weixinpay"><a href="javascript:void(0);" onclick="webappPay('weixinpay');"><h3 class="wxpayh3">无需开通网银即可支付</h3></a></li>
     </ul>
   </div>
</div>

<!--尾部-->
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">
</@s.form>
</body>
<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/payment.js" type="text/javascript"></script>
</html>
