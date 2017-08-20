<html>
  <head>
    <title>添加换货</title>
  </head>
  <body>  
<@s.form action="/admin_Exchange_insert.action" method="post" validate="true"  id="detailForm">
<div class="postion">
	当前位置：1 > 2 > 3
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		
			 <tr>
	           <td class="table_name">trade_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.trade_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.trade_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">buy_cust_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.buy_cust_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.buy_cust_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">buy_user_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.buy_user_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.buy_user_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">order_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.order_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.order_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">buy_reason<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.buy_reason" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.buy_reason</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">buy_date<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.buy_date" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.buy_date</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">seller_state<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.seller_state" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.seller_state</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">seller_date<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.seller_date" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.seller_date</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">seller_reason<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.seller_reason" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.seller_reason</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">img_path<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.img_path" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.img_path</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">info_date<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.info_date" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.info_date</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">consignee<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.consignee" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.consignee</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">mconsignee<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.mconsignee" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.mconsignee</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">area_attr<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.area_attr" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.area_attr</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">marea_attr<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.marea_attr" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.marea_attr</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">sell_address<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.sell_address" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.sell_address</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">buy_address<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.buy_address" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.buy_address</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">mobile<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.mobile" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.mobile</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">mmobile<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.mmobile" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.mmobile</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">send_mode<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.send_mode" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.send_mode</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">msend_mode<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.msend_mode" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.msend_mode</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">send_time<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.send_time" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.send_time</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">msend_time<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.msend_time" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.msend_time</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">sure_time<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.sure_time" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.sure_time</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">msure_time<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.msure_time" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.msure_time</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">sell_remark<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.sell_remark" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.sell_remark</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">buy_remark<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.buy_remark" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.buy_remark</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">send_num<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.send_num" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.send_num</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">msend_num<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.msend_num" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.msend_num</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">deny_num<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.deny_num" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.deny_num</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">return_no<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.return_no" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.return_no</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">detail_id_str<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.detail_id_str" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.detail_id_str</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">refund_state<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.refund_state" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.refund_state</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">refund_type<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="exchange.refund_type" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>exchange.refund_type</@s.param></@s.fielderror>
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
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Exchange_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>  
</body>
</html>

