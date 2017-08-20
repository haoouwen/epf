<html>
  <head>
    <title>审核会员</title>
   
    <link rel="StyleSheet"  type="text/css" href="/include/admin/css/member.css"/>
    <script type="text/javascript" src="/include/js/admin/member.js"></script>	
  </head>
  <body>
  <@s.form action="/admin_Member_auditState.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->当前位置:会员管理 > 会员管理 > 审核会员
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 <tr>
             <td class="btitle" colspan="4" align="left">会员账号信息</td>
           </tr>
        <tr>
            <td class="table_name" style="width:330px;" >用户名:</td>
            <td colspan="3">
             	${memberuser.user_name?if_exists}
             </td>
           </tr>
           <tr>
             <td  class="btitle" colspan="4" align="left">会员基本信息</td>
           </tr>  
           <tr>
             <td class="table_name">会员名称:</td>
             <td colspan="3">
            <#if member.cust_name?if_exists!=null && member.cust_name?if_exists!=''>
	    		 ${member.cust_name?if_exists}
	    	<#else>
	    	 	-
	    	</#if>
             </td>
           </tr>
           <tr>
             <td class="table_name">会员头像:</td>
             <td colspan="3">
	                 <#list member.logo_path?if_exists?if_exists?split(',') as img>
		               <#if img?if_exists>
				               <a class="group" href="${img?if_exists}">
				              	 <img class="showimgstyle" src="${img?if_exists}"/>
				               </a> 
				               <#else>
				               <img src="/include/common/images/nopic.jpg"  width="110" height="110">
				               </#if> 
		            </#list>
             </td>
           </tr>
           <tr>
           
                <td class="table_name">
                       真实姓名:
                </td>
	            <td width="300px;">
	             	${memberuser.real_name?if_exists}
	            </td>
	            <td class="table_name">skype:</td>
	            <td>
	             	${memberuser.skype?if_exists}
	           </td>
            </tr>
                <td class="table_name" >性别:</font></td>
	             <td>
	             <#if memberuser.sex?if_exists="0" >
	                    男
	             <#else>
	                    女
	              </#if>
	               
	                
	             </td>
	             <td class="table_name">电子邮箱:</td>
		         <td >
	             	${memberuser.email?if_exists}
	             </td>
             
           </tr>
           <tr>
             <td class="table_name">公司电话:</td>
             <td>
                    ${memberuser.phone?if_exists}
             </td> 
              <td class="table_name">联系人QQ:</td>
             <td>
             ${memberuser.qq?if_exists}
             </td> 
           </tr>
            <tr>
             <td class="table_name">联系手机:</td>
             <td>
             		${memberuser.cellphone?if_exists}
             </td>
              <td class="table_name">微信:</td>
             <td>
                    ${memberuser.msn?if_exists}
             </td>
           </tr> 
           <tr>
             	<td class="table_name">审核状态<font color='red'>*</font></td>
             	<td >
		             <#list infoStateList as infoState>
						<#if infoState.para_value?if_exists='1'>
						   <input type="radio" name="member.info_state" value="${infoState.para_value?if_exists}" <#if member.info_state?if_exists==infoState.para_value>checked="true"</#if> onclick="checkinput(this)">${infoState.para_key?if_exists}
					    <#elseif infoState.para_value?if_exists='2'>
					       <input type="radio" name="member.info_state" value="${infoState.para_value?if_exists}" <#if member.info_state?if_exists==infoState.para_value>checked="true"</#if> onclick="checkinput(this)">${infoState.para_key?if_exists}
					    </#if>
					 </#list> 
		             <img class="ltip" src="/include/common/images/light.gif" alt="若审核未通过请点击审核未通过选项填写理由">
		             <@s.fielderror><@s.param>member.info_state</@s.param></@s.fielderror>
		             <br/>
	             	<@s.textarea name="reason"id="reason"  maxlength="100" cssStyle="width:260px;height:100px;display:none;" />
		        </td>
		        <td  class="table_name">买家级别</td>
	              <td colspan="3">
		              	<#list malllevelsetbuyList as buyLevel>
								<#if buyLevel.level_code?if_exists==member.buy_level?if_exists>
								   ${buyLevel.level_name?if_exists}
							    </#if>
					  </#list>
	             </td>
           </tr>                      
		</table>
		  <#include "/WEB-INF/template/manager/admin/Audithistory/auditlist.ftl"/>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
    	  <@s.hidden name="malllevelset.level_code"/>
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存" />
	       <@s.hidden name="member.cust_id" />
	       <input type="button" name="returnList" value="返回列表" onClick="linkToInfo('/admin_Member_auditList.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
  
  </body>
</html>

