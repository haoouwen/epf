<html>
  <head>
    <title>添加网站留言</title>
  </head>
  <body>
<@s.form action="/admin_Message_insert.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
当前位置:网站管理 > 基本功能 > 网站留言管理 > 添加网站留言
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		  <tr>
             <td class="table_name">留言名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="message.title" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>message.title</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">留言内容<font color='red'>*</font></td>
             <td>
             	<@s.textarea name="message.content" id="message" value="" cssClass="txtinput" />
             	<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
				<script type="text/javascript">
			     CKEDITOR.replace('message');  
				</script>
             	<@s.fielderror><@s.param>message.content</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name">联系人:</td>
             <td>
             	<@s.textfield name="message.name" cssClass="txtinput" maxLength="10"/>
             	<@s.fielderror><@s.param>message.name</@s.param></@s.fielderror>
             </td>
           </tr>
           
          <tr>
             <td class="table_name">联系电话:</td>
             <td>
             	<@s.textfield name="message.phone" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>message.phone</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name">电子邮件:</td>
             <td>
             	<@s.textfield name="message.email" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>message.email</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">QQ:</td>
             <td>
             	<@s.textfield name="message.qq" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>message.qq</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name">MSN:</td>
             <td>
             	<@s.textfield name="message.msn" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>message.msn</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name">Skype:</td>
             <td>
             	<@s.textfield name="message.skype" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>message.skype</@s.param></@s.fielderror>
             </td>
           </tr>
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
   <@s.token/>
	   <@s.hidden name="message.mess_id"/>
	   ${listSearchHiddenField?if_exists}
	   <@s.submit value="保存"/>
	   <@s.reset value="重置"/>
	   <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Message_list.action','');" />
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>