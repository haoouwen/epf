<html>
  <head>
  <title>友情链接管理</title>
  <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
  <script type="text/javascript">
	  $(document).ready(function(){       
         //所属地区的回选
         loadArea("${areaIdStr?if_exists}","");
	  });
   </script>
  </head>
<body>
<@s.form action="/admin_Link_list.action" method="post"  id="indexForm">
<@s.hidden name="link.is_display" id="admin_link_state"/>
<!--当前位置-->
	<div class="postion">当前位置：网站管理 > 友情链接 > 友情链接 </div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>友情链接标题:</td>
			<td><@s.textfield name="link_name_s"  cssStyle="width:245px;"/></td>
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
		    <th width="3%" > <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('link.link_id')"/></th>
		    <th width="25%"  >友情链接标题</th>
		    <th width="15%"  >分组类型</th>
		    <th width="20%"  >地区</th>
		    <th width="25%"  >链接地址</th>
		    <th width="5%"  >是否显示</th>
		    <th width="7%"  >操作</th>
	  </tr>
	  
	  <#list linkList as link>
	    <tr >
		    <td><input type="checkbox" name="link.link_id" value="${link.link_id?if_exists}"/></td>
		    <td align="center">
				<a onclick="linkToInfo('/admin_Link_view.action','link.link_id=${link.link_id?if_exists}');">${link.link_name?if_exists}</a>
		    </td>
		    <td align="center">
				<a onclick="linkToInfo('/admin_Link_list.action','link_group=${link.link_group?if_exists}');">${link.name?if_exists}</a>
		    </td>
		    <td align="center">
				${link.area_attr?if_exists}
		    </td>
		    <td align="center">
				<a href="${link.url?if_exists}" target="_blank">${link.url?if_exists}</a>
		    </td>
		    <td align="center">
				<#if link.is_display?if_exists=='0'>
    				<a onclick="linkToInfo('/admin_Link_list.action','is_display_s=${link.is_display?if_exists}');">
				    <font color='green'>显示</font></a>
				    <#else>
				    <a onclick="linkToInfo('/admin_Link_list.action','is_display_s=${link.is_display?if_exists}');">
				    <font color='red'>隐藏</font></a>
				 </#if>
		    </td>
		    <td align="center">
				<a onclick="linkToInfo('/admin_Link_view.action','link.link_id=${link.link_id?if_exists}');">
           		<img src="/include/common/images/bj.gif" /></a>
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
      <input type="button" class="rbut" onclick="linkToInfo('/admin_Link_add.action','');" value="添加链接">
      <input type="button" class="rbut"onclick="delInfo('link.link_id','/admin_Link_delete.action')"value="删除">
      <input type="button" class="rbut"onclick="updateBatchState('0','link.link_id','/admin_Link_updateIsshow.action','显示');" value="显示">
      <input type="button" class="rbut"onclick="updateBatchState('1','link.link_id','/admin_Link_updateUnshow.action','隐藏');"value="隐藏">
      <input type="button" class="rbut"onclick="linkToInfo('/admin_Linkgroup_list.action','name=${name?if_exists}');"value="分组管理">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="area_attr_s"/>
  <@s.hidden name="url_s"/>
  <@s.hidden name="is_display_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Link_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td class="searchDiv_td">友情链接标题:</td>
			<td><@s.textfield name="link_name_s"  maxLength="20"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">链接地址:</td>
			<td><@s.textfield name="url_s" maxLength="100"  /></td>
		</tr>
		<tr>
			<td class="searchDiv_td">地区:</td>
			<td><div id="areaDiv" ></div></td>
		</tr>
		<tr>
			<td class="searchDiv_td">是否显示:</td>
			<td><@s.select name="is_display_s" list=r"#{'0':'显示','1':'不显示'}" headerKey="" headerValue="请选择"/>  </td>
		</tr>
		<tr>
			<td align="center" colspan="2" style="border:0px;">
				<input type="button" name="search" value="搜索" onclick="showSearchDiv('area_attr','cat_attr','search_area_attr','search_cat_attr','form_search_id');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			</td>
	   </tr>
		</table>
		<!--搜索框隐藏域存放-->
		    <@s.hidden id="search_area_attr" name="area_attr_s"/>
			<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>
