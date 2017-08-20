/*
 * Package:com.rbt.servie
 * FileName: IGoodsshareService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Goodsshare;

/**
 * @function 功能 晒单Service层业务接口实现类
 * @author  创建人 QJY
 * @date  创建日期 Wed Oct 29 14:36:43 CST 2014
 */

public interface IGoodsshareService extends IGenericService<Goodsshare,String>{
	
	public List getWebList(Map map);
	public int getWebCount(Map map);
	
}

