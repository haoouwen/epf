var isBuySelf=$("#isBuySelf").val();
		if(isBuySelf=='0'){
		  	jAlert("卖家不能购买自己的商品!","系统提示");
		}
		//跳回登陆前位置
		var urlhref = window.location.href;
	  	var locstr = '/spikedetail';
	    if(urlhref.indexOf(locstr) > -1){
	   	  var posi = urlhref.indexOf(locstr);
	   	  var loc = urlhref.substring(posi,urlhref.length);
	   	  $("#refloc").val(loc);
	   }
//离开始时间倒计时
    var difftime = $("#starttime").val(); 
    spikeStart(difftime);
 //离结束时间倒计时
    var difftime = $("#endtime").val(); 
    spikeEnd(difftime);
  