<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}—我的收藏</title>

<meta name="author" content="久通宏达科贸（北京）有限公司">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">           
<meta http-equiv="Pragma" content="no-cache">
<link href="/malltemplate/jiutong/css/mbPublic.css" rel="stylesheet" type="text/css"></link>
<link href="/malltemplate/jiutong/css/mbMember.css" rel="stylesheet" type="text/css"></link>
<link rel="stylesheet" type="text/css" href="/malltemplate/jiutong/css/scrollbar.css">
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
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>我的收藏 <span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">

<#if collectionList?if_exists && collectionList.size() gt 0>
    <!--订单不为空-->
<header class="hasOrder">
<div id="wrapper">
	<div id="scroller">
	<div id="thelist">
   <#list collectionList as collection>
		   <!--待发货-->
		  <div class="hoDiv">
	 		  <input type="hidden" name="collection.coll_id" value="${collection.coll_id?if_exists}"/>
		    <table>
		    
		    <#if collection.coll_type?if_exists=="0">
		      <tr>
		        <td>
		           <div class="imgDiv">
		               <a href="/webapp/goodsdetail/${collection.goods_id?if_exists}.html" >
		                <img src="${collection.list_img?if_exists}"/></a>   
		             </div>
		        </td>
		        <td>
		           <p><a href="/webapp/goodsdetail/${collection.goods_id?if_exists}.html">${collection.goods_name?if_exists}</a></p>
		           <p><b>￥${collection.sale_price?if_exists}</b></p>
		           <p>商品收藏</p>
		           <p><a href="javascript:void(0);" onclick='webappdelOneInfo("/webapp/collection!webappdelete.action","chb_id","${collection.coll_id?if_exists}");' class="mBut">取消收藏</a></p>
		        </td>
		      </tr>
		      <#else>
		       <tr>
		        <td>
		           <div class="imgDiv">
		               <a href="/webapp/goods!list.action?brand_id_s=${collection.brand_id?if_exists}" >
		                <img src="${collection.logo?if_exists}"/></a>   
		             </div>
		        </td>
		        <td>
		           <p><a href="/webapp/goods!list.action?brand_id_s=${collection.brand_id?if_exists}">${collection.brand_name?if_exists}</a></p>
		           <p>品牌收藏</p>
		           <p><a href="javascript:void(0);" onclick='webappdelOneInfo("/webapp/collection!webappdelete.action","chb_id","${collection.coll_id?if_exists}");' class="mBut">取消收藏</a></p>
		        </td>
		      </tr>
		      </#if>
		    </table>
		  </div>
	 </#list>
    </div>
	 	 <div id="pullUp">
			<span class="pullUpIcon"></span><span class="pullUpLabel">上拉加载更多...</span>
		</div>
		<!-- <div class="clearEmpty"><a href="javascript:void(0);" onclick='webappdelInfo("/webapp/collection!webappdelete.action","collection.coll_id");'>全部取消</a></div>-->
	</div>
</div>
    </header>
<#else>
<!--订单为空-->
<#include "/WEB-INF/template/webapp/jiutong/mbemptyorder.ftl">
</#if>
</div>

<!--尾部-->
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">


<@s.form  action="" id="commonForm">
	<@s.hidden name="" id="commonId" value=""/>	
	<@s.hidden name="" id="commonText" value=""/>	
	<div id="hidden_div">
		${listSearchHiddenField}
	</div>
</@s.form>
</body>
<script src="/wro/webapp_common.js" type="text/javascript"></script>
<script src="/include/components/artDialog5.0/artDialog.min.js"></script>
<link href="/include/components/artDialog5.0/skins/blue.css" rel="stylesheet" />
<script type="application/javascript" src="/malltemplate/jiutong/js/iscroll.js"></script>
<script type="application/javascript" src="/malltemplate/jiutong/js/collect.js"></script>
<script type="application/javascript" src="/malltemplate/jiutong/js/collectlist.js"></script>
</html>
