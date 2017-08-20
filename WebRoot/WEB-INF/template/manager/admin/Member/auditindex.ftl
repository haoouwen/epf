<html>
  <head>
    <title>审核会员列表</title>   
     <script type="text/javascript" src="/include/js/getcatarea.js"></script>
  </head>
<body>
<@s.form action="/admin_Member_auditList.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:会员管理 > 会员管理 > 审核会员列表 </div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td align="right">会员名称:</td> 
			<td><@s.textfield name="cust_name_s"  cssStyle="width:200px;"/></td>
	        <td><input type="submit" class="rbut" value="查询"></td>
	      </tr>
     </table>
   </div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
      
		     <th width="3%"  ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('member.cust_id')"/></th>
	    
	     	 <th width="35%" >会员名称</th>
	    
	     	 <th width="30%" >用户名</th>
	    
	     	 <th width="10%" >买家级别</th>
	    
	     	 <th width="15%" >状态</th>
	    
	         <th width="7%"  >操作</th>
	  </tr>
	  
	  <#list memberList as member>
	  <tr>
	    <td><input type="checkbox" name="member.cust_id" value="${member.cust_id?if_exists}"/></td>    
	 		<td align="center">${member.cust_name?if_exists}</td>
	 		<td align="center">${member.user_name?if_exists}</td>
	        <td align="center">${member.buy_level_name?if_exists}</td>
	        <td align="center">
	         <#list infoStateList as infoState>
				<#if member.info_state?if_exists==infoState.para_value>
					<#if infoState.para_value?if_exists='0'>
				       <font class="redcolor">${infoState.para_key?if_exists}</font>
				    <#elseif infoState.para_value?if_exists='2'>
				        <font class="bluecolor">${infoState.para_key?if_exists}</font>
					<#elseif infoState.para_value?if_exists='1'>
				        <font class='greencolor'>${infoState.para_key?if_exists}</font>
					<#elseif infoState.para_value?if_exists='3'>
					    <font class="redcolor">${infoState.para_key?if_exists}</font>
				    </#if>
				    <#break/>
			    </#if>
			 </#list>
		   </td>  	
	          
	    <td align="center"><a onclick="linkToInfo('/admin_Member_audit.action','member.cust_id=${member.cust_id?if_exists}');"><img src="/include/common/images/audit.png" /></a></td>
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
     <input type="button" class="rbut" onclick="delInfo('member.cust_id','/admin_Member_auditdelete.action')" value="删除">
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

