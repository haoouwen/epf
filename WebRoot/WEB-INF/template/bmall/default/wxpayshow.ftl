<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>微信支付-${cfg_webname?if_exists}</title>
<link href="/malltemplate/bmall/css/jt_public.css" rel="stylesheet" type="text/css"/>
<link href="/malltemplate/bmall/css/payProcess.css" rel="stylesheet" type="text/css"/>
<script src="/include/common/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/include/common/js/common.js"></script>
<script type="text/javascript" src="/malltemplate/bmall/js/jquery.qrcode.min.js"></script>
<script type="text/javascript" src="/include/components/payment/weixinpay/wxpay.js"></script>
<script src="/malltemplate/bmall/js/jt_public.js" type="text/javascript"></script>
<link href="/malltemplate/index/css/mall_public.css" rel="stylesheet" type="text/css" />
</head>
<body>
<@s.form id="commonForm"  action="#"  method="post" >
<!--头部--> 
<#include "/WEB-INF/template/bmall/default/topest.ftl">
<!--logo，搜索-->
<div class="logoSearDiv">
   <!--logo-->
   <div class="logo"><a href="/"><img src="${cfg_logourl}" width="246px" height="64px" /></a></div>
   <!--支付步骤-->
   <#if is_recharge=="1"><div class="successProcess"></div></#if>
</div>	
<!--内容-->
<div class="sucOrder">
<@s.hidden name="wxpay_scan" id="wxpay_scan"/>
<@s.hidden name="order_id_str" id="order_id_str"/>
<@s.hidden name="sub_total_price" id="sub_total_price"/>
<@s.hidden name="is_recharge" id="is_recharge"/>
   <#if is_recharge=='1'><#if is_check_email?if_exists=='1' || is_check_email?if_exists=='' || is_check_mobile?if_exists=='' || is_check_mobile?if_exists=='1' || pay_pass?if_exists==''><div class="sucTips">为保障您账号及资金的安全：请您尽快启用<#if pay_pass?if_exists==''>支付密码，</#if><#if is_check_mobile?if_exists=='' || is_check_mobile?if_exists=='1'>验证手机，</#if><#if is_check_email?if_exists=='1' || is_check_email?if_exists==''>验证邮箱，</#if> <a  href="/bmall-accountCenter.action?parentMenuId=6644344633&selli=2251641346">立即启动>></a></div></#if></#if> 
   <div class="payMain">
        <h2><#if is_recharge=="1"><#if order_pay_tip=="1">请您及时付款，以便订单尽快处理!<#else>订单提交成功，请您尽快付款!</#if><#else> 充值订单提交成功，请尽快充值</#if></h2>
       <p class="orderp"><span>订单号：${order_id_str?if_exists}
       </span><span>应付金额：<b>${sub_total_price?if_exists}元</b></span></p>
       <#if is_recharge=="1">
         <p class="tipsp">请您在提交订单后<span>
          <#if (cfg_publictimeout?if_exists?number>60)>${cfg_publictimeout?if_exists?number/60}小时
           <#else>${cfg_publictimeout?if_exists}分钟
         </#if>
        </span>内完成支付,否则订单自动取消
      </p>
      </#if>    
     <div class="selPayStyle">
         
         <div class="payh2">
            <ul>
              <li class="selli">微信支付-扫码支付</li>
            </ul>
         </div>  
            
         <!--微信支付-->
         <div class="wxPayDiv"><div class="ewmDiv"  id="qrcode"></div></div>
         <!--返回其他支付方式-->
         <#if is_recharge=="1">
           <div class="otherPay"><a href="###" 
               onclick="linkToInfo('/mall/order!goPay.action','order_pay_tip=1&order_id_str=${order_id_str}&use_num_pay=${sub_total_price?if_exists}&total_amount=${sub_total_price?if_exists}&all_total=${sub_total_price?if_exists}&sendway=0');"> &nbsp;<&nbsp; 选择其他支付方式</a></div>
           </div>
         </#if> 
   </div>
   <#if is_recharge=="1"><div class="orderTips">完成支付后，您可以：<a href="/bmall-buyer.action">查看订单状态</a><a href="/" >继续购物</a><!--<a href="#">问题反馈</a>--></div></#if> 
</div>  

<!--尾部-->
  </div>
<#include "/a/bmall/mallbottom.html">
	
</@s.form>
<script src="/malltemplate/bmall/js/jqueryPopupLayer.js" type="text/javascript"></script>
</body>

</html>

