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
<div class="postion"><span style="margin-left:-220px;"><a href="/">首页</a>&nbsp;&gt&nbsp;<a href="/findShopIndex.html">门店查询</a>&nbsp;&gt&nbsp;<a href="/findShopList.html">门店列表</a></span></div>


<div class="fShopBack">
  <div class="fShopCont">
     <div class="fsBCont"></div>
     <div class="fsCont">
       <span>门店地区选择:</span>
       <div class="mdDiv">
           
            <select onchange="storeSearch();" id="asysuserList_area_number" name="area_number">
			    <option value="" <#if area_number==''>selected="selected"</#if>>--请选择地区--</option>
			    <option value="000" <#if area_number=='000'>selected="selected"</#if>>全国直营店</option>
			    <#list areaList as alist>
			    <option value="${(alist.area_number)?if_exists}" <#if area_number==alist.area_number>selected="selected"</#if>>${(alist.area_name)?if_exists}</option>
			    </#list>
			</select>
           
           
           
           
       </div>
     </div>
  </div>
</div>


<div class="tjShopSear">
  <div class="tjh2"><span>条件筛选</span></div>
  <div class="shopSear">
    <p>其他筛选：
     <#list storesevrceList as slist> 
       <span><input type="checkbox" 
        <#if ((storesevice_s)?if_exists?string?index_of((slist.store_id)?if_exists?string)>-1)>
         checked="true" 
          </#if>
           name="storesevice_s" value="${slist.store_id }" onchange="storeSearch();"  onpropertychange="storeSearch();"  > ${slist.store_name }</span>
      </#list>
    </p>
   </div>
</div>


<#if asysuserList?if_exists && (asysuserList?size > 0)>
 <div class="fShopList">
  <ul>
   <#list asysuserList as asysuser> 
	     <li>
      <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
          <td width="90%">
            <p><a href="/findShopDetails_${(asysuser.user_id)?if_exists}.html"><#if asysuser.store_name?if_exists>${(asysuser.store_name)?if_exists}<#else>--</#if></a> </p>
              <p>
               <#if (asysuser.store_servce)?if_exists!="" && (asysuser.store_servce)?if_exists!=null>
	              <#list storesevrceList as slist> 
	                 <#if ((asysuser.store_servce)?if_exists?string?index_of((slist.store_id)?if_exists?string)>-1)>
	               			<span style='background:${(slist.store_color)?if_exists};' class="findlistservice" >${(slist.store_name)?if_exists}</span>
	                 </#if>
	             </#list>
             </#if>
            </p>
            <p>门店编号：<#if asysuser.nike_name?if_exists>${(asysuser.nike_name)?if_exists}<#else>--</#if></p>
	            <p>门店地址：<#if asysuser.address?if_exists>${(asysuser.address)?if_exists}<#else>--</#if></p>
	            <p>联系电话：<#if asysuser.phone?if_exists>${(asysuser.phone)?if_exists}<#else>--</#if></p>
	            <p>营业时间：<#if asysuser.store_opentime?if_exists>${(asysuser.store_opentime)?if_exists}<#else>--</#if></p>
	          </td>
	          <td>
	            <a href="/findShopDetails_${(asysuser.user_id)?if_exists}.html" class="xqBut">查看详情</a>
	          </td>
        </tr>
      </table>
    </li>
   </#list> 
  </ul>
 
</div>
 
 
<#else>
 
 <!--未找到商品-->
<div class="noFind"><img src="/malltemplate/bmall/images/noFind.jpg"><b>您搜索地区暂无门店信息，我们正在努力建设中······</b></div>
 
</#if>

<!--翻页控件-->
<div class="page">
 <#if asysuserList?if_exists && (asysuserList?size > 0)> ${webPageBar?if_exists} </#if>
</div>
<!--翻页控件-->
</@s.form>
<!--返回顶部-->
<div class="returnTop"></div>

<div class="clear"></div>
<!--尾部-->
<#include "/a/bmall/mallbottom.html">

</body>

</html>
