<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>订单详情</title>
<link href="/include/admin/css/backindex.css" rel="stylesheet" type="text/css" />

<style>

</style>

</head>
<body>
 <div class="crumb-wrap">
      <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>经营统计<span class="crumb-step">&gt;</span>订单列表<span class="crumb-step">&gt;</span><span>订单详情</span></div>
        </div>
        <div class="result-wrap">
            
            <div class="result-content">
                 <span style="font-size:16px;">商品信息：</span>
            </div>
           
            <div class="result-content">
               
               <table class="insert-tab" width="100%">   
		           <tr class="ordertd">
		             <td  width="15%;" style='line-height:25px;'>商品编号</td>
		             <td  colspan="2" width="40%;">
		            	商品名称
		             </td>
		             <td  width="15%;">
		            	价格
		             </td>
		             <td  width="10%;">
		            	购买数量
		             </td>
		             <td  width="10%;">
		            	小计
		             </td>
		           </tr>
		           <#list orderdetaiList as orderlist>
		           <tr>
		             <td>${(orderlist.goods_no)?if_exists}</td>
		              <td>
			              <#if orderlist.img_path!=''>
				      			<img src="${(orderlist.img_path)?if_exists}" align="absmiddle" class="f_left" width='30' height='30'>
			      		   <#else>
			      				<img src="${(cfg_nopic)?if_exists}" align="absmiddle" class="f_left" width='30' height='30'>
			      		    </#if>
		               </td>
		             <td>
		             <#if (orderlist.goods_attr)?if_exists>
				      		 <#assign g_attr=orderlist.goods_attr>
				      		  <#if ((g_attr)?index_of("<br>") > (-1))>
				      		       <#assign g_attr =g_attr.replace('<br>','').replace(' ','')>
			      		  	  </#if>
				      		 </#if>
		            	${(orderlist.goods_name)?if_exists}
		            	<#if g_attr!=null&&g_attr?trim!=''>
		            	[${(g_attr)?if_exists}]
		            	</#if>
		             </td>
		              <td >
		            		${(orderlist.goods_price)?if_exists}
		             </td>
		              <td >
		            		${(orderlist.order_num)?if_exists}
		             </td>
		              <td >
		            		${(orderlist.goods_price*orderlist.order_num)?if_exists}
		             </td>
		           </tr>
		           </#list>
				           
	            </table>
                 
            </div>
            
           <#if freegoodsList?if_exists && (freegoodsList?size gt 0)>
                
                <div class="result-content">
                 <span style="font-size:16px;">赠品信息：</span>
            </div>
           
            <div class="result-content">
               
               <table class="insert-tab" width="100%">   
		           <tr class="ordertd">
		             <td  width="15%;" style='line-height:25px;'>赠品编号</td>
		             <td  colspan="2" width="40%;">
		            	赠品名称
		             </td>
		             <td  width="15%;">
		            	价格
		             </td>
		             <td  width="10%;">
		            	数量
		             </td>
		             <td  width="10%;">
		            	小计
		             </td>
		           </tr>
		           <#list freegoodsList as freegoods>
		           <tr>
		             <td>${(freegoods.fg_no)?if_exists}</td>
		              <td>
			              <#if freegoods.img_path!=''>
				      			<img src="${(freegoods.img_path)?if_exists}" align="absmiddle" class="f_left" width='30' height='30'>
			      		   <#else>
			      				<img src="${(cfg_nopic)?if_exists}" align="absmiddle" class="f_left" width='30' height='30'>
			      		    </#if>
		               </td>
		             <td>
		            	${(freegoods.fg_name)?if_exists}
		             </td>
		              <td >
		            		${(freegoods.fg_price)?if_exists}
		             </td>
		              <td >
		            		${(freegoods.fg_number)?if_exists}
		             </td>
		              <td >
		            		${(freegoods.fg_price*freegoods.fg_number)?if_exists}
		             </td>
		           </tr>
		           </#list>
				           
	            </table>
                 
            </div>
               
           </#if> 
            <div class="result-content">
                 <span style="font-size:16px;">订单信息：</span>
            </div>
            
            <div class="result-content">
              <table class="insert-tab" width="100%;">   
					           <tr>
						             <td class="ordertd">订单号</td>
						             <td width="30%">
						            		${(goodsorder.order_id)?if_exists}
						             </td>
						             <td class="ordertd">订单状态</td>
						             <td>
						                <#list commparaList as comlist>
						                   <#if comlist.para_value==goodsorder.order_state> 
						                   ${(comlist.para_key)?if_exists}
						                   </#if>
						                </#list>
						            	
						             </td>
						            
					           </tr>
					           <tr>
						             <td class="ordertd">商品总金额</td>
						             <td>
						            	${(goodsorder.goods_amount)?if_exists}
						             </td>
						             <td class="ordertd">下单日期</td>
						             <td width="30%">
						            	${(goodsorder.order_time)?if_exists}
						             </td>
						             
						             
					           </tr>
					           <tr>
						             <td class="ordertd">配送费用</td>
						             <td>
						            		${(goodsorder.ship_free)?if_exists}
						             </td>
						             <td class="ordertd">支付方式</td>
						             <td>
						              <#list paymentList as plist>
					                     <#if goodsorder.pay_id==plist.pay_id>
					                          ${(plist.pay_name)?if_exists}
					                     </#if>
					                </#list> 
						             </td>
						          
					           </tr>
					           <tr>
						             <td class="ordertd">保价费用</td>
						             <td>
						             <#if goodsorder.insured!=null&&goodsorder.insured!=''>
						            	 ${(goodsorder.insured)?if_exists}
						            	<#else>
						            	-
					            	</#if>
						               
						             </td>
						                   <td class="ordertd">配送方式</td>
						             <td>
						                <#list sendmodeList as slist>
						                     <#if goodsorder.send_mode==slist.smode_id>
						                          ${(slist.smode_name)?if_exists}
						                     </#if>
						                </#list>
						             </td>
						             
					           </tr>
					           <tr>
					           		 <td class="ordertd">订单总金额</td>
						             <td >
						              ${(goodsorder.tatal_amount)?if_exists}
						             </td>
						           <td class="ordertd">订单商品总重量</td>
						             <td>
                                       <#if goodsorder.order_weight!=null&&goodsorder.order_weight!=''>
						            	      ${(goodsorder.order_weight)?if_exists}
	                                       <#else>
							            	-
						            	</#if>                  
						             </td>
					           </tr>
					           
					           <tr>
					           		 <td >积分抵扣金额</td>
						             <td >
						                ${(dedu_amount)?if_exists}
						             </td>
						           <td>可得积分</td>
						             <td>
						            	${(goodsorder.present_integral)?if_exists}                 
						             </td>
					           </tr>
					           
					            <tr>
					                  <td class="ordertd">关税</td>
						             <td>
						             	 <#if goodsorder.taxes!=null&&goodsorder.taxes!=''>
							            	￥${(goodsorder.taxes)?if_exists}
							            	<#else>
							            	-
						            	</#if>
						            	
						              </td>
					                  
						             <td class="ordertd">订单备注</td>
						             <td>
						             	 <#if goodsorder.mem_mark!=null&&goodsorder.mem_mark!=''>
							            	${(goodsorder.mem_mark)?if_exists}
							            	<#else>
							            	-
						            	</#if>
						            	
						              </td>
							   </tr>
		            </table>
            </div>
            
            <div class="result-content">
                 <span style="font-size:16px;">会员信息：</span>
            </div>
            
            
            <div class="result-content">
                 <table class="insert-tab" width="100%;">   
					           <tr>
						             <td class="ordertd">会员用户名</td>
						             <td width="30%;">
						            	${(memberuser.user_name)?if_exists}
						             </td>
						             <td class="ordertd">姓名</td>
						             <td width="30%;">
					             	 <#if memberuser.real_name!=null&&memberuser.real_name!=''>
						            	${(memberuser.real_name)?if_exists}
						            	<#else>
						            	-
					            	</#if>
						            	
						             </td>
					           </tr>
					           <tr>
					                 <td class="ordertd">联系电话</td>
						             <td>
						             	<#if memberuser.phone!=null&&memberuser.phone!=''>
						            	${(memberuser.phone)?if_exists}
						            	<#else>
						            	-
					            		 </#if>
						            	
						             </td>
						             <td class="ordertd">手机</td>
						             <td>
						             	<#if memberuser.cellphone!=null&&memberuser.cellphone!=''>
						            	${(memberuser.cellphone)?if_exists}
						            	<#else>
						            	-
					            		 </#if>
						            	
						             </td>
					           </tr>
					           <tr>
					           <td class="ordertd">微信</td>
						             <td>
						             	<#if memberuser.msn!=null&&memberuser.msn!=''>
						            	${(memberuser.msn)?if_exists}
						            	<#else>
						            	-
					            		 </#if>
						             </td>
						             <td class="ordertd">QQ</td>
						             <td>
						                <#if (memberuser.qq)?if_exists>
						            	${(memberuser.qq)?if_exists}
							      		  <#else>
							      		  -
								        </#if>
						             </td>
					           </tr>
					           
		            </table>
                 
            </div>
            
            <div class="result-content">
                 <span style="font-size:16px;">收货人信息：</span>
            </div>
            
             <div class="result-content">
                 <table class="insert-tab" width="100%;">   
									           <tr>
										             <td id="name" class="ordertd">收货人姓名</td>
										             <td width="30%;">
										             ${(goodsorder.consignee)?if_exists}
										             </td>
										             <td id="mobile" class="ordertd">联系手机</td>
										             <td width="30%;">
									               <#if (goodsorder.mobile)?if_exists>
									            	 ${(goodsorder.mobile)?if_exists}
									            	 <#else>
									            	 -
											        </#if>
										             
										             </td>
									           </tr>
									           <tr>
									           <td id="phone" class="ordertd">联系电话</td>
										             <td>
										              <#if (goodsorder.telephone)?if_exists>
									            	 ${(goodsorder.telephone)?if_exists}
									            	 <#else>
									            	 -
											        </#if>
										             </td>
										             <td id="post" class="ordertd">邮政编码</td>
										             <td>
										             <#if (goodsorder.zip_code)?if_exists>
									            	 ${(goodsorder.zip_code)?if_exists}
									            	 <#else>
									            	 -
											        </#if>
										             </td>
									           </tr>
									           <tr>
									           <td id="addres" class="ordertd">收货地区</td>
										             <td >
											             <#if (order_area)?if_exists>
											            	${(order_area)?if_exists}
											            	<#else>
											            	-
											            	</#if>
										             </td>
										              <td></td>
										             <td></td>
									           </tr>
									            <tr>
									           		<td id="addres" class="ordertd">详细收货地址</td>
										             <td >
										              ${(goodsorder.buy_address)?if_exists}
										             </td>
										             <td></td>
										             <td></td>
									           </tr>
						            </table>
            </div>
            
            <div class="result-content">
              
		          <span style="padding-left:500px;"> <input class="btn btn6" onclick="linkToInfo('/agent_Asysuser_orderAnalysis.action','');" value="返回" type="button"></span>
		              
            </div>
            
        </div>

   </div>
</body>
</html>
