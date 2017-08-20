<div  style="display:none;" id="buyaddressinfo" >
      <@s.form id="ModifybuyerForm" action="/admin_Goodsorder_ModifyBuyeraddr.action"  method="post" class="searchDiv" >
	        <table >
	         <tr>
	           	<td  class="table_name" width="70px">订单信息：</td>
           		<td id="borderinfo_id"></td>
	          </tr>
	          <tr>
	           	<td  class="table_name" width="70px">原收货人：</td>
           		<td id="bshouhuoren_id"></td>
	          </tr>
	            <tr>
	           	<td  class="table_name" width="70px">新收货人：</td>
           		<td>
           		      <table >
				         <tr><td  >收&nbsp;货&nbsp;人：</td><td ><@s.textfield name="consignee" cssStyle="width:120px;"  maxLength="10"   id="consignee_id"  /></td></tr> 
				         <tr><td  >身&nbsp;份&nbsp;证：</td><td ><@s.textfield name="identitycard" cssStyle="width:120px;"  maxLength="18"   id="identitycard_id"  /></td></tr> 
				          <tr> <td  >地&nbsp;&nbsp;&nbsp;&nbsp;区：</td>
					          <td >
					              <table>
						             <tr>
						                  <td width="300px">
						             	         <div id="areaDiv" > </div>
						             	 </td>
						             	 </tr> 
						             </table>	 
					         </td>
				          </tr>
				           <tr><td   >详细地址：</td><td ><@s.textarea id="buy_address_id" name="buy_address"  cssStyle="width: 199px; height: 31px;" /></td></tr>
				          <tr> <td   > 电&nbsp;&nbsp;&nbsp;&nbsp;话：</td><td ><@s.textfield name="mobile" cssStyle="width:120px;"  maxLength="12"  onblur="checkMobile(this);"     id="mobile_id"  /></td></tr>
				       </table>
           		</td>
	          </tr>
	           <tr>
	           <td  ></td>
	           	<td  style="color:red;" >注：以上信息不填写,表示不修改!</td>
	          </tr>
	          <tr>
	           <td  colspan="2" align="center">
	             <@s.hidden name="goods_order_ids" id="b_order_id" />
	             <@s.hidden  name="order_state_s" />
	           	<input type="button" onclick="modifyBuerAdress()" value="确认修改"/>
	           	<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('buyaddressinfo')"/>
	           </td>
	          </tr> 
	       </table>
	     </@s.form>
</div>