<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}—订单详情</title>

<meta name="author" content="久通宏达科贸（北京）有限公司">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">           
<meta http-equiv="Pragma" content="no-cache">
<link href="/malltemplate/jiutong/css/mbPublic.css" rel="stylesheet" type="text/css"></link>
<link href="/malltemplate/jiutong/css/mbMember.css" rel="stylesheet" type="text/css"></link>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>

<body>

<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>订单详情<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">

<@s.form action="/webapp/goodsorder!webappOrderDetail.action" method="post" id="indexForm">
<!--未付款-->

<!--订单信息-->
<div class="orderDetailTop">
  <p>订单类型：
  <#if (goodsorder.is_sea)?if_exists=='0'>
       保税订单
  <#elseif (goodsorder.is_sea)?if_exists=='1'>
       直邮订单
  </#if>
  </p>
  <p>订单编号：${(goodsorder.order_id)?if_exists}</p>
  <p>订单金额：￥${(goodsorder.tatal_amount)?if_exists}</p>
  <p>订单日期：${(goodsorder.order_time)?if_exists}</p>
  <p>订单状态：
    <#if goodsorder.order_state=='0'>
                <#if (goodsorder.pay_time)?if_exists&&(goodsorder.pay_trxid)?if_exists>
	             <!-- 取消订单-已支付-->
	             交易关闭
	              <#else>
	               <!-- 取消订单-未付款-->
	           			取消订单
	             </#if>
	<#elseif goodsorder.order_state=='2'>
	             <#if (goodsorder.deliver_state)!="0"> 订单已审核<#else>会员已付款 </#if>
     <#else>
  		 <#if (goodsorder.is_sea)?if_exists=='0' && (goodsorder.is_kjtsuccess)?if_exists=='1'>
			    <#list kjtcommparaList as clist>
			         <#if goodsorder.kjtorder_state==clist.para_value>
			            	${clist.para_key}
			         </#if>
			    </#list>
		<#else>
		   <#list commparaList as comlist>
	           <#if comlist.para_value==goodsorder.order_state> 
	           ${(comlist.para_key)?if_exists}
	           </#if>
			</#list>
			 <#if goodsorder.order_state?if_exists=="3" && goodsorder.is_clearance?if_exists=="0">
		        <!--海外直发订单_已发货待通关-->
		        /待通关
		     <#elseif goodsorder.order_state?if_exists=="3" && goodsorder.is_clearance?if_exists=="1">
		        /通关成功
		     <#elseif goodsorder.order_state?if_exists=="3" && goodsorder.is_clearance?if_exists=="2">
		        /通关失败
			 </#if>
		</#if>		
      </#if>
	                 
  </p>
  <p class="orderDetailP">
	   <#if (goodsorder.order_state)?if_exists=='1'>
		     <a href="/webapp/order!goPay.action?order_pay_tip=1&order_id_str=${(goodsorder.order_id)?if_exists}&use_num_pay=${(goodsorder.tatal_amount)?if_exists}&total_amount=${(goodsorder.tatal_amount)?if_exists}&all_total=${(goodsorder.tatal_amount)?if_exists}&sendway=${(goodsorder.send_mode)?if_exists}&type=1&pay_terminal=1" class="mBut"><i class="iRed">付款</i></a>
		     <a href="javascript:void(0);" onclick="cancelOrder('${(goodsorder.order_id)?if_exists}','${(goodsorder.tatal_amount)?if_exists}');"><i class="iRed">取消订单</i></a> 
	    <#elseif (goodsorder.order_state)?if_exists=='2'>
	           <#if (goodsorder.deliver_state)!="0">
	               <a href="/webapp/goodsorder!webappAllOrder.action" class="loderDetailbt" ><i >返回订单</i></a>
	           <#else>
	               <a href="javascript:void(0);" onclick="cancelOrder('${(goodsorder.order_id)?if_exists}','${(goodsorder.tatal_amount)?if_exists}');"><i class="iRed">取消订单</i></a> 
	              <a href="/webapp/goodsorder!webappAllOrder.action" ><i >返回订单</i></a>
	            </#if>
	    <#elseif (goodsorder.order_state)?if_exists=='3'>
	     	  <#if (goodsorder.is_sea)?if_exists=='1'>
                    <#if goodsorder.is_clearance?if_exists=="1">
                        <a href="javascript:void(0);" onclick="orderconfirmreceipt('${goodsorder.order_id?if_exists}','${goodsorder.tatal_amount?if_exists}')"><i class="iRed">确认收货</i></a>
                    </#if>
                <#else>
                <a href="javascript:void(0);" onclick="orderconfirmreceipt('${goodsorder.order_id?if_exists}','${goodsorder.tatal_amount?if_exists}')"><i class="iRed">确认收货</i></a>
                </#if>
		     <a href="/webapp/goodsorder!webappAllOrder.action" ><i >返回订单</i></a>
		 <#elseif (goodsorder.order_state)?if_exists=='7' >
		     <a href="/webapp/refundapp!appRefundGoods.action?order_id=${goodsorder.order_id?if_exists}"><i class="iRed">申请售后</i></a>
		     <a href="/webapp/goods!auditList.action?order_id_s=${goodsorder.order_id?if_exists}" ><i >评价</i></a>
		<#elseif (goodsorder.order_state)?if_exists=='8'>
		     <a href="/webapp/refundapp!appRefundGoods.action?order_id=${goodsorder.order_id?if_exists}"><i class="iRed">申请售后</i></a>
		     <a href="/webapp/goodsorder!webappAllOrder.action" ><i >返回订单</i></a>
		  <#elseif (goodsorder.order_state)?if_exists=='0'>
	               <a href="/webapp/goodsorder!webappAllOrder.action" class="loderDetailbt" ><i >返回订单</i></a>
	      </#if>
	 <br class="clear"/>
  </p>
