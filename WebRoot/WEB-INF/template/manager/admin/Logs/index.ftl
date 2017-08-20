<html>
  <head>
    <title>系统日志管理</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
    <script language="javascript" type="text/javascript" src="/include/common/js/exportstoxls.js"></script>
    <script type="text/javascript">
    //重置系统日志
	function resetLogs(){
		if(window.confirm("确定要重置系统日志吗?")) {
			document.forms[0].action='/admin_Logs_reset.action';
			document.forms[0].submit();
		}
	}	
	 //导出系统日志
	function exprotExcel(){
		if(window.confirm("确定要导出系统日志吗?")) {
			document.forms["form_search_id"].action='/admin_Logs_export.action';
			document.forms["form_search_id"].submit();
		}
	}	
    </script>
  </head>
  <body>
<@s.form action="/admin_Logs_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：系统管理 > 系统管理 > 系统日志管理</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>用户名:</td>
			<td><@s.textfield name="user_name_s"  cssStyle="width:245px;"/></td>
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
		    <th width="3%" > <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('logs.log_id')"/></th>
		    <th width="15%"  >用户名</th>
		    <th width="15%"  >IP</th>
		    <th width="57%"  >操作内容</th>
		    <th width="10%"  >操作时间</th>
	  </tr>
	  
	   <#list logslist as logs>
	    <tr >
		    <td><input type="checkbox" name="logs.log_id" value="${logs.log_id?if_exists}"/></td>
		    <td align="center">
				${logs.user_name?if_exists}
		    </td>
		     <td align="center">
				${logs.ip?if_exists}
		    </td>
		     <td align="center">
				${logs.content?if_exists}
		    </td>
		     <td align="center">
				${logs.in_date?if_exists?string("yyyy-MM-dd HH:mm:ss")}
		    </td>
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
     <input type="button" class="rbut"onclick="exprotExcel();"  value="导出日志">
   <#--<input type="button" class="rbut" onclick="javascript:exports('tableExcel');"value="导出日志">-->
     <input type="button" class="rbut"onclick="resetLogs();"value="重置">
     <input type="button" class="rbut"onclick="delInfo('logs.log_id','/admin_Logs_delete.action')"value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden  name="content_s"/>
  <@s.hidden  name="ip_s"/>
  <@s.hidden  name="starttime_s"/>
  <@s.hidden  name="endtime_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Logs_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td class="searchDiv_td">用户名:</td>
			<td><@s.textfield name="user_name_s"  /></td>
		</tr>
		<tr>
			<td class="searchDiv_td">操作内容:</td>
			<td><@s.textfield name="content_s"  /></td>
		</tr>
		<tr>
			<td class="searchDiv_td">IP:</td>
			<td><@s.textfield name="ip_s"  /></td>
		</tr>
		<tr>
			<td class="searchDiv_td">时间段:</td>
			<td>
					<@s.textfield id="txtStartDate" name="starttime_s"  type="text" cssClass="Wdate"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtEndDate\\',{d:-1})}',readOnly:true})" />&nbsp;至
             		<@s.textfield id="txtEndDate" name="endtime_s"  type="text" cssClass="Wdate"  onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtStartDate\\',{d:1})}',readOnly:true})" />
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
