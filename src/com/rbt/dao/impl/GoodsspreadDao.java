/*
 
 * Package:com.rbt.dao.impl
 * FileName: GoodsspreadDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IGoodsspreadDao;
import com.rbt.model.Goodsspread;

/**
 * @function 功能  记录商品推广信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Wed Mar 20 13:21:09 CST 2014
 */
@Repository
public class GoodsspreadDao extends GenericDao<Goodsspread,String> implements IGoodsspreadDao {
	
	public GoodsspreadDao() {
		super(Goodsspread.class);
	}
	
}

