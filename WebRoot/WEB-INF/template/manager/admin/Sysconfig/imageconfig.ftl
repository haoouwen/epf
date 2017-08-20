					       	 <div class="showtitle"><b>水印管理</b><span><img class='ltip' src="/include/common/images/light.gif" alt="修改设置后,请点击更新缓存" />[修改参数设置后,请点击<a href="###" onclick="renewload();">更新缓存</a>]</span></div>
					       	 
							       <table width="100%;" class = "sztable">   
							       	  <#list sysconfigList as sys>    
							       	 	   <#if sys.var_name='cfg_imagemark'>
									           <tr>
									           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
												    <input type="hidden" class="desc" name="sysconfig.var_id"  value="${sys.var_id?if_exists}"/>
													<@s.radio  name="${sys.var_name?if_exists}" list=r"#{'0':'是','1':'否'}" value="${sys.var_value?if_exists}" onclick="setHiddenVal('${sys.var_id?if_exists}',this.value);"/>
													<@s.hidden cssClass="input" id="set_${sys.var_id?if_exists}"  value="${sys.var_value?if_exists}" />
									             </td>
									           </tr>
								           </#if>
								            <#if sys.var_name='cfg_goodsdetail_content'>
									           <tr>
									           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
												    <input type="hidden" class="desc" name="sysconfig.var_id"  value="${sys.var_id?if_exists}"/>
													<@s.radio  name="${sys.var_name?if_exists}" list=r"#{'1':'是','0':'否'}" value="${sys.var_value?if_exists}" onclick="setHiddenVal('${sys.var_id?if_exists}',this.value);"/>
													<@s.hidden cssClass="input" id="set_${sys.var_id?if_exists}"  value="${sys.var_value?if_exists}" />
									             </td>
									           </tr>
								           </#if>
								           <#if sys.var_name='cfg_imagemaricon'>
											<tr>
											<td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
												<td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
													<td class="rtd"><@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td style="padding:0px;">
											  						<div id="fileQueue_img"></div>
																	<@s.textfield name="sysconfig.var_value" id="uploadresult_img" cssClass="input" cssStyle="width:300px;" value="${sys.var_value?if_exists}"/>
																</td>
																<td style="padding-left:3px;">
																<input type="file" name="uploadifyfile" id="uploadifyfile_img"/>
																</td>
																<td style="padding-left:3px;">
																<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult_img');"/>
																<img class='ltip' src="/include/common/images/light.gif" alt="建议图片大小：宽220px高220px">
																<script>sysUploadImg("uploadifyfile_img","uploadresult_img","fileQueue_img");</script>
																</td>
								             				</tr>
								             		</table>  
								             	   </td>
								           </tr>
							           </#if>
								       <#if sys.var_name='cfg_imagemarktype'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	<input type="hidden" class="desc" name="sysconfig.var_id"  value="${sys.var_id?if_exists}"/>
													<@s.radio  name="${sys.var_name?if_exists}" list=r"#{'0':'是','1':'否'}" value="${sys.var_value?if_exists}" onclick="setHiddenVal('${sys.var_id?if_exists}',this.value);"/>
													<@s.hidden cssClass="input" id="set_${sys.var_id?if_exists}"  value="${sys.var_value?if_exists}" />
									             </td>
								           </tr>
							           </#if>   
							           
							           <#if sys.var_name='cfg_imagemarkdegree'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
								             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
								             <td class="rtd">
								             		 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input" cssStyle="width:200px;"  maxlength="100" value="${sys.var_value?if_exists}"/>
									             	 (只能输入数字)
								             </td>
								           </tr>
							           </#if>
							           
							           <#if sys.var_name='cfg_imagemarkalpha'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="noNum" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									                 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input" cssStyle="width:200px;"  maxlength="100" value="${sys.var_value?if_exists}"/>
									             </td>
								           </tr>
							           </#if> 
							           
							           <#if sys.var_name='cfg_imagemarkwidth'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input" cssStyle="width:200px;"  maxlength="100" value="${sys.var_value?if_exists}"/>
									             	 (只能输入数字)
									             </td>
								           </tr>
							           </#if> 
							           
							           <#if sys.var_name='cfg_imagemarkheight'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input" cssStyle="width:200px;"  maxlength="100" value="${sys.var_value?if_exists}"/>
									             	 (只能输入数字)
									             </td>
								           </tr>
							           </#if>
							           
							           <#if sys.var_name='cfg_imagemarktext'>
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
							           
							           <#if sys.var_name='cfg_imageforntsize'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
									             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
									             <td class="rtd">
									             	 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									             	 <@s.textfield id="unified_title" name="sysconfig.var_value" cssClass="input" cssStyle="width:200px;"  maxlength="100" value="${sys.var_value?if_exists}"/>
									             	 (只能输入数字)
									             </td>
								           </tr>
							           </#if>
							           
							        </#list>
						         </table>
					     