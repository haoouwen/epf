<html>
  <head>
    <title>添加记录单据模板信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Receipt_insert.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">receipt_code<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="receipt.receipt_code" cssClass="txtinput" onkeyup="checkLength(this,10);" maxlength="10"/>
		             	<@s.fielderror><@s.param>receipt.receipt_code</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">receipt_name<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="receipt.receipt_name" cssClass="txtinput" onkeyup="checkLength(this,30);" maxlength="30"/>
		             	<@s.fielderror><@s.param>receipt.receipt_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">receipt_content<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="receipt.receipt_content" cssClass="txtinput" onkeyup="checkLength(this,25000);" maxlength="25000"/>
		             	<@s.fielderror><@s.param>receipt.receipt_content</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.token/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Receipt_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

