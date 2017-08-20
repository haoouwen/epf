					       	 <div class="showtitle"><b>索引设置</b><span><img class='ltip' src="/include/common/images/light.gif" alt="修改设置后,请点击更新缓存" />[修改参数设置后,请点击<a href="###" onclick="renewload();">更新缓存</a>]</span></div>
					       	 
							       <table width="100%;" class = "sztable">   
							       	  <#list sysconfigList as sys>
								       	  
							       	 	   <#if sys.var_name='cfg_solr_host'>
									           <tr>
									           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	<@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield  name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
									             </td>
									           </tr>
								           </#if>
								           
								       <#if sys.var_name='cfg_solr_name'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield  name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
								           </tr>
							           </#if>   
							           
							           <#if sys.var_name='cfg_solr_core_name'>
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
							            <#if sys.var_name='cfg_indexing'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	 <input type="hidden" class="desc" name="sysconfig.var_id" id="set_${sys.var_name?if_exists}" value="${sys.var_id?if_exists}"/>
													<@s.radio  name="${sys.var_name?if_exists}" list=r"#{'0':'Solr1','1':'Lucene'}" value="${sys.var_value?if_exists}" onclick="setHiddenVal('${sys.var_id?if_exists}',this.value);"/>
													<@s.hidden cssClass="input" id="set_${sys.var_id?if_exists}"  value="${sys.var_value?if_exists}" />
									             </td>
								           </tr>
							           </#if> 
							        </#list>
						         </table>
					     