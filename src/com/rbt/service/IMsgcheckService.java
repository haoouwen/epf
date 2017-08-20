/*
  
 
 * Package:com.rbt.servie
 * FileName: IMsgcheckService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Msgcheck;

/**
 * @function 功能 记录商城的活动管理Service层业务接口实现类
 * @author  创建人 LJQ
 * @date  创建日期 Wed Oct 10 16:38:35 CST 2014
 */

public interface IMsgcheckService extends IGenericService<Msgcheck,String>{
	public void deleteMsgcheck(Map msgCheckMap);
	public void deleteById(String id);
	/**
	 * @author : HZX
	 * @date : Feb 12, 2014 1:34:25 PM
	 * @Method Description :发送验证邮件
	 */
	public List sendCheckEmail(Msgcheck msgcheck, String session_cust_id);
	/**
	 * @Method Description :获取验证是否有效
	 * @author: 胡惜坤
	 * @date : Mar 18, 2016 2:58:54 PM
	 * @param 
	 * @return return_type
	 */
	public boolean checkMsgListToUse(String cp_phone,String cp_check);
	/**
	 * @Method Description :将验证码 修改已经验证
	 * @author: 胡惜坤
	 * @date : Mar 18, 2016 3:37:18 PM
	 * @param 
	 * @return void
	 */
	public void updateUse(String checkcode);
}

