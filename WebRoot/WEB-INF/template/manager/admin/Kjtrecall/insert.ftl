<html>
  <head>
    <title>添加跨境通回调表</title>
  </head>
  <body>  
<@s.form action="/admin_Kjtrecall_insert.action" method="post" validate="true"  id="detailForm">
<div class="postion">
	当前位置：1 > 2 > 3
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		
			 <tr>
	           <td class="table_name">sosysno<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="kjtrecall.sosysno" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>kjtrecall.sosysno</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">order_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="kjtrecall.order_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>kjtrecall.order_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">tatal_amount<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="kjtrecall.tatal_amount" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>kjtrecall.tatal_amount</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">taxes<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="kjtrecall.taxes" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>kjtrecall.taxes</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">ship_free<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="kjtrecall.ship_free" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>kjtrecall.ship_free</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">merchantorderid<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="kjtrecall.merchantorderid" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>kjtrecall.merchantorderid</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">productamount<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="kjtrecall.productamount" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>kjtrecall.productamount</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">taxamount<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="kjtrecall.taxamount" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>kjtrecall.taxamount</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">shippingamount<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="kjtrecall.shippingamount" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>kjtrecall.shippingamount</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">sostatus<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="kjtrecall.sostatus" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>kjtrecall.sostatus</@s.param></@s.fielderror>
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
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Kjtrecall_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>  
</body>
</html>

