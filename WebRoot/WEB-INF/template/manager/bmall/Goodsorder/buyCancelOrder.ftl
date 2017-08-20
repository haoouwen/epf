<html>
<head>
<title>取消订单记录</title>
</head>
<body>


<@s.form action="/bmall_Goodsorder_buyCancelOrder.action" method="post" id="tradeForm">

<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>取消订单记录</span></h2>
        <!----> 
        <div class="cancelDiv">
        	<!---->
            <div class="cancelTop">
            	 <p>
                	<span><b>订单编号：${goodsorder.order_id}</b></span>
                    <span><b>状态：</b>
                    <b class="mgreen">
                    <#if cancelorder.order_state?if_exists=="0">   
                       退款处理
                    <#elseif cancelorder.order_state?if_exists=="1">
                       完成        
                    </#if>   
                     </b>
                    </span>
                    <span><b>支付金额：</b><b class="mred">￥${goodsorder.tatal_amount?if_exists}</b></span>
                 </p>
                 <p class="linep">
                     <#if cancelorder.order_state?if_exists=="0">   
                        亲爱的客户，我们正在退款处理，请耐心等待
                    <#elseif cancelorder.order_state?if_exists=="1">
                        亲爱的客户，订单取消处理完成    
                    </#if>
                 </p>
            </div>
            <!---->
            <#if cancelorder.order_state?if_exists=="0">   
                 <!--取消订单-取消处理-->
           		 <div class="cancelOrder"><span class="qxOrder"></span></div>
            <#elseif cancelorder.order_state?if_exists=="1">
	              <!--取消订单-完成-->
                   <div class="cancelOrder"><span class="success"></span></div>
	         <#else>
	            <!-- 取消订单-取消处理-->
               <div class="cancelOrder"><span class="wait"></span></div>
            </#if>
            <!---->
            <div class="opeDiv">
            	<h3>取消申请进度</h3>
                <table width="100%" cellpadding="0" cellspacing="0" class="opeTab">
                	<tr>
                    	<th width="25%">处理时间</th>
                        <th width="55%">处理信息</th>
                        <th width="20%">操作人</th>
                    </tr>
                   <#list cancelorderList as cancel>
                    <tr>
                      <td><i>${cancel.trans_time?if_exists}</i></td>
                      <td class="opeTd">${cancel.reason?if_exists}</td>
                      <td>
                       <#if cancel.cust_id!="0">   
                           客户
                       <#else>
                           客服
                       </#if>    
                      </td>
                    </tr>
                   </#list> 
                </table>
            </div>
            <!---->
            <div class="opeDiv padDiv">
              <h3>申请单详细信息</h3>
              <table width="100%" cellpadding="0" cellspacing="0" class="detTab">
              	<tr><th width="15%">订单编号</th><td width="85%">${goodsorder.order_id?if_exists}</td></tr>
                <tr><th>支付明细</th><td></p>支付总额：<b class="mred">￥${goodsorder.tatal_amount?if_exists}</b></p><p>
                <#assign zfpay="">
	                 <#list paymentList as plist>
	                     <#if goodsorder.pay_id==plist.pay_id>
				            	<#assign zfpay=plist.pay_name>
	                     </#if>
	                </#list>
	                <#if (zfpay)?if_exists>
	            	${(zfpay)?if_exists}
	            	<#else>
	            </#if>
                <b>￥${goodsorder.tatal_amount?if_exists}</b></p></td></tr>
                <tr><th>取消原因</th><td>${cancelorder.buy_reason?if_exists}</td></tr>
                <tr><th>联系方式</th><td><span>联系人：${goodsorder.consignee?if_exists}</span><span>联系电话：${goodsorder.mobile?if_exists}</span></td></tr>
                <#if (cancelorder.order_state)?if_exists=="1">
                   <tr><th>处理意见</th><td><span>同意</span></td></tr>
                <#elseif (cancelorder.order_state)?if_exists=="2">
                   <tr><th>处理意见</th><td><span>不同意 &nbsp;&nbsp; 原因：${cancelorder.reject_reason?if_exists}</span></td></tr>
                </#if>
              
              </table>
              
            </div>
            
            <div class="opeDiv padDiv">
              <h3>使用遇到问题？</h3>
              <table width="100%" cellpadding="0" cellspacing="0" class="detTab">
              	<tr>
              	   <td>问： 为什么同一张银行卡支付，退款时银行处理成功时间均有差异？</td>
              	</tr>
              	<tr>
              	   <td>答： 银行处理时间根据支付方式不同会存在差异，比如同一张银行卡，选择快捷支付方式或网银支付，申请退款则银行处理时间不同，故退款成功银行到账的时间存在差异。</td>
              	</tr>
              	<tr>
              	   <td>问： 退款显示“完成”，但我的银行账户查询不到退款？</td>
              	</tr>
              	<tr>
              	   <td>答： 显示【完成】之后，银行会在1-2日内完成入账，退款入账后，银行一般无短信通知。请在退款成功最后提示的时间点（含入账时间），主动查询您的银行账户，若超过该时间点还未收到该笔退款，请联系银行进行核实，具体退款到账时间以银行完成入账的时间点为准。</td>
              	</tr>
              	<tr>
              	   <td>问： 退款是否可以加急处理？</td>
              	</tr>
              	<tr>
              	   <td>答： 不能，退款均由系统进行处理，并非人工，无法加急，请您耐心等待。</td>
              	</tr>
                 	<tr>
              	   <td>问： 为什么我的退款银行需要一段时间才能受理？</td>
              	</tr>
              	<tr>
              	   <td>答： 由于各银行的处理方式不同，导致银行受理时间存在差异，请耐心等待。</td>
              	</tr>
                
              </table>
              
            </div>
            
        </div>
        
   </div>
   
  </div>
  <div class="clear"></div>
</div>
 <@s.hidden name="is_virtual"  value="${(is_virtual)?if_exists}"/>
 <@s.hidden name="goods_order_id"  value="${(goodsorder.order_id)?if_exists}"/>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>    
</@s.form>
</body>
</html>



