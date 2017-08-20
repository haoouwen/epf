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
import com.rbt.service.INavigationService;

/**
 * @function 功能  APP 商城标签数据
 * @author  创建人 HXK
 */
public class MarkGoodsFuc extends CreateSpringContext{
	
	public static String cfg_mobiledomain=SysconfigFuc.getSysValue("cfg_mobiledomain");//手机端域名
	
	/**
	 * @Method Description :通过商城标签获取对于的商品列表
	 * @author: HXK
	 * @date : Aug 26, 2015 6:52:42 PM
	 * @param 
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public static List indexMarkGoodsJson(String tab_number,String row) throws Exception {
		INavigationService navigationService = (INavigationService)getContext().getBean("navigationService");
		// 构造list列表搜索条件
		Map nMap=new HashMap();
		nMap.put("is_del", "1");
		nMap.put("tab_number", tab_number);
		nMap.put("is_up", "0");
		nMap.put("sort_no_asc", "ASC");
		if(!ValidateUtil.isRequired(row)){
			nMap.put("start", "0");
			nMap.put("limit", row);
		}
		List gList=new ArrayList();
		List goodsList=new ArrayList();
		gList=navigationService.getWebList(nMap);
		if(gList!=null&&gList.size()>0){
			for(int i=0;i<gList.size();i++){
				HashMap gmap = new HashMap();
				//构造一个新的对象
				HashMap gnmap = new HashMap();
				gmap=(HashMap)gList.get(i);
				if(gmap!=null){
					//商品ID
					gnmap.put("goodsId", gmap.get("goods_id"));
					//商品名称
					gnmap.put("goodsName", gmap.get("goods_name"));
					//商品图片
					gnmap.put("goodsImg", cfg_mobiledomain+gmap.get("list_img"));
					//商品销售价格
					gnmap.put("goodsSalePrice", gmap.get("min_sale_price"));
					//商品市场价格
					gnmap.put("goodsMarketPrice", gmap.get("goods_market_price"));
					//商品链接地址
					gnmap.put("goodsUrl", cfg_mobiledomain+"/webapp/goodsdetail/"+gmap.get("goods_id")+".html");
					goodsList.add(gnmap);
				}
			}
		}
		return goodsList;
	}
	
	

	
	
	
	
}