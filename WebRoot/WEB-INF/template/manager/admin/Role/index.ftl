<html>
  <head>
    <title>角色管理</title>
  </head>
<body>
<@s.form action="/admin_Role_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:系统管理 > 帐号管理 > 角色管理</div>
<!--当前位置结束-->
<div class="rtdcont">
 <!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
       <tr>
         <td>角色名：</td><td><@s.textfield name="role_name_s"/></td>
         <td><input type="submit" class="rbut" value="查询"></td>
       <tr>
     </table>
   </div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table  width="100%" cellspacing="0" cellpadding="0" class="indexTab">
       <tr >
		    <th width="5%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('role.role_id')"/></th>
		    <th width="45%" >角色名</th>
		    <th width="40%" >管理员数量</th>
		    <th width="10%" >操作</th>
 	   </tr>
	  
	  <#list rolelist as role>
	  
	   	<tr>
		    <td align="center">
		    <#if role.is_system=='1'>
	    		<img class='ltip' src="/include/common/images/light.gif" alt="系统内置角色" />
	    	<#else>
	    	<input type="checkbox" name="role.role_id" value="${role.role_id?if_exists}"/>
	    	</#if>
		    
		    </td>
		    <td align="center">${role.role_name?if_exists}</td>
		    <td align="center">
		     <#if (role.adminnum)?if_exists=='0'>
		       ${role.adminnum?if_exists}
		     <#else>
		       <a href="/admin_Sysuser_list.action?role_id_s=${(role.role_id)?if_exists}">${role.adminnum?if_exists}</a>
		     </#if>
		    </td>
		    <td align="center">
		    <a onclick="linkToInfo('/admin_Role_view.action','role.role_id=${role.role_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a>
		    <a onclick="linkToInfo('/admin_Sysuser_list.action','role_id_s=${(role.role_id)?if_exists}');" title="查看该角色下管理员"><img src="/include/admin/images/medit.gif" /></a>
		    </td>
		</tr>

 	  </#list>
	</table>
  </div>
<!--后台table结束-->
<!--翻页-->
   <div class="pages">
     ${pageBar?if_exists}
   </div>
   <div class="clear"/>
<!--翻页结束-->
<!--按钮操作存放-->
   <div class="bsbut">
   <#if grade!='3'>
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Role_add.action','');" value="添加角色">
    </#if>
     <input type="button" class="rbut" onclick="delInfo('role.role_id','/admin_Role_delete.action');" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->

 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	  
<!--搜索区域结束-->
  </body>
</html>





