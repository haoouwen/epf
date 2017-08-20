<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>${cfg_webname?if_exists}-${cat_name_?if_exists}</title>
<#include "/WEB-INF/template/bmall/default/jscssinclude.ftl">
<link href="/malltemplate/bmall/css/notice.css" rel="stylesheet" type="text/css"/>
<script src="/wro/mallpublic.js" type="text/javascript"></script>
</head>

<body >

<!--头部-->
<#include "/WEB-INF/template/bmall/default/publictop.ftl">
<!--主体-->

<!--内容-->
<div class="helpb">

    <!--位置-->
    <div class="postion"><a href="/">首页</a>&nbsp;>&nbsp;<a href="#">${cat_name_?if_exists}</a></div>
    
    <!---->
    <div class="notice">
       <h2 class="nh2">${cat_name_?if_exists}</h2>
       <div class="nList">
         <ul>
           <#list articleList as notice>
           <li><a href="/mall-noticedetail-${notice.article_id?if_exists}.html">${notice.title?if_exists}</a><span>${notice.in_date?if_exists}</span></li>
           </#list>
         </ul>
       </div>
	  
	  <#if articleList!=null&&articleList.size() gt 0>
	  <div class="listbottom">
	  ${pageBar?if_exists}
	   </div>     
      </#if> 
    </div>
     
</div>

<!--尾部-->
<#include "/a/bmall/mallbottom.html">

<script src="/malltemplate/bmall/js/help.js" type="text/javascript"></script>
</body>

</html>
