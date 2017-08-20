<html>
  <head>
    <title>添加参数值</title>
  </head>
  <body>
<@s.form action="/admin_Paravalue_insert.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
当前位置：商城管理 > 商品参数管理 > 添加参数值
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		   <tr>
             <td class="table_name">参数名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="paravalue.para_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>paravalue.para_name</@s.param></@s.fielderror>
             </td>
           </tr>
       
           <tr>
             <td class="table_name">参数值:</td>
             <td>
             	<@s.textfield name="paravalue.value" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>paravalue.value</@s.param></@s.fielderror>
             </td>
           </tr>
       
           <tr>
             <td class="table_name">排序:</font></td>
             <td>
             	<@s.textfield name="paravalue.sort_no" cssClass="txtinput" maxLength="11" value="0" onkeyup="checkNum(this)"/>
             	<@s.fielderror><@s.param>paravalue.sort_no</@s.param></@s.fielderror>
             </td>
           </tr>
		 
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>  
       ${listSearchHiddenField?if_exists}
       <@s.hidden name="pgi" value="${pgi}"/> 
       <@s.submit value="保存" />
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Paravalue_list.action','pgi_s=${pgi}');"/>
       <@s.token/>
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

