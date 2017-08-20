//初始化加载
$(document).ready(function() {
    if(isWeiXin()){
        $("#lsq").html("微信浏览器");
    }else{
      $("#lsq").html("普通浏览器");
    }
    
var u = navigator.userAgent;
if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {
//安卓手机
$("#lsq1").html("安卓手机");
}else if (u.indexOf('iPhone') > -1) {
//苹果手机
$("#lsq1").html("苹果手机");
}else if (u.indexOf('Windows Phone') > -1) {
//winphone手机
$("#lsq1").html("winphone手机");
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
//手机端判断各个平台浏览器及操作系统平台
function checkPlatform(){
	if(/android/i.test(navigator.userAgent)){
		document.write("This is Android'browser.");//这是Android平台下浏览器
	}
	if(/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)){
		document.write("This is iOS'browser.");//这是iOS平台下浏览器
	}
	if(/MicroMessenger/i.test(navigator.userAgent)){
		document.write("This is MicroMessenger'browser.");//这是微信平台下浏览器
	}
}
