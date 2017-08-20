<html>
  <head>
    <title>订单打印-联合打印</title>
    <#include "/WEB-INF/template/manager/admin/Goodsorder/commonprint.ftl">
    <link href="/include/admin/css/goodsorder.css" rel="stylesheet" type="text/css">
	<script src="/include/admin/js/orderprint.js" type="text/javascript"></script>
	<script src="/include/admin/js/order_int_print.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function(){
			IntPrintList("id_send_content","id_send_table");
			IntPrintList("id_go_content","id_go_table");
	});
	</script>
  </head>
  <body >
  
  
<div class="postion_index"></div>
<div class="rtdcont">
	<div class="rttable">
	   <!--列表页table的数据-->	
	   <div>
 	<form id="form1" >
		<div >${print_content?if_exists}</div>			    
   </form>
   <@s.hidden name="goodsorder.order_id" id="order_id_s"/>
	<div class="fixbuttom" style="margin-top:10px;">
	 	<input type="button" name="returnList" style="cursor:pointer;" value="打印预览"  onclick="order_print_preview();"/>
	    <input type="button" name="returnList" style="cursor:pointer;" value="打印"  onclick="order_print_print();"/>
	    <input id="btnClose" type="button" value="关闭本页" onClick="custom_close();" />
	</div>
</div> 
	</div>
	<div class="clear"/>
</div>
<div class="clear"></div>
  
</body>
</html>