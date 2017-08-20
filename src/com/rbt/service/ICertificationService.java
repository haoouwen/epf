/*
  
 
 * Package:com.rbt.servie
 * FileName: ICertificationService.java 
 */
package com.rbt.service;

import com.rbt.model.Certification;

/**
 * @function 功能 记录企业身份认证信息Service层业务接口实现类
 * @author  创建人 LJQ
 * @date  创建日期 Wed Nov 30 13:34:38 CST 2014
 */

public interface ICertificationService extends IGenericService<Certification,String>{
	/**
	 * @Method Description :审核会员实名认证
	 * @author : LJQ
	 * @date : Dec 2, 2014 4:17:25 PM
	 */
	public void auditState(Certification t);
	/**
	 * @Method Description : 插入会员信用指数表
	 * @author : LJQ
	 * @date : Dec 2, 2014 3:15:20 PM
	 */
	//第一个参数需要操作的CUST_ID,第二个参数传正负一，第三个参数指数值,第四个参数理由类型，第五个参数为理由内容,第六个文件名称
	public void creditChangeNum(String cust_id,int symbol,String fund_value,String reason_type,String reason,String title);
	
	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 1:15:37 PM
	 * @Method Description：对审核通过的实名认证删除操作减分
	 */
	public void del(String id);
}

