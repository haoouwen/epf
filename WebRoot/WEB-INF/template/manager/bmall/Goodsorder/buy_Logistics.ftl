<html>
<head>
<title>物流跟踪</title>
<script type="text/javascript" src="/include/bmember/js/goodsorder.js"></script>
   <script type="text/javascript">
	$(document).ready(function(){
	    getLOgisticsInfo();
	});
  </script>
</head>
<body>
<@s.form action="/bmall_Goodsorder_sellerDelivery.action" method="post" id="tradeForm">
<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>物流跟踪</span></h2>
        <!----> 
        <div class="cancelDiv">
        	<!---->
        	
            <div class="cancelTop">
            	 <p>
                	<span>订单编号：<b class="mred">${(goodsorder.order_id)?if_exists}</b></span><span>物流公司：
                       <#assign zfsend="">
		                 <#list sendmodeList as slist>
		                     <#if goodsorder.send_mode==slist.smode_id>
		                          <#assign zfsend=slist.smode_name>
		                     </#if>
		                </#list>
		                <#if (zfsend)?if_exists>
		            	${(zfsend)?if_exists}
		            	<#else>	
		            	 --
		            	</#if></span><span>运单号：${goodsorder.send_num?if_exists}</span>
                 </p>
            </div>
            <!-- 显示物流-->
            <div class="opeDiv">
                
	             <table width="100%" cellpadding="0" cellspacing="0" class="opeTab">
	              <tr> 
		              <td class="spanTd">
	                       <div class="logDycon" id="show_log"></div>
	                  </td>
                  </tr>
                 <table width="100%" cellpadding="0" cellspacing="0" class="opeTab">
                  <tr>
                       <td  class="spanTd">
		            	<span>(以上信息数据由 <a href="http://www.kuaidi100.com" target="_blank" style="color:#0579C6;">快递100</a>  提供，您可以查看每条信息及其来源)</span></td>
                  </tr>
                </table>
            </div>
            <!---->
            <div class="opeDiv padDiv">
            	<h3>商品清单</h3>
                <table width="100%" cellpadding="0" cellspacing="0" class="opeTab">
                	<tr>
                    	<th width="15%">商品编号</th>
                        <th width="10%">商品图片</th>
                        <th width="45%">商品名称</th>
                        <th width="15%">数量</th>
                        <th width="15%">价格(元)</th>
                    </tr>
                    <#list orderdetailList as orderlist>
                    <tr>
                       <td>${(orderlist.goods_id)?if_exists}</td>
                       <td><a href="/mall-goodsdetail-${orderlist.goods_id?if_exists}.html"><img src="${orderlist.list_img?if_exists}"></a></td>
                       <td><a href="/mall-goodsdetail-${orderlist.goods_id?if_exists}.html">
                           <#if (orderlist.goods_attr)?if_exists>
				      		<#assign g_attr=orderlist.goods_attr>
				      		  <#if ((g_attr)?index_of("<br>") > (-1))>
				      		       <#assign g_attr =g_attr.replace('<br>','')>
				      		  </#if>
				      		 </#if>
				       <#if orderlist.goods_name?if_exists!=""> ${(orderlist.goods_name)?if_exists}<#if g_attr!=null && g_attr!='  '&& g_attr!='   '&& g_attr!=''&& g_attr!=' '>(${g_attr?if_exists})</#if></#if>
                       </a></td>   
                       <td>${(orderlist.order_num)?if_exists}</td>
                       <td>${(orderlist.goods_price)?if_exists}</td>
                    </tr>
                     </#list>
                    <tr>
                       <td colspan="5" class="totalTd">付款总额：
                       <#if (goodsorder.goods_amount)?if_exists>
		            		<b class="mred">￥${(goodsorder.tatal_amount)?if_exists}</b> 元
		            	<#else>
		            		-
		            	</#if>
                       </td>
                    </tr>
                    
                </table>
            </div>
            <!---->
            <div class="opeDiv padDiv">
              <h3>订单信息</h3>
              <table width="100%" cellpadding="0" cellspacing="0" class="detTab">
              	<tr>
              	<th width="15%">订单编号</th>
              	<td width="85%">${(goodsorder.order_id)?if_exists}</td>
              	</tr>
                <tr>
                <th>支付</th>
                <td>
                <span>支付方式：
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
                </span>
                
                 </td>
                 </tr>
                <tr>
                <th>配送</th>
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
                   ￥${(goodsorder.ship_free)?if_exists} 
                 <#else>(免运费)
		        </#if></span>
               
                </td>
                </tr>
                <tr><th>时间</th>
                <td>
                <span>下单时间：
                <#if (goodsorder.order_time)?if_exists>
		            		${(goodsorder.order_time)?if_exists}
		            	<#else>
		            		--
		         </#if>
                </span>
               <#if goodsorder.pay_time!="">
                <span>支付时间：
                    <#if (goodsorder.pay_time)?if_exists?length lt 19>
		            		${(goodsorder.pay_time)?if_exists}
		            	<#else>
		            	   ${(goodsorder.pay_time)?if_exists[0..18]}
		             </#if>
                </span>
                </#if>
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

<div class="clear"></div>
</@s.form>
</body>
</html>

