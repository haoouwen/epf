<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}—消息中心</title>

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
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>消息中心<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<#if receiveboxList?size gt 0>
<!--订单不为空-->
<div class="hasOrder">
  <!--优惠券-->
 <#list receiveboxList as receivebox >
  <div class="hoDiv">
    <table>
      <tr>
        <td>
           <p><#if receivebox.is_read=='0'> 
           			<img src="/include/bmember/images/ydyj.gif">
				 <#else>
					<img src="/include/bmember/images/wdyj.gif">
				  </#if>&nbsp;&nbsp;主题：<a href="/webapp/receivebox!view.action?receivebox_id=${receivebox.receive_id?if_exists}" >${receivebox.title?if_exists}</a>
			</p>
            <p>时间：${(receivebox.in_date)?if_exists} </p>
        </td>
      </tr>
    </table>
  </div>
  
  </#list>
  
  <div class="hoDiv page" style="text-align:center;">
  ${pageBar?if_exists} 
  </div>
 
</div>

<#else>
<!--订单为空-->
  <div class="emptyOrder">
   <p><img src="/malltemplate/jiutong/images/eGoods.gif"></p>
   <p>  您目前还没有消息</p>
</div>
</#if>
<!--尾部-->
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">


</body>
<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
</html>
