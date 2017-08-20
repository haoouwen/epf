/*
  
 
 * Package:com.rbt.dao
 * FileName: IGoodsorderDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Goodsorder;

/**
 * @function 功能 商品订单dao层业务接口
 * @author  创建人LHY
 * @date  创建日期 Fri Feb 01 16:00:36 CST 2014
 */

public interface IGoodsorderDao extends IGenericDao<Goodsorder,String>{
	
	public List getWebList(Map map);
	
	public int getWebCount(Map map);
	
	public int getWebGoodsCount(Map map);
	
	public void updateState(Map map);
	
	public void update(Map<String,String> map);
	
	/**
	 * @author : QJY
	 * @date : Apr 19, 2015 11:05:33 AM
	 * @Method Description :批量发货
	 */
    public void updateOrderWeight(final List list)throws Exception;
	
	//会员后台卖家交易数据
	public List getoverList(Map map);
	
	public List getTake(Map map);
	
	public int getOrderCount(Map map);
	
	public int getRefundCount(Map map);
	
	public Goodsorder getByTrxID(String paytrxID);
	
	/**
	 * 区域订单集合
	 * @param map
	 * @return
	 */
	public List getAreaOrderList(Map map)throws Exception;
	
	public int getAreaCount(Map map)throws Exception;
	/**
	 * 自动确认收货
	 */
	public List getConfirmReceiptOrderList(Map map);
	
	public int getConfirmReceiptOrderCount(Map map)throws Exception;
	public List getCustlist(String buy_cust_id);
	
}

