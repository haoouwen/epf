	<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}—购物车</title>

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
<@s.form id="goOrder" action="/webapp/order!goOrder.action" method="post">
<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>购物车(<font id='cart_id_num'>0</font>)<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<#if (shopList==null||shopList?size lt 1 ) &&(baoshopList == null || baoshopList?size lt 1)>
<!--购物车为空 直邮和保税都为0 显示-->
<div class="emptyCart">
   <p><img src="/malltemplate/jiutong/images/cart.gif"></p>
   <p>您的购物车没有东西，快去购物吧<br/>或者登录查看您的购物车</p>
   <p class="emptyP">
     <a href="/mindex.html" style="width:100%;"><i>去逛逛</i></a>
     <br class="clear"/>
   </p>
</div>
</#if>
<div id="cartDiv">
<!--购物车有内容-->
<div class="hasCart" id="noEmptyCart">
  <!----> 
  <#list shopList as shop>
  <input class="selcust" type="hidden" value="${(shop.shop_cust_id)?if_exists}"/>
		  		<input id="shopname${(shop.shop_cust_id)?if_exists}" type="hidden" value="${(shop.shop_name)?if_exists}"/>
		  		<input id="shopqq${(shop.shop_cust_id)?if_exists}" type="hidden" value="${(shop.shop_qq)?if_exists}"/>
		  		<input id="radomno${(shop.shop_cust_id)?if_exists}" type="hidden" value="${(shop.radom_no)?if_exists}"/>
   <#assign count = 0>	
			  	<#list cartgoodsList as cartgoods>	
			  	   	<#assign is_up=stack.findValue("@com.rbt.function.GoodsFuc@is_up('${cartgoods.goods_id}')")>
			  		<#assign stock=stack.findValue("@com.rbt.function.StockFuc@goods('${cartgoods.goods_id}','${cartgoods.spec_id}')")>
			  		<#assign limit_num=stack.findValue("@com.rbt.function.StockFuc@limits('${cartgoods.goods_id}')")>
			  	    <#assign sum="${(cartgoods.sale_price)?if_exists?number * (cartgoods.buy_num)?if_exists}">
			  	    <#if cartgoods.sale_id?if_exists != null && cartgoods.sale_id !="">  
					 <#assign sum=stack.findValue("@com.rbt.function.SalegoodsFuc@goSaleprice('${cartgoods.sale_id?if_exists}','${(cartgoods.sale_price)?if_exists?number * (cartgoods.buy_num)?if_exists}')")>
					 </#if>			  		
			  		<#if (shop.shop_cust_id)?if_exists == (cartgoods.shop_cust_id)?if_exists>
			  		<#assign count = count + 1>	
  <div class="hcDiv">
    <table >
      <tr  id="item${(cartgoods.trade_id)?if_exists}" class="goodsid${(cartgoods.trade_id)?if_exists}">
        <td>
         <#if is_up=="0">
           <input type="checkbox" class="goods" id="cart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists}" value="${(cartgoods.shop_cust_id)?if_exists},${(cartgoods.goods_id)?if_exists},${(cartgoods.trade_id)?if_exists},${(cartgoods.cookie_id)?if_exists},${(cartgoods.buy_num)?if_exists}"/>
        </#if>
        <input class="selgoods_id" type="hidden" value="${(cartgoods.goods_id)?if_exists}_"/>
        </td>
        <td>
        <div class="imgDiv">
                    		<a href="/webapp/goodsdetail/${(cartgoods.goods_id)?if_exists}.html" target="_blank">
                    			<img width="55px" height="55px" src="${(cartgoods.img_path)?if_exists}" width="60" height="60"/>
                    		</a>
							<input id="goods_img${(cartgoods.goods_id)?if_exists}" type="hidden" value="${(cartgoods.img_path)?if_exists}"/>
							<#if is_up=="1">(已下架)</#if>
        </div>
        </td>
        <td>
           <p>
         	  <a href="/webapp/goodsdetail/${(cartgoods.goods_id)?if_exists}.html" target="_blank">${(cartgoods.goods_name)?if_exists}</a>
		   	  <input id="goods_name${(cartgoods.goods_id)?if_exists}" type="hidden" value="${(cartgoods.goods_name)?if_exists}"/>
		   	  <input id="goods_cat${(cartgoods.goods_id)?if_exists}" type="hidden" value="${(cartgoods.goods_cat)?if_exists}"/>
           </p>
           <p class="pColor"> ${(cartgoods.spec_name)?if_exists}</p>
			<input id="spec_id${(cartgoods.trade_id)?if_exists}" type="hidden" value="${(cartgoods.spec_id)?if_exists}"/>
			<input id="spec_name${(cartgoods.trade_id)?if_exists}" type="hidden" value="${(cartgoods.spec_name)?if_exists}"/>
			<input id="use_integral${(cartgoods.goods_id)?if_exists}" type="hidden" value="${(cartgoods.use_integral)?if_exists}"/>
           <#if cartgoods.sale_name?if_exists != null>  
	           <@s.hidden id="sale_id${(cartgoods.trade_id)?if_exists}" value="${cartgoods.sale_id?if_exists}"/>
	           <p>促销活动：<span class="salesbcart">${cartgoods.sale_name?if_exists}</span></p>
           <#else>
            	<@s.hidden id="sale_id${(cartgoods.trade_id)?if_exists}" value="0"/>
          </#if>
         <#if cartgoods.tax_rate != null && cartgoods.tax_rate != "0">
         <p class="slP">适用税率<b id="taxrate${(cartgoods.trade_id)?if_exists}">${cartgoods.tax_rate?if_exists}</b>%&nbsp;<span class="tax" style="display:none;">关税：￥<b id="tax_rate${(cartgoods.trade_id)?if_exists}"> <#if cartgoods.tax_rate != null && cartgoods.tax_rate != "0">${(sum)?if_exists?number * (cartgoods.tax_rate)?if_exists?number / 100}<#else>0</#if></b></span>
         </p>
         </#if>          
           <p><span class="hcNum">数量：</span>
              <#if is_up=="0">
              <span class="hcSpan">
                  <span class="recNumBut"  onclick="SubGoodsNum('cart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?if_exists?replace(':','_')}',${(cartgoods.trade_id)?if_exists?number},'${cartgoods.goods_id}','${(cartgoods.cookie_id)?if_exists}');" ></span>
                    <input  id="gnumber_cart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?replace(":",'_')}" type="text" class="numText" onkeyup="checkNums(this);addNums('cart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?if_exists?replace(':','_')}',${(cartgoods.trade_id)?if_exists?number},'${stock}','${cartgoods.goods_id}','${limit_num}','${(cartgoods.cookie_id)?if_exists}');"  value="${(cartgoods.buy_num)?if_exists}">
					<input id="gnumber${(cartgoods.goods_id)?if_exists}_${(cartgoods.cookie_id)?if_exists?replace(':','_')}" class="selgoods_sale_price" type="hidden" value="${(cartgoods.buy_num)?if_exists}"/>
                   <span class="addNumBut" onclick="AddGoodsNums('cart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?if_exists?replace(':','_')}',${(cartgoods.trade_id)?if_exists?number},'${stock}','${cartgoods.goods_id}','${limit_num}','${(cartgoods.cookie_id)?if_exists}');"></span>
               </span>
               <#else>
                 <span class="hcNum"> 0</span>
               </#if>
               <br class="clear"/>
           </p>
           <p>金额：<b>￥</b><b>${(cartgoods.sale_price)?if_exists}</b><#if cartgoods.old_price!= null && cartgoods.sale_price?if_exists?number != cartgoods.old_price?if_exists?number>&nbsp;&nbsp;<span class="oldP">${cartgoods.old_price?if_exists}</span></#if></p>
           <input id="price_cart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?if_exists?replace(':','_')}" class="selgoods_sale_price" type="hidden" value="${(cartgoods.sale_price)?if_exists}"/>
        	<input id="price${(cartgoods.trade_id)?if_exists}" type="hidden" value="${(cartgoods.sale_price)?if_exists}"/>
        	<input id="integral${(cartgoods.trade_id)?if_exists}" class="selgoods_integral" type="hidden" value="${(cartgoods.integral)?if_exists}"/>
           <p>小计：
           <b>￥</b>
           <b id="gprice_cart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?replace(':','_')}">
          		 ${sum?if_exists}
           </b>
           </p>
        </td>
      </tr>
       
    </table>
  </div>
  	</#if>
