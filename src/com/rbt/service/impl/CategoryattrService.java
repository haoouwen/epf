/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: CategoryattrService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.ICategoryDao;
import com.rbt.dao.ICategoryattrDao;
import com.rbt.dao.ISysmenuDao;
import com.rbt.dao.ISysmoduleDao;
import com.rbt.model.Category;
import com.rbt.model.Categoryattr;
import com.rbt.model.Sysmodule;
import com.rbt.service.ICategoryattrService;

/**
 * @function 功能 产品属性列表Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Tue Jul 19 08:48:08 CST 2014
 */
@Service
public class CategoryattrService  extends GenericService<Categoryattr,String> implements ICategoryattrService {

	/*
	 * 产品属性列表Dao层接口
	 */
	ICategoryattrDao categoryattrDao;
	@Autowired
	ISysmoduleDao sysmoduleDao;
	@Autowired
    ICategoryDao categoryDao;
	@Autowired
	public CategoryattrService(ICategoryattrDao categoryattrDao) {
		super(categoryattrDao);
		this.categoryattrDao = categoryattrDao;
	}

	public List getCatAttrList(Map map){
		return this.categoryattrDao.getCatAttrList(map);
	}

	public void deleteAttr_id(String id){
		this.categoryattrDao.deleteAttr_id(id);
	}
	/**
	 * @author：XBY
	 * @date：Feb 11, 2014 3:30:21 PM
	 * @Method Description：添加之前跳转判断
	 */
	public Map getMap(String url_up_id,String level,String up_level_id,String up_level,String modtype_name_id){
		Map map=new HashMap();
		if((url_up_id!=null && !"".equals(url_up_id)) && (level!=null && !"".equals(level))){
			//等级为一级  level页面传值
			if("1".equals(level)){
			}
			//等级超过2级
			else{
			String	now_up_id = url_up_id;
				for(int i=1;i<(Integer.parseInt(level));i++){
				Category category = categoryDao.get(now_up_id);
					if(category==null){
						category = new Category();
					}
					up_level_id=category.getUp_cat_id();
					if(!"".equals(up_level_id)){
						up_level=up_level_id+','+up_level;
						now_up_id=up_level_id;
						
					}
					
				}	
				map.put("up_level", up_level);
			}		
		}		
		if(modtype_name_id!=null && !"".equals(modtype_name_id)){
			Sysmodule sysmodule=this.sysmoduleDao.get(modtype_name_id);
			//模块名称
		 String modtype_name =sysmodule.getModule_name();
		 map.put("modtype_name", modtype_name);
		}
		return map;
	}
}

