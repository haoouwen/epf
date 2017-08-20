<html>
  <head>
    <title>敏感字管理</title>
  </head>
<body>
<@s.form action="/admin_Filterword_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：系统管理 > 系统管理 > 敏感字管理 </div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>敏感字:</td>
			<td><@s.textfield name="name_s" maxLength="20"/></td>
			 <td class="tdpad">替换字:</td>
			 <td><@s.textfield name="rep_name_s" maxLength="20"/></td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		    <th width="3%" >  <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('filterword.word_id')"/></th>
		    <th width="45%"  >敏感字</th>
		    <th width="45%"  >替换字</th>
		    <th width="7%"  >操作</th>
	  </tr>
	  
	   <#list filterwordList as word>
	    <tr >
		    <td> <input type="checkbox" name="filterword.word_id" value="${word.word_id?if_exists}"/></td>
		    <td align="center">
				${word.name?if_exists}
		    </td>
		    <td align="center">
				${word.rep_name?if_exists}
		    </td>
		    <td align="center">
			<a onclick="linkToInfo('/admin_Filterword_view.action','filterword.word_id=${word.word_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Filterword_add.action','');"value="添加">
     <input type="button" class="rbut"onclick="delInfo('filterword.word_id','/admin_Filterword_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
 <!--表单框隐藏域存放-->  
</@s.form>

  </body>
</html>
