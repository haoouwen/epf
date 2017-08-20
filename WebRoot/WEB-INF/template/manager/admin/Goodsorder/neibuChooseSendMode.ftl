<html>
  <head>
    <title>选择发货方式</title>
	    <link href="/include/admin/css/goodsorder.css" rel="stylesheet" type="text/css">
	    <link href="/include/admin/css/deliveryGoods.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="/include/admin/js/goodsorder.js"></script>
	   <script type="text/javascript">
	   $(document).ready(function(){
		    loadsendmodelenth("sendmode_id","order_send_num_id");	
     	})；
	</script>
  </head>
  <body>
<@s.form action="#" method="post"  id="tradeForm">
<div class="postion">当前位置:商城管理 > 订单管理 > 订单发货</div>
<div class="rtdcont">
	<div class="rttable">
	   <!--列表页table的数据-->	
	    <div class="main_cont">
 		    <div class="main_content">
 		                      <#if (goods_order_num)?if_exists==1>
 		                      <div class="deliveryStep">
			 			               <h2><span>订单基本信息</span></h2>
								        <div id="oper_seo_div" class="oper_seo_div">      
										   <div class="deStepcont">
									          <table width="100%" cellpadding="0" cellspacing="0" border="0">
										            <tr height="30px;">
										                <td>
												            <span>订单编号：${(goodsorder.order_id)?if_exists}</span>&nbsp;|&nbsp;
												            <span>订单总金额：${(goodsorder.tatal_amount)?if_exists}&nbsp;元</span>&nbsp;|&nbsp;
												            <span>下单时间：${(goodsorder.order_time)?if_exists}</span>
												        </td>
										            </tr>
										          </table>
										   </div>
								 		</div>
						        <div>
 		                     </#if>
			 			      <div class="deliveryStep">
			 			               <h2><span>选择发货方式</span></h2>
							        <div  class="oper_seo_div">      
									   <div class="deStepcont">
								          <p>
								             <input type="radio" name="sendMode" id="ordinaryMode" checked="checked" value="1" onclick="chooseMode('1');"/>普通物流
								             <@s.select id="order_send_mode"  name="order_send_mode"  list="sendmodeList" listValue="smode_name" listKey="smode_id"/>
								             &nbsp;&nbsp;运单号：
								             <@s.textfield  name="order_send_num" id="order_send_num_id"  onblur="sellerSend_num();" cssStyle="width:150px;"/>
                                           <span class="errorSpan" style="display:none;" id="ydhtip"></span>
								          </p>
								          <br/>
								           <p>
								              说明：普通物流发货方式
								          </p>
									   </div>
							 		</div>	

						       <div>
						       
						       
							   <!--确定发货-->
						     <div class="batch">
						       <p> 
						             <input type="button" class="rbut" onclick="neibuChooseSendMode();"  value="确定发货"/>
						       </p>
						     </div>
  		    </div>
   </div>
		    <@s.hidden name="goodsorder.order_state" id="goods_order_state"/>
		    <@s.hidden name="goods_order_ids" id="goods_order_ids"/>
		    <@s.hidden name="goods_order_num" id="goods_order_num"/>
	</div></div></div></div>
	<div class="clear"/>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>