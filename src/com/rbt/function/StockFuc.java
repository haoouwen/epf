package com.rbt.function;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.zookeeper.server.quorum.Election;

import com.rbt.createHtml.InfoVo;
import com.rbt.model.Goods;
import com.rbt.model.Goodsattr;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsattrService;
/**	
 * @author : WXP
 * @param :
 * @date May 20, 2014 2:02:05 PM
 * @Method Description :库存处理方法工具类
 */
public class StockFuc extends CreateSpringContext{
	/**	
	 * @author : WXP
	 * @param :order_type 订单类型（0:虚拟 1:普通 2:积分 3:秒杀 4:套餐 5:团购 6:预售） map 条件 diff 订单库存
	 * @date May 20, 2014 2:06:04 PM
	 * @Method Description :更新库存
	 */
	public static void updateStock(char order_type, Map map, int diff){
 		InfoVo iv = new InfoVo();
		//主键
		String trade_id = "";
		if(map.get("trade_id") != null){
			trade_id = map.get("trade_id").toString();
		}
		//商品标识
		String goods_id = "";
		//规格值
		String spec_id = "";
		String sql = "";
		switch (order_type) {  
	      case '2':  
	    	  sql = "update pointsgoods set stock = stock - " +diff+ " where trade_id = " +trade_id;
	          break;  
	      case '3':  
	    	  sql = "update spikegoods set stock = stock - " +diff+ " where trade_id = " +trade_id;
	          break;  
	      case '4':  
	    	  sql = "update combo set stock = stock - " +diff+ " where trade_id = " +trade_id;
	          break;  
	      case '5':  
	    	  sql = "update groupgoods set stock = stock - " +diff+ " where trade_id = " +trade_id;
		      break; 
	      case '6':
	    	  sql = "update directsell set stock = stock - " +diff+ " where trade_id = " +trade_id;
	      default:  
	    	  if(map.get("goods_id") != null ){
	    		  goods_id = map.get("goods_id").toString();
		    	//规格条件
		    	  String flag = "";
		    	  if((map.get("spec_id") != null)&&!"".equals(map.get("spec_id"))&&!"0".equals(map.get("spec_id"))){
		    		  spec_id = map.get("spec_id").toString();
		    		  String[] specstrs = spec_id.split(":");
		  			  for(int i = 0; i < specstrs.length; i++){
		  				 flag = flag +" and specstr like '%" +specstrs[i].trim()+ "%'";
		  			  }
		    	  }
		    	 
	  			 sql = " update goodsattr set stock = stock - " +diff+ " where goods_id = " +goods_id +flag;
			  }
	    }  
		System.out.println("--------------------SQL------------------"+sql);
		iv.excuteSql(sql);
	}
	
	/**
	 * @param args
	 * 运行的主方法
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Map map = new HashMap();
		map.put("trade_id", "53");
		updateStock('4', map, 2);
	}
	
	public static IGoodsattrService getGoodsattrObj() {
		return (IGoodsattrService) getContext().getBean("goodsattrService");
	}
	public static IGoodsService getGoodsObj() {
		return (IGoodsService) getContext().getBean("goodsService");
	}
	
	 /**
	 * @author：XBY
	 * @date：Sep 3, 2014 10:46:55 AM
	 * @Method Description：获取商品库存
	 */
	 @SuppressWarnings("unchecked")
	 public static String goods(String goods_id,String spec_id)
	 {
		 IGoodsattrService goodsattrService=getGoodsattrObj();
		  Goodsattr goods=null;
		 if(goods_id!=null&&!"".equals(goods_id)){
			 if(spec_id==null||"".equals(spec_id)||"0".equals(spec_id.replace(" ", ""))){
				 spec_id=" ";
			 }
			 goods=goodsattrService.getGoodsattr(goods_id,spec_id);
		   return goods.getStock();
		 }
		 return "";
	 }
	 
	 @SuppressWarnings("unchecked")
	 public static int limits(String goods_id)
	 {
		 int limit_num=999999;
		 IGoodsService goodsService=getGoodsObj();
		  Goods goods=new Goods();
		 if(goods_id!=null&&!"".equals(goods_id)){
			 goods=goodsService.get(goods_id);
			 if(goods.getIs_limit().equals("0")){
				 if(goods.getLimit_num()!=null){
					 limit_num=Integer.parseInt(goods.getLimit_num().toString());
				 }
			 }
		   return limit_num;
		 }
		 return limit_num;
	 }
	 
}

