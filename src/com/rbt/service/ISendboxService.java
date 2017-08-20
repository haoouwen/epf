/*
  
 
 * Package:com.rbt.servie
 * FileName: ISendboxService.java 
 */
package com.rbt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.model.Sendbox;

/**
 * @function 功能 记录发件箱信息Service层业务接口实现类
 * @author  创建人 LSQ
 * @date  创建日期 Wed Jan 30 15:36:28 CST 2014
 */

public interface ISendboxService extends IGenericService<Sendbox,String>{
	
	
	public void deletelogic(HashMap map);
	
	public List getDelSend();
	/**
	 * @author : HZX
	 * @date : Feb 11, 2014 10:00:20 AM
	 * @Method Description :获取可发送的会员id
	 */
	public Map getCustStr(String[] cust_name_str,String send_cust_id);
	/**
	 * @author : HZX
	 * @date : Feb 11, 2014 10:24:59 AM
	 * @Method Description :保存到收件箱
	 */
	public void setReceivebox(String get_cust_id_str, String send_id);
}

