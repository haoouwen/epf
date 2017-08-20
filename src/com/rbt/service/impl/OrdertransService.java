/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: OrdertransService.java 
 */
package com.rbt.service.impl;

import com.rbt.dao.IGoodsorderDao;
import com.rbt.dao.IOrdertransDao;
import com.rbt.function.MessageAltFuc;
import com.rbt.model.Goodsorder;
import com.rbt.model.Ordertrans;
import com.rbt.service.IOrdertransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @function 功能 记录商品订单异动信息Service层业务接口实现
 * @author 创建人 HXK
 * @date 创建日期 Thu Feb 28 10:02:15 CST 2014
 */
@Service
public class OrdertransService extends GenericService<Ordertrans,String> implements IOrdertransService {
	
	IOrdertransDao ordertransDao;

	@Autowired
	public OrdertransService(IOrdertransDao ordertransDao) {
		super(ordertransDao);
		this.ordertransDao = ordertransDao;
	}
	@Autowired
	IGoodsorderDao goodsorderDao;
	/**
	 * @author：XBY
	 * @date：Feb 18, 2014 4:44:52 PM
	 * @Method Description：删除订单详情 
	 */
	public int deleteByOrderId(String order_id) {
		return  this.ordertransDao.deleteByOrderId(order_id);
	}
	/**
	 * @Method Description :插入订单异动
	 * @author: 胡惜坤
	 * @date : Mar 28, 2016 1:24:52 PM
	 * @param 
	 * @return return_type
	 */

	public void  inserOrderTran(String order_id,String cust_id,String user_id,String reason,String order_state,String opt_username){
		// 插入订单异动记录
		Ordertrans ordertrans=new Ordertrans();
		ordertrans.setOrder_id(order_id);
		ordertrans.setCust_id(cust_id);
		ordertrans.setUser_id(user_id);
		ordertrans.setOrder_state(order_state);
		ordertrans.setReason(reason);
		ordertrans.setOpt_username(opt_username);
		this.ordertransDao.insert(ordertrans);	
		
		if(order_state!=null&&!"".equals(order_state)&&order_id!=null&&!"".equals(order_id)){
			try {
				if("3".equals(order_state)){
					//订单发货
						mestipByBuyer("2",order_id);
				}else if("7".equals(order_state)) {
					//订单确认收货
					mestipByBuyer("1",order_id);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/**	
	 * @author : HXK
	 * @param :mes_id：消息提醒模版 order_id：订单ID
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :前台订单提醒-发送给买家
	 */
	public void mestipByBuyer(String mes_id,String order_id) throws Exception{
		if(order_id!=null&&!"".equals(order_id)){
			Goodsorder gorder = new Goodsorder();
			gorder = goodsorderDao.get(order_id);
			if(gorder != null){
				MessageAltFuc mesalt=new MessageAltFuc();
				if(gorder.getBuy_cust_id()!=null&&!"".equals(gorder.getBuy_cust_id())){
					mesalt.messageAutoSend(mes_id,gorder.getBuy_cust_id(),gorder);
				}
			}
		}
	}
}

