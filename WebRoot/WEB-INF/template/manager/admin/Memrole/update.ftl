<html>
  <head>
    <title>修改商城后台角色信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Memrole_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">memrole_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memrole.memrole_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memrole.memrole_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">cust_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memrole.cust_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memrole.cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">memrole_name<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memrole.memrole_name" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memrole.memrole_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">memmenu_right<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memrole.memmenu_right" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memrole.memmenu_right</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">remark<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memrole.remark" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memrole.remark</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Memrole_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

