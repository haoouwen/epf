/*
 
 * Package:com.rbt.dao.impl
 * FileName: GoodsevalDao.java 
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
import com.rbt.dao.IGoodsevalDao;
import com.rbt.model.Goodseval;
import com.rbt.model.Sysconfig;

/**
 * @function 功能  记录商品评价表信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Tue Jan 08 11:06:50 CST 2014
 */
@Repository
public class GoodsevalDao extends GenericDao<Goodseval,String> implements IGoodsevalDao {
	
	public GoodsevalDao() {
		super(Goodseval.class);
	}
	
	public List getWebList(Map map){
		return this.getSqlMapClientTemplate().queryForList("goodseval.getWebList", map);
	}
	
	public int getWebCount(Map map) {
		Map infoMap = (Map)this.getSqlMapClientTemplate().queryForObject("goodseval.getWebCount", map);
		return (infoMap!=null && infoMap.get("ct")!=null)?Integer.parseInt(infoMap.get("ct").toString()):0;
	}
	
	/**
	 * @Method Description :买家评价
	 * @author : HZX
	 * @date : Apr 2, 2014 10:27:08 AM
	 */
	@SuppressWarnings("unchecked")
	public void updatebuy(Goodseval goodseval) {
		this.getSqlMapClientTemplate().update("goodseval.updatebuy",goodseval);
	}
	
	/**
	 * @author:HXM
	 * @date:May 30, 20149:37:14 AM
	 * @param:
	 * @Description:分组查询通过goods_name 分组
	 */
	public List getListByGroup(Map map) {
		return this.getSqlMapClientTemplate().queryForList("goodseval.getListByGroup",map);
	}
	
	/**
	 * @author:HXM
	 * @date:May 30, 201410:54:39 AM
	 * @param:
	 * @Description:查询获得分组总条数
	 */
	public int getGroupCount(Map map){
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject("goodseval.getGroupCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}
}

