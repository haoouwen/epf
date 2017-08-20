/*
 
 * Package:com.rbt.dao.impl
 * FileName: PrintstyleDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IPrintstyleDao;
import com.rbt.model.Printstyle;

/**
 * @function 功能  记录打印样式信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Wed Feb 27 15:56:03 CST 2014
 */
@Repository
public class PrintstyleDao extends GenericDao<Printstyle,String> implements IPrintstyleDao {
	
	public PrintstyleDao() {
		super(Printstyle.class);
	}
	
	/**
	 * 通过快递单模板代码获取快递单模板实体
	 * @return
	 */
	public Printstyle getTemplate_code(String template_code) {
		return (Printstyle)this.getSqlMapClientTemplate().queryForObject("printstyle.getByTemplateCode", template_code);
	}
	
}

