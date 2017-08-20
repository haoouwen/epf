/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: WaterfallDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IWaterfallDao;
import com.rbt.model.Waterfall;

/**
 * @function 功能  瀑布布局dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Fri Dec 28 11:10:08 CST 2014
 */
@Repository
public class WaterfallDao extends GenericDao<Waterfall,String> implements IWaterfallDao {
	
	public WaterfallDao() {
		super(Waterfall.class);
	}
	
}

