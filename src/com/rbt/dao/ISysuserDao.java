/*
  
 
 * Package:com.rbt.dao
 * FileName: ISysuserDao.java 
 */

package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Sysuser;

/**
 * @function 功能 系统用户dao层业务接口
 * @author  创建人 HXK
 * @date  创建日期  Jun 3, 2014
 */
public interface ISysuserDao extends IGenericDao<Sysuser,String>{
	

	/**
	 * @MethodDescribe 方法描述    找出运营商后台需要审核的条数
	 * @author  创建人  LJQ
	 * @date  创建日期  Sep 20, 2014 9:57:00 AM
	 */
	@SuppressWarnings("unchecked")
	public List getMsgCount(Map map);
	/**
	 * @MethodDescribe 方法描述    更新最后运营商最后一次登录的ID
	 * @author  创建人  LJQ
	 * @date  创建日期  Sep 21, 2014 10:33:01 AM
	 */	
	public void updatelaststate(Sysuser sysuser);
	
}
