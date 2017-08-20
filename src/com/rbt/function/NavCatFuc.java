package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.rbt.service.ICatnavService;
/**
 * @function 功能 Tomcat服务器启动时加载分类列表
 * @author 创建人 HXK
 * @date 创建日期 Aug 10, 2015 2:05:57 PM
 */
public class NavCatFuc extends CreateSpringContext {

	
	public static HashMap catnavMap  = null;
	
	ICatnavService catnavService = (ICatnavService) getContext().getBean("catnavService");
	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:31:31 PM
	 * @Method Description :通过上级分类标识和分类类型找出分类信息
	 */
	@SuppressWarnings("unchecked")
	public static List getCatnavList(Map map) {
		return getCatObj().getList(map);
	}
	
	/**
	 * @Method Description :初始化分类BEAN
	 * @author: HXK
	 * @date : Aug 18, 2015 4:31:41 PM
	 * @param 
	 * @return ICatnavService
	 */
	public static ICatnavService getCatObj() {
		return (ICatnavService) getContext().getBean("catnavService");
	}

}