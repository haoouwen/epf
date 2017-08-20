<html>
  <head>
  
    <title>发件箱</title>
    <link href="/include/bmember/css/email.css" rel="stylesheet" type="text/css">  
    
  </head>
  <body>
<@s.form action="/admin_Sendbox_update.action" method="post" validate="true" >
<div class="postion">
当前位置:会员管理 > 通讯管理 > 发件箱详情</div>
  <div class="rtdcont">
   <!--最新订单-->
   <div class="rdtable">
        <div class="email">
          <table width="100%" cellpadding="0" cellspacing="0" class="inboxDTab">
             <tr valign="top">
              <th width="90%">
               <p> 标题:<b>${sendbox.title?if_exists}</b></p>
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
             <tr valign="top">
               
               <th colspan="2">
                  <span>内容:</span>
                ${(sendbox.content)?if_exists}
               </th>
             </tr>
             <tr>
               <td colspan="2" class="butTd" align="center"><input type="button" class="rbut"  value="<< 返回" onclick="linkToInfo('/admin_Sendbox_list.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');"></td>
             </tr>
          </table>

        </div>
   </div>
  <div class="clear"></div>
<@s.hidden name="sendbox.send_cust_id"/>
<@s.hidden name="sendbox.is_send_del" value="1"/>	
 <@s.token/>  
 </@s.form>
</div>
<!--cont结束-->
</body>
</html>

