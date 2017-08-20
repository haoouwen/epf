/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: SelfextendattrDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ISelfextendattrDao;
import com.rbt.model.Selfextendattr;

/**
 * @function 功能  商品自定义属性dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Sat Feb 16 10:59:08 CST 2014
 */
@Repository
public class SelfextendattrDao extends GenericDao<Selfextendattr,String> implements ISelfextendattrDao {
	
	public SelfextendattrDao() {
		super(Selfextendattr.class);
	}
	public void deleteByGoodsid(String goods_id) {
		this.getSqlMapClientTemplate().delete("selfextendattr.deleteByGoodsid", goods_id);
	}
}

