<html>
  <head>
    <title>记录会员密保信息列表</title>   
  </head>
<body>
<@s.form action="/admin_Memprotect_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:1 > 2 > 3</div>
<!--当前位置结束-->
<!--条件查询-->

<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
      
	   	<td width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('memprotect.id')"/></td>
   
     	 <td width="25%">id</td>
    
     	 <td width="25%">cust_id</td>
    
     	 <td width="20%">question</td>
    
     	 <td width="20%">answer</td>
    
         <td width="7%">修改</td>
         
      </tr>
	  
	  <#list memprotectList as memprotect>
		  <tr>
		    <td><input type="checkbox" name="memprotect.id" value="${memprotect.id?if_exists}"/></td>    
		 	
		    	<td align="center">${memprotect.id?if_exists}</td>
		    
		    	<td align="center">${memprotect.cust_id?if_exists}</td>
		    
		    	<td align="center">${memprotect.question?if_exists}</td>
		    
		    	<td align="center">${memprotect.answer?if_exists}</td>
		          
		    <td align="center"><a onclick="linkToInfo('/admin_Memprotect_view.action','memprotect.id=${memprotect.id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
   
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Memberuser_cate.action','');" value="添加会员登陆账号信息表">
     <input type="button" class="rbut" onclick="delInfo('memprotect.id','/admin_Memberuser_delete.action')" value="删除">
     <input type="button" class="rbut" onclick="updateRecomState('1','memprotect.id','/admin_Memberuser_updateisrecom.action','admin_memberuser_state');" value="推荐">
     <input type="button" class="rbut" onclick="updateRecomState('0','memprotect.id','/admin_Memberuser_updateisrecom.action','admin_memberuser_state');" value="不推荐">
  
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

