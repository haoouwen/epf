/*
  
 
 * Package:com.rbt.servie
 * FileName: IGoodsorderService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.rbt.model.Goodsorder;

/**
 * @function 功能 商品订单Service层业务接口实现类
 * @author  创建人 LHY
 * @date  创建日期 Fri Feb 01 16:00:36 CST 2014
 */

public interface IGoodsorderService extends IGenericService<Goodsorder,String>{
	@SuppressWarnings("unchecked")
	public int getWebCount(Map map);
	
	@SuppressWarnings("unchecked")
	public int getWebGoodsCount(Map map);
	
	@SuppressWarnings("unchecked")
   	public List getWebList(Map map); 
	
	public void updateState(Map map);
	
	public void update(Map<String,String> map);
	@SuppressWarnings("unchecked")
   	public List getoverList(Map map); 
	public List getTake(Map map);
	public Map replaceList(List goodsorderList);
	public void orderouttime(Map map);
	public void directouttime(Map map);
	public void comboorderout(Map map);
	public void publicorderout(Map map);
	public void grouporderout(Map map);
	public void recycleorderout(Map map);
	public void spikeorderout(Map map);
	public void cancelorderout(Map map);
	public int getOrderCount(Map map);
	public int getRefundCount(Map map);
	
	 /**
	 * @author : HXM
	 * @param :oid:订单编号，
	 * session_user_id:用于插入资金流操作人字段
	 * @date Mar 28, 2014 1:33:55 PM
	 * @Method Description :将资金从运营转入卖家
	 */
	public void sellerFundManage(String oid,String session_user_id);
	
	 /**
	 * @author : HXM
	 * @param :oid:订单编号，
	 * session_user_id:用于插入资金流操作人字段
	 * @date Mar 28, 2014 1:33:55 PM
	 * @Method Description :将资金从运营转入卖家
	 */
	public void onlinepayManage(String oid,String session_user_id);
	
	 /**
	 * @author:HXM
	 * @date:Jul 16, 201410:00:53 AM
	 * @param: goodsorder_id:订单编号，cfg_sc_pointsrule：系统返回的积分，
	 * session_user_id:用于插入积分记录的操作人
	 * @Description: 插入积分
	 */
	public void insertOrderInter(String goodsorder_id ,String cfg_sc_pointsrule,String session_user_id);
	public void exportExcel(List orderlist, HttpServletResponse response) throws Exception;
	public void exportOrderExcel(List orderlist, HttpServletResponse response) throws Exception;
	public void exportTestExcel(List orderlist, HttpServletResponse response) throws Exception ;
	public String iCvs(String iname);
	/**
	 * @author : QJY
	 * @date : Apr 19, 2015 11:05:33 AM
	 * @Method Description :批量发货
	 */
    public boolean updateOrderWeight(String field_id,String field_sort,String ids,String vals)throws Exception;
    
    /**
     * 
     * 
     */
    public Goodsorder getByTrxID(String paytrxID);
    
    /**
     * 删除订单回收站的订单 
     * @return
     */
    public boolean deleteReOrder(Goodsorder goodsorder);
    /**
     * 
     * @param map
     * @return
     */
    public List getAreaOrderList(Map map)throws Exception;
    
    public int getAreaCount(Map map)throws Exception;
    
    /**
	 * @Method Description :物理删除订单信息 包括有关订单一并删除
	 * @author: HXK
	 * @date : Oct 23, 2015 4:19:17 PM
	 * @param 
	 * @return return_type
	 */
     public boolean DeleteOrderInfo(String order_str);
     /**
 	 * @Method Description :物理删除订单信息 包括有关订单一并删除
 	 * @author: HXK
 	 * @date : Oct 23, 2015 4:19:17 PM
 	 * @param Map 对象
 	 * @return return_type
 	 */
 	public void recycleByOrderList(Map map);
 	  /**
 	 * @author QJY
 	 * @function 商量销售量更新
 	 * @date 2015-08-24
 	 * @throws Exception
 	 */
 	public void updateGoodsSales(String order_id)throws Exception;
 	/**
	 * 导出备货清单
	 * @param orderList
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean prepareGoodsExport(List exList, HttpServletResponse response) throws Exception;
	/**
	 * 导出发货清单
	 * @param orderList
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean deliverGoodsExport(List exList,Map orderstate_map, HttpServletResponse response) throws Exception;
	/**
	 * @Method Description : 系统执行自动确认收货
	 * @author: 胡惜坤
	 * @date : Feb 18, 2016 3:04:05 PM
	 * @param 
	 * @return return_type
	 */
	public void updateAutoConfirmReceipt(Map map);
	/**
	 * @Method Description :系统自动评价
	 * @author: 胡惜坤
	 * @date : Feb 18, 2016 3:10:00 PM
	 * @param 
	 * @return return_type
	 */
    public void autoASS(List list);
    /**
	 * 自动确认收货
	 */
	public List getConfirmReceiptOrderList(Map map);
	
	public int getConfirmReceiptOrderCount(Map map)throws Exception;    
    public List getCustlist(String custID);
}


