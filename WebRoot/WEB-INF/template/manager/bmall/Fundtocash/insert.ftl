<html>
<head>
<title>余额提现申请</title>
<link rel="StyleSheet" href="/include/bmember/css/account.css" type="text/css" />
<script type="text/javascript" src="/include/common/js/common.js"></script>
<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
</head>
<body>
	
<@s.form action="/bmall_Fundtocash_insert.action"  method="post"  name="formshopcongif" validate="true">
<div class="wR810">
    <div class="rwTitle">
     	<h2><span>余额提现申请</span></h2>
      <div class="accontDiv">
          <table width="100%" cellpadding="0" cellspacing="0" class="ainfoTab">
          
            <tr><th>当前余额<font color="red">*</font></th><td>
        	 ${memberfund.use_num?if_exists}
            </td></tr>
          
            <tr><th >提现余额<font color="red">*</font></th><td>
        	<@s.textfield name="fundtocash.fund_num"  maxLength="12" onkeyup="checkRMB(this)"/>
            <@s.fielderror><@s.param>fundtocash.fund_num</@s.param></@s.fielderror>
            </td></tr>
              
            <tr><th>收款方式<font color="red">*</font></th>
        	 <td>
             <@s.select name="fundtocash.getcash_type" list="commparaList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择"/>
             <@s.fielderror><@s.param>fundtocash.getcash_type</@s.param></@s.fielderror>          	           	            
             </td>
            </tr>  
            
            <tr><th  >收款账号<font color="red">*</font></th><td>
        	<@s.textfield name="fundtocash.account" cssStyle="width:200px;" maxLength="20"/>
            <@s.fielderror><@s.param>fundtocash.account</@s.param></@s.fielderror>
            <img class="ltip" src="/include/common/images/light.gif" alt="现金支付除外，必填"/>
            </td></tr>  
            
            
            <tr><th>账号姓名<font color="red">*</font></th><td>
        	<@s.textfield name="fundtocash.account_name" cssStyle="width:200px;" maxLength="10"/>
            <@s.fielderror><@s.param>fundtocash.account_name</@s.param></@s.fielderror>
            <img class="ltip" src="/include/common/images/light.gif" alt="现金支付除外，必填"/>
            </td></tr>
            
            <tr><th>开户网点<font color="red">*</font></th><td>
        	<@s.textfield name="fundtocash.branch" cssStyle="width:250px;"  maxLength="100"/>
            <@s.fielderror><@s.param>fundtocash.branch</@s.param></@s.fielderror>
            </td></tr> 

            <tr>
             <td colspan="2" align="center">
                <@s.token/>
             	<@s.submit value="提  交" cssClass="submitbut"/>
		     	<input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Fundtocash_list.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');"/>
             </td>
            </tr> 
            
          </table>
       </div>  
     </div> 
</div>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>  
 </@s.form>
</body>
</html>

