/*
 
 * Package:com.rbt.dao.impl
 * FileName: ComboDao.java 
 */
package com.rbt.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.rbt.dao.IComboDao;
import com.rbt.model.Combo;

/**
 * @function 功能  套餐表dao层业务接口实现类
 * @author 创建人 LHY
 * @date 创建日期 Mon Mar 25 15:09:17 CST 2014
 */
@Repository
public class ComboDao extends GenericDao<Combo,String> implements IComboDao {
	
	public ComboDao() {
		super(Combo.class);
	}
	
	/**
	 * @author : LJQ
	 * @date : May 15, 2014 2:20:01 PM
	 * @Method Description :获取套餐订单的商品ID串
	 */
	public String getComboGoodsid(String goods_id) {
		HashMap cgMap = new HashMap();
		cgMap.put("goods_id", goods_id);
		List list = this.getSqlMapClientTemplate().queryForList("combo.getComboGoodsid",cgMap);
		String goods_str="";
		if(list!=null && list.size()>0){
			Map map = (HashMap)list.get(0);
			if(map.get("goods_id_str")!=null){
				goods_str = map.get("goods_id_str").toString();
			}
		}
		return goods_str;
	}
	
	
}

