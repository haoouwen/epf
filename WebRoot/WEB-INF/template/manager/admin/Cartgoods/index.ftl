<html>
  <head>
    <title>购物车列表</title>   
  </head>
  <body>
<@s.form action="/admin_Cartgoods_list.action" method="post">
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Cartgoods_add.action','');">添加购物车</a></li>
     <li class="sc"><a onclick="delInfo('cartgoods.trade_id','/admin_Cartgoods_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="updateRecomState('1','cartgoods.trade_id','/admin_Cartgoods_updateisrecom.action','admin_cartgoods_state');">推荐</a></li>
     <li class="jingyong"><a onclick="updateRecomState('0','cartgoods.trade_id','/admin_Cartgoods_updateisrecom.action','admin_cartgoods_state');">不推荐</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('cartgoods.trade_id')"/></td>
   
    
     	 <td width="10%"  align="center" class="top_td">trade_id</td>
    
     	 <td width="10%"  align="center" class="top_td">buy_num</td>
    
     	 <td width="10%"  align="center" class="top_td">cust_id</td>
    
     	 <td width="10%"  align="center" class="top_td">cookie_id</td>
    
     	 <td width="10%"  align="center" class="top_td">shop_name</td>
    
     	 <td width="10%"  align="center" class="top_td">shop_qq</td>
    
     	 <td width="10%"  align="center" class="top_td">user_name</td>
    
     	 <td width="10%"  align="center" class="top_td">goods_id</td>
    
     	 <td width="10%"  align="center" class="top_td">img_path</td>
    
     	 <td width="10%"  align="center" class="top_td">goods_name</td>
    
     	 <td width="10%"  align="center" class="top_td">spec_name</td>
    
     	 <td width="10%"  align="center" class="top_td">spec_id</td>
    
     	 <td width="10%"  align="center" class="top_td">integral</td>
    
     	 <td width="10%"  align="center" class="top_td">privilege_way</td>
    
     	 <td width="10%"  align="center" class="top_td">is_virtual</td>
    
     	 <td width="10%"  align="center" class="top_td">in_date</td>
    
    
    <td width="5%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list cartgoodsList as cartgoods>
  <tr>
    <td><input type="checkbox" name="cartgoods.trade_id" value="${cartgoods.trade_id?if_exists}"/></td>    
 	
    	<td align="center">${cartgoods.trade_id?if_exists}</td>
    
    	<td align="center">${cartgoods.buy_num?if_exists}</td>
    
    	<td align="center">${cartgoods.cust_id?if_exists}</td>
    
    	<td align="center">${cartgoods.cookie_id?if_exists}</td>
    
    	<td align="center">${cartgoods.shop_name?if_exists}</td>
    
    	<td align="center">${cartgoods.shop_qq?if_exists}</td>
    
    	<td align="center">${cartgoods.user_name?if_exists}</td>
    
    	<td align="center">${cartgoods.goods_id?if_exists}</td>
    
    	<td align="center">${cartgoods.img_path?if_exists}</td>
    
    	<td align="center">${cartgoods.goods_name?if_exists}</td>
    
    	<td align="center">${cartgoods.spec_name?if_exists}</td>
    
    	<td align="center">${cartgoods.spec_id?if_exists}</td>
    
    	<td align="center">${cartgoods.integral?if_exists}</td>
    
    	<td align="center">${cartgoods.privilege_way?if_exists}</td>
    
    	<td align="center">${cartgoods.is_virtual?if_exists}</td>
    
    	<td align="center">${cartgoods.in_date?if_exists}</td>
          
    <td align="center"><a onclick="linkToInfo('/admin_Cartgoods_view.action','cartgoods.trade_id=${cartgoods.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
  </tr>
  </#list>
</table>
 </div>
 <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
</div>
</div>
<div class="clear"></div>


<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
		<tr>
		<td align="right">下载标题:</td> 
		<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
	</tr>
		<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('area_attr','cat_attr','search_area_attr','search_cat_attr');" />
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>

</@s.form>
</body>
</html>

