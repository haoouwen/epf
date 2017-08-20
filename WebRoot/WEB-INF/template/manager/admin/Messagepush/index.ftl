<html>
  <head>
    <title>消息推送列表</title>
  </head>
  <body>
<@s.form action="/admin_Messagepush_list.action" method="post" id="indexForm">

        <div class="postion">当前位置：网站管理 > 消息推送 > 消息推送列表 </div>
        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
                  <td >标题:</td><td><@s.textfield name="title_s" cssClass="search_input" cssStyle="width:250px;"/></td>
                 <td><input type="submit" class="rbut" value="查询"></td>
               </tr>
             </table>
          
           </div>
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                 <th width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('messagepush.msgpush_id')"/></th>
                 
                    <th width="8%">消息类型</th>
                 
                 	<th width="32%">标题</th>
                 	
                 	<th width="9%">推送状态</th>
                 	
                 	<th width="9%">安卓状态</th>
                 	
                 	<th width="9%">IOS状态</th>
			    
                 	<th width="15%">时间</th>
			    
                   <th  width="9%">操作</th>
                   
               </tr>
			    <#list messagepushList as messagepush>
				 <tr >
			         <td><input type="checkbox" name="messagepush.msgpush_id" value="${messagepush.msgpush_id?if_exists}"/></td>
			         
				    <td align="center">
				    <#if messagepush.apns_type=="1">
					<img src="/include/admin/appimg/apns_type1.png" width="20px" height="20px" title="活动" />
					<#elseif messagepush.apns_type="2">
					<img src="/include/admin/appimg/apns_type2.png" width="20px" height="20px" title="降价提醒" />
					<#elseif messagepush.apns_type="3">
					<img src="/include/admin/appimg/apns_type3.png" width="20px" height="20px" title="通知消息" />
					<#elseif messagepush.apns_type="4">
					<img src="/include/admin/appimg/apns_type4.png" width="20px" height="20px" title="资讯"  />
					<#elseif messagepush.apns_type="5">
					<img src="/include/admin/appimg/apns_type5.png" width="20px" height="20px" title="新品" />
					<#elseif messagepush.apns_type="6">
					<img src="/include/admin/appimg/apns_type6.png" width="20px" height="20px" title="行业动态" />
					</#if>
				    </td>
			         	<td align="center">${messagepush.msgpush_name?if_exists}</td>
			         	
				         <td align="center">
				          <#if messagepush.info_state?if_exists=='0'>
					      <font color='red'>未推送</font>
					      <#else>
					      <font color='green'>已推送</font>
					      </#if> 
				         </td>
				    
				    	 <td align="center">
				          <#if messagepush.android_state?if_exists=='0'>
					       <img src="/include/admin/images/selectfalse.png" />
					      <#else>
					      <img src="/include/admin/images/selecttrue.png" />
					      </#if> 
				         </td>
				         
				          <td align="center">
				          <#if messagepush.ios_state?if_exists=='0'>
					       <img src="/include/admin/images/selectfalse.png" />
					      <#else>
					      <img src="/include/admin/images/selecttrue.png" />
					      </#if> 
				         </td>
				    
				    
			         	<td align="center">${messagepush.in_date?if_exists}</td>
				    
				    <td align="center">
				    
				       <#if messagepush.info_state?if_exists=='0'>
					       <a onclick="linkToInfo('/admin_Messagepush_view.action','messagepush.msgpush_id=${messagepush.msgpush_id?if_exists}');">
					 			 <img src="/include/common/images/bj.gif" /></a>
					      <#else>
					       <a onclick="linkToInfo('/admin_Messagepush_view.action','messagepush.msgpush_id=${messagepush.msgpush_id?if_exists}');">
					 			 <img src="/include/common/images/view.gif" /></a>
					      </#if> 
					  
					    <#if messagepush.info_state?if_exists=='0'>
					    <a onclick="linkToInfo('/admin_Messagepush_sendMsgView.action','messagepush.msgpush_id=${messagepush.msgpush_id?if_exists}');">
					  <img src="/include/common/images/lastu.png"   title="推送消息"  /></a>
					  </#if> 
					  </td>
				    
			       </tr>
			    </#list>
             </table>
           </div>
           <!--翻页-->
           <div class="pages">
             ${pageBar?if_exists}
           </div>
           <div class="clear"/>
           <!--翻页-->
           <div class="bsbut">
             <input type="button" class="rbut" onclick="linkToInfo('/admin_Messagepush_add.action','');" value="新增推送">
             <input type="button" class="rbut"onclick="delInfo('messagepush.msgpush_id','/admin_Messagepush_delete.action')" value="删除">
           </div>
        </div>
		<!--表单框隐藏域存放-->
		 <!-- <@s.hidden  name="xxx_s"/>-->
	   <!--表单框隐藏域存放--> 
</@s.form>
  </body>
</html>

