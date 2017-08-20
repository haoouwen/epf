/*
  
 
 * Package:com.rbt.servie
 * FileName: ISelfextendattrService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Selfextendattr;

/**
 * @function 功能 商品自定义属性Service层业务接口实现类
 * @author  创建人 LJQ
 * @date  创建日期 Sat Feb 16 10:59:08 CST 2014
 */

public interface ISelfextendattrService extends IGenericService<Selfextendattr,String>{
	public void deleteByGoodsid(String goods_is);
}

