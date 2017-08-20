<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改密码成功-${cfg_webname?if_exists}</title>
		<link href="/malltemplate/bmall/css/jt_public.css" rel="stylesheet" type="text/css"/>
		<link href="/malltemplate/bmall/css/retrievePsw.css" rel="stylesheet" type="text/css"/>
		<link href="/malltemplate/bmall/css/jquery.autocomplete.css" rel="stylesheet" type="text/css"/>
		<link href="/malltemplate/index/css/mall_public.css" rel="stylesheet" type="text/css" />
		<script src="/malltemplate/index/js/mall_top.js" type="text/javascript"></script>
		<script src="/include/common/js/jquery-1.4.4.min.js" type="text/javascript"></script>
		<script src="/include/common/js/jquery.cookie.js" type="text/javascript"></script>
		<script src="/include/common/js/jquery.autocomplete.js" type="text/javascript"></script>
		<script src="/include/common/js/common.js" type="text/javascript"></script>
		<script src="/include/common/js/commonplugin.js" type="text/javascript"></script>
		<script src="/include/common/js/jquery.masonry.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="/malltemplate/bmall/js/passSuccess.js"></script>
</head>

<body>
<@s.form action="" method="post">
 <!--顶部-->
<#include "/WEB-INF/template/bmall/default/publictop.ftl">        
<!--内容-->
<div class="registb">
   <div class="regist">
      <!--步骤提示-->
      <div class="pswSucceed"></div>
      <!---->
      <div class="regMain">
         <h2><span>找回密码</span></h2>
         <div class="regSuMian">
           <div class="regsucont">
              <div class="regsbpic"></div>
              <p>请牢记您新设置的密码，您可以用密码<a href="/login.html" >登录</a>了—祝您购物愉快！<span  id="time">5</span>秒后自动跳转</a></span></p>
           </div>
         </div>
      </div>
   </div>
</div>
        
<@s.hidden name="sl" />
<@s.hidden id="returnUrl" value="/login.html"/>
<div class="clear"></div>
<!--尾部-->
<!--底部开始-->
<#include "/a/bmall/mallbottom.html" >
 <!--底部结束-->
 </@s.form> 
</body>
</html>
