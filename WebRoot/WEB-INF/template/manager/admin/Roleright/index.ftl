<html>
  <head>
    <title>操作权限管理</title>
    <link href="/include/admin/css/role.css" rel="stylesheet" type="text/css">
    <script src="/include/common/js/commonplugin.js" type="text/javascript"></script>
    <script type="text/javascript">
		$(function(){
				$("#sysmenuTab").mallTab({
			});
		})
	</script>	
  </head>
<body>
<@s.form action="/admin_Roleright_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:系统管理 > 帐号管理 > 操作权限管理</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   
<!--条件结束-->
<!--后台table-->
    <div id="sysmenuTab">
                <div class="tabbar" >
		       		 <ul>
				       <#list menuoneList as menuone>
						   	<li  <#if menuone_index==0> class="selected"</#if>><span class="chk"></span>${(menuone.menu_name)?if_exists}</li>
					   </#list>
				     </ul>
		       </div>
             
	          <#list menuoneList as menuone>
	             <div class="tabdiv"  <#if menuone_index==0>style="display:block;"</#if>>
		             <#list menutowList as menutow>
				         	 <#if menuone.menu_id==menutow.up_menu_id>
				         	 	<div class="towleve">
		                          	  <div class="towleve_one_menu"><b>${menutow.menu_name?if_exists}:</b></div>
			                          <#list menuthreeList as menuthree>
			                          		<#if menuthree.up_menu_id = menutow.menu_id>
					                            <div class="threeleve" style="width:100%;">
						                            <ul class="rtable">
						                                <li >
						                                	<table  class="indexTab">
						                                		<tr>
						                                			<td  width="145px"><b>${menuthree.menu_name?if_exists}</b></td>
						                                			<td>
						                                			<#assign x=0>	
						                                			<#list rolerightList as roleright>
									                                	<#if menuthree.menu_id==roleright.menu_attr>
									                                		<#assign x=x+1>
									                                		<input type="checkbox" name="roleright.right_id" value="${roleright.right_id?if_exists}"/><span onclick="linkToInfo('/admin_Roleright_view.action','roleright.right_id=${roleright.right_id?if_exists}');"> ${roleright.right_name?if_exists}  |  </span>
									                                		<#if x%10==0>
									                                			<br/>
									                                		</#if>
									                                	</#if>
								                                	</#list>
						                                			</td>
						                                		</tr>
						                                	</table>
						                                </li>
						                            </ul>
					            				</div>
			            					</#if>
			            			</#list>
			            		</div>		
		    				</#if>
					 </#list>
	             </div>
         		</#list>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Roleright_add.action','');" value="添加权限">
     <input type="button" class="rbut" onclick="delInfo('roleright.right_id','/admin_Roleright_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>
