/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: Extend_attrService.java 
 */
package com.rbt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.dao.IExtendattrDao;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Extendattr;
import com.rbt.service.IExtendattrService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录商品扩展属性信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Tue Jan 15 10:51:04 CST 2014
 */
@Service
public class ExtendattrService extends GenericService<Extendattr,String> implements IExtendattrService {
	
	IExtendattrDao extendattrDao;

	@Autowired
	public ExtendattrService(IExtendattrDao extend_attrDao) {
		super(extend_attrDao);
		this.extendattrDao = extend_attrDao;
	}
	public List getExAttrList(Map map){
		return this.extendattrDao.getExAttrList(map);
	}
	/**
	 * 方法描述：构造LIST用于信息的回选
	 * 
	 * @return
	 * @throws Exception
	 */
	public List cat_attr_list(String cat_ids,List cat_attr_list) {
		String ids[] = cat_ids.split("\\|");
		cat_attr_list = new ArrayList();
		for (int i = 0; i < ids.length; i++) {
			Map listMap = new HashMap();
			String id = ids[i].replace(" ", "");
			listMap.put("id", id);
			String catName = CategoryFuc.getCateNameByMap(id);
			listMap.put("val", catName);
			if (!id.equals("") && !catName.equals("")) {
				cat_attr_list.add(i, listMap);
			}
		}
		return cat_attr_list;
	}
}

