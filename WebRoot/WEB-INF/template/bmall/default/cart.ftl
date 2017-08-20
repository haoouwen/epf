<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>购物车-${cfg_webname?if_exists}</title>
<#include "/WEB-INF/template/bmall/default/jscsstop.ftl">
<script src="/malltemplate/bmall/js/json2.js" type="text/javascript"></script>
<link href="/malltemplate/bmall/css/payProcess.css" rel="stylesheet" type="text/css">
<script src="/malltemplate/bmall/js/cart.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/baocart.js" type="text/javascript"></script>
<script type="text/javascript" src="/include/common/js/jquery.jNotify.js"></script>
<script type="text/javascript" src="/include/common/js/jquery.alert.js"></script>
<link href="/include/common/css/alert.css" rel="stylesheet" type="text/css" />
<link href="/malltemplate/index/css/mall_public.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	//初始化加载
	$(document).ready(function(){
		var isBuySelf=$("#isBuySelf").val();
		if(isBuySelf=='0'){
		  	jAlert("商家不能购买自己的商品!","系统提示");
		}
		isEmptyCart();
	});
</script> 
</head>

<body>
<@s.form id="goOrder" action="/mall/order!goOrder.action" method="post">
<!--顶部-->
<#include "/WEB-INF/template/bmall/default/topest.ftl">
<!--logo，搜索-->
<div class="logoSearDiv">
   <!--logo-->
   <div class="logo"><a href="/"><img src="${cfg_logourl}"/></a></div>
   <!--支付步骤-->
   <div class="cartProcess"></div>
</div>

<!--内容-->
<div class="emptyCart">
   <p>您的购物车暂时还没有商品</p>
   <p>&nbsp;&nbsp;&nbsp;&nbsp;赶紧行动：去<a href="/">首页</a>挑选您喜欢的商品吧！</p>
