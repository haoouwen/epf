<html>
<head>
<title>查看充值记录</title>
</head>
<body>
	
<@s.form action="/bmall_Fundrecharge_update.action"  method="post"  name="formshopcongif" validate="true">
<!--右边-->
<div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>查看充值记录</span></h2>
        <!----> 
            <div class="cancelDiv">
	            <!---->
	            <div class="opeDiv padDiv">
	              <table width="100%" cellpadding="0" cellspacing="0" class="detTab">
	                    <tr><th width="15%">订单号：</th>
	                    <td width="85%">
			        	 ${fundrecharge.order_id?if_exists}
			            </td></tr>
			          
			            <tr><th width="15%">银行交易订单号：</th>
			            <td width="85%">
			        	 ${fundrecharge.bank_order_id?if_exists}<img class="ltip" src="/include/common/images/light.gif" alt="通过在线支付,系统自动获取银行交易的订单号">
			            </td></tr>
			              
			            <tr><th width="15%">充值余额：</font></th>
			        	 <td width="85%">
			             <@s.label name="fundrecharge.fund_num" cssClass="txtinput"/>        	            
			             </td>
			            </tr>  
			            
			            <tr><th width="15%" >支付平台：</th>
			            <td width="85%">
			        	  <#list paymentList as py>
			        	          <#if py.pay_code==fundrecharge.payplat>
			        	              ${py.pay_name}
			        	          </#if>
			        	     </#list>
			            </td></tr>  
			            
			            
			            <tr><th width="15%" >支付时间：</th>
			            <td width="85%">
			        	<@s.label name="fundrecharge.pay_date" cssClass="txtinput"/>
			            </td></tr>
			            
			            <tr><th width="15%" >审核状态：</th>
			            <td width="85%">
			        	<#if fundrecharge.order_state?if_exists=='0'>未审核
					    <#elseif fundrecharge.order_state?if_exists=='1'>已审核
					    <#elseif fundrecharge.order_state?if_exists=='2'>作废</#if>
			            </td></tr> 
			            
			  			<tr><th width="15%" >备注：</th>
			  			<td width="85%">
			        	 <#if fundrecharge.remark?if_exists!=null && fundrecharge.remark?if_exists!=''>
						    		 ${fundrecharge.remark?if_exists}
						    	<#else>
						    	 	-
					    	</#if>
			            </td></tr>
			             <tr><th>.</th>
		                <td>
		              		 <@s.token/>
		             		 <input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Fundrecharge_list.action','');"/>
		                </td></tr>
	                
	              </table>
	            </div>
          </div>
   </div>
</div>
  <div class="clear"></div>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>  
</@s.form>
</body>
</html>

