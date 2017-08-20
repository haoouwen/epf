   <!--获取系统的所有地区-->
		<div class='showareadiv1'>
			<div class='areatitle'>
				选择区域				
			</div>
			<table width="100%">
				<#list commparalist as comm>
					<tr  bgcolor="<#if comm_index%2==0>#FFFFFF<#else>#ECF4FF</#if>">
						<td class='td_width'>
								<input id='ah${comm.para_value?if_exists}' type="checkbox" class='cb_first' value="${comm.para_value?if_exists}"/>
								<span class='tdtitle'>${comm.para_key?if_exists}</span>
						</td>
						<td class='td_content'>
							<ul class='areaul1'>
								<#list areaList as area>
										<#if comm.para_value==area.area_have && area.area_level=='2'>	
											<li><input id="ah${comm.para_value?if_exists}_${area.area_id?if_exists}"  class='cb_two'  name="end_area"   type="checkbox" value="${area.area_id?if_exists}"/>
											<span><span id='gan_${area.area_id?if_exists}'>${area.area_name?if_exists}</span><font class='areanum'></font></span>
											<div class="clear"></div>	
												<div class="threediv">
													<ul class='threeul1'>
														<#list areaList as three>
															<#if three.up_area_id==area.area_id && three.area_level='3'>
																<#if three.area_name?length gt 6>
																	<#assign swidth = 'liwidth6'/>
																<#elseif three.area_name?length == 5>
																	<#assign swidth = 'liwidth5'/>
																<#elseif three.area_name?length == 4>
																	<#assign swidth = 'liwidth4'/>
																<#elseif three.area_name?length == 3>
																	<#assign swidth = 'liwidth3'/>
																<#elseif three.area_name?length == 2>
																	<#assign swidth = 'liwidth2'/>
																</#if>															
																<li class="${swidth?if_exists}"><input id="ah${comm.para_value?if_exists}_${area.area_id?if_exists}_${three.area_id?if_exists}" class='cb_three' name="end_area" type="checkbox" value="${three.area_id?if_exists}"/>
																<span id='gan_${three.area_id?if_exists}' class='g_areaname'>${three.area_name?if_exists}</span></li>																	
															</#if>
														</#list>																										
													</ul>
													<div class='clear'></div>
													<div class='close'>
														<input class="colsethree" type="button" value="关闭" />
													</div>												
												</div>
											</li>
										</#if>					
								</#list>
							</ul>
						</td>
					</tr>				
				</#list>
			</table>
			<div class='areabottom'>
				<@s.hidden name="is_insert" value="1"/>
				<@s.hidden id="sel_submit" value="1"/>
				<input type='button' value="确定" onclick="checkareaid();"/>&nbsp;
				<input type='button' onclick='colseShipCover();' value="取消"/>
			</div>
		</div>