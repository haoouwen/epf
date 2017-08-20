/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: VisitnumDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IVisitnumDao;
import com.rbt.model.Visitnum;
 
/**
 * @function 功能  记录日访问数dao层业务接口实现类
 * @author 创建人 LHY
 * @date 创建日期 Thu Oct 11 09:56:36 CST 2014
 */
@Repository
public class VisitnumDao extends GenericDao<Visitnum,String> implements IVisitnumDao {
	
	public VisitnumDao() {
		super(Visitnum.class);
	}

	public int getSum(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject("visitnum.getSum", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}
	
}

