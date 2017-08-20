<html>
  <head>
    <title>添加角色</title>
    <link href="/include/admin/css/role.css" rel="stylesheet" type="text/css">
    <script src="/include/common/js/commonplugin.js" type="text/javascript"></script>
    <script src="/include/admin/js/role.js" type="text/javascript"></script>
    <script type="text/javascript">
		$(function(){
			$("#sysmenuTab").mallTab({});
		})
	</script>
  </head>
  <body>
<@s.form action="/admin_Role_insert.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
当前位置:系统管理 > 帐号管理 > 角色管理 > 添加角色
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 <td class="table_name">角色名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield id="tf_name" name="role.role_name" cssClass="txtinput" maxlength="20"/>
             	<@s.fielderror><@s.param>role.role_name</@s.param></@s.fielderror>             	            
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">菜单权限<font color='red'>*</font></td>
             <td  id="sysmenuTab">
                <div class="tabbar">
		       		 <ul>
				       <#list menuoneList as menuone>
						   	<li <#if menuone_index==0> class="selected"</#if>><span class="chk"><input class="menu_right" type="checkbox" name="ro_menu_right" id="${menuone.menu_id?if_exists}"  value="${menuone.menu_id?if_exists}" /></span>${(menuone.menu_name)?if_exists}</li>
					   </#list>
				     </ul>
		       </div>
             
	          <#list menuoneList as menuone>
	             <div class="tabdiv"  <#if menuone_index==0>style="display:block;"</#if>>
		             <#list menutowList as menutow>
				         	 <#if menuone.menu_id==menutow.up_menu_id>
				         	 	<div class="towleve">
		                          	  <div class="towleve_one_menu"><b><input type="checkbox"  class="menu_right" name="ro_menu_right"  id="${menuone.menu_id?if_exists}${menutow.menu_id?if_exists}" value="${menutow.menu_id?if_exists}"/>${menutow.menu_name?if_exists}:</b></div>
			                          <#list menuthreeList as menuthree>
			                          		<#if menuthree.up_menu_id = menutow.menu_id>
					                            <div class="threeleve">
						                            <ul>
						                                <li>
						                                	<table>
						                                		<tr>
						                                			<td  width="145px"><b><input  class="menu_right" type="checkbox" value="${menuthree.menu_id?if_exists}" name="ro_menu_right" id="${menuone.menu_id?if_exists}${menutow.menu_id?if_exists}${menuthree.menu_id?if_exists}"/>${menuthree.menu_name?if_exists}</b></td>
						                                			<td>
						                                			<#assign x=0>	
						                                			<#list rolerightList as roleright>
									                                	<#if menuthree.menu_id==roleright.menu_attr>
									                                		<#assign x=x+1>
									                                		<span><input id="${menuone.menu_id?if_exists},${menutow.menu_id?if_exists},${menuthree.menu_id?if_exists},${roleright.right_id?if_exists}" class="oper_right" type="checkbox" name="ro_op_right" value="${roleright.right_id?if_exists}"/>${roleright.right_name?if_exists}</span>
									                                		<#if x%7==0>
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
         		<@s.fielderror><@s.param>ro_menu_right</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">备注:</td>
             <td>
             	<@s.textarea name="role.remark" cssClass="mailCss" cssStyle="height:80px;width:420px;"onkeyup="checkLength(this,200);"/>      
             	<@s.fielderror><@s.param>role.remark</@s.param></@s.fielderror>       
             </td>
           </tr>
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
   	   <@s.token/>
       <@s.hidden name="nike_name_s"/>
       <@s.hidden id="menu_right" value="${ro_menu_right?if_exists}"/>
       <@s.hidden id="oper_right" value="${ro_op_right?if_exists}"/>
       ${listSearchHiddenField?if_exists}
       <@s.submit value="保存" />
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Role_list.action';document.forms[0].submit();"/>              
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>