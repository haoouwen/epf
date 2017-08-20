/*
 
 * Package:com.rbt.dao.impl
 * FileName: Extend_attrDao.java 
 */
package com.rbt.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.rbt.dao.IExtendattrDao;
import com.rbt.model.Extendattr;

/**
 * @function 功能  记录商品扩展属性信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Tue Jan 15 10:51:04 CST 2014
 */
@Repository
public class ExtendattrDao extends GenericDao<Extendattr,String> implements IExtendattrDao {
	
	public ExtendattrDao() {
		super(Extendattr.class);
	}
	
	public List getExAttrList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("extendattr.getexAttrList",map);
	}
}

