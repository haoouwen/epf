<html>
  <head>
     <title>属性列表</title>   
     <script type="text/javascript" src="/include/common/js/common.js"></script>
	 <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
	 <script type="text/javascript" src="/include/common/js/extendattr1.js"></script>
	 <!--该段注释外部引用无效果-->
	 <script type="text/javascript">
	  $(document).ready(function(){
	       //所属分类的回选
	       loadCat("${catIdStr?if_exists}","","","goods");
	   });
	</script>
  </head>
<body>
<@s.form action="/admin_Extendattr_list.action" method="post" id="indexForm">
<@s.hidden name="extendattr_sortno_id" id="extendattr_sortno_id"/>
<!--当前位置-->
	<div class="postion">当前位置：商品管理 > 商品属性管理 > 属性列表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>属性名称:</td> 
		 	<td><@s.textfield name="attr_name_s"  cssStyle="width:245px;"/></td>
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
		    <th width="3%" >  <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('extendattr.ex_attr_id')"/></th>
		    <th width="5%"  >排序</th>
		    <th width="30%"  >属性名称</th>
		    <th width="20%"  >选项类型</th>
		    <th width="30%"  >所属分类</th>
		    <th width="5%"  >是否显示</th>
		    <th width="7%"  >操作</th>
	  </tr>
	  
  <#list extendattrList as extendattr>
	    <tr >
		    <td><input type="checkbox" name="extendattr.ex_attr_id" value="${extendattr.ex_attr_id?if_exists}"/></td>
		    <td align="center">
			<input name="isort_no" value="${extendattr.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" maxLength="11"/>
		    </td>
		    <td align="center">
		    ${extendattr.attr_name?if_exists}
		    </td>
		     <td align="center">
		     <#if extendattr.option_type?if_exists?if_exists='0'>
		   	   <font class="redcolor">选择项</font>
		    <#elseif extendattr.option_type?if_exists?if_exists='1'>	     
		   		<font class="greencolor">输入框</font>
		   	<#elseif extendattr.option_type?if_exists?if_exists='2'>	     
		   		<font class="bluecolor">下拉框</font>
		   	<#else>
		   		-
	    	</#if>
		    </td>
		     <td  align="left" >
		     <#if extendattr.cat_attr!=null>
	    		<#if extendattr.cat_attr ?length lt 50>
	    			${extendattr.cat_attr?if_exists}
	    		<#else>
	    			${extendattr.cat_attr[0..49]?if_exists}...
	    		</#if>
	    	<#else>
	    		-
	    	</#if>
		    </td>
		     <td align="center">
		     <#if extendattr.is_display?if_exists?if_exists='0'>
		   	   <font class="greencolor">显示</font>
		    <#elseif extendattr.is_display?if_exists?if_exists='1'>	     
		   		<font class="redcolor">不显示</font>
	    	</#if>
		    </td>
		     <td align="center">
		     <a onclick="linkToInfo('/admin_Extendattr_view.action','extendattr.ex_attr_id=${extendattr.ex_attr_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Extendattr_add.action','');" value="添加属性">
     <input type="button" class="rbut"onclick="delInfo('extendattr.ex_attr_id','/admin_Extendattr_delete.action')" value="删除">
      <input type="button" class="rbut" onclick="updateSort('extendattr.ex_attr_id','isort_no','/admin_Extendattr_updateSort.action');"value="排序">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
 <@s.hidden  name="option_type_s"/>
<@s.hidden  name="is_display_s"/>
<@s.hidden name="cat_attr_s" />
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Extendattr_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		
		<tr>
			<td class="searchDiv_td">属性名称:</td>
			<td><@s.textfield name="attr_name_s"  /></td>
		</tr>
			<tr>
			<td class="searchDiv_td">选项类型:</td>
			<td><@s.select name="option_type_s" list=r"#{'':'请选择','1':'输入框','0':'选择项'}"  /></td>
		</tr>
			<tr>
			<td class="searchDiv_td">是否显示:</td>
			<td><@s.select name="is_display_s" list=r"#{'':'请选择','0':'显示','1':'不显示'}"  /></td>
		</tr>
			<tr>
			<td class="searchDiv_td" width="80px">所属分类:</td>
			<td width="300px">
				<div id="catDiv"></div>
			 </td>
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
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>










