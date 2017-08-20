<html>
  <head>
    <title>修改部门</title>
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
	<script type="text/javascript">
	  $(document).ready(function(){     
         //所属地区的回选
         loadArea("${areaIdStr?if_exists}","");
         
	  });
	</script> 
  </head>
  <body>
<@s.form action="/admin_Organize_update.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
当前位置:系统管理 > 帐号管理 > 组织部门管理 > 修改部门
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		   <tr>
             <td width="19%" class="table_name">部门名称<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="organize.org_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>organize.org_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">部门级别:</td>
             <td>
             	  <#if  organize.org_level?if_exists!=null>
             	  		${organize.org_level?if_exists}
             	  <#else>
             	  		1
             	  </#if> 级   
             	  <@s.fielderror><@s.param>organize.org_level</@s.param></@s.fielderror>	  	
             </td>
           </tr>
           <#if organize.sys_org=='0'>
           	   <tr>
	             <td width="19%" class="table_name">所属地区:</td>
	             <td>
	             	  <div id="areaDiv" style="margin-left:-5px;"></div>
	                  <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>      
	             </td>
	           </tr>
           </#if>
        
           
           <tr>
             <td width="19%" class="table_name">联系人<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="organize.contact" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>organize.contact</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">电话<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="organize.phone" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>organize.phone</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">手机:</td>
             <td>
             	<@s.textfield name="organize.cellphone" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>organize.cellphone</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">Skype:</td>
             <td>
             	<@s.textfield name="organize.skype" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>organize.skype</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">QQ:</td>
             <td>
             	<@s.textfield name="organize.qq" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>organize.qq</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">微信:</td>
             <td>
             	<@s.textfield name="organize.msn" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>organize.msn</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">Email:</td>
             <td>
             	<@s.textfield name="organize.email" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>organize.email</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">传真:</td>
             <td>
             	<@s.textfield name="organize.fax" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>organize.fax</@s.param></@s.fielderror>
             </td>
           </tr>   
           <tr>
             <td width="19%" class="table_name">联系地址:</td>
             <td>
             	<@s.textfield name="organize.address" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>organize.address</@s.param></@s.fielderror>
             </td>
           </tr> 
           <tr>
             <td class="table_name">系统提示:</td>
             <td>
             	<span><img class='ltip' src="/include/common/images/light.gif" alt="编辑保存后,请点击更新缓存" />
             	[编辑保存后,请点击<a href="###" onclick="renewload();"><font color="red">更新缓存</font></a>]</span>
             </td>
           </tr>
		 
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
   	   <@s.token/>
       <@s.submit value="保存"/>
       <@s.hidden name="organize.org_id" />
       <@s.hidden name="organize.sys_org" />
       <@s.hidden name="organize.up_org_id" value="${(organize.up_org_id?if_exists)!'1111111111'}" />
       <@s.hidden name="organize.org_level" value="${(organize.org_level?if_exists)!'1'}" />
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Organize_list.action';document.forms[0].submit();"/>
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>