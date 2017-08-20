<html>
  <head>
    <title>维护消息推送</title>
    <script charset="utf-8" src="/kindeditor/kindeditor.js"></script>
  </head>
<body >
<@s.form action="/admin_Messagepush_update.action"  method="post" validate="true"  id="detailForm">
<div class="postion">
 当前位置：网站管理 > 消息推送 > 消息推送列表 > 维护消息推送
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
			
			  <#if messagepush.info_state?if_exists=='0'>
			  
			  		<tr>
	           <td class="table_name" width="10%">标题<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="messagepush.msgpush_name" cssClass="txtinput" maxLength="200" cssStyle="width:400px;"  />
	             	<@s.fielderror><@s.param>messagepush.msgpush_name</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	        
	          <tr>
	           <td class="table_name">摘要<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textarea name="messagepush.mp_abstract" cssClass="txtinput" maxLength="500" cssStyle="width:450px;height:100px;" />
	             	<@s.fielderror><@s.param>messagepush.mp_abstract</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	        
	        
	          <tr>
	           <td class="table_name"  width="10%">消息类型<font color='red'>*</font></td>
	           <td class="table_right">
	             	<input type="radio" value="1" name="messagepush.apns_type" <#if messagepush.apns_type=="1">checked</#if> />
					<img src="/include/admin/appimg/apns_type1.png" width="30px" height="30px" title="活动" />[活动]
					<input type="radio" value="2" name="messagepush.apns_type" <#if messagepush.apns_type=="2">checked</#if> />
					<img src="/include/admin/appimg/apns_type2.png" width="30px" height="30px" title="降价提醒" />[降价提醒]
					<input type="radio" value="3" name="messagepush.apns_type" <#if messagepush.apns_type=="3">checked</#if> />
					<img src="/include/admin/appimg/apns_type3.png" width="30px" height="30px" title="通知消息" />[通知消息]
					<input type="radio" value="4" name="messagepush.apns_type" <#if messagepush.apns_type=="4">checked</#if> />
					<img src="/include/admin/appimg/apns_type4.png" width="30px" height="30px" title="资讯"  />[资讯]
					<input type="radio" value="5" name="messagepush.apns_type" <#if messagepush.apns_type=="5">checked</#if> />
					<img src="/include/admin/appimg/apns_type5.png" width="30px" height="30px" title="新品" />[新品]
					<input type="radio" value="6" name="messagepush.apns_type" <#if messagepush.apns_type=="6">checked</#if> />
					<img src="/include/admin/appimg/apns_type6.png" width="30px" height="30px" title="行业动态" />[行业动态]
	             	
	             	
	           </td>
	        </tr>	
	           <tr>
		             <td class="table_name">内容<font color='red'>*</font></td>
			         <td>
			             <@s.textarea id="content" name="messagepush.content" cssClass="txtinput" cssStyle="width:60%;height:300px;" onkeyup="checkLength(this,25000);" maxlength="25000"/>
			             <script>
							KindEditor.ready(function(K) {
								var editor1 = K.create('textarea[name="messagepush.content"]', {
									uploadJson : '/kindeditor/jsp/upload_json.jsp',
									fileManagerJson : '/kindeditor/jsp/file_manager_json.jsp',
									allowFileManager : true,
									afterCreate : function() {
									    var self = this;
										K.ctrl(document, 13, function() {
											self.sync();
										});
										K.ctrl(self.edit.doc, 13, function() {
											self.sync();
										});
									},
									afterBlur: function(){this.sync();}
								});
							});
						</script>
		                 <@s.fielderror><@s.param>messagepush.content</@s.param></@s.fielderror>
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
			  
			  
			  
		      <#else>
		      
		      
		      
		      
		      
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
					      <font color='green'>已推送</font>
	           </td>
	        </tr>	
	        
	        
		      </#if> 
	           
       </table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
            <@s.token/>    
 		   <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
 		   <@s.hidden name="messagepush.msgpush_id" value="${messagepush.msgpush_id?if_exists}"/>
		   ${listSearchHiddenField?if_exists}
		   
		   
		     <#if messagepush.info_state?if_exists=='0'>
					      <@s.submit value="保存"/>
			</#if> 
		   
          
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Messagepush_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>   
</body>
</html>

