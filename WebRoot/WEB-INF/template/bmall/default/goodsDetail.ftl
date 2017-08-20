<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>
            ${seo_title?if_exists}
        </title>
        <meta name="keywords" content="${seo_keyword?if_exists}" />
        <meta name="description" content="${seo_desc?if_exists}" />
        <#include "/WEB-INF/template/bmall/default/jscssinclude.ftl">
		<script src="/wro/goodsdetail.js" type="text/javascript"></script>
		<script src="/malltemplate/bmall/js/jqueryPopupLayer.js" type="text/javascript"></script>
		<link href="/wro/goodsdetail.css" rel="stylesheet" type="text/css"/>
            <!--规格个数-->
            <#assign spec_count=0>
                <#list selfspecnameList as ssn>
                    <#assign spec_count=spec_count+1>
                </#list>
                <input type="hidden" value="${spec_count?if_exists}" id="spec_nums">
         <script src="/malltemplate/bmall/js/jquery.lazyload.js" type="text/javascript"></script>
         <script src="/malltemplate/bmall/js/operImgMustLazyload.js" type="text/javascript"></script>
    </head>
    <body>
        <@s.form id="buy" action="/mall/order!goOrder.action">
            <@s.hidden id="goods_id" name="goods_id" value="${(goods.goods_id)?if_exists}"/>
            <@s.hidden name="cust_name"  id="cust_name" value="${(member.cust_name)?if_exists}" />
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
            <@s.hidden id="logsweight" name="logsweight" value="${(logsweight_attr)?if_exists}"/>
            <@s.hidden id="ship_id" name="ship_id" value="${(goods.ship_id)?if_exists}"/>
            <@s.hidden id="volume" name="volume" value="${(volume_attr)?if_exists}" />
            <@s.hidden id="use_integral_str" name="use_integral_str" value="${(goods.use_integral)?if_exists}"/>
            <@s.hidden id="integral_state" name="integral_state" value="0"/>
            <#if goods.active_state?if_exists!='0' && flag_active_state!=null>
                <@s.hidden name="sale_price_str" value="${active_price?if_exists}" />
                <@s.hidden name="order_type" value="5" />
                <@s.hidden name="group_id" value="${activeId?if_exists}" />
                <@s.hidden name="active_MaxBuy" value="${active_MaxBuy?if_exists}" id="active_MaxBuy"/>
                <#else>
                    <@s.hidden id="order_type" name="order_type" value="1" />
                    <@s.hidden id="sale_price_str" name="sale_price_str" value="${min_sale_price?if_exists}" />
            </#if>
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
<!--头部-->
<#include "/a/bmall/malltop.html">
<!--分类，导航-->
<div class="headerList">
    <div class="w1180">
      <!--导航-->
      <#include "/a/bmall/mallnav.html">
      <!--分类-->
      <#include "/a/bmall/mallcat.html">
   </div>
</div>

<!--内容-->
<div class="postion"><span style="margin-left:-220px;"><a href="#">${postName}</a><span> > </span>${(goods.goods_name)?if_exists}</span></div>

