<html>
  <head>
    <title>广告管理</title>
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
 <body>
<@s.form action="/admin_Advinfo_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：网站管理 > 广告管理 > 广告管理 > 广告列表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>广告名称:</td>
			<td><@s.textfield name="adv_name_s"  cssStyle="width:245px;"/></td>
		    
		    <td class="searchDiv_td">广告所属类型:</td>
		    <td><@s.select name="adv_pos_s" list="typecommparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/></td>
	       
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
		    <th width="3%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('advinfo.adv_id')"/></th>
		    <th wihth="3%" align="center" >排序</th>
            <th width="20%" align="center" >广告位名称</th>
		    <th width="18%" align="center" >广告名称</th>
		    <th width="10%" align="center" >广告类型</th>
		    <th width="12%" align="center" >开始时间</th>
		    <th width="12%" align="center" >结束时间</th>
		    <th width="10%" align="center" >广告所属类型</th>
		    <th  align="center" >操作</th>
	  </tr>
	  <#list advinfoList as adv>

    <tr>
    <td><input type="checkbox" name="advinfo.adv_id" value="${adv.adv_id?if_exists}"/></td>
    <td align="left">${adv.info_id?if_exists}</td>
    <td align="left">${adv.pos_name?if_exists}</td>
    <td align="left">
      <#if adv.adv_name?if_exists!=''>
      <#if adv.adv_name?if_exists?length lt 25>${adv.adv_name?if_exists}<#else>${adv.adv_name?if_exists[0..24]}..</#if>
      </#if>
    </td>
    <td align="center">${adv.pos_type?if_exists}</td>
    <td align="center">
    <#if adv.start_date?string!=''>
    <#if  adv.start_date?length   lt   10  >   ${adv.start_date} <#else> ${adv.start_date[0..9]}</#if>
    </#if>
    </td>
    <td align="center">
    <#if adv.end_date?string!=''>
    <#if  adv.end_date?length   lt   10  >   ${adv.end_date} <#else> ${adv.end_date[0..9]}</#if>
    </#if>
    </td>
    <td align="center">
        ${(adv.para_key)?if_exists}
     </td>
    <td align="center">
  <a onclick="document.getElementById('indexForm').action='/admin_Advinfo_view.action?advinfo.adv_id=${adv.adv_id?if_exists}&type=${adv.type?if_exists}&tablename=${adv.module_type?if_exists}&posid=${posid?if_exists}';document.getElementById('indexForm').submit();">
    <img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut"onclick="delInfo('advinfo.adv_id','/admin_Advinfo_delete.action?posid=${posid?if_exists}&two_pages_link=no')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden  name="module_type_s"/>
  <@s.hidden  name="pos_type_s"/>
  <@s.hidden  name="adv_state_s"/>
  <@s.hidden  name="iscount_s"/>
  <@s.hidden  name="start_date_s"/>
  <@s.hidden  name="end_date_s"/>            
  <@s.hidden  name="cat_attr_s"/>
  <@s.hidden  name="area_attr_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Advinfo_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
		<td class="searchDiv_td">广告名称:</td>
		<td><@s.textfield name="adv_name_s" size="40"/></td>
	</tr>
	<tr>
		<td class="searchDiv_td">广告类型:</td>
		<td><@s.select name="pos_type_s" list="advcommparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/></td>
	</tr>
	<tr>
		<td class="searchDiv_td">广告状态:</td>
		<td><@s.select name="adv_state_s" list=r"#{'':'请选择','0':'已审核','1':'未审核'}"/> </td>
	</tr>
	<tr>
		<td class="searchDiv_td">开始时间:</td>
		<td>
		<@s.textfield id="txtstartDate" name="start_date_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		</td>
	</tr>
	<tr>
		<td class="searchDiv_td">结束时间:</td>
		<td>
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
  
