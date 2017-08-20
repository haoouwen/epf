<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${cfg_webname?if_exists}换货详情</title>
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
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>换货详情<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
 <@s.form action="/webapp/exchange!buyDelivery.action" method="post" validate="true" >
<!--1-->
 <#if (exchange.refund_state)?if_exists=='0'><!--等待处理-->
                        
        <!--退款流程-等待--> 
        <div class="orderDetailTop">
		  <p><b>等待商家处理换货申请</b></p>
		  <p>申请时间：<#if exchange.buy_date?if_exists?length gt 19>
			      ${exchange.buy_date[0..18]?if_exists}
			      <#else>
			      ${exchange.buy_date?if_exists}
			      </#if>	
		 </p>
		  <p class="applyTip">
		    如果商家同意，换货申请将达成,商家会提供相应的地址请注意查看状态并填写物流信息
              如果商家拒绝，将需要您修改换货申请
		  </p>
		  <p class="orderDetailP">
		     <a href="/webapp/exchange!buylist.action"><i class="iRed">返回我的换货</i></a>
		     <a href="/webapp/goodsorder!webappAllOrder.action"><i>返回订单中心</i></a> 
		     <br class="clear"/>
		  </p>
</div>
  <#elseif (exchange.refund_state)?if_exists=='1' ||(exchange.refund_state)?if_exists=='a'><!--退款成功-->
    <!--退款流程-等待--> 
        <div class="orderDetailTop">
		  <p><b>换货成功</b></p>
		  <p>换货时间：<#if exchange.seller_date?if_exists?length gt 19>
			      ${exchange.seller_date[0..18]?if_exists}
			      <#else>
			      ${exchange.seller_date?if_exists}
			      </#if>	
		 </p>
		  <p class="orderDetailP">
		     <a href="/webapp/exchange!buylist.action"><i class="iRed">返回我的换货</i></a>
		     <a href="/webapp/goodsorder!webappAllOrder.action"><i>返回订单中心</i></a> 
		     <br class="clear"/>
		  </p>  
		  </div>
<#elseif (exchange.refund_state)?if_exists=='2' ||(exchange.refund_state)?if_exists=='9' ||(exchange.refund_state)?if_exists=='b'><!--退款失败-->
				    	
  <!--换货流程-换货失败--> 
      <div class="orderDetailTop">
		  <p><b>换货失败</b></p>
		  <p>处理时间：<#if exchange.seller_date?if_exists?length gt 19>
			      ${exchange.seller_date[0..18]?if_exists}
			      <#else>
			      ${exchange.seller_date?if_exists}
			      </#if>	
		 </p>
		  <p>失败类型：商家拒绝换货</p>
          <p>理由：${(exchange.seller_reason)?if_exists}</p>
		  <p class="orderDetailP">
		     <a href="/webapp/refundapp!appRefundGoods.action?order_id=${exchange.order_id?if_exists}"><i class="iRed">重新换货申请</i></a>
		     <a href="/webapp/exchange!buylist.action"><i>返回我的换货</i></a> 
		     <br class="clear"/>
		  </p>  
		  </div>
<#elseif (exchange.refund_state)?if_exists=='5'><!--撤销申请-->
				    	 
	  <!--换货流程-换货失败--> 
         <div class="orderDetailTop">
		  <p><b>换货失败</b></p>
		  <p>撤销时间：<#if exchange.info_date?if_exists?length gt 19>
			      ${exchange.info_date[0..18]?if_exists}
			      <#else>
			      ${exchange.info_date?if_exists}
			      </#if>	
		 </p>
		  <p>失败类型：<b >您撤销换货申请!</b></p>
          <p>理由：${(exchange.seller_reason)?if_exists}</p>
		  <p class="orderDetailP">
		     <a href="/webapp/refundapp!appRefundGoods.action?order_id=${exchange.order_id?if_exists}"><i class="iRed">重新换货申请</i></a>
		     <a href="/webapp/exchange!buylist.action"><i>返回我的换货</i></a> 
		     <br class="clear"/>
		  </p>  
		  </div>
