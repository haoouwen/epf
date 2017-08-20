<html>
<head>
	<title>审核实名认证</title>
	  <script src="/include/js/admin/pitureshow.js" type="text/javascript"></script>	
	  <script type="text/javascript">
	  $(document).ready(function(){    
         //图片展示
         firstaddimges("mypicid","addimg","100","100");
         firstaddimges("mypicid1","addimg1","100","100");
  	  });
	</script> 	
</head>

<body>
<@s.form action="/admin_Certification_auditState.action" method="post"   onsubmit="return noreasron('certification.info_state','noreasonvalue','2');">
<div class="postion">
  <!--当前位置-->
   当前位置:会员管理 > 企业会员 > 实名认证审核列表 > 审核实名认证
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		   
	         <tr>
		         <td  width="19%" class="table_name">公司名称:</td>
		         <td>
		         ${certification.cust_name?if_exists}
		         </td>
	         </tr>  
	         <tr>
		         <td width="19%" class="table_name">地区:</td>
	             <td>
	             ${certification.area_attr?if_exists}
	             </td>
	         </tr>  
	         <tr>
         		 <td width="19%" class="table_name">公司注册地址:</td>
                 <td>
                 ${certification.address?if_exists}
                 </td>
	         </tr>  
	         <tr>
		         <td width="19%" class="table_name">法定代表人:</td>
		         <td>
		          ${certification.corporate?if_exists}
		         </td>
	         </tr>  
	         <tr>
		         <td width="19%" class="table_name">企业类型:</td>
	             <td>
	                <@s.fielderror><@s.param></@s.param></@s.fielderror>
	                <#list cust_type_List as cust>
	                    <#if cust.para_value==certification.cust_type>
	                        ${cust.para_key?if_exists}
	                    </#if>
	                </#list>
	             </td>
	         </tr>  
	         <tr>
		         <td width="19%" class="table_name">注册资本:</td> 
		         <td>
			          ${certification.reg_money?if_exists}万	 
			         <#list commparaList as para>
			               <#if (para.para_value==certification.currency)>
				            	${para.para_key?if_exists}
				           </#if>			            
		             </#list>     
			                      
		         </td>
	         </tr>  
	         <tr>
		         <td width="19%" class="table_name">经营期限:</td>
		         <td>
		              ${certification.o_start_date?if_exists}~ ${certification.o_end_date?if_exists}
             	 </td>
	         </tr>  
	         <tr>
		         <td width="19%" class="table_name">经营范围:</td>
		         <td>
		         <#list catList as cat>
		               <#if (certification.class_attr?if_exists?index_of(cat.cat_id?if_exists)>-1)>
			            	<input type="checkbox" checked name="certification.class_attr" value="${cat.cat_id?if_exists}"/> 
			           <#else>
			                <input type="checkbox" name="certification.class_attr" value="${cat.cat_id?if_exists}"/> 
			           </#if>
			            <font>${cat.cat_name?if_exists}</font>
		         </#list>         
		         </td>
	         </tr>  
	         <tr>
	             <td width="19%" class="table_name">成立日期:</td> 
              	 <td>             	 
              	 	${certification.reg_date?if_exists}
              	 </td>
	         </tr>
	         <tr>
		         <td width="19%" class="table_name">是否年检:</td>
		         <td>
		             <#if certification.is_inspect=='1'>已年检<#else>未年检</#if>
		         </td>
		     </tr>
		     <tr>
		         <td width="19%" class="table_name">年检记录:</td>
		         <td>
		             <div>
			            <#list certification.ins_record?split(",") as s>	
			              ${s}已年检&nbsp;
			             </#list>
		             </div>
		         </td>
	         </tr> 
	         <tr>
		         <td width="19%" class="table_name">登记机关:</td> 
		         <td>
				         ${certification.reg_auth?if_exists}
		         </td>
	         </tr> 
	         <tr>
		         <td width="19%" class="table_name">营业执照复印件:</td>
		         <td>  
		          <@s.hidden name="certification.license_path" id="mypicid"/>   
                  <a class="group" href="${certification.license_path?if_exists}">
	                  	 <img class="showimgstyle" src="${certification.license_path?if_exists}"/>
	                </a> 
		         </td>
	         </tr> 
	         <tr>
		         <td width="19%" class="table_name">申请人姓名:</td>
		          <td>
			          ${certification.app_name?if_exists}
		          </td>
	         </tr> 
	         <tr>
	              <td width="19%" class="table_name">申请人部门:</td> 
                  <td>
                      ${certification.app_depart?if_exists}
                   </td>
	         </tr> 
	         <tr>
	        	  <td width="19%" class="table_name">申请人职位:</td> 
                  <td>
	                  ${certification.app_career?if_exists}
                  </td>
	         </tr> 
	         <tr>
	              <td width="19%" class="table_name">联系人手机:</td> 
                  <td>
	                  ${certification.app_contact?if_exists}
                  </td>
	         </tr> 
	         <tr>
	              <td width="19%" class="table_name">授权证明复印件:</td> 
                  <td>
                   <@s.hidden name="certification.auth_path" id="mypicid1"/>   
                  <a class="group" href="${certification.auth_path?if_exists}">
	                  	 <img class="showimgstyle" src="${certification.auth_path?if_exists}"/>
	                </a>
                  </td>
	         </tr> 	 
	         <tr>
             <td class="table_name">信息状态:</td>
             <td>
             <#if certification.info_state=='3'>
                已通过
             <#else>
             	<@s.radio name="certification.info_state" list=r"#{'1':'认证中','2':'被驳回','3':'已通过'}" onclick="getRadioValue('certification.info_state','reasonid','noreasonvalue','2');" />
             	<@s.fielderror><@s.param>supply.info_state</@s.param></@s.fielderror>
             </#if>	
             	
             </td>
           </tr>
            <#if certification.info_state?if_exists=='2'>
             <tr  id="reasonid">
             <td class="table_name">拒绝理由:<font color='red'>*</font></td>
             <td>
             	<@s.textarea name="certification.reason"  cssStyle="width:300px;height:80px;" id="noreasonvalue" onkeyup="checkLength(this,100);"/>
             	<@s.fielderror><@s.param>certification.reason</@s.param></@s.fielderror>
             </td>
           </tr>
           <#else>
            <tr  id="reasonid" style="display:none;">
             <td class="table_name">拒绝理由:<font color='red'>*</font></td>
             <td>
             	<@s.textarea name="certification.reason"  cssStyle="width:300px;height:80px;" id="noreasonvalue" onkeyup="checkLength(this,100);"/>
             	<@s.fielderror><@s.param>reason.no_reason</@s.param></@s.fielderror>
             </td>
           </tr>
           </#if>     
           
                      
           <#if certification.audit_user_id!=''>
               <tr>
	             <td class="table_name">审核人:</td>
	             <td>
	             	 ${sysuser.user_name?if_exists}
	             </td>
	           </tr>
               <tr>
	             <td class="table_name">审核时间:</td>
	             <td>
	             	 ${certification.audit_date?if_exists}
	             </td>
	           </tr>
               
           </#if> 
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
	     <@s.token/>
	     <@s.hidden  name="certification.cust_id" />
	     <#if certification.info_state!='3'>
              <@s.submit value="审核" />
         </#if>	     
	     <input type="button" name="returnList" value="返回列表"  onclick="linkToInfo('/admin_Certification_auditList.action','')" />  
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>
