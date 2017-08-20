<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}我的换货</title>

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
<link href="/malltemplate/jiutong/css/refund.css" rel="stylesheet" type="text/css"></link>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="top">
    <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>我的换货<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<@s.form action="/webapp/exchange!buylist.action" method="post" id="indexForm">
<#if exchangeList?if_exists && exchangeList.size() gt 0>
<!--订单不为空-->
<div class="hasOrder">

  
 <#list exchangeList as exchange>
 <!--定于撤销申请需要的字段-->
 <!--定于撤销申请需要的字段-->
    <#assign num=0>
    <#assign list_img_str=""><!-- 换货图片-->
    <#assign ret_number_str=""><!-- 换货编号-->
    <#assign ret_liyou_str=""><!-- 换货理由-->
    <#assign ret_desc_str=""><!-- 换货描述-->
     <!--获取撤销申请需要的值-->
    <#assign ret_number_str=exchange.return_no><!-- 换货编号-->
    <#list commparaList as com>
        <#if com.para_value?if_exists==exchange.refund_type?if_exists>
          <#assign ret_liyou_str="${com.para_key?if_exists}"><!-- 换货理由 -->
        </#if>
    </#list>
    <#assign ret_desc_str=exchange.buy_reason> <!--换货描述-->
     <#list detailList as detail>
     	 <#if ((detail.order_id)?if_exists)==((exchange.order_id)?if_exists)>
     	 <!-- 主要用于撤销申请提示框的数据 -->
     	 <#assign num=num+1>   
        </#if>
	 </#list> 
  <header class="hoDiv">
    <table>
      <tr>
        <td><div class="imgDiv">
        <#assign numtotal=0> 
         <#list detailList as detail>
         	 <#if ((detail.order_id)?if_exists)==((exchange.order_id)?if_exists)>
         	 <!-- 主要用于撤销申请提示框的数据 -->
         	 <#assign numtotal=numtotal+1>   
         	            <#assign img_path="${( detail.temporary_goodsimg)?if_exists}">
	                     <a href="/webapp/goodsdetail/${(detail.goods_id)?if_exists}.html" title="${detail.temporary_goodsname?if_exists}" target="_blank">
	                     	<img src="<#if img_path!=''>${img_path?if_exists}<#else>${(cfg_nopic)?if_exists}</#if>"/>
	                     </a>
	            </#if>
  		 </#list> 
        </div>
        </td>
        <td>
           <p><span class="tkSpan">换货</span></p>
           <p>换货编号：<span class="pcOrange">${(exchange.return_no)?if_exists}</span></p>
           <p>订单号：${(exchange.order_id)?if_exists}</p>
           <p>提交时间：${(exchange.info_date)?if_exists}</p>
           <p>共<span class="pcOrange">${numtotal}</span>件</p>
           <p class="pcOrange">
           <#if (exchange.refund_state)?if_exists=='0'>等待处理
		    	<#elseif (exchange.refund_state)?if_exists=='1'>换货成功
		    	<#elseif (exchange.refund_state)?if_exists=='2'>换货失败
		    	<#elseif (exchange.refund_state)?if_exists=='5'>撤销申请
		    	<#elseif (exchange.refund_state)?if_exists=='3'>等待会员发货
		    	<#elseif (exchange.refund_state)?if_exists=='4'>等待商家确认收货
		    	<#elseif (exchange.refund_state)?if_exists=='6'>等待商家发货
		    	<#elseif (exchange.refund_state)?if_exists=='7'>等待会员确认收货
		    	<#elseif (exchange.refund_state)?if_exists=='8'>会员拒绝收货
		    	<#elseif (exchange.refund_state)?if_exists=='9'>换货失败
	    		<#elseif (exchange.refund_state)?if_exists=='a'>换货成功
	    		<#elseif (exchange.refund_state)?if_exists=='b'>换货失败
	    	</#if>
           </p>
            <p>
              <a href="/webapp/exchange!buygoodsview.action?exchange.trade_id=${exchange.trade_id}" class="mBut">查看详情</a>
              <#if (exchange.refund_state)?if_exists=='0'>
              <a  class="mBut"  href="###" onclick="cancelOrder('${exchange.trade_id?if_exists}','${ret_number_str?if_exists}','${list_img_str?if_exists}','${ret_liyou_str?if_exists}','${ret_desc_str?if_exists}')" >撤销申请</a>
              </#if>
            </p>
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
<@s.hidden name="cancel_trade_id" id="cancel_desr_id"/>   
<@s.hidden name="order_id" id="cancel_trade_id"/>
</@s.form>
<!--尾部-->
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">
<div class="popupDiv" id="cancelOrderId">
</div>
</body>
<script src="/wro/webapp_common.js" type="text/javascript"></script>
<script type="text/javascript" src="/malltemplate/jiutong/js/exchange.js"></script>
</html>
