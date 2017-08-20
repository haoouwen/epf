/*
 
 * Package:com.rbt.dao.impl
 * FileName: Ban_IpDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.rbt.dao.IBanipDao;
import com.rbt.model.Banip;;
/**
 * @function 功能  禁止Ip管理dao层接口实现
 * @author  创建人 HXK
 * @date  创建日期  July 5, 2014
 */
@Repository
public class BanipDao extends GenericDao<Banip,String> implements IBanipDao {
	public BanipDao() {
		super(Banip.class);
	}

	@SuppressWarnings("unchecked")
	public void updateAllIp(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("banip.updateIp", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}
}
