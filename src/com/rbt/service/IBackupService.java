/*
  
 
 * Package:com.rbt.service
 * FileName: IBackupService.java 
 */

package com.rbt.service;

import java.util.List;
import java.util.Map;
import com.rbt.model.Backup;

/**
 * @function 功能  数据库备份业务层接口层
 * @author 创建人 LJQ
 * @date 创建日期 Jun 28, 2014 3:25:23 PM
 */
public interface IBackupService extends IGenericService<Backup,String>{
	/**
	 * @MethodDescribe 方法描述 获取数据库中所有的表的名称
	 * @author 创建人 LJQ
	 * @date 创建日期 Jul 7, 2014 4:14:46 PM
	 */
	@SuppressWarnings("unchecked")
	public List getTableList(Map map);

	/**
	 * @MethodDescribe 方法描述 获取数据库中有多少表
	 * @author 创建人 LJQ
	 * @date 创建日期 Jul 7, 2014 4:46:22 PM
	 */
	@SuppressWarnings("unchecked")
	public int getTableCount(Map map);

	/**
	 * @MethodDescribe 方法描述    获取某个表的表结构
	 * @author  创建人  LJQ
	 * @date  创建日期  Jul 11, 2014 2:41:54 PM
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
	 * @function 功能 修改信息的点击数并取出点击数
	 * @author  创建人 HXK
	 * @date  创建日期 2014-10-28
	 */
	
	public List updateGetClickNum(Map map);
    
	
	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 9:56:00 AM
	 * @Method Description：重构原List数据
	 */
	public List newTableList(List tableList,String dbName,List structureList);
	/**
	 * @Method Description :数据库初始化
	 * @author : HZX
	 * @date : Apr 10, 2014 4:36:35 PM
	 */
	public boolean dbInit();
}
