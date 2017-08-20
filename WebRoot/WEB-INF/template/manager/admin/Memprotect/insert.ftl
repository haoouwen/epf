<html>
  <head>
    <title>添加记录会员密保信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Memprotect_insert.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memprotect.id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>memprotect.id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">cust_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memprotect.cust_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>memprotect.cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">question<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memprotect.question" cssClass="txtinput" onkeyup="checkLength(this,20);" maxlength="20"/>
		             	<@s.fielderror><@s.param>memprotect.question</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">answer<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="memprotect.answer" cssClass="txtinput" onkeyup="checkLength(this,20);" maxlength="20"/>
		             	<@s.fielderror><@s.param>memprotect.answer</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.token/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Memprotect_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

