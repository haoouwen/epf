<html>
  <head>
    <title>退款处理</title>
        <script type="text/javascript" src="/include/admin/js/refundapp.js"></script>
        <link href="/include/admin/css/refund.css" rel="stylesheet" type="text/css"/>
      </head>
  <body>
  
  <@s.form method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
 	当前位置:商城管理 > 售后服务 > 退款处理
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		 <tr>
		        <td class="table_name" style="width:220px;" height="60px;">退款编号:</td>
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
		             	￥${(refundapp.refund_amount)?if_exists}
		             </td>
		           </tr>
 					<tr>
		             <td class="table_name">退款类型:</td>
		             <td>
		             <#list commparaList as com>
			              <#if com.para_value=refundapp.buy_type>
			              	${(com.para_key)?if_exists}
			              </#if>
		              </#list>
		                
		             </td>
		           </tr>
		            <#if refundapp.img_path?if_exists!=null && refundapp.img_path?if_exists!="">
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
		             <td class="table_name">退款原因:</td>
		             <td>
		                  ${(refundapp.buy_reason)?if_exists}
		             </td>
		           </tr>
		           
	               <tr>
		             <td class="table_name">退款状态:</td>
		             <td>
		              <#if (refundapp.refund_state)?if_exists=='0'><font color="red">退款中</font>
				    	<#elseif (refundapp.refund_state)?if_exists=='1'><font color="green">退款成功</font>
				    	<#elseif (refundapp.refund_state)?if_exists=='2'><font color="red">退款失败</font>
				    	<#elseif (refundapp.refund_state)?if_exists=='5'><font color="red">退款关闭</font>
				    </#if>
		             </td>
		           </tr>
	                <tr>
		             <td class="table_name">申请时间:</td>
		             <td>
		              ${(refundapp.buy_date)?if_exists}
		             </td>
		           </tr> 
                    <#if (refundapp.seller_date)?if_exists!=null && (refundapp.seller_date)?if_exists!="">
					 <tr>
		             <td class="table_name">处理时间:</td>
		             <td>
		                   ${refundapp.seller_date?if_exists}
		             </td>
		           </tr>	
                   </#if>
                   <#if (refundapp.seller_state)?if_exists!=null && (refundapp.seller_state)?if_exists!="">
		            <tr>
		             <td class="table_name">处理状态:</td>
		             <td>
		                 <#if refundapp.seller_state=='0'>
		                     同意退款
		                 <#elseif refundapp.seller_state=='1'>
		                    拒接退款
		                 </#if>
		             </td>
		           </tr>	
		           </#if>
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
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Refundapp_refundGoodsList.action','');"/>
   </div>
   <!--操作按钮结束-->
</div>
</@s.form>
</body>
</html>
