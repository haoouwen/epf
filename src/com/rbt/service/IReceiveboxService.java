/*
  
 
 * Package:com.rbt.servie
 * FileName: IReceiveboxService.java 
 */
package com.rbt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.model.Receivebox;

/**
 * @function 功能 记录收件箱信息Service层业务接口实现类
 * @author  创建人 LSQ
 * @date  创建日期 Wed Jan 30 15:37:25 CST 2014
 */

public interface IReceiveboxService extends IGenericService<Receivebox,String>{
	
	public void deletelogic(HashMap map);
	/**
	 * @author : HZX
	 * @date : Feb 11, 2014 4:15:51 PM
	 * @Method Description :处理删除
	 */
	public void dealDelete(String chb_id);
	
}

