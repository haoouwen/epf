package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.service.ILinkService;

public class LinkFuc extends CreateSpringContext{
	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 4:05:27 PM
	 * @Method Description :通过地区ID查询出相关的友情链接
	 */
	public static List<Map<String,String>> getLinkList(String area_id){
		Map<String,String> linkMap = new HashMap<String,String>();
		linkMap.put("area_attr", area_id);
		linkMap.put("limit", "18");
		linkMap.put("start", "0");	
		return getLinkObj().getList(linkMap);
	}
	
	//从Spring容器中获取招聘业务Bean
	public static ILinkService getLinkObj(){
		return (ILinkService)getContext().getBean("linkService");
	}
}
