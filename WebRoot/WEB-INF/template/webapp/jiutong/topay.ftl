<@s.form name="topay" action="/webapp/paySend.html" method='get' >
			<input type='hidden' name='order_id_str'   value='${order_id_str?if_exists}'>
			<input type='hidden' name='total_amount' value='${total_amount?if_exists}'>
			<input type='hidden' name='integral_state' value='${integral_state?if_exists}'>
			<input type='hidden' name='type' value='1'>
			<input type='hidden' name='pay_terminal' value='1'>
</@s.form>
		<script language=javascript> 
			document.topay.submit();
</script> 