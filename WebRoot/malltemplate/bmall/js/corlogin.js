
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
