<html>
  <head>
    <title>商机订阅</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
     <script src="/include/admin/js/subscribe.js" type="text/javascript"></script>
  </head>


<body>
<@s.form action="/admin_Subscribe_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:会员管理 > 企业相关 > 商机订阅</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>会员名称:</td>
			<td><@s.textfield name="cust_name_s"/></td>
			<td class="tdpad">关键字:</td>
			<td><@s.textfield name="keyword_s"/></td>
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
    <th width="5%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('subscribe.sub_id')"/></th>
    <th width="15%" >会员名称</th>
    <th width="20%" >关键字</th>
    <th width="10%" >频率(天数)</th>
    <th width="10%" >信息类型</th>
    <th width="10%" >发送类型</th>
    <th width="10%" >是否有效</th>
    <th width="10%" >订阅时间</th>
    <th width="10%">操作</th>
  </tr>
  
  <#list subscribeList as subscribe>
  <tr>
    <td><input type="checkbox" name="subscribe.sub_id" value="${subscribe.sub_id?if_exists}"/></td>
    <td align="center"><a onclick="linkToInfo('/admin_Subscribe_view.action','subscribe.sub_id=${subscribe.sub_id?if_exists}');">${subscribe.cust_name?if_exists}</a></td>
    <td align="center">
    <#if subscribe.keyword?if_exists!=''>
    <#if subscribe.keyword?length lt 31>
    ${subscribe.keyword?if_exists}
    <#else>
    ${subscribe.keyword[0..30]}...
    </#if>
    </#if></td>
    <td align="center">${subscribe.period?if_exists}</td>
    <td align="center">
    	<a onclick="linkToInfo('/admin_Subscribe_list.action','info_type_s=${subscribe.info_type?if_exists}');">
    		<#if subscribe.info_type?if_exists=='0'>
    			<font color='green'>供应</font>
    		<#else>
    			<font color='red'>求购</font>
    		</#if>
    	</a>
    </td>
    <td align="center">
    	<a onclick="linkToInfo('/admin_Subscribe_list.action','send_type_s=${subscribe.send_type?if_exists}');">
    		<#if subscribe.send_type?if_exists=='0'>
    			<font color='green'>邮件</font>
    		<#else>
    			<font color='red'>站内信</font>
    		</#if> 
    	</a>
    </td>
    <td align="center">
    	<a onclick="linkToInfo('/admin_Subscribe_list.action','enabled_s=${subscribe.enabled?if_exists}');">
    		<#if subscribe.enabled?if_exists=='0'>
    			<font color='green'>有效</font>
    		<#else>
    			<font color='red'>回收站</font>
    		</#if> 
    	</a>
    </td>
    <td align="center">${subscribe.in_date?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Subscribe_view.action','subscribe.sub_id=${subscribe.sub_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Subscribe_add.action','');" value="添加订阅">
     <input type="button" class="rbut" onclick="delInfo('subscribe.sub_id','/admin_Subscribe_delete.action')" value="删除">
    <input type="button" class="rbut" onclick="linkToInfo('/admin_Subscribeinfo_list.action','');" value="邮件记录">
    <input type="button" class="rbut" onclick="updateState('1','subscribe.sub_id','/admin_Subscribe_updateenabled.action');" value="有效">
    <input type="button" class="rbut" onclick="updateState('0','subscribe.sub_id','/admin_Subscribe_updateenabled.action');" value="回收站">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="subscribe.enabled" id="admin_subscribe_state"/>
	<@s.hidden name="info_type_s" />
	<@s.hidden name="send_type_s" />
	<@s.hidden name="enabled_s" />
	<@s.hidden name="starttime_s" />
	<@s.hidden name="endtime_s" />
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Subscribe_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
				<td class="searchDiv_td">会员名称:</td>
				<td><@s.textfield name="cust_name_s"/></td>
			</tr>
			<tr>
				<td class="searchDiv_td">关键字:</td>
				<td><@s.textfield name="keyword_s"/></td>
			</tr>
			<tr>
			<td class="searchDiv_td">信息类型:</td>
			<td><@s.select id="info_type_s" name="info_type_s" list=r"#{'0':'供应','1':'求购'}"  headerKey="" headerValue="请选择"/></td>
			</tr>
			<tr>
			<td class="searchDiv_td">发送类型:</td>
			<td><@s.select id="send_type_s" name="send_type_s" list=r"#{'0':'邮件','1':'站内信'}"  headerKey="" headerValue="请选择"/></td>
			</tr>
			<tr>
			<td class="searchDiv_td">是否有效:</td>
			<td><@s.select id="endabled_s" name="enabled_s" list=r"#{'0':'有效','1':'无效'}"  headerKey="" headerValue="请选择"/></td>
			</tr>
		    <tr>
		   <td class="searchDiv_td" >时间段:</td>
		        <td>  	
		        <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
				               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  />至
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





