<html>
  <head>
    <title>会员余额提现申请</title>
    <script type="text/javascript" src="/include/common/js/common.js"></script>
  </head>
  <body>
  <@s.form action="/admin_Fundtocash_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:会员管理 > 财务管理 > 会员余额提现记录 > 会员余额提现申请
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 <tr>
             <td class="table_name" style="width:220px;" height="60px;">申请账号<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="cust_name" cssClass="txtinput" maxLength="20"/>
                <@s.fielderror><@s.param>cust_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">余额<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="fundtocash.fund_num" cssClass="txtinput" maxLength="11" onkeyup="checkRMB(this)"/>
                <@s.fielderror><@s.param>fundtocash.fund_num</@s.param></@s.fielderror>
             </td>
           </tr>
                <tr>
             <td class="table_name">收款方式<font color='red'>*</font></td>
             <td>
             <@s.select name="fundtocash.getcash_type" list="commparaList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择"/>
             <@s.fielderror><@s.param>fundtocash.getcash_type</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">收款账号<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="fundtocash.account" cssClass="txtinput" maxLength="20"/>
                <@s.fielderror><@s.param>fundtocash.account</@s.param></@s.fielderror>
                <img class='ltip' src="/include/common/images/light.gif"  alt="<@s.property value="%{getText('manager.admin.Memberchconfig')}"/>"> 
             </td>
           </tr>
            <tr>
             <td class="table_name">账号姓名<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="fundtocash.account_name" cssClass="txtinput" maxLength="20"/>
                <@s.fielderror><@s.param>fundtocash.account_name</@s.param></@s.fielderror>
                 <img class='ltip' src="/include/common/images/light.gif"  alt="<@s.property value="%{getText('manager.admin.Memberchconfig')}"/>"> 
             </td>
           </tr>
           <tr>
               <td class="table_name">开户网点：</td><td>
		    	<@s.textfield name="fundtocash.branch"  maxLength="100"/>
		        <@s.fielderror><@s.param>fundtocash.branch</@s.param></@s.fielderror>
               </td>
            </tr> 
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
  		  <@s.token/>
	       <@s.hidden name="key_name"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Fundtocash_list.action','');" />
   </div>
</div>
<div class="clear"></div>
</@s.form>
  </body>
</html>