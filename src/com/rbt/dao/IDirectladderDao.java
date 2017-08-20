/*
  
 
 * Package:com.rbt.dao
 * FileName: IDirectladderDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Directladder;
import com.rbt.model.Groupladder;

/**
 * @function 功能 预售商品阶梯价格dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Wed Jul 17 13:28:18 CST 2014
 */

public interface IDirectladderDao extends IGenericDao<Directladder,String>{
	Directladder getByDirectID(String direct_id);
	public void deleteDirectID(String id);
}

