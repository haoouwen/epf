/*
 
 * Package:com.rbt.dao.impl
 * FileName: MemberinterDao.java 
 */
package com.rbt.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.rbt.dao.IInterhistoryDao;
import com.rbt.dao.IMemberinterDao;
import com.rbt.model.Interhistory;
import com.rbt.model.Memberinter;

/**
 * @function 功能  记录会员积分数dao层业务接口实现类
 * @author 创建人 CYC
 * @date 创建日期 Thu Jul 14 14:30:38 CST 2014
 */
@Repository
public class MemberinterDao extends GenericDao<Memberinter,String> implements IMemberinterDao {

	public MemberinterDao() {
		super(Memberinter.class);
	}
	
	@Autowired
	public IInterhistoryDao interhistoryDao;
	//跟新积分
	public void updateinter(Interhistory interhistory,Memberinter memberinter){
		this.interhistoryDao.insert(interhistory);
		super.update(memberinter);
	}
	
	public Double getSumCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject("memberinter.getSumCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Double
				.parseDouble(infoMap.get("ct").toString()) : 0;
	}
	
	
	
}

