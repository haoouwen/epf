/*
 
 * Package:com.rbt.dao.impl
 * FileName: LinkDao.java 
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
import com.rbt.dao.ILinkDao;
import com.rbt.model.Link;

@Repository
public class LinkDao extends GenericDao<Link,String> implements ILinkDao {
	
	public LinkDao() {
		super(Link.class);
	}
	
}
