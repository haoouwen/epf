<#if auditList?size gt 0>
	<link href="/include/common/css/auditlist.css" rel="stylesheet" type="text/css" />  

	<div class="auditdiv"  <#if auditList?size gt 6>style="height:200px;overflow-y:scroll;"</#if>>
		<table class="wwtable " cellspacing="1" cellpadding="8">
			       <tr>
		             <td class="table_name">信息操作流:</td>
		             <td>
		             		<div  class="auditlist">
			             		<table width="100%">
				             		<tr class="audit_title">
					             		<td class="audit_td1" width="20%">操作人</td>
					             		<td class="audit_td2" width="20%">状态</td>
					             		<td class="audit_td3" width="40%">理由</td>
					             		<td class="audit_td4" width="20%">操作时间</td>
				             		</tr>
				             		<#list auditList as audit>
				             			<tr class="audit_tr">
						             		<td align="right" style="padding-right:9%;">
						             		<#if audit_index=='0'>
						             			<img class="ltip" alt="最后的操作人" src="/include/common/images/lastu.png">
						             		</#if>
						             		${audit.user_name?if_exists}
						             		</td>
								            <td  align="center">
							             		<#list infoStateList as infoState>
								    				<#if audit.info_state?if_exists==infoState.para_value>
								    					<#if infoState.para_value?if_exists=='0'>
													        <font class="redcolor">${infoState.para_key?if_exists}</font>
													    <#elseif infoState.para_value?if_exists=='2'>
													        <font class="bluecolor">${infoState.para_key?if_exists}</font>
								    					<#elseif infoState.para_value?if_exists=='1'>
													        <font class='greencolor'>${infoState.para_key?if_exists}</font>
														<#elseif infoState.para_value?if_exists=='3'>
														    <font class="redcolor">${infoState.para_key?if_exists}</font>
													    </#if>
												    </#if>
								    			</#list>
						             		</td>
						             		<td>${audit.no_reason?if_exists}</td>
						             		<td  align="center">
						             		<#if audit.in_date?if_exists!=null && audit.in_date!="">
						             				${audit.in_date?if_exists?string("yyyy-MM-dd HH:mm:ss")}
						             		</#if>
						             		</td>
				             			</tr>
				             		</#list>
			             		</table>
			             	</div>
		             </td>
		           </tr>
		</table>
	</div>
</#if>