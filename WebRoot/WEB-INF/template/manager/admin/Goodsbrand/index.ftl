<html>
  <head>
    <title>品牌管理列表</title>   
	 <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
	 <script type="text/javascript">
	  $(document).ready(function(){
	       //所属分类的回选
	       loadCat("${catIdStr?if_exists}","","","goods");
	   });
	 </script>
  </head>
  <body>
<@s.form action="/admin_Goodsbrand_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：商品管理 > 品牌管理 > 品牌管理列表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>品牌名称:</td>
			<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
	        <td><input type="submit" class="rbut" value="查询"></td>
	        <td><input type="button" onclick="searchShowDIV('searchDiv','300px','220px');" class="rbut" value="高级查询"></td>
	        <td><input type="button" class="rbut" onclick="exprotgoodsbrandExcel();"  value="导出品牌"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		    <th width="3%"> <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('goodsbrand.brand_id')"/></th>
		     <th width="5%">ID</th>
		    <th width="7%">排序</th>
		      <th width="10%">品牌LOGO</th>
		    <th width="25%">品牌名称</th>
		    <th width="25%">所属分类</th>
		    <th width="8%">状态</th>
		    <th width="10%">操作</th>
	  </tr>
	  
	  <#list goodsbrandList as goodsbrand>
	    <tr >
		    <td><input type="checkbox" name="goodsbrand.brand_id" value="${goodsbrand.brand_id?if_exists}"/></td>
		     <td align="center">
				${goodsbrand.brand_id?if_exists}
		    </td>
		    <td align="center">
				<input name="isort_no" value="${goodsbrand.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" />
		    </td>
		      <td align="center">
		        <a onclick="linkToInfo('/admin_Goodsbrand_view.action','goodsbrand.brand_id=${goodsbrand.brand_id?if_exists}');">
				<img src="${goodsbrand.logo?if_exists}"  width="70px" height="25px" /></a>	
		    </td>
		     <td align="center">
		        <a onclick="linkToInfo('/admin_Goodsbrand_view.action','goodsbrand.brand_id=${goodsbrand.brand_id?if_exists}');">
				${goodsbrand.brand_name?if_exists}</a>
		    </td>
		     <td align="center">
				<#if goodsbrand.goods_attr?if_exists? length lt 40>
		    		${goodsbrand.goods_attr?if_exists}
				<#else>
					${goodsbrand.goods_attr[0..39]?if_exists}...
				</#if>
		    </td>
		    <td align="center">
			<#list infoStateList as infoState>
				<#if goodsbrand.info_state?if_exists==infoState.para_value>
					<#if infoState.para_value?if_exists='1'>
				        <font class='greencolor'>${infoState.para_key?if_exists}</font>
					<#elseif infoState.para_value?if_exists='3'>
					    <font class="redcolor">${infoState.para_key?if_exists}</font>
					<#elseif infoState.para_value?if_exists='0'>
				       <font class="redcolor">${infoState.para_key?if_exists}</font>
				    <#elseif infoState.para_value?if_exists='2'>
				        <font class="bluecolor">${infoState.para_key?if_exists}</font>
				    </#if>
				    <#break/>
			    </#if>
			 </#list>
		    	
		    	
		    </td>
		     <td align="center">
				<a onclick="linkToInfo('/admin_Goodsbrand_view.action','goodsbrand.brand_id=${goodsbrand.brand_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Goodsbrand_add.action','');" value="添加品牌">
     <input type="button" class="rbut"onclick="delInfo('goodsbrand.brand_id','/admin_Goodsbrand_delete.action')" value="删除">
    <input type="button" class="rbut"onclick="updateSort('goodsbrand.brand_id','isort_no','/admin_Goodsbrand_updateSort.action');" value="排序">
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
<@s.hidden   name="cat_attr_s"/>
 <!--表单框隐藏域存放-->  
</@s.form> 
<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Goodsbrand_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
			
			<tr>
				<td class="searchDiv_td">品牌名称:</td>
				<td><@s.textfield name="title_s"  /></td>
			</tr>
			
			<tr>
				<td class="searchDiv_td">所属分类:</td>
				<td  width="330px">
				  	<div class="morecatdiv">
			          	<div id="catDiv" ></div>
		            </div>
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
		    <@s.hidden id="search_cat_attr"  name="cat_attr_s"/>
			<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>
