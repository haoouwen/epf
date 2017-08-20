package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.common.util.ValidateUtil;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IOrderdetailService;

/**
 * @function 功能 订单详情
 * @author 创建人 QJY
 * @date 创建日期 Jul 29, 2014 1:10:56 PM
 */
public class DetailOrderFuc extends CreateSpringContext {
	/**
	 * @author : QJY
	 * @param :
	 * @throws Exception
	 * @throws Exception
	 * @date Mar 11, 2015 2:28:08 PM
	 * @Method Description :取出订单下的商品名称
	 */
	public static String getDetailOrderForGoodsName(String order_id)
			throws Exception {

		IGoodsorderService goodsorderService = (IGoodsorderService) getContext()
				.getBean("goodsorderService");
		IOrderdetailService orderdetailService = (IOrderdetailService) getContext()
				.getBean("orderdetailService");
		Map orderdetailMap = new HashMap();
		orderdetailMap.put("order_id_s", order_id);
		List orderdetailList = orderdetailService.getList(orderdetailMap);
		StringBuffer sbgn = null;
		String goodsNameStr = "";
		String goodsNames = "";
		if (orderdetailList != null && orderdetailList.size() > 0) {
			sbgn = new StringBuffer();
			HashMap goodsNameMap = new HashMap();
			if (orderdetailList.size() == 1) {
				for (int i = 0; i < orderdetailList.size(); i++) {
					goodsNameMap = (HashMap) orderdetailList.get(i);
					if (goodsNameMap.get("ogoods_name") != null) {
						goodsNames = goodsNameMap.get("ogoods_name").toString();
					}
				}
			} else if (orderdetailList.size() > 1) {
				for (int i = 0; i < orderdetailList.size(); i++) {
					goodsNameMap = (HashMap) orderdetailList.get(i);
					if (goodsNameMap.get("ogoods_name") != null) {
						goodsNameStr = goodsNameMap.get("ogoods_name")
								.toString();
						sbgn.append(goodsNameStr);
						sbgn.append(",");
					}
					if(i==1){
						break;
					}
				}
				sbgn.deleteCharAt(sbgn.length() - 1);
				sbgn.append(" 等多件");
				goodsNames = sbgn.toString();
			}

		}

		return goodsNames;
	}

	/**
	 * @author : QJY
	 * @param :
	 * @throws Exception
	 * @throws Exception
	 * @date Mar 11, 2015 2:28:08 PM
	 * @Method Description :取出订单下的商品名称
	 */
	public static String getDetailOrderForGoodsNameBody(String order_id)
			throws Exception {

		IGoodsorderService goodsorderService = (IGoodsorderService) getContext()
				.getBean("goodsorderService");
		IOrderdetailService orderdetailService = (IOrderdetailService) getContext()
				.getBean("orderdetailService");
		Map orderdetailMap = new HashMap();
		orderdetailMap.put("order_id_s", order_id);
		List orderdetailList = orderdetailService.getList(orderdetailMap);
		StringBuffer sbgn = null;
		String goodsNameStr = "";
		String goodsNames = "";
		if (orderdetailList != null && orderdetailList.size() > 0) {
			sbgn = new StringBuffer();
			HashMap goodsNameMap = new HashMap();
			for (int i = 0; i < orderdetailList.size(); i++) {
				goodsNameMap = (HashMap) orderdetailList.get(i);
				if (goodsNameMap.get("ogoods_name") != null) {
					goodsNameStr = goodsNameMap.get("ogoods_name").toString();
					sbgn.append(goodsNameStr);
					sbgn.append(",");
				}
			}
			sbgn.deleteCharAt(sbgn.length() - 1);
			goodsNames = sbgn.toString();

		}

		return goodsNames;
	}
	
	/**
	 * @author : QJY
	 * @param :
	 * @throws Exception
	 * @throws Exception
	 * @date Mar 11, 2015 2:28:08 PM
	 * @Method Description :取出订单下的商品名称
	 */
	public static HashMap getGoodsNameBody(String order_id)
			throws Exception {
		HashMap resultMap = new HashMap();
		//获取商品ID
	    String product_ids = "",details="",body="";
		if(!ValidateUtil.isRequired(order_id)){
			IOrderdetailService orderdetailService = (IOrderdetailService) getContext().getBean("orderdetailService");
			HashMap orderMap =new HashMap();
		    orderMap.put("order_id_s", order_id);
		    List detailList=orderdetailService.getList(orderMap);
		    
		    if(detailList!=null&&detailList.size()>0){
		    	for(int i=0;i<detailList.size();i++){
		    		HashMap oMap =new HashMap();
		    		oMap=(HashMap)detailList.get(i);
		    		if(oMap!=null&&oMap.get("goods_id")!=null){
		    			product_ids+=oMap.get("goods_id").toString()+",";
		    			details+=oMap.get("ogoods_name").toString()+",";
		    		}
		    	}
		    }
		    if(product_ids!=null&&!"".equals(product_ids)){
		    	//获得商品ID
		    	product_ids=product_ids.substring(0,product_ids.length()-1);
		    }
		    if(details!=null&&!"".equals(details)){
		    	//获得商品名称
		    	details=details.substring(0,details.length()-1);
		    	//获取body长度为32位
		    	if(details.length()>32){
		    		body=details.substring(0,30);
		    	}else{
		    		body=details+"等";
		    	}
		    }
		}
		resultMap.put("product_id", product_ids);
	    resultMap.put("body", body);
	    return resultMap;
		
	}

}