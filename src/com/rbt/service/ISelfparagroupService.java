/*
  
 
 * Package:com.rbt.servie
 * FileName: ISelfparagroupService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Selfparagroup;

/**
 * @function 功能 记录自定义参数组信息Service层业务接口实现类
 * @author  创建人 LJQ
 * @date  创建日期 Sat Feb 16 13:36:38 CST 2014
 */

public interface ISelfparagroupService extends IGenericService<Selfparagroup,String>{
	public void deleteByGoodsid(String goods_id);
}

