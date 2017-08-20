<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}—提交订单</title>

<meta name="author" content="久通宏达科贸（北京）有限公司">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">           
<meta http-equiv="Pragma" content="no-cache">
<link href="/wro/webapp_common.css" rel="stylesheet" type="text/css"/>
<link href="/malltemplate/jiutong/css/mbShopProcess.css" rel="stylesheet" type="text/css"></link>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>

<body>
 <@s.form id="subOrder" action="/webapp/order!subOrder.action?type=0" method="post">
 
<div class="top">
  <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>提交订单<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<#include "/a/webapp/mbCommon.html">
  

<@s.hidden id="addr_id" name="addr_id"/>
<input type="hidden" name="end_area_attr"  id="end_area_attr"  value="7" />
<!--收货地址-->
<div class="address">

    <div class="addrh2">收货地址</div>
    <div  id="addrContent"></div>
    <!---->
    <div class="addAddrBut" onclick="addnewaddress();">增加收货地址</div>
    
</div>


<!--支付,配送方式及发票信息-->
<div class="way">
   <div class="wayh2">支付,配送方式</div>
   <div class="wayDiv">
     <ul>
       <li><h3>支付方式： 在线支付</h3></li>
       <li><h3>配送方式： 由商家选择合作快递为您配送</h3></li>
       <li><h3 class="fph3">发票信息：
        <span id="in_type">不开发票</span>
        <span id="in_look_up"> </span>
        <span id="in_content"></span>
         </h3>
            
           <div class="fpDiv"  id="invStyleId">
           
             <div class="instDiv"><span onclick="setHidden('invoice_type','2')">不开发票</span><span onclick="setHidden('invoice_type','0')">普通发票</span><span onclick="setHidden('invoice_type','1')">增值税发票</span></div>
             <div class="fpCont">
                <div class="fpBut" onclick="noset()">保存</div>
              </div>
             <div class="fpCont">
                <p><span>发票抬头：</span>
                <@s.textfield cssClass="fpText"   id="personlook_up" />
                </p>
                <p><span>发票内容：</span>
                <#assign p_content="">
                    <#list p_contentList as p_contentlist>
                   			  <input type="radio" name="p_content_radio" onclick="setHidden('p_content','${p_contentlist.para_value}')" value="${p_contentlist.para_value}">${p_contentlist.para_key}
                   			  <#if p_contentlist_index == 0>
                   			   <#assign p_content=p_contentlist.para_value>
                   			   </#if>
                    </#list>
                 </p>
                <div class="fpBut" onclick="setperson()">保存</div>
              </div>
              
              <div class="fpCont">
                <p><span>发票内容：</span>
                 <#assign z_content="">
                       <#list z_contentList as z_contentlist>
                   			 <span onclick="setHidden('z_content','${z_contentlist.para_key}')" >${z_contentlist.para_key}</span>
                   			   <#if z_contentlist_index == 0>
                   			   <#assign z_content=z_contentlist.para_key>
                   			   </#if>
                   			 
                    </#list>
                 </p>
                <p><span class="thSpan"><i>*</i>单位名称：</span><@s.textfield  id="company_name"  cssClass="fpText" maxlength="50"/></p>
                       <p><span class="thSpan"><i>*</i>纳税人识别码：</span><@s.textfield   id="identifier"  cssClass="fpText" maxlength="20"/></p>
                        <p><span class="thSpan"><i>*</i>注册地址：</span><@s.textfield   id="re_address" cssClass="fpText" maxlength="50"/></p>
                        <p><span class="thSpan"><i>*</i>注册电话：</span><@s.textfield  id="re_phone"  cssClass="fpText" maxlength="20"/></p>
                        <p><span class="thSpan"></span>注册电话格式：010-1234567</p>
                        <p><span class="thSpan"><i>*</i>开户银行：</span><@s.textfield   id="bank_name" cssClass="fpText" maxlength="20"/></p>
                        <p><span class="thSpan"><i>*</i>银行账户：</span><@s.textfield   id="bank_id" cssClass="fpText" maxlength="30"/></p>
                        <p><span class="thSpan"><i>*</i>收票人姓名：</span><@s.textfield    id="ticket_name"  cssClass="fpText" maxlength="10"/></p>
                       <p><span class="thSpan"><i>*</i>收票人手机：</span><@s.textfield   id="ticket_cell" cssClass="fpText" maxlength="11"/></p>
                       <p><span class="thSpan "><i>*</i>收票人省份：</span><i id="v_areaDiv" style="margin-left:5px;"></i></p>
                       <p><span class="thSpan"><i>*</i>详细地址：</span><@s.textfield id="addresstail"  cssClass="fpText" maxlength="100"/></p> 
                <div class="fpBut" onclick="setZeng()">保存</div>
              </div>
              
           </div>
           
       </li>
     </ul>
   </div>
