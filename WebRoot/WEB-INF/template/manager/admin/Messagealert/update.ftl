<html>
  <head>
    <title>修改记录信息提醒设置信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Messagealert_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">msg_code<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="messagealert.msg_code" cssClass="txtinput" onkeyup="checkLength(this,20);" maxlength="20"/>
		             	<@s.fielderror><@s.param>messagealert.msg_code</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">msg_title<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="messagealert.msg_title" cssClass="txtinput" onkeyup="checkLength(this,20);" maxlength="20"/>
		             	<@s.fielderror><@s.param>messagealert.msg_title</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">is_send_email<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="messagealert.is_send_email" cssClass="txtinput" onkeyup="checkLength(this,1);" maxlength="1"/>
		             	<@s.fielderror><@s.param>messagealert.is_send_email</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">email_code<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="messagealert.email_code" cssClass="txtinput" onkeyup="checkLength(this,20);" maxlength="20"/>
		             	<@s.fielderror><@s.param>messagealert.email_code</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">is_send_mobile<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="messagealert.is_send_mobile" cssClass="txtinput" onkeyup="checkLength(this,1);" maxlength="1"/>
		             	<@s.fielderror><@s.param>messagealert.is_send_mobile</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">mobile_code<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="messagealert.mobile_code" cssClass="txtinput" onkeyup="checkLength(this,20);" maxlength="20"/>
		             	<@s.fielderror><@s.param>messagealert.mobile_code</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">is_send_letter<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="messagealert.is_send_letter" cssClass="txtinput" onkeyup="checkLength(this,1);" maxlength="1"/>
		             	<@s.fielderror><@s.param>messagealert.is_send_letter</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">letter_code<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="messagealert.letter_code" cssClass="txtinput" onkeyup="checkLength(this,20);" maxlength="20"/>
		             	<@s.fielderror><@s.param>messagealert.letter_code</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.token/>  
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Messagealert_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

