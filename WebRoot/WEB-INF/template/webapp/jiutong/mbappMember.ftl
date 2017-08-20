<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}—会员中心</title>

<meta name="author" content="久通宏达科贸（北京）有限公司">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">           
<meta http-equiv="Pragma" content="no-cache">
<link href="/wro/webapp_common.css" rel="stylesheet" type="text/css"/>
<link href="/malltemplate/jiutong/css/mbMember.css" rel="stylesheet" type="text/css"></link>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<@s.form action="" method="post" id="indexForm">

<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>会员中心<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">

<!--头像-->
<div class="memberHead">
   <div class="head">
      <a href ="http://m.epff.cc/webapp/memberuser!updateMemberIcon.action?user_id=${(member.cust_id)?if_exists}">
	       <#if (member.logo_path)?if_exists != null && (member.logo_path)?if_exists !="">
	           <img src="${(member.logo_path)?if_exists}" width="100px" height="100px"/>
	       <#else>
	           <img src="/malltemplate/jiutong/images/headDefaultImage.jpg" width="100px" height="100px"/>
	       </#if>
	    </a>
   </div>
   <p>${(memberuser.user_name)?if_exists}</p>
</div>

<!--会员中心-->
<div class="member">
    <!---->
    <div class="topMember">
       <a href="javascript:void(0);" onclick="searchByOrderState('1');" class="wPayA">${waitPayNunm?if_exists}</a>
       <a href="javascript:void(0);" onclick="searchByOrderState('2');" class="wDeliveryA">${waitDeliveryNum?if_exists}</a>
       <a href="javascript:void(0);" onclick="searchByOrderState('3');" class="wGoodsA">${waitReceiptNum?if_exists}</a>
       <a href="javascript:void(0)" class="wEvaluate"  onclick="searchByOrderState('7','evorder');">${waitEvaluationNum?if_exists}</a>
       <a href="/webapp/receivebox!list.action" class="wNews">${total_news?if_exists}</a>
    </div>
    <!---->
    <div class="botMember">
      <a href="/webapp/goodsorder!webappAllOrder.action" class="orderA">全部订单</a>
     
      <a href="/webapp/collection!webappCollection.action" class="collectionA">我的收藏</a>
     
      <a href="/webapp/goodsorder!webappLogisticsOrder.action" class="logisticsA">物流信息</a>
      
       <a href="/webapp/memberuser!webappAccount.action" class="accountA">账户中心</a>
       
          <a href="/webapp/redsumer!list.action" class="envelopeA">我的红包</a>
          
           <a href="/webapp/exchange!buylist.action" class="newsA">我的换货</a>
      
       <a href="/webapp/goodsorder!cancelOrderList.action" class="cancelA">取消订单记录</a>
       
        <a href="/webapp/comsumer!list.action" class="couponA">我的优惠劵</a>
    
        <a href="/webapp/refundapp!buylist.action" class="browseA">退款退货</a>
     
      <br class="clear"/>
    </div>
</div>
<@s.hidden name="order_state_s" id="order_state_s"/>
</@s.form>


 <#if (recomList?if_exists?size>0)>
 <!--会员优选-->
<div class="yxMember">
      <h2>会员优选</h2>
      <div class="hotProuct">
         <ul>
            <#list recomList as goods> 
	           <li>
	             <div>
	                <a href="/webapp/goodsdetail/${goods.goods_id?if_exists}.html" target="_blank">
	                 <img src="<#if goods.list_img!=null>${(goods.list_img)?if_exists}<#else>${cfg_nopic?if_exists}</#if>"  />
	                </a>
	                <p class="pName"><a href="/webapp/goodsdetail/${goods.goods_id?if_exists}.html" target="_blank">${goods.goods_name?if_exists}</a></p>
	                <p class="pPrice">￥${goods.min_sale_price?if_exists}</p>
	             </div>
	           </li>
            </#list>
         </ul>
      </div>
 </div>
 </#if>


<!--尾部-->

<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">


</body>
<script src="/wro/webapp_common.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/goodsorder.js" type="text/javascript"></script>
</html>
