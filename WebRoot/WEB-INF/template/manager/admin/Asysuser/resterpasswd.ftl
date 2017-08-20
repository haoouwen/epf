<html>
  <head>
    <title>密码重置</title>
  </head>
 <body>
<@s.form action="/admin_Asysuser_sendPwd.action" method="post" validate="true" id="modify_id">
<div class="postion">当前位置：会员管理 > 区域代理 > 密码重置</div>
<div class="rtdcont">
   <!--后台table-->
   <div class="rdtable">
    <table  width="100%" cellspacing="0" cellpadding="0">
       <tr>
         <td class="table_name" width="25%">用户名：</td>
         <td>
           ${asysuser.user_name}
         </td>
       </tr>
       <tr>
         <td class="table_name">邮箱：</td>
         <td>
         	${asysuser.email}
         </td>
       </tr>
     </table>
   </div>
   <div class="clear"/>
   <div class="bsbut_detail">
       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
       <@s.token/>
       <@s.hidden name="asysuser.user_id"/>
        <@s.hidden name="pwd_state"/>
       ${listSearchHiddenField?if_exists}
        <input type="button" value="重置密码" onclick="resterPwd('/admin_Asysuser_sendPwd.action');"/>
   </div>
</div>
</@s.form>
  </body>
</html>