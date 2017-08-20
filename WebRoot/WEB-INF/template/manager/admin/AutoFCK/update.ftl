<html>
  <head>
    <title>修改在线编辑器</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Autofck_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="autofck.id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>autofck.id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">content<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="autofck.content" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>autofck.content</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">in_date<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="autofck.in_date" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>autofck.in_date</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">cust_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="autofck.cust_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>autofck.cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">random_num<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="autofck.random_num" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>autofck.random_num</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Autofck_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