</div>
<!--内容-->
<div class="cart" id="noEmptyCart">
   	<h2> <!--
        <span class="f_left">
            <a href="/login.html?loc=/cart.html">
            </a>
            为了可以永久保存您购物车中的商品，请您先
        </span>
       -->
    </h2>
    <!--直邮购物车-->
    <#if shopList != null && shopList?size gt 0>
    <div class="cartDiv" id="cartDiv">
        <!---->
        <div class="carth3" >
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <th width="7%"  id="cart_p">
                        <input type="checkbox"  id="cart"/>
                        &nbsp;全选
                    </th>
                    <th width="30%">
                        商品信息
                    </th>
                    <th width="13%">
                        规格/型号	
                    </th>
                    <th width="10%">
                        单价(元)
                    </th>
                    <th width="10%">
                        数量
                    </th>
                    <th width="10%">
                        小计(元)
                    </th>
                    <th width="10%">
                        操作
                    </th>
                </tr>
            </table>
        </div>
      <!---->
     <#list shopList as shop>
        <div class="cartTab">
            <table width="100%" cellpadding="0" cellspacing="0"id="carttable${(shop.shop_cust_id)?if_exists}">
       			<input class="selcust" type="hidden" value="${(shop.shop_cust_id)?if_exists}"/>
		  		<input id="shopname${(shop.shop_cust_id)?if_exists}" type="hidden" value="${(shop.shop_name)?if_exists}"/>
		  		<input id="shopqq${(shop.shop_cust_id)?if_exists}" type="hidden" value="${(shop.shop_qq)?if_exists}"/>
		  		<input id="radomno${(shop.shop_cust_id)?if_exists}" type="hidden" value="${(shop.radom_no)?if_exists}"/>
                <tr><th colspan="9">仓库：<span>直邮仓库</span></th></tr>
                <#assign count = 0>	
			  	<#list cartgoodsList as cartgoods>	
			  		<#assign stock=stack.findValue("@com.rbt.function.StockFuc@goods('${cartgoods.goods_id}','${cartgoods.spec_id}')")>
			  		<#assign limit_num=stack.findValue("@com.rbt.function.StockFuc@limits('${cartgoods.goods_id}')")>
			  	    <#assign sum="${(cartgoods.sale_price)?if_exists?number * (cartgoods.buy_num)?if_exists}">
			  	    <#if cartgoods.sale_id?if_exists != null && cartgoods.sale_id !="">  
					 <#assign sum=stack.findValue("@com.rbt.function.SalegoodsFuc@goSaleprice('${cartgoods.sale_id?if_exists}','${(cartgoods.sale_price)?if_exists?number * (cartgoods.buy_num)?if_exists}')")>
					 </#if>
			  		<#if (shop.shop_cust_id)?if_exists == (cartgoods.shop_cust_id)?if_exists>
			  		<#assign count = count + 1>	
			  	<tr id="item${(cartgoods.trade_id)?if_exists}" class="goodsid${(cartgoods.trade_id)?if_exists}">
                    <td width="5%">
                        <input type="checkbox" class="goods" id="cart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists}" value="${(cartgoods.shop_cust_id)?if_exists},${(cartgoods.goods_id)?if_exists},${(cartgoods.trade_id)?if_exists},${(cartgoods.cookie_id)?if_exists},${(cartgoods.buy_num)?if_exists}"/>
                    	<input class="selgoods_id" type="hidden" value="${(cartgoods.goods_id)?if_exists}_"/>
                    </td>
                    <td width="32%" class="imgTd">
                        <div class="imgDiv">
                    		<a href="/mall-goodsdetail-${(cartgoods.goods_id)?if_exists}.html" target="_blank">
                    			<img width="55px" height="55px" src="${(cartgoods.img_path)?if_exists}" width="60" height="60"/>
                    		</a>
							<input id="goods_img${(cartgoods.goods_id)?if_exists}" type="hidden" value="${(cartgoods.img_path)?if_exists}"/>
							<input id="use_integral${(cartgoods.goods_id)?if_exists}" type="hidden" value="${(cartgoods.use_integral)?if_exists}"/>
                        </div>
                        <div class="textDiv">
                            <p>
                                <a href="/mall-goodsdetail-${(cartgoods.goods_id)?if_exists}.html" target="_blank">${(cartgoods.goods_name)?if_exists}</a>
								<input id="goods_name${(cartgoods.goods_id)?if_exists}" type="hidden" value="${(cartgoods.goods_name)?if_exists}"/>
								<input id="goods_cat${(cartgoods.goods_id)?if_exists}" type="hidden" value="${(cartgoods.goods_cat)?if_exists}"/>
                            </p>
	                          <#if cartgoods.sale_name?if_exists != null>  
	                          <@s.hidden id="sale_id${(cartgoods.trade_id)?if_exists}" value="${cartgoods.sale_id?if_exists}"/>
	                          <p><span>${cartgoods.sale_name?if_exists}</span></p>
	                          <#else>
	                          <@s.hidden id="sale_id${(cartgoods.trade_id)?if_exists}" value="0"/>
	                         </#if>
		                     <#if cartgoods.tax_rate != null && cartgoods.tax_rate != "0">
		                     <p class="slP">适用税率<b id="taxrate${(cartgoods.trade_id)?if_exists}">${cartgoods.tax_rate?if_exists}</b>%&nbsp;<span class="tax" style="display:none;">关税：￥<b id="tax_rate${(cartgoods.trade_id)?if_exists}"> <#if cartgoods.tax_rate != null && cartgoods.tax_rate != "0">${(sum)?if_exists?number * (cartgoods.tax_rate)?if_exists?number / 100}<#else>0</#if></b></span>
		                     <i>
			                      <b class="priceb">税费=不含税价格*件数*商品税率</b></br>
			                         根据海关规定，本商品适用税率为${cartgoods.tax_rate?if_exists}%，
			                         若订单总税额<=50元，海关与以免征。
		                      </i>
		                     </p>
		                     </#if>
                        </div>
                    </td>
                    <td width="13%" class="proTd">
                  				<p> ${(cartgoods.spec_name)?if_exists}</p>
								<input id="spec_id${(cartgoods.trade_id)?if_exists}" type="hidden" value="${(cartgoods.spec_id)?if_exists}"/>
								<input id="spec_name${(cartgoods.trade_id)?if_exists}" type="hidden" value="${(cartgoods.spec_name)?if_exists}"/>
                    </td>
                    <td width="10%">
                        <b>
                        	<#if cartgoods.old_price!= null &&  cartgoods.sale_price?if_exists?number != cartgoods.old_price?if_exists?number>
                        	<span class="oldP">${cartgoods.old_price?if_exists}</span></br>
                        	</#if>
                        	${(cartgoods.sale_price)?if_exists}
                        	<input id="price_cart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?if_exists?replace(':','_')}" class="selgoods_sale_price" type="hidden" value="${(cartgoods.sale_price)?if_exists}"/>
                        	<input id="price${(cartgoods.trade_id)?if_exists}" type="hidden" value="${(cartgoods.sale_price)?if_exists}"/>
                        </b>
                    </td>
								<input id="integral${(cartgoods.trade_id)?if_exists}" class="selgoods_integral" type="hidden" value="${(cartgoods.integral)?if_exists}"/>
                    <td width="10%">
                        <div class="numDiv">
                    		<a onclick="SubGoodsNum('cart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?if_exists?replace(':','_')}',${(cartgoods.trade_id)?if_exists?number},'${cartgoods.goods_id}','${(cartgoods.cookie_id)?if_exists}');" class="redBut"></a>
							
								<input  id="gnumber_cart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?replace(":",'_')}" type="text" class="numText" onkeyup="checkNums(this);addNums('cart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?if_exists?replace(':','_')}',${(cartgoods.trade_id)?if_exists?number},'${stock}','${cartgoods.goods_id}','${limit_num}','${(cartgoods.cookie_id)?if_exists}');"  value="${(cartgoods.buy_num)?if_exists}">
								
								<input id="gnumber${(cartgoods.goods_id)?if_exists}_${(cartgoods.cookie_id)?if_exists?replace(':','_')}" class="selgoods_sale_price" type="hidden" value="${(cartgoods.buy_num)?if_exists}"/>
						
						    	<a onclick="AddGoodsNums('cart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?if_exists?replace(':','_')}',${(cartgoods.trade_id)?if_exists?number},'${stock}','${cartgoods.goods_id}','${limit_num}','${(cartgoods.cookie_id)?if_exists}');" class="addBut"></a>
                          
                        </div>
                    </td>
                    <td width="10%">
                        <b class="priceb"id="gprice_cart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?replace(':','_')}">
							${sum?if_exists}
                        </b>
                    </td>
                    <td width="10%" class="aTd">
                        <p>
                            <a href="javascript:void(0);" onclick="insertColl('${(cartgoods.goods_name)?if_exists}','${(cartgoods.goods_id)}','${(cartgoods.sale_price)}');">
                                收藏
                            </a>
                        </p>
                        <p>
                            <a href="javascript:void(0);" onclick="removerCookies('${(cartgoods.trade_id)?if_exists}','${goodsid?if_exists}' )">
                                删除
                            </a>
                        </p>
                    </td>
                </tr>
               	</#if>
			  	</#list>	
			  		
            </table>
          </div>
        </#list>
        <div class="delDiv">
            <div class="f_left"><a href="javascript:void(0);" onclick="removerAllCookies()">删除选中物品</a></div>
            <div class="jsDiv f_right">
               <p>已选<b id="count">0</b>种商品 共<b id="allnum">0</b>件</p>   
               <p>商品总计：<span id="total_goodsprice">1500</span>元</p>         
               <p class="gup gup2" id="gup"><b>订单关税：<span id="total_tax">54.10</span>元</b>
                  <i>
                    订单关税超过50元！建议分开下单。<br/>
                    (海关免征限额为50元)<br/>
                    <b>您可以重新选择商品，把关税总控制在50元以下，
                     即可免税。</b>
                    <img src="/malltemplate/bmall/images/topClose.png" class="close">
                  </i>
               </p>
               <p class="zjp">
                  总计(不含运费)：<b class="priceb" id="total">0</b>元
               </p>
           </div>
           <div class="clear"></div>
        </div>
        <!---->
        <div class="settle">
           <div class="lSpan">
                <a href="/">
                </a>
               <#-- <b>
                    共送积分：
                </b>
               <span id="all_integral">0</span>-->
            </div>
            <div class="rSpan">

                <input type="hidden" name="loc" id="refloc" value=""/>
                <a  href="javascript:void(0);" id="zhi_tax" class="goSettle"  onclick="checkTax();">直邮结算</a>
                <a  href="javascript:void(0);" id="zhi_count" style="display:none;" class="goSettle"  onclick="cartSubmit();">继续结算</a>
            </div>
        </div>
    </div>
    </#if> 
    <!--保税购物车-->
    <#if baoshopList != null && baoshopList?size gt 0> 
    <div class="cartDiv" id="baoCartDiv">
        <!---->
        <div class="carth3" >
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <th width="7%"  id="baoCart_p">
                        <input type="checkbox"  id="baoCart"/>
                        &nbsp;全选
                    </th>
                    <th width="30%">
                        商品信息
                    </th>
                    <th width="13%">
                        规格/型号	
                    </th>
                    <th width="10%">
                        单价(元)
                    </th>
                    <th width="10%">
                        数量
                    </th>
                    <th width="10%">
                        小计(元)
                    </th>
                    <th width="10%">
                        操作
                    </th>
                </tr>
            </table>
        </div>
      <!--保税购物车-->
     <#list baoshopList as shop>
        <div class="cartTab">
            <table width="100%" cellpadding="0" cellspacing="0"id="carttable${(shop.shop_cust_id)?if_exists}">
       			<input class="selcust" type="hidden" value="${(shop.shop_cust_id)?if_exists}"/>
		  		<input id="shopname${(shop.shop_cust_id)?if_exists}" type="hidden" value="${(shop.shop_name)?if_exists}"/>
		  		<input id="shopqq${(shop.shop_cust_id)?if_exists}" type="hidden" value="${(shop.shop_qq)?if_exists}"/>
		  		<input id="radomno${(shop.shop_cust_id)?if_exists}" type="hidden" value="${(shop.radom_no)?if_exists}"/>
                <tr><th colspan="9">仓库：<span>保税区仓库 </span></th></tr>
                <#assign count = 0>	
			  	<#list baocartgoodsList as cartgoods>	
			  		<#assign stock=stack.findValue("@com.rbt.function.StockFuc@goods('${cartgoods.goods_id}','${cartgoods.spec_id}')")>
			  		<#assign limit_num=stack.findValue("@com.rbt.function.StockFuc@limits('${cartgoods.goods_id}')")>
			  	    <#assign sum="${(cartgoods.sale_price)?if_exists?number * (cartgoods.buy_num)?if_exists}">
			  	    <#if cartgoods.sale_id?if_exists != null && cartgoods.sale_id !="">  
					 <#assign sum=stack.findValue("@com.rbt.function.SalegoodsFuc@goSaleprice('${cartgoods.sale_id?if_exists}','${(cartgoods.sale_price)?if_exists?number * (cartgoods.buy_num)?if_exists}')")>
					 </#if>
			  		<#if (shop.shop_cust_id)?if_exists == (cartgoods.shop_cust_id)?if_exists>
			  		<#assign count = count + 1>	
			  	<tr id="item${(cartgoods.trade_id)?if_exists}" class="goodsid${(cartgoods.trade_id)?if_exists}">
                    <td width="5%">
                        <input type="checkbox" class="baoGoods" id="baoCart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists}" value="${(cartgoods.shop_cust_id)?if_exists},${(cartgoods.goods_id)?if_exists},${(cartgoods.trade_id)?if_exists},${(cartgoods.cookie_id)?if_exists},${(cartgoods.buy_num)?if_exists}"/>
                    	<input class="selgoods_id" type="hidden" value="${(cartgoods.goods_id)?if_exists}_"/>
                    </td>
                    <td width="32%" class="imgTd">
                        <div class="imgDiv">
                    		<a href="/mall-goodsdetail-${(cartgoods.goods_id)?if_exists}.html" target="_blank">
                    			<img width="55px" height="55px" src="${(cartgoods.img_path)?if_exists}" width="60" height="60"/>
                    		</a>
							<input id="goods_img${(cartgoods.goods_id)?if_exists}" type="hidden" value="${(cartgoods.img_path)?if_exists}"/>
							<input id="use_integral${(cartgoods.goods_id)?if_exists}" type="hidden" value="${(cartgoods.use_integral)?if_exists}"/>
                        </div>
                        <div class="textDiv">
                            <p>
                                <a href="/mall-goodsdetail-${(cartgoods.goods_id)?if_exists}.html" target="_blank">${(cartgoods.goods_name)?if_exists}</a>
								<input id="goods_name${(cartgoods.goods_id)?if_exists}" type="hidden" value="${(cartgoods.goods_name)?if_exists}"/>
								<input id="goods_cat${(cartgoods.goods_id)?if_exists}" type="hidden" value="${(cartgoods.goods_cat)?if_exists}"/>
                            </p>
	                          <#if cartgoods.sale_name?if_exists != null>  
	                          <@s.hidden id="sale_id${(cartgoods.trade_id)?if_exists}" value="${cartgoods.sale_id?if_exists}"/>
	                          <p><span>${cartgoods.sale_name?if_exists}</span></p>
	                          <#else>
	                          <@s.hidden id="sale_id${(cartgoods.trade_id)?if_exists}" value="0"/>
	                         </#if>
		                     <#if cartgoods.tax_rate != null && cartgoods.tax_rate != "0">
		                     <p class="slP">适用税率<b id="taxrate${(cartgoods.trade_id)?if_exists}">${cartgoods.tax_rate?if_exists}</b>%&nbsp;<span class="baotax" style="display:none;">关税：￥<b id="tax_rate${(cartgoods.trade_id)?if_exists}"> <#if cartgoods.tax_rate != null && cartgoods.tax_rate != "0">${(sum)?if_exists?number * (cartgoods.tax_rate)?if_exists?number / 100}<#else>0</#if></b></span>
		                     <i>
			                      <b class="priceb">税费=不含税价格*件数*商品税率</b></br>
			                         根据海关规定，本商品适用税率为${cartgoods.tax_rate?if_exists}%，
			                         若订单总税额<=50元，海关与以免征。
		                      </i>
		                     </p>
		                     </#if>
                        </div>
                    </td>
                    <td width="13%" class="proTd">
                  				<p> ${(cartgoods.spec_name)?if_exists}</p>
								<input id="spec_id${(cartgoods.trade_id)?if_exists}" type="hidden" value="${(cartgoods.spec_id)?if_exists}"/>
								<input id="spec_name${(cartgoods.trade_id)?if_exists}" type="hidden" value="${(cartgoods.spec_name)?if_exists}"/>
                    </td>
                    <td width="10%">
                        <b>
                        	<#if cartgoods.old_price!= null &&  cartgoods.sale_price?if_exists?number != cartgoods.old_price?if_exists?number>
                        	<span class="oldP">${cartgoods.old_price?if_exists}</span></br>
                        	</#if>
                        	${(cartgoods.sale_price)?if_exists}
                        	<input id="price_baoCart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?if_exists?replace(':','_')}" class="selgoods_sale_price" type="hidden" value="${(cartgoods.sale_price)?if_exists}"/>
                        	<input id="price${(cartgoods.trade_id)?if_exists}" type="hidden" value="${(cartgoods.sale_price)?if_exists}"/>
                        </b>
                    </td>
								<input id="baointegral${(cartgoods.trade_id)?if_exists}" class="selgoods_integral" type="hidden" value="${(cartgoods.integral)?if_exists}"/>
                    <td width="10%">
                        <div class="numDiv">
                    		<a onclick="SubBaoGoodsNum('baoCart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?if_exists?replace(':','_')}',${(cartgoods.trade_id)?if_exists?number},'${cartgoods.goods_id}','${(cartgoods.cookie_id)?if_exists}');" class="redBut"></a>
							
								<input  id="gnumber_baoCart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?replace(":",'_')}" type="text" class="numText" onkeyup="checkNums(this);addBaoNums('baoCart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?if_exists?replace(':','_')}',${(cartgoods.trade_id)?if_exists?number},'${stock}','${cartgoods.goods_id}','${limit_num}','${(cartgoods.cookie_id)?if_exists}');"  value="${(cartgoods.buy_num)?if_exists}">
								
								<input id="gnumber${(cartgoods.goods_id)?if_exists}_${(cartgoods.cookie_id)?if_exists?replace(':','_')}" class="selgoods_sale_price" type="hidden" value="${(cartgoods.buy_num)?if_exists}"/>
						
						    	<a onclick="AddBaoGoodsNums('baoCart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?if_exists?replace(':','_')}',${(cartgoods.trade_id)?if_exists?number},'${stock}','${cartgoods.goods_id}','${limit_num}','${(cartgoods.cookie_id)?if_exists}');" class="addBut"></a>
                          
                        </div>
                    </td>
                    <td width="10%">
                        <b class="priceb"id="gprice_baoCart_${(cartgoods.shop_cust_id)?if_exists}_${(cartgoods.goods_id)?if_exists}_${(cartgoods.spec_id)?if_exists?replace(':','_')}">
							${sum?if_exists}
                        </b>
                    </td>
                    <td width="10%" class="aTd">
                        <p>
                            <a href="javascript:void(0);" onclick="insertColl('${(cartgoods.goods_name)?if_exists}','${(cartgoods.goods_id)}','${(cartgoods.sale_price)}');">
                                收藏
                            </a>
                        </p>
                        <p>
                            <a href="javascript:void(0);" onclick="removerCookies('${(cartgoods.trade_id)?if_exists}','${goodsid?if_exists}' )">
                                删除
                            </a>
                        </p>
                    </td>
                </tr>
               	</#if>
			  	</#list>	
			  		
            </table>
          </div>
        </#list>
        <div class="delDiv">
            <div class="f_left"><a href="javascript:void(0);" onclick="removerBaoAllCookies()">删除选中物品</a></div>
            <div class="jsDiv f_right">
               <p>已选<b id="baoCount">0</b>种商品 共<b id="baoAllnum">0</b>件</p>   
               <p>商品总计：<span id="baototal_goodsprice">1500</span>元</p>         
               <p class="gup gup2" id="baogup"><b>订单关税：<span id="baototal_tax">54.10</span>元</b>
                  <i>
                    订单关税超过50元！建议分开下单。<br/>
                    (海关免征限额为50元)<br/>
                    <b>您可以重新选择商品，把关税总控制在50元以下，
                     即可免税。</b>
                    <img src="/malltemplate/bmall/images/topClose.png" class="close">
                  </i>
               </p>
               <p class="zjp">
                  总计(不含运费)：<b class="priceb" id="baototal">0</b>元
               </p>
           </div>
           <div class="clear"></div>
        </div>
        <!---->
        <div class="settle">
           <div class="lSpan">
                <a href="/">
                </a>
               <#-- <b>
                    共送积分：
                </b>
               <span id="all_integral">0</span>-->
            </div>
            <div class="rSpan">

                <a  href="javascript:void(0);" id="bao_tax" class="goSettle"  onclick="checkBaoTax();">保税区结算</a>
                <a  href="javascript:void(0);" id="bao_count" style="display:none;" class="goSettle"  onclick="baoCartSubmit();">继续结算</a>
            </div>
        </div>
    </div>
    </#if>
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
        <!---->        
