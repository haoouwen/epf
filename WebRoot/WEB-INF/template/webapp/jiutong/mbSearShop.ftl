<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}门店查询</title>

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
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>门店查询<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<@s.form id="searchShop" action="/webapp/findShopList.html" method="post">

<#if asysuserList?if_exists && asysuserList.size() gt 0>


<!--门店列表页-->
<header class="shopList">
   <ul>
     <#list asysuserList as asysuser> 
       <li>
          <p><img src="/malltemplate/jiutong/images/wz.png"><a href="/webapp/findShopDetail_${(asysuser.user_id)?if_exists}.html">
          <#if asysuser.store_name?if_exists>${(asysuser.store_name)?if_exists}<#else>--</#if></a>
          </p>
           <p>
           <#if (asysuser.store_servce)?if_exists!="" && (asysuser.store_servce)?if_exists!=null>
	              <#list storesevrceList as slist> 
	                 <#if ((asysuser.store_servce)?if_exists?string?index_of((slist.store_id)?if_exists?string)>-1)>
	               			<span style='background:${(slist.store_color)?if_exists};' class="findlistservice"  >${(slist.store_name)?if_exists}</span>
	                 </#if>
	             </#list>
             </#if>
          </p>
          <p>门店编号：<#if asysuser.nike_name?if_exists>${(asysuser.nike_name)?if_exists}<#else>--</#if></p>
	      <p>门店地址：<#if asysuser.address?if_exists>${(asysuser.address)?if_exists}<#else>--</#if></p>
	      <p>联系电话：<#if asysuser.phone?if_exists>${(asysuser.phone)?if_exists}<#else>--</#if></p>
	      <p>营业时间：<#if asysuser.store_opentime?if_exists>${(asysuser.store_opentime)?if_exists}<#else>--</#if></p>
        </li>
      </#list>
   </ul>
</header>

<#else>
<!--搜索为空-->
<#include "/WEB-INF/template/webapp/jiutong/mbempty.ftl">
</#if>

<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">

<!--筛选-->
<div class="sxDiv"><span>门店搜索</span></div>

<!--筛选-->
<div class="filterDiv">
 
    <div class="filh2">门店搜索</div> 
    <!---->
    <div class="filsDiv">
      <p class="ph2">地址选择</p>
      <p class="selelctP">
         <select onchange="storeSearch();" id="asysuserList_area_number" name="area_number">
			    <option value="" <#if area_number==''>selected="selected"</#if>>--请选择地区--</option>
			    <option value="000" <#if area_number=='000'>selected="selected"</#if>>全国直营店</option>
			    <#list areaList as alist>
			    <option value="${(alist.area_number)?if_exists}" <#if area_number==alist.area_number>selected="selected"</#if>>${(alist.area_name)?if_exists}</option>
			    </#list>
			</select>
      </p>
    </div>
    
    <div class="filrBut">
       <span onclick="searchShopList();"><i>确定</i></span>
    </div>
</div>

<div class="filClose"><img src="/malltemplate/jiutong/images/filClose.gif"></div>

</@s.form>
</body>

<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/mbBrand.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/mbShop.js" type="text/javascript"></script>
 <script src="/malltemplate/jiutong/js/payment.js" type="text/javascript"></script>
</html>
