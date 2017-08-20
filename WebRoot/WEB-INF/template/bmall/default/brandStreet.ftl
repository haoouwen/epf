<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>${seo_title?if_exists}</title>
<meta name="keywords" content="${seo_keyword?if_exists}"/>
<meta name="description" content="${seo_desc?if_exists}"/>
<#include "/WEB-INF/template/bmall/default/jscssinclude.ftl">
<script src="/malltemplate/bmall/js/claMenu.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/findShop.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/collect.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="/include/admin/js/jquery.lazyload.js"></script>
<link href="/malltemplate/bmall/css/findShop.css" rel="stylesheet" type="text/css">
<script type="text/javascript" >
  	 $(document).ready(function(){
  		  $(".lazyload").lazyload({
	  		  placeholder : "${cfg_lazyPic?if_exists}", 
	  		  threshold : 0, 
	  		  effect : "show"
  		  });
  	 })
</script>
</head>

<body>
<input type="hidden" id="en_index" value="${goodsBrandMap.en_index?if_exists}"/>
<input type="hidden" id="cat_id" value="${goodsBrandMap.cat_id?if_exists}"/>
<!--头部-->
<#include "/a/bmall/malltop.html">
<!--导航-->
<div class="navBack">

  <div class="navDiv">
  
  	<#include "/a/bmall/mallcat.html">
  	
    <#include "/a/bmall/mallnav.html">
    
  </div>
</div>
<!--内容-->
<div class="postion"><a href="#">首页</a>&nbsp;>&nbsp;<a href="#">品牌库</a></div>
<!--品牌头部-->
<div class="brandTop">
   <!---->
   <div class="zmTop">
      <ul>
        <a href="/mall-brandlist-All.html" class="searall"><li class="zmli selli"><font color="white">全部品牌</font></li></a>
        <#assign aToz = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"]> 
        <#list aToz as az>
         	<a href="/mall-brandlist-${az}.html"><li>${az}</li></a>
        </#list>
      </ul>
   </div>
   <!--
     <#if (goodsBrandMap.categoryList)?if_exists?size gt 0>
	   <div class="selcont">
	   
		      <div class="lscont">分类</div>
			      <div class="rscont">
				        <div class="rsTextDiv">
				          <ul>
				             <#list goodsBrandMap.categoryList as category>
				             <li><a id="${(category.cat_id)?if_exists}" href="/mall-brandlist-${en_index?if_exists}.html?cat_id=${(category.cat_id)?if_exists}">${(category.cat_name)?if_exists}</a></li>             
				             </#list>
				           </ul>
				         </div>
			         <div class="clear"></div>
			      </div>
		      <div class="clear"></div>
	    </div>
	   </#if>
	   -->
</div>
<!--品牌列表-->
<div class="brandBot">
    <!---->
    <!--字母排序-->
    <#if en_index?if_exists!="All">
		<#if goodsBrandMap.goodsbrandList?if_exists && (goodsBrandMap.goodsbrandList?size > 0)>     
		<#list aToz as az>
		<#if az==en_index>
		<div class="brandList">
		   <h2><span>${az}</span></h2>
		   <div class="rsPicDiv">
		      <ul>
		         <#list goodsBrandMap.goodsbrandList as brands>
		           <li><a href="/mall-searchbrand-${(brands.brand_id)?if_exists}.html" title="${(brands.brand_name)?if_exists}"><img src="${(brands.logo)?if_exists}" width="120" height="50"></a><a href="javascript:void(0);" onclick="brandColl('/mall-searchbrand-${(brands.brand_id)?if_exists}.html','${(brands.brand_id)?if_exists}');" class="ppsc"><img src="/malltemplate/bmall/images/ppsc.png"></a></li>
		         </#list> 
		     </ul>
		      <div class="clear"></div>
		    </div>
		</div>
		</#if>
		</#list>
		  <#else>
	     <div class="noFind"><img src="/malltemplate/bmall/images/noFind.jpg"><b>您搜索的品牌未能找到!</b></div>
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
				    <div class="brandList">
				       <h2><span>${az}</span></h2>
				       <div class="rsPicDiv">
				          <ul>
				         	<#list goodsBrandMap.goodsbrandList as brands>
				         	<#if az == (brands.en_index)?if_exists?capitalize>
				            <li><a href="/mall-searchbrand-${(brands.brand_id)?if_exists}.html" title="${(brands.brand_name)?if_exists}"><img src="${(brands.logo)?if_exists}" width="120" height="50"></a><a  href="javascript:void(0);" onclick="brandColl('/mall-searchbrand-${(brands.brand_id)?if_exists}.html','${(brands.brand_id)?if_exists}');"  class="ppsc"><img src="/malltemplate/bmall/images/ppsc.png"></a></li>
				            </#if>
				            </#list>
				          </ul>
				          <div class="clear"></div>
				        </div>
				    </div>
				    </#if> 
				    </#list>
		      <#else>
	     			<div class="noFind"><img src="/malltemplate/bmall/images/noFind.jpg"><b>您搜索的品牌未能找到!</b></div>
		       </#if>
    </#if>
</div>
<!--尾部-->
<#include "/a/bmall/mallbottom.html">
<@s.hidden name="loc" id="refloc" />
</body>

</html>
