<html>
  <head>
    <title>退货详情</title>
        <script type="text/javascript" src="/include/admin/js/refundapp.js"></script>
        <link href="/include/admin/css/refund.css" rel="stylesheet" type="text/css"/>
      </head>
  <body>
  
  <@s.form action="/admin_Refundapp_update.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
 	当前位置:商城管理 > 售后服务 > 退货详情
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		 <tr>
		        <td class="table_name" style="width:220px;" height="60px;">退货编号:</td>
	             <td>
	                ${(refundapp.return_no)?if_exists}
	             </td>
	             </tr>
	              <tr>
		             <td class="table_name">会员名称:</td>
		             <td>
		                ${(buy_name)?if_exists}
		             </td>
		           </tr>
	             <tr>
	             <td class="table_name" style="width:220px;" height="60px;">订单号:</td>
	             <td>
	                ${(refundapp.order_id)?if_exists}
	             </td>
	           </tr>
	            <tr>
		             <td class="table_name">商品信息:</td>
		             <td>
		                <#list detailList as detail>
		                <#if ((detail.order_id)?if_exists)==((refundapp.order_id)?if_exists)>
	                 	 <#assign img_path="${( detail.temporary_goodsimg)?if_exists}">   
	                     <div class="reUsedImg">
		                     <a href="/mall-goodsdetail-${detail.goods_id?if_exists}.html" title="${detail.temporary_goodsname?if_exists}">
		                     	<img src="<#if img_path!=''>${img_path?if_exists}<#else>${(cfg_nopic)?if_exists}</#if>"/></a>
		                     </a>
	                       </div>
	                      </#if>
		      		   </#list>
		             </td>
		           </tr>
		            <tr>
		             <td class="table_name">退款金额:</td>
		             <td>
		             	￥${refundapp.refund_amount?if_exists}
		             </td>
		           </tr>
 					<tr>
		             <td class="table_name">退货类型:</td>
		             <td>
		             <#list commparaList as com>
			              <#if com.para_value=refundapp.buy_type>
			              	${(com.para_key)?if_exists}
			              </#if>
		              </#list>
		                
		             </td>
		           </tr>
		            <#if refundapp.img_path!=null && refundapp.img_path!="">
	              <tr>
		              <td class="table_name">凭证：</td>
		              <td> 
		              		<ul id="show_pic" class="show_pic">
		             	    	<#list refundapp.img_path?if_exists?split(",") as img>
			             	   		<li>
				             	   		<input type="hidden" value="${img?if_exists}" name="gimg">
				             	   		<a href="${img?if_exists}" class="group cboxElement">
				             	   			<img src="${img?if_exists}"></a>
			             	   		</li>
		             	   		</#list>
	             	   		</ul>
		              </td>
	              </tr>
	              </#if>  	           
		           <tr>
		             <td class="table_name">退货原因:</td>
		             <td>
		              ${(refundapp.buy_reason)?if_exists}
		             </td>
		           </tr>
	                <tr>
		             <td class="table_name">申请时间:</td>
		             <td>
		              ${(refundapp.info_date)?if_exists}
		             </td>
		           </tr>
		           <#if (refundapp.send_mode)?if_exists!=null && (refundapp.send_mode)?if_exists!="">
	              <tr>
		              <td class="table_name">物流信息：</td>
		              <td> 
		              		快递名称：	
		              	<#list sendmodeList as sendmode>
			              <#if sendmode.smode_id=refundapp.send_mode>
			              	   ${(sendmode.smode_name)?if_exists}
			              </#if>
		                </#list>
		              		 &nbsp;&nbsp;运单号：${(refundapp.send_num)?if_exists}
		              </td>
	              </tr>
	              </#if>          
                     <tr>
		             <td class="table_name">处理状态:</td>
		             <td>
		             <#if (refundapp.seller_state)?if_exists>
				    	<#if (refundapp.seller_state)?if_exists=='0'>
				    	<font color="green">同意退货</font>
				    	<#elseif (refundapp.seller_state)?if_exists=='1'>
				    	<font color="red">拒绝退货</font>
				    	</#if>
			    	<#else>-</#if>
		             </td>
		           </tr>	 
					 <tr>
		             <td class="table_name">处理时间:</td>
		             <td>
		             <#if (refundapp.seller_date)?if_exists>${refundapp.seller_date?if_exists}<#else>-</#if>
		             </td>
		           </tr>	
	              
	               <tr>
		             <td class="table_name">退货状态:</td>
		             <td>
		              <#if (refundapp.refund_state)?if_exists=='0'><font color="red">退货中</font>
				    	<#elseif (refundapp.refund_state)?if_exists=='1'><font color="green">退货成功</font>
				    	<#elseif (refundapp.refund_state)?if_exists=='2'><font color="red">退货失败</font>
				    	<#elseif (refundapp.refund_state)?if_exists=='5'><font color="red">退货关闭</font>
				    	<#elseif (refundapp.refund_state)?if_exists=='3'><font color="red">等待会员发货</font>
				    	<#elseif (refundapp.refund_state)?if_exists=='4'><font color="red">会员已发货</font>
				    </#if>
		             </td>
		           </tr>
		           <#if refundapp.seller_state=='1'>
		            <tr>
		             <td class="table_name">拒绝理由:</td>
		             <td>
		         		  ${(refundapp.seller_reason)?if_exists}
		             </td>
		           </tr>	    
		           </#if>
		 
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Refundapp_returnGoodsList.action','');"/>
   </div>
   <!--操作按钮结束-->
</div>
 <@s.hidden name="refundapp.trade_id"/>
</@s.form>
</body>
</html>