</div>

<!--购物车有内容-->
<div class="hasCart">
  <div class="orderh2 ordercont">确认订单信息</div>
  <!---->
    <#assign total=0>
        <#assign ship_total=0>
        <#assign shoptotal=0>
        <#assign shoptotal_norate=0>
        <#assign tax_rate_all = 0>
        <#assign goodstotal=0>
        <#assign active="0">
         <#assign om= 0>
        <#assign allnum=0>
        <#assign zy_count=0.0>
        <#assign bx_count=0.0>
        <#assign zy_num=0.0>
        <#assign bx_num=0.0>
        <#list shopList as shopMap>
    <#assign shop_cust_id = "${(shopMap.shop_cust_id)?if_exists}">
    <@s.hidden id="cust_id_str" name="cust_id_str" value="${(shopMap.shop_cust_id)?if_exists}"/>
   <@s.hidden id="goods_length_str" name="goods_length_str" value="${(shopMap.goods_length)?if_exists}"/>
    
	 <#list orderList as order>
	 	<#if (order.cust_id)?if_exists==shop_cust_id>
	 		<@s.hidden  name="goods_id_str" id="goods_id_str" value="${(order.goods_id)?if_exists}"/>
	 		<@s.hidden  name="order_num_str" id="order_num_str"  value="${(order.order_num)?if_exists}"/>
	 		<@s.hidden  name="goods_name_str" value="${(order.goods_name)?if_exists}"/>
	 		<@s.hidden  name="goods_img_str" value="${(order.goods_img)?if_exists}"/>
	 		<@s.hidden  name="spec_id_str" id="spec_id_str" value="${(order.spec_id)?if_exists}"/>
	 		<@s.hidden name="spec_name_str" value="${(order.spec_name)?if_exists}"/>
	 		<@s.hidden  name="sale_price_str" value="${(order.sale_price)?if_exists}"/>
	 		<@s.hidden  name="give_inter_str" value="${(order.give_inter)?if_exists}"/>
	 		<#assign num = 0>
	 		<#assign om =om+ 1>
	 		<#assign price = 0>
	 		<#assign sum = 0>
	 		<#assign price = "${(order.sale_price)?if_exists}">
	 		<#assign num = "${(order.order_num)?if_exists}">
	 		<#assign allnum="${num?number + allnum?number}">
	 		<#assign sum = "${price?number * num?number}">
	 		<#if order.sale_id?if_exists !=null && order.sale_id !="">
	 		<#assign sum=stack.findValue("@com.rbt.function.SalegoodsFuc@goSaleprice('${order.sale_id?if_exists}','${sum?if_exists}')")>
	 		</#if>	 		
	 		<#assign tax_rate = 0>	
	 		<#assign tax_rate ="${(order.tax_rate)?if_exists}" >	
	 		<#assign tax_rate = "${sum?number * tax_rate?number/100}">
	 		<#if shopMap.shop_cust_id=='1'>
           		<#assign zy_count="${zy_count?number+sum?number}">
		        <#assign zy_num="${zy_num?number+num?number}">
            <#else>
            	 <#assign bx_count="${bx_count?number+sum?number}">
            	 <#assign bx_num="${bx_num?number+num?number}">
            </#if>
				  <div class="hcDiv">
				    <table>
				      <tr>
				        <td><div class="imgDiv"><a href="/webapp/goodsdetail/${(order.goods_id)?if_exists}.html"><img src="${(order.goods_img)?if_exists}"></a></div></td>
				        <td> <#setting number_format="0.##">
        <#assign tax_rate_all = "${(tax_rate_all?number + tax_rate?number)?number}">
				           <p><a href="/webapp/goodsdetail/${(order.goods_id)?if_exists}.html">${(order.goods_name)?if_exists}</a></p>
				           <p class="pColor">${(order.spec_name)?if_exists}</p>
	                       <#if order.sale_name?if_exists != "">
	                       <#assign active="1">
	                       <p>促销活动：<span class="salesbcart">${order.sale_name?if_exists}</span></p>
	                       </#if>				           
				           <p>金额：<b>￥${sum?if_exists}</b><#if order.old_price!= null && order.sale_price?if_exists?number != order.old_price?if_exists?number>&nbsp;&nbsp;<span class="oldP">${order.old_price?if_exists}</span></#if></p>
				           <p>数量：${num}</p> 
				           <p>关税：${tax_rate}</p> 
                            <#if order.use_integral=='1'>
                               <@s.hidden id="inter_sub" name="inter_sub" value="0" cssClass="yfText inter_sub"/> 
			                <#else>
			                   <@s.hidden id="inter_sub" name="inter_sub" value="0" cssClass="yfText inter_sub"/> 
	                	   </#if>
				        </td>
				      </tr>
				    </table>
				  </div>
                    <@s.hidden id="totaltaxrate${(order.goods_id)?if_exists}" value="${tax_rate}"/>
                    <@s.hidden id="taxrate${(order.goods_id)?if_exists}" value="${(order.tax_rate)?if_exists}"/>				  
	                <@s.hidden id="subtotal${(order.goods_id)?if_exists}" value="${sum}"/>
	                <@s.hidden  name="subtotal_str" value="${sum?if_exists}"/>
	                <@s.hidden  id="shop_cust_id${(order.goods_id)?if_exists}" value="${shop_cust_id?if_exists}"/>     
				    <@s.hidden id="inter_sub" name="inter_sub" value="0" cssClass="yfText inter_sub"/> 
		
		       <#assign shoptotal_norate = "${((shoptotal_norate?number + sum?number)*discount)}">
               <#assign shoptotal = "${((shoptotal?number + sum?number))}">
               <#if saleorder != null && saleorder.term_state?if_exists == "3" && saleorder.term?trim?index_of('${order.goods_id?if_exists}') gt -1>
                         <#assign goodstotal='${goodstotal?number + sum?number }'>
                </#if>
	 	</#if>
	 	</#list>
	 	</#list>
                       <#if integral_state?if_exists == '0'>
                       <#assign tax_rate_all = "${(tax_rate_all?number) *discount}">
                       <#assign gshoptotal = "${shoptotal?if_exists?number *discount}">
                       <#assign shoptotal_norate="${(shoptotal?number)*discount}">
                       <#else>
                       <#assign tax_rate_all = "${(tax_rate_all?number)}">
                       <#assign gshoptotal = "${shoptotal?if_exists?number}">
                       <#assign shoptotal_norate="${(shoptotal?number)}">
                        </#if>
                        <#if tax_rate_all?number gt 0>
                       			<#assign gshoptotal = "${((gshoptotal?number+tax_rate_all?number))}">
                       <#else>
              					 <#assign gshoptotal = "${((gshoptotal?number))}">
                       </#if>   
                       <@s.hidden id="shoptotal_norate" value="${shoptotal_norate?if_exists}"/>
                       <#if tax_rate_all?number gt 0>
                       			<#assign shoptotal = "${((shoptotal_norate?number+tax_rate_all?number))}">
                       <#else>
              					 <#assign shoptotal = "${((shoptotal_norate?number))}">
                       </#if>
  <!---->
      
        <@s.hidden cssClass="goods_amount_str" name="goods_amount_str" value="${gshoptotal?if_exists}"/>         
        <#assign shoptotal = "${shoptotal?number}">
     	<@s.hidden id="shop_total_amount_str" name="shop_total_amount_str" value="${shoptotal?if_exists}"/>   
     	<@s.hidden id="smode_id_str" name="smode_id_str" value="0"/>    
     	<@s.hidden id="ship_price" name="ship_free_str" value="0"/> 
     	 <input type="hidden" name="end_area_attr" id="end_area_attr" value="7"/>
     	 <@s.hidden id="cfg_sc_exchscale" name="cfg_sc_exchscale" /> 
 		<@s.hidden id="use_paynum" name="use_paynum" value="${(memberfund.use_num)?if_exists}" /> 
 		<@s.hidden id="use_num" name="use_num" value="${(memberfund.use_num)?if_exists}" />
     	<@s.hidden id="is_use_inter" name="is_use_inter"value="0" />
		<@s.hidden id="allinter" name="allinter" value="${allinter?if_exists}"/> 
  
