<html>
  <head>
    <title>发件箱</title>
    <link href="/include/bmember/css/email.css" rel="stylesheet" type="text/css">  
  </head>
  <body>
<@s.form action="/bmall_Sendbox_update.action" method="post" validate="true" >
<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>发件箱</span></h2>
        <!---->
        <div class="email">
          <table width="100%" cellpadding="0" cellspacing="0" class="inboxDTab">
             <tr valign="top">
              <th width="90%">
               <p><b>${sendbox.title?if_exists}</b></p>
               <p id="sendbox_cust_name">发件人：${cust_name?if_exists}</p>
               <p id="sendbox_in_date">时&nbsp;&nbsp;间：${sendbox.in_date?if_exists[0..18]}</p>
               <p id="sendbox_recevie_name">收件人：${sendbox.recevie_name?if_exists}</p>
               </th>
               <th width="10%">
                    <#if logo_path?if_exists!=""> 
               		<#list logo_path?if_exists?split(',') as img>
		 	        <img src="${img?if_exists}" width="80"  height="80"/>
		 	        </#list>
		 	        <#else>
                    <img src="/include/bmember/images/defalutHead.gif"  width="80"  height="80" />
                    </#if>
               </th>
             </tr>
             <tr>
               <td colspan="2">
                ${sendbox.content?if_exists}
               </td>
             </tr>
             <tr>
               <td colspan="2" class="butTd"><input type="button" class="submitbut" value="<< 返回" onclick="linkToInfo('/bmall_Sendbox_list.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');"></td>
             </tr>
          </table>

        </div>
		
   </div>
   
  </div>
  <div class="clear"></div>
</div>
<@s.hidden name="sendbox.send_cust_id"/>
<@s.hidden name="sendbox.is_send_del" value="1"/>	
 <@s.token/>  
 </@s.form>
</div>
<!--cont结束-->
</body>
</html>

