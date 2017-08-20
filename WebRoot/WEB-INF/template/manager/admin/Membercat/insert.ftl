<html>
  <head>
    <title>添加记录会员自定义分类信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Membercat_insert.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">cat_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="membercat.cat_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>membercat.cat_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">cust_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="membercat.cust_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>membercat.cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">cat_name<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="membercat.cat_name" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>membercat.cat_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">cat_type<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="membercat.cat_type" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>membercat.cat_type</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">sort_no<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="membercat.sort_no" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>membercat.sort_no</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">parent_cat_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="membercat.parent_cat_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>membercat.parent_cat_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">in_date<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="membercat.in_date" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>membercat.in_date</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Membercat_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

