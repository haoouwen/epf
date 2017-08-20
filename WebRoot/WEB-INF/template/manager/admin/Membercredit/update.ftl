<html>
  <head>
    <title>修改会员信誉</title>
  </head>
<body >
<@s.form action="/admin_Membercredit_update.action"  method="post" validate="true"  id="detailForm">
<div class="postion">
 当前位置：1 > 2 > 3
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
			
			   <tr>
	             <td class="table_name">credit_id<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="membercredit.credit_id"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>membercredit.credit_id</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">cust_id<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="membercredit.cust_id"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>membercredit.cust_id</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">user_id<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="membercredit.user_id"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>membercredit.user_id</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">buy_num<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="membercredit.buy_num"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>membercredit.buy_num</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">sell_num<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="membercredit.sell_num"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>membercredit.sell_num</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
	           
       </table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
 		   <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
		   ${listSearchHiddenField?if_exists}
           <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Membercredit_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>   
</body>
</html>

