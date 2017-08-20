/*
 * Package:com.rbt.function
 * FileName: AdvinfoFuc.java 
 */
package com.rbt.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Comsumer;
import com.rbt.model.Coupon;
import com.rbt.model.Goods;
import com.rbt.model.Goodsorder;
import com.rbt.model.Redsumer;
import com.rbt.model.Salegoods;
import com.rbt.service.IComsumerService;
import com.rbt.service.ICouponService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.IRedsumerService;
/**
 * @function 功能 前台广告显示
 * @author 创建人 HXK
 * @date 创建日期 2014-09-22
 */
@SuppressWarnings("unchecked")
public class CouponFuc extends CreateSpringContext {

	// 根据广告位标识找出该广告位下的广告
	@SuppressWarnings("unchecked")
	public static List couponList(List list, String goods_id_str,
			String sale_price_str, String order_num_str, String member_level) throws Exception {
		IGoodsService goodsService = (IGoodsService) getContext().getBean(
				"goodsService");
		if (list != null && list.size() > 0) {
			String[] goods_id = goods_id_str.split(",");
			String[] sale_price = sale_price_str.split(",");
			String[] order_num = order_num_str.split(",");
			
			
			for (int i = 0; i < list.size(); i++) {
				  Map map = (HashMap) list.get(i);
				  List goodsList = new ArrayList();
				  List totalList = new ArrayList();
					for (int j = 0; j < goods_id.length; j++) {
						Goods goods = goodsService.get(goods_id[j]);
						Salegoods salegoods = SalegoodsFuc.getSaleGoods(goods, "0", member_level);
						// 有促销活动的商品，不参与使用优惠券
						if (salegoods == null) {
						//指定商品	
						if (map.get("need_state").toString().equals("1")&& ifcontansInfo(goods.getGoods_id(),map.get("term").toString().trim()))
						{
							goodsList.add(goods.getGoods_id());
							totalList.add(Double.valueOf(sale_price[j])* Double.valueOf(order_num[j]));
						} else if (map.get("need_state").toString().equals("2")//指定分类
								&& map.get("term").toString().trim().equals(
										goods.getCat_attr())) {
							goodsList.add(goods.getGoods_id());
							totalList.add(Double.valueOf(sale_price[j])* Double.valueOf(order_num[j]));
						} else if (map.get("need_state").toString().equals("3")) {//所有商品
							goodsList.add(goods.getGoods_id());
							totalList.add(Double.valueOf(sale_price[j])* Double.valueOf(order_num[j]));
						}
					}
				}
				//商品ID	
				String selgoods_id = "";
				//所有商品总价
				double total = 0;
				//判断商品总价是否符合满减	
				if(goodsList != null && goodsList.size() > 0){
					for(int k = 0; k < goodsList.size(); k++) {
						//获取选择商品的ID
						if(ValidateUtil.isRequired(selgoods_id)){
							selgoods_id = (String) goodsList.get(k);
						}else {
							selgoods_id += ","+(String) goodsList.get(k);
						}
						//计算所有商品总价
						total += (Double) totalList.get(k);
					}
					//判断是否符合优惠条件
					if(map.get("need_money") != null) {
						if(total >= Double.valueOf(map.get("need_money").toString())) {
							map.put("use", "1");
							map.put("goods_id", selgoods_id);
						}
					}
				}
			}

		}
		return list;
	}
	 public static boolean ifcontansInfo(String gid,String gstr){
			boolean  fage=false;
			if(gid!=null&&!"".equals(gid)&&gstr!=null&&!"".equals(gstr)){
				String gstrs[]=gstr.split(",");
				if(gstrs!=null){
					for(int i=0;i<=gstrs.length-1;i++){
						if(gstrs[i]!=null){
							if(gid.equals(gstrs[i].toString())){
								fage=true;
							}
						}
					}
				}
			}
			return fage;
		}
	/**
	 * 优惠金额
	 * 
	 * @param coupon_id优惠券ID
	 * @param coupon_money商品总价
	 * @return
	 */
	public static String getCouponMoney(String coupon_id, String coupon_money) {
		String money = "0";
		ICouponService couponService = (ICouponService) getContext().getBean(
				"couponService");
		if (!ValidateUtil.isRequired(coupon_id)) {
			Coupon coupon = couponService.get(coupon_id);
			if (coupon != null) {
				int coupon_state = Integer.valueOf(coupon.getCoupon_state());
				// 条件判断
				switch (coupon_state) {
				case 1:
					// 符合应用条件的商品以固定折扣出售
					money = Double.valueOf(coupon_money)
							- ((Double.valueOf(coupon_money)) * (Double
									.valueOf(coupon.getCoupon_money()) / 100))
							+ "";
					break;
				case 2:
					// 符合应用条件的商品固定价格购买
					money = Double.valueOf(coupon_money)
							- Double.valueOf(coupon.getCoupon_money()) + "";
					break;
				case 3:
					// 符合应用条件的商品减去固定折扣出售
					money = Double.valueOf(coupon_money)
							- (Double.valueOf(coupon_money) - ((Double
									.valueOf(coupon_money)) * (Double
									.valueOf(coupon.getCoupon_money()) / 100)))
							+ "";
					break;
				case 4:
					// 符合应用条件的商品减固定价格购买
					money = Double.valueOf(coupon_money)
							- (Double.valueOf(coupon_money) - Double
									.valueOf(coupon.getCoupon_money())) + "";
					break;
				}
			}
		}

		return money;
	}
	
	
	
	
	
