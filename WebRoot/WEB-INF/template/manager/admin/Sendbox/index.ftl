<html>
<head>
<title>发件箱</title>
<link href="/include/bmember/css/email.css" rel="stylesheet" type="text/css">
</head>
<body>
<@s.form action="/admin_Sendbox_list.action" method="post" id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:会员管理 > 通讯管理 > 收件箱</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td align="right">收件人:</td> 
			<td><@s.textfield name="rate_name_s" cssStyle="width:200px;"/></td>
	        <td><input type="submit" class="rbut" value="查询"></td>
	      </tr>
     </table>
   </div>
<!--条件结束-->
<!--后台table-->
 <div class="rtable">
          <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
             <tr>
             	<th width="3%"><input type="checkbox" id="checkall" onclick="selectAllBoxs('checkall','sendbox.send_id')"></th>
                <th width="5%"><img src="/include/bmember/images/yj.gif"></th>
                <th width="25%">收件人</th>
                <th width="35%" >主题</th>
                <th width="15%" >时间</th>
             </tr> 
             
            <#list sendboxList as sendbox>  
             <tr>
               	<td><input type="checkbox" name="sendbox.send_id" value="${sendbox.send_id?if_exists}"></td>
                    <td><a href="#" onclick="linkToInfo('/admin_Sendbox_view.action','sendbox.send_id=${sendbox.send_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');"><img src="/include/bmember/images/ydyj.gif"></a>  </td>
                    <td><a href="#" onclick="linkToInfo('/admin_Sendbox_view.action','sendbox.send_id=${sendbox.send_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');" title="${sendbox.recevie_name?if_exists}">
                        <#if sendbox.recevie_name?if_exists!=''>
                       <#if sendbox.recevie_name?if_exists?length lt 25>
                       ${sendbox.recevie_name?if_exists}
                       <#else>
                       ${sendbox.recevie_name?if_exists[0..24]}...</#if>
                       </#if>
                    </a>
                    </td>
                    <td><a href="#" onclick="linkToInfo('/admin_Sendbox_view.action','sendbox.send_id=${sendbox.send_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');">${sendbox.title?if_exists}</a>  </td> 
                    <td><a href="#" onclick="linkToInfo('/admin_Sendbox_view.action','sendbox.send_id=${sendbox.send_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');">${sendbox.in_date?string("yyyy-MM-dd ")}</a>  </td>
             </tr>
             </#list>
          </table>
       <!--后台table结束-->
<!--翻页-->
   <div class="pages">
     ${pageBar?if_exists}
   </div>
   <div class="clear"/>
<!--翻页结束-->
<!--按钮操作存放-->
   <div class="bsbut">
             <input type="button" class="rbut" value="彻底删除" onclick="delB2cInfo('/admin_Sendbox_delete.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}','sendbox.send_id')">
       <!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
<@s.hidden name="receivebox.receive_id" value="${(receivebox.receive_id)?if_exists}"/>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/> 
 <!--表单框隐藏域存放-->  
</@s.form>
<!--搜索区域开始-->
<!--搜索区域结束-->
  </body>
</html>



