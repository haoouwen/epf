/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: CartgoodsService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.rbt.common.util.ValidateUtil;
import com.rbt.dao.ICartgoodsDao;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Cartgoods;
import com.rbt.service.ICartgoodsService;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @function 功能 购物车Service层业务接口实现
 * @author 创建人 WXP
 * @date 创建日期 Mon May 13 14:10:06 CST 2014
 */
@Service
public class CartgoodsService extends GenericService<Cartgoods, String>
		implements ICartgoodsService {

	ICartgoodsDao cartgoodsDao;

	@Autowired
	public CartgoodsService(ICartgoodsDao cartgoodsDao) {
		super(cartgoodsDao);
		this.cartgoodsDao = cartgoodsDao;
	}

	// 修改购物车
	public void updateCustId(Cartgoods cart) {
		this.cartgoodsDao.updateCustId(cart);
	}

	/**
	 * @author : WXP
	 * @param :
	 * @date May 13, 2014 1:02:09 PM
	 * @Method Description :加入购物车
	 */
	public String addCartGoods(Cartgoods cartgoods, String session_cust_id) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String outStr = "";
		try {
			// 购物车可存放最大商品数
			String cfg_cart_max = SysconfigFuc.getSysValue("cfg_cart_max");
			// 判断该规格商品是否存在购物车里
			Map map = new HashMap();
			map.put("goods_id", cartgoods.getGoods_id());
			map.put("cust_id", session_cust_id);
			if (!ValidateUtil.isRequired(cartgoods.getSpec_id()))
				map.put("sepc_id", cartgoods.getSpec_id());
			// 购物车商品个数
			int count = this.cartgoodsDao.getCount(map);
			if (cartgoods.getSpec_id() != null
					&& !cartgoods.getSpec_id().equals("")) {
				String specStr[] = cartgoods.getSpec_id().split(",");
				if (specStr.length > 0) {
					for (int i = 0; i < specStr.length; i++) {
						map.put("specstr_like" + i, specStr[i]);
					}
				}
			}
			List list = this.cartgoodsDao.getList(map);
			if (list != null && list.size() > 0) {// 商品若在购物车，直接更新数量
				Map cartMap = new HashMap();
				cartMap = (HashMap) list.get(0);
				String trade_id = "";
				if (cartMap.get("trade_id") != null) {
					trade_id = cartMap.get("trade_id").toString();
				}
				Cartgoods cartgds = new Cartgoods();
				cartgds = this.cartgoodsDao.get(trade_id);
				if (cartgds != null) {
					String old_buy_num = cartgds.getBuy_num();
					cartgds.setBuy_num(String.valueOf(Integer
							.parseInt(cartgoods.getBuy_num())
							+ Integer.parseInt(old_buy_num)));
					this.cartgoodsDao.update(cartgoods);
				}
				outStr = "0";

			} else {
				// 是否达到最大个数限制
				if (count >= Integer.parseInt(cfg_cart_max)) {
					outStr = "2";
				} else {
					this.cartgoodsDao.insert(cartgoods);
					if (ValidateUtil.isRequired(session_cust_id))
						session.setAttribute("flag", "1");
					outStr = "1";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			outStr = "3";
		}
		return outStr;
	}

	/**
	 * @author : WXP
	 * @param :trade_id
	 * @date May 15, 2014 9:52:09 AM
	 * @Method Description :删除购物车商品
	 */
	public String delCartGoods(String trade_id) {
		String outStr = "1";
		int count = 0;
		try {
			if (!ValidateUtil.isRequired(trade_id)) {
				trade_id = trade_id.replace(" ", "");
				if (trade_id.indexOf(",") > -1) {
					// 批量删除
					String[] ids = trade_id.split(",");
					for (int i = 0; i < ids.length; i++) {
						count = this.cartgoodsDao.delete(ids[i]);
						if (count == 0) {
							outStr = "0";
							break;
						}
					}
				} else {
					// 删除单个
					count = this.cartgoodsDao.delete(trade_id);
					if (count == 0) {
						outStr = "0";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			outStr = "0";
		}
		return outStr;
	}
}
