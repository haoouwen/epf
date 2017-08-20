<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>销售统计</title>
<link href="/include/admin/css/backindex.css" rel="stylesheet" type="text/css" />
</head>
<body>
   
	<@s.form action="/agent_Asysuser_salesAnalysis.action" method="post" id="indexForm">
 <div class="crumb-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>
			<span class="crumb-name">经营统计</span><span class="crumb-step">&gt;</span>销售统计</div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                    <table class="search-tab">
                        <tr>
                            <th width="70">会员名:</th>
                            <td>
                            <@s.textfield name="user_name_s" cssClass="common-text"/>
                            </td>
                             <th width="70">会员编号:</th>
                             <td><@s.textfield name="membernum_s" cssClass="common-text"/></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                            <td >&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;${store_name}销售总金额：<#if totalAreaAmountMap !=null && totalAreaAmountMap!="">${totalAreaAmountMap.get("totalAreaAmount")} </#if>元</td>
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
                            <th>消费金额（元）</th>
                            <th>状态</th>
                        </tr>
                        <#list agentMemberList as agentMember>
                        <tr align="center">
                            <td>${(agentMember.membernum)?if_exists}</td>
                            <td>${(agentMember.user_name)?if_exists}</td>
                            <td>${(agentMember.total_amount)?if_exists}</td>
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
                           
                        </tr>
                        </#list>
                    </table>
                  <div class="pages">
				     ${pageBar?if_exists}
				   </div>
                </div>
        </div>
   </div>
   
</@s.form>
</body>
</html>
