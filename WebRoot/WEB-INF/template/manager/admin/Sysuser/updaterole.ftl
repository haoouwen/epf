<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"] />
<html>
  <head>
    <title>修改角色信息</title>
	<script type="text/javascript" src="/include/admin/js/organize.js"></script>
	<script type="text/javascript">
	  $(document).ready(function(){ 	    
	     checkback("1111111111",1);	     
	  });
	</script>
  </head>
 <body>
<@s.form action="/admin_Sysuser_update.action" method="post" validate="true">
<div class="postion">当前位置：系统管理 > 帐号管理 > 管理员管理 > 修改角色信息</div>
<div class="rtdcont">
   <!--后台table-->
   <div class="rdtable">
    <table  width="100%" cellspacing="0" cellpadding="0">
       <tr>
         <td class="table_name" width="20%">用户名<font color="red">*</font></td>
         <td>
         	<@s.textfield name="sysuser.user_name" cssStyle="border:0;background-color:#FFFFFF;" id="sys_user_name" readonly="true"/>
         	<input type="hidden" name="user_name_bak" value="" id="user_name_bak"/> 
         	<script>
         		document.getElementById('user_name_bak').value = document.getElementById('sys_user_name').value;
         	</script>
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
     </table>
   </div>
   <div class="clear"/>
   <div class="bsbut_detail">
       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
       <@s.token/>
       <@s.hidden name="sysuser.user_id"/>
       <@s.hidden name="sysuser.real_name"/>
       <@s.hidden name="sysuser.email"/>
       <@s.hidden name="sysuser.state"/>
       <@s.hidden name="sysuser.nike_name"/>
       <@s.hidden name="oldusername" value="${sysuser.user_name}"/>  
       ${listSearchHiddenField?if_exists}
       <@s.hidden name="org_hidden_value" id="org_value"/>
       <@s.submit value="保存"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Sysuser_list.action','');"/>
   </div>
</div>
</@s.form>
  </body>
</html>