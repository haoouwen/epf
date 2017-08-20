/*
  
 
 * Package:com.rbt.servie
 * FileName: IAutoupgoodsService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Autoupgoods;

/**
 * @function 功能 记录商品上下架管理信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Fri Feb 01 10:46:02 CST 2014
 */

public interface IAutoupgoodsService extends IGenericService<Autoupgoods,String>{
	public void deleteByGoodsId(String id);
}

