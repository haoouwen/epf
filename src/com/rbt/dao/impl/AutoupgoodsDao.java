/*
 
 * Package:com.rbt.dao.impl
 * FileName: AutoupgoodsDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IAutoupgoodsDao;
import com.rbt.model.Autoupgoods;

/**
 * @function 功能  记录商品上下架管理信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Fri Feb 01 10:46:02 CST 2014
 */
@Repository
public class AutoupgoodsDao extends GenericDao<Autoupgoods,String> implements IAutoupgoodsDao {
	
	public AutoupgoodsDao() {
		super(Autoupgoods.class);
	}
	
	
	public void deleteByGoodsId(String id) {
		this.getSqlMapClientTemplate().delete("autoupgoods.deleteByGoodsid", id);
	}

	
}

