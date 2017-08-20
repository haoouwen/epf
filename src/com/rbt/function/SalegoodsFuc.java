/*
  
 
 * Package:com.rbt.function
 * FileName: AdvinfoFuc.java 
 */
package com.rbt.function;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Goods;
import com.rbt.model.Salegoods;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsattrService;
import com.rbt.service.ISalegoodsService;

/**
 * @function 功能 商品促销
 * @author 创建人 XBY
 * @date 创建日期 2015-08-13
 */
@SuppressWarnings("unchecked")
public class SalegoodsFuc extends CreateSpringContext {

	private static ISalegoodsService salegoodsService = (ISalegoodsService) getContext()
			.getBean("salegoodsService");
	private static IGoodsService goodsService = (IGoodsService) getContext()
	.getBean("goodsService");

	/**
	 * 查询商品促销列表
	 * 
	 * @param platform生效平台
	 * @return返回list
	 */
	private static List saleGoodsList(String platform, String member_level) {
		HashMap map = new HashMap();
		// 启用状态
		map.put("info_state", "1");
		// 不过期
		map.put("time", "1");
		// 排它
		// map.put("is_recome", "1");
		//会员级别
		if(!ValidateUtil.isRequired(member_level)) {
		   map.put("member_levels", member_level);
		}
		// 生效平台
		map.put("platforms", platform);
		// 按优先级排序
		map.put("priority", "1");

		return salegoodsService.getList(map);
	}

