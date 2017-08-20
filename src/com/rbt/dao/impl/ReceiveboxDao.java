/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: ReceiveboxDao.java 
 */
package com.rbt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IReceiveboxDao;
import com.rbt.model.Receivebox;

/**
 * @function 功能  记录收件箱信息dao层业务接口实现类
 * @author 创建人 LSQ
 * @date 创建日期 Wed Jan 30 15:37:25 CST 2014
 */
@Repository
public class ReceiveboxDao extends GenericDao<Receivebox,String> implements IReceiveboxDao {
	
	public ReceiveboxDao() {
		super(Receivebox.class);
	}
	
	
	public void deletelogic(HashMap map) {
		this.getSqlMapClientTemplate().delete("receivebox.deletelogic", map);
	}
	
}

