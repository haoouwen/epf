/*
  
 
 * Package:com.rbt.dao
 * FileName: ISelfsepcnameDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Selfspecname;

/**
 * @function 功能 自定义规格名称dao层业务接口
 * @author  创建人LJQ
 * @date  创建日期 Wed Jan 30 15:32:16 CST 2014
 */

public interface ISelfspecnameDao extends IGenericDao<Selfspecname,String>{
	public void deleteByGoodsid(String goods_id);
}

