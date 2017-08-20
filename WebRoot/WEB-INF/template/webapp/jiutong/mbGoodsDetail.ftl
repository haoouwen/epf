<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}-商品详细</title>

<meta name="author" content="久通宏达科贸（北京）有限公司">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">           
<meta http-equiv="Pragma" content="no-cache">
<link href="/wro/webapp_common.css" rel="stylesheet" type="text/css"></link>
<link href="/malltemplate/jiutong/css/mbProduct.css" rel="stylesheet" type="text/css"></link>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<#assign spec_count=0>
                <#list selfspecnameList as ssn>
                    <#assign spec_count=spec_count+1>
                </#list>
        <input type="hidden" value="${spec_count?if_exists}" id="spec_nums">
        
<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>商品详细<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<@s.form id="buy" action="/webapp/submitorder.html">
            <@s.hidden id="goods_id" name="goods_id" value="${(goods.goods_id)?if_exists}"/>
            <@s.hidden name="cust_name" value="${(member.cust_name)?if_exists}" />
            <@s.hidden name="specstr_str_all" id="specstr_str_all" value="${specstr_str?if_exists}"/>
            <@s.hidden id="downpri" name="downpri" />
            <@s.hidden id="uppri" name="uppri" />
            <@s.hidden id="selName" name="selName" />
            <@s.hidden id="user_name" name="user_name" />
            <@s.hidden id="type" name="type" />
            <@s.hidden id="show" name="show" />
            <@s.hidden id="min_sale_price" name="min_sale_price" value="${min_sale_price?if_exists}"/>
            <@s.hidden id="min_market_price" name="min_market_price" value="${min_market_price?if_exists}"/>
            <@s.hidden id="stock" name="stock" value="${total_stock?if_exists}" />
            <@s.hidden id="singleImg" value="${goods.list_img?if_exists}" />
            <@s.hidden id="is_limit" value="${goods.is_limit?if_exists}" />
             <@s.hidden id="limit_num" value="${goods.limit_num?if_exists}" />
            <@s.hidden id="specstr_attr" value="${specstr_attr?if_exists}" />
            <@s.hidden id="isBuySelf" name="isBuySelf" />
            <!--立即购买隐藏域开始-->
            <@s.hidden id="cust_id_str" name="cust_id_str" value="${(goods.is_international)?if_exists}"/>
            <@s.hidden id="goods_id_str" name="goods_id_str" value="${(goods.goods_id)?if_exists}"/>
            <@s.hidden id="goods_name_str" name="goods_name_str" value="${(goods.goods_name)?if_exists}" />
            <@s.hidden id="goods_cat_str" name="goods_cat_str" value="${(goods.cat_attr)?if_exists}" />
            <@s.hidden id="is_ship" name="is_ship" value="${(goods.is_ship)?if_exists}"/>
            <@s.hidden id="goods_length_str" name="goods_length_str" value="1" />
            <@s.hidden id="spec_name_str" name="spec_name_str" />
            <@s.hidden id="spec_id_str" name="spec_id_str" />
            <@s.hidden id="goods_img_str" name="goods_img_str" value="${(goods.list_img)?if_exists}" />
            <@s.hidden id="logsweight" name="logsweight" value="${(goods.logsweight)?if_exists}"/>
            <@s.hidden id="ship_id" name="ship_id" value="${(goods.ship_id)?if_exists}"/>
            <@s.hidden id="volume" name="volume" value="${(goods.volume)?if_exists}" />
            <@s.hidden id="use_integral_str" name="use_integral_str" value="${(goods.use_integral)?if_exists}"/>
            <@s.hidden id="integral_state" name="integral_state" value="0"/>
            <@s.hidden id="order_type" name="order_type" value="1" />
            <@s.hidden id="sale_price_str" name="sale_price_str" value="${min_sale_price?if_exists}" />
            <@s.hidden id="give_inter_str" name="give_inter_str" />
            <@s.hidden id="shop_name_str" name="shop_name_str" value="${(shopconfig.shop_name)?if_exists}"/>
            <@s.hidden id="shop_qq_str" name="shop_qq_str" value="${(shopconfig.qq)?if_exists}"/>
            <@s.hidden id="radom_no_str" name="radom_no_str" value="${(shopconfig.radom_no)?if_exists}"/>
            <@s.hidden name="flag" value="1" />
            <@s.hidden name="loc" id="refloc" />
            <input type="hidden" id="title" value="${goods.goods_name?if_exists}"/>
            <!--立即购买隐藏域结束-->
            <!--地区相关隐藏域开始-->
            <input id="shipareaid" type="hidden" value="${shipareaid?if_exists}" />
            <input id="areaid" type="hidden" value="${areaid?if_exists}" />
            <input id="careaid" type="hidden" />
            <input id="sareaid" type="hidden" value="${shipareaid?if_exists}" />
            <!--地区相关隐藏域结束-->
            <!--购物车相关隐藏域开始-->
            <input id="cart_goods_id" type="hidden" value="${(goods.goods_id)?if_exists}" />
            <input id="cart_goods_name" type="hidden" value="${(goods.goods_name)?if_exists}"/>
            <input id="cart_is_virtual" type="hidden" value="${(goods.is_virtual)?if_exists}" />
            <input id="cart_goods_img_path" type="hidden" value="<#if goods.list_img!=null>${(goods.list_img)?if_exists}<#else>${cfg_nopic?if_exists}</#if>"/>
            <input id="cart_shop_cust_id" type="hidden" value="${(goods.is_international)?if_exists}"/>
            <input id="cart_shop_name" type="hidden" value="${(shopconfig.shop_name)?if_exists}"/>
            <input id="cart_give_inter" type="hidden" value="${min_sale_price?if_exists}" />
            <input id="cart_goods_sale_price" type="hidden" value="${min_sale_price?if_exists}" />
            <input id="cart_sepc_name" type="hidden" />
            <input id="cart_sepc_id" type="hidden" />
            <input id="cart_shopqq" value="${(shopconfig.qq)?if_exists}" type="hidden"/>
            <input id="cart_radom_no" value="${(shopconfig.radom_no)?if_exists}" type="hidden"/>
            <!--购物车相关隐藏域结束-->
            <@s.hidden id="temp_stock" value="${total_stock}" />

