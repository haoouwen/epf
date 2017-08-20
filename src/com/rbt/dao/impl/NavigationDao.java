/*
 
 * Package:com.rbt.dao.impl
 * FileName: NavigationDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.INavigationDao;
import com.rbt.model.Navigation;

/**
 * @function 功能  导航列表信息dao层业务接口实现类
 * @author 创建人 ZMS
 * @date 创建日期 Thu Aug 13 11:37:53 CST 2015
 */
@Repository
public class NavigationDao extends GenericDao<Navigation,String> implements INavigationDao {
	
	public NavigationDao() {
		super(Navigation.class);
	}
	public int getWebCount(Map map) {
		Map infoMap = (Map)this.getSqlMapClientTemplate().queryForObject("navigation.getWebCount", map);
		return (infoMap!=null && infoMap.get("ct")!=null)?Integer.parseInt(infoMap.get("ct").toString()):0;
	}
	public List getWebList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("navigation.getWebList",map);
	}
	
}

