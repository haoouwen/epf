<html>
  <head>
    <title>荣誉资质管理</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
<body>
<@s.form action="/admin_Membercert_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:会员管理 > 企业会员 > 荣誉资质管理</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
	      <tr>
      		<td class="searchDiv_td">证书标题:</td>
			<td><@s.textfield name="title_s"  cssStyle="width:200px"/></td>
			
			<td class="tdpad">企业名称:</td>
			<td><@s.textfield name="cust_name_s" cssStyle="width:200px"/></td>
			
			<td class="tdpad">证书状态:</td>
			<td><@s.select name="cert_state_s" list=r"#{'':'请选择','0':'审核通过','1':'未审核','2':'未通过'}"/></td>
			
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
	    <th width="3%"   ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('membercert.cert_id')"/></th>
	    <th width="25%"  >证书标题</th>
	    <th width="25%"  >企业名称</th>
	    <th width="20%"  >发证机构</th>
	    <th width="10%"  >证书状态</th>
	    <th width="10%"  >添加时间</th>
	    <th width="7%"  >操作</th>
	  </tr>
	  
	  <#list membercertList as cert>
	
	    <tr bgcolor="<#if cert_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
	    <td><input type="checkbox" name="membercert.cert_id" value="${cert.cert_id?if_exists}"/></td>
	    <td align="left">
	    	<#if cert.title?if_exists !='' >
	    	   <a onclick="linkToInfo('/admin_Membercert_view.action','membercert.cert_id=${cert.cert_id?if_exists}');">
	    		<#if cert.title?length lt 19>${cert.title?if_exists}<#else>${cert.title[0..18]}..</#if>
	    	   </a>
	    	</#if>
	    </td>
	     <td align="center">     
	       <a href="/admin_Member_viewinfo.action?member.cust_id=${(cert.cust_id)?if_exists}" target="_blank">    
	    	<#if cert.cust_name?if_exists !='' >
	    		<#if cert.cust_name?length lt 21>${cert.cust_name?if_exists}<#else>${cert.cust_name[0..20]}..</#if>
	    	</#if>
	      </a>	
	    </td>
	    <td align="center">
	    	<#if cert.organize?if_exists !='' >
	    		<#if cert.organize?length lt 19>${cert.organize?if_exists}<#else>${cert.organize[0..18]}..</#if>
	    	</#if>
	    </td>
	    
	    <td align="center">
	       <a href="/admin_Membercert_list.action?cert_state_s=${cert.cert_state?if_exists}">
	    	<#if cert.cert_state=='0'><span class="greencolor">审核通过</span>
	    	<#elseif cert.cert_state=='1'><span class="redcolor">未审核</span>
	    	<#else><span class="bluecolor">未通过</span>
	    	</#if>
	       </a>	
	    </td>
	    <td align="center"> 
	    	<#if cert.in_date?if_exists?string !='' >
	    		${cert.in_date?if_exists} 
	    	</#if> 
	    </td>
	    <td align="center">
	     <a onclick="linkToInfo('/admin_Membercert_view.action','membercert.cert_id=${cert.cert_id?if_exists}');" title="修改"><img src="/include/common/images/bj.gif" /></a>
	     <a onclick="linkToInfo('/admin_Membercert_audit.action','membercert.cert_id=${cert.cert_id?if_exists}');" title="审核"><img src="/include/common/images/audit.png" /></a>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Membercert_add.action','');" value="添加资质">
     <input type="button" class="rbut" onclick="delInfo('membercert.cert_id','/admin_Membercert_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
  <@s.hidden id="search_area_attr" name="area_attr_s"/>
  <@s.hidden name="organize_s"/>
  <@s.hidden name="in_date_s"/>
  <@s.hidden name="end_date_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>

<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Membercert_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
	
		<tr>
			<td class="searchDiv_td">证书标题:</td>
			<td><@s.textfield name="title_s" maxLength="50"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">企业名称:</td>
			<td><@s.textfield name="cust_name_s" maxLength="20"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">发证机构:</td>
			<td><@s.textfield name="organize_s" maxLength="20"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">证书状态:</td>
			<td><@s.select name="cert_state_s" list=r"#{'':'请选择','0':'审核通过','1':'未审核','2':'未通过'}"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">到期日期:</td>
			<td>
			<@s.textfield id="txtstartDate" name="in_date_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
			               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
			&nbsp;至&nbsp;
			 <@s.textfield id="txtendDate" name="end_date_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
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

