/*
 
 * Package:com.rbt.dao.impl
 * FileName: MalllevelsetDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IMalllevelsetDao;
import com.rbt.model.Malllevelset;

/**
 * @function 功能  记录商城会员等级信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Fri Dec 28 14:46:47 CST 2014
 */
@Repository
public class MalllevelsetDao extends GenericDao<Malllevelset,String> implements IMalllevelsetDao {
	
	public MalllevelsetDao() {
		super(Malllevelset.class);
	}
	
}