</div>


  <#if integral_state == "0">     
<!--支付,配送方式及发票信息-->
<div class="youhui">
   <div class="yhDiv">
     <ul>
	   <#assign list_index=0>
	   <#if comsumerList?size gt 0>
       <li><h3 class="fph3">使用优惠劵</h3> 
           <div class="fpDiv">
             <#list comsumerList as comsumer>
             <#assign list_index= list_index +1>
             <p><input type="checkbox" name="coupon" <#if comsumer.use?if_exists !="1" || active?if_exists !="0">disabled="true"</#if> onclick="useCoupon('${list_index?if_exists}','${comsumer.comsumer_id?if_exists}','${comsumer.coupon_id?if_exists}','${comsumer.goods_id?if_exists}','1','${comsumer.coupon_money?if_exists}');" value="${list_index}">
             </td><td width="10%"><img src="/malltemplate/jiutong/images/youhuiquanimg.png" /><span>${comsumer.coupon_name?if_exists}</span></td><td width="86%" class="colortd">
              <#if comsumer.need_state?if_exists=="1"><#assign goods_name=stack.findValue("@com.rbt.function.GoodsFuc@getGoodsName('${comsumer.term?if_exists}')")>
              指定商品：${goods_name?if_exists}<#elseif comsumer.need_state?if_exists=="2"><#assign cat_name=stack.findValue("@com.rbt.function.CategoryFuc@getCateNameByMap('${comsumer.term?if_exists}')")>
              指定分类：${cat_name?if_exists}<#elseif comsumer.need_state?if_exists=="3">
              全品类</#if>(有效期至：${comsumer.end_time?if_exists})</p> 
             </#list> 
           </div> 
       </li>
       </#if>
       <#if redsumerList?size gt 0>
       <li><h3 class="fph3">使用红包</h3> 
           <div class="fpDiv">
             <#list redsumerList as redsumer>
             <#assign list_index= list_index +1>
             <p><input type="checkbox" name="coupon"  <#if active?if_exists !="0">disabled="true"</#if> 
             onclick="useCoupon('${list_index?if_exists}','${redsumer.redsumer_id?if_exists}','${redsumer.red_id?if_exists}','','2','${redsumer.money?if_exists}');" 
             value="${list_index?if_exists}"></td><td width="10%"><img src="/malltemplate/jiutong/images/hongbaoimg.png" /><span>${redsumer.money?if_exists}元</span>
             </td>
             <td width="86%" class="colortd">${redsumer.red_name?if_exists}(有效期至：${redsumer.end_time?if_exists})</p>
             </#list>  
           </div> 
       </li>
       </#if>       
       <li><h3 class="fph3">使用余额</h3> 
           <div class="fpDiv">
              <p>
                 <input type="checkbox" onclick="isUseNumPay(this)"  id="paytype_id" name="paytype_id" <#if (memberfund.use_num)?if_exists =='0.00' >disabled="true"</#if>>
                 账户当前余额：<span><#if (memberfund.use_num)?if_exists!='0'>${(memberfund.use_num)?if_exists}<#else>0</#if></span>元
              </p>  
              <p>支付密码：<input type="password" id="pay_password" name="pay_password" class="fpText">
              <@s.fielderror><@s.param>pay_password</@s.param></@s.fielderror>
              <a href="/bmall_Memberfund_goPass.action?parentMenuId=6644344633&selli=2251641346">忘记支付密码？</a>
              </p>
           </div> 
       </li>
     </ul>
   </div>
