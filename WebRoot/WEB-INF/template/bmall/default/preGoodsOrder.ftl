<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="x-ua-compatible" content="ie=7" />
        <title>
            ${cfg_webname?if_exists}_提交预售订单
        </title>
        <#include "/WEB-INF/template/bmall/default/jscsstop.ftl">
		<link href="/malltemplate/bmall/css/payProcess.css" rel="stylesheet" type="text/css">
		<script src="/malltemplate/bmall/js/order.js" type="text/javascript"></script>
		<script src="/malltemplate/bmall/js/orderMainjs.js" type="text/javascript"></script>
		<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
		<script type="text/javascript" src="/include/common/js/jquery.jNotify.js"></script>
		<script type="text/javascript" src="/include/common/js/jquery.alert.js"></script>
		<link href="/include/common/css/alert.css" rel="stylesheet" type="text/css" />
        <link href="css/public.css" rel="stylesheet" type="text/css">
        <link href="css/payProcess.css" rel="stylesheet" type="text/css">
    </head>
     <script type="text/javascript">
	//初始化加载
	$(document).ready(function(){
		//所属地区的回选
		loadArea("${areaIdStr?if_exists}","");
	 });
	 function ge(id){
 		loadArea(id,"");
	 }
  </script> 
    <body>
    <@s.form id="subOrder" action="/mall/directOrder!subPreOrder.action" method="post">
<!--订单提交隐藏域开始-->
<@s.hidden id="addr_id" name="addr_id"/>
<!--订单提交隐藏域结束-->
        <!--顶部-->
