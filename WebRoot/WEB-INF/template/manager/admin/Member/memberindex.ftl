<html>
  <head>
    <title>内部员工列表</title>   
  </head>
  <body>
  

<body>
<@s.form action="/admin_Member_membernalList.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:会员管理 > 会员管理 > >内部员工列表</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td >会员名称:</td> 
			<td><@s.textfield name="cust_name_s"  cssStyle="width:150px;"/></td>
	        <td><input type="submit" class="rbut" value="查询"></td>
	      </tr>
     </table>
   </div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
	     <th width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('member.cust_id')"/></th>
   
    
     	 <th width="30%"  >会员名称</th>

     	 <th width="30%"  >用户名</th>
    
     	 <th width="15%"  >买家级别</th>
    
     	 <th width="12%"  >状态</th>
    
         <th width="10%" >操作</th>
  </tr>
  
  <#list memberList as member>
  <tr>
    <td><input type="checkbox" name="member.cust_id" value="${member.cust_id?if_exists}"/></td> 
 		<td align="center">${member.cust_name?if_exists}</td>
 		<td align="center">${member.user_name?if_exists}</td>
        <td align="center">${(member.buy_level_name)?if_exists}</td>
        <td align="center">
	    	<#list infoStateList as infoState>
				<#if member.info_state?if_exists==infoState.para_value>
					<#if infoState.para_value?if_exists='1'>
				        <font class='greencolor'>${infoState.para_key?if_exists}</font>
					<#elseif infoState.para_value?if_exists='3'>
					    <font class="redcolor">${infoState.para_key?if_exists}</font>
					<#elseif infoState.para_value?if_exists='0'>
				       <font class="redcolor">${infoState.para_key?if_exists}</font>
				    <#elseif infoState.para_value?if_exists='2'>
				        <font class="bluecolor">${infoState.para_key?if_exists}</font>
				    </#if>
				    <#break/>
			    </#if>
			</#list>
	    </td>	
	    	 
          
    <td align="center">
    	 <a onclick="linkToInfo('/admin_Member_view.action','member.cust_id=${member.cust_id?if_exists}&is_nal=1');"><img src="/include/common/images/bj.gif" /></a>
         <a onclick="linkToInfo('/admin_Memberinter_list.action','custId=${member.cust_id?if_exists}&memberinter_name_s=${member.cust_name?if_exists}&two_pages_link=no&enter=0');" title="会员积分管理"><img src="/include/admin/images/inter.gif" /></a>
         <a onclick="linkToInfo('/admin_Memberfund_list.action','cust_id=${member.cust_id?if_exists}&memberfund_name_s=${member.cust_name?if_exists}&two_pages_link=no&enter=0');" title="会员余额管理"><img src="/include/admin/images/money.gif" /></a>
    </td>
  </tr>
  </#list>
      </tr>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Member_add.action','');" value="添加会员">
     <input type="button" class="rbut" onclick="delInfo('member.cust_id','/admin_Member_delMember.action')" value="删除">
   
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
  <@s.hidden id="search_area_attr" name="area_attr_s"/>
  <@s.hidden name="nav_post_s"/>
  <@s.hidden name="isshow_s"/>
   <@s.token/> 
   <@s.hidden name="memberuser.user_id" value="${(memberuser.user_id)?if_exists}"/>
 <!--表单框隐藏域存放-->  
</@s.form>

  </body>
</html>




</body>
</html>

