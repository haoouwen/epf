<html>
  <head>
    <title>会员充值</title>
  </head>
  <body>
<@s.form action="/admin_Fundrecharge_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
   当前位置:会员管理 > 财务管理 > 会员资金充值记录表 > 会员充值
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
             <td class="table_name">会员名称：</td>
             <td>
             	 <@s.label name="fundrecharge.cust_id" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">充值余额<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="fundrecharge.fund_num" cssClass="txtinput" maxLength="20"/>
                <@s.fielderror><@s.param>fundrecharge.fund_num</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
            <td valign="middle" class="left_td">支付平台<span class="mustfield">*</span></td>
            <td>
             <@s.select name="fundrecharge.payplat" list="paymentList"  headerKey="0" listValue="pay_name" listKey="pay_code" headerValue="请选择"/>  
             <@s.fielderror><@s.param>fundrecharge.payplat</@s.param></@s.fielderror>
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
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Fundrecharge_list.action','');" />
   </div>
</div>
<div class="clear"></div>
</@s.form>
  </body>
</html>