<html>
  <head>
    <title>店铺留言回复</title>
  </head>
  <body>
  
  <@s.form action="/admin_Shopreplymsg_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
   	当前位置:商城管理 > 店铺管理 > 留言回复
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <tr>
		             <td class="table_name">会员名称：<font color='red'>*</font></td>
		             <td>
		             	${cust_name?if_exists}
		             </td>
		           </tr>
		           
		           <tr>
		             <td class="table_name">店铺名称：<font color='red'>*</font></td>
		             <td>
		             	${shop_name?if_exists}
		             </td>
		           </tr>
		           
		           <tr>
		             <td class="table_name">留言内容：<font color='red'>*</font></td>
		             <td>
		             	${shopmsg.msg_content?if_exists}
		             </td>
		           </tr>
		           
		           <tr>
		             <td class="table_name">留言时间：<font color='red'>*</font></td>
		             <td>
		             	${shopmsg.in_date?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">回复内容：<font color='red'>*</font></td>
		             <td>
		             	<@s.textarea id="content" name="shopreplymsg.reply_content" cssClass="txtinput" cssStyle="width:300px;height:150px;" maxLength="300"/>
		             	<@s.fielderror><@s.param>shopreplymsg.reply_content</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
       <@s.token/>    
       <@s.hidden name="shopmsg_id"/> 
       ${listSearchHiddenField?if_exists}
       <@s.submit value="保存" />
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Shopreplymsg_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

