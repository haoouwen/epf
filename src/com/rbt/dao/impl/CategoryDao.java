/*
 
 * Package:com.rbt.dao.impl
 * FileName: CategoryDao.java 
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
import com.rbt.dao.ICategoryDao;
import com.rbt.model.Category;

/**
 * @function 功能  分类信息表dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Tue Jul 12 13:04:58 CST 2014
 */

@Repository
public class CategoryDao extends GenericDao<Category,String> implements ICategoryDao {

	public CategoryDao() {
		super(Category.class);
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.ICategoryDao#getAreaCategoryList(java.util.Map)
	 */
	public List getAreaCategoryList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("category.getAreaCategoryList",map);
	}
	/* (non-Javadoc)
	 * @see com.rbt.dao.ICategoryDao#getTowAreaCategoryList(java.util.Map)
	 */
	public List getTwoAreaCategoryList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("category.getTowAreaCategoryList",map);
	}
	
	/* (non-Javadoc)
	 * @see com.rbt.dao.ICategoryDao#getCategoryIndexList(java.util.Map)
	 */
	public List getCategoryIndexList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("category.getCategoryIndexList",map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.ICategoryDao#getWebCategroyList(java.util.Map)
	 */
	public List getWebCategroyList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("category.getWebCategroyList",map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.ICategoryDao#getAll()
	 */
	public List getAll() {
		return this.getSqlMapClientTemplate().queryForList("category.getAll");
	}


	public void updateDisplay(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("category.updateDisplay", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
		
	}

	public List getDeleteList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("category.getDeleteList",map);
	}


}

