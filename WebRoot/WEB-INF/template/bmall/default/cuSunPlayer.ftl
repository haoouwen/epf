<SCRIPT LANGUAGE=JavaScript>
<!--
function getLight(pars)
{	
	//alert("播放器模式参数值："+string+"");
	if(pars == "open")
	{
		close_light(this);
	}
	else
	{
		close_light(this);
	}
}


//兼容性
function thisMovie(movieName) {
    if (navigator.appName.indexOf("Microsoft") != -1) {
        return window[movieName]
    }
    else {
        return document[movieName]
    }
}

//-->
</SCRIPT> 

<style type="text/css">
<!--
.video { OVERFLOW: hidden; WIDTH: 100%; POSITION: relative}
.close_light_bg {DISPLAY: none; BACKGROUND: #000; FILTER: alpha(opacity = 85); LEFT: 0px; WIDTH: 100%; POSITION: absolute; TOP: 0px; HEIGHT: 100%; opacity: .85}
-->
</style>
<DIV class=close_light_bg id=close_light_bg></DIV>
<!--极酷播放器/代码开始-->

<script type="text/javascript" src="include/components/CuSunPlayerV2/swfobject.js"></script>
<div class="video" id="CuPlayer"><b>网页视频播放器加载中，请稍后...</b></div>
<script type="text/javascript">
var so = new SWFObject("include/components/CuSunPlayerV2/player.swf","ply","${video_width?if_exists}","${video_height?if_exists}","9","#000000");
so.addParam("allowfullscreen","true");
so.addParam("allowscriptaccess","always");
so.addParam("wmode","opaque");
so.addParam("quality","high");
so.addParam("salign","lt");
//播放器设置文件-----------------------------
so.addVariable("JcScpFile","include/components/CuSunPlayerV2/CuSunV2set.xml");
//视频文件及略缩图--------------------------
//so.addVariable("JcScpServer","rtmp://www.yoursite.com/vod")；
so.addVariable("JcScpVideoPath","${(goods.goods_video)?if_exists}");
so.addVariable("JcScpVideoPathHD","${(goods.goods_video)?if_exists}");
so.addVariable("JcScpImg","include/components/CuSunPlayerV2/images/rbtb2c2c.jpg"); 
//-前置Flash广告-----------------------------
so.addVariable("ShowJcScpAFront","no");
so.addVariable("JcScpCountDownsPosition","top-left");
so.addVariable("JcScpCountDowns","4"); 
so.addVariable("JcScpAFrontW","520");
so.addVariable("JcScpAFrontH","325"); 
so.addVariable("JcScpAFrontPath","Images/SevenColorPlayer_650x418.swf"); 
so.addVariable("JcScpAFrontLink","http://yxp.163.com/act/20120214.html"); 
//-视频广告参数-----------------------------
so.addVariable("ShowJcScpAVideo","no");
so.addVariable("JcScpAVideoPath","http://www.roewe.com.cn/download/roewew5/video/01/video/1.flv");
//-暂停广告参数-----------------------------
so.addVariable("ShowJcScpAPause","yes");　
so.addVariable("JcScpAPauseW","680");　
so.addVariable("JcScpAPauseH","240");　
so.addVariable("JcScpAPausePath","include/components/CuSunPlayerV2/images/rbtb2c2c.jpg");　 
so.addVariable("JcScpAPauseLink","http://www.xxxx.net"); 
//-角标广告参数-----------------------------
so.addVariable("ShowJcScpACorner","no");　
so.addVariable("JcScpACornerW","85");　
so.addVariable("JcScpACornerH","50");　
so.addVariable("JcScpACornerPath","include/components/CuSunPlayerV2/images/rbtb2c2c.jpg");　
so.addVariable("JcScpACornerPosition","bottom-right");
so.addVariable("JcScpACornerLink","http://www.xxxxx.com"); 


//-----------------------------------------
so.addVariable("JcScpSharetitle","标题信息"); 
so.write("CuPlayer");
</script>
<SCRIPT language=javascript src="include/components/CuSunPlayerV2/action.js" type=text/javascript></SCRIPT>
<!--极酷播放器/代码结束-->
