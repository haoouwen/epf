/*
 
 * Package:com.rbt.servie.impl
 * FileName: NavigationService.java 
 */
package com.rbt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.dao.INavigationDao;
import com.rbt.model.Navigation;
import com.rbt.service.INavigationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 导航列表信息Service层业务接口实现
 * @author 创建人 ZMS
 * @date 创建日期 Thu Aug 13 11:37:53 CST 2015
 */
@Service
public class NavigationService extends GenericService<Navigation,String> implements INavigationService {
	
	INavigationDao navigationDao;

	@Autowired
	public NavigationService(INavigationDao navigationDao) {
		super(navigationDao);
		this.navigationDao = navigationDao;
	}
	
	/**
	 * @Method Description :查询前台条数
	 */
	public int getWebCount(Map map) {
		return this.navigationDao.getWebCount(map);
	}

	/**
	 * @Method Description :查询前台列表
	 */
	public List getWebList(Map map) {
		return this.navigationDao.getWebList(map);
	}
	
	
	/**
	 * @function 批量插入导航商品数据
	 * @param goods_str
	 * @param tab_id
	 */
	public int  insertNavGoods(String goods_str,String tab_id,String tab_number){
			int insertfalseNumber=0;
		    List<Map<String,String>> mgList=new ArrayList();
		    Map sMap=new HashMap ();
		    sMap.put("tab_id", tab_id);
		    mgList=navigationDao.getList(sMap);
			String goodsstr[]=goods_str.split(",");
			if(goodsstr!=null&&goodsstr.length>0){
				for(String g_id:goodsstr){
					boolean flag=true;
					if(mgList.size()>0){
						for(Map<String,String> gmap:mgList){
							if(gmap!=null){
								//找出是否存在该商品
								if(g_id.equals(gmap.get("goods_id").toString())){
									flag=false;
									//累计失败个数
									insertfalseNumber++;
									break;
								}
							}
						}
					}
					if(flag){
						Navigation navigation=new Navigation();
						navigation.setGoods_id(g_id);
						navigation.setTab_id(tab_id);
						navigation.setSort_no("999");
						navigation.setTab_number(tab_number);
						navigationDao.insert(navigation);
					}
			    }
		    }
			return insertfalseNumber;
	}
	
}

