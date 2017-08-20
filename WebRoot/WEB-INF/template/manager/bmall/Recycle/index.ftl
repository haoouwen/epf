<html>
<head>
<title>回收站</title>
	<script type="text/javascript" src="/include/bmall/js/recycle.js"></script>
	<link href="/include/bmember/css/email.css" rel="stylesheet" type="text/css">
</head>
<body>

<@s.form action="/bmall_Recycle_list.action" method="post" id="indexForm">
<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>回收站</span></h2>
        <!---->
        <div class="email">
          <table width="100%" cellpadding="0" cellspacing="0" class="inboxTab">
             <tr>
             	<th width="10%"><input type="checkbox" id="checkall" onclick="selectAllBoxs('checkall','recycle.recycle_id')">全选</th>
                <th width="8%"><img src="/include/bmember/images/yj.gif"></th>
                <th width="17%">发件人</th>
                <th width="50%" >主题</th>
                <th width="15%" >时间</th>
             </tr>
             
             <#list recycleList as recycle>
             <tr>
               	<td><input type="checkbox" name="recycle.recycle_id" value="${recycle.recycle_id?if_exists}"></td>
                <a href="#">
                    <td>
                    	<#if recycle.is_read=='0'>
					   		 <img src="/include/bmember/images/ydyj.gif">
					    <#else>
					    	<img src="/include/bmember/images/wdyj.gif">
					    </#if>
                    
                    </td>
                    <td>${recycle.cust_name?if_exists}</td>
                    <td>${recycle.title?if_exists}</td> 
                    <td>${recycle.in_date?if_exists?string("yyyy-MM-dd ")}</td>
                </a>  
             </tr>
             </#list>
          </table>
          <!---->
            <div class="selUsed">
              <span><input type="checkbox" id="checkAll" onclick="selectAllBoxs('checkAll','recycle.recycle_id')">全选</span><input type="button" class="graybut" value="还原" onclick="revertInfo('recycle.recycle_id','/bmall_Recycle_revert.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')">&nbsp;&nbsp;<input type="button" class="graybut" value="彻底删除" onclick="delB2cInfo('/bmall_Recycle_delete.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}','recycle.recycle_id')">
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

















