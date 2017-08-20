<html>
  <head>
    <title>参数管理</title>
  <script src="/wro/admin_commpara_index.js" type="text/javascript"></script>
  <link href="/wro/admin_commpara_index.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
<@s.form action="/admin_Commpara_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：系统管理 > 系统管理 > 参数管理</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>参数名称:</td>
			<td><@s.textfield name="para_name_s"  cssStyle="width:245px;"/></td>
			 <td class="tdpad">是否有效:</td>
			<td>
				<@s.select name="isshow_s" list=r"#{'0':'有效','1':'无效'}" headerKey="" headerValue="请选择"/>
			</td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	       <td><input type="button" onclick="searchShowDIV('searchDiv','300px','220px');" class="rbut" value="高级查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
		    <th width="3%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('commpara.para_id')"/></th>
		    <th width="10%"  align="center" >排序</th>
		    <th width="20%" align="center" >参数编码</th>
		    <th width="20%"  align="center" >参数名称</th>
		    <th width="17%"  align="center" >参数键</th>
		    <th width="10%"  align="center" >参数值</th>
		    <th width="10%"  align="center" >是否有效</th>
		    <th width="10%" align="center" >操作</th>
	  </tr>
	  
		 <#list commparaList as syscommpara>
		
		    <tr bgcolor="<#if syscommpara_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
		    <td><input type="checkbox" name="commpara.para_id" value="${syscommpara.para_id?if_exists}"/></td>
		    <td align="center"><input name="isort_no" value="${syscommpara.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" /></td>
		    <td align="center">${syscommpara.para_code?if_exists}</td>
		    <td align="center">${syscommpara.para_name?if_exists}</td>
		    <td align="center">${syscommpara.para_key?if_exists}</td>
		   <td align="center">${syscommpara.para_value?if_exists}</td>
		   <td align="center">
		
		    <a onclick=linkToInfo("/admin_Commpara_list.action","isshow_s=${syscommpara.enabled?if_exists}");>
		     <#if syscommpara.enabled?if_exists=='0'>
		     
		      <font color='green'>有效</font>
		      <#else>
		      <font color='red'>无效</font>
		      </#if> 
		      
		      </a>
		      </td>
		    <td align="center">
		    <a onclick=linkToInfo("/admin_Commpara_view.action","commpara.para_id=${(syscommpara.para_id)?if_exists}");>
		    <img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="delInfo('commpara.para_id','/admin_Commpara_delete.action')" value="删除">
     <input type="button" class="rbut" onclick="updateSort('commpara.para_id','isort_no','/admin_Commpara_updateSort.action');" value="排序">
     <input type="button" class="rbut" onclick="updateBatchState('0','commpara.para_id','/admin_Commpara_updateUseful.action','有效');" value="有效">
     <input type="button" class="rbut" onclick="updateBatchState('1','commpara.para_id','/admin_Commpara_updateUseless.action','无效');" value="无效">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="admin_commp_id" id="admin_commp_id"/>
  <@s.hidden name="commpara.enabled" id="admin_commpara_state"/>
  <@s.hidden  name="para_code_s"/>
  <@s.hidden  name="para_key_s"/>
  <@s.hidden  name="para_value_s"/>      
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Commpara_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
	    <tr>
			<td  class="searchDiv_td">参数名称: </td>
			<td><@s.textfield name="para_name_s"/></td>
	   </tr>
	   <tr >
		    <td  class="searchDiv_td">参 数 键: </td>
	        <td><@s.textfield name="para_key_s"/></td>
	   </tr>
	    <tr>
			<td  class="searchDiv_td">参 数 值: </td>
			<td><@s.textfield name="para_value_s"/></td>
	   </tr>
		<tr>
	         <td  class="searchDiv_td">是否有效: </td>
		     <td><@s.select name="isshow_s" list=r"#{'0':'有效','1':'无效'}" headerKey="" headerValue="请选择"/></td>
       </tr>
		<tr>
			<td align="center" colspan="2" style="border:0px;">
				<input type="button" name="search" value="搜索" onclick="showSearchDiv('area_attr','cat_attr','search_area_attr','search_cat_attr','form_search_id');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			</td>
	   </tr>
		</table>
		<!--搜索框隐藏域存放-->
		    <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
		    <@s.hidden id="search_area_attr" name="area_attr_s"/>
			<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
			<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
			<@s.hidden  name="para_code_s"/>
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>
