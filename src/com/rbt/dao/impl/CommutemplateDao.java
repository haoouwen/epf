/*
 
 * Package:com.rbt.dao.impl
 * FileName: CommutemplateDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ICommutemplateDao;
import com.rbt.model.Commutemplate;
import com.rbt.model.Shopconfig;

/**
 * @function 功能  记录会员发送模板信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Sat Dec 29 13:20:17 CST 2014
 */
@Repository
public class CommutemplateDao extends GenericDao<Commutemplate,String> implements ICommutemplateDao {
	
	public CommutemplateDao() {
		super(Commutemplate.class);
	}
	//通过temp_code获取Commutemplate的对象
	public Commutemplate getByTempcode(String temp_code) {
		return  (Commutemplate)this.getSqlMapClientTemplate().queryForObject("commutemplate.getByTempcode", temp_code);
	}
}

