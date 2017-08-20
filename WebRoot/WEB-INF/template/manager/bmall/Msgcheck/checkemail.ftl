 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>邮箱验证-${cfg_mallwebname?if_exists}</title>
 <link rel="StyleSheet" href="/include/bmember/css/account.css" type="text/css" />
<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/include/bmember/js/checkmobileandemail.js"></script>
<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
<script type="text/javascript" src="/malltemplate/bmall/js/popupLayer.js"></script> 
</head>
<body>   

<@s.form action="/bmall_Msgcheck_sendcheckemail.action" method="post">

<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>验证邮箱</span></h2>
        <!---->
        <div class="verifyDiv">
          <!--步骤-->
          <h3><span class="morange">1.输入邮箱地址</span><span>2.邮箱验证成功</span></h3>
          <div class="verifyCont">
             <table width="100%" cellpadding="0" cellspacing="0">
                 <tr>
                    <th><span>*</span>邮箱号码：</th><td><input type="text" class="vText" id="email" name="msgcheck.cp_phone" maxlength="30" onblur="EmailIsNull();"  onfocus="EmailForm();"><span><@s.fielderror><@s.param>msgcheck.cp_phone</@s.param></@s.fielderror></span><span id="emailError" /></td>
                 </tr>
                 <tr>
                    <th><span>*</span>图形验证码：</th><td><input type="text" class="vText" name="commentrand" maxLength="4" id="commentrand"/><span style="display:inline-block;padding:7px;margin-left:15px;"><img src="/imgrand.action" onclick="changeValidateCode(this);"/></span><span id="randError"/></span></td>
                 </tr>
                 <tr>
                    <th><span>*</span>邮箱验证码：</th><td><input type="text" class="vText" id="cp_check" name="msgcheck.cp_check"  onblur="cpIsNull();" Maxlength="6"><i><a href="javascript:void(0);" onclick="sendEmailcode();" id="cpc">发送邮箱获取验证码</a></i><span><@s.fielderror><@s.param>msgcheck.cp_phone</@s.param></@s.fielderror></span><span id="cpError" /></td>
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