</div>
 </#if>
      
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
		<@s.hidden id="hasfree" name="hasfree" /> 
		<@s.hidden  name="orderinvoice.invoice_id" />
		<@s.hidden  name="orderinvoice.invoice_type"id="invoice_type" value="0" />
		<@s.hidden  name="orderinvoice.p_content"id="p_content" value="${p_content}" />
		<@s.hidden  name="orderinvoice.z_content"id="z_content" value="${z_content}"/>
		<@s.hidden id="now_order_allfund" name="now_order_allfund" value="${total?if_exists}"/>
		<@s.hidden id="cfg_maxpay" name="cfg_maxpay"/>
		<@s.hidden id="zy_count" value="${zy_count}"/>
		<@s.hidden id="bx_count" value="${bx_count}"/>
		<@s.hidden id="zy_num" value="${zy_num}"/>
		<@s.hidden id="bx_num" value="${bx_num}"/>
		<@s.hidden id="integral_state" name="integral_state" value="${integral_state?if_exists}"/>
<!--支付-->
<div class="payInfor">
   <p>数量：共 ${allnum} 件</p>
   <p>商品金额：<span id="goods_shop">${shoptotal_norate}</span> 元
        <#if discount!=1 && integral_state?if_exists == '0'>
        <span>
          (${discount*100}折)
        </span>
        </#if>
    </p>
   <p class="ship_fee1">运费：
            <#assign is_ship_free="0">
            <#assign bao_ship="0">
            <#assign needs_ship_3="0">
            <#if saleorder != null && saleorder.coupon_state == '3'  && integral_state?if_exists == '0'>
                    <#if (saleorder.term_state == '1' || saleorder.term_state == '2')  && shoptotal?if_exists?number gte saleorder.need_money?if_exists?number>
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
   <p id="duty">
    关税：
     <#if tax_rate_all?number gt 0><font color="red">${tax_rate_all}</font>元<#else> 0 元 </#if>
    </p>
    <#if integral_state?if_exists == "0"> 
   <p>优惠券/红包：<span id="couponMoney">0</span>元</p>
   <p>订单优惠：<span id="oderMoney">${order_money?if_exists}</span>元</p>
    <#if order_names?if_exists != "">
    <p>订单促销活动：<font color="red">${order_names?if_exists}</font></p>
    </#if>
    <p>应付款金额：<b class="orderprice" id="orderprice">${shoptotal}</b>元</p>
    <p>
         余额支付：<span id="usepayprice">0</span>元
    </p>
    <p>
         剩余应付总额：<b><span id="overprice">${shoptotal}</span></b>元
    </p>
    <#else>
 <p>您当前积分：<span>${allinter}</span></p> 
   <p class="" >
    应付总积分：<span id="overprice">${shoptotal}</span></p>
 </#if> 
