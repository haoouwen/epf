<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>${seo_title?if_exists}</title>
<meta name="keywords" content="${seo_keyword?if_exists}"/>
<meta name="description" content="${seo_desc?if_exists}"/>
<#include "/WEB-INF/template/bmall/default/jscssinclude.ftl">
<link href="/malltemplate/bmall/css/help.css" rel="stylesheet" type="text/css"/>
</head>

<body  >

<!--头部-->
<#include "/a/bmall/malltop.html">
<!--位置-->
<div class="posback">
  <div class="postion" >
      <a href="/">首页</a><span>&nbsp;>&nbsp;</span><a href="/mall-sitenotice-2624811457.html" target="_self">网站公告</a>
  </div>
</div>
<!--主体-->
<div class="w1000">
    <!--左边帮助中心菜单-->
    <#include "/WEB-INF/template/bmall/default/leftarticle.ftl">
    <!--帮助中心内容区域-->
	   <div class="w740">
       <@s.form action="/mall/article!sitenotice.action"  method="post">
		   <div class="articleList" >
				<h2> ${article_nav?if_exists}<!--${cat_name_?if_exists}--></h2>
			    <ul class="articleul" >
				    <#list articleList as article>
				      <li>
				      		<a href="/mall-articledetail-${article.article_id?if_exists}.html">${article.title?if_exists}</a>
				      		<span>${article.in_date?if_exists}</span>
				      </li>
				      
				    </#list>
			    </ul>
		   <!--翻页-->
		   <div class="clear"></div>
		   
		  </div>
		    <div class="listbottom">
		      ${pageBar?if_exists}
		   </div>
		   </div>
		   <@s.hidden  name="page_cat_attr" value="${cat_attr_name}"/>
	  </@s.form>
    </div>
    <br class="clear"/>
	</div>


<!--尾部-->
<#include "/a/bmall/mallbottom.html">
</body>
</html>
<script src="/malltemplate/bmall/js/help.js" type="text/javascript"></script>
