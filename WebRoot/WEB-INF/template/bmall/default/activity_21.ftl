<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>秒杀专场</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/malltemplate/activity_002/css/activity_201.css" rel="stylesheet" type="text/css" />
<script src="/malltemplate/activity_002/js/activity_201.js" type="text/javascript"></script>
<script src="/wro/mallpublic.js" type="text/javascript"></script>
<link href="/wro/mallpublic.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
	var interval = setInterval(getRTime,50);//第一个定时器:活动开始计时器启动
	var interval3 = setInterval(getNowTime,1000);//获取服务器时间
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
<div class="postion" style="height:0">
</div>
<div class="activity_main_001">
	<!--活动头部-->
	<div><img src="/malltemplate/activity_002/images/201/activity_title_001.png" style="width:100%;height:100%;"/></div>
	<!--背景-->
	<div class="div_bg"><img src="/malltemplate/activity_002/images/201/activity_background_001.png" style="width:100%;height:100%;"/></div>
	<div class="activity_goods_001">
		<div class="div_bar">
			<img src="/malltemplate/activity_002/images/201/activity_bar_001.png" />
		</div>
		<div class="activity_time" style="line-height:20px;height:35px;vertical-align:top;">
			<img src="/malltemplate/activity_002/images/201/activity_time_001.png" height="22" style="vertical-align:middle;"/>
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
			<a href="javascript:void(0)" onclick="submit_url('mall-goodsdetail-376.html');"><img src="/malltemplate/activity_002/images/201/activity_goods_001.png"/></a>
		</div>
		<div class="activity_time">
			<img src="/malltemplate/activity_002/images/201/activity_time_001.png" height="22" style="vertical-align:middle;"/>
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
			<a href="javascript:void(0)" onclick="submit_url('mall-goodsdetail-373.html');"><img src="/malltemplate/activity_002/images/201/activity_goods_002.png"/></a>
		</div>
		<div class="activity_time">
			<img src="/malltemplate/activity_002/images/201/activity_time_001.png" height="22" style="vertical-align:middle;"/>
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
			<a href="javascript:void(0)" onclick="submit_url('mall-goodsdetail-371.html');"><img src="/malltemplate/activity_002/images/201/activity_goods_003.png"/></a>
		</div>
		<div class="activity_time">
			<img src="/malltemplate/activity_002/images/201/activity_time_001.png" height="22" style="vertical-align:middle;"/>
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
			<a href="javascript:void(0)" onclick="submit_url('mall-goodsdetail-435.html');"><img src="/malltemplate/activity_002/images/201/activity_goods_004.png"/></a>
		</div>
		<div class="activity_time">
			<img src="/malltemplate/activity_002/images/201/activity_time_001.png" height="22" style="vertical-align:middle;"/>
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
			<a href="javascript:void(0)" onclick="submit_url('mall-goodsdetail-2663.html');"><img src="/malltemplate/activity_002/images/201/activity_goods_005.png"/></a>
		</div>
		<div class="activity_time">
			<img src="/malltemplate/activity_002/images/201/activity_time_001.png" height="22" style="vertical-align:middle;"/>
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
			<a href="javascript:void(0)" onclick="submit_url('mall-goodsdetail-2664.html');"><img src="/malltemplate/activity_002/images/201/activity_goods_006.png"/></a>
		</div>
		<div class="activity_time">
			<img src="/malltemplate/activity_002/images/201/activity_time_001.png" height="22" style="vertical-align:middle;"/>
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
			<a href="javascript:void(0)" onclick="submit_url('mall-goodsdetail-427.html');"><img src="/malltemplate/activity_002/images/201/activity_goods_007.png"/></a>
		</div>
		<div class="activity_time">
			<img src="/malltemplate/activity_002/images/201/activity_time_001.png" height="22" style="vertical-align:middle;"/>
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
			<a href="javascript:void(0)" onclick="submit_url('mall-goodsdetail-434.html');"><img src="/malltemplate/activity_002/images/201/activity_goods_008.png"/></a>
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


