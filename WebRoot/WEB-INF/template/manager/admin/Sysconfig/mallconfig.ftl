									 <div class="showtitle">
								 		 <b>图片管理</b><span><img class='ltip' src="/include/common/images/light.gif" alt="修改设置后,请点击更新缓存" />[修改参数设置后,请点击<a href="###" onclick="renewload();">更新缓存</a>]</span>
									 </div>
							       	 <table width="100%;" class = "sztable">   
							       	  <#list sysconfigList as sys>    
								           <#if sys.var_name='cfg_logourl'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
								             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
								             <td class="rtd">
								             		 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									              <table border="0" cellpadding="0" cellspacing="0">
								             		<tr>
								             			<td style="padding:0px;">
								             			    <div id="fileQueue_index" style="display:none;"></div>
									              			  <@s.textfield name="sysconfig.var_value" id="uploadresult_index" cssClass="input" cssStyle="width:300px;" value="${sys.var_value?if_exists}"/>
								             			</td>
								             			<td style="padding-left:3px;">
								             				<input type="file" name="uploadifyfile" id="uploadifyfile_index"/>
								             			</td>
								             			<td style="padding-left:3px;">
								             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult_index');"/>
								             				<img class='ltip' src="/include/common/images/light.gif" alt="建议图片大小：宽232px高90px">
								             				<!--一个页面同时可以拥有多个上传图片控件，前面两个参数值必须不一样-->
									              			<script>sysUploadImg("uploadifyfile_index","uploadresult_index","fileQueue_index");</script>
								             			</td>
								             		</tr>
								             	</table>  
								             </td>
								           </tr>
							           </#if>
							           
							           <#if sys.var_name='cfg_memberlogo'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
								             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
								             <td class="rtd">
								             		 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									              <table border="0" cellpadding="0" cellspacing="0">
								             		<tr>
								             			<td style="padding:0px;">
								             			    <div id="fileQueue_member"></div>
									              			  <@s.textfield name="sysconfig.var_value" id="uploadresult_member" cssClass="input" cssStyle="width:300px;" value="${sys.var_value?if_exists}"/>
								             			</td>
								             			<td style="padding-left:3px;">
								             				<input type="file" name="uploadifyfile" id="uploadifyfile_member"/>
								             			</td>
								             			<td style="padding-left:3px;">
								             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult_member');"/>
								             				<img class='ltip' src="/include/common/images/light.gif" alt="建议图片大小：宽236px高90px">
									              			<script>sysUploadImg("uploadifyfile_member","uploadresult_member","fileQueue_member");</script>
								             			</td>
								             		</tr>
								             	</table>  
								             </td>
								           </tr>
							           </#if>
							           <#if sys.var_name='cfg_syslogo'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
								             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
								             <td class="rtd">
								             		 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									              <table border="0" cellpadding="0" cellspacing="0">
								             		<tr>
								             			<td style="padding:0px;">
								             			    <div id="fileQueue_sys"></div>
									              			  <@s.textfield name="sysconfig.var_value" id="uploadresult_sys" cssClass="input" cssStyle="width:300px;" value="${sys.var_value?if_exists}"/>
								             			</td>
								             			<td style="padding-left:3px;">
								             				<input type="file" name="uploadifyfile" id="uploadifyfile_sys"/>
								             			</td>
								             			<td style="padding-left:3px;">
								             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult_sys');"/>
								             				<img class='ltip' src="/include/common/images/light.gif" alt="建议图片大小：宽232px高128px">
									              			<script>sysUploadImg("uploadifyfile_sys","uploadresult_sys","fileQueue_sys");</script>
								             			</td>
								             		</tr>
								             	</table>  
								             </td>
								           </tr>
							           </#if>
							           
							           <#if sys.var_name='cfg_nopic'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
								             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
								             <td class="rtd">
								             		 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									              <table border="0" cellpadding="0" cellspacing="0">
								             		<tr>
								             			<td style="padding:0px;">
								             			    <div id="fileQueue_defaul"></div>
									              			  <@s.textfield name="sysconfig.var_value" id="uploadresult_defaul" cssClass="input" cssStyle="width:300px;" value="${sys.var_value?if_exists}"/>
								             			</td>
								             			<td style="padding-left:3px;">
								             				<input type="file" name="uploadifyfile" id="uploadifyfile_defaul"/>
								             			</td>
								             			<td style="padding-left:3px;">
								             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult_defaul');"/>
								             				<img class='ltip' src="/include/common/images/light.gif" alt="建议图片大小：宽154px高154px"/>
									              			<script>sysUploadImg("uploadifyfile_defaul","uploadresult_defaul","fileQueue_defaul");</script>
								             			</td>
								             		</tr>
								             	</table>  
								             </td>
								           </tr>
							           </#if>
							           
							           <#if sys.var_name='cfg_webappdefaultpic'>
								           <tr>
								           <td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
								             <td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
								             <td class="rtd">
								             		 <@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
									              <table border="0" cellpadding="0" cellspacing="0">
								             		<tr>
								             			<td style="padding:0px;">
								             			    <div id="fileQueue_webappdefaul"></div>
									              			  <@s.textfield name="sysconfig.var_value" id="uploadresult_webappdefault" cssClass="input" cssStyle="width:300px;" value="${sys.var_value?if_exists}"/>
								             			</td>
								             			<td style="padding-left:3px;">
								             				<input type="file" name="uploadifyfile" id="uploadifyfile_webappdefault"/>
								             			</td>
								             			<td style="padding-left:3px;">
								             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult_webappdefault');"/>
								             				<img class='ltip' src="/include/common/images/light.gif" alt="建议图片大小：宽300px高300px"/>
									              			<script>sysUploadImg("uploadifyfile_webappdefault","uploadresult_webappdefault","fileQueue_webappdefaul");</script>
								             			</td>
								             		</tr>
								             	</table>  
								             </td>
								           </tr>
							           </#if>
							           <#if sys.var_name='cfg_twoDimension'>
											<tr>
											<td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
												<td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
													<td class="rtd"><@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td style="padding:0px;">
											  						<div id="fileQueue_two"></div>
																	<@s.textfield name="sysconfig.var_value" id="uploadresult_two" cssClass="input" cssStyle="width:300px;" value="${sys.var_value?if_exists}"/>
																</td>
																<td style="padding-left:3px;">
																<input type="file" name="uploadifyfile" id="uploadifyfile_two"/>
																</td>
																<td style="padding-left:3px;">
																<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult_two');"/>
																<img class='ltip' src="/include/common/images/light.gif" alt="建议图片大小：宽80px高80px">
																<script>sysUploadImg("uploadifyfile_two","uploadresult_two","fileQueue_two");</script>
																</td>
								             				</tr>
								             		</table>  
								             	   </td>
								           </tr>
							           </#if>
							           
							            <#if sys.var_name='cfg_sysPageLogo'>
											<tr>
											<td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
												<td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
													<td class="rtd"><@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td style="padding:0px;">
											  						<div id="fileQueue_sysPage"></div>
																	<@s.textfield name="sysconfig.var_value" id="uploadresult_sysPage" cssClass="input" cssStyle="width:300px;" value="${sys.var_value?if_exists}"/>
																</td>
																<td style="padding-left:3px;">
																<input type="file" name="uploadifyfile" id="uploadifyfile_sysPage"/>
																</td>
																<td style="padding-left:3px;">
																<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult_sysPage');"/>
																<img class='ltip' src="/include/common/images/light.gif" alt="建议图片大小：宽137px高40px"/>
																<script>sysUploadImg("uploadifyfile_sysPage","uploadresult_sysPage","fileQueue_sysPage");</script>
																</td>
								             				</tr>
								             		</table>  
								             	   </td>
								           </tr>
							           </#if>

							           <#if sys.var_name='cfg_lazyPic'>
											<tr>
											<td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
												<td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
													<td class="rtd"><@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td style="padding:0px;">
											  						<div id="fileQueue_lazyPic"></div>
																	<@s.textfield name="sysconfig.var_value" id="uploadresult_lazyPic" cssClass="input" cssStyle="width:300px;" value="${sys.var_value?if_exists}"/>
																</td>
																<td style="padding-left:3px;">
																<input type="file" name="uploadifyfile" id="uploadifyfile_lazyPic"/>
																</td>
																<td style="padding-left:3px;">
																<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult_lazyPic');"/>
																<img class='ltip' src="/include/common/images/light.gif" alt="建议图片大小：宽150px高150px"/>
																<script>sysUploadImg("uploadifyfile_lazyPic","uploadresult_lazyPic","fileQueue_lazyPic");</script>
																</td>
								             				</tr>
								             		</table>  
								             	   </td>
								           </tr>
							           </#if>
							           <#if sys.var_name='cfg_public_number'>
											<tr>
											<td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
												<td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
													<td class="rtd"><@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td style="padding:0px;">
											  						<div id="fileQueue_public"></div>
																	<@s.textfield name="sysconfig.var_value" id="uploadresult_public" cssClass="input" cssStyle="width:300px;" value="${sys.var_value?if_exists}"/>
																</td>
																<td style="padding-left:3px;">
																<input type="file" name="uploadifyfile" id="uploadifyfile_public"/>
																</td>
																<td style="padding-left:3px;">
																<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult_public');"/>
																<img class='ltip' src="/include/common/images/light.gif" alt="建议图片大小：宽150px高150px"/>
																<script>sysUploadImg("uploadifyfile_public","uploadresult_public","fileQueue_public");</script>
																</td>
								             				</tr>
								             		</table>  
								             	   </td>
								           </tr>
							           </#if>
							            <#if sys.var_name='cfg_App_android'>
											<tr>
											<td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
												<td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
													<td class="rtd"><@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td style="padding:0px;">
											  						<div id="fileQueue_android"></div>
																	<@s.textfield name="sysconfig.var_value" id="uploadresult_android" cssClass="input" cssStyle="width:300px;" value="${sys.var_value?if_exists}"/>
																</td>
																<td style="padding-left:3px;">
																<input type="file" name="uploadifyfile" id="uploadifyfile_android"/>
																</td>
																<td style="padding-left:3px;">
																<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult_android');"/>
																<img class='ltip' src="/include/common/images/light.gif" alt="建议图片大小：宽150px高150px"/>
																<script>sysUploadImg("uploadifyfile_android","uploadresult_android","fileQueue_android");</script>
																</td>
								             				</tr>
								             		</table>  
								             	   </td>
								           </tr>
							           </#if>
							            <#if sys.var_name='cfg_App_iphone'>
											<tr>
											<td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
												<td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
													<td class="rtd"><@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td style="padding:0px;">
											  						<div id="fileQueue_iphone"></div>
																	<@s.textfield name="sysconfig.var_value" id="uploadresult_iphone" cssClass="input" cssStyle="width:300px;" value="${sys.var_value?if_exists}"/>
																</td>
																<td style="padding-left:3px;">
																<input type="file" name="uploadifyfile" id="uploadifyfile_iphone"/>
																</td>
																<td style="padding-left:3px;">
																<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult_iphone');"/>
																<img class='ltip' src="/include/common/images/light.gif" alt="建议图片大小：宽150px高150px"/>
																<script>sysUploadImg("uploadifyfile_iphone","uploadresult_iphone","fileQueue_iphone");</script>
																</td>
								             				</tr>
								             		</table>  
								             	   </td>
								           </tr>
							           </#if>
							            <#if sys.var_name='cfg_Phone_touch'>
											<tr>
											<td class="sort">
									           	<@s.textfield id="unified_title" name="sysconfig.sort_no" cssClass="sortinput" cssStyle="width:40px;"  maxlength="10" value="${sys.sort_no?if_exists}"/>
									           	</td>
												<td class="ctd">${sys.var_desc?if_exists}(${sys.var_name?if_exists}):</td>
													<td class="rtd"><@s.hidden cssClass="desc" name="sysconfig.var_id" value="${sys.var_id?if_exists}" />
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td style="padding:0px;">
											  						<div id="fileQueue_touch"></div>
																	<@s.textfield name="sysconfig.var_value" id="uploadresult_touch" cssClass="input" cssStyle="width:300px;" value="${sys.var_value?if_exists}"/>
																</td>
																<td style="padding-left:3px;">
																<input type="file" name="uploadifyfile" id="uploadifyfile_touch"/>
																</td>
																<td style="padding-left:3px;">
																<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult_touch');"/>
																<img class='ltip' src="/include/common/images/light.gif" alt="建议图片大小：宽150px高150px"/>
																<script>sysUploadImg("uploadifyfile_touch","uploadresult_touch","fileQueue_touch");</script>
																</td>
								             				</tr>
								             		</table>  
								             	   </td>
								           </tr>
							           </#if>
							           
							           
							        </#list>
						           </table>
					     