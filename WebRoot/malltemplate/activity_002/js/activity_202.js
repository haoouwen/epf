var activity_turn = "1";//活动轮数，默认第一轮
var activity_flag = "wait";//活动标志 wait=倒计时 start=开始
//活动时间段分布 分别对应4个时间段
var arr_time_start = new Array("2015/12/31 14:00:00","2016/01/01 00:00:00","2016/01/01 10:00:00","2016/01/01 14:00:00","2016/01/02 10:00:00","2016/01/02 14:00:00","2016/01/02 16:00:00","1990/01/01 00:00:00");
var arr_time_stop  = new Array("2015/12/31 14:15:00","2016/01/01 00:15:00","2016/01/01 10:15:00","2016/01/01 14:15:00","2016/01/02 10:15:00","2016/01/02 14:15:00","2016/01/02 16:15:00","1990/01/01 00:00:00");
var interval_activity;
var nFlag = true;
var now_time_v = new Date();
//获取服务器的当前时间
function getNowTime(){
    $.ajax({
		type:"post",
		url:"/mall/goods!getNowTime.action",
		datatype:"json",
		async:false,
		success:function(data){
			now_time_v= data;
		}
	});
}

//活动开始倒计时
function getRTime(){
	//获取时间
	var EndTime= new Date(arr_time_start[activity_turn-1]); 
	var NowTime = new Date(now_time_v);
	var t =EndTime.getTime() - NowTime.getTime();
	var y=EndTime.getFullYear();
	var d=Math.floor(t/1000/60/60/24);
	var h=Math.floor(t/1000/60/60%24);
	var m=Math.floor(t/1000/60%60);
	var s=Math.floor(t/1000%60);
	if(d<0){d=0;}
	if(h<0){h=0;}
	if(m<0){m=0;}
	if(s<0){s=0;}
	//剩余时间不足1秒
	if(t<=0){
		if(y=="1990"){//最后1轮的时候，活动结束，销毁定时器
			clearInterval(interval);
			$(".next_show").html("最后一场");
		}else{//轮数不为5的时候，第n场开始抢购
			$("#time_run").val("ok");
			$(".now_show").html("第"+activity_turn+"场开始  ");
			$(".now_show").show();
			$(".next_show").hide();
			//活动结束计时器启动
			if(nFlag){
				interval_activity = setInterval(getRTime1,50);//第二个定时器:活动剩余时间
				nFlag = false;
			}
			activity_turn++;
		}
	}else{
		//显示剩余时间
		$(".activity_num_d_1").html(d);
		$(".activity_num_h_1").html(h);
		$(".activity_num_m_1").html(m);
		$(".activity_num_s_1").html(s);
	}
}
//活动结束倒计时
function getRTime1(){
	var EndTime= new Date(arr_time_stop[activity_turn-2]); 
	var NowTime = new Date(now_time_v);
	var t =EndTime.getTime() - NowTime.getTime();
	var d=Math.floor(t/1000/60/60/24);
	var h=Math.floor(t/1000/60/60%24);
	var m=Math.floor(t/1000/60%60);
	var s=Math.floor(t/1000%60);
	if(d<0){d=0;}
	if(h<0){h=0;}
	if(m<0){m=0;}
	if(s<0){s=0;}
	$(".now_time").html("本场剩余时间  "+d+"天"+h+"时"+m+"分"+s+"秒");
	var tempTime = new Date(arr_time_stop[activity_turn-1]); 
	var y=tempTime.getFullYear();
	if(t<=0){
		$("#time_run").val("run");
		clearInterval(interval_activity);
		nFlag = true;
		if(y=="1990"){
			$(".activity_time").html("活动结束!");
			clearInterval(interval3);
		}else{
			var st = activity_turn-1;
			$(".now_show").html("第"+st+"场结束 ");
			$(".now_time").html("");
			$(".now_show").hide();
			$(".next_show").show();
		}
	}
}
//点击图片动作
function submit_url(url){
	var t = $("#time_run").val();
	if(t=="ok"){
		window.open(url);
	}else{
		alert("活动暂未开始，请耐心等待！");
	}
}
