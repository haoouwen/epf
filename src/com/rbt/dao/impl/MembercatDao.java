/*
 
 * Package:com.rbt.dao.impl
 * FileName: MembercatDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IMembercatDao;
import com.rbt.model.Membercat;

/**
 * @function 功能  记录会员自定义分类信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Fri Jan 11 09:28:06 CST 2014
 */
@Repository
public class MembercatDao extends GenericDao<Membercat,String> implements IMembercatDao {
	
	public MembercatDao() {
		super(Membercat.class);
	}

	public List getDeleteList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("membercat.getDeleteList",map);
	}
	public List getAll(){
		return this.getSqlMapClientTemplate().queryForList("membercat.getAll");
	}


}

