/*
  
 
 * Package:com.rbt.servie
 * FileName: IMemberuserService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbt.dao.impl.MemberuserDao;
import com.rbt.model.Member;
import com.rbt.model.Memberuser;
import com.rbt.model.Sysuser;

/**
 * @function 功能 会员登陆账号信息表Service层业务接口实现类
 * @author  创建人 LSQ
 * @date  创建日期 Fri Jan 04 10:06:41 CST 2014
 */

public interface IMemberuserService extends IGenericService<Memberuser,String>{
	
	
	public Memberuser getPKByCustID(String id);
	
	/**
	 * 方法描述：修改用户密码
	 * 
	 * @param memberuser
	 */
	public void updatePassword(Memberuser memberuser);
	/**
	 * 方法描述：插入用户并且返回用户ID
	 * 
	 * @param memberuser
	 */
	public String insertMemberuser(Memberuser memberuser);
	
	//通过用户名获取对象
	public Memberuser getMemberuserByusername(String username);
	/**
	 * @author :LSQ
	 * @date : Mar 4, 2014 9:56:30 AM
	 * @Method Description :用于删除管理员列表中的memberuser表中的cust_id和user_name
	 */
	public void deleteMembuser(String user_name);
	
	public String insertSysUser(Memberuser memberuser);

	public Map get(Map userMap);
	/**
	 * @author : HZX
	 * @date : Feb 17, 2014 2:10:38 PM
	 * @Method Description :获取用户权限菜单
	 */
	public StringBuffer getMentRight(Member member, String role_code,String mem_type,String user_type);
	
	
	/**
	 * @author : HZX
	 * @date : Feb 10, 2014 1:26:41 PM
	 * @Method Description :获取cookie的值
	 */

	public String getCookieValue(HttpServletRequest request, String name);
	/**
	 * @author : HZX
	 * @date : Feb 10, 2014 2:04:09 PM
	 * @Method Description :保存cookies
	 */
	public void savecokes(HttpServletResponse response,String username,String remusername);
	
	
	
}

