/*
  
 
 * Package:com.rbt.servie
 * FileName: IAssociationkeywordsService.java 
 */
package com.rbt.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.lucene.queryParser.ParseException;

import com.rbt.model.Associationkeywords;

/**
 * @function 功能 联想关键词Service层业务接口实现类
 * @author  创建人 HXK
 * @date  创建日期 Wed Jun 24 11:18:16 CST 2015
 */

public interface IAssociationkeywordsService extends IGenericService<Associationkeywords,String>{
	public List getExAttrList(Map map);
	/**
	 * 方法描述：构造LIST用于信息的回选
	 * 
	 * @return
	 * @throws Exception
	 */
	public List cat_attr_list(String cat_ids,List cat_attr_list);
	public String associationToCategory(String ass_title) throws ParseException,IOException;
	
}

