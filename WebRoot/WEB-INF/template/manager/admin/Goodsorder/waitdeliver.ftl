<html>
  <head>
    <title>发货管理-待确认发货订单</title>
    <#include "/WEB-INF/template/manager/admin/Goodsorder/commonprint.ftl">
    <link href="/include/admin/css/goodsorder.css" rel="stylesheet" type="text/css">
    <link href="/include/admin/css/order.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/include/admin/js/goodsorder.js"></script>
    <script src="/include/admin/js/orderprint.js" type="text/javascript"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
	<script type="text/javascript" src="/include/admin/js/commonTop.js"></script>
  </head>
  <body>
<@s.form action="/admin_Goodsorder_waitdeliver.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：商城管理 > 订单管理 > 待确认发货订单</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>订单编号:</td>
			<td><@s.textfield name="order_id_s" cssStyle="width:160px;"  /></td>
			<td class="tdpad">会员名称:</td>
			<td><@s.textfield name="buy_cust_s"  /></td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	       <td><input type="button" onclick="searchShowDIV('searchDiv','300px','220px');" class="rbut" value="高级查询"></td>
	       <td>  <input type="button" class="rbut" onclick="order_print_all('2');"  value="打印配货单" title="批量打印配货单"></td>
	       <td>  <input type="button" class="rbut" onclick="batchpintExpressShowDIV('batchprintepress','2');"  value="打印运单" title="批量打印运单"></td>
	       <td>  <input type="button" class="rbut" onclick="batchsumitWaitOrder();" title="批量提交待确认发货"  value="提交待发货"></td>
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
                              <th width="4%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('goodsorder.order_id')"/></th>                         
                              <th width="34%">订单信息</th>
                              <th width="9%">单价(元)</th>
                              <th width="9%">数量 </th>
                              <th width="8%">会员名称</th>
                              <th  width="9%">实付款 </th>
                              <th  width="8%">交易状态</th>
                              <th  width="10%">交易操作</th>
                            </tr>
                            <#list goodsorderList as goodsorder>
                            <tr>
                              <td colspan="9">
                                  <div class="tddiv">
                                      <p class="pftd">
                                         <span><input type="checkbox" name="goodsorder.order_id" value="${goodsorder.order_id?if_exists}"/></span>
                                          <span>订单编号：<b><a onclick="linkToInfo('/admin_Goodsorder_view.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}&detail_return_flage=5');" >${(goodsorder.order_id)?if_exists}</a></b></span>
                                          <span>时间：${(goodsorder.order_time)?if_exists}</span>
                                          <span >会员：<font color="#005ea7">${(goodsorder.buy_user_name)?if_exists}</font></span>
                                         <span >收货人：<font color="#005ea7">${(goodsorder.consignee)?if_exists}</font></span>
                                         <span >收货地址：<font color="#005ea7" title="${(goodsorder.area_attr)?if_exists} ${goodsorder.buy_address?if_exists}">${(goodsorder.area_attr)?if_exists}  
                                         <#if goodsorder.buy_address?if_exists? length lt 10>
									    		${goodsorder.buy_address?if_exists}
											<#else>
												${goodsorder.buy_address[0..9]?if_exists}
											</#if>
                                        </font></span>
                                          <span>
									         <#if goodsorder.print_fahuo?if_exists=="0">
									          <font color="green">[配]</font>
									        </#if>
									       <#if goodsorder.print_kuaidi?if_exists=="0">
									          <font color="red">[递]</font>
									        </#if>
                                          </span>
                                       </p>
                                       <p class="prtd">
	                                      <a href="/sendPrint.action?goodsorder.order_id=${goodsorder.order_id?if_exists}" target="_blank"  title="打印配送清单">配</a>
                                          <a href="###"  onclick="pintExpressShowDIV('printepress','${goodsorder.order_id?if_exists}','${(goodsorder.tatal_amount)?if_exists}','${(goodsorder.order_time)?if_exists}','${(goodsorder.consignee)?if_exists}','${(goodsorder.area_attr)?if_exists}  ${(goodsorder.buy_address)?if_exists}','${(goodsorder.mobile)?if_exists}','${goodsorder.print_kuaidi?if_exists}');" 	 title="打印运单" >递</font></a>
                                          <input type="button"  onclick="onesumitWaitOrder('${goodsorder.order_id?if_exists}','${goodsorder.print_kuaidi?if_exists}');" title="提交待确认发货"  value="提交待发货">
                                       </p>
                                       <div class="clear"></div>
                                  </div>
                                  <div class="tddivCont">
                                  
                                  
                                  
                                     <table width="100%" cellspacing="0" cellpadding="0" class="orderTab1">
                                       <tr>
                                         <td width="56%">
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
										<td  width="8%"  class="gray" rowspan="9">
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
                                         
                                         <td  width="8%"   rowspan="9">
                                          <#if goodsorder.order_para!=''>
									   		<font color="#005ea7">${(goodsorder.order_para)?if_exists}</font>
									     </#if>
										</td>
                                         <td   rowspan="10">	
                                         <p>
	                                         <a onclick="linkToInfo('/admin_Goodsorder_view.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}&detail_return_flage=5');" >订单详情</a>
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
  <@s.hidden id="goods_order_num" name="goods_order_num"/>
  <@s.hidden id="goods_order_ids" name="goods_order_ids"/>
 <!--表单框隐藏域存放-->  
</@s.form>
<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Goodsorder_waitdeliver.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td class="searchDiv_td">订单编号:</td>
			<td><@s.textfield name="order_id_s"  /></td>
		</tr>
		<tr>
			<td class="searchDiv_td">交易类型:</td>
			<td><@s.select  name="order_type_s"  list=r"#{'999':'--请选择--','1':'普通订单'}" /></td>
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
				<input type="button" name="search" value="搜索" onclick="showSearchDiv('','','','','form_search_id');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			</td>
	   </tr>
		</table>
		<!--搜索框隐藏域存放-->
		</@s.form>
	</div>		   
<!-- 修改运费 end-->
<!-- 打印运单 start-->
<#include "/WEB-INF/template/manager/admin/Goodsorder/printexpressmode.ftl">
<!-- 打印运单 end-->

<div  style="display:none;" id="batchprintepress" >
      <@s.form id="printDeliveryForm" action="/admin_Goodsorder_confirmDeliveryGoodsOrder.action"  method="post" class="searchDiv" >
	        <table >
	         <tr>
	           	<td  class="table_name">系统提示：</td>
           		<td id="batch_info_id"></td>
	          </tr>
	            <tr>
	           	<td  class="table_name">快递公司：</td>
           		<td> <@s.select list="sendmodeList" listKey="smode_id" listValue="smode_name"  name="order_send_mode" style="width:120px" id="batchprint_sendmode_id"/></td>
	          </tr>
	          <tr>
	           <td  colspan="2" align="center">
	             <@s.hidden name="goods_order_ids" id="batch_send_order_id" />
	           	<input type="button" onclick="printMoreExpress();" value="批量打印"/>
	           	<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('batchprintepress')"/>
	           </td>
	          </tr> 
	           <tr>
	           	<td  colspan="2"><font color="red">重要提示：运单可重复打印,如您已打印过运单,请核对,勿重复发货。</font></td>
	          </tr>
	          
	             
	       </table>
	     </@s.form>
</div>


</body>

</html>
