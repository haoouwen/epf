<html>
  <head>
    <title>重置密码</title>
  </head>
  <body>
<@s.form action="/admin_ResetPwd_resetpwd.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
当前位置:会员管理 > 会员管理 > 企业会员列表 > 重置密码
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		  <tr>
            <td class="table_name">企业名称:</td>
             <td colspan="3">
                ${member.cust_name?if_exists}
             </td>
           </tr>
           <tr>
             <td class="table_name">用&nbsp;户&nbsp;名:</td>
             <td colspan="3">
             ${memberuser.user_name?if_exists}
             </td>
           </tr>
           <tr>
             <td class="table_name">店铺名称:</td>
             <td colspan="3">
             ${shopconfig.shop_name?if_exists}
             </td>
           </tr>
	           <tr>
             <td class="table_name">重置密码<font color='red'> *</font></td>
             <td colspan="3">
             	<@s.password  name="reset_passwd" cssClass="txtinput" cssStyle="width:200px;" maxLength="32"/>
             	<@s.fielderror><@s.param>reset_passwd</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">确认重置密码<font color='red'> *</font></td>
             <td colspan="3">
             	<@s.password name="con_repasswd" cssClass="txtinput" cssStyle="width:200px;" maxLength="100"/>
             	<@s.fielderror><@s.param>con_repasswd</@s.param></@s.fielderror>
             </td>
           </tr>
		 
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
	   <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	   <@s.hidden name="member.cust_id" value="${member.cust_id?if_exists}" /> 
	   <@s.hidden name="memberuser.user_id" value="${memberuser.user_id?if_exists}" />
	   <@s.hidden name="shopconfig.shop_id" value="${shopconfig.shop_id?if_exists}" />
	   ${listSearchHiddenField?if_exists}
	   <@s.submit value="重置密码" />
	   <@s.reset value="重置"/>
	   <input type="button" name="returnList" value="返回列表" onClick="linkToInfo('/admin_Corpomember_list.action','');"/>
   	   <@s.token/>
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

