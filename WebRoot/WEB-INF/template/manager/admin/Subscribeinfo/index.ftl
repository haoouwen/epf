<html>
  <head>
    <title>查看商机邮件</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  <body>


<body>
<@s.form action="/admin_Subscribeinfo_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：输入当前位置</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			 <td>商机标题:</td>
		 	<td><@s.textfield name="keyword_s"/></td>
		 	<td class="tdpad">信息类型:</td>
			<td><@s.select id="info_type_s" name="info_type_s" list=r"#{'0':'供应','1':'求购'}"  headerKey="" headerValue="请选择"/></td>
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
		    <th width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('subscribeinfo.send_id')"/></th>
		    <th width="20%" >会员名称</th>
		    <th width="20%"  >商机标题</th>
		    <th width="20%"  >信息类型</th>
		    <th width="20%"  >信息标识串</th>
		    <th width="15%"  >发送时间</th>
		  </tr>
		  
		  <#list subscribeinfoList as subscribeinfo>
		  <tr>
		    <td><input type="checkbox" name="subscribeinfo.send_id" value="${subscribeinfo.send_id?if_exists}"/></td>
		    <td align="center">${subscribeinfo.cust_name?if_exists}</td>
		    <td align="center">${subscribeinfo.keyword?if_exists}</td>
		    <td align="center"><#if subscribeinfo.info_type?if_exists=='0'>
		    <a onclick="linkToInfo('/admin_Subscribeinfo_list.action','info_type_s=${subscribeinfo.info_type?if_exists}');"><font color='green'>供应</font></a>
		    <#else><a onclick="linkToInfo('/admin_Subscribeinfo_list.action','info_type_s=${subscribeinfo.info_type?if_exists}');"><font color='red'>求购</font></a></#if> </td>
		    <td align="center">${subscribeinfo.info_attr?if_exists}</td>
		    <td align="center">${subscribeinfo.in_date?if_exists}</td>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Subscribe_list.action','');" value="返回">
     <input type="button" class="rbut" onclick="delInfo('subscribeinfo.send_id','/admin_Subscribeinfo_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  	<@s.hidden name="nav.isshow" id="admin_nav_state"/>
    <@s.hidden name="cust_name_s" />
    <@s.hidden name="starttime_s" />
    <@s.hidden name="endtime_s" />
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Subscribeinfo_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<<tr>
		<td class="searchDiv_td">会员名称:</td>
		<td><@s.textfield name="cust_name_s"/></td>
	</tr>
	<tr>
		<td class="searchDiv_td">商机标题:</td>
		<td><@s.textfield name="keyword_s"/></td>
	</tr>
	<tr>
	<td class="searchDiv_td">信息类型:</td>
	<td><@s.select id="info_type_s" name="info_type_s" list=r"#{'0':'供应','1':'求购'}"  headerKey="" headerValue="请选择"/></td>
	</tr>
	<tr>
    <tr>
    <td class="searchDiv_td" >时间段:</td>
        <td>
        <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
         至
        <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
        </td>
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




