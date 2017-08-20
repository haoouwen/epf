<html>
  <head>
    <title>订单详情</title>
    <#include "/include/uploadInc.html">
    <#include "/WEB-INF/template/manager/admin/Goodsorder/commonprint.ftl">
    <script src="/include/admin/js/orderprint.js" type="text/javascript"></script>
    <link href="/include/admin/css/goodsorder.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
	<script type="text/javascript" src="/include/common/js/copytext.js"></script>
    <script type="text/javascript" src="/include/common/js/jquery.alert.js"></script>
    <link href="/include/common/css/alert.css" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="/include/admin/js/goodsorder.js"></script>
	<link href="/include/admin/css/logistics.css" rel="stylesheet" type="text/css"/>
	<script  type="text/javascript">
	   $(document).ready(function(){ 
		 $("#confirmBtn").click(function(){
		    var billnos =  $("#billnos").val();
		    if(billnos=="" || billnos==null){
		         jNotify("请输入正确的运单号"); 
		        return false;
		    }
		  });
		   //加载物流动态信息
	      getLOgisticsInfo();
       });
	</script>
  </head>
  <body>
  <@s.form action="/admin_Goodsorder_baoTaxupdate.action" method="post" validate="true" id="tradeForm">
<div class="postion">	当前位置:商城管理 > 订单管理  > 保税订单详情</div>
<!--startprint-->
<div class="rtdcont">
	<div class="rttable">
	   <!--列表页table的数据-->	 
	   <div class="main_cont">
 		<div class="main_content">
 			<div>
 			<b>商品信息</b>
			 <div id="oper_seo_div" class="oper_seo_div">      
		       	 <table cellSpacing=1 cellPadding=1 width="100%" class="tabdiv">   
		           <tr >
		             <th class="gth" width="15%;" style='line-height:25px;'>商品编号</th>
		             <th class="gth" colspan="2" width="40%;">
		            	商品名称
		             </th>
		             <th class="gth" width="15%;">
		            	价格
		             </th>
		             <th class="gth" width="10%;">
		            	购买数量
		             </th>
		             <th class="gth" width="10%;">
		            	小计
		             </th>
		           </tr>
		           <#list orderdetailList as orderlist>
		           <tr>
		             <td class="gtd">${(orderlist.goods_no)?if_exists}</td>
		              <td align="right">
			              <#if orderlist.img_path!=''>
				      			<img src="${(orderlist.img_path)?if_exists}" align="absmiddle" class="f_left" width='30' height='30'>
			      		   <#else>
			      				<img src="${(cfg_nopic)?if_exists}" align="absmiddle" class="f_left" width='30' height='30'>
			      		    </#if>
		               </td>
		             <td  align="left">
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
		              <td class="gtd">
		            		${(orderlist.goods_price)?if_exists?string("0.00")}
		             </td>
		              <td class="gtd">
		            		${(orderlist.order_num)?if_exists}
		             </td>
		              <td class="gtd">
		            		${(orderlist.goods_price*orderlist.order_num)?if_exists?string("0.00")}
		             </td>
		           </tr>
		           </#list>
				           
	            </table>
			 		</div>
			 	</div>
			 <br/>
			 
			<#if freegoodsList?if_exists && (freegoodsList?size gt 0)>
 			<b>赠品信息</b>
			 <div id="oper_seo_div" class="oper_seo_div">      
		       	 <table cellSpacing=1 cellPadding=1 width="100%" class="tabdiv">   
		           <tr >
		              <th class="gth" colspan="2" width="100%;">
		            	赠品名称
		             </th>
		            
		           </tr>
		           <#list freegoodsList as freegoods>
		           <tr>
		              <td align="right">
			              <#if freegoods.img_path!=''>
				      			<img src="${(freegoods.img_path)?if_exists}" align="absmiddle" class="f_left" width='30' height='30'>
			      		   <#else>
			      				<img src="${(cfg_nopic)?if_exists}" align="absmiddle" class="f_left" width='30' height='30'>
			      		    </#if>
		               </td>
		               <td align="left">
		                  ${(freegoods.fg_name)?if_exists}
		               </td>
		           </tr>
		           </#list>
				           
	            </table>
			 		</div>
			 	</div>
			 <br/>
			 <div>
			 </#if>
			 
			 <div>
			 <b>订单信息</b>
			 <div id="oper_seo_div" class="oper_seo_div">      
			       	 <table cellSpacing=1 cellPadding=1 width="100%;" class="tabdiv">   
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
						            	${(goodsorder.goods_amount)?if_exists?string("0.00")}
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
					           		 <td class="ordertd">积分抵扣金额</td>
						             <td >
						                ${(dedu_amount)?if_exists}
						             </td>
						           <td class="ordertd">可得积分</td>
						             <td>
						            	${(goodsorder.present_integral)?if_exists}                 
						             </td>
					           </tr>
					           <tr>
					             <td class="ordertd">支付时间</td>
					              <td>
					               <#if goodsorder.pay_time!="" && goodsorder.pay_time!=null>
					                 <#if (goodsorder.pay_time)?if_exists?length lt 19>
                                       ${(goodsorder.pay_time)?if_exists}                           
                                 <#else>
                                   ${(goodsorder.pay_time)?if_exists[0..18]}
                                 </#if>
                                 <#else>
                                 - -
                                 </#if>   
					            </td>
					            <td class="ordertd">支付流水号</td>
					             <td>
					              <#if goodsorder.pay_trxid!=""&& goodsorder.pay_trxid!=null>
                                  ${(goodsorder.pay_trxid)?if_exists}
                                  <#else>
                                  - -
                                  </#if>
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
			 </div>
			 
			  <br/>
			 <div>
			 <b>买家信息</b>
			 <div id="oper_seo_div" class="oper_seo_div">      
			       	 <table cellSpacing=1 cellPadding=1 width="100%;" class="tabdiv">   
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
			 </div>
			   <br/>
			 <div>
			 <b>收货人信息</b>
			 <div id="shouhuo" class="oper_seo_div">      
							       	 <table cellSpacing=1 cellPadding=1 width="100%;" class="tabdiv">   
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
										             <td class="ordertd">身份证</td>
										             <td> ${(goodsorder.identitycard)?if_exists}</td>
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
			 </div>
			 
			 
			 
			  <div>
			 <b>发票信息</b>
			 <div id="shouhuo" class="oper_seo_div"> 
			 <table cellSpacing=1 cellPadding=1 width="100%;" class="tabdiv">     
			 <#if (orderinvoice.invoice_type)?if_exists=='0'>
				                                <tr>
						                              <td id="name" class="ordertd">普通发票</td>
						                             <td width="30%;">
										             </td>
									              </tr>
									              <tr>
									             <td id="name" class="ordertd">发票抬头</td>
										             <td width="30%;">
										             ${(orderinvoice.look_up)?if_exists}
										             </td>
										             </tr>
										             <tr>
										             <td id="mobile" class="ordertd">发票内容</td>
										             <td width="30%;">
									               <#if (orderinvoice.p_content)?if_exists>
									            	 ${(orderinvoice.p_content)?if_exists}
									            	 <#else>
									            	 -
											        </#if>
										             
										             </td>
									           </tr>
									         
						          	 
					            	 <#elseif (orderinvoice.invoice_type)?if_exists=='1'>
						            
									           <tr>
									           		<td id="name" class="ordertd">发票类型</td>
										             <td width="30%;">增值发票
										             </td>
										             <td id="phone" class="ordertd">单位名称</td>
										             <td align="center">
										              <#if (orderinvoice.company_name)?if_exists>
									            	 ${(orderinvoice.company_name)?if_exists}
									            	 <#else>
									            	 -
											        </#if>
										             </td>
									           </tr>
									           
									            <tr>
									           <td id="phone" class="ordertd">注册地址</td>
										             <td align="center">
										              <#if (orderinvoice.re_address)?if_exists>
									            	 ${(orderinvoice.re_address)?if_exists}
									            	 <#else>
									            	 -
											        </#if>
										             </td>
										             <td id="post" class="ordertd">注册电话</td>
										             <td align="center">
										             <#if (orderinvoice.re_phone)?if_exists>
									            	 ${(orderinvoice.re_phone)?if_exists}
									            	 <#else>
									            	 -
											        </#if>
										             </td>
									           </tr>
									          <tr>
									           <td id="phone" class="ordertd">开户银行</td>
										             <td align="center">
										              <#if (orderinvoice.bank_name)?if_exists>
									            	 ${(orderinvoice.bank_name)?if_exists}
									            	 <#else>
									            	 -
											        </#if>
										             </td>
										             <td id="post" class="ordertd">银行账户</td>
										             <td align="center">
										             <#if (orderinvoice.bank_id)?if_exists>
									            	 ${(orderinvoice.bank_id)?if_exists}
									            	 <#else>
									            	 -
											        </#if>
										             </td>
									           </tr>
									           <tr>
									           <td id="phone" class="ordertd">发票内容</td>
										             <td align="center">
										              <#if (orderinvoice.z_content)?if_exists>
									            	 ${(orderinvoice.z_content)?if_exists}
									            	 <#else>
									            	 -
											        </#if>
										             </td>
										             <td id="post" class="ordertd">收票人姓名</td>
										             <td align="center">
										             <#if (orderinvoice.ticket_name)?if_exists>
									            	 ${(orderinvoice.ticket_name)?if_exists}
									            	 <#else>
									            	 -
											        </#if>
										             </td>
									           </tr>
									           <tr>
									           <td id="phone" class="ordertd">收票人手机</td>
										             <td align="center">
										              <#if (orderinvoice.ticket_cell)?if_exists>
									            	 ${(orderinvoice.ticket_cell)?if_exists}
									            	 <#else>
									            	 -
											        </#if>
										             </td>
										             <td id="post" class="ordertd">收票人省份</td>
										             <td align="center">
										             <#if (orderinvoice.area_attr)?if_exists>
									            	 ${(orderinvoice.v)?if_exists}
									            	 <#else>
									            	 -
											        </#if>
										             </td>
									           </tr>
									            <tr>
									            <td id="post" class="ordertd">纳税人识别码</td>
										             <td align="center">
										             <#if (orderinvoice.identifier)?if_exists>
									            	 ${(orderinvoice.identifier)?if_exists}
									            	 <#else>
									            	 -
											        </#if>
										             </td>
									           <td id="phone" class="ordertd">详细地址</td>
										             <td align="center">
										              <#if (orderinvoice.address)?if_exists>
									            	 ${(orderinvoice.address)?if_exists}
									            	 <#else>
									            	 -
											        </#if>
										             </td>
										             
									           </tr>
									           <tr>
									            <td id="post" class="ordertd">营业执照副本</td>
										             <td align="center">
										             <img src="${orderinvoice.license?if_exists}" onclick="showpicture('img_license');" height="50px" width="50px" />
	                                                 <@s.hidden  id="img_license"  value="${orderinvoice.license?if_exists}"/>
										             </td>
									           <td id="phone" class="ordertd">税务登记证副本</td>
										             <td align="center">
										               <img src="${orderinvoice.certificate?if_exists}"  height="50px" width="50px" onclick="showpicture('img_certificate');"/>
	        	                                       <@s.hidden  id="img_certificate"  value="${orderinvoice.certificate?if_exists}"/>
										             </td>
										             
									           </tr>
					                   <#else>
									             <tr>
						                              <td id="name" class="ordertd">发票类型</td>
						                             <td width="30%;">不开发票
										             </td>
									              </tr>
					                    </#if>    
							</table>       	 
					</div>
			 </div>
			   <!--endprint-->
			 <#if goodsorder.order_state !="0" && goodsorder.order_state !="1" && goodsorder.order_state !="2">
				<div>
				 		<b>物流信息</b>
						 <div id="shouhuo" class="oper_seo_div">
					       	 <table cellSpacing=1 cellPadding=1 width="100%;" class="tabdiv">   
							           <tr>
								             <td id="name" class="ordertd">物流公司</td>
								             <td width="30%;">
								            <#list sendmodeList as slist>
							                     <#if goodsorder.send_mode==slist.smode_id>
							                          ${(slist.smode_name)?if_exists}
							                          <#if slist.en_name=="ems">
							                            <#assign sendmodeflag = "${(slist.en_name)?if_exists}"/>
							                          </#if>
							                     </#if>
							                </#list>
								             </td>
								             <td id="mobile" class="ordertd">物流单号</td>
								             <td width="30%;">
								               ${(goodsorder.send_num)?if_exists}
								               <#if goodsorder.order_state=="3">
								                    <#if sendmodeflag!="ems">
								                        <input type="button" 
				                                           onclick="modifyfahuoShowDIV('invoice','${goodsorder.order_id?if_exists}','${(goodsorder.tatal_amount)?if_exists}','${(goodsorder.order_time)?if_exists}','${(goodsorder.consignee)?if_exists}','${(order_area)?if_exists}  ${(goodsorder.buy_address)?if_exists}','${(goodsorder.mobile)?if_exists}','${(goodsorder.send_num)?if_exists}','${(goodsorder.order_weight)?if_exists}','${(goodsorder.send_mode)?if_exists}');"
				                                           title="点击修改物流信息" value="修改物流" />
								                    <#else>
								                       	<input type="button"  value="修改运单号" onclick="linkToInfo('/admin_Goodsorder_chooseSendMode.action','chb_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}');" class="rbut"/>
								                    </#if>
								               </#if>
								             </td>
							           </tr>
				            </table>
					        <div class="logDynamic">
					           <h2><span>物流动态</span></h2>
					           <div class="logDycon" id="show_log">
					           </div>
					        </div>
					        <div class="prompt">
					          <h2>以上信息数据由 <a href="http://www.kuaidi100.com" target="_blank" style="color:#0579C6;">快递100</a>  提供，您可以查看每条信息及其来源</h2>
					        </div>
						</div>
						
				 </div>
			 </#if>
			 
			  <br/>
			<div>
 			<b>订单日志</b>
			 <div id="oper_seo_div" class="oper_seo_div">      
		       	 <table cellSpacing=1 cellPadding=1 width="100%;" class="tabdiv">   
		           <tr>
		             <th class="gth" width="15%;"  style='line-height:25px;'>序号</th>
		              <th class="gth" width="10%;">
		                 任务
		             </th>
		             <th class="gth" width="15%;">
		                 时间
		             </th>
		             <th class="gth" width="10%;">
		            	操作人
		             </th>
		             <th class="gth" width="30%;">
		                备注
		             </th>
		           </tr>
		           <#list ordertransList as otranslist>
		           <tr>
		             <td class="gtd">${otranslist_index+1}</td>
		             <td class="gtd">
		                <#list commparaList as comlist>
		                   <#if comlist.para_value==otranslist.order_state> 
		                   ${(comlist.para_key)?if_exists}
		                   </#if>
		                  </#list>
		             </td>
		              <td class="gtd">
		            		${(otranslist.trans_time)?if_exists}
		             </td>
		             <td align="center">
		            	${(otranslist.user_name)?if_exists}
		             </td>
		              <td class="gtd">
		            		${(otranslist.reason)?if_exists}
		             </td>
		           </tr>
		           </#list>
				           
	            </table>
			 		</div>
			 	</div>
			 <br/>
			 <div>
			 <div class="fixbuttom" style="margin-top:10px;">
			   <@s.token/>
			    <@s.hidden name="sysdesc" id="sysdesc" />
			    <@s.hidden name="sysvalue" id="sysvalue" />
			    <@s.hidden name="goodsorder.order_id"/>  
			    <@s.hidden name="goods_order_id" id="goods_order_id"/>
			  <@s.hidden name="goods_order_state" id="goods_order_state"/>
			    <#if goodsorder.sell_cust_id=='0'>
			           <#if detail_return_flage=="0">
			           <input type="button"  onclick="linkToInfo('/admin_Goodsorder_baoTaxlist.action','order_state=${(order_state)?if_exists}');" value="返回列表">
			           <#elseif detail_return_flage=="1">
			           <input type="button"  onclick="linkToInfo('/admin_Goodsorder_neibuList.action','order_state=${(order_state)?if_exists}');" value="返回列表">
			          <#elseif detail_return_flage=="2">
			          <input type="button"  onclick="linkToInfo('/admin_Goodsorder_postagegoodsList.action','order_state=${(order_state)?if_exists}');" value="返回列表">
			          <#elseif detail_return_flage=="3">
			         <input type="button"  onclick="linkToInfo('/admin_Goodsorder_cancelOrderList.action','order_state=${(order_state)?if_exists}');" value="返回列表">
			          <#elseif detail_return_flage=="4">
			         <input type="button"  onclick="linkToInfo('/admin_Goodsorder_auditOrderIndex.action','order_state=${(order_state)?if_exists}');" value="返回列表">
			         <#elseif detail_return_flage=="5">
			         <input type="button"  onclick="linkToInfo('/admin_Goodsorder_waitdeliver.action','order_state=${(order_state)?if_exists}');" value="返回列表">
			         <#elseif detail_return_flage=="6">
			         <input type="button"  onclick="linkToInfo('/admin_Goodsorder_waitconfirmdeliver.action','order_state=${(order_state)?if_exists}');" value="返回列表">
			         <#elseif detail_return_flage=="7">
			         <input type="button"  onclick="linkToInfo('/admin_Goodsorder_confirmdeliver.action','order_state=${(order_state)?if_exists}');" value="返回列表">
			        </#if>
			         <#else>
			           <input type="button"  onclick="linkToInfo('/admin_Goodsorder_list.action','order_state=${(order_state)?if_exists}');" value="返回列表">
			       </#if>
			         <input type="button" name="returnList" style="cursor:pointer;" value="打印"  onclick="doPrint();"/>
			</div>
  		 </div>
   </div>
	</div>
	<div class="clear"/>