<div class="proLDetail">

  <div class="proLCont">
  
    <div class="proFocus">
       <ul>
         <#if imgGroup!=''>
            <#list (imgGroup)?if_exists?split( ",") as img>
                <li>
                        <img src="${img?if_exists}" >
                </li>
            </#list>
            <#else>
                <li>
                        <img src="${cfg_nopic?if_exists}">
                </li>
        </#if>
       </ul>
    </div>
    <p class="pName"><span><#if (goods.depot_id)?if_exists=="1">欧洲直邮<#else>保税仓发货</#if></span>${goods.goods_name}</p>
     <#if sale_names != "">
      <p>促销信息：<span><b class="salesb">${sale_names?if_exists}</b></span></p>
      <#if limit_time =="1">
      <p class="sfPrice">
         <@s.hidden id="difftime" name="difftime" value="${difftime?if_exists}"/>
         <span id="ggleftTime" class="rtime">
          <b>0</b>天<b>0</b>时<b>0</b>分<b>0</b>秒
         </span>
       </p>  
       </#if> 
     </#if>
    <p class="pPrice">￥
		 <b id="sale_price">
	    <#if min_sale_price?if_exists!='' &&max_sale_price?if_exists!='' &&min_sale_price!=max_sale_price>
            ${min_sale_price}-${max_sale_price}
            <#elseif min_sale_price==max_sale_price>
                ${min_sale_price}
                <#else>
                    0.00
        </#if>
        <#if  goods.unit!=null&&goods.unit?if_exists!=''>/${goods.unit}</#if>
	    </b>
    	
    </p>
    <p>市 场 价：<span>￥</span>
    <span  id="market_price_str">
	   		<#if min_market_price?if_exists!='' &&max_market_price?if_exists!='' &&min_market_price!=max_market_price>
                ${min_market_price}-${max_market_price}
                <#elseif min_market_price==max_market_price && !min_market_price=null>
                    ${min_market_price}
                    <#else>
                        0.00
            </#if>
	    </span>
    </p>
    
      <p>
       <a href="/webapp/goods!kefulist.action" >
       <img src="/malltemplate/jiutong/images/kfIcon.gif"  style="vertical-align:middle"  /> 联系客服</a>
     </p>
     
    <#if goods.use_integral?if_exists =='1'>
    <p>支持积分购买：<a href="javascript:void(0)" onclick="integralBuy();" class="jfBuy">积分购买</a></p>
    </#if>
     <#if getCouponByGoodsList?if_exists && getCouponByGoodsList.size() gt 0>
      <p>领取优惠券：
         <#list getCouponByGoodsList as getCouponByGoods>
	         <span>
	           <a href="/webapp/coupon_${(getCouponByGoods.coupon_id)?if_exists}.html?platformType=0" style="color:red;">${(getCouponByGoods.coupon_name)?if_exists}</a>
	         </span>
	     </#list>
      </p>
	  </#if>
  </div>
  
