<html>
  <head>
    <title>修改记录自定义参数值信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Selfparavalue_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">slef_para_value_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="selfparavalue.slef_para_value_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>selfparavalue.slef_para_value_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">slef_para_group_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="selfparavalue.slef_para_group_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>selfparavalue.slef_para_group_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">para_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="selfparavalue.para_id" cssClass="txtinput" onkeyup="checkLength(this,10);" maxlength="10"/>
		             	<@s.fielderror><@s.param>selfparavalue.para_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">slef_para_value<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="selfparavalue.slef_para_value" cssClass="txtinput" onkeyup="checkLength(this,20);" maxlength="20"/>
		             	<@s.fielderror><@s.param>selfparavalue.slef_para_value</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">sort_no<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="selfparavalue.sort_no" cssClass="txtinput" onkeyup="checkLength(this,11);" maxlength="11"/>
		             	<@s.fielderror><@s.param>selfparavalue.sort_no</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.token/>  
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Selfparavalue_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

