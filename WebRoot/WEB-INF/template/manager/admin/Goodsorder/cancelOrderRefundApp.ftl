<!-- 取消订单 start-->
<div  style="display:none;" id="cancelorderinfo" >
      <@s.form id="tradecancelForm" action=""  method="post" class="searchDiv" >
	        <table >
	         <tr>
	           	<td  class="table_name" width="23%">订单信息：</td>
           		<td id="orderinfo_cancel_id"></td>
	          </tr>
	          <tr>
	           	<td  class="table_name">收货人：</td>
           		<td id="shouhuoren_cancel_id"></td>
	          </tr>
	           <tr>
	           	<td  class="table_name">支付信息：</td>
           		<td id="payinfo_cancel_id"></td>
	          </tr>
	           <tr>
	           <td  ></td>
	           	<td  style="color:red;" >注：取消订单之后,付款金额将按照原支付方式退回!</td>
	          </tr>
	          <tr>
	           <td  colspan="2" align="center">
	               <@s.hidden name="goodsorder.order_id" id="cg_order_id"/>  
	               <@s.hidden name="order_id_str" id="co_order_id"/>
	               <@s.hidden name="payCode"  id="cpay_code_id"/>
	               <@s.hidden name="refund_type" value="1"/>
	               <@s.hidden name="goodsorder.order_state" id="corder_state_id"/>
	               <@s.hidden name="goodsorder.pay_trxid" id="cpay_trxid_id"/>
	               <@s.hidden name="goodsorder.tatal_amount" id="ct_amount_id"/>
	               <@s.hidden name="is_return_page"  id="is_return_page" />
	               <@s.hidden name="is_sea"  id="is_sea" />
	               <@s.hidden name="front_back_tip"  id="front_back_tip" value="1"/> 
                    <span id="wxpay_id" style="display:none;">
                       <input type="button"  onclick="handleCancelOrderadmin('/admin_Goodsorder_adminDealWXpayCancelOrder.action');"  value="取消订单并微信退款">
                    </span>
                   <span id="alipay_id" style="display:none;">
                       <input type="button"  onclick="handleCancelOrderadmin('/mall-alipay-refund.html');"  value="取消订单并支付宝退款">
                    </span>
                   <span id="goldpay_id" style="display:none;">
                       <input type="button"  onclick="handleCancelOrderadmin('/admin_Goodsorder_adminGoldCancelOrder.action');" value="取消订单并余额退款">
                    </span>
                   <span id="alipaywap_id" style="display:none;">
                       <input type="button" onclick="handleCancelOrderadmin('/mall-alipay-refund.html');"  value="取消订单并支付宝退款">
                    </span>
                    <span id="unionpay_id" style="display:none;">
                       <input type="button" onclick="handleCancelOrderadmin('/admin_Goodsorder_adminDealUnionpayCancelOrder.action');"  value="取消订单并银联在线退款">
                    </span>
                    <span id="integral_id" style="display:none;">
                       <input type="button" onclick="handleCancelOrderadmin('/admin_Goodsorder_adminIntegralCancelOrder.action');"  value="取消订单并积分退款">
                    </span>
			     
	           	<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('cancelorderinfo')"/>
	           </td>
	          </tr> 
	       </table>
	     </@s.form>
</div>