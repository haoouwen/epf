<html>
<head>
<title>回收站</title>
	<script type="text/javascript" src="/include/bmall/js/recycle.js"></script>
	<link href="/include/bmember/css/email.css" rel="stylesheet" type="text/css">
</head>
<body>

<@s.form action="/admin_Recycle_list.action" method="post" id="indexForm">
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
             	<th width="3%"><input type="checkbox" id="checkall" onclick="selectAllBoxs('checkall','recycle.recycle_id')"></th>
                <th width="8%"><img src="/include/bmember/images/yj.gif"></th>
                <th width="17%">发件人</th>
                <th width="50%" >主题</th>
                <th width="15%" >时间</th>
                <th width="5%">操作</th>
             </tr>
             
             <#list recycleList as recycle>
             <tr>
               	<td><input type="checkbox" name="recycle.recycle_id" value="${recycle.recycle_id?if_exists}"></td>
               
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
                   <td align="center"><a onclick="linkToInfo('/admin_Recycle_view.action','recycle.recycle_id=${recycle.recycle_id?if_exists}');"><img src="/include/common/images/view.gif" /></a></td>
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
       <input type="button" class="rbut" value="还原" onclick="revertInfo('recycle.recycle_id','/admin_Recycle_revert.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')">
       <input type="button" class="rbut" value="彻底删除" onclick="delB2cInfo('/admin_Recycle_delete.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}','recycle.recycle_id')">
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
<@s.hidden name="receivebox.receive_id" value="${(receivebox.receive_id)?if_exists}"/>
 <!--表单框隐藏域存放-->  
</@s.form>
<!--搜索区域开始-->
<!--搜索区域结束-->
  </body>
</html>

