</div>
 <#if goodsbrand?if_exists>
<div class="proNotice">
   <p>
		    <span >
		     <a href="/webapp/brandslist/detail_${(goodsbrand.brand_id)?if_exists}.html"><img src="${(goodsbrand.logo)?if_exists}"    style="vertical-align:middle"   widhth='100px' height="50px" /></a>
		    </span>	
		    <span >
		     <a href="/webapp/brandslist/detail_${(goodsbrand.brand_id)?if_exists}.html" >${(goodsbrand.brand_name)?if_exists}</a>
		    </span>
   </p>
</div>
</#if>
<div class="proNotice">
   <p>根据（中华人民共和国进境物品完税价格表），本商品适用税为<span>${goods.tax_rate?if_exists}</span>%</p>
</div>

<div class="proSDetail">

   <div class="proLCont">
      <!--销量显示-->
      <div class="xlDiv">
         <span class="xlSpan">
            <#if (goods.sale_num)?if_exists>${(goods.sale_num)}<#else>0</#if>
         </span>
        <span class="pjSpan">  ${evalNum?if_exists}</span>
        <span  id="dshoucid"  class=<#if is_collect>yscSpan<#else>scSpan</#if> <#if !is_collect>onclick="ginsertColl('0');"</#if> title="<#if is_collect>已收藏<#else>收藏商品</#if>"></span>
      </div>
      <!--选择规格-->
      <div class="ggDiv">
         <table width="100%" cellpadding="0" cellspacing="0">
              		<!--产品规格-->
<#if is_more_attr=='0'>		
 <#if selfspecnameList?if_exists && (selfspecnameList?size > 0)>
    <#list selfspecnameList as ssn>	
    	<#if (ssn.show_type)?if_exists !="1">
              <tr class="specstr"><th id="${(ssn.spec_serial_id)?if_exists}">${(ssn.sname)?if_exists}</th>
                  <td>
                    <div class="ggSDiv spttext">
                      <ul class="selsize">
                      <#list selfsepcvalueList as ssv> 
		                <#if ssv.spec_serial_id==ssn.spec_serial_id>
		                 <li><a title="${(ssv.self_spec_value)?if_exists}" alt="${(ssv.self_spec_value)?if_exists}" id="${(ssv.self_size_id)?if_exists}" href="javascript:void(0);">${(ssv.self_spec_value)?if_exists}</a></li>  
		            	</#if>
           			</#list>
                       
                      </ul>
                    </div>
                  </td>
              </tr>
               <#else>
              <tr class="specstr"><th id="${(ssn.spec_serial_id)?if_exists}">${(ssn.sname)?if_exists}</th>
                  <td >
                    <div class="ggSDiv sptpic">
                      <ul class="selsize">
                      	<#list selfsepcvalueList as ssv> 
		                <#if ssv.spec_serial_id==ssn.spec_serial_id>  
		                	 <li>
		                	 
		                	 <a title="${(ssv.self_spec_value)?if_exists}" alt="${(ssv.self_spec_value)?if_exists}" id="${(ssv.self_size_id)?if_exists}" href="javascript:void(0);" style="position:relative;">
		                		<img src="${(ssv.self_spec_img)?if_exists}" style="width:32px;height:32px;"/>
		                		<i id="${(ssv.self_size_id)?if_exists}"> <img="${(ssv.self_img)?if_exists}" style="width:32px;height:32px;padding-left:-60px;position:absolute;left:-1px;top:-1px;"></i>
		                	</a>
		                	 
		                	 </li>  
		                	
		            	</#if>
           				</#list>
                        
                      </ul>
                    </div>
                  </td>
              </tr>
		 </#if>
    </#list>
</#if>
</#if>
            <tr><th>数量：</th>
                <td>
                  <div class="ggnDiv">
                    <span class="recNumBut" onclick="sub();"></span>
                     <input type="text" onkeyup="getShipPrice();checkBuyNum('1');" value="1" name="order_num_str"  class="numText" id="buy_nums" />
                    <span class="addNumBut" onclick="add();"></span>
                  </div>
                   <p class="ggnP"  <#if (goods.depot_id)?if_exists=="1">style="display:none;"</#if>> 库存<#if total_stock?if_exists!=''>
                        <strong id="total_stock">
                           ( ${total_stock})
                        </strong>
                        <#else>
                            0
                    </#if></p>
                </td>
            </tr>
         </table>
      </div>
   </div>
