/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: TryapplyDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ITryapplyDao;
import com.rbt.model.Tryapply;

/**
 * @function 功能  申请试用dao层业务接口实现类
 * @author 创建人 CYC
 * @date 创建日期 Sat Jul 12 09:25:36 CST 2014
 */
@Repository
public class TryapplyDao extends GenericDao<Tryapply,String> implements ITryapplyDao {
	
	public TryapplyDao() {
		super(Tryapply.class);
	}
	
}

