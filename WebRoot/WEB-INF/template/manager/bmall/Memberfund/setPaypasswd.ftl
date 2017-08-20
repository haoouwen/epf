<html>
<head>
<title><#if is_set=="0">找回支付密码<#else>设置支付密码</#if></title>
<link rel="StyleSheet" href="/include/bmember/css/account.css" type="text/css" />
</head>
<body>
	
<@s.form action="/bmall_Memberfund_setPayPassword.action"  method="post"  name="formshopcongif" validate="true">

<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span><#if is_set=="0">找回支付密码<#else>设置支付密码</#if></span></h2>
        <!---->
        <div class="payPsw">
          <!--步骤-->
          <h3 class="sh3Step"><span >1.验证身份</span><span class="morange">2.设置新密码</span><span>3.设置支付密码完成</span></h3>
          <div class="verifyCont">
             <table width="100%" cellpadding="0" cellspacing="0">
                 <tr><th><span>*</span>输入新密码：</th><td><input type="password" class="vText" name="newpasswd"  maxLength="32"><span><@s.fielderror><@s.param>newpasswd</@s.param></@s.fielderror></span></td></tr>
                 <tr><th><span>*</span>再次输入密码：</th><td><input type="password" class="vText" name="confirmpasswd"  maxLength="32"><span><@s.fielderror><@s.param>confirmpasswd</@s.param></@s.fielderror></span></td></tr>
                 <tr><th></th><td><input type="submit" class="graybut" value="下一步"></td></tr>
             </table>
          </div>
       </div>

   </div>
   
  </div>
  <div class="clear"></div>
</div>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>
<@s.hidden name="is_set"/>
 </@s.form>
</body>
</html>

