<html>
  <head>
    <title>修改充值卡</title>
  </head>
<body >
<@s.form action="/admin_Rechargeablecards_update.action"  method="post" validate="true"  id="detailForm">
<div class="postion">
 当前位置：1 > 2 > 3
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
			
			   <tr>
	             <td class="table_name">cardid<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="rechargeablecards.cardid"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>rechargeablecards.cardid</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">cardskey<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="rechargeablecards.cardskey"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>rechargeablecards.cardskey</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">cardsmoney<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="rechargeablecards.cardsmoney"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>rechargeablecards.cardsmoney</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">cardsdate<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="rechargeablecards.cardsdate"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>rechargeablecards.cardsdate</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">cardsstate<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="rechargeablecards.cardsstate"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>rechargeablecards.cardsstate</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">cardsuseddate<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="rechargeablecards.cardsuseddate"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>rechargeablecards.cardsuseddate</@s.param></@s.fielderror>
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
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Rechargeablecards_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>   
</body>
</html>

