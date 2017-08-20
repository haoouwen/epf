/*
 * Package:com.rbt.servie.impl
 * FileName: TokensInfoService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ITokensInfoDao;
import com.rbt.model.TokensInfo;
import com.rbt.service.ITokensInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 IOSAndroidTokenService层业务接口实现
 * @author 创建人 HXK
 * @date 创建日期 Wed Jul 06 12:11:35 CST 2016
 */
@Service
public class TokensInfoService extends GenericService<TokensInfo,String> implements ITokensInfoService {
	
	ITokensInfoDao tokensInfoDao;

	@Autowired
	public TokensInfoService(ITokensInfoDao tokensInfoDao) {
		super(tokensInfoDao);
		this.tokensInfoDao = tokensInfoDao;
	}
	
}

