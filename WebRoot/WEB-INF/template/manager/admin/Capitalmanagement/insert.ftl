<html>
  <head>
    <title>添加运营商资金管理</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Capitalmanagement_insert.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">trade_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="capitalmanagement.trade_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>capitalmanagement.trade_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">pass_men<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="capitalmanagement.pass_men" cssClass="txtinput" onkeyup="checkLength(this,20);" maxlength="20"/>
		             	<@s.fielderror><@s.param>capitalmanagement.pass_men</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">password<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="capitalmanagement.password" cssClass="txtinput" onkeyup="checkLength(this,50);" maxlength="50"/>
		             	<@s.fielderror><@s.param>capitalmanagement.password</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">question<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="capitalmanagement.question" cssClass="txtinput" onkeyup="checkLength(this,50);" maxlength="50"/>
		             	<@s.fielderror><@s.param>capitalmanagement.question</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">answer<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="capitalmanagement.answer" cssClass="txtinput" onkeyup="checkLength(this,50);" maxlength="50"/>
		             	<@s.fielderror><@s.param>capitalmanagement.answer</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.token/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Capitalmanagement_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

