/*
 
 * Package:com.rbt.dao.impl
 * FileName: GoodsorderDao.java 
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
import com.rbt.dao.IGoodsorderDao;
import com.rbt.model.Goodsorder;

/**
 * @function 功能  商品订单dao层业务接口实现类
 * @author 创建人 LHY
 * @date 创建日期 Fri Feb 01 16:00:36 CST 2014
 */
@Repository
public class GoodsorderDao extends GenericDao<Goodsorder,String> implements IGoodsorderDao {
	
	public GoodsorderDao() {
		super(Goodsorder.class);
	}
	
	public List getWebList(Map map){
		return this.getSqlMapClientTemplate().queryForList("goodsorder.getWebList", map);
	}
	
	public int getWebCount(Map map) {
		Map infoMap = (Map)this.getSqlMapClientTemplate().queryForObject("goodsorder.getWebCount", map);
		return (infoMap!=null && infoMap.get("ct")!=null)?Integer.parseInt(infoMap.get("ct").toString()):0;
	}
	
	public int getWebGoodsCount(Map map) {
		Map infoMap = (Map)this.getSqlMapClientTemplate().queryForObject("goodsorder.getWebGoodsCount", map);
		return (infoMap!=null && infoMap.get("ct")!=null)?Integer.parseInt(infoMap.get("ct").toString()):0;
	}
	
	public void updateState(Map map){
		this.getSqlMapClientTemplate().update("goodsorder.updateState", map);
	}
	public void update(Map<String,String> map){
		this.getSqlMapClientTemplate().update(getModelName()+".updateState", map);
	}
	//cusr_id
	public List getCustlist(String custID){
		return this.getSqlMapClientTemplate().queryForList("goodsorder.getCustlist",custID);
	}
	/**
	 * @author : QJY
	 * @date : Apr 19, 2015 11:05:33 AM
	 * @Method Description :批量发货
	 */
    public void updateOrderWeight(final List list)throws Exception{
    	this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("goodsorder.updateOrderWeight", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
    }
	
	public List getoverList(Map map){
		return this.getSqlMapClientTemplate().queryForList("goodsorder.getoverList", map);
	}

	public List getTake(Map map) {
		return this.getSqlMapClientTemplate().queryForList("goodsorder.getTakeList",map);
	}

	public int getOrderCount(Map map){
		Map infoMap = (Map)this.getSqlMapClientTemplate().queryForObject("goodsorder.getorderCount", map);
		return (infoMap!=null && infoMap.get("ct")!=null)?Integer.parseInt(infoMap.get("ct").toString()):0;
	}
	
	public int getRefundCount(Map map){
		Map infoMap = (Map)this.getSqlMapClientTemplate().queryForObject("goodsorder.getrefundCount", map);
		return (infoMap!=null && infoMap.get("ct")!=null)?Integer.parseInt(infoMap.get("ct").toString()):0;
	}
	
	//通过交易流水号
	public Goodsorder getByTrxID(String paytrxID){
		return (Goodsorder)this.getSqlMapClientTemplate().queryForObject("goodsorder.getByTrxID", paytrxID);
	}
	
	/**
	 * 区域订单集合
	 */
	public List getAreaOrderList(Map map){
		return this.getSqlMapClientTemplate().queryForList("goodsorder.getAreaOrderList", map);
	}
	
	public int getAreaCount(Map map)throws Exception{
		Map infoMap = (Map)this.getSqlMapClientTemplate().queryForObject("goodsorder.getAreaCount", map);
		return (infoMap!=null && infoMap.get("ct")!=null)?Integer.parseInt(infoMap.get("ct").toString()):0;
	}
	
	/**
	 * 自动确认收货
	 */
	public List getConfirmReceiptOrderList(Map map){
		return this.getSqlMapClientTemplate().queryForList("goodsorder.getConfirmReceiptOrderList", map);
	}
	
	public int getConfirmReceiptOrderCount(Map map)throws Exception{
		Map infoMap = (Map)this.getSqlMapClientTemplate().queryForObject("goodsorder.getConfirmReceiptCount", map);
		return (infoMap!=null && infoMap.get("ct")!=null)?Integer.parseInt(infoMap.get("ct").toString()):0;
	}
	
	
}

