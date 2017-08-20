/*
  
 
 * Package:com.rbt.servie
 * FileName: IRecycleService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Recycle;

/**
 * @function 功能 记录回收站信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Mon Mar 04 15:55:48 CST 2014
 */

public interface IRecycleService extends IGenericService<Recycle,String>{
	/**
	 * @author : HZX
	 * @date : Feb 11, 2014 3:16:10 PM
	 * @Method Description :处理删除回收站信息
	 */
	void dealDelete(String chb_id);
	/**
	 * @author : HZX
	 * @date : Feb 11, 2014 3:25:54 PM
	 * @Method Description :还原站内信
	 */
	void revert(String chb_id);
	/**
	 * @author : HZX
	 * @date : Feb 11, 2014 3:54:44 PM
	 * @Method Description :处理查看信件
	 */
	Map dealView(Recycle recycle);
	
}

