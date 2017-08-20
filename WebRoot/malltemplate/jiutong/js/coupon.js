//领取优惠券
function getCoupon(coupon_id){
   var link_url = document.location.href;
   //跳回登陆前位置
		var urlhref = window.location.href;
	  	var locstr = '/webapp';
	    if(urlhref.indexOf(locstr) > -1){
	   	  var posi = urlhref.indexOf(locstr);
	   	  var loc = urlhref.substring(posi,urlhref.length);
	   	  $("#refloc").val(loc);
	   }
	$.ajax({  	 
          type: "post",    	     
          url: "/webapp/goods!ajaxCoupon.action",
          data:{coupon_id:coupon_id},
          datatype:"json",
          success: function(data){
             if(data=='1'){
             	alert("您还没登陆，请先登陆!");
             	window.location.href="/webapplogin.html?loc="+loc;
             }else if(data=='2'){
             	alert("已经领取过此优惠券!");
             }else if(data=='0'){
                alert("领取成功!");
             }else if(data=='3')
                alert("优惠券已经领取完!");
          }
      });     
}

//领取红包
function getRedpacket(red_id){
   var link_url = document.location.href;
   //跳回登陆前位置
		var urlhref = window.location.href;
	  	var locstr = '/webapp';
	    if(urlhref.indexOf(locstr) > -1){
	   	  var posi = urlhref.indexOf(locstr);
	   	  var loc = urlhref.substring(posi,urlhref.length);
	   	  $("#refloc").val(loc);
	   }
	$.ajax({  	 
          type: "post",    	     
          url: "/webapp/goods!ajaxRedpacket.action",
          data:{red_id:red_id},
          datatype:"json",
          success: function(data){
             if(data=='1'){
             	alert("您还没登陆，请先登陆!");
             	window.location.href="/webapplogin.html?loc="+loc;
             }else if(data=='2'){
             	alert("已经领取过此红包!");
             }else if(data=='0'){
                alert("领取成功!");
             }else if(data=='3')
                alert("红包已经领取完!");
          }
      });     
}

function gotoCou_Red(action){
   $("#indexForm").attr("action",action).submit();
}