</div>
<!--订单商品-->
<#if detailList?if_exists && detailList.size() gt 0>
<div class="hasOrder">
	  <!--待收货-->
	  <header class="hoDiv">
	    <table>
	     <#list detailList as detail>
	      <tr>
	        <td><div class="imgDiv"><a href="/webapp/goodsdetail/${(detail.goods_id)?if_exists}.html"><img src="${detail.temporary_goodsimg?if_exists}"></a></div></td>
	        <td>
	           <p><a href="/webapp/goodsdetail/${(detail.goods_id)?if_exists}.html">${detail.temporary_goodsname?if_exists}</a></p>
	           <p>金额：<b>￥${detail.goods_price?if_exists}</b></p>
	           <p>数量：${detail.order_num?if_exists}</p>
	        </td>
	      </tr>
	       </#list>
	    </table>
	  </header>
   </div>
</div>
</#if>

<#if freegoodsList?if_exists && freegoodsList.size() gt 0>
<div class="hasOrder">
	  <!--待收货-->
	  <header class="hoDiv">
	    <table>
	     <#list freegoodsList as freegoods>
	      <tr>
	        <td>
	            <div class="imgDiv">
	                   <#if freegoods.img_path!=''>
				      			<img src="${(freegoods.img_path)?if_exists}" align="absmiddle" class="f_left" width='30' height='30'>
		      		   <#else>
		      				   <img src="${(cfg_nopic)?if_exists}" align="absmiddle" class="f_left" width='30' height='30'>
		      		    </#if>
	            </div>
	        </td>
	        <td>
	           <p>${freegoods.fg_name?if_exists}（赠品）</p>
	           <p>数量：${freegoods.fg_number?if_exists}</p>
	        </td>
	      </tr>
	       </#list>
	    </table>
	  </header>
   </div>
</div>
</#if>

<div class="memberInfor">
  <p>收件人：${(goodsorder.consignee)?if_exists}</p>
  <p>身份证：${(goodsorder.identitycard)?if_exists} </p>
  <p>联系方式：${(goodsorder.mobile)?if_exists}</p>
  <p>地址：${(order_area)?if_exists} ${(goodsorder.buy_address)?if_exists}</p>
</div>

<div class="memberInfor">
  <p>发票信息</p>
   <#if (orderinvoice.invoice_type)?if_exists =='0'>
    <p>发票类型：普通发票</p>	  
    <p>发票抬头： ${(orderinvoice.look_up)?if_exists}</p>	  
     <p>发票内容：  <#if (orderinvoice.p_content)?if_exists>
	            	 ${(orderinvoice.p_content)?if_exists}
	            	 <#else>
	            	  - -
					 </#if>
	 </p>	    
     <#elseif (orderinvoice.invoice_type)?if_exists =='1'>
       <p>发票类型：增值发票</p>	  
       <p>单位名称： <#if (orderinvoice.company_name)?if_exists>
        	  ${(orderinvoice.company_name)?if_exists}
        	  <#else>
        	     -
	          </#if></p>
       <p>注册地址： <#if (orderinvoice.re_address)?if_exists>
        	 ${(orderinvoice.re_address)?if_exists}
        	 <#else>
        	 -
	        </#if></p>
       <p>注册电话：<#if (orderinvoice.re_phone)?if_exists>
        	 ${(orderinvoice.re_phone)?if_exists}
        	 <#else>
        	 -
	        </#if></p>
       <p>开户银行： <#if (orderinvoice.bank_name)?if_exists>
        	 ${(orderinvoice.bank_name)?if_exists}
        	 <#else>
        	 -
	        </#if></p>
       <p>银行账户： <#if (orderinvoice.bank_id)?if_exists>
        	 ${(orderinvoice.bank_id)?if_exists}
        	 <#else>
        	 -
	        </#if></p>
       <p>发票内容： <#if (orderinvoice.z_content)?if_exists>
        	 ${(orderinvoice.z_content)?if_exists}
        	 <#else>
        	 -
	        </#if></p>
       <p>收票人姓名：   <#if (orderinvoice.ticket_name)?if_exists>
        	 ${(orderinvoice.ticket_name)?if_exists}
        	 <#else>
        	 -
	        </#if></p>
       <p>收票人手机：   <#if (orderinvoice.ticket_cell)?if_exists>
        	 ${(orderinvoice.ticket_cell)?if_exists}
        	 <#else>
        	 -
	        </#if></p>
       <p>收票人省份： <#if (orderinvoice.area_attr)?if_exists>
        	 ${(orderinvoice.area_attr)?if_exists}
        	 <#else>
        	 -
	        </#if></p>
       <p>纳税人识别码：  <#if (orderinvoice.identifier)?if_exists>
        	 ${(orderinvoice.identifier)?if_exists}
        	 <#else>
        	  - -
	        </#if></p>
       <p>详细地址：<#if (orderinvoice.address)?if_exists>
        	     ${(orderinvoice.address)?if_exists}
        	 <#else>
        	  - -
	        </#if></p>
          <#else>
             <p>发票类型：不开发票</p>	 
     </#if>   
  
