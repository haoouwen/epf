<html>
  <head>
    <title>修改记录商品咨询l回复信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Consulting_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">re_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="consulting.re_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>consulting.re_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">trade_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="consulting.trade_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>consulting.trade_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">user_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="consulting.user_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>consulting.user_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">re_content<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="consulting.re_content" cssClass="txtinput" onkeyup="checkLength(this,600);" maxlength="600"/>
		             	<@s.fielderror><@s.param>consulting.re_content</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">re_date<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="consulting.re_date" cssClass="txtinput" onkeyup="checkLength(this,25000);" maxlength="25000"/>
		             	<@s.fielderror><@s.param>consulting.re_date</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">is_display<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="consulting.is_display" cssClass="txtinput" onkeyup="checkLength(this,1);" maxlength="1"/>
		             	<@s.fielderror><@s.param>consulting.is_display</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.token/>  
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Consulting_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

