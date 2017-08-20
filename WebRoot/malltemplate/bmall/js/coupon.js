//领取优惠券
function getCoupon(coupon_id){
   var link_url = document.location.href;
   //跳回登陆前位置
		var urlhref = window.location.href;
	  	var locstr = '/mall';
	    if(urlhref.indexOf(locstr) > -1){
	   	  var posi = urlhref.indexOf(locstr);
	   	  var loc = urlhref.substring(posi,urlhref.length);
	   	  $("#refloc").val(loc);
	   }
	$.ajax({  	 
          type: "post",    	     
          url: "/mall/goods!ajaxCoupon.action",
          data:{coupon_id:coupon_id},
          datatype:"json",
          success: function(data){
             if(data=='1'){
             	jAlert("您还没登陆，请先登陆!","系统提示");
             	window.location.href="/login.html?loc="+loc;
             }else if(data=='2'){
             	jAlert("已经领取过此优惠券!","系统提示");
             }else if(data=='0'){
                jAlert("领取成功!","系统提示");
             }else if(data=='3')
                jAlert("优惠券已经领取完!","系统提示");
          }
      });     
}

//领取红包
function getRedpacket(red_id){
   var link_url = document.location.href;
   //跳回登陆前位置
		var urlhref = window.location.href;
	  	var locstr = '/mall';
	    if(urlhref.indexOf(locstr) > -1){
	   	  var posi = urlhref.indexOf(locstr);
	   	  var loc = urlhref.substring(posi,urlhref.length);
	   	  $("#refloc").val(loc);
	   }
	$.ajax({  	 
          type: "post",    	     
          url: "/mall/goods!ajaxRedpacket.action",
          data:{red_id:red_id},
          datatype:"json",
          success: function(data){
             if(data=='1'){
             	jAlert("您还没登陆，请先登陆!","系统提示");
             	window.location.href="/login.html?loc="+loc;
             }else if(data=='2'){
             	jAlert("已经领取过此红包!","系统提示");
             }else if(data=='0'){
                jAlert("领取成功!","系统提示");
             }else if(data=='3')
                jAlert("红包已经领取完!","系统提示");
          }
      });     
}


