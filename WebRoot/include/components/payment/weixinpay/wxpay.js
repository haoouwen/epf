$(document).ready(function(){
  setTimeout('myrefresh()',30000); //指定30秒刷新一次
  var code_url=$("#wxpay_scan").val();
  $('#qrcode').qrcode(code_url);
});
//检查是否支付成功，如果支付成功，跳转到支付成功页面
function myrefresh()
{
   var order_id_str=$("#order_id_str").val();
   var total_amount = $("#sub_total_price").val();
   var is_recharge = $("#is_recharge").val();
   var action_url = "";
   if(is_recharge=="1"){
      action_url = "/mall/pay!refreshWXPayTip.action?order_id_str="+order_id_str;
   }else if(is_recharge=="0"){
      action_url = "/mall/pay!refreshWXRechargeTip.action?order_id_str="+order_id_str;
   }
   $.ajax({
       type: "post",
       url: action_url,
       dataype:"json",
       async:false,
       success: function(data){
         if(data=="1"){
           //检测到支付成功了，执行条状页面
           $("#commonForm").attr("action","/mall/pay!wxScanPaySuccess.action?order_id_str="+order_id_str+"&total_amount="+total_amount+"&is_recharge"+is_recharge).submit();
         }else{
          setTimeout('myrefresh()',15000); //指定15秒刷新一次
         }
       }
  });
}
