<head>
<title>留言列表</title>
<link href="/include/bmember/css/email.css" rel="stylesheet" type="text/css">
</head>
<body>
<@s.form action="/bmall_Shopmsg_buyMebList.action"  method="post" id="indexForm">
  <!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>留言列表</span></h2>
        <!---->
        <div class="email">
          <table width="100%" cellpadding="0" cellspacing="0" class="inboxTab">
             <tr>
                <th width="70%">留言内容</th>
                <th width="20%" >留言时间</th>
                <th width="10%" >操作</th>
             </tr>  
             <#list shopmsgList as shopmsg>
             <tr>
                <td>
                <#if shopmsg.msg_content?length < 60>
		    	<a onclick="linkToInfo('/bmall_Shopmsg_seachMsg.action','shopmsg_id=${shopmsg.trade_id?if_exists}');" title="${shopmsg.msg_content?if_exists}">${shopmsg.msg_content?if_exists}</a>
		    	<#else>
		    	<a onclick="linkToInfo('/bmall_Shopmsg_seachMsg.action','shopmsg_id=${shopmsg.trade_id?if_exists}');" title="${shopmsg.msg_content?if_exists}">${shopmsg.msg_content[0..59]}...</a>
		    	</#if></td>
                <td>${shopmsg.in_date?if_exists}</td> 
                <td><a onclick="linkToInfo('/bmall_Shopmsg_seachMsg.action','shopmsg_id=${shopmsg.trade_id?if_exists}');"><img src="/include/common/images/audit.png"></a></td>
             </tr>
            </#list>
            <#if  shopmsgList.size()==0>
             <tr>
               	<td colspan="3">暂无数据</td>
             </tr>  
            </#if>
          </table>
          <!---->
        </div>
        <!--翻页控件-->
        <div class="listbottom">${pageBar?if_exists}</div>
		
   </div>
   
  </div>
  <div class="clear"></div>
   <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
   <@s.hidden id="search_area_attr" name="area_attr_s"/>
</div>
</@s.form>
</body>
</html>
