<html>
  <head>
    <title>会员登陆账号信息表列表</title>   
  </head>
<body>
<@s.form action="/admin_Memberuser_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：输入当前位置</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>标题:</td>
			<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
			 <td class="tdpad">域名类型:</td>
			<td>
				<@s.select name="domaintype_s" list=r"#{'':'请选择','0':'一级域名','1':'二级域名'}" />
			</td>
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
			 <td width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('memberuser.user_id')"/></td>
	
	     	 <td width="5%">user_id</td>
	    
	     	 <td width="5%">cust_id</td>
	    
	     	 <td width="10%">user_type</td>
	    
	     	 <td width="10%">user_name</td>
	    
	     	 <td width="10%">passwd</td>
	    
	     	 <td width="10%">role_code</td>
	    
	     	 <td width="10%">email</td>
	    
	     	 <td width="10%">real_name</td>
	    
	     	 <td width="10%">sex</td>
	    
	     	 <td width="10%">cellphone</td>
	    
	     	 <td width="10%">phone</td>
	    
	     	 <td width="10%">qq</td>
	    
	     	 <td width="10%">msn</td>
	    
	     	 <td width="10%">skype</td>
	    
	     	 <td width="10%">pass_strength</td>
	    
	     	 <td width="10%">login_time</td>
	    
	         <td width="2%" align="center" class="top_td">修改</td>
	     </tr>
	  
	  <#list memberuserList as memberuser>
		  <tr>
		    <td><input type="checkbox" name="memberuser.user_id" value="${memberuser.user_id?if_exists}"/></td>    
		 	
		    	<td align="center">${memberuser.user_id?if_exists}</td>
		    
		    	<td align="center">${memberuser.cust_id?if_exists}</td>
		    
		    	<td align="center">${memberuser.user_type?if_exists}</td>
		    
		    	<td align="center">${memberuser.user_name?if_exists}</td>
		    
		    	<td align="center">${memberuser.passwd?if_exists}</td>
		    
		    	<td align="center">${memberuser.role_code?if_exists}</td>
		    
		    	<td align="center">${memberuser.email?if_exists}</td>
		    
		    	<td align="center">${memberuser.real_name?if_exists}</td>
		    
		    	<td align="center">${memberuser.sex?if_exists}</td>
		    
		    	<td align="center">${memberuser.cellphone?if_exists}</td>
		    
		    	<td align="center">${memberuser.phone?if_exists}</td>
		    
		    	<td align="center">${memberuser.qq?if_exists}</td>
		    
		    	<td align="center">${memberuser.msn?if_exists}</td>
		    
		    	<td align="center">${memberuser.skype?if_exists}</td>
		    
		    	<td align="center">${memberuser.pass_strength?if_exists}</td>
		    
		    	<td align="center">${memberuser.login_time?if_exists}</td>
		          
		    <td align="center"><a onclick="linkToInfo('/admin_Memberuser_view.action','memberuser.user_id=${memberuser.user_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="delInfo('memberuser.user_id','/admin_Memberuser_delete.action')" value="删除">
     <input type="button" class="rbut" onclick="updateRecomState('1','memberuser.user_id','/admin_Memberuser_updateisrecom.action','admin_memberuser_state');" value="推荐">
     <input type="button" class="rbut" onclick="updateRecomState('0','memberuser.user_id','/admin_Memberuser_updateisrecom.action','admin_memberuser_state');" value="不推荐">
  
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


