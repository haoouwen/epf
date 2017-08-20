/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: Index_historyService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IIndex_historyDao;
import com.rbt.model.Index_history;
import com.rbt.service.IIndex_historyService;

/**
 * @function 功能 记录已经索引过的信息Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Fri Aug 12 10:12:10 CST 2014
 */
@Service
public class Index_historyService  extends GenericService<Index_history,String>  implements IIndex_historyService {

	/*
	 * 记录已经索引过的信息Dao层接口
	 */	
	IIndex_historyDao index_historyDao;

	@Autowired
	public Index_historyService(IIndex_historyDao index_historyDao) {
		super(index_historyDao);
		this.index_historyDao = index_historyDao;
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IIndex_historyService#insertIndex(java.util.List)
	 */
	public void insertIndex(List list) {
		this.index_historyDao.insertIndex(list);
	}
	
	public void deleteIndex_historyList(List list) {
		this.index_historyDao.deleteIndex_historyList(list);
	}
}

