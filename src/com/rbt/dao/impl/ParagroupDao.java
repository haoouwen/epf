/*
 
 * Package:com.rbt.dao.impl
 * FileName: ParagroupDao.java 
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
import com.rbt.dao.IParagroupDao;
import com.rbt.model.Paragroup;

/**
 * @function 功能  商品扩展属性信息dao层业务接口实现类
 * @author 创建人 LSQ
 * @date 创建日期 Wed Jan 16 10:14:11 CST 2014
 */
@Repository
public class ParagroupDao extends GenericDao<Paragroup,String> implements IParagroupDao {
	
	public ParagroupDao() {
		super(Paragroup.class);
	}
	
}

