<html>
  <head>
    <title>审核会员升级</title>
  </head>
  <body>
<@s.form action="/admin_Memberupgrade_auditstate.action" method="post" validate="true" id="modiy_form" onsubmit="return noreasron('memberupgrade.audit_state','noreason');">
<!--当前位置开始-->
<div class="postion">
当前位置:会员管理 > 会员管理 > 会员升级 > 审核会员升级 
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		  <tr>
             <td width="19%" class="table_name">当前级别:</td>
             <td>
               <#if memberupgrade.now_level?if_exists=='1'>个人普通会员
               <#elseif memberupgrade.now_level?if_exists=='2'>个人VIP会员
               <#elseif memberupgrade.now_level?if_exists=='3'>企业普通会员
               <#elseif memberupgrade.now_level?if_exists=='4'>企业VIP会员
               </#if>
             </td>
           </tr>
            <tr>
             <td class="table_name">申请级别:</td>
             <td>
             	<#if memberupgrade.apply_level?if_exists=='1'>个人普通会员
			    <#elseif memberupgrade.apply_level?if_exists=='2'>个人VIP会员
			    <#elseif memberupgrade.apply_level?if_exists=='3'>企业普通会员
			    <#elseif memberupgrade.apply_level?if_exists=='4'>企业VIP会员
			    </#if>
             </td>
           </tr>
            <tr>
             <td class="table_name">申请时间:</td>
             <td>
             	<@s.label name="memberupgrade.in_date.substring(0,19)" />
             </td>
           </tr>
            <tr>
             <td class="table_name">申请用户名:</td>
             <td>
             	<@s.label name="memberupgrade.user_name" />
             </td>
           </tr>
           <#if memberupgrade.audit_state?if_exists!='0'>
            <tr>
             <td class="table_name"> 审核时间:</td>
             <td>
             	<@s.label name="memberupgrade.audit_date.substring(0,19)"  />
             </td>
           </tr>
            <tr>
             <td class="table_name">审核用户名:</td>
             <td>
             	<@s.label name="memberupgrade.audit_user" />
             </td>
           </tr>
           </#if>
           <tr>
             <td class="table_name">审核状态:</td>
             <td>
             	<#list infoStateList as infoState>
					<#if infoState.para_value?if_exists='1'>
					   <input type="radio" name="memberupgrade.info_state" value="${infoState.para_value?if_exists}" onclick="getRadioValue('memberupgrade.audit_state','auditstate','noreason','2');">${infoState.para_key?if_exists}
				    <#elseif infoState.para_value?if_exists='2'>
				       <input type="radio" name="memberupgrade.info_state" value="${infoState.para_value?if_exists}" onclick="getRadioValue('memberupgrade.audit_state','auditstate','noreason','2');">${infoState.para_key?if_exists}
				    </#if>
				 </#list>
             </td>
           </tr>   
           <tr id="auditstate" style="display:<#if auditstateTip?if_exists=='2'><#else>none</#if>">
             <td class="table_name">拒绝<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="memberupgrade.reason" cssClass="txtinput" cssStyle="width:600px;" maxLength="300" id="noreason"/>
             	<@s.fielderror><@s.param>memberupgrade.reason</@s.param></@s.fielderror>
             </td>
           </tr>  
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
   <@s.token/>
       <@s.hidden name="memberupgrade.cust_id"/>
       <@s.hidden name="memberupgrade.grade_id"/>
       <@s.hidden name="memberupgrade.now_level"/>
       <@s.hidden name="memberupgrade.apply_level"/>
       <@s.hidden name="memberupgrade.in_date"/>
       <@s.hidden name="memberupgrade.user_name"/>
       <@s.hidden name="memberupgrade.audit_date"/>
       <@s.hidden name="memberupgrade.audit_user"/>  
       ${listSearchHiddenField?if_exists}     
       <@s.submit value="审核"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Memberupgrade_list.action','');" />
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>

  </body>
</html>