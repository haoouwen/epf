/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: SendshipmodeDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ISendshipmodeDao;
import com.rbt.model.Sendmode;
import com.rbt.model.Sendshipmode;

/**
 * @function 功能  快递方式dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Tue May 06 11:17:02 CST 2014
 */
@Repository
public class SendshipmodeDao extends GenericDao<Sendshipmode,String> implements ISendshipmodeDao {
	
	public SendshipmodeDao() {
		super(Sendshipmode.class);
	}
	public Sendshipmode getByEnname(String en_name) {
		return (Sendshipmode) this.getSqlMapClientTemplate().queryForObject("sendshipmode.getByEnname",en_name);
	}
}

