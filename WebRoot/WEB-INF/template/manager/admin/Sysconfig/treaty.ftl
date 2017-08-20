<html>
  <head>
    <title>系统基本设置管理</title>
    <#include "/include/uploadInc.html">
  </head>
  <body>
  
  <@s.form action="/admin_Sysconfig_updateMallTreaty.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
 	当前位置:系统管理 > 帐号管理 > 角色管理 > 修改角色
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
	 <#list sysconfigList as config>
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		  <tr>
             <td class="table_name">变量描述:<font color='red'></font></td>
             <td align="center">
    		${config.var_desc?if_exists}
    		<#if config.var_type='3'>
	      (字数不能超过800字)
	    </#if>
    </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">变量值:<font color='red'></font></td>
             <td>
		       <@s.textarea id="content" name="var_value" onblur="checkNull_dou(this);" value="${config.var_value?if_exists}" cssClass="txtinput" rows="3" cssStyle="width:400px;height:90px;" onkeyup="checkLength(this,800);"/>
		             	<script type="text/javascript"  src="/include/components/ckeditor/ckeditor.js"></script>
						<script type="text/javascript">
					     CKEDITOR.replace('content');  
						</script>  
		             	<@s.fielderror><@s.param>var_value</@s.param></@s.fielderror>
		             </td>
           </tr>

           <tr>
             <td class="table_name">变量名:</td>
             	 <td align="center">${config.var_name?if_exists}</td>
           </tr>
           <tr>
           <td class="table_name"></td>
           	<td align="center">
	       <@s.submit value="保存"/>
           		
           		</td>
           </tr>
		 
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
   	     <@s.hidden name="var_id" value="${config.var_id?if_exists}"/>
	       <@s.token/>
   </div>
    </#list>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
 </body>
</html>