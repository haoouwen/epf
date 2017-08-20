<html>
  <head>
    <title>密码重置</title>
  </head>
 <body>
<@s.form action="/admin_Sysuser_sendPwd.action" method="post" validate="true" id="modify_id">
<div class="postion">当前位置：系统管理 > 帐号管理 > 管理员管理 > 密码重置</div>
<div class="rtdcont">
   <!--后台table-->
   <div class="rdtable">
    <table  width="100%" cellspacing="0" cellpadding="0">
       <tr>
         <td class="table_name">用户名：</td>
         <td>
           ${sysuser.user_name}
         </td>
       </tr>
       <tr>
         <td class="table_name">邮箱：</td>
         <td>
         	${sysuser.email}
         </td>
       </tr>
     </table>
   </div>
   <div class="clear"/>
   <div class="bsbut_detail">
       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
       <@s.token/>
       <@s.hidden name="sysuser.user_id"/>
       ${listSearchHiddenField?if_exists}
        <input type="button" value="重置密码" onclick="resterPwd('/admin_Sysuser_sendPwd.action');"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Sysuser_list.action','');"/>
   </div>
</div>
</@s.form>
  </body>
</html>