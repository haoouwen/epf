/*
  
 
 * Package:com.rbt.dao
 * FileName: IGoodsbrandDao.java 
 */
package com.rbt.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.lucene.queryParser.ParseException;

import com.rbt.model.Goodsbrand;

/**
 * @function 功能 商品品牌表dao层业务接口
 * @author  创建人HXK
 * @date  创建日期 Mon Feb 27 12:42:32 CST 2014
 */

public interface IGoodsbrandDao extends IGenericDao<Goodsbrand,String>{
	
	/**	
	 * @author : HXK
	 * @param :随机读取品牌20条
	 * @throws IOException 
	 * @throws ParseException 
	 * @Method Description :相关品牌
	 */
	public List getBrandRandList(Map map) throws ParseException, IOException;
	
}

