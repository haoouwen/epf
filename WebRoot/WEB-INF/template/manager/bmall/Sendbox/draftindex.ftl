<html>
<head>
<title>草稿箱</title>
<link href="/include/bmember/css/email.css" rel="stylesheet" type="text/css">
</head>

<body>
<@s.form action="/bmall_Sendbox_list.action" method="post" id="indexForm">

<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>草稿箱</span></h2>
        <!---->
        <div class="email">
          <table width="100%" cellpadding="0" cellspacing="0" class="inboxTab">
             <tr>
             	<th width="10%"><input type="checkbox" id="checkall" onclick="selectAllBoxs('checkall','sendbox.send_id')">全选</th>
                <th width="8%"><img src="/include/bmember/images/yj.gif"></th>
                <th width="17%">收件人</th>
                <th width="50%" >主题</th>
                <th width="15%" >时间</th>
             </tr> 
             
            <#list sendboxList as sendbox>  
             <tr>
               	<td><input type="checkbox" name="sendbox.send_id" value="${sendbox.send_id?if_exists}"></td>
                    <td><a href="#" onclick="linkToInfo('/bmall_Sendbox_draftView.action','sendbox.send_id=${sendbox.send_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');"><img src="/include/bmember/images/ydyj.gif"></a>  </td>
                    <td><a href="#" onclick="linkToInfo('/bmall_Sendbox_draftView.action','sendbox.send_id=${sendbox.send_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');">${sendbox.recevie_name?if_exists}</a>  </td>
                    <td><a href="#" onclick="linkToInfo('/bmall_Sendbox_draftView.action','sendbox.send_id=${sendbox.send_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');">${sendbox.title?if_exists}</a>  </td> 
                    <td><a href="#" onclick="linkToInfo('/bmall_Sendbox_draftView.action','sendbox.send_id=${sendbox.send_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');">${sendbox.in_date?string("yyyy-MM-dd ")}</a>  </td>
             </tr>
             </#list>
          </table>
          <!---->
            <div class="selUsed">
              <span><input type="checkbox" id="checkAll" onclick="selectAllBoxs('checkAll','sendbox.send_id')">全选</span><input type="button" class="graybut" value="删除草稿" onclick="delB2cInfo('/bmall_Sendbox_deleteDraft.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}','sendbox.send_id')">
           </div>
        </div>
        <!--翻页控件-->
        <div class="listbottom">${pageBar?if_exists}</div>
		
   </div>
   
  </div>
  <div class="clear"></div>
</div>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/> 
</@s.form>
</body>
</html>

