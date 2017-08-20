/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: SelfsepcnameDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ISelfspecnameDao;
import com.rbt.model.Selfspecname;

/**
 * @function 功能  自定义规格名称dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Wed Jan 30 15:32:16 CST 2014
 */
@Repository
public class SelfspecnameDao extends GenericDao<Selfspecname,String> implements ISelfspecnameDao {
	
	public SelfspecnameDao() {
		super(Selfspecname.class);
	}
	
	/**
	 * @author : LJQ
	 * @date : Feb 19, 2014 10:52:44 AM
	 * @Method Description :根据商品ID删除信息
	 */
	public void deleteByGoodsid(String goods_id) {
		this.getSqlMapClientTemplate().delete("selfspecname.deleteByGoodsid", goods_id);
	}
}

