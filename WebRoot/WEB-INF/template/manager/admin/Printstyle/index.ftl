<html>
  <head>
    <title>运单管理</title>   
  </head>
<body>
<@s.form action="/admin_Printstyle_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:商城管理 > 配送管理 > 运单管理</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
   
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
       <th width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('printstyle.trade_id')"/></th>
	    <th width="77%" >模板名称</th>
	    
	    <th width="20%" >操作</th>
	    
	  </tr>
	  
	  <#list printstyleList as printstyle>
	  <tr>
	    <td><input type="checkbox" name="printstyle.trade_id" value="${printstyle.trade_id?if_exists}"/></td>
	    <td align="center">${printstyle.template_name?if_exists}</td>
	          
	    <td align="center"><a onclick="linkToInfo('/admin_Printstyle_view.action','printstyle.trade_id=${printstyle.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
	    
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Printstyle_add.action','');" value="添加">
	</div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
<!--表单框隐藏域存放-->  
</@s.form>

<!--搜索区域开始-->
<!--搜索区域结束-->
  </body>
</html>

