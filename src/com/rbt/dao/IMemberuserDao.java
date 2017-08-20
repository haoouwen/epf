/*
  
 
 * Package:com.rbt.dao
 * FileName: IMemberuserDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Memberuser;

/**
 * @function 功能 会员登陆账号信息表dao层业务接口
 * @author  创建人LSQ
 * @date  创建日期 Fri Jan 04 10:06:41 CST 2014
 */

public interface IMemberuserDao extends IGenericDao<Memberuser,String>{
	/**
	 * 方法描述：修改用户密码
	 * 
	 * @param memberuser
	 */
	public void updatePassword(Memberuser memberuser);
	/**
	 * 方法描述：插入用户账号
	 * 
	 * @param memberuser
	 */
	public String insertMemberuser(Memberuser memberuser);
	
	public Memberuser getPKByCustID(String cust_id);
	//通过用户名获取对象
	public Memberuser getMemberuserByusername(String username);
	public void deleteMembuser(String user_name);
	public String insertSysUser(Memberuser memberuser);
}

