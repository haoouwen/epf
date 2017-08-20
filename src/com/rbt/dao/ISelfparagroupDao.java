/*
  
 
 * Package:com.rbt.dao
 * FileName: ISelfparagroupDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Selfparagroup;

/**
 * @function 功能 记录自定义参数组信息dao层业务接口
 * @author  创建人LJQ
 * @date  创建日期 Sat Feb 16 13:36:38 CST 2014
 */

public interface ISelfparagroupDao extends IGenericDao<Selfparagroup,String>{
	public void deleteByGoodsid(String goods_id);
}

