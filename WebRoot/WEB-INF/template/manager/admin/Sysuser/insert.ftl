<html>
  <head>
    <title>添加管理员</title>
	<script type="text/javascript" src="/include/admin/js/organize.js"></script>
	<script type="text/javascript">
	  $(document).ready(function(){ 	    
	     checkback("1111111111",1);	     
	  });
	</script>
  </head>
<body>
<@s.form action="/admin_Sysuser_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:系统管理 > 帐号管理 > 管理员管理 > 添加管理员
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		     <tr>
             <td class="table_name">用户名<font color="red">*</font></td>
             <td>
             	<@s.textfield name="sysuser.user_name" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>sysuser.user_name</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Sysuser.user_name')}"/>">        
             </td>
           </tr>
           <tr>
             <td class="table_name">密码<font color="red">*</font></td>
             <td>
             	<@s.password name="sysuser.passwd" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>sysuser.passwd</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Sysuser.passwd')}"/>">
             </td>
           </tr>
           <tr>
             <td class="table_name">确认密码<font color="red">*</font></td>
             <td>
             	<@s.password name="confirmpasswd" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>confirmpasswd</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Sysuser.passwd')}"/>">	
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
             <td class="table_name">角色<font color="red">*</font></td>
             <td>
               <@s.select id="type" name="sysuser.role_id"  list="roleList" listValue="role_name" listKey="role_id" headerKey="" headerValue="请选择"/>        
               <@s.fielderror><@s.param>sysuser.role_id</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">所属部门:</td>
             <td>
                <table>
             		<tr>
             			<td class="tdbottom">
             				<div id="orgselect"></div>
             			</td>
             			<td class="tdbottom">
             				<a href="admin_Organize_list.action" target="_blank">[部门管理]</a>
	              		</td>
	              	</tr>
	            </table> 
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
           <tr>
             <td class="table_name">状态:</td>
             <td>
             	<@s.radio name="sysuser.state" list=r"#{'0':'启用','1':'禁用'}" value="0"/>
             	<@s.fielderror><@s.param>sysuser.state</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif" alt='<@s.property value="%{getText('manager.admin.Sysuser.state')}"/> '>
             </td>
           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
      <@s.token/>	
	       <@s.hidden name="org_hidden_value" id="org_value"/>
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>	       
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Sysuser_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>