</div>
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
         <@s.hidden id="ship_goodstotal_id" value="${goodstotal?if_exists}"/>
       <@s.hidden id="needs_ship_3" value="${needs_ship_3?if_exists}"/>
       <@s.hidden id="sub_total_negative" value="0"/>
        <@s.hidden id="order_sign_str" name="order_sign_str" value="${order_sign_str?if_exists}"/>
       
<!--尾部-->
<#include "/a/webapp/mbFooter.html">

<!--结算浮动-->
<div class="settlement">
  <div class="settlCont">
     <span class="setSPrice">  
     总计（含运费）：<b class="orderprice priceb cartprice" id="alltotal"> ${total}</b>
         <#if integral_state =='0'>
           元
        <#else>
           积分 
        </#if>

    </span>
        <#if integral_state?if_exists == '1'>
        <a href="javascript:void(0);" class="setA" id="jf_suborder"  onclick="submitIntegral();">提交订单 >></a>
        <#else>
        <a href="javascript:void(0);" class="setA"  id="pt_suborder" onclick="submitOrder();" >提交订单>></a> 
        </#if>
   </div>
</div>

</@s.form>




<!--增加地址-->
<div class="addAddress">

   <!---->
  <div class="addh2" onclick="addnewaddress();" >增加收货地址</div>
  <!---->
  <div class="addTable">
    <table cellpadding="0" cellspacing="0">
     <tr><th>收货人：</th><td><input type="text" class="addText"id="consignee" name="consignee" maxLength="10" ></td></tr>
     <tr><th>地区：</th><td><div id="areaDiv"></div> </td></tr>
     <tr><th>详细地址：</th><td><input type="text" class="addText" id="address"  name="address"  maxLength="50"></td></tr>
     <tr><th>手机号码：</th><td><input type="text" class="addText" id="cell_phone" name="cell_phone" onkeyup="checkDigital(this)" maxLength="11"></td></tr>
     <tr><th>联系电话：</th><td><input type="text" class="addText" id="phone" name="phone"  maxLength="12"></td></tr>
     <tr><th>身份证：</th><td><input type="text" class="addText" id="identitycard" name="identitycard"  maxLength="18"></td></tr>
    </table>
  </div>   
  <!---->
  <div class="addBut" onclick="addAddr()">确定</div>
  
</div>
<div class="addrClose"><img src="/malltemplate/jiutong/images/filClose.gif"></div>



</body>

<script src="/wro/webapp_common.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/order.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/shopProcess.js" type="text/javascript"></script>
<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
<script type="text/javascript" src="/include/common/js/v-get-cat-area.js"></script> 
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
</html>
