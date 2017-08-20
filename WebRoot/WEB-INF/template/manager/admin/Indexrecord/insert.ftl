<html>
  <head>
    <title>添加记录更新的索引记录</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Indexrecord_insert.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">trade_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="indexrecord.trade_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>indexrecord.trade_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">module_name<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="indexrecord.module_name" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>indexrecord.module_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">info_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="indexrecord.info_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>indexrecord.info_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">oper_type<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="indexrecord.oper_type" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>indexrecord.oper_type</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="fixbuttom">
	       <@s.token/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Indexrecord_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

