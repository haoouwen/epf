<html>
  <head>
    <title>添加记录直通车资金信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Expressfund_insert.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">cust_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="expressfund.cust_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>expressfund.cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">summoney<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="expressfund.summoney" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>expressfund.summoney</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Expressfund_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

