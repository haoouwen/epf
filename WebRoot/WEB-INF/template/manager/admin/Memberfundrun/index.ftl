<html>
  <head>
    <title>余额调整审核列表</title>
  </head>
  <body>
<@s.form action="/admin_Memberfundrun_list.action" method="post" id="indexForm">

        <div class="postion">当前位置：财务统计 >财务管理 > 余额调整审核列表</div>
        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
                  <td >会员名称:</td><td><@s.textfield name="user_name_s" cssClass="search_input"/></td>
                   <td class="tdpad">审核状态:</td>
                   <td>
                  <select name="info_state_s" style="width:70px;">
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
                 <td><input type="submit" class="rbut" value="查询"></td>
               </tr>
             </table>
          
           </div>
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
			    
                 	<th width="20%">会员名称</th>
			    
                 	<th width="10%">调整金额</th>
			    
                 	<th width="10%">类型</th>
			    
                 	<th width="10%">状态</th>
                 	
                 	<th width="10%">申请人</th>
                 	
                 	<th width="10%">申请时间</th>
                 	
                 	<th width="10%">审核人</th>
                 	
                 	<th width="10%">审核时间</th>
			    
                    <th  width="5%">操作</th>
               </tr>
			    <#list memberfundrunList as memberfundrun>
				 <tr >
			         
				    
			         	<td align="center">${memberfundrun.user_name?if_exists}</td>
				    
			         	<td align="center">${memberfundrun.fund_num?if_exists}</td>
				    
			         	<td align="center"><#if memberfundrun.fund_type?if_exists=='0'><font class="bluecolor">收入</font><#else><font class="greencolor">支出</font></#if></td>
				    
			         	<td align="center">
				         	<#list infoStateList as infoState>
							<#if memberfundrun.info_state?if_exists==infoState.para_value>
								<#if infoState.para_value?if_exists='0'>
							       <font class="redcolor">${infoState.para_key?if_exists}</font>
							    <#elseif infoState.para_value?if_exists='2'>
							        <font class="bluecolor">${infoState.para_key?if_exists}</font>
							      <#elseif infoState.para_value?if_exists='1'>
							        <font class="greencolor">审核通过</font>
							    </#if>
							    <#break/>
						    </#if>
						 </#list>
			         	</td>
				    <td align="center">
				    <#if (memberfundrun.apply_user_name)?if_exists>
				    ${memberfundrun.apply_user_name?if_exists}
				    <#else>
				     --
				    </#if>
				    </td>
				    <td align="center">
				     <#if (memberfundrun.apply_date)?if_exists>
				    ${memberfundrun.apply_date?if_exists}
				    <#else>
				     --
				    </#if>
				    </td>
				    <td align="center">
				     <#if (memberfundrun.audit_user_name)?if_exists>
				    ${memberfundrun.audit_user_name?if_exists}
				    <#else>
				     --
				    </#if>
				    </td>
				    <td align="center">
				     <#if (memberfundrun.audit_date)?if_exists>
				    ${memberfundrun.audit_date?if_exists}
				    <#else>
				     --
				    </#if>
				   </td>
				    <td align="center"><a onclick="linkToInfo('/admin_Memberfundrun_view.action','memberfundrun.fund_id=${memberfundrun.fund_id?if_exists}');">
					  <img src="/include/common/images/audit.png" /></a></td>
				    
			       </tr>
			    </#list>
             </table>
           </div>
           <!--翻页-->
           <div class="pages">
             ${pageBar?if_exists}
           </div>
           <div class="clear"/>
           <!--翻页-->
        </div>
		<!--表单框隐藏域存放-->
		 <!-- <@s.hidden  name="xxx_s"/>-->
	   <!--表单框隐藏域存放--> 
</@s.form>

  </body>
</html>

