<html>
  <head>
    <title>会员充值</title>
  </head>
  <body>
  <@s.form action="/admin_Memberfund_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->  当前位置:<#if enter?if_exists==''>财务统计 >财务管理 ><#elseif enter?if_exists=='0'> 会员管理 ><#elseif enter?if_exists=='1'> 企业会员 ></#if> 余额管理 > 会员充值
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 <tr>
             <td class="table_name">会员名称:</td>
             <td>
             	 <@s.label name="member.cust_name" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">充值余额<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="fund_num" cssClass="txtinput" maxLength="8"onkeyup="checkRMB(this)"/>
                <@s.fielderror><@s.param>fundrecharge.fund_num</@s.param></@s.fielderror>
             </td>
           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
 		    <@s.token/>
	       <@s.hidden name="memberfund.cust_id"/>
	       <@s.hidden name="key_name"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="提交"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Memberfund_list.action','enter=${enter?if_exists}&cust_id=${cust_id?if_exists}');" />
   </div>
</div>
<div class="clear"></div>
<@s.hidden name="cust_id"/>
<@s.hidden name="enter"/>
</@s.form>

  </body>
</html>