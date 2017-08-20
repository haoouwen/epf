/*
 
 * Package:com.rbt.dao.impl
 * FileName: InterhistoryDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.rbt.dao.IInterhistoryDao;
import com.rbt.model.Interhistory;

/**
 * @function 功能  记录会员积分异动历史dao层业务接口实现类
 * @author 创建人 CYC
 * @date 创建日期 Thu Jul 14 15:01:09 CST 2014
 */
@Repository
public class InterhistoryDao extends GenericDao<Interhistory,String> implements IInterhistoryDao {
	
	public InterhistoryDao() {
		super(Interhistory.class);
	}
	public int getInterhistorySumScore(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject("interhistory.getSumScore", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer.parseInt(infoMap.get("ct").toString()) : 0;
	}

	public List getReleaseCustId(Map map) {
		return this.getSqlMapClientTemplate().queryForList("interhistory.getReleaseCustId",
				map);
	}
   
	
}

