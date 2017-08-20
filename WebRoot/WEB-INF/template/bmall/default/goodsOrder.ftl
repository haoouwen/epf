<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>${cfg_webname?if_exists}_提交订单</title>
<#include "/WEB-INF/template/bmall/default/jscsstop.ftl">
<link href="/malltemplate/bmall/css/payProcess.css" rel="stylesheet" type="text/css">
<script src="/malltemplate/bmall/js/order.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/orderMainjs.js" type="text/javascript"></script>
<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
<script type="text/javascript" src="/include/common/js/v-get-cat-area.js"></script> 
<script type="text/javascript" src="/include/common/js/jquery.jNotify.js"></script>
<script type="text/javascript" src="/include/common/js/jquery.alert.js"></script>
<link href="/include/common/css/alert.css" rel="stylesheet" type="text/css" />
<link href="/malltemplate/index/css/mall_public.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript">
//初始化加载
$(document).ready(function(){
history.go(1);
	//所属地区的回选
	loadArea("${areaIdStr?if_exists}","");
v_loadArea("${areaIdStr?if_exists}","");
 });
 function ge(id){
	loadArea(id,"");
 }
 </script>
 <#include "/include/uploadifive.html"> 
<body>

<@s.form id="subOrder" action="/mall/order!subOrder.action" method="post">

<!--订单提交隐藏域开始-->
<@s.hidden id="addr_id" name="addr_id"/>
<!--订单提交隐藏域结束-->
        <!--顶部-->
<#include "/WEB-INF/template/bmall/default/topest.ftl">
        <!--logo，搜索-->
        <div class="logoSearDiv">
            <!--logo-->
            <div class="logo">
                <a href="/">
                    <img src="${cfg_logourl}"/>
                </a>
            </div>
            <!--支付步骤-->
            <div class="orderProcess">
            </div>
        </div>
        <!--内容-->
        <!--订单信息-->
       		
<div class="address">
    <h2>确认收货地址</h2>
    <div class="addressCont" id="addressCont">
       <!---->
       <div id="addressDiv"></div>
       <p class="addrsp"><span></span></p>
    </div>
</div>	

<!--新增地址-->
<div class="popupDiv"  id="addressId">
   <div class="addAddress">
      <table width="100%" cellpadding="0" cellspacing="0">
        <tr><th width="15%"><span>*</span>收货人：</th><td width="85%"><input type="text" id="consignee" name="consignee" class="nameText" maxlength="10" /></td></tr>
            <tr><th><span>*</span>所在地区：</th><td><div id="areaDiv"></div></td></tr>
            <tr><th><span>*</span>详细地址：</th><td><input type="text" id="address"  name="address"  class="addressText"  maxlength="50"></textarea></td></tr>
            <tr><th><span>*</span>手机号码：</th><td><input type="text"  id="cell_phone" name="cell_phone" onkeyup="checkDigital(this)" class="nameText" maxlength="11"  /></td></tr>
		   <tr><th>联系电话：</th><td><input type="text" class="nameText" id="phone" name="phone" onkeyup="checkNum(this)" maxlength="12"></td></tr>
		   <tr><th><span>*</span>身份证：</th><td><input type="text" class="nameText" id="identitycard" name="identitycard"  maxlength="18" /><span></span></td></tr>
            <tr><th></th><td><input type="button" class="saveBut" onclick="addAddr()" value="保存信息" /></td></tr>
      </table>
    </div>
</div> 

        <!--订单信息-->
        <div class="invoice">
            <h2>
                支付配送方式及发票信息
            </h2>
            <div class="invDiv">
                <table width="100%" cellpadding="0" cellspacing="0">
                    <tr>
                        <th>
                            支付方式：
                        </th>
                        <td>
                            在线支付 
                        </td>
                    </tr>
                    <tr>
                        <th>
                            配送方式：
                        </th>
                        <td>
                            快递运输
                        </td>
                    </tr>
                    <tr >
                        <th>
                            发票信息：
                        </th>
                        <td>
                        <span id="in_type">
                                 不开发票
                            </span>
                            <span id="in_look_up">
                            </span>
                            <span id="in_content">
                            </span>
                            <i class="iModify">
                                [修改]
                            </i>
                        </td>
                    </tr>
                </table>
 
       
    <!--发票信息-->
