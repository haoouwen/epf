<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>秒杀专场</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/malltemplate/bmall/css/activity.css" rel="stylesheet" type="text/css" />
<script src="/malltemplate/bmall/js/activity.js" type="text/javascript"></script>
<script src="/wro/mallpublic.js" type="text/javascript"></script>
<link href="/wro/mallpublic.css" rel="stylesheet" type="text/css"/>
<script src="/malltemplate/bmall/js/jt_product.js" type="text/javascript"></script>
<link href="/malltemplate/bmall/css/jt_product.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	var activity_turn = "1";//活动轮数，默认第一轮
	var activity_flag = "wait";//活动标志 wait=倒计时 start=开始
	//活动时间段分布 分别对应4个时间段
	var arr_time_start = new Array("2015/12/23 10:00:00","2015/12/24 10:45:00","2015/12/25 13:00:00","2015/12/25 16:00:00","1990/01/01 00:00:00");
	var arr_time_stop  = new Array("2015/12/24 23:59:59","2015/12/25 12:00:00","2015/12/25 15:15:00","2015/12/25 18:15:00","1990/01/01 00:00:00");
	var interval = setInterval(getRTime,50);//第一个定时器:活动开始计时器启动
	var interval_activity;
	var nFlag = true;
    function getRTime(){//活动开始倒计时
    	//获取时间
        var EndTime= new Date(arr_time_start[activity_turn-1]); 
        var NowTime = new Date();
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
		if(t<=1000){
			if(y=="1990"){//最后1轮的时候，活动结束，销毁定时器
				clearInterval(interval);
				$(".next_show").html("最后一场");
			}else{//轮数不为5的时候，第n场开始抢购
				$("#time_run").val("ok");
				$(".now_show").html("第"+activity_turn+"场开始  ");
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
    function getRTime1(){//活动结束倒计时
        var EndTime= new Date(arr_time_stop[activity_turn-2]); 
        var NowTime = new Date();
        var t =EndTime.getTime() - NowTime.getTime();
        var d=Math.floor(t/1000/60/60/24);
        var h=Math.floor(t/1000/60/60%24);
        var m=Math.floor(t/1000/60%60);
        var s=Math.floor(t/1000%60);
        if(d<0){d=0;}
        if(h<0){h=0;}
        if(m<0){m=0;}
        if(s<0){s=0;}
        $(".now_time").html("本场剩余时间:"+d+":"+h+":"+m+":"+s);
        var tempTime = new Date(arr_time_stop[activity_turn-1]); 
		var y=tempTime.getFullYear();
		if(t<=50){
			$("#time_run").val("run");
			clearInterval(interval_activity);
			nFlag = true;
			if(y=="1990"){
				$(".activity_time").html("活动结束!");
			}else{
				var st = activity_turn-1;
				$(".now_show").html("第"+st+"场结束 ");
				$(".now_time").html("");
			}
		}
    }
</script>

</head>
<body>
<form id="form1" action="">
<input type="hidden" id="time_run" value="run"/>
<!--头部--> 
<#include "/a/bmall/malltop.html">
<!--导航-->
<div class="navBack">
   <div class="navDiv">
      <!-- --分类 -->
       <#include "/a/bmall/mallcat.html">
     <!-- -导航 -->
       <#include "/a/bmall/mallnav.html">
   </div>
</div>
<!--内容-->
<div class="postion">
</div>
<div class="activity_main">
	<div class="activity_title_001"></div>
	<div class="activity_goods_001">
		<div class="activity_time">
			<img src="/malltemplate/bmall/images/activity_time_01.png" height="22" style="vertical-align:middle;"/>
			<span class="now_show"></span><span class="now_time"></span>
			<span class="next_show">
				下一场倒计时:
				<span class="activity_num activity_num_d_1" >0</span> 天
				<span class="activity_num activity_num_h_1" >0</span> 时
				<span class="activity_num activity_num_m_1" >0</span> 分
				<span class="activity_num activity_num_s_1" >0</span> 秒
			</span>
		</div>
		<div class="activity_good_001">
			<a href="javascript:void(0)" onclick="submit_url('mall-goodsdetail-376.html');"><img src="/malltemplate/bmall/images/activity_001.png"/></a>
		</div>
		<div class="activity_time">
			<img src="/malltemplate/bmall/images/activity_time_01.png" height="22" style="vertical-align:middle;"/>
			<span class="now_show"></span><span class="now_time"></span>
			<span class="next_show">
				下一场倒计时:
				<span class="activity_num activity_num_d_1" >0</span> 天
				<span class="activity_num activity_num_h_1" >0</span> 时
				<span class="activity_num activity_num_m_1" >0</span> 分
				<span class="activity_num activity_num_s_1" >0</span> 秒
			</span>
		</div>
		<div class="activity_good_001">
			<a href="javascript:void(0)" onclick="submit_url('mall-goodsdetail-373.html');"><img src="/malltemplate/bmall/images/activity_002.png"/></a>
		</div>
		<div class="activity_time">
			<img src="/malltemplate/bmall/images/activity_time_01.png" height="22" style="vertical-align:middle;"/>
			<span class="now_show"></span><span class="now_time"></span>
			<span class="next_show">
				下一场倒计时:
				<span class="activity_num activity_num_d_1" >0</span> 天
				<span class="activity_num activity_num_h_1" >0</span> 时
				<span class="activity_num activity_num_m_1" >0</span> 分
				<span class="activity_num activity_num_s_1" >0</span> 秒
			</span>
		</div>
		<div class="activity_good_001">
			<a href="javascript:void(0)" onclick="submit_url('mall-goodsdetail-1363.html');"><img src="/malltemplate/bmall/images/activity_003.png"/></a>
		</div>
		<div class="activity_time">
			<img src="/malltemplate/bmall/images/activity_time_01.png" height="22" style="vertical-align:middle;"/>
			<span class="now_show"></span><span class="now_time"></span>
			<span class="next_show">
				下一场倒计时:
				<span class="activity_num activity_num_d_1" >0</span> 天
				<span class="activity_num activity_num_h_1" >0</span> 时
				<span class="activity_num activity_num_m_1" >0</span> 分
				<span class="activity_num activity_num_s_1" >0</span> 秒
			</span>
		</div>
		<div class="activity_good_001">
			<a href="javascript:void(0)" onclick="submit_url('mall-goodsdetail-371.html');"><img src="/malltemplate/bmall/images/activity_004.png"/></a>
		</div>
		<div class="activity_time">
			<img src="/malltemplate/bmall/images/activity_time_01.png" height="22" style="vertical-align:middle;"/>
			<span class="now_show"></span><span class="now_time"></span>
			<span class="next_show">
				下一场倒计时:
				<span class="activity_num activity_num_d_1" >0</span> 天
				<span class="activity_num activity_num_h_1" >0</span> 时
				<span class="activity_num activity_num_m_1" >0</span> 分
				<span class="activity_num activity_num_s_1" >0</span> 秒
			</span>
		</div>
		<div class="activity_good_001">
			<a href="javascript:void(0)" onclick="submit_url('mall-goodsdetail-435.html');"><img src="/malltemplate/bmall/images/activity_005.png"/></a>
		</div>
		<div class="activity_time">
			<img src="/malltemplate/bmall/images/activity_time_01.png" height="22" style="vertical-align:middle;"/>
			<span class="now_show"></span><span class="now_time"></span>
			<span class="next_show">
				下一场倒计时:
				<span class="activity_num activity_num_d_1" >0</span> 天
				<span class="activity_num activity_num_h_1" >0</span> 时
				<span class="activity_num activity_num_m_1" >0</span> 分
				<span class="activity_num activity_num_s_1" >0</span> 秒
			</span>
		</div>
		<div class="activity_good_001">
			<a href="javascript:void(0)" onclick="submit_url('mall-goodsdetail-2663.html');"><img src="/malltemplate/bmall/images/activity_006.png"/></a>
		</div>
		<div class="activity_time">
			<img src="/malltemplate/bmall/images/activity_time_01.png" height="22" style="vertical-align:middle;"/>
			<span class="now_show"></span><span class="now_time"></span>
			<span class="next_show">
				下一场倒计时:
				<span class="activity_num activity_num_d_1" >0</span> 天
				<span class="activity_num activity_num_h_1" >0</span> 时
				<span class="activity_num activity_num_m_1" >0</span> 分
				<span class="activity_num activity_num_s_1" >0</span> 秒
			</span>
		</div>
		<div class="activity_good_001">
			<a href="javascript:void(0)" onclick="submit_url('mall-goodsdetail-2664.html');"><img src="/malltemplate/bmall/images/activity_007.png"/></a>
		</div>
		<div class="activity_time">
			<img src="/malltemplate/bmall/images/activity_time_01.png" height="22" style="vertical-align:middle;"/>
			<span class="now_show"></span><span class="now_time"></span>
			<span class="next_show">
				下一场倒计时:
				<span class="activity_num activity_num_d_1" >0</span> 天
				<span class="activity_num activity_num_h_1" >0</span> 时
				<span class="activity_num activity_num_m_1" >0</span> 分
				<span class="activity_num activity_num_s_1" >0</span> 秒
			</span>
		</div>
		<div class="activity_good_001">
			<a href="javascript:void(0)" onclick="submit_url('mall-goodsdetail-427.html');"><img src="/malltemplate/bmall/images/activity_008.png"/></a>
		</div>
	</div>
</div>
<!--返回顶部-->
<div class="returnTop"></div>
</form>
<script type="text/javascript">
function init_a(){
		$("#time_run").val("run");
}
init_a();
</script>
</body>
</html>


