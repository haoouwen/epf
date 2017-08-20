<html>
  <head>
    <title>添加代购汇账单</title>
  </head>
  <body>  
<@s.form action="/admin_Fepbill_insert.action" method="post" validate="true"  id="detailForm">
<div class="postion">
	当前位置：1 > 2 > 3
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		
			 <tr>
	           <td class="table_name">fepbill_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="fepbill.fepbill_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>fepbill.fepbill_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">purchasingtotalamount<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="fepbill.purchasingtotalamount" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>fepbill.purchasingtotalamount</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">orderids<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="fepbill.orderids" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>fepbill.orderids</@s.param></@s.fielderror>
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
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Fepbill_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>  
</body>
</html>

