/*
 * Package:com.rbt.dao.impl
 * FileName: ExredbagDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IExredbagDao;
import com.rbt.model.Exredbag;

/**
 * @function 功能  红包兑换表dao层业务接口实现类
 * @author 创建人 XBY
 * @date 创建日期 Fri Oct 09 13:33:32 CST 2015
 */
@Repository
public class ExredbagDao extends GenericDao<Exredbag,String> implements IExredbagDao {
	
	public ExredbagDao() {
		super(Exredbag.class);
	}
	
}

