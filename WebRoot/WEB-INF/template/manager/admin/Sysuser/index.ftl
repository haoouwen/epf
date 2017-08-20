<html>
  <head>
    <title>系统用户管理</title>
    <script src="/include/admin/js/sysuser.js" type="text/javascript"></script>	
     <script type="text/javascript" src="/include/admin/js/organize.js"></script>
	<script type="text/javascript">
	  $(document).ready(function(){ 	    
	     checkback("1111111111",1);	     
	  });
	</script>
  </head>
  <body>

<@s.form action="/admin_Sysuser_list.action" method="post" id="indexForm">
<@s.hidden name="sysuser.state" id="admin_user_state"/>
<div class="postion">当前位置：系统管理 > 帐号管理 > 管理员管理</div>
<div class="rtdcont">
   <!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
       <tr>
         <td>用户名：</td><td><@s.textfield name="user_name_s"/></td>
          <td class="tdpad">昵称：</td><td><@s.textfield name="nike_name_s"/></td>
          <td class="tdpad">角色：</td><td><@s.select name="role_id_s"  list="roleList" listValue="role_name" listKey="role_id" headerKey="" headerValue="请选择"/> </td>
           <td class="tdpad">所属部门：</td>
           <td>
             		<div id="orgselect"></div>
           </td>
         <td><input type="submit" class="rbut" value="查询"></td>
       <tr>
     </table>
   </div>
   <!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
       <tr>
         <th width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('sysuser.user_id')"/></th>
         <th width="10%">用户名</th>
         <th width="10%">昵称 </th>
         <th width="10%">角色</th>
         <th width="10%">隶属会员</th>
         <th width="10%">所属部门</th>
         <th  width="10%">状态</th>
         <th  width="15%">操作</th>
       </tr>
      <#list userList as user>

	 <tr>
        <td>
        <#if user.user_type=='3'>
        --
        <#else>
        <input type="checkbox" name="sysuser.user_id" value="${user.user_id?if_exists}"/>
        </#if>
        </td>
	    <td align="center">${user.user_name?if_exists}</td>
	    <td align="center">${user.nike_name?if_exists}</td>
	    <td align="center">
	    
	    	<#if user.user_type=='3'>
	    		<font color="red">[系统管理员]</font>
	    	</#if>
	    	
	    	<#if user.user_type=='4'>
	    		<a href="/admin_Sysuser_list.action?role_id_s=${user.role_id?if_exists}">${user.role_name?if_exists}</a>
	    	</#if>
	    	
	    </td>
	      <td align="center">${user.subjection?if_exists}</td>
	    <td align="center">${user.org_name?if_exists}</td>
	    
	    <td align="center"><#if user.state?if_exists=='0'><font color='green'>启用</font><#else><font color='red'>禁用</font></#if> </td>
	    <td align="center">
	    	<a onclick=linkToInfo("/admin_Sysuser_view.action","sysuser.user_id=${user.user_id?if_exists}");><img src="/include/common/images/bj.gif" /></a>
	    	<a onclick="linkToInfo('/admin_Sysuser_roleView.action','sysuser.user_id=${user.user_id?if_exists}');" title="修改角色信息"><img src="/include/admin/images/medit.gif" /></a>
	    	<a onclick="linkToInfo('/admin_Sysuser_resterView.action','sysuser.user_id=${user.user_id?if_exists}');" title="密码重置"><img src="/include/admin/images/accedit.gif" /></a>
	    	<#if user.user_type=='4'>
	    	<a href="javascript:delOneInfo('sysuser.user_id','/admin_Sysuser_delete.action','${(user.user_id)?if_exists}');"><img src="/include/admin/images/delete.png" border="0"/></a>
	    	</#if>
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
   <!--翻页-->
   <div class="bsbut">
   <#if grade!='3'>
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Sysuser_add.action','');" value="添加管理员">
 	</#if>
     <input type="button" class="rbut" onclick="updateBatchState('0','sysuser.user_id','/admin_Sysuser_updateOnState.action','启用');" value="启用">
     <input type="button" class="rbut" onclick="updateBatchState('1','sysuser.user_id','/admin_Sysuser_updateDownState.action','禁用');" value="禁用">
   </div>
</div>
<!--搜索框隐藏域存放-->
		  <@s.hidden name="org_hidden_value" id="org_value"/>
</@s.form>
</body>
</html>


