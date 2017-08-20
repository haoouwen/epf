 <!--宝贝排行-->
      <div class="ranking">
        <h2>宝贝排行</h2>
        <div class="rankcont">
           <ul class="ranktableul">
             <li class="rankbut" id="rankbut1"  onmousedown="re_show(1,2,'rankbut','rankcontulId','rankbut','')">宝贝热销排行</li>
             <li id="rankbut2"  onmousedown="re_show(2,2,'rankbut','rankcontulId','rankbut','')">热门收藏排行</li>
           </ul>
 
           <!--热门销售排行-->
            <div class="grank" id="rankcontulId1">
           
             <dl id="grankidf">
             	<#list hotsaleList as goods>
                <dt><span>${goods_index+1?if_exists}</span><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html">${goods.goods_name?if_exists}</a></dt>
                <dd><div><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html"><img src="${goods.list_img?if_exists}" width="150px;" height="160px;"></a></div><span class="ddlprice">￥${goods.sale_price?if_exists}</span><span class="ddfsell">已售：<strong class="orange">${goods.sale_num?if_exists}</strong>件</span><br class="clear"/></dd>
                 </#list>              
             </dl>
             
           </div>
           <!--热门收藏排行-->
           <div class="grank" id="rankcontulId2" style="display:none">
           
             <dl id="grankids">
             	<#list collectList as goods>
                <dt><span>${goods_index+1?if_exists}</span><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html">${goods.goods_name?if_exists}</a></dt>
                <dd><div><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html"><img src="${goods.list_img?if_exists}" width="150px;" height="160px;"></a></div><span class="ddlprice">￥${goods.sale_price?if_exists}</span><span class="ddfsc">收藏：${goods.collect_num?if_exists}人</span><br class="clear"/></dd>
   				</#list>                                   
             </dl>   
           
           </div>
        </div>
      </div>   