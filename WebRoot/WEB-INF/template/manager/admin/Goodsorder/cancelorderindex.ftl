<html>
  <head>
    <title>订单操作-取消订单列表</title>
    <link href="/include/admin/css/goodsorder.css" rel="stylesheet" type="text/css">
    <link href="/include/admin/css/order.css" rel="stylesheet" type="text/css">
   <script type="text/javascript" src="/include/admin/js/commonTop.js"></script>
  </head>
  <body>
<@s.form action="/admin_Goodsorder_cancelOrderList.action" method="post"  id="indexForm">

<!--当前位置-->
	<div class="postion">当前位置：商城管理  > 订单管理  > 取消订单列表 </div>
<!--当前位置结束-->
	
	<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>订单编号:</td>
			<td><@s.textfield name="order_id_s" cssStyle="width:160px;"  /></td>
	        <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
	<div class="rtdcont">
	   
<!--后台table-->
   
       <div class="orderDiv" id="orderDivId">
              
                <div class="orderH2">
                  <ul>
                    <li <#if cancel_state_s=='0' || cancel_state_s==''>class="selli"</#if>><a href="/admin_Goodsorder_cancelOrderList.action?cancel_state_s=0">待处理订单</a></li>
                   <li   <#if cancel_state_s=='3'>class="selli"</#if>><a href="/admin_Goodsorder_cancelOrderList.action?cancel_state_s=3">取消订单</a></li>
                  </ul>
                </div>
                
                <div class="tabDiv">
                     <!--1-->
                     <div class="orderTab" id="oftabId">
                        <table width="100%" cellspacing="0" cellpadding="0">
                             <tr>
					             <th width="13%">订单号</th>
					             
					             <th width="22%">订单商品</th>
					             
					             <th width="10%">订单金额(元)</th>
					             
					              <th width="10%">支付状态</th>
					             
					             <th width="10%">支付方式</th>
					             
					             <th width="10%">处理状态</th>
					             
					             <th width="10%">提交时间</th>
					             
					             <th width="5%">操作</th>
					        </tr> 
                            <#list goodsorderList as goodsorder>
                             <tr>
							 	    <td align="center">   <a onclick="linkToInfo('/admin_Goodsorder_cancelOrderDetails.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&detail_return_flage=1');"  title="取消详情">${goodsorder.order_id?if_exists}</a></td>
							    	<td align="center">
					                          <#assign num=0>
					                         <#assign goods_id="">
					                        <#list detailList as detail>
					                       <#assign num=num+1>
										       <#if ((detail.order_id)?if_exists)==((goodsorder.order_id)?if_exists)>
									           <#if detail.remark!=null && detail.remark?index_of("###") gt -1>
											      		<#list detail.remark?if_exists?split('###') as remarks>
											      						<#if remarks_index==0>
																			<#assign remark_id = remarks>
																		</#if>
																		<#if remarks_index==1>
																			<#assign remark_name = remarks>
																		</#if>
																		
														</#list>
											   </#if>
											   		 <#assign goodsdetailurl="">
											   		 <#assign goods_id="${detail.goods_id?if_exists}">
											   		 <#if (goodsorder.order_type)?if_exists=='1'>
							                          <#assign goodsdetailurl="/mall-goodsdetail-${detail.goods_id?if_exists}.html">
							
													<#elseif (goodsorder.order_type)?if_exists=='2'>
							
							                          <#assign goodsdetailurl="/mall-goodsdetail-${detail.goods_id?if_exists}.html">
							
													<#elseif (goodsorder.order_type)?if_exists=='3'>
																				
														<#assign goodsdetailurl="/spikedetail-${remark_id}.html">						
							
													<#elseif (goodsorder.order_type)?if_exists=='4'>
																				
							                           <#assign goodsdetailurl="/mall-combodetail-${remark_id}.html">
							
													<#elseif (goodsorder.order_type)?if_exists=='5'>
																				
							                           <#assign goodsdetailurl="/mall/groupbuy!groupDetail.action?groupid=${remark_id}">
																				
													</#if>
					                      		     <#if detail.list_img!=''>
										      			<a href="${goodsdetailurl?if_exists}" target="_blank" title="${detail.goods_name?if_exists}"><img src="${( detail.list_img)?if_exists}" class="f_left" align="absmiddle" width="35px" height="35px"></a>
										      		 <#else>
										      			<a href="${goodsdetailurl?if_exists}" target="_blank" title="${detail.goods_name?if_exists}"><img src="${(cfg_nopic)?if_exists}"  class="f_left" align="absmiddle" width="35px" height="35px"></a>
										      		 </#if>
					                       </#if>
						      		      </#list>	
							    	</td>
							    	<td align="center">
							    	     <font style="font-weight:bold;"  color="blue"> ${goodsorder.tatal_amount?if_exists}</font>
							    	     <#if goodsorder.is_webapp_order=='1'>
							    	      <br/>
							    	      <img src="/include/common/images/sjdd.jpg" />
							    	   </#if>
							    	</td>
							    	<td align="center">
							                <#if goodsorder.pay_id!="">
							            	<font color="green"> 已支付</font>
							            	<#else>
							            	<font color="red">未支付</font>
							            	</#if>
							    	</td>
							    	
							    	
							    	<td align="center">
							    	 <#if goodsorder.pay_name!=''>
							    	      <font color="green"> ${goodsorder.pay_name}</font>
							    	   <#else>
							    	      --
							    	   </#if>
							    	</td>
							    
							    	<td align="center">                 
							    	<#if (goodsorder.cancel_state)?if_exists=="0">  
					                      <font color="red">已提交,等待处理</font>
					                 <#elseif (goodsorder.cancel_state)?if_exists=="1">
					                      <font color="green">已处理,成功取消</font>
					                 <#elseif (goodsorder.cancel_state)?if_exists=="2">
					                      <font color="red">已处理,拒绝取消</font>
					                 </#if>
					                 </td>
							    	<td align="center"><#if goodsorder.buy_date?if_exists?length lt 18>${goodsorder.buy_date?if_exists}<#else>${goodsorder.buy_date?if_exists[0..10]}${goodsorder.buy_date?if_exists[10..18]}</#if></td>
							    	<td align="center">
						                 <a onclick="linkToInfo('/admin_Goodsorder_cancelOrderDetails.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&detail_return_flage=3');"  title="取消详情">
							     		  <#if goodsorder.cancel_state?if_exists=="0">
							     		    <img src="/include/common/images/bj.gif" />
							     		  <#else>
							     		    <img src="/include/common/images/view.gif" />
							     		  </#if>
							     	   </a>
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
  <@s.hidden  name="buy_cust_s"/>
  <@s.hidden  name="sell_cust_s"/>
  <@s.hidden  name="cancel_state_s"/>
  <@s.hidden id="goods_order_num" name="goods_order_num"/>
  <@s.hidden id="goods_order_ids" name="goods_order_ids"/>
 <!--表单框隐藏域存放-->  
</@s.form>

</body>

</html>
