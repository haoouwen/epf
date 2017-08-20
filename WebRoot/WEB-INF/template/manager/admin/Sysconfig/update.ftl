<html>
  <head>
    <title>修改变量</title>
    <script type="text/javascript" src="/include/common/js/common.js"></script>
    <script type="text/javascript">
    function getRadioVal(name)
     {
        var radioes = document.getElementsByName(name); 
       for(var i=0;i<radioes.length;i++)
      {    
         if(radioes[i].checked)
             {
           if(radioes[i].value=="1")
            {
                 $("#varvalue").val("0");
            }
            
         }
        }
     }
    </script>
  </head>
<body>
<@s.form action="/admin_Sysconfig_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
当前位置:系统管理 > 系统设置 > 系统基本设置管理 > 修改变量
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		 
		 <tr>
		             <td class="table_name">排序:</td>
		             <td>
		             	<@s.textfield name="sysconfig.sort_no" cssClass="txtinput" maxLength="11" value="0" cssStyle="width:80px;" onkeyup="checkNum(this)"/>
		             	<@s.fielderror><@s.param>sysconfig.sort_no</@s.param></@s.fielderror>
		             </td>
		    </tr>
           <tr>
             <td width="19%"  class="table_name">变量名称<font color='red'>*</font></td>
             <td>
             	${sysconfig.var_name?if_exists}
             </td>
           </tr>
           <tr>
             <td class="table_name">变量类型:</td>
             <td> 
               <@s.radio name="sysconfig.var_type" list=r"#{'0':'文本','1':'数字','2':'布尔','3':'多行文本','4':'图片'}" value="0"  onclick="getRadioVal(name);"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">变量值<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="sysconfig.var_value" cssClass="txtinput" maxLength="300" id="varvalue"/>
             	<@s.fielderror><@s.param>sysconfig.var_value</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">变量描述<font color='red'>*</font></td>
             <td>
             	<@s.textarea name="sysconfig.var_desc" cssClass="txtinput" rows="3" style="width:300px;" onkeyup="checkLength(this,300);"/>
             	<@s.fielderror><@s.param>sysconfig.var_desc</@s.param></@s.fielderror>
             </td>
           </tr>
           <#if module_type_s?if_exists!=''>
	      	   <tr>
	             <td class="table_name">所属模块<font color='red'>*</font></td>
	             <td>	             
	             	<@s.hidden name="sysconfig.module_type" value="${module_name?if_exists}"/>
	                ${module_name?if_exists}
	             	<@s.fielderror><@s.param>sysconfig.var_group</@s.param></@s.fielderror>	             	
	             </td>
	           </tr>
	       <#else>
	      	   <tr>
	             <td class="table_name">所属组<font color='red'>*</font></td>
	             <td>
	             	<@s.select name="sysconfig.var_group" list="commparaList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择" />
	             	<@s.fielderror><@s.param>sysconfig.var_group</@s.param></@s.fielderror>
	             </td>
	           </tr>
           </#if> 
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
       <@s.hidden name="module_type_s"/>
       <@s.hidden name="sysconfig.var_id"/>
       <@s.token/>
       <@s.submit value="保存"/>
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Sysconfig_newlist.action','para_key=2');"/>
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>

  </body>
</html>