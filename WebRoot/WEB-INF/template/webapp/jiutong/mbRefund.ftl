<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}退款退货</title>

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
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>退款退货<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<@s.form action="/webapp/refundapp!buylist.action" method="post" id="indexForm">
<#if refundappList?if_exists && refundappList.size() gt 0>
<!--订单不为空-->
<div class="hasOrder">
  <!--取消订单记录-->
  <#list refundappList as refundapp>
  
  <header class="hoDiv">
  
    <table>
     
      <#assign list_img_str=""><!-- 退款图片-->
      <#assign goods_num=0>
      <tr>
        <td>
           <div class="imgDiv">
             <#list detailList as detail>
                 	 <#if ((detail.order_id)?if_exists)==((refundapp.order_id)?if_exists)>
                 	 <#assign img_path="${( detail.temporary_goodsimg)?if_exists}">
	                     <a href="/webapp/goodsdetail/${(detail.goods_id)?if_exists}.html" title="${detail.temporary_goodsname?if_exists}" target="_blank">
	                     	<img src="<#if img_path!=''>${img_path?if_exists}<#else>${(cfg_nopic)?if_exists}</#if>"/>
	                     </a>
                    </#if>
	      		 </#list> 
           </div>
        </td>
        <td>
           <p><span class="tkSpan"><#if (refundapp.is_return)?if_exists==0>退款<#else>退货</#if></span></p>
           <p><#if (refundapp.is_return)?if_exists==0>退款<#else>退货</#if>编号：${(refundapp.return_no)?if_exists}</p>
           <p>提交时间：${(refundapp.info_date)?if_exists}</p>
           <p>退款金额：<b>￥${(refundapp.refund_amount)?if_exists}</b></p>
           <p>共${goods_num?if_exists}件</p>
           <p class="pcOrange">
             <#if (refundapp.refund_state)?if_exists=='0'><b class="blueSpan">等待处理</b></font>
			    	<#elseif (refundapp.refund_state)?if_exists=='1'><b class="greenSpan">退款成功</b></font>
			    	<#elseif (refundapp.refund_state)?if_exists=='2'><b ><#if refundapp.is_return=='0'>退款<#else>退货</#if>失败</b></font>
			    	<#elseif (refundapp.refund_state)?if_exists=='5'><b class="greenSpan">撤销申请</b></font>
			    	<#elseif (refundapp.refund_state)?if_exists=='3'><b class="blueSpan">等待会员发货</b></font>
			    	<#elseif (refundapp.refund_state)?if_exists=='4'><b class="blueSpan">等待卖家确认收货</b></font>
		     </#if>
           </p>
           <p>
             <#if refundapp.is_return=='0'>
                  <a href="/webapp/refundapp!buyfundview.action?refundapp.trade_id=${refundapp.trade_id}" class="mBut">查看详情</a>
             <#elseif refundapp.is_return=='1'>
                  <a href="/webapp/refundapp!buygoodsview.action?refundapp.trade_id=${refundapp.trade_id}" class="mBut">查看详情</a>
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

</@s.form>
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">
</body>
<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
</html>
