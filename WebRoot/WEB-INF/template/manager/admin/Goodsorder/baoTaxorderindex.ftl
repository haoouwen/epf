<html>
  <head>
    <title>订单操作-保税订单</title>
    <link href="/include/admin/css/goodsorder.css" rel="stylesheet" type="text/css">
    <link href="/include/admin/css/order.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/include/admin/js/goodsorder.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
     <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
     <script type="text/javascript" src="/include/admin/js/commonTop.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		//所属地区的回选
 		loadArea("${areaIdStr?if_exists}","","");
	})
	</script>  
     
  </head>
  <body>
<@s.form action="/admin_Goodsorder_baoTaxlist.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：商城管理 > 订单管理 > 保税订单</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>订单编号:</td>
			<td><@s.textfield name="order_id_s" cssStyle="width:160px;"  /></td>
			<td class="tdpad">订单来源:</td>
			<td><@s.select name="is_webapp_order_s" list=r"#{'':'请选择','0':'PC订单','1':'手机订单'}"/></td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	       <td><input type="button" onclick="searchShowDIV('searchDiv','300px','220px');" class="rbut" value="高级查询"></td>
	       <td><input type="button" onclick="recycle('goodsorder.order_id','/admin_Goodsorder_recycle.action');" class="rbut" value="订单回收"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
       <div class="orderDiv" id="orderDivId">
                <div class="orderH2">
                  <ul>
                    <li <#if order_state_s=='999'||order_state_s==''>class="selli"</#if>><a href="/admin_Goodsorder_baoTaxlist.action?order_state_s=999">全部订单</a></li>
                     <#list commparaList as clist>
                   <li   <#if order_state_s=='${clist.para_value?if_exists}'>class="selli"</#if>><a href="/admin_Goodsorder_baoTaxlist.action?order_state_s=${clist.para_value?if_exists}">${clist.para_key?if_exists}</a></li>
                    </#list>
                   
                  </ul>
                </div>
                
                <div class="tabDiv">
                     <!--1-->
                     <div class="orderTab" id="oftabId">
                        <table width="100%" cellspacing="0" cellpadding="0">
                            <tr>
                              <th width="4%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('goodsorder.order_id')"/></th>                            
                              <th width="32%">订单信息</th>
                              <th width="9%">单价(元)</th>
                              <th width="8%" onclick="numOrder()" style="cursor:pointer;">数量<#if new_order=='num_desc'><img src="/include/admin/images/up.png"/><#else><img src="/include/admin/images/down.png"/></#if></th>
                              <th width="8%"onclick="nameOrder()" style="cursor:pointer;">会员名称<#if new_order=='buy_custname_desc'><img src="/include/admin/images/up.png"/><#else><img src="/include/admin/images/down.png"/></#if></th>
                              <th  width="8%"onclick="tcountOrder()" style="cursor:pointer;">实付款<#if new_order=='tatal_amount_desc'><img src="/include/admin/images/up.png"/><#else><img src="/include/admin/images/down.png"/></#if></th>
                              <th  width="8%">交易状态</th>
                              <th  width="11%">交易操作</th>
                            </tr>
                            <#list goodsorderList as goodsorder>
                            <tr>
                              <td colspan="9">
                                  <div class="tddiv">
                                      <p class="pftd">
                                         <span><input type="checkbox" name="goodsorder.order_id" value="${goodsorder.order_id?if_exists}"/></span>
                                          <span>订单编号：<b><a onclick="linkToInfo('/admin_Goodsorder_view.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}');" >${(goodsorder.order_id)?if_exists}</a></b></span>
										   <span>状态：
							                  <#list commparaList as cp>
							                    <#if goodsorder.order_state==cp.para_value>
							                     <font color="#005ea7"> ${cp.para_key}</font>
							                    </#if>
							                  </#list>	
										</span>
                                          <span>时间：${(goodsorder.order_time)?if_exists}</span>
                                           <span ><font color="#005ea7">${(goodsorder.buy_user_name)?if_exists}</font></span>
                                          
                                          <span>
									         <#if goodsorder.is_webapp_order?if_exists=="1">
									           <img src="/include/admin/images/mobileorder.jpg" width="63" height="20"/>
									        </#if>
                                          </span>
                                           <#if goodsorder.order_state=='2'>
                                            <span>
										         <#if goodsorder.deliver_state?if_exists=="0">
										                   <font color="red">[待审核发货]</font>
										          <#elseif  goodsorder.deliver_state?if_exists=="3">
										                <#if goodsorder.print_fahuo=='1'>
										                   <font color="blue">[已审核,待打印出库单]</font>
										                <#elseif goodsorder.print_fahuo=='0'>
										                           <#if goodsorder.print_kuaidi=='1'>
													                   <font color="blue">[已审核,待打印运单]</font>
													                </#if>
										                </#if>
										             <#elseif  goodsorder.deliver_state?if_exists=="1">
										             <font color="green"> [已审核,待确认出库]</font>
										        </#if>
                                          </span>
                                          </#if>
                                       </p>
                                       <p class="prtd">
                                        <#if goodsorder.order_state=='2' || goodsorder.order_state=='1' || goodsorder.order_state=='9' >
                                           <input type="button"   
                                            onclick="modifyshouhuorenShowDIV('buyaddressinfo','${goodsorder.order_id?if_exists}','${(goodsorder.tatal_amount)?if_exists}','${(goodsorder.order_time)?if_exists}','${(goodsorder.consignee)?if_exists}','${(goodsorder.area_attr)?if_exists}  ${(goodsorder.buy_address)?if_exists}','${(goodsorder.mobile)?if_exists}','baoTaxorderindex','${(goodsorder.identitycard)?if_exists}');"
                                           title="点击收货地址" value="修改收货地址" class="cssbtn"/>
                                         </#if>

                                          <#if goodsorder.order_state=='2'>
                                           <input type="button"   
                                            onclick="cancelOrderShowDIV('cancelorderinfo','${goodsorder.order_id?if_exists}','${(goodsorder.tatal_amount)?if_exists}','${(goodsorder.order_time)?if_exists}','${(goodsorder.consignee)?if_exists}','${(goodsorder.area_attr)?if_exists}  ${(goodsorder.buy_address)?if_exists}','${(goodsorder.mobile)?if_exists}','${(goodsorder.pay_name)?if_exists}','${(goodsorder.pay_id)?if_exists}','${(goodsorder.pay_time)?if_exists}','${(goodsorder.order_state)?if_exists}','${(goodsorder.pay_code)?if_exists}','${(goodsorder.pay_trxid)?if_exists}','baoTaxorderindex');"
                                           title="点击取消订单" value="取消订单" class="cssbtn"/>
                                           </#if>

                                          <#if goodsorder.order_state=='4'>
                                           <#assign pay_code="">
                                           <#list paymentList as plist>
					                         <#if goodsorder.pay_id==plist.pay_id>
					                           <#assign pay_code="${(plist.pay_code)?if_exists}">
					                         </#if>
					                       </#list> 
                                            <#if pay_code=="alipay" || pay_code=="alipaywap">
                                              <input type="button" onclick="alipayRefund('','${goodsorder.order_id?if_exists}','${(goodsorder.tatal_amount)?if_exists}','${(goodsorder.order_time)?if_exists}','${(goodsorder.consignee)?if_exists}','${(goodsorder.area_attr)?if_exists}  ${(goodsorder.buy_address)?if_exists}','${(goodsorder.mobile)?if_exists}','${(goodsorder.pay_name)?if_exists}','${(goodsorder.pay_id)?if_exists}','${(goodsorder.pay_time)?if_exists}','${(goodsorder.order_state)?if_exists}','${(goodsorder.pay_code)?if_exists}','${(goodsorder.pay_trxid)?if_exists}','baoTaxorderindex');" title="支付宝退款" value="支付宝退款" class="cssbtn"/>
                                            </#if>
                                           </#if>
                                       </p>
                                       <div class="clear"></div>
                                  </div>
                                  <div class="tddivCont">
                                  
                                  
                                  
                                     <table width="100%" cellspacing="0" cellpadding="0" class="orderTab1">
                                       <tr>
                                         <td width="60%">
                                            <table width="100%" cellpadding="0" cellspacing="0" class="tdtable">
                                                                                 <#assign num=0>
											<!-- 除预售以外的  开始-->
										    <#if goodsorder.order_type!='6'>
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
	                                          <div class="wltd" >
		                                           <#if goodsorder.order_state!='2'&& goodsorder.order_state!='0'&& goodsorder.order_state!='1'>
												        <p>
										        	   <a  onclick="linkToInfo('/admin_Goodsorder_admin_order_Logistics.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}');" >
															    查看物流
														</a>
	                                             </p>
											      </#if>
											  </div>
                                         </td>
                                         
                                         <td  width="9%"  class="gray" rowspan="9">
                                          <#if goodsorder.order_para!=''>
									   		${(goodsorder.order_para)?if_exists}
									     </#if><br/>
									    <a onclick="linkToInfo('/admin_Goodsorder_baoTaxview.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}');" >订单详情</a>
										</td>
                                         <td width="12%"   rowspan="10">	
	                                          <#if goodsorder.order_state=='0'>
	                                               <p>
	                                              <font color="#808080">交易关闭</font>
	                                               </p>
										     <#elseif goodsorder.order_state=='1'>
												   <p>
												  <font color="#808080">等待付款</font>
												  <a onclick="showUpdateShip('${goodsorder.order_id?if_exists}','${goodsorder.ship_free?if_exists}')"> <font color="#808080">修改运费</font></a>
										   				<@s.fielderror><@s.param>newship_free</@s.param></@s.fielderror>
												  <br/>
	                                              <a  onclick="linkToInfo('/admin_Goodsorder_adminCancelOrder.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}');" > 取消订单</a>
	                                               </p>
										    <#elseif goodsorder.order_state=='2' || goodsorder.order_state=='6'>
										    	<p>
										    	 	<font color="#808080"> 等待发货</font>
	                                             </p>
											 <#elseif goodsorder.order_state=='3'>
													 <p>
			                                               	<font color="#808080">等待收货</font>
			                                          </p>
											 
												    
										     <#elseif goodsorder.order_state=='5'||  goodsorder.order_state=='6'>
	                                          <#elseif goodsorder.order_state=='4' >
										         <p>
										           <font color="#808080">等待处理</font>
	                                             </p>
											 <#elseif goodsorder.order_state=='7'>
											    <p>
			                                               	<font color="#808080">等待评价</font>
			                                     </p>
										     <#elseif goodsorder.order_state=='8'>
										     <p>
	                                              <font color="green">交易成功</font>
	                                             </p>
										    
										     </#if>
                                         
		                                     </p>
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
  <@s.hidden  name="order_state_s"   id="o_state_id" />
  <@s.hidden  name="starttime_s"/>
  <@s.hidden  name="endtime_s"/>
  <@s.hidden  name="sell_cust_s"/>
   <@s.hidden  name="buy_cust_s"/>
  <@s.hidden  name="consignee_s"/>
  <@s.hidden  name="buy_address_s"/>
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
	<@s.form action="/admin_Goodsorder_baoTaxlist.action" method="post"  id="form_search_id">
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
		</@s.form>
	</div>		   
