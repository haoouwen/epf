<html>
  <head>
    <title>订单打印-配货单打印</title>
    <#include "/WEB-INF/template/manager/admin/Goodsorder/commonprint.ftl">
    <link href="/include/admin/css/goodsorder.css" rel="stylesheet" type="text/css">
	<script src="/include/admin/js/orderprint.js" type="text/javascript"></script>
	<script src="/include/admin/js/order_int_print.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function(){
			IntPrintList("id_send_content","id_send_table");
			IntPrintList("id_send_content","id_send_table");
	});
	</script>
  </head>
  <body >
  
<div class="postion_index"></div>
<div class="rtdcont">
	<div class="rttable">
	<div>
 	<form id="form1" >
		<div >${print_content?if_exists}</div>			    
   </form>
   <div class="rttable" style="text-align:center;font-size:14px;">
      <font color="red">重要提示：配货单可重复打印，如您已打印过配货单，请注意核对，勿重复配货。</font>
   </div>
   <@s.hidden name="order_id_s" id="order_id_s"/>
   <@s.hidden  id="type" value="2"/>
	<div class="fixbuttom" style="margin-top:10px;">
	 	<input type="button" name="returnList" style="cursor:pointer;" value="打印预览"  onclick="order_print_preview();"/>
	    <input type="button" name="returnList" style="cursor:pointer;" value="打印"  onclick="order_print_print();"/>
	    <input id="btnClose" type="button" value="关闭本页" onClick="custom_close();" /><br/>
	</div>
</div>
	</div>
	<div class="clear"/>
</div>
<div class="clear"></div>

</body>
</html>
