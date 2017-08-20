<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>${seo_title?if_exists}</title>
<meta name="keywords" content="${seo_keyword?if_exists}"/>
<meta name="description" content="${seo_desc?if_exists}"/>
<#include "/WEB-INF/template/bmall/default/jscssinclude.ftl">
<link href="/malltemplate/bmall/css/help.css" rel="stylesheet" type="text/css"/>
</head>
<body >
<#include "/WEB-INF/template/bmall/default/publictop.ftl">
<!--主体-->
<div class="helpb">

	<!--位置-->
	 <div class="postion">
	  <a href="/">首页</a><span>&nbsp;>&nbsp;</span> <#if article.cat_attr !='2624811457'>${articleMap.article_nav?if_exists}<#else><a href="/mall-sitenotice-2624811457.html" target="_self">网站公告</a></#if>
	 </div>

	<div class="w1000">
		<!--左边帮助中心菜单-->
		<#include "/WEB-INF/template/bmall/default/leftarticle.ftl">
		<!--帮助中心内容区域-->
		<div class="w740">
		   <h2 class="wh2">${article.title?if_exists}</h2>
		   <div class="helpCont">
		 <p>${article.content?if_exists}</p>
		   </div>
		</div>
		<br class="clear"/>
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
