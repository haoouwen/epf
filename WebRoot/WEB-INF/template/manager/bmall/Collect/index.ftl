<html>
<head>
	<title>商品收藏</title>
</head>
<body>
<@s.form action="/bmall_Collect_list.action" method="post" id="indexForm">

<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   		<h2>
   		     <span>我的收藏</span>
   		</h2> 
   		
   		<div class="tabIDiv">
			<input type="button" class="siBut" value="商品收藏" onclick="linkToInfo('/bmall_Collect_list.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')"/>
			<input type="button" class="niBut" value="品牌收藏" onclick="linkToInfo('/bmall_Collect_brandlist.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')"/>
	    </div>
	    
        <div class="usedTDiv">
           <table width="100%" cellpadding="0" cellspacing="0" class="usedTab">
              <tr><th width="10%"><input type="checkbox" id="checkall" onclick="selectAllBoxs('checkall','collect.coll_id')">全选</th><th width="45%">商品信息</th><th width="10%">收藏时间</th><th width="10%">价格(元)</th><th width="10%">库存(件)</th><th width="15%">操作</th></tr>
              <#list collectList as collect>
              <tr><td><input type="checkbox" name="collect.coll_id" value="${collect.coll_id?if_exists}"></td>
                  <td class="lUsedTd">
                     <div class="lUsedImg"><a target="_blank" href="/mall-goodsdetail-${collect.goods_id?if_exists}.html">
                     <img src="${collect.list_img?if_exists}" width="80" height="80"></a></div>
                     <div class="lUsedDiv attDiv">
                          <p><a target="_blank" href="/mall-goodsdetail-${collect.goods_id?if_exists}.html">${collect.goods_name?if_exists}</a></p>
                          <p><img src="/include/bmember/images/evafi.gif" align="absmiddle"><i>${collect.evalcount}评价</i></p>
                     </div>
                  </td>
                  <td><span class="graySpan">${collect.in_date?if_exists}<br/></span></td>
                  <td><b>￥${collect.sale_price?if_exists}</b></td>
                  <td>${collect.total_stock?if_exists}</td>
                  <td><p><a href="###" class="bluea" onclick="delB2cOneInfo('/bmall_Collect_del.action','collect.coll_id=${collect.coll_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')">取消收藏</a></p>
                  <p><a href="###" class="addCart" onclick="addCart('${collect.goods_id?if_exists}','${collect.sale_price?if_exists}')"></a></p></td>
              </tr>
             </#list> 
           </table>
           
        </div>
        <!---->
        <div class="selUsed">
          <span><input type="checkbox" id="checkAll" onclick="selectAllBoxs('checkAll','collect.coll_id')">全选</span><input type="button" class="graybut" value="取消收藏" onclick="delB2cInfo('/bmall_Collect_delete.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}','collect.coll_id')">
       </div>
        <!--翻页控件-->
        <div class="listbottom">${pageBar?if_exists}</div>
          
   </div>
   
  </div>
  <div class="clear"></div>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>    
</@s.form>
</body>
</html>




