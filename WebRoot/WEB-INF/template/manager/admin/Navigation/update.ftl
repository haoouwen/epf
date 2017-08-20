<html>
  <head>
    <title>修改导航列表信息</title>
  </head>
<body >
<@s.form action="/admin_Navigation_update.action"  method="post" validate="true"  id="detailForm">
<div class="postion">
 当前位置：1 > 2 > 3
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
			
			   <tr>
	             <td class="table_name">n_id<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="navigation.n_id"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>navigation.n_id</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">goods_id<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="navigation.goods_id"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>navigation.goods_id</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">tab_id<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="navigation.tab_id"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>navigation.tab_id</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">sort_no<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="navigation.sort_no"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>navigation.sort_no</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">in_date<font color='red'>*</font></td>
	             <td class="table_right">
	             	<@s.textfield name="navigation.in_date"  cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>navigation.in_date</@s.param></@s.fielderror>
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
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Navigation_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>   
</body>
</html>

