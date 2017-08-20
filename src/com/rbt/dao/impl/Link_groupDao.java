/*
 
 * Package:com.rbt.dao.impl
 * FileName: Link_groupDao.java 
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
import com.rbt.dao.ILink_groupDao;
import com.rbt.model.Link_group;
@Repository
public class Link_groupDao extends GenericDao<Link_group,String> implements ILink_groupDao {
	
	public Link_groupDao() {
		super(Link_group.class);
	}
	
	/**
	 * 方法描述：批量修改友情链接分组值
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateLinkgroup_name(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("link_group.updateName", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}

}
