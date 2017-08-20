/*
  
 
 * Package:com.rbt.service.impl
 * FileName: BackupService.java 
 */

package com.rbt.service.impl;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.model.Backup;
import com.rbt.service.IBackupService;
import com.rbt.common.util.DbUtil;
import com.rbt.common.util.PropertiesUtil;
import com.rbt.dao.IBackupDao;

/**
 * @function 功能 数据库备份/还原业务层
 * @author 创建人 LJQ
 * @date 创建日期 Jul 11, 2014 9:36:38 AM
 */
@Service
public class BackupService extends GenericService<Backup,String> implements IBackupService {

	/*
	 * 数据库备份/还原实现层接口
	 */
	IBackupDao backupDao;
	private DbUtil jdbcDbm;

	@Autowired
	public BackupService(IBackupDao backupDao) {
		super(backupDao);
		this.backupDao = backupDao;
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IBackupService#getTableCount(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public int getTableCount(Map map) {
		return backupDao.getTableCount(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IBackupService#getTableList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List getTableList(Map map) {
		return backupDao.getTableList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IBackupService#getTablestructure(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List getTablestructure(Map map) {
		return backupDao.getTablestructure(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IBackupService#getDatabaseversion()
	 */
	public String getDatabaseversion() {
		return this.backupDao.getDatabaseversion();
	}

	public List updateGetClickNum(Map map) {
		return this.backupDao.updateGetClickNum(map);
	}
	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 9:56:00 AM
	 * @Method Description：重构原List数据
	 */
	public List newTableList(List tableList,String dbName,List structureList){
		List newTableList = new ArrayList();
		if (tableList != null && tableList.size() > 0) {
			Map tableMap = new HashMap();
			// 定义MAP对象
			Map countMap = new HashMap();
			for (int i = 0; i < tableList.size(); i++) {
				// 获取表中行的对象
				tableMap = (HashMap) tableList.get(i);
				if (tableMap.get("Tables_in_" + dbName) != null) {
					// 取出数据库中相应表的名称
					String tableName = tableMap.get("Tables_in_" + dbName).toString();
					countMap.put("tabName", tableName);
					// 通过表名查找该表中行的记录数
					int Count = backupDao.getTableCount(countMap);
					// 通过表名获取该表的表结构
					structureList = backupDao.getTablestructure(countMap);
					if (structureList != null && structureList.size() > 0) {
						Map stlMap = (HashMap) structureList.get(0);
						if (stlMap.get("Create Table") != null) {
							Map newTableMap = new HashMap();
							newTableMap.put("tableName", tableName);
							newTableMap.put("tableCount", Count);
							String createSql = stlMap.get("Create Table").toString();
							// 过滤掉非法的字符
							createSql = createSql.replace("COMMENT=", "").replace("COMMENT", "").replace("�", "").replace("''", "");
							// 为创建的新表添加表结构语句
							newTableMap.put("structure", createSql);
							// 为新List添加新表的MAP对象
							newTableList.add(newTableMap);
						}
					}
				}
			}
		}
		return newTableList;
	}
	/**
	 * @Method Description :数据库初始化
	 * @author : HZX
	 * @date : Apr 10, 2014 4:35:57 PM
	 */
	public boolean dbInit() {
		//获取系统根目录
		String ROOT_PATH = PropertiesUtil.getClassPath();
		//获取标签sql配置文件
		String textPath = ROOT_PATH+"dbInit.txt";
		//执行text文本读取
			RandomAccessFile raf = null;
			try {
				raf = new RandomAccessFile(textPath,"r");
				String temp = null;
				while (true) {
					temp = raf.readLine();
					if(temp == null){
						break ;
					}
					jdbcDbm=new DbUtil();
					try {
						jdbcDbm.excuteSql( temp.trim());
					} catch (Exception e) {
						return false ;
					}
					
				}
				raf.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false ;
			} finally {
				if(raf != null){
					try {
						raf.close();
					} catch (Exception e) {
						e.printStackTrace();
						return false ;
					}
				}
			}
		
		return true;
	}
	
}
