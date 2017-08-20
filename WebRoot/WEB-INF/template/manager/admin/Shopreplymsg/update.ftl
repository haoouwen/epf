<html>
  <head>
    <title>修改店铺留言表</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Shopreplymsg_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">trade_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="shopreplymsg.trade_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>shopreplymsg.trade_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">buy_cust_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="shopreplymsg.buy_cust_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>shopreplymsg.buy_cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">reply_in_date<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="shopreplymsg.reply_in_date" cssClass="txtinput" onkeyup="checkLength(this,25000);" maxlength="25000"/>
		             	<@s.fielderror><@s.param>shopreplymsg.reply_in_date</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">reply_content<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="shopreplymsg.reply_content" cssClass="txtinput" onkeyup="checkLength(this,300);" maxlength="300"/>
		             	<@s.fielderror><@s.param>shopreplymsg.reply_content</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">is_enbale<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="shopreplymsg.is_enbale" cssClass="txtinput" onkeyup="checkLength(this,1);" maxlength="1"/>
		             	<@s.fielderror><@s.param>shopreplymsg.is_enbale</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">user_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="shopreplymsg.user_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>shopreplymsg.user_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.token/>  
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Shopreplymsg_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

