<html>
  <head>
    <title>修改记录商品订单异动信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Ordertrans_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">trans_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="ordertrans.trans_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>ordertrans.trans_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">cust_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="ordertrans.cust_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>ordertrans.cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">order_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="ordertrans.order_id" cssClass="txtinput" onkeyup="checkLength(this,17);" maxlength="17"/>
		             	<@s.fielderror><@s.param>ordertrans.order_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">order_state<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="ordertrans.order_state" cssClass="txtinput" onkeyup="checkLength(this,1);" maxlength="1"/>
		             	<@s.fielderror><@s.param>ordertrans.order_state</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">user_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="ordertrans.user_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>ordertrans.user_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">trans_time<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="ordertrans.trans_time" cssClass="txtinput" onkeyup="checkLength(this,25000);" maxlength="25000"/>
		             	<@s.fielderror><@s.param>ordertrans.trans_time</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">reason<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="ordertrans.reason" cssClass="txtinput" onkeyup="checkLength(this,300);" maxlength="300"/>
		             	<@s.fielderror><@s.param>ordertrans.reason</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.token/>  
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Ordertrans_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

