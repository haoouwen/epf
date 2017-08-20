<html>
  <head>
    <title>网站留言管理</title>
  </head>
<body>
<@s.form action="/admin_Message_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:网站管理 > 基本功能 > 网站留言管理</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>留言主题:</td>
			<td><@s.textfield name="mess_title_s" cssStyle="width:200px;"/></td>
			
			<td class="tdpad">联系人:</td>
			<td><@s.textfield name="mess_name_s" cssStyle="width:200px;"/></td>
			
	        <td><input type="submit" class="rbut" value="查询"></td>
	        <td><input type="button" onclick="searchShowDIV('searchDiv','300px','220px');" class="rbut" value="高级查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
	    <td width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('message.mess_id')"/></td>
	    <td width="15%">留言主题</td>
	    <!--<td width="10%"  >留言内容</td>-->
		<td width="10%">联系人</td>
		<td width="10%">联系电话</td>
		<td width="10%">电子邮件</td>
		<td width="15%">QQ</td>
		<td width="10%">MSN</td>
		<td width="10%">Skype</td>
		<td width="10%">留言时间</td>
		<td width="7%" >操作</td>
      </tr>
	  
	   <#list messageList as message>
	   
		  <tr>
		    <td><input type="checkbox" name="message.mess_id" value="${message.mess_id?if_exists}"/></td>
		    <td align="center">${message.title?if_exists}</td>
		    <!--<td align="center">${message.content?if_exists}</td>-->
			<td align="center">${message.name?if_exists}</td>
			<td align="center">${message.phone?if_exists}</td>
			<td align="center">${message.email?if_exists}</td>
			<td align="center">${message.qq?if_exists}</td>
			<td align="center">${message.msn?if_exists}</td>
			<td align="center">${message.skype?if_exists}</td>
			<td align="center">${message.in_date.toString().substring(0,10)?if_exists}</td>
		    <td align="center"><a onclick="linkToInfo('/admin_Message_view.action','message.mess_id=${message.mess_id?if_exists}');"><img src="/include/common/images/view.gif" /></a></td>
		  </tr>
		  
  	  </#list>
  	  
	</table>
  </div>
<!--后台table结束-->
<!--翻页-->
   <div class="pages">
     ${pageBar?if_exists}
   </div>
   <div class="clear"/>
<!--翻页结束-->
<!--按钮操作存放-->
   <div class="bsbut">
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Message_add.action','');" value="网站留言">
     <input type="button" class="rbut"onclick="delInfo('message.mess_id','/admin_Message_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="mess_phone_s"/>
  <@s.hidden name="mess_email_s"/>
  <@s.hidden name="mess_qq_s"/>
  <@s.hidden name="mess_msn_s"/>
  <@s.hidden name="mess_skype_s"/>
  
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Message_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		
		<tr>
			<td class="searchDiv_td">留言主题:</td>
			<td><@s.textfield name="mess_title_s"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">联系人:</td>
			<td><@s.textfield name="mess_name_s"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">联系电话:</td>
			<td><@s.textfield name="mess_phone_s"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">电子邮件:</td>
			<td><@s.textfield name="mess_email_s"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">QQ:</td>
			<td><@s.textfield name="mess_qq_s"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">MSN:</td>
			<td><@s.textfield name="mess_msn_s"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">Skype:</td>
			<td><@s.textfield name="mess_skype_s"/></td>
		</tr>
		<tr>
			<td align="center" colspan="2" style="border:0px;">
				<input type="button" name="search" value="搜索" onclick="showSearchDiv('area_attr','cat_attr','search_area_attr','search_cat_attr','form_search_id');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			</td>
	   </tr>
		</table>
		<!--搜索框隐藏域存放-->
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>
