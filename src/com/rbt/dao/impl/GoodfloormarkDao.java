/*
 * Package:com.rbt.dao.impl
 * FileName: GoodfloormarkDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IGoodfloormarkDao;
import com.rbt.model.Goodfloormark;

/**
 * @function 功能  商品楼层信息dao层业务接口实现类
 * @author 创建人 HXK
 * @date 创建日期 Mon Aug 10 11:00:14 CST 2015
 */
@Repository
public class GoodfloormarkDao extends GenericDao<Goodfloormark,String> implements IGoodfloormarkDao {
	
	public GoodfloormarkDao() {
		super(Goodfloormark.class);
	}
	public List getGoodsFloorList(Map map){
		return this.getSqlMapClientTemplate().queryForList("goodfloormark.getGoodsFList",map);
	}
}

