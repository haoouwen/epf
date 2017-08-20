<html>
  <head>
    <title>添加操作权限</title>
    <link rel="StyleSheet" href="/include/components/dtree/dtree.css" type="text/css" />
	<script type="text/javascript" src="/include/components/dtree/dtree.js"></script>
	<script type="text/javascript">
	function selectSyscode(name) {
	if (name == "sys") {
		document.getElementById("adminmenu").style.display = "";
		document.getElementById("membermenu").style.display = "none";
	} else {
		document.getElementById("adminmenu").style.display = "none";
		document.getElementById("membermenu").style.display = "";
	}
}
function selectSyscodeType(name)
	{
	  if (name == 'sys') {
		document.forms[0].action="/admin_Roleright_add.action?syscode_s=sys";
		document.forms[0].submit();
	} 
	else 
	{
		document.forms[0].action="/admin_Roleright_add.action?syscode_s=b2c";
		document.forms[0].submit();
	} 
}
	
	</script>
  </head>
  <body>

<@s.form action="/admin_Roleright_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  当前位置:系统管理 > 帐号管理 > 操作权限管理 > 添加操作权限
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <tr>
             <td width="19%" class="table_name">权限名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="roleright.right_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>roleright.right_name</@s.param></@s.fielderror>
             </td>
           </tr>
        
           <tr>
             <td class="table_name">所属后台:</td>
             <td>
             	<@s.select name="roleright.syscode" list=r"#{'sys':'管理员后台','b2c':'会员后台'}" onchange="selectSyscode(this.value);"/>
             	<@s.fielderror><@s.param>roleright.syscode</@s.param></@s.fielderror>   
             		<@s.fielderror><@s.param>roleright.syscode</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr id="adminmenu" >
             <td class="table_name">所属菜单<font color='red'>*</font></td>
             <td>
             <a href="javascript: a.openAll();">全部打开</a> | <a href="javascript: a.closeAll();">全部关闭</a>
             <script type="text/javascript">
        	  a = new dTree('a');
		      a.add(1111111111,-1,'管理员菜单&nbsp;');		
		      <#list adminmenuList as menu>		
		       a.add(${menu.menu_id?if_exists},${menu.up_menu_id?if_exists},'<input type="radio" name="roleright.menu_attr" value="${menu.menu_id?if_exists}" <#if menu.menu_id==roleright.menu_attr>checked<#else></#if>   /> ${menu.menu_name?if_exists}&nbsp;','#');
		      </#list>		
		      document.write(a);
	         </script>   
	          <@s.fielderror><@s.param>roleright.menu_attr</@s.param></@s.fielderror> 
	          <@s.fielderror><@s.param>roleright.oper_right</@s.param></@s.fielderror>  	
             </td>
           </tr>
            <tr id="membermenu" style="display:none;">
             <td class="table_name">所属菜单<font color='red'>*</font></td>
             <td>
             <script type="text/javascript">
        	  d = new dTree('d');
		      d.add(1111111111,-1,'会员菜单&nbsp;');		
		      <#list membermenuList as menu>		
		       d.add(${menu.menu_id?if_exists},${menu.up_menu_id?if_exists},'<input type="radio" name="roleright.menu_attr" value="${menu.menu_id?if_exists}" <#if menu.menu_id==roleright.menu_attr>checked<#else></#if>   /> ${menu.menu_name?if_exists}&nbsp;','#');
		      </#list>		
		      document.write(d);
	         </script>   
	          <@s.fielderror><@s.param>roleright.menu_attr</@s.param></@s.fielderror>   	
             </td>
           </tr>
           
           <tr>
             <td class="table_name">权限地址<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="roleright.url" size="70" maxLength="100"/>
             	<img class='ltip'src="/include/common/images/light.gif" alt='<@s.property value="%{getText('manager.admin.Roleright.url')}"/> '>
             	<@s.fielderror><@s.param>roleright.url</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">操作提示信息:</td>
             <td>
             	<@s.textfield name="roleright.prompt" size="70" maxLength="120"/>
             	<@s.fielderror><@s.param>roleright.prompt</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">备注:</td>
             <td>
             	<@s.textarea name="roleright.remark" cssClass="txtinput" rows="5" cssStyle="width:400px;" onkeyup="checkLength(this,200);"/>
             	<@s.fielderror><@s.param>roleright.remark</@s.param></@s.fielderror>
             </td>
           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
   <@s.token/> 
   ${listSearchHiddenField?if_exists}     
   <@s.submit value="保存"/>
   <@s.reset value="重置"/>
   <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Roleright_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
<div class="clear"></div>

  </body>
</html>