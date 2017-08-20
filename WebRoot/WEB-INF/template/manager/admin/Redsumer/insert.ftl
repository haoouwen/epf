<html>
  <head>
    <title>添加红包消费码</title>
  </head>
  <body>  
<@s.form action="/admin_Redsumer_insert.action" method="post" validate="true"  id="detailForm">
<div class="postion">
	当前位置：1 > 2 > 3
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		
			 <tr>
	           <td class="table_name">redsumer_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="redsumer.redsumer_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>redsumer.redsumer_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">red_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="redsumer.red_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>redsumer.red_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">use_state<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="redsumer.use_state" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>redsumer.use_state</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">cust_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="redsumer.cust_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>redsumer.cust_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">use_date<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="redsumer.use_date" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>redsumer.use_date</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">order_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="redsumer.order_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>redsumer.order_id</@s.param></@s.fielderror>
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
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Redsumer_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>  
</body>
</html>

