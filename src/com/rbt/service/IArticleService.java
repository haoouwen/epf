/*
  
 
 * Package:com.rbt.servie
 * FileName: IArticleService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Article;

/**
 * @function 功能 记录文章管理表信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Wed Feb 20 10:44:52 CST 2014
 */

public interface IArticleService extends IGenericService<Article,String>{
	/**
	 * @author：XBY
	 * @date：Feb 13, 2014 3:29:44 PM
	 * @Method Description：获取详细页面信息
	 */
   public Map getDetail(String id);
   
   /**
	 * @author：XBY
	 * @date：Feb 13, 2014 3:59:15 PM
	 * @Method Description：分类列文章
	 */
	public Map getAttrDetail(String cat_attr,String page_cat_attr);
}

