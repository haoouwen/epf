<!--相关分类-->
  <div class="relatedClass">
     <h2>相关分类</h2>
     <div class="relatedCont">
     <#list categoryList as category>
       <a href="/mall-goodslist-${category.en_name?if_exists}.html">${category.cat_name?if_exists}</a>
     </#list>
     </div>
  </div>
  <!--同类其他品牌-->
  <div class="otherClass">
    <h2>同类其他品牌</h2>
    <div class="otherCont">
    <#list goodsBrandList as goodsbrand>
      <a href="/mall-goodslist-${goodsbrand.en_name?if_exists}.html">${goodsbrand.brand_name?if_exists}</a>
    </#list>
    </div>
    <#--
       <!--最近浏览记录
    <#if spec_count?if_exists gt 0 >
	    	<#include "WEB-INF/template/bmall/default/goodsRecord.ftl">
    </#if>
    -->
  </div>
  <!--同分类销量排行-->
  <#if hotsaleList?size gt 0>
	  <div class="specialRank">
	    <h2>同分类销量排行</h2>
	    <div class="specialRankcont">
	        
	        <div class="specialGrank">
	          <ul>
	              <#list hotsaleList as hot>
	                <li><div><a href="/mall-goodsdetail-${hot.goods_id?if_exists}.html">
	                <#if hot.img_path==null || hot.img_path=="">
	                	<img src="${cfg_nopic?if_exists}">
	                <#else>
	                	<img src="${hot.list_img?if_exists}">
	                </#if>
	                	</a></div><p><a href="/mall-goodsdetail-${hot.goods_id?if_exists}.html">${hot.goods_name?if_exists}</a></p><p><strong class="red">￥${hot.sale_price?if_exists}</strong></p></li>
	               </#list>
	          </ul>
	        </div>
	        
	    </div>
	  </div>
  </#if>