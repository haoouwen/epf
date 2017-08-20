<html>
  <head>
    <title>会员升级管理</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
<body>
<@s.form action="/admin_Memberupgrade_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:会员管理 > 会员管理 > 会员升级</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>申请用户名:</td>
			<td><@s.textfield name="user_name_s"  cssStyle="width:245px;" maxLength="50"/></td>
			
			<td class="tdpad">当前级别:</td>
			<td><@s.select name="now_level_s" list=r"#{'':'请选择','1':'个人普通会员','2':'个人VIP会员','3':'企业普通会员','4':'企业VIP会员'}"/></td>
		
			<td class="tdpad">申请级别:</td>
			<td><@s.select name="apply_level_s" list=r"#{'':'请选择','1':'个人普通会员','2':'个人VIP会员','3':'企业普通会员','4':'企业VIP会员'}"/></td>
			
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
            <th width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('memberupgrade.grade_id')"/></th>
		    <th width="20%">会员名称</th>
		    <th width="10%">当前级别</th>
		    <th width="10%">申请级别</th>
		    <th width="10%">申请时间</th>
		    <th width="10%">审核状态</th>
		    <th width="20%">审核用户名</th>
		    <th width="10%">审核时间</th>
		    <th width="7%">操作</th>
	  	  </tr>
	  
	  <#list memberupgradeList as upgrade>

		    <tr bgcolor="<#if upgrade_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
			    <td><input type="checkbox" name="memberupgrade.grade_id" value="${upgrade.grade_id?if_exists}"/></td>
			    
			    <td align="center">${upgrade.cust_name?if_exists}</td>
			    
			    <td align="center">
			        <#if upgrade.now_level?if_exists==1>普通会员
			        <#elseif upgrade.now_level?if_exists==2>普通商家
			        <#elseif upgrade.now_level?if_exists==3>金牌商家
			        </#if>
			    </td>
			    <td align="center">
			        <#if upgrade.apply_level?if_exists==1>普通会员
			        <#elseif upgrade.apply_level?if_exists==2>普通商家
			        <#elseif upgrade.apply_level?if_exists==3>金牌商家
			        </#if>
			    </td>
			    <td align="center">${upgrade.in_date?if_exists}</td>
			    
			    <td align="center">
			     <#list infoStateList as infoState>
    				<#if upgrade.audit_state?if_exists==infoState.para_value>
    					<#if infoState.para_value?if_exists='1'>
					        <font class='greencolor'>${infoState.para_key?if_exists}</font>
						<#elseif infoState.para_value?if_exists='3'>
						    <font class="redcolor">${infoState.para_key?if_exists}</font>
    					<#elseif infoState.para_value?if_exists='0'>
					       <font class="redcolor">${infoState.para_key?if_exists}</font>
					    <#elseif infoState.para_value?if_exists='2'>
					        <font class="bluecolor">${infoState.para_key?if_exists}</font>
					    </#if>
					    <#break/>
				    </#if>
    			</#list>
			    </td>
			    <td align="center">${upgrade.audit_user?if_exists}</td>
			    <td align="center">${upgrade.audit_date?if_exists}</td>
			    <td align="center">
			    
			    <a onclick="linkToInfo('/admin_Memberupgrade_audit.action','memberupgrade.grade_id=${upgrade.grade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Memberupgrade_cate.action','');" value="添加">
     <input type="button" class="rbut"onclick="delInfo('memberupgrade.grade_id','/admin_Memberupgrade_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="cat_attr_s"/>
  <@s.hidden name="area_attr_s"/>
  <@s.hidden name="in_date_s"/>
  <@s.hidden name="out_date_s"/>
  <@s.hidden name="audit_state_s"/>
  <@s.hidden name="audit_user_s"/>
  <@s.hidden name="audit_date_s"/>
  <@s.hidden name="audit_date_end_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Memberupgrade_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">

		<tr>
			<td class="searchDiv_td">当前级别:</td>
			<td><@s.select name="now_level_s" list=r"#{'':'请选择','1':'个人普通会员','2':'个人VIP会员','3':'企业普通会员','4':'企业VIP会员'}"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">申请级别:</td>
			<td><@s.select name="apply_level_s" list=r"#{'':'请选择','1':'个人普通会员','2':'个人VIP会员','3':'企业普通会员','4':'企业VIP会员'}"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">申请时间:</td>
			<td>
			<@s.textfield id="txtstartDate" name="in_date_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
			               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
			&nbsp;至&nbsp;
			 <@s.textfield id="txtendDate" name="out_date_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
			               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
			               </td>
		</tr>
		<tr>
			<td class="searchDiv_td">申请用户名:</td>
			<td><@s.textfield name="user_name_s" maxLength="50"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">审核状态:</td>
			<td>
			<select name="audit_state_s" style="width:70px;">
				<option value="" selected>请选择
				<#list infoStateList as infoState>
					<#if infoState.para_value?if_exists='0'>
				       <option value="${infoState.para_value?if_exists}">${infoState.para_key?if_exists}
				    <#elseif infoState.para_value?if_exists='1'>
				       <option value="${infoState.para_value?if_exists}">${infoState.para_key?if_exists}
				    <#elseif infoState.para_value?if_exists='2'>
				       <option value="${infoState.para_value?if_exists}">${infoState.para_key?if_exists}
				    </#if>
    			</#list>
			</select>
			</td>
		</tr>
		<tr>
			<td class="searchDiv_td">审核用户名:</td>
			<td><@s.textfield name="audit_user_s" maxLength="50"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">审核时间:</td>
			<td><input id="audit_date_s" name="audit_date_s"  type="text" class="Wdate" readonly="true"
				   onclick="WdatePicker({maxDate:'#F{$dp.$D(\'audit_date_end_s\')}',readOnly:true})" />&nbsp;至&nbsp;
				   <input id="audit_date_end_s" name="audit_date_end_s"  type="text" class="Wdate" readonly="true"
				   onclick="WdatePicker({minDate:'#F{$dp.$D(\'audit_date_s\')}',readOnly:true})" /></td>
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
