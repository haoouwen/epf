package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.rbt.service.IGoodsspreadService;


public class GoodsSpreadFuc extends CreateSpringContext{
	/**	
	 * @author : WXP
	 * @param :position 推广位置 area_filter 是否开启地区过滤
	 * @date Mar 25, 2014 9:42:07 AM
	 * @Method Description :根据获取相应位置的商品推广
	 */
	public static List getGoodsSpreadList(String position , boolean area_filter){
		HttpServletRequest request = ServletActionContext.getRequest();
		IGoodsspreadService goodsspreadService = (IGoodsspreadService)getContext().getBean("goodsspreadService");
		Map map = new HashMap();
		//获取客户端所在地区id
		if(area_filter){
			String area_id = AreaFuc.getAreaidByIpaddr(request);
			map.put("area_attr", area_id);
		}
		map.put("spread_position", position);
		map.put("in_time", "in_time");//在推广时间范围之内
		List list = goodsspreadService.getList(map);
		return list;
	}
}