<html>
  <head>
    <title>留言管理</title>
    <script type="text/javascript" src="/include/admin/js/memberleave.js"></script> 
       
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>

<body>
<@s.form action="/admin_Memberleave_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:会员管理 > 企业相关 > 留言管理</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>留言会员名称:</td>
			<td><@s.textfield name="cust_name_s"/></td>
			 <td class="tdpad">是否有效:</td>
	          <td>
	           	<@s.select name="is_del_s" list=r"#{'0':'有效','1':'回收站'}" headerKey="" headerValue="请选择"/>  
	          </td>
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
		    <td width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('memberleave.leave_id')"/></td>
		    <td width="10%" >留言会员名称</td>
		    <td width="15%" >留言用户名称</td>
		    <td width="15%"  >留言标题</td>
		    <td width="15%"  >接收人</td>
		    <td width="15%"  >信息来源</td>
		    <td width="10%"  >是否有效</td>
		    <td width="10%"  >留言时间</td>
		    <td width="5%" >查看</td>
		  </tr>
  
  <#list memberleaveList as memberleave>
  <tr>
    <td><input type="checkbox" name="memberleave.leave_id" value="${memberleave.leave_id?if_exists}"/></td>
    <td align="center">${memberleave.cust_name?if_exists}</td>
    <td align="center">${memberleave.send_user_name?if_exists}</td>
    <td align="center">
    <#if memberleave.title?if_exists!=''>
    <#if memberleave.title?length lt 21>
    ${memberleave.title?if_exists}
    <#else>
    ${memberleave.title[0..20]}...
    </#if>
    </#if>
    </td>
     <td align="center">
     <#if memberleave.get_cust_id?if_exists!=''>
    <#if memberleave.get_cust_id?length lt 10>
    ${memberleave.get_cust_id?if_exists}
    <#else>
    ${memberleave.get_cust_id[0..9]}...
    </#if>
    </#if>
    </td>
    <td align="center">
    <#if memberleave.source?if_exists!=''>
    <#if memberleave.source?length lt 8>
    ${memberleave.source?if_exists}
    <#else>
    ${memberleave.source[0..7]}...
    </#if>
    </#if>
    </td>
    <td align="center"><#if memberleave.is_del?if_exists=='0'><a onclick="linkToInfo('/admin_Memberleave_list.action','is_del_s=${memberleave.is_del?if_exists}');"><font color='green'>有效</font></a><#else><a onclick="linkToInfo('/admin_Memberleave_list.action','is_del_s=${memberleave.is_del?if_exists}');"><font color='red'>回收站</font></a></#if> </td>
    <td align="center">${memberleave.in_date?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Memberleave_view.action','memberleave.leave_id=${memberleave.leave_id?if_exists}');"><img src="/include/common/images/view.gif" /></a></td>	
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
     <input type="button" class="rbut" onclick="updateState('0','memberleave.leave_id','/admin_Memberleave_updateisdel.action');" value="有效">
     <input type="button" class="rbut" onclick="delInfo('memberleave.leave_id','/admin_Memberleave_delete.action')" value="删除">
     <input type="button" class="rbut" onclick="updateState('1','memberleave.leave_id','/admin_Memberleave_updateisdel.action');" value="回收站">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
	  <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
	  <@s.hidden id="search_area_attr" name="area_attr_s"/>
	  <@s.hidden name="memberleave.is_del" id="admin_memberleave_state"/>
	  <@s.hidden name="send_user_name_s"/>
	  <@s.hidden name="starttime_s"/>
	  <@s.hidden name="endtime_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Memberleave_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
		<td class="searchDiv_td">留言会员名称:</td>
		<td><@s.textfield name="cust_name_s"/></td>
	</tr>
	<tr>
		<td class="searchDiv_td">留言用户名称:</td>
		<td><@s.textfield name="send_user_name_s"/></td>
	</tr>
   	<tr>
         <td class="searchDiv_td">是否有效:</td>
          <td>
           <@s.select name="is_del_s" list=r"#{'0':'有效','1':'回收站'}" headerKey="" headerValue="请选择"/>  
          </td>
    </tr>
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
		    <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
		    <@s.hidden id="search_area_attr" name="area_attr_s"/>
			<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
			<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>




