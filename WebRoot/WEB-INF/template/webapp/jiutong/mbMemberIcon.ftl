<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}修改头像</title>

<meta name="author" content="久通宏达科贸（北京）有限公司">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">           
<meta http-equiv="Pragma" content="no-cache">
<link href="/malltemplate/jiutong/css/mbPublic.css" rel="stylesheet" type="text/css"></link>
<link href="/malltemplate/jiutong/css/mbRegister.css" rel="stylesheet" type="text/css"></link>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>

<body>

<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>修改头像<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<@s.form id="uploadIconForm" action="/webapp/memberuser!uploadIcon.action" method="post">
<@s.hidden name="member_icon" id="member_icon"/>
<div class="forgetPswDiv">
   <p>会员：${(memberuser.user_name)?if_exists}</p>
   <p>
         头像上传：
        <div id="dropbox"></div> 
        <input id="uploadifyfile" name="uploadifyfile" type="file" multiple>
   </p>
  
   <p><a href="javascript:void(0);" onclick="submitIcon();" class="forgetBut">保存</a></p>
</div>
</@s.form>
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">

</body>
<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
<script type="text/javascript" src="/include/components/uploadify/jquery.html5uploader.min.js"></script> 
<script src="/malltemplate/jiutong/js/uploadImg.js" type="text/javascript"></script>
</html>
