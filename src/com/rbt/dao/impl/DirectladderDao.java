/*
 
 * Package:com.rbt.dao.impl
 * FileName: DirectladderDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IDirectladderDao;
import com.rbt.model.Directladder;

/**
 * @function 功能  预售商品阶梯价格dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Wed Jul 17 13:28:18 CST 2014
 */
@Repository
public class DirectladderDao extends GenericDao<Directladder,String> implements IDirectladderDao {
	
	public DirectladderDao() {
		super(Directladder.class);
	}
	//通过direct_id获取Directladder的对象
	public Directladder getByDirectID(String direct_id) {
		return  (Directladder)this.getSqlMapClientTemplate().queryForObject("directladder.getByDirectId", direct_id);
	}
	public void deleteDirectID(String id) {
		this.getSqlMapClientTemplate().delete("directladder.deleteByDirectId",id);
	}
}

