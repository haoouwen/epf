<html>
  <head>
    <title>退货详情</title>
       <link href="/include/bmember/css/refund.css" rel="stylesheet" type="text/css">
       <script type="text/javascript" src="/include/bmember/js/goodsorder.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
		 	getLOgisticsInfo();
		});
		</script>
  </head>
  <body>
  <@s.form action="/bmall_Refundapp_buyDelivery.action" method="post" validate="true" >
 <!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>退货</span></h2>
        
        <!----> 
        <div class="cancelDiv">
           <!---->
            <div class="cancelTop">
            	 <p>
                	<span> 退货编号：<b class="mgreen">${(refundapp.return_no)?if_exists} </b></span>
                    <span><b>状态：</b><b class="mgreen"> 
                    	<#if (refundapp.refund_state)?if_exists=='0'>等待处理
				    	<#elseif (refundapp.refund_state)?if_exists=='1'>退款成功
				    	<#elseif (refundapp.refund_state)?if_exists=='2'>退款失败
				    	<#elseif (refundapp.refund_state)?if_exists=='5'>撤销申请
				    	<#elseif (refundapp.refund_state)?if_exists=='3'>等待买家发货
				    	<#elseif (refundapp.refund_state)?if_exists=='4'>等待商家确认收货
				    	</#if>
                    
                   </b></span>
                    <span>订单金额：<b class="mred">￥${(goodsorder.tatal_amount)?if_exists}</b></span>
                    <span>退款金额：<b class="mred">￥ ${(refundapp.refund_amount)?if_exists}</b></span>
                 <p class="linep">亲爱的客户，您提交的申请我们会尽快为您处理，请耐心等待  </p>
            </div>
            
            
            
                        <#if (refundapp.refund_state)?if_exists=='0'><!--等待处理-->
                        
                            <!--退款流程-等待--> 
				            <div class="thStep"><span class="wait"></span></div>
				            <!---->
				            <div class="stateDiv">
				               <div class="stateCont">
				                  <p class="wp"><img src="/include/bmember/images/waitPic.gif" align="absmiddle"><b>等待商家处理退货申请</b></p>
			                      <p>申请时间：
			                        <#if exchange.buy_date?if_exists?length gt 19>
								      ${exchange.buy_date[0..18]?if_exists}
								      <#else>
								      ${exchange.buy_date?if_exists}
								      </#if>	
			                      </p>
				                  <p>如果商家同意，退货申请将达成,商家会提供相应的地址请注意查看状态并填写物流信息</p>
				                  <p>如果商家拒绝，将需要您修改退货申请</p>
				               </div>
				               <div class="lineDiv">
				                  您可以： <a href="/bmall_Refundapp_buylist.action?parentMenuId=4637243721&selli=7215368137">返回我的订单</a>
				               </div>
				            </div>
                        
				    	<#elseif (refundapp.refund_state)?if_exists=='1'><!--退款成功-->
				            <!--退货流程-退货成功--> 
				            <div class="thStep"><span class="success"></span></div>
				            <div class="stateDiv">
				               <div class="stateCont">
				                  <p class="wp"><img src="/include/bmember/images/successPic.gif" align="absmiddle"><b>退货成功</b></p>
				                  <p>账号：<b >${buy_name?if_exists}</b></p>
				                  <p>退款金额：<b class="mgreen">${(refundapp.refund_amount)?if_exists}</b>元</p>
				                  <p>退款时间：${(refundapp.seller_date)?if_exists}</p>
				               </div>
				               <div class="lineDiv">
				               <a href="/bmall_Refundapp_buylist.action?parentMenuId=4637243721&selli=7215368137">返回我的订单</a>
				               </div>
				            </div>
				    	
				    	<#elseif (refundapp.refund_state)?if_exists=='2'><!--退款失败-->
				    	
				    	
				    	  <!--退货流程-退货失败--> 
				            <div class="thStep"><span class="fail"></span></div>
				            <div class="stateDiv">
				               <div class="stateCont">
				                  <p class="wp"><img src="/include/bmember/images/failPic.gif" align="absmiddle"><b>退货失败</b></p>
				                  <p>处理时间：${(refundapp.seller_date)?if_exists}</p>
				                  <p>失败类型：<b class="mred">商家拒绝退货!</b></p>
				                  <p>理由：${(refundapp.seller_reason)?if_exists}</p>
				               </div>
				               <div class="lineDiv">
				                  您可以：<a  href="###" onclick="linkToInfo('/bmall_Refundapp_appRefundGoods.action','order_id=${refundapp.order_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')" >重新退货申请</a> <a href="/bmall_Refundapp_buylist.action?parentMenuId=4637243721&selli=7215368137">返回我的订单</a>
				               </div>
				            </div>
				    	
				    	<#elseif (refundapp.refund_state)?if_exists=='5'><!--撤销申请-->
				    	 
				    	  <!--退货流程-退货失败--> 
				            <div class="thStep"><span class="fail"></span></div>
				            <div class="stateDiv">
				               <div class="stateCont">
				                  <p class="wp"><img src="/include/bmember/images/failPic.gif" align="absmiddle"><b>退货失败</b></p>
				                  <p>撤销时间：${(refundapp.info_date)?if_exists}</p>
				                  <p>失败类型：<b class="mred">您撤销退货申请!</b></p>
				                  <p>理由：${(refundapp.seller_reason)?if_exists}</p>
				               </div>
				               <div class="lineDiv">
				                  您可以：<a  href="###" onclick="linkToInfo('/bmall_Refundapp_appRefundGoods.action','order_id=${refundapp.order_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')" >重新退货申请</a> <a href="/bmall_Refundapp_buylist.action?parentMenuId=4637243721&selli=7215368137">返回我的订单</a>
				               </div>
				            </div>
				    	
				    	<#elseif (refundapp.refund_state)?if_exists=='3'><!--等待买家发货-->
					            <!----> 
					            <!--退货流程-同意退货--> 
					            <div class="thStep"><span class="agree"></span></div>
					            <!---->
					            <div class="stateDiv">
					               <div class="stateCont">
					                  <p class="wp"><img src="/include/bmember/images/successPic.gif" align="absmiddle"><b>商家同意您的退货申请，请填写物流信息</b></p>
					                   <p>收货人：<b class="mred">${(buyeraddr.consignee)?if_exists} </b></p>
					                  <p>收货地址：${(buyeraddr.area_attr)?if_exists}  ${(buyeraddr.address)?if_exists}</p> 
					                  <p>手机：${(buyeraddr.cell_phone)?if_exists}</p>
					                  <p>电话：${(buyeraddr.phone)?if_exists}</p>
					               </div>
					               
					               <div class="linesDiv">
					               
					                    <div class="opeDiv">
					             
					                        <table width="100%" cellpadding="0" cellspacing="0" class="detTab">
					                             <tr>
					                              <th width="16%"><span>*</span>物流公司</th>
					                              <td>
					                              <@s.select  name="order_send_mode"  list="sendmodeList" listValue="smode_name" listKey="smode_id" /> 
					                              </td>
					                            </tr>
					                            <tr>
					                              <th width="16%"><span>*</span>物流单号</th>
					                              <td>
					                               <@s.textfield   name="order_send_num" cssClass="aifText"  maxlength="20" />
					                               <@s.fielderror><@s.param>order_send_num_tip</@s.param></@s.fielderror></td>
					                            </tr>
					                            <tr>
					                              <th width="16%">.</th>
					                              <td>
					                                <@s.hidden name="refundapp.trade_id" id="refundapp_id" value="${(refundapp.trade_id)?if_exists}"/>
					                                <@s.submit value="提 交" cssClass="submitbut" />
					                              </td>
					                            </tr>
					                        </table>
					                     </div>
					               </div>
					            </div>
				    	<#elseif (refundapp.refund_state)?if_exists=='4'><!--等待商家确认收货-->
				    	    
					            <!--退货流程-等待商家收货--> 
					            <div class="thStep"><span class="waitMj"></span></div>
					            <div class="stateDiv">
					               <div class="stateCont">
					                  <p class="wp"><img src="/include/bmember/images/waitPic.gif" align="absmiddle"><b>等待商家确认收货</b></p>
					                  <p>如果确认收货，退货申请达成，商家将用余额的形式退款到您的会员账号上，请注意查收</p>
					               </div>
					            </div>
					            <!---->
					            <div class="opeDiv padDiv">
					            	<h3>退货物流跟踪</h3>
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
								                     <#if refundapp.send_mode==slist.smode_id>
								                          <#assign zfsend=slist.smode_name>
								                     </#if>
								                </#list>
								                <#if (zfsend)?if_exists>
								            	${(zfsend)?if_exists}
								            	<#else>	
								            	 快递
								            	</#if></span><span>运单号：${refundapp.send_num?if_exists}</span>
								            	<span>(以上信息数据由 <a href="http://www.kuaidi100.com" target="_blank" style="color:#0579C6;">快递100</a>  提供，您可以查看每条信息及其来源)</span></td>
						                  </tr>
						                </table>
					            </div>
				    	</#if>
            <!---->
            <!---->
             <div class="opeDiv padDiv">
                <h3>申请退货详情</h3>
                <table width="100%" cellpadding="0" cellspacing="0" class="detTab">
                     <tr>
                      <th width="16%">售后类型</th><td>退货</td>
                    </tr>
                  <tr><th width="16%">订单编号</th><td><b class="mgreen">${(refundapp.order_id)?if_exists}</b></td>
                  <tr><th width="16%">申请时间</th><td><b >${(refundapp.buy_date)?if_exists}</b></td>
                    <tr>
                      <th width="16%">退款金额</th>
                      <td><b class="mred">${(refundapp.refund_amount)?if_exists}</b>&nbsp;元&nbsp;( 所退款的金额会以余额的形式打到您的会员账号上)</td>
                    </tr>
                    <tr>
                      <th width="16%">退货原因</th>
                      <td>  <#list commparaList as com>
			              <#if com.para_value=refundapp.buy_type>
			              	${(com.para_key)?if_exists}
			              </#if>
		              </#list></td>
                    </tr>
                    <tr><th width="16%">问题描述</th><td>${(refundapp.buy_reason)?if_exists}</td>
                    <tr><th width="16%">凭证图片</th><td>  <#if refundapp.img_path!=null>
			      		<#list refundapp.img_path?if_exists?split(',') as pic>
			            <img src="${pic?if_exists}"  width="90"  height="90">
						</#list>
						</#if>
						</td>
                    </tr>
                </table>
             </div>
            <!---->
            <div class="opeDiv padDiv">
                <h3>退货商品详情</h3>
                <table width="100%" cellpadding="0" cellspacing="0" class="opeTab">
                	<tr>
                        <th width="10%">商品图片</th>
                        <th width="32%">商品名称</th>
                        <th width="10%">购买数量</th>
                        <th width="10%">购买价格(元)</th>
                        <th width="20%">购买时间</th>
                        <th >状态</th>
                    </tr>
                  <#list detailList as dlist>
	                    <tr>
							<td>
								 <#if dlist.img_path!=''>
						  			<#if ((dlist.img_path)?index_of(",") > (-1))>      			
						  				<#assign startpos = "${dlist.img_path?if_exists}"?index_of(',')>
						                <#if ((startpos-1)>-1)>
						                    <#assign img =(dlist.img_path)[(0)..(startpos-1)]>
						                 </#if>
						             <#else> 
						             		<#assign img =dlist.img_path>
						             </#if> 
						             	<a href="/mall-goodsdetail-${(dlist.goods_id)?if_exists}.html" target="_blank">
						      				<img src="${(img)?if_exists}" width='50' height='50'>
						      			</a>
					      		 <#else>
					      		 	<a href="/mall-goodsdetail-${(dlist.goods_id)?if_exists}.html" target="_blank">
					      				<img src="${(cfg_nopic)?if_exists}" width='50' height='50'>
					      			</a>
					      		 </#if>	
							</td>
	                        <td><a href="/mall-goodsdetail-${(dlist.goods_id)?if_exists}.html" target="_blank">${(dlist.goods_name)?if_exists}</a></td>    
	                        <td><b><#if dlist.order_num?if_exists>${dlist.order_num}<#else>-</#if></b></td>
	                        <td><b class="mred"><#if dlist.goods_price?if_exists>${(dlist.goods_price*dlist.order_num)?if_exists}<#else>-</#if></b></td>
	                        <td>${(dlist.order_time)?if_exists} </td>
	                         <td>
	                          <#if dlist.orderdetail_state=='1'>
	                       			 <span class="mred" >退款中</span>
		                        <#elseif dlist.orderdetail_state=='2'>	
		                         		 退款关闭
		                        <#elseif dlist.orderdetail_state=='3'>
		                       			 <span class="mgreen" > 退款成功</span>
		                        <#elseif dlist.orderdetail_state=='4'>
		                          		<span class="mred" >退货中</span>
		                        <#elseif dlist.orderdetail_state=='5'>
		                        		  退货关闭
		                        <#elseif dlist.orderdetail_state=='6'>
		                         	    <span class="mgreen" >退货成功</span>
		                        <#elseif dlist.orderdetail_state=='7'>
		                         		 <span class="mred" >换货中</span>
		                        <#elseif dlist.orderdetail_state=='8'>
		                          		换货关闭
		                        <#elseif dlist.orderdetail_state=='9'>
		                       		    <span class="mgreen" >  换货成功</span>
		                       <#elseif dlist.orderdetail_state=='0'>
		                       		   --
		                        </#if>
	                        </td>
	                     </tr>
                    </#list>
               </table>
             </div> 
             <!---->
             	<#if (refundapp.refund_state)?if_exists=='1'><!--退款成功-->
			            <div class="opeDiv padDiv">
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
				                     <#if refundapp.send_mode==slist.smode_id>
				                          <#assign zfsend=slist.smode_name>
				                     </#if>
				                </#list>
				                <#if (zfsend)?if_exists>
				            	${(zfsend)?if_exists}
				            	<#else>	
				            	 快递
				            	</#if></span><span>运单号：${refundapp.send_num?if_exists}</span>
				            	<span>(以上信息数据由 <a href="http://www.kuaidi100.com" target="_blank" style="color:#0579C6;">快递100</a>  提供，您可以查看每条信息及其来源)</span></td>
			              </tr>
			            </table>
			            </div>
				</#if>
            <!---->
            <div class="opeDiv padDiv">
              <h3>售后服务规则</h3>
              <table width="100%" cellpadding="0" cellspacing="0" class="detTab">
              	<tr class="reTr">
                   <th width="16%">退换类别</th>
                   <th width="39%">退换原因</th>
                   <th width="13%"><p class="">是否支持退换货</p>
                                    <p>(签收7天内)</p>
                   </th>
                   <th width="13%"><p>是否支持换货</p>
                                   <p>(签收8-15天)</p>
                   </th>
                  <th width="29%"><p>是否支持保修</p>
                    			  <p>(签收15天以上在质保期内)</p>
                   </th>
                </tr>
                <!---->
                <tr class="reTd">
                   <td>性能故障</td>
                   <td>商品使用过程中，无法满足售前介绍的使用基本需求</td>
                   <td>是</td>
                   <td>是</td>
                   <td>是</td>
                </tr>
                <!---->
                <tr class="reTd">
                   <td>缺少配件</td>
                   <td>实际收到商品附件与网页介绍包装清单中的内容不符</td>
                   <td>是</td>
                   <td>是</td>
                   <td class="redtd">否</td>
                </tr>
                <!---->
                <tr class="reTd">
                   <td>物流损</td>
                   <td>因物流运输导致商品损坏、残缺，无法正常使用</td>
                   <td>是</td>
                   <td>是</td>
                   <td class="redtd">否</td>
                </tr>
                <!---->
                <tr class="reTd">
                   <td>商品实物与网站不符</td>
                   <td>实际收到 商品实物与网页介绍规格参数中的内容不符</td>
                   <td>是</td>
                   <td>是</td>
                   <td class="redtd">否</td>
                </tr>
                <!---->
                <tr class="reTd">
                   <td>错买、多买</td>
                   <td>在商品（包装及附件）完好的前提下</td>
                   <td>是</td>
                   <td>是</td>
                   <td class="redtd">否</td>
                </tr>
                
              </table>
              
            </div>
            <!---->
            <div class="opeDiv padDiv">
            	<h3>服务说明</h3>
                <div class="instructions">
                   <p>1. 附件赠品，退换货时请一并退回。</p>
                   <p>2. 关于物流损：请您在收货时务必仔细验货，如发现商品外包装或商品本身外观存在异常，需当场向配送人员
                    指出，并拒收整个包裹；如您在收货后发现外观异常，请在收货24小时内提交退换货申请。如超时未申请，将无法受理。</p>
                   <p>3. 关于商品实物与网站描述不符：EPF-020保证所出售的商品均为正品行货，并与时下市场上同样主流新品一致。
                    但因厂家会在没有任何提前通知的情况下更改产品包装、产地或者一些附件，所以我们无法确保您收到的货物与商城图片、产地、附件说明完全一致。</p>
                   <p>4. 如果您在使用时对商品质量表示置疑，您可出具相关书面鉴定，我们会按照国家法律规定予以处理。</p>
                </div>
            </div>
            <!---->
                
        </div>
        
   </div>
   
  </div>
 <@s.hidden name="logistics_query" id="logistics_query_id"/>
 <@s.hidden name="kuai_company" id="kuai_company_id"/>
<div class="clear"></div>
 </@s.form>
<!--cont结束-->
</body>
</html>

