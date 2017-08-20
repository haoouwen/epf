<html>
  <head>
    <title>添加记录单据管理信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div> 
   <div class="tj_main_cont">
   	<@s.form action="/admin_Receiptmanage_insert.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">trade_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="receiptmanage.trade_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>receiptmanage.trade_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">cust_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="receiptmanage.cust_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>receiptmanage.cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr> 
	           
		           <tr>
		             <td class="table_name">receipt_code<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="receiptmanage.receipt_code" cssClass="txtinput" onkeyup="checkLength(this,10);" maxlength="10"/>
		             	<@s.fielderror><@s.param>receiptmanage.receipt_code</@s.param></@s.fielderror>
		             </td>
		           </tr> 
	           
		           <tr>
		             <td class="table_name">receipt_content<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="receiptmanage.receipt_content" cssClass="txtinput" onkeyup="checkLength(this,25000);" maxlength="25000"/>
		             	<@s.fielderror><@s.param>receiptmanage.receipt_content</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">in_date<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="receiptmanage.in_date" cssClass="txtinput" onkeyup="checkLength(this,25000);" maxlength="25000"/>
		             	<@s.fielderror><@s.param>receiptmanage.in_date</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.token/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Receiptmanage_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

