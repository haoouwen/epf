<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}退货详情</title>

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
<link href="/malltemplate/jiutong/css/refund.css" rel="stylesheet" type="text/css"></link>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>

<body>

<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>退货详情<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<@s.form action="/webapp/refundapp!buyDelivery.action" method="post" id="indexForm">
<!--1-->
<!--等待卖家处理退货申请-->
<div class="orderDetailTop">
  <p>
    <b>
       <#if (refundapp.refund_state)?if_exists=='0'>等待处理
    	<#elseif (refundapp.refund_state)?if_exists=='1'>退货成功
    	<#elseif (refundapp.refund_state)?if_exists=='2'>退货失败
    	<#elseif (refundapp.refund_state)?if_exists=='5'>撤销申请
    	<#elseif (refundapp.refund_state)?if_exists=='3'>等待买家发货
    	<#elseif (refundapp.refund_state)?if_exists=='4'>等待商家确认收货
       </#if>
    </b>
  </p>
  <p>退货编号：${(refundapp.return_no)?if_exists}</p>
  <p>退款金额：<b>￥${(refundapp.refund_amount)?if_exists}</b></p>
  <p>退货原因：
      <#list commparaList as com>
          <#if com.para_value=refundapp.buy_type>
          	${(com.para_key)?if_exists}
          </#if>
      </#list>
  </p>
  <p class="applyTip">
    <#if (refundapp.refund_state)?if_exists=='0'>
         如果卖家同意，退货申请将达成,卖家会提供相应的地址请注意查看状态并填写物流信息<br/> 
         如果卖家拒绝，将需要您修改退货申请
      
    	<#elseif (refundapp.refund_state)?if_exists=='1'>
    	  账号：<b>${buy_name?if_exists}</b><br/> 
	    退款金额：<b>${(refundapp.refund_amount)?if_exists}</b>元<br/> 
	    退款日期：${(refundapp.seller_date)?if_exists}

    	<#elseif (refundapp.refund_state)?if_exists=='2'>
    	  处理时间：${(refundapp.seller_date)?if_exists}<br/>
       失败类型：<b>商家拒绝退货!</b><br/>
       理由：${(refundapp.seller_reason)?if_exists}
                    
    	<#elseif (refundapp.refund_state)?if_exists=='5'>
    	  撤销时间：${(refundapp.info_date)?if_exists}<br/>
		  失败类型：<b class="mred">您撤销退货申请!</b><br/>
		  理由：${(refundapp.seller_reason)?if_exists}<br/>
    	
    	<#elseif (refundapp.refund_state)?if_exists=='3'>
    	  <b>商家同意您的退货申请，请填写物流信息</b><br/>
		  收货人：<b>${(buyeraddr.consignee)?if_exists}</b><br/> 
	    收货地址：${(buyeraddr.area_attr)?if_exists}  ${(buyeraddr.address)?if_exists}<br/>
	    收货手机：${(buyeraddr.cell_phone)?if_exists}<br/>
	    收货电话：${(buyeraddr.phone)?if_exists}
    	<#elseif (refundapp.refund_state)?if_exists=='4'>
    	 <b>等待商家确认收货</b><br/>
    	 <b>如果确认收货，退货申请达成，商家按原路返回对应的金额，请注意查收</b><br/>
    	 退货物流跟踪:</br/>
    	 送货方式：	
           <#assign zfsend="">
             <#list sendmodeList as slist>
                 <#if refundapp.send_mode==slist.smode_id>
                      <#assign zfsend=slist.smode_name>
                 </#if>
            </#list>
            <#if (zfsend)?if_exists>
        	${(zfsend)?if_exists}
        	<#else>
        	 快递
    	    </#if><br/>
    	 运单号：${refundapp.send_num?if_exists}<br/>
    	 <a href="http://m.kuaidi100.com/index_all.html?type=${(zfsend)?if_exists}&postid=${refundapp.send_num?if_exists}&callbackurl=${cfg_mobiledomain}/webapp/refundapp!buygoodsview.action?refundapp.trade_id=${refundapp.trade_id}"  target="_blank" ><i class="pcOrange">请点击查看物流信息<i></a>
    </#if>
   
  </p>
  <#if (refundapp.refund_state)?if_exists=='0'>
    <p class="orderDetailP">
	 <#assign ret_type_str="">
     <#assign ret_liyou_str="">
     <#assign list_img_str=""><!-- 退款图片-->
     <#if (refundapp.is_return)?if_exists==0> <#assign ret_type_str="退款"><#else> <#assign ret_type_str="退货"></#if></b><!-- 退款类型-->
     <#list commparaList as com>
        <#if com.para_value=refundapp.buy_type>
          <#assign ret_liyou_str=com.para_key><!-- 退款理由 -->
        </#if>
     </#list>
     <#list detailList as detail>
     	 <#if ((detail.order_id)?if_exists)==((refundapp.order_id)?if_exists)>
	     	 <!-- 主要用于撤销申请提示框的数据 -->
	     	 <#assign num=num+1>   
	         <#if num?if_exists=="1">
	         	<#assign list_img_str="${detail.temporary_goodsimg?if_exists}"><!-- 退款图片-->
	   		 <#else>
	   			 <#assign list_img_str="${list_img_str}#${detail.temporary_goodsimg?if_exists}"><!-- 退款图片-->
	   		 </#if>
	   		 
   		 </#if>
     </#list>
     <a href="javascript:void(0);" onclick="cancelOrder('${(refundapp.trade_id)?if_exists}','${(refundapp.refund_amount)?if_exists}','${(refundapp.return_no)?if_exists}','${list_img_str?if_exists}','${ret_type_str?if_exists}','${ret_liyou_str?if_exists}','${(refundapp.buy_reason)?if_exists}')"><i>撤销退货申请</i></a>  
	     <br class="clear"/>
	</p>
	<#elseif (refundapp.refund_state)?if_exists=='2' || (refundapp.refund_state)?if_exists=='5'>
	  <p class="orderDetailP">
	     <a href="/webapp/refundapp!appRefundGoods.action?order_id=${refundapp.order_id?if_exists}"><i>重新申请退货</i></a> 
	     <br class="clear"/>
	  </p>
    </#if>
