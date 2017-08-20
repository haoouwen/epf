<html>
  <head>
    <title>审核余额调整</title>
  </head>
<body >
<@s.form action="/admin_Memberfundrun_update.action"  method="post" validate="true"  id="detailForm">
<div class="postion">
 当前位置：财务统计 >财务管理 > 审核余额调整
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
	        
			   <tr>
	             <td class="table_name" width="20%">会员名称</td>
	             <td class="table_right"width="80%">
                    <@s.label name="memberfundrun.user_name"></@s.label>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">条件金额</td>
	             <td class="table_right">
	             	<@s.label name="memberfundrun.fund_num"></@s.label>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">类型</td>
	             <td class="table_right">
	             	<#if memberfundrun.fund_type?if_exists=='0'><font class="bluecolor">收入</font><#else><font class="greencolor">支出</font></#if>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">审核状态<#if  memberfundrun.info_state !='1'&& memberfundrun.info_state !='2'><font color='red'>*</font></#if>	</td>
	             <td class="table_right">              
	             <#if  memberfundrun.info_state='1'>
	                 <font class="greencolor">审核通过</font>
	             <#elseif memberfundrun.info_state='2'>
	              <font class="bluecolor">审核未通过</font>
	              <#else>
     				<#list infoStateList as infoState>
					<#if infoState.para_value?if_exists='1'>
					   <input type="radio" name="info_state" value="${infoState.para_value?if_exists}" <#if memberfundrun.info_state='1'>checked</#if>>${infoState.para_key?if_exists}
				    <#elseif infoState.para_value?if_exists='2'>
				       <input type="radio" name="info_state" value="${infoState.para_value?if_exists}" <#if memberfundrun.info_state='1'>checked</#if>>${infoState.para_key?if_exists}
				    </#if>
				   </#list>
	               <@s.fielderror><@s.param>memberfundrun.info_state</@s.param></@s.fielderror>
	              </#if>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">事由</td>
	             <td class="table_right">
	             	<@s.label name="memberfundrun.reason"></@s.label>
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">审核理由</td>
	             <td class="table_right">
	             <#if  memberfundrun.info_state !='1'&& memberfundrun.info_state !='2'>
                   <@s.textarea name="reason" cssClass="txtinput" maxLength="100" cssStyle="width:260px;height:60px;"onkeyup="checkLength(this,100)"/>
                   <@s.fielderror><@s.param>reason</@s.param></@s.fielderror>
                 <#else>
                    ${(memberfundrun.reason)?if_exists}
                 </#if>
	             </td>
	           </tr>
	           <tr>
	             <td class="table_name" width="20%">申请人</td>
	             <td class="table_right"width="80%">
                    ${(memberfundrun.apply_user_name)?if_exists}
	             </td>
	           </tr>
	           <tr>
	             <td class="table_name" width="20%">申请时间</td>
	             <td class="table_right"width="80%">
                    ${(memberfundrun.apply_date)?if_exists}
	             </td>
	           </tr>
	             <#if  memberfundrun.info_state =='1'|| memberfundrun.info_state =='2'>
	            <tr>
	             <td class="table_name" width="20%">审核人</td>
	             <td class="table_right"width="80%">
                    ${(memberfundrun.audit_user_name)?if_exists}
	             </td>
	           </tr>
	           <tr>
	             <td class="table_name" width="20%">审核时间</td>
	             <td class="table_right"width="80%">
                    ${(memberfundrun.audit_date)?if_exists}
	             </td>
	           </tr>
	            </#if>
       </table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
 		   <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
 		   <@s.token/>
 		   <@s.hidden name="memberfundrun.fund_id"/>
		   ${listSearchHiddenField?if_exists}
		   <#if  memberfundrun.info_state !='1' && memberfundrun.info_state !='2'>
           <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       </#if>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Memberfundrun_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>   
</body>
</html>

