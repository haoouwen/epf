<html>
  <head>
    <title>系统模块设置</title>
    <script type="text/javascript" src="/include/admin/js/sysmodule.js1"></script>
</head>









<body>
<@s.form action="/admin_Sysmodule_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:系统管理 > 系统设置 > 系统模块设置</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		   <th width="5%" >
		    <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('sysmodule.module_type')"/></th>
		    <th width="10%"  >排序</th>
		    <th width="20%"  >模块编码</th>
		    <th width="20%"  >模块名称</th>
		    <th width="15%"  >表名</th>
		    <th width="15%"  >状态</th>
		    <th width="15%"  >操作</th>
		  </tr>
  
  <#list sysmoduleList as mod>
   <tr bgcolor="<#if mod_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
	    <td ><input type="checkbox" name="sysmodule.module_type" value="${mod.module_type?if_exists}"/></td>
	    <td align="center">
			<input class="textsort" style="width:36px;" onkeyup="checkNum(this)" name="mod_sort_no" value="${mod.sort_no?if_exists}">
		</td>	
		<td align="center">${mod.module_type?if_exists}</td>
	    <td align="center">${mod.module_name?if_exists}</td>
		<td align="center">${mod.table_name?if_exists}</td>
		<td align="center">
			<#if mod.state_code=='0'><span class="greencolor">启用</span><#else><span class="redcolor">禁用</span></#if>
		</td>
	
	    <td align="center">	    
	      <a onclick=linkToInfo("/admin_Sysmodule_view.action","sysmodule.module_type=${mod.module_type?if_exists}"); title="修改"><img src="/include/common/images/bj.gif" /></a>
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
     <input type="button" class="rbut" onclick="updateSort('sysmodule.module_type','mod_sort_no','/admin_Sysmodule_updateSort.action');" value="排序">
     <input type="button" class="rbut" onclick="updateBatchState('0','sysmodule.module_type','/admin_Sysmodule_updateOn.action','启用');" value="启用">
          <input type="button" class="rbut" onclick="updateBatchState('1','sysmodule.module_type','/admin_Sysmodule_updateDown.action','禁用');" value="禁用">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="sysmodule.state_code" id="admin_Sysmodule_state"/>
  <@s.hidden name="admin_sysmodule_id" id="admin_sysmodule_id"/>
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>
