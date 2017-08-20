<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${cfg_webname?if_exists}—全部订单</title>
<meta name="author" content="久通宏达科贸（北京）有限公司">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">           
<meta http-equiv="Pragma" content="no-cache">
<link href="/wro/webapp_common.css" rel="stylesheet" type="text/css"/>
<link href="/malltemplate/jiutong/css/mbMember.css" rel="stylesheet" type="text/css"></link>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<@s.form action="/webapp/goodsorder!webappAllOrder.action" method="post" id="indexForm">

<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>
   <span class="topSpan"><#if state_text=='1'>全部订单<#else>${state_text}</#if></span>
   <div class="claOrder">
      <div class="trDot"></div>
      <a href="javascript:void(0);" onclick="searchByOrderState('','allorder');" class="allorder">全部订单</a>
      <a href="javascript:void(0);" onclick="searchByOrderState('1','payorder');" class="payorder">待付款（${waitPayNunm?if_exists}）</a>
      <a href="javascript:void(0);" onclick="searchByOrderState('2','deorder');" class="deorder">待发货（${waitDeliveryNum?if_exists}）</a>
      <a href="javascript:void(0);" onclick="searchByOrderState('3','reorder');" class="reorder">待收货（${waitReceiptNum?if_exists}）</a>
      <a href="javascript:void(0);" onclick="searchByOrderState('7','evorder');" class="evorder">待评价（${waitEvaluationNum?if_exists}）</a>
      <a href="javascript:void(0);" onclick="searchByDate('1','threeorderin');" class="threeorderin">三个月内的订单</a>
      <a href="javascript:void(0);" onclick="searchByDate('2','threeorderout');" class="threeorderout">三个月前的订单</a>
   </div>
   <span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<#if goodsorderList?if_exists && goodsorderList.size() gt 0>
