<html>
  <head>
    <title>修改优惠券消费码</title>
  </head>
<body >
<@s.form action="/admin_Comsumer_update.action"  method="post" validate="true"  id="detailForm">
<div class="postion">
 当前位置：1 > 2 > 3
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
			
			   <tr>
	             <td class="table_name">comsumer_id<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="comsumer.comsumer_id"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>comsumer.comsumer_id</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">coupon_id<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="comsumer.coupon_id"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>comsumer.coupon_id</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">use_state<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="comsumer.use_state"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>comsumer.use_state</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">cust_id<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="comsumer.cust_id"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>comsumer.cust_id</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">use_date<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="comsumer.use_date"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>comsumer.use_date</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">order_id<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="comsumer.order_id"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>comsumer.order_id</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
	           
       </table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
 		   <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
		   ${listSearchHiddenField?if_exists}
           <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Comsumer_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>   
</body>
</html>

