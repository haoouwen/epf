/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: AreasetService.java 
 */
package com.rbt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.dao.IAreasetDao;
import com.rbt.function.AreaFuc;
import com.rbt.model.Areaset;
import com.rbt.service.IAreasetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录区域设置信息Service层业务接口实现
 * @author 创建人 HXK
 * @date 创建日期 Wed Mar 28 13:22:27 CST 2014
 */
@Service
public class AreasetService extends GenericService<Areaset,String> implements IAreasetService {
	
	IAreasetDao areasetDao;

	@Autowired
	public AreasetService(IAreasetDao areasetDao) {
		super(areasetDao);
		this.areasetDao = areasetDao;
	}
	public void deleteByShopid(String id){
		this.areasetDao.deleteByShopid(id);
	}
	
	/**
	 * 方法描述：构造LIST用于信息的回选
	 * @return
	 * @throws Exception
	 */
	public void area_attr_list(String area_ids,List area_attr_list) {
		String ids[] = area_ids.split("\\|");
		area_attr_list = new ArrayList();
		for (int i = 0; i < ids.length; i++) {
			Map listMap = new HashMap();
			String id = ids[i].replace(" ", "");
			listMap.put("id", id);
			String areaName = AreaFuc.getAreaNameByMap(id);
			listMap.put("val", areaName);
			if (!id.equals("") && !areaName.equals("")) {
				area_attr_list.add(i, listMap);
			}
		}
	}
	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 9:47:27 AM
	 * @Method Description：List判断处理
	 */
	public List replaceList(List areasetList){
		if (areasetList != null && areasetList.size() > 0) {
			for (int i = 0; i < areasetList.size(); i++) {
				HashMap map = new HashMap();
				map = (HashMap) areasetList.get(i);
				if (map.get("end_area") != null) {
					map.put("end_area", AreaFuc.getAreaNameByMap(map.get("end_area").toString()));
				}
				if (map.get("start_area") != null) {
					map.put("start_area", AreaFuc.getAreaNameByMap(map.get("start_area").toString()));
				}
			}
		}
		return areasetList;
	}
}

