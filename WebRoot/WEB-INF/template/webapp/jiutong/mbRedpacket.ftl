<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}领取红包</title>

<meta name="author" content="久通宏达科贸（北京）有限公司">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">           
<meta http-equiv="Pragma" content="no-cache">
<link href="/malltemplate/jiutong/css/mbPublic.css" rel="stylesheet" type="text/css"></link>
<link href="/malltemplate/jiutong/css/youhui.css" rel="stylesheet" type="text/css"></link>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>领取红包<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<@s.form action="" method="post" id="indexForm">

<!--领取红包-->
  <div class="lqhb">
    <h2></h2>
    <ul>
      <#list redpacketList as redpacket>
        <li><div><b>${redpacket.money?if_exists}</b>元</div><p>
        <a href="javascript:void(0);" onclick="gotoCou_Red('/webapp/redpacket_${redpacket.red_id?if_exists}.html?platformType=0');">
        <img src="/malltemplate/jiutong/images/djckBut.png" /></a></p></li>
      </#list> 
      <br class="clear"/>
    </ul>
  </div>

</@s.form>
<#include "/a/webapp/mbCommon.html">

</body>

<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/coupon.js" type="text/javascript"></script>
<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
</html>
