<html>
  <head>
    <title>会员信息</title>
    <script type="text/javascript">  
       function selectvalue(id){
         opener.setid(id);
         window.close();
        }
</script>
  </head>
  <body>
<@s.form action="/admin_Advinfo_indexinfo.action" method="post"  id="indexForm">
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>信息名称:</td>
			<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		    <th wihth="20%" align="center" >Id</th>
		    <th wihth="20%"  align="center" >信息名称</th>
		    <th wihth="20%"  align="center" >选择</th>
	      </tr>
  <#list intrList as intr>
  <tr>
    <td align="center">${intr.id?if_exists}</td>
    <td align="center">${intr.title?if_exists}</td>
    <td align="center"><input type="button" name="info_id" value="选择" onclick="selectvalue('${intr.id?if_exists}');"/></td>
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
</div>
</@s.form>
</body>
</html>