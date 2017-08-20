/*
  
 
 * Package:com.rbt.function
 * FileName: AdvinfoFuc.java 
 */
package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Goodsorder;
import com.rbt.model.Orderdetail;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IOrderdetailService;

/**
 * @function 功能  前台广告显示
 * @author  创建人 HXK
 * @date  创建日期  2014-09-22
 */
@SuppressWarnings("unchecked")
public class RefundappFuc extends CreateSpringContext{
	
	//获取商品退款金额
	public static String getGoodsTotal(String order_id,String trade_id){
		IGoodsorderService goodsorderService = (IGoodsorderService)getContext().getBean("goodsorderService");
		IOrderdetailService orderdetailService = (IOrderdetailService)getContext().getBean("orderdetailService");
		//商品总价
		double total = 0.00;
		//获取订单对象
		Goodsorder goodsorder = goodsorderService.get(order_id);
		//获取订单详情对象
		Orderdetail orderdetail = orderdetailService.get(trade_id);
		if(orderdetail != null) {
			//商品总价
			total = Double.valueOf(orderdetail.getSubtotal());
			Map map = new HashMap();
			map.put("order_id", order_id);
			List list = orderdetailService.getList(map);
			//所有商品总价
			double sum = 0.00;
			double coupontotal = 0.00;
			String coupon_money = "";
			String red_money = goodsorder.getRed_money();
			if(list != null && list.size() > 0) {
			    for(int i = 0; i < list.size(); i ++) {
						Map orderMap = (HashMap) list.get(i);
					    //获取商品总价
						sum += Double.valueOf(orderMap.get("subtotal").toString());
						if(orderMap.get("use_coupon") !=null && orderMap.get("use_coupon").toString().equals("1")){
							coupontotal += Double.valueOf(orderMap.get("subtotal").toString());
						}
						//获取优惠总价
						if(orderMap.get("coupon_money") != null && !ValidateUtil.isRequired(orderMap.get("coupon_money").toString())) {
							coupon_money =  orderMap.get("coupon_money").toString();
						}
				}
		    }
			if(goodsorder != null &&   !ValidateUtil.isRequired(goodsorder.getMemberdiscount())) {
	        	total = total * Double.valueOf(goodsorder.getMemberdiscount());
	    		//判断是否是有优惠券
	    		if(orderdetail.getUse_coupon() != null &&orderdetail.getUse_coupon().equals("1")){
	    			//扣掉优惠金额
	    			total = total - Double.valueOf(orderdetail.getSubtotal()) / coupontotal * Double.valueOf(coupon_money);
	    			//计算订单优惠
	    			if(!ValidateUtil.isRequired(goodsorder.getCoupon_money())) {
	    				total = total - Double.valueOf(orderdetail.getSubtotal()) / sum * Double.valueOf(goodsorder.getCoupon_money());
	    			}
	    		}else if(!ValidateUtil.isRequired(goodsorder.getRed_money())) { //判断是否使用红包
	    			if(!ValidateUtil.isRequired(goodsorder.getCoupon_money())) {
	    				red_money  += Double.valueOf(goodsorder.getCoupon_money()) + "";
	    			}
	    			//扣掉优惠金额
	    			total = total - Double.valueOf(orderdetail.getSubtotal()) / sum  * Double.valueOf(red_money);
	    		}else if(!ValidateUtil.isRequired(goodsorder.getCoupon_money())) {
	    			//扣掉优惠金额
	    			total = total - Double.valueOf(orderdetail.getSubtotal()) / sum  * Double.valueOf(goodsorder.getCoupon_money());
	    		}
			}
			
		}
        
		//扣点优惠，如果是负数，总价至于0
		if(total < 0) {
			total = 0;
		}
		
		
		return String.format("%.2f", (total))+"";
	}
	
	/**
	 * 获取商品单价，红包，订单优惠，优惠券 算的商品单价，其中 优惠会具体到哪个商品价格，红包和订单优惠 是统计扣除
	 * @param order_id 订单ID
	 * @param trade_id 订单详情ID
	 * @return
	 */
	public static String getSubTotal(String order_id,String trade_id){
		IGoodsorderService goodsorderService = (IGoodsorderService)getContext().getBean("goodsorderService");
		IOrderdetailService orderdetailService = (IOrderdetailService)getContext().getBean("orderdetailService");
		//商品总价
		double total = 0.00;
		double coupontotal = 0.00;
		//商品单价
		double sale_price = 0.00;
		//获取订单对象
		Goodsorder goodsorder = goodsorderService.get(order_id);
		//获取订单详情对象
		Orderdetail orderdetail = orderdetailService.get(trade_id);
		if(orderdetail != null) {
			//商品总价
			total = Double.valueOf(orderdetail.getSubtotal());
			Map map = new HashMap();
			map.put("order_id", order_id);
			List list = orderdetailService.getList(map);
			//所有商品总价
			double sum = 0.00;
			String red_money = goodsorder.getRed_money();
			if(list != null && list.size() > 0) {
			    for(int i = 0; i < list.size(); i ++) {
						Map orderMap = (HashMap) list.get(i);
					    //获取所有商品总价
						sum += Double.valueOf(orderMap.get("subtotal").toString());
						//获取试用优惠券所有商品总价
						if(orderMap.get("use_coupon") !=null && orderMap.get("use_coupon").toString().equals("1")){
							coupontotal += Double.valueOf(orderMap.get("subtotal").toString());
						}
				}
		    }
			if(goodsorder != null &&   !ValidateUtil.isRequired(goodsorder.getMemberdiscount())) {
	        	total = total * Double.valueOf(goodsorder.getMemberdiscount());
	    		//判断是否是有优惠券
	    		if(!ValidateUtil.isRequired(orderdetail.getCoupon_money())){
	    			//扣掉优惠金额
	    			total = total -  Double.valueOf(orderdetail.getSubtotal()) / coupontotal * Double.valueOf(orderdetail.getCoupon_money());
	    			//计算订单优惠金额
	    			if(!ValidateUtil.isRequired(goodsorder.getCoupon_money())) {
	    				total = total - Double.valueOf(orderdetail.getSubtotal()) / sum * Double.valueOf(goodsorder.getCoupon_money());
	    			}
	    			//计算商品单价
	    			sale_price = total / Double.valueOf(orderdetail.getOrder_num());
	    		}else if(!ValidateUtil.isRequired(goodsorder.getRed_money())) { //判断是否使用红包
	    			//红包优惠金额加上订单优惠金额
	    			if(!ValidateUtil.isRequired(goodsorder.getCoupon_money())) {
	    				red_money  += Double.valueOf(goodsorder.getCoupon_money()) + "";
	    			}
	    			//扣掉优惠金额
	    			total = total - Double.valueOf(orderdetail.getSubtotal()) / sum  * Double.valueOf(red_money);
	    			//计算商品单价
	    			sale_price = total / Double.valueOf(orderdetail.getOrder_num());
	    			
	    		}else if(!ValidateUtil.isRequired(goodsorder.getCoupon_money())) {
	    			//扣掉优惠金额
	    			total = total - Double.valueOf(orderdetail.getSubtotal()) / sum  * Double.valueOf(goodsorder.getCoupon_money());
	    			//计算商品单价
	    			sale_price = total / Double.valueOf(orderdetail.getOrder_num());
	    		}else {
	    			//计算商品单价
	    			sale_price = total / Double.valueOf(orderdetail.getOrder_num());
	    		}
			}
			
		}

		return String.format("%.2f", (sale_price))+"";
	}
	
}