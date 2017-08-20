<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}品牌</title>

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

<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>热门品牌<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">

 <#assign aToz = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"]> 
<div class="brandList">
   <ul>
   
     <#if en_index?if_exists!="All">
		<#if goodsBrandMap.goodsbrandList?if_exists && (goodsBrandMap.goodsbrandList?size > 0)>     
		<#list aToz as az>
		<#if az==en_index>
		  <li>
	       <h3>${az}</h3>
	       <p class="pBrand">
		        <#list goodsBrandMap.goodsbrandList as brands>
		        		<a href="/webapp/brandslist/detail_${(brands.brand_id)?if_exists}.html"><img src="${(brands.logo)?if_exists}"  width="140px"  height="70px"  /></a>
		         </#list> 
	       </p>
	     </li>
		</#if>
		</#list>
		  <#else>
	    <!--搜索为空-->
			<#include "/WEB-INF/template/webapp/jiutong/mbempty.ftl">
	 </#if>
	<#else>
		    <!---->
	    <#if aToz?if_exists && (aToz?size > 0)>     
	    <#list aToz as az>
	    <#assign n = 0> 
	    <#list goodsBrandMap.goodsbrandList as brands>
	     <#if az == (brands.en_index)?if_exists?capitalize>
	       <#assign n = n + 1>
	      </#if> 
	     </#list>
	    <#if n != 0> 
		    <li>
		       <h3>${az}</h3>
		       <p class="pBrand">
			         <#list goodsBrandMap.goodsbrandList as brands>
			                <#if az == (brands.en_index)?if_exists?capitalize>
			        		 <a href="/webapp/brandslist/detail_${(brands.brand_id)?if_exists}.html"><img src="${(brands.logo)?if_exists}"   width="140px"  height="70px"  /></a>
			        		 </#if>
			         </#list> 
		       </p>
		     </li>
	    </#if> 
	    </#list>
	      <#else>
     			<!--搜索为空-->
				<div class="empty">
				   <p><img src="/malltemplate/jiutong/images/eGoods.gif"></p>
				   <p>您搜索的品牌不存在<br/>可能品牌已下架</p>
				</div>
	       </#if>
     </#if>
	     
   </ul>
</div>

<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">


<!--筛选-->
<div class="sxDiv"><span>品牌筛选</span></div>

<!--筛选-->
<div class="filterDiv">
 
    <div class="filh2">品牌筛选</div> 
    <!---->
    <div class="filsDiv">
      <p class="ph2">字母</p>
      <p class="pfil">
        <a href="/webapp/brandslist/ALL.html">全部</a>
        <#list aToz as az>
         	<a href="/webapp/brandslist/${az}.html">${az}</a>
        </#list>
         </p>
    </div>
    <div class="filrBut">
     <span><i>确定</i></span>
    </div>
    
</div>
<div class="filClose"><img src="/malltemplate/jiutong/images/filClose.gif"></div>
</body>
<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/mbBrand.js" type="text/javascript"></script>
</html>
