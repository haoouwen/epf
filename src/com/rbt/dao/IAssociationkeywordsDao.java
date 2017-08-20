/*
  
 
 * Package:com.rbt.dao
 * FileName: IAssociationkeywordsDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Associationkeywords;

/**
 * @function 功能 联想关键词dao层业务接口
 * @author  创建人HXK
 * @date  创建日期 Wed Jun 24 11:18:16 CST 2015
 */

public interface IAssociationkeywordsDao extends IGenericDao<Associationkeywords,String>{
	public List getExAttrList(Map map);	
}

