					       	 <div class="showtitle"><b>跨境通参数设置</b><span><img class='ltip' src="/include/common/images/light.gif" alt="修改设置后,请点击更新缓存" />[修改参数设置后,请点击<a href="###" onclick="renewload();">更新缓存</a>]</span></div>
					       	 
							       <table width="100%;" class = "sztable">   
							       	  <#list sysconfigList as sys>

							           
							           <#if sys.var_name='cfg_kjtappid'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	  <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
									             </td>
								           </tr>
							           </#if> 
							           
							            <#if sys.var_name='cfg_kjtsecretkey'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	  <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
									             </td>
								           </tr>
							           </#if> 
							           
							            <#if sys.var_name='cfg_channelid'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	  <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
									             </td>
								           </tr>
							           </#if> 
							           
							           <#if sys.var_name='cfg_depotid'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	  <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
									             </td>
								           </tr>
							           </#if> 
							              <#if sys.var_name='cfg_posttax'>
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
							           <#if sys.var_name='cfg_poundage'>
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
							           <#if sys.var_name='cfg_logistics'>
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
							           <#if sys.var_name='cfg_kjtdefaultmail'>
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
							             <#if sys.var_name='cfg_depotid_direct'>
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
							             <#if sys.var_name='cfg_logistics_direct'>
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
							        </#list>
						         </table>
					     