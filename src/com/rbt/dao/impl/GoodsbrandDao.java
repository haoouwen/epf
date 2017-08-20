/*
 
 * Package:com.rbt.dao.impl
 * FileName: GoodsbrandDao.java 
 */
package com.rbt.dao.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.lucene.queryParser.ParseException;
import org.springframework.stereotype.Repository;
import com.rbt.dao.IGoodsbrandDao;
import com.rbt.model.Goodsbrand;

/**
 * @function 功能  商品品牌表dao层业务接口实现类
 * @author 创建人 HXK
 * @date 创建日期 Mon Feb 27 12:42:32 CST 2014
 */
@Repository
public class GoodsbrandDao extends GenericDao<Goodsbrand,String> implements IGoodsbrandDao {
	
	public GoodsbrandDao() {
		super(Goodsbrand.class);
	}
	/**	
	 * @author : HXK
	 * @param :随机读取品牌20条
	 * @throws IOException 
	 * @throws ParseException 
	 * @Method Description :相关品牌
	 */
	public List getBrandRandList(Map map) throws ParseException, IOException{
		return this.getSqlMapClientTemplate().queryForList("goodsbrand.getRandList",map);
	}
	
}
