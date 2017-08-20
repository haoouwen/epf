/*
 
 * Package:com.rbt.dao.impl
 * FileName: FundrechargeDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.IFundrechargeDao;
import com.rbt.model.Fundrecharge;

/**
 * @function 功能  会员资金充值记录dao层业务接口实现类
 * @author 创建人 CYC
 * @date 创建日期 Tue Jul 12 13:10:48 CST 2014
 */
@Repository
public class FundrechargeDao extends GenericDao<Fundrecharge,String> implements IFundrechargeDao {

	public FundrechargeDao() {
		super(Fundrecharge.class);
	}

	public Fundrecharge getByOrderId(String order_id) throws Exception {
		return (Fundrecharge)this.getSqlMapClientTemplate().queryForObject("fundrecharge.getByOrderId", order_id);
	}
	
	public Fundrecharge getByTrxid(String trxid)throws Exception{
		return (Fundrecharge)this.getSqlMapClientTemplate().queryForObject("fundrecharge.getByTrxid", trxid);	
	}

}

