/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: SendboxDao.java 
 */
package com.rbt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ISendboxDao;
import com.rbt.model.Sendbox;

/**
 * @function 功能  记录发件箱信息dao层业务接口实现类
 * @author 创建人 LSQ
 * @date 创建日期 Wed Jan 30 15:36:28 CST 2014
 */
@Repository
public class SendboxDao extends GenericDao<Sendbox,String> implements ISendboxDao {
	
	public SendboxDao() {
		super(Sendbox.class);
	}
	
	
	public void deletelogic(HashMap map) {
		this.getSqlMapClientTemplate().delete("sendbox.deletelogic", map);
	}


	public List getDelSend() {
		return this.getSqlMapClientTemplate().queryForList("sendbox.getDelList");
	}


}

