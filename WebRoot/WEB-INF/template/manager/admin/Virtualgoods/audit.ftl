<html>
  <head>
    <title>审核虚拟商品</title>
    <#include "/include/uploadInc.html">
	<script type="text/javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
	<script type="text/javascript" src="/include/common/js/jquery.easydrag.handler.js"></script>
	<script type="text/javascript" src="/include/admin/js/goodsaudit.js"></script>
	<script type="text/javascript" src="/include/common/js/jquery.alert.js"></script>
	<script type="text/javascript" src="/include/common/js/jqModal.js"></script>
	<script type="text/javascript" src="/include/common/js/tablePlugin.js"></script>
	<link href="/include/common/css/jqModal.css" rel="stylesheet" type="text/css" />  
	<link href="/include/admin/css/goods.css" rel="stylesheet" type="text/css" />  
	<link href="/include/common/css/alert.css" rel="stylesheet" type="text/css" /> 
	
  </head>
<body>

<@s.form action="/admin_Virtualgoods_auditState.action" method="post" validate="true" id="goodspost">
<!--当前位置开始-->
<div class="postion">
 	当前位置:商品管理 > 虚拟商品管理 > 审核虚拟商品 
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		 <!--详细页table的数据开始-->
		  <div class="oper_top"></div>	
  		 <div id="oper_div" class="oper_seo_div">      
					      
					      <div class="tabbar">
							    <ul>
							   		<li class="selected">商品总览</li>
							   		<li>基本信息</li>
							   		<li>详细介绍</li>
							   		<li>标签</li>
							   		<li>相关商品</li>
							   		<li>搜索引擎优化</li>
							    </ul>
					       </div>
					       
					       <div class="clear"></div>
					       
					       <div class="tabdiv"></div>
					       <div class="tabdiv">
					       	    <div class="showtitle"><b>基本信息</b></div>
					       	    
					       	    <div >
									   	 <table class="wwtable" cellspacing="1" cellpadding="8"  >    
									   	         
											<tr>
										             <td class="table_name fixheight">所属分类:</td>
										             
										             <td>
										             	${cat_name?if_exists}
										             	<@s.hidden name="goods_cat"/>
										             	<@s.hidden name="cat_name"/>
										             	<@s.fielderror><@s.param>goods.cat_attr</@s.param></@s.fielderror>
										             </td>
								             
										             <td rowspan="12" width="316" valign="top">
												             		<div class="list_pic">
												             		 	<table>
												             		 		<tr>
												             		 			<td width="50"><b>列表图:</b></td>
												             		 		</tr>
												             		 	</table>
												             		 	
																	    <div class="listImgDiv">
																	      	
																	      	<#if goods.list_img!=null && goods.list_img!=null>
												             	    	    <#list goods.list_img?if_exists?split(",") as img>
													             	   		
														             	   		<input type="hidden" value="${img?if_exists}" id="listimgID">
														             	   		<a href="${img?if_exists}" class="group cboxElement">
														             	   			<img src="${img?if_exists}"></a>
														             	   		<div>
														             	   		</div>
												             	   		  </#list>
												             	   		  <#elseif goods.list_img==null && goods.list_img==null>
												             	   		  <img id="listimgID" src="${cfg_nopic?if_exists}"/>
												             	   		  </#if>
																	      	
																	    </div>							 
												             		<!--显示图片-->	             		
												             	    <ul id="show_pic" class="show_pic">
												             	    	<#if goods.img_path!=null && goods.img_path!=null>
												             	    	<#list goods.img_path?if_exists?split(",") as img>
													             	   		<li>
														             	   		<input type="hidden" value="${img?if_exists}" name="gimg">
														             	   		<a href="${img?if_exists}" class="group cboxElement">
														             	   			<img src="${img?if_exists}"></a>
														             	   		<div>
														             	   		</div>
													             	   		</li>
												             	   		</#list>
												             	   		</#if>
												             	    </ul>   
											             	    </div>  
										               </div>  
										             </td>
								           </tr>	           
							                
							
								           <tr>
								             <td class="table_name  fixheight"  >商品名称:</td>
								             <td>
								            	<#if goods.goods_name?if_exists!=null && goods.goods_name?if_exists!=''>
									               ${goods.goods_name?if_exists}
								               <#else>
									               -
								               </#if>
								            	 
								             </td>
								           </tr>
							
								           
								           <tr>
								             <td class="table_name fixheight"  >商品编号:</td>
								             <td>
									             <@s.hidden name="goods.goods_no" id="goods_no"/>
									             <@s.label name="goods.goods_no" value=" ${goods.goods_no?if_exists}"/>
								             </td>
								           </tr>
								           
								            <tr>
								             <td class="table_name fixheight" >自定义分类:</td>
								             <td>
								             		${stack.findValue("@com.rbt.function.MembercatFuc@getCateNameByMap('${(goods.self_cat)?if_exists}')")}
								             </td>
								           </tr>
							               
							                
							                <#if goodsbrandList?if_exists?size gt 0 > 
									           <tr>
									             <td class="table_name fixheight"  >品牌:</td>
									             <td>
									             	<#list goodsbrandList as brand>
									             	 <#if goods.brand_id == brand.brand_id>
									             	 	${brand.brand_name?if_exists}
									             	 </#if>
									             	</#list>
									             </td>
									           </tr>
								             </#if>
							                
							                 <tr>
										        <td class="table_name fixheight">商品视频:</td>
										             <td>	
										             	<div id="flashQueue"></div>
										             	<table>
										             		<tr>
											             		<td>
											             		<@s.hidden name="goods.goods_video" id="goodsvideo"/>
											             		<@s.label name="goods.goods_video" value="${goods.goods_video?if_exists}"/>
											             		</td>
										             		</tr>
										             	</table>
										             	<script>uploadFlash("","uploadflash","goodsvideo","flashQueue");</script>
										             	<@s.fielderror><@s.param>goods.goods_video</@s.param></@s.fielderror>
										             </td>
										       </tr>
							                
							                
								           	<tr>
										        <td class="table_name fixheight">计量单位:</td>
										             <td>	
											             <@s.hidden name="goods.unit" id="m_unit"/>
									                     <@s.label name="goods.unit" value=" ${goods.unit?if_exists}"/>
										             </td>
										           </tr>
							              <tr>
										             <td class="table_name fixheight" >商品排序:</td>
										             <td>
										             	${goods.sort_order?if_exists}
										             </td>
										           </tr>  
										     <tr>
										             <td class="table_name fixheight">重量:</td>
										             <td>
										             	${goods.weight?if_exists}
										             </td>
										           </tr>	
							               <tr>
								             <td class="table_name fixheight" >是否免运费:</td>
								             <td>
								             <#if goods.is_ship?if_exists=='0'>
									                是
								             <#else>
									               否
								             </#if>
								             </td>
								           </tr>
							               
							               <tr>
								             <td class="table_name fixheight">上架管理:</td>
								             <td>
								            	<#list autoupList as atp>
								             <@s.hidden name="goods.is_up" id="is_shelves"/>
								             <#if atp.up_time?if_exists==null&&atp.up_time?if_exists=="">
									                否
								             <#else>
									              <#if autoupList?if_exists?size gt 0>
									                <table id="updowngoods_tr" class="wwtable" cellspacing="1" cellpadding="8" style="margin-top:3px;">   
									                		<#assign uptr=0/>                                   
														
																<tr id="uptr${uptr?if_exists}">
																	
																	<#assign uptrone=uptr+1/>
																	<#assign uptrtwo=uptr+2/>
																	<td class="table_name">上架时间:</td>
																	<#if atp_index==0>
																		<td style="width:250px;">${atp.up_time?if_exists?string("yyyy-MM-dd HH:mm:ss")}</td>
																	<#else>
																		<td style="width:250px;">
																			${atp.up_time?if_exists?string("yyyy-MM-dd HH:mm:ss")}</td>
																	</#if>
																	<td class="table_name">下架时间:</td>
																	<td style="width:250px;"><#if atp.down_time==null ||atp.down_time==''><#else>${atp.down_time?if_exists?string("yyyy-MM-dd HH:mm:ss")}</#if>
																    </td>
																</tr>
																<#assign uptr=uptr+2/>
															
		    										</table>
	    										</#if>
								             </#if>
								             </#list>
								             
								              <@s.hidden  name="up_goods_str" value="0"/>
								             </td>
								           </tr>
								           
								           <tr>
								             <td class="table_name fixheight">商品关键字:</td>
								             <td  valign="top"  style="padding-top:12px;">
													${goods.goods_wd?if_exists}
								             </td>
								           </tr>
									</table>          
								    <!--单个属性开始-->  
								    <#if is_more_attr=='1' || is_more_attr==null>    
									<div id="op_div">
										<#list goodsattrList as attr>
							              <table class="wwtable" cellspacing="1" cellpadding="8">
										           <tr>
										             <td class="table_name">市场价:</td>
										             <td>
 										                <@s.hidden name="market_price_str" id="market_val"/>
									                    <@s.label name="market_price_str" value=" ${attr.market_price_str?if_exists}"/>
										             </td>
										           </tr>
										           
									               <tr>
										             <td class="table_name">销售价:</td>
										             <td>
										                <@s.hidden name="sale_price_str" id="sale_val"/>
									                    <@s.label name="sale_price_str" value=" ${attr.sale_price_str?if_exists}"/>
										             </td>
										           </tr>
									                
										           <tr>
										             <td class="table_name">成本价:</td>
										             <td>
										                <@s.hidden name="cost_price_str" id="cost_val"/>
									                    <@s.label name="cost_price_str" value=" ${attr.cost_price_str?if_exists}"/>
										             </td>
										           </tr> 
										           
									               	 <tr>
										             <td class="table_name">货号:</td>
										             <td>
										                <@s.hidden name="goods_item_str" id="weight_val"/>
									                    <@s.label name="goods_item_str" value=" ${attr.goods_item_str?if_exists}"/>
										             </td>
										           </tr>	
							
										           
										           <tr>
										             <td class="table_name">库存:</td>
										             <td>
										                <@s.hidden name="stock_str" id="stock_val"/>
									                    <@s.label name="stock_str" value=" ${attr.stock_str?if_exists}"/>
										             	<@s.hidden name="specstr_str"/>
										             </td>
										           </tr>
							
							             	</table>
							            </#list>
						             </div>
						             </#if>
						       <!--单个属性结束-->     
						             
						             
						             
						       <!--多属性规格值开始-->     
						             <div id="op_sizeattr" style="width:100%; <#if is_more_attr=='0'>display:block;<#else>display:none;</#if>">
							             <table class="op_table" cellspacing="1" cellpadding="8" > 
									           <tr>
									           </tr>
									           <tr>
									               <td >
														<div id="selfsize" class="checkformNull">		
														 	<#if is_more_attr=='0'>		
																<table  class='op_zi_table' cellspacing='1' cellpadding='5' width='100%'>
																	<tr><th>货号</th>
																	
																  	<#list specselfNameList as sn>
																		<th>${sn.sname?if_exists}</th>
																	</#list>
																	
																	<th>库存</th><th>市场价</th><th>销售价</th><th>成本价</th><th>上架</th></tr>
																	
																	<#list goodsattrList as attr>
																		<tr id="linetr${attr_index?if_exists}">
																			<td  align="center">
																			<@s.hidden name="goods_item_str"/>
																			${attr.goods_item?if_exists}
																				<#list attr.specstr_str?if_exists?split(":") as sa>
																						<#assign already_size=''>
																						<#list specselfValueList as self>
																							<#if already_size?if_exists?index_of("${sa?if_exists}") == -1>
																								<#if sa?if_exists==self.self_size_id>
																									<td align="center">
																										<#if self.self_spec_img?if_exists!=null ||self.self_spec_img?if_exists!=''>
																												<img src="${self.self_spec_img?if_exists}"  class="setsizeimg">
																										<#else>									
																												${self.self_spec_value?if_exists}
																										</#if>		
																									 </td>	
																									  <#assign already_size=already_size+"${sa?if_exists}"+","/>			
																								</#if>		
																							</#if>	
																						</#list>
																				</#list>
								
																			
																			
																			<td  align="center"><input type="hidden" name="specstr_str"  value="${attr.specstr_str?if_exists}">
																					${attr.stock_str?if_exists}
																			</td>
																			<td  align="center">
																					${attr.market_price_str?if_exists}
																			</td>                         
																			<td  align="center">
																			  		 ${attr.sale_price_str?if_exists}
																			</td>
																			<td  align="center">
																			  		 ${attr.cost_price_str?if_exists}
																			</td>
																			<td  align="center">
																				<#if attr.up_goods_str?if_exists=='0'>
																							是
																						<input  name='up_goods_str'  type='hidden' value='0'/>
																				<#else>
																						否	
																						<input  name='up_goods_str'  type='hidden' value='1'/>
																				</#if>					
																			</td>
																		 </tr>
																		
																	</#list>
																	</table>
															 </#if>	
														</div>
								 				   </td>
									           </tr>
								         </table>  
							         </div>
							     <!--多属性规格值结束--> 
							         
						             <div id="sizelist" class="sizelist">	
						             	 <div id="selsizelist" class="selsizelist">
						             	 	<p class="selsizelist_p">
						             	 		<b>1 选择规格项</b>
						             	 	</p>
						             	 	<ul class="selsizelist_ul">
						             	 		<li><span class="size_b">&nbsp;选择规格:</span></li>
						             	 		<#list specnameList as specname>
						             	 			<li>
						             	 			<input type="checkbox" value="${specname.spec_code?if_exists}"/>
						             	 			<input class="show_type" type="hidden" value="${specname.show_type?if_exists}"/>
						             	 			<span>${specname.sname?if_exists}</span>
						             	 			</li>
						             	 		</#list>	
						             	 		<li>
						             	 			<span class="size_b">
						             	 				<input type="button" value="选中规格" onclick="selsize();"/>
						             	 			</span>
						             	 		</li>
						             	 	</ul>
						             	 	<div class="clear"></div>
						             	 	<p class="selsizelist_p">
						             	 		<b>2 选择需要的规格值</b>
						             	 	</p>
						             	 	<div id="specnamediv">
												 <div id="showsizevalmsg" class="showsizevalmsg">
												 	请先选择添加本商品需要的规格
												 </div>				
						             	 	</div>
						             	 	<p class="selsizelist_p">
						             	 		<b>3 自定义规格值或关联相册 » 保存</b>
						             	 	</p>
						             	 	<div id="alreadysize" class="alreadysize" >
								             	 <div id="showsizeval" class="showsizevalmsg">
												 	暂未选择规格值
												 </div>	
						             	 	</div>
						             	 	
						             	 </div>
						             	 
						             	 <div class="autocreate">
						             	 	<input type="button" value="生成所有的货品" onclick="selfmoresize();"/>
						             	 </div>
						             
						             </div>
				       	    
					       	    </div>
					       </div>

					       <div class="tabdiv">
								<div class="showtitle"><b>详细介绍</b></div>
								<div >
									 <@s.hidden name="goods.goods_detail" id="content"/>
					            	 ${goods.goods_detail?if_exists}
					       	    
					       	    </div>
					        </div>
					       
					       
							<div class="tabdiv">
								<div class="showtitle"><b>标签</b></div>
								<div  style="padding:10px 5px 0px 5px;">
									<#list goodsLabelList as lab>
										<#if goods.lab?if_exists?index_of("${lab.para_value}") gt -1>
											${lab.para_key}&nbsp;&nbsp;
										</#if>
									</#list>
									
					       	    </div>
					        </div>
					        
					        <div class="tabdiv">
								<div class="showtitle"><b>相关商品</b></div>
								<div >
					       	    	<table class="wwtable" cellspacing="1" cellpadding="8" >
										  <tr>
								             <td class="table_name">相关商品:</td>
								             <td>
								             	<div id="selralategoods" class="selralategoods">
								             		<table width="100%"><tr class="rg_title">
									             		<td class="span_td2">商品名称</td>
								             		</tr></table>
								             		<table id="selulrg" class="selulrg"  width="100%">
								             			<#list ralateList as rlt>
									             			<tr id="ralate_li${rlt.goods_id}">
																<input class="al_goods_id" type="hidden" value="${rlt.goods_id}">
																<td class="span_td2">${rlt.goods_name}</td>
															 </tr>
														 </#list>
								             		</table>
								             	</div>
								             </td>
								           </tr>
					       	    	</table>
					       	    </div>
					        </div>
					        
					        
					        <div class="tabdiv">
								<div class="showtitle"><b>搜索引擎优化</b></div>
								<div >
					       	    	<table class="wwtable" cellspacing="1" cellpadding="8" >
										  <tr>
								             <td class="table_name">SEO标题:</td>
								             <td>
								             	${goods.seo_title?if_exists}
								             </td>
								           </tr>
								           <tr>
								             <td class="table_name">SEO关键字:</td>
								             <td>
								                ${goods.seo_keyword?if_exists}
								             </td>
								           </tr>
								            <tr>
								             <td class="table_name">SEO描述:</td>
								             <td>
								               ${goods.seo_desc?if_exists}
								             </td>
								           </tr>					       	    	
					       	    	</table>
					       	    </div>
					        </div>
			         <table class="wwtable" cellspacing="1" cellpadding="8">
						 <tr>
							  <td class="table_name fixheight">审核状态<font color='red'>*</font></td>
							  <td >
							  <#list infoStateList as infoState>
								<#if infoState.para_value?if_exists='1'>
								   <input type="radio" name="info_state" value="${infoState.para_value?if_exists}" onclick="checkinput(this)">${infoState.para_key?if_exists}
							    <#elseif infoState.para_value?if_exists='2'>
							       <input type="radio" name="info_state" value="${infoState.para_value?if_exists}" onclick="checkinput(this)">${infoState.para_key?if_exists}
							    </#if>
			    			 </#list>&nbsp;&nbsp;
							       <img class="ltip" src="/include/common/images/light.gif" alt="若审核未通过请点击审核未通过选项填写理由">
							       <@s.fielderror><@s.param>info_state</@s.param></@s.fielderror>
							       <@s.fielderror><@s.param>goods.info_state</@s.param></@s.fielderror>
							       <br/>
							       <@s.textarea name="reason"id="reason"  maxlength="100" cssStyle="width:260px;height:100px;display:none;" />
							  </td>
						 </tr>
		 
		 <!--详细页table的数据结束-->
		</table>
		<#include "/WEB-INF/template/manager/admin/Audithistory/auditlist.ftl"/>
	</div>
		 <br/>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
   	       <@s.token/>    
	       
	       <@s.hidden  id="relate_goods"  name="goods.relate_goods"/>
	       <@s.hidden  id="ship_id"  name="goods.ship_id"/>
	       
	       <@s.hidden  name="goods.goods_id"/> 
	       <@s.hidden  name="goods.info_state"/> 
	       <@s.hidden  name="goods.user_id"/> 
	       <@s.hidden  name="goods.is_del"/>
	       
	       <@s.hidden  id="goods_detail"  name="goods_detail"/>    
	       <@s.hidden  id="sel_spec_name"  name="sel_spec_name"/>    
	       <@s.hidden  id="sel_spec_count"  name="sel_spec_count"/>    
	       <@s.hidden  id="is_more_attr"  name="is_more_attr"/> 
	       
	       <@s.hidden  id="self_goods_size_value"  name="self_goods_size_value"/>
	       <@s.hidden  id="self_goods_img_value"  name="self_goods_img_value"/>
	       <@s.hidden  id="self_goods_relate_img_value"  name="self_goods_relate_img_value"/>
	       <@s.hidden  id="self_goods_sort_value"  name="self_goods_sort_value"/>
	       <@s.hidden  id="self_size_id"  name="self_size_id"/>
	       
	       <@s.hidden  id="goods_up_str"  name="goods_up_str"/>
	       <@s.hidden  id="goods_down_str"  name="goods_down_str"/>
	       
	       <@s.hidden  id="ex_attr_value"  name="ex_attr_value"/>
	       
	       <@s.hidden  id="slef_para_value"  name="slef_para_value"/>
	       
	       <@s.hidden id="is_must_delete_spec" name="is_must_delete_spec" value="0"/>
	       
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Virtualgoods_auditlist.action','');"/>
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>

