 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>邮箱修改-${cfg_mallwebname?if_exists}</title>
 <link rel="StyleSheet" href="/include/bmember/css/account.css" type="text/css" />
</head>
<body>

<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>手机修改</span></h2>
        <!---->
		<div class="verifyDiv">
          <!--步骤-->
          <h3 class="h3Step"><span>1.输入手机号码</span><span class="morange">2.手机号码修改成功</span></h3>
          <div class="vSucced">
             <h3 class="mPhoneh3"></h3>
             <p>您修改后的号码是：<i>${newphone?if_exists}</i></br><span>您现在也可以用手机号码做为登录号码</span></p>
          </div>
        </div>

   </div>
   
  </div>
  <div class="clear"></div>
</div>
</body>
</html>
