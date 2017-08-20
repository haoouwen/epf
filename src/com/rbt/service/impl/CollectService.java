/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: CollectService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ICollectDao;
import com.rbt.model.Collect;
import com.rbt.service.ICollectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录会员商机收藏信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Mon Jan 14 10:40:08 CST 2014
 */
@Service
public class CollectService extends GenericService<Collect,String> implements ICollectService {
	
	ICollectDao collectDao;

	@Autowired
	public CollectService(ICollectDao collectDao) {
		super(collectDao);
		this.collectDao = collectDao;
	}
	/**
	 * @author:HXM
	 * @date:May 30, 20149:35:42 AM
	 * @param:
	 * @Description:分组查询通过cust_id 分组
	 */
	public List getListByGroup(Map map){
		return this.collectDao.getListByGroup(map);
	}
	
	/**
	 * @author:HXM
	 * @date:May 30, 201410:58:48 AM
	 * @param:
	 * @Description:获得分组以后的条数
	 */
	public int getGroupCount(Map map){
		return this.collectDao.getGroupCount(map);
	}
}