</#list>	
 </#list>
</div>

<#if shopList != null && shopList?size gt 0>
<!--支付-->
<div class="payInfor">
   <p>数量：共 <span id="allnum">0</span> 件</p>
   <p>商品金额：<b id="total_goodsprice">0</b> 元</p>
   <p>关税：<b id="total_tax">0</b>元</p>
   <p>合计：<b>￥</b><b id="total">0</b>（不含运费）</span></p>
   <p><input type="checkbox" class="checkBox" id="cart"><span>全选</span><span class="clearBut"  onclick="removerAllCookies()">删除</span></p>
   <p><a href="javascript:void(0);" class="zysetA" onclick="cartSubmit('1');" id="cart_count">直邮结算</a></p>
</div>
</#if>

<div id="baoCartDiv">
  <!----> 
  <#list baoshopList as shop>
  <input class="selcust" type="hidden" value="${(shop.shop_cust_id)?if_exists}"/>
		  		<input id="shopname${(shop.shop_cust_id)?if_exists}" type="hidden" value="${(shop.shop_name)?if_exists}"/>
		  		<input id="shopqq${(shop.shop_cust_id)?if_exists}" type="hidden" value="${(shop.shop_qq)?if_exists}"/>
		  		<input id="radomno${(shop.shop_cust_id)?if_exists}" type="hidden" value="${(shop.radom_no)?if_exists}"/>
   <#assign count = 0>	
			  	<#list baocartgoodsList as cartgoods>	
			  	   	<#assign is_up=stack.findValue("@com.rbt.function.GoodsFuc@is_up('${cartgoods.goods_id}')")>
			  		<#assign stock=stack.findValue("@com.rbt.function.StockFuc@goods('${cartgoods.goods_id}','${cartgoods.spec_id}')")>
			  		<#assign limit_num=stack.findValue("@com.rbt.function.StockFuc@limits('${cartgoods.goods_id}')")>
			  	    <#assign sum="${(cartgoods.sale_price)?if_exists?number * (cartgoods.buy_num)?if_exists}">
			  	    <#if cartgoods.sale_id?if_exists != null && cartgoods.sale_id !="">  
					 <#assign sum=stack.findValue("@com.rbt.function.SalegoodsFuc@goSaleprice('${cartgoods.sale_id?if_exists}','${(cartgoods.sale_price)?if_exists?number * (cartgoods.buy_num)?if_exists}')")>
					 </#if>			  		
			  		<#if (shop.shop_cust_id)?if_exists == (cartgoods.shop_cust_id)?if_exists>
			  		<#assign count = count + 1>	
  <div class="hcDiv">
    <table >
      <tr  id="item${(cartgoods.trade_id)?if_exists}" class="goodsid${(cartgoods.trade_id)?if_exists}">
        <td>
         <#if is_up=="0">
           <input type="checkbox" class="baoGoods" id="baoCart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists}" value="${(cartgoods.shop_cust_id)?if_exists},${(cartgoods.goods_id)?if_exists},${(cartgoods.trade_id)?if_exists},${(cartgoods.cookie_id)?if_exists},${(cartgoods.buy_num)?if_exists}"/>
        </#if>
        <input class="selgoods_id" type="hidden" value="${(cartgoods.goods_id)?if_exists}"/>
        </td>
        <td>
        <div class="imgDiv">
                    		<a href="/webapp/goodsdetail/${(cartgoods.goods_id)?if_exists}.html" target="_blank">
                    			<img width="55px" height="55px" src="${(cartgoods.img_path)?if_exists}" width="60" height="60"/>
                    		</a>
							<input id="goods_img${(cartgoods.goods_id)?if_exists}" type="hidden" value="${(cartgoods.img_path)?if_exists}"/>
							<#if is_up=="1">(已下架)</#if>
        </div>
        </td>
        <td>
           <p>
         	  <a href="/webapp/goodsdetail/${(cartgoods.goods_id)?if_exists}.html" target="_blank">${(cartgoods.goods_name)?if_exists}</a>
		   	  <input id="goods_name${(cartgoods.goods_id)?if_exists}" type="hidden" value="${(cartgoods.goods_name)?if_exists}"/>
		   	  <input id="goods_cat${(cartgoods.goods_id)?if_exists}" type="hidden" value="${(cartgoods.goods_cat)?if_exists}"/>
           </p>
           <p class="pColor"> ${(cartgoods.spec_name)?if_exists}</p>
			<input id="spec_id${(cartgoods.trade_id)?if_exists}" type="hidden" value="${(cartgoods.spec_id)?if_exists}"/>
			<input id="spec_name${(cartgoods.trade_id)?if_exists}" type="hidden" value="${(cartgoods.spec_name)?if_exists}"/>
			<input id="use_integral${(cartgoods.goods_id)?if_exists}" type="hidden" value="${(cartgoods.use_integral)?if_exists}"/>
           <#if cartgoods.sale_name?if_exists != null>  
           <@s.hidden id="sale_id${(cartgoods.trade_id)?if_exists}" value="${cartgoods.sale_id?if_exists}"/>
           <p>促销活动：<span class="salesbcart">${cartgoods.sale_name?if_exists}</span></p>
           <#else>
           <@s.hidden id="sale_id${(cartgoods.trade_id)?if_exists}" value="0"/>
          </#if>
         <#if cartgoods.tax_rate != null && cartgoods.tax_rate != "0">
         <p class="slP">适用税率<b id="taxrate${(cartgoods.trade_id)?if_exists}">${cartgoods.tax_rate?if_exists}</b>%&nbsp;<span class="tax" style="display:none;">关税：￥<b id="tax_rate${(cartgoods.trade_id)?if_exists}"> <#if cartgoods.tax_rate != null && cartgoods.tax_rate != "0">${(sum)?if_exists?number * (cartgoods.tax_rate)?if_exists?number / 100}<#else>0</#if></b></span>
         </p>
         </#if>          
           <p><span class="hcNum">数量：</span>
              <#if is_up=="0">
              <span class="hcSpan">
                  <span class="recNumBut"  onclick="SubBaoGoodsNum('baoCart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?if_exists?replace(':','_')}',${(cartgoods.trade_id)?if_exists?number},'${cartgoods.goods_id}','${(cartgoods.cookie_id)?if_exists}');" ></span>
                    <input  id="gnumber_baoCart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?replace(":",'_')}" type="text" class="numText" onkeyup="checkNums(this);addBaoNums('baoCart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?if_exists?replace(':','_')}',${(cartgoods.trade_id)?if_exists?number},'${stock}','${cartgoods.goods_id}','${limit_num}','${(cartgoods.cookie_id)?if_exists}');"  value="${(cartgoods.buy_num)?if_exists}">
					<input id="gnumber${(cartgoods.goods_id)?if_exists}_${(cartgoods.cookie_id)?if_exists?replace(':','_')}" class="selgoods_sale_price" type="hidden" value="${(cartgoods.buy_num)?if_exists}"/>
                   <span class="addNumBut" onclick="AddBaoGoodsNums('baoCart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?if_exists?replace(':','_')}',${(cartgoods.trade_id)?if_exists?number},'${stock}','${cartgoods.goods_id}','${limit_num}','${(cartgoods.cookie_id)?if_exists}');"></span>
               </span>
               <#else>
                 <span class="hcNum"> 0</span>
               </#if>
               <br class="clear"/>
           </p>
           <p>金额：<b>￥</b><b>${(cartgoods.sale_price)?if_exists}</b><#if cartgoods.old_price!= null && cartgoods.sale_price?if_exists?number != cartgoods.old_price?if_exists?number>&nbsp;&nbsp;<span class="oldP">${cartgoods.old_price?if_exists}</span></#if></p>
           <input id="price_baoCart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?if_exists?replace(':','_')}" class="selgoods_sale_price" type="hidden" value="${(cartgoods.sale_price)?if_exists}"/>
        	<input id="price${(cartgoods.trade_id)?if_exists}" type="hidden" value="${(cartgoods.sale_price)?if_exists}"/>
        	<input id="integral${(cartgoods.trade_id)?if_exists}" class="selgoods_integral" type="hidden" value="${(cartgoods.integral)?if_exists}"/>
           <p>小计：
           <b>￥</b>
           <b id="gprice_baoCart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?replace(':','_')}">
          		 ${sum?if_exists}
           </b>
           </p>
        </td>
      </tr>
       
    </table>
  </div>
  	</#if>