<!--订单不为空-->
<div class="hasOrder">
  
  <#list goodsorderList as goodsorder>
   <!--待收货-->
  <header class="hoDiv">
    <table>
      <tr>
        <td>
          <div class="imgDiv">
              
                <#assign num=0>
                <#assign i=0>
                <#assign b=0>
       		    <#assign goods_id_str="">
       		    <#assign goods_name_str="">
       		    <#assign list_img_str="">
       		    <#assign sale_price_str="">
	           		<!-- 普通订单--> 
					<#list detailList as detail>
						  <#if ((detail.order_id)?if_exists)==((goodsorder.order_id)?if_exists)>
							  	 <#assign num=num+1>   
							  	 <#assign i=i+1>
						     <#if i==1>
						          <#assign goods_id_str="${detail.goods_id?if_exists}">
						         <#assign img_path="${(detail.temporary_goodsimg)?if_exists}">
						         <#assign list_img_str="${detail.temporary_goodsimg?if_exists}">
						         <#assign sale_price_str="${detail.goods_price?if_exists}">
						       	<a href="/webapp/orderDetail/${(detail.order_id)?if_exists}.html" target="_blank">
				      	        <img src="<#if img_path!=''>${img_path?if_exists}<#else>${(cfg_nopic)?if_exists}</#if>" class="f_left" align="absmiddle"></a>
						     </#if>
		      		 </#if>
		      		 </#list>	
		      		  <#assign i=0>
             
          </div>
        </td>
        <td>	
            <p > 订单号：<a href="/webapp/orderDetail/${(goodsorder.order_id)?if_exists}.html"><span class="pcOrange">${(goodsorder.order_id)?if_exists}</span></a></p>
                 <#list detailList as detaila>
					  <#if ((detaila.order_id)?if_exists)==((goodsorder.order_id)?if_exists)>
					 <#assign b=b+1>
						  <#if b==1>
							  	 <#assign goods_name_str="${(detaila.temporary_goodsname)?if_exists}">   
						        <p><a href="/webapp/orderDetail/${(goodsorder.order_id)?if_exists}.html">${goods_name_str?if_exists}</a></p>
                          </#if>
					  </#if>
	      		 </#list>	
	      		 <#assign b=0>
	       <p>订单类型：
	       <#if (goodsorder.is_sea)?if_exists=='0'>
               保税订单
          <#elseif (goodsorder.is_sea)?if_exists=='1'>
               直邮订单
          </#if>
	       </p>
           <p> <#if (goodsorder.order_state)?if_exists=="1">应<#else>实</#if>付款：<b>￥${(goodsorder.tatal_amount)?if_exists}</b></p>
           <#if (goodsorder.order_state)?if_exists=="1">
               <p>
                    <a href="/webapp/paySend.html?order_pay_tip=1&order_id_str=${(goodsorder.order_id)?if_exists}&use_num_pay=${(goodsorder.tatal_amount)?if_exists}&total_amount=${(goodsorder.tatal_amount)?if_exists}&all_total=${(goodsorder.tatal_amount)?if_exists}&sendway=${(goodsorder.send_mode)?if_exists}&type=1&pay_terminal=1" class="mBut">付款</a>
               </p>
           <#elseif (goodsorder.order_state)?if_exists=="2">
                 <#if (goodsorder.deliver_state)!="0">
                      <p class="pcOrange">订单已审核</p>
                  <#else>
                       <p class="pcOrange">会员已付款</p>
                  </#if>
           <#elseif (goodsorder.order_state)?if_exists=="3">
               <p class="pcOrange">商家已发货
                <#if (goodsorder.is_sea)?if_exists=='1'&& goodsorder.is_clearance?if_exists=="0">
		        <!--海外直发订单_已发货待通关-->
			        /待通关
			     <#elseif (goodsorder.is_sea)?if_exists=='1' && goodsorder.is_clearance?if_exists=="1">
			        /通关成功
			     <#elseif (goodsorder.is_sea)?if_exists=='1' && goodsorder.is_clearance?if_exists=="2">
			        /通关失败
				 </#if>
               </p>
               <p>
                <#if (goodsorder.is_sea)?if_exists=='1'>
                    <#if goodsorder.is_clearance?if_exists=="1">
                         <a href="javascript:void(0);" class="mBut" 
              			 onclick="orderconfirmreceipt('${goodsorder.order_id?if_exists}','${goodsorder.tatal_amount?if_exists}')">确认收货</a>
              			  <a href="http://m.kuaidi100.com/index_all.html?type=${goodsorder.smode_name?if_exists}&postid=${goodsorder.send_num?if_exists}&callbackurl=${cfg_mobiledomain}/webapp/goodsorder!webappAllOrder.action"  target="_blank" class="mBut">物流信息</a>
                    </#if>
                <#else>
                 <a href="javascript:void(0);" class="mBut" 
                 onclick="orderconfirmreceipt('${goodsorder.order_id?if_exists}','${goodsorder.tatal_amount?if_exists}')">确认收货</a>
                  <a href="http://m.kuaidi100.com/index_all.html?type=${goodsorder.smode_name?if_exists}&postid=${goodsorder.send_num?if_exists}&callbackurl=${cfg_mobiledomain}/webapp/goodsorder!webappAllOrder.action"  target="_blank" class="mBut">物流信息</a>
                </#if>
              </p>
           <#elseif (goodsorder.order_state)?if_exists=="4">
               <p class="pcOrange">退款中</p>
           <#elseif (goodsorder.order_state)?if_exists=="5">
               <p class="pcOrange">退款成功</p>
           <#elseif (goodsorder.order_state)?if_exists=="6">
               <p class="pcOrange">退款失败</p>
           <#elseif (goodsorder.order_state)?if_exists=="7">
               <p class="pcGreen">已签收待评价</p>
               <p><a href="/webapp/refundapp!appRefundGoods.action?order_id=${goodsorder.order_id?if_exists}" class="mBut">申请售后</a>
               <a href="/webapp/goods!auditList.action?order_id_s=${goodsorder.order_id?if_exists}" class="mBut">评价</a>
               </p>
           <#elseif (goodsorder.order_state)?if_exists=="8">
               <p class="pcGreen">交易完成</p>
               <p><a href="/webapp/refundapp!appRefundGoods.action?order_id=${goodsorder.order_id?if_exists}" class="mBut">申请售后</a></p>
            <#elseif (goodsorder.order_state)?if_exists=="0">
                <#if (goodsorder.pay_time)?if_exists&&(goodsorder.pay_trxid)?if_exists>
	             <!-- 取消订单-已支付-->
	             <p class="pcOrange">交易关闭(退款完成)</p>
	              <#else>
	               <!-- 取消订单-未付款-->
	           			<p class="pcOrange">订单取消</p>
	             </#if>
           </#if>
            
        </td>
      </tr>
    </table>
  </header>
  </#list>
  
  <div class="hoDiv page">
     ${pageBar?if_exists}
   </div>
  
</div>

<#else>
<!--订单为空-->
<#include "/WEB-INF/template/webapp/jiutong/mbemptyorder.ftl">
</#if>
<@s.hidden name="order_state_s" id="order_state_s"/>
<@s.hidden name="date_s" id="date_s"/>
<@s.hidden name="jsontotal" id="jsontotal_id"/> 
<@s.hidden name="goods_order_id" id="order_id"/>
<@s.hidden name="refund_s" id="refund_s"/>
<@s.hidden name="receipt_flag" id="receipt_flag" value="1"/>
<@s.hidden name="state_text" id="state_text" value="1"/>
</@s.form>
<!--取消订单弹出层-->
<div class="popupDiv" id="cancelOrderId">
</div>
<!--确认收货弹出层-->
<div class="popupDiv" id="confirmOrderId">
</div>
<!--尾部-->
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">
</body>
<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/mbMember.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/goodsorder.js" type="text/javascript"></script>
</html>
