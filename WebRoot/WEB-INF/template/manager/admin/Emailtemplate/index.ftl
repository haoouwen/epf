<html>
  <head>
    <title>邮件模板管理</title>
  </head>
<body>
<@s.form action="/admin_Emailtemplate_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：会员管理 > 企业相关 > 邮件模板管理</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>模板名称:</td>
			<td><@s.textfield name="temp_name_s"  cssStyle="width:245px;"/></td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
		    <th width="3%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('emailtemplate.temp_id')"/></th>
		    <th width="20%" align="center" >模板名称</th>
		    <th width="10%" align="center" >操作</th>
	  </tr>
	  
		  <#list emailtemplateList as emailtemplate>
		  <tr>
		    <td><input type="checkbox" name="emailtemplate.temp_id" value="${emailtemplate.temp_id?if_exists}"/></td>
		    <td align="center">${emailtemplate.temp_name?if_exists}</td>
		    <td align="center">
		    	<a onclick="linkToInfo('/admin_Emailtemplate_view.action','emailtemplate.temp_id=${emailtemplate.temp_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a>
		    	<#if emailtemplate.sys_temp=='0'>
		    	<a href="/admin_Emailtemplate_delete.action?emailtemplate.temp_id=${emailtemplate.temp_id?if_exists}"><img src="/include/common/images/delete.png" border="0"/></a>
		    	</#if>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Emailtemplate_add.action','');" value="添加">
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Emailhistory_list.action')" value="返回">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>