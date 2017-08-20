/*
 
 * Package:com.rbt.dao.impl
 * FileName: BuyeraddrDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IBuyeraddrDao;
import com.rbt.model.Buyeraddr;

/**
 * @function 功能  收货地址管理dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Thu Dec 27 16:33:56 CST 2014
 */
@Repository
public class BuyeraddrDao extends GenericDao<Buyeraddr,String> implements IBuyeraddrDao {
	
	public BuyeraddrDao() {
		super(Buyeraddr.class);
	}
	
	/**
	 * @author:HXM
	 * @date:May 30, 20149:37:14 AM
	 * @param:
	 * @Description:分组查询通过cust_id 分组
	 */
	public List getListByGroup(Map map) {
		return this.getSqlMapClientTemplate().queryForList("buyeraddr.getListByGroup",map);
	}
	
	/**
	 * @author:HXM
	 * @date:May 30, 201410:54:39 AM
	 * @param:
	 * @Description:查询获得分组条数
	 */
	public int getGroupCount(Map map){
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject("buyeraddr.getGroupCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}
}

