					       	 <div class="showtitle"><b>会员设置</b><span><img class='ltip' src="/include/common/images/light.gif" alt="修改设置后,请点击更新缓存" />[修改参数设置后,请点击<a href="###" onclick="renewload();">更新缓存</a>]</span></div>
					       	 
							       <table width="100%;" class = "sztable">   
							       	  <#list sysconfigList as sys>
									       <#if sys.var_name='cfg_mb_notallow'>
									           <tr>
									           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
										             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
										             <td class="rtd">
										                 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
										             	 <@s.textarea id="unified_title" name="sysconfig.var_value" cssStyle="width:430px;height: 50px;" cssClass="input"  maxlength="100" value="${sys.var_value?if_exists}"/>
										             	 <br class="clear" />
										             </td>
									           </tr>
								           </#if> 
								           
								       <#if sys.var_name='cfg_mb_isaudit'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	  <input type="hidden" class="desc" name="sysconfig.var_id" id="set_${sys.var_name?if_exists}" value="${sys.var_id?if_exists}"/>
													<@s.radio  name="${sys.var_name?if_exists}" list=r"#{'0':'是','1':'否'}" value="${sys.var_value?if_exists}" onclick="setHiddenVal('${sys.var_id?if_exists}',this.value);"/>
													<@s.hidden cssClass="input" id="set_${sys.var_id?if_exists}"  value="${sys.var_value?if_exists}" />
									             </td>
								           </tr>
							           </#if> 
							            
							           <#if sys.var_name='cfg_selectCheckWay'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	  <input type="hidden" class="desc" name="sysconfig.var_id" id="set_${sys.var_name?if_exists}" value="${sys.var_id?if_exists}"/>
													<@s.radio  name="${sys.var_name?if_exists}" list=r"#{'0':'手机验证','1':'邮箱验证','3':'邮箱或者手机验证'}" value="${sys.var_value?if_exists}" onclick="setHiddenVal('${sys.var_id?if_exists}',this.value);"/>
													<@s.hidden cssClass="input" id="set_${sys.var_id?if_exists}"  value="${sys.var_value?if_exists}" />
									             </td>
								           </tr>
							           </#if>    
							       
							           <#if sys.var_name='cfg_vipname'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									                 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input" cssStyle="width:200px;"  maxlength="100" value="${sys.var_value?if_exists}"/>
									             </td>
								           </tr>
							           </#if> 
							           
							           <#if sys.var_name='cfg_initialpass'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									                 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input" cssStyle="width:200px;"  maxlength="100" value="${sys.var_value?if_exists}"/>
									             </td>
								           </tr>
							           </#if>  
							           
							           <#if sys.var_name='cfg_IsAuditMemberfund'>
								          <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	  <input type="hidden" class="desc" name="sysconfig.var_id" id="set_${sys.var_name?if_exists}" value="${sys.var_id?if_exists}"/>
													<@s.radio  name="${sys.var_name?if_exists}" list=r"#{'0':'是','1':'否'}" value="${sys.var_value?if_exists}" onclick="setHiddenVal('${sys.var_id?if_exists}',this.value);"/>
													<@s.hidden cssClass="input" id="set_${sys.var_id?if_exists}"  value="${sys.var_value?if_exists}" />
									             </td>
								           </tr>
							           </#if> 
							           
							           <#if sys.var_name='cfg_IsAuditChonzhi'>
								          <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	  <input type="hidden" class="desc" name="sysconfig.var_id" id="set_${sys.var_name?if_exists}" value="${sys.var_id?if_exists}"/>
													<@s.radio  name="${sys.var_name?if_exists}" list=r"#{'0':'是','1':'否'}" value="${sys.var_value?if_exists}" onclick="setHiddenVal('${sys.var_id?if_exists}',this.value);"/>
													<@s.hidden cssClass="input" id="set_${sys.var_id?if_exists}"  value="${sys.var_value?if_exists}" />
									             </td>
								           </tr>
							           </#if>
							           
							          <#if sys.var_name='cfg_defaultpaymentPC'>
								          <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	  <input type="hidden" class="desc" name="sysconfig.var_id" id="set_${sys.var_name?if_exists}" value="${sys.var_id?if_exists}"/>
													<@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
									             </td>
								           </tr>
							           </#if>
							           
							           <#if sys.var_name='cfg_defaultpaymentMobile'>
								          <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	  <input type="hidden" class="desc" name="sysconfig.var_id" id="set_${sys.var_name?if_exists}" value="${sys.var_id?if_exists}"/>
													<@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
									             </td>
								           </tr>
							           </#if>  
							          
							            
							        </#list>
						         </table>
					     