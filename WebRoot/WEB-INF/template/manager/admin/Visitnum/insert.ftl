<html>
  <head>
    <title>添加记录日访问数</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Visitnum_insert.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="visitnum.id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>visitnum.id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">day_pv<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="visitnum.day_pv" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>visitnum.day_pv</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">day_time<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="visitnum.day_time" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>visitnum.day_time</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.token/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Visitnum_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

