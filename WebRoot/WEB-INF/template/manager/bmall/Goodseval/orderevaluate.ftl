 <html>
<head>
<title>评价列表</title>
<#include "/include/uploadInc.html">
<script language="javascript" type="text/javascript" src="/include/bmember/js/goodseval.js"></script>
</head>
<body>
	
<@s.form action="/bmall_Goodseval_orderGoodsEvaluate.action" method="post" id="tradeForm">
<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>商品评价</span></h2>
        <!----> 
        <div class="cancelDiv">
            <!---->
            <div class="opeDiv">
                <table width="100%" cellpadding="0" cellspacing="0" class="opeTab">
                	<tr>
                        <th width="10%">商品图片</th>
                        <th width="40%">商品名称</th>
                        <th width="20%">购买时间</th>
                        <th width="15%">状态</th>
                        <th width="15%">操作</th>
                    </tr>
                    <!---->
                    <#assign num=0>
                    <#assign goods_id="">
                    <#assign is_sure="">
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
		                        <#elseif (goodsorder.order_type)?if_exists=='6'>
															
		                        <#assign goodsdetailurl="/mall-yushoudetail-${detail.direct_id?if_exists}.html">
															
								</#if>
							 <#assign trade_id=stack.findValue("@com.rbt.function.GoodsevalFuc@getTradeId('${(goods_id)?if_exists}','${goodsorder.order_id?if_exists}')")>
                    <@s.hidden id="goods_id${detail_index}"  value="${(goods_id)?if_exists}"/>
                    <tr>
                  	   <td colspan="5">
                         <table  width="100%" cellpadding="0" cellspacing="0">
                           <tr>
                              <td width="10%">
                              	 <#if detail.list_img!=''>
					      			<a href="${goodsdetailurl?if_exists}" target="_blank"><img src="${( detail.list_img)?if_exists}" class="f_left" align="absmiddle"></a>
					      		 <#else>
					      			<a href="${goodsdetailurl?if_exists}" target="_blank"><img src="${(cfg_nopic)?if_exists}"  class="f_left" align="absmiddle"></a>
					      		 </#if>
                              </td>
                              <td width="40%"><a href="${goodsdetailurl?if_exists}"><#if detail.goods_name!="">${detail.goods_name?if_exists}<#else>-</#if></td>   
                              <td width="20%">${goodsorder.order_time?if_exists[0..9]} </td>
                              <td width="15%"><span class="mblue"><#if trade_id?if_exists=="">未评价<#else>已评价</#if></span></td>
                              <td width="15%"><#if trade_id?if_exists==""><span class="evaSpan">发表评价</span><#else><p><a href="###" class="bluea" onclick="linkToInfo('/bmall_Goodseval_audit.action','goodsorder.goods_id=${goods_id?if_exists}&goodsorder.order_id=${goodsorder.order_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')">查看</a></p><p><a href="###" onclick="deleteEval('/bmall_Goodseval_deleteEval.action','goodsorder.trade_id=${trade_id?if_exists}&goodsorder.order_id=${goodsorder.order_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');">[删除]</a></p></#if></td>
                            </tr>
                            <tr class="evasTr">
                              <td colspan="5">
                                 <div class="evasDiv">
                                    <div class="jtPic"><img src="/include/bmember/images/fbjt_03.gif"></div>
                                    <table  width="100%" cellpadding="0" cellspacing="0">
                                      <tr>
			             	           <th>晒图：</th>
					       		    	<td>

						                  
						                  
						                  
						                   <div id="fileQueue${detail_index?if_exists}"></div>
					       		    	  <@s.textfield  id="uploadresult${detail_index?if_exists}" cssStyle="display:none;" readonly="true"/>
					       		    	  <span style="position:absolute;top:21px;"><input type="file" name="uploadifyfile${detail_index?if_exists}" id="uploadifyfile${detail_index?if_exists}"/>
					       		    	  <span style="position:absolute;left:65px;top:-5px;"><img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult${detail_index?if_exists}');"/ ></span>
					       		    	  <script>uploadImgMultis('uploadifyfile${detail_index?if_exists}','uploadresult${detail_index?if_exists}','fileQueue${detail_index?if_exists}');</script>
						                  
						                  

					       		    	</td>
                                       </tr>
                                    	
                                    	<tr>
                                          <th width="16%"><span>*</span>评分：</th>
                                          <td width="84%">
                                             <input type="radio" name="radio_${detail_index}" value="1" checked>好评
                                             <input type="radio" name="radio_${detail_index}" value="0">中评
                                             <input type="radio" name="radio_${detail_index}" value="-1">差评
                                           </td>
                                        </tr>
                                        <tr>
                                          <th><span>*</span>内容：</th>
                                          <td><textarea name="evaluate_content"   onkeyup="checkLength(this,200);" id="evaluate_content${detail_index}" ></textarea></td>
                                        </tr>
                                    </table>
                                 </div>
                              </td>
                            </tr>
                         </table>
                       </td> 
                    </tr>
                    
                      </#if>
	      		     </#list>	
	      		      <tr>
                                      
                                          <td colspan=2><input type="button" class="submitbut" value="提交" onclick="submitEvaluate();"></td>
                                        </tr>
	      		     
                </table>
            </div>
            
           
                           
        </div>
        
   </div>
   
  </div>
  <div class="clear"></div>
</div>
<@s.hidden name="order_id" id="g_order_id"  value="${(goodsorder.order_id)?if_exists}"/>
<@s.hidden name="order_goods_id_str" id="str_goods_id" />
<@s.hidden name="order_goods_feng_str" id="str_goods_feng"  />
<@s.hidden name="order_goods_content_str" id="str_goods_content"  />
<@s.hidden name="order_share_pic_str" id="str_share_pic"  />
 <@s.hidden id="count_list_numbe_id"  name="orderdetaiCount"/>
 <@s.hidden   name="sell_cust_id"   value="${(goodsorder.sell_cust_id)?if_exists}"/>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/> 
</@s.form>
<script type="text/jscript">
$(document).ready(function(){
	//商品评价
	if($("div").hasClass("opeDiv")){
		$(".opeTab tr table .evaSpan").each(function(){
			$(this).toggle(function(){
				$(this).parents("tr").siblings(".evasTr").show();
				$(this).parents("tr").siblings().find(".evasTr").hide();
			},
			function(){
				$(this).parents("tr").siblings(".evasTr").hide();
			})
		})
	}
})
</script>
</body>
</html>

