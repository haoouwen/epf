/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: ComboService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.rbt.dao.IComboDao;
import com.rbt.dao.IMemberchannelDao;
import com.rbt.dao.IShopconfigDao;
import com.rbt.model.Combo;
import com.rbt.service.IComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 套餐表Service层业务接口实现
 * @author 创建人 LHY
 * @date 创建日期 Mon Mar 25 15:09:17 CST 2014
 */
@Service
public class ComboService extends GenericService<Combo,String> implements IComboService {
	@Autowired
	private IShopconfigDao shopconfigDao;
	@Autowired
	private IMemberchannelDao memberchannelDao;
	IComboDao comboDao;

	@Autowired
	public ComboService(IComboDao comboDao) {
		super(comboDao);
		this.comboDao = comboDao;
	}
	
	public String getComboGoodsid(String goods_id){
		return this.comboDao.getComboGoodsid(goods_id);
	}
	/**
	 * @author：XBY
	 * @date：Feb 17, 2014 1:06:22 PM
	 * @Method Description：所有商品价格
	 */
	public Double allPrice(List goodsList,Double all_goods_price){
		if(goodsList != null && goodsList.size() > 0){
			for(int i = 0; i < goodsList.size(); i++){
				Map map = new HashMap();
				map  = (HashMap) goodsList.get(i);
				if(map.get("min_sale_price") != null){
					all_goods_price = all_goods_price + Double.parseDouble(map.get("min_sale_price").toString());
				}
			}
		}
		return all_goods_price;
	}
	/**
	 * @author：XBY
	 * @date：Feb 17, 2014 1:11:22 PM
	 * @Method Description：获取用户名称
	 */
	public String getUserName(List list){
		String user_name="";
		if(list!=null&&list.size()>0){
			 Map listMap=new HashMap();
			 listMap=(HashMap)list.get(0);
			 if(listMap.get("user_name")!=null){
				 user_name=listMap.get("user_name").toString();
			 }					 
		}
		return user_name;
	}
}

