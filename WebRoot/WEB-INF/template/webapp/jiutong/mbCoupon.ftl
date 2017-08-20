<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}—优惠劵</title>

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
   <a href="javascript:gbackhistory();"  class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>优惠劵<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<#if comsumerList?size gt 0>
<!--订单不为空-->
<div class="hasOrder">
  <!--优惠券-->
 <#list comsumerList as comsumer >
  <header class="hoDiv">
    <table>
      <tr>
        <td>
           <p><b><img src="/malltemplate/jiutong/images/youhuiquanimg.png" />&nbsp;&nbsp;${comsumer.coupon_name?if_exists}</b></p>
           <p>优惠券号码：${comsumer.comsumer_no?if_exists}</p>
           <p>有效期至：${comsumer.start_time?if_exists} 至 ${comsumer.end_time?if_exists}</p>
             <p>使用说明：${(comsumer.content)?if_exists}</p>
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
  <div class="emptyOrder">
   <p><img src="/malltemplate/jiutong/images/eGoods.gif"></p>
   <p> 您目前还没有优惠券</p>
</div>
</#if>
<!--尾部-->
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">
</body>
<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
</html>
