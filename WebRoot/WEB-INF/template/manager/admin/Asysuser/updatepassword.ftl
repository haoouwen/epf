<html>
  <head>
    <title>修改账号密码</title>
  </head>
  <body>
<@s.form action="/admin_Sysuser_updatepwd.action" method="post" validate="true">
<div class="postion">
当前位置：系统管理 > 帐号管理 > 账号密码管理 > 修改账号密码
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
        <tr>
         <td class="table_name" width="30%">用户名<font color="red">*</font></td>
         <td>
         	<@s.textfield name="sysuser.user_name" cssStyle="border:0;background-color:#FFFFFF;" id="sys_user_name" readonly="true"/>
         </td>
         </tr>
           <tr>
             <td class="table_name">旧密码<font color='red'> *</font></td>
             <td >
             	<@s.password name="oldpasswd" cssClass="txtinput" maxLength="100"  cssStyle="width:200px;"/>
             	<@s.fielderror><@s.param>oldpasswd</@s.param></@s.fielderror>
           </td>
           </tr>
           <tr>
             <td class="table_name">新密码<font color='red'> *</font></td>
             <td >
             	<@s.password name="newpasswd" cssClass="txtinput" maxLength="100"  cssStyle="width:200px;"/>
             	<@s.fielderror><@s.param>newpasswd</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">确认密码<font color='red'> *</font></td>
             <td >
             	<@s.password name="confirmpasswd" cssClass="txtinput" maxLength="100"  cssStyle="width:200px;"/>
             	<@s.fielderror><@s.param>confirmpasswd</@s.param></@s.fielderror>
             </td>
           </tr>
         </table>
         </div>
	<div class="clear"/>
   <div class="bsbut_detail">
           <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
           <@s.token/>
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存" />
	       <@s.reset value="重置"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>