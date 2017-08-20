/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: WaterfallService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IWaterfallDao;
import com.rbt.model.Waterfall;
import com.rbt.service.IWaterfallService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 瀑布布局Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Fri Dec 28 11:10:08 CST 2014
 */
@Service
public class WaterfallService extends GenericService<Waterfall,String> implements IWaterfallService {
	
	IWaterfallDao waterfallDao;

	@Autowired
	public WaterfallService(IWaterfallDao waterfallDao) {
		super(waterfallDao);
		this.waterfallDao = waterfallDao;
	}
	
}

