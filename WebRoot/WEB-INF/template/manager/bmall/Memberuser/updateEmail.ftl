<html>
<head>
<title>邮箱修改</title>
 <link rel="StyleSheet" href="/include/bmember/css/account.css" type="text/css" />
<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/include/bmember/js/checkmobileandemail.js"></script>

</head>
<body>
	
<@s.form action="/bmall_Memberuser_updateE.action"  method="post" name="formshopcongif" validate="true">
<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>邮箱修改</span></h2>
        <!---->
        <div class="verifyDiv">
          <!--步骤-->
          <h3><span class="morange">1.输入邮箱地址</span><span>2.邮箱修改成功</span></h3>
          <div class="verifyCont">
             <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <th>旧的邮箱号码：</th><td>${memberuser.email?if_exists}</td>
                 </tr>
                 <tr>
                    <th><span>*</span>新的邮箱号码：</th><td><input type="text" class="vText" name="newemail" id="email"  maxlength="30" onblur="EmailIsNull();"  onfocus="EmailForm();"><i><a href="#" id="cpc"  onclick="sendEmailcode();">发送邮箱获取验证码</a></i><span><@s.fielderror><@s.param>updateEmail</@s.param></@s.fielderror></span><span id="emailError" /></td>
                 </tr>
                 <tr>
                    <th><span>*</span>邮箱验证码：</th><td><input type="text" class="vText" id="cp_check" name="cp_check"  onblur="cpIsNull();" maxlength="6"><@s.fielderror><@s.param>cp_phone</@s.param></@s.fielderror></span><span id="cpError" /></td>
                 </tr>
                 <tr>
                    <th></th><td><input type="buttom" onclick="checkEmailForm();" class="graybut" value="提交"></td>
                 </tr>
             </table>
          </div>
       </div>
        
     </div>
   
  </div>
  <div class="clear"></div>
</div>
<@s.hidden name="msgcheck.cp_type"  value="4" id="cp_type"/>
<input type="hidden" id="timeMinus" value="0"/>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>  
 </@s.form>
</body>
</html>

