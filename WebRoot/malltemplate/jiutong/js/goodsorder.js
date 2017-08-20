

/***************************************触屏版会员中心相关操作方法**************************************************/


//加载物流动态
function getLOgisticsInfo(){
 var order_id=$("#order_id").val();
 var showdata="";
 var fag="1";
//获取物流信息json
 $.ajax({
       type: "post",
       url: "/webapp/goodsorder!ajaxlogisticsJson.action?o_id="+order_id,
       dataype:"json",
       async:false,
       success: function(data){
            if(data!=null&&data!=""){
                data=eval("("+data+")");
		        showdata="<div class='otherDay'>";
		       	for(var i=0;i<data.length;i++){
			        	showdata+="<div><table cellpadding='0' cellspacing='0'><tr><th>"+data[i].time+"</th><td>"+data[i].context+"</td></tr></table></div>";
		       	}    
		       	showdata+="</div>";
		     }
		     if(showdata=="<div class='otherDay'></div>"||showdata==null||showdata==""){
		       fag="2";
		        showdata="<div class='today'><div><table cellpadding='0' cellspacing='0'><tr>"+
		        "<th>系统提示</th><td>物流公司未返回数据，请到物流官网查询或联系其公示电话</td></tr></table></div></div>";
		     }
       }
  });
  if(fag=="1"){
     $("#show_log").show();
     $("#show_log_s").hide();
     $("#show_log").html(showdata);
  }else{
     $("#show_log").hide();
     $("#show_log_s").show();
  }
 
}

//订单状态搜索
function searchByOrderState(order_state,state_tip){
  
    var state_text = $("."+state_tip).html();
    $("#state_text").val(state_text);
    $("#date_s").val("");
    $("#refund_s").val("");
    $("#order_state_s").val(order_state);
    $("#indexForm").attr("action","/webapp/goodsorder!webappAllOrder.action");
	$("#indexForm").submit();

}
//订单时间搜索
function searchByDate(dateflag,state_tip){
    var state_text = $("."+state_tip).html();
    $("#state_text").val(state_text);
    $("#refund_s").val("");
    $("#order_state_s").val("");
    $("#date_s").val(dateflag);
    $("#indexForm").attr("action","/webapp/goodsorder!webappAllOrder.action");
	$("#indexForm").submit();
}

//订单时间搜索
function searchByOrderRefund(refund_s){
    $("#date_s").val("");
    $("#order_state_s").val("");
    $("#refund_s").val(refund_s);
    $("#indexForm").attr("action","/webapp/goodsorder!webappAllOrder.action");
	$("#indexForm").submit();
}

//确认收货弹出层
function orderconfirmreceipt(order_id,total_amont){
    $("#date_s").val("");
    $("#refund_s").val("");
    $("#order_state_s").val("");
	var orderHtml='<div class="popOrderDiv">'+
  	 '<table width="100%" cellpadding="0" bordercolorlight="0">'+
     	'<tr><th colspan="2">订单信息<span>(请您收到货后，再确认收货)</span></th></tr>'+
        '<tr><td class="potd" height="30px;">订单编号：</td><td>'+order_id+'</td></tr>'+
        '<tr><td class="potd" height="30px;">订单金额：</td><td>'+total_amont+'元</td></tr>'+
        '<tr><td></td><td colspan="2"><input type="button" class="graybut" value="确定" onclick=\'submitConfirm("'+order_id+'")\'>&nbsp;&nbsp;<input type="button" class="graybut" value="取消" id="shbutId"></td></tr>'+
     '</table>'+
    '</div>';
   $("#confirmOrderId").html(orderHtml);
	$("#confirmOrderId").popup({
			pOpacity:"0.5",
			ptBackground:"#f5f5f5",
			pcBackground:"#fff",
			pWidth:"250",
			pHeight:"120",
			pTitle:"确认收货",
			oprid:"shbutId"
	  });
}


//确定收货处理
function submitConfirm(order_id){
 $("#order_id").val(order_id);
 $("#indexForm").attr("action","/webapp/goodsorder!webappConfirmReceipt.action");
 $("#indexForm").submit();
}

//取消订单弹出层
function cancelOrder(order_id,total_amont){
	var jsdata=$("#jsontotal_id").val();
	    jsdata=eval("(" +jsdata+")"); 
	var orderHtml='<div class="popOrderDiv">'+
  	 '<table width="100%" cellpadding="0" bordercolorlight="0">'+
     	'<tr><th colspan="2">订单信息</th></tr>'+
        '<tr><td class="potd" height="30px;">订单编号：</td><td>'+order_id+'</td></tr>'+
        '<tr><td class="potd" height="30px;">订单金额：</td><td>'+total_amont+'</td></tr>'+
        '<tr><td class="potd" height="30px;">取消原因：</td><td><select name="refund_buy_reason" id="reason">';
        for(var j=0;j<jsdata.length;j++){ 
         orderHtml+='<option value="'+jsdata[j].para_key+'">'+jsdata[j].para_key+'</option>';
        }
        orderHtml+='</select></td></tr>'+
        '<tr><td colspan="2" style="text-align:center;height:30px;"><input type="button" class="graybut" value="确定" onclick=\'submitCancel("'+order_id+'")\'>&nbsp;&nbsp;<input type="button" class="graybut" value="取消" id="ddbutId"></td></tr>'+
     '</table>'+
    '</div>';
   $("#cancelOrderId").html(orderHtml);
	$("#cancelOrderId").popup({
		pOpacity:"0.5",
		ptBackground:"#f5f5f5",
		pcBackground:"#fff",
		pWidth:"250",
		pHeight:"160",
		pTitle:"取消订单",
		oprid:"ddbutId"
  });
}

//取消订单处理
function submitCancel(order_id){
 $("#order_id").val(order_id);
 var reason=$("#reason").val();
 $("#refund_buy_reason").val(reason);
 $("#indexForm").attr("action","/webapp/goodsorder!webappCancelOrder.action");
 $("#indexForm").submit();
}

//提醒发货
function remindDelivery(order_id){
   $.ajax({
       type: "post",
       url: "/webapp/goodsorder!remindDelivery.action?order_id="+order_id,
       dataype:"json",
       async:false,
       success: function(data){
         if(data=="success"){
             alert("提醒发货成功!");
	     }
       }
   });

}
