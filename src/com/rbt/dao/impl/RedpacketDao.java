/*
 
 * Package:com.rbt.dao.impl
 * FileName: RedpacketDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IRedpacketDao;
import com.rbt.model.Redpacket;

/**
 * @function 功能  红包dao层业务接口实现类
 * @author 创建人 XBY
 * @date 创建日期 Tue Aug 11 20:50:54 CST 2015
 */
@Repository
public class RedpacketDao extends GenericDao<Redpacket,String> implements IRedpacketDao {
	
	public RedpacketDao() {
		super(Redpacket.class);
	}
	
}

