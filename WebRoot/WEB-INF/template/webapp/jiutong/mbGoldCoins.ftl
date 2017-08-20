<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}充值</title>

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
<link href="/malltemplate/jiutong/css/alert.css" rel="stylesheet" type="text/css">
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>

<body>

<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>充值记录<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<#if fundrechargeList?if_exists && fundrechargeList.size() gt 0>

<!--订单不为空-->
<div class="hasOrder">
  <!--充值记录-->
  <#list fundrechargeList as fundrecharge>
  <input type="hidden" name="fundrecharge.trade_id" value="${fundrecharge.trade_id?if_exists}"/>
  <header class="hoDiv">
    <table>
      <tr>
        <td>
           <p>时间：${(fundrecharge.pay_date)?if_exists}</p>
           <p>充值订单号：${fundrecharge.order_id?if_exists}</p>
           <p>充值平台：${fundrecharge.payplat?if_exists}</p>
           <p>充值金额：<b>${fundrecharge.fund_num?if_exists}</b></p>
           <p>
             <#if fundrecharge.order_state?if_exists=='0'>
				    <font color='blue'>未审核</font>
				    </#if>
				    <#if fundrecharge.order_state?if_exists=='1'>
				    <font color='red'>已审核</font>
				    </#if>
				    <#if fundrecharge.order_state?if_exists=='2'>
				    <font color='green'>作废</font>
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

  <div class="clearEmpty"><a href="javascript:void(0);" onclick='webappdelInfo("/webapp/memberuser!webappdelete.action","fundrecharge.trade_id");'>全部删除</a></div>
  
</div>

<#else>

<!--订单为空-->
<#include "/WEB-INF/template/webapp/jiutong/mbemptyorder.ftl">

</#if>

<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">

<@s.form  action="" id="commonForm">
	<@s.hidden name="" id="commonId" value=""/>	
	<@s.hidden name="" id="commonText" value=""/>	
</@s.form>
</body>
<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
</html>
