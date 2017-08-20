/*
 
 * Package:com.rbt.dao.impl
 * FileName: MembercreditDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IMembercreditDao;
import com.rbt.model.Membercredit;

/**
 * @function 功能  会员信誉dao层业务接口实现类
 * @author 创建人 XBY
 * @date 创建日期 Tue Apr 22 19:59:28 CST 2014
 */
@Repository
public class MembercreditDao extends GenericDao<Membercredit,String> implements IMembercreditDao {
	
	public MembercreditDao() {
		super(Membercredit.class);
	}
	
	/**
	 * 通过UserId获取会员信誉
	 * @param membercredit
	 * @return
	 */
	public Membercredit getByCustId(String cust_id){
		return (Membercredit)this.getSqlMapClientTemplate().queryForObject("membercredit.getByCustId", cust_id);
	}
	
	/**
	 * 修改买家信誉
	 * @param membercredit
	 */
	public void updateBuyNum(Membercredit membercredit){
		this.getSqlMapClientTemplate().update("membercredit.updateBuyNum", membercredit);
	}
	
}