</div>
<!--随便带走-->
<div class="hsale" id="collectId">
    <div class="hsh2">
        <ul>
            <li>
                最近浏览过的
            </li>
            <li>
                最近收藏
            </li>
        </ul>
    </div>
    <div class="tabDiv">

        <div class="hscont">

            <ul id="historyCarRecord">
            </ul>
            <div class="clear">
            </div>
        </div>
        <div class="hscont">
            <ul>
            <#list goodsCollectList as goodsList>
                <li>
                    <div>
                        <a href="/mall-goodsdetail-${goodsList.goods_id?if_exists}.html">
                            <#if goodsList.list_img!=null && goodsList.list_img!=''>
								<img src="${goodsList.list_img?if_exists}">
							<#else>
								<img src="${cfg_nopic?if_exists}">
							</#if>
                        </a>
                    </div>
                    <p>
                        <span class="lpspan">
                            <b>
                                <#if goodsList.min_sale_price !=goodsList.max_sale_price&&goodsList.max_sale_price!=null>
									￥${(goodsList.min_sale_price)?if_exists}-￥${(goodsList.max_sale_price)?if_exists}
								<#else>
									￥${(goodsList.min_sale_price)?if_exists}
								</#if>
                            </b>
                        </span>
                        <br class="clear">
                    </p>
                    <p>
                        <a href="/mall-goodsdetail-${goodsList.goods_id?if_exists}.html">
                           <#if (goodsList.goods_name)?if_exists?length lt 10>
		           				${goodsList.goods_name}
		           			<#else>
		           				${goodsList.goods_name[0..9]}...
		           			</#if>
                        </a>
                    </p>
                </li>
                </#list>
            </ul>
            <div class="clear">
            </div>
        </div>
    </div>



</div>
<!--尾部-->
<div class="clear"></div>
<#include "/a/bmall/mallbottom.html">
</@s.form>
</body>
<script src="/malltemplate/bmall/js/jqueryTab.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/payProcess.js" type="text/javascript"></script>
</html>
