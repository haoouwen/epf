<html>
  <head>
    <title>修改会员登陆账号信息表</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Memberuser_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">user_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memberuser.user_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memberuser.user_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">cust_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memberuser.cust_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memberuser.cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">user_type<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memberuser.user_type" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memberuser.user_type</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">user_name<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memberuser.user_name" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memberuser.user_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">passwd<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memberuser.passwd" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memberuser.passwd</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">role_code<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memberuser.role_code" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memberuser.role_code</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">email<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memberuser.email" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memberuser.email</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">real_name<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memberuser.real_name" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memberuser.real_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">sex<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memberuser.sex" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memberuser.sex</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">cellphone<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memberuser.cellphone" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memberuser.cellphone</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">phone<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memberuser.phone" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memberuser.phone</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">qq<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memberuser.qq" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memberuser.qq</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">msn<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memberuser.msn" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memberuser.msn</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">skype<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memberuser.skype" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memberuser.skype</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">pass_strength<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memberuser.pass_strength" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memberuser.pass_strength</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">login_time<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memberuser.login_time" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>memberuser.login_time</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Memberuser_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

