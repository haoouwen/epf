/*
 
 * Package:com.rbt.dao.impl
 * FileName: GroupgoodsDao.java 
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
import com.rbt.dao.IGroupgoodsDao;
import com.rbt.model.Groupgoods;

/**
 * @function 功能  团购商品信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Thu Mar 28 14:55:26 CST 2014
 */
@Repository
public class GroupgoodsDao extends GenericDao<Groupgoods,String> implements IGroupgoodsDao {
	
	public GroupgoodsDao() {
		super(Groupgoods.class);
	}
	/**
	 * @author:HXM
	 * @date:May 9, 20142:34:03 PM
	 * @param:
	 * @Description:修改资金冻结状态
	 */
	public void updateApply(Map map){
		this.getSqlMapClientTemplate().update("groupgoods.updateApply", map);
	}
}

