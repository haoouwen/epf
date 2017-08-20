<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${seo_title?if_exists}</title>
<meta name="keywords" content="${seo_keyword?if_exists}" />
<meta name="description" content="${seo_desc?if_exists}" />
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
<@s.form id="goodsList" action="/Search.html" method="post">
<@s.hidden name="catEn_name" id="catEn_name"/>
<@s.hidden name="sort" id="sort"/>
<@s.hidden name="specstr_s" id="specstr"/>
<@s.hidden name="brand_id_s" id="brand_id"/>
<@s.hidden name="area_attr_s"/>
<@s.hidden name="goods_cat_attr_s"  id="goods_cat_attr_s"/>
<@s.hidden name="order_by_s" id="order_by_s"/>
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
<#if goodsListMap?if_exists.postName?if_exists!=''>
<span style="margin-left:-220px;">${goodsListMap.postName?if_exists}</span>
<#else>
<span style="margin-left:-220px;">
<a href="/">首页</a>&nbsp;>&nbsp;<span>搜索</span>
</span>
</#if>
</div>
<!--条件选择-->
<div class="selmain">
   <!--品牌-->
        <#if (goodsBrandList)?if_exists?size gt 0>
      <div class="selcont">
      <div class="lscont">品牌</div>
      <div class="rscont">
        <div class="rsPicDiv">
          <ul>
            <#list goodsBrandList as goodsbrand>
     			 <li><a href="###" onclick="document.forms[0].brand_id_s.value=${(goodsbrand.id)?if_exists};document.forms[0].submit();">
							<img src="${(goodsbrand.logo)?if_exists}" width="120" height="50" />
						</a>
				</li>
              </#list>
          </ul>
          <div class="clear"></div>
        </div>
      </div>
      <div class="clear"></div>
    </div>
    </#if>
     
   <!--分类-->
        <#if (cateList)?if_exists?size gt 0>
         <div class="selcont">
		      <div class="lscont">分类</div>
		      <div class="rscont">
		        <div class="rsTextDiv">
		          <ul>
		             <#list cateList as cate> 
			     	   <li><a id="${(cate.id)?if_exists}" href="###" 
			     	   onclick="document.forms[0].goods_cat_attr_s.value='${(cate.id)?if_exists}';document.forms[0].submit();">${(cate.name)?if_exists}</a>
			     	   (${(cate.count)?if_exists})
			     	   </li>
			        </#list>
		           </ul>
		         </div>
		         <div class="clear"></div>
		      </div>
		      <div class="clear"></div>
		    </div>
        </#if>
 <!--属性-->
	<#if goodsListMap.specnameList?if_exists && (goodsListMap.specnameList?size > 0)>
	 <#list goodsListMap.specnameList as specname>	
	  <div class="selcont">
	      <div class="lscont">${(specname.sname)?if_exists}</div>
	      <div class="rscont">
	        <div class="rsTextDiv">
	          <ul>
	              <#list goodsListMap.specsizevalueList as specsecond> 
		                <#if specsecond.spec_code==specname.spec_code>  
		                     <li><a id="${specsecond.sv_code?if_exists}" href="###" onclick="document.forms[0].specstr_s.value=${specsecond.sv_code?if_exists};document.forms[0].submit();">
		                     	<#if (specsecond.sv_name)?if_exists?length lt 11>
			           				${specsecond.sv_name}
			           			<#else>
			           				${specsecond.sv_name[0..10]}
			           			</#if>
		                     </a>
		                     </li>
		            	</#if>
           			</#list>
	           </ul>
	         </div>
	         <div class="clear"></div>
	      </div>
	      <div class="clear"></div>
	    </div>
	</#list>
	</#if>
</div>


