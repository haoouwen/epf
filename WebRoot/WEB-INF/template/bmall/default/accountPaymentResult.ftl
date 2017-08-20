<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title><#if integral_state?if_exists=='0'>账户余额<#else>积分</#if>支付结果-${cfg_webname?if_exists}</title>
<script type="text/javascript" src="/include/common/js/jquery-1.4.4.min.js"></script>
<script src="/malltemplate/bmall/js/jt_public.js" type="text/javascript"></script>
<link href="/malltemplate/bmall/css/jt_public.css" rel="stylesheet" type="text/css"/>
<link href="/malltemplate/bmall/css/orderSuccess.css" rel="stylesheet" type="text/css"/>
<link href="/malltemplate/index/css/mall_public.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/malltemplate/bmall/js/jt_public.js" ></script>
</head>
<body>
<@s.form id="commonForm"  action="#"  method="post" >
<!--头部--> 
<#include "/WEB-INF/template/bmall/default/topest.ftl">
<div class="logoSearDiv">
   <!--logo-->
   <div class="logo"><a href="/"><img src="${cfg_logourl}" width="246px" height="64px" /></a></div>
   <!--支付步骤-->
   <div class="successProcess"></div>
</div>
<!--内容-->
<div class="registb">
   <div class="regist">
      <!--步骤提示-->
      <div class="pswSucceed"></div>
      <!---->
      <div class="regMain">
         <div class="regSuMian">
           <div class="regsucont">
              <div class="regsbpic"></div>
              <p>订单号为:${order_id_str?if_exists}的订单支付成功，付款<#if integral_state?if_exists=='0'>金额为${total_balance?if_exists}元<#else>积分为${integral_total_amount?if_exists}</#if><br/></p>
           </div>
           <div class="returnhome">
              <p><a href="/">返回首页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/bmall_Goodsorder_buyOrderView.action?goodsorder.order_id=${order_id_str?if_exists}&order_state=2">查看订单</a></p>
           </div>
           
         </div>
      </div>
   </div>
</div>
<#include "/a/bmall/mallbottom.html">
	
</@s.form>
</body>
<script src="/malltemplate/bmall/js/jqueryPopupLayer.js" type="text/javascript"></script>
</html>

