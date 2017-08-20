/*
 
 * Package:com.rbt.dao.impl
 * FileName: AttrvalueDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.IAttrvalueDao;
import com.rbt.model.Attrvalue;

/**
 * @function 功能  记录分类属性的值dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Tue Aug 21 15:25:18 CST 2014
 */
@Repository
public class AttrvalueDao extends GenericDao<Attrvalue,String> implements IAttrvalueDao {
	
	public AttrvalueDao() {
		super(Attrvalue.class);
	}
	
	public void deleteByattrid(String id) {
		this.getSqlMapClientTemplate().delete("attrvalue.deleteByattrid", id);
	}
}

