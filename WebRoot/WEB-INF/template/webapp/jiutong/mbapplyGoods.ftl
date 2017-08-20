<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}申请退款/退换货</title>

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
 <@s.form action="/webapp/refundapp!orderBuyRefundmentView.action" method="post"  id="tradeForm"  >
<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>申请退款/退换货<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<!--订单商品-->
<div class="hasOrder">
 <div class="orderh2">
         <#assign num=0> 
	     <#list detailList as dlist>
	             <#if (dlist.order_num-dlist.final_apply_num) gt 0>
    	              <#if  dlist.orderdetail_state=='0' || dlist.orderdetail_state=='2' || dlist.orderdetail_state=='5' ||  dlist.orderdetail_state=='8'>
    	                  <#assign num=num+1> 
    	               <#elseif  (dlist.orderdetail_state=='1' || dlist.orderdetail_state=='3' || dlist.orderdetail_state=='4' || dlist.orderdetail_state=='6' || dlist.orderdetail_state=='7' || dlist.orderdetail_state=='9')&& (dlist.final_apply_num!=dlist.order_num)>
               			 <#assign num=num+1> 
                     </#if>
                   </#if>
	     </#list>
	     <#if if_appinfo !=0>
		     <#if num!=0>
	       	   <input type="checkbox" name="checkbox" id="checkall" onclick="getAll('re_goods_id_str')" />全选
	        </#if>
         </#if>
        请先选择要服务的商品
 </div>

  <!--待选服务的商品-->
   <#list detailList as dlist>
  <header class="hoDiv">
    <table>
      <tr>
        <#if if_appinfo !=0>
	        <#if (dlist.order_num-dlist.final_apply_num) gt 0>
	           <#if  (dlist.orderdetail_state=='0' || dlist.orderdetail_state=='2' || dlist.orderdetail_state=='5'||  dlist.orderdetail_state=='8') >
	                <td>
	       			 <input type="checkbox" name="re_goods_id_str" value="${dlist.trade_id?if_exists}"     onclick="getRenM();"/>
	                </td>
	            <#elseif (dlist.orderdetail_state=='1' || dlist.orderdetail_state=='3' || dlist.orderdetail_state=='4' || dlist.orderdetail_state=='6' || dlist.orderdetail_state=='7' || dlist.orderdetail_state=='9')&& (dlist.final_apply_num!=dlist.order_num)>
	                <td>
	       			 <input type="checkbox" name="re_goods_id_str" value="${dlist.trade_id?if_exists}"     onclick="getRenM();"/>
	                </td>
	            <#else>
	           		   <td> -- </td>
	          </#if>
	          <#else>
	               <td> -- </td>
	          </#if>
          <#else>
               <td> -- </td>
          </#if>
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
	         	<a href="#" >
	  				<img src="${(img)?if_exists}" />
	  			</a>
			 <#else>
			 	<a href="#" >
					<img src="${(cfg_nopic)?if_exists}" >
				</a>
			 </#if>	
	        </div>
        </td>
        <td>
           <p><a href="#" >${(dlist.temporary_goodsname)?if_exists}</a></p>
           <p>数量：<#if dlist.order_num?if_exists>${dlist.order_num}<#else>-</#if></p>
           <p>购买价格：<b>￥${(dlist.subtotal)?if_exists}</b></p>
           <p>已申请数量：${(dlist.final_apply_num)?if_exists}</p>
           <p>可申请数量：
               <#if if_appinfo !=0>
                  <#if (dlist.order_num-dlist.final_apply_num) lt 1>
                     <b>0</b>
                 <#else>
                   <input type="text" id="trade_${(dlist.trade_id)?if_exists}" name="apply_num" size='1px' style="height:15px;" onblur="apply_deal('${(dlist.trade_id)?if_exists}','${(dlist.order_num-dlist.final_apply_num)?if_exists}',this.value);" value="${(dlist.order_num-dlist.final_apply_num)?if_exists}"/>
                 </#if>
                 <#else>
                              -- 
                       </#if>
           </p>
            <p>状态： 
               <#if dlist.orderdetail_state=='1'>
	                       			<#if dlist.final_apply_num==dlist.order_num>
	                       			    <span class="mred" >售后中</span>
	                       			 <#else>
	                       			    <font color="#e1292d">部分退款中</font>
	                       			 </#if>
	                        <#elseif dlist.orderdetail_state=='2'>	
	                         		 部分售后
	                        <#elseif dlist.orderdetail_state=='3'>
	                                <span class="mgreen" >
	                                <#if dlist.final_apply_num==dlist.order_num>
	                       			      售后成功
	                       			 <#else>
	                       			     部分售后成功
	                       			 </#if>
	                       			 </span>
	                        <#elseif dlist.orderdetail_state=='4'>
	                              <#if dlist.final_apply_num==dlist.order_num>
	                          		<span class="mred" >售后中</span>
	                              <#else>
	                                 <font color="#e1292d">部分退货中</font> 
	                              </#if>		
	                        <#elseif dlist.orderdetail_state=='5'>
	                        		  部分售后
	                        <#elseif dlist.orderdetail_state=='6'>
	                         	    <span class="mgreen">
	                         	       <#if dlist.final_apply_num==dlist.order_num>
	                         	       售后成功
	                         	       <#else>
	                         	       部分售后成功
	                         	       </#if>
	                         	    </span>
	                        <#elseif dlist.orderdetail_state=='7'>
	                        　　<#if dlist.final_apply_num==dlist.order_num>
	                         		 　<span class="mred" >售后中</span>
	                         	  <#else>
	                         	  　　<font color="#e1292d">部分换货中</font>
	                         	  </#if>
	                        <#elseif dlist.orderdetail_state=='8'>
	                          		部分售后
	                        <#elseif dlist.orderdetail_state=='9'>
	                       		    <span class="mgreen" >  
	                       		     <#if dlist.final_apply_num==dlist.order_num>
	                       		        售后成功
	                       		     <#else>
	                       		        部分售后成功
	                       		     </#if>
	                       		    </span>
	                       		    
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
<div class="submitDiv">
<#if if_appinfo !=0>
			<#if num !=0>
			<@s.hidden name="refund_goods_id_str" id="re_goods_id_str" />
			<@s.hidden name="order_id" id="g_order_id"  value="${(goodsorder.order_id)?if_exists}"/>
			<@s.hidden name="goodsorder.order_state" id="order_state" />
			<@s.hidden name="is_return_str" id="is_return" value="0" />
			<@s.hidden name="apply_num_str" id="apply_num_str"/>
			<@s.hidden name="back_goods_num" id="back_goods_num"/>
			<a href="###" onclick="submitapprefund();"  > 提交申请</a>
			 <#else>
			<span class="pcOrange" >*抱歉,您的订单商品暂时无法申请任何售后服务!</span>
			 </#if>
  <#else>
	                <span class="pcOrange" >*抱歉,您的订单商品已经超过售后有效期,暂时无法申请任何售后服务!</span>
             </#if>
</div>
<!--尾部-->
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">
 </@s.form>
</body>
<script src="/wro/webapp_common.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/refundapp.js" type="text/jscript"></script>
 <script type="text/javascript" >
  //初始化加载
	$(document).ready(function(){
	 selectRadioGoods();
	});
 </script>
</html>
