<html>
  <head>
    <title>发送电子邮件</title>
    <script type="text/javascript" src="/include/common/js/common.js"></script>
  </head>
  <body>
  <@s.form action="/admin_Emailhistory_sendEmail.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
   当前位置:会员管理 > 会员相关 > 发送电子邮件
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		           <tr>
		             <td class="table_name">收件人邮箱<font color='red'>*</font></td>
		             <td>
		             	<@s.textarea name="emailhistory.get_email" cssClass="txtinput" maxLength="200"style="width: 500px; height: 100px;" />
		             	 <img class="ltip" src="/include/common/images/light.gif" alt="如果收件人有多个，请用','隔开!">
		             	<@s.fielderror><@s.param>emailhistory.get_email</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">邮件标题<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="emailhistory.title" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>emailhistory.title</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">发件人邮箱<font color='red'>*</font></td>
		             <td>
		                 ${var_value?if_exists}
		                 <@s.hidden name="emailhistory.send_email" value="${var_value?if_exists} "cssClass="txtinput" maxLength="20"/>
		             </td>
		           </tr>
	           	         <@s.hidden name="emailhistory.send_name" cssClass="txtinput" maxLength="20"/>
	           
		           <tr>
		             <td class="table_name">邮件正文<font color='red'>*</font></td>
		             <td>
		             	<@s.textarea name="emailhistory.content" cssClass="txtinput" style="width: 500px; height: 200px;" onkeyup="checkLength(this,10000)"/>
		             	<@s.fielderror><@s.param>emailhistory.content</@s.param></@s.fielderror>
		             </td>
		           </tr>
	               <@s.hidden name="emailhistory.temp_code"/>
	           
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
	       ${listSearchHiddenField?if_exists}
           <@s.token/> 
           <@s.submit value="发送" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Emailhistory_list.action','');"/>    
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