<div class="popupDiv" id="invStyleId">
  
    <div class="invStyle" id="instDivId">
    
        <div class="instDiv">
           <ul>
             <li>普通发票</li>
             <li>增值税发票</li>
             <li>不开发票</li>
           </ul>
        </div>
        
        <div class="tabDiv">
            <div class="insfTabDiv padtop">
              <table width="100%" cellpadding="0" cellspacing="0">
                  <tr>
                    <th>发票抬头：</th>
                    <td><@s.textfield type="text" class="pText"  id="personlook_up" /></td>
                 </tr>
                  <tr>
                    <th>发票内容：</th>
                    <td><span><@s.radio id="p_content"  list=r"#{'0':'明细','1':'办公用品(附购物清单)'}" name="p_content_radio" value="0"/></span></td>
                  </tr>
                  <tr>
                    <th></th>
                    <td><input type="button"  class="saveBut" onclick="setperson()"  value="保存信息"></td>
                  </tr>
                  <tr>
                    <th></th>
                    <td>温馨提示：发票的开票金额不包括优惠劵/积分等支付部分</td>
                  </tr>
              </table>
            </div>
          
            <div class="insfTabDiv">
              <table width="100%" cellpadding="0" cellspacing="0">
                  <tr valign="top">
                    <td width="50%">
                       <p><span class="thSpan"><i>*</i>单位名称：</span><@s.textfield id="company_name"  cssClass="pText" maxlength="50"/></p>
                       <p><span class="thSpan"><i>*</i>纳税人识别码：</span><@s.textfield id="identifier"  cssClass="pText" maxlength="20"/></p>
                        <p><span class="thSpan"><i>*</i>注册地址：</span><@s.textfield id="re_address"  cssClass="pText" maxlength="50"/></p>
                        <p><span class="thSpan"><i>*</i>注册电话：</span><@s.textfield id="re_phone"    cssClass="pText" maxlength="20" />  <img class="ltip" src="/include/common/images/light.gif" title="例如：010-66668888"></p>
                        <p><span class="thSpan"><i>*</i>开户银行：</span><@s.textfield id="bank_name"  cssClass="pText" maxlength="20"/></p>
                        <p><span class="thSpan"><i>*</i>银行账户：</span><@s.textfield id="bank_id"   cssClass="pText" maxlength="30"/></p>
                        <p><span class="thSpan">发票内容：</span>明细</p>
                    </td>
                    <td width="50%">
                       <p><span class="thSpan"><i>*</i>收票人姓名：</span><@s.textfield id="ticket_name"  cssClass="pText" maxlength="10"/></p>
                       <p><span class="thSpan"><i>*</i>收票人手机：</span><@s.textfield id="ticket_cell"   cssClass="pText" maxlength="11"/></p>
                       <p><span class="thSpan""><i>*</i>收票人省份：</span><span id="v_areaDiv" ></span></p>
                       <p><span class="thSpan"><i>*</i>详细地址：</span><@s.textfield id="addresstail"  cssClass="pText" maxlength="100"/></p> 
                       <p><span class="thSpan f_left"><i>*</i>营业执照：</br>(副本需加盖公章)</span>
                           <table class="spanTab f_left">
                           <tr>
                           <td>
						    <input type="text" id="uploadresult0" style="float:left;width:150px;height:25px;line-height:25px;"/>
       		    	        <input id="file_upload" type="file" name="file" />
       		    	        <input id="showpic" type="button" onclick="showpicture('uploadresult0');" value="预览" style="float:left;background:#f1f9eb;border:1px solid #bfd7af;color:#333;text-align:center;width:50px;height:32px;"/>
		                    <div id="tip-queue" style="clear:both;display:none;"></div>
			                <script>uploadone('file_upload','uploadresult0',false,'tip-queue');</script>
						   </td>
						   <td><@s.fielderror><@s.param>orderinvoice.license</@s.param></@s.fielderror></td>
						  </tr>
						  </table>
                       </p> 
                       <p>
                          <span class="thSpan f_left"><i>*</i>税务登记证：</br>(副本需加盖公章)</span>
                          <table class="spanTab f_left">
                           <tr>
                           <td> 
						    <input type="text" id="uploadresult1" style="float:left;width:150px;height:25px;line-height:25px;"/>
       		    	        <input id="file_upload1" type="file" name="file" />
       		    	        <input id="showpic" type="button" onclick="showpicture('uploadresult1');" value="预览" style="float:left;background:#f1f9eb;border:1px solid #bfd7af;color:#333;text-align:center;width:50px;height:32px;"/>
		                    <div id="tip-queue1" style="clear:both;display:none;"></div>
			                <script>uploadone('file_upload1','uploadresult1',false,'tip-queue1');</script>
						   <td>
						   <td><@s.fielderror><@s.param>orderinvoice.certificate</@s.param></@s.fielderror></td>
						  
						  </tr>
						  </table>         
                       </p> 
                    </td>
                 </tr>
                  <tr>
                    <td colspan="2" class="padTd"><input type="button"  class="saveBut"  onclick="setZeng()"  value="保存信息">&nbsp;&nbsp;温馨提示：发票的开票金额不包括优惠劵/积分等支付部分</td>
                  </tr>
              </table>
            </div>
            <div class="insfTabDiv padtop">
              <table width="100%" cellpadding="0" cellspacing="0">
                  <tr>
                    <th></th>
                    <td><input type="button"  class="saveBut" onclick="noset()"  value="确定"></td>
                  </tr>
              </table>
            </div>
            
          </div>
    </div>
