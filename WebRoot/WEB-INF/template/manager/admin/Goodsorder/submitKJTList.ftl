<html>
  <head>
    <title>直邮发货-待提交海关</title>
    <link href="/include/admin/css/goodsorder.css" rel="stylesheet" type="text/css">
    <link href="/include/admin/css/order.css" rel="stylesheet" type="text/css">
    <link href="/include/common/css/buttons.css" rel="stylesheet" type="text/css">
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
<@s.form action="/admin_Goodsorder_submitKJTList.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：商城管理 > 直邮发货 > 待提交海关</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>订单编号:</td>
			<td><@s.textfield name="order_id_s"  cssStyle="width:160px;"  />&nbsp;</td>
	       <td><button type="submit" class="rbut">查询</button>&nbsp;</td>
	       <td><button type="button" class="rbut" onclick="recycle('goodsorder.order_id','/admin_Goodsorder_sKJTSendRecycle.action');">订单回收</button></td>
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
                                      <p class="pftd90">
                                        <span><input type="checkbox" name="goodsorder.order_id" value="${goodsorder.order_id?if_exists}"/></span>
                                        <span>订单编号：<b><a onclick="linkToInfo('/admin_Goodsorder_view.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}&detail_return_flage=7');" >${(goodsorder.order_id)?if_exists}</a></b></span>
										   <span>状态：待提交海关
							                  <#list deliverList as cp>
							                    <#if goodsorder.deliver_state==cp.para_value>
							                     <font color="#005ea7"> ${cp.para_key}/<#if goodsorder.is_clearance?if_exists=='0'> <font color="blue">待通关</font></#if></font>
							                    </#if>
							                  </#list>	
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
                                       		<button type="button" class="cssbtn" onclick="SubmitToCustomsShowDIV('customs','${goodsorder.order_id?if_exists}')">提交海关</button>
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
                                         
                                         <td  width="9%"  class="gray" rowspan="9">
                                          <#if goodsorder.order_para!=''>
									   		${(goodsorder.order_para)?if_exists}
									     </#if><br/>
									    <a onclick="linkToInfo('/admin_Goodsorder_view.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}&detail_return_flage=16');" >订单详情</a>
										</td>
                                         <td  width="12%" rowspan="10" class="wltd" >	
	                                          <#if goodsorder.order_state=='3'>
										        	<p>
										        	   <a  onclick="linkToInfo('/admin_Goodsorder_admin_order_Logistics.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}');" >
															    查看物流
														</a>
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
	<!-- 打印票据 start-->
	<div style="display:none;" id="customs" >
      <@s.form id="customsForm" action=""  method="post" class="searchDiv" >
      	   <@s.hidden name="goods_order_ids" id="submit_order_id" />
           <@s.hidden name="is_modify" value="0" />
           <br><br>
	       <table border="0" style="margin-left:30px;margin-right:30px;">
	          <tr>
	           	<td width="150" style="border-bottom: 0px;background: #E3E8EC none repeat scroll 0%;line-height:30px;">&nbsp;&nbsp;请选择海关：</td>
           		<td width="70%" id="orderinfo_id" style="border: 1px solid #EBEBEB">
           			<@s.select list="commparaList" listKey="para_value" listValue="para_key" name="defual_select_code" style="width:150px;" id="customs_id"/>
           		</td>
	          </tr>
	          <tr>
	          	<td colspan="2" align="left" height="70" style="border-bottom: 0px;color:red">
	          		注：<br>
	          		1.“久通宏达”为线下交易，不与海关进行在线对接<br>
	          		2.根据选用的海关，订单将从不同的海关进行通关<br>
	          	</td>
	          </tr>
	          <tr>
	           <td colspan="2" align="center" style="border-bottom: 0px">
	             <button type="button" class="button button-royal button-pill button-raised button-small" id="button_submit_customs" onclick="submitCustoms('1')">提交海关</button>
	             <button type="button" class="button button-royal button-pill button-raised button-small" onclick="closeSearchShowDIV('customs')" >关闭</button>
	           </td>
	          </tr> 
	       </table>
      </@s.form>
	</div>
<!-- 修改运费 end-->
<#include "/WEB-INF/template/manager/admin/Goodsorder/cancelOrderRefundApp.ftl">
<#include "/WEB-INF/template/manager/admin/Goodsorder/cancelOrderTip.ftl">
</body>
</html>
