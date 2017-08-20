/*
 
 * Package:com.rbt.dao.impl
 * FileName: CancleordertransDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ICancleordertransDao;
import com.rbt.model.Cancleordertrans;

/**
 * @function 功能  取消申请进度dao层业务接口实现类
 * @author 创建人 XBY
 * @date 创建日期 Thu Sep 25 10:55:07 CST 2014
 */
@Repository
public class CancleordertransDao extends GenericDao<Cancleordertrans,String> implements ICancleordertransDao {
	
	public CancleordertransDao() {
		super(Cancleordertrans.class);
	}
	
}

