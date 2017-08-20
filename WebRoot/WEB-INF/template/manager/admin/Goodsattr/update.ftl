<html>
  <head>
    <title>修改记录商品属性信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Goodsattr_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">attr_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsattr.attr_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsattr.attr_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">goods_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsattr.goods_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsattr.goods_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">specstr<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsattr.specstr" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsattr.specstr</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">market_price<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsattr.market_price" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsattr.market_price</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">sale_price<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsattr.sale_price" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsattr.sale_price</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">cost_price<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsattr.cost_price" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsattr.cost_price</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">stock<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsattr.stock" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsattr.stock</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">sale_num<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsattr.sale_num" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsattr.sale_num</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Goodsattr_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

