<head>
<title>取消订单记录</title>
</head>
<body>
<@s.form action="/bmall_Goodsorder_cancelOrderList.action" method="post" id="indexForm">
<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>取消订单记录</span></h2>
        
        <div class="usedTDiv padDiv">
        
           <table width="100%" cellpadding="0" cellspacing="0" class="usedTab">
           
              <tr><th width="15%">订单号</th><th width="30%">订单商品</th><th width="12%">提交时间</th><th width="20%">支付明细(元)</th><th width="12%">处理状态</th><th width="11%">操作</th></tr>
              <#list goodsorderList as goodsorder>
              <tr>
                 <td><span class="blueSpan">${goodsorder.order_id?if_exists}</span></td>
                 <td class="coTd">
                   <div>
                          <#assign num=0>
                         <#assign goods_id="">
                        <#list detailList as detail>
                       <#assign num=num+1>
					       <#if ((detail.order_id)?if_exists)==((goodsorder.order_id)?if_exists)>
				           <#if detail.remark!=null && detail.remark?index_of("###") gt -1>
						      		<#list detail.remark?if_exists?split('###') as remarks>
						      						<#if remarks_index==0>
														<#assign remark_id = remarks>
													</#if>
													<#if remarks_index==1>
														<#assign remark_name = remarks>
													</#if>
													
									</#list>
						   </#if>
						   		 <#assign goodsdetailurl="">
						   		 <#assign goods_id="${detail.goods_id?if_exists}">
						   		 <#if (goodsorder.order_type)?if_exists=='1'>
		                          <#assign goodsdetailurl="/mall-goodsdetail-${detail.goods_id?if_exists}.html">
		
								<#elseif (goodsorder.order_type)?if_exists=='2'>
		
		                          <#assign goodsdetailurl="/mall-goodsdetail-${detail.goods_id?if_exists}.html">
		
								<#elseif (goodsorder.order_type)?if_exists=='3'>
															
									<#assign goodsdetailurl="/spikedetail-${remark_id}.html">						
		
								<#elseif (goodsorder.order_type)?if_exists=='4'>
															
		                           <#assign goodsdetailurl="/mall-combodetail-${remark_id}.html">
		
								<#elseif (goodsorder.order_type)?if_exists=='5'>
															
		                           <#assign goodsdetailurl="/mall/groupbuy!groupDetail.action?groupid=${remark_id}">
															
								</#if>
                      		     <#if detail.list_img!=''>
					      			<a href="${goodsdetailurl?if_exists}" target="_blank" title="${detail.goods_name?if_exists}"><img src="${( detail.list_img)?if_exists}" class="f_left" align="absmiddle"></a>
					      		 <#else>
					      			<a href="${goodsdetailurl?if_exists}" target="_blank" title="${detail.goods_name?if_exists}"><img src="${(cfg_nopic)?if_exists}"  class="f_left" align="absmiddle"></a>
					      		 </#if>
                       </#if>
	      		      </#list>	
                   </div>
                 </td>
                 <td><span class="graySpan"><#if goodsorder.buy_date?if_exists?length lt 18>${goodsorder.buy_date?if_exists}<#else>${goodsorder.buy_date?if_exists[0..10]}<br/>${goodsorder.buy_date?if_exists[10..18]}</#if></span></td>
                 <td>
                     <p>金额：<b>${goodsorder.tatal_amount?if_exists}</b></p>
                    <p>
                     <#assign zfpay="">
	                 <#if goodsorder.pay_id=="4">
				          <#assign zfpay="余额支付">
				      <#else>    
				          <#assign zfpay="在线支付">
	                 </#if>
	                <#if (zfpay)?if_exists>
	            	${(zfpay)?if_exists}
	            	<#else>
	            	未付款
	            	</#if>
	            	</p>
                 </td>
                 <td>
                 <#if goodsorder.cancel_state?if_exists=="1">  
                                  已完成
                 <#elseif goodsorder.cancel_state?if_exists=="0">
                    <b class="orangeSpan"> 退款处理</b>
                 </#if>   
                 </td>
                 <td>
                   <p><a href="###" class="bluea" onclick="linkToInfo('/bmall_Goodsorder_buyCancelOrderView.action','goods_order_id=${goodsorder.order_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')">查看</a></p>
                 </td>
              </tr>
              </#list>
           </table>
           
        </div>
        <!---->
        <!--翻页控件-->
        <div class="listbottom">${pageBar?if_exists}</div>
          
   </div>
   
  </div>
  <div class="clear"></div>
</div>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>    
</@s.form>
</body>
</html>