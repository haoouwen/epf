package com.rbt.function;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.rbt.service.ICartgoodsService;
import com.rbt.service.IGoodsService;

public class CartcounteFuc extends CreateSpringContext{
	//购物车数量
	public static int getCartCount() throws UnsupportedEncodingException{
		ICartgoodsService cartgoodsService=(ICartgoodsService) getContext().getBean("cartgoodsService");
		IGoodsService goodsService=(IGoodsService) getContext().getBean("goodsService");
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int car_num_count = 0;
		
		
		Map map = new HashMap();
		
		// 获取购物车cookie
		Cookie[] cookies = request.getCookies();
		//购物车处理
		goodsService.dealCart(cookies,session.getAttribute("cust_id").toString());		
		map.put("cust_id",session.getAttribute("cust_id"));
		List cartList =cartgoodsService.getList(map);
		car_num_count=cartList.size();
		return car_num_count;
	}

}
