<html>
  <head>
    <title>添加记录商城的活动管理</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Msgcheck_insert.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="msgcheck.id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>msgcheck.id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">cp_phone<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="msgcheck.cp_phone" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>msgcheck.cp_phone</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">cp_check<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="msgcheck.cp_check" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>msgcheck.cp_check</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">send_time<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="msgcheck.send_time" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>msgcheck.send_time</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.token/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Msgcheck_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

