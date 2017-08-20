/*
  
 
 * Package:com.rbt.servie
 * FileName: ICapitalmanagementService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Capitalmanagement;

/**
 * @function 功能 运营商资金管理Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Fri Aug 23 14:17:23 CST 2014
 */

public interface ICapitalmanagementService extends IGenericService<Capitalmanagement,String>{
	/**
	 * @author : HZX
	 * @date : Aug 23, 2014 3:10:32 PM
	 * @Method Description :调整运营商资金
	 */
	public String fundUpdate(String radiochecked,String fund_num,String session_user_id,String reason,String remark) throws Exception;
}

