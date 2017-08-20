<html>
  <head>
    <title>关键字管理</title>
  </head>
  <body>
<@s.form action="/admin_Keyword_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：网站管理 > 基本功能 > 关键字管理 </div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>关键字标题:</td>
			<td><@s.textfield name="key_name_s"  cssStyle="width:245px;"/></td>
			<td>类型:</td>
			<td>
				<@s.select name="module_type_s" list="commparaList" listValue="module_name" listKey="module_type" headerKey="" headerValue="请选择"/>
			    <@s.fielderror><@s.param>keyword.module_type</@s.param></@s.fielderror>
			</td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		    <th width="3%" > <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('keyword.key_id')"/></th>
		    <th width="37%"  >关键字标题</th>
		    <th width="10%"  >类型</th>
		    <th width="10%"  >搜索频率</th>
		    <th width="10%"  >前台显示</th>
		    <th width="15%"  >搜索IP</th>
		    <th width="10%"  >搜索时间</th>
		    <th width="5%"  >操作</th>
	  </tr>
	  
	  <#list keywordList as keyword>
	    <tr >
		    <td><input type="checkbox" name="keyword.key_id" value="${keyword.key_id?if_exists}"/></td>
		    <td align="center">
				<a onclick="linkToInfo('/admin_Keyword_view.action','keyword.key_id=${keyword.key_id?if_exists}');">${keyword.key_name?if_exists}</a>
		    </td>
		    
		    <td align="center">
				${(keyword.module_name)?if_exists}
		    </td>
		    <td align="center">
				${keyword.num?if_exists}
		    </td>
		    
		     <td align="center">
				<#if (keyword.is_show)?if_exists=="0">
				  <font color='green'>显示</font>
				<#else>
				 <font color='red'>隐藏</font>
				</#if>
		    </td>
		    
		     <td align="center">
				${(keyword.m_ip)?if_exists}
		    </td>
		    
		     <td align="center">
				${(keyword.in_date)?if_exists}
		    </td>
		    <td align="center">
				<a onclick="linkToInfo('/admin_Keyword_view.action','keyword.key_id=${keyword.key_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Keyword_add.action','');" value="添加关键字">
     <input type="button" class="rbut"onclick="delInfo('keyword.key_id','/admin_Keyword_delete.action')" value="删除">
      <input type="button" class="rbut" onclick="updateBatchState('0','keyword.key_id','/admin_Keyword_updateIsshow.action','显示');" value="显示">
      <input type="button" class="rbut" onclick="updateBatchState('1','keyword.key_id','/admin_Keyword_updateUnshow.action','隐藏');" value="隐藏">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
<@s.hidden name="keyword.key_id"/>
 <!--表单框隐藏域存放-->  
</@s.form>

  </body>
</html>
