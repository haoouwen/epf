/*
 * Package:com.rbt.dao.impl
 * FileName: TokensInfoDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ITokensInfoDao;
import com.rbt.model.TokensInfo;

/**
 * @function 功能  IOSAndroidTokendao层业务接口实现类
 * @author 创建人 HXK
 * @date 创建日期 Wed Jul 06 12:11:35 CST 2016
 */
@Repository
public class TokensInfoDao extends GenericDao<TokensInfo,String> implements ITokensInfoDao {
	
	public TokensInfoDao() {
		super(TokensInfo.class);
	}
	
}

