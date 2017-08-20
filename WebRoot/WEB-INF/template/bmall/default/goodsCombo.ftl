<!--套餐商品-->
<#if comboList?if_exists!=null && comboList?size gt 0>
       <div class="relatedPro">
       	 <#assign index=1>
		 <#list comboList as combo>
		 	<p class='relate_p'>搭配套餐${index?if_exists}-<span class="p_combo_name">${combo.combo_name}</span></p>
		 	<#assign index=index+1>
			<ul>
				<#list comboGoodsList as cgl>
					<#if goods.goods_id = cgl.goods_id>
						<li>
							<div>
								<a href="/mall-goodsdetail-${(cgl.goods_id)?if_exists}.html" target="_blank">
									<#if cgl.list_img!=null && cgl.list_img!=''>
										<img src="${cgl.list_img?if_exists}">
									<#else>
										<img src="${cfg_nopic?if_exists}">
									</#if>
								</a>
							</div>
							<p>
								<a href="/mall-goodsdetail-${(cgl.goods_id)?if_exists}.html" target="_blank">
									<#if (cgl.goods_name)?if_exists?length lt 5>
				           				${cgl.goods_name}
				           			<#else>
				           				${cgl.goods_name[0..4]}...
				           			</#if>
								</a>
							</p>
							<p>
								<span class="combooldprice">原价：${(cgl.min_sale_price)?if_exists}</span>
							</p>
						</li>
					</#if>
				</#list>
				<#list comboGoodsList as cgl>
					<#if combo.goods_str?index_of("${cgl.goods_id?if_exists}") gt -1>
						<#if goods.goods_id != cgl.goods_id >
							<li class="comboplus"></li>
							<li>
								<div>
									<a href="/mall-goodsdetail-${(cgl.goods_id)?if_exists}.html" target="_blank">
										<#if cgl.list_img!=null && cgl.list_img!=''>
											<img src="${cgl.list_img?if_exists}">
										<#else>
											<img src="${cfg_nopic?if_exists}">
										</#if>
									</a>
								</div>
								<p>
									<a href="/mall-goodsdetail-${(cgl.goods_id)?if_exists}.html" target="_blank">
										<#if (cgl.goods_name)?if_exists?length lt 5>
					           				${cgl.goods_name}
					           			<#else>
					           				${cgl.goods_name[0..4]}...
					           			</#if>
									</a>
								</p>
								<p>
									<span class="combooldprice">原价：${(cgl.min_sale_price)?if_exists}</span>
								</p>
							</li>
						</#if>
					</#if>
				</#list>
				<li class="combopoint"></li>
				<li class="comboorder">
					<p><b>套餐价格：</b> <span class="comboprice">${(combo.combo_price)?if_exists}</span></p>
					<p><a target="_blank" class="lookcombo" href="/mall-combodetail-${(combo.trade_id)?if_exists}.html">查看套餐</a></p>
				</li>
			</ul>
			<br class="clear"/>
		 </#list>
         <br class="clear"/>
       </div>
</#if>