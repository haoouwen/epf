/*
  
 
 * Package:com.rbt.dao
 * FileName: IComboDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Combo;

/**
 * @function 功能 套餐表dao层业务接口
 * @author  创建人LHY
 * @date  创建日期 Mon Mar 25 15:09:17 CST 2014
 */

public interface IComboDao extends IGenericDao<Combo,String>{
	public String getComboGoodsid(String goods_id);
}

