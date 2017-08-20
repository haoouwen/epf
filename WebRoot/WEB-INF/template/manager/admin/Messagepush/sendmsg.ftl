<html>
  <head>
    <title>消息推送</title>
  </head>
<body >
<@s.form action="/admin_Messagepush_sendupdate.action"  method="post" validate="true"  id="detailForm">
<div class="postion">
 当前位置：网站管理 > 消息推送 > 消息推送列表 > 消息推送
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
			
				 <tr>
	           <td class="table_name" width="20%">标题</td>
	           <td class="table_right">
	              ${(messagepush.msgpush_name)?if_exists}
	           </td>
	        </tr>	
	        
	        
	        
	          <tr>
	           <td class="table_name">摘要</td>
	           <td class="table_right">
	            ${(messagepush.mp_abstract)?if_exists}
	           </td>
	        </tr>	
	        
	        
	      <tr>
	           <td class="table_name"  width="10%">消息类型</td>
	           <td class="table_right">
	             <#if messagepush.apns_type=="1">
					<img src="/include/admin/appimg/apns_type1.png" width="30px" height="30px" title="活动" /><br/>【活动】
					<#elseif messagepush.apns_type="2">
					<img src="/include/admin/appimg/apns_type2.png" width="30px" height="30px" title="降价提醒" /><br/>【降价提醒】
					<#elseif messagepush.apns_type="3">
					<img src="/include/admin/appimg/apns_type3.png" width="30px" height="30px" title="通知消息" /><br/>【通知消息】
					<#elseif messagepush.apns_type="4">
					<img src="/include/admin/appimg/apns_type4.png" width="30px" height="30px" title="资讯"  /><br/>【资讯】
					<#elseif messagepush.apns_type="5">
					<img src="/include/admin/appimg/apns_type5.png" width="30px" height="30px" title="新品" /><br/>【新品】
					<#elseif messagepush.apns_type="6">
					<img src="/include/admin/appimg/apns_type6.png" width="30px" height="30px" title="行业动态" /><br/>【行业动态】
					</#if>
	             	
	             	
	           </td>
	        </tr>	
	    
	           <tr>
		             <td class="table_name">内容</td>
			         <td>
			             ${(messagepush.content)?if_exists}
			         </td>
		           </tr>
	        	 <tr>
	           <td class="table_name">状态</td>
	           <td class="table_right">
	             	 <#if messagepush.info_state?if_exists=='0'>
					      <font color='red'>未推送</font>
					      <#else>
					      <font color='green'>已推送</font>
					      </#if> 
	           </td>
	        </tr>	
	           
       </table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
            <@s.token/>    
 		   <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
 		   <@s.hidden name="messagepush.msgpush_id" value="${messagepush.msgpush_id?if_exists}"/>
		   ${listSearchHiddenField?if_exists}
           <@s.submit value="推送消息"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Messagepush_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>   
</body>
</html>