	/**
	 * 取消优惠券红包
	 * @param order_id
	 */
	public void cancelCouponAnd(String order_id){
		
		IGoodsorderService goodsorderService = (IGoodsorderService) getContext().getBean("goodsorderService");
		IComsumerService comsumerService = (IComsumerService) getContext().getBean("comsumerService");
		IRedsumerService redsumerService = (IRedsumerService) getContext().getBean("redsumerService");
		//获取goodsorder对象
		Goodsorder goodsorder = goodsorderService.get(order_id);
		if(goodsorder != null) {
			
		    //取消优惠券	
		   if(!ValidateUtil.isRequired(goodsorder.getSend_coupon_id())) {
			   String[] coupon_ids = goodsorder.getSend_coupon_id().trim().split(",");
			   for(int i = 0; i < coupon_ids.length; i++) {
				   Map map = new HashMap();
				   map.put("coupon_id", coupon_ids[i]);
				   map.put("cust_id", goodsorder.getBuy_cust_id());
				   map.put("use_state", "0");
				   List list = comsumerService.getList(map);
				   if(list != null && list.size() > 0) {
					   Map comMap = (HashMap) list.get(0);
					   comsumerService.delete(comMap.get("comsumer_id").toString());
				   }
			   }
 		   }else if(!ValidateUtil.isRequired(goodsorder.getSend_red_id())) { //取消红包
 			  String[] red_ids = goodsorder.getSend_red_id().trim().split(",");
			   for(int i = 0; i < red_ids.length; i++) {
				   Map map = new HashMap();
				   map.put("red_id", red_ids[i]);
				   map.put("cust_id", goodsorder.getBuy_cust_id());
				   map.put("use_state", "0");
				   List list = redsumerService.getList(map);
				   if(list != null && list.size() > 0) {
					   Map comMap = (HashMap) list.get(0);
					   redsumerService.delete(comMap.get("redsumer_id").toString());
				   }
			   }
 		   }
			
		}
		
	}
	
	
	/**
	 * 取消优惠券红包
	 * @param order_id
	 */
	public void backCouponAnd(String order_id){
		
		IGoodsorderService goodsorderService = (IGoodsorderService) getContext().getBean("goodsorderService");
		IComsumerService comsumerService = (IComsumerService) getContext().getBean("comsumerService");
		IRedsumerService redsumerService = (IRedsumerService) getContext().getBean("redsumerService");
		IOrderdetailService orderdetailService = (IOrderdetailService) getContext().getBean("orderdetailService");
		//获取goodsorder对象
		Goodsorder goodsorder = goodsorderService.get(order_id);
		//优惠券ID
		String coupon_id ="";
		if(goodsorder != null) {
			Map oMap = new HashMap();
			oMap.put("order_id", order_id);
			List oList = orderdetailService.getList(oMap);
			if(oList != null && oList.size() > 0) {
				for(int i = 0; i < oList.size(); i ++){
					Map odMap = (HashMap) oList.get(i);
					if(odMap.get("coupon_id") != null && !ValidateUtil.isRequired(odMap.get("coupon_id").toString())) {
						coupon_id = odMap.get("coupon_id").toString();
					}
				}
			}
		    //取消优惠券	
		   if(!ValidateUtil.isRequired(coupon_id)) {
			   String[] coupon_ids = coupon_id.trim().split(",");
			   Comsumer comsumer = new Comsumer();
			   for(int j = 0; j < coupon_ids.length; j++) {
				   Map map = new HashMap();
				   map.put("coupon_id", coupon_ids[j]);
				   map.put("cust_id", goodsorder.getBuy_cust_id());
				   map.put("use_state", "1");
				   List list = comsumerService.getList(map);
				   if(list != null && list.size() > 0) {
					   Map comMap = (HashMap) list.get(0);
					   comsumer = comsumerService.get(comMap.get("comsumer_id").toString());
				   }
				   comsumer.setUse_state("0");
				   comsumerService.update(comsumer);
			   }
 		   }else if(!ValidateUtil.isRequired(goodsorder.getRed_id())) { //取消红包
 			 String[] red_ids = goodsorder.getRed_id().trim().split(",");
 			 Redsumer redsumer = new Redsumer(); 
 			  for(int i = 0; i < red_ids.length; i++) {
				   Map map = new HashMap();
				   map.put("red_id", red_ids[i]);
				   map.put("cust_id", goodsorder.getBuy_cust_id());
				   map.put("use_state", "1");
				   List list = redsumerService.getList(map);
				   if(list != null && list.size() > 0) {
					   Map comMap = (HashMap) list.get(0);
					   redsumer =  redsumerService.get(comMap.get("redsumer_id").toString());
				   }
				   redsumer.setUse_state("0");
				   redsumerService.update(redsumer);
			   }
 		   }
			
		}
		
	}
	
	

}