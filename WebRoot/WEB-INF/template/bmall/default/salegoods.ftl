<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${seo_title?if_exists}</title>
<meta name="keywords" content="${seo_keyword?if_exists}" />
<meta name="description" content="${seo_desc?if_exists}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<#include "/WEB-INF/template/bmall/default/jscssinclude.ftl">
<script src="/malltemplate/bmall/js/jqueryFloat.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/jqueryTab.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/product.js" type="text/javascript"></script>
<link href="/malltemplate/bmall/css/product.css" rel="stylesheet" type="text/css" />
</head>

<body>
<@s.form id="goodsList" action="/Search.html" method="post">
<@s.hidden name="catEn_name"/>
<@s.hidden name="sort" id="sort"/>
<@s.hidden name="specstr_s" id="specstr"/>
<@s.hidden name="brand_id_s" id="brand_id"/>
<@s.hidden name="area_attr_s"/>
<@s.hidden name="goods_cat_attr_s"/>
<!--头部--> 
<#include "/a/bmall/malltop.html">
<!--分类，导航，购物车-->
<div class="twNavBack">
   <div class="twNavMain">
      <!-- --分类 -->
      <#include "/a/bmall/mallcat.html">
     <!-- -导航 -->
	  <#include "/a/bmall/mallnav.html">	
   </div>
</div>
<div class="clear"></div>
<!--内容-->
<div class="postion">
	<a href="/">首页</a>> <span>	搜索</span>
</div>
<!--大图列表-->
<div class="proList">
  <#if salegoodsList?if_exists && (salegoodsList?size > 0)>
    <ul class="bProShow">
     <#list salegoodsList as goods>
        <li>
          <div><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html"><img src="${(goods.list_img)?if_exists}"></a></div>
          <p><span>￥<b><#if (goods.sale_price)?if_exists!="">
	          			${goods.sale_price}
	          		</#if></b></span><#if (goods.market_price)?if_exists!="">
	          			￥${goods.market_price}
	          		</#if></p>
          <p><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html">${goods.goods_name?if_exists}</a></p>
        </li>
     </#list>
    </ul>
  <#else>
    	${search_null_page}
    </#if>
    <br class="clear"/>    
</div>
<!--翻页控件-->
 <#if salegoodsList?if_exists && (salegoodsList?size > 0)> ${webPageBar?if_exists} </#if>

<div class="clear"></div>
<!--尾部-->
<#include "/a/bmall/mallbottom.html">
<@s.hidden name="key_word" id="serach_word"/>
<@s.hidden name="en_word" id="serach_enword"/>
<!--<script src="/malltemplate/bmall/js/listMainjs.js" type="text/javascript"></script>-->
<!--<script src="/malltemplate/bmall/js/area.js" type="text/javascript"></script>-->
<script src="/malltemplate/bmall/js/goodsList.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/claMenu.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/classify.js" type="text/javascript"></script>
</body>
</@s.form>
</html>
