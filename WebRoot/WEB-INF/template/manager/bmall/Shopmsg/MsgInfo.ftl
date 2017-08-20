<html>
  <head>
    <title>留言回复</title>
  </head>
 <body>

<@s.form action="/bmall_Shopmsg_mebInsert.action" method="post" validate="true" >

  <!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>留言信息</span></h2>
        <!----> 
        <div class="cancelDiv">
            <!----> 
             <div class="opeDiv padDiv">
                <!---->
                <table width="100%" cellpadding="0" cellspacing="0" class="detTab">
                    <tr><th width="20%">留言内容</th>
                    <td>
                     <div style="word-break:break-all; width:400px;" >
		               ${shopmsg.msg_content?if_exists}
		               </div>
                    </td>
                    </tr>  
                    <tr><th>留言时间</th><td>${shopmsg.in_date[0..18]}</td></tr>  
                    <tr><th>回复人</th><td> <#if  reply_name?if_exists!=null&&reply_name?if_exists!=''>
			             	${reply_name?if_exists}
			             	<#else>
			             	-
			             	</#if>
			         </td></tr>
                    <tr><th>回复内容</th>
                    	<td>
                        	 <div style="word-break:break-all; width:400px;" >
				         <#if shopreplymsg.reply_content?if_exists!=null&&shopreplymsg.reply_content?if_exists!=''>
				         ${shopreplymsg.reply_content?if_exists}
				         <#else>
				         -
				         </#if>
				         </div>
                        </td>
                    </tr> 
                    <tr><th></th>
                    	<td>
                        	<input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Shopmsg_buyMebList.action','');"/>
                        </td>
                    </tr>                     
                </table>
                             
            </div>
            
        </div>
   </div>
   
  </div>
  <div class="clear"></div>
	<@s.token/>    
   <@s.hidden name="shopmsg_id"/> 
   <@s.hidden name="shopreplymsg.trade_id"/> 
   ${listSearchHiddenField?if_exists}   
</@s.form>
</body>
</html>