<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.rbt.function.GoodsSpreadFuc"%>
<%
	//热门商品列表
	List spreadList = GoodsSpreadFuc.getGoodsSpreadList("1" , false);
	//定义变量
	String goods_id = "",slogan = "",list_img = "",sale_price = "",market_price = "";
	double discounts=0;
	if(spreadList != null && spreadList.size() > 0){
		//循环取出推广商品
		%>
			var html = "";
		<%
		for(int i = 0; i < spreadList.size(); i++){
			HashMap spreadMap = new HashMap();
			spreadMap = (HashMap) spreadList.get(i);
			if (spreadMap.get("goods_id") != null) {
				goods_id = spreadMap.get("goods_id").toString();
			}
			if (spreadMap.get("slogan") != null) {
				slogan = spreadMap.get("slogan").toString();
				if(slogan.length() > 23){
					slogan = slogan.substring(0,23)+"...";
				}
			}
			if (spreadMap.get("spread_img") != null) {
				list_img = spreadMap.get("spread_img").toString();
			}
		    if (spreadMap.get("discounts") != null) {
				discounts = Double.parseDouble(spreadMap.get("discounts").toString());
			}
			if (spreadMap.get("sale_price") != null) {
				sale_price = spreadMap.get("sale_price").toString();
			}
			if (spreadMap.get("market_price") != null) {
				market_price = spreadMap.get("market_price").toString();
			}
			%>
				html += '<li><%if(discounts!=10){ %><div class="discount"><b><%=discounts %></b>折</div><div><%}%><a href="/mall-goodsdetail-<%=goods_id %>.html"><img src="<%=list_img %>"></a></div><p><a href="/mall-goodsdetail-<%=goods_id %>.html"><%=slogan %></a></p><p><strong class="f_left red">￥<%=sale_price %></strong><strong class="price">￥<%=market_price %></strong></p></li>';
				document.getElementById("example").innerHTML = html; 
			<%
		}
	}
%>

