<html>
  <head>
    <title>添加审核余额调整</title>
  </head>
  <body>  
<@s.form action="/admin_Memberfundrun_insert.action" method="post" validate="true"  id="detailForm">
<div class="postion">
	当前位置：1 > 2 > 3
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		
			 <tr>
	           <td class="table_name">fund_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="memberfundrun.fund_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>memberfundrun.fund_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">cust_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="memberfundrun.cust_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>memberfundrun.cust_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">fund_num<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="memberfundrun.fund_num" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>memberfundrun.fund_num</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">fund_type<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="memberfundrun.fund_type" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>memberfundrun.fund_type</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">info_state<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="memberfundrun.info_state" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>memberfundrun.info_state</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">reason<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="memberfundrun.reason" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>memberfundrun.reason</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">auditreason<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="memberfundrun.auditreason" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>memberfundrun.auditreason</@s.param></@s.fielderror>
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
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Memberfundrun_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>  
</body>
</html>

