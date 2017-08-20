<html>
  <head>
    <title>提现申请审核</title>
  </head>
  <body>
  <@s.form action="/admin_Fundtocash_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
   当前位置:会员管理 > 财务管理 > 会员余额提现记录 > <#if fundtocash.order_state=='1'||fundtocash.order_state=='2'>提现记录信息<#else>提现申请审核</#if>
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
           <#if fundtocash.order_state=='1'||fundtocash.order_state=='2'>
            <tr>
             <td class="table_name">状态:</td>
             <td>
             	<#if fundtocash.order_state=='1'>
             	<font color="green">审核通过</font>
             	</#if>
             	<#if fundtocash.order_state=='2'>
             	<font color="blue">作废</font>
             	</#if>
             </td>
           </tr>
           <tr>
           		<td class="table_name">备注:</td>
           		<td colspan="3">${fundtocash.remark?if_exists}</td>
           </tr>
           <#else>
             <tr>
             <td class="table_name">审核状态<font color="red">*</font></td>
             <td>
             	<@s.radio name="fundtocash.order_state" list=r"#{'0':'未审核','1':'审核通过','2':'作废'}"/>
             </td>
           </tr>
              <tr>
             <td class="table_name">备注:</td>
             <td colspan="3">
             	<@s.textarea name="fundtocash.remark" id="remark" cssClass="txtinput" cssStyle="width:300px;height:200px;"  />
				<@s.fielderror><@s.param>fundtocash.remark</@s.param></@s.fielderror>
             </td>
           </tr>
           </#if>
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
	       
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Fundtocash_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
  
  </body>
</html>