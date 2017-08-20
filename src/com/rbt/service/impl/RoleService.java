/*
  
 
 * Package:com.rbt.service.Impl
 * FileName: RoleService.java 
 */
package com.rbt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IAboutusDao;
import com.rbt.dao.IRoleDao;
import com.rbt.dao.ISysmenuDao;
import com.rbt.dao.ISysuserDao;
import com.rbt.model.Aboutus;
import com.rbt.model.Role;
import com.rbt.service.IRoleService;

/**
 * @function 功能 添加角色业务层类
 * @author 创建人 LJQ
 * @date 创建日期 Jun 28, 2014 3:25:23 PM
 */
@Service
public class RoleService extends GenericService<Role,String> implements IRoleService {
	/*
	 * 角色管理实现层接口
	 */
	IRoleDao roleDao;
	@Autowired
	ISysuserDao sysuserDao;
	@Autowired
	ISysmenuDao sysmenuDao;
	@Autowired
	public RoleService(IRoleDao roleDao) {
		super(roleDao);
		this.roleDao = roleDao;
	}
	public List getRoleList(List rolelist) {
		if (rolelist != null && rolelist.size() > 0) {
			int adminnum = 0;
			Map aMap = new HashMap();
			Map bMap = new HashMap();
			for (int i = 0; i < rolelist.size(); i++) {
				aMap = (Map) rolelist.get(i);
				if (aMap.get("role_id") != null) {
					bMap.put("role_id", aMap.get("role_id"));
					adminnum = sysuserDao.getCount(bMap);
				}
				aMap.put("adminnum", adminnum);
				rolelist.set(i, aMap);
			}
		}
		return rolelist;
	}
	
	/**
	 * @author : HZX
	 * @date : Feb 11, 2014 10:51:43 AM
	 * @Method Description :获取不同级别菜单Map
	 */
	private Map getListMap(String syscode,String level){
		Map Map = new HashMap();
		Map.put("syscode", syscode);
		Map.put("ro_menu_level", level);
		Map.put("enabled", "0");
		return Map;
	}
	/**
	 * @author:HXM
	 * @date:Jun 4, 20141:22:19 PM
	 * @param:menu_right管理员操作对应的菜单项
	 * @param:syscode菜单所属的类别
	 * @param:level菜单项属于第几级
	 * @Description:获得会员对应的操作菜单项，用于添加角色时分配菜单的项使用
	 */
	public List queryCommon(String syscode,String level,String menu_right){
		List<Map<String, String>> menuoneListNew = new ArrayList<Map<String, String>>();
		List<Map<String, String>> returnList = new ArrayList<Map<String, String>>();
		
		//获得菜单列表
		Map Map = this.getListMap(syscode, level);
		menuoneListNew = this.sysmenuDao.getList(Map);
		String [] menuList =null;
		//all为超级管理员（系统初始化管理员）
		if("all".equals(menu_right)){
			return menuoneListNew;
		}else{
			menuList=menu_right.replace(" ", "").split(",");
		}
		for (int i = 0; i < menuList.length; i++) {
			String menu_right_x = menuList[i];
			for (Map<String, String> ob : menuoneListNew) {
				String menu_right_y = ob.get("menu_id").toString();
				if (menu_right_x.equals(menu_right_y)) {
					returnList.add(ob);
				}
			}
		}
		return returnList;
	}
	
}