<!--详细页头部-->
<div class="prodeBack">

  <div class="prodeMian">
     <!--放大镜-->
     <div class="prozoom">
        <div class="zoom">
           <div class="jqzoom">
                <#if imgGroup?if_exists!=''>
                    <#list imgGroup?split( ',') as firstImg>
                        <#if firstImg_index='0'>
                           <!-- 详细页面显示 中图和水印 暂时不启用
                            <#if firstImg?index_of( 'big') gt 0>
                                <#assign midImg=firstImg?replace('big','mid')>
                                <img src="${midImg?if_exists}" jqimg="${firstImg?if_exists}">
                                <#else>
                                    <#assign midImg=firstImg?replace('big','mid')>
                                    <img src="${midImg?if_exists}" jqimg="${firstImg?if_exists}">
                            </#if>
                            -->
                             <#if firstImg?index_of( 'big') gt 0>
                                <img src="${firstImg?if_exists}" jqimg="${firstImg?if_exists}">
                                <#else>
                                    <img src="${firstImg?if_exists}" jqimg="${firstImg?if_exists}">
                            </#if>
                        </#if>
                    </#list>
                    <#else>
                        <img src="${cfg_nopic?if_exists}" jqimg="${cfg_nopic?if_exists}">
                 </#if>
             </div>
             <div class="zoomcont">
                 <div class="preBut"></div>
                 <div class="speclist">
                    <ul>
                        <!-- 详细页面显示 中图和水印 暂时不启用
                        <#if imgGroup!=''>
                            <#list (imgGroup)?if_exists?split( ",") as img>
                                 <#assign smallImg=img?replace('big','small')>
                                 <#assign midImg=img?replace('big','mid')>
                                <li>
                                    <a href="###">
                                        <img src="${smallImg?if_exists}"  jbigimg="${img?if_exists}" jmidimg="${midImg?if_exists}">
                                    </a>
                                </li>
                            </#list>
                            <input type="hidden" id="img_str" value="${(imgGroup)?if_exists}">
                            <#else>
                                <li>
                                    <a href="###">
                                        <img src="${cfg_nopic?if_exists}">
                                    </a>
                                </li>
                        </#if>
                        -->
                         <#if imgGroup!=''>
                            <#list (imgGroup)?if_exists?split( ",") as img>
                                <li>
                                    <a href="#">
                                        <img src="${img?if_exists}" >
                                    </a>
                                </li>
                            </#list>
                            <input type="hidden" id="img_str" value="${(imgGroup)?if_exists}">
                            <#else>
                                <li>
                                    <a href="#">
                                        <img src="${cfg_nopic?if_exists}">
                                    </a>
                                </li>
                        </#if>
                    </ul>
                 </div>
               <div class="nextBut"></div>
		     </div>
		  </div>    
	  </div><!--prozoom end-->
      <!---->
      <div class="pw625">
		 <div class="ph2"> 
		    <p class="bph2"><span><#if (goods.depot_id)?if_exists=="1">欧洲直邮<#else>保税仓发货</#if></span>${goods.goods_name}</p>
            <#if goods.title?if_exists !=null && goods.title?if_exists!= ""><p class="sph2">${goods.title?if_exists}</p></#if>
		 </div>
         <div class="pw410">
          <!---->
          <div class="protab">
               <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                <th width="20%">销售价</th>
                <td width="80%">
                   <p><span id="sale_price" class="sPrice">
	                    <#if min_sale_price?if_exists!='' &&max_sale_price?if_exists!='' &&min_sale_price!=max_sale_price>
	                        ${min_sale_price}-${max_sale_price}
	                    <#elseif min_sale_price==max_sale_price>
	                        ${min_sale_price}
	                    <#else>
	                        0.00
	                    </#if>                
                </span>元
                <span class="mPrice">
                   市场价：
                <span id="market_price_str">   
			            <#if min_market_price?if_exists!='' &&max_market_price?if_exists!='' &&min_market_price!=max_market_price>
			                ${min_market_price}-${max_market_price}
			            <#elseif min_market_price==max_market_price && !min_market_price=null>
			                ${min_market_price}
			             <#else>
			                0.00
			            </#if>
                </span>                   
                   元
                </span></p>
                 <p class="sfPrice">根据（中华人民共和国进境物品完税价格表），本商品适用税为<b>${goods.tax_rate?if_exists}</b>%</p>
                </td>
                </tr>
                <#if  goods.is_limit=='0'>
                <tr><th>每人限购 </th><td><span class="pPrice">${goods.limit_num?if_exists}件</span></td></tr>
                </#if>
                <#if sale_names != "">
                <tr>
                   <th>促销信息</th>
                   <td>
                   <b class="salesb">${sale_names?if_exists}</b>
                   <#if fg_ids != "">
                   
                   <#assign freegoodsList=stack.findValue("@com.rbt.function.FreegoodsFuc@getList('${fg_ids?if_exists}')")>
                    <#list freegoodsList as freegoods>
                      <img align="center" src="${freegoods.img_path?if_exists}" title="${freegoods.fg_name?if_exists}" width="35px;" height="35px;"/> &nbsp;
                    </#list>
                   </#if>
                   <#if limit_time =="1">
                   <#if fg_ids != "">
                   <p class="sfPrice">
                   </#if>
                     <@s.hidden id="difftime" name="difftime" value="${difftime?if_exists}"/>
                     <span id="ggleftTime" class="rtime">
                      <b>0</b>天<b>0</b>时<b>0</b>分<b>0</b>秒
                     </span>
                   <#if fg_ids != "">  
                   </p>  
                   </#if>
                   </#if>     
                   </td>
                 </tr>
                </#if>
                <!--<tr><th>赠送积分 </th><td><span class="pPrice">268</span></td></tr>-->
                <#if (goods.is_ship)?if_exists==1>
                <tr>
                <th>发货地</th>
                <td>
                      ${ship_addr?if_exists}
                </td>
                </tr>
                </#if>
                <tr><th>销售量</th><td><span class="porange"><#if monthSaleNum?if_exists!=''>${monthSaleNum}<#else>0</#if></span></td></tr>
                <tr><th>评价</th><td><span class="pbule">${evalNum?if_exists}</span></td></tr>
                <#if goods.use_integral?if_exists =='1'>
                <tr><th>支持积分购买</th><td><a href="javascript:void(0)" onclick="integralBuy();" class="jfBuy">积分购买</a></td></tr>
                </#if>
                 <!--
              <#if getCouponByGoodsList?if_exists && getCouponByGoodsList.size() gt 0>
                <tr>
                  <th>领取优惠券</th>
                   <td>
                    <#list getCouponByGoodsList as getCouponByGoods>
                   		<a href="/mall-coupon_${(getCouponByGoods.coupon_id)?if_exists}.html" target="_blank" style="color:red;">
                   		${getCouponByGoods.coupon_name?if_exists}</a>
                    </#list>
                </td>
                </tr>
               </#if>
               -->
              </table>
          </div>          
          <!---->
          <div class="specifica">
           <h2 class="speh2" id="error_span">请选择您要的商品信息<span><img src="/malltemplate/bmall/images/areaClose.gif" onclick="closeSpan()"></span></h2>
            <table width="100%" cellpadding="0" cellspacing="0" class="spetab" id="spetabId">
              <#include "WEB-INF/template/bmall/default/goodsSpec.ftl">
               <tr>
                   <th width="18%">数量</th>
                   <td width="82%">
                        <div class="sptnum">
                            <input type="text" onkeyup="getShipPrice();checkBuyNum('1');" value="1" name="order_num_str"  class="numtext" id="buy_nums" />
                            <input type="button" class="numadd" onclick="add();">
                            <input type="button" class="numdec" onclick="sub();">
                           
                            <p  <#if (goods.depot_id)?if_exists=="1">style="display:none;"</#if>>(库存<#if total_stock?if_exists!=''><strong id="total_stock">${total_stock}</strong><#else>0</#if>件)</p>
                    
                            
                         </div>
                     </td>
                 </tr>
                 <tr>
                     <th width="18%"></th>
                     <td width="82%">
	                        <div class="sptbut">
	                            <#if (goods.active_state?if_exists)=="0" || activeId="">
	                                <#if (goods.is_virtual)?if_exists!="0">
	                                    <p class="cartbutp">
	                                        <#if total_stock?if_exists!='' &&total_stock?if_exists!="0">
	                                            <#if goods.is_limit?if_exists=='1'>
													<input type="button" class="buyNow" onclick="buyNow()">
												</#if>
												<input type="button" class="addCart" onclick="addCart(this);">

	                                            <#else>
	                                            <input type="button" class="notBut"/>
	                                            <input type="button" class="notCart"/>
	                                        </#if>
	                                    </p>
	                                    <#else>
	                                        <p class="virbuyp">
	                                            <#if total_stock?if_exists!='' &&total_stock?if_exists!="0">
	                                                <input type="button" class="buyNow" onclick="virBuyNow()">
	                                                <#else>
	                                                    <input type="button" class="buyNowno">
	                                            </#if>
	                                        </p>
	                                </#if>
	                                <#else>
	                                    <#if flag_active_state!=null>
	                                        <p class="cartbutp">
	                                            <#if total_stock?if_exists!='' && total_stock?if_exists!="0">
	                                                <input type="button" class="buyNow" onclick="buyNow()">
	                                                <#else>
	                                                    <input type="button" class="buyNowno">
	                                            </#if>
	                                        </p>
	                                        <#else>
	                                    </#if>
	                            </#if>
	                        </div>
                       </td>
                    </tr>
              </table>
        </div>
    </div>
    <!---->
     <div class="pw210">
         <div class="fgsmDiv">
              <p>根据（<a href="http://www.customs.gov.cn/publish/portal0/tab49576/info428591.htm" target="_blank" style="color: red;">海关总署公告2010年第43号公告规定</a>个人物品申报）应征进口税税额在人民币<b>50</b>元（含50元）以下的，海关予以免征
              </p>
        </div>
        <div class="proEwm">
        <div id="qrcode"></div>
        <@s.hidden value="${cfg_basehost}/webapp/goodsdetail/${goods.goods_id}.html" id="code_url"/>
        <p>扫一扫 手机浏览</p>
        </div>
        <div class="bdsharebuttonbox">
           <a href="#" class="bds_more" data-cmd="more"></a>
           <a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
           <a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博">
           </a><a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
           <a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
           <a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
        </div>
        <div class="scShop"><a href="javascript:void(0);" onclick="ginsertColl('0');">+ 收藏商品</a></div>
     </div>   
	   </div><!--pw625 end-->    
	    <br class="clear"/>
	     
	   </div><!--prodeMian end-->
	   
	</div>
	
