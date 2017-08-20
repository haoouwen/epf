/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: RefundappService.java 
 */
package com.rbt.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.common.util.DateUtil;
import com.rbt.dao.IFundhistoryDao;
import com.rbt.dao.IGoodsorderDao;
import com.rbt.dao.IMemberfundDao;
import com.rbt.dao.IOrderdetailDao;
import com.rbt.dao.IRefundappDao;
import com.rbt.dao.ISysfundDao;
import com.rbt.model.Fundhistory;
import com.rbt.model.Goodsorder;
import com.rbt.model.Memberfund;
import com.rbt.model.Orderdetail;
import com.rbt.model.Refundapp;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.IRefundappService;
import com.rbt.service.ISysfundService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 退款申请表Service层业务接口实现
 * @author 创建人 HXK
 * @date 创建日期 Fri Mar 29 16:04:49 CST 2014
 */
@Service
public class RefundappService extends GenericService<Refundapp,String> implements IRefundappService {
	
	IRefundappDao refundappDao;
	@Autowired
	IMemberfundDao memberfundDao;
	@Autowired
	IGoodsorderDao goodsorderDao;
	@Autowired
	ISysfundService sysfundService;
	@Autowired
	IOrderdetailDao orderdetailDao;
	@Autowired
	IMemberfundService memberfundService;
	@Autowired
	IFundhistoryDao fundhistoryDao;
	@Autowired
	public RefundappService(IRefundappDao refundappDao) {
		super(refundappDao);
		this.refundappDao = refundappDao;
	}
	public Refundapp getByOrderId(String order_id)  {
		return this.refundappDao.getByOrderId(order_id);
	}
	/**
	 * @author : HZX
	 * @date : Feb 11, 2014 2:14:28 PM
	 * @Method Description :处理退款
	 */
	public void dealRefund(Map refundMap) {
		Goodsorder goodsorder=(Goodsorder) refundMap.get("goodsorder");
		Refundapp refundapp=(Refundapp) refundMap.get("refundapp");
		String type=(String) refundMap.get("type");
		String session_user_id =refundMap.get("session_user_id").toString();
		String goods_order=goodsorder.getOrder_id();
		//订单总金额
		Double goods_amount=goodsorder.getTatal_amount();
		//应退款给正方
		Double refund_amount=Double.parseDouble(refundapp.getRefund_amount());
		//应退款另一方
		Double other_refund_amount=0.0;
		boolean is_success=false;
		if(refund_amount>0){
			other_refund_amount=goods_amount-refund_amount;
			is_success=true;
		}
		//买家
		Memberfund buymf=new Memberfund();
		//卖家
		Memberfund sellmf=new Memberfund();
		if(goodsorder!=null){
			if(type!=null&&!type.equals("")){
				buymf=memberfundDao.get(goodsorder.getBuy_cust_id());
				sellmf=memberfundDao.get(goodsorder.getSell_cust_id());
				if(type.equals("buy")){
					//买家
					//执行退款的时候，需要把平台总资金的冻结订单金额 解冻到可用资金里面
					sysfundService.freezeNum(goods_amount, 1);
					//退款给买家
					memberfundService.outAndInNum(goodsorder.getBuy_cust_id(),refund_amount,1);
					//退款给卖家
					memberfundService.outAndInNum(goodsorder.getSell_cust_id(),other_refund_amount,1);
					if(is_success){//退款成功
						refundapp.setRefund_state("1");
						goodsorder.setOrder_state("5");
					}else{
						refundapp.setRefund_state("2");
						goodsorder.setOrder_state("6");
					}
				}else if(type.equals("sell")){
					//卖家
					//执行退款的时候，需要把平台总资金的冻结订单金额 解冻到可用资金里面
					sysfundService.freezeNum(goods_amount, 1);
					//退款给买家
					memberfundService.outAndInNum(goodsorder.getBuy_cust_id(),other_refund_amount,1);
					//退款给卖家
					memberfundService.outAndInNum(goodsorder.getSell_cust_id(),refund_amount,1);
					if(is_success){//退款成功
						refundapp.setRefund_state("1");
						goodsorder.setOrder_state("5");
					}else{
						refundapp.setRefund_state("2");
						goodsorder.setOrder_state("6");
					}
					
				}
			}
		}
		refundapp.setInfo_state("1");
		this.refundappDao.update(refundapp);
		this.goodsorderDao.update(goodsorder);
		//买家的资金异动
		Fundhistory buy_fh = new Fundhistory();
		buy_fh.setBalance(Double.parseDouble(buymf.getFund_num()));
		buy_fh.setCust_id(goodsorder.getBuy_cust_id());
		if(type.equals("buy")){
			buy_fh.setFund_in(refund_amount);
			buy_fh.setReason("买家收到订单号:"+goods_order+" 退款"+refund_amount+"元");
		}else{
			buy_fh.setFund_in(other_refund_amount);
			buy_fh.setReason("买家收到订单号:"+goods_order+" 退款"+other_refund_amount+"元");
		}
		buy_fh.setFund_out(0.0);
		buy_fh.setUser_id(session_user_id);
		this.fundhistoryDao.insert(buy_fh);
		//卖家的资金异动
		Fundhistory sell_fh = new Fundhistory();
		sell_fh.setBalance(Double.parseDouble(sellmf.getFund_num()));
		sell_fh.setCust_id(goodsorder.getSell_cust_id());
		if(type.equals("sell")){
			sell_fh.setFund_in(refund_amount);
			sell_fh.setReason("卖家收到订单号:"+goods_order+" 退款补偿"+refund_amount+"元");
		}else{
			sell_fh.setFund_in(other_refund_amount);
			sell_fh.setReason("卖家收到订单号:"+goods_order+" 退款补偿"+other_refund_amount+"元");
		}
		sell_fh.setFund_out(0.0);
		sell_fh.setUser_id(session_user_id);
		this.fundhistoryDao.insert(sell_fh);
		
	}
	/**
	 * @author : HZX
	 * @throws ParseException 
	 * @date : Feb 18, 2014 2:07:29 PM
	 * @Method Description :取消退款
	 */
	public boolean cancelRefund(String trade_id,String canceldesc,String o_type) throws ParseException {
		
		Refundapp rfund=new Refundapp();
		//获取退款信息
		rfund=refundappDao.get(trade_id);
		//退款关闭
		rfund.setRefund_state("5");
		rfund.setInfo_date(DateUtil.getCurrentTime());
		//设置买家申请关闭理由
		rfund.setSeller_reason(canceldesc);
		//更新订单详情里面的商品状态
		updateOrderDetailState(rfund.getGoods_id_str(),o_type);
		
		this.refundappDao.update(rfund);
		return true;
		
	}
	/**
	 * @Method Description :更新订单详情的 orderdetail_state订单详情商品状态表示0：正常，1，退款中，2：退款关闭，3：退款成功 
	 * @author: HXK
	 * @date : Jan 13, 2015 1:49:13 PM
	 * @param goods_is_str 商品ID串，order_state状态订单详情商品状态表示0：正常，1，退款中，2：退款关闭，3：退款成功
	 * @return return_type
	 */
	public void updateOrderDetailState(String goods_is_str,String order_state){
		if(goods_is_str!=null&&!"".equals(goods_is_str)){
			String[]orders=goods_is_str.split(",");
			for(int i=0;i<orders.length;i++){
				if(orders[i]!=null&&!"".equals(orders[i])){
					String order_id=orders[i].toString();
					//详情商品状态：0：正常，1，退款中，2：退款关闭，3：退款成功
					Orderdetail od=new Orderdetail();
					od=orderdetailDao.get(order_id);
					od.setOrderdetail_state(order_state);
					orderdetailDao.update(od);
				}
			}
		}
	}

	public Refundapp getByRefundNo(String refund_no){
		return refundappDao.getByRefundNo(refund_no);
	}
	
	//通过支付宝退款批次号取得，仅在支付宝退款时调用
	public Refundapp getByBatchNo(String batch_no){
		return refundappDao.getByBatchNo(batch_no);
	}
}

