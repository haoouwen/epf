 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>手机验证-${cfg_mallwebname?if_exists}</title>
<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
 <link rel="StyleSheet" href="/include/bmember/css/account.css" type="text/css" />
<script type="text/javascript" src="/include/bmember/js/checkmobileandemail.js"></script>
<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
<script type="text/javascript" src="/malltemplate/bmall/js/popupLayer.js"></script> 
</head>
<body>

<@s.form action="/bmall_Msgcheck_checkPhoneCodebyform.action" method="post">
 <!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>手机验证</span></h2>
        <!---->
        <div class="verifyDiv">
          <!--步骤-->
          <h3><span class="morange">1.输入手机号码</span><span>2.手机验证成功</span></h3>
          <div class="verifyCont">
             <table width="100%" cellpadding="0" cellspacing="0">
                 <tr>
                    <th><span>*</span>手机号码：</th><td><input type="text" class="vText" id="phone" name="msgcheck.cp_phone" maxlength="15" onblur="CellphoneIsNull();" onfocus="CellphoneForm();">
                   <span id="phoneError" /></td>
                 </tr>
                   <tr>
                       <th>
                             <span>*</span>验证码：
                               </th>
                           <td>
                            	<@s.textfield name="commentrand" maxLength="4" cssClass="vText" id="commentrand"/>
				                <img src="/imgrand.action" class=""  onclick="changeValidateCode(this)"/>
				                <span id="randError"/></span>
                        </td>
                    </tr>
                 <tr>
                 <tr>
                    <th><span>*</span>短信验证码：</th><td><input type="text" class="vText" maxlength="6" id="cp_check" name="msgcheck.cp_check"  onblur="cpIsNull();">
                     <i><a href="#" id="cpc"  onclick="sendcode();">获取验证码</a></i>
                    <span id="cpError" /></td>
                 </tr>
                    <th></th><td><input type="buttom" onclick="checkMsgForm();" class="graybut" value="提交"></td>
                 </tr>
             </table>
          </div>
       </div>

   </div>
   
  </div>
  <div class="clear"></div>
</div>
 <@s.hidden id="cp_type"  value="3"/>
 <input type="hidden" id="timeMinus" value="0"/> 
 <@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>  
</@s.form> 
</body>
</html>
