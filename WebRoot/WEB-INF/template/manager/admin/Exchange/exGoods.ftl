<html>
  <head>
    <title>换货处理</title>
        <script type="text/javascript" src="/include/admin/js/exchange.js"></script>
        <link href="/include/admin/css/exchange.css" rel="stylesheet" type="text/css"/>
      </head>
  <body>
  
  <@s.form method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
 	当前位置:商城管理 > 售后服务 > 换货处理
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
		                     	<img src="<#if img_path!=''>${img_path?if_exists}<#else>${(cfg_nopic)?if_exists}</#if>" width="60px" height="60px" /></a>
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
		           
		            <#if exchange.img_path!=null && exchange.img_path!="">
	              <tr>
		              <td class="table_name">凭证：</td>
		              <td> 
		              		<ul id="show_pic" class="show_pic">
		             	    	<#list exchange.img_path?if_exists?split(",") as img>
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
		             <td class="table_name">换货状态:</td>
		             <td>
		              <#if (exchange.refund_state)?if_exists=='0'><font color="red">申请换货中</font>
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
		          
		            <tr>
		             <td class="table_name">处理意见:</td>
		             <td>
		                <#if (exchange.seller_state)?if_exists=="" || (exchange.seller_state)?if_exists==null || (exchange.seller_state)?if_exists=="1">
		                  <@s.radio name="exchange.seller_state" list=r"#{'0':'同意换货','1':'拒绝换货'}"  onclick="getSellerState(this.value);"/>
		                <#else>
		                  <#if (exchange.seller_state)?if_exists==0>
		                     <font color="green">同意换货</font> 
		                  <#else>
		                     <font color="red">拒接换货</font>
		                  </#if>
		                </#if>
		                <#if (exchange.refund_state)?if_exists=='4' >
		               			<@s.hidden name="exchange.refund_state" id="refund_state"/>
		                </#if>
		                
		             </td>
		           </tr>	
		            <#if (exchange.refund_state)?if_exists=='8' >
		            <tr>
		             <td class="table_name">操作:</td>
		             <td>
		               	<@s.radio name="exchange.refund_state" list=r"#{'a':'换货成功','b':'换货失败'}"  onclick="getExState(this.value);"/>
		                
		             </td>
		           </tr>	
		           </#if>
		            <tr class="rejectReason" style="display:none;">
		             <td class="table_name">拒绝理由:</td>
		             <td>
		             	  <@s.hidden name="exchange.seller_reason" id="newseller_reason"/>
		         		  <@s.textarea  id="seller_reason" cssClass="txtinput" cssStyle="width:300px;height:100px;" />
		             </td>
		           </tr>	    
		           
		           
		           
		           
		           <#if (exchange.refund_state)?if_exists=='6'><!--等待卖家发货-->
	        	   <tr>
	            	 <td class="table_name">商家发货:</td>
		             <td>
					             
	                        <table width="100%" cellpadding="0" cellspacing="0" >
	                            <tr>
	                              <td class="table_name" >收货人</td>
	                              <td>
	                              ${(exchange.mconsignee)?if_exists}
	                              </td>
	                            </tr>
	                            <tr>
	                              <td class="table_name">收货地址</td>
	                              <td>
	                              ${(exchange.marea_attr)?if_exists}  ${(exchange.msell_address)?if_exists}
	                              </td>
	                            </tr>
	                            <tr>
	                              <td class="table_name">手机</td>
	                              <td>
	                             ${(exchange.mmobile)?if_exists}
	                              </td>
	                            </tr>
	                             <tr>
	                              <td class="table_name">物流公司</td>
	                              <td>
	                              <@s.select  name="order_send_mode"  list="sendmodeList" listValue="smode_name" listKey="smode_id" /> 
	                              </td>
	                            </tr>
	                            <tr>
	                              <td class="table_name">物流单号</td>
	                              <td>
	                               <@s.textfield   name="order_send_num" cssClass="txtinput"  maxlength="20" />
	                                
	                               <@s.fielderror><@s.param>order_send_num_tip</@s.param></@s.fielderror></td>
	                            </tr>
	                            <tr>
	                              <td width="12%">&nbsp;</td>
	                              <td>
	                                <@s.submit value="提 交" cssClass="submitbut" onclick="sellDelivery();"/>
	                              </td>
	                            </tr>
	                          </table>
			             </td>
			           </tr>
	         	  </#if>
		           
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
       <@s.token/>  
       ${listSearchHiddenField?if_exists}
       <#if (exchange.refund_state)?if_exists=='4'>
        <input type="button" value="确定收货" onclick="linkToInfo('/admin_Exchange_confirmGoods.action','exchange.trade_id=${exchange.trade_id}')");"/>
         <input type="button" value="换货失败" onclick="fgoOver();"/>
       <#elseif (exchange.refund_state)?if_exists=='0' && (exchange.seller_state)?if_exists=='0'>
        <input type="button" value="发送收货地址" onclick="agreeChoosedAddress();"/>
       <#elseif (exchange.refund_state)?if_exists=='0' && ((exchange.seller_state)?if_exists=="" || (exchange.seller_state)?if_exists==nul|| (exchange.seller_state)?if_exists=="1")>
        <input type="button" value="确定" onclick="handleReturnGoods();"/>
          <#elseif (exchange.refund_state)?if_exists=="8">
           <input type="button" value="确定" onclick="goOver();"/>
       </#if>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Exchange_exGoodsList.action','');"/>
   </div>
   <!--操作按钮结束-->
</div>
 <@s.hidden name="exchange.trade_id"/>
 <@s.hidden name="exchange.order_id"/>
</@s.form>
<!--修改运单号区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Exchange_confirmGoods.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td align="center" colspan="2" style="border:0px;">
			    <@s.hidden name="exchange.trade_id"/>
			    <@s.hidden name="exchange.seller_cust_id" id="seller_cust_id"/>
				<input type="submit" id="confirmBtn" name="confirmBtn"  value="确定" />&nbsp;
			   <input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			</td>
	   </tr>
		</table>
		</@s.form>
	</div>		   
<!--修改运单号区域结束-->  
</body>
</html>