<!--商品详细尾部-->
<div class="wpd1800">
   <!--推荐商品-->
   <div class="w220">
      <div class="rank">
       <#if (relateGoodsList?size>0)>
       <h2>相关商品</h2>
        <!----> 
         <div class="rankcont">
           <ul>
             <#list relateGoodsList as goods> 
             <li>
               <div><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html" target="_blank">
               <img src="<#if goods.list_img!=null>${(goods.list_img)?if_exists}<#else>${cfg_nopic?if_exists}</#if>" width="200px" height="200px" />
               </a></div>
                <p style="word-wrap:break-word;word-break:break-all;"><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html" target="_blank">${goods.goods_name?if_exists}</a></p>
                <p><span class="xsSpan">￥${goods.min_sale_price?if_exists}</span> <span class="scSpan">${goods.max_market_price?if_exists}</span></p>    
             </li>
             </#list>
           </ul>          
        </div>
      </#if>
       <h2>推荐商品</h2>
        <!----> 
         <div class="rankcont">
           <ul>
             <#list recomList as goods> 
             <li>
               <div><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html" target="_blank">
               <img src="<#if goods.list_img!=null>${(goods.list_img)?if_exists}<#else>${cfg_nopic?if_exists}</#if>" width="200px" height="200px" />
               </a></div>
                <p style="word-wrap:break-word;word-break:break-all;">
                <a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html" target="_blank" >
                ${goods.goods_name?if_exists}</a></p>
                <p><span class="xsSpan">￥${goods.min_sale_price?if_exists}</span> <span class="scSpan">${goods.max_market_price?if_exists}</span></p>    
             </li>
             </#list>
           </ul>          
        </div>
        
        
      </div> 
   </div>
   <!--商品详情-->
   <div class="pdbwDiv" id="pdbwDivId">
      <!---->
      <div class="prodh2">
         <ul>
           <li>图文详情</li>
           <li>产品参数</li>
           <li>商品评价(${evalNum?if_exists})</li>
           <li>成交记录(${monthSaleNum?if_exists})</li>
           <li>商品咨询</li>
           <li>品牌故事</li>
           <li>售后服务</li>
         </ul>
       </div>
       <!---->
       <div class="tabDiv">
          
         <!--图文详情-->
         <div class="pdbCont">
         
            <div class="tuwenDiv">
             <div style="text-align:center;">
                <#if (goods.goods_video)?if_exists!="">
                    <#if (goods.goods_video)?index_of('iframe') gt 0 ||(goods.goods_video)?index_of('embed') gt 0>
                      ${goods.goods_video?if_exists}
                       <#else>
                            <#include "WEB-INF/template/bmall/default/cuSunPlayer.ftl">
                     </#if>
                    
      		    </#if>
  		      </div>            
               ${(goods.goods_detail)?if_exists}
                <#if (goods.product_process)?if_exists&&(goods.product_process)!=''>
                    <#list ((goods.product_process))?if_exists?split( ",") as img>
                        <li>
                            <img src="${img?if_exists}" width="600px" >
                        </li>
                    </#list>
                </#if>
                <#if (goods.import_cert)?if_exists&&(goods.import_cert)!=''>
                    <#list ((goods.import_cert))?if_exists?split( ",") as img>
                        <li>
                            <img src="${img?if_exists}" width="600px"  >
                        </li>
                    </#list>
                </#if>
            </div>     
         </div>
         
         <!--产品参数-->
         <div class="pdbCont" >
         
           <div class="prodParameter" >
              <h2>基本参数</h2>
              <table width="100%" cellpadding="0" cellspacing="0" class="partab">
                <tr>
                  <th width="15%">商品编号</th>
                  <td width="35%">${goods.goods_no?if_exists}</td>
                  <th width="15%">外文名称</th>
                  <td width="35%"><#if goods.goods_en_name !="">${goods.goods_en_name?if_exists}<#else>-</#if></td>
                </tr>
                <tr>
                    <th>条形码</th>
                    <td><#if goods.bar_code !="">${goods.bar_code?if_exists}<#else>-</#if></td>
                    <th>品牌</th>
                    <td><#if brand_name !="">${brand_name?if_exists}<#else>-</#if></td>
                </tr>
                <tr>
                   <th>净含量</th>
                   <td><#if goods.weight !="">${goods.weight?if_exists}<#else>-</#if></td>
                   <th>保质期</th>
                   <td><#if goods.quality !="">${goods.quality?if_exists}<#else>-</#if></td>
                </tr>
                <tr>
                   <th>产地	</th>
                   <td><#if goods_place !="">${goods_place?if_exists}<#else>-</#if></td>
                   <th>生产商</th>
                   <td><#if goods.producer !="">${goods.producer?if_exists}<#else>-</#if></td>
                </tr>
                <tr>
                   <th>生产地址</th>
                   <td><#if goods.product_address !="">${goods.product_address?if_exists}<#else>-</#if></td>
                   <th>出口经销商</th>
                   <td><#if goods.export !="">${goods.export?if_exists}<#else>-</#if></td>
                </tr>
                <tr>
                    <th>海关已备案 </th>
                    <td><#if goods.is_customs !=""><img src="${goods.is_customs?if_exists}"/><#else>-</#if></td>
                    <th>商检经备案</th>
                    <td><#if goods.inspection !=""><img src="${goods.inspection?if_exists}"/><#else>-</#if></td>
                </tr>
              </table>
            </div>
	      <!--商品属性-->
	      <div class="prodParameter">
              <#if (selfextendattrList?size>0)>
                <h2>商品属性</h2>
                 <table width="100%" cellpadding="0" cellspacing="0" class="partab">
                        <#assign n= 0>
                        <#list selfextendattrList as selfExtendAttr>
                               <#assign n= n + 1>
                               <#if n%2 == 1>
                                <tr>
                                </#if>
                                    <th width="15%">
                                        ${(selfExtendAttr.ex_attr_alias)?if_exists}
                                    </th>
                                     <td width="35%">
                                    <#if (selfExtendAttr.ex_attr_value)?if_exists != "">
                                        ${selfExtendAttr.ex_attr_value}
                                    <#else>
                                        -
                                    </#if>
                                    </td>
                                <#if n%2 == 0>
                                </tr>
                                <#elseif n == "${selfextendattrList?size}">
                                </tr>
                               </#if>
                        </#list>
                    </table>
                </#if>
             </div> 
             
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
         
         <!--评价-->
         <div class="pdbCont" id="com_goodsdetai_nolazy2">
            
            <div class="evaluTop">
              <span><input type="radio" name="ev"  onclick="clickRadio('${goods.goods_id}','','')"/><b>全部评价</b></span>
              <span><input type="radio" name="ev"  onclick="clickRadio('${goods.goods_id}','1','')"/><b>好评</b>(${eval_one})</span>
              <span><input type="radio" name="ev"  onclick="clickRadio('${goods.goods_id}','0','')"/><b>中评</b>(${eval_two})</span>
              <span><input type="radio" name="ev"  onclick="clickRadio('${goods.goods_id}','-1','')"/><b>差评</b>(${eval_three})</span>
              <span><input type="radio" name="ev"  onclick="clickRadio('${goods.goods_id}','','1')"/><b>带图评论</b></span>

           </div>
             
           <div class="evaluMain" id="evaluMain"></div>
           
         </div>
         
         <!--成交记录-->
         <div class="pdbCont" id="com_goodsdetai_nolazy3">
           
            <div class="record" id="monthSale"></div>  
           
         </div>
         
         <!--商品咨询-->
         <div class="pdbCont" id="com_goodsdetai_nolazy4">
           
             <div class="evaluTop">
	            <#list commparaList as commpara>
	               <span><input name="c" type="radio" value="${(commpara.para_value)?if_exists}" onclick="clickConsult('${goods.goods_id}','${(commpara.para_value)?if_exists}')"/><b>${(commpara.para_key)?if_exists}</b></span>
	      		</#list>
	      		<span class="topChat">
	      		<a href="javascript:void(0)" style="font-weight:bold;vertical-align:middle;">
	      		<img src="/malltemplate/bmall/images/kfIcon.gif" style="vertical-align:middle;"  /> 在线咨询</a></span>
           </div>
             
           <div  class="evaluMain" id="consult"></div>
           
            <!--发表咨询-->
            <div class="consult">
               <h3>发布咨询</h3>
               <table width="100%" cellpadding="0" cellspacing="0">
                 <tr><th>声明：</th><td>您可在购买前对产品包装、颜色、运输、库存等方面进行咨询，我们有专人进行回复！因厂家随时会更改一些产品的包装、颜色、产地等参数，
                     所以该回复仅在当时对提问者有效，其他网友仅供参考！咨询回复的工作时间为：周一至周五，9:00至18:00，请耐心等待工作人员回复。</td></tr>
                                  <tr>
                 <th>咨询类型：</th>
                 <td>
                	<#list commparaList as commpara>
			      		<span><input type="radio" name="con_type" value="${(commpara.para_value)?if_exists}" onfocus="isLogin();"/>${(commpara.para_key)?if_exists}</span>
			      	</#list>
                </td>
                </tr>
                 <tr><th>咨询内容：</th><td><textarea class="conArea" id="c_content"  onblur="filterWord();" class="conArea messtarea"  onfocus="isLogin();" onkeyup="checkLength(this,150);" maxlength="150"></textarea></td></tr>
                 <tr><th>验证码：</th>
                 <td><input type="text" class="yzmText" maxlength="4" name="commentrand" id="rands" onfocus="isLogin();">
                 <img src="/imgrand.action" style="vertical-align:middle;" onclick="changeValidateCode(this)"/></td>
                 </tr>
                   <tr><th></th>
                  <td><input type="button"  class="conBut" value="提交" onclick="subGoodsConsult();"></td>
                 </tr>
               </table>
            </div>
            
         </div>

         <!--品牌故事-->
         <div class="pdbCont" id="com_goodsdetai_nolazy5">
         
            <div class="tuwenDiv">
               ${(goods.brand_store)?if_exists}
            </div>     
         </div>
         
         <!--售后服务-->
         <div class="pdbCont" id="com_goodsdetai_nolazy6">
         
            <div class="fwDiv">
               ${(aftersales.content)?if_exists}
            </div>     
         </div>         
         
       </div><!--tabDiv end-->
       
   </div>
   
   <div class="clear"></div>
   
</div>

<div class="clear"></div>
            
<!--尾部-->
<#include "/a/bmall/mallbottom.html">
    <!--加入购物车显示效果开始-->
<#include "WEB-INF/template/bmall/default/goodsCart.ftl">
<!-- 右侧购物车 -->
<#include "/WEB-INF/template/bmall/default/rightCart.ftl">
                        
                        
<!--地区插件弹出层-->
<div id="showship" style="display:none;">
	<span onclick="closearea();" class="shipclose"></span>
 	${carea_name?if_exists}
 	<div class="clear"></div>
 	<div class="twoarea">
 	</div>
</div>
        </@s.form>

</body>
	 <script>
	  window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"1","bdSize":"24"},"share":{},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"0"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
	</script>
   <script type="text/javascript">
	 $(document).ready(function(){
	  var code_url=$("#code_url").val();
	  $('#qrcode').qrcode({width:160,height:160,text:code_url});
	});
	</script>	   
</html>