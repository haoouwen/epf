<!--左边商家信息等信息-->
 <div class="wld200">
	<#if (goods.cust_id)?if_exists=='0'>
	    <#include "/WEB-INF/template/bmall/default/goodsMallLeft.ftl">
	<#else>
	    <#include "/WEB-INF/template/bmall/default/goodsShopinfo.ftl">
	    <#include "/WEB-INF/template/bmall/default/goodsCat.ftl">
	    <#include "/WEB-INF/template/bmall/default/goodsSort.ftl">
    </#if>
     <!--最近浏览记录-->
     <#if spec_count?if_exists == 0 >
	    <#include "WEB-INF/template/bmall/default/goodsRecord.ftl">
	 </#if>
 </div>
    