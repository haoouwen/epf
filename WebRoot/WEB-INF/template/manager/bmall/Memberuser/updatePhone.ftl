<html>
<head>
<title>手机修改</title>
 <link rel="StyleSheet" href="/include/bmember/css/account.css" type="text/css" />
<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/include/bmember/js/checkmobileandemail.js"></script>

</head>
<body>
	
<@s.form action="/bmall_Memberuser_updateP.action"  method="post" name="formshopcongif" validate="true">
<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>手机修改</span></h2>
        <!---->
        <div class="verifyDiv">
          <!--步骤-->
           <h3><span class="morange">1.输入手机号码</span><span>2.手机号码修改成功</span></h3>
          <div class="verifyCont">
             <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <th>旧的手机号码：</th><td>${memberuser.cellphone?if_exists}</td>
                 </tr>
                 <tr>
                    <th><span>*</span>新的手机号码：</th><td><input type="text" class="vText" name="newphone" id="phone"  maxlength="15" onblur="CellphoneIsNull();" onfocus="CellphoneForm();"><span><@s.fielderror><@s.param>updateEmail</@s.param></@s.fielderror></span><span id="phoneError" /></td>
                 </tr>
                 
                 <tr>
                    <th><span>*</span>图形验证码：</th><td>
                      
                        <@s.textfield name="commentrand" maxLength="4" cssClass="vText" id="commentrand"/>
				                <img src="/imgrand.action" class=""  onclick="changeValidateCode(this)"/>
				                <span id="randError"/></span>
                    
                    </td>
                 </tr>
                 
                 <tr>
                    <th><span>*</span>手机验证码：</th><td><input type="text" class="vText" id="cp_check" name="cp_check" maxlength="6" onblur="cpIsNull();"><@s.fielderror><@s.param>cp_phone</@s.param></@s.fielderror></span><i><a href="#" id="cpc"  onclick="sendcode();">发送验证码</a></i><span id="cpError" /></td>
                 </tr>
                 <tr>
                    <th></th><td><input type="buttom" onclick="checkMsgForm();" class="graybut" value="提交"></td>
                 </tr>
             </table>
          </div>
       </div>
        
     </div>
   
  </div>
  <div class="clear"></div>
</div>
<@s.hidden   value="3" id="cp_type"/>
<input type="hidden" id="timeMinus" value="0"/>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>  
 </@s.form>
</body>
</html>

