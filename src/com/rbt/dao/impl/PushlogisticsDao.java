/*
 
 * Package:com.rbt.dao.impl
 * FileName: PushlogisticsDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IPushlogisticsDao;
import com.rbt.model.Pushlogistics;

/**
 * @function 功能  物流推送dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Tue Jun 18 09:31:12 CST 2014
 */
@Repository
public class PushlogisticsDao extends GenericDao<Pushlogistics,String> implements IPushlogisticsDao {
	
	public PushlogisticsDao() {
		super(Pushlogistics.class);
	}
	
}

