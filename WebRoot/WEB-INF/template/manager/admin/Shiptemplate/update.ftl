<html>
  <head>
    <title>修改国内运费模版</title>
  	<script type="text/javascript" src="/include/common/js/common.js"></script>
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
    <script type="text/javascript" src="/include/admin/js/shiptemplete.js"></script> 
    <link rel="StyleSheet" href="/include/admin/css/shiptemplate.css" type="text/css" />   
    <#include "/WEB-INF/template/manager/admin/Shiptemplate/areadiv.ftl">
    <script type="text/javascript">
	$(document).ready(function(){ 
	   	 //所属地区的回选
      	 loadArea("${areaIdStr?if_exists}","");
	});
	</script>
  </head>
  <body>
<@s.form action="/admin_Shiptemplate_update.action" method="post" validate="true" id='shipfrom'>
<!--当前位置开始-->
<div class="postion">
当前位置:商城管理 > 物流管理 > 物流模板管理 > 修改国内运费模版
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		  <tr>
             <td class="table_name" width="200px">模板名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="shiptemplate.ship_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>shiptemplate.ship_name</@s.param></@s.fielderror>
             </td>
           </tr>
       
           <tr>
             <td class="table_name">计价方式:</td>
             <td>		             
             按重量
             <!--
             	<#if shiptemplate!=null && shiptemplate.valuation_mode?exists!=''>
             		<@s.radio name="shiptemplate.valuation_mode" list=r"#{'1':'按件数','2':'按重量','3':'按体积'}" value="${shiptemplate.valuation_mode?if_exists}"/>
				<#else>
					<@s.radio name="shiptemplate.valuation_mode" list=r"#{'1':'按件数','2':'按重量','3':'按体积'}" value="1"/>
				</#if>
             	<@s.fielderror><@s.param>shiptemplate.valuation_mode</@s.param></@s.fielderror>
                -->
              </td>
           </tr>
           <tr>
             <td class="table_name">发货地址<font color='red'>*</font></td>
             <td>
              <div id="areaDiv" style="margin-left:0px;"></div>   
             </td>
           </tr>
	     <tr>
             <td class="table_name" valign="top">运送方式:</td>
             <td>
				 <div class="elsearea">除指定地区外，其余地区的运费采用“默认运费”</div>
				 <@s.fielderror><@s.param>areasetval</@s.param></@s.fielderror>
				 <div>
				    <#list sendmodeList as sm>						    
				    <div>						    
					    	<div style="line-height:26px;">
					    			<#if (((shiptemplate.smode_attr)?if_exists?index_of("${sm.smode_id?if_exists}")) > -1 ) >
		                     	  		<#assign isCheck = 'checked'/>
		                     	    <#else>
		                     	 		<#assign isCheck = ''/>
		                     	  	</#if>    
					    		<input id="cb${sm.smode_id?if_exists}" name="smode_id" type="radio" value="${sm.smode_id?if_exists}" ${isCheck?if_exists}/>
					    		<input type="hidden" class='cs_num' name="smode_num" value='1'/>
					    		<input type="hidden" name="cs_smode_id" value='${sm.smode_id?if_exists}'/>
					    		<input type="hidden" name="smodeName" value='${sm.smode_name?if_exists}'/>
					    		<span>${sm.smode_name?if_exists}</span>					    	
					    	</div>
					    	<#if shiptemplate.valuation_mode=='1'>
			    				<#assign mName = '件'/>
			    				<#assign mUint = '件'/>
			    			<#elseif shiptemplate.valuation_mode=='2'>
			    				<#assign mName = '重'/>
			    				<#assign mUint = 'kg'/>
			    			<#elseif shiptemplate.valuation_mode=='3'>
			    				<#assign mName = '体积'/>
			    				<#assign mUint = 'm³'/>
			    			</#if>			
						    	<div class='modes_div' <#if isCheck=='checked'>style="display:block;"</#if> >
						    		<#if shipList!=null&&shipList?size gt 0 >
						    			<#assign s_start=0/>
						    			<#assign s_end=0/>
						    			<#assign s_len=0/>
						    			<#assign len_num =0/>
						    			<#list shipList as ship>							    	  	 	
								    	  <#if (sm.smode_id?if_exists==ship.c_mid?if_exists) && ship.d_ship=='0'>
							    	   		   <div class="defalut_mode">
							    	   		   	    <input type='hidden' name='end_area_str' class='ckb_end_area'  value="${ship.c_area?if_exists}"/>
									    			<input type='hidden' name="default_ship"   <#if ship.d_ship?if_exists!=''>value="${ship.d_ship?if_exists}"<#else>value="0" </#if>/>
									    			<span>默认运费：<input  name='first_weight' type="text" value="${ship.f_weight?if_exists}"/> <font class='fd_unit'>${mUint?if_exists}</font>内，</span>
									    			<span><input  name='first_price' type="text"  value="${ship.f_price?if_exists}"/> 元，</span>
									    			<span>每增加<input name='cont_weight' type="text" value="${ship.c_weight?if_exists}"/> <font class='fd_price'>${mUint?if_exists}</font>，</span>
									    			<span>增加运费<input  name='cont_price'  type="text" value="${ship.c_price?if_exists}"/> 元 </span>
								    		   </div>
								    		   <#if ship.c_num==1>
								    		   		<div id="setareadiv_${sm.smode_id?if_exists}"></div>
								    		   		<br/>						    		
											    	<span style="margin-left:20px;"><a onclick="setmodearea(this,${sm.smode_id?if_exists});">为指定地区城市设置运费</a></span>
								    		   </#if>
									      </#if>
						    			  	    							    	  	 	
							    	   		<#if (sm.smode_id?if_exists==ship.c_mid?if_exists) && ship.d_ship=='1'>			
   				
							    	   			<#if s_start==0 || s_start!=ship.c_num?if_exists>
							    	   			
							    	   				<#assign s_start = (ship.c_num)?if_exists/>										    	   				
							    	   					<div id="setareadiv_${sm.smode_id?if_exists}">						    	   											    	   												    	   				
							    	   					<!--找出过滤表的首索引与个数-->									    	   				
								    	   				<table id='other_${sm.smode_id?if_exists}' width='100%' class='wwtable' cellspacing='1' cellpadding='8'>
										    			   <tr style='background-color:#f8f8f8'>
											    			   <td align='center' width='39%'>运送到</td>
											    			   <td align='center' width='13%'>首${mName?if_exists}(${mUint?if_exists})</td>
											    			   <td align='center' width='13%'>首费(元)</td>
											    			   <td align='center' width='13%'>续${mName?if_exists}(${mUint?if_exists})</td>
											    			   <td align='center' width='13%'>续费(元)</td>
											    			   <td align='center' width='13%'>操作</td>
										    			   </tr>
								    			</#if>
								    				
								    											    			 
													<tr>
														<td><input type='hidden' name='end_area_str' class='ckb_end_area' value="${ship.c_area?if_exists}"/>
														<input type='hidden' name='default_ship' value='1'/>
														<div class='cot_area'>
														<#if ship.c_area_attr!=''>
															${ship.c_area_attr?if_exists}
														<#else>
															未设置地区
														</#if>																
														</div>
														<span class='cot_edit'><a onclick='editarea(this);'><img src="/include/common/images/bj.gif" title="编辑"/></a></span></td>
														<td align='center'><input name='first_weight' type='text' style='width:60px' value="${ship.f_weight?if_exists}"></td>
														<td align='center'><input name='first_price' type='text' style='width:60px' value="${ship.f_price?if_exists}"></td>
														<td align='center'><input name='cont_weight' type='text' style='width:60px' value="${ship.c_weight?if_exists}"></td>
														<td align='center'><input name='cont_price' type='text'  style='width:60px' value="${ship.c_price?if_exists}"></td>
														<td align='center'><a onclick="delarea_tr(this,'${sm.smode_id?if_exists}');">删除</a></td>
													</tr>
													
								    			 	<#if (ship_index+1)==(ship.c_size?if_exists)>
								    			 	 	   </table>	
								    			 	    </div>
								    			 	 	<#if ship.c_num gt 1>
								    			 	 		<br/>						    		
											    			<span style="margin-left:20px;"><a onclick="setmodearea(this,${sm.smode_id?if_exists});">为指定地区城市设置运费</a></span>
											    		</#if>
											    		</div>											    			 	 	
								    				</#if>		
						    			   	  </#if>
						    			 </#list>
					    			 </#if>							    				
						    	 </div>
					    	</div>
				    </div>
				 	</#list>	
				 	<@s.fielderror><@s.param>shiptemplate.smode_attr</@s.param></@s.fielderror>
				 </div>						 
             </td>
         </tr>  
		 
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
	 <@s.token/>    
	 ${listSearchHiddenField?if_exists}
	 <@s.hidden name="shiptemplate.cust_id"/>
	 <@s.hidden name="shiptemplate.ship_id"/>
	  <@s.hidden name="shiptemplate.valuation_mode"  value="2"/>
	 <input type="button" value="保存" onclick="shipsubmit();"/>
	 <@s.reset value="重置"/>
	 <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Shiptemplate_list.action','custId=${shiptemplate.cust_id?if_exists}');"/>
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>

</body>
</html>

