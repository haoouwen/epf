/*
 
 * Package:com.rbt.dao.impl
 * FileName: PointsgoodsDao.java 
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
import com.rbt.dao.IPointsgoodsDao;
import com.rbt.model.Pointsgoods;

/**
 * @function 功能  记录积分商品信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Mon Mar 25 16:00:03 CST 2014
 */
@Repository
public class PointsgoodsDao extends GenericDao<Pointsgoods,String> implements IPointsgoodsDao {
	
	public PointsgoodsDao() {
		super(Pointsgoods.class);
	}

}

