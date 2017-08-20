/*
  
 
 * Package:com.rbt.service.impl
 * FileName: SysuserService.java 
 */

package com.rbt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IMemberuserDao;
import com.rbt.dao.IOrganizeDao;
import com.rbt.dao.IRoleDao;
import com.rbt.dao.ISysuserDao;
import com.rbt.model.Memberuser;
import com.rbt.model.Organize;
import com.rbt.model.Role;
import com.rbt.model.Sysuser;
import com.rbt.service.IMemberuserService;
import com.rbt.service.ISysuserService;

/**
 * @function 功能  系统管理员业务实现类
 * @author  创建人 HXK
 * @date  创建日期  Jun 13, 2014
 */
@Service
public class SysuserService extends GenericService<Sysuser,String> implements ISysuserService {
	
	ISysuserDao sysuserDao;
	@Autowired
	private IMemberuserDao memberuserDao;
	@Autowired
	private IOrganizeDao organizeDao;
	@Autowired
	private IRoleDao roleDao;
	int count=0;
	@Autowired
	public SysuserService(ISysuserDao sysuserDao) {
		super(sysuserDao);
		this.sysuserDao = sysuserDao;
	}
	/* (non-Javadoc)
	 * @see com.rbt.service.ISysuserService#getMsgCount(java.util.Map)
	 */
	public List getMsgCount(Map map) {
		return this.sysuserDao.getMsgCount(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.ISysuserService#updatelaststate(com.rbt.model.Sysuser)
	 */
	public void updatelaststate(Sysuser sysuser) {
		this.sysuserDao.updatelaststate(sysuser);		
	}

	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 11:30:43 AM
	 * @Method Description :新增数据
	 */
	public void insertSysuser(Sysuser sysuser){
		Memberuser memberuser = new Memberuser();
		memberuser.setCust_id("0");
		memberuser.setUser_name(sysuser.getUser_name());
		memberuser.setPasswd("");
		// 先添加memberuser表并返回一个user_id
		String user_id = this.memberuserDao.insertSysUser(memberuser);
		// 将插入memberuser表返回的id添加在sysuser里
		if (user_id != null && !user_id.equals("")) {
			sysuser.setUser_id(user_id);
			this.sysuserDao.insert(sysuser);
		} 
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 1:48:30 PM
	 * @Method Description : 更新对象
	 */
	public void updateSysuser(Sysuser sysuser){
		this.sysuserDao.update(sysuser);
		//this.memberuserDao.update(memberuser);
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 1:58:06 PM
	 * @Method Description :删除数据
	 */
	public void deleteSysuser(String user_id,String user_name){
		this.sysuserDao.delete(user_id);
		this.memberuserDao.deleteMembuser(user_name);
	}
	/**
	 * @author : HZX
	 * @date : Feb 10, 2014 1:25:07 PM
	 * @Method Description :运营商后台获得今日新增与未审核条数 列表
	 */
	public List getcountList(List list, List modlist, String session_user_type){
		List countlist =new ArrayList();
		if (list != null && list.size() > 0) {
			Map listMap = (HashMap) list.get(0);
			for (int i = 0; i < modlist.size(); i++) {
				Map modtypeMap = new HashMap();
				modtypeMap = (HashMap) modlist.get(i);
				String module_type = "";
				if (modtypeMap.get("module_type") != null) {
					module_type = modtypeMap.get("module_type").toString();
				}
				if (module_type.equals("subject")) {
					continue;
				}
				Map modcountMap = new HashMap();
				// 获取模块名称
				if (listMap.get(module_type + "module_name")!=null) {
					modcountMap.put("module_name", listMap.get(module_type + "module_name").toString());
				}
				// 获取模块分类属性
				if (listMap.get(module_type + "is_catattr")!=null) {
					modcountMap.put("is_catattr", listMap.get(module_type + "is_catattr").toString());
				}
				// 获取模块表名
				if (listMap.get(module_type + "table_name")!=null) {
					modcountMap.put("table_name", listMap.get(module_type + "table_name").toString());
				}
				// 获取模块信息的总数
				if (listMap.get(module_type + "todaycount")!=null) {
					modcountMap.put("todaycount", listMap.get(module_type + "todaycount").toString());
				}
				// 获取模块未审核信息的条数
				if (listMap.get(module_type + "countaudit")!=null) {
					modcountMap.put("countaudit", listMap.get(module_type + "countaudit").toString());
				}
				String is_admin = session_user_type;
				modcountMap.put("is_admin", is_admin);
				countlist.add(modcountMap);
			}
		}
		return countlist;
	}
	/**
	 * 根据名字获取cookie（接口方法）
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public  Cookie getCookieByName(HttpServletRequest request, String name) {
		
			Map<String, Cookie> cookieMap = ReadCookieMap(request);
			if (cookieMap.containsKey(name)) {
				Cookie cookie = (Cookie) cookieMap.get(name);
				return cookie;
			} else {
				return null;
			}
	}
	/**
	 * 将cookie封装到Map里面（非接口方法）
	 * @param request
	 * @return
	 */
	private  Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
	public String getCookieValue(HttpServletRequest request, String name) {
		String loginName="";
		Cookie cokLoginName=this.getCookieByName(request, name);
		if (cokLoginName != null && cokLoginName.getValue() != null) {
			loginName =cokLoginName.getValue();
		}
		return loginName;
	}
	/**
	 * 设置cookie（接口方法）
	 * @param name  cookie名字
	 * @param value  cookie值
	 * @param maxAge cookie生命周期 以秒为单位
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0)
			cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}
	public void savecokes(HttpServletResponse response, Sysuser sysuser,String remusername) {
		int loginMaxAge = 30 * 24 * 60 * 60; // 定义账户密码的生命周期，这里是一个月。单位为秒
		String username = sysuser.getUser_name();
		if (remusername != null && remusername.equals("on")) {
			addCookie(response, "loginName", username, loginMaxAge);
		}
		
	}
	public Map getUserMap(List userList) {
		Map userInfoMap=new HashMap();
		String user_id = "", role_id = "", user_type = "", area_id = "",menu_right = "", oper_right = "";
		if (userList != null && userList.size() > 0) {
			HashMap userMap = (HashMap) userList.get(0);
			if (userMap.get("user_id") != null) {
				user_id = userMap.get("user_id").toString();
			}
			if (userMap.get("role_id") != null) {
				role_id = userMap.get("role_id").toString();
			}
			if (userMap.get("user_type") != null) {
				user_type = userMap.get("user_type").toString();
			}
			// 获取系统用户表中的部门串,再获取所属的地区ID,用于分类信息的过滤
			if (userMap.get("org_id") != null && !userMap.get("org_id").equals("")) {
				String org_id = userMap.get("org_id").toString();
				if (org_id.indexOf(",") > -1) {
					int len = org_id.lastIndexOf(",");
					if(len + 1 < org_id.length()){
						//获取最后一级的部门ID串，找出对应的管理地区
						org_id = org_id.substring((len + 1), org_id.length());
					}
				}
				//对应的部门对象
				Organize organize =organizeDao.get(org_id);
				if (organize != null && !organize.getArea_attr().equals("")) {
					area_id = organize.getArea_attr();
					if (area_id.indexOf(",") > -1) {
						int area_len = area_id.lastIndexOf(",");
						if(area_len + 1 < area_id.length()){
							//获取最后一级的地区ID
							area_id = area_id.substring((area_len + 1), area_id.length());
						}
					}
				}
			}
		}
		// 获取对应运营商后台角色的菜单串和操作权限串
		Role role = roleDao.get(role_id);
		if (role != null) {
			if (role.getMenu_right() != null) {
				menu_right = role.getMenu_right();
			}
			if (role.getOper_right() != null) {
				oper_right = role.getOper_right();
			}
		}
		userInfoMap.put("user_id", user_id);
		userInfoMap.put("role_id", role_id);
		userInfoMap.put("user_type", user_type);
		userInfoMap.put("area_id", area_id);
		userInfoMap.put("menu_right", menu_right);
		userInfoMap.put("oper_right", oper_right);
		// 登录成功后，为用户添加最后登录时间，最后登录IP，登录的次数
		Sysuser user = new Sysuser();
		String lastip = ServletActionContext.getRequest().getRemoteAddr();
		user.setUser_id(user_id);
		user.setLoginip(lastip);
		sysuserDao.updatelaststate(user);
		return userInfoMap;
	}
	public int close(String ids) {
		String user_ids="";
		Map sMap=new HashMap();
		sMap.put("subjection_in", ids);
		List sysuserList=this.sysuserDao.getList(sMap);
		if(sysuserList.size()>0){
			for(int i=0;i<sysuserList.size();i++){
				Map soMap=(Map) sysuserList.get(i);
				user_ids+=soMap.get("user_id").toString()+",";
				boolean flag =updateBatchState(soMap.get("user_id").toString(), "1", "user_id", "state");
				if(!flag){
					count++;
				}
			}
			
		}
		if(user_ids.length()>0){
			user_ids=user_ids.substring(0, user_ids.length()-1);
			close(user_ids);
		}else {
			return count;
		}
		return count;
	}
	
}
