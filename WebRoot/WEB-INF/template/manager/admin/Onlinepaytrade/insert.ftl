<html>
  <head>
    <title>添加记录网银支付流水信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Onlinepaytrade_insert.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">trade_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="onlinepaytrade.trade_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>onlinepaytrade.trade_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">cust_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="onlinepaytrade.cust_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>onlinepaytrade.cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">online_amount<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="onlinepaytrade.online_amount" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>onlinepaytrade.online_amount</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">circlegold_amount<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="onlinepaytrade.circlegold_amount" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>onlinepaytrade.circlegold_amount</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">in_date<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="onlinepaytrade.in_date" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>onlinepaytrade.in_date</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">state<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="onlinepaytrade.state" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>onlinepaytrade.state</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">pay_type<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="onlinepaytrade.pay_type" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>onlinepaytrade.pay_type</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.token/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Onlinepaytrade_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

