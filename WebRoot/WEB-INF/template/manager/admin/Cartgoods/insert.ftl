<html>
  <head>
    <title>添加购物车</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Cartgoods_insert.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">trade_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="cartgoods.trade_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>cartgoods.trade_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">buy_num<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="cartgoods.buy_num" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>cartgoods.buy_num</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">cust_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="cartgoods.cust_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>cartgoods.cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">cookie_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="cartgoods.cookie_id" cssClass="txtinput" onkeyup="checkLength(this,32);" maxlength="32"/>
		             	<@s.fielderror><@s.param>cartgoods.cookie_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">shop_name<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="cartgoods.shop_name" cssClass="txtinput" onkeyup="checkLength(this,20);" maxlength="20"/>
		             	<@s.fielderror><@s.param>cartgoods.shop_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">shop_qq<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="cartgoods.shop_qq" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>cartgoods.shop_qq</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">user_name<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="cartgoods.user_name" cssClass="txtinput" onkeyup="checkLength(this,20);" maxlength="20"/>
		             	<@s.fielderror><@s.param>cartgoods.user_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">goods_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="cartgoods.goods_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>cartgoods.goods_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">img_path<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="cartgoods.img_path" cssClass="txtinput" onkeyup="checkLength(this,100);" maxlength="100"/>
		             	<@s.fielderror><@s.param>cartgoods.img_path</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">goods_name<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="cartgoods.goods_name" cssClass="txtinput" onkeyup="checkLength(this,100);" maxlength="100"/>
		             	<@s.fielderror><@s.param>cartgoods.goods_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">spec_name<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="cartgoods.spec_name" cssClass="txtinput" onkeyup="checkLength(this,100);" maxlength="100"/>
		             	<@s.fielderror><@s.param>cartgoods.spec_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">spec_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="cartgoods.spec_id" cssClass="txtinput" onkeyup="checkLength(this,100);" maxlength="100"/>
		             	<@s.fielderror><@s.param>cartgoods.spec_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">integral<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="cartgoods.integral" cssClass="txtinput" onkeyup="checkLength(this,10,2);" maxlength="10,2"/>
		             	<@s.fielderror><@s.param>cartgoods.integral</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">privilege_way<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="cartgoods.privilege_way" cssClass="txtinput" onkeyup="checkLength(this,20);" maxlength="20"/>
		             	<@s.fielderror><@s.param>cartgoods.privilege_way</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">is_virtual<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="cartgoods.is_virtual" cssClass="txtinput" onkeyup="checkLength(this,1);" maxlength="1"/>
		             	<@s.fielderror><@s.param>cartgoods.is_virtual</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">in_date<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="cartgoods.in_date" cssClass="txtinput" onkeyup="checkLength(this,25000);" maxlength="25000"/>
		             	<@s.fielderror><@s.param>cartgoods.in_date</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.token/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Cartgoods_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

