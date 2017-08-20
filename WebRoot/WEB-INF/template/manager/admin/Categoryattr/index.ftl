<html>
  <head>
    <title>分类属性管理</title>
	<script src="/wro/admin_category_update.js" type="text/javascript"></script>
  </head>
 <body>
<@s.form action="/admin_Categoryattr_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：功能模块 > 分类管理 > ${modtype_name?if_exists}分类属性管理</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>属性名:</td>
			<td><@s.textfield name="attr_name_s"  cssStyle="width:245px;"/></td>
			 <td class="tdpad">是否必填:</td>
			<td>
				<@s.select name="is_must_s" list=r"#{'0':'不必填','1':'必填'}" headerKey=""  headerValue="请选择"/>
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
		    <th width="6%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('categoryattr.attr_id')"/>&nbsp;全选</th>
		    <th width="20%" align="center" >属性名</th>
		    <th width="10%" align="center" >所属模块</th>
		    <th width="20%" align="center" >所属分类</th>
		    <th width="20%" align="center" >属性类型</th>
		    <th width="14%" align="center" >是否必填</th>
		    <th width="10%" align="center" >操作</th>
	  </tr>
	  
		  <#list categoryattrList as categoryattr>
		
		    <tr>
		    <td><input type="checkbox" name="categoryattr.attr_id" value="${categoryattr.attr_id?if_exists}"/></td>
		    <td align="center">${categoryattr.attr_name?if_exists}</td>
		    <td align="center">${categoryattr.module_name?if_exists}</td>
		    <td align="center">${categoryattr.cat_attr?if_exists}</td>
		    <td align="center">   
		    <#if categoryattr.attr_type='0'>
		    <font color="#999999"> 单行文本</font>
		    <#elseif categoryattr.attr_type='1'>
		    <font color="#990000"> 多行文本 </font>
		    <#elseif categoryattr.attr_type='2'>
		    <font color="red"> 单选框</font>
		    <#elseif categoryattr.attr_type='3'>
		    <font color="red"> 复选框</font>
		    <#elseif categoryattr.attr_type='4'>
		    <font color="red">图片上传</font>
		    <#elseif categoryattr.attr_type='5'>
		    <font color="red">附件</font>
		    <#elseif categoryattr.attr_type='6'>
		    <font color="red">WEB编辑器</font>
		    <#elseif categoryattr.attr_type='7'>
		    <font color="red">日期</font>
		    <#else>
		    <font color="green"></font>
		    </#if>    
		    </td>
		    <td align="center">
		    <#if categoryattr.is_must='0'>
		    <font color="red">不必填</font>
		    <#else>
		     <font color="green">必填</font>
		    </#if> 
		    </td>
		    <td align="center"><a  onclick="linkToInfo('/admin_Categoryattr_view.action?modtype_name_id=${modtype_name_id?if_exists}&url_up_id=${url_up_id?if_exists}&level=${level?if_exists}','categoryattr.attr_id=${categoryattr.attr_id?if_exists}&modtype_s=${modtype_s?if_exists}')"><img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Categoryattr_add.action?modtype_name_id=${modtype_name_id?if_exists}&intr_type=${modtype_name_id?if_exists}&url_up_id=${url_up_id?if_exists}&level=${level?if_exists}','code_value=${code_value?if_exists}')" value="添加">
     <input type="button" class="rbut" onclick="delInfo('categoryattr.attr_id','/admin_Categoryattr_delete.action?url_up_id=${url_up_id?if_exists}')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden id="code_value" name="code_value"/>
  <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
  <@s.hidden id="search_area_attr" name="area_attr_s"/>
  <@s.hidden  name="modtype_s"/>
  <@s.hidden  name="attr_type_s"/> 
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Categoryattr_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
    <tr>
		<td class="searchDiv_td">属性名:</td>
		<td><@s.textfield name="attr_name_s"/></td>
	</tr>
	<tr>
		<td class="searchDiv_td">所属模块:</td>
		<td>		
			<@s.select id="modletype" name="modtype_s"  list="moduleList" listValue="module_name" listKey="module_type"  headerKey=""  headerValue="请选择"/> 
		</td>
	</tr>	
	<tr>
		<td class="searchDiv_td">所属分类:</td>
		<td><div id="divlist"></div></td>
	</tr>
	<tr>
		<td class="searchDiv_td">属性类型:</td>
		<td><@s.select name="attr_type_s" list=r"#{'0':'单行文本','1':'多行文本','2':'单选框','3':'复选框','4':'图片上传','5':'附件','6':'WEB编辑器','7':'日期'}" headerKey=""  headerValue="请选择"/>  </td>
	</tr>
	<tr>
		<td class="searchDiv_td">是否必填:</td>
		<td><@s.select name="is_must_s" list=r"#{'0':'不必填','1':'必填'}" headerKey=""  headerValue="请选择"/>  </td>
	</tr>
		<tr>
			<td align="center" colspan="2" style="border:0px;">
				<input type="button" name="search" value="搜索" onclick="showSearchDiv('area_attr','cat_attr','search_area_attr','search_cat_attr','form_search_id');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			<script>
				function showSelectDiv(){
					var cat_attr = document.getElementsByName('cat_attr');
					var cat_attr_str = '';
					for(var i=0;i<cat_attr.length;i++){
						if(cat_attr[i].value!='0'){
							cat_attr_str += cat_attr[i].value+',';
						}
					}
					$("#search_cat_attr").val(cat_attr_str);
					document.forms[0].submit();
				}
			</script>	
			</td>
	   </tr>
		</table>
		<!--搜索框隐藏域存放-->
		    <@s.hidden id="url_up_id" name="url_up_id"/>
		    <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
		    <@s.hidden id="search_area_attr" name="area_attr_s"/>
			<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
			<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		</@s.form>
	</div>		   
<!--搜索区域结束-->
 	<script type="text/javascript">
	  $(document).ready(function(){ 
         //加载分类
         cate_select(${cfg_topcatid?if_exists},1, $("#modletype").val()); 
         //select事件触发  
         $("#modletype").change(function(){
			   $('option:selected', this).each(function(){
			        $("#divlist").html("");
                    cate_select(${cfg_topcatid?if_exists},1,this.value);                   	
			   });  	
		  });     
	  });
	</script>
  </body>
</html>