/*
 
 * Package:com.rbt.dao.impl
 * FileName: CategoryattrDao.java 
 */
package com.rbt.dao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.rbt.dao.ICategoryattrDao;
import com.rbt.model.Categoryattr;

/**
 * @function 功能  产品属性列表dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Tue Jul 19 08:48:08 CST 2014
 */
@Repository
public class CategoryattrDao extends GenericDao<Categoryattr,String> implements ICategoryattrDao {

	public CategoryattrDao() {
		super(Categoryattr.class);
	}
	
	public List getCatAttrList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("categoryattr.getCatAttrList",map);
	}

	
	public void deleteAttr_id(String id) {
		this.getSqlMapClientTemplate().delete(getModelName()+".deleteByAttr_id", id);
	}
}

