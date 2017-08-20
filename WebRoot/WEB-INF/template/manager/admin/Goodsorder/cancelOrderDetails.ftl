<html>
  <head>
    <title>取消订单详情</title>
    <link href="/include/admin/css/goodsorder.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
	<script type="text/javascript" src="/include/common/js/copytext.js"></script>
<script type="text/javascript" src="/include/common/js/jquery.alert.js"></script>
<link href="/include/common/css/alert.css" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="/include/admin/js/goodsorder.js"></script>
  </head>
  <body>
  <@s.form action=""  method="post" validate="true" id="tradeForm">
<div class="postion">	当前位置:订单管理 > 订单列表 > 取消订单详情</div>
<div class="rtdcont">
	<div class="rttable">
	   <!--列表页table的数据-->	 
	 <#assign payname="">
      <#list paymentList as plist>
         <#if goodsorder.pay_id==plist.pay_id>
              <#assign payname=plist.pay_code>
         </#if>
    </#list> 
     <#if payname=="wxpay" && ifwxcer=="0">
		   <div class="main_cont">
			 		<div class="main_content">
						 		<div>
						 			<b><img src="/include/admin/images/wxcetip.gif"  height="20px" width="20px" />  系统检测提示</b>
									 <div class="oper_seo_div">      
								       	   
													 <div style="margin-top:10px;background-color:yellow;" class="oper_seo_div">
													     <font color="red">由于微信支付退款接口的安全性要求,所以第一次提交退款到微信操作,都需要先安装微信证书! 请先安装证书!</font>
													     <a href="/include/commonfiles/weixin.doc" style="color:blue;">如何安装证书?</a>
													    <a href="/include/commonfiles/WXCER.zip" style="color:blue;">点击下载微信证书</a>
													</div>
										
									 </div>
								</div>
			 		</div>
			</div>		
	</#if>
 	<div class="main_cont">
 		<div class="main_content">
 			<div>
 			<b>商品信息</b>
			 <div id="oper_seo_div" class="oper_seo_div">      
		       	 <table cellSpacing=1 cellPadding=1 width="100%;" class="tabdiv">   
		           <tr >
		             <th class="gth" width="15%;" style='line-height:25px;'>商品编号</th>
		             <th class="gth" width="40%;">
		            	商品名称
		             </th>
		             <th class="gth" width="15%;">
		            	价格
		             </th>
		             <th class="gth" width="10%;">
		            	购买数量
		             </th>
		             <th class="gth" width="10%;">
		            	小计
		             </th>
		           </tr>
		           <#list orderdetailList as orderlist>
		           <tr>
		             <td class="gtd">${(orderlist.goods_no)?if_exists}</td>
		             <td align="center">
		             <#if (orderlist.goods_attr)?if_exists>
				      		 <#assign g_attr=orderlist.goods_attr>
				      		  <#if ((g_attr)?index_of("<br>") > (-1))>
				      		       <#assign g_attr =g_attr.replace('<br>','')>
				      		  </#if>
				      		 </#if>
		            	${(orderlist.goods_name)?if_exists}(${(g_attr)?if_exists})
		             </td>
		              <td class="gtd">
		            		${(orderlist.goods_price)?if_exists?string("0.00")}
		             </td>
		              <td class="gtd">
		            		${(orderlist.order_num)?if_exists}
		             </td>
		              <td class="gtd">
		            		${(orderlist.goods_price*orderlist.order_num)?if_exists?string("0.00")}
		             </td>
		           </tr>
		           </#list>
				           
	            </table>
			 		</div>
			 	</div>
			 <br/>
			 <div>
			 <b>订单信息</b>
			 <div id="oper_seo_div" class="oper_seo_div">      
			       	 <table cellSpacing=1 cellPadding=1 width="100%;" class="tabdiv">   
					           <tr>
						             <td class="ordertd">订单号</td>
						             <td width="30%">
						            		${(goodsorder.order_id)?if_exists}
						             </td>
						             <td class="ordertd">订单状态</td>
						             <td>
						                <#list commparaList as comlist>
						                   <#if comlist.para_value==goodsorder.order_state> 
						                   ${(comlist.para_key)?if_exists}
						                   </#if>
						                </#list>
						            	
						             </td>
						            
					           </tr>
					           <tr>
						             <td class="ordertd">商品总金额</td>
						             <td>
						            	${(goodsorder.goods_amount)?if_exists?string("0.00")}
						             </td>
						             <td class="ordertd">下单日期</td>
						             <td width="30%">
						            	${(goodsorder.order_time)?if_exists}
						             </td>
						             
						             
					           </tr>
					           <tr>
						             <td class="ordertd">配送费用</td>
						             <td>
						            		${(goodsorder.ship_free)?if_exists?string("0.00")}
						             </td>
						             <td class="ordertd">支付方式</td>
						             <td>
						             <#if (goodsorder.pay_id)?if_exists!="">
						               <#list paymentList as plist>
					                      <#if goodsorder.pay_id==plist.pay_id>
					                          ${(plist.pay_name)?if_exists}
					                      </#if>
					                   </#list>
					                  <#else>
					                    --
					                 </#if> 
						             </td>
						          
					           </tr>
					       
					            <tr>
						             <td class="ordertd">保价费用</td>
						             <td>
						             <#if goodsorder.insured!=null&&goodsorder.insured!=''>
						            	 ${(goodsorder.insured)?if_exists}
						            	<#else>
						            	-
					            	</#if>
						               
						             </td>
						             <td class="ordertd">交易流水号</td>
						             <td>
					                     <#if goodsorder.pay_trxid!=null&& goodsorder.pay_trxid !=''>
					                          ${(goodsorder.pay_trxid)?if_exists}
					                     <#else>
					                       -
					                     </#if>
						             </td>
						             
					           </tr>
					           <tr>
					           		 <td class="ordertd">订单总金额</td>
						             <td >
						              ${(goodsorder.tatal_amount)?if_exists?string("0.00")}
						             </td>
						            
						                 <td class="ordertd">配送方式</td>
						             <td>
						                <#list sendmodeList as slist>
						                     <#if goodsorder.send_mode==slist.smode_id>
						                          ${(slist.smode_name)?if_exists}
						                     </#if>
						                </#list>
						             </td> 
					           </tr>
					           
					           <tr>
					           		 <td class="ordertd">积分抵扣金额</td>
						             <td >
						                ${(dedu_amount)?if_exists}
						             </td>
						            <td class="ordertd">订单商品总重量</td>
						             <td>
                                       <#if goodsorder.order_weight!=null&&goodsorder.order_weight!=''>
						            	      ${(goodsorder.order_weight)?if_exists}
	                                       <#else>
							            	-
						            	</#if>                  
						             </td>
					           </tr>
					           
					           <tr>
					                  
					                  
						             <td class="ordertd">积分支付</td>
						             <td>
						             	 <#if goodsorder.integral_use!=null&&goodsorder.integral_use!=''>
							            	${goodsorder.integral_use?if_exists}
							            	<#else>
							            	-
						            	</#if>
						            	
						              </td>
						              <td class="ordertd">可得积分</td>
						             <td>
						            	${(goodsorder.present_integral)?if_exists}                 
						             </td>
						             
							   </tr>
							   
							   <tr>
							   
							        
							           <td class="ordertd">余额支付</td>
						             <td>
						             	 <#if goodsorder.balance_use!=null&&goodsorder.balance_use!=''>
							            	￥${goodsorder.balance_use?if_exists}
							            	<#else>
							            	-
						            	</#if>
						            	
						              </td>
					                  <td class="ordertd">关税</td>
						             <td>
						             	 <#if goodsorder.taxes!=null&&goodsorder.taxes!=''>
							            	￥${(goodsorder.taxes)?if_exists}
							            	<#else>
							            	-
						            	</#if>
						            	
						              </td>
					                  
						             
							   </tr>
							   
							   <tr>
							     
							      <td class="ordertd">在线支付</td>
						             <td>
						               <#if (goodsorder.order_type)?if_exists!='2'>
						             	  <#if goodsorder.order_state=='1'||goodsorder.order_state=='0'>
					           			   ￥0
					           			  <#else>
					           			    ￥${((goodsorder.last_pay))}
					           			  </#if>
					           			<#else>
					           			    ￥0
						            	</#if>
						              </td>
							     
							     <td class="ordertd">订单备注</td>
					             <td>
					             	 <#if goodsorder.mem_mark!=null&&goodsorder.mem_mark!=''>
						            	${(goodsorder.mem_mark)?if_exists}
						            	<#else>
						            	-
					            	</#if>
					            	
					              </td>
							   
							   </tr>
		            </table>
				 </div>
			 </div>
			 
			  <br/>
			 <div>
			 <b>购买人信息</b>
			 <div id="oper_seo_div" class="oper_seo_div">      
			       	 <table cellSpacing=1 cellPadding=1 width="100%;" class="tabdiv">   
					           <tr>
						             <td class="ordertd">会员用户名</td>
						             <td width="30%;">
						            	${(memberuser.user_name)?if_exists}
						             </td>
						             <td class="ordertd">姓名</td>
						             <td width="30%;">
						            	${(memberuser.real_name)?if_exists}
						             </td>
					           </tr>
					           <tr>
					                 <td class="ordertd">联系电话</td>
						             <td>
						            	${(memberuser.phone)?if_exists}
						             </td>
						             <td class="ordertd">手机</td>
						             <td>
						            	${(memberuser.cellphone)?if_exists}
						             </td>
					           </tr>
					           <tr>
					           <td class="ordertd">MSN</td>
						             <td>
						            	${(memberuser.msn)?if_exists}
						             </td>
						             <td class="ordertd">QQ</td>
						             <td>
						                <#if (memberuser.qq)?if_exists>
						            	${(memberuser.qq)?if_exists}
								        </#if>
						             </td>
					           </tr>
					           
		            </table>
					</div>
			 </div>
			   <br/>
			 <div>
			 <b>收货人信息</b>
			 <div id="shouhuo" class="oper_seo_div">      
							       	 <table cellSpacing=1 cellPadding=1 width="100%;" class="tabdiv">   
									           <tr>
										             <td id="name" class="ordertd">收货人姓名</td>
										             <td width="30%;">
										             ${(goodsorder.consignee)?if_exists}
										             </td>
										             <td id="mobile" class="ordertd">联系手机</td>
										             <td width="30%;">
										              ${(goodsorder.mobile)?if_exists}
										             </td>
									           </tr>
									           <tr>
									           <td id="phone" class="ordertd">联系电话</td>
										             <td>
										              ${(goodsorder.telephone)?if_exists}
										             </td>
										             <td id="post" class="ordertd">邮政编码</td>
										             <td>
										              ${(goodsorder.zip_code)?if_exists}
										             </td>
									           </tr>
									           <tr>
									           <td id="addres" class="ordertd">收货地区</td>
										             <td >
											             <#if (order_area)?if_exists>
											            	${(order_area)?if_exists}
											            	<#else>
											            	-
											            	</#if>
										             </td>
										              <td></td>
										             <td></td>
									           </tr>
									            <tr>
									           		<td id="addres" class="ordertd">详细收货地址</td>
										             <td >
										              ${(goodsorder.buy_address)?if_exists}
										             </td>
										             <td></td>
										             <td></td>
									           </tr>
						            </table>
					</div>
					
			 </div>
			 <br/>
			 	 <div>
			 <b>退单信息</b>
			 <div id="shouhuo" class="oper_seo_div">
							       	 <table cellSpacing=1 cellPadding=1 width="100%;" class="tabdiv">   
									           <tr>
										             <td id="name" class="ordertd">取消原因</td>
										             <td cols=“3”>
										                  ${(order_cancel_reason)?if_exists}&nbsp;&nbsp;${(order_cancel_date)?if_exists}
										             </td>
									           </tr>
						            </table>
					</div>
			 </div>
			 
			 
			  <br/>
			<div>
 			<b>订单日志</b>
			 <div id="oper_seo_div" class="oper_seo_div">      
		       	 <table cellSpacing=1 cellPadding=1 width="100%;" class="tabdiv">   
		           <tr>
		             <th class="gth" width="15%;"  style='line-height:25px;'>序号</th>
		              <th class="gth" width="10%;">
		                 任务
		             </th>
		             <th class="gth" width="15%;">
		                 时间
		             </th>
		             <th class="gth" width="10%;">
		            	操作人
		             </th>
		             <th class="gth" width="30%;">
		                备注
		             </th>
		           </tr>
		           <#list ordertransList as otranslist>
		           <tr>
		             <td class="gtd">${otranslist_index+1}</td>
		             <td class="gtd">
		                <#list commparaList as comlist>
		                   <#if comlist.para_value==otranslist.order_state> 
		                   ${(comlist.para_key)?if_exists}
		                   </#if>
		                  </#list>
		             </td>
		              <td class="gtd">
		            		${(otranslist.trans_time)?if_exists}
		             </td>
		             <td align="center">
		            	${(otranslist.user_name)?if_exists}
		             </td>
		              <td class="gtd">
		            		${(otranslist.reason)?if_exists}
		             </td>
		           </tr>
		           </#list>
				           
	            </table>
			 </div>
			 <br/>

			 <b></b>
			 <div id="oper_seo_div" class="oper_seo_div">      
		       	 <table cellSpacing=1 cellPadding=1 width="100%;" >   
		           <tr>
		              <th align="left">
		                <#if cancel_state?if_exists=="0">
		                  <@s.radio name="cancel_state" list=r"#{'1':'同意取消订单','2':'拒绝取消订单'}"  onclick="getSellerState(this.value);"/>
		                <#elseif cancel_state?if_exists=="1">
		                   <#if goodsorder.pay_id!=""&& goodsorder.pay_id!=null>
		                       <font color="green">同意取消订单且系统向第三方支付平台申请退款成功</font>
		                    <#else>
		                         会员取消订单。
		                    </#if>
		                <#elseif cancel_state?if_exists=="2">
		                     <font color="red">不同意取消订单</font>&nbsp;&nbsp;原因：${cancel_reject_reason?if_exists}
		                </#if>
		             </th>
		           </tr>
		            <tr  class="rejectReason" style="display:none;">
		             <td>
		                <br/>
		                 原因：
		                 <br/><br/>
		                 <#if cancel_state?if_exists=="0">
		                     <@s.textarea name="cancel_reject_reason" id="cancelrejectreason" cssClass="txtinput" cssStyle="width:300px;height:100px;" />
		                 <#elseif cancel_state?if_exists=="1">
		                     ${cancel_reject_reason?if_exists}
		                 </#if>
		             </td>
		           </tr>
	            </table>
			 	</div>
			 	
			 	</div>
			 <br/>
			 <div>
			 <div class="fixbuttom" style="margin-top:10px;">
			   <@s.token/>
			    <@s.hidden name="goodsorder.order_id"/>  
               <@s.hidden name="order_id_str" value="${(goodsorder.order_id)?if_exists}"/>
               <@s.hidden name="payCode" id="payCode" value="${(payment.pay_code)?if_exists}"/>
               <@s.hidden name="refund_type" value="1"/>
                <@s.hidden name="sysdesc" id="sysdesc" />
			    <@s.hidden name="sysvalue" id="sysvalue" />
               <@s.hidden name="goodsorder.order_state"/>
               <@s.hidden name="goodsorder.pay_trxid"/>
               <@s.hidden name="origQryId" value="${(goodsorder.pay_trxid)?if_exists}"/>
               <@s.hidden name="goodsorder.tatal_amount"/>
               <@s.hidden name="front_back_tip"  id="front_back_tip" value="0"/>
             <#if cancel_state?if_exists=="0">
                   <#if payment.pay_code=="wxpay">
                       <input type="button"  onclick="handleCancelOrder('/admin_Goodsorder_dealWXpayCancelOrder.action');"  value="提交微信支付退款" style="display:none;" class="wxpay_tip"/>
                   <#elseif payment.pay_code=="alipay" || payment.pay_code=="alipaywap">
                       <input type="button"  onclick="handleCancelOrder('/mall-alipay-refund.html');"  value="提交支付宝退款" style="display:none;" class="alipay_tip"/>
                   <#elseif payment.pay_code=="goldpay">
                       <input id="goldpay" type="button"  onclick="handleCancelOrder('/admin_Goodsorder_dealGoldCancelOrder.action');" value="账户余额退款" style="display:none;" class="goldpay_tip"/>
                   <#elseif payment.pay_code == "unionpay">
                       <input type="button"  onclick="handleCancelOrder('/admin_Goodsorder_dealUnionpayCancelOrder.action');"  value="提交银联在线退款" style="display:none;" class="unionpay_tip"/>
                   <#elseif payment.pay_code == "integral">
                       <input type="button"  onclick="handleCancelOrder('/admin_Goodsorder_dealIntegralCancelOrder.action');"  value="积分退款" style="display:none;" class="integral_tip"/>
                   </#if>
                   
		     </#if>
		     <input type="button"  onclick="rejectCancleOrder()" value="确定" style="display:none;" class="reject_tip"> 
			 <input type="button"  onclick="linkToInfo('/admin_Goodsorder_cancelOrderList.action','cancel_state_s=<#if cancel_state?if_exists=='0'>0<#else>3</#if>');" value="返回列表">
			 <span>${refund_message}</span>
			</div>
  		 </div>
   </div>
	</div>
	<div class="clear"/>
</div>
<div class="clear"></div>
</@s.form>
  
<div style="display:none;"  id="setPay"  >
	        <table  style="text-align: center;">
	          <tr>
	           	<td style="color:#EA9359;padding-top:10px;">退款完成前请不要关闭此窗口, 退款后请点击下面按钮</td>
	          </tr>
	           <tr>
	           	<td style="padding-top:25px;font-size:14px;text-align: center;">
	           	<a href="/admin_Goodsorder_cancelOrderList.action">退款成功</a>&nbsp;&nbsp;
	           	<a href="/admin_Goodsorder_cancelOrderList.action">关闭返回</a></td>
	          </tr>
	       </table>
</div>  
  
</body>
</html>

