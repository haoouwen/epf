<html>
  <head>
    <title>参数管理</title>
  <script src="/wro/admin_commpara_index.js" type="text/javascript"></script>
  <link href="/wro/admin_commpara_index.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
<@s.form action="/admin_Commpara_groupList.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：系统管理 > 系统管理 > 参数管理</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>参数编码:</td>
			<td><@s.textfield name="para_code_s"  cssStyle="width:245px;"/></td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
		    <th width="40%"  align="center" >参数名称</th>
		    <th width="40%" align="center" >参数编码</th>
		    <th width="10%" align="center"> 信息条数 </th>
		    <th width="10%" align="center"> 查看 </th>
	  </tr>
	  
		 <#list commparaList as syscommpara>
		   <tr bgcolor="<#if syscommpara_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
		    <td align="center"><a onclick="linkToInfo('/admin_Commpara_list.action','para_code_s=${syscommpara.para_code?if_exists}');" title="查看" >${syscommpara.para_name?if_exists}</a></td>
		    <td align="center">${syscommpara.para_code?if_exists}</td>
		    <td align="center">${syscommpara.info_num?if_exists}</td>
		    <td align="center"><a onclick="linkToInfo('/admin_Commpara_list.action','para_code_s=${syscommpara.para_code?if_exists}');" title="查看" ><img src="/include/common/images/audit.png" /></a></td>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Commpara_add.action','');" value="添加">
   </div>
<!--按钮操作存放结束-->

</div>
</@s.form>
  </body>
</html>
