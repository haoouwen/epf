<html>
  <head>
    <title>举报管理</title>
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
<body>
<@s.form action="/admin_Memberreport_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion"> 当前位置:网站管理 > 基本功能 > 举报管理</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>举报人:</td>
			<td><@s.textfield name="user_name_s"cssStyle="width:245px;"/></td></td>
			 <td class="tdpad">举报类型:</td>
			<td>
				     <@s.select name="report_type_s"  list="CommparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
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
		    <td width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('memberreport.report_id')"/></td>
		    <td width="15%"  >举报人</td>
		    <td width="30%"  >举报地址</td>
		    <td width="20%"  >举报类型</td>
		    <td width="10%"  >状态</td>
		    <td width="15%"  >发布时间</td>
		    <td width="7%" >修改</td>
		  </tr>
	  
	  <#list memberreportList as report>
		  <tr>
		    <td><input type="checkbox" name="memberreport.report_id" value="${report.report_id?if_exists}"/></td>
		    <td align="center">${report.cust_name?if_exists}</td>
		    <td align="center">
		    <#if report.link_url?if_exists!=''>
		    <#if report.link_url?length lt 30>
		    <a href="${report.link_url?if_exists}" title="${report.link_url?if_exists}" target="_blank">${report.link_url?if_exists}</a>
		    <#else>
		    <a href="${report.link_url?if_exists}" title="${report.link_url?if_exists}" target="_blank">${report.link_url[0..29]}</a>
		    </#if>
		    </#if></td>
		    <td align="center">${report.model_value?if_exists}</td>
		    <td align="center"><#if report.info_state?if_exists=='0'><a onclick="linkToInfo('/admin_Memberreport_list.action','info_state_s=${report.info_state?if_exists}');"><span class="redcolor">等待处理</span></a></#if>
		    <#if report.info_state?if_exists=='1'><a onclick="linkToInfo('/admin_Memberreport_list.action','info_state_s=${report.info_state?if_exists}');"><span class="greencolor">受理中</span></a></#if>
		    <#if report.info_state?if_exists=='2'><a onclick="linkToInfo('/admin_Memberreport_list.action','info_state_s=${report.info_state?if_exists}');"><span class="bluecolor">已撤销</span></a></#if>
		    <#if report.info_state?if_exists=='3'><a onclick="linkToInfo('/admin_Memberreport_list.action','info_state_s=${report.info_state?if_exists}');"><span class="orangecolor">已完结</span></a></#if></td> 
		     <td align="center">${report.in_date?if_exists}</td>
		    <td align="center"><a onclick="linkToInfo('/admin_Memberreport_view.action','memberreport.report_id=${report.report_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Memberreport_cate.action','');" value="添加">
     <input type="button" class="rbut"onclick="delInfo('memberreport.report_id','/admin_Memberreport_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="cat_attr_s"/>
  <@s.hidden name="area_attr_s"/>
  <@s.hidden name="info_state_s"/>
  <@s.hidden name="starttime_s"/>
  <@s.hidden name="endtime_s"/>
<!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Memberreport_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td class="searchDiv_td">举报人:</td>
				<td><@s.textfield name="user_name_s"/></td>
			</tr>
			<tr>
				 <td class="searchDiv_td">举报类型:</td>
				    <td>
				     <@s.select name="report_type_s"  list="CommparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
				  </td>
			</tr>
			 <tr>
		             <td class="searchDiv_td">处理状态:</td>
		             <td>
		             	<@s.select name="info_state_s" list=r"#{'0':'等待处理','1':'受理中','2':'已撤销','3':'已完结'}" headerKey="" headerValue="请选择"/>
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
					<input type="button" name="search" value="搜索" onclick="showSelectDiv('','cat_attr','','search_cat_attr');"/>&nbsp;
					<input type="button" name="close" value="关闭" onclick="closeSearch();"/>
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
