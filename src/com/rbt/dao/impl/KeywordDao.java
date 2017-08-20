/*
 
 * Package:com.rbt.dao.impl
 * FileName: KeywordDao.java 
 */
package com.rbt.dao.impl;

import com.rbt.dao.IKeywordDao;
import com.rbt.model.Keyword;
import org.springframework.stereotype.Repository;

@Repository
public class KeywordDao extends GenericDao<Keyword,String> implements IKeywordDao {
	
	public KeywordDao() {
		super(Keyword.class);
	}

	public void updateKeyNums(Keyword keyword) {
		this.getSqlMapClientTemplate().update("keyword.updatekeynums", keyword);
	}

}
