<html>
  <head>
    <title>添加物流推送</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Pushlogistics_insert.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">trade_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="pushlogistics.trade_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>pushlogistics.trade_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">number<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="pushlogistics.number" cssClass="txtinput" onkeyup="checkLength(this,20);" maxlength="20"/>
		             	<@s.fielderror><@s.param>pushlogistics.number</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">com<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="pushlogistics.com" cssClass="txtinput" onkeyup="checkLength(this,20);" maxlength="20"/>
		             	<@s.fielderror><@s.param>pushlogistics.com</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">status<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="pushlogistics.status" cssClass="txtinput" onkeyup="checkLength(this,5);" maxlength="5"/>
		             	<@s.fielderror><@s.param>pushlogistics.status</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">time<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="pushlogistics.time" cssClass="txtinput" onkeyup="checkLength(this,25000);" maxlength="25000"/>
		             	<@s.fielderror><@s.param>pushlogistics.time</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">content<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="pushlogistics.content" cssClass="txtinput" onkeyup="checkLength(this,600);" maxlength="600"/>
		             	<@s.fielderror><@s.param>pushlogistics.content</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">is_ship<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="pushlogistics.is_ship" cssClass="txtinput" onkeyup="checkLength(this,1);" maxlength="1"/>
		             	<@s.fielderror><@s.param>pushlogistics.is_ship</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">is_send<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="pushlogistics.is_send" cssClass="txtinput" onkeyup="checkLength(this,1);" maxlength="1"/>
		             	<@s.fielderror><@s.param>pushlogistics.is_send</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">is_sign<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="pushlogistics.is_sign" cssClass="txtinput" onkeyup="checkLength(this,1);" maxlength="1"/>
		             	<@s.fielderror><@s.param>pushlogistics.is_sign</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.token/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Pushlogistics_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

