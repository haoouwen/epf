/*
 
 * Package:com.rbt.dao.impl
 * FileName: GoodsshareDao.java 
 */
package com.rbt.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IGoodsshareDao;
import com.rbt.model.Goodsshare;

/**
 * @function 功能  晒单dao层业务接口实现类
 * @author 创建人 QJY
 * @date 创建日期 Wed Oct 29 14:36:43 CST 2014
 */
@Repository
public class GoodsshareDao extends GenericDao<Goodsshare,String> implements IGoodsshareDao {
	
	public GoodsshareDao() {
		super(Goodsshare.class);
	}
    
	public List getWebList(Map map){
		return this.getSqlMapClientTemplate().queryForList("goodsshare.getWebList", map);
	}

	public int getWebCount(Map map) {
		Map infoMap = (Map)this.getSqlMapClientTemplate().queryForObject("goodsshare.getWebCount", map);
		return (infoMap!=null && infoMap.get("ct")!=null)?Integer.parseInt(infoMap.get("ct").toString()):0;
	}
}

