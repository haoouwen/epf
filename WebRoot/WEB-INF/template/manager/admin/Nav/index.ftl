<html>
  <head>
    <title>导航管理</title>
        <script type="text/javascript" src="/include/admin/js/nav.js"></script> 
  </head>
<body>
<@s.form action="/admin_Nav_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:网站管理 > 基本功能 > 导航管理</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			   <td class="tdpad">导航名称：</td>
			   <td><@s.textfield name="nav_name_s" cssStyle="width:245px;"/></td>
			   <td class="tdpad">导航链接地址：</td>
			   <td><@s.textfield name="link_url_s" cssStyle="width:245px;"/></td>
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
	    <th width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('nav.nav_id')"/></th>
	    <th width="10%">排序</th>
	    <th width="20%">导航名称</th>
	    <th width="40%">导航链接地址</th>
	    <th width="10%">导航位置</th>
	    <th width="7%">导航图标</th>
	    <th width="7%">是否显示</th>
	    <th width="7%">修改</th>
      </tr>
	  
	  <#list navList as nav>
  <tr>
    <td><input type="checkbox" name="nav.nav_id" value="${nav.nav_id?if_exists}"/></td>
    <td align="center"><input name="isort_no" value="${nav.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" /></td>
    <td align="center">${nav.nav_name?if_exists}</td>
    <td align="center">${nav.link_url?if_exists}</td>
    <td align="center">
	    <a onclick="linkToInfo('/admin_Nav_list.action','nav_post_s=${nav.nav_post?if_exists}');">
	    	<#if nav.nav_post?if_exists=='1'>
	    		<font color='red'>上部</font>
	    	</#if>
	    	<#if nav.nav_post?if_exists=='2'>
	    		<font color='blue'>中部</font>
	    	</#if>
	    	<#if nav.nav_post?if_exists=='3'>
	    		<font color='green'>下部</font>
	    	</#if>
	    </a>
    </td>
    <td aling="center">
    <#if nav.nav_icon?if_exists=='0'>
      - -
     </#if>
    <#if nav.nav_icon?if_exists=='1' >
      <font color="red">新品</font>
    </#if>
    <#if nav.nav_icon?if_exists=='2' >
      <font color="red">热门</font>
    </#if> 
     <#if nav.nav_icon?if_exists=='3' >
      <font color="red">折扣</font>
    </#if>
    </td>
    <td align="center">
	    <a onclick="linkToInfo('/admin_Nav_list.action','isshow_s=${nav.isshow?if_exists}');">
	    	<#if nav.isshow?if_exists=='0'>
	    		<font color='green'>显示</font>
	    	<#else>
	    		<font color='red'>隐藏</font>
	    	</#if>
	    </a>
    </td>
    <td align="center">
    <a onclick="linkToInfo('/admin_Nav_view.action','nav.nav_id=${nav.nav_id?if_exists}');">
    		<img src="/include/common/images/bj.gif" />
    </a>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Nav_add.action','');" value="添加导航">
     <input type="button" class="rbut" onclick="delInfo('nav.nav_id','/admin_Nav_delete.action')" value="删除">
     <input type="button" class="rbut" onclick="updateSort('nav.nav_id','isort_no','/admin_Nav_updateSort.action');" value="排序">
     <input type="button" class="rbut" onclick="updateBatchState('0','nav.nav_id','/admin_Nav_updateIsshow.action','显示');" value="显示">
     <input type="button" class="rbut" onclick="updateBatchState('1','nav.nav_id','/admin_Nav_updateUnshow.action','隐藏');" value="隐藏">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="nav.isshow" id="admin_nav_state"/>
  <@s.hidden name="admin_nav_id" id="admin_nav_id"/>
  <@s.hidden name="nav_post_s"/>
  <@s.hidden name="isshow_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Nav_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td class="searchDiv_td">导航名称：</td>
			<td><@s.textfield name="nav_name_s"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">导航链接地址：</td>
			<td><@s.textfield name="link_url_s"/></td>
		</tr>
		<tr>
            <td class="searchDiv_td">导航位置：</td>
            <td>
             	<@s.select name="nav_post_s" list=r"#{'1':'上部','2':'中部','3':'下部'}" headerKey="" headerValue="请选择"/>  
            </td>
        </tr>
	   	<tr>
	         <td class="searchDiv_td">是否显示：</td>
	          <td>
	           <@s.select name="isshow_s" list=r"#{'0':'显示','1':'不显示'}" headerKey="" headerValue="请选择"/>  
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
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>









