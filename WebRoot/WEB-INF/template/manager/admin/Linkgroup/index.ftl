<html>
  <head>
    <title>友情链接分组管理</title>
       <script type="text/javascript" src="/include/admin/js/linkgroup.js"></script> 
  </head>
  <body>
<@s.form action="/admin_Linkgroup_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：网站管理 > 友情链接 > 分组管理</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		    <th width="3%" > <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('link_group.id')"/></th>
		    <th width="97%"  >友情链接分组名称</th>
	  </tr>
	  
	  <#list link_groupList as link_group>
	    <tr >
		    <td><input type="checkbox" name="link_group.id" value="${link_group.id?if_exists}"/></td>
		    <td align="center">
				<@s.textfield name="link_group.name" value="${link_group.name?if_exists}" id="${link_group.id}" cssClass="txtinput" maxLength="20"/>
		    </td>
		     <@s.fielderror id="groupnamefiled"><@s.param>link_group.name</@s.param></@s.fielderror>
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
     <input type="button" class="rbut" onclick="showSearch(this,'searchDiv');"  value="添加分组">
     <input type="button" class="rbut"onclick="delInfo('link_group.id','/admin_Linkgroup_delete.action')"value="删除">
     <input type="button" class="rbut"onclick="updateLinkgroup( '/admin_Linkgroup_updateGroupname.action');"value="修改">
     <input type="button" class="rbut"onclick="linkToInfo('/admin_Link_list.action','');" value="链接管理">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="admin_link_group_id" id="admin_linkgroup_id"/>
  <@s.hidden name="name" id="name"/>
  <@s.hidden  name="cat_attr_s"/>
  <@s.hidden id="search_area_attr" name="area_attr_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Linkgroup_insert.action" method="post"  id="form_search_id" validate="true" onsubmit="return check();">
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td class="searchDiv_td">友情链接分组名称:</td>
			<td>
				<@s.textfield id="groupname" name="link_group.name" value="" maxLength="20" cssClass="txtinput"/>
             	<span id="nameerror"></span>
             </td>
		</tr>
		 <tr> 
	           <@s.token/>
		       <@s.hidden name="name"/>
		       ${listSearchHiddenField?if_exists}
		       <td></td>
		       <td><@s.submit value="保存" />
		      <input type="button" name="close" value="关闭" onclick="closeSearch();"/></td>
		  </tr>
		</table>
		<!--搜索框隐藏域存放-->
		    <@s.hidden name="mall_type" />
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>
