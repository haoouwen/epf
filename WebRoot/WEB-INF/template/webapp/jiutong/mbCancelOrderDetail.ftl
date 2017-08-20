<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}—取消订单详情</title>

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
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>取消订单详情<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<div class="orderDetailTop">

  <p><b>
        <#if cancelorder.order_state?if_exists=="0">   
           退款处理
        <#elseif cancelorder.order_state?if_exists=="1">
           完成        
        </#if>  
   </b></p>
  <p>订单编号：${goodsorder.order_id?if_exists}</p>
  <p>订单支付金额：<b>￥${goodsorder.tatal_amount?if_exists}</b></p>
  <p>订单日期：${goodsorder.order_time?if_exists}</p>
  <p>取消原因：${cancelorder.buy_reason?if_exists}</p>
  
</div>
<!--订单商品-->
<div class="hasOrder">
  <!---->
  <#if detailList?if_exists && detailList.size() gt 0>
	<#list detailList as detail>
	  <!--待收货-->
	  <header class="hoDiv">
	    <table>
	      <tr>
	        <td><div class="imgDiv"><a href="/webapp/goodsdetail/${(detail.goods_id)?if_exists}.html"><img src="${detail.temporary_goodsimg?if_exists}"></a></div></td>
	        <td>
	           <p><a href="/webapp/goodsdetail/${(detail.goods_id)?if_exists}.html">${detail.temporary_goodsname?if_exists}</a></p>
	           <p>金额：<b>￥${detail.goods_price?if_exists}</b></p>
	           <p>数量：${detail.order_num?if_exists}</p>
	        </td>
	      </tr>
	    </table>
	  </header>
    </#list>
  </#if>
  
</div>

<div class="memberInfor">
  <p>联系人：${goodsorder.consignee?if_exists}</p>
  <p>联系方式：${goodsorder.mobile?if_exists}</p>
  <p>地址：${(order_area)?if_exists} ${(goodsorder.buy_address)?if_exists}</p>
 
</div>
<!--尾部-->
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">
</body>
<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
</html>
