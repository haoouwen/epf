<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>申请退款/退换货</title>
<#include "/include/uploadifive.html">
<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
<script type="text/javascript" src="/malltemplate/bmall/js/order.js"></script>
<script src="/include/bmember/js/refundapp.js" type="text/jscript"></script>

 <script type="text/javascript" >
  //初始化加载
	$(document).ready(function(){
	//所属地区的回选
	 loadArea("${areaIdStr?if_exists}","");
	 isReturn(${is_return_str});
	});
 </script>
</head>
<body>
<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>申请退款/退换货</span></h2>
        
        <!----> 
        <div class="cancelDiv">
           <!---->
            <div class="cancelTop">
            	 <p>
                	<span><b>订单编号：${(goodsorder.order_id)?if_exists}</b></span>
                	 <span><b>交易状态：</b><b class="mgreen">
                	  <#list commparaStateList as clist>
	                     <#if (goodsorder.order_state)?if_exists==clist.para_value>
				            	${clist.para_key}
	                     </#if>
	                </#list>
                      <#if (goodsorder.order_para)?if_exists!=''>
				   		<b>${(goodsorder.order_para)?if_exists}</b>
				    </#if></b></span>
                    <span><b>申请状态：</b><b class="mgreen">提交申请</b></span>
                    <span><b>订单金额：</b><b class="mred">${(goodsorder.tatal_amount)?if_exists}</b>元</span>
                 </p>
                 <p class="linep">亲爱的客户，您提交的申请我们会尽快为您处理，请耐心等待  </p>
            </div>
            <!---->
            <div class="opeDiv padDiv">
                <h3>已选择商品</h3> <@s.fielderror><@s.param>re_goods_id_str</@s.param></@s.fielderror>
                <table width="100%" cellpadding="0" cellspacing="0" class="opeTab">
                	<tr>
                        <th width="8%">商品图片</th>
                        <th width="30%">商品名称</th>
                        <th width="10%">购买数量</th>
                        <th width="10%">商品原价（元）</th>
                         <th width="15%">申请数量</th>
                         <th width="15%">状态</th>
                    </tr>
                    <#list detailList as dlist>
                    <#assign strade_id =dlist.trade_id>
                     <#if (refund_goods_id_str)?if_exists?index_of(strade_id?string) gt (-1)>
                    <tr>
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
                        <td><b class="mred">${(dlist.old_subtotal)?if_exists}</b></td>
                        <td>${(dlist.apply_num)?if_exists}</td>
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
                     </#if>
                    </#list>
                  </table>
             </div> 
             <@s.form action="/bmall_Refundapp_orderBuyRefundment.action" method="post"  id="tradeForm"  >
             <!--换货-->
             <div class="tabIDiv">
               <#if if_refund==1>
               <a href="###" class="siBut"  onclick="isReturn('0')"  id="tkbutton_id">退款</a>
               </#if>
               <#if if_regoods==1>
               <a href="###" class="niBut" onclick="isReturn('1')"  id="thbutton_id">退货</a>
               </#if>
               <#if if_exchangegoods==1>
               <a href="###" class="niBut" id="hhbutton_id" onclick="isReturn('2')">换货</a>
               </#if>
             </div>
             
             
             
             <#if if_exchangegoods ==1>
             <!---->
             <#setting number_format="0.##">
             <div class="opeDiv" id="th">
             
                <table width="100%" cellpadding="0" cellspacing="0" class="detTab">
                     <tr>
                      <th width="16%"><span>*</span><font id="liyou_id">退款原因</font></th>
                      <td>
                      		<@s.select  name="buy_refund_type"  list="commparaList" listValue="para_key"
						       listKey="para_value" headerKey="--请选择理由--" headerValue="--请选择理由--" id="buy_refund_type" value="${refundapp?if_exists.buy_type?if_exists}"/>
						       <@s.fielderror><@s.param>buy_refund_type</@s.param></@s.fielderror>
                      </td>
                    </tr>
                    <tr>
                      <th width="16%"><span>*</span><font id="jine_id">退款金额</font></th>
                      <td>
                       <#assign t_amount=0>
                       <#list detailList as dlist>
	                      <#assign strade_id =dlist.trade_id>
	                      <#assign ont_price =dlist.trade_id>
	                      <#if (refund_goods_id_str)?if_exists?index_of(strade_id?string) gt (-1)>
	                          <#assign t_amount = "${(t_amount?number + dlist.subtotal?number)?number}">
	                      </#if>
                       </#list>
                      
                      <@s.textfield   name="need_refund" id="need_refund" cssClass="aiflitteText" onkeyup="checkRMB(this);checkAll(this,'${(t_amount)?if_exists?number}');" maxlength="11" value="${(t_amount)?if_exists?number}" />元
                      退款上限:<b id="sx"  class="mred">${(t_amount)?if_exists?number}</b>元 ( 扣除优惠后的可退金额 )
                       <@s.fielderror><@s.param>need_refund</@s.param></@s.fielderror>
                      </td>
                  
                    </tr>	
                    <tr>
                      <th width="16%"><span>*</span>问题描述</th>
                      <td>
                      		<#if refundapp?if_exists.buy_reason?if_exists>  修改前的退款说明：</br><div style="width:600px;word-break:break-all; overflow:auto; " >${refundapp?if_exists.buy_reason?if_exists}</div></#if>
							            <@s.textarea  name="buy_refund_reason" cssStyle="width:500px;height:75px;" onkeyup="checkLength(this,200)" id="buy_refund_reason" /> 200字
								      <@s.fielderror><@s.param>buy_refund_reason</@s.param></@s.fielderror>
						  <br/>请详细描述问题，以便商家更好的处理申请！
                      
                      </td>
                    </tr>
                    <tr>
                      <th width="16%">上传凭证</th>
                      <td>
			             	<input type="text" name="imgString" id="uploadresult" value="${(refundapp.img_path)?if_exists}" style="float:left;width:260px;height:25px;line-height:25px;"/>
       		    	        <input id="file_upload" type="file" name="file" />
       		    	        <input id="showpic" type="button" onclick="showpicture('uploadresult');" value="预览" style="float:left;background:#f1f9eb;border:1px solid #bfd7af;color:#333;text-align:center;width:60px;height:32px;"/>
		                    <div id="tip-queue" style="clear:both;"></div>
			                <script>uploadmulti('file_upload','uploadresult',true,5,5,'tip-queue');</script>
			             	
                        <p class="mgray">最多可上传5张图片，每张图片大小不超过1M，支持bmp,gif,jpg,png,jpeg格式文件</p>
                      </td>
                    </tr>
                    <tr>
                      <th width="16%">.</th>
                      <td>
                       <@s.submit value="提 交" cssClass="submitbut" />
                      </td>
                    </tr>
                </table>
	             </div>
	             
	             <div class="opeDiv" id="hh" style="display:none">
             
                <table width="100%" cellpadding="0" cellspacing="0" class="detTab">
                    <tr>
                      <th width="16%"><span>*</span>换货原因</th>
                      <td>
                      <@s.select  name="refund_type"  list="hcommparaList" listValue="para_key"
						       listKey="para_value" headerKey="--请选择理由--" headerValue="--请选择理由--"  value="${exchange?if_exists.refund_type?if_exists}"/>
						       <@s.fielderror><@s.param>refund_type</@s.param></@s.fielderror>
                      </td>
                    </tr>
                    <tr>
                      <th width="16%"><span>*</span>问题描述</th>
                      <td>
                      		<#if exchange?if_exists.buy_reason?if_exists>  修改前的退款说明：</br><div style="width:600px;word-break:break-all; overflow:auto; " >${exchange?if_exists.buy_reason?if_exists}</div></#if>
							            <@s.textarea  name="refund_reason" cssStyle="width:500px;height:75px;" onkeyup="checkLength(this,200)" id="buy_refund_reason" /> 200字
								      <@s.fielderror><@s.param>refund_reason</@s.param></@s.fielderror>
						  <br/>请详细描述问题，以便商家更好的处理申请！
                      
                      </td>
                    </tr>
                    <tr>
                      <th width="16%">上传凭证</th>
                      <td>
                        <input type="text" name="imgStrings" id="uploadresult1" value="${(exchange.img_path)?if_exists}" style="float:left;width:260px;height:25px;line-height:25px;"/>
   		    	        <input id="file_upload1" type="file" name="file" />
   		    	        <input id="showpic" type="button" onclick="showpicture('uploadresult1');" value="预览" style="float:left;background:#f1f9eb;border:1px solid #bfd7af;color:#333;text-align:center;width:60px;height:32px;"/>
	                    <div id="tip-queue1" style="clear:both;"></div>
		                <script>uploadmulti('file_upload1','uploadresult1',true,5,5,'tip-queue1');</script>
                        <p class="mgray">最多可上传5张图片，每张图片大小不超过1M，支持bmp,gif,jpg,png,jpeg格式文件</p>
                      </td>
                    </tr>
                    <tr>
                      <th width="16%"><span>*</span>收货人姓名</th>
                      <td> 
                      <@s.textfield  cssClass="aisText" name="exchange.mconsignee"/>
                      <@s.fielderror><@s.param>exchange.mconsignee</@s.param></@s.fielderror>
                      </td>
                    </tr>
                    <tr>
                      <th width="16%"><span>*</span>所在地区</th>
                      <td>
                      <div id="areaDiv" style="margin-left:0px;"></div>
                  	  <@s.fielderror><@s.param>areaDiv</@s.param></@s.fielderror>
                      </td>
                    </tr>
                    <tr>
                      <th width="16%"><span>*</span>具体地址</th>
                      <td>
                      <@s.textfield  cssClass="aisText" name="exchange.buy_address"/>
                        <@s.fielderror><@s.param>exchange.buy_address</@s.param></@s.fielderror>
                      </td>
                    </tr>
                    <tr>
                      <th width="16%"><span>*</span>手机号码</th>
                      <td>
                      <@s.textfield   id="phone" cssClass="aisText" name="exchange.mmobile"  onkeyup="checkDigital(this)" />
                      <@s.fielderror><@s.param>exchange.mmobile</@s.param></@s.fielderror>
                      </td>
                    </tr>
                    <tr>
                      <th width="16%">.</th>
                 		 <td><input style="cursor:pointer<#if is_deny_num>;display:none"<#else>"</#if>  onclick="submitRefund();" value="提 交" class="submitbut" /></td>
                    </tr>
                </table>
          </div>
             <#else>
             
             
              <div >
             
                <table width="100%" cellpadding="0" cellspacing="0" class="detTab">
                   
                    <tr>
                      <th width="16%">.</th>
                 		 <td><span class="mred" >*抱歉,您的订单商品已经超过售后有效期,暂时无法申请任何售后服务!</span></td>
                    </tr>
                </table>
          </div>
             </#if>
             
             
             
             
	        <@s.hidden name="order_id" id="g_order_id"  value="${(goodsorder.order_id)?if_exists}"/>
			<@s.hidden name="goodsorder.order_state" id="order_state" />
			<@s.hidden name="refundapp_id"id="refundapp_id" value="${refundapp?if_exists.trade_id?if_exists}"/>
			<@s.hidden name="exchange_id"id="exchange_id" value="${exchange?if_exists.trade_id?if_exists}"/>
			<@s.hidden name="is_return_str" id="is_return" value="0" />
			<@s.hidden name="refund_goods_id_str" />
			<@s.hidden name="apply_num_str" />
			 <@s.hidden name="all_refund" id="all_refund" value="${(t_amount)?if_exists}" />
			 <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
			 <@s.hidden id="back_goods_num" name="back_goods_num"/>
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
