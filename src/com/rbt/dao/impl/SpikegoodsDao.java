/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: SpikegoodsDao.java 
 */
package com.rbt.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.rbt.dao.ISpikegoodsDao;
import com.rbt.model.Spikegoods;

/**
 * @function 功能  秒杀商品dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Fri Mar 29 15:32:29 CST 2014
 */
@Repository
public class SpikegoodsDao extends GenericDao<Spikegoods,String> implements ISpikegoodsDao {
	
	public SpikegoodsDao() {
		super(Spikegoods.class);
	}

	
	public int getWebCount(Map map) {
		Map infoMap = (Map)this.getSqlMapClientTemplate().queryForObject("spikegoods.getWebCount", map);
		return (infoMap!=null && infoMap.get("ct")!=null)?Integer.parseInt(infoMap.get("ct").toString()):0;
	}
	
	public List getWebList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("spikegoods.getWebList",map);
	}
}

