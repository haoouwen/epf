/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: MemroleService.java 
 */
package com.rbt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.dao.IMemberuserDao;
import com.rbt.dao.IMemroleDao;
import com.rbt.dao.ISysmenuDao;
import com.rbt.model.Memrole;
import com.rbt.service.IMemroleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 商城后台角色信息Service层业务接口实现
 * @author 创建人 LSQ
 * @date 创建日期 Thu Jan 24 16:33:37 CST 2014
 */
@Service
public class MemroleService extends GenericService<Memrole,String> implements IMemroleService {
	
	IMemroleDao memroleDao;
	@Autowired
	IMemberuserDao memberuserDao;
	@Autowired
	ISysmenuDao sysmenuDao;
	@Autowired
	public MemroleService(IMemroleDao memroleDao) {
		super(memroleDao);
		this.memroleDao = memroleDao;
	}

	public List getmemroleList(List memroleList,String session_cust_id) {
		if (memroleList != null && memroleList.size() > 0) {
			int membernum = 0;
			Map aMap = new HashMap();
			Map bMap = new HashMap();
			for (int i = 0; i < memroleList.size(); i++) {
				aMap = (Map) memroleList.get(i);
				if (aMap.get("memrole_id") != null) {
					bMap.put("role_code", aMap.get("memrole_id"));
					bMap.put("cust_id", session_cust_id);
					membernum = this.memberuserDao.getCount(bMap);
				}
				aMap.put("membernum", membernum);
				memroleList.set(i, aMap);
			}
		}
		return memroleList;
	}

	public List queryCommon(String[] menu_right_x, String syscode,String level, String enabled) {
		List<Map<String, String>> menuoneListNew = new ArrayList<Map<String, String>>();
		List<Map<String, String>> returnList = new ArrayList<Map<String, String>>();
		String menu_right = "", menu_right_y = "";
		for (int i = 0; i < menu_right_x.length; i++) {
			menu_right = menu_right_x[i];
			// 获取会员后台一级菜单
			Map Map = new HashMap();
			if (syscode != null && !syscode.equals("")) {
				Map.put("syscode", syscode);
			}

			if (level != null && !level.equals("")) {
				Map.put("ro_menu_level", level);
			}
			if (enabled != null && !enabled.equals("")) {
				Map.put("enabled", enabled);
			}

			menuoneListNew = this.sysmenuDao.getList(Map);
			for (Map<String, String> ob : menuoneListNew) {
				menu_right_y = ob.get("menu_id").toString();
				if (menu_right.equals(menu_right_y)) {
					returnList.add(ob);
				}
			}

		}
		return returnList;
	}


	
}

