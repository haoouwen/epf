/*
 
 * Package:com.rbt.servie.impl
 * FileName: NavtabService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.dao.IGoodsDao;
import com.rbt.dao.INavtabDao;
import com.rbt.dao.ISysmenuDao;
import com.rbt.model.Navtab;
import com.rbt.service.INavtabService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 导航标签信息Service层业务接口实现
 * @author 创建人 ZMS
 * @date 创建日期 Wed Aug 12 20:56:05 CST 2015
 */
@Service
public class NavtabService extends GenericService<Navtab,String> implements INavtabService {
	
	INavtabDao navtabDao;
	@Autowired
	IGoodsDao goodsDao;
	@Autowired
	public NavtabService(INavtabDao navtabDao) {
		super(navtabDao);
		this.navtabDao = navtabDao;
	}
	//找出标签相对应的商品数量
	public List getNavtabList(List navtabList) {
		if (navtabList != null && navtabList.size() > 0) {
			int adminnum = 0;
			Map aMap = new HashMap();
			Map bMap = new HashMap();
			for (int i = 0; i < navtabList.size(); i++) {
				aMap = (Map) navtabList.get(i);
				if (aMap.get("tab_id") != null) {
					bMap.put("tab_id", aMap.get("tab_id"));
					adminnum = goodsDao.getCount(bMap);
				}
				aMap.put("adminnum", adminnum);
				navtabList.set(i, aMap);
			}
		}
		return navtabList;
	}
	
	public Navtab getByTaxNumber(String tab_number) {
		return (Navtab)navtabDao.getByTaxNumber(tab_number);
	}
	
	
}