<!-- 搜索区域结束-->
<!-- 修改运费 start-->
<div style="display:none;"  id="ship" >
      <@s.form  action="/admin_Goodsorder_updateShip.action"  method="post"  >
	        <table  >
	           <tr>
	           	<td  class="table_name">订单号：</td>
           		<td> <@s.textfield name="ship_oid" id="ship_oid" readonly="true" cssStyle="width:160px" /></td>
	          </tr>
	          <tr>
	           	<td  class="table_name">原运费：</td>
           		<td> <@s.textfield name="oldship_free" id="oldship_free"readonly="true" cssStyle="width:60px"  /></td>
	          </tr>
	          <tr>
	           	<td  class="table_name">新运费：</td>
           		<td> <@s.textfield name="newship_free"  onkeyup="checkRMB(this)"cssStyle="width:60px" /></td>
	          </tr>
	          
	          <tr>
	           <td  colspan="2"align="center">
	           	<@s.submit value="提 交" cssClass="submitbut" />
	           </td>
	          </tr> 
	       </table>
	     </@s.form>
</div>
<!-- 修改运费 end-->
<!-- 打印票据 start-->
<!-- 打印票据 end-->
<#include "/WEB-INF/template/manager/admin/Goodsorder/modifyaddressApp.ftl">
<#include "/WEB-INF/template/manager/admin/Goodsorder/cancelOrderRefundApp.ftl">
<#include "/WEB-INF/template/manager/admin/Goodsorder/baotaxcancelOrderTip.ftl">
<!-- 打印票据 end-->
</body>

</html>
