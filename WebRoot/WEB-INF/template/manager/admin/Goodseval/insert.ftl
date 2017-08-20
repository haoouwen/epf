<html>
  <head>
    <title>添加记录商品评价表信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Goodseval_insert.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">trade_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodseval.trade_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodseval.trade_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">goods_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodseval.goods_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodseval.goods_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">cust_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodseval.cust_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodseval.cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">user_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodseval.user_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodseval.user_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">g_eval<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodseval.g_eval" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodseval.g_eval</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">g_comment<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodseval.g_comment" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodseval.g_comment</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">is_two<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodseval.is_two" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodseval.is_two</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">eval_date<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodseval.eval_date" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodseval.eval_date</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">explan_cust_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodseval.explan_cust_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodseval.explan_cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">explan_man<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodseval.explan_man" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodseval.explan_man</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">explan_content<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodseval.explan_content" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodseval.explan_content</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">explan_date<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodseval.explan_date" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodseval.explan_date</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">is_enable<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodseval.is_enable" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodseval.is_enable</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Goodseval_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

