<html>
  <head>
    <title>管理员管理</title>
    <script src="/include/admin/js/sysuser.js" type="text/javascript"></script>	
     <script type="text/javascript" src="/include/admin/js/organize.js"></script>
  </head>
  <body>

<@s.form action="/admin_Asysuser_groupList.action" method="post" id="indexForm">
<@s.hidden name="asysuser.state" id="admin_user_state"/>
<div class="postion">当前位置：系统管理 > 帐号管理 > 管理员管理</div>
<div class="rtdcont">
   <!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
       <tr>
         <td>角色：</td><td><@s.textfield name="role_name_s"  cssStyle="width:245px;"/></td>
         <td><input type="submit" class="rbut" value="查询"></td>
       <tr>
     </table>
   </div>
   <!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
       <tr>
         <th width="45%">角色</th>
         <th width="45%">管理员人数</th>
         <th  width="10%">查看</th>
       </tr>
     <#list userList as user>
		 <tr>
	        <td><a href="/admin_Asysuser_list.action?role_id_s=${user.role_id?if_exists}">${user.role_name?if_exists}</a></td>
		    <td align="center">${user.info_num?if_exists}</td>
		    <td align="center">
		    	<a href="/admin_Asysuser_list.action?role_id_s=${user.role_id?if_exists}" title="查看"><img src="/include/common/images/audit.png" /></a>
		    </td>
	     </tr>
    </#list>
     </table>
   </div>
   <!--翻页-->
   <div class="pages">
     ${pageBar?if_exists}
   </div>
   <div class="clear"/>
</div>
</@s.form>
</body>
</html>


