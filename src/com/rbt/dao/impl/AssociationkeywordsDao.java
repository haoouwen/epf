/*
 
 * Package:com.rbt.dao.impl
 * FileName: AssociationkeywordsDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IAssociationkeywordsDao;
import com.rbt.model.Associationkeywords;

/**
 * @function 功能  联想关键词dao层业务接口实现类
 * @author 创建人 HXK
 * @date 创建日期 Wed Jun 24 11:18:16 CST 2015
 */
@Repository
public class AssociationkeywordsDao extends GenericDao<Associationkeywords,String> implements IAssociationkeywordsDao {
	
	public AssociationkeywordsDao() {
		super(Associationkeywords.class);
	}
	public List getExAttrList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("associationkeywords.getexAttrList",map);
	}
}

