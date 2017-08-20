<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>${seo_title?if_exists}</title>
<meta name="keywords" content="${seo_keyword?if_exists}"/>
<meta name="description" content="${seo_desc?if_exists}"/>
<#include "/WEB-INF/template/bmall/default/jscssinclude.ftl">
<link href="/malltemplate/bmall/css/notice.css" rel="stylesheet" type="text/css"/>
</head>

<body >

<!--头部-->
<#include "/WEB-INF/template/bmall/default/publictop.ftl">

<!--主体-->

<!--内容-->
<div class="helpb">

    <!--位置-->
    <div class="postion"><a href="/">首页</a><span>&nbsp;>&nbsp;</span><a href="/mall-sitenotice-${article.cat_attr?if_exists}.html">${cat_attr_name?if_exists}</a></div>
    
    <!---->
    <div class="notice">
       <h2 class="nh2">${article.title?if_exists}</h2>
       <div class="notCont">
         ${article.content?if_exists}
       </div>
    </div>

</div>

<!--尾部-->
<#include "/a/bmall/mallbottom.html">

<script src="/malltemplate/bmall/js/help.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/claMenu.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/classify.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/jquerySFocus.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/jqueryFloat.js" type="text/javascript"></script>
</body>

</html>
