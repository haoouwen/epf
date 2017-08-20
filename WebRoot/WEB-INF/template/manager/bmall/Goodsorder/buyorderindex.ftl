<html>
<head>
<title>商品订单</title>
<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
<script language="javascript" type="text/javascript" src="/include/bmember/js/directSell.js"></script> 
<script src="/include/bmember/js/goodsorder.js" type="text/jscript"></script>
</head>
<body>
<@s.form action="/bmall_Goodsorder_buyorderlist.action" method="post" id="indexForm">
  <!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>商品订单</span></h2>
        
        <div class="rTopDiv">
           <span class="searchtext">订单号：<@s.textfield name="order_id_s"  /></span>
           <span class="searchtext">状态：<@s.select  name="order_state_s"  list="commparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="--请选择--" /> </span>
					   
		    <span class="searchtext">时间：<@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
			               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
					至
					 <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
					               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
		      
		      </span>
           <span><input type="submit" value="查询" class="cssbtn"/></span>
           
       </div>
        
        <div class="orderDiv">
        
        	<!---->             
             <table width="100%" cellpadding="0" cellspacing="0" class="h2Tab">
               <tr><td width="37%">商品信息</td><td width="12%">收货人</td><td width="12%">实付款(元)</td><td width="12%">交易时间</td><td width="12%">交易状态</td><td width="15%">交易操作</td></tr>
             </table>
            
              <#list goodsorderList as goodsorder>
          <table width="100%" cellpadding="0" cellspacing="0" class="orderTab">
           <tr>
               <th colspan="6">
               	   <span>订单编号：
               	   <a onclick="linkToInfo('/bmall_Goodsorder_buyOrderView.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}&parentMenuId=4637243721&selli=2442547481');" >${(goodsorder.order_id)?if_exists}</a>
               	   </span>
               	     <#if (goodsorder.is_sea)?if_exists=='1'>
					 	<font color="#005ea7">直邮订单</font>
					<#elseif (goodsorder.is_sea)?if_exists=='0'>
						<font color="#005ea7">保税区订单</font>
					</#if>
               </th>
           </tr>
           
           <tr>
           		<td width="37%" class="imgTd">
           		<div>
       		    <#assign num=0>
       		    <#assign goods_id_str="">
       		    <#assign goods_name_str="">
       		    <#assign list_img_str="">
       		    <#assign sale_price_str="">
           		 <#if (goodsorder.order_type)?if_exists=='6'>
           		 <#else>
					<#list detailList as detail>
						  <#if ((detail.order_id)?if_exists)==((goodsorder.order_id)?if_exists)>
						  	 <#assign num=num+1>   
						  	 <#assign img_path="${( detail.temporary_goodsimg)?if_exists}">   
					         <#if num?if_exists=="1">
					         <#assign goods_id_str="${detail.goods_id?if_exists}">
					         <#assign goods_name_str="${detail.temporary_goodsname?if_exists}">
					         <#assign list_img_str="${detail.temporary_goodsimg?if_exists}">
					         <#assign sale_price_str="${detail.goods_price?if_exists}">
					   		 <#else>
					   		 <#assign goods_id_str="${goods_id_str}#${detail.goods_id?if_exists}">
					   		 <#assign goods_name_str="${goods_name_str}#${detail.temporary_goodsname?if_exists}">
					   		 <#assign list_img_str="${list_img_str}#${detail.temporary_goodsimg?if_exists}">
					   		 <#assign sale_price_str="${sale_price_str}#${detail.goods_price?if_exists}">
					   		 </#if>
					   		 <#assign goodsdetailurl="">
					   		 <#if (goodsorder.order_type)?if_exists=='1'>
	                          <#assign goodsdetailurl="/mall-goodsdetail-${detail.goods_id?if_exists}.html">
							</#if>
				      	<a href="${goodsdetailurl?if_exists}" target="_blank">
				      	<img src="<#if img_path!=''>${img_path?if_exists}<#else>${(cfg_nopic)?if_exists}</#if>" class="f_left" align="absmiddle"></a>
				      		 
		      		 </#if>
		      		 </#list>	
           		 </#if>
	      		 
           		</div></td>
           		<td width="12%">${goodsorder.consignee?if_exists}</td>
           		<td width="12%">	
           		<p> 
           			￥<b>${goodsorder.tatal_amount?if_exists}</b> <br/>
           			(<#if goodsorder.ship_free!='0'>含运费${goodsorder.ship_free?if_exists}<#else>免运费</#if>)
           			</p>
           		</td>
                <td width="12%">
                	<p>${goodsorder.order_time?if_exists[0..10]}</p><p>${goodsorder.order_time?if_exists[10..18]}</p>
                	<p>
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
           			</p>
                </td>
                <td width="12%">
                	<p>
				       <#if goodsorder.order_para!=''>
				       
				              <#if goodsorder.order_state=='0'>
				                        <#if (goodsorder.pay_time)?if_exists&&(goodsorder.pay_trxid)?if_exists>
							             <!-- 取消订单-已支付-->
							                    <b>交易关闭</b>
							                     </br>(退款完成)
							              <#else>
							               <!-- 取消订单-未付款-->
							           			<b>${(goodsorder.order_para)?if_exists}</b>
							             </#if>
				              <#else>
				              		<b>${(goodsorder.order_para)?if_exists}</b>
				              </#if>
				   		   
				   		   <#if goodsorder.is_sea=="1">
				   		   <#if goodsorder.order_state=="3" && goodsorder.is_clearance=="0"></br><b>待通关</b><#elseif goodsorder.order_state=="3" && goodsorder.is_clearance=="1"></br><b>通关成功</b><#elseif goodsorder.order_state=="3" && goodsorder.is_clearance=="2"></br><b>通关失败</b></#if>
				   		   </#if>
				       </#if>
				        <#if goodsorder.order_state=='2'&& goodsorder.deliver_state!="0">
				    	    </br><b>订单已审核</b>
				    	</#if>
                	</p>
                	<#if goodsorder.order_state?if_exists=='3' && goodsorder.is_clearance =="1">
	                          <i> <a  onclick="linkToInfo('/bmall_Goodsorder_buy_order_Logistics.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}');" >
																	    查看物流</a></i>
					     </#if >
                </td>
                <td width="15%">
                	<p>
	                	<#if goodsorder.order_state=='1'&&(goodsorder.order_type)?if_exists!='6'>
					    	<a  target="_blank" onclick="linkToInfo('/mall/order!goPay.action','order_pay_tip=1&order_id_str=${goodsorder.order_id?if_exists}&use_num_pay=${(goodsorder.tatal_amount)?if_exists}&total_amount=${(goodsorder.tatal_amount)?if_exists}&all_total=${(goodsorder.tatal_amount)?if_exists}&sendway=${goodsorder.send_mode?if_exists}');" 
					    	class="paya">付款</a>
					        <br/>
						</#if>
							<a onclick="linkToInfo('/bmall_Goodsorder_buyOrderView.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');" >
						查看</a>
						<#if goodsorder.order_state=='0'>
							<span>|</span>
							<#if goodsorder.is_del=='1'>
							<a onclick="delB2cOneInfo('/bmall_Goodsorder_deleteOrder.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')" >
							删除</a>
							</#if>
						</#if>
						<#if goodsorder.order_state=='7'>
						   	<span>|</span>
						   <a  href="###" onclick="linkToInfo('/bmall_Goodseval_orderEvaluateView.action','order_id=${goodsorder.order_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')" >
						    评价晒单</a>
					     </#if> 
					<br/>
					<#if goodsorder.order_state=='1'|| goodsorder.order_state=='2'>
				        <#if goodsorder.deliver_state=="0">
					        <#if goodsorder.order_type =="6"&&goodsorder.order_state=='2'>
					        <#else>
					         <!-- 未付款 取消订单 -->
					         <#if goodsorder.is_clearance?if_exists==null || goodsorder.is_clearance?if_exists=="" || goodsorder.is_clearance?if_exists=="0">
						     <a onclick="cancelOrder('${goodsorder.order_id?if_exists}','${goodsorder.tatal_amount?if_exists}','${goods_id_str?if_exists}','${list_img_str?if_exists}','${goods_name_str?if_exists}')" >取消订单</a>
					         </#if>
					        </#if>
				        </#if>
				   </#if>
				   <#if goodsorder.is_sea=='1'&& goodsorder.order_state=='3' && goodsorder.is_clearance =="1">
						 <a  href="###"  class="qrbut" onclick="orderconfirmreceipt('${goodsorder.order_id?if_exists}','${goodsorder.tatal_amount?if_exists}')">确认收货</a>
				    </#if> 
				    <#if goodsorder.is_sea=='0'&& goodsorder.order_state=='3'>
						 <a  href="###"  class="qrbut" onclick="orderconfirmreceipt('${goodsorder.order_id?if_exists}','${goodsorder.tatal_amount?if_exists}')">确认收货</a>
				    </#if> 
                	</p>
                	<#if  goodsorder.order_state=='7' || goodsorder.order_state=='8'>
                	        <#if (goodsorder.order_type)?if_exists=='1'>
		                	   <p><a  href="###" onclick="linkToInfo('/bmall_Refundapp_appRefundGoods.action','order_id=${goodsorder.order_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')" >申请退款/退换货</a></p>
		                	</#if>
		                	
		                	 <!--<#if  goodsorder.order_state=='0' ||goodsorder.order_state=='7' || goodsorder.order_state=='8' || goodsorder.order_state=='4'>
		                	     <p><a href="###" class="addCart" onclick="addCart('${goods_id_str?if_exists}','${sale_price_str?if_exists}')"></a></p>
                		        </#if>
                		    -->
                	</#if>
                </td>
            </tr>
          </table>
          </#list>
         
        </div>
      
        <!--翻页控件-->
        <div class="listbottom">${pageBar?if_exists}</div>
          
   </div>
   
  </div>

<div class="clear"></div>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>
<@s.hidden name="buy_order_cancel" id="refund_buy_reason"/>
<@s.hidden name="goods_order_id" id="order_id"/>
<@s.hidden name="jsontotal" id="jsontotal_id"/>    
</@s.form>
<!--取消订单弹出层-->
<div class="popupDiv" id="cancelOrderId">
</div>
<!--确认收货弹出层-->
<div class="popupDiv" id="confirmOrderId">
</div>
</body>
</html>