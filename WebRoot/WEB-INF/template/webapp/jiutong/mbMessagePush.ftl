<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${cfg_webname?if_exists}--消息详情</title>
<meta name="author" content="久通宏达科贸（北京）有限公司">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">           
<meta http-equiv="Pragma" content="no-cache">
<link href="/malltemplate/jiutong/css/mbPublic.css" rel="stylesheet" type="text/css"></link>
<link href="/malltemplate/jiutong/css/mbSearShop.css" rel="stylesheet" type="text/css"></link>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="shopDetail">
  <div class="sdTopDiv">
     <p> 主题：<#if (messagepush.msgpush_name)?if_exists>${(messagepush.msgpush_name)?if_exists}<#else>--</#if></p>
     <p>时间：<#if (messagepush.in_date)?if_exists>${(messagepush.in_date)?if_exists}<#else>--</#if></p>
     <p><#if (messagepush.content)?if_exists>${(messagepush.content)?if_exists}<#else>--</#if></p>
  </div>
</div>


</body>
</html>
