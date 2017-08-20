<html>
  <head>
    <title>会员积分变动</title>
  </head>
  <body>
  <@s.form action="/admin_Memberfund_Izhifubao.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->当前位置:会员管理 > 财务管理 > 会员积分管理表 > 会员积分变动
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 <tr>
             <td class="table_name">会员名称：</td>
             <td>
             	 <@s.label name="memberfund.cust_id" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">充值资金<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="fundrecharge.fund_num" cssClass="txtinput" maxLength="6"/>
                <@s.fielderror><@s.param>fundrecharge.fund_num</@s.param></@s.fielderror>
             </td>
           </tr>
            <!-- <tr>
             <td class="table_name">支付平台<font color='red'>*</font></td>
             <td>
             	<@s.select name="fundrecharge.payplat" list=r"#{'支付宝':'支付宝','网银':'网银','PayPal':'PayPal'}" headerKey="0" headerValue="请选择"/>  
             	<@s.fielderror><@s.param>fundrecharge.payplat</@s.param></@s.fielderror>
             </td>
           </tr>-->
		</table>
		
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
    	   <@s.token/>
	       <@s.hidden name="key_name"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="下一步"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Memberinter_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
  
  </body>
</html>