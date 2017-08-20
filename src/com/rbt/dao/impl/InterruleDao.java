/*
 
 * Package:com.rbt.dao.impl
 * FileName: InterruleDao.java 
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
import com.rbt.dao.IInterruleDao;
import com.rbt.model.Interrule;

/**
 * @function 功能  积分规则表dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Thu Nov 10 14:26:30 CST 2014
 */
@Repository
public class InterruleDao extends GenericDao<Interrule,String> implements IInterruleDao {

	public InterruleDao() {
		super(Interrule.class);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IInterruleDao#updateInterrule(com.rbt.model.Interrule)
	 */
	public void updateInterruleList(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("interrule.update", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}

}

