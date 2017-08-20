<html>
  <head>
    <title>订单操作- 订单回收站</title>
    <link href="/include/admin/css/goodsorder.css" rel="stylesheet" type="text/css">
    <link href="/include/admin/css/order.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/include/admin/js/goodsorder.js"></script>
     <script type="text/javascript" src="/include/admin/js/commonTop.js"></script>     
  </head>
  <body>
<@s.form action="/admin_Goodsorder_reList.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：商城管理 > 订单管理 > 订单回收站</div>
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
	      <td><input type="button" onclick="delInfo_order('goodsorder.order_id','/admin_Goodsorder_deleteReOrder.action');" class="rbut" value="删除订单"></td>
	      <td><input type="button" onclick="reinfo_order('goodsorder.order_id','/admin_Goodsorder_ReOrder.action');" class="rbut" value="还原订单"></td>
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
                              <th width="9%">订单类型</th>
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
                                          <span>订单编号：<b><a onclick="linkToInfo('/admin_Goodsorder_view.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}');" >${(goodsorder.order_id)?if_exists}</a></b></span>
                                         <span>类型：
	                                      <#if (goodsorder.is_sea)?if_exists=='1'>
										  直邮订单
										  <#else>
										  保税订单
										</#if>
										</span>
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
                                         <td width="9%" rowspan="10">
                                         <#if (goodsorder.is_sea)?if_exists=='1'>
										 	直邮订单
										<#else>
											保税订单
										</#if>
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
	                                          <div class="wltd" >
		                                           <#if goodsorder.order_state!='2'&& goodsorder.order_state!='0'&& goodsorder.order_state!='1'>
												         <a  onclick="linkToInfo('/admin_Goodsorder_admin_order_Logistics.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}');" >
															    查看物流
														</a>
											      </#if>
											  </div>
                                         </td>
                                         
                                         <td  width="8%"  class="gray" rowspan="9">
                                          <#if goodsorder.order_para!=''>
									   		${(goodsorder.order_para)?if_exists}
									     </#if><br/>
									    <a onclick="linkToInfo('/admin_Goodsorder_view.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}');" >订单详情</a>
										</td>
                                         <td   rowspan="10">	
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
	                                               <a  onclick="linkToInfo('/admin_Goodsorder_view.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}');" > 取消订单</a>
	                                               </p>
										    <#elseif goodsorder.order_state=='2' || goodsorder.order_state=='6'>
										    	<p>
	                                               <a  onclick="linkToInfo('/admin_Goodsorder_chooseSendMode.action','chb_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}');" >发货</a>
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
  <@s.hidden  name="order_state_s" />
  <@s.hidden  name="starttime_s"/>
  <@s.hidden  name="endtime_s"/>
  <@s.hidden  name="sell_cust_s"/>
  <@s.hidden id="goods_order_num" name="goods_order_num"/>
  <@s.hidden id="goods_order_ids" name="goods_order_ids"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Goodsorder_reList.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td class="searchDiv_td">订单编号:</td>
			<td><@s.textfield name="order_id_s"  /></td>
		</tr>
		<tr>
			<td class="searchDiv_td">交易状态:</td>
			<td><@s.select  name="order_state_s"  list="commparaList" listValue="para_key" 
					   listKey="para_value" headerKey="" headerValue="--请选择--" />   </td>
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
<!-- 搜索区域结束-->

<!-- 打印票据 end-->
</body>

</html>
