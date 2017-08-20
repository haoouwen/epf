<html>
  <head>
    <title>添加记录商品咨询信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 商品管理 > 商品咨询
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Goodsask_insert.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
	           
		           <tr>
		             <td class="table_name">咨询商品<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsask.goods_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsask.goods_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">咨询类型<font color='red'>*</font></td>
		             <td>
		             	<@s.select name="goodsask.c_type" list="commparaList" listValue="para_key" listKey="para_value"/>
		             	<@s.fielderror><@s.param>goodsask.c_type</@s.param></@s.fielderror>
		             </td>
		           </tr>
	          
	           
		           <tr>
		             <td class="table_name">咨询内容<font color='red'>*</font></td>
		             <td>
		             	<@s.textarea name="goodsask.c_content" cssStyle="width:400px;height:100px" cssClass="txtinput" maxLength="20" onkeyup="checkLength(this,600);"/>
		             	<@s.fielderror><@s.param>goodsask.c_content</@s.param></@s.fielderror>
		             </td>
		           </tr>
	                
         </table>
	     <div class="buttom">
	       <@s.token/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Goodsask_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>


