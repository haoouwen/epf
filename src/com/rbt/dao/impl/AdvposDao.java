/*
 
 * Package:com.rbt.dao.impl
 * FileName: AdvposDao.java 
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
import com.rbt.dao.IAdvposDao;
import com.rbt.model.Advinfo;
import com.rbt.model.Advpos;

/**
 * @function 功能 广告位dao层业务接口实现类
 * @author 创建人 QJY
 * @date 创建日期 Jul 7, 2014 4:57:54 PM
 */

@Repository
public class AdvposDao extends GenericDao<Advpos,String> implements IAdvposDao {

	public AdvposDao() {
		super(Advpos.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.rbt.dao.IAdvposDao#updateAdvposBatch(java.util.List)
	 */
	public void updateAdvposBatch(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if (list != null && list.size() > 0) {
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap) iter.next();
						executor.update("advpos.updateAdvposBatch", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * @see com.rbt.dao.IAdvposDao#getPosNameList(java.util.Map)
	 */
	public List getPosNameList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("advpos.getPosNameList", map);
	}

}