</div>

<div class="proNotice">
   <p>根据（<a href="http://gss.mof.gov.cn/zhengwuxinxi/gongzuodongtai/201603/t20160324_1922972.html" target="_blank" style="color: red;">关于2016年3月24日跨境电子商务零售进口税收政策的通知）</a>个人物品申报）应征进口物品综合税
，海关不再免征。</p>
</div>


<!--商品详情-->
<div class="proDetail" id="proDeId">
   <!---->
   <div class="proDeh2" >
      <ul id="proDeh2Id">
        <li>图文详情</li>
        <li>产品参数</li>
        <li>商品评价 ( ${evalNum?if_exists})</li>
      </ul>
   </div>
   <!---->
   <div class="tabDiv">
   
      <!---->
      <div class="proDCont">
         <div class="proCont" id="phone_detai_id">
         <#if (goods.goods_video)?if_exists>
            <div style="width:300px;margin:0 auto;margin-top:10px;">
			    <video width="300" height="220" controls="controls">
			       <source src="${(goods.goods_video)?if_exists}" type="video/mp4">
			    </video>
			 </div>
		</#if>
          ${(goods.phone_goods_detail)?if_exists}
          ${goods.product_process?if_exists}
          ${goods.import_cert?if_exists}
        </div>
      </div><!--proDCont end-->
      
      <!---->
      <div class="proDCont">
        <div class="parameter">
          <table width="100%" cellpadding="0" cellspacing="0">
            <tr>
               <th width="30%">参数名称</th>
               <td>具体参数</td>
           </tr>
            <tr>
                <th>商品编号</th>
                <td >${goods.goods_no?if_exists}</td>
            </tr>
            <tr>
              <th>外文名称</th>
              <td><#if goods.goods_en_name !="">${goods.goods_en_name?if_exists}<#else>-</#if></td>
            </tr>
            <tr>
                <th>条形码</th>
                <td><#if goods.bar_code !="">${goods.bar_code?if_exists}<#else>-</#if></td>
            </tr>
            <tr>
                <th>品牌</th>
                <td><#if brand_name !="">${brand_name?if_exists}<#else>-</#if></td>
             </tr>
             <tr>
               <th>重量</th>
               <td><#if goods.weight !="">${goods.weight?if_exists}克<#else>-</#if></td>
             </tr>
             <tr>
               <th>保质期</th>
               <td><#if goods.quality !="">${goods.quality?if_exists}月<#else>-</#if></td>
             </tr>
             <tr>
               <th>产地	</th>
               <td><#if goods_place !="">${goods_place?if_exists}<#else>-</#if></td>
               </tr>
             <tr>
               <th>生产商</th>
               <td><#if goods.producer !="">${goods.producer?if_exists}<#else>-</#if></td>
             </tr>
             <tr>
               <th>生产地址</th>
               <td><#if goods.product_address !="">${goods.product_address?if_exists}<#else>-</#if></td>
               </tr>
             <tr>
               <th>出口经销商</th>
               <td><#if goods.export !="">${goods.export?if_exists}<#else>-</#if></td>
             </tr>
             <tr>
                <th>海关已备案 </th>
                <td><#if goods.is_customs !=""><img src="${goods.is_customs?if_exists}"/><#else>-</#if></td>
                </tr>
             <tr>
                <th>商检经备案</th>
                <td><#if goods.inspection !=""><img src="${goods.inspection?if_exists}"/><#else>-</#if></td>
             </tr>
          </table>
        </div>
        <!--商品属性-->
         <#if (selfextendattrList?size>0)>
         <div class="parameter">
          <table width="100%" cellpadding="0" cellspacing="0">
            <#list selfextendattrList as selfExtendAttr>
            <tr><th width="30%">  ${(selfExtendAttr.ex_attr_alias)?if_exists}</th>
            <td><#if (selfExtendAttr.ex_attr_value)?if_exists != "">
                ${selfExtendAttr.ex_attr_value}
            <#else>
                -
            </#if><td></tr>
             </#list>
          </table>
        </div>
        </#if>
	      <!--商品参数-->
	      <div class="prodParameter">
              <#if (selfparagroupList?size>0)>
                <h2>商品参数</h2>
                 <table width="100%" cellpadding="0" cellspacing="0" class="partab">
                        <#list selfparagroupList as selfparagroup>
                        <tr><th colspan="2" style="text-align:center;">${selfparagroup.para_name?if_exists}</th></tr>
                        <#assign n= 0>
                        <#list selfparavalueList as selfparavalue>
                            <#assign n= n + 1> 
                            <#if (selfparavalue.slef_para_group_id)?if_exists == selfparagroup.slef_para_group_id>
                                <#if n%2 == 1>
                                <tr>
                                </#if>
                                    <th width="15%">
                                        ${(selfparavalue.para_name)?if_exists}
                                    </th>
                                     <td width="35%">
                                    <#if (selfparavalue.slef_para_value)?if_exists != "">
                                        ${selfparavalue.slef_para_value}
                                    <#else>
                                        -
                                    </#if>
                                    </td>
                                <#if n%2 == 0>
                                </tr>
                                <#elseif n == "${selfparavalueList?size}">
                                </tr>
                                </#if>
                            </#if>
                        </#list>
                        </#list>
                    </table>
                </#if>
             </div>        
      </div>
      <!--proDCont end-->
      
      <!---->
      <div class="proDCont">
         <div class="evaluation">
            <!---->
            <div class="evah2">
               <span class="selSapn" g_eval="1">好评(${eval_one})</span>
               <span g_eval="0">中评(${eval_two})</span>
               <span g_eval="-1">差评(${eval_three})</span>
            </div> 
            <!---->
             <div class="evaluMain" id="evaluMain"></div>		   
         </div>
      </div><!--proDCont end-->
       
   </div><!--tabDiv end-->
