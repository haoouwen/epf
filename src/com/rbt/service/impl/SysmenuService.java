/*
  
 
 * Package:com.rbt.service.impl
 * FileName: SysmenuService.java 
 */

package com.rbt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IRoleDao;
import com.rbt.dao.ISysmenuDao;
import com.rbt.model.Role;
import com.rbt.model.Sysmenu;
import com.rbt.service.ISysmenuService;

/**
 * @function 功能 系统菜单业务层接口实现
 * @author  创建人 HXK
 * @date  创建日期  Jun 25, 2014
 */
@Service
public class SysmenuService extends GenericService<Sysmenu,String> implements ISysmenuService {
	
	ISysmenuDao sysmenuDao;
	@Autowired
	public SysmenuService(ISysmenuDao sysmenuDao) {
		super(sysmenuDao);
		this.sysmenuDao = sysmenuDao;
	}
	public void updateEnable(List list) {
		this.sysmenuDao.updateEnable(list);
	}
	public void updateEnabled(List list) {
		this.sysmenuDao.updateEnabled(list);
	}
	//递归更改菜单状态时，查询menu_id的拼串
	public List getEnableList(Map map){
		return this.sysmenuDao.getEnableList(map);
	}
	/**
	 * @author : HZX
	 * @date : Feb 10, 2014 3:02:45 PM
	 * @Method Description : 递归更新
	 */
	public void updateValid(String id, String enabled) {
			if(id==null ||id.length()<1||id.equals("")){
				return;
			}
			id=id.replace(" ","");
			//请求数据的子节点
			String child_str="";
			//更新菜单父节点的容器
			List list = new ArrayList();
			//将传入的id以，拆分
			String[] pid_str = id.split(",");
			for(int i=0;i<pid_str.length;i++){
				if(pid_str[i].trim().equals("")){
					return;
				}
				//子菜单列表
				Map pMap =new HashMap();
				pMap.put("up_menu_id", pid_str[i]);
				List childlist = this.sysmenuDao.getEnableList(pMap);
				if(childlist!=null && childlist.size() >0){
					for(int j=0;j<childlist.size();j++){
						Map listMap =(HashMap)childlist.get(0);
						if(listMap.get("menu_id_str")!=null){
							child_str+=listMap.get("menu_id_str")+",";
						}
					}
				}
				//判断是否最后一个字符是否为逗号
				if(child_str.lastIndexOf(",")>0){
					child_str=child_str.substring(0, child_str.lastIndexOf(","));
					//更新菜单有效
					Map map = new HashMap();
					map.put("enabled", enabled);
					map.put("menu_id",child_str);
					list.add(map);
					this.sysmenuDao.updateEnable(list);
					updateValid(child_str,enabled);
				}
			}
		
	}
	/**
	 * @author : LHY
	 * @Method Description : 递归调用删除数据
	 */
	public void recuDelete(String id) {
			if(id==null || id.equals(""))
				return ;
			String chhid_id="";
			List list=new ArrayList();
			String[] str_id=id.split(",");
			for (int i = 0; i < str_id.length; i++) {
				if(str_id[i].trim().equals("")){
					return;
				}
				Map chiMap=new HashMap();
				chiMap.put("up_menu_id",str_id[i]);
				//获取所有上级id为当前id的栏目
				List chi_list=this.sysmenuDao.getEnableList(chiMap);
				System.out.println(chi_list);
				if(chi_list!=null && chi_list.size()>0){
					Map listMap=null;
					for (int j = 0; j < chi_list.size(); j++) {
						listMap=(HashMap) chi_list.get(j);
						if(listMap.get("menu_id_str")!=null){
							chhid_id+=listMap.get("menu_id_str")+",";
						}
					}
				}
			}
			//判断是否最后一个字符是否为逗号，是则删除
			if(chhid_id.lastIndexOf(",")>0){
				chhid_id=chhid_id.substring(0, chhid_id.lastIndexOf(","));
			}
			//删除ID
			this.sysmenuDao.delete(id);
			recuDelete(chhid_id);
		
		
	}
	/**
	 * @author : HZX
	 * @date : Feb 10, 2014 3:23:58 PM
	 * @Method Description :匹配菜单串，若不存在删除节点
	 */
	public List removeMenuList(List menuList,String menu_string) {
		String menu_id = "";
		Map listMap=new HashMap();
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
		return menuList;
	}
}