</div> 
</div>
    
        <!--订单信息-->
        <div class="order">
            <h2>
                确认订单信息
            </h2>
            <div class="cartDiv">
                <!---->
                <div class="carth3">
                    <table width="100%" cellpadding="0" cellspacing="0">
                        <tr>
                            <th width="45%">商品名称</th>
                            <th width="13%">规格/型号</th>
                            <th width="10%">单价(元)</th>
                            <th width="7%">关税(元)</th>
                            <th width="7%"> 数量</th>
                            <th width="11%">小计(元)</th>
                        </tr>
                    </table>
                </div>
                <!---->
                
         <#assign total=0>
        <#assign ship_total=0>
        <#assign shoptotal=0>
        <#assign shoptotal_norate=0>
        <#assign goodstotal=0>
         <#assign om= 0>
         <#assign tax_rate_all = 0>
         <#assign active="0">
        <#assign allnum=0.0>
        <#assign zy_count=0.0>
        <#assign bx_count=0.0>
        <#assign zy_num=0.0>
        <#assign bx_num=0.0>
                <div class="cartTab ordercont">
                    <table width="100%" cellpadding="0" cellspacing="0">
                         <#list shopList as shopMap>
                           <#if shopMap.shop_cust_id=='1'>
                           <tr><th colspan="8">仓库：<span>直邮仓库</span></th></tr>
                            <#else>
                             <tr><th colspan="8">仓库：<span>保税区仓库</span></th></tr>
                            </#if>
			        	<#assign shop_cust_id = "${(shopMap.shop_cust_id)?if_exists}">
			        	<@s.hidden id="cust_id_str" name="cust_id_str" value="${(shopMap.shop_cust_id)?if_exists}"/>
			        	<@s.hidden  name="goods_length_str" value="${(shopMap.goods_length)?if_exists}"/>
						 <#list orderList as order>
						 	<#if (order.cust_id)?if_exists==shop_cust_id>
						 		<@s.hidden  name="goods_id_str" id="goods_id_str" value="${(order.goods_id)?if_exists}"/>
						 		<@s.hidden  name="order_num_str" id="order_num_str"  value="${(order.order_num)?if_exists}"/>
						 		<@s.hidden  name="goods_name_str" value="${(order.goods_name)?if_exists}"/>
						 		<@s.hidden  name="goods_img_str" value="${(order.goods_img)?if_exists}"/>
						 		<@s.hidden  name="spec_id_str" id="spec_id_str"  value="${(order.spec_id)?if_exists}"/>
						 		<@s.hidden name="spec_name_str" value="${(order.spec_name)?if_exists}"/>
						 		<@s.hidden  name="sale_price_str" value="${(order.sale_price)?if_exists}"/>
						 		<@s.hidden  name="give_inter_str" value="${(order.give_inter)?if_exists}"/>
						 		<#assign num = 0>
						 		<#assign om =om+ 1>
						 		<#assign price = 0>
						 		<#assign tax_rate = 0>	
						 		<#assign tax_rate ="${(order.tax_rate)?if_exists}" >	
						 		<#assign sum = 0>
						 		<#assign price = "${(order.sale_price)?if_exists}">
						 		<#assign num = "${(order.order_num)?if_exists}">
						 		<#assign allnum="${num?number + allnum?number}">
						 		<#assign sum = "${price?number * num?number}">
						 		<#if order.sale_id?if_exists !=null && order.sale_id !="">
						 		<#assign sum=stack.findValue("@com.rbt.function.SalegoodsFuc@goSaleprice('${order.sale_id?if_exists}','${sum?if_exists}')")>
						 		</#if>						 		
						 		<#assign tax_rate = "${sum?number * tax_rate?number/100}">
						 		<#if shopMap.shop_cust_id=='1'>
	                           		<#assign zy_count="${zy_count?number+sum?number}">
							        <#assign zy_num="${zy_num?number+num?number}">
	                            <#else>
	                            	 <#assign bx_count="${bx_count?number+sum?number}">
	                            	 <#assign bx_num="${bx_num?number+num?number}">
	                            </#if>
                        <tr>
                            <td width="45%" class="imgTd">
                                <div class="imgsDiv">
                                    <img src="${(order.goods_img)?if_exists}" width="60" height="60">
                                </div>
                                <div class="textDiv">
                                    <p>
                                        <a  href="/mall-goodsdetail-${(order.goods_id)?if_exists}.html" target="_blank">${(order.goods_name)?if_exists}</a>
                                    </p>
                                    <#if order.use_integral=='1'>
                                       <@s.hidden id="inter_sub" name="inter_sub" value="0" cssClass="yfText inter_sub"/> 
					                <#else>
					                   <@s.hidden id="inter_sub" name="inter_sub" value="0" cssClass="yfText inter_sub"/> 
			                	   </#if>
                                    <#if order.sale_name?if_exists != "">
                                    <#assign active="1">
                                    <p><span>${order.sale_name?if_exists}</span></p>
                                    </#if>
                                </div>
                            </td>
                            <td width="13%" class="proTd">
                                ${(order.spec_name)?if_exists}
                            </td>
                            <td width="10%">
                                <b>
                                <#if order.old_price!= null && order.sale_price?if_exists?number != order.old_price?if_exists?number>
	                        	<span class="oldP">${order.old_price?if_exists}</span></br>
	                        	</#if>
                                  ${price}
                                </b>
                            </td>
                             <td width="7%">
                                <b>
                                  ${tax_rate}
                                </b>
                            </td>
                            
                            <td width="7%">
                               ${num}
                            </td>
                            
                            <td width="11%">
                                <b class="priceb">
                                    ${sum}
                                </b>
                                <@s.hidden id="totaltaxrate${(order.goods_id)?if_exists}" value="${tax_rate}"/>
                                <@s.hidden id="taxrate${(order.goods_id)?if_exists}" value="${(order.tax_rate)?if_exists}"/>
                                <@s.hidden id="subtotal${(order.goods_id)?if_exists}" value="${sum}"/>
                                <@s.hidden  name="subtotal_str" value="${sum?if_exists}"/>
                                <@s.hidden  id="shop_cust_id${(order.goods_id)?if_exists}" value="${shop_cust_id?if_exists}"/>                                
                            </td>
                        </tr>
                        <#setting number_format="0.##">
                       <#assign tax_rate_all = "${(tax_rate_all?number + tax_rate?number)?number}">
                       <#assign shoptotal = "${((shoptotal?number + sum?number))}">
                       <#if saleorder != null && saleorder.term_state?if_exists == "3" && saleorder.term?trim?index_of('${order.goods_id?if_exists}') gt -1>
                         <#assign goodstotal='${goodstotal?number + sum?number }'>
                       </#if>
                       
				 	</#if>
				 	</#list>
				 	</#list>
                    </table>
                  </div><!--cartTab end-->
                       <#if integral_state?if_exists == '0'>
                       <#assign tax_rate_all = "${(tax_rate_all?number) *discount}">
                       <#assign gshoptotal = "${shoptotal?if_exists?number *discount}">
                       <#assign shoptotal_norate="${(shoptotal?number)*discount}">
                       <#assign goodstotal='${goodstotal?number * discount }'>
                       <#else>
                       <#assign tax_rate_all = "${(tax_rate_all?number)}">
                       <#assign gshoptotal = "${shoptotal?if_exists?number}">
                       <#assign shoptotal_norate="${(shoptotal?number)}">
                        </#if>
                        <#if tax_rate_all?number gt 50>
                       			<#assign gshoptotal = "${((gshoptotal?number+tax_rate_all?number))}">
                       <#else>
              					 <#assign gshoptotal = "${((gshoptotal?number))}">
                       </#if>   
                       <@s.hidden id="shoptotal_norate" value="${shoptotal_norate?if_exists}"/>
                       <#if tax_rate_all?number gt 50>
                       			<#assign shoptotal = "${((shoptotal_norate?number+tax_rate_all?number))}">
                       <#else>
              					 <#assign shoptotal = "${((shoptotal_norate?number))}">
                       </#if>
         
      <#if integral_state == "0">                
        <!--优惠的使用-->
       <div class="youhuiDiv">
          <ul>
            <#assign list_index=0>
            <#if comsumerList?size gt 0>
            <li>
              <h3>使用优惠劵</h3>
              <div class="yhDiv">
                 <table width="100%" cellpadding="0" cellspacing="0">
                    <#list comsumerList as comsumer>
                    <#assign list_index= list_index +1>
                     <tr>
                       <td width="4%">
                         <input type="checkbox" name="coupon" <#if comsumer.use?if_exists !="1" || active?if_exists !="0">disabled="true"</#if> onclick="useCoupon('${list_index?if_exists}','${comsumer.comsumer_id?if_exists}','${comsumer.coupon_id?if_exists}','${comsumer.goods_id?if_exists}','1','${comsumer.coupon_money?if_exists}');" value="${list_index}"></td><td width="10%"><span>${comsumer.coupon_name?if_exists}</span></td><td width="86%" class="colortd">有效期至：${comsumer.end_time?if_exists} <#if comsumer.need_state?if_exists=="1"><#assign goods_name=stack.findValue("@com.rbt.function.GoodsFuc@getGoodsName('${comsumer.term?if_exists}')")>指定商品：<#if goods_name?if_exists?length gt 30 ><i title="${goods_name?if_exists}">${goods_name?if_exists[0..29]}...</i><#else>${goods_name?if_exists}</#if><#elseif comsumer.need_state?if_exists=="2"><#assign cat_name=stack.findValue("@com.rbt.function.CategoryFuc@getCateNameByMap('${comsumer.term?if_exists}')")>指定分类：${cat_name?if_exists}<#elseif comsumer.need_state?if_exists=="3">全品类</#if>
                       </td>
                     </tr>
                    </#list> 
                 </table>
              </div>
            </li>
            </#if>
            <#if redsumerList?size gt 0>
            <li>
              <h3>使用红包</h3>
              <div class="yhDiv">
                 <table width="100%" cellpadding="0" cellspacing="0">
                    <#list redsumerList as redsumer>
                    <#assign list_index= list_index +1>
                    <tr><td width="4%"><input type="checkbox" name="coupon"  <#if active?if_exists !="0">disabled="true"</#if> onclick="useCoupon('${list_index?if_exists}','${redsumer.redsumer_id?if_exists}','${redsumer.red_id?if_exists}','','2','${redsumer.money?if_exists}');" value="${list_index?if_exists}"></td><td width="10%"><span>${redsumer.money?if_exists}元</span></td><td width="86%" class="colortd">${redsumer.red_name?if_exists}...&nbsp;有效期至：${redsumer.end_time?if_exists} </td></tr>
                    </#list> 
                 </table>
              </div>
            </li>
            </#if>  
            <#--<li>
              <h3>使用积分</h3>
              <div class="yhDiv">
                 <table width="100%" cellpadding="0" cellspacing="0">
                    <tr><th><b>本次使用<@s.textfield  cssClass="yfText" onkeyup="checkNums(this);" name="use_inter" id="use_inter"/>积分支付</b><input type="button" value="使用" class="syBut" onclick="useInter()"></th></tr>
                 </table>
                 <p class="yhp">您有积分<b>${allinter}</b>，本次可以使用<b>${(allinter?number/cfg_sc_exchscale?number)?int*cfg_sc_exchscale?number}</b>积分</p>
              </div>
            </li>-->
            <li>
              <h3>使用余额</h3>
              <div class="yhDiv">
                 <table width="100%" cellpadding="0" cellspacing="0">
                    <tr>
                       <td>
                         <input type="checkbox" onclick="isUseNumPay(this)"  id="paytype_id" name="paytype_id" <#if (memberfund.use_num)?if_exists =='0.00' >disabled="true"</#if>>
                  	     使用余额（账户当前余额：<#if (memberfund.use_num)?if_exists!='0'>${(memberfund.use_num)?if_exists}<#else>0</#if>元）
                        </td>
                    </tr>
                    <tr>
                    <td>
                     <b>支付密码：</b>
                      <input type="password" id="pay_password" name="pay_password" class="yfText">
                  	   <@s.fielderror><@s.param>pay_password</@s.param></@s.fielderror>
                  	   <a href="/bmall_Memberfund_goPass.action?parentMenuId=6644344633&selli=2251641346">忘记支付密码？</a>
                     </td>
                    </tr>
                 </table>
              </div>
            </li>
          </ul>
       </div>
       </#if>
   <!---->
                     
                <div class="freDiv">
                       <@s.hidden cssClass="goods_amount_str" name="goods_amount_str" id="goods_amount_str" value="${gshoptotal?if_exists}"/>         
                        <#assign shoptotal = "${shoptotal?number}">
			         	<@s.hidden id="shop_total_amount_str" name="shop_total_amount_str" value="${shoptotal?if_exists}"/>   
			         	<@s.hidden id="smode_id_str" name="smode_id_str" value="0"/>    
			         	<@s.hidden id="ship_price" name="ship_free_str" value="0"/> 
			         	<@s.hidden id="hasfree" name="hasfree" /> 
		         		<@s.hidden id="cfg_sc_exchscale" name="cfg_sc_exchscale" /> 
		         		<@s.hidden id="use_paynum" name="use_paynum" value="${(memberfund.use_num)?if_exists}" /> 
		         		<@s.hidden id="use_num" name="use_num" value="${(memberfund.use_num)?if_exists}" /> 
			         	<@s.hidden id="is_use_inter" name="is_use_inter"value="0" />
			         	<@s.hidden id="allinter" name="allinter" value="${allinter?if_exists}"/> 	
			         	
			         	
			         	 <input type="hidden" name="end_area_attr" id="end_area_attr" value="7"/>
                        <p>已选
                            <span>
                              ${om}
                            </span>
                            种商品&nbsp;&nbsp;共<span>${allnum}</span>件&nbsp;&nbsp;总商品金额：<span id="goods_shop">${shoptotal_norate?if_exists}</span>元
                            <#if discount!=1 && integral_state?if_exists == '0'>
                            <span>
                              (${discount*100}折)
                            </span>
                            </#if>
                        </p>
                        <p class="ship_fee1">
                            运费：
                            <#assign is_ship_free="0">
                            <#assign bao_ship="0">
                            <#assign needs_ship_3="0">
                            <#if saleorder != null && saleorder.coupon_state == '3'  && integral_state?if_exists == '0'>
		                            <#if (saleorder.term_state == '1' || saleorder.term_state == '2' ) && shoptotal?if_exists?number gte saleorder.need_money?if_exists?number>
		                            <#assign is_ship_free="1">
		                            <#assign bao_ship="${saleorder.term?if_exists}">
		                            <span>0</span>元
		                            <i>亲,${cfg_webname}给你包邮啦!!</i>
		                            <#elseif saleorder.term_state == '3'  && goodstotal?if_exists?number gte saleorder.need_money?if_exists?number>
		                             <#assign is_ship_free="1">
		                             <#assign bao_ship="${saleorder.term?if_exists}">
		                            <#assign needs_ship_3="${saleorder.need_money?if_exists}">
		                            <span>0</span>元
		                            <i>亲,${cfg_webname}给你包邮啦!!</i>
		                            <#elseif saleorder.term_state == '4'  && shoptotal_norate?if_exists?number gte saleorder.need_money?if_exists?number>
		                            <#assign is_ship_free="1">
		                            <#assign bao_ship="${saleorder.term?if_exists}">
		                            <span>0</span>元
		                            <i>亲,${cfg_webname}给你包邮啦!!</i>
		                            <#else>
		                            <span class="ship_fee" id="ship_fee">0</span>元
		                            </#if> 
                            <#else>
                            <span class="ship_fee" id="ship_fee">0</span>元
                            </#if> 
                        </p>
                         <p class="" id="duty">
                      关税：
                             <#if tax_rate_all?number gt 50>
                       			${tax_rate_all}
		                       <#else>
		                      			 0
		                       </#if>
                       
                            
                            元
                        </p>
                        <#if integral_state?if_exists == "0">
                        <p class="" >优惠券/红包：<span id="couponMoney">0</span>元</p>
                        <p class="" >订单优惠：<span id="oderMoney">${order_money?if_exists}</span>元</p>
                        <#if order_names?if_exists != "">
                        <p>订单促销活动：<font color="red" title="${order_names?if_exists}">
                        <#if order_names?if_exists? length lt 12 >${order_names?if_exists}<#else>${order_names[0..11]?if_exists}...</#if></font></p>
                        </#if>
                        <p class="orderprice" id="orderprice">
                            应付总额：${shoptotal}元
                        </p>
                        <p class="" >
                            余额支付：<span id="usepayprice">0</span>元
                        </p>
                        <p class="" >
                            剩余应付总额：<span id="overprice">${shoptotal}</span>元</p>
                        <#else>
                         <p>您当前积分：<span>${allinter}</span></p> 
                           <p class="" >
                            应付总积分：<span id="overprice">${shoptotal}</span></p>
                        </#if>    
                            
                           </div>
                </div>
                <!---->
          
               
                
        <#assign total = "${total?number + shoptotal?number}"> 
        <@s.hidden id="goods_price" value="${total?number}"/>
        <@s.hidden name = "shoptotal_norate" id="shopgoodstotal" value="${shoptotal_norate}"/>
        <@s.hidden id="total_amount" name="total_amount" value="${total?if_exists}"/>
        <@s.hidden id="all_total" name="all_total" value="${total?if_exists}"/>
        <@s.hidden id="all_totals" name="all_totals" value="${total?if_exists}"/>
        <@s.hidden id="coupon_total" name="coupon_total" value="${total?if_exists}"/>
        <@s.hidden id="red_total" name="red_total" value="${total?if_exists}"/>
        <@s.hidden id="all_tax" name="all_tax" value="${tax_rate_all?if_exists}"/>
        <@s.hidden id="order_type" name="order_type"/>
        <@s.hidden id="group_id" name="group_id"/>
        <@s.hidden name="spike_id" />
        <@s.hidden id="cfg_order_allfund" name="cfg_order_allfund"/>
        <@s.hidden id="cfg_freight" name="cfg_freight"/>
        <@s.hidden id="cartId_str" name="cartId_str" value="${(trade_id_str)?if_exists}"/>
		<@s.hidden id="cookieId_str" name="cookieId_str" value="${(cookie_id_str)?if_exists}"/>
		<@s.hidden  name="orderinvoice.invoice_id" />
		<@s.hidden  name="orderinvoice.invoice_type"id="invoice_type"value="2"/>
		<@s.hidden  name="orderinvoice.z_content" id="z_content" value="明细"/>
		<@s.hidden id="now_order_allfund" name="now_order_allfund" value="${total?if_exists}"/>
		<@s.hidden id="cfg_maxpay" name="cfg_maxpay"/>
		<@s.hidden id="zy_count" value="${zy_count}"/>
		<@s.hidden id="bx_count" value="${bx_count}"/>
		<@s.hidden id="zy_num" value="${zy_num}"/>
		<@s.hidden id="bx_num" value="${bx_num}"/>
		<@s.hidden id="integral_state" name="integral_state" value="${integral_state?if_exists}"/>
		<!-- 优惠券参数-->
		<@s.hidden id="coupon_money" name="coupon_money"/>
		<@s.hidden id="coupon_goods_id" name="coupon_goods_id"/>
		<@s.hidden id="coupon_id" name="coupon_id"/>
		<@s.hidden id="comsumer_id" name="comsumer_id"/>
		<@s.hidden id="coupon_cust_id" name="coupon_cust_id"/>
		<@s.hidden id="newtax" name="newtax"/>
		
		<!-- 红包参数-->
		<@s.hidden id="red_id" name="red_id"/>
		<@s.hidden id="redsumer_id" name="redsumer_id"/>
		<@s.hidden id="red_money" name="red_money"/>
		<@s.hidden id="is_ship_free" name="is_ship_free" value="${is_ship_free?if_exists}"/>
		<@s.hidden id="bao_ship" value="${bao_ship?if_exists}"/>
		<@s.hidden id="total_ship"/>
		<@s.hidden id="discount" value="${discount}"/>
		<@s.hidden id="webname" value="${cfg_webname}"/>
		<@s.hidden id="order_money" value="${order_money?if_exists}"/>		

	   <@s.hidden id="hpersonlook_up" name="orderinvoice.look_up"/>
       <@s.hidden id="hp_content" name="orderinvoice.p_content"/>
       <@s.hidden id="hcompany_name" name="orderinvoice.company_name"/>
       <@s.hidden id="hidentifier" name="orderinvoice.identifier"/>
       <@s.hidden id="hre_address" name="orderinvoice.re_address"/>
       <@s.hidden id="hre_phone" name="orderinvoice.re_phone"/>
       <@s.hidden id="hbank_name" name="orderinvoice.bank_name"/>
       <@s.hidden id="hbank_id" name="orderinvoice.bank_id"/>
       <@s.hidden id="hticket_name" name="orderinvoice.ticket_name"/>
       <@s.hidden id="hticket_cell" name="orderinvoice.ticket_cell"/>
       <@s.hidden id="haddresstail" name="orderinvoice.address"/>
       <@s.hidden id="hlicense" name="orderinvoice.license"/>
       <@s.hidden id="hcertificate" name="orderinvoice.certificate"/>
       <@s.hidden id="v_area_attr" name="v_area_attr"/>
       <@s.hidden id="v_area_attr" name="v_area_attr"/>
       <@s.hidden id="ship_goodstotal_id" value="${goodstotal?if_exists}"/>
       <@s.hidden id="needs_ship_3" value="${needs_ship_3?if_exists}"/>
       <@s.hidden id="sub_total_negative" value="0"/>
      
                <div class="settle">
                <span class="lsSpan">
                        <a href="/cart.html">
                        </a>
                    </span>
                    <span class="rsSpan">
                        <b>
                            总计(含运费)：
                        </b>
                        <b class="priceb cartprice"  id="alltotal">
                          ${total}
                        </b>
                        <#if integral_state =='0'>
                           元
                        <#else>
                           积分 
                        </#if>
                        <#if integral_state?if_exists == '1'>
                        <a href="javascript:void(0);" onclick="submitIntegral();"></a>
                        <#else>
                        <a href="javascript:void(0);" onclick="submitOrder();"></a> 
                        </#if>
                    </span>
                </div>
            </div>
        </div>
        <!--尾部-->
        <div class="clear">  </div>
<#include "/a/bmall/mallbottom.html">
    </body>
    <script type="text/javascript" src="/include/common/js/common.js"></script>
    <script src="/malltemplate/bmall/js/jqueryPopupLayer.js" type="text/javascript"></script>
    <script src="/malltemplate/bmall/js/payProcess.js" type="text/javascript"> </script>
    <script src="/malltemplate/bmall/js/jqueryTab.js" type="text/javascript"> </script>
	<script src="/include/components/artDialog5.0/artDialog.min.js"></script>
	<link href="/include/components/artDialog5.0/skins/blue.css" rel="stylesheet" />
	<link href="/malltemplate/bmall/css/payProcess.css" rel="stylesheet" type="text/css">
   <@s.token/> 
	</@s.form>
</html>