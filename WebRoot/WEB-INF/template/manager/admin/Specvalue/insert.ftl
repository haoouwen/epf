<html>
  <head>
    <title>添加记录规格值信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Specvalue_insert.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">sv_code<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="specvalue.sv_code" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>specvalue.sv_code</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">sv_name<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="specvalue.sv_name" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>specvalue.sv_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">sv_img_path<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="specvalue.sv_img_path" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>specvalue.sv_img_path</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">sort_no<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="specvalue.sort_no" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>specvalue.sort_no</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">spec_code<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="specvalue.spec_code" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>specvalue.spec_code</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Specvalue_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

