<html>
  <head>
    <title>草稿箱</title>
   <link href="/include/bmember/css/email.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
	function submitDraft(){
	    $("#is_draft").val("1");
	    $("#sendForm").submit();
	}
 </script>
  </head>
  <body>
   	<@s.form action="/bmall_Sendbox_updateDraft.action" method="post" validate="true" id="sendForm">
   	
   	<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>草稿箱-写信息</span></h2>
        <!---->
        <div class="email">
          <table width="100%" cellpadding="0" cellspacing="0" class="writeTab">
          	<tr>
            	<th><span>*</span>发送至：</th><td><span>${send_cust_name_member?if_exists}</span></td>
            </tr>
            <tr>
            	<th><span>*</span>主题：</th>
            	<td>
            	        <@s.textfield name="sendbox.title" cssClass="aisText" onkeyup="checkLength(this,30);" maxlength="30"/>
                 		<@s.fielderror><@s.param>sendbox.title</@s.param></@s.fielderror>
            	</td>
            </tr>
             <tr valign="top">
            	<th><span>*</span>内容：</th>
                <td class="cktd">
                	    
                	     <@s.textarea id="content" name="sendbox.content" cssStyle="width:400px;height:250px;"/>
                 		<@s.fielderror><@s.param>sendbox.content</@s.param></@s.fielderror>
                	    
                </td>
            </tr>
            <tr>
            	
                <td  colspan="2" align="center">
                	<input type="submit" class="submitbut" value="立即发送">&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" class="submitbut" value="保存草稿箱" onclick="submitDraft();">
                </td>
            </tr>
          </table>
        </div>
		
   </div>
   
  </div>
  <div class="clear"></div>
</div>
<@s.token/>
<@s.hidden name="sendbox.send_cust_id" />
<@s.hidden name="sendbox.send_id"/>
<@s.hidden name="sendbox.is_draft" value="0" id="is_draft"/>
<@s.hidden name="send_cust_name_member"/> 
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/> 
 </@s.form>
</body>
</html>