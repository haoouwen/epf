<html>
  <head>
    <title>换货详情</title>
        <script type="text/javascript" src="/include/admin/js/exchange.js"></script>
        <link href="/include/admin/css/exchange.css" rel="stylesheet" type="text/css"/>
      </head>
  <body>
  
  <@s.form action="/admin_Exchange_update.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
 	当前位置:商城管理 > 售后服务 > 换货详情
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		 <tr>
		        <td class="table_name" style="width:220px;" height="60px;">换货编号:</td>
	             <td>
	                ${(exchange.return_no)?if_exists}
	             </td>
	             </tr>
	              <tr>
		             <td class="table_name">会员名称:</td>
		             <td>
		                ${(buy_cust_name_s)?if_exists}
		             </td>
		           </tr>
	             <tr>
	             <td class="table_name" style="width:220px;" height="60px;">订单号:</td>
	             <td>
	                ${(exchange.order_id)?if_exists}
	             </td>
	           </tr>
	            <tr>
		             <td class="table_name">商品信息:</td>
		             <td>
		                <#list detailList as detail>
		                <#if ((detail.order_id)?if_exists)==((exchange.order_id)?if_exists)>
	                 	 <#assign img_path="${( detail.temporary_goodsimg)?if_exists}">   
	                     <div class="reUsedImg">
		                     <a href="/mall-goodsdetail-${detail.goods_id?if_exists}.html" title="${detail.temporary_goodsname?if_exists}">
		                     	<img src="<#if img_path!=''>${img_path?if_exists}<#else>${(cfg_nopic)?if_exists}</#if>" width="200px" height="200px" /></a>
		                     </a>
	                       </div>
	                      </#if>
		      		   </#list>
		             </td>
		           </tr>
		            	           
		           <tr>
		             <td class="table_name">换货理由:</td>
		             <td>
		              ${(exchange.buy_reason)?if_exists}
		             </td>
		           </tr>
	                <tr>
		             <td class="table_name">申请时间:</td>
		             <td>
		             <#if exchange.buy_date?if_exists?length gt 19>
			      ${exchange.buy_date[0..18]?if_exists}
			      <#else>
			      ${exchange.buy_date?if_exists}
			      </#if>
		             </td>
		           </tr>
		           <#if (exchange.msend_mode)?if_exists!=null && (exchange.msend_mode)?if_exists!="">
	              <tr>
		              <td class="table_name">物流信息：</td>
		              <td> 
		              		快递名称：${(sendmode.send_name)?if_exists} &nbsp;&nbsp;运单号：${(exchange.msend_num)?if_exists}
		              </td>
	              </tr>
	              </#if>          
                     <tr>
		             <td class="table_name">处理状态:</td>
		             <td>
		             <#if (exchange.seller_state)?if_exists>
				    	<#if (exchange.seller_state)?if_exists=='0'>
				    	<font color="green">同意退货</font>
				    	<#elseif (exchange.seller_state)?if_exists=='1'>
				    	<font color="red">拒绝退货</font>
				    	</#if>
			    	<#else>-</#if>
		             </td>
		           </tr>	 
					 <tr>
		             <td class="table_name">处理时间:</td>
		             <td>
		                 <#if exchange.seller_date?if_exists?length gt 19>
					      ${exchange.seller_date[0..18]?if_exists}
					      <#else>
					      ${exchange.seller_date?if_exists}
					      </#if>	
		             </td>
		           </tr>	
		           
		          <#if exchange.img_path!=null && exchange.img_path!="">
	              <tr>
		              <td class="table_name">凭证：</td>
		              <td> 
		              		<ul id="show_pic" class="show_pic">
		             	    	<#list exchange.img_path?if_exists?split(",") as img>
			             	   		<li>
				             	   		<input type="hidden" value="${img?if_exists}" name="gimg">
				             	   		<a href="${img?if_exists}" class="group cboxElement">
				             	   			<img src="${img?if_exists}" width="200px" height="200px"></a>
			             	   		</li>
		             	   		</#list>
	             	   		</ul>
		              </td>
	              </tr>
	              </#if>  
	              
	               <tr>
		             <td class="table_name">换货状态:</td>
		             <td>
		              <#if (exchange.refund_state)?if_exists=='0'><font color="red">换货中</font>
				    	<#elseif (exchange.refund_state)?if_exists=='1'><font color="green">换货成功</font>
				    	<#elseif (exchange.refund_state)?if_exists=='2'><font color="red">换货失败</font>
				    	<#elseif (exchange.refund_state)?if_exists=='5'><font color="red">换货关闭</font>
				    	<#elseif (exchange.refund_state)?if_exists=='3'><font color="red">等待会员发货</font>
				    	<#elseif (exchange.refund_state)?if_exists=='4'><font color="red">等待商家确认收货</font>
			    	   	<#elseif (exchange.refund_state)?if_exists=='6'><font color="red">等待商家发货</font>
			    		<#elseif (exchange.refund_state)?if_exists=='7'><font color="red">等待会员确认收货</font>
			    		<#elseif (exchange.refund_state)?if_exists=='8'><font color="red">会员拒绝收货</font>
			    		<#elseif (exchange.refund_state)?if_exists=='9'><font color="red">换货失败</font>
			    		<#elseif (exchange.refund_state)?if_exists=='a'><font color="red">换货成功</font>
			    		<#elseif (exchange.refund_state)?if_exists=='b'><font color="red">换货失败</font>
			    		
				    </#if>
		             </td>
		           </tr>
		           <#if exchange.seller_state=='1' || (exchange.refund_state)?if_exists=='9' || (exchange.refund_state)?if_exists=='b'>
		            <tr>
		             <td class="table_name">拒绝理由:</td>
		             <td>
		         		  ${(exchange.seller_reason)?if_exists}
		             </td>
		           </tr>	    
		           </#if>
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Exchange_exGoodsList.action','');"/>
   </div>
   <!--操作按钮结束-->
</div>
 <@s.hidden name="exchange.trade_id"/>
</@s.form>
</body>
</html>

