<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>微信支付成功-${cfg_webname?if_exists}</title>
<script src="/malltemplate/bmall/js/jt_public.js" type="text/javascript"></script>
<link href="/malltemplate/bmall/css/jt_public.css" rel="stylesheet" type="text/css"/>
<link href="/malltemplate/bmall/css/orderSuccess.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<@s.form id="commonForm"  action="#"  method="post" >
<!--头部--> 
<#include "/WEB-INF/template/bmall/default/topest.ftl">
<div class="logoSearDiv">
   <!--logo-->
   <div class="logo"><a href="/"><img src="${cfg_logourl}" width="246px" height="64px" /></a></div>
   <!--支付步骤-->
   <#if is_recharge=="1"><div class="successProcess"></div></#if>
</div>
<!--内容-->
<div class="registb">
   <div class="regist">
      <div class="regMain">
         <div class="regSuMian">
           <div class="regsucont">
              <div class="<#if is_recharge?if_exists=='1'>regsbpic<#else>rechargeSucceed</#if>"></div>
              <p><#if is_recharge=="0">充值</#if>订单号为:${order_id_str?if_exists}的订单支付成功，<#if is_recharge=="1">付款<#else>充值</#if>金额为${sub_total_price?if_exists}元<br/></p>
           </div>
           <div class="returnhome">
              <#if is_recharge=="1">
                 <p><a href="/">返回首页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/bmall_Goodsorder_buyOrderView.action?goodsorder.order_id=${order_id_str?if_exists}&order_state=2">查看订单</a></p>
              <#else>
                 <p><a href="/">返回首页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/bmall_Fundrecharge_list.action">查看充值信息</a></p>
              </#if>
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

