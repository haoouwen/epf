<div  style="display:none;" id="printepress" >
      <@s.form id="printDeliveryForm" action="/admin_Goodsorder_confirmDeliveryGoodsOrder.action"  method="post" class="searchDiv" >
	        <table >
	         <tr id="repeat_tip" style="display:none;" >
	           <td  colspan="2" align="center">
	            <font color="red">重要提示：您已打印过运单，请注意核对，勿重复发货。</font>
	           </td>
	          </tr> 
	         <tr>
	           	<td  class="table_name">订单信息：</td>
           		<td id="send_orderinfo_id"></td>
	          </tr>
	          <tr>
	           	<td  class="table_name">收货人信息：</td>
           		<td id="send_shouhuoren_id"></td>
	          </tr>
	            <tr>
	           	<td  class="table_name">快递公司：</td>
           		<td> <@s.select list="sendmodeList" listKey="smode_id" onchange="loadsendmodelenth('print_sendmode_id','print_send_num_id');" 
           		listValue="smode_name"  name="order_send_mode" style="width:120px" id="print_sendmode_id"/></td>
	          </tr>
	          <tr>
	           	<td  class="table_name">运单号：</td>
           		<td>
           		<@s.textfield  name="print_send_num" id="print_send_num_id" cssStyle="width:150px;"/>
           		 <span>(长度为<span id="s_length"></span>位)</span>
                <span class="errorSpan" style="display:none;" id="ydhtip"></span>
           		</td>
	          </tr>
	          <tr>
	           <td  colspan="2" align="center">
	             <@s.hidden name="goods_order_ids" id="send_order_id" />
	             <@s.hidden name="printState" id="printState" />
	             <@s.hidden id="ptype" />
	           	<input type="button" onclick="printKuaiDiDan()" value="打印"/>
	           	<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('printepress')"/>
	           </td>
	          </tr> 
	       </table>
	     </@s.form>
</div>