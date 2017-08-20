<html>
  <head>
    <title>修改商品自定义属性</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Selfextendattr_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">self_attr_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="selfextendattr.self_attr_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>selfextendattr.self_attr_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">ex_attr_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="selfextendattr.ex_attr_id" cssClass="txtinput" onkeyup="checkLength(this,10);" maxlength="10"/>
		             	<@s.fielderror><@s.param>selfextendattr.ex_attr_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">ex_attr_alias<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="selfextendattr.ex_attr_alias" cssClass="txtinput" onkeyup="checkLength(this,30);" maxlength="30"/>
		             	<@s.fielderror><@s.param>selfextendattr.ex_attr_alias</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">ex_attr_value<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="selfextendattr.ex_attr_value" cssClass="txtinput" onkeyup="checkLength(this,100);" maxlength="100"/>
		             	<@s.fielderror><@s.param>selfextendattr.ex_attr_value</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">goods_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="selfextendattr.goods_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>selfextendattr.goods_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.token/>  
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Selfextendattr_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

