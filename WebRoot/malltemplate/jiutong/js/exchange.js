
//撤销申请弹出层
function cancelOrder(trade_id,ret_number_str,list_img_str,ret_type_str,ret_liyou_str){
	if(list_img_str.indexOf("#")>-1){
	   list_img_str=list_img_str.split("#"); 
	}
	var orderHtml='<div class="popOrderDiv">'+
  	 '<table width="100%" cellpadding="0" bordercolorlight="0">'+
        '<tr><td width="16%" class="potd">售后类型：</td><td ><b class="mgreen">'+ret_type_str+'</b></td></tr>'+
        '<tr><td width="16%" class="potd">申请编号：</td><td ><b class="mgreen">'+ret_number_str+'</b></td></tr>';
        orderHtml+='<tr><td width="16%" class="potd">申请原因：</td><td >'+ret_liyou_str+'</td></tr>';
        orderHtml+= '<tr><td class="potd"><span>*</span>撤销理由：</td><td><textarea  id="cancel_text_id" /></td></tr>';
        orderHtml+='<tr><td></td><td colspan="3"><input type="button" class="graybut" value="确定" onclick=\'submitCancel("'+trade_id+'")\'>&nbsp;&nbsp;<input type="button" class="graybut" value="取消" id="ddbutId"></td></tr>'+
     '</table>'+
    '</div>';
   $("#cancelOrderId").html(orderHtml);
	$("#cancelOrderId").popup({
		pOpacity:"0.7",
		ptBackground:"#f5f5f5",
		pcBackground:"#fff",
		pWidth:"300",
		pHeight:"340",
		pTitle:"撤销申请",
		oprid:"ddbutId"
  });
}

//取消订单处理
function submitCancel(order_id){
 var cancel_desr=$("#cancel_text_id").val();
 if(cancel_desr==null||cancel_desr==""){
  alert("请输入撤销理由");
  return;
 }
 $("#cancel_trade_id").val(order_id);
 $("#cancel_desr_id").val(cancel_desr);
 $("#indexForm").attr("action","/webapp/exchange!cancelRefund.action");
 $("#indexForm").submit();
}





