<html>
  <head>
    <title>会员充值审核</title>
  </head>
  <body>
  <@s.form action="/admin_Fundrecharge_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  当前位置:财务统计 >财务管理 > 会员余额充值记录表 > <#if fundrecharge.order_state=='1'>会员充值记录<#else>会员充值审核</#if>
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 <tr>
             <td class="table_name">订单号：</td>
             <td>
             ${fundrecharge.order_id?if_exists}
             </td>
           </tr>
           <tr>
             <td class="table_name">银行交易单号：</td>
             <td>
             ${fundrecharge.bank_order_id?if_exists}
             </td>
           </tr>
           <tr>
             <td class="table_name">会员名称:</td>
             <td>
             	 <@s.label name="cust_name" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">充值余额:</td>
             <td>
             ${fundrecharge.fund_num?if_exists}&nbsp;元
             </td>
           </tr>
             <tr>
             <td class="table_name">支付平台:</td>
             <td>  
             	${fundrecharge.payplat?if_exists}
             </td>
           </tr>
            <tr>
             <td class="table_name">支付时间:</td>
             <td>  
             	${fundrecharge.pay_date[0..18]}
             </td>
           </tr>
           <#if fundrecharge.order_state=='1'|| fundrecharge.order_state=='2'>
            <tr>
             <td class="table_name">状态:</td>
             <td>
             	<#if fundrecharge.order_state=='1'>
             	<font color="green">审核通过</font>
             	</#if>
             	<#if fundrecharge.order_state=='2'>
             	<font color="blue">作废</font>
             	</#if>
             </td>
           </tr>
           <tr>
           		<td class="table_name">备注:</td>
           		<td colspan="3">${fundrecharge.remark?if_exists}</td>
           </tr>
           <#else>
             <tr>
             <td class="table_name">审核状态<font color="red">*</font></td>
             <td>
             	<@s.radio name="fundrecharge.order_state" list=r"#{'1':'审核通过','2':'作废'}"/>
             	<@s.fielderror><@s.param>fundrecharge.order_state</@s.param></@s.fielderror>
             </td>
           </tr>
              <tr>
             <td class="table_name">备注:</td>
             <td colspan="3">
             	<@s.textarea name="fundrecharge.remark" id="remark" cssClass="txtinput" cssStyle="width:300px;height:200px;"  />
				<@s.fielderror><@s.param>fundrecharge.remark</@s.param></@s.fielderror>
             </td>
           </tr>
           </#if>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
    	    <@s.token/>
	       <@s.hidden name="fundrecharge.trade_id"/>
	       <@s.hidden name="fundrecharge.pay_date"/>
	       <@s.hidden name="fundrecharge.cust_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <#if fundrecharge.order_state=='1'||fundrecharge.order_state=='2'>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Fundrecharge_list.action','');"/>
	       <#else>
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Fundrecharge_auditlist.action','');"/>
	       </#if>
   </div>
</div>
<div class="clear"></div>
</@s.form>
  </body>
</html>