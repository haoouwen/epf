<html>
  <head>
    <title>退货处理</title>
        <script type="text/javascript" src="/include/admin/js/refundapp.js"></script>
        <link href="/include/admin/css/refund.css" rel="stylesheet" type="text/css"/>
      </head>
  <body>
  
  <@s.form method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
 	当前位置:商城管理 > 售后服务 > 退货处理
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
		                     <a href="/mall-goodsdetail-${detail.goods_id?if_exists}.html" title="${detail.temporary_goodsname?if_exists}">
		                     	<img src="<#if img_path!=''>${img_path?if_exists}<#else>${(cfg_nopic)?if_exists}</#if>"  width="30px" height="30px"/></a>
		                     </a>
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
		           <tr>
		             <td class="table_name">退货理由:</td>
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
	              </#if> 
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
		             <td class="table_name">处理时间:</td>
		             <td>
		             <#if (refundapp.seller_date)?if_exists>${refundapp.seller_date?if_exists}<#else>-</#if>
		             </td>
		           </tr>	
		          
		            <tr>
		             <td class="table_name">处理意见:</td>
		             <td>
		                <#if (refundapp.seller_state)?if_exists=="" || (refundapp.seller_state)?if_exists==null>
		                  <@s.radio name="refundapp.seller_state" list=r"#{'0':'同意退货','1':'拒绝退货'}"  onclick="getSellerState(this.value);"/>
		                <#else>
		                  <#if (refundapp.seller_state)?if_exists==0>
		                     <font color="green">同意退货</font> 
		                  <#else>
		                     <font color="red">拒接退货</font>
		                  </#if>
		                </#if>
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
       <#if (refundapp.refund_state)?if_exists=='4'>
        <input type="button" value="确定收货" onclick="showBackDIV('searchDiv','300px','220px');"/>
       <#elseif (refundapp.refund_state)?if_exists=='0' && (refundapp.seller_state)?if_exists=='0'>
        <input type="button" value="发送收货地址" onclick="agreeChoosedAddress();"/>
       <#elseif (refundapp.refund_state)?if_exists=='0' && ((refundapp.seller_state)?if_exists=="" || (refundapp.seller_state)?if_exists==null)>
        <input type="button" value="确定" onclick="handleReturnGoods();"/>
       </#if>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Refundapp_returnGoodsList.action','');"/>
   </div>
   <!--操作按钮结束-->
</div>
 <@s.hidden name="refundapp.trade_id"/>
 <@s.hidden name="refundapp.order_id" id="order_id_str"/>
 <@s.hidden name="front_back_tip"  id="front_back_tip" value="0"/>
 <#assign pay_type="">
<#list paymentList as payment> 
   <#if payment.pay_id==goodsorder.pay_id> 
       <#assign pay_type="${(payment.pay_code)?if_exists}">
   </#if>
</#list>
<@s.hidden name="pay_type" id="pay_type" value="${pay_type?if_exists}"/>
</@s.form>
<!--修改运单号区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Refundapp_agreeRefundGoods.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
	 <tr>
			<td class="searchDiv_td">退款金额：</td> 
			<td>￥${refundapp.refund_amount?if_exists}</td>
	 </tr>
		<tr>
			<td align="center" colspan="2" style="border:0px;">
			    <@s.hidden name="refundapp.trade_id"/>
			    <@s.hidden name="refundapp.is_return"/>
				<input type="button" id="confirmBtn" name="confirmBtn" onclick="confirmReturnGoods();"  value="确定" />&nbsp;
			    <input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			</td>
	   </tr>
		</table>
		</@s.form>
	</div>		   
<!--修改运单号区域结束--> 

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
