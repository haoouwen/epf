/*
  
 
 * Package:com.rbt.dao
 * FileName: ISelfextendattrDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Selfextendattr;

/**
 * @function 功能 商品自定义属性dao层业务接口
 * @author  创建人LJQ
 * @date  创建日期 Sat Feb 16 10:59:08 CST 2014
 */

public interface ISelfextendattrDao extends IGenericDao<Selfextendattr,String>{
	public void deleteByGoodsid(String goods_id);
}