	/**
	 * 判断是否有商品促销
	 * 
	 * @param goods 商品对象        
	 * @param platfrom 生效平台          
	 * @return 返回salgegoods对象
	 */
	public static Salegoods getSaleGoods(Goods goods, String platform, String member_level)
			throws Exception {

		String sale_id = "";

		// 查询商品促销列表
		List list = saleGoodsList(platform, member_level);

		if (list != null && list.size() > 0) {
			// 遍历集合
			for (int i = 0; i < list.size(); i++) {
				Map saleMap = (HashMap) list.get(i);

				// 获取优惠条件状态
				String term_state = saleMap.get("term_state").toString();

				// 获取优惠条件
				String term = saleMap.get("term").toString();

				// 符合指定商品
				if (term_state.equals("1")&&ifcontansInfo(goods.getGoods_id(),term.trim())==true ) {
					// 赋值活动ID
					sale_id = saleMap.get("sale_id").toString();
					break;
				} else if (term_state.equals("2")&&term.equals(goods.getCat_attr())) { // 符合商品分类
					// 赋值活动ID
					sale_id = saleMap.get("sale_id").toString();
					break;
				} else if (term_state.equals("3")) { // 所有商品
					// 赋值活动ID
					sale_id = saleMap.get("sale_id").toString();
					break;
				} else if (term_state.equals("4")&&ifcontansInfo(goods.getGoods_id(),term.trim())==true) {
					// 赋值活动ID
					sale_id = saleMap.get("sale_id").toString();
					break;
				}
			}
		}

		if (!ValidateUtil.isRequired(sale_id)) {
			return salegoodsService.get(sale_id);
		} else {
			Salegoods salegoods = null;
			return salegoods;
		}
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
	 * 判断是否有商品促销
	 * 
	 * @param goods 商品对象        
	 * @param platfrom 生效平台          
	 * @return 返回salgegoods对象
	 */
	public static String getSaleId(Goods goods, String platform, String member_level)
			throws Exception {

		String sale_id = "";

		// 查询商品促销列表
		List list = saleGoodsList(platform, member_level);

		if (list != null && list.size() > 0) {
			//储存选中的活动
			List selSaleList = new ArrayList(); 
			// 遍历集合
			for (int i = 0; i < list.size(); i++) {
				Map saleMap = (HashMap) list.get(i);

				// 获取优惠条件状态
				String term_state = saleMap.get("term_state").toString();

				// 获取优惠条件
				String term = saleMap.get("term").toString();

				// 符合指定商品
				if (term_state.equals("1")&&ifcontansInfo(goods.getGoods_id(),term.trim())==true) {
					//储存选中商品活动
					selSaleList.add(list.get(i));
				} else if (term_state.equals("2")&& goods.getCat_attr().contains(term)) { // 符合商品分类
					//储存选中商品活动
					selSaleList.add(list.get(i));
				} else if (term_state.equals("3")) { // 所有商品
					//储存选中商品活动
					selSaleList.add(list.get(i));
				} else if (term_state.equals("4")&&ifcontansInfo(goods.getGoods_id(),term.trim())==true) {
					//储存选中商品活动
					selSaleList.add(list.get(i));
				}

			}
			//排序活动顺序
			if(selSaleList != null && selSaleList.size() > 0) {
				//排它集合
				List recomeList = new ArrayList();
				//非排它集合
				List comeList = new ArrayList();
				for(int k = 0; k < selSaleList.size(); k ++) {
					Map selMap = (HashMap) selSaleList.get(k);
					if(selMap.get("is_recome") != null && !ValidateUtil.isRequired(selMap.get("is_recome").toString())) {
						if(selMap.get("is_recome").toString().equals("1")) {
							recomeList.add(selSaleList.get(k));
						}else {
							comeList.add(selSaleList.get(k));
						}
					}
					
				}
				
				double  priority = 0.00;
				//判断是否排它的活动
				if(recomeList != null && recomeList.size() > 0) {
					for (int m = 0; m < recomeList.size(); m ++) {
						Map recomeMap = (HashMap) recomeList.get(m);
						if(m == 0) { 
							//获取第一个集合的活动Id
							if(recomeMap.get("sale_id")!=null){
								sale_id = recomeMap.get("sale_id").toString();
							}
							//获取第一个集合的活动优先级
							if(recomeMap.get("priority")!= null) {
								priority = Double.valueOf(recomeMap.get("priority").toString());
							}
						}else {
							if(priority > Double.valueOf(recomeMap.get("priority").toString()) ) {
								//排它优先级
								priority = Double.valueOf(recomeMap.get("priority").toString());
								//排它优先级高的活动名称
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
		  }
		}
        return sale_id;
	}
	
	
	/**
	 * 替换商品列表活动名称
	 * 
	 * @param list集合
	 * @param platform 生效平台           
	 * @return 返回list集合
	 */
	public static List replaceList(List list, String platform, String member_level) throws Exception {
		return commonList(list, "0", platform, member_level);
	}

	/**
	 * 替换购物车商品活动名称
	 * 
	 * @param list集合
	 * @param platform 生效平台          
	 * @return 返回list集合
	 */
	public static List replaceCartgoodsList(List list, String platform, String member_level)
			throws Exception {
		return commonList(list, "1", platform, member_level);
	}

	/**
	 * 
	 * @param type类型          
	 * @return 返回List
	 * @throws Exception
	 */
	private static List commonList(List list, String type, String platform, String member_level)
			throws Exception {
		IGoodsattrService goodsattrService = (IGoodsattrService) getContext()
				.getBean("goodsattrService");
		String goods_id = "", specstr = "";
		if (list != null && list.size() > 0) {
			// 查询商品促销列表
			List salegoodsList = saleGoodsList(platform, member_level);

			if (salegoodsList != null && salegoodsList.size() > 0) {
				// 遍历list集合
				for (int i = 0; i < list.size(); i++) {
					Map goodsMap = (HashMap) list.get(i);
					//储存选中的活动
					List selSaleList = new ArrayList(); 
					// 遍历集合
					for (int j = 0; j < salegoodsList.size(); j++) {
						// 获取商品促销
						Map saleMap = (HashMap) salegoodsList.get(j);
						// 获取优惠条件状态
						String term_state = saleMap.get("term_state")
								.toString();
						// 获取优惠条件
						String term = saleMap.get("term").toString();

						// 符合指定商品
						if (term_state.equals("1")&&ifcontansInfo(goodsMap.get("goods_id").toString().replaceAll(" ", ""),term.trim())==true) {
							//添加选中的活动
							selSaleList.add(salegoodsList.get(j));
							
						} else if (term_state.equals("2")&& goodsMap.get("goods_cat").toString().contains(term)) { // 符合商品分类
							//添加选中的活动
							selSaleList.add(salegoodsList.get(j));
						} else if (term_state.equals("3")) { // 所有商品
							//添加选中的活动
							selSaleList.add(salegoodsList.get(j));
						} else if (term_state.equals("4")&&ifcontansInfo(goodsMap.get("goods_id").toString(),term.trim())==true) {// 指定商品满X是优惠
							//添加选中的活动
							selSaleList.add(salegoodsList.get(j));
						}
					}
					
					//排序活动顺序
					if(selSaleList != null && selSaleList.size() > 0) {
						//排它集合
						List recomeList = new ArrayList();
						//非排它集合
						List comeList = new ArrayList();
						for(int k = 0; k < selSaleList.size(); k ++) {
							Map selMap = (HashMap) selSaleList.get(k);
							if(selMap.get("is_recome") != null && !ValidateUtil.isRequired(selMap.get("is_recome").toString())) {
								if(selMap.get("is_recome").toString().equals("1")) {
									recomeList.add(selSaleList.get(k));
								}else {
									comeList.add(selSaleList.get(k));
								}
							}
							
						}
						
						//活动价格
						String sale_price = "";
						if (type.equals("1")) {
							// 获取商品ID
							goods_id = goodsMap.get("goods_id").toString();
							// 商品规格
							specstr = goodsMap.get("spec_id").toString();

							Map map = new HashMap();
							map.put("goods_id", goods_id);
							if (!ValidateUtil.isRequired(specstr)
									&& !specstr.equals("0")) {
								map.put("specstr", specstr);
							}
							// 获取商品销售价格
							List goodsattrList = goodsattrService.getList(map);

							if (goodsattrList != null
									&& goodsattrList.size() > 0) {
								Map goodsattrMap = (HashMap) goodsattrList
										.get(0);
								sale_price = goodsattrMap.get("sale_price")
										.toString();
								goodsMap.put("old_price", sale_price);
							}
						}
						
						//活动名称
						String selsale_name = "";
						//活动ID
						String sale_id = "";
						double  priority = 0.00;
						//判断是否排它的活动
						if(recomeList != null && recomeList.size() > 0) {
							for (int m = 0; m < recomeList.size(); m ++) {
								Map recomeMap = (HashMap) recomeList.get(m);
								if(m == 0) { 
									//获取第一个集合的活动名称
									if(recomeMap.get("sale_name_list")!=null){
										selsale_name = recomeMap.get("sale_name_list").toString();
									}
									//获取第一个集合的活动优先级
									if(recomeMap.get("priority")!= null) {
										priority = Double.valueOf(recomeMap.get("priority").toString());
									}									
									//获取第一个集合的活动ID
									if(recomeMap.get("sale_id")!= null) {
										sale_id = recomeMap.get("sale_id").toString();
									}										

								}else {
									if(priority > Double.valueOf(recomeMap.get("priority").toString()) ) {
										//排它优先级
										priority = Double.valueOf(recomeMap.get("priority").toString());
										//排它优先级高的活动名称
										selsale_name = recomeMap.get("sale_name_list").toString();
										//活动ID
										sale_id = recomeMap.get("sale_id").toString();
									}
										
								}
							}
						    
						   //优惠商品价格	
							if(type.equals("1")) {
								Salegoods salegoods = salegoodsService.get(sale_id);
								if(!salegoods.getTerm_state().equals("4") && !salegoods.getCoupon_state().equals("6")){
									sale_price = getSalePrice(sale_id, sale_price);
								}
							}
							
						}else if(comeList != null && comeList.size() > 0){
							//显示全部非排它活动
							for (int m = 0; m < comeList.size(); m ++) {
								Map comeMap = (HashMap) comeList.get(m);
								if(m == 0) {
									selsale_name = comeMap.get("sale_name_list").toString();
									sale_id = comeMap.get("sale_id").toString();
								}else {
									selsale_name += " " + comeMap.get("sale_name_list").toString();
									sale_id += "," + comeMap.get("sale_id").toString();
								}
								 //优惠商品价格	
								if(type.equals("1")) {
									Salegoods salegoods = salegoodsService.get(comeMap.get("sale_id").toString());
									if(!salegoods.getTerm_state().equals("4") && !salegoods.getCoupon_state().equals("6")){
									sale_price = getSalePrice(comeMap.get("sale_id").toString(), sale_price);
									}
								}
							}
						}
						
						//储存活动名称
						if(!ValidateUtil.isRequired(selsale_name)) {
							goodsMap.put("sale_name", selsale_name);
						}
                         
						//商品优惠价格
						if(!ValidateUtil.isRequired(sale_price)) {
							if(Double.valueOf(sale_price) < 0) {
								sale_price = "0.00";
							}
							goodsMap.put("sale_price", sale_price);
						}
						
					    //活动ID
						if(!ValidateUtil.isRequired(sale_id)) {
							goodsMap.put("sale_id", sale_id);
						}
						
					}

				}

			}
		}
		return list;

	}

	
	
	
	/**
	 * 获取促销价格
	 * 
	 * @param sale_id促销活动id        
	 * @param sale_price原价
	 * @return 返回促销价格
	 */
	public static String getSalePrice(String sale_id, String sale_price)
			throws Exception {
		if (!ValidateUtil.isRequired(sale_id)
				&& !ValidateUtil.isRequired(sale_price)) {
			Salegoods salegoods = salegoodsService.get(sale_id);
			int coupon_state = Integer.valueOf(salegoods.getCoupon_state());
			// 条件判断
			switch (coupon_state) {
			case 3:
				// 符合应用条件的商品以固定折扣出售
				sale_price = ((Double.valueOf(sale_price)) * (Double
						.valueOf(salegoods.getCoupon_plan()) / 100))
						+ "";
				sale_price = String.format("%.2f", Double.valueOf(sale_price));
				break;
			case 4:
				// 符合应用条件的商品固定价格购买
				sale_price = salegoods.getCoupon_plan();
				sale_price = String.format("%.2f", Double.valueOf(sale_price));
				break;
			case 5:
				// 符合应用条件的商品减去固定折扣出售
				sale_price = (Double.valueOf(sale_price) - ((Double
						.valueOf(sale_price)) * (Double.valueOf(salegoods
						.getCoupon_plan()) / 100)))
						+ "";
				sale_price = String.format("%.2f", Double.valueOf(sale_price));
				break;
			case 6:
				// 符合应用条件的商品减固定价格购买
				sale_price = (Double.valueOf(sale_price) - Double
						.valueOf(salegoods.getCoupon_plan()))
						+ "";
				sale_price = String.format("%.2f", Double.valueOf(sale_price));
				break;
			}
		}
		return sale_price;
	}

	/**
	 * 订单页面获取商品促销
	 * 
	 * @param sale_id商品促销ID
	 * @param sale_price商品价格
	 * @return 返回促销价格
	 * @throws Exception
	 */
	public static String goSaleprice(String sale_id, String sale_price)
			throws Exception {
		if (!ValidateUtil.isRequired(sale_id)) {
			String[] sale_ids = sale_id.trim().split(",");
			for(int i = 0; i < sale_ids.length; i++) {
			Salegoods salegoods = salegoodsService.get(sale_ids[i]);
			if (salegoods != null && salegoods.getTerm_state().equals("4")) {
				if (Double.valueOf(sale_price) >= Double.valueOf(salegoods
						.getNeed_money())) {
					sale_price = getSalePrice(sale_ids[i], sale_price);
				}
			}else if(salegoods != null && salegoods.getCoupon_state().equals("6")) {
				sale_price = getSalePrice(sale_ids[i], sale_price);
			}
		   }
		}
		return sale_price;
	}
   
	
    /**
     * 赠送优惠券或者红包
     * @param salegoods 商品促销对象
     * @param total 总价
     * @return
     * @throws Exception
     */
	public static String getCoupon(Salegoods salegoods, String total) throws Exception {
	    
		String state = "0";
		
		if(salegoods != null) {
	    	if(salegoods.getTerm_state().equals("1")){
                state = commonState(Integer.valueOf(salegoods.getCoupon_state()));	    		
	    	}else if(salegoods.getTerm_state().equals("2")) {
	    		 state = commonState(Integer.valueOf(salegoods.getCoupon_state()));
	    	}else if(salegoods.getTerm_state().equals("3")) {
	    		 state = commonState(Integer.valueOf(salegoods.getCoupon_state()));
	    	}else if(salegoods.getTerm_state().equals("4") && Double.valueOf(total) >= Double.valueOf(salegoods.getNeed_money())) {
	    		state = commonState(Integer.valueOf(salegoods.getCoupon_state()));
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
		case 7:
			//赠品
			state = "3";
			break;
		}
		return state;
	}
	
	
	/**
	 * 
	 * @param type类型          
	 * @return 返回List
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public static List replaceSalegoodsList(List list, String platform, String member_level)throws Exception {
		if (list != null && list.size() > 0) {

			// 查询商品促销列表
			List salegoodsList = saleGoodsList(platform, member_level);

			if (salegoodsList != null && salegoodsList.size() > 0) {
				// 遍历list集合
				for (int i = 0; i < list.size(); i++) {
					// 获取活动名称==
					String sale_str ="";
					Map goodsMap = (HashMap) list.get(i);
					// 遍历集合
					for (int j = 0; j < salegoodsList.size(); j++) {
						// 获取商品促销
						Map saleMap = (HashMap) salegoodsList.get(j);
						// 获取优惠条件状态
						String term_state = saleMap.get("term_state")
								.toString();
						// 获取优惠条件
						String term = saleMap.get("term").toString();
						// 赋值活动ID
						String sale_id = saleMap.get("sale_id").toString();
						// 符合指定商品
						if (term_state.equals("1")&&ifcontansInfo(goodsMap.get("goods_id").toString(),term.trim())==true) {
							if(ValidateUtil.isRequired(sale_str)) {
								sale_str = saleMap.get("sale_name").toString();
							}else {
								sale_str += ","+saleMap.get("sale_name").toString();
							}
						} else if (term_state.equals("2")&& goodsMap.get("goods_cat").toString().contains(term)) { // 符合商品分类
							if(ValidateUtil.isRequired(sale_str)) {
								sale_str = saleMap.get("sale_name").toString();
							}else {
								sale_str += ","+saleMap.get("sale_name").toString();
							}
						} else if (term_state.equals("3")) { // 所有商品
							if(ValidateUtil.isRequired(sale_str)) {
								sale_str = saleMap.get("sale_name").toString();
							}else {
								sale_str += ","+saleMap.get("sale_name").toString();
							}
						} else if (term_state.equals("4")&&ifcontansInfo(goodsMap.get("goods_id").toString(),term.trim())==true) {// 指定商品满X是优惠
							if(ValidateUtil.isRequired(sale_str)) {
								sale_str = saleMap.get("sale_name").toString();
							}else {
								sale_str += ","+saleMap.get("sale_name").toString();
							}
						}
						
					}
					goodsMap.put("sale_name", sale_str);
				}

			}
		}
		
		return list;

	}
	
	
	
	/**
	 * 获取促销倒计时列表
	 * 
	 * @param platform生效平台
	 * @throws Exception 
	 * @return返回list
	 */
	public  static List saleGoodsTimeList(List goodsList,String platform,String member_level) throws Exception {
		if(goodsList!=null&&goodsList.size()>0){
			// 遍历list集合
			for (int i = 0; i < goodsList.size(); i++) {
				String goods_id="";
				HashMap gMap=new HashMap();
				gMap=(HashMap)goodsList.get(i);
				if(gMap.get("goods_id")!=null&&!"".equals(gMap.get("goods_id").toString())){
					Goods goods=new Goods();
					goods_id=gMap.get("goods_id").toString();
					goods=goodsService.get(goods_id);
					if(goods!=null){
						//判断商品是否有做促销活动
						String s_id = SalegoodsFuc.getSaleId(goods, platform, member_level);
						//判断商品是否有做促销活动
						if(!ValidateUtil.isRequired(s_id)){
							String[] s_ids = s_id.trim().split(",");
							//从商品参加的活动中 提取出倒计时时间
							for(int j=0;j<s_ids.length;j++){
								Salegoods salegoods=new Salegoods();
								salegoods = salegoodsService.get(s_ids[j]);
								// 是否限时倒计时
								String limit_time="0"; 
							    long difftime=0;//倒计时
							    String timeout="0";//时间结束
							    String timenumber="1";
								limit_time= salegoods.getLimit_time();
								if("1".equals(limit_time)){
									//获取倒计时时间秒数
				    				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    				if(salegoods.getEnd_time()!=null){
				    					Date date = sd.parse(salegoods.getEnd_time());
				    					difftime =date.getTime() - new Date().getTime();
				    					if(0 > difftime){
				    						timeout = "1";
				    					}
				    				}
				    				 if(difftime!=0.0){
				    			        	timenumber=String.valueOf(difftime);
				    			     }
				    				gMap.put("difftime", timenumber);
				    				break;
								}
							}
						}
					}
				}
				
			}
		}
		return goodsList;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}