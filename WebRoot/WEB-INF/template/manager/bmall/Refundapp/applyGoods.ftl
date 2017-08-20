<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>申请退款/退换货</title>
<script src="/include/bmember/js/refundapp.js" type="text/jscript"></script>
 <script type="text/javascript" >
  //初始化加载
	$(document).ready(function(){
	 selectRadioGoods();
	});
 </script>
</head>
<body>
<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>申请退款/退换货</span></h2>
        <@s.form action="/bmall_Refundapp_orderBuyRefundmentView.action" method="post"  id="tradeForm"  >
        <!----> 
        <div class="cancelDiv">
           <!---->
            <div class="cancelTop">
            	 <p>
                	<span><b>订单编号：${(goodsorder.order_id)?if_exists}</b></span>
                	 <span><b>交易状态：</b><b class="mgreen">
                	  <#list commparaStateList as clist>
	                     <#if goodsorder.order_state==clist.para_value>
				            	${clist.para_key}
	                     </#if>
	                </#list>
                      <#if goodsorder.order_para!=''>
				   		<b>${(goodsorder.order_para)?if_exists}</b>
				    </#if></b></span>
                    <span><b>申请状态：</b><b class="mgreen">提交申请</b></span>
                    <span><b>订单金额：</b><b class="mred">${(goodsorder.tatal_amount)?if_exists}</b>元</span>
                 </p>
                 <p class="linep">亲爱的客户，您提交的申请我们会尽快为您处理，请耐心等待  </p>
            </div>
            <!---->
            <div class="opeDiv padDiv">
                <h3>请先选择要申请服务的商品</h3> <@s.fielderror><@s.param>re_goods_id_str</@s.param></@s.fielderror>
                <table width="100%" cellpadding="0" cellspacing="0" class="opeTab">
                	<tr>
                	    <th width="7%">
	                	    <#assign num=0> 
	                	     <#list detailList as dlist>
	                	             <#if (dlist.order_num-dlist.final_apply_num) gt 0>
		                	              <#if  dlist.orderdetail_state=='0' || dlist.orderdetail_state=='2' || dlist.orderdetail_state=='5' ||  dlist.orderdetail_state=='8'>
		                	                  <#assign num=num+1> 
		                	               <#elseif  (dlist.orderdetail_state=='1' || dlist.orderdetail_state=='3' || dlist.orderdetail_state=='4' || dlist.orderdetail_state=='6' || dlist.orderdetail_state=='7' || dlist.orderdetail_state=='9')&& (dlist.final_apply_num!=dlist.order_num)>
			                       			 <#assign num=num+1> 
			                             </#if>
		                               </#if>
	                	     </#list>
	                	     <#if if_appinfo !=0>
		                	     <#if num!=0>
		                       	 <input type="checkbox" name="checkbox" id="checkall" onclick="getAll('re_goods_id_str')" />全选
		                         </#if>
		                      </#if>
                         </th>
                        <th width="8%">商品图片</th>
                        <th width="32%">商品名称</th>
                        <th width="8%">数量</th>
                        <th width="10%">价格(元)</th>
                        <th width="10%">已申请数量</th>
                        <th width="10%">可申请数量</th>
                         <th width="15%">状态</th>
                    </tr>
                    <#list detailList as dlist>
                    <tr>
                    
                        <#if if_appinfo !=0>
                           <#if (dlist.order_num-dlist.final_apply_num) gt 0>
                           <#if  (dlist.orderdetail_state=='0' || dlist.orderdetail_state=='2' || dlist.orderdetail_state=='5'||  dlist.orderdetail_state=='8') >
	                            <td>
	                   			 <input type="checkbox" name="re_goods_id_str" value="${dlist.trade_id?if_exists}"     onclick="getRenM();"/>
	                            </td>
	                        <#elseif (dlist.orderdetail_state=='1' || dlist.orderdetail_state=='3' || dlist.orderdetail_state=='4' || dlist.orderdetail_state=='6' || dlist.orderdetail_state=='7' || dlist.orderdetail_state=='9')&& (dlist.final_apply_num!=dlist.order_num)>
	                            <td>
	                   			 <input type="checkbox" name="re_goods_id_str" value="${dlist.trade_id?if_exists}"     onclick="getRenM();"/>
	                            </td>
                            <#else>
	                       		   <td> -- </td>
                          </#if>
                          <#else>
                               <td> -- </td>
                          </#if>
                      <#else>
                               <td> -- </td>
                       </#if>
                          
                          
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
                        <td><a href="/mall-goodsdetail-${(dlist.goods_id)?if_exists}.html" target="_blank">${(dlist.goods_name)?if_exists} </a></td>    
                        <td><b><#if dlist.order_num?if_exists>${dlist.order_num}<#else>-</#if></b></td>
                        <td><b class="mred">${(dlist.subtotal)?if_exists}</b></td>
                         <td> <b>${(dlist.final_apply_num)?if_exists}</b></td>
                         <td>
                             <#if if_appinfo !=0>
	                             <#if (dlist.order_num-dlist.final_apply_num) lt 1>
	                                 <b>0</b>
	                             <#else>
	                               <input type="text" id="trade_${(dlist.trade_id)?if_exists}" name="apply_num" size="1px" onblur="apply_deal('${(dlist.trade_id)?if_exists}','${(dlist.order_num-dlist.final_apply_num)?if_exists}',this.value);" value="${(dlist.order_num-dlist.final_apply_num)?if_exists}"/>
	                             </#if>
                             <#else>
                              -- 
                       </#if>
                        </td>  
                        <td>
	                        <#if dlist.orderdetail_state=='1'>
	                       			<#if dlist.final_apply_num==dlist.order_num>
	                       			    <span class="mred" >售后中</span>
	                       			 <#else>
	                       			    <font color="#e1292d">部分退款中</font>
	                       			 </#if>
	                        <#elseif dlist.orderdetail_state=='2'>	
	                         		 退款关闭
	                        <#elseif dlist.orderdetail_state=='3'>
	                                <span class="mgreen" >
	                                <#if dlist.final_apply_num==dlist.order_num>
	                       			      售后成功
	                       			 <#else>
	                       			     部分售后成功
	                       			 </#if>
	                       			 </span>
	                        <#elseif dlist.orderdetail_state=='4'>
	                              <#if dlist.final_apply_num==dlist.order_num>
	                          		<span class="mred" >售后中</span>
	                              <#else>
	                                 <font color="#e1292d">部分退货中</font> 
	                              </#if>		
	                        <#elseif dlist.orderdetail_state=='5'>
	                        		  退货关闭
	                        <#elseif dlist.orderdetail_state=='6'>
	                         	    <span class="mgreen">
	                         	       <#if dlist.final_apply_num==dlist.order_num>
	                         	       售后成功
	                         	       <#else>
	                         	       部分售后成功
	                         	       </#if>
	                         	    </span>
	                        <#elseif dlist.orderdetail_state=='7'>
	                        　　<#if dlist.final_apply_num==dlist.order_num>
	                         		 　<span class="mred" >售后中</span>
	                         	  <#else>
	                         	  　　<font color="#e1292d">部分换货中</font>
	                         	  </#if>
	                        <#elseif dlist.orderdetail_state=='8'>
	                          		换货关闭
	                        <#elseif dlist.orderdetail_state=='9'>
	                       		    <span class="mgreen" >  
	                       		     <#if dlist.final_apply_num==dlist.order_num>
	                       		        售后成功
	                       		     <#else>
	                       		        部分售后成功
	                       		     </#if>
	                       		    </span>
	                       		    
	                       <#elseif dlist.orderdetail_state=='0'>
	                       		   --
	                        </#if>
                        </td>
                     </tr>
                    </#list>
                  </table>
             </div> 
             <div style="padding-top:20px;text-align:center;">
             <#if if_appinfo !=0>
	              <#if num !=0>
	                <@s.hidden name="refund_goods_id_str" id="re_goods_id_str" />
	                <@s.hidden name="order_id" id="g_order_id"  value="${(goodsorder.order_id)?if_exists}"/>
					<@s.hidden name="goodsorder.order_state" id="order_state" />
					<@s.hidden name="is_return_str" id="is_return" value="0" />
					 <@s.hidden name="apply_num_str" id="apply_num_str"/>
					 <@s.hidden name="back_goods_num" id="back_goods_num"/>
	             	<input onclick="submitapprefund();" type="button" value="提交申请" class="submitbut" />
	             <#else>
	                <span class="mred" >*抱歉,您的订单商品暂时无法申请任何售后服务!</span>
	             </#if>
	           <#else>
	                <span class="mred" >*抱歉,您的订单商品已经超过售后有效期,暂时无法申请任何售后服务!</span>
             </#if>
             </div>
            </@s.form>
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
                  <th width="29%"><p>是否支持换货</p>
                    			  <p>(签收30天内)</p>
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
  <div class="clear"></div>
</body>
</html>
