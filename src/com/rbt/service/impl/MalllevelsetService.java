/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: MalllevelsetService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.rbt.common.Constants;
import com.rbt.dao.IMalllevelsetDao;
import com.rbt.dao.ISysmenuDao;
import com.rbt.model.Malllevelset;
import com.rbt.service.IMalllevelsetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录商城会员等级信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Fri Dec 28 14:46:47 CST 2014
 */
@Service
public class MalllevelsetService extends GenericService<Malllevelset,String> implements IMalllevelsetService {
	
	IMalllevelsetDao malllevelsetDao;
	@Autowired
	ISysmenuDao sysmenuDao;
	private static final String SYSCODE_B2C_VALUE = "b2c";
	private static final String SYSCODE_SYS_VALUE = "sys";
	@Autowired
	public MalllevelsetService(IMalllevelsetDao malllevelsetDao) {
		super(malllevelsetDao);
		this.malllevelsetDao = malllevelsetDao;
	}
	/**
	 * @author : LJQ
	 * @date : Apr 15, 2014 5:00:16 PM
	 * @Method Description :根据会员类型获取相应的会员等级
	 */
	public List getMemLevelList(String mem_type){
		Map levelMap = new HashMap();
		levelMap.put("mem_type", mem_type);
		List list = this.malllevelsetDao.getList(levelMap);
		return list;
	}
	public List roleTree(HttpSession session) {
		List menuList;
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		// 操作者为会员则默认加入搜索条件
		if (session.getAttribute(Constants.CUST_TYPE).equals(Constants.MEMBER_TYPE)) {
			pageMap.put("syscode", SYSCODE_B2C_VALUE);
		} else {
			pageMap.put("syscode", SYSCODE_SYS_VALUE);
		}
		// 找出信息列表，放入list
		
		pageMap.put("syscode", "b2c");
		pageMap.put("enabled", "0");
		menuList = this.sysmenuDao.getList(pageMap);
		Map listMap = new HashMap();
		// 从数据库获取权限串,取出session中的菜单串,用于匹配菜单
		String menu_string = "";
		if (session.getAttribute("menu_right") != null) {
			menu_string = session.getAttribute("menu_right").toString();
		}
		// 操作者为运营商时运行方法
		if (session.getAttribute(Constants.CUST_TYPE).equals(Constants.MEMBER_TYPE)) {
			// 匹配菜单串，若不存在删除节点
			String menu_id = "";
			if (menuList != null && menuList.size() > 0) {
				for (int i = 0; i < menuList.size(); i++) {
					listMap = (HashMap) menuList.get(i);
					if (listMap.get("menu_id") != null) {
						menu_id = listMap.get("menu_id").toString();
						if (menu_string.indexOf(menu_id) < 0) {
							menuList.remove(i);
							// 因为remove方法在删除时去向前移位所以要减一
							i = i - 1;
						}
					}
				}
			}
		}
		
		return menuList;
	}
	
}

