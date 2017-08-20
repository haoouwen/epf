<@s.form name="topay" action="/mall/order!goPay.action" method='POST' >
			<input type='hidden' name='order_id_str'   value='${order_id_str?if_exists}'>
			<input type='hidden' name='total_amount' value='${total_amount?if_exists}'>
			<input type='hidden' name='integral_state' value='${integral_state?if_exists}'>
			
</@s.form>
		<script language=javascript> 
			document.topay.submit();
</script> 