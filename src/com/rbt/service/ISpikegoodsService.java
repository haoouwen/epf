/*
  
 
 * Package:com.rbt.servie
 * FileName: ISpikegoodsService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Spikegoods;

/**
 * @function 功能 秒杀商品Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Fri Mar 29 15:32:29 CST 2014
 */

public interface ISpikegoodsService extends IGenericService<Spikegoods,String>{
	
	
	@SuppressWarnings("unchecked")
	public int getWebCount(Map map);
	
	@SuppressWarnings("unchecked")
	public List getWebList(Map map);
	/**
	 * @author : HZX
	 * @date : Feb 13, 2014 2:29:23 PM
	 * @Method Description :绑定前台页面所需的值
	 */
	public Map getIndexMap(int cfg_spike_day,String spikeday,String time,int pages_s,int pageSize_s)throws Exception;
	
	/**
	 * @author:HXM
	 * @date:May 9, 20149:30:42 AM
	 * @param:
	 * @Description:为集合添加一个标识来判断，团购是在开始前后，或是进行
	 */
	public List changList(List list)throws Exception;
}

