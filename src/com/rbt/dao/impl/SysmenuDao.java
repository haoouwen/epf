/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: SysmenuDao.java 
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
import com.rbt.dao.ISysmenuDao;
import com.rbt.model.Sysmenu;

/**
 * @function 功能 系统菜单dao层接口实现
 * @author 创建人 HXK
 * @date 创建日期 Jun 25, 2014
 */
@Repository
public class SysmenuDao extends GenericDao<Sysmenu, String> implements ISysmenuDao {
	public SysmenuDao() {
		super(Sysmenu.class);
	}
	
	public void updateEnable(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("sysmenu.updateEnable", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});		
	}	
	public void updateEnabled(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("sysmenu.updateEnabled", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});		
	}
	
	//递归更改菜单状态时，查询menu_id的拼串
	public List getEnableList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("sysmenu.getEnalbeList",map);
	}
	
}
