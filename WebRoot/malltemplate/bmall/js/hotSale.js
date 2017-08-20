$(document).ready(function(){
	/*特卖轮播*/
	focusm('tmfocusid',4,false,1);
	/*特卖内容块*/
	var hStimerID = null;  
	var timerRunning = false;  
	$('.tmul > li').hover(
		function(){
			$(this).addClass('addbli');
			$(this).find(".timep").show("fast");
			var addDate =$(this).find(".diftime").val();
			//注意时间格式是：2013-07-05 11:41:30.910 
			var date = eval('new Date(' + addDate.replace(/\d+(?=-[^-]+$)/,    
           function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
			addDate = new Date(Date.parse(date));
			var now = new Date();
			var difftime = addDate.getTime()-now.getTime()  ;
			//预售时间倒计时
		    hSellshowtime(difftime);
		},
		function(){
			$(this).removeClass('addbli');
			$(this).find(".timep").hide("fast");
			hSstopclock();//移开时关闭定时器
		}
	)
		/*分类显示和隐藏*/
	$('.classcont').mousemove(function(){
		$(this).find('#classlistId').show();
		$(this).find('h3,p').addClass('lclaconthover');
	});
	$('.classcont').mouseleave(function(){
		$(this).find('#classlistId').hide();
		$(this).find('h3,p').removeClass('lclaconthover');
	});
	$(".lclacont:odd").addClass("addclabg");
	$("#classMainId").hide();
	$("#h2classId").hover(
		function(){
			$("#classMainId").show();
			$(this).removeClass("h2down");
			$(this).addClass("h2up");
			$("#classMainId").hover(
			  function(){
				  $("#classMainId").show();
				  $("#h2classId").removeClass("h2down");
			      $("#h2classId").addClass("h2up");
			  },
			  function(){
				  $("#classMainId").hide();
				  $("#h2classId").removeClass("h2up");
				  $("#h2classId").addClass("h2down");
			  }
			);
		},
		function(){
			$("#classMainId").hide();
			$(this).removeClass("h2up");
			$(this).addClass("h2down");
		}
	);
});
//特卖倒计时开始
function hSellshowtime(difftime){
    var nd = 1000*24*60*60;
    var nh = 1000*60*60;
    var nm = 1000*60;
    var ns = 1000;
    var diffday = parseInt(difftime/nd);
    var diffhour = parseInt(difftime%nd/nh);
    var difmin = parseInt(difftime%nd%nh/nm);
    var difsec = parseInt(difftime%nd%nh%nm/ns);
	difftime = difftime - 1000;
	hStimerID = setTimeout('hSellshowtime(' + difftime + ')',1000);  
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
	temp='剩余时间:' + diffday + '<span>天</span>'+ diffhour + '<span>时</span>' + difmin + '<span>分</span>' + difsec + '<span>秒</span>';
	$(".timep").html(temp);
}

//特卖关闭定时器
function hSstopclock() { 
	clearTimeout(hStimerID); 
} 
