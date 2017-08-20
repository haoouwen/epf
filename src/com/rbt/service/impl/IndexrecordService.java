/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: IndexrecordService.java 
 */
package com.rbt.service.impl;

import com.rbt.dao.IIndexrecordDao;
import com.rbt.model.Indexrecord;
import com.rbt.service.IIndexrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录更新的索引记录Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Wed Jul 18 15:42:50 CST 2014
 */
@Service
public class IndexrecordService extends GenericService<Indexrecord,String> implements IIndexrecordService {
	
	IIndexrecordDao indexrecordDao;

	@Autowired
	public IndexrecordService(IIndexrecordDao indexrecordDao) {
		super(indexrecordDao);
		this.indexrecordDao = indexrecordDao;
	}
	
}

