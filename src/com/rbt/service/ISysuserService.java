/*
  
 
 * Package:com.rbt.service
 * FileName: ISysuserService.java 
 */

package com.rbt.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbt.model.Memberuser;
import com.rbt.model.Sysuser;

/**
 * @function 功能  系统管理员业务层接口
 * @author  创建人 HXK
 * @date  创建日期  Jun 13, 2014
 */
public interface ISysuserService extends IGenericService<Sysuser,String>{
	
	/**
	 * @MethodDescribe 方法描述    更新最后运营商最后一次登录的ID
	 * @author  创建人  LJQ
	 * @date  创建日期  Sep 21, 2014 10:33:01 AM
	 */	
	public void updatelaststate(Sysuser sysuser);

	/**
	 * @MethodDescribe 方法描述    找出运营商后台需要审核的条数
	 * @author  创建人  LJQ
	 * @date  创建日期  Sep 20, 2014 9:57:00 AM
	 */
	@SuppressWarnings("unchecked")
	public List getMsgCount(Map map);
	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 11:30:43 AM
	 * @Method Description :新增数据
	 */
	public void insertSysuser(Sysuser sysuser);
	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 1:48:30 PM
	 * @Method Description : 更新对象
	 */
	public void updateSysuser(Sysuser sysuser);
	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 1:59:21 PM
	 * @Method Description :删除数据
	 */
	public void deleteSysuser(String user_id,String user_name);
	/**
	 * @author : HZX
	 * @date : Feb 10, 2014 1:25:07 PM
	 * @Method Description :运营商后台获得今日新增与未审核条数 列表
	 */
	public List getcountList(List list, List modlist, String session_user_type);
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
	public void savecokes(HttpServletResponse response, Sysuser sysuser,String remusername);
	/**
	 * @author : HZX
	 * @date : Feb 10, 2014 2:04:49 PM
	 * @Method Description :获取该用户id及其它信息
	 */
	public Map getUserMap(List userList);
	public int close(String ids);
}
