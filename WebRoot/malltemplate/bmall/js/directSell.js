var dstimerID = null;  
var timerRunning = false;  
$(document).ready(function(){
	/*预售轮播*/
	focusm('ysfocusid',4,false,1);
	/*预售中*/
	$(".ysul > li").hover(
		function(){
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
			if(timerRunning) { 
				clearTimeout(timerID); 
			}
			 //预售时间倒计时
		    dSellshowtime(difftime);
		},
		function(){
			$(this).find(".ystime").hide("fast");
			$(this).find(".ysz").show();
			dsstopclock();//移开时关闭定时器
		}
	)
	var $ysli = $("."+"ysul"+" "+"li");
	var len = $ysli.length;
	for(var i=0 ;i<len;i++){
		if((i+1)%4 == 0){
			$(".ysul > li:eq("+i+")").addClass('addlib');
		}
	};
});
//预售倒计时开始
function dSellshowtime(difftime){
    var nd = 1000*24*60*60;
    var nh = 1000*60*60;
    var nm = 1000*60;
    var ns = 1000;
    var diffday = parseInt(difftime/nd);
    var diffhour = parseInt(difftime%nd/nh);
    var difmin = parseInt(difftime%nd%nh/nm);
    var difsec = parseInt(difftime%nd%nh%nm/ns);
	difftime = difftime - 1000;
	dstimerID = setTimeout('dSellshowtime(' + difftime + ')',1000);  
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
	    temp='<span>预售结束</span>';
		$(".ystime").html(temp);
	    return;
	}  
	temp='<span>预售中:' + diffday + '天'+ diffhour + '时' + difmin + '分' + difsec + '秒</span>';
	$(".ystime").html(temp);
}

//预售关闭定时器
function dsstopclock() { 
	if(timerRunning)  
	clearTimeout(dstimerID);  
	timerRunning = false;  
} 