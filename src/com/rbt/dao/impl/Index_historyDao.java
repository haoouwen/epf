/*
 
 * Package:com.rbt.dao.impl
 * FileName: Index_historyDao.java 
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
import com.rbt.dao.IIndex_historyDao;
import com.rbt.model.Index_history;

/**
 * @function 功能  记录已经索引过的信息dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Fri Aug 12 10:12:10 CST 2014
 */
@Repository
public class Index_historyDao extends GenericDao<Index_history,String> implements IIndex_historyDao {

	public Index_historyDao() {
		super(Index_history.class);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IIndex_historyDao#deleteIndex_history(java.lang.String)
	 */
	public void deleteIndex_historyList(final List list) {
		//this.getSqlMapClientTemplate().delete("index_history.delete", pk);
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if (list != null && list.size() > 0) {
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap) iter.next();
						executor.delete("index_history.delete", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.IIndex_historyDao#insertIndex(java.util.List)
	 */
	public void insertIndex(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if (list != null && list.size() > 0) {
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap) iter.next();
						executor.insert("index_history.insert", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}

}

