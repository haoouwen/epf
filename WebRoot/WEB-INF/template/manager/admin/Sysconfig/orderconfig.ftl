					       	 <div class="showtitle"><b>订单设置</b><span><img class='ltip' src="/include/common/images/light.gif" alt="修改设置后,请点击更新缓存" />[修改参数设置后,请点击<a href="###" onclick="renewload();">更新缓存</a>]</span></div>
					       	 
							       <table width="100%;" class = "sztable">   
							       	  <#list sysconfigList as sys>

							           
							           <#if sys.var_name='cfg_publictimeout'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	  <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
									             	 (只能输入数字,单位分钟)
									             </td>
								           </tr>
							           </#if> 
							           
							            <#if sys.var_name='cfg_regoods_limt_date'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	  <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
									             	    (只能输入数字,单位天)
									             </td>
								           </tr>
							           </#if> 
							           
							            <#if sys.var_name='cfg_exchange_limt_date'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	  <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
									             	   (只能输入数字,单位天)
									             </td>
								           </tr>
							           </#if> 
							           
							           <#if sys.var_name='cfg_recycle_limt_date'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	  <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
									             	   (只能输入数字,单位天)
									             </td>
								           </tr>
							           </#if> 
							              <#if sys.var_name='cfg_sc_pointsrule'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input" cssStyle="width:200px;"  maxlength="100" value="${sys.var_value?if_exists}"/>
									             	% (只能输入数字)
									             </td>
								           </tr>
							           </#if>
							           
							              <#if sys.var_name='cfg_refund_limt_date'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	  <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
									             	   (只能输入数字,单位天)
									             </td>
								           </tr>
							           </#if> 
							            <#if sys.var_name='cfg_orderTime'>
								           <tr>
									           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
										             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
										             <td class="rtd">
										                 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
										             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
										             	  (只能输入数字,单位天)
										             	 <br class="clear" />
										             </td>
									           </tr>
							           </#if>
							           
							           
							           <#if sys.var_name='cfg_seaorderTime'>
								           <tr>
									           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
										             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
										             <td class="rtd">
										                 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
										             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
										             	  (只能输入数字,单位天)
										             	 <br class="clear" />
										             </td>
									           </tr>
							           </#if>							           
							           
							           <#if sys.var_name='cfg_assessTime'>
								           <tr>
									           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
										             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
										             <td class="rtd">
										                 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
										             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
										             	  (只能输入数字,单位天)
										             	 <br class="clear" />
										             </td>
									           </tr>
							           </#if>
							            <#if sys.var_name='cfg_maxpay'>
								           <tr>
									           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
										             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
										             <td class="rtd">
										                 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
										             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
										             	  (只能输入数字,单位元)
										             	 <br class="clear" />
										             </td>
									           </tr>
							           </#if>
							            <#if sys.var_name='cfg_catgoodsdate'>
								           <tr>
									           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
										             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
										             <td class="rtd">
										                 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
										             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
										             	  (只能输入数字,单位天)
										             	 <br class="clear" />
										             </td>
									           </tr>
							           </#if>
							             <#if sys.var_name='cfg_delete_cancelorder'>
								           <tr>
									           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
										             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
										             <td class="rtd">
										                 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
										             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input"  maxlength="50" cssStyle="width:200px;" value="${sys.var_value?if_exists}" />
										             	  (只能输入数字,单位月)
										             	 <br class="clear" />
										             </td>
									           </tr>
							           </#if>
							        </#list>
						         </table>
					     