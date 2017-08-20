/*
  
 
 * Package:com.rbt.function
 * FileName: AdvinfoFuc.java 
 */
package com.rbt.function;

import java.util.HashMap;
import java.util.List;

import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Advpos;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.IAdvposService;
import com.rbt.service.IGoodsevalService;

/**
 * @function 功能  前台广告显示
 * @author  创建人 HXK
 * @date  创建日期  2014-09-22
 */
public class GoodsevalFuc extends CreateSpringContext{
	
	//根据广告位标识找出该广告位下的广告
	@SuppressWarnings("unchecked")
	public static String getTradeId(String goods_id,String order_id){
		IGoodsevalService goodsevalService = (IGoodsevalService)getContext().getBean("goodsevalService");
		HashMap map = new HashMap();
		map.put("goods_id", goods_id);
		if(!ValidateUtil.isRequired(goods_id)){
			map.put("goods_id", goods_id);
		}
		if(!ValidateUtil.isRequired(order_id)){
			map.put("order_id", order_id);
		}
		List list=goodsevalService.getList(map);
		String trade_id="";
	    if(list!=null && list.size()>0){
	    	HashMap evalMap=(HashMap)list.get(0);
	    	trade_id=evalMap.get("trade_id").toString();
	    }
		return trade_id;
	}
	
}