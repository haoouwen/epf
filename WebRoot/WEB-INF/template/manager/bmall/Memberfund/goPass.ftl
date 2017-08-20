<html>
<head>
<title>找回支付密码</title>
<link rel="StyleSheet" href="/include/bmember/css/account.css" type="text/css" />
<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/include/bmember/js/memberfund.js"></script>
<script type="text/javascript" src="/include/bmember/js/checkmobileandemail.js"></script>
</head>
<body>
<@s.form action="/bmall_Memberfund_setPayPassword.action"  method="post"  name="formshopcongif" validate="true" id="pass">
<!--右边-->
  <div class="wR810">
   <!--最新-->
   <div class="rwTitle">
   		<h2><span>找回支付密码</span></h2>
        <!---->
		 <div class="payPsw">
          <!--步骤-->
          <h3><span class="morange">1.验证身份</span><span>2.设置新密码</span><span>3.设置支付密码完成</span></h3>
          <p class="findp"><b><input type="radio" name="vname" <#if check_way?if_exists=="" || check_way?if_exists="0">checked="checked"</#if> value="0" onclick="selectRadio()">邮箱验证</b>
                           <b><input type="radio" name="vname" <#if check_way?if_exists="1">checked="checked"</#if>value="1" onclick="selectRadio()">手机验证</b></p>
          <div class="verifyCont" id="checkEmail">
             <table width="100%" cellpadding="0" cellspacing="0">
                <tr><th>昵称：</th><td>${memberuser.user_name?if_exists}</td></tr>
                 <tr><th>已验证邮箱：</th><td>${memberuser.email?if_exists}</td></tr>
                 <tr><th>邮箱验证码：</th><td><input type="text" name="email_code" class="vText"><i><a href="#" onclick="sendEmailcodePasswd();">发送邮箱获取验证码</a></i><span><@s.fielderror><@s.param>email_check</@s.param></@s.fielderror></span></td></tr>
                 <tr><th></th><td><input type="button" class="graybut" value="下一步" onclick="emailSubmit();"></td></tr>
             </table>
          </div>
          
          <div class="verifyCont" id="checkPhone">
             <table width="100%" cellpadding="0" cellspacing="0">
                <tr><th>昵称：</th><td>${memberuser.user_name?if_exists}</td></tr>
                 <tr><th>已验证手机：</th><td>${memberuser.cellphone?if_exists}</td></tr>
                 <tr>
                    <th><span>*</span>图形验证码：</th><td>
                        <@s.textfield name="commentrand" maxLength="4" cssClass="vText" id="commentrand"/>
				                <img src="/imgrand.action" class=""  onclick="changeValidateCode(this)"/>
				                <span id="randError"/></span>
                    
                    </td>
                 </tr>
                 <tr><th>手机验证码：</th><td><input type="text" class="vText" name="phone_code"><i><a href="#" onclick="sendcodepasswd();">发送获取手机验证码</a></i><span><@s.fielderror><@s.param>phone_check</@s.param></@s.fielderror></span></td></tr>
                 <tr><th></th><td><input type="button" class="graybut" value="下一步" onclick="phoneSubmit();"></td></tr>
             </table>
          </div>
       </div>

   </div>
   
  </div>
  <div class="clear"></div>
</div>
<input type="hidden" id="timeMinus" value="0"/>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>
<@s.hidden name="email" id="email" value="${memberuser.email?if_exists}"/>
<@s.hidden name="phone" id="phone" value="${memberuser.cellphone?if_exists}"/>
 </@s.form>
</body>
</html>

