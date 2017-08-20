/*
 
 * Package:com.rbt.dao.impl
 * FileName: MsgcheckDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IMsgcheckDao;
import com.rbt.model.Msgcheck;

/**
 * @function 功能  记录商城的活动管理dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Wed Oct 10 16:38:35 CST 2014
 */
@Repository
public class MsgcheckDao extends GenericDao<Msgcheck,String> implements IMsgcheckDao {
	
	public MsgcheckDao() {
		super(Msgcheck.class);
	}
	
	public void deleteMsgcheck(Map map) {
		this.getSqlMapClientTemplate().delete("msgcheck.deleteMsgcheck", map);
	}
	public void deleteById(String id) {
		this.getSqlMapClientTemplate().delete("msgcheck.deleteById",id);
	}
    public void updateUse(Map map){
    	this.getSqlMapClientTemplate().update("msgcheck.updateUse",map);
    }


}

