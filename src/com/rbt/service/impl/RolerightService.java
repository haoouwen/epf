/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: RolerightService.java 
 */
package com.rbt.service.impl;

import it.unimi.dsi.fastutil.Hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IRolerightDao;
import com.rbt.model.Roleright;
import com.rbt.service.IRolerightService;

/**
 * @function 功能 角色权限业务实现类
 * @author 创建人 QJY
 * @date 创建日期 Jun 28, 2014 4:03:16 PM
 */
@Service
public class RolerightService extends GenericService<Roleright, String>
		implements IRolerightService {

	IRolerightDao rolerightDao;

	@Autowired
	public RolerightService(IRolerightDao rolerightDao) {
		super(rolerightDao);
		this.rolerightDao = rolerightDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IRolerightService#getRolerightMenuList(java.util.Map)
	 */
	public List getRolerightMenuList(Map map) {
		return this.rolerightDao.getRolerightMenuList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IRolerightService#getRolerightLogsList(java.util.Map)
	 */
	public List getRolerightLogsList(Map map) {
		return rolerightDao.getRolerightLogsList(map);
	}
	
	/**
	 * @author:HXM
	 * @date:Jun 4, 20143:05:43 PM
	 * @param:syscode标记权限操作类型
	 * @param:权限操作字符串
	 * @Description:获得权限操作集合，用于添加 角色 时用于分配用户能添加那些指定的操作权限
	 */
	public List getOperRightList(String syscode,String oper_right){
		List<Map<String, String>> allOperList = new ArrayList<Map<String, String>>();
		List<Map<String, String>> returnList = new ArrayList<Map<String, String>>();
		
		//获得菜单列表
		Map map = new HashMap();
		map.put("syscode", syscode);
		allOperList = this.rolerightDao.getList(map);
		String [] operList =null;
		//all为超级管理员（系统初始化管理员）
		if("all".equals(oper_right)){
			return allOperList;
		}else{
			operList=oper_right.replace(" ", "").split(",");
		}
		for (int i = 0; i < operList.length; i++) {
			String oper_right_x = operList[i];
			for (Map<String, String> ob : allOperList) {
				String oper_right_y = ob.get("right_id").toString();
				if (oper_right_x.equals(oper_right_y)) {
					returnList.add(ob);
				}
			}
		}
		return returnList;
	}


}
