<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>久通区域代理/会员信息详情</title>
<link href="/include/admin/css/backindex.css" rel="stylesheet" type="text/css" />

<style>

</style>

</head>
<body>
 <div class="crumb-wrap">
      <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/agentindex.action">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">门店经营</a><span class="crumb-step">&gt;</span><span>会员信息详情</span></div>
        </div>
        <div class="result-wrap">
            
            <div class="result-content">
                 <span style="font-size:16px;">会员信息详情：</span>
            </div>
           
            <div class="result-content">
               
               <table class="insert-tab" width="100%">
		           <tr>
		             <td width="15%">会员名称:</td>
		             <td colspan="3">
		                ${(member.cust_name)?if_exists}
		             </td>
		           </tr>
		           <tr>
		             <td>会员头像:</td>
		             <td width="35%">
		                 <img src="${(member.logo_path)?if_exists}" width="100px" heigth="100px"/>
		             
		             </td>
		             
		              <td width="15%">会员生日:</td>
			              <td>
			               <#if (memberuser.birthday)?if_exists!=null && (memberuser.birthday)?if_exists!=''>
			                ${(memberuser.birthday)?if_exists}
			              <#else>
			                <font color="grey">暂无内容</font>
			              </#if>
			             </td>
		           </tr>  
		           </tr>
		           <tr>
		           
		                <td>
		                       真实姓名:
		                </td>
			            <td>
			             <#if (memberuser.real_name)?if_exists!=""||(memberuser.real_name)?if_exists!=Null>
			             ${(memberuser.real_name)?if_exists}
	                     <#else>
	                     <font color="grey">暂无内容</font>
	                     </#if> 		           
			            <td>skype:</td>
			            <td>
			                <#if (memberuser.skype)?if_exists!=""||(memberuser.skype)?if_exists!=Null>
			                ${(memberuser.skype)?if_exists}
			                <#else>
			                <font color="grey">暂无内容</font>
			                </#if>
			           </td>
		            </tr>
		                <td>性别:</td>
		             <td>
		                <#if (memberuser.memberuser.sex)?if_exists=='0'>
		                                                   先生
		                <#else>
		                                                  女士
		                </#if>
		               
		             </td>
			             <td>电子邮箱:</td>
			             <td>
			             <#if (memberuser.email)?if_exists!=""||(memberuser.email)?if_exists!=Null>
			             ${(memberuser.email)?if_exists}
			             <#else>
			             <font color="grey">暂无内容</font>
			             </#if>
		             </td>
		             
		           </tr>
		           <tr>
		             <td>电话:</td>
		             <td>
		              <#if (memberuser.phone)?if_exists!=""||(memberuser.phone)?if_exists!=Null>
		             ${(memberuser.phone)?if_exists}
		             <#else>
		             <font color="grey">暂无内容</font>
		             </#if>
		             </td> 
		              <td>联系人QQ:</td>
		             <td>
		             <#if (memberuser.qq)?if_exists!=""||(memberuser.qq)?if_exists!=Null>
		             ${(memberuser.qq)?if_exists}
		             <#else>
		             <font color="grey">暂无内容</font>
		             </#if>	
		             </td> 
		           </tr>
		            <tr>
		             <td>联系手机:</td>
		             <td>
		             <#if (memberuser.cellphone)?if_exists!=""||(memberuser.cellphone)?if_exists!=Null>
		             ${(memberuser.cellphone)?if_exists}
		             <#else>
		             <font color="grey">暂无内容</font>
		             </#if>
		             </td>
		              <td>联系人MSN:</td>
		             <td>
		             <#if (memberuser.msn)?if_exists!=""||(memberuser.msn)?if_exists!=Null>
		             ${(memberuser.msn)?if_exists}
		             <#else>
		             <font color="grey">暂无内容</font>
		             </#if>
		             </td>
		           </tr> 
		           <tr>
		             <td>买家级别:</td>
			              <td >
			               
			               <#list malllevelsetbuyList as malllevel>
								<#if (member.buy_level)?if_exists==(malllevel.level_code)?if_exists>
								 ${malllevel.level_name?if_exists}
							    </#if>
			    			 </#list> 
    
			             </td>
			             <td>状态:</td>                
			              <td>
				             <#list infoStateList as infoState>
								<#if (member.info_state)?if_exists==(infoState.para_value)?if_exists>
								 ${infoState.para_key?if_exists}
							    </#if>
			    			 </#list> 
				        </td>
		           </tr>
				 
				 
				 <!--详细页table的数据结束-->
				</table>
		              
            </div>
            
          
            
        </div>

         <div class="result-content">
              
		          <span style="padding-left:500px;"> <input class="btn btn6" onclick="linkToInfo('/agent_Asysuser_memberAnalysis.action','');" value="返回" type="button"></span>
		              
            </div>
        
   </div>
</body>
</html>
