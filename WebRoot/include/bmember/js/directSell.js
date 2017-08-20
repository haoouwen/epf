$(document).ready(function(){
	/*预售中*/
	var dstimerID = null;  
	var timerRunning = false;  
	$(".payfinal").hover(
		function(){
			$(this).find(".ystime").show("fast");
			var addDate =$(this).find(".diftime").val();
			//alert(addDate);
			//注意时间格式是：2013-07-05 11:41:30.910 
			var date = eval('new Date(' + addDate.replace(/\d+(?=-[^-]+$)/,    
           function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
			addDate = new Date(Date.parse(date));
			var now = new Date();
			var difftime = addDate.getTime()-now.getTime()  ;
			 //预售时间倒计时
		    dSellshowtime(difftime,this);
		},
		function(){
			$(this).find(".ystime").hide("fast");
			dsstopclock();//移开时关闭定时器
		}
	)
	$(".payear").hover(
		function(){
			$(this).find(".ystime").show("fast");
			var addDate =$(this).find(".diftime").val();
			//alert(addDate);
			//注意时间格式是：2013-07-05 11:41:30.910 
			var date = eval('new Date(' + addDate.replace(/\d+(?=-[^-]+$)/,    
           function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
			addDate = new Date(Date.parse(date));
			var now = new Date();
			var difftime = addDate.getTime()-now.getTime()  ;
			 //预售时间倒计时
		    dSellshowtime(difftime,this);
		},
		function(){
			$(this).find(".ystime").hide("fast");
			dsstopclock();//移开时关闭定时器
		}
	)
	
});
/*
function onHover(){
	$(this).find(".ysz").hide();
			$(this).find(".ystime").show("fast");
			var addDate =$(this).find(".diftime").val();
			//alert(addDate);
			//注意时间格式是：2013-07-05 11:41:30.910 
			var date = eval('new Date(' + addDate.replace(/\d+(?=-[^-]+$)/,    
           function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
			addDate = new Date(Date.parse(date));
			var now = new Date();
			var difftime = addDate.getTime()-now.getTime()  ;
			 //预售时间倒计时
		    dSellshowtime(difftime);
}
function onOver(){
	$(this).find(".ystime").hide("fast");
			$(this).find(".ysz").show();
			dsstopclock();//移开时关闭定时器
}
*/
//预售倒计时开始
function dSellshowtime(difftime,obj){
    var nd = 1000*24*60*60;
    var nh = 1000*60*60;
    var nm = 1000*60;
    var ns = 1000;
    var diffday = parseInt(difftime/nd);
    var diffhour = parseInt(difftime%nd/nh);
    var difmin = parseInt(difftime%nd%nh/nm);
    var difsec = parseInt(difftime%nd%nh%nm/ns);
	difftime = difftime - 1000;
	dstimerID = setTimeout('dSellshowtime(' + difftime + ','+obj+')',1000);  
	timerRunning = true;  
	if(diffday <10 ){
		diffday = "0"+diffday;
	}
	if(diffhour <10 ){
		diffhour = "0"+diffhour;
	}
	if(difmin <10 ){
		difmin = "0"+difmin;
	}
	if(difsec <10 ){
		difsec = "0"+difsec;
	}
	
	if (diffday <1 && diffhour < 1 && diffhour < 1 && difsec < 1){   
		dsstopclock();
	}	
	if(difftime<0){
	    dsstopclock();
	    return;
	}  
	temp='<span>请在' + diffday + '天'+ diffhour + '时' + difmin + '分' + difsec + '秒内完成支付</span>';
	$(obj).find(".ystime").html(temp);
}

//预售关闭定时器
function dsstopclock() { 
	clearTimeout(dstimerID); 
}
 //判断定金支付时间或者尾款支付时间是否到期
function pretimeout(actionName,paraStr,order_id_str){
  var flag;
  $.ajax({
    type:"post",
    url:"/mall/directOrder!pretimeout.action?order_id_str="+order_id_str,
    datatype:"json",
    async:false,
    success:function(data){
       if(data=="0"){//定金支付超时判断
          flag="0";
       }else if(data=="1"){//尾款支付超时判断
          flag="1";
       }else if(data=="2"){
          flag="2";
       }
    }
  });
  if(flag=="0"){
    alert("抱歉,该订单已经超过定金支付时间!");
    return false;
  }else if(flag=="1"){
    alert("抱歉,该订单已经超过尾款支付时间!");
    return false;
  }else if(flag=="2"){
    linkToInfo(actionName,paraStr);
  }
}
