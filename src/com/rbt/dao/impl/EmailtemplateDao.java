/*
 
 * Package:com.rbt.dao.impl
 * FileName: EmailtemplateDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.IEmailtemplateDao;
import com.rbt.model.Emailtemplate;

/**
 * @function 功能 记录会员邮件发送模板信息dao层业务接口实现类
 * @author 创建人 CYC
 * @date 创建日期 Fri Jul 15 09:13:20 CST 2014
 */
@Repository
public class EmailtemplateDao extends GenericDao<Emailtemplate,String> implements IEmailtemplateDao {
 
	public EmailtemplateDao() {
		super(Emailtemplate.class);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IEmailtemplateDao#getEmailtemplateByTempcode(java.lang.String)
	 */
	public Emailtemplate getEmailtemplateByTempcode(String temp_code) {
		return (Emailtemplate) this.getSqlMapClientTemplate().queryForObject(
				"emailtemplate.getByTempcode", temp_code);
	}

}
