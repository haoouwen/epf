<!--宝贝分类-->
      <div class="deproclass">
        <h2>宝贝分类</h2>
        <div class="deprocont">
          <p class="ckpro"><a href="#">查看所有宝贝分类>></a></p>
          <p class="ckproa"><a style= "cursor:pointer;" onclick="goodsactionbmall('sales','')">按销量</a>
          <a style= "cursor:pointer;" onclick="goodsactionbmall('newgoods','')">按新品</a>
          <a style= "cursor:pointer;" onclick="goodsactionbmall('price_asc','')">按价格</a>
          <a style= "cursor:pointer;" onclick="goodsactionbmall('collect','')">按收藏</a><p>
          <ul>
          <#list membercatList as membercar>
	            <#if membercar.parent_cat_id =='1111111111'>
	             <li><p><a style= "cursor:pointer;" onclick="catsearch(${membercar.cat_id?if_exists});">${membercar.cat_name?if_exists}</a></p>
	                <ul>
	                  <#list membercatList as membertwocar>
		          			<#if membertwocar.parent_cat_id == membercar.cat_id>
		                  	<li><a style= "cursor:pointer;" onclick="catsearch(${membercar.cat_id?if_exists});">${membertwocar.cat_name?if_exists}</a></li>
		                  	</#if>
	                  </#list>
	                </ul>
	             </li>
	             </#if>
            </#list>
          </ul>
        </div>
      </div>
