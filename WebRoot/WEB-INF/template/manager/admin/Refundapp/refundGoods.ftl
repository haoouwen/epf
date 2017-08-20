<html>
  <head>
    <title>退款处理</title>
        <script type="text/javascript" src="/include/admin/js/refundapp.js"></script>
        <link href="/include/admin/css/refund.css" rel="stylesheet" type="text/css"/>
      </head>
  <body>
  
  <@s.form  method="post" validate="true" id="modiy_form">
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
		             	￥${refundapp.refund_amount?if_exists}
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
		           <tr>
		             <td class="table_name">退款理由:</td>
		             <td>
		              ${(refundapp.buy_reason)?if_exists}
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
		             <td class="table_name">申请时间:</td>
		             <td>
		              ${(refundapp.buy_date)?if_exists}
		             </td>
		           </tr> 
		           
		                  
					 <tr>
		             <td class="table_name">处理时间:</td>
		             <td>
		             <#if (refundapp.seller_date)?if_exists>${refundapp.seller_date?if_exists}<#else>-</#if>
		             </td>
		           </tr>	
		          
	               <tr>
		             <td class="table_name">退款状态:</td>
		             <td>
		              <#if (refundapp.refund_state)?if_exists=='0'><font color="red">退款中</font>
				    	<#elseif (refundapp.refund_state)?if_exists=='1'><font color="green">退款成功</font>
				    	<#elseif (refundapp.refund_state)?if_exists=='2'><font color="red">退款失败</font>
				    	<#elseif (refundapp.refund_state)?if_exists=='5'><font color="red">退款关闭</font>
				    	<#elseif (refundapp.refund_state)?if_exists=='3'><font color="red">等待会员发货</font>
				    	<#elseif (refundapp.refund_state)?if_exists=='4'><font color="red">会员已发货</font>
				    </#if>
		             </td>
		           </tr>
		           
		            <tr>
		             <td class="table_name">处理意见:</td>
		             <td>
		                  <@s.radio name="refundapp.seller_state" list=r"#{'0':'同意','1':'拒绝'}"  onclick="getSellerState(this.value);"/>
		             </td>
		           </tr>	
		           
		            <tr class="rejectReason" style="display:none;">
		             <td class="table_name">拒绝理由:</td>
		             <td>
		         		  <@s.textarea name="refundapp.seller_reason" id="seller_reason" cssClass="txtinput" cssStyle="width:300px;height:100px;" />
		             </td>
		           </tr>	    
		           
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
       <@s.token/>  
       ${listSearchHiddenField?if_exists}
       <span id="agreeState" style="display:none;"><input type="button" value="确定" onclick="handleRefundGoods();"/></span>
       <span id="disAgreeState" style="display:none;"><input type="button" value="确定" onclick="handleRefundGoods();"/></span>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Refundapp_refundGoodsList.action','');"/>
   </div>
   <!--操作按钮结束-->
</div>

 </div>	
 
<@s.hidden name="refundapp.trade_id"/>
<@s.hidden name="order_id_str" value="${refundapp.order_id}" id="order_id_str"/>
<@s.hidden name="refund_no" value="${refundapp.return_no}"/>
<@s.hidden name="refund_type" value="2"/>
<@s.hidden name="front_back_tip"  id="front_back_tip" value="0"/>
<#assign pay_type="">
<#list paymentList as payment> 
   <#if payment.pay_id==goodsorder.pay_id> 
       <#assign pay_type="${(payment.pay_code)?if_exists}">
   </#if>
</#list>
<@s.hidden name="pay_type" id="pay_type" value="${pay_type?if_exists}"/>

 
</@s.form>

	   
<!--修改运单号区域结束-->  
<div style="display:none;"  id="setPay"  >
	        <table  style="text-align: center;">
	          <tr>
	           	<td style="color:#EA9359;padding-top:10px;">退款完成前请不要关闭此窗口, 退款后请点击下面按钮</td>
	          </tr>
	           <tr>
	           	<td style="padding-top:25px;font-size:14px;text-align: center;">
	           	<a href="admin_Refundapp_refundGoodsList.action">退款成功</a>&nbsp;&nbsp;
	           	<a href="admin_Refundapp_refundGoodsList.action">关闭返回</a></td>
	          </tr>
	       </table>
</div> 
</body>
</html>

