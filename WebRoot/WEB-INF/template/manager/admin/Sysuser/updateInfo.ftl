<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"] />
<html>
  <head>
    <title>修改账号信息</title>
	<script type="text/javascript" src="/include/admin/js/organize.js"></script>
  </head>
 <body>
<@s.form action="/admin_Sysuser_update.action" method="post" validate="true">
<div class="postion">当前位置：系统管理 > 帐号管理 > 账号维护管理 > 修改账号信息</div>
<div class="rtdcont">
   <!--后台table-->
   <div class="rdtable">
    <table  width="100%" cellspacing="0" cellpadding="0">
       <tr>
         <td class="table_name">用户名<font color="red">*</font></td>
         <td>
         	<@s.textfield name="sysuser.user_name" cssStyle="border:0;background-color:#FFFFFF;" id="sys_user_name" readonly="true"/>
         </td>
       </tr>
       <tr>
         <td width="19%" class="table_name">昵称:</td>
         <td>
         	<@s.textfield name="sysuser.nike_name" cssClass="txtinput"/>
         	<@s.fielderror><@s.param>sysuser.nike_name</@s.param></@s.fielderror>
         	<img class='ltip' src="/include/common/images/light.gif" alt='<@s.property value="%{getText('manager.admin.Sysuser.nike_name')}"/> '>
         </td>
       </tr>
       <tr>
         <td class="table_name">真实姓名:</td>
         <td>
            <@s.textfield name="sysuser.real_name" cssClass="txtinput"/>
            <@s.fielderror><@s.param>sysuser.real_name</@s.param></@s.fielderror>
         </td>
       </tr>
       <tr>
         <td class="table_name">邮箱<font color="red">*</font></td>
         <td>
         	<@s.textfield name="sysuser.email" cssClass="txtinput"/>
         	<@s.fielderror><@s.param>sysuser.email</@s.param></@s.fielderror>
         </td>
       </tr>
     </table>
   </div>
   <div class="clear"/>
   <div class="bsbut_detail">
       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
       <@s.token/>
       <@s.hidden name="sysuser.user_id"/>
       <@s.hidden name="sysuser.role_id"/>
       <@s.hidden name="sysuser.state"/>
       <@s.hidden name="oldusername" value="${sysuser.user_name}"/>
       <@s.hidden name="info" value="1"/>
       ${listSearchHiddenField?if_exists}
       <@s.hidden name="org_hidden_value" id="org_value"/>
       <@s.submit value="保存"/>
       <@s.reset value="重置"/>
   </div>
</div>
</@s.form>
  </body>
</html>