</div>

 <div class="memberInfor">
     <p>支付方式：
     <#list paymentList as payment> 
       <#if payment.pay_id==goodsorder.pay_id> 
           ${(payment.pay_name)?if_exists}
       </#if>
     </#list>    
     </p>
     
     <p>支付时间： 
        <#if goodsorder.pay_time!="" && goodsorder.pay_time!=null>
        <#if (goodsorder.pay_time)?if_exists?length lt 19>
           ${(goodsorder.pay_time)?if_exists}                          
         <#else>
         ${(goodsorder.pay_time)?if_exists[0..18]}
         </#if>
         <#else>
        - -
       </#if>    </p>
 
      <p>支付流水号：<#if goodsorder.pay_trxid!=""&& goodsorder.pay_trxid!=null>${(goodsorder.pay_trxid)?if_exists}<#else> - -</#if></p>
      
      <p>积分支付： ${goodsorder.integral_use?if_exists}</p>
      
      <p>余额支付：￥${goodsorder.balance_use?if_exists}</p>
      
      <p>其他支付：￥
           			 <#if goodsorder.order_state=='1'||goodsorder.order_state=='0'>
           			 0
           			 <#elseif goodsorder.integral_use?if_exists?number gt 0>
           			  0 
           			 <#else>
           			 ${((goodsorder.last_pay))}
           			 </#if></p>
      
      <p>关税：￥${(goodsorder.taxes)?if_exists}</p>
     
     <p>商品金额：￥${(goodsorder.goods_amount)?if_exists}</p>
     <!--<p>返现：￥0.00</p>-->
     <p>运费：
       <#if (goodsorder.ship_free)?if_exists&&(goodsorder.ship_free)?if_exists!='0'>
                   ￥${(goodsorder.ship_free)?if_exists}
                 <#else>(免运费)
		        </#if>
	 </p>
     <p>应付款金额：<b>￥${(goodsorder.tatal_amount)?if_exists}</b></p>
</div>

<#if (goodsorder.order_state)?if_exists=='3'><!--已发货订单，才有物流信息-->
<!--物流跟踪(详细页)-->
<div class="memberInfor">

  <!--物流-->
  <div class="logisticsDiv"  id="show_log">
     <!--今天-->
  </div>
   <div  id="show_log_s"  style="display:none;">
        物流公司：
	    <#list sendmodeList as sendmode>
	      <#if sendmode.smode_id==goodsorder.send_mode>
	          ${(sendmode.smode_name)?if_exists}
	      </#if>
	    </#list>
	    <br/>
	    运单号码：${(goodsorder.send_num)?if_exists}<br/>
       <a href="http://m.kuaidi100.com/index_all.html?type=${goodsorder.smode_name?if_exists}&postid=${goodsorder.send_num?if_exists}&callbackurl=${cfg_mobiledomain}/webapp/orderDetail/${(goodsorder.order_id)?if_exists}.html"  target="_blank" ><i class="pcOrange">请点击查看物流信息<i></a>
  </div>
  
</div>
</#if>

<@s.hidden name="buy_order_cancel" id="refund_buy_reason"/>
<@s.hidden name="goods_order_id" id="order_id" value="${(goodsorder.order_id)?if_exists}"/>
<@s.hidden name="jsontotal" id="jsontotal_id"/> 
 <@s.hidden name="logistics_query" id="logistics_query_id"/>
 <@s.hidden name="kuai_company" id="kuai_company_id"/>
 <@s.hidden name="receipt_flag" id="receipt_flag" value="3"/>
<!--取消订单弹出层-->
<div class="popupDiv" id="cancelOrderId">
</div>
<!--确认收货弹出层-->
<div class="popupDiv" id="confirmOrderId">
</div>

</@s.form>
<!--尾部-->
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">


</body>

<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/mbMember.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/goodsorder.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/payment.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function(){
	    getLOgisticsInfo();
	});
  </script>
</html>
