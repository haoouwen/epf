/*
  
 
 * Package:com.rbt.function
 * FileName: AdvinfoFuc.java 
 */
package com.rbt.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.common.util.ValidateUtil;
import com.rbt.service.IFreegoodsService;

/**
 * @function 功能  前台广告显示
 * @author  创建人 HXK
 * @date  创建日期  2014-09-22
 */
public class FreegoodsFuc extends CreateSpringContext{
	
	//获取列表
	@SuppressWarnings("unchecked")
	public static List getList (String fg_ids){
        List list = new ArrayList();
		IFreegoodsService freegoodsService = (IFreegoodsService)getContext().getBean("freegoodsService");
		if(!ValidateUtil.isRequired(fg_ids)) {
	        Map map = new HashMap();
	        map.put("sgis", fg_ids);
	        list = freegoodsService.getList(map);
		}
		
        return list;
	}
	
}