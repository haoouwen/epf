/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: AdvposService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IAdvposDao;
import com.rbt.model.Advpos;
import com.rbt.service.IAdvposService;

/**
 * @function 功能 广告位Service层业务接口实现
 * @author 创建人 QJY
 * @date 创建日期 Jul 7, 2014 5:41:01 PM
 */
@Service
public class AdvposService extends GenericService<Advpos,String> implements IAdvposService {

	IAdvposDao advposDao;

	@Autowired
	public AdvposService(IAdvposDao advposDao) {
		super(advposDao);
		this.advposDao = advposDao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.rbt.service.IAdvposService#updateAdvposBatch(java.util.List)
	 */
	public void updateAdvposBatch(List list) {
		this.advposDao.updateAdvposBatch(list);
	}

	/*
	 * (non-Javadoc)
	 * @see com.rbt.service.IAdvposService#getPosNameList(java.util.Map)
	 */
	public List getPosNameList(Map map) {
		return this.advposDao.getPosNameList(map);
	}

}
