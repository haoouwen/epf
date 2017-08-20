 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员注册-${cfg_webname?if_exists}</title>
		<script src="/include/common/js/jquery-1.4.4.min.js" type="text/javascript"></script>
		<script src="/include/common/js/jquery.cookie.js" type="text/javascript"></script>
		<script src="/include/common/js/jquery.autocomplete.js" type="text/javascript"></script>
		<script src="/include/common/js/common.js" type="text/javascript"></script>
		<script src="/include/common/js/commonplugin.js" type="text/javascript"></script>
		<script src="/include/common/js/jquery.masonry.min.js" type="text/javascript"></script>
        <script src="/malltemplate/bmall/js/jt_public.js" type="text/javascript"></script>		
		<link href="/malltemplate/bmall/css/jt_public.css" rel="stylesheet" type="text/css"/>
		<link href="/malltemplate/bmall/css/regist.css" rel="stylesheet" type="text/css"/>
		<link href="/malltemplate/bmall/css/jquery.autocomplete.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<!--顶部-->
<#include "/WEB-INF/template/bmall/default/publictop.ftl">
<!--内容-->
<div class="registb">
   <div class="regist">
      <!---->
      <div class="regMain">
         <h2><span>会员注册</span></h2>
         <div class="regSuMian">
           <div class="regsucont">
              <div class="regsbpic"></div>
              <p>您可以<a href="/login.html">登录</a>或到<a href="/">首页</a>挑选您喜欢的商品,祝您购物愉快！</p>
           </div>
         </div>
      </div>
   </div>
</div>
<div class="clear"></div>
<#include "/a/bmall/mallbottom.html">
</body>
</html>
