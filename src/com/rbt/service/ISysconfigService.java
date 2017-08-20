/*
  
 
 * Package:com.rbt.servie
 * FileName: ISysconfigService.java 
 */
package com.rbt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.model.Aboutus;
import com.rbt.model.Sysconfig;

/**
 * @function 功能 系统变量设置Service层接口
 * @author 创建人 QJY
 * @date 创建日期 Jul 6, 2014 11:15:32 AM
 */

public interface ISysconfigService extends IGenericService<Sysconfig, String> {

	/**
	 * 方法描述：根据变量名找出网站名称信息
	 * 
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public Sysconfig getWebname(String var_value);

	/**
	 * 方法描述：批量修改系统参数状态
	 * 
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateSysconfigBatch(List list);

	/**
	 * 方法描述：取出全部
	 * 
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getAll();
	
	public void updateSys(Sysconfig sysconfig);
	
	public void updateByvarname(HashMap map);

	public void updateParma(Sysconfig sysconfig);
	
}
