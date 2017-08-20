package com.rbt.function;

import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Goods;
import com.rbt.service.IGoodsService;
/**	
 * @author : WXP
 * @param :
 * @date May 20, 2014 2:02:05 PM
 * @Method Description :库存处理方法工具类
 */
public class GoodsFuc extends CreateSpringContext{
	
	public static IGoodsService getGoodsObj() {
		return (IGoodsService) getContext().getBean("goodsService");
	}
	 
	 /**
	  * 判断商品是否上架
	  * @param goods_id
	  * @return
	  */
	 public static String is_up(String goods_id){
		 IGoodsService goodsService=getGoodsObj();
		 String up_flag = "";
		 Goods goods = new Goods();
		 goods = goodsService.get(goods_id);
		 up_flag = goods.getIs_up();
		 return up_flag;
	 }
	 
	 /**
	  * 获取商品名称
	  * @param goods_id 商品ID串
	  * @return 返回商品名称
	  */
	 public static String getGoodsName(String goods_id) {
		 String goods_name = "";
		 if(!ValidateUtil.isRequired(goods_id)) {
			 String[] goods_ids = goods_id.trim().split(",");
			 for(int i = 0; i < goods_ids.length; i ++) {
				 Goods goods = new Goods();
				 goods = getGoodsObj().get(goods_ids[i]);
				 if(goods != null) {
					if(ValidateUtil.isRequired(goods_name)) {
						 goods_name = goods.getGoods_name(); 
					}else {
						 goods_name += "," +goods.getGoods_name();
					}
					
				 }
			 }
		 }
		 
		 return goods_name;
	 }
}

