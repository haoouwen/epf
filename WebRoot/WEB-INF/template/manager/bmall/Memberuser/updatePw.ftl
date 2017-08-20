<html>
<head>
<title>修改密码</title>

 <link rel="StyleSheet" href="/include/bmember/css/account.css" type="text/css" />
 
<script type="text/javascript" src="/include/bmember/js/menberuser.js"></script>

</head>
<body >

<@s.form action="/bmall_Memberuser_updatePassword.action"  method="post" name="formshopcongif" validate="true">
<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>修改密码</span></h2>
        <!---->
        <div class="verifyDiv">
          <!--步骤-->
          <h3><span class="morange">1.修改会员登录密码</span><span>2.修改会员登录密码完成</span></h3>
          <div class="verifyCont">
             <table width="100%" cellpadding="0" cellspacing="0">
                 <tr>
                    <th><span>*</span>输入旧密码：</th><td><input type="password" class="pswText"  name="oldpasswd"><span><@s.fielderror><@s.param>oldpasswd</@s.param></@s.fielderror></span></td>
                 </tr>
                 <tr>
                    <th><span>*</span>输入新密码：</th><td><input type="password" class="pswText" name="newpasswd" onkeyup="javascript:checkpass(this)"  id="pass" onblur="setpswstrong(this)"><p id="password_label"><b class="lowb"></b></p><span><@s.fielderror><@s.param>newpasswd</@s.param></@s.fielderror></span></td>
                 </tr>
                 <tr>
                    <th><span>*</span>再次输入密码：</th><td><input type="password" class="pswText" name="confirmpasswd"><span><@s.fielderror><@s.param>confirmpasswd</@s.param></@s.fielderror></span></td>
                 </tr>
                 <tr>
                    <th></th><td><input type="submit" class="graybut" value="提交"></td>
                 </tr>
             </table>
          </div>
       </div>
                
   </div>
   
  </div>
  <div class="clear"></div>
</div>
<@s.hidden name="memberuser.pass_strength" value="0" id="psw_strong" />
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>  
 </@s.form>
</body>
</html>

