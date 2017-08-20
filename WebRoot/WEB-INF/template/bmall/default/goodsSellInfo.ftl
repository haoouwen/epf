          <!--保障-->
          <ul class="bzul">
            <li class="zpli"><a href="#">正品保障</a></li>
            <li class="fpli"><a href="#">提供发票</a></li>
            <li class="tkli"><a href="#">无忧退款</a></li>
            <li class="dbli"><a href="#">担保服务</a></li>
          </ul>
          <br class="clear"/>
          <!--评分-->
          <ul class="pful">
            <li><strong>店铺动态评分</strong><span class="thspan">与同行业相比</span></li>
            <#list (directSellMap.scoreList)?if_exists as score>
	                 <li>描述相符：
		                 <strong class="blue">
			                 <#if score.desc_score?length lt 4>
			                 ${score.desc_score?string("0.00")}
			                 <#else>
			                 ${score.desc_score?string("0.00")}
			                 </#if>
		                 </strong>
		                 <#if score.ml_desc?if_exists=='1'>
		                 	<b title="与同行业平均分的比较">高于</b>
		                 <#else>
		                 	<b title="与同行业平均分的比较" class="bdy">低于</b>
		                 </#if>
		                 <strong <#if score.ml_desc?if_exists=='1'>class="red"<#else>class="green"</#if> >
			                 <#if score.desc_score?length lt 4>
			                		${score.desc_sub?string("0.00")}
			                 <#else>
			                		${score.desc_sub?string("0.00")}
			                 </#if>
		                 </strong>
	                 </li>
	                 
		            <li>服务态度：
			            <strong class="blue">
			            <#if score.service_score.length lt 4>
		                 ${score.service_score?string("0.00")}
		                 <#else>
		                 ${score.service_score?string("0.00")}
		                 </#if>
			            </strong>
			             <#if score.ml_service?if_exists=='1'>
		                 	<b title="与同行业平均分的比较">高于</b>
		                 <#else>
		                 	<b title="与同行业平均分的比较" class="bdy">低于</b>
		                 </#if>
			            <strong <#if score.ml_service?if_exists=='1'>class="red"<#else>class="green"</#if> >
							 <#if score.service_sub?length lt 4>
			                		${score.service_sub?string("0.00")}
			                 <#else>
			                		${score.service_sub?string("0.00")}
			                 </#if>
						</strong>
		            </li>
		            
		            <li>发货速度：
			            <strong class="blue">
				            <#if score.delivery_score.length lt 4>
			                 ${score.delivery_score?string("0.00")}
			                 <#else>
			                 ${score.delivery_score?string("0.00")}
			                 </#if>
			            </strong>
			            <#if score.ml_delivery?if_exists=='1'>
		                 	<b title="与同行业平均分的比较">高于</b>
		                 <#else>
		                 	<b title="与同行业平均分的比较" class="bdy">低于</b>
		                 </#if>
			            
			            <strong <#if score.ml_delivery?if_exists=='1'>class="red"<#else>class="green"</#if> >
							 <#if score.delivery_sub?length lt 4>
				                	${score.delivery_sub?string("0.00")}
			                 <#else>
			                		${score.delivery_sub?string("0.00")}
			                 </#if>
						</strong>
					</li>
	              </#list>
          </ul>
          <br class="clear"/>
          <!--介绍-->
          <table class="sitable" width="100%" cellspacing="0">
            <tr><td width="35%">店铺名称：</td><td width="65%">${(shopconfig.shop_name)?if_exists}</td></tr>
            <tr><td>所&nbsp;在&nbsp;地：</td><td>${ship_addr?if_exists}</td></tr>
            <tr><td>具体地址：</td><td><#if (shopconfig.address)?if_exists?length gt 8>${shopconfig.address[0..7]}<#else>${(shopconfig.address)?if_exists}</#if></td></tr>
            <tr><td>电&nbsp;&nbsp;&nbsp;&nbsp;话：</td><td>${(shopconfig.phone)?if_exists}</td></tr>
            <tr><td>营业时间：</td><td>${(shopconfig.sale_time)?if_exists}</td></tr>
            <#if (shopconfig.qq)?if_exists==null && (shopconfig.qq)?if_exists=="">
            <#else>
            <tr><td>在线客服：</td><td><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${(shopconfig.qq)?if_exists}&site=qq&menu=yes" target="_blank"><img src="/shoptemplate/default/images/qq_03.gif"></a></td></tr>
            </#if>
          </table>
          <p class="scshop"><a href="/shopsIndex.action?radom_no=${shopconfig.radom_no?if_exists}"><img src="/shoptemplate/default/images/jrshop_07.gif"></a></p>
          