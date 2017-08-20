<html>
  <head>
    <title>修改手机短信</title>
     <script type="text/javascript" src="/include/common/js/common.js"></script>
  </head>
  <body>
  <@s.form action="/admin_Smshistory_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
 当前位置:会员管理 > 会员相关 > 修改手机短信
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <tr>
			             <td class="table_name">手机号码<font color='red'>*</font></td>
			             <td>
			             	<@s.textfield name="smshistory.cellphones" cssClass="txtinput" maxLength="20"/>
			             	<@s.fielderror><@s.param>smshistory.cellphones</@s.param></@s.fielderror>
			             </td>
			           </tr>
		           
			           <tr>
			             <td class="table_name">短信标题<font color='red'>*</font></td>
			             <td>
			             	<@s.textfield name="smshistory.celltitle" cssClass="txtinput" maxLength="20"/>
			             	<@s.fielderror><@s.param>smshistory.celltitle</@s.param></@s.fielderror>
			             </td>
			           </tr>
		           
			           <tr>
			             <td class="table_name">发送人手机号码<font color='red'>*</font></td>
			             <td>
			                 ${smshistory.cellnum?if_exists}
			             	 <@s.hidden name="smshistory.cellnum" value="${send_cellnum?if_exists}" cssClass="txtinput" cssstyle="width:400px;height:150px" onkeyup="checkLength(this,10000)"/>
			             </td>
			           </tr>
			             	<@s.hidden name="smshistory.cellname" cssClass="txtinput" maxLength="20"/>
			           <tr>
			             <td class="table_name">短信正文<font color='red'>*</font></td>
			             <td>
			             	<@s.textarea name="smshistory.content" cssClass="txtinput" cssstyle="width:400px;height:150px" onkeyup="checkLength(this,10000)"/>
			             	<@s.fielderror><@s.param>smshistory.content</@s.param></@s.fielderror>
			             </td>
			           </tr>
		           
			           <tr>
			             <td class="table_name">手机模板代码:</td>
			             <td>
			             	<@s.textfield name="smshistory.cell_code" cssClass="txtinput" maxLength="20"/>
			             	<@s.fielderror><@s.param>smshistory.cell_code</@s.param></@s.fielderror>
			             </td>
			           </tr>
		           
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
      <@s.hidden name="smshistory.trade_id"/>
       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    	      			             	
       ${listSearchHiddenField?if_exists}
       <@s.token/> 
       <@s.submit value="保存" />
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Smshistory_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

