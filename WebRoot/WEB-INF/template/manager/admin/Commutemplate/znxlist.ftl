<html>
  <head>
    <title>站内信模板</title>   
  </head>
 <body>
<@s.form action="/admin_Commutemplate_znxlist.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：会员管理 > 通讯模板管理 > 站内信模板</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>模板名称:</td>
			<td><@s.textfield name="temp_name_s" cssStyle="width:200px;"/></td>
	        <td><input type="submit" class="rbut" value="查询"></td>
	      </tr>
     </table>
   </div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
		      <tr >
		   
		         <th width="23%">模板代码</th>
		         
		     	 <th width="72%" >模板名称</th>
		    
		         <th width="5%" >修改</th>
      </tr>
	  
		 <#list commutemplateList as commutemplate>
		  <tr>
		     
		 	    <td align="center">${commutemplate.temp_code?if_exists}</td>
		    
		    	<td align="center">${commutemplate.temp_name?if_exists}</td>
		    
		          
		    <td align="center"><a onclick="linkToInfo('/admin_Commutemplate_znxview.action','commutemplate.temp_id=${commutemplate.temp_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>

