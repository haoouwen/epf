<html>
<head>
<title>收件箱</title>
<link href="/include/bmember/css/email.css" rel="stylesheet" type="text/css">
</head>
<body>
<@s.form action="/admin_Receivebox_list.action" method="post" id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:会员管理 > 通讯管理 > 收件箱</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td align="right">发件人:</td> 
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
             	<th width="3%" align="center"><input type="checkbox" id="checkall" onclick="selectAllBoxs('checkall','receivebox.receive_id')"></th>
                <th width="8%" align="center"><img src="/include/bmember/images/yj.gif"></th>
                <th width="15%" align="center">收件人</th>
                <th width="15%" align="center">发件人</th>
                <th width="44%" align="center">主题</th>
                <th width="15%" align="center">时间</th>
             </tr>
             <#list receiveboxList as receivebox>
             <tr>
               	<td><input type="checkbox" name="receivebox.receive_id" value="${receivebox.receive_id?if_exists}"></td>
                   
                    <td>
                      <a href="#" onclick="linkToInfo('/admin_Receivebox_view.action','receivebox.receive_id=${receivebox.receive_id?if_exists}');">
                    		<#if receivebox.is_read=='0'>
						   		 <img src="/include/bmember/images/ydyj.gif">
						    <#else>
						    	 <img src="/include/bmember/images/wdyj.gif">
						    </#if>
						    </a> 
                    </td>
                    <td><a href="#" onclick="linkToInfo('/admin_Receivebox_view.action','receivebox.receive_id=${receivebox.receive_id?if_exists}');">${receivebox.recevie_name?if_exists}</a></td>
                    <td><a href="#" onclick="linkToInfo('/admin_Receivebox_view.action','receivebox.receive_id=${receivebox.receive_id?if_exists}');">${receivebox.cust_name?if_exists}</a></td>
                    <td><a href="#" onclick="linkToInfo('/admin_Receivebox_view.action','receivebox.receive_id=${receivebox.receive_id?if_exists}');"><#if receivebox.title?if_exists?length lt 21>${receivebox.title?if_exists}<#else>${receivebox.title[0..20]?if_exists}..</#if></a></td> 
                    <td>
                    <a href="#" onclick="linkToInfo('/admin_Receivebox_view.action','receivebox.receive_id=${receivebox.receive_id?if_exists}');">
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

<!--后台table结束-->
<!--翻页-->
   <div class="pages">
     ${pageBar?if_exists}
   </div>
   <div class="clear"/>
<!--翻页结束-->
<!--按钮操作存放-->
   <div class="bsbut">
    <input type="button" class="rbut" value="加入回收站" onclick="delInfo('receivebox.receive_id','/admin_Receivebox_delete.action')">
    <input type="button" class="rbut" value="彻底删除" onclick="delB2cInfo('/admin_Receivebox_realDelete.action','','receivebox.receive_id')">
   </div>
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

