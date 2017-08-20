<!--相关商品-->
<#if relateGoodsList?if_exists!=null && relateGoodsList?size gt 0>
       <div class="relatedGoods">
         <h2>相关商品</h2>
         <ul>
			<#list relateGoodsList as relateGoods>
				<li>
					<div>
						<a href="/mall-goodsdetail-${relateGoods.goods_id?if_exists}.html" target="_blank">
							<#if relateGoods.list_img!=null && relateGoods.list_img!=''>
								<img src="${relateGoods.list_img?if_exists}">
							<#else>
								<img src="${cfg_nopic?if_exists}">
							</#if>
						</a>
					</div>
					<p>
						<a href="/mall-goodsdetail-${relateGoods.goods_id?if_exists}.html" target="_blank">
							<#if (relateGoods.goods_name)?if_exists?length lt 10>
		           				${relateGoods.goods_name}
		           			<#else>
		           				${relateGoods.goods_name[0..9]}...
		           			</#if>
						</a>
					</p>
					<p>
						<b class="red">
						<#if relateGoods.min_sale_price !=relateGoods.max_sale_price>
							￥${(relateGoods.min_sale_price)?if_exists}-￥${(relateGoods.max_sale_price)?if_exists}
						<#else>
							￥${(relateGoods.min_sale_price)?if_exists}
						</#if>
						</b>
					</p>
				</li>
			</#list>
         </ul>
        <br class="clear"/>
       </div>
</#if>