<!--排序，优惠-->
<div class="proDiv">
  <#assign sort_default="">
       <#assign sort_collnum="">
       <#assign sort_salenum="">
       <#assign sort_goodsprice="">
       
       <#assign order_by_collnum="">
       <#assign order_by_salenum="">
       <#assign order_by_price="">
       
       <#if sort?if_exists=='0' || sort?if_exists==''>
           <#assign sort_default="mrsb">
       <#elseif sort?if_exists=='collNum_desc'>
           <#assign sort_default="mrb">
	       <#assign sort_collnum="gdb">
	       
	       <#assign order_by_collnum="collNum_asc">
	       
	   <#elseif  sort?if_exists=='collNum_asc'>
	       <#assign sort_default="mrb">
	       <#assign sort_collnum="dgb">
	       
	       <#assign order_by_collnum="collNum_desc">
	       
       <#elseif sort?if_exists=='sale_num_desc'>
          <#assign sort_default="mrb">
	       <#assign sort_salenum="gdb">
	       
	       <#assign order_by_salenum="sale_num_asc">
	       
	   <#elseif sort?if_exists=='sale_num_asc'>
	       <#assign sort_default="mrb">
	       <#assign sort_salenum="dgb">
	       
	       <#assign order_by_salenum="sale_num_desc">
	       
	   <#elseif sort?if_exists=='price_desc'>
	       <#assign sort_default="mrb">
	       <#assign sort_goodsprice="gdb">
	       
	       <#assign order_by_price="price_asc">
	       
       <#elseif sort?if_exists=='price_asc'>
           <#assign sort_default="mrb">
	       <#assign sort_goodsprice="dgb">
	       
	       <#assign order_by_price="price_desc">
	       
       </#if>
       
       <#assign big_small_pic="">
       <#if listshow?if_exists=="0">
             <#assign big_small_pic="1">
       <#elseif listshow?if_exists=='1'>
             <#assign big_small_pic="0">
       </#if>
  <div class="proSortDiv">
     <div class="proSort" id="proSortId">
       <b class="${sort_default}" onclick="toSubmit('0','');">默认</b>
       <b class="${sort_collnum}" onclick="toSubmit('${order_by_collnum}','collnum');">新品</b>
       <b class="${sort_salenum}" onclick="toSubmit('${order_by_salenum}','salenum');">销量</b>
       <b class="${sort_goodsprice}" onclick="toSubmit('${order_by_price}','price');">价格</b>
     </div>
     <div class="proPrice">
     <input type="text" name="min_price" value="${min_price?if_exists}" class="pftext">
     <input type="text" name="max_price" value="${max_price?if_exists}" class="pstext">
     <input type="button" class="pbut"  onclick="document.forms[0].submit();">
     </div>
      <!-- 优惠
     <div class="proSale">
          <span><input type="checkbox" name="saleName">新的商品</span>
          <span name="saleName"><input type="checkbox">包邮</span>
          <span><input type="checkbox" name="saleName">折扣</span>
          <span name="saleName"><input type="checkbox">满就减</span>
     </div>
     -->
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
		          <div><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html" target="_blank">
		          
		          <img src="<#if goods.list_img?if_exists>${(goods.list_img)?if_exists}<#else>${cfg_nopic?if_exists}</#if>"  width="265px" height="265px">
		          
		          </a><#if goods.sale_name?if_exists!=""><span>${goods.sale_name?if_exists}</span></#if></div>
		          <p class="plPrice"><span class="xsSpan"><i>￥</i>${goods.lmin_price}</span><span class="scSpan">￥${goods.market_price}</span></p>
		          <p class="plName"><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html"  target="_blank">${goods.goods_name?if_exists}</a></p>
		          <p>
		          	销售量：<span class="xlspan"><#if (goods.order_num)?if_exists>${(goods.order_num)}<#else>0</#if>&nbsp;</span>|
		          	评价：<span class="pjspan">${(goods.eval_num)?if_exists}</span>
		          	<input type="button" onclick="prepare_AddCart(this,${(goods.goods_id)?if_exists});" class="gmspan" />
		          </p>
		        </li>
	         </#list>
	    </ul>
	    <!--小图列表-->
	    <ul class="sProShow" id="small_showlist">
	       <#list goodsList as goods>
	       <li>
	         <div class="sldiv"><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html" target="_blank">
	           <img src="<#if goods.list_img?if_exists>${(goods.list_img)?if_exists}<#else>${cfg_nopic?if_exists}</#if>"  width="100px" height="100px">
	         </a><#if goods.sale_name?if_exists!=""><span>${goods.sale_name?if_exists}</span></#if></div>
	         <div class="srdiv">
	           <p class="plName"><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html"  target="_blank">${goods.goods_name?if_exists}</a></p>
	           <p class="plPrice"><span class="xsSpan"><i>￥</i>${goods.sale_price}</span><span class="scSpan">￥${goods.market_price}</span></p>
	           <p>
	           	销售量：<span class="xlspan">${(goods.order_num)}</span>&nbsp;|
	           	评价：<span class="pjspan">${(goods.eval_num)?if_exists}</span>
	           	<input type="button" onclick="prepare_AddCart(this,${(goods.goods_id)?if_exists});" class="gmspan" />
	           </p>
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

<div class="clear"></div>
<!--尾部-->
<#include "/a/bmall/mallbottom.html">
<!--加入购物车显示效果开始-->
<#include "WEB-INF/template/bmall/default/goodsCart.ftl">
<!-- 右侧购物车 -->
<#include "/WEB-INF/template/bmall/default/rightCart.ftl">
<@s.hidden name="key_word" id="serach_word"/>
<@s.hidden name="en_word" id="serach_enword"/>
<script src="/malltemplate/bmall/js/claMenu.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/classify.js" type="text/javascript"></script>
<@s.hidden name="listshow" id="listshow"/>
</body>
</@s.form>
</html>


