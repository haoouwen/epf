<html>
  <head>
    <title>发站内信</title>
	<script type="text/javascript" src="/include/common/js/common.js"></script> 
  </head>
  <body>
<@s.form action="/admin_Sendbox_insert.action" method="post" validate="true" >
<div class="postion">
当前位置:会员管理 > 通讯管理 > 站内信</div>
<div class="rtdcont">
	<div class="rdtable">
         <table  width="100%" cellspacing="0" cellpadding="0">
                  <tr>
		             <td class="table_name">发件人名称:</td>
		             <td>
		                 ${cust_name?if_exists}
		             </td>
		           </tr>
           		 <tr>
	                 <td class="table_name">收件人名称<font color='red'>*</font></td>
			             <td>
								<@s.textfield name="send_cust_name" cssStyle="width:300px" onkeyup="checkLength(this,200);"/>
								<img class="ltip" src="/include/common/images/light.gif" alt="提示：请输入你要发送的会员名称,如果需要发送到多个会员,请用逗号隔开!"> 
								<@s.fielderror><@s.param>send_cust_name</@s.param></@s.fielderror>
			             </td>
		         </tr> 
		           
		           <tr>
		             <td class="table_name">主题<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="sendbox.title" cssClass="txtinput" onkeyup="checkLength(this,30);" maxlength="30"/>
		             	<@s.fielderror><@s.param>sendbox.title</@s.param></@s.fielderror>
		             </td>
		           </tr>
                   <tr>
			             <td class="table_name">内容<font color='red'>*</font></td>
			             <td >
			                <@s.textarea  name="sendbox.content" cssStyle="width:660px;height:300px;"/>
			             </td>
			           </tr>
          </table>
       </div>  
       <div class="clear"/>
         <div class="bsbut_detail">
        <@s.hidden name="sendbox.send_id" />
				       <@s.token/>
				       ${listSearchHiddenField?if_exists}
			           <@s.submit value="发送" cssClass="submitbut"/>
				       <@s.reset value="重置" cssClass="submitbut"/>
				       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Sendbox_list.action','');" class="submitbut"/>

   </div>
</div>
<div class="clear"></div>
</@s.form>
<div class="clear"></div>
</body>
</html>
