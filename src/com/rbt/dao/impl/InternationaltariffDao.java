/*
 
 * Package:com.rbt.dao.impl
 * FileName: InternationaltariffDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IInternationaltariffDao;
import com.rbt.model.Internationaltariff;

/**
 * @function 功能  国际物流dao层业务接口实现类
 * @author 创建人 ZMS
 * @date 创建日期 Thu Aug 20 18:55:20 CST 2015
 */
@Repository
public class InternationaltariffDao extends GenericDao<Internationaltariff,String> implements IInternationaltariffDao {
	
	public InternationaltariffDao() {
		super(Internationaltariff.class);
	}
	
}

