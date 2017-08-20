<html>
  <head>
    <title>修改记录打印样式模板信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Printstyletem_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">trade_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="printstyletem.trade_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>printstyletem.trade_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">template_code<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="printstyletem.template_code" cssClass="txtinput" onkeyup="checkLength(this,20);" maxlength="20"/>
		             	<@s.fielderror><@s.param>printstyletem.template_code</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">template_name<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="printstyletem.template_name" cssClass="txtinput" onkeyup="checkLength(this,30);" maxlength="30"/>
		             	<@s.fielderror><@s.param>printstyletem.template_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">label_explan<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="printstyletem.label_explan" cssClass="txtinput" onkeyup="checkLength(this,100);" maxlength="100"/>
		             	<@s.fielderror><@s.param>printstyletem.label_explan</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">print_content<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="printstyletem.print_content" cssClass="txtinput" onkeyup="checkLength(this,25000);" maxlength="25000"/>
		             	<@s.fielderror><@s.param>printstyletem.print_content</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.token/>  
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Printstyletem_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

