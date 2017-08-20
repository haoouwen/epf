<html>
  <head>
    <title>添加发票</title>
  </head>
  <body>  
<@s.form action="/admin_Orderinvoice_insert.action" method="post" validate="true"  id="detailForm">
<div class="postion">
	当前位置：1 > 2 > 3
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		
			 <tr>
	           <td class="table_name">invoice_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="orderinvoice.invoice_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>orderinvoice.invoice_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">look_up<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="orderinvoice.look_up" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>orderinvoice.look_up</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">p_content<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="orderinvoice.p_content" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>orderinvoice.p_content</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">invoice_type<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="orderinvoice.invoice_type" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>orderinvoice.invoice_type</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">company_name<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="orderinvoice.company_name" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>orderinvoice.company_name</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">identifier<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="orderinvoice.identifier" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>orderinvoice.identifier</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">re_address<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="orderinvoice.re_address" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>orderinvoice.re_address</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">re_phone<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="orderinvoice.re_phone" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>orderinvoice.re_phone</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">bank_name<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="orderinvoice.bank_name" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>orderinvoice.bank_name</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">bank_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="orderinvoice.bank_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>orderinvoice.bank_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">z_content<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="orderinvoice.z_content" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>orderinvoice.z_content</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">ticket_name<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="orderinvoice.ticket_name" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>orderinvoice.ticket_name</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">ticket_cell<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="orderinvoice.ticket_cell" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>orderinvoice.ticket_cell</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">area_attr<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="orderinvoice.area_attr" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>orderinvoice.area_attr</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">address<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="orderinvoice.address" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>orderinvoice.address</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">cust_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="orderinvoice.cust_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>orderinvoice.cust_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">user_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="orderinvoice.user_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>orderinvoice.user_id</@s.param></@s.fielderror>
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
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Orderinvoice_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>  
</body>
</html>

