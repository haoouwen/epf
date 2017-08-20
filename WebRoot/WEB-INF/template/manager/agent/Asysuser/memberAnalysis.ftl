<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>会员统计</title>
<link href="/include/admin/css/backindex.css" rel="stylesheet" type="text/css" />
</head>
<body>
   <script type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
	<@s.form action="/agent_Asysuser_memberAnalysis.action" method="post" id="indexForm">
 <div class="crumb-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>
			<span class="crumb-name">经营统计</span><span class="crumb-step">&gt;</span>会员统计</div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                    <table class="search-tab">
                        <tr>
                            <th width="70">会员名:</th>
                            <td>
                            <@s.textfield name="user_name_s" cssClass="common-text" cssStyle="width:260px;"/>
                            </td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                            <td><input type="button" class="btn btn-primary btn2" onclick="searchShowDIV('searchDiv','300px','220px');"  value="高级查询"></td>
                            <td>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;${store_name}会员总数量：${totalAreaMember}</td>
                        </tr>
                    </table>
            </div>
        </div>
        <div class="result-wrap">
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>会员编号</th>
                            <th>会员名</th>
                            <th>手机</th>
                            <th>消费金额</th>
                            <th>注册时间</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        <#list agentMemberList as agentMember>
                        <tr align="center">
                            <td>${(agentMember.membernum)?if_exists}</td>
                            <td>${(agentMember.user_name)?if_exists}</td>
                            <td>${(agentMember.cellphone)?if_exists}</td>
                            <td>${(agentMember.total_amount)?if_exists}</td>
                            <td>${(agentMember.in_date)?if_exists}</td>
                            <td>
                            <#list infoStateList as infoState>
								  <#if agentMember.info_state?if_exists==infoState.para_value>
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
                            <td>
                            	<a onclick=linkToInfo("/agent_Asysuser_memberdetails.action","cust_id=${agentMember.cust_id?if_exists}");><img src="/include/common/images/view.gif" /></a>
						    	
                            </td>
                        </tr>
                        </#list>
                    </table>
                  <div class="pages">
				     ${pageBar?if_exists}
				   </div>
                </div>
        </div>
   </div>
   <@s.hidden  name="membernum_s" />
   <@s.hidden  name="email_s" />
   <@s.hidden  name="cellphone_s" />
   <@s.hidden  name="starttime_s" />
   <@s.hidden  name="endtime_s" />
</@s.form>

<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/agent_Asysuser_memberAnalysis.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td class="searchDiv_td">会员编号:</td>
			<td><@s.textfield name="membernum_s"  cssStyle="width:260px;" /></td>
		</tr>
		<tr>
		    <td class="searchDiv_td">会员名:</td>
           <td><@s.textfield name="user_name_s"/></td>
	</tr>
		<tr>
			<td class="searchDiv_td">邮箱:</td>
			<td><@s.textfield name="email_s"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">手机:</td>
			<td><@s.textfield name="cellphone_s"/></td>
		</tr>
		
		<tr>
			<td class="searchDiv_td">注册时间:</td>
			<td> <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
			               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"/> 
			&nbsp;至&nbsp;
			 <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
			               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"/></td>
		</tr>
		<tr>
			<td align="center" colspan="2" style="border:0px;">
				<input type="button" name="search" value="搜索" onclick="showSearchDiv('area_attr','','search_area_attr','','form_search_id');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			</td>
	   </tr>
		</table>
		<!--搜索框隐藏域存放-->
		</@s.form>
	</div>


</body>
</html>
