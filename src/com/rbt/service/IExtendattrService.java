/*
  
 
 * Package:com.rbt.servie
 * FileName: IExtend_attrService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Extendattr;

/**
 * @function 功能 记录商品扩展属性信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Tue Jan 15 10:51:04 CST 2014
 */

public interface IExtendattrService extends IGenericService<Extendattr,String>{
	
	public List getExAttrList(Map map);
	/**
	 * 方法描述：构造LIST用于信息的回选
	 * 
	 * @return
	 * @throws Exception
	 */
	public List cat_attr_list(String cat_ids,List cat_attr_list);
}

