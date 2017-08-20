/*
 
 * Package:com.rbt.dao.impl
 * FileName: FloorinfoDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IFloorinfoDao;
import com.rbt.model.Floorinfo;

/**
 * @function 功能  楼层管理dao层业务接口实现类
 * @author 创建人 HXK
 * @date 创建日期 Sat Aug 08 16:33:52 CST 2015
 */
@Repository
public class FloorinfoDao extends GenericDao<Floorinfo,String> implements IFloorinfoDao {
	
	public FloorinfoDao() {
		super(Floorinfo.class);
	}
	
}

