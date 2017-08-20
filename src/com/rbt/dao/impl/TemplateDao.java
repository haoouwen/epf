/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: TemplateDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ITemplateDao;
import com.rbt.model.Template;

/**
 * @function 功能  瀑布流模板dao层业务接口实现类
 * @author 创建人 XBY
 * @date 创建日期 Wed Feb 19 17:12:59 CST 2014
 */
@Repository
public class TemplateDao extends GenericDao<Template,String> implements ITemplateDao {
	
	public TemplateDao() {
		super(Template.class);
	}
	
}

