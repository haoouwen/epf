<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}—取消订单记录</title>

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
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>取消订单记录<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<#if goodsorderList?if_exists && goodsorderList.size() gt 0>
<!--订单不为空-->
<div class="hasOrder">
  <!--取消订单记录-->
  <#list goodsorderList as goodsorder>
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
	           		<!-- 普通订单,积分订单、秒杀订单、套餐订单、团购订单、试用订单--> 
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
           <p>提交时间：${goodsorder.order_time?if_exists}</p>
           <p>订单支付金额：<b>￥${(goodsorder.tatal_amount)?if_exists}</b></p>
           <p>共${num?if_exists}件</p>
           <p class="pcOrange">
                 <#if goodsorder.cancel_state?if_exists=="1">  
                                  已完成
                 <#elseif goodsorder.cancel_state?if_exists=="0">
                    退款处理
                 </#if> 
           </p>
           <p><a href="/webapp/goodsorder!buyCancelOrderView.action?goods_order_id=${goodsorder.order_id?if_exists}" class="mBut">查看详情</a></p>
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
<!--尾部-->
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">


</body>
<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
</html>
