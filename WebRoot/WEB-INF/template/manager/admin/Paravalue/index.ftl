<html>
  <head>
    <title>参数值列表</title> 
    <script type="text/javascript" src="/include/common/js/common.js"></script>
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
  	<script type="text/javascript" src="/include/admin/js/paravalue.js"></script>  
  </head>
<body>
<@s.form action="/admin_Paravalue_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：商品管理 > 商品参数管理 > 参数值列表</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
	      <tr>
	        <td align="right">参数名称:</td> 
			<td><@s.textfield name="para_name_s"  cssStyle="width:200px;"/></td>
	        <td><input type="submit" class="rbut" value="查询"></td>
	      </tr>
     </table>
   </div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
		    <th width="3%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('paravalue.para_id')"/></th>
	   
	     	 <th width="10%"  >排序</th>
	     	 
	     	 <th width="30%"  >参数名称</th>
	    
	     	 <th width="25%"  >参数值</th>
	     	 
	     	 <th width="25%"  >参数组名</th>
	        
	        <th width="7%" >修改</th>
	  </tr>
	  
	  <#list paravalueList as paravalue>
	  <tr>
	    <td><input type="checkbox" name="paravalue.para_id" value="${paravalue.para_id?if_exists}"/></td>    
	 	
	 	 	<td align="center"><input name="isort_no" value="${paravalue.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" maxLength="11"/>
	    
	    	<td align="center">${paravalue.para_name?if_exists}</td>
	    	
	    	<td align="center">${paravalue.value?if_exists}</td>
	    	
	    	<td align="center">${paravalue.paras_name?if_exists}</td>
	    
	    
	    <td align="center"><a onclick="linkToInfo('/admin_Paravalue_view.action','paravalue.para_id=${paravalue.para_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
   	 <#if pgi_s!=null&&pgi_s!=''>
     	<input type="button" class="rbut" onclick="linkToInfo('/admin_Paravalue_add.action','paravalue.para_group_id=${pgi_s}');" value="添加参数值">
   	 </#if> 
     <input type="button" class="rbut" onclick="delInfo('paravalue.para_id','/admin_Paravalue_delete.action?paravalue.para_group_id=${paravalue.para_group_id?if_exists}');" value="删除">
     <input type="button" class="rbut" onclick="updateSort('paravalue.para_id','isort_no','/admin_Paravalue_updateSort.action');" value="排序">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
<@s.hidden name="paravalue_sortno_id" id="paravalue_sortno_id"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
			   
<!--搜索区域结束-->
  </body>
</html>


