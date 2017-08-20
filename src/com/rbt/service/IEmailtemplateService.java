/*
  
 
 * Package:com.rbt.servie
 * FileName: IEmailtemplateService.java 
 */
package com.rbt.service;

import com.rbt.model.Emailtemplate;

/**
 * @function 功能 记录会员邮件发送模板信息Service层业务接口实现类
 * @author 创建人 CYC
 * @date 创建日期 Fri Jul 15 09:13:20 CST 2014
 */

public interface IEmailtemplateService extends IGenericService<Emailtemplate,String>{
	
	/**
	 * 方法描述：根据记录会员邮件发送模板的编号找出记录会员邮件发送模板信息
	 * 
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public Emailtemplate getEmailtemplateByTempcode(String temp_code);
}
