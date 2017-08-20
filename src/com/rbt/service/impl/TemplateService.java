/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: TemplateService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ITemplateDao;
import com.rbt.model.Template;
import com.rbt.service.ITemplateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 瀑布流模板Service层业务接口实现
 * @author 创建人 XBY
 * @date 创建日期 Wed Feb 19 17:12:59 CST 2014
 */
@Service
public class TemplateService extends GenericService<Template,String> implements ITemplateService {
	
	ITemplateDao templateDao;

	@Autowired
	public TemplateService(ITemplateDao templateDao) {
		super(templateDao);
		this.templateDao = templateDao;
	}
	
}

