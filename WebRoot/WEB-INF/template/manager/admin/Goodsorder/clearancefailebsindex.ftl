<html>
  <head>
    <title>保税发货-提交海关失败</title>
    <link href="/include/admin/css/goodsorder.css" rel="stylesheet" type="text/css">
    <link href="/include/admin/css/order.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/include/admin/js/goodsorder.js"></script>
    <script type="text/javascript" src="/include/admin/js/commonTop.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		//所属地区的回选
 		loadArea("${areaIdStr?if_exists}","","");
	})
	</script>
  </head>
 <body>
<@s.form action="/admin_Goodsorder_clearanceFaileBslist.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：商城管理 > 保税发货 > 提交海关失败</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>订单编号:</td>
			<td><@s.textfield name="order_id_s"  cssStyle="width:160px;"  /></td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   
       <div class="orderDiv" id="orderDivId">
                
                <div class="tabDiv">
                     <!--1-->
                     <div class="orderTab" id="oftabId">
                        <table width="100%" cellspacing="0" cellpadding="0">
                            <tr>
                              <#if (order_state_s=="2") ><th width="4%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('goodsorder.order_id')"/></th></#if>                             
                              <th width="37%">订单信息</th>
                              <th width="9%">单价(元)</th>
                              <th width="8%" onclick="numOrder()" style="cursor:pointer;">数量<#if new_order=='num_desc'><img src="/include/admin/images/up.png"/><#else><img src="/include/admin/images/down.png"/></#if></th>
                              <th width="8%"onclick="nameOrder()" style="cursor:pointer;">会员名称<#if new_order=='buy_custname_desc'><img src="/include/admin/images/up.png"/><#else><img src="/include/admin/images/down.png"/></#if></th>
                              <th  width="8%"onclick="tcountOrder()" style="cursor:pointer;">实付款<#if new_order=='tatal_amount_desc'><img src="/include/admin/images/up.png"/><#else><img src="/include/admin/images/down.png"/></#if></th>
                              <th  width="8%">失败原因</th>
                              <th  width="11%">交易操作</th>
                            </tr>
                            <#list goodsorderList as goodsorder>
                            <tr>
                              <td colspan="9">
                                  <div class="tddiv">
                                      <p class="pftd90">
                                        <span>订单编号：<b><a onclick="linkToInfo('/admin_Goodsorder_view.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}&detail_return_flage=7');" >${(goodsorder.order_id)?if_exists}</a></b></span>
										   <span>状态： 提交海关失败
										</span>
                                          <span >会员：<font color="#005ea7">${(goodsorder.buy_user_name)?if_exists}</font></span>
                                         <span >收货人：<font color="#005ea7">${(goodsorder.consignee)?if_exists}</font></span>
                                           <span >收货地址：<font color="#005ea7" title="${(goodsorder.area_attr)?if_exists} ${goodsorder.buy_address?if_exists}">${(goodsorder.area_attr)?if_exists}  
                                         <#if goodsorder.buy_address?if_exists? length lt 15>
									    		${goodsorder.buy_address?if_exists}
											<#else>
												${goodsorder.buy_address[0..4]?if_exists}
											</#if>
                                        </font></span>
                                       </p>
                                         <p class="prtd">
                                         <span>  <input type="button"   
                                            onclick="cancelOrderShowDIV('cancelorderinfo','${goodsorder.order_id?if_exists}','${(goodsorder.tatal_amount)?if_exists}','${(goodsorder.order_time)?if_exists}','${(goodsorder.consignee)?if_exists}','${(goodsorder.area_attr)?if_exists}  ${(goodsorder.buy_address)?if_exists}','${(goodsorder.mobile)?if_exists}','${(goodsorder.pay_name)?if_exists}','${(goodsorder.pay_id)?if_exists}','${(goodsorder.pay_time)?if_exists}','${(goodsorder.order_state)?if_exists}','${(goodsorder.pay_code)?if_exists}','${(goodsorder.pay_trxid)?if_exists}','clearanceFaileBslist');"
                                           title="点击取消订单" value="取消订单并退款" class="cssbtn"/></span>
                                       </p>
                                        <p class="prtd">
                                         <span><input type="button" class="cssbtn" onclick="submitkjt('${goodsorder.order_id?if_exists}','2')" value="重新提交海关"></span>
                                       </p>
                                        <div class="clear"></div>
                                  </div>
                                  <div class="tddivCont">
                                     <table width="100%" cellspacing="0" cellpadding="0" class="orderTab1">
                                       <tr>
                                         <td width="61%">
                                            <table width="100%" cellpadding="0" cellspacing="0" class="tdtable">
                                             <#assign num=0>
											<!-- 除预售以外的  开始-->
										    <#if goodsorder.order_type !='6'>
													<#list detailList as detail>
														  <#if ((detail.order_id)?if_exists)==((goodsorder.order_id)?if_exists)>
														  	<#assign num=num+1>
				                                              <tr>
				                                                <td width="47%"  class="otd">
				                                                <a target='_blank'  href="/mall/order!skipPage.action?goods_id=${(detail.goods_id)?if_exists}&order_type=${(goodsorder.order_type)?if_exists}">
				                                                <#if detail.img_path!=''>
													      			<img src="${(detail.img_path)?if_exists}" align="absmiddle" class="f_left" width='50' height='50'>
												      		   <#else>
												      				<img src="${(cfg_nopic)?if_exists}" align="absmiddle" class="f_left" width='50' height='50'>
												      		    </#if>
														      			${(detail.goods_name)?if_exists}
														      	</a>
														      	
				                                                </td>
				                                                <td width="15%" class="gray">
					                                                <#if (detail.goods_attr)?if_exists&&(detail.goods_attr)!=" ">
															      		 <#assign g_attr=detail.goods_attr>
															      		  <#if ((g_attr)?index_of("<br>")>(-1))>
															      		       <#assign g_attr =g_attr.replace('<br>','')>
						                                                  </#if>
						                                                  ${g_attr}
															      	<#else>
				                                                       --
				                                                    </#if>
														      	</td>
				                                         		<td width="15%"><b>${(detail.goods_price)?if_exists}</b></td>
				                                                <td width="14%"><b>${(detail.order_num)?if_exists}</b></td>
				                                              </tr>
		                                                  </#if>	 
													</#list>
											</#if>
											<!-- 除预售以外的  结束 -->  
                                           </table>
                                         </td>
										<td  width="9%"  class="gray" rowspan="9">
											${(goodsorder.buy_user_name)?if_exists}<br/>
									   		
								        </td>
                                         <td  width="9%" rowspan="10" >
	                                         <p>
		                                         <b>${(goodsorder.tatal_amount)?if_exists}</b><br/>
												<font color="#808080">(
													<#if goodsorder.ship_free!='0'>
														(快递${goodsorder.ship_free?if_exists}元)
													<#else>
														 免运费
													</#if>)
												</font>
	                                         </p>
                                         </td>
                                         
                                         <td  width="20%"  class="gray" rowspan="9">
                                        ${(goodsorder.falsereason)?if_exists}
										</td>
                                         <td  width="1%"  rowspan="10" class="wltd" >	
	                                          
                                         </td>
                                       </tr>
                                     </table>
                                     
                                     
                                  </div>
                              </td>
                            </tr>
							</#list>
                        </table>
                        <!--翻页-->
                        <div class="pages"> ${pageBar?if_exists}</div>
                        
                     </div>
                     
                 </div>

<!--后台table结束-->

<div class="clear"/>
</div>
<!--表单框隐藏域存放-->
  <@s.hidden  name="order_type_s"/>
  <@s.hidden  name="starttime_s"/>
  <@s.hidden  name="endtime_s"/>
  <@s.hidden  name="consignee_s"/>
  <@s.hidden  name="buy_address_s"/>
   <@s.hidden  name="buy_cust_s"/>
   <@s.hidden  name="area_attr_s"/>
   <@s.hidden  name="mobile_s"/>
   <@s.hidden  name="identitycard_s"/>
   <@s.hidden  name="new_order" id="new_order"/>
  <@s.hidden id="goods_order_num" name="goods_order_num"/>
  <@s.hidden id="goods_order_ids" name="goods_order_ids"/>
  <@s.hidden id="is_sea" name="is_sea_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Goodsorder_clearancelist.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td class="searchDiv_td">订单编号:</td>
			<td><@s.textfield name="order_id_s"  /></td>
		</tr>
		<tr>
			<td class="searchDiv_td">会员名称:</td>
			<td><@s.textfield name="buy_cust_s"  /></td>
		</tr>
		<tr>
			<td class="searchDiv_td">收货人:</td>
			<td><@s.textfield name="consignee_s"  />  </td>
		</tr>
			<tr>
			<td class="searchDiv_td">身份证:</td>
			<td><@s.textfield name="identitycard_s"  />  </td>
		</tr>
		
		<tr>
			<td class="searchDiv_td">收货手机:</td>
			<td><@s.textfield name="mobile_s"  />  </td>
		</tr>
		<tr>
			<td class="searchDiv_td">收货地区:</td> 
			<td><div id="areaDiv" ></div></td>
		</tr>
		<tr>
			<td class="searchDiv_td">收货地址:</td>
			<td><@s.textfield name="buy_address_s"  />  </td>
		</tr>
		<tr>
			<td class="searchDiv_td">订单时间:</td>
			<td> <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
			               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
			&nbsp;至&nbsp;
			 <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
			               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  /></td>
		</tr>
		<tr>
			<td align="center" colspan="2" style="border:0px;">
				<input type="button" name="search" value="搜索" onclick="showSearchDiv('area_attr','','search_area_attr','','form_search_id');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			</td>
	   </tr>
		</table>
		<!--搜索框隐藏域存放-->
		    <@s.hidden id="search_area_attr" name="area_attr_s"/>
			<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		</@s.form>
	</div>		   
<#include "/WEB-INF/template/manager/admin/Goodsorder/cancelOrderRefundApp.ftl">
<#include "/WEB-INF/template/manager/admin/Goodsorder/cancelOrderTip.ftl">
</body>
</html>
