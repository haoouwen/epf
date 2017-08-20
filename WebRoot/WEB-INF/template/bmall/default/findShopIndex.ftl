<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>${cfg_webname?if_exists}--门店查询</title>
<script src="/wro/mallpublic.js" type="text/javascript"></script>
<link href="/wro/mallpublic.css" rel="stylesheet" type="text/css"/>
<script src="/malltemplate/bmall/js/findShop.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/claMenu.js" type="text/javascript"></script>
<link href="/malltemplate/bmall/css/findShop.css" rel="stylesheet" type="text/css" />
<script src="/malltemplate/bmall/js/jt_product.js" type="text/javascript"></script>
</head>
<body>
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

<@s.form id="asysuserList" action="/findShopList.html" method="post">
<!--搜索部分-->
<!--内容-->
<div class="postion"><span style="margin-left:-220px;"><a href="/">首页</a>&nbsp;>&nbsp;<a href="/findShopIndex.html">门店查询</a></span></div>

<div class="fShopBack">
  <div class="fShopCont">
     <div class="fsBCont"></div>
     <div class="fsCont">
       <span>门店地区查询:</span>
       <div class="mdDiv">
        <@s.select name="area_number" list="areaList" listValue="area_name" listKey="area_number"  headerKey="" headerValue="--请选择地区--" />
       </div>
       <input type="button" value="搜 索" onclick="storeSearch();" class="shopSearBut">
     </div>
  </div>
</div>

<!--门店-->
<div class="shopIntro" id="shopIntroId">
   <!---->
   <div class="tabDiv">
    <#list storeintroList as slist> 
      <div class="introCont">
         <h2>${slist.sto_name }</h2>
         <p>${(slist.content)?if_exists}</p>
      </div>
      </#list>
   </div>
   <!---->
   <div class="introh2">
     <ul>
        <#list storeintroList as slist> 
         <li><div><img src="<#if (slist.img_path)?if_exists>${(slist.img_path)?if_exists}<#else>${cfg_nopic?if_exists}</#if>" width="275px" height="200px;" /></div><p>${slist.sto_name }</p></li>
       </#list>
     </ul>
     <div class="clear"></div>
   </div>
</div>

</@s.form>
<!--返回顶部-->
<div class="returnTop"></div>

<div class="clear"></div>
<!--尾部-->
<#include "/a/bmall/mallbottom.html">

</body>

</html>
