<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${cfg_webname?if_exists}-商品列表</title>
<meta name="author" content="久通宏达科贸（北京）有限公司">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">           
<meta http-equiv="Pragma" content="no-cache">
<link href="/malltemplate/jiutong/css/mbPublic.css" rel="stylesheet" type="text/css"></link>
<link href="/malltemplate/jiutong/css/mbProduct.css" rel="stylesheet" type="text/css"></link>
<link href="/malltemplate/jiutong/css/scrollbar.css" rel="stylesheet" type="text/css"></link>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
#footer {
	position:absolute;
	bottom:0; left:0;
	width:100%;
	height:48px;
	padding:0;
}
</style>
</head>

<body>

<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>商品列表<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<@s.form id="indexForm" action="/webapp/goods!list.action" method="post">
<@s.hidden name="catEn_name" id="catEn_name"/>
<@s.hidden name="sort" id="sort"/>
<@s.hidden name="specstr_s" id="specstr"/>
<@s.hidden name="brand_id_s" id="brand_id"/>
<@s.hidden name="area_attr_s"/>
<@s.hidden name="goods_cat_attr_s" id="goods_cat_attr_s"/>
<@s.hidden name="key_word" id="serach_word"/>
<@s.hidden name="en_word" id="serach_enword"/>
<@s.hidden name="order_by_s" id="order_by_s"/>
<@s.hidden name="listshow" id="listshow"/>
<#if goodsList?if_exists && goodsList.size() gt 0>
  <!--产品筛选-->
<div class="sortDiv">

   <div class="choiceDiv">
     <p>
       <#assign sort_default="">
       <#assign sort_collnum="">
       <#assign sort_salenum="">
       <#assign sort_goodsprice="">
       
       <#assign order_by_collnum="">
       <#assign order_by_salenum="">
       <#assign order_by_price="">
       <#assign smButStyle="">
       
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
       
       <#if listshow?if_exists=="0" || listshow?if_exists=="">
             <#assign smButStyle="">
       <#elseif listshow?if_exists=="1">
             <#assign smButStyle="smBut">
       </#if>
       
       <span><b class="${sort_default}" onclick="toSubmit('0','');">默认</b></span>
       <span><b class="${sort_collnum}" onclick="toSubmit('${order_by_collnum}','collnum');">新品</b></span>
       <span><b class="${sort_salenum}" onclick="toSubmit('${order_by_salenum}','salenum');">销量</b></span>
       <span><b class="${sort_goodsprice}" onclick="toSubmit('${order_by_price}','price');">价格</b></span>
     </p>
   </div>
   
   <#--><div class="selModel"><span class="bmBut ${smButStyle}" onclick="big_small_submit('${listshow}');"></span></div>-->
   <div class="sxDiv"><span class="sxBut"></span></div>
</div>

<!--小图产品-->
<header class="sProList">
  <div id="wrappers">
	 <div id="scrollers">
	     <ul id="thelists">
		     <#list goodsList as goods>
			     <li>
			       <div class="sPic">
			             <a href="/webapp/goodsdetail/${(goods.goods_id)?if_exists}.html">
			                 <#if (goods.list_img)?if_exists!="">
						          <img src="${(goods.list_img)?if_exists}"/>
						      <#else>
						          <img src="${(cfg_webappdefaultpic)?if_exists}"/>
						      </#if>
			             </a>
			       </div>
			       <div class="sPCont">
			         <a href="/webapp/goodsdetail/${(goods.goods_id)?if_exists}.html">
			          <p ><span class="sPContshouw">
			          <#if (goods.depot_id)?if_exists=="1">欧洲直邮<#else>保税仓发货</#if></span>${goods.goods_name?if_exists}</p>
			          <#if goods.sale_name?if_exists!=""><p>促销活动：<span class="salesblist">${goods.sale_name?if_exists}</span></p></#if>
			          <p class="pPrice"><b>￥${(goods.sale_price)?if_exists}</b><span class="sProListsss">￥${(goods.market_price)?if_exists}</span></p>
			          </a>
			          <p>销量：<#if (goods.goods_sale_num)?if_exists>${(goods.goods_sale_num)}<#else>0</#if>&nbsp;|&nbsp; 评价：${(goods.eval_num)?if_exists?number}</p>
			       </div>
			       <br class="clear"/>
			     </li>
			  </#list>
           </ul>
           <div id="pullUps">
			  <span class="pullUpIcon"></span><span class="pullUpLabel">上拉加载更多...</span>
		  </div>
     </div>
  </div>
</header>

<#else>

<!--搜索为空-->
<#include "/WEB-INF/template/webapp/jiutong/mbempty.ftl">

</#if>

<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">

<!--筛选-->
<div class="filterDiv">
 
    <div class="filh2">商品筛选</div> 
    <!---->
    <#if goodsBrandList?if_exists && goodsBrandList.size() gt 0>
    <div class="filsDiv">
      <p class="ph2">品牌</p>
      <p class="pfil">
         <#list goodsBrandList as goodsbrand>
              <a href="javascript:void(0);" onclick="chooseBrand('${(goodsbrand.id)?if_exists}');">
                  ${(goodsbrand.name)?if_exists}
              </a>
         </#list>
      </p>
    </div>
    </#if>
    <!---->
   <#if cateList?if_exists && cateList.size() gt 0>
    <div class="filsDiv">
      <p class="ph2">分类</p>
      <p class="pfil">
         <#list cateList as cate> 
             <a href="javascript:void(0);" onclick="chooseCat('${(cate.id)?if_exists}');">${(cate.name)?if_exists}</a>
         </#list>
      </p>
    </div>
    </#if>
    
    <div class="filPrice">
      <p> 价格区间</p>
          <input type="text" id="l_price_id"  name="min_price" 
          value="${min_price?if_exists}" class="filText">元<span>~</span><input type="text" id="h_price_id"  
          name="max_price" value="${max_price?if_exists}" class="filText">元
    </div>
    
    <div class="filrBut">
     <span><i onclick="clearAll();">清空</i></span>
     <span><i class="qdBut" onclick="toSearch();">确定</i></span>
    </div>
    
</div>
<div class="filClose"><img src="/malltemplate/jiutong/images/filClose.gif"/></div>

</@s.form>

</body>
<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/mbProduct.js"  type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/iscroll.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/mbProductList.js"  type="text/javascript"></script>
<script type="text/javascript">
	//初始化加载
	$(document).ready(function(){
	   $("#rtb_id").hide();
	 });
</script>
</html>
