<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${cfg_webname?if_exists}APP下载</title>
<meta name="author" content="久通宏达科贸（北京）有限公司">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">     
<meta http-equiv="Pragma" content="no-cache">

<style>
html,body,div,span,applet,object,iframe,h1,h2,h3,h4,h5,h6,p,
blockquote,pre,a,abbr,acronym,address,big,cite,code,del,dfn,
em,img,ins,kbd,q,s,samp,small,strike,strong,sub,sup,tt,var,
b,u,i,center,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,
table,caption,tbody,tfoot,thead,tr,th,td,article,aside,canvas,
details,embed,figure,figcaption,footer,header,hgroup,menu,nav,
output,ruby,section,summary,time,mark,audio,video{
	margin:0;
	padding:0;
	border:0;
}
body{
	background:#ffd101;
	font-family:"Microsoft YaHei",Arial,Helvetica,sans-serif;
	font-size:12px;
	color:#888;
	-webkit-text-size-adjust:none;
}
a{color:#888;text-decoration:none;}
a:hover{color:#888;text-decoration:none;}

.viewport{
	max-width:640px;
	min-width:320px;
	margin:0 auto;
	padding-bottom:10px;
}
.pbut{
	text-align:center;
	padding:2px 0 0 0;
}
.azbut{
	display:none;
}
.pgBut{
	display:none;
}
.wxTips{
	width:100%;
	height:100%;
	background:#000;
	position:fixed;
	top:0px;
	left:0px;
	opacity:0.7;
	display:none;
}
.tishiPic{
	width:138px;
	height:100px;
	background:url("/malltemplate/jiutong/images/tishiPic.png");
	position:absolute;
	top:0px;
	right:0px;
	display:none;
}
</style>

</head>

<body>

<div class="viewport">
   <p><img src="/malltemplate/jiutong/images/dlBigPic.jpg" width="100%"></p>
   <p class="pbut">
     <a href="${cfg_andorid_url}" class="azbut"><img src="/malltemplate/jiutong/images/azBut.gif" width="140" onclick="downcount_statistics('android')"></a>
     <a href="${cfg_ios_url}" class="pgBut"><img src="/malltemplate/jiutong/images/pgBut.gif" width="140" onclick="downcount_statistics('ios')"></a>
  </p>
</div>

<div class="wxTips"></div>
<div class="tishiPic"></div>

</body>

<script src="/malltemplate/jiutong/js/jquery-1.4.4.js" type="text/javascript"></script>
<script type="text/javascript">
//初始化加载
$(document).ready(function() {
    if(isWeiXin()){
	   $(".wxTips,.tishiPic").show();
	   $(".wxTips,.tishiPic").click(function(){
			 $(".wxTips,.tishiPic").hide();
	   })
	   
	 var u = navigator.userAgent;
	 if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {
	  //安卓手机
	 $(".azbut").click(function(){
			$(".wxTips,.tishiPic").show();
	  })
	}else if (u.indexOf('iPhone') > -1) {
	  //苹果手机
	  $(".pgBut").click(function(){
			$(".wxTips,.tishiPic").show();
	   })
	}
	   	
    }
	var u = navigator.userAgent;
	if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {
	  //安卓手机
	  $(".azbut").show();
	}else if (u.indexOf('iPhone') > -1) {
	  //苹果手机
	   $(".pgBut").show();
	}else if (u.indexOf('Windows Phone') > -1) {
	//winphone手机
	}else{
	    $(".azbut").show();
	    $(".pgBut").show();
	}
	
});
function isWeiXin(){
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
        return true;
    }else{
        return false;
    }
}
function downcount_statistics(type){
	$.ajax({
          type: "post",
          url: "apidata!downcount_statistics_android.action?type="+type,
          datatype:"json",
          async:false,
          success: function(data){ 
          
          }	 
	});  
}
</script>

</html>
