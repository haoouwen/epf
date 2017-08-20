/*
 
 * Package:com.rbt.dao.impl
 * FileName: GroupladderDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IGroupladderDao;
import com.rbt.model.Groupladder;
import com.rbt.model.Shopconfig;

/**
 * @function 功能  团购阶梯价格dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Mon Apr 15 17:26:40 CST 2014
 */
@Repository
public class GroupladderDao extends GenericDao<Groupladder,String> implements IGroupladderDao {
	
	public GroupladderDao() {
		super(Groupladder.class);
	}
	//通过group_id获取Groupladder的对象
	public Groupladder getByGroupID(String group_id) {
		return  (Groupladder)this.getSqlMapClientTemplate().queryForObject("groupladder.getByGroupId", group_id);
	}
	public void deleteGroupID(String id) {
		this.getSqlMapClientTemplate().delete("groupladder.deleteByGroupId",id);
	}
}

