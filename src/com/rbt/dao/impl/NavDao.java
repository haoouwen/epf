/*
 
 * Package:com.rbt.dao.impl
 * FileName: NavDao.java 
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
import com.rbt.dao.INavDao;
import com.rbt.model.Nav;

@Repository
public class NavDao extends GenericDao<Nav,String> implements INavDao {
	
	public NavDao() {
		super(Nav.class);
	}

}
