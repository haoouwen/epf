/*
 
 * Package:com.rbt.dao.impl
 * FileName: CollectDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ICollectDao;
import com.rbt.model.Collect;

/**
 * @function 功能  记录会员商机收藏信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Mon Jan 14 10:40:08 CST 2014
 */
@Repository
public class CollectDao extends GenericDao<Collect,String> implements ICollectDao {
	
	public CollectDao() {
		super(Collect.class);
	}
	
	/**
	 * @author:HXM
	 * @date:May 30, 20149:37:14 AM
	 * @param:
	 * @Description:分组查询通过cust_id 分组
	 */
	public List getListByGroup(Map map) {
		return this.getSqlMapClientTemplate().queryForList("collect.getListByGroup",map);
	}
	
	/**
	 * @author:HXM
	 * @date:May 30, 201410:54:39 AM
	 * @param:
	 * @Description:查询获得分组总条数
	 */
	public int getGroupCount(Map map){
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject("collect.getGroupCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}
	public void deleteByGoodsId(String goods_id){
		this.getSqlMapClientTemplate().delete("collect.deletebygoodsid", goods_id);
	}
}

