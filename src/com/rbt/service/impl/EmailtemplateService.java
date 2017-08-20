/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: EmailtemplateService.java 
 */
package com.rbt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IEmailtemplateDao;
import com.rbt.model.Emailtemplate;
import com.rbt.service.IEmailtemplateService;

/**
 * @function 功能 记录会员邮件发送模板信息Service层业务接口实现
 * @author 创建人 CYC
 * @date 创建日期 Fri Jul 15 09:13:20 CST 2014
 */
@Service
public class EmailtemplateService extends GenericService<Emailtemplate,String> implements IEmailtemplateService {

	
	IEmailtemplateDao emailtemplateDao;

	@Autowired
	public EmailtemplateService(IEmailtemplateDao emailtemplateDao) {
		super(emailtemplateDao);
		this.emailtemplateDao = emailtemplateDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IEmailtemplateService#getEmailtemplateByTempcode(java.lang.String)
	 */
	public Emailtemplate getEmailtemplateByTempcode(String temp_code) {
		return this.emailtemplateDao.getEmailtemplateByTempcode(temp_code);
	}

}
