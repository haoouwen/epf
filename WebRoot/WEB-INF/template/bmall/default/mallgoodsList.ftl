<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${cfg_webname?if_exists}-${(navtab.tab_name)?if_exists}</title>
<meta name="keywords" content="${(navtab.tab_name)?if_exists}" />
<meta name="description" content="${(navtab.tab_name)?if_exists}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<script src="/wro/mallpublic.js" type="text/javascript"></script>
<link href="/wro/mallpublic.css" rel="stylesheet" type="text/css"/>
<script src="/malltemplate/bmall/js/jt_product.js" type="text/javascript"></script>
<link href="/malltemplate/bmall/css/jt_product.css" rel="stylesheet" type="text/css" />
<script src="/malltemplate/bmall/js/jquery.lazyload.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/operImgMustLazyload.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/goods.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/cart.js" type="text/javascript"></script>
</head>
<body>
<@s.form id="goodsList" action="/mallmarkslist_${(navtab.tab_number)?if_exists}.html" method="post">
<@s.hidden name="spec_nums" id="spec_nums" value="0"/>
<!--购物车相关隐藏域开始-->
<input id="cart_goods_id" type="hidden" />
<input id="cart_goods_name" type="hidden" />
<input id="cart_is_virtual" type="hidden" />
<input id="cart_goods_img_path" type="hidden" />
<input id="cart_shop_cust_id" type="hidden" />
<input id="cart_shop_name" type="hidden" />
<input id="cart_give_inter" type="hidden" />
<input id="cart_goods_sale_price" type="hidden" />
<input id="cart_sepc_name" type="hidden" />
<input id="cart_sepc_id" type="hidden" />
<input id="cart_shopqq" type="hidden"/>
<input id="cart_radom_no" type="hidden"/>
<!--购物车相关隐藏域结束-->
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
<div class="postion">
<span style="margin-left:-220px;">
<a href="/">首页</a>&nbsp;>&nbsp;<span>${(navtab.tab_name)?if_exists}</span>
</span>
</div>
<!--排序，优惠-->
<div class="proDiv">
  <div class="proSortDiv">
     <div class="proShow">
       <b class="dtsel" id="big_ico">大图</b>
       <b class="xtnsel" id="small_ico">小图</b>
     </div>  
  </div>
  
</div>
<div class="proList">
      <#if goodsList?if_exists && (goodsList?size > 0)>
	    <!--大图列表-->
	    <ul class="bProShow" id="big_showlis">
	       <#list goodsList as goods>
	       		<!--购物车相关隐藏域开始-->
	            <input id="cart_goods_id_${(goods.goods_id)?if_exists}" type="hidden" value="${(goods.goods_id)?if_exists}" />
	            <input id="cart_goods_name_${(goods.goods_id)?if_exists}" type="hidden" value="${(goods.goods_name)?if_exists}"/>
	            <input id="cart_is_virtual_${(goods.goods_id)?if_exists}" type="hidden" value="${(goods.is_virtual)?if_exists}" />
	            <input id="cart_goods_img_path_${(goods.goods_id)?if_exists}" type="hidden" value="<#if goods.list_img!=null>${(goods.list_img)?if_exists}<#else>${cfg_nopic?if_exists}</#if>"/>
	            <input id="cart_shop_cust_id_${(goods.goods_id)?if_exists}" type="hidden" value="${(goods.is_international)?if_exists}"/>
	            <input id="cart_shop_name_${(goods.goods_id)?if_exists}" type="hidden" value="${(shopconfig.shop_name)?if_exists}"/>
	            <input id="cart_give_inter_${(goods.goods_id)?if_exists}" type="hidden" value="${min_sale_price?if_exists}" />
	            <input id="cart_goods_sale_price_${(goods.goods_id)?if_exists}" type="hidden" value="${min_sale_price?if_exists}" />
	            <input id="cart_sepc_name_${(goods.goods_id)?if_exists}" type="hidden" />
	            <input id="cart_sepc_id_${(goods.goods_id)?if_exists}" type="hidden" />
	            <input id="cart_shopqq_${(goods.goods_id)?if_exists}" value="${(shopconfig.qq)?if_exists}" type="hidden"/>
	            <input id="cart_radom_no_${(goods.goods_id)?if_exists}" value="${(shopconfig.radom_no)?if_exists}" type="hidden"/>
	            <!--购物车相关隐藏域结束-->
		        <li>
		          <div><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html"><img src="${(goods.list_img)?if_exists}" width="265px" height="265px"></a><#if goods.sale_name?if_exists!=""><span>${goods.sale_name?if_exists}</span></#if></div>
		          <p class="plPrice"><span class="xsSpan"><i>￥</i>${goods.lmin_price}</span><span class="scSpan">￥${goods.goods_market_price}</span></p>
		          <p class="plName"><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html" >${goods.goods_name?if_exists}</a></p>
		           <p>销售量：<span class="xlspan"><#if (goods.order_num)?if_exists>${(goods.order_num)}<#else>0</#if></span><input type="button" onclick="prepare_AddCart(this,${(goods.goods_id)?if_exists});" class="gmspan" /></p>
		        </li>
	         </#list>
	    </ul>
	    <!--小图列表-->
	    <ul class="sProShow" id="small_showlist">
	       <#list goodsList as goods>
	       <li>
	         <div class="sldiv"><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html"><img src="${(goods.list_img)?if_exists}" width="100px" height="100px"></a><#if goods.sale_name?if_exists!=""><span>${goods.sale_name?if_exists}</span></#if></div>
	         <div class="srdiv">
	           <p class="plName"><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html" >${goods.goods_name?if_exists}</a></p>
	           <p class="plPrice"><span class="xsSpan"><i>￥</i>${goods.lmin_price}</span><span class="scSpan">￥${goods.goods_market_price}</span><input type="button" onclick="prepare_AddCart(this,${(goods.goods_id)?if_exists});" class="gmspan" /></p>
	         </div>
	       </li>
	        </#list>
	    </ul>
      <#else>
    	${search_null_page}
    </#if>
    <br class="clear"/>    
</div>
<!--翻页控件-->
<div class="page">
 <#if goodsList?if_exists && (goodsList?size > 0)> ${webPageBar?if_exists} </#if>
</div>
<!--翻页控件-->

<!--返回顶部-->
<div class="returnTop"></div>

<div class="clear"></div>
<!--尾部-->
<#include "/a/bmall/mallbottom.html">
<script src="/malltemplate/bmall/js/claMenu.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/classify.js" type="text/javascript"></script>
<!--加入购物车显示效果开始-->
<#include "WEB-INF/template/bmall/default/goodsCart.ftl">
<@s.hidden name="listshow" id="listshow"/>
</body>
</@s.form>
<!-- 右侧购物车 -->
<#include "/WEB-INF/template/bmall/default/rightCart.ftl">
</html>