<#elseif (exchange.refund_state)?if_exists=='3'><!--等待买家发货-->
    <!--换货流程-同意换货--> 
    <!--卖家同意您的退货申请，请填写物流信息-->
	<div class="orderDetailTop">
	  <p><b>商家同意您的换货申请，请填写物流信息</b></p>
	  <p class="applyTip">
	    收货人：<b>${(buyeraddr.consignee)?if_exists}</b><br/> 
	    收货地址：${(buyeraddr.area_attr)?if_exists}  ${(buyeraddr.address)?if_exists}<br/>
	    收货手机：${(buyeraddr.cell_phone)?if_exists}<br/>
	    收货电话：${(buyeraddr.phone)?if_exists}
	  </p>
	</div>
	<!--退款-->
	<div class="apply">
	    <div class="applyCont">
	      <p><span>物流信息:</span> <@s.select  name="order_send_mode"  list="sendmodeList" listValue="smode_name" listKey="smode_id" /> <br class="clear"/></p>
	      <p><span>物流单号:</span>
	      <@s.textfield   name="order_send_num" cssClass="applyText"  maxlength="20" />
          <@s.fielderror><@s.param>order_send_num_tip</@s.param></@s.fielderror></td>
	      <br class="clear"/></p>
	    </div>  
	</div>
	<div class="submitDiv">
	 <@s.hidden name="exchange.trade_id" id="exchange_id" value="${(exchange.trade_id)?if_exists}"/>
     <@s.submit value="提 交"  />
	</div>
<#elseif (exchange.refund_state)?if_exists=='4'><!--等待商家确认收货-->
  <!--换货流程-等待商家收货--> 
 <div class="orderDetailTop">
  <p><b>等待商家确认收货</b></p>
  <p>物流公司：
  <#assign zfsend="">
  <#assign zfid="">
     <#list sendmodeList as slist>
         <#if exchange.msend_mode==slist.smode_id>
              <#assign zfsend=slist.smode_name>
              <#assign zfid=slist.en_name>
         </#if>
    </#list>
    <#if (zfsend)?if_exists>
	${(zfsend)?if_exists}
	<#else>	
	 快递
	</#if>
 </p>
  <p>物流单号：${exchange.msend_num?if_exists}</p>
  <p class="orderDetailP">
     <a href="http://m.kuaidi100.com/index_all.html?type=${zfsend?if_exists}&postid=${exchange.msend_num?if_exists}&callbackurl=http://m.epff.cc/webapp/goodsorder!webappAllOrder.action"  ><i class="iRed">点击查看物流信息</i></a>
     <a href="/webapp/exchange!buylist.action"><i>返回我的换货</i></a> 
     <br class="clear"/>
  </p>  
  </div>
 
<#elseif (exchange.refund_state)?if_exists=='6'><!--等待商家发货-->
   <div class="orderDetailTop">
  <p><b>等待商家发货</b></p>
    <p class="orderDetailP">
      <a href="/webapp/exchange!buylist.action"><i class="iRed">返回我的换货</i></a>
     <a href="/webapp/goodsorder!webappAllOrder.action"><i>返回订单中心</i></a> 
     <br class="clear"/>
  </p>  
  </div>      
  <#elseif (exchange.refund_state)?if_exists=='7'><!--等待买家确认收货-->
  <!--换货流程-等待买家收货--> 
  <div class="orderDetailTop">
  <p><b>等待买家确认收货</b></p>
  <p>物流公司：<#assign zfsend1="">
  				<#assign zfid1="">
             <#list sendmodeList as slist>
                 <#if exchange.send_mode==slist.smode_id>
                      <#assign zfsend1=slist.smode_name>
                       <#assign zfid1=slist.en_name>
                 </#if>
            </#list>
            <#if (zfsend1)?if_exists>
        	${(zfsend1)?if_exists}
        	<#else>	
        	 快递
        	</#if>
 </p>
  <p>物流单号：${exchange.send_num?if_exists}</p>
  <p><a href="http://m.kuaidi100.com/index_all.html?type=${zfsend1?if_exists}&postid=${exchange.send_num?if_exists}&callbackurl=http://m.epff.cc/webapp/goodsorder!webappAllOrder.action"  target="_blank" ><b>点击查看物流信息</b></a></p>
  <p class="orderDetailP">
     <a href="/webapp/exchange!mConfirmGoods.action?exchange.trade_id=${exchange.trade_id}"><i class="iRed">确认收货</i></a>
    <a href="/webapp/exchange!nConfirmGoods.action?exchange.trade_id=${exchange.trade_id}"><i>拒绝收货</i></a>
   <br class="clear"/>
  </p>  
    
  </div>
