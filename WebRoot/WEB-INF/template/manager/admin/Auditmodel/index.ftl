<html>
  <head>
    <title>审核模型信息列表</title>   
  </head>
<body>
<@s.form action="/admin_Auditmodel_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：系统管理 > 系统设置 > 审核模式设置</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
	     <th width="1%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('auditmodel.model_type')"/></th>
	     <th width="30%"  align="center" >模型类型</th>
	     <th width="60%"  align="center" >审核顺序</th>
	     <th width="5%" align="center" >操作</th>
	  </tr>
  <#list auditList as auditmodel>
  <tr>
    <td><input type="checkbox" name="auditmodel.model_type" value="${auditmodel.model_type?if_exists}"/></td>    
 	
    	<td align="center">${auditmodel.para_key?if_exists}</td>
    
    	<td align="center">${auditmodel.audit_sortno_name?if_exists}</td>
              
    <td align="center"><a onclick="linkToInfo('/admin_Auditmodel_view.action','audti_model_type=${auditmodel.model_type?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Auditmodel_add.action','');" value="添加">
     <input type="button" class="rbut" onclick="delInfo('auditmodel.model_type','/admin_Auditmodel_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>

