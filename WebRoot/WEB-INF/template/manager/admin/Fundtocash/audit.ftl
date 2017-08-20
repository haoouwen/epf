<html>
  <head>
    <title>提现申请审核</title>
  </head>
  <body>
  <@s.form action="/admin_Fundtocash_auditState.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:会员管理 > 财务管理 > 会员余额提现记录 > 提现申请审核
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		  <tr>
             <td class="table_name" style="width:220px;" height="60px;">会员名称:</td>
             <td>
             	 <@s.label name="scust_name" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">余额:</td>
             <td>
             	<@s.label name="fundtocash.fund_num" cssClass="txtinput"/>
             </td>
           </tr>
             <tr>
             <td class="table_name">收款方式:</td>
             <td>  
             	<@s.label name="sgetcash_type" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">收款账号:</td>
             <td>  
             	<@s.label name="fundtocash.account" cssClass="txtinput"/>
             </td>
           </tr>
            <tr>
             <td class="table_name">账号姓名:</td>
             <td>  
             	<@s.label name="fundtocash.account_name" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
               <td class="table_name">开户网点:</td><td>
                <@s.label name="fundtocash.branch" cssClass="txtinput"/>
               </td>
            </tr> 
             <tr>
             <td class="table_name">申请时间:</td>
             <td>
             	${fundtocash.in_date?if_exists[0..18]}
             </td>
           </tr>
             <tr>
             <td class="table_name">操作人:</td>
             <td>
             	<@s.label name="username" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">状态<font color='red'>*</font></td>
             <td>
             	<@s.radio name="fundtocash.order_state" list=r"#{'1':'审核通过','2':'审核未通过'}"/>
             </td>
           </tr>
               <tr>
             <td class="table_name">备注<font color='red'>*</font></td>
             <td>
             	<@s.textarea name="fundtocash.remark" cssClass="txtinput" maxLength="200" cssStyle="width:280px;height:80px;"
             	 onkeyup="checkLength(this,100);"/>
                <@s.fielderror><@s.param>fundtocash.remark</@s.param></@s.fielderror>
             </td>
           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
   			 <@s.token/>
	       
	       <@s.hidden name="sgetcash_type"/>
	       <@s.hidden name="fundtocash.account_name"/>
	       <@s.hidden name="fundtocash.account"/>
	       <@s.hidden name="fundtocash.fund_num"/>
	       <@s.hidden name="fundtocash.cust_id"/>
	       <@s.hidden name="fundtocash.trade_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Fundtocash_auditList.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
  </body>
</html>