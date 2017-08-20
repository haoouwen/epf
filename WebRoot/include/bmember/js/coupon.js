//兑换优惠券
function exCoupon(){
  var coupon_no=$("#coupon_no").val();
  if(coupon_no==''){
	alert("请填写优惠券号码!");
	return false ;
  }
  $.ajax({  	 
          type: "post",    	     
          url: "/comsumer!exCoupon.action",
          data:{coupon_no:coupon_no},
          datatype:"json",
          success: function(data){
             if(data=='1'){
             	jAlert("输入优惠券号码有误！!","系统提示");
             }else if(data=='2'){
             	jAlert("已经兑换过此优惠券!","系统提示");
             }else if(data=='3')
                jAlert("优惠券兑换成功!","系统提示");
             }
      });     
}

//兑换红包
function exRedpacket(){
  var red_no=$("#red_no").val();
  if(red_no==''){
	alert("请填写红包号码!");
	return false ;
  }
  $.ajax({  	 
          type: "post",    	     
          url: "/redsumer!exRedpacket.action",
          data:{red_no:red_no},
          datatype:"json",
          success: function(data){
             if(data=='1'){
             	jAlert("输入红包号码有误！!","系统提示");
             }else if(data=='2'){
             	jAlert("已经兑换过此红包!","系统提示");
             }else if(data=='3')
                jAlert("红包兑换成功!","系统提示");
             }
      });     
}