					       	 <div class="showtitle"><b>性能选项</b><span><img class='ltip' src="/include/common/images/light.gif" alt="修改设置后,请点击更新缓存" />[修改参数设置后,请点击<a href="###" onclick="renewload();">更新缓存</a>]</span></div>
					       	 
							       <table width="100%;" class = "sztable">   
							       	  <#list sysconfigList as sys>
								           
								       <#if sys.var_name='cfg_filterword'>
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
							           
							           <#if sys.var_name='cfg_autokeyword'>
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
							           
							           
							           <#if sys.var_name='cfg_qtzSendSubscribe'>
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
							           
							           <#if sys.var_name='cfg_tagcache'>
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
							           
							           <#if sys.var_name='cfg_luindex'>
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
							           
							           <#if sys.var_name='cfg_define_row'>
									           <tr>
									           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:300px;" value="${sys.var_value?if_exists}" />
									            	<font color="#D4D0C8"></font>
									             </td>
									           </tr>
								       </#if>
							           <#if sys.var_name='cfg_fastdfs'>
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
							             <#if sys.var_name='cfg_login_limittime'>
									           <tr>
									           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
									             	 (只能输入数字)
									             </td>
									           </tr>
								       </#if>
								        
								        <#if sys.var_name='cfg_login_limitnum'>
									           <tr>
									           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
									             	 (只能输入数字)
									             </td>
									           </tr>
								       </#if>
							        </#list>
						         </table>
					     