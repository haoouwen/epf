<html>
  <head>
    <title>参数组列表</title>   
	<script type="text/javascript" src="/include/common/js/common.js"></script>
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
  	<script type="text/javascript" src="/include/admin/js/paragroup.js"></script>
  	<link rel="StyleSheet"  type="text/css" href="/include/admin/css/common.css"/>
  	<script type="text/javascript">
	  $(document).ready(function(){
	       //所属分类的回选
	       loadCat("${catIdStr?if_exists}","","","goods");
	   });
     
    </script> 
  </head>
<body>
<@s.form action="/admin_Paragroup_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:商品管理 > 商品参数管理 > 参数组列表</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>参数组名:</td> 
			<td><@s.textfield name="para_name_s"  cssStyle="width:200px;"/></td>
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
		     <th width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('paragroup.para_group_id')"/></th>
	   
	         <th width="20%">排序</th>
	         
	     	 <th width="33%">参数组名</th>
	    
	     	 <th width="20%">分类</th>
	    
	    	 <th width="10%">操作</th>
	  </tr>
	  
	  <#list paragroupList as paragroup>
	  <tr>
	    <td>
	    <input type="checkbox" name="paragroup.para_group_id" value="${paragroup.para_group_id?if_exists}"/></td>    
	 	 	
	 	 	<td align="center"><input name="isort_no" value="${paragroup.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" maxLength="11"/>
	    	
	    	<td align="center">
	    	<#if paragroup.para_name!=null>
	    	  ${paragroup.para_name?if_exists}
	    	<#else>
	    	  -
	    	</#if></td>
	    	 <td align="left" style="line-height:18px;">
	    		<#if paragroup.cat_attr!=null>
	    			<#if paragroup.cat_attr ?length lt 20>
		    			${paragroup.cat_attr?if_exists}
		    		<#else>
		    			${paragroup.cat_attr[0..19]?if_exists}...
		    		</#if>
		    	<#else>
		    		-
		    	</#if>
	    	</td>
	    
	    <td align="center"><a onclick="linkToInfo('/admin_Paragroup_view.action','paragroup.para_group_id=${paragroup.para_group_id?if_exists}');"><img src="/include/common/images/bj.gif" title="修改参数组"/></a>
	    <a onclick="linkToInfo('/admin_Paravalue_list.action','pgi_s=${paragroup.para_group_id?if_exists}');"><img src="/include/common/images/add.png" title="管理参数值" /></a></td>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Paragroup_add.action','');" value="添加参数组">
     <input type="button" class="rbut" onclick="delInfo('paragroup.para_group_id','/admin_Paragroup_delete.action')" value="删除">
     <input type="button" class="rbut" onclick="updateSort('paragroup.para_group_id','isort_no','/admin_Paragroup_updateSort.action');" value="排序">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="paragroup_sortno_id" id="paragroup_sortno_id"/>
  <@s.hidden name="cat_attr_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Paragroup_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		
		<tr>
			<td class="searchDiv_td">参数组名:</td> 
			<td><@s.textfield name="para_name_s"/></td>
	   </tr>
	   <tr>
		<td class="searchDiv_td" whidth="80px">所属分类:</td>
		<td width="300px">
	          	<div id="catDiv" ></div>
     	</td>
		</tr>	
		
		<tr>
			<td align="center" colspan="2" style="border:0px;">
				<input type="button" name="search" value="搜索" onclick="showSearchDiv('','cat_attr','','search_cat_attr','form_search_id');"/>&nbsp;
				<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			</td>
	   </tr>
		</table>
		<!--搜索框隐藏域存放-->
		    <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
			<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>
