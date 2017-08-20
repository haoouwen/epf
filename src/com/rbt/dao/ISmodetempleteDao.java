/*
  
 
 * Package:com.rbt.dao
 * FileName: ISmodetempleteDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Emailtemplate;
import com.rbt.model.Goods;
import com.rbt.model.Smodetemplete;

/**
 * @function 功能 记录区域设置信息dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Thu Jan 10 10:52:23 CST 2014
 */

public interface ISmodetempleteDao extends IGenericDao<Smodetemplete,String>{
	/**
	 * 方法描述：根据配送模板的编号找出记录会员邮件发送模板信息
	 * 
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public Smodetemplete getSmodetempleteBySmodeId(String smode_id);
}

