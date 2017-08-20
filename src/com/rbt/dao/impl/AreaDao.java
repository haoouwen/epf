/*
 
 * Package:com.rbt.dao.impl
 * FileName: SysuserDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import com.rbt.dao.IAreaDao;
import com.rbt.model.Area;

/**
 * @function 功能 地区管理dao层接口实现
 * @author 创建人 HXK
 * @date 创建日期 Jun 28, 2014
 */

@Repository
public class AreaDao  extends GenericDao<Area,String> implements IAreaDao {
	public AreaDao() {
		super(Area.class);
	}
	
	/* (non-Javadoc)
	 * @see com.rbt.dao.IAreaDao#getIndexList(java.util.Map)
	 */
	public List getAreaIndexList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("area.getAreaIndexList", map);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IAreaDao#updateOneAreaSortNo(java.util.Map)
	 */
	public void updateOneAreaSortNo(Map map) {
		this.getSqlMapClientTemplate().update("area.updateSortNo", map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.IAreaDao#getWebAreaList(java.util.Map)
	 */
	public List getWebAreaList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("area.getWebAreaList", map);
	}

	public List getAll() {
		return this.getSqlMapClientTemplate().queryForList("area.getAll");
	}

    public List getWebAreaIndexList(Map map) {
    	return this.getSqlMapClientTemplate().queryForList("area.getWebAreaIndexList", map);
	}
    
    public List getAreaCityList(Map map) {
    	return this.getSqlMapClientTemplate().queryForList("area.getAreaCityList", map);
	}
    
    public List getCharacterList(Map map) {
    	return this.getSqlMapClientTemplate().queryForList("area.getCharacterList", map);
	}
    
    public List getCountryList(Map map) {
    	return this.getSqlMapClientTemplate().queryForList("area.getCountryList", map);
	}

	public List getDeleteList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("area.getDeleteList",map);
	}

}
