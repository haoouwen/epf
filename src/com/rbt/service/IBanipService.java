/*
  
 
 * Package:com.rbt.service
 * FileName: IBan_IpService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Aboutus;
import com.rbt.model.Banip;;

/**
 * @function 功能  禁止IP管理业务层接口
 * @author  创建人 HXK
 * @date  创建日期  July 5, 2014
 */
public interface IBanipService extends IGenericService<Banip,String> {
	
	/**
	 * 方法描述：批量地区排序
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateAllIp(List list);
	/**
	 * @author：XBY
	 * @date：Feb 10, 2014 2:06:51 PM
	 * @Method Description：构造ipList
	 */
	public List banipidList(String banipid,String banip,String user_id);
}
