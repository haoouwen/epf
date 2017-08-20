<html>
  <head>
     <title>修改模块参数</title>
  </head>
<body>

<@s.form action="/admin_Sysmodule_update.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
 	当前位置:系统管理 > 系统设置 > 系统模块设置 > 修改模块参数
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		  <tr>
             <td class="table_name">模块编码:</td>
             <td>
	             <@s.hidden name="sysmodule.module_type" id="tf_name"/>
	             <@s.label name="sysmodule.module_type" value="${sysmodule.module_type?if_exists}"/>
             </td>
           </tr>                
           <tr>
             <td class="table_name">模块名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield id="tf_name" name="sysmodule.module_name" cssClass="txtinput" maxlength="20"/>
             	<@s.fielderror><@s.param>sysmodule.module_name</@s.param></@s.fielderror>             	            
             </td>
           </tr>  
           
           <tr>
             <td class="table_name">模块表名<font color='red'>*</font></td>
             <td>
             	<@s.textfield id="tf_name" name="sysmodule.table_name" cssClass="txtinput" maxlength="10"/>
             	<@s.fielderror><@s.param>sysmodule.table_name</@s.param></@s.fielderror>             	            
             </td>
           </tr>  
          <tr>
             <td class="table_name">是否启用<font color='red'>*</font></td>
             <td>
             	<@s.radio name="sysmodule.state_code" list=r"#{'0':'启用','1':'禁用'}"  checked="true" />          	            
             </td>
           </tr> 
          <tr style="display:none;">
             <td class="table_name">分类属性<font color='red'>*</font></td>
             <td>
             	<@s.radio name="sysmodule.is_catattr" list=r"#{'0':'开启','1':'关闭'}"  checked="true" />                 	            
             </td>
           </tr>   
          <tr>
             <td class="table_name">排序:</td>
             <td>
             	<@s.textfield id="tf_name" name="sysmodule.sort_no" onkeyup="checkNum(this);" />（只能输入数字，且越小越往前）
             	<@s.fielderror><@s.param>sysmodule.sort_no</@s.param></@s.fielderror>             	            
             </td>
          </tr>    
           <tr>
             <td class="table_name">安装目录:</td>
             <td>
             	<@s.textfield id="tf_name" name="sysmodule.install_dir" cssStyle="width:280px;" maxlength="10"/>
             	<@s.fielderror><@s.param>sysmodule.install_dir</@s.param></@s.fielderror>             	            
             </td>
           </tr>   
          <tr>
             <td class="table_name">关联菜单:</td>
             <td>
             	<@s.textfield id="tf_name" name="sysmodule.link_menu" cssStyle="width:280px;" maxlength="300"/>
             	<@s.fielderror><@s.param>sysmodule.link_menu</@s.param></@s.fielderror>             	            
             </td>
           </tr>  
          <tr>
             <td class="table_name">关联表:</td>
             <td>
             	<@s.textfield id="tf_name" name="sysmodule.link_table" cssStyle="width:280px;" maxlength="100"/>
             	<@s.fielderror><@s.param>sysmodule.link_table</@s.param></@s.fielderror>             	            
             </td>
          </tr>           
          <tr>
             <td class="table_name">关联文件:</td>
             <td>
             	<@s.textarea id="tf_name" name="sysmodule.link_file" cssStyle="width:280px;" maxlength="600"/>
             	<@s.fielderror><@s.param>sysmodule.link_file</@s.param></@s.fielderror>             	            
             </td>
          </tr> 
		 
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
	   <@s.hidden name="sysmodule.is_mem"/>
	   <@s.hidden name="sysmodule.mod_type"/>
	   <@s.hidden name="checkonly" value="${sysmodule.module_type}"/>
	   <@s.hidden name="old_link_menu" value="${sysmodule.link_menu}"/>
	   <@s.token/>
	   <@s.submit value="保存"/>
	   <@s.reset value="重置"/>
	   <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Sysmodule_list.action';document.forms[0].submit();"/>
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
 </body>
</html>