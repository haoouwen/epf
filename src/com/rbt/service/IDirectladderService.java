/*
  
 
 * Package:com.rbt.servie
 * FileName: IDirectladderService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Directladder;
import com.rbt.model.Groupladder;

/**
 * @function 功能 预售商品阶梯价格Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Wed Jul 17 13:28:18 CST 2014
 */

public interface IDirectladderService extends IGenericService<Directladder,String>{
	public Directladder getByDirectID(String id);
	public void deleteDirectID(String id);
}

