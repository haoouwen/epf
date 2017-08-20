/*
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IAsysuserService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbt.model.Asysuser;
import com.rbt.model.Sysuser;

/**
 * @function 功能 代理商Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Wed Aug 05 14:52:52 CST 2015
 */

public interface IAsysuserService extends IGenericService<Asysuser,String>{
	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 11:30:43 AM
	 * @Method Description :新增数据
	 */
	public void insertSysuser(Asysuser asysuser);
	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 1:48:30 PM
	 * @Method Description : 更新对象
	 */
	public void updateSysuser(Asysuser asysuser);
	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 1:59:21 PM
	 * @Method Description :删除数据
	 */
	public void deleteSysuser(String user_id,String user_name);
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
	public void savecokes(HttpServletResponse response, Asysuser asysuser,String remusername);
	/**
	 * @author : HZX
	 * @date : Feb 10, 2014 2:04:49 PM
	 * @Method Description :获取该用户id及其它信息
	 */
	public Map getUserMap(List userList);
	public int close(String ids);
	
	/**
	 * 前台
	 * @param map
	 * @return
	 */
	public int getWebCount(Map map);
	/**
	 * 前台
	 * @param map
	 * @return
	 */
	public List getWebList(Map map);
	
}

