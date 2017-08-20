/*
  
 
 * Package:com.rbt.function
 * FileName: AdvinfoFuc.java 
 */
package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.model.Advpos;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.IAdvposService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.ITryapplyService;

/**
 * @function 功能  前台广告显示
 * @author  创建人 HXK
 * @date  创建日期  2014-09-22
 */
public class TryapplyFuc extends CreateSpringContext{
	
	//取广告位信息
	public static String getStatus(String oder_id){
		ITryapplyService tryapplyService = (ITryapplyService)getContext().getBean("tryapplyService");
		String status="";
		Map map=new HashMap();
		map.put("oder_id", oder_id);
		List list=tryapplyService.getList(map);
		if(list!=null&&list.size()>0){
			HashMap tryMap=(HashMap)list.get(0);
			status=tryMap.get("status").toString();
		}
		return status;
	}
	
	/**
	 * 获取申请的数量
	 * @param try_id
	 * @return
	 */
	public static int getTrygoods(String goods_id){
		IGoodsorderService goodsorderService =(IGoodsorderService)getContext().getBean("goodsorderService");
		Map map=new HashMap();
		map.put("order_state", "2,3,4,5,6,7,8");
		map.put("order_type", "7");
		map.put("goods_id", goods_id);
		return goodsorderService.getCount(map);
	}
	
}