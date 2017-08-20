					       	 <div class="showtitle"><b>基本设置</b><span><img class='ltip' src="/include/common/images/light.gif" alt="修改设置后,请点击更新缓存" />[修改参数设置后,请点击<a href="###" onclick="renewload();">更新缓存</a>]</span></div>
							       	 <table width="100%;" class = "sztable">   
							       	  <#list sysconfigList as sys>    
							       	   <#if sys.var_name='cfg_cnzz'>
								           <tr>
								           		<td class="sort">
								           		<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
								           		</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									                 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textarea id="unified_title" name="sysconfig.var_value" cssStyle="width:430px;height: 50px;" cssClass="input"  maxlength="800" value="${sys.var_value?if_exists}"/>
									             	 <br class="clear" />
									             </td>
								           </tr>
							           </#if> 
							            <#if sys.var_name='cfg_cnzz2'>
								           <tr>
								           		<td class="sort">
								           		<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
								           		</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									                 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textarea id="unified_title" name="sysconfig.var_value" cssStyle="width:430px;height: 50px;" cssClass="input"  maxlength="800" value="${sys.var_value?if_exists}"/>
									             	 <br class="clear" />
									             </td>
								           </tr>
							           </#if> 
							           
							           
						       	 	   <#if sys.var_name='cfg_webname'>
								           <tr>
								           	<td class="sort">
								           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
								           	</td>
								             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
								             <td class="rtd">
								             	 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
								             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
								            	<font color="#D4D0C8"></font>
								             </td>
								           </tr>
							           </#if>
								       <#if sys.var_name='cfg_basehost'>
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
							           <#if sys.var_name='cfg_mobiledomain'>
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
							           <#if sys.var_name='cfg_powerby'>
								           <tr>
								           		<td class="sort">
								           		<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
								           		</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									                 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textarea id="unified_title" name="sysconfig.var_value" cssStyle="width:430px;height: 50px;" cssClass="input"  maxlength="100" value="${sys.var_value?if_exists}"/>
									             	 <br class="clear" />
									             	 <font color="red">最多输入字数300字！</font>
									             </td>
								           </tr>
							           </#if> 
							           <#if sys.var_name='cfg_beian'>
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
							           <#if sys.var_name='cfg_phone'>
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
							           <#if sys.var_name='cfg_fax'>
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
							           <#if sys.var_name='cfg_maxaddressnumber'>
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
							            <#if sys.var_name='cfg_default_search'>
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
							            <#if sys.var_name='cfg_Cautionstock'>
								           <tr>
								           <td class="sort">
								           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
								           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									                 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input" cssStyle="width:200px;"  maxlength="100" value="${sys.var_value?if_exists}"/>(件)
									             </td>
								           </tr>
							           </#if> 
							             <#if sys.var_name='cfg_description'>
									           <tr>
									           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textarea name="sysconfig.var_value" value="${sys.var_value?if_exists}" cssClass="input" rows="3" cssStyle="width:400px;height:90px;"/>
									            	<font color="#D4D0C8"></font>
									             </td>
									           </tr>
								       </#if>
								       <#if sys.var_name='cfg_keywords'>
									           <tr>
									           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textarea name="sysconfig.var_value" value="${sys.var_value?if_exists}" cssClass="input" rows="3" cssStyle="width:400px;height:90px;"/>
									            	<font color="#D4D0C8"></font>
									             </td>
									           </tr>
								       </#if>
								       <#if sys.var_name='cfg_webtitle'>
									           <tr>
									           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input"  maxlength="200" cssStyle="width:400px;" value="${sys.var_value?if_exists}" />
									            	<font color="#D4D0C8"></font>
									             </td>
									           </tr>
								       </#if>
								        <#if sys.var_name='cfg_andorid_url'>
									           <tr>
									           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input" 
									             	  maxlength="200" cssStyle="width:400px;" value="${sys.var_value?if_exists}" />
									            	<font color="#D4D0C8"></font>
									             </td>
									           </tr>
								       </#if>
								        <#if sys.var_name='cfg_ios_url'>
									           <tr>
									           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input" 
									             	  maxlength="200" cssStyle="width:400px;" value="${sys.var_value?if_exists}" />
									            	<font color="#D4D0C8"></font>
									             </td>
									           </tr>
								       </#if>
								         <#if sys.var_name='android_downcount'>
									           <tr>
									           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input" 
									             	  maxlength="200" cssStyle="width:400px;" value="${sys.var_value?if_exists}" />
									            	<font color="#D4D0C8"></font>
									             </td>
									           </tr>
								       </#if>
								         <#if sys.var_name='ios_downcount'>
									           <tr>
									           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input" 
									             	  maxlength="200" cssStyle="width:400px;" value="${sys.var_value?if_exists}" />
									            	<font color="#D4D0C8"></font>
									             </td>
									           </tr>
								       </#if>
							        </#list>
						           </table>
					     