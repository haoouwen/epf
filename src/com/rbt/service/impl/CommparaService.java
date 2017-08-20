/*
  
 
 * Package:com.rbt.service.impl
 * FileName: CommparaService.java 
 */

package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.ICommparaDao;
import com.rbt.model.Commpara;
import com.rbt.service.ICommparaService;
/**
 * @function 功能  系统参数务实现类
 * @author  创建人 HXK
 * @date  创建日期  July 6, 2014
 */
@Service
public class CommparaService extends GenericService<Commpara,String> implements ICommparaService {
	
	/**
	 * 参数管理dao实现层
	 */
	ICommparaDao commparaDao;
	@Autowired
	public CommparaService(ICommparaDao commparaDao) {
		super(commparaDao);
		this.commparaDao = commparaDao;
	}
	/* (non-Javadoc)
	 * @see com.rbt.service.ICommparaService#getCommparaGroupList(java.util.Map)
	 */
	public List getCommparaGroupList(Map map) {
		return this.commparaDao.getCommparaGroupList(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.ICommparaService#getCommparaIndexList(java.util.Map)
	 */
	public List getCommparaIndexList(Map map) {
		return this.commparaDao.getCommparaIndexList(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.ICommparaService#getWebCommparaList(java.util.Map)
	 */
	public List getWebCommparaList(Map map) {
		return this.commparaDao.getWebCommparaList(map);
	}
	

	/**
	 * @author : LJQ
	 * @date : Apr 15, 2014 7:03:55 PM
	 * @Method Description :获取对应的参数列表
	 */
	public List getCommparaList(String para_code){
		Map map = new HashMap();
		map.put("para_code", para_code);
		List list=this.commparaDao.getList(map);
		return list;
	}

	/**
	 * @author:HXM
	 * @date:May 30, 20149:35:42 AM
	 * @param:
	 * @Description:分组查询通过cust_id 分组
	 */
	public List getListByGroup(Map map){
		return this.commparaDao.getListByGroup(map);
	}
	
	/**
	 * @author:HXM
	 * @date:May 30, 201410:58:48 AM
	 * @param:
	 * @Description:获得分组以后的条数
	 */
	public int getGroupCount(Map map){
		return this.commparaDao.getGroupCount(map);
	}

}
