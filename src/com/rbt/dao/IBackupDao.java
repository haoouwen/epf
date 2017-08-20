/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: IBackupDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Backup;

/**
 * @function 功能 数据库备份实现层类的接口
 * @author 创建人 LJQ
 * @date 创建日期 Jun 28, 2014 3:29:51 PM
 */
public interface IBackupDao  extends IGenericDao<Backup,String>{
	/**
	 * @MethodDescribe 方法描述 获取数据库中所有的表的名称
	 * @author 创建人 LJQ
	 * @date 创建日期 Jul 7, 2014 4:15:42 PM
	 */
	@SuppressWarnings("unchecked")
	public List getTableList(Map map);

	/**
	 * @MethodDescribe 方法描述  获取数据库中有多少表
	 * @author 创建人 LJQ
	 * @date 创建日期 Jul 7, 2014 4:42:25 PM
	 */
	@SuppressWarnings("unchecked")
	public int getTableCount(Map map);

	/**
	 * @MethodDescribe 方法描述   获取某个表的表结构
	 * @author  创建人  LJQ
	 * @date  创建日期  Jul 11, 2014 2:40:18 PM
	 */
	@SuppressWarnings("unchecked")
	public List getTablestructure(Map map);

	/**
	 * @function 功能 获取数据库版本信息
	 * @author  创建人 QJY
	 * @date  创建日期 Aug 26, 2014 5:46:55 PM
	 */
	public String getDatabaseversion();

	/**
	 * @Method Description : 更新表的点击次数
	 * @author : LJQ
	 * @date : Nov 28, 2014 9:34:13 AM
	 */
	public List updateGetClickNum(Map map);
}
