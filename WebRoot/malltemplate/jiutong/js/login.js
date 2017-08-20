$(document).ready(function() {
     var names = getck("mallLoginName");
     $("#user_name").val(names);

     //登陆账号校验
     var un = "#user_name";
     var un_conent = "请输入账号/手机号/邮箱";
     if ($(un).val() == '' || $(un).val() == un_conent) {
         $(un).val(un_conent);
     }
     //获得焦点事件
     $(un).focus(function() {
         if ($(un).val() == '' || $(un).val() == un_conent) {
             $(un).val("");
         }
     });
     //失去焦点事件
     $(un).blur(function() {
         if ($(un).val() == '') {
             $(un).val(un_conent);
         }
     });
     
     //登陆密码校验
     var password = "#passwd";
     var psw_default = "请输入密码";
      if ($(password).val() == '' || $(password).val() == psw_default) {
         $(password).val(psw_default);
     }
     //获得焦点事件
     $(password).focus(function() {
         if ($(password).val() == '' || $(password).val() == psw_default) {
             $(password).val("");
         }
     });
     //失去焦点事件
     $(password).blur(function() {
         if ($(password).val() == '') {
             $(password).val(psw_default);
         }
     });
     
     //验证码校验
     var chekcrand = "#commentrand";
     var rand_default = "请输入验证码";
     if ($(chekcrand).val() == '' || $(chekcrand).val() == rand_default) {
         $(chekcrand).val(rand_default);
     }
     //获得焦点事件
     $(chekcrand).focus(function() {
         if ($(chekcrand).val() == '' || $(chekcrand).val() == rand_default) {
             $(chekcrand).val("");
         }
     });
     //失去焦点事件
     $(chekcrand).blur(function() {
         if ($(chekcrand).val() == '') {
             $(chekcrand).val(rand_default);
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
});
        

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
//设置存储时间 
function setStorageTime(key,value){

	  var curtime = new Date().getTime();//获取当前时间
	  localStorage.setItem(key,JSON.stringify({val:value,time:curtime}));//转换成json字符串序列
}

//获取存储时间
function getStorageTime(key,exp){//exp是设置的过期时间

	  var val = localStorage.getItem(key);//获取存储的元素
	  var dataobj = JSON.parse(val);//解析出json对象
	  
	  if(new Date().getTime() - dataobj.time > exp){//如果当前时间-减去存储的元素在创建时候设置的时间 > 过期时间
	     //alert("expires");//提示过期
	     return false;
	  }else{
	     //alert("val="+dataobj.val);
	     return true;
	  }
}
 
//登陆
function webapp_login(){
   if(window.localStorage){
	   var userName=$("#user_name").val();
	   var userPassWord=$("#passwd").val();  
	   //利用HTML5的本地存储机制实现
	   var storage = window.localStorage;  
	   //存储到loaclStage    
	   storage.setItem('username', userName);
	   storage.setItem('password', userPassWord);
	   storage.setItem('isautologin', "yes");
   }
   $("#webapplogin").submit();
}
