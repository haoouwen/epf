<html>
  <head>
    <title>添加虚拟商品</title>
    <#include "/include/uploadInc.html">
    <script type="text/javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
	<script src="/wro/admin_goods_insert.js" type="text/javascript"></script>
	<link href="/wro/admin_goods_insert.css" rel="stylesheet" type="text/css"/>
  </head>
<body>
<@s.form id="goodspost" action="/admin_Virtualgoods_insert.action" method="post" validate="true" >
<div class="postion">当前位置:商品管理 > 虚拟商品管理 > 添加虚拟商品  </div>
<div class="rtdcont">
	<div class="rttable">
	   <div class="oper_top"></div>	
   		 
   		 
  		 <div id="oper_div" class="oper_seo_div">      
					      
					      <div class="tabbar">
							    <ul>
							   		<li class="selected">商品总览</li>
							   		<li>基本信息</li>
							   	<!--	<li>分销商价格</li>-->
							   		<li>详细介绍</li>
							   		<li>标签</li>
							   		<li>相关商品</li>
									<li>扩展属性</li>
									<li>参数组</li>
							    </ul>
					       </div>
					       
					       <div class="clear"></div>
					       
					       <div class="tabdiv"></div>
					       <div class="tabdiv">
					       	    <div class="showtitle"><b>基本信息</b></div>
					       	    
					       	    <div>
									   	 <table class="wwtable" cellspacing="1" cellpadding="8"  >    
									   	         
											<tr>
										             <td class="table_name fixheight">所属分类:</td>
										             
										             <td>
										             	${cat_name?if_exists}
										             	<@s.hidden name="goods_cat"/>
										             	<@s.hidden name="cat_name"/>
										             	<@s.fielderror><@s.param>goods.cat_attr</@s.param></@s.fielderror>
										             </td>
								             
										             <td rowspan="9" width="316" valign="top">
										             <div class="imgdiv">
												             	<div id="uploadQueue" class="uploadQueue">
												             	  	<div id="fileQueue"></div>
												             	</div>
												             	<div class="showlistimgdiv">
												             		<div class="pic_top">
																	    <table>
																	    	<tr>
																	    		<td><b>商品图片:</b></td>
																	    		<td><@s.hidden id="uploadresult" name="goods.img_path" cssStyle="width:145px;"/></td>
																	    		<td><input type="file" name="uploadifyfile" id="uploadifyfile" /></td>
																	    	</tr>
																	    </table>
																	    <div  class="clear"></div>	  
												                     	<script>uploadGoodsImageMulti();</script>
												             		</div>		  		
												             		<div class="list_pic">
												             		 	<table>
												             		 		<tr>
												             		 			<td colspan="3">注：列表页商品图片默认由系统自动生成.您也可以上传一张商品图片来覆盖.</td>
												             		 		</tr>
												             		 		<tr>
												             		 			<td width="50"><b>列表图:</b></td>
												             		 			<td><@s.textfield name="goods.list_img" id="uploadoneimage"  cssStyle="width:145px;"/></td>
												             		 			<td><input type="file" name="uploadifyfile1" id="uploadifyfile1" /></td>
												             		 			<script>uploadSingleGoodsImage("uploadifyfile1","uploadoneimage","fileQueue");</script>
												             		 		</tr>
												             		 	</table>
												             		 	
																	    <div class="listImgDiv">
																	      	<img id="listimgID" src="${cfg_nopic?if_exists}"/>	
																	    </div>							 
												             		</div>	
												             		<!--显示图片-->	             		
												             	    <ul id="show_pic" class="show_pic"></ul>   
											             	    </div>  
										               </div>  
										             </td>
								           </tr>	          
							                
							
								           <tr>
								             <td class="table_name  fixheight"  >商品名称<font color='red'>*</font></td>
								             <td>
								             	<@s.textfield id="goodsName" name="goods.goods_name" cssClass="input" cssStyle="width:360px;" onkeyup="checkLength(this,120);" 
							maxlength="50"/>
								             	<@s.fielderror><@s.param>goods.goods_name</@s.param></@s.fielderror>
								             </td>
								           </tr>
							
								           
								           <tr>
								             <td class="table_name fixheight"  >商品编号:</td>
								             <td>
								             	<@s.textfield id="goods_no" name="goods.goods_no" cssClass="input" onkeyup="checkLength(this,20);" maxlength="20"/>
								             	<@s.fielderror><@s.param>goods.goods_no</@s.param></@s.fielderror><font color="red">(随机生成,可自己修改)</font>
								             </td>
								           </tr>
							               
							                <#if goodsbrandList?if_exists?size gt 0 > 
									           <tr>
									             <td class="table_name fixheight"  >品牌:</td>
									             <td>
									             	 <@s.select  name="goods.brand_id" list="goodsbrandList" listValue="brand_name"  listKey="brand_id" headerKey=""  headerValue="请选择"/>    
									             	<@s.fielderror><@s.param>goods.brand_id</@s.param></@s.fielderror>
									             </td>
									           </tr>
							                </#if>
							                <tr>
										        <td class="table_name fixheight">商品视频:</td>
										             <td>	
										             	<div id="flashQueue"></div>
										             	<table>
										             		<tr>
											             		<td><@s.textfield id="goodsvideo" name="goods.goods_video" cssStyle="width:330px;"/></td>
											             		<td><input type="file" name="uploadflash" id="uploadflash" /></td>
											             		<td><img class="ltip" src="/include/common/images/light.gif" alt="如果引用外部网址,直接复制地址到商品视屏文本框"></td>
										             		</tr>
										             	</table>
										             	<script>uploadFlash("","uploadflash","goodsvideo","flashQueue");</script>
										             	<@s.fielderror><@s.param>goods.goods_video</@s.param></@s.fielderror>
										             </td>
										       </tr>
								           	<tr>
										        <td class="table_name fixheight">计量单位:</td>
										             <td>	
										                <@s.textfield id="m_unit" name="goods.unit" cssClass="input" onkeyup="checkLength(this,10);" maxlength="10"/>             	
										             	<@s.fielderror><@s.param>goods.unit</@s.param></@s.fielderror>
										             </td>
										           </tr>
							              <tr>
										             <td class="table_name fixheight" >商品排序:</td>
										             <td>
										             	<@s.textfield name="goods.sort_order" cssClass="input" onkeyup="checkLength(this,9);" value="100" maxlength="9"/>
										             	<@s.fielderror><@s.param>goods.sort_order</@s.param></@s.fielderror>
										             </td>
										           </tr>  
										     <tr>
										             <td class="table_name fixheight">重量:</td>
										             <td>
										             	<@s.textfield name="goods.weight" cssClass="input" onkeyup="checkFloat(this);" maxlength="6"/><span>克(g)</span>
										             	<@s.fielderror><@s.param>goods.weight</@s.param></@s.fielderror>
										             </td>
										           </tr>
										         <tr>
								             <td class="table_name "  style="height:auto;padding-top:30px;"  valign="top">商品关键字:</td>
								             <td  valign="top"  style="padding-top:12px;">
								             	<@s.textarea name="goods.goods_wd" cssClass="input" onkeyup="checkLength(this,100);" maxlength="100" 
							cssStyle="width:360px;height:80px;"/><br/>
								             	<font color="red" style="margin-top:6px;">仅用于在前台、后台筛选商品，多个关键词用半角竖线"|"分开</font>
								             	<@s.fielderror><@s.param>goods.goods_keyword</@s.param></@s.fielderror>
								             </td>
								           </tr>	
							               
							               <tr>
								             <td class="table_name fixheight">上架管理:</td>
								             <td colspan="2">
								                <@s.radio id="is_shelves" name="goods.is_up" list=r"#{'0':'是','1':'否'}" value="0"/>
								                &nbsp;&nbsp;<input type="button" value="开启自动上下架" onclick="addupdowntime();"/>
								                <table id="updowngoods_tr" class="wwtable" cellspacing="1" cellpadding="8" style="margin-top:3px;">   

	    										</table>
								             </td>
								           </tr>
								           
								          
									</table>          
								    
							    <!--单个属性开始-->  
								<div id="op_div">
								    <#if is_more_attr=='1' || is_more_attr==null>    
							              <table class="wwtable" cellspacing="1" cellpadding="8">
										           <tr>
										             <td class="table_name">市场价:</td>
										             <td>
										             	<@s.textfield id="market_val" name="market_price_str" cssClass="input" onkeyup="checkRMB(this);" maxlength="11"/>
										             	<font>格式0.00</font>
										             	<@s.fielderror><@s.param>goodsattr.market_price</@s.param></@s.fielderror>
										             </td>
										           </tr>
										           
									               <tr>
										             <td class="table_name">销售价<font color='red'>*</font></td>
										             <td>
										             	<@s.textfield  id="sale_val" name="sale_price_str" cssClass="input"  onkeyup="checkRMB(this);" maxlength="11"/>
										             	<font>格式0.00</font>
										             	<@s.fielderror><@s.param>goodsattr.sale_price</@s.param></@s.fielderror>
										             </td>
										           </tr>
									                
										           <tr>
										             <td class="table_name">成本价:</td>
										             <td>
										             	<@s.textfield  id="cost_val" name="cost_price_str" cssClass="input"   onkeyup="checkRMB(this);" maxlength="11"/>
										             	<font>格式0.00</font>;<span>前台不会显示，仅供后台使用</span>
										             	<@s.fielderror><@s.param>goodsattr.cost_price</@s.param></@s.fielderror>
										             </td>
										           </tr> 
										           
									               	 <tr>
										             <td class="table_name">货号:</td>
										             <td>
										             	<@s.textfield id="weight_val" name="goods_item_str" cssClass="input" onkeyup="checkLength(this,20);" maxlength="20"/>(若不填则与商品编号一致)
										             	<@s.fielderror><@s.param>goodsattr.weight</@s.param></@s.fielderror>
										             </td>
										           </tr>	
							
										           
										           <tr>
										             <td class="table_name">库存:</td>
										             <td>
										             	<@s.textfield id="stock_val"  name="stock_str" value="0" cssClass="input" onkeyup="checkDigital(this);"  maxlength="11"/>
										             	<@s.hidden name="specstr_str"/>
										             	<@s.hidden  name="up_goods_str" value="0"/>
										             	<@s.fielderror><@s.param>goodsattr.stock</@s.param></@s.fielderror>
										             </td>
										           </tr>
							
										           <tr>
										             <td class="table_name">规格:</td>
										             <td>
										                <input type="button" id="showsize" value="开启规格"/>
										             	<font>开启规格前先填写以上价格等信息，可自动复制信息到货品</font>
										             </td>
										           </tr>
							             	</table>
						             </#if>
					             </div>
						       <!--单个属性结束-->     
						             
						             
						             
						       <!--多属性规格值开始-->     
						             <div id="op_sizeattr" style="width:100%; <#if is_more_attr=='0'>display:block;<#else>display:none;</#if>">
							             <table class="op_table" cellspacing="1" cellpadding="8" > 
									           <tr>
									              <td  style="text-align:left;" width="100%">
														<input type="button" value="选择规格项" onclick="showgoodssizelist();">
														<input type="button" value="关闭规格" onclick="showOnegoods();">
												  </td>
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
																	
																	<th>库存</th><th>市场价</th><th>销售价<font color='red'>*</font></th><th>成本价</th><th>上架</th><th>操作</th></tr>
																	
																	<#list goodsattrList as attr>
																		<tr id="linetr${attr_index?if_exists}">
																			<td  align="center"><input type="text" style="width:100px;" name="goods_item_str" value="${attr.goods_item?if_exists}"></td>
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
																			<input type="text"  style="width:60px;" name="stock_str"  value="${attr.stock_str?if_exists}" onkeyup="checkDigital(this);"  maxlength="11"></td>
																			<td  align="center"><input type="text" style="width:60px;" name="market_price_str"  value="${attr.market_price_str?if_exists}" onkeyup="checkRMB(this);" maxlength="11"></td>                         
																			<td  align="center"><input type="text"   style="width:60px;" name="sale_price_str"  value="${attr.sale_price_str?if_exists}" onkeyup="checkRMB(this);" maxlength="11"></td>
																			<td  align="center"><input type="text"  style="width:60px;" name="cost_price_str"  value="${attr.cost_price_str?if_exists}" onkeyup="checkRMB(this);" maxlength="11"></td>
																			<td  align="center">
																				<#if attr.up_goods_str?if_exists=='0'>
																						<input type="checkbox"  checked="true"  class='goods_is_up' >	
																						<input  name='up_goods_str'  type='hidden' value='0'/>
																				<#else>
																						<input type="checkbox"   class='goods_is_up'   >	
																						<input  name='up_goods_str'  type='hidden' value='1'/>
																				</#if>					
																			</td>
																			<td  align="center"><input type="button" onclick="dellinetr(${attr_index?if_exists});" value="删除" style="width:60px;"></td>
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
					       	    	<@s.textarea id="content" cssClass="txtinput"/>
									<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
									<script type="text/javascript">
								     	var editor = CKEDITOR.replace( 'content');  
									</script>
				           			<@s.fielderror><@s.param>goods.goods_detail</@s.param></@s.fielderror>
					       	    </div>
					       	   	<#include "/WEB-INF/template/manager/admin/AutoFCK/fck.ftl">
					        </div>
					       
					       
							<div class="tabdiv">
								<div class="showtitle"><b>标签</b></div>
								<div  style="padding:10px 5px 0px 5px;">
									<@s.checkboxlist name="goods.lab" list="goodsLabelList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
					       	    </div>
					        </div>
					        
					        
					        <div class="tabdiv">
								<div class="showtitle"><b>相关商品</b></div>
								<div >
					       	    	<table class="wwtable" cellspacing="1" cellpadding="8" >
										  <tr>
								             <td class="table_name">相关商品:<span id="join_goods"></span></td>
								             <td>
								             	<input type="button" value="添加相关商品" onclick="addRalateGoods('searchgoods','0',this);"/>
								             	<#include "/WEB-INF/template/manager/searchCheckboxGoods.ftl"/>
								             	<div id="selralategoods" class="selralategoods">
								             		<table width="100%"><tr class="rg_title">
									             		<td class="span_td1">操作</td>
									             		<td class="span_td2">商品名称</td>
								             		</tr></table>
								             		<table id="selulrg" class="selulrg"  width="100%">
								             		
								             		</table>
								             	</div>
								             </td>
								           </tr>
					       	    	</table>
					       	    </div>
					        </div>
					        
					        
					        <div class="tabdiv">
								<div class="showtitle"><b>扩展属性</b></div>
								<div >
									<table class="wwtable" cellspacing="1" cellpadding="8" >
										<#list 	extendattrList as ea>
										  	<tr>
									             <td class="table_name">${ea.attr_name}</td>
									             <td>	
									             		<@s.hidden name="selfextendattr.ex_attr_id" value="${ea.ex_attr_id?if_exists}"/>
									             		<@s.hidden name="selfextendattr.ex_attr_alias"  value="${ea.attr_name?if_exists}"/>
									             		<#if ea.option_type?if_exists=='0'>
									             			<#list ea.default_value?if_exists?split(",") as s>
									             				<input type="checkbox" value="${s}" onclick="attrSelCheck(this);"/>${s}		
									             			</#list>
									             			<@s.hidden cssClass="ex_class" name="selfextendattr.ex_attr_value" />
									             		<#elseif ea.option_type?if_exists=='1'>
									             			<@s.textfield cssClass="ex_class" name="selfextendattr.ex_attr_value"   onkeyup="checkLength(this,100);" cssStyle="width:260px;"  value="${ea.default_value?if_exists}"/>
									             		<#elseif ea.option_type?if_exists=='2'>
									             			<select class="ex_class" name="selfextendattr.ex_attr_value" />
										             			<#list ea.default_value?if_exists?split(",") as s>
										             				<option value="${s}">&nbsp;${s}&nbsp;</option>	
										             			</#list>   
									             			</select>        
									             		</#if>
									             </td>
								           	</tr>
								        </#list>
					       	    	</table>
					       	    </div>
					        </div>
					        
					        
					        <div class="tabdiv">
								<div class="showtitle"><b>参数组名</b></div>
								<div >
					       	    	   <table class="wwtable" cellspacing="1" cellpadding="0" >
												<#list 	paragroupList as pg>
													<tr><td colspan="2" class="table_goods_td1">
														<input type='hidden' name="selfparagroup.para_group_id" value="${pg.para_group_id?if_exists}"/>
														<input type='hidden' name="selfparagroup.sort_no" value="${pg.sort_no?if_exists}"/>
														${pg.para_name}
													</td></tr>
													<#assign pn=0/>
													<#list 	paravalueList as pv>
											            <#if pg.para_group_id==pv.para_group_id>
														  	<tr>
													             <td  class="table_goods_td2">	
													             		${pv.para_name?if_exists}
																		<input type='hidden' name="selfparavalue.para_id" value="${pv.para_id?if_exists}"/>
																		<input type='hidden' name="selfparavalue.sort_no" value="${pv.sort_no?if_exists}"/>
													             </td>
													             <td>
													             		<@s.textfield name="selfparavalue.slef_para_value" value="${pv.value?if_exists}" cssStyle="width:260px;"maxlength="20"/>	
													             </td>
												           	</tr>
												           	<#assign pn=pn+1/>
											           	</#if>
											        </#list>
											        <input type='hidden' name="para_num" value="${pn?if_exists}"/>
										        </#list>
						       	       </table>
					       	    </div>
					        </div>
		 </div>	
	</div>
	<div class="clear"/>
	 <div class="fixbuttom">
	       <@s.token/>    
	       
	      <@s.hidden  id="relate_goods"  name="goods.relate_goods"/>
	       <@s.hidden  id="ship_id"  name="goods.ship_id"value="0"/>
	       <@s.hidden  name="goods.is_ship"value="0"/>
	       <@s.hidden  name="goods.is_virtual" value="0"/> 
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
	       
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
           <input type="button" value="保存并添加相似商品" onclick="savegoods('/virtualgoods!savegoods.action');"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Virtualgoods_list.action','');"/>
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

</body>
</html>

