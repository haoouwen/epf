<html>
  <head>
    <title>添加记录直通车资金流动信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Expressfundrun_insert.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">trade_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="expressfundrun.trade_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>expressfundrun.trade_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">fundtype<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="expressfundrun.fundtype" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>expressfundrun.fundtype</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">income<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="expressfundrun.income" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>expressfundrun.income</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">pay<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="expressfundrun.pay" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>expressfundrun.pay</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">balance<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="expressfundrun.balance" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>expressfundrun.balance</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">cust_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="expressfundrun.cust_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>expressfundrun.cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">pay_code<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="expressfundrun.pay_code" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>expressfundrun.pay_code</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="fixbuttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Expressfundrun_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