<#include "/WEB-INF/template/bmall/default/topest.ftl">
        <!--logo，搜索-->
        <div class="twLogoArea">
            <!--logo-->
            <div class="logo">
                <a href="/">
                    <img src="${cfg_logourl}" width="246px" height="64px" />
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
       <div  id="addressDiv"></div>
       <p class="addrsp"><span></span></p>
       <!--新增地址-->
       <div class="addAddress">
          <table width="100%" cellpadding="0" cellspacing="0">
            <tr><th width="15%"><span>*</span>收货人：</th><td width="85%"><input type="text"id="consignee" name="consignee" class="nameText"></td></tr>
            <tr><th><span>*</span>所在地区：</th><td><div id="areaDiv" style="margin-left:-5px;"></div></td></tr>
            <tr><th><span>*</span>详细地址：</th><td><textarea id="address"  name="address"  class="addressText"></textarea></td></tr>
            <tr><th><span>*</span>手机号码：</th><td><input type="text"  id="cell_phone" name="cell_phone" onkeyup="checkDigital(this)"class="nameText"></td></tr>
       <!--    <tr><th>邮箱：</th><td><input type="text" class="nameText"><span>用来接收订单提醒邮件，便于您及时了解订单状态</span></td></tr>--> 
            <tr><th></th><td><input type="button" class="saveBut"onclick="addAddr()"><input type="button" class="backBut"></td></tr>
          </table>
        </div>
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
                            在线支付或余额支付
                        </td>
                    </tr>
                    <tr>
                        <th>
                            配送方式：
                        </th>
                        <td>
                            由商家选择合作快递为您配送
                        </td>
                    </tr>
                    <tr style="display:none">
                        <th>
                            发票信息：
                        </th>
                        <td>
                            普通发票（纸质）
                            <span>
                                个人
                            </span>
                            <span>
                                明细
                            </span>
                            <i class="iModify">
                                [修改]
                            </i>
                        </td>
                    </tr>
                </table>
                <!--发票信息-->
                <div class="invStyle">
                    <table width="100%" cellpadding="0" cellspacing="0">
                        <tr>
                            <th>
                                发票开具方式：
                            </th>
                            <td>
                                <span>
                                    <input type="radio" checked="checked" name="style">
                                    普通发票(纸质)
                                </span>
                                <span>
                                    <input type="radio" name="style">
                                    增值税发票
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                发票抬头：
                            </th>
                            <td>
                                <span>
                                    <input type="radio" checked="checked" name="use">
                                    个人
                                </span>
                                <span>
                                    <input type="radio" name="use">
                                    单位
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                发票内容：
                            </th>
                            <td>
                                <span>
                                    <input type="radio" checked="checked" name="classify">
                                    明细
                                </span>
                                <span>
                                    <input type="radio" name="classify">
                                    办公用品
                                </span>
                                <span>
                                    <input type="radio" name="classify">
                                    耗材
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <th>
                            </th>
                            <td>
                                <input type="button" class="saveBut">
                                <input type="button" class="backBut">
                            </td>
                        </tr>
                    </table>
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
                            <th width="35%">
                                商品名称
                            </th>
                            <th width="13%">
                               单价(元)
                            </th>
                            <th width="15%">
                               定金 
                            </th>
                            <th width="10%">
                                尾款
                            </th>
                            <th width="12%">
                                数量
                            </th>
                            <th width="15%">
                                小计
                            </th>
                        </tr>
                    </table>
                </div>
                <!---->
                
        <#assign total=0>
        <#assign ship_total=0>
        <#list createListMap.shopList as shop>
        	<#assign shop_cust_id = "${(shop.shop_cust_id)?if_exists}">
        	<@s.hidden id="cust_id_str" name="cust_id_str" value="${(shop.shop_cust_id)?if_exists}"/>
        	<@s.hidden id="goods_length_str" name="goods_length_str" value="${(shop.goods_length)?if_exists}"/>
                <div class="cartTab ordercont">
                    <table width="100%" cellpadding="0" cellspacing="0">
                         <#assign shoptotal=0>
                         <#assign om= 0>
						 <#list createListMap.orderList as order>
						 	<#if (order.cust_id)?if_exists==shop_cust_id>
						 		<@s.hidden  name="goods_id_str" value="${(order.goods_id)?if_exists}"/>
						 		<@s.hidden  name="goods_name_str" value="${(order.goods_name)?if_exists}"/>
						 		<@s.hidden  name="goods_img_str" value="${(order.goods_img)?if_exists}"/>
						 		<@s.hidden  name="order_num_str" value="${(order.order_num)?if_exists}"/>
						 		<@s.hidden  name="spec_id_str" value="${(order.spec_id)?if_exists}"/>
						 		<@s.hidden name="spec_name_str" value="${(order.spec_name)?if_exists}"/>
						 		<@s.hidden  name="sale_price_str" value="${(order.sale_price)?if_exists}"/>
						 		<@s.hidden  name="give_inter_str" value="${(order.give_inter)?if_exists}"/>
						 		<#assign num = 0>
						 		<#assign om =om+ 1>
						 		<#assign price = 0>
						 		<#assign sum = 0>
						 		<#assign price = "${(order.sale_price)?if_exists}">
						 		<#assign num = "${(order.order_num)?if_exists}">
						 		<#assign sum = "${earnest?number * num?number}">
                        <tr>
                            <td width="35%" class="imgTd">
                                <div class="imgsDiv">
                                    <a  href="/mall-yushoudetail-${(order.direct_id)?if_exists}.html" target="_self">
                                       <img src="${(order.goods_img)?if_exists}" width="60" height="60"/>
                                    </a>
                                </div>
                                <div class="textDiv">
                                    <p>
                                        <a  href="/mall-yushoudetail-${(order.direct_id)?if_exists}.html" target="_self">
                                            ${(order.goods_name)?if_exists}
                                        </a>
                                    </p>
                                </div>
                            </td>
                            <td width="13%" class="proTd">
                            	${price}
                            </td>
                            <td width="15%">
                                 ${earnest}
                            </td>
                            <td width="10%">
                              ${(price?number)-(earnest?number)}
                            </td>
                            <td width="12%">
                               ${num}
                            </td>
                            <td width="15%">
                                <b class="priceb">
                                    ${sum}
                                </b>
                            </td>
                        </tr>
                       <#assign shoptotal = "${shoptotal?number + sum?number}">
				 	</#if>
				 </#list>
                    </table>
                     <!---->
                <div class="freDiv">
                      <@s.hidden cssClass="goods_amount_str" name="goods_amount_str" value="${shoptotal?if_exists}"/>
                      <@s.hidden id="goodsnum"  value="${num?if_exists}"/>         
                      <#assign shoptotal = "${shoptotal?number}">
			         	<@s.hidden id="shop_total_amount_str" name="shop_total_amount_str" value="${shoptotal?if_exists}"/>   
			         	<@s.hidden id="smode_id_str" name="smode_id_str" value="0"/>    
			         	<@s.hidden id="ship_price" name="ship_free_str" value="0"/> 
			         	 <input type="hidden" name="end_area_attr" id="end_area_attr" value="7"/>
                    <div class="f_right">
                        <p>
                            <span>
                              ${om}
                            </span>
                            件商品&nbsp;&nbsp;总商品金额：${shoptotal}元
                        </p>
                        <p class="ship_fee1">
                            运费：免运费
                            <!--<span class="ship_fee2">
                              <select class="ship_fee">
	 							<option id="0" value="0">
	 								免运费
	 							</option>
			 				</select>
                            </span>-->
                        </p>
                        <p class="orderprice">
                            应付总额：${shoptotal}元
                        </p>
                    </div>
                </div>
                <!---->
                </div>
               
                
                <#assign total = "${total?number + shoptotal?number}"> 
         </#list>
        <#assign earnest = "${earnest?number * num?number}"> 
        <@s.hidden id="goods_price" name="goods_price" value="${earnest?number}"/>
        <@s.hidden name="earnest" value="${earnest?if_exists}"/><!--定金-->
        <@s.hidden name="endpay_time" value="${endpay_time?if_exists}"/><!--尾款支付结束时间-->
        <@s.hidden  name="sale_title" value=" ${(sale_title)?if_exists}"/><!--预售标题-->
  	    <@s.hidden id="direct_id" name="direct_id" value="${(direct_id)?if_exists}"/><!--预售标识-->
  	    <@s.hidden name="end_time" value="${(end_time)?if_exists}"/><!--定金支付结束时间-->
        
        <@s.hidden id="total_amount" name="total_amount" value="${total?if_exists}"/>

        <@s.hidden id="cartId_str" name="cartId_str" value="${(trade_id_str)?if_exists}"/>
		<@s.hidden id="cookieId_str" name="cookieId_str" value="${(cookie_id_str)?if_exists}"/>
                <div class="settle">                    
                    
                    <span class="rsSpan">
                        <b>
                            预付定金(含运费)：
                        </b>
                        <b class="priceb cartprice"  >
                           ${earnest?if_exists}
                        </b>
                                                     元
                       <#if can_buy!=null>
                        <span><font color="red">${can_buy?if_exists}</font></span>
                        <#else>
                        <a href="javascript:void(0);" onclick="submitPreOrder();"></a>
                        </#if>
                       
                    </span>
                </div>
            </div>
        </div>
        <!--尾部-->
        <div class="clear">
        </div>
<#include "/a/bmall/mallbottom.html">
    </body>
    
    <script type="text/javascript" src="/include/common/js/common.js"></script>
    <script src="/malltemplate/bmall/js/jqueryPopupLayer.js" type="text/javascript"></script>
    <script src="/malltemplate/bmall/js/payProcess.js" type="text/javascript"> </script>
	<script src="/include/components/artDialog5.0/artDialog.min.js"></script>
	<link href="/include/components/artDialog5.0/skins/blue.css" rel="stylesheet" />
   <@s.token/> 
	</@s.form>
<script src="/malltemplate/bmall/js/jqueryPopupLayer.js" type="text/javascript"></script>
</html>