<!--图片选择框开始-->
<div id="imgbox" style='display:none;'>
	<ul id="showulimg"></ul>
	<div class="imgoper">
			 <input value="确定" onclick="ralateImg();"  type="button"/>
	</div>
</div>
<!--图片选择框结束-->

<!--保存进度条开始-->
<div id="loaderbar" class="jqmWindow loaderbar">
	<img src="/include/common/images/loaderbar.gif"/>
</div>
<!--保存进度条结束-->

<!--上架时间框开始-->
<div id="updowngoods"   class="jqmWindow updowngoods" >
	<p class="updown_mana">
		<b>商品上下架管理</b>
		<img src="/include/common/images/closecover.png"  style="float:right;margin:-3px -5px auto;cursor:pointer;" onclick="closeupdown();">
	</p>
	<div class="contain_up">
	 	<p><input type="button" value="新增上下架时间" onclick="addupdowntime();"/></p>
	 	<table id="updowngoods_tr" class="wwtable" cellspacing="1" cellpadding="8"  >   
	 		<#assign num=1/>
	 	 	<#list updownList as ud>
				<tr>
					<td class="table_name">上架时间:</td>
					<td><input type="text" value="${ud.up_time?if_exists}" class="Wdate upWdate" style="width:142px;" id="updown${num?if_exists}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'updown2\',{M:0,d:0})||$dp.$DV(\'2020-4-3\',{M:0,d:0})}'})"/></td>
					<td class="table_name">下架时间:</td>
					<td><input type="text" value="${ud.down_time?if_exists}" class="Wdate downWdate" style="width:142px;" id="updown${(num+1)?if_exists}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'updown1\',{M:0,d:0});}',maxDate:'2020-4-3'})"/></td>
				</tr>
				<#assign num=num+1/>
			</#list>
			
	    </table>	
	    <p class="updownoper">
	    	<input type="button" value="保存" onclick="saveupdown();"/>
	    	<input type="button" value="关闭" onclick="closeupdown();"/>
	    </p>
	</div>
</div>
<!--上架时间框结束-->
</body>
</html>