</#list>	
 </#list>
</div>

<#if baoshopList != null && baoshopList?size gt 0>
<!--支付-->
<div class="payInfor">
   <p>数量：共 <span id="baoAllnum">0</span> 件</p>
   <p>商品金额：<b id="baototal_goodsprice">0</b> 元</p>
   <p>关税：<b id="baototal_tax">0</b>元</p>
   <p>合计：<b>￥</b><b id="baototal">0</b>（不含运费）</span></p>
   <p><input type="checkbox" class="checkBox" id="baoCart"><span>全选</span><span class="clearBut"  onclick="removerBaoAllCookies()">删除</span></p>
   <p><a href="javascript:void(0);" class="zysetA" onclick="baoCartSubmit();" id="cart_count">保税结算</a></p>
</div>
</#if>
</div>
<!--尾部-->

</div>
 <!--订单提交隐藏域开始(商品相关)-->
				<@s.hidden id="goods_id_str" name="goods_id_str"/>
				<@s.hidden id="goods_name_str" name="goods_name_str"/>
				<@s.hidden id="spec_name_str" name="spec_name_str" />
				<@s.hidden id="goods_cat_str" name="goods_cat_str"/>
				<@s.hidden id="spec_id_str" name="spec_id_str" />
				<@s.hidden id="goods_img_str" name="goods_img_str" />
				<@s.hidden id="sale_price_str" name="sale_price_str"/>
				<@s.hidden id="give_inter_str" name="give_inter_str"/>
				<@s.hidden id="trade_id_str" name="trade_id_str"/>
				<@s.hidden id="cookie_id_str" name="cookie_id_str"/>
				<@s.hidden id="isBuySelf" name="isBuySelf"/>
				<@s.hidden id="order_num_str" name="order_num_str"/>
				<@s.hidden id="use_integral_str" name="use_integral_str"/>
				
<!--订单提交隐藏域结束(商品相关)-->
   <!--订单提交隐藏域开始(店铺相关)-->
		   		<@s.hidden id="cust_id_str" name="cust_id_str"/>
				<@s.hidden id="shop_name_str" name="shop_name_str"/>
				<@s.hidden id="shop_qq_str" name="shop_qq_str"/>
				<@s.hidden id="goods_length_str" name="goods_length_str"/>
				<@s.hidden id="radom_no_str" name="radom_no_str"/>
<!--订单提交隐藏域结束(店铺相关)-->
                <@s.hidden id="count"/>
</@s.form>
<#include "/a/webapp/mbFooter.html">

<#include "/a/webapp/mbCommon.html">
</body>

<script src="/wro/webapp_common.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/baocart.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/shopProcess.js" type="text/javascript"></script>
<script type="text/javascript">
	//初始化加载
	$(document).ready(function(){
	  goCartupdate();
		var isBuySelf=$("#isBuySelf").val();
		if(isBuySelf=='0'){
		  	jAlert("商家不能购买自己的商品!","系统提示");
		}
		isEmptyCart();
	});
</script> 

</html>
