/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: ShopconfigService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.dao.IMemberDao;
import com.rbt.dao.IMembercatDao;
import com.rbt.dao.IShopconfigDao;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Member;
import com.rbt.model.Memberuser;
import com.rbt.model.Shopconfig;
import com.rbt.service.IGoodsService;
import com.rbt.service.IMemberService;
import com.rbt.service.IShopconfigService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录商城设置信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Fri Jan 04 10:10:44 CST 2014
 */
@Service
public class ShopconfigService extends GenericService<Shopconfig,String> implements IShopconfigService {
	
	IShopconfigDao shopconfigDao;
	@Autowired
	IMemberDao memberDao;
	@Autowired
	IMemberService memberService;
	@Autowired
	IMembercatDao membercatDao;
	@Autowired
	IGoodsService goodsService;
	@Autowired
	public ShopconfigService(IShopconfigDao shopconfigDao) {
		super(shopconfigDao);
		this.shopconfigDao = shopconfigDao;
	}
	public Shopconfig getByCustID(String cust_id){
		return  (Shopconfig) this.shopconfigDao.getByCustID(cust_id);
	}
	public void ChangeSeller(String cust_id) {
		Member	member = this.memberDao.get(cust_id);
		if(member==null){
			return ;
		}
		member.setMem_type("0");
		// 若已存在卖家等级不做修改，否设为默认等级
		if (member.getSell_level() == null||member.getSell_level().equals("0")) {
			// 新卖家默认等级
			String cfg_sell_member = SysconfigFuc.getSysValue("cfg_sell_member");
			member.setSell_level(cfg_sell_member);
		}
		// 更新Member设置
		this.memberDao.update(member);
		// 会员审核通过时初始化插入的数据
		this.memberService.insertChangeSeller(cust_id);
		
	}
	public Map getIndexMap(Shopconfig shopconfig,String temp_loc) {
		Map indexMap=new HashMap();//取店铺首页数据
		List goodsList,newgoodsLsit,populList,collectList,hotsaleList,custCatList;
		String shop_cust_id=shopconfig.getCust_id();
		String temp_loc5 = temp_loc;
		// 推荐商品
		goodsList = this.goodsService.getGoodsLabList(shop_cust_id, "2", 8);
		// 新品商品
		newgoodsLsit = this.goodsService.getGoodsLabList(shop_cust_id, "1",
				8);
		// 人气商品
		populList = this.goodsService.getGoodsLabList(shop_cust_id, "3", 8);
		// 店铺商品的自定义分类
		Map custcatMap = new HashMap();
		custcatMap.put("cust_id", shop_cust_id);
		custcatMap.put("cat_type", "3");
		custCatList = this.membercatDao.getList(custcatMap);
		indexMap.put("shopconfig", shopconfig);
		indexMap.put("goodsList", goodsList);
		indexMap.put("newgoodsLsit", newgoodsLsit);
		indexMap.put("populList", populList);
		indexMap.put("custCatList", custCatList);
		return indexMap;
	}
	
}

