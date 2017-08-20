/*
 
 * Package:com.rbt.dao.impl
 * FileName: BackupDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.rbt.dao.IBackupDao;
import com.rbt.model.Backup;

/**
 * @function 功能 数据库备份实现层类
 * @author 创建人 LJQ
 * @date 创建日期 Jul 11, 2014 9:01:49 AM
 */
@Repository
public class BackupDao extends GenericDao<Backup,String> implements IBackupDao {

	public BackupDao() {
		super(Backup.class);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IBackupDao#getTableCount(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public int getTableCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				"backup.gettableCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IBackupDao#getTableList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List getTableList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(
				"backup.gettableList");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IBackupDao#getTablestructure(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List getTablestructure(Map map) {
		return this.getSqlMapClientTemplate().queryForList(
				"backup.gettablestructure", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IBackupDao#getDatabaseversion()
	 */
	public String getDatabaseversion() {
		return (String) this.getSqlMapClientTemplate().queryForObject(
				"backup.getdatabaseversion");
	}

	public List updateGetClickNum(Map map) {
		this.getSqlMapClientTemplate().update("backup.updateClickNum",map);
		return this.getSqlMapClientTemplate().queryForList("backup.getClickNum",map);
	}
}