</div>
<div class="clear"></div>
  <@s.hidden name="logistics_query" id="logistics_query_id"/>
  <@s.hidden name="kuai_company" id="kuai_company_id"/> 
</@s.form>
  
<!--修改运单号区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Goodsorder_updateBillnos.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
	 <tr>
			<td class="searchDiv_td">运单号</td> 
			<td><@s.textfield id="billnos" name="billnos"  cssStyle="width:180px;"/></td>
	 </tr>
		<tr>
			<td align="center" colspan="2" style="border:0px;">
			    <@s.hidden name="goodsorder.order_id" />
				<input type="submit" id="confirmBtn" name="confirmBtn"  value="确定" />&nbsp;
			   <input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			</td>
	   </tr>
		</table>
		</@s.form>
	</div>		   
<!--修改运单号区域结束-->  
  <!-- 打印票据 start-->
<div  style="display:none;" id="invoice" >
      <@s.form id="DeliveryForm" action="/admin_Goodsorder_confirmDeliveryGoodsOrder.action"  method="post" class="searchDiv" >
	        <table >
	         <tr>
	           	<td  class="table_name">订单信息：</td>
           		<td id="orderinfo_id"></td>
	          </tr>
	          <tr>
	           	<td  class="table_name">收货人信息：</td>
           		<td id="shouhuoren_id"></td>
	          </tr>
	          <tr>
	           	<td  class="table_name">物流公司：</td>
           		<td> <@s.select list="sendmodeList" listKey="smode_id" listValue="smode_name" name="order_send_mode" style="width:120px" id="sendmode_id"/></td>
	          </tr>
	           <tr>
	           	<td  class="table_name">重量(KG)：</td>
           		<td><@s.textfield name="orderweight" cssStyle="width:100px;" maxLength="10" id="orderweight" onkeyup="checkFloat(this);" onblur="sellerOrderWeight();" />
           		  <span class="errorSpan" style="display:none;" id="weighttip"></span>
           		 </td>
	          </tr>
	           <tr>
	           	<td  class="table_name">运单号：</td>
           		<td>
           		<@s.textfield  name="order_send_num" id="order_send_num_id"  onblur="sellerSend_num();" maxLength="${sendlength}" cssStyle="width:150px;"/>
           		 <span>(长度为${sendlength}位)</span>
                <span class="errorSpan" style="display:none;" id="ydhtip"></span>
           		</td>
	          </tr>
	          <tr>
	           <td  colspan="2" align="center"><br/>
	             <@s.hidden name="goods_order_ids" id="fahuo_order_id" />
	             <@s.hidden name="is_modify" value="1" />
	              <@s.hidden name="is_modify_update" value="1" />
	           	<input type="button" onclick="modifypostConfirmDelivery()" value="确认修改物流"/>
	           	<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('invoice')"/>
	           </td>
	          </tr> 
	       </table>
	     </@s.form>
</div>
</body>
</html>

