<html>
  <head>
    <title>发送手机短信</title>
     <script type="text/javascript" src="/include/common/js/common.js"></script>
  </head>
  <body>
  
  
  <@s.form action="/admin_Smshistory_sendMessage.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  当前位置:会员管理 > 会员相关 > 发送手机短信
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		   <tr>
		             <td class="table_name">手机号码<font color='red'>*</font></td>
		             <td>
		             	<@s.textarea name="smshistory.cellphones" cssClass="txtinput" maxLength="200"style="width: 500px; height: 100px;"/>
		             	<img class="ltip" src="/include/common/images/light.gif" alt="如果手机号码有多个，请用','隔开!">
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
		               ${send_cellnum?if_exists}
		                <@s.hidden name="smshistory.cellnum" value="${send_cellnum?if_exists}" cssClass="txtinput" maxLength="20" />
		             </td>
		           </tr>
		           
		           <@s.hidden name="smshistory.cellname" cssClass="txtinput" maxLength="20"/>
		           
		           <tr>
		             <td class="table_name">短信正文<font color='red'>*</font></td>
		             <td>
		             	<@s.textarea name="smshistory.content" cssClass="txtinput" style="width: 500px; height: 200px;" onkeyup="checkLength(this,10000)"/>
		             	<@s.fielderror><@s.param>smshistory.content</@s.param></@s.fielderror>
		             </td>
		           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
       <@s.hidden name="smshistory.cell_code"/> 
       ${listSearchHiddenField?if_exists}
       <@s.token/> 	
       <@s.submit value="发送" />
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Smshistory_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

