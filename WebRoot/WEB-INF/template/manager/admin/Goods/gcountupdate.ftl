<html>
  <head>
    <title>修改商品</title>
    <#include "/include/uploadInc.html">
	<script type="text/javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
	<script src="/wro/admin_goods_update.js" type="text/javascript"></script>
	<link href="/wro/admin_goods_update.css" rel="stylesheet" type="text/css"/>
	<script src="/include/admin/js/goodsdetailToAPPDetail.js" type="text/javascript"></script>
	<script type="text/javascript" src="/include/common/js/get-taxrate.js"></script>
	<script type="text/javascript">
		  $(function(){
		    	backSelfCat("${mbStr?if_exists}","");
		    	loadTax("${taxIdStr?if_exists}","");
		  })
	</script>
  </head>
<body>
<@s.form id="goodspost" action="/admin_Goods_gcountupdate.action" method="post" validate="true" >
<div class="postion">当前位置:商品管理 > 商品管理 > 修改商品 </div>
<div class="rtdcont">
	<div class="rttable">
	    <div class="oper_top"></div>	
  		 <div id="oper_div" class="oper_seo_div">      
					       <div class="tabdiv">
					       	    <div class="showtitle"><b>基本信息</b></div>
					       	    <div >
									   	 <table class="wwtable" width="100%" cellspacing="1" cellpadding="1">    
									   	         
											<tr>
										             <td class="table_name" width="12%">所属分类:</td>
										             <td width="45%">
										             	 <@s.textfield value="${(cat_name)?if_exists}" cssStyle="width:300px" readonly="true"/>
										             	<@s.hidden name="goods_cat"/>
										             	<@s.hidden name="goods.cat_attr"/>
										             	<@s.hidden name="cat_name"/>
										             </td>
								             <td class="table_name" width="12%">商品名称</td>
								             <td width="40%">
								             	<@s.textfield  value="${(goods.goods_name)?if_exists}" readonly="true" />
								             	
								             </td>
								           </tr>
							
								          <tr>
								             <td class="table_name">外文名称:</td>
								             <td>
								             	<@s.textfield value="${(goods.goods_en_name)?if_exists}" readonly="true" cssStyle="width:200px"/>
								             </td>
								          
								             <td class="table_name">商品编号:</td>
								             <td>
								             	<@s.textfield value="${(goods.goods_no)?if_exists}" readonly="true"/>
								             </td>
								           </tr>
								           
								          <tr>
								             <td class="table_name">条形码:</td>
								             <td>
								             	<@s.textfield value="${(goods.bar_code)?if_exists}" readonly="true" cssStyle="width:300px"/>
								             </td>
							               <#if goodsbrandList?if_exists?size gt 0 > 
									             <td class="table_name">品牌:</td>
									             <td>
									             	 <@s.select  name="goods.brand_id" list="goodsbrandList" listValue="brand_name"  listKey="brand_id" headerKey=""  headerValue="请选择" readonly="true" disabled="true"/>    
									             </td>
									           </tr>
							                </#if>
							                <tr>
										        <td class="table_name">计量单位:</td>
										             <td>	
										                <@s.textfield value="${(goods.unit)?if_exists}" cssStyle="width:50px" readonly="true"/>             	
										             		(盒、件、片、包、箱 等)
										             </td>
										           
										             <td class="table_name">商品排序:</td>
										             <td>
										             	<@s.textfield value="${(goods.sort_order)?if_exists}" readonly="true" cssStyle="width:50px"/>
										             </td>
										           </tr>  
										     <tr>
										             <td class="table_name">重量</td>
										             <td>
										             	<@s.textfield  value="${(goods.weight)?if_exists}" cssStyle="width:50px" readonly="true"/><span>克(g)</span>
										             </td>
										 
								             <td class="table_name">保质期:</td>
								             <td>
								             	<@s.textfield value="${(goods.quality)?if_exists}" readonly="true" cssStyle="width:30px"/><span>月</span>
								             </td>
								           </tr>								               

								          <tr>
								             <td class="table_name">产地</td>
								             <td>
								             	<@s.select id="goods_place" name="goods.goods_place"  list="areaList" listValue="area_name" listKey="area_id" headerValue="请选择" readonly="true" disabled="true"/>
								             </td>
								           
								             <td class="table_name">生产商:</td>
								             <td>
								             	<@s.textfield  value="${(goods.producer)?if_exists}"cssStyle="width:200px;" readonly="true"/>
								             </td>
								           </tr>
								           
								          <tr>
								             <td class="table_name">商品来源:</td>
								             <td><#if (goods.goods_source)?if_exists=='0'>
								                   <font color="green">自主运营</font>
								                  <#else>
								                  <font color="green">供货商供货</font>
								                  </#if>
								             </td>
								       
								             <td class="table_name ">生产地址:</td>
								             <td>
								             	<@s.textfield  value="${(goods.product_address)?if_exists}"  cssStyle="width:100px;" readonly="true"/>
								             </td>
								           </tr>								           
								           								           										      
								          <tr>
								             <td class="table_name">税费</td>
								             <td >
									          	  <table>
									             		<tr>
									             			<td class="tdbottom">
									             				<div id="taxDiv" style="margin-left:-5px;"></div>
									             			</td>
									             			<td class="tdbottom">
									             				<@s.fielderror><@s.param>tax_attr</@s.param></@s.fielderror>
										              		</td>
										              	</tr>
										            </table>
										            <td class="table_name" >出口经销商:</td>
								             <td>
								             	<@s.textfield  value="${(goods.export)?if_exists}" readonly="true"/>
								             </td>
								             </td>
								           </tr>

								          <tr>
								             <td class="table_name">海关已备案:</td>
								             <td>
								                 <img src="/include/admin/images/hai.png"/>
								             	<@s.hidden name="goods.is_customs" value="/include/admin/images/hai.png"/> 
								             	<@s.fielderror><@s.param>goods.is_customs</@s.param></@s.fielderror>
								             </td>
								          
								             <td class="table_name">商检经备案:</td>
								             <td>
								             	<img src="/include/admin/images/gou.png"/>
								             	<@s.hidden  name="goods.inspection" value="/include/admin/images/gou.png"/>
								             	<@s.fielderror><@s.param>goods.inspection</@s.param></@s.fielderror>
								             </td>
								           </tr>
								           
								          <tr>
								             <td class="table_name">贸易方式:</td>
								             <td>
								             	<#list depotList as depot>
								             	 <input type="radio" name="goods.depot_id" value="${depot.depot_id}" <#if depot.depot_id=="${goods.depot_id}">checked</#if>/>${depot.depot_name?if_exists}
								             	</#list>
								             	<@s.fielderror><@s.param>goods.trade_way</@s.param></@s.fielderror>
								             </td>
								             <td class="table_name">商品图片</td>
								               <td align="center"><img src="${(goods.list_img)?if_exists}" width="50px" hieght="50px"  /></td>
								             </tr>
									</table>          
								    <!--单个属性开始-->  
								   <div id="op_div">
								    <#if is_more_attr=='1' || is_more_attr==null> 
										<#list goodsattrList as attr>
							              <table class="wwtable" cellspacing="1" cellpadding="8">
										           <tr>
										             <td class="table_name" width="12%">原价<font color='red'>*</font></td>
										             <td width="45%">
										             	<@s.textfield id="market_val" name="market_price_str" cssClass="input" onkeyup="checkRMB(this);"  maxlength="11" value="${attr.market_price_str?if_exists}" readonly="true"/>
										             	<font>格式0.00</font>
										             	<@s.fielderror><@s.param>goodsattr.market_price</@s.param></@s.fielderror>
										             </td>
										             <td class="table_name" width="12%">售价<font color='red'>*</font></td>
										             <td width="40%">
										             	<@s.textfield  id="sale_val" name="sale_price_str" cssClass="input"  onkeyup="checkRMB(this);"  maxlength="11"  value="${attr.sale_price_str?if_exists}" readonly="true"/>
										             	<font>格式0.00</font>
										             	<@s.fielderror><@s.param>goodsattr.sale_price</@s.param></@s.fielderror>
										             </td>
										           </tr>
									                
										           <tr>
										             <td class="table_name">成本价:</td>
										             <td>
										             	<@s.textfield  id="cost_val" name="cost_price_str" cssClass="input"   onkeyup="checkRMB(this);"  maxlength="11"  value="${attr.cost_price_str?if_exists}" readonly="true"/>
										             	<font>格式0.00</font>;<span>前台不会显示，仅供后台使用</span>
										             	<@s.fielderror><@s.param>goodsattr.cost_price</@s.param></@s.fielderror>
										             </td>
										           
										             <td class="table_name">货号:</td>
										             <td>
										             	<@s.textfield id="weight_val" name="goods_item_str" cssClass="input" onkeyup="checkLength(this,20);" maxlength="20"  value="${attr.goods_item?if_exists}" readonly="true"/>(若不填则与商品编号一致)
										             	<@s.fielderror><@s.param>goodsattr.weight</@s.param></@s.fielderror>
										             </td>
										           </tr>	
							
										           <tr>
										             <td class="table_name" >物流体积</td>
										             <td>
										             	 <@s.textfield   id="volume_val" name="volume_str"  onkeyup="checkFloat(this);" value="${attr.volume_str?if_exists}" readonly="true"/>(立方厘米)
										             </td>
										           
										             <td class="table_name" >物流重量</td>
										             <td>
										             	 <@s.textfield    id="logsweight_val" name="logsweight_str"  onkeyup="checkFloat(this);" value="${attr.logsweight_str?if_exists}" readonly="true"/>(千克)
										             </td>
										             </tr><tr>
										             <td class="table_name"><font color="red">库存*</font></td>
										             <td>
										             	<@s.textfield id="stock_val"  name="stock_str" cssClass="input" onkeyup="checkDigital(this);"  maxlength="11"  value="${attr.stock_str?if_exists}"/>
										             	<@s.hidden name="specstr_str"/>
										             	<@s.fielderror><@s.param>goodsattr.stock</@s.param></@s.fielderror>
										             </td>
										             <td class="table_name">规格:</td>
										             <td>
										                <input type="button" id="showsize" value="开启规格"/>
										             	<font>开启规格前先填写以上价格等信息，可自动复制信息到货品</font>
										             </td>
										           </tr>
							             	</table>
							            </#list>
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
																	
																	<th>库存</th><th>原价<font color='red'>*</font></th><th>售价<font color='red'>*</font></th><th>成本价</th><th>物流体积<font color='red'>*</font></th><th>物流重量<font color='red'>*</font></th><th>上架</th><th>操作</th></tr>
																	
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
																			<input type="text"  style="width:60px;" name="stock_str"  value="${attr.stock_str?if_exists}" onkeyup="checkDigital(this);" maxlength="11"></td>
																			<td  align="center"><input type="text" style="width:60px;" name="market_price_str"  value="${attr.market_price_str?if_exists}" onkeyup="checkRMB(this);" maxlength="11" ></td>                         
																			<td  align="center"><input type="text"   style="width:60px;" name="sale_price_str"  value="${attr.sale_price_str?if_exists}" onkeyup="checkRMB(this);" maxlength="11" ></td>
																			<td  align="center"><input type="text"  style="width:60px;" name="cost_price_str"  value="${attr.cost_price_str?if_exists}" onkeyup="checkRMB(this);" maxlength="11" ></td>
																			<td align="center"><input type="text"  style="width:60px;" name="volume_str"  value="${attr.volume_str?if_exists}" onkeyup="checkFloat(this);" maxlength="11"> </td>
																			<td align="center"><input type="text"  style="width:60px;" name="logsweight_str"  value="${attr.logsweight_str?if_exists}" onkeyup="checkFloat(this);" maxlength="11"></td>
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
						             </div>
					       	    </div>
		 </div>	
   		 <br/>
	     <div class="fixbuttom">
	       <@s.token/>    
	        <@s.hidden name="goods.is_limit" value="1"/>
			<@s.hidden  name="up_goods_str" value="0"/>
	       <@s.hidden  id="relate_goods"  name="goods.relate_goods"/>
	       <@s.hidden  id="ship_id"  name="goods.ship_id"/>
	       <@s.hidden  name="goods.is_virtual" value="1"/>
	       <@s.hidden  name="goods.goods_id" id="flag_goods_id"/> 
	       <@s.hidden  name="goods.info_state" /> 
	       <@s.hidden  name="goods.user_id"/> 
	       <@s.hidden  name="goods.is_del"/>
	       <@s.hidden  name="info_state" value="1" /> 
	      <#-- <@s.hidden  id="goods_detail"  name="goods_detail"/>    -->
	       <@s.hidden  id="sel_spec_name"  name="sel_spec_name"/>    
	       <@s.hidden  id="sel_spec_count"  name="sel_spec_count"/>    
	       <@s.hidden  id="is_more_attr"  name="is_more_attr"/> 
	       <@s.hidden  id="self_goods_size_value"  name="self_goods_size_value"/>
	       <@s.hidden  id="self_goods_img_value"  name="self_goods_img_value"/>
	       <@s.hidden  id="self_goods_relate_img_value"  name="self_goods_relate_img_value"/>
	       <@s.hidden  id="self_goods_sort_value"  name="self_goods_sort_value"/>
	       <@s.hidden  id="self_size_id"  name="self_size_id"/>
	       <@s.hidden  id="goods_up_str"  name="goods_up_str" value=""/>
	       <@s.hidden  id="goods_down_str"  name="goods_down_str"  value=""/>
	       <@s.hidden  id="ex_attr_value"  name="ex_attr_value"/>
	       <@s.hidden  id="slef_para_value"  name="slef_para_value"/>
	       <@s.hidden id="is_must_delete_spec" name="is_must_delete_spec" value="0"/>
	       <@s.hidden  name="is_update" value="1"/>
	       ${listSearchHiddenField?if_exists}
            <@s.submit value="修改库存"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Goods_gcountlist.action','');"/>
	     </div>
	</div>
	<div class="clear"/>
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

