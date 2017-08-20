<html>
  <head>
    <title>添加记录分类属性的值</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Attrvalue_insert.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">trade_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="attrvalue.trade_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>attrvalue.trade_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">attr_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="attrvalue.attr_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>attrvalue.attr_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">attr_value<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="attrvalue.attr_value" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>attrvalue.attr_value</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.token/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Attrvalue_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

