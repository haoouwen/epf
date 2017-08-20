<html>
  <head>
    <title>修改团购阶梯价格</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Groupladder_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">trade_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupladder.trade_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>groupladder.trade_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">group_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupladder.group_id" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>groupladder.group_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">lownum<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupladder.lownum" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>groupladder.lownum</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">price<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupladder.price" cssClass="txtinput" onkeyup="checkLength(this,25000);" maxlength="25000"/>
		             	<@s.fielderror><@s.param>groupladder.price</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.token/>  
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Groupladder_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

