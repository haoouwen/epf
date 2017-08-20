<html>
<head>
<title>积分兑换余额</title>
<link rel="StyleSheet" href="/include/bmember/css/account.css" type="text/css" />
<script type="text/javascript" src="/include/common/js/common.js"></script>
</head>
<body>
	
<@s.form action="/bmall_Interhistory_update.action"  method="post"  name="formshopcongif" validate="true">
<div class="wR810">
    <div class="rwTitle">
     	<h2><span>积分兑换余额</span></h2>
      <div class="accontDiv">
          <table width="100%" cellpadding="0" cellspacing="0" class="ainfoTab">
            <tr><th >您的积分数:</th>
        	 <td>
        	 <#if  integer_num?if_exists!=null&&integer_num?if_exists!=''>
        	 <@s.label id="integer_num" name="integer_num"  cssClass="txtinput"/> 分 
        	 <#else>
        	 0分
        	 </#if>
                        	           	            
             </td>
            </tr>  

            <tr><th  >您目前可用余额:</th><td>
             <#if use_fund?if_exists!=null&&use_fund?if_exists!=''>
        	 <@s.label id="use_fund" name="use_fund"  cssClass="txtinput"/>余额
        	 <#else>
        	 0 余额
        	 </#if>
        	 
            </td></tr>
              

            
            <tr><th>兑换规则:</th><td>
            	&nbsp;<span class="mustfield">会员主动兑换：${gold_value?if_exists}个积分兑换一个余额。</span>
            </td></tr>  
            
             <tr><th>兑换的积分数<font color="red">*</font></th><td>
        	 <@s.textfield name="rech_fund"   maxlength="8" onkeyup="checkDigitalNoNone(this)"/>
             <@s.fielderror><@s.param>rech_fund</@s.param></@s.fielderror>
             </td></tr> 
            
             <tr><th>支付密码<font color="red">*</font></th><td>
        	 <input type="password" name="password" maxlength="8">
             <@s.fielderror><@s.param>password</@s.param></@s.fielderror>
            </td></tr> 
            
            <tr>
             <td colspan="2" align="center">
	         ${listSearchHiddenField?if_exists}
        	  <@s.token/>
	         <@s.submit value="兑换" cssClass="submitbut"/>
	         </td> 
            </tr> 
	
          </table>
       </div>  
     </div> 
</div>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>  
 </@s.form>
 </div>
<div class="clear"></div>
</body>
</html>

