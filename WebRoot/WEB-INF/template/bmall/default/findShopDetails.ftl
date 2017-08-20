<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>${cfg_webname?if_exists}--门店</title>
<link href="/wro/mallpublic.css" rel="stylesheet" type="text/css"/>
<link href="/malltemplate/bmall/css/findShop.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/include/common/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/malltemplate/bmall/js/jt_public.js" ></script>
</head>

<body onload="init()">

<!--头部--> 
<#include "/a/bmall/malltop.html">
<!--导航-->
<div class="headerList">
    <div class="w1180">
    <!-- -导航 -->
       <#include "/a/bmall/mallnav.html">
      <!-- --分类 -->
       <#include "/a/bmall/mallcat.html">
   </div>
</div>

<!--内容-->
<div class="postion"><a href="/">首页</a>&nbsp;&gt&nbsp;<a href="/findShopIndex.html">门店查询</a>&nbsp;&gt&nbsp;<a href="/findShopList.html">门店列表</a>&nbsp;&gt&nbsp;<#if asysuser.store_name?if_exists>${(asysuser.store_name)?if_exists}</#if></div>


<div class="fShopDetail">
   <table width="100%" cellpadding="0" cellspacing="0">
     <tr valign="top">
       <td width="28%">
         <div><img src="<#if asysuser.store_img?if_exists>${(asysuser.store_img)?if_exists}<#else>${cfg_nopic?if_exists}</#if>" width="300px" height="300px"/></div>
       </td>
       <td  width="62%">
          <p><b><#if asysuser.store_name?if_exists>${(asysuser.store_name)?if_exists}<#else>--</#if></b></p>
         <p>门店编号：<#if asysuser.nike_name?if_exists>${(asysuser.nike_name)?if_exists}<#else>--</#if></p>
         <p>门店地址：<#if asysuser.address?if_exists>${(asysuser.address)?if_exists}<#else>--</#if></p>
         <p>联系电话：<#if asysuser.phone?if_exists>${(asysuser.phone)?if_exists}<#else>--</#if></p>
         <p>营业时间：<#if asysuser.store_opentime?if_exists>${(asysuser.store_opentime)?if_exists}<#else>--</#if></p>
         <p>公交线路：<#if asysuser.bus_line?if_exists>${(asysuser.bus_line)?if_exists}<#else>--</#if></p>
         <p>地铁线路：<#if asysuser.railway_line?if_exists>${(asysuser.railway_line)?if_exists}<#else>--</#if></p>
         <p>停车服务：<#if asysuser.parking_service?if_exists>${(asysuser.parking_service)?if_exists}<#else>--</#if></p>
       </td>
       <td width="10%">
         <span class="mapSpan">查看地图</span>
       <td>
     </tr>
   </table>
</div>
<div class="popupDiv" id="mapId">
 <div class="popupCont" >
	<input  id="address" type="hidden" value="${(asysuser.address)?if_exists}">
	<div style="width:900px;height:570px"id="container"></div>
 </div>
</div> 
<!--服务-->
<div class="fShopServer">
   <h2 class="fsh2">本店为您提供以下服务</h2>
   <div class="fserCont" id="fserContId">
      <div class="fscul">
         <ul>
         <#if (asysuser.store_servce)?if_exists!="" && (asysuser.store_servce)?if_exists!=null>
              <#list storesevrceList as slist> 
                 <#if ((asysuser.store_servce)?if_exists?string?index_of((slist.store_id)?if_exists?string)>-1)>
               		 <li><img src="<#if (slist.store_img)?if_exists>${(slist.store_img)?if_exists}<#else>${cfg_nopic?if_exists}</#if>" width="100px" height="100px" /><p>${(slist.store_name)?if_exists}</p></li>
                 </#if>
             </#list>
         </#if>
         </ul>
      </div>
      <div class="tabDiv">
       <#if (asysuser.store_servce)?if_exists!="" && (asysuser.store_servce)?if_exists!=null>
              <#list storesevrceList as slist> 
                 <#if ((asysuser.store_servce)?if_exists?string?index_of((slist.store_id)?if_exists?string)>-1)>
               		<div class="fssCont">${(slist.content)?if_exists}</div>
                 </#if>
             </#list>
         </#if>
      </div>
   </div>
</div>

<!--返回顶部-->
<div class="returnTop"></div>

<div class="clear"></div>
<!--尾部-->
<#include "/a/bmall/mallbottom.html">
</body>
<script src="/wro/mallpublic.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/jqueryPopupLayer.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/findShop.js" type="text/javascript"></script>
<#include "/WEB-INF/template/bmall/default/mapinfo.ftl">
</html>
