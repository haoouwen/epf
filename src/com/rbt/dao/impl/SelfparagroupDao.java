/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: SelfparagroupDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ISelfparagroupDao;
import com.rbt.model.Selfparagroup;

/**
 * @function 功能  记录自定义参数组信息dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Sat Feb 16 13:36:38 CST 2014
 */
@Repository
public class SelfparagroupDao extends GenericDao<Selfparagroup,String> implements ISelfparagroupDao {
	
	public SelfparagroupDao() {
		super(Selfparagroup.class);
	}
	public void deleteByGoodsid(String goods_id) {
		this.getSqlMapClientTemplate().delete("selfparagroup.deleteByGoodsId", goods_id);
	}
	
}

