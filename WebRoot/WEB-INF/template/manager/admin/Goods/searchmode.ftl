	 <script type="text/javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Goods_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td class="searchDiv_td" width="70px">商品名称:</td>
				<td colspan="3"><@s.textfield name="goods_name_s"   cssStyle="width:300px;"  /></td>
			</tr>
			<tr>
				<td class="searchDiv_td" width="70px">英文名称:</td>
				<td colspan="3"><@s.textfield name="g_goods_en_name_s"  cssStyle="width:300px;"  /></td>
			</tr>
			<tr>
				<td class="searchDiv_td" width="70px">商品分类:</td>
				<td colspan="3" width="330px">
				  	<div class="morecatdiv">
			          	<div id="catDiv" ></div>
		            </div>
	         	</td>
			</tr>
			<tr>
				<td class="searchDiv_td" width="70px">商品税率:</td>
				<td colspan="3" width="330px">
					<div class="morecatdiv">
			          	<div id="taxDiv" ></div>
		            </div>
				</td>
			</tr>
			
			<tr>
				<td class="searchDiv_td" width="70px">活动商品:</td>
				<td colspan="3" width="330px">
				   <@s.select  name="g_goods_sale_id_s" list="salegoodsList" listValue="sale_name"  listKey="sale_id" headerKey=""  headerValue="请选择"/>
				</td>
			</tr>			
			
			<tr>
			    <td class="searchDiv_td" width="70px">原价:</td>
				<td ><@s.textfield name="g_goods_marketstart_s" cssStyle="width:45px;"   /> 至 <@s.textfield name="g_goods_marketend_s"  cssStyle="width:45px;"  /></td>
				<td class="searchDiv_td" width="70px">售价:</td>
				<td ><@s.textfield name="g_goods_salestart_s" cssStyle="width:45px;"  /> 至 <@s.textfield name="g_goods_saleend_s" cssStyle="width:45px;"  /></td>
			</tr>
			<tr>
			    <td class="searchDiv_td" width="70px">库存:</td>
				<td ><@s.textfield name="g_goods_stockstart_s" cssStyle="width:45px;"  /> 至 <@s.textfield name="g_goods_stockend_s" cssStyle="width:45px;"  /></td>
			    <td class="searchDiv_td" width="70px">商品来源:</td>
				<td><@s.select name="g_goods_source_s" list=r"#{'':'请选择','0':'自主运营','1':'供货商供货'}"/></td>
			</tr>
			<tr>
				<td class="searchDiv_td" width="70px">贸易方式:</td>
				<td><@s.select  name="g_goods_depot_s" list="depotList" listValue="depot_name"  listKey="depot_id" headerKey=""  headerValue="请选择"/></td>
				<td class="searchDiv_td" width="70px">品牌:</td>
				<td> <@s.select  name="g_goods_brandid_s" list="goodsbrandList" listValue="brand_name"  listKey="brand_id" headerKey=""  headerValue="请选择"/>  </td>
			</tr>
			<tr>
				<td class="searchDiv_td" width="70px">商品标签:</td>
				<td >
				<@s.select name="tab_s" list="commparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
				</td>
				<td class="searchDiv_td" width="70px">积分消费:</td>
				<td><@s.select name="g_goods_useintegral_s" list=r"#{'':'请选择','0':'否','1':'是'}"/></td>
			</tr>
			<tr>
				<td class="searchDiv_td" width="70px">产地:</td>
				<td colspan="3" width="330px"><@s.select id="goods_place" name="g_goods_place_s"  list="areaList" listValue="area_name" listKey="area_id" headerKey=""  headerValue="请选择"/></td>
			</tr>
			
			
			<tr>
				<td class="searchDiv_td" width="70px">商品编号:</td>
				<td><@s.textfield name="goods_no_s" cssStyle="width:120px;" /></td>
				<td class="searchDiv_td" width="70px">仓库编码:</td>
				<td><@s.textfield name="g_goods_warehouse_s"  cssStyle="width:120px;"/></td>
			</tr>
			<tr>
				<td class="searchDiv_td" width="70px">跨境通ID:</td>
				<td colspan="3" width="330px"><@s.textfield name="g_goods_kjtid_s" cssStyle="width:120px;" /></td>
			</tr>
			<tr>
			    <td class="searchDiv_td" width="70px">条形码:</td>
				<td><@s.textfield name="g_goods_barcode_s" cssStyle="width:120px;" /></td>
				<td class="searchDiv_td" width="70px">生产商:</td>
				<td><@s.textfield name="g_goods_producer_s" cssStyle="width:120px;" /></td>
			</tr>
			<tr>
			    <td class="searchDiv_td" width="70px">发布时间:</td>
				<td colspan="3">
				
				  <@s.textfield id="txtstartDate" name="g_starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
                    readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
			        至
			        <@s.textfield id="txtendDate" name="g_endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
					               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
				</td>
			</tr>
			<tr>
				<td align="center" colspan="4" style="border:0px;">
					<input id="searhGoods" type="button" name="search" value="搜索" onclick="showSearchGoodDiv('','cat_attr','','search_cat_attr','form_search_id','indexForm');"/>&nbsp;
				    <input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
				</td>
		   </tr>
			</table>
		<!--搜索框隐藏域存放-->
		    <@s.hidden id="search_cat_attr"  name="cat_attr_s"/>
			<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		</@s.form>
	</div>		   