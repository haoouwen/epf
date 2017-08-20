<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>地区-${cfg_webname?if_exists}</title>
<#include "/WEB-INF/template/bmall/default/jscssinclude.ftl">
<link href="/malltemplate/bmall/css/groupBuy.css" rel="stylesheet"  type="text/css"/>
<link href="/malltemplate/bmall/css/groupBuyList.css" rel="stylesheet"  type="text/css"/>
<script src="/malltemplate/bmall/js/floatDiv.js" type="text/javascript"></script>
</head>

<body>
<@s.form id="groupgoods" action="/mall/groupbuy!groupBuyIndex.action" method="post">
<@s.hidden name="en_name"/>
<@s.hidden id="searchtype" name="searchtype"/>
<@s.hidden id="searchname" name="searchname"/>
<@s.hidden id="cssstatus" name="cssstatus"/>
<@s.hidden id="cat_attr" name="cat_attr"/>
<@s.hidden id="sele_area_id" name="sele_area_id"/>

<!--头部-->
<#include "/a/bmall/grouptop.html">
<!--顶部分类-->
<!--导航-->

  
<div class="clear"></div>
<div class="w1180">
  <!--团购分类-->
 <#assign x=1>
<#if (areacharList.size() < 1) > <P class="cont_area">暂无地区</P> </#if>
<#list areacharList as areachar>
    <P class="cont_area">
    <span class="letter">
    ${areachar.en_name?capitalize}.
    </span>
    <span class="area1">
    <#list areaList as area>
      <#if area.en_name[0..0] == areachar.en_name?if_exists>
      <a href="/mall-grouparea-${area.en_name?if_exists}.html">${area.area_name?if_exists}</a>
      </#if>
    </#list>
     </span></P>
     <br class="clear"/>
</#list>


</div>
<!--团购列表-->

<div class="clear"></div>
<div class="w1180">
  <!--这边放翻页控件-->
</div>
<!--尾部-->
<div class="clear"></div>
<#include "a/bmall/mallbottom.html">

<div class="returnTop" id="returnTopId"><a href="#areturntop"></a></div>
</body>
</@s.form>
</html>