</div>


 <#if (relateGoodsList?if_exists?size>0)>
<!--关联商品-->
<div class="yxMember">
      <h2>推荐商品</h2>
      <div class="hotProuct">
         <ul>
            <#list relateGoodsList as goods> 
	           <li>
	             <div>
	                <a href="/webapp/goodsdetail/${goods.goods_id?if_exists}.html" target="_blank">
	                 <img src="<#if goods.list_img!=null>${(goods.list_img)?if_exists}<#else>${cfg_nopic?if_exists}</#if>"  />
	                </a>
	                <p class="pName"><a href="/webapp/goodsdetail/${goods.goods_id?if_exists}.html" target="_blank">${goods.goods_name?if_exists}</a></p>
	                <p class="pPrice">￥${goods.min_sale_price?if_exists}</p>
	             </div>
	           </li>
            </#list>
         </ul>
      </div>
 </div>
  </#if>



<input type="hidden" id="custnum" name="custnum" value="${custnum?if_exists}"/>
<input type="hidden" id="registertype" name="registertype" value="${registertype?if_exists}"/>
</@s.form>
<!--尾部-->
<footer class="botDiv">
     <div class="both2">
        <span class="f_left"><script src="/include/iswebapplogin.jsp?custnum=${custnum?if_exists}&registertype=${registertype?if_exists}"></script></span>
        <span class="f_right"><a href="#">返回顶部</a></span>
     </div>
     <div class="botCont">
       <p>${cfg_powerby?if_exists} <br/> ${cfg_beian?if_exists}</p>
     </div>
</footer>

<#include "/a/webapp/mbCommon.html">

<#if total_stock?if_exists!='' &&total_stock?if_exists!="0">
<!--购物浮动-->
<div class="cartDiv">
  <div class="carCont">
     <a href="###" onclick="addCart(this);"><i class="iCart">加入购物车</i></a>
      
 <#if goods.is_limit?if_exists=='1'>
													<a href="###" onclick="buyNow()"><i class="iBuy">立即购物</i></a>
												</#if>
      <a href="###" class="pCarta" onclick="goCart();"><i class="pCart"></i></a>
   </div>
</div>
</#if>
 <script type="text/javascript" charset="utf-8" src="http://lead.soperson.com/20001014/10056071.js"></script>
</body>

<script src="/wro/webapp_common.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/jqueryTab.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/jqueryMobileSlider.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/jqueryFloat.js" type="text/javascript"></script>
<script src="/include/common/js/tablePlugin.js" type="text/javascript"></script>
<script src="/include/common/js/commonplugin.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/mbProduct.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/goodsSpec.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/mbgoods.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/collect.js" type="text/javascript"></script>
<script src="/include/common/js/timerJs.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/jquery.lazyload.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/operImgMustLazyload.js" type="text/javascript"></script>
</html>
