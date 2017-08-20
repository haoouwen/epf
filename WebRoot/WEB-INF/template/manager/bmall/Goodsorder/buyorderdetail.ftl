<html>
<head>
<title>订单详细</title>
 <#include "/include/uploadInc.html">
<script type="text/javascript" src="/include/bmember/js/goodsorder.js"></script>
<script type="text/javascript">
$(document).ready(function(){
 	getLOgisticsInfo();
});
</script>
</head>
<body>
<@s.form action="/bmall_Goodsorder_buyCancelOrder.action" method="post"  id="tradeForm"  >
<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>订单详情</span></h2>
        <!----> 
        <div class="cancelDiv">
        	<!---->
        	
            <div class="cancelTop">
            	 <p>
                	<span>订单编号：<b class="mred">${(goodsorder.order_id)?if_exists}</b></span>
                	 <span>订单类型：
	                    <b class="mgreen">
		           	     <#if (goodsorder.is_sea)?if_exists=='1'>
						 	直邮订单
						<#elseif (goodsorder.is_sea)?if_exists=='0'>
							保税区订单
						</#if>
	                    </b>
	                 </span>
		              <span><b>订单金额：</b>
                    <#if (goodsorder.goods_amount)?if_exists>
		            		<b class="mred">￥${(goodsorder.tatal_amount)?if_exists}</b>
		            <#else>
		            		-
		            </#if>
	                  </span>
                    <span><b>状态：</b>
                    <b class="mgreen">
                    
                     <#if goodsorder.order_state=='0'>
	                        <#if (goodsorder.pay_time)?if_exists&&(goodsorder.pay_trxid)?if_exists>
				             <!-- 取消订单-已支付-->
				                    <b>交易关闭</b>
				              <#else>
				               <!-- 取消订单-未付款-->
				           			<b>取消订单</b>
				             </#if>
	              <#else>
		              		  <#if (goodsorder.is_sea)?if_exists=='0' && (goodsorder.is_kjtsuccess)?if_exists=='1'>
				                     <#list kjtcommparaList as clist>
					                     <#if goodsorder.kjtorder_state==clist.para_value>
								            	${clist.para_key}
					                     </#if>
					                </#list>
					          <#else>
		                	         <#list commparaList as clist>
					                     <#if goodsorder.order_state==clist.para_value>
								            	${clist.para_key}
					                     </#if>
					                </#list>
		                     </#if>
	                      <#if goodsorder.order_para!=''>
					   		<b>${(goodsorder.order_para)?if_exists}</b>
					    </#if>
	              </#if>
                    
                    
                    
                     
                    </b>
                    </span>
                   
                 </p>
                 <p class="linep">
                     <#if goodsorder.order_state?if_exists=="1">   
                         亲爱的客户，您的订单已生成，请尽快付款
                     <#elseif goodsorder.order_state?if_exists=="2">
                         活动期间，由于订单量较大，发货会延迟3-5个工作日，请您耐心等待！
                     <#elseif goodsorder.order_state?if_exists=="3">
                          亲爱的客户，我们商品已发货，请耐心等待
                      <#elseif goodsorder.order_state?if_exists=="4">
                          亲爱的客户，您申请退款的订单，正在处理，请耐心等待
                     <#elseif goodsorder.order_state?if_exists=="5" ||goodsorder.order_state?if_exists=="6">
                         亲爱的客户，您的退款已经处理，请查看结果
                     <#elseif goodsorder.order_state?if_exists=="7" ||goodsorder.order_state?if_exists=="8">
                         亲爱的客户，欢迎您下次购买
                     <#elseif goodsorder.order_state?if_exists=="9">
                          亲爱的客户，您已付定金，请在 ${starttime[0..18]?if_exists} - ${threedate[0..18]?if_exists} 时间内付尾款
                     </#if>   
                 </p>
            </div>
	             <#if goodsorder.order_state?if_exists=="0">
		             <!-- 取消订单-完成-->
		             
		              <#if (goodsorder.pay_time)?if_exists&&(goodsorder.pay_trxid)?if_exists>
		             <!-- 取消订单-已支付-->
		              <div class="stepDiv">  <h3 class="fosh3"></h3></div>
		              <#else>
		               <!-- 取消订单-未付款-->
		             <div class="cancelOrder"><span class="success"></span></div>
		             </#if>
		             
	            <!-- 操作步骤显示-->
	            <#elseif   goodsorder.is_clearance?if_exists=="2" && goodsorder.order_state?if_exists=="5">
				            <!--海外直发订单_退款完成-->
				            <div class="hwzfOrderStep"><span class="tkwc"></span></div>	          
	            <#elseif  goodsorder.order_state?if_exists=="4" ||goodsorder.order_state?if_exists=="5" ||goodsorder.order_state?if_exists=="6">
	                <!-- 退款/退货 操作步骤显示-->
		            <div class="stepDiv">
		                     <!-- 除了预售订单之外 -->
				             <#if goodsorder.order_state?if_exists=="4">   
				                 <h3 class="tsh3"></h3>
				             <#elseif goodsorder.order_state?if_exists=="5" ||goodsorder.order_state?if_exists=="6">
				                 <h3 class="fosh3"></h3>
				             </#if>  
		            </div>
	            <#else>
		                 <#if goodsorder.order_type!='6' && goodsorder.is_sea=='1'>
		                     <!-- 普通订单操作显示 -->
			             <#if goodsorder.deliver_state?if_exists=="0"  && goodsorder.order_state?if_exists=="1" >   
			                  <!--海外直发订单_提交订单-->
            				 <div class="hwzfOrderStep"><span class="tjdd"></span></div>
            			 <#elseif goodsorder.deliver_state?if_exists=="0"  && goodsorder.order_state?if_exists=="2" >   
			                  <!--海外直发订单_提交订单-->
            				 <div class="hwzfOrderStep"><span class="tjdd"></span></div>	 
			             <#elseif goodsorder.deliver_state?if_exists!="0"  && goodsorder.order_state?if_exists=="2" >  
                             <!--海外直发订单_订单处理中-->
                             <div class="hwzfOrderStep"><span class="djcl"></span></div>
			             <#elseif goodsorder.order_state?if_exists=="3" && goodsorder.is_clearance?if_exists=="0">
				            <!--海外直发订单_已发货待通关-->
				            <div class="hwzfOrderStep"><span class="dtg"></span></div>
			             <#elseif goodsorder.order_state?if_exists=="3" && goodsorder.is_clearance?if_exists=="1">
				            <!--海外直发订单_通关成功发往您的收货地址-->
				            <div class="hwzfOrderStep"><span class="tgcg"></span></div>
                         <#elseif goodsorder.order_state?if_exists=="3" && goodsorder.is_clearance?if_exists=="2">
				            <!--海外直发订单_通关失败,货款将随后退还-->
				            <div class="hwzfOrderStep"><span class="thsb"></span></div>	
			             <#elseif goodsorder.order_state?if_exists=="7">
				            <!--海外直发订单_货物已签收-->
				            <div class="hwzfOrderStep"><span class="yqshw"></span></div>					            		            
			             <#elseif goodsorder.order_state?if_exists=="8">
				            <!--海外直发订单_完成-->
				            <div class="hwzfOrderStep"><span class="ddwc"></span></div>
			             </#if>  
		             <#elseif goodsorder.order_type!='6' && goodsorder.is_sea=='0'>
		                 <!-- 普通订单操作显示 -->
			             <#if  goodsorder.order_state?if_exists=="1" >   
			                  <!--保税订单_提交订单-->
            				 <div class="baoshuiOrderStep"><span class="tjdd"></span></div>
            			 <#elseif goodsorder.order_state?if_exists=="2" >   
			                  <!--保税订单_提交订单已支付-->
            				 <div class="baoshuiOrderStep"><span class="djcl"></span></div>	 
			             <#elseif goodsorder.is_kjtsuccess?if_exists!="1"  && goodsorder.order_state?if_exists=="2" >  
                             <!--保税订单_成功提交跨境通-->
                             <div class="baoshuiOrderStep"><span class="yfh"></span></div>
			             <#elseif goodsorder.order_state?if_exists=="3" >
				            <!--保税订单_保税区发往您的收货地址-->
				            <div class="baoshuiOrderStep"><span class="dtg"></span></div>
			             <#elseif goodsorder.order_state?if_exists=="7">
				            <!--保税订单_货物已签收-->
				            <div class="baoshuiOrderStep"><span class="tgcg"></span></div>					            		            
			             <#elseif goodsorder.order_state?if_exists=="8">
				            <!--保税订单_完成-->
				            <div class="baoshuiOrderStep"><span class="yqshw"></span></div>
			             </#if>  
		             
		             <#else>
		                <!-- 预售订单操作 -->
		                <#if goodsorder.order_state?if_exists=="1">   
		                       <!-- 预售订单_支付定金-->
            					<div class="ysOrderStep"><span class="subpay"></span></div>
			             <#elseif goodsorder.order_state?if_exists=="2">
			                   <!--预售订单_支付尾款-->
           					 <div class="ysOrderStep"><span class="wkPay"></span></div>
			             <#elseif goodsorder.order_state?if_exists=="3">
			                 	 <!--预售订单_等待收货-->
                                 <div class="ysOrderStep"><span class="waitGoods"></span></div>
			             <#elseif goodsorder.order_state?if_exists=="7" ||goodsorder.order_state?if_exists=="8">
			                   <!-- 预售订单_完成-->
            					<div class="ysOrderStep"><span class="success"></span></div>
            
			              <#elseif goodsorder.order_state?if_exists=="9">
			                  <!-- 预售订单_支付尾款-->
        					    <div class="ysOrderStep"><span class="djPay"></span></div>
			             </#if>  
		             </#if>
             </#if>
            <!-- 显示物流-->
            <#if goodsorder.order_state?if_exists!="1"&&goodsorder.order_state?if_exists!="2"&&goodsorder.order_state?if_exists!="9"&&goodsorder.order_state?if_exists!="0">
            
            <div class="opeDiv">
            	<h3>物流跟踪</h3>
                
	             <table width="100%" cellpadding="0" cellspacing="0" class="opeTab">
	              <tr> 
		              <td class="spanTd">
	                       <div class="logDycon" id="show_log"></div>
	                  </td>
                  </tr>
                 <table width="100%" cellpadding="0" cellspacing="0" class="opeTab">
                  <tr>
                       <td  class="spanTd"><span>送货方式：	
                       <#assign zfsend="">
		                 <#list sendmodeList as slist>
		                     <#if goodsorder.send_mode==slist.smode_id>
		                          <#assign zfsend=slist.smode_name>
		                     </#if>
		                </#list>
		                <#if (zfsend)?if_exists>
		            	${(zfsend)?if_exists}
		            	<#else>	
		            	 快递
		            	</#if></span><span>运单号：${goodsorder.send_num?if_exists}</span>
		            	<span>(以上信息数据由 <a href="http://www.kuaidi100.com" target="_blank" style="color:#0579C6;">快递100</a>  提供，您可以查看每条信息及其来源)</span></td>
                  </tr>
                </table>
            </div>
           </#if>
            <!---->
            <div class="opeDiv padDiv">
            	<h3>商品清单</h3>
                <table width="100%" cellpadding="0" cellspacing="0" class="opeTab">
                	<tr>
                        <th width="10%">商品图片</th>
                        <th width="30%">商品名称</th>
                         <th width="10%">规格</th>
                        <th width="10%">数量</th>
                        <th width="10%">价格(元)</th>
                         <th width="10%">优惠(元)</th>
                          <th width="10%">小计(元)</th>
                        <th width="10%">状态</th>
                    </tr>
                    
                    <#if goodsorder.order_type=='6'>
                    <#else>
	                       <!-- 普通订单-->
	                   <#list orderdetailList as orderlist>
	                    <tr>
	                       <td><a href="/mall-goodsdetail-${orderlist.goods_id?if_exists}.html" target="_blank" title="${orderlist.goods_name?if_exists}"><img src="${orderlist.list_img?if_exists}"></a></td>
	                       <td><a href="/mall-goodsdetail-${orderlist.goods_id?if_exists}.html" target="_blank" title="${orderlist.goods_name?if_exists}">
	                           <#assign g_attr="">
	                           <#if (orderlist.goods_attr)?if_exists&&orderlist.goods_attr!=''>
					      		<#assign g_attr=orderlist.goods_attr>
					      		  <#if ((g_attr)?index_of("<br>") > (-1))>
					      		       <#assign g_attr =g_attr.replace('<br>','')>
					      		  </#if>
					      		 </#if>
					       <#if orderlist.goods_name?if_exists!=""> ${(orderlist.goods_name)?if_exists}</#if>
	                       </a></td>   
	                       <td><#if g_attr!=null && g_attr!='  '&& g_attr!='   '&& g_attr!=''&& g_attr!=' '>${g_attr?if_exists}<#else>--</#if></td>
	                       <td>${(orderlist.order_num)?if_exists}</td>
	                       <td>${(orderlist.goods_price)?if_exists}</td>
	                       <td>${(orderlist.goods_price)?if_exists?number * (orderlist.order_num)?if_exists?number  - (orderlist.subtotal)?if_exists?number}</td>
	                       <td>${(orderlist.subtotal)?if_exists}</td>
	                        <td>
	                         <#if orderlist.orderdetail_state=='1'>
	                       			 <span class="mred" >退款中</span>
	                        <#elseif orderlist.orderdetail_state=='2'>	
	                         		 退款关闭
	                        <#elseif orderlist.orderdetail_state=='3'>
	                       			 <span class="mgreen" > 退款成功</span>
	                        <#elseif orderlist.orderdetail_state=='4'>
	                          		<span class="mred" >退货中</span>
	                        <#elseif orderlist.orderdetail_state=='5'>
	                        		  退货关闭
	                        <#elseif orderlist.orderdetail_state=='6'>
	                         	    <span class="mgreen" >退货成功</span>
	                        <#elseif orderlist.orderdetail_state=='7'>
	                         		 <span class="mred" >换货中</span>
	                        <#elseif orderlist.orderdetail_state=='8'>
	                          		换货关闭
	                        <#elseif orderlist.orderdetail_state=='9'>
	                       		    <span class="mgreen" >  换货成功</span>
	                       <#elseif orderlist.orderdetail_state=='0'>
	                       		   --
	                        </#if>
	                       </td>
	                    </tr>
	                     </#list>
                    </#if>
                    <tr>
                       <td colspan="8" class="totalTd">付款总额：
                       <#if (goodsorder.goods_amount)?if_exists>
		            		<b class="mred">￥${(goodsorder.tatal_amount)?if_exists}</b> 元
		            	<#else>
		            		-
		            	</#if>
                       </td>
                    </tr>
                </table>
            </div>
            
           <#if freegoodsList?if_exists && (freegoodsList?size gt 0)>
                <div class="opeDiv padDiv">
            	<h3>赠品信息</h3>
                <table width="100%" cellpadding="0" cellspacing="0" class="opeTab">
                	<tr>
                        <th width="10%">赠品图片</th>
                        <th width="30%">赠品名称</th>
                    </tr>
	                       <!-- 普通订单-->
	                   <#list freegoodsList as freegoods>
	                    <tr>
	                       <td>
	                           <#if freegoods.img_path!=''>
				      			 <img src="${(freegoods.img_path)?if_exists}" align="absmiddle" class="f_left" width='30' height='30'>
			      		      <#else>
			      				 <img src="${(cfg_nopic)?if_exists}" align="absmiddle" class="f_left" width='30' height='30'>
			      		      </#if>
	                       
	                       </td>
	                       <td align="left">
					           ${(freegoods.fg_name)?if_exists}
	                       </td>   
	                    </tr>
	                     </#list>
                </table>
            </div>
            </#if>
            
            
            <!---->
            <div class="opeDiv padDiv">
              <h3>订单信息</h3>
              <table width="100%" cellpadding="0" cellspacing="0" class="detTab">
              	<tr>
              	<th width="15%">订单编号</th>
              	<td width="85%"><b class="mred">${(goodsorder.order_id)?if_exists}</b></td>
              	</tr>
                <tr>
                <th>支付</th>
                <td>
               <#-- <span>支付方式：
	            	  <#assign zfpay="">
	                  <#list paymentList as plist>
	                     <#if goodsorder.pay_id==plist.pay_id>
				            	<#assign zfpay=plist.pay_name>
	                     </#if>
	                </#list>
	                <#if (zfpay)?if_exists>
	            	${(zfpay)?if_exists}
	            	<#else>
	            	--
	            	</#if>
                </span>-->
                
                 <#if (goodsorder.order_type)?if_exists=='6'>
		           <#else>
		             <span>支付金额：
	                   	   	<#if (goodsorder.goods_amount)?if_exists>
			            		<b class="mred">￥${(goodsorder.tatal_amount)?if_exists}</b>
			            	<#else>
			            		--
			            	</#if>
	                 </span>
           			 <span>积分支付：<b class="mred"> ${goodsorder.integral_use?if_exists} </b></span>
           			 <span>余额支付：<b class="mred">￥${goodsorder.balance_use?if_exists} </b></span>
           			 <span>其他支付：<b class="mred">￥
           			 <#if goodsorder.order_state=='1'||goodsorder.order_state=='0'>
           			 0
           			 <#elseif goodsorder.integral_use?if_exists?number gt 0>
           			  0 
           			 <#else>
           			 ${((goodsorder.last_pay))}
           			 </#if>
           			 </b> </span>
	              </#if>
	              </br>
	              <span>
                          支付方式：
                              <#if goodsorder.pay_id!=null && goodsorder.pay_id!="">
                                <#list paymentList as plist>
					                 <#if goodsorder.pay_id==plist.pay_id>
					                    <font color="red">${(plist.pay_name)?if_exists}</font>
					                 </#if>
					             </#list>                  
					             <#else>
					              <font color="grey">- -</font>
					             </#if>
                        </span>
                       <span>
                        支付时间：
                         <#if goodsorder.pay_time!="" && goodsorder.pay_time!=null>
                            <#if (goodsorder.pay_time)?if_exists?length lt 19>
                               <font color="red">${(goodsorder.pay_time)?if_exists}</font>                           
                             <#else>
                             <font color="red">${(goodsorder.pay_time)?if_exists[0..18]}</font>
                             </#if>
                             <#else>
                             <font color="grey">- -</font>
                           </#if>                       
                       </span>
                        <span>
                        支付流水号：
                         <#if goodsorder.pay_trxid!=""&& goodsorder.pay_trxid!=null>
                          <font color="red">${(goodsorder.pay_trxid)?if_exists}</font>
                         <#else>
                         <font color="grey">- -</font>
                        </#if>
                        </span>
                 </td>
                 </tr>
                <tr>
                  <th>积分</th>
                  <td>积分抵扣：<b class="mred">${(dedu_amount)?if_exists}</b>&nbsp;&nbsp;可得积分：<b class="mred">${(goodsorder.present_integral)?if_exists}</b> </td>
                </tr>
                <tr>
                <th>配送信息</th>
                <td>
                <span>配送方式：
                 	   	<#assign zfsend="">
		                 <#list sendmodeList as slist>
		                     <#if goodsorder.send_mode==slist.smode_id>
		                          <#assign zfsend=slist.smode_name>
		                     </#if>
		                </#list>
		                <#if (zfsend)?if_exists>
		            	${(zfsend)?if_exists}
		            	<#else>	
		            	 快递
		            	</#if>
                </span>
               
                <span>运费：
                 <#if (goodsorder.ship_free)?if_exists&&(goodsorder.ship_free)?if_exists!='0'>
                   <b class="mred">￥${(goodsorder.ship_free)?if_exists}</b> 
                 <#else>(免运费)
		        </#if></span>
               
                </td>
                </tr>
                <tr>
                <th>关税</th>
                <td>
                <span>关税：
                  <b class="mred">￥${(goodsorder.taxes)?if_exists}</b> 
                </span>
               
                </td>
                </tr>                
                <tr><th>下单时间</th>
                <td>
                <span>下单时间：
                <#if (goodsorder.order_time)?if_exists>
		            		${(goodsorder.order_time)?if_exists}
		            	<#else>
		            		--
		         </#if>
                </span>
                </td>
                </tr>
                <tr><th>订单备注</th>
                <td>
            	  <#if (goodsorder.order_mark)?if_exists>
            	  ${(goodsorder.order_mark)?if_exists}
            	 <#else>
            	   无
            	 </#if>
                </td>
                </tr>
              </table>
              
            </div>
            <!---->
            <div class="opeDiv padDiv">
              <h3>收货人信息</h3>
              <table width="100%" cellpadding="0" cellspacing="0" class="detTab">
              	<tr>
              	<th width="15%">收货人姓名</th>
              	<td width="85%">
              	    	   	<#if (goodsorder.consignee)?if_exists>
			            	${(goodsorder.consignee)?if_exists}
			                </#if>
              	</td>
              	</tr>
              	<tr><th>身份证</th><td>${(goodsorder.identitycard)?if_exists}</td></tr>
                <tr>
                <th>地址</th>
                <td>
                           <#if (order_area)?if_exists>
			            	${(order_area)?if_exists}
			            	</#if>
			            	<#if (goodsorder.buy_address)?if_exists>
			            	${(goodsorder.buy_address)?if_exists}
			            	</#if>
                </td>
                </tr>
                <tr><th>固定电话</th><td>手机号码：${(goodsorder.mobile)?if_exists}</td></tr>
                <tr><th>邮政编码</th><td>${(goodsorder.zip_code)?if_exists}</td></tr>
              </table>
            </div> 
            <!---->
            <div class="opeDiv padDiv">
              <h3>发票信息</h3>
              <table cellSpacing="0" cellPadding="0" width="100%;" class="detTab">   
          	    <#if (orderinvoice.invoice_type)?if_exists =='0'>
		           <tr>
		           		<th width="15%" align="center">发票类型</th>
			             <td>普通发票
			             </td>
		           </tr>
		           <tr>
		             <th width="15%" align="center">发票抬头</th>
			              <td>
			               ${(orderinvoice.look_up)?if_exists}
			             </td>
			         </tr>
			         <tr>
			             <th width="15%" align="center">发票内容</th>
			             <td>
		               <#if (orderinvoice.p_content)?if_exists>
		            	 ${(orderinvoice.p_content)?if_exists}
		            	 <#else>
		            	  - -
				        </#if>
			             </td>
		            </tr>
        	 <#elseif (orderinvoice.invoice_type)?if_exists =='1'>
	       <tr>
	       		<th width="15%" align="center"> 发票类型</th>
	             <td align="center" width="35%">增值发票
	             </td>
	       
	              <th width="15%" align="center">单位名称</th>
	               <td align="center" >
	               <#if (orderinvoice.company_name)?if_exists>
	        	  ${(orderinvoice.company_name)?if_exists}
	        	  <#else>
	        	     -
		          </#if>
	              </td>
	         </tr>
	       
	        <tr>
	       <th width="15%" align="center">注册地址</th>
	             <td align="center">
	              <#if (orderinvoice.re_address)?if_exists>
	        	 ${(orderinvoice.re_address)?if_exists}
	        	 <#else>
	        	 -
		        </#if>
	             </td>
	             <th width="15%" align="center">注册电话</th>
	             <td align="center">
	             <#if (orderinvoice.re_phone)?if_exists>
	        	 ${(orderinvoice.re_phone)?if_exists}
	        	 <#else>
	        	 -
		        </#if>
	             </td>
	       </tr>
	      <tr>
	       <th width="15%" align="center">开户银行</th>
	             <td align="center">
	              <#if (orderinvoice.bank_name)?if_exists>
	        	 ${(orderinvoice.bank_name)?if_exists}
	        	 <#else>
	        	 -
		        </#if>
	             </td >
	             <th width="15%" align="center">银行账户</th>
	             <td align="center">
	             <#if (orderinvoice.bank_id)?if_exists>
	        	 ${(orderinvoice.bank_id)?if_exists}
	        	 <#else>
	        	 -
		        </#if>
	             </td>
	       </tr>
	       <tr>
	       <th width="15%" align="center">发票内容</th>
	             <td align="center">
	              <#if (orderinvoice.z_content)?if_exists>
	        	 ${(orderinvoice.z_content)?if_exists}
	        	 <#else>
	        	 -
		        </#if>
	             </td>
	             <th width="15%" align="center">收票人姓名</th>
	             <td align="center">
	             <#if (orderinvoice.ticket_name)?if_exists>
	        	 ${(orderinvoice.ticket_name)?if_exists}
	        	 <#else>
	        	 -
		        </#if>
	             </td>
	       </tr>
	       <tr>
	       <th width="15%" align="center">收票人手机</th>
	             <td align="center">
	              <#if (orderinvoice.ticket_cell)?if_exists>
	        	 ${(orderinvoice.ticket_cell)?if_exists}
	        	 <#else>
	        	 -
		        </#if>
	             </td>
	             <th width="15%" align="center">收票人省份</th>
	             <td align="center">
	             <#if (orderinvoice.area_attr)?if_exists>
	        	 ${(orderinvoice.area_attr)?if_exists}
	        	 <#else>
	        	 -
		        </#if>
	             </td>
	       </tr>
	        <tr>
	         <th width="15%" align="center">纳税人识别码</th>
	             <td align="center">
	             <#if (orderinvoice.identifier)?if_exists>
	        	 ${(orderinvoice.identifier)?if_exists}
	        	 <#else>
	        	  - -
		        </#if>
	             </td>
	             <th width="15%" align="center">详细地址</th>
	             <td align="center"><#if (orderinvoice.address)?if_exists>
	        	     ${(orderinvoice.address)?if_exists}
	        	 <#else>
	        	  - -
		        </#if>
	             </td>
	          </tr>
              <tr>
	         <th width="15%" align="center">营业执照副本</th>
	             <td align="center">
	              <img src="${orderinvoice.license?if_exists}" onclick="showpicture('img_license');" height="50px" width="50px" />
	              <@s.hidden  id="img_license"  value="${orderinvoice.license?if_exists}"/>
	        	 </td>
	             <th width="15%" align="center">税务登记证副本</th>
	             <td align="center">
	        	     <img src="${orderinvoice.certificate?if_exists}"  height="50px" width="50px" onclick="showpicture('img_certificate');"/>
	        	     <@s.hidden  id="img_certificate"  value="${orderinvoice.certificate?if_exists}"/>
	             </td>
	          </tr>
              <#else>
              <tr>
               <th width="15%" align="center">发票类型</th>
               <td>
                 不开发票
               </td>
              </tr>  
         </#if>   
         </table>
            </div>
             <div class="opeDiv">
            	<h3>订单日记</h3>
                <table width="100%" cellpadding="0" cellspacing="0" class="opeTab">
                	<tr>
                    	<th width="10%">序号</th>
                        <th width="20%">操作内容</th>
                        <th width="30%">操作时间</th>
                    </tr>
                    <#list ordertransList as otranslist>
                    <tr>
                      <td><i>${otranslist_index+1}</i></td>
                      <td>${(otranslist.reason)?if_exists}</td>
                      <td>${(otranslist.trans_time)?if_exists}</td>
                      
                    </tr>
                   </#list>
                </table>
           </div>
           
            <div class="opeDiv padDiv">
              <table width="100%" cellpadding="0" cellspacing="0" class="detTab">
              	<tr>
	              	<td width="100%" align="center">
	              	<@s.token/> ${listSearchHiddenField?if_exists}
	              	<#if goodsorder.order_type=='7'>
	              	 <input type="button" class="submitbut" onclick="linkToInfo('/bmall_Goodsorder_postageList.action','order_state=${(goodsorder.order_state)?if_exists}');" value="返回列表"/>
				     <#else>
				     <input type="button" class="submitbut" onclick="linkToInfo('/bmall_Goodsorder_buyorderlist.action','order_state=${(goodsorder.order_state)?if_exists}');" value="返回列表"/>
				     </#if></td>
              	</tr>
              </table>
            </div>
        </div>
   </div>
 
           
  </div>
  <div class="clear"></div>
</div>
<@s.hidden name="goods_order_id" id="goods_order_id" value="${goodsorder.order_id?if_exists}"/>
<@s.hidden name="goods_order_state" id="goods_order_state"/>
 <@s.hidden name="logistics_query" id="logistics_query_id"/>
 <@s.hidden name="kuai_company" id="kuai_company_id"/>
<div class="clear"></div>
</@s.form>
</body>
</html>