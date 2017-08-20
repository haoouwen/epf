<html>
<head>
<title>收件箱</title>
<link href="/include/bmember/css/email.css" rel="stylesheet" type="text/css">
</head>
<body>
<@s.form action="/bmall_Receivebox_list.action" method="post" id="indexForm">

<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>收件箱(${totalRow})</span></h2>
        <!---->
        <div class="email">
          <table width="100%" cellpadding="0" cellspacing="0" class="inboxTab">
             <tr>
             	<th width="10%"><input type="checkbox" id="checkall" onclick="selectAllBoxs('checkall','receivebox.receive_id')">全选</th>
                <th width="8%"><img src="/include/bmember/images/yj.gif"></th>
                <th width="17%">发件人</th>
                <th width="50%" >主题</th>
                <th width="15%" >时间</th>
             </tr>
             <#list receiveboxList as receivebox>
             <tr>
               	<td><input type="checkbox" name="receivebox.receive_id" value="${receivebox.receive_id?if_exists}"></td>
                   
                    <td>
                      <a href="#" onclick="linkToInfo('/bmall_Receivebox_view.action','receivebox.receive_id=${receivebox.receive_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');">
                    		<#if receivebox.is_read=='0'>
						   		 <img src="/include/bmember/images/ydyj.gif">
						    <#else>
						    	 <img src="/include/bmember/images/wdyj.gif">
						    </#if>
						    </a> 
                    </td>
                    <td><a href="#" onclick="linkToInfo('/bmall_Receivebox_view.action','receivebox.receive_id=${receivebox.receive_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');">${receivebox.cust_name?if_exists}</a></td>
                    <td><a href="#" onclick="linkToInfo('/bmall_Receivebox_view.action','receivebox.receive_id=${receivebox.receive_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');"><#if receivebox.title?if_exists?length lt 21>${receivebox.title?if_exists}<#else>${receivebox.title[0..20]?if_exists}..</#if></a></td> 
                    <td>
                    <a href="#" onclick="linkToInfo('/bmall_Receivebox_view.action','receivebox.receive_id=${receivebox.receive_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');">
                    <#if receivebox.in_date?if_exists!=null&&receivebox.in_date?if_exists!=''>
				        ${receivebox.in_date?string("yyyy-MM-dd")}
				    <#else>
				    -
				    </#if>
				    </a>
				    </td>
             </tr>
             </#list>
          </table>
          <!---->
            <div class="selUsed">
              <span><input type="checkbox" id="checkAll" onclick="selectAllBoxs('checkAll','receivebox.receive_id')">全选</span>
              <input type="button" class="qrbut" value="加入回收站" onclick="delInfo('receivebox.receive_id','/bmall_Receivebox_delete.action')">
              &nbsp;&nbsp;<input type="button" class="qrbut" value="彻底删除" onclick="delB2cInfo('/bmall_Receivebox_realDelete.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}','receivebox.receive_id')">
           </div>
        </div>
        <!--翻页控件-->
        <div class="listbottom">${pageBar?if_exists}</div>
		
   </div>
   
  </div>
  <div class="clear"></div>
</div>
<@s.hidden name="receivebox.receive_id" value="${(receivebox.receive_id)?if_exists}"/>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/> 
</@s.form>
</body>
</html>

