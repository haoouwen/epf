<html>
  <head>
    <title>添加取消订单理由</title>
  </head>
  <body>  
<@s.form action="/admin_Cancelorder_insert.action" method="post" validate="true"  id="detailForm">
<div class="postion">
	当前位置：1 > 2 > 3
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		
			 <tr>
	           <td class="table_name">trade_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="cancelorder.trade_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>cancelorder.trade_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">buy_cust_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="cancelorder.buy_cust_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>cancelorder.buy_cust_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">buy_user_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="cancelorder.buy_user_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>cancelorder.buy_user_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">order_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="cancelorder.order_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>cancelorder.order_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">buy_reason<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="cancelorder.buy_reason" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>cancelorder.buy_reason</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">buy_date<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="cancelorder.buy_date" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>cancelorder.buy_date</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">sell_cust_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="cancelorder.sell_cust_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>cancelorder.sell_cust_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">order_state<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="cancelorder.order_state" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>cancelorder.order_state</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	        
        </table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
           <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Cancelorder_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>  
</body>
</html>

