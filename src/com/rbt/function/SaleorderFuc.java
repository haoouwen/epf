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
import com.rbt.model.Saleorder;
import com.rbt.service.ISaleorderService;

/**
 * @function 功能  订单促销
 * @author  创建人 XBY
 * @date  创建日期  2015-08-13
 */
@SuppressWarnings("unchecked")
public class SaleorderFuc extends CreateSpringContext{
	
	private static ISaleorderService saleorderService = (ISaleorderService)getContext().getBean("saleorderService");
	
	
	/**
	 * 查询订单促销列表
	 * 
	 * @param platform生效平台
	 * @return 返回list
	 */
	private static List saleGoodsList(String platform, String member_level) {
		HashMap map = new HashMap();
		// 启用状态
		map.put("info_state", "1");
		// 不过期
		map.put("time", "1");
		// 排它
		//map.put("is_recome", "1");
		// 生效平台
		map.put("platforms", platform);
		//会员级别
		map.put("member_levels", member_level);
		// 按优先级排序
		map.put("priority", "1");

		return saleorderService.getList(map);
	}
	
	
	/**
	 * 判断是否有订单促销活动
	 * @param platform生效平台
	 * @return 订单促销对象
	 * @throws Exception
	 */
	public static String getSaleorderId(String platform, String member_level) throws Exception {
		//订单促销ID
		String sale_id ="" ;
		List list = saleGoodsList(platform, member_level);
		//排它集合
		List recomeList = new ArrayList();
		//非排它集合
		List comeList = new ArrayList();
		if(list != null && list.size() > 0) {
			for(int i = 0; i < list.size(); i++) {
				Map selMap = (HashMap) list.get(i);
				if(selMap.get("is_recome") != null && !ValidateUtil.isRequired(selMap.get("is_recome").toString())) {
					if(selMap.get("is_recome").toString().equals("1")) {
						recomeList.add(list.get(i));
					}else {
						comeList.add(list.get(i));
					}
				}
			}
			
		}
		double  priority = 0.00;
		//判断是否排它的活动
		if(recomeList != null && recomeList.size() > 0) {
			for (int m = 0; m < recomeList.size(); m ++) {
				Map recomeMap = (HashMap) recomeList.get(m);
				if(m == 0) { 
					//获取第一个集合的活动优先级
					if(recomeMap.get("priority")!= null) {
						priority = Double.valueOf(recomeMap.get("priority").toString());
					}									
					//获取第一个集合的活动ID
					if(recomeMap.get("sale_id")!= null) {
						sale_id = recomeMap.get("sale_id").toString();
					}										

				}else {
					if(Double.valueOf(recomeMap.get("priority").toString()) < priority) {
						//排它优先级
						priority = Double.valueOf(recomeMap.get("priority").toString());
						//活动ID
						sale_id = recomeMap.get("sale_id").toString();
					}
						
				}
			}
		}else if(comeList != null && comeList.size() > 0){
			//显示全部非排它活动
			for (int m = 0; m < comeList.size(); m ++) {
				Map comeMap = (HashMap) comeList.get(m);
				if(m == 0) {
					sale_id = comeMap.get("sale_id").toString();
				}else {
					sale_id += "," + comeMap.get("sale_id").toString();
				}
			}
		}
		
       return sale_id;
	}
	
   
   /**
    * 赠送优惠券或者红包
    * @param saleorder 订单促销对象
    * @param total 总价
    * @return
    * @throws Exception
    */
	public static String getCoupon(Saleorder saleorder, String total, String goodstoatl) throws Exception {
	    
		String state = "0";
		
		if(saleorder != null) {
	    	if(saleorder.getTerm_state().equals("2")){
               state = commonState(Integer.valueOf(saleorder.getCoupon_state()));	    		
	    	}else if(saleorder.getTerm_state().equals("1") && Double.valueOf(total) >= Double.valueOf(saleorder.getNeed_money())) {
	    		state = commonState(Integer.valueOf(saleorder.getCoupon_state()));
	    	}else if(saleorder.getTerm_state().equals("3") && Double.valueOf(goodstoatl) >= Double.valueOf(saleorder.getNeed_money())){
	    		state = commonState(Integer.valueOf(saleorder.getCoupon_state()));
	    	}
	    }
       
		return state;
		
	}
	
	/**
	 * 
	 * @param coupon_state
	 * @return
	 */
	private static String commonState(int coupon_state){
		String state ="0";
		switch (coupon_state) {
		case 1:
			//赠送优惠券
			state = "1";
			break;
		case 2:
			//赠送红包
			state = "2";
			break;
		case 4:
			//赠品
			state = "3";
			break;
		}
		return state;
	}
   
	
}