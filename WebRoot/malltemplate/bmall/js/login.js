var login_returnurl="";
$(document).ready(function() {
    var names = getck("mallLoginName");
    $("#user_name").val(names);
 
   var cart_num=0;
	if($.cookie("ccn")!=null){
		cart_num=parseInt($.cookie("ccn"));
	}
  	 $.ajax({  	 
          type: "post",    	     
          url: "/mall/goods!getCarNumCount.action",
          datatype:"json",
          success: function(data){
          		if(cart_num!=null){
          			cart_num=parseInt(data)+cart_num;
          		}else{
          				$.cookie("ccn", null, { expires: 7, path: '/' });
          		}
          		if(cart_num ==null || cart_num=='' || cart_num<0){
					$("#cart_id_num").html("0");
					$(".numi").html("0");
				}else{
					$("#cart_id_num").html(cart_num);
					$(".numi").html(cart_num);
				}
          }
      });  
             var un = "#user_name";
             var un_conent = " ";
             if ($(un).val() == '' || $(un).val() == un_conent) {
                 $(un).val(un_conent);
                 $(un).addClass("usersize");
             }
             //获得焦点事件
             $(un).focus(function() {
                 if ($(un).val() == '' || $(un).val() == un_conent) {
                     $(un).val("");
                     $(un).removeClass("usersize");
                 }
             });
             //失去焦点事件
             $(un).blur(function() {
                 if ($(un).val() == '') {
                     $(un).val(un_conent);
                     $(un).addClass("usersize");
                 }
             });
              var urlhref = window.location.href;
         var locstr = '?loc=';
         if (urlhref.indexOf(locstr) > -1) {
             var posi = urlhref.indexOf('?loc=');
             var loc = urlhref.substring(posi, urlhref.length);
             loc = loc.replace(locstr, '');
             document.getElementById('refloc').value = loc;
         }
         
         document.onkeyup = function (event) {
            var e = event || window.event;
            var keyCode = e.keyCode || e.which;
            switch (keyCode) {
                case 13:
                    $("#login").submit();
                    break;
                default:
                    break;
            }
        }
});
        
         //验证码功能
         function changeValidateCode(obj) {
             //获取当前的时间作为参数，无具体意义   
             var timenow = new Date().getTime();
             //每次请求需要一个不同的参数，否则可能会返回同样的验证码
             //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
             obj.src = "/imgrand.action?d=" + timenow;
         }
         function sunbmit(){
         $("#login").submit();
         }
         //加入收藏
function addFavorite() {   
   if (document.all) {
      window.external.addFavorite(location.href,document.title);   
   } else if (window.sidebar) {   
   	 $(".store").attr("rel","sidebar");
     window.sidebar.addPanel(document.title,location.href, "");   
   } else{
 	 alert("加入收藏失败，请使用Ctrl+D进行添加");
   }
}

function getck(sname) { //获取单个cookies
     var acookie = document.cookie.split("; ");
     for (var i = 0; i < acookie.length; i++) {
         var arr = acookie[i].split("=");
         if (sname == arr[0]) {
             if (arr.length > 1) {
                 return unescape(arr[1]);
             } else {
                 return "";
             }
         }
     }
     return "";
 }
 //验证码功能
 function changeValidateCode(obj) {
     //获取当前的时间作为参数，无具体意义   
     var timenow = new Date().getTime();
     //每次请求需要一个不同的参数，否则可能会返回同样的验证码
     //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
     obj.src = "/imgrand.action?d=" + timenow;
 }
 
//QQ登录 
function toLogin(){
   //以下为按钮点击事件的逻辑。注意这里要重新打开窗口
   //否则后面跳转到QQ登录，授权页面时会直接缩小当前浏览器的窗口，而不是打开新窗口http://www.epff.cc/
   var state = $("#state").val();
   var childWindow = window.open("https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101216755&redirect_uri=http://www.epff.cc/qqlogin.do&scop=get_user_info,get_info&state="+state);
}
 
//微信扫描登录
function open_weixin(){
    var redirect_uri = UrlEncode("http://www.epff.cc/weixinlogin.do");
    var shloginurl="https://open.weixin.qq.com/connect/qrconnect?appid=wxee8fbbeaa95e7dd3&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_login&state=00d8dec0f8cc3659d4914cada8be27d9#wechat_redirect";
    window.open(shloginurl,'souhu');
}
 
function UrlEncode(str){     
  var ret="";     
  var strSpecial="!\"#$%&'()*+,/:;<=>?[]^`{|}~%";     
  var tt= "";    

  for(var i=0;i<str.length;i++){     
   var chr = str.charAt(i);     
    var c=str2asc(chr);     
    tt += chr+":"+c+"n";     
    if(parseInt("0x"+c) > 0x7f){     
      ret+="%"+c.slice(0,2)+"%"+c.slice(-2);     
    }else{     
      if(chr==" ")     
        ret+="+";     
      else if(strSpecial.indexOf(chr)!=-1)     
        ret+="%"+c.toString(16);     
      else     
        ret+=chr;     
    }     
  }     
  return ret;     
}  

function str2asc(strstr){
return ("0"+strstr.charCodeAt(0).toString(16)).slice(-2);
} 