<#elseif (exchange.refund_state)?if_exists=='8'>
	 <!--换货流程-买家拒绝收货--> 
	   <!--换货流程-等待买家收货--> 
	  <div class="orderDetailTop">
	  <p><b>买家拒绝收货</b></p>
	  <p>物流公司：<#assign zfsend1="">
  				<#assign zfid1="">
             <#list sendmodeList as slist>
                 <#if exchange.send_mode==slist.smode_id>
                      <#assign zfsend1=slist.smode_name>
                       <#assign zfid1=slist.en_name>
                 </#if>
            </#list>
            <#if (zfsend1)?if_exists>
        	${(zfsend1)?if_exists}
        	<#else>	
        	 快递
        	</#if>
	 </p>
	  <p>物流单号：${exchange.send_num?if_exists}</p>
	  <p class="orderDetailP">
	     <a href="http://m.kuaidi100.com/index_all.html?type=${zfsend1?if_exists}&postid=${exchange.send_num?if_exists}&callbackurl=http://m.epff.cc/webapp/goodsorder!webappAllOrder.action"  target="_blank" class="mBut">点击查看物流信息</a>
	  </p>  
	     <br class="clear"/>
	  </div>
</#if>
<!--换货基本信息-->
<div class="orderDetailTop">
  <p><b>换货详情</b></p>
  <p>退货编号：<b>${(exchange.return_no)?if_exists}</b></p>
  <p>订单号：${(exchange.order_id)?if_exists}</p>
  <p>提交时间： <#if exchange.info_date?if_exists?length gt 19>
		      ${exchange.info_date[0..18]?if_exists}
		      <#else>
		      ${exchange.info_date?if_exists}
		      </#if></p>
  <p>换货原因：<#list commparaList as com>
			              <#if com.para_value=exchange.refund_type>
			              	${(com.para_key)?if_exists}
			              </#if>
		              </#list></p>
   <p>问题描述：${(exchange.buy_reason)?if_exists}</p>
  <p class="applyTip">
     <#list exchange.img_path?if_exists?split(',') as img>
	   <input type="hidden" value="${img?if_exists}" name="gimg">
	   <#if img!=''> <img src="${img?if_exists}" width='60px' width='60px'></#if>
	 </#list>
  </p>
</div>


<!--订单商品-->
<div class="hasOrder">

 <#list detailList as dlist>
  <!--待收货-->
  <header class="hoDiv">
    <table>
      <tr>
        <td><div class="imgDiv">
        <a href="#"><img src="<#if dlist.list_img!=''>${(dlist.list_img)?if_exists} <#else>${(cfg_nopic)?if_exists}</#if>" width=''></a>
        </div></td>
        <td>
           <p><a href="#">${(dlist.goods_name)?if_exists}</a></p>
           <p>金额：<b>￥<#if dlist.goods_price?if_exists>${(dlist.goods_price*dlist.order_num)?if_exists}<#else>-</#if></b></p>
           <p>数量：<#if dlist.order_num?if_exists>${dlist.order_num}<#else>-</#if></p>
           <p>状态：<#if dlist.orderdetail_state=='1'>
	                      退款中
                <#elseif dlist.orderdetail_state=='2'>	
                 		 退款关闭
                <#elseif dlist.orderdetail_state=='3'>
               			  退款成功
                <#elseif dlist.orderdetail_state=='4'>
                  		退货中
                <#elseif dlist.orderdetail_state=='5'>
                		  退货关闭
                <#elseif dlist.orderdetail_state=='6'>
                 	    退货成功
                <#elseif dlist.orderdetail_state=='7'>
                 		 换货中
                <#elseif dlist.orderdetail_state=='8'>
                  		换货关闭
                <#elseif dlist.orderdetail_state=='9'>
               		     换货成功
               <#elseif dlist.orderdetail_state=='0'>
               		   --
                </#if>
           </p>
        </td>
      </tr>
    </table>
  </header>
  </#list>
</div>


<!--尾部-->
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">
 <@s.hidden name="logistics_query" id="logistics_query_id"/>
 <@s.hidden name="kuai_company" id="kuai_company_id"/>
 </@s.form>
</body>
<script src="/wro/webapp_common.js" type="text/javascript"></script>
<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
<script src="/malltemplate/jiutong/js/refundapp.js" type="text/jscript"></script>
<script src="/malltemplate/jiutong/js/goodsorder.js" type="text/jscript"></script>
<script type="text/javascript" >
  //初始化加载
	$(document).ready(function(){
	 loadArea("${areaIdStr?if_exists}","");
	});
 </script>
</html>