</div>

<#if (refundapp.refund_state)?if_exists=='3'>
<div class="apply">
    <div class="applyCont">
      <p><span>物流信息:</span><@s.select  name="order_send_mode"  list="sendmodeList" listValue="smode_name" listKey="smode_id" /><br class="clear"/></p>
      <p><span>物流单号:</span><@s.textfield   name="order_send_num" cssClass="aifText"  maxlength="20" /><br class="clear"/></p>
      
    </div>  
</div>
<@s.hidden name="refundapp.trade_id" id="refundapp_id" value="${(refundapp.trade_id)?if_exists}"/>
					                                
<div class="submitDiv"><a href="javascript:void(0);" onclick="document.forms[0].submit();">提交</a></div>
</#if>

<!--订单商品-->
<div class="hasOrder">
  <!--待收货-->
  <header class="hoDiv">
    <table>
     <#list detailList as dlist>
      <tr>
        <td>
          <div class="imgDiv">
            <#if dlist.img_path!=''>
	  			<#if ((dlist.img_path)?index_of(",") > (-1))>      			
	  				<#assign startpos = "${dlist.img_path?if_exists}"?index_of(',')>
	                <#if ((startpos-1)>-1)>
	                    <#assign img =(dlist.img_path)[(0)..(startpos-1)]>
	                 </#if>
	             <#else> 
	             		<#assign img =dlist.img_path>
	             </#if> 
	             	<a href="/webapp/goodsdetail/${(dlist.goods_id)?if_exists}.html" target="_blank">
	      				<img src="${(img)?if_exists}" width='60px' height='60px'>
	      			</a>
      		 <#else>
      		 	<a href="/webapp/goodsdetail/${(dlist.goods_id)?if_exists}.html" target="_blank">
      				<img src="${(cfg_nopic)?if_exists}" width='60px' height='60px'>
      			</a>
      		 </#if>
          </div>
        </td>
        <td>
           <p><a href="/webapp/goodsdetail/${(dlist.goods_id)?if_exists}.html" target="_blank">${(dlist.ogoods_name)?if_exists}</a></p>
           <p>金额：<b>￥<#if dlist.goods_price?if_exists>${(dlist.goods_price*dlist.order_num)?if_exists}<#else>-</#if></b></p>
           <p>数量：<#if dlist.order_num?if_exists>${dlist.order_num}<#else>-</#if></p>
        </td>
       </tr>
     </#list>
    </table>
  </header>
</div>

<@s.hidden name="logistics_query" id="logistics_query_id"/>
<@s.hidden name="kuai_company" id="kuai_company_id"/>
<@s.hidden name="cancel_trade_desc" id="cancel_desr_id"/>   
<@s.hidden name="trade_id" id="cancel_trade_id"/>
</@s.form>
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">
<!--取消订单弹出层-->
<div class="popupDiv" id="cancelOrderId">
</body>
<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/refundapp.js" type="text/javascript"></script>
</html>
