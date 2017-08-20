/*
  
 
 * Package:com.rbt.dao.Impl
 * FileName: RoleDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.IRoleDao;
import com.rbt.model.Role;

/**
 * @function 功能 添加角色业务实现层类
 * @author 创建人 LJQ
 * @date 创建日期 Jun 28, 2014 3:31:34 PM
 */
@Repository
public class RoleDao extends GenericDao<Role,String> implements IRoleDao {

	public RoleDao() {
		super(Role.class);
	}
	
}
