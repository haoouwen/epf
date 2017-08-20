/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: GoodsorderService.java 
 */
package com.rbt.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import jxl.Cell;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import com.rbt.common.util.DateUtil;
import com.rbt.common.util.PropertiesUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.dao.ICancelorderDao;
import com.rbt.dao.ICancleordertransDao;
import com.rbt.dao.IComsumerDao;
import com.rbt.dao.IDirectorderdetailDao;
import com.rbt.dao.IDirectsellDao;
import com.rbt.dao.IExchangeDao;
import com.rbt.dao.IFundhistoryDao;
import com.rbt.dao.IGoodsDao;
import com.rbt.dao.IGoodsattrDao;
import com.rbt.dao.IGoodsevalDao;
import com.rbt.dao.IGoodsorderDao;
import com.rbt.dao.IGroupgoodsDao;
import com.rbt.dao.IInterhistoryDao;
import com.rbt.dao.IKjtrecallDao;
import com.rbt.dao.IMemberinterDao;
import com.rbt.dao.IMemberuserDao;
import com.rbt.dao.IOrderdetailDao;
import com.rbt.dao.IOrderinvoiceDao;
import com.rbt.dao.IOrdertransDao;
import com.rbt.dao.IPaymentDao;
import com.rbt.dao.IPointsgoodsDao;
import com.rbt.dao.IRedsumerDao;
import com.rbt.dao.IRefundappDao;
import com.rbt.dao.ISendmodeDao;
import com.rbt.dao.ISpikegoodsDao;
import com.rbt.function.CommparaFuc;
import com.rbt.function.CouponFuc;
import com.rbt.function.MembercreditFuc;
import com.rbt.function.MessageAltFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Directorderdetail;
import com.rbt.model.Directsell;
import com.rbt.model.Fundhistory;
import com.rbt.model.Goods;
import com.rbt.model.Goodsattr;
import com.rbt.model.Goodseval;
import com.rbt.model.Goodsorder;
import com.rbt.model.Groupgoods;
import com.rbt.model.Interhistory;
import com.rbt.model.Memberinter;
import com.rbt.model.Memberuser;
import com.rbt.model.Ordertrans;
import com.rbt.model.Payment;
import com.rbt.model.Pointsgoods;
import com.rbt.model.Sendmode;
import com.rbt.model.Spikegoods;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.ISysfundService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @function 功能 商品订单Service层业务接口实现
 * @author 创建人 LHY
 * @date 创建日期 Fri Feb 01 16:00:36 CST 2014
 */
@Service
public class GoodsorderService extends GenericService<Goodsorder, String>
		implements IGoodsorderService {

	IGoodsorderDao goodsorderDao;

	@Autowired
	private IOrderdetailDao orderdetailDao;

	@Autowired
	private IPointsgoodsDao pointsgoodsDao;

	@Autowired
	private IDirectsellDao directsellDao;

	@Autowired
	private ISpikegoodsDao spikegoodsDao;

	@Autowired
	private IGoodsattrDao goodsattrDao;

	@Autowired
	private IGroupgoodsDao groupgoodsDao;

	@Autowired
	private IDirectorderdetailDao directorderdetailDao;

	@Autowired
	private IFundhistoryDao fundhistoryDao;

	@Autowired
	private IMemberinterDao memberinterDao;

	@Autowired
	private IInterhistoryDao interhistoryDao;
	
	@Autowired
	private IOrdertransDao OrdertransDao;
	
	@Autowired
	private IGoodsDao goodsDao;
	
	
	@Autowired
	private ISendmodeDao sendmodeDao;
	
	@Autowired
	private IMemberuserDao memberuserDao;
	
	@Autowired
	public GoodsorderService(IGoodsorderDao goodsorderDao) {
		super(goodsorderDao);
		this.goodsorderDao = goodsorderDao;
	}
	@Autowired
	private ISysfundService sysfundService;
	@Autowired
	private IMemberfundService memberfundService;
	@Autowired
	private ICancelorderDao cancelorderDao;
	@Autowired
	private ICancleordertransDao cancleordertransDao;
	@Autowired
	private IComsumerDao comsumerDao;
	@Autowired
	private IExchangeDao exchangeDao;
	@Autowired
	private IGoodsevalDao goodsevalDao;
	@Autowired
	private IKjtrecallDao kjtrecallDao;
	@Autowired
	private IOrderinvoiceDao orderinvoiceDao;
	@Autowired
	private IRedsumerDao redsumerDao;
	@Autowired
	private IRefundappDao refundappDao;
	@Autowired
	private IPaymentDao paymentDao;

	@SuppressWarnings("unchecked")
	public int getWebCount(Map map) {
		return this.goodsorderDao.getWebCount(map);
	}

	@SuppressWarnings("unchecked")
	public int getWebGoodsCount(Map map) {
		return this.goodsorderDao.getWebGoodsCount(map);
	}

	@SuppressWarnings("unchecked")
	public List getWebList(Map map) {
		return this.goodsorderDao.getWebList(map);
	}

	public void updateState(Map map) {
		this.goodsorderDao.updateState(map);
	}

	public void update(Map<String, String> map) {
		this.goodsorderDao.update(map);
	}

	@SuppressWarnings("unchecked")
	public List getoverList(Map map) {
		return this.goodsorderDao.getoverList(map);
	}

	public List getTake(Map map) {
		return this.goodsorderDao.getTake(map);
	}
	//首单插券-CJY
	public List getCustlist(String custID){
		return this.goodsorderDao.getCustlist(custID);
	}
	public Map replaceList(List goodsorderList) {
		String directOidStr = "";
		String orderidStr = "";
		Map map = new HashMap();
		if (goodsorderList != null && goodsorderList.size() > 0) {
			for (int i = 0; i < goodsorderList.size(); i++) {
				Map listMap = new HashMap();
				listMap = (HashMap) (goodsorderList).get(i);
				if (listMap.get("order_id") != null) {
					orderidStr += listMap.get("order_id").toString() + ",";
				}
				if (listMap.get("order_type") != null
						&& listMap.get("order_type").equals("6")) {
					directOidStr += listMap.get("order_id").toString() + ",";
				}
			}
		}
		map.put("orderidStr", orderidStr);
		map.put("directOidStr", directOidStr);
		return map;
	}

	// 积分定时检查订单是否支付，30分钟未支付取消订单并恢复
	public void orderouttime(Map map) {
		List goodsorderlist = goodsorderDao.getList(map);
		for (int i = 0; i < goodsorderlist.size(); i++) {
			HashMap maps = (HashMap) goodsorderlist.get(i);
			String order_id = "";// 获取订单号
			order_id = maps.get("order_id").toString();
			Goodsorder goodsorder = goodsorderDao.get(order_id);
			// 修改订单状态未已取消
			goodsorder.setOrder_state("0");
			goodsorderDao.update(goodsorder);
			// 获得积分订单详情
			HashMap ordermap = new HashMap();
			ordermap.put("order_id", order_id);
			List orderdetaillist = orderdetailDao.getList(ordermap);
			HashMap mapvlaue=new HashMap(); 
			if(orderdetaillist!=null&&orderdetaillist.size()>0){
			    mapvlaue = (HashMap) orderdetaillist.get(0);
			}
			// 恢复积分商品库存量
			String goods_id = mapvlaue.get("goods_id").toString();// 获取商品编号
			HashMap pointsmap = new HashMap();
			pointsmap.put("goods_id", goods_id);
			
			List pointsgoodslist = pointsgoodsDao.getList(pointsmap);
			HashMap pointvalue=new HashMap(); 
			if(pointsgoodslist!=null&&pointsgoodslist.size()>0){
			   pointvalue = (HashMap) pointsgoodslist.get(0);
			}
			Pointsgoods pointsgoods = pointsgoodsDao.get(pointvalue.get(
					"trade_id").toString());
			String stocknum = Integer.toString((Integer.parseInt(pointsgoods
					.getStock()) + Integer.parseInt(mapvlaue.get("order_num")
					.toString())));
			pointsgoods.setStock(stocknum);
			pointsgoodsDao.update(pointsgoods);
		}

	}

	// 预售定时检查订单是否支付，30分钟未支付取消订单并恢复
	public void directouttime(Map map) {
		List directselllist = directsellDao.getdeliverpay(map);
		for (int i = 0; i < directselllist.size(); i++) {
			HashMap maps = (HashMap) directselllist.get(i);
			String order_id = "";// 获取订单号
			order_id = maps.get("order_id").toString();
			Goodsorder goodsorder = goodsorderDao.get(order_id);
			// 修改订单状态未已取消
			goodsorder.setOrder_state("0");
			goodsorderDao.update(goodsorder);
			// 获得预约订单详情
			String trade_id = "";// 获取订单号
			trade_id = maps.get("trade_id").toString();
			Directorderdetail directordertail = directorderdetailDao.get(trade_id);
			// 恢复商品库存量
			Directsell dircetsell = directsellDao.get(trade_id);
			String stocknum = Integer.toString((Integer.parseInt(dircetsell
					.getStock()) + Integer.parseInt(directordertail
					.getOrder_num())));
			dircetsell.setStock(stocknum);
			directsellDao.update(dircetsell);
		}
	}

	// 秒杀定时检查订单是否支付，30分钟未支付取消订单并恢复
	public void spikeorderout(Map map) {
		List goodsorderlist = goodsorderDao.getList(map);
		for (int i = 0; i < goodsorderlist.size(); i++) {
			HashMap maps = (HashMap) goodsorderlist.get(i);
			String order_id = "";// 获取订单号
			order_id = maps.get("order_id").toString();
			Goodsorder goodsorder = goodsorderDao.get(order_id);
			// 修改订单状态未已取消
			goodsorder.setOrder_state("0");
			goodsorderDao.update(goodsorder);
			// 获得秒杀订单详情
			HashMap ordermap = new HashMap();
			ordermap.put("order_id", order_id);
			List orderdetaillist = orderdetailDao.getList(ordermap);
			HashMap mapvlaue=new HashMap ();
			if(orderdetaillist!=null&&orderdetaillist.size()>0){
			  mapvlaue = (HashMap) orderdetaillist.get(0);
			}
			// 恢复积分商品库存量
			String goods_id = mapvlaue.get("goods_id").toString();// 获取商品编号
			HashMap spikesmap = new HashMap();
			spikesmap.put("goods_id", goods_id);
			List spikegoodslist = spikegoodsDao.getList(spikesmap);
			HashMap pointvalue = (HashMap) spikegoodslist.get(0);
			Spikegoods spikegoods = spikegoodsDao.get(pointvalue
					.get("trade_id").toString());
			String stocknum = Integer.toString((Integer.parseInt(spikegoods
					.getStock()) + Integer.parseInt(mapvlaue.get("order_num")
					.toString())));
			spikegoods.setStock(stocknum);
			spikegoodsDao.update(spikegoods);
		}
	}

	/**
	 * @author Administrator QJY
	 * @method 未付款取消订单，若在提交订单时使用部分余额，需退回给会员
	 * @date 2015-10-14
	 * @update_date 2015-10-14
	 */
	public void publicorderout(Map map) {
		//红包，优惠券
		CouponFuc recoupon = new CouponFuc();
		
		List goodsorderlist = goodsorderDao.getList(map);
		for (int i = 0; i < goodsorderlist.size(); i++) {
			HashMap maps = (HashMap) goodsorderlist.get(i);
			String order_id = "";// 获取订单号
			order_id = maps.get("order_id").toString();
			Goodsorder goodsorder = goodsorderDao.get(order_id);
			// 修改订单状态未已取消
			goodsorder.setOrder_state("0");
			goodsorderDao.update(goodsorder);
			String buy_cust_id = goodsorder.getBuy_cust_id();
		    String sell_cust_id = goodsorder.getSell_cust_id();
			double balance_use = Double.valueOf(goodsorder.getBalance_use());//使用的余额
			double integral_use = Double.valueOf(goodsorder.getIntegral_use());//使用的积分 
			Memberuser memberuser = this.memberuserDao.getPKByCustID(sell_cust_id);
			String sell_user_id = memberuser.getUser_id();
			//退回使用的余额
			backBalanceUsed(balance_use,buy_cust_id,sell_user_id,order_id);
			//退回使用的积分
			backIntegralUsed(integral_use,buy_cust_id,sell_user_id,order_id);
			//退回优惠券，红包
			recoupon.backCouponAnd(order_id);
			
			// 获得普通订单详情
			HashMap ordermap = new HashMap();
			ordermap.put("order_id", order_id);
			List orderdetaillist = orderdetailDao.getList(ordermap);
			if (orderdetaillist != null && orderdetaillist.size() > 0) {
				HashMap mapvlaue = (HashMap) orderdetaillist.get(0);
				// 恢复普通商品库存量
				String goods_item = mapvlaue.get("goods_item").toString();// 获取商品编号
				Goodsattr goodsattr = goodsattrDao.get(goods_item);
				String stocknum = Integer.toString((Integer.parseInt(goodsattr
						.getStock()) + Integer.parseInt(mapvlaue.get(
						"order_num").toString())));
				goodsattr.setStock(stocknum);
				goodsattrDao.update(goodsattr);
			}
		}
	}

	/**
	 * 退回使用的余额
	 */
	public void backBalanceUsed(double balance_use,String buy_cust_id,String sell_user_id,String order_id){
		//执行退款的时候，需要把平台总资金的冻结订单金额 解冻到可用资金里面
		sysfundService.freezeNum(balance_use, 1);//退回使用的余额
		//退回余额
		 Double use_num = memberfundService.freezeNum(buy_cust_id,balance_use,1);//退回使用的余额
		 
		//会员的资金异动
		Fundhistory buy_fh = new Fundhistory();
		buy_fh.setBalance(use_num);
		buy_fh.setFund_in(balance_use);
		buy_fh.setFund_out(0.0);
		buy_fh.setCust_id(buy_cust_id);
		buy_fh.setUser_id(sell_user_id);
		buy_fh.setReason("会员收到订单号:"+order_id+" 的订单退款"+balance_use+"元");
		this.fundhistoryDao.insert(buy_fh);
		
	}
	
	/**
	 * 退回使用的积分
	 */
	public void backIntegralUsed(double integral_use,String buy_cust_id,String sell_user_id,String order_id){
		//退回 积分给会员
		Memberinter memberinter = this.memberinterDao.get(buy_cust_id);
		int cfg_sc_exchscale=Integer.parseInt(SysconfigFuc.getSysValue("cfg_sc_exchscale"));
		integral_use = integral_use*cfg_sc_exchscale;
		Double total_inter = Double.valueOf(memberinter.getFund_num())+integral_use;
		memberinter.setFund_num(String.valueOf(total_inter));
		//积分异动
		Interhistory interhistory = new Interhistory();
		interhistory.setInter_in(String.valueOf(integral_use));
		interhistory.setInter_out("0");
		interhistory.setThisinter(String.valueOf(total_inter));
		interhistory.setUser_id(sell_user_id);
		interhistory.setCust_id(buy_cust_id);
		interhistory.setReason("会员取消订单");
		interhistory.setRemark("");
		
		//更新积分表和积分异动表
		this.memberinterDao.updateinter(interhistory, memberinter); 
		
	}
	
	public void grouporderout(Map map) {
		List goodsorderlist = goodsorderDao.getList(map);
		for (int i = 0; i < goodsorderlist.size(); i++) {
			HashMap maps = (HashMap) goodsorderlist.get(i);
			String order_id = "";// 获取订单号
			order_id = maps.get("order_id").toString();
			Goodsorder goodsorder = goodsorderDao.get(order_id);
			// 修改订单状态未已取消
			goodsorder.setOrder_state("0");
			goodsorderDao.update(goodsorder);
			// 获得秒杀订单详情
			HashMap ordermap = new HashMap();
			ordermap.put("order_id", order_id);
			List orderdetaillist = orderdetailDao.getList(ordermap);
			if (orderdetaillist != null && orderdetaillist.size() > 0) {
				HashMap mapvlaue = (HashMap) orderdetaillist.get(0);
				// 恢复积分商品库存量
				String goods_id = mapvlaue.get("goods_id").toString();// 获取商品编号
				HashMap groupmap = new HashMap();
				groupmap.put("goods_id", goods_id);
				List groupgoodsList = groupgoodsDao.getList(groupmap);
				if (groupgoodsList != null && groupgoodsList.size() > 0) {
					HashMap mapvalue = (HashMap) groupgoodsList.get(0);
					Groupgoods groupgoods = groupgoodsDao.get(mapvalue.get(
							"trade_id").toString());
					String stocknum = Integer.toString((Integer
							.parseInt(groupgoods.getStock()) + Integer
							.parseInt(mapvlaue.get("order_num").toString())));
					groupgoods.setStock(stocknum);
					groupgoodsDao.update(groupgoods);
				}
			}
		}
	}

	/**
	 * 自动回收取消订单
	 */
	public void cancelorderout(Map map) {
		
		//获取取消订单集合
		List goodsorderlist = goodsorderDao.getList(map);
		
        //判断订单集合不为空
		if(goodsorderlist!=null&&goodsorderlist.size()>0){
	 	 
		  for (int i = 0; i < goodsorderlist.size(); i++) {
			
			HashMap maps = (HashMap) goodsorderlist.get(i);
			String order_id = "";// 获取订单号
			order_id = maps.get("order_id").toString();
			// 回收订单
			Map orderMap=new HashMap();
            orderMap.put("order_id", order_id);
            orderMap.put("is_del", "0");
			goodsorderDao.updateState(orderMap);
		 }
		  
		}
	}
	
	/**
	 * @author : HXM
	 * @param :oid:订单编号，
	 *            session_user_id:用于插入资金流操作人字段
	 * @date Mar 28, 2014 1:33:55 PM
	 * @Method Description :将资金从运营转入卖家
	 */
	public void sellerFundManage(String oid, String session_user_id) {
		Goodsorder order = this.goodsorderDao.get(oid);
		if (order != null) {
			double final_amount = order.getTatal_amount()-Double.parseDouble(order.getBalance_use())-Double.parseDouble(order.getIntegral_use());
			// 获取商家的ID
			String seller_cust_id = order.getSell_cust_id();
			String buy_cust_id = order.getBuy_cust_id();
			// 卖家资金处理
			double j1 = memberfundService.outAndInNum(seller_cust_id,final_amount, 1);
			Payment payment = paymentDao.get(order.getPay_id());
			if(payment.getPay_code().equals("goldpay")){
				//支付为金币支付的情况才去处理 解冻金币的情况
				double i1 = memberfundService.freezePlusNum(buy_cust_id,Double.parseDouble(order.getBalance_use()), 0);
			}
			// 运营商资金处理
			sysfundService.freezeNum(final_amount, 1);
			
			// 卖家的资金异动
			Fundhistory buy_fh = new Fundhistory();
			buy_fh.setBalance(j1);
			buy_fh.setCust_id(seller_cust_id);
			buy_fh.setFund_in(final_amount);
			buy_fh.setFund_out(0.00);
			buy_fh.setUser_id(session_user_id);
			buy_fh.setReason("商家收到订单号:" + oid + "支付" + final_amount + "元");
			this.fundhistoryDao.insert(buy_fh);
		}
	}
	
	/**
	 * @author : HXM
	 * @param :oid:订单编号，
	 *            session_user_id:用于插入资金流操作人字段
	 * @date Mar 28, 2014 1:33:55 PM
	 * @Method Description :将资金从运营转入卖家
	 */
	public void onlinepayManage(String oid, String session_user_id) {
		Goodsorder order = this.goodsorderDao.get(oid);
		if (order != null) {
			double final_amount = order.getTatal_amount()-Double.parseDouble(order.getBalance_use());
			// 获取商家的ID
			String seller_cust_id = order.getSell_cust_id();
			//商家入金处理
			double j1 = memberfundService.outAndInNum(seller_cust_id,final_amount, 1);
			// 运营商资金处理
			sysfundService.freezeNum(final_amount, 1);
			
			// 卖家的资金异动
			Fundhistory buy_fh = new Fundhistory();
			buy_fh.setBalance(j1);
			buy_fh.setCust_id(seller_cust_id);
			buy_fh.setFund_in(final_amount);
			buy_fh.setFund_out(0.00);
			buy_fh.setUser_id(session_user_id);
			buy_fh.setReason("商家收到订单号:" + oid + "支付" + final_amount + "元");
			this.fundhistoryDao.insert(buy_fh);
		}
	}
	
	/**
	 * @author:HXM
	 * @date:Jul 16, 201410:00:53 AM
	 * @param: goodsorder_id:订单编号，cfg_sc_pointsrule：系统返回的积分，
	 *         session_user_id:用于插入积分记录的操作人
	 * @Description: 插入积分
	 */
	public void insertOrderInter(String goodsorder_id,
			String cfg_sc_pointsrule, String session_user_id) {
		if (!ValidateUtil.isRequired(goodsorder_id)) {
			Goodsorder god = new Goodsorder();
			// 获取订单的买家会员
			god = goodsorderDao.get(goodsorder_id);
			String b_cust_id = "";
			b_cust_id = god.getBuy_cust_id();
			Memberinter meminter = new Memberinter();
			// 获取会员当前的积分
			meminter = memberinterDao.get(b_cust_id);
			Double coutinter = 0.0;
			// 统计当前订单的总额数为获得积分总额数+当前积分数
			double Inter_in = (Double.parseDouble(cfg_sc_pointsrule) * Double
					.parseDouble(god.getTatal_amount().toString())) / 100;
			coutinter = Inter_in + Double.parseDouble(meminter.getFund_num());
			meminter.setFund_num(coutinter.toString());
			memberinterDao.update(meminter);
			// 插入积分流
			Interhistory interhistory = new Interhistory();
			interhistory.setCust_id(b_cust_id);
			interhistory.setInter_in(Double.toString(Inter_in));
			interhistory.setInter_out("0");
			interhistory.setThisinter(coutinter.toString());
			interhistory.setUser_id(session_user_id);
			interhistory.setReason("购买商品获取积分,商品订单号为:" + goodsorder_id);
			interhistoryDao.insert(interhistory);
		}
	}
	
	
	/**
	 * @Method Description : 系统执行自动确认收货
	 * @author: 胡惜坤
	 * @date : Feb 18, 2016 3:04:05 PM
	 * @param 
	 * @return return_type
	 */
	public void updateAutoConfirmReceipt(Map map) {
		//取出为收货的订单
		List list=new ArrayList();
		list=goodsorderDao.getConfirmReceiptOrderList(map);
		if(list!=null&&list.size()>0){
			for (int i = 0; i <list.size(); i++) {
				Map oMap=new HashMap();
				oMap=(HashMap) list.get(i);
				//订单的id
				String orderId=(String) oMap.get("order_id");
				if(oMap!=null&&oMap.get("order_state")!=null&&"3".equals(oMap.get("order_state"))){
					// 付款到卖家
					sellerFundManage(orderId,"0");
					// 插入积分
					insertOrderInter(orderId,SysconfigFuc.getSysValue("cfg_sc_pointsrule"),"0");
					Map temp=new HashMap();
					temp.put("order_id",orderId);
					temp.put("order_state","7");
					try {
						 //插入确认收货时间
						temp.put("sure_time",DateUtil.getCurrentTime().toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					//更新商品订单状态
					goodsorderDao.update(temp);
				}
			}
		}
	}
	
	/**
	 * @Method Description :系统自动评价
	 * @author: 胡惜坤
	 * @date : Feb 18, 2016 3:10:00 PM
	 * @param 
	 * @return return_type
	 */
    public void autoASS(List list){
		String cust_id="";
		String sell_cust_id="";
    	Map oMap=new HashMap();
		if(list!=null&&list.size()>0){
			Goodseval goodseval=new Goodseval();
			String goodsId="";
			for (int i = 0; i <list.size(); i++) {
				oMap=(HashMap) list.get(i);
				//从map里取出对应的数据
				goodsId=oMap.get("goods_id").toString();
				cust_id=oMap.get("buy_cust_id").toString();
				sell_cust_id=oMap.get("sell_cust_id").toString();
				//添加商品评价
				goodseval.setCust_id(cust_id);
				goodseval.setGoods_id(goodsId);
				goodseval.setG_eval("1");
				goodseval.setIs_two("1");
				goodseval.setExplan_cust_id(sell_cust_id);
				if(oMap.get("order_id")!=null&&!"".equals(oMap.get("order_id"))){
					goodseval.setOrder_id(oMap.get("order_id").toString());
				}
				goodseval.setG_comment("好评");
				goodsevalDao.insert(goodseval);
				MembercreditFuc.updateSellNum(sell_cust_id, 1);
				Map temp=new HashMap();
				//更新商品订单状态
				temp.put("order_id",oMap.get("order_id"));
				temp.put("order_state","8");
				goodsorderDao.update(temp);
			}
			
       }
    }
	
	
	
	
	
	
	
	
	
	
	
	

	public void exportExcel(List orderlist, HttpServletResponse response) throws Exception {
		orderlist=ToolsFuc.replaceList(orderlist, "");
		response.reset();
	    response.setContentType("application/vnd.ms-excel");
	    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	    String nowtime =df.format(new Date());
	    response.setHeader("Content-Disposition", "filename="+nowtime+"Order.xls");//attachment // WritableWorkbook是JexcelApi的一个类。
	// 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
	    OutputStream os = response.getOutputStream();//取得输出流
	    WritableWorkbook workbook = Workbook.createWorkbook(os);
	    WritableSheet sheet = workbook.createSheet("订单列表", 0); // 组织excel文件的内容
	    jxl.write.Label label = null;
	    int excelCol = 0;
	    int row = 0;
	    try {
	    	WritableCellFormat wc = new WritableCellFormat();
	    	wc.setVerticalAlignment(VerticalAlignment.CENTRE); 
	        wc.setAlignment(Alignment.CENTRE); 
	        label = new jxl.write.Label(excelCol++, row, "订单序号", wc);
	        sheet.setColumnView(0,35);
	        sheet.mergeCells( 0 , 0 , 0 , 1 );
	        sheet.addCell(label);
	        label = new jxl.write.Label(excelCol++, row, "收件方信息", wc);
	     // sheet.setColumnView(1,15);
	       sheet.mergeCells( 1 ,0 , 5 , 0 );
	        sheet.addCell(label);
	        label = new jxl.write.Label(excelCol-1, 1, "联系人", wc);
	        sheet.setColumnView(excelCol,15);
	      //  sheet.mergeCells( 1 , 1 , 1 , 1 );
	        sheet.addCell(label);
	        label = new jxl.write.Label(excelCol++, 1, "联系电话", wc);
	       sheet.setColumnView(excelCol,15);
	       // sheet.mergeCells( 2 , 1 , 2 , 1 );
	        sheet.addCell(label);
	        label = new jxl.write.Label(excelCol++, 1, "手机号码 ", wc);
	       sheet.setColumnView(excelCol,15);
	       // sheet.mergeCells( 3 , 1 , 3 , 1 );
	        sheet.addCell(label);
	        label = new jxl.write.Label(excelCol++, 1, "收件详细地址", wc);
	      sheet.setColumnView(excelCol,15);
	       // sheet.mergeCells( 4 , 1 , 4 , 1 );
	        sheet.addCell(label);
	       label = new jxl.write.Label(excelCol++, 1, "买家昵称", wc);
	        sheet.setColumnView(excelCol,30);
	       // sheet.mergeCells( 5 , 1 , 5 , 1 );
	        sheet.addCell(label);
	        
	        
	        label = new jxl.write.Label(excelCol++, row, "商品信息", wc);
		     // sheet.setColumnView(1,15);
		       sheet.mergeCells( 6 ,0 , 10 , 0 );
		        sheet.addCell(label);
		        label = new jxl.write.Label(excelCol-1, 1, "商品编码", wc);
		        sheet.setColumnView(excelCol,30);
		       // sheet.mergeCells( 5 , 1 , 5 , 1 );
		        sheet.addCell(label);
		        label = new jxl.write.Label(excelCol++, 1, "商品标题", wc);
		        sheet.setColumnView(excelCol,30);
		       // sheet.mergeCells( 5 , 1 , 5 , 1 );
		        sheet.addCell(label);
		        label = new jxl.write.Label(excelCol++, 1, "商品数量", wc);
		        sheet.setColumnView(excelCol,30);
		       // sheet.mergeCells( 5 , 1 , 5 , 1 );
		        sheet.addCell(label);
		        label = new jxl.write.Label(excelCol++, 1, "订单金额", wc);
		        sheet.setColumnView(excelCol,30);
		       // sheet.mergeCells( 5 , 1 , 5 , 1 );
		        sheet.addCell(label);
		        label = new jxl.write.Label(excelCol++, 1, "商品金额", wc);
		        sheet.setColumnView(excelCol,30);
		       // sheet.mergeCells( 5 , 1 , 5 , 1 );
		        sheet.addCell(label);
		        
		        label = new jxl.write.Label(excelCol++, row, "附加服务", wc);
			     // sheet.setColumnView(1,15);
			       sheet.mergeCells( 11 ,0 , 15 , 0 );
			        sheet.addCell(label);
			        label = new jxl.write.Label(excelCol-1, 1, "代收金额", wc);
			        sheet.setColumnView(excelCol,30);
			       // sheet.mergeCells( 5 , 1 , 5 , 1 );
			        sheet.addCell(label);
			        label = new jxl.write.Label(excelCol++, 1, "保价金额", wc);
			        sheet.setColumnView(excelCol,30);
			       // sheet.mergeCells( 5 , 1 , 5 , 1 );
			        sheet.addCell(label);
			        label = new jxl.write.Label(excelCol++, 1, "纸箱费", wc);
			        sheet.setColumnView(excelCol,30);
			       // sheet.mergeCells( 5 , 1 , 5 , 1 );
			        sheet.addCell(label);
			        label = new jxl.write.Label(excelCol++, 1, "签单返还", wc);
			        sheet.setColumnView(excelCol,30);
			       // sheet.mergeCells( 5 , 1 , 5 , 1 );
			        sheet.addCell(label);
			        label = new jxl.write.Label(excelCol++, 1, "自取件", wc);
			        sheet.setColumnView(excelCol,30);
			       // sheet.mergeCells( 5 , 1 , 5 , 1 );
			        sheet.addCell(label);
			        
			        label = new jxl.write.Label(excelCol++, row, "其他", wc);
				     // sheet.setColumnView(1,15);
				       sheet.mergeCells( 16 ,0 ,34 , 0 );
				        sheet.addCell(label);
				        label = new jxl.write.Label(excelCol-1, 1, "业务类型", wc);
				        sheet.setColumnView(excelCol,30);
				       // sheet.mergeCells( 5 , 1 , 5 , 1 );
				        sheet.addCell(label);
				        label = new jxl.write.Label(excelCol++, 1, "付款方式", wc);
				        sheet.setColumnView(excelCol,30);
				       // sheet.mergeCells( 5 , 1 , 5 , 1 );
				        sheet.addCell(label);
				        label = new jxl.write.Label(excelCol++, 1, "件数", wc);
				        sheet.setColumnView(excelCol,30);
				       // sheet.mergeCells( 5 , 1 , 5 , 1 );
				        sheet.addCell(label);  
				        label = new jxl.write.Label(excelCol++, 1, "包裹重量", wc);
				        sheet.setColumnView(excelCol,30);
				       // sheet.mergeCells( 5 , 1 , 5 , 1 );
				        sheet.addCell(label); 
				        label = new jxl.write.Label(excelCol++, 1, "买家备注", wc);
				        sheet.setColumnView(excelCol,30);
				       // sheet.mergeCells( 5 , 1 , 5 , 1 );
				        sheet.addCell(label);
				        label = new jxl.write.Label(excelCol++, 1, "卖家备注", wc);
				        sheet.setColumnView(excelCol,30);
				       // sheet.mergeCells( 5 , 1 , 5 , 1 );
				        sheet.addCell(label);
				        label = new jxl.write.Label(excelCol++, 1, "币种", wc);
				        sheet.setColumnView(excelCol,30);
				       // sheet.mergeCells( 5 , 1 , 5 , 1 );
				        sheet.addCell(label);  
				        label = new jxl.write.Label(excelCol++, 1, "头程运单号", wc);
				        sheet.setColumnView(excelCol,30);
				       // sheet.mergeCells( 5 , 1 , 5 , 1 );
				        sheet.addCell(label);  
				        label = new jxl.write.Label(excelCol++, 1, "支付工具", wc);
				        sheet.setColumnView(excelCol,30);
				       // sheet.mergeCells( 5 , 1 , 5 , 1 );
				        sheet.addCell(label);
				        label = new jxl.write.Label(excelCol++, 1, "支付号", wc);
				        sheet.setColumnView(excelCol,30);
				       // sheet.mergeCells( 5 , 1 , 5 , 1 );
				        sheet.addCell(label);
				        label = new jxl.write.Label(excelCol++, 1, "是否定时派送", wc);
				        sheet.setColumnView(excelCol,30);
				       // sheet.mergeCells( 5 , 1 , 5 , 1 );
				        sheet.addCell(label);  
				        label = new jxl.write.Label(excelCol++, 1, "定时派送日期", wc);
				        sheet.setColumnView(excelCol,30);
				       // sheet.mergeCells( 5 , 1 , 5 , 1 );
				        sheet.addCell(label);  
				        label = new jxl.write.Label(excelCol++, 1, "定时派送时间", wc);
				        sheet.setColumnView(excelCol,30);
				       // sheet.mergeCells( 5 , 1 , 5 , 1 );
				        sheet.addCell(label);
				        label = new jxl.write.Label(excelCol++, 1, "自定义1", wc);
				        sheet.setColumnView(excelCol,30);
				       // sheet.mergeCells( 5 , 1 , 5 , 1 );
				        sheet.addCell(label);
				        label = new jxl.write.Label(excelCol++, 1, "自定义2", wc);
				        sheet.setColumnView(excelCol,30);
				       // sheet.mergeCells( 5 , 1 , 5 , 1 );
				        sheet.addCell(label);  
				        label = new jxl.write.Label(excelCol++, 1, "自定义3", wc);
				        sheet.setColumnView(excelCol,30);
				       // sheet.mergeCells( 5 , 1 , 5 , 1 );
				        sheet.addCell(label);  
	       
	        
	        //jxl.write.Number number = null;
	        jxl.write.DateTime dateTime;
	        jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat("yyyy-MM-dd");//时间格式
	        WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);       
	          for(int i=0;i<orderlist.size();i++){
	          Map orderMap=(Map)orderlist.get(i);
	            excelCol = 0;
	            row = i + 1;      
	            String send_num,send_time,telephone,mobile,consignee,area_attr,goods_name,goods_sets,buy_real_name;
	            if(orderMap.get("send_num")!=null){
	            	send_num=orderMap.get("send_num").toString();
	            }else {
	            	send_num="";
				}
	            if(orderMap.get("send_time")!=null){
	            	send_time=orderMap.get("send_time").toString();
	            }else {
	            	send_time="";
				}
	            if(orderMap.get("telephone")!=null){
	            	telephone=orderMap.get("telephone").toString();
	            }else {
	            	telephone="";
				}
	            if(orderMap.get("mobile")!=null){
	            	mobile=orderMap.get("mobile").toString();
	            }else {
	            	mobile="";
				}
	            if(orderMap.get("consignee")!=null){
	            	consignee=orderMap.get("consignee").toString();
	            }else {
	            	consignee="";
				}
	            if(orderMap.get("area_attr")!=null){
	            	area_attr=orderMap.get("area_attr").toString();
	            	String area_attrs[]=area_attr.split(",");
	            	if(area_attrs.length==3){
	            		area_attr=area_attrs[0]+"省"+area_attrs[1]+"市"+area_attrs[2];
	            	}else if (area_attrs.length==2) {
	            		area_attr=area_attrs[0]+area_attrs[1];
					}
	            	
	            }else {
	            	area_attr="";
				}
	            if(orderMap.get("ogoods_name")!=null){
	            	goods_name=orderMap.get("ogoods_name").toString();
	            }else {
	            	goods_name="";
				}
	            if(orderMap.get("goods_sets")!=null){
	            	goods_sets=orderMap.get("goods_sets").toString();
	            }else {
	            	goods_sets="";
				}
	            if(orderMap.get("buy_real_name")!=null){
	            	buy_real_name=orderMap.get("buy_real_name").toString();
	            }else {
	            	buy_real_name="";
				}
	            System.out.println("goods_name:"+goods_name);
	            
	                 //label = new jxl.write.Label(excelCol++, row+1, (i+1)+"", wc);
	           // sheet.addCell(label);      
	            label = new jxl.write.Label(excelCol++, row+1, orderMap.get("order_id").toString(), wc);
	           sheet.addCell(label);
	                   label = new jxl.write.Label(excelCol++, row+1, consignee, wc);
	            sheet.addCell(label);        
	                 label = new jxl.write.Label(excelCol++, row+1, telephone, wc);
	            sheet.addCell(label);             /*字串格式*/
	                       label = new jxl.write.Label(excelCol++, row+1,  mobile, wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, area_attr+orderMap.get("buy_address").toString(), wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, buy_real_name, wc);
	            
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, "", wc);//商品编码
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, goods_name, wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, orderMap.get("goods_num").toString(), wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, orderMap.get("goods_amount").toString(), wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, orderMap.get("tatal_amount").toString(), wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, "", wc);//附加服务
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, "", wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, "", wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, "", wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, "", wc);
	            
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, "电商速配", wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, "寄付", wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, goods_sets, wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, "", wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, "", wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, "", wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, "人民币", wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, "", wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, "", wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, "", wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, "否", wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, "", wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, "", wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, orderMap.get("order_id").toString(), wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, "", wc);
	            sheet.addCell(label);
	            label = new jxl.write.Label(excelCol++, row+1, "", wc);
	            sheet.addCell(label);
	            
	            }

	    }catch (Exception e) {
	        e.printStackTrace();
	        
	    } finally{
//		      生成excel文件
	        workbook.write();
	        workbook.close();
	        os.close();

	    }
		
	}
    
	
	public void exportTestExcel(List orderlist, HttpServletResponse response) throws Exception {
		WritableWorkbook wwb=null; 
        //获取要读取的EXCEL表格模板
        String rootPath = PropertiesUtil.getRootpath();// 获取项目的根目录
        File is = new File("f:\\ExcelTest\\test.xls");  
        try { 
        	InputStream ins = new FileInputStream("f:\\ExcelTest\\test.xls");
            //获取工作簿对象     
            Workbook wb = Workbook.getWorkbook(is);
            File outFile = new File("f:\\ExcelTest\\test12.xls");  
            // 创建可写入的工作簿对象    
            wwb = Workbook.createWorkbook(outFile,wb); 
        } catch (Exception e) {
  	        e.printStackTrace();
  	        
	  	} finally{
	  	      wwb.close();  
	  	}    
	}
	
	public void exportOrderExcel(List orderlist, HttpServletResponse response) throws Exception {
		orderlist=ToolsFuc.replaceList(orderlist, "");
    	WorkbookSettings settings = new WorkbookSettings ();  
    	settings.setWriteAccess(null);
		WritableWorkbook wwb=null;  
        WritableSheet sheet=null;
        jxl.write.WritableCell wcs =null;
		response.reset();
	    response.setContentType("application/vnd.ms-excel");
	    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	    String nowtime =df.format(new Date());
	    response.setHeader("Content-Disposition", "filename="+nowtime+"Order.xls");//attachment // WritableWorkbook是JexcelApi的一个类。
	  // 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
	    OutputStream os = response.getOutputStream();//取得输出流
        //获取要读取的EXCEL表格模板
        String rootPath = PropertiesUtil.getRootpath();// 获取项目的根目录
        File is = new File(rootPath+"/include/ordertemplate/order_template_20150205.xls");  
        //写入到新的表格里  
        try {  
            //获取工作簿对象     
            Workbook wb = Workbook.getWorkbook(is);  
            // 创建可写入的工作簿对象    
            wwb = Workbook.createWorkbook(os, wb,settings); 
            //根据工作表名获取WritableSheet对象  
            sheet=wwb.getSheet(0);
            sheet.getSettings().setSelected(true);
            Label label=null;
            Cell cell=null;
            jxl.write.DateTime dateTime;
	        jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat("yyyy-MM-dd");//时间格式
	        WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat); 
	        int excelCol = 0;
		    int row = 0;
		    for(int i=0;i<orderlist.size();i++){
		          Map orderMap=(Map)orderlist.get(i);
		            excelCol = 0;
		            row = i + 2;      
		            String send_num,send_time,telephone,mobile,consignee,area_attr,goods_name,goods_no,goods_sets,buy_real_name;
		            if(orderMap.get("send_num")!=null){
		            	send_num=orderMap.get("send_num").toString();
		            }else {
		            	send_num="";
					}
		            if(orderMap.get("send_time")!=null){
		            	send_time=orderMap.get("send_time").toString();
		            }else {
		            	send_time="";
					}
		            if(orderMap.get("telephone")!=null){
		            	telephone=orderMap.get("telephone").toString();
		            }else {
		            	telephone="";
					}
		            if(orderMap.get("mobile")!=null){
		            	mobile=orderMap.get("mobile").toString();
		            }else {
		            	mobile="";
					}
		            if(orderMap.get("consignee")!=null){
		            	consignee=orderMap.get("consignee").toString();
		            }else {
		            	consignee="";
					}
		            if(orderMap.get("area_attr")!=null){
		            	area_attr=orderMap.get("area_attr").toString();
		            	String area_attrs[]=area_attr.split(",");
		            	if(area_attrs.length==3){
		            		area_attr=area_attrs[0]+"省"+area_attrs[1]+"市"+area_attrs[2];
		            	}else if (area_attrs.length==2) {
		            		area_attr=area_attrs[0]+area_attrs[1];
						}
		            	
		            }else {
		            	area_attr="";
					}
		            //修改日期2015-01-07 在goods_order.xml 中左关联goods 取值造成订单列表重复，应该从orderdetail中关联goods，改成编码方式取得
		            if(orderMap.get("order_id")!=null){
		            	String order_id=orderMap.get("order_id").toString();
		            	HashMap orderdetailMap = new HashMap();
		            	orderdetailMap.put("order_id", order_id);
		            	List orderdetailList = this.orderdetailDao.getList(orderdetailMap);
		          		StringBuffer goodsNamesb = new StringBuffer();
	            		StringBuffer goodsNosb = new StringBuffer();
		            	if(orderdetailList != null && orderdetailList.size()>0){
		            		for(int j=0;j<orderdetailList.size();j++){
		            			Map gMap = (Map) orderdetailList.get(j);
		            			goodsNamesb.append(gMap.get("ogoods_name").toString());
		            			goodsNamesb.append("  ");
		            			goodsNosb.append(gMap.get("ogoods_no").toString());
		            			goodsNosb.append("  ");
		            		}
		            	}
		            	goods_name=goodsNamesb.toString();
		            	goods_no=goodsNosb.toString();
		            }else {
		            	goods_name="";
		            	goods_no="";
					}
		           /* if(orderMap.get("goods_no")!=null){
		            	goods_no=orderMap.get("goods_no").toString();
		            }else {
		            	goods_no="";
					}*/
		            if(orderMap.get("goods_sets")!=null){
		            	goods_sets=orderMap.get("goods_sets").toString();
		            }else {
		            	goods_sets="";
					}
		            if(orderMap.get("buy_user_name")!=null){
		            	buy_real_name=orderMap.get("buy_user_name").toString();
		            }else {
		            	buy_real_name="";
					}
		            System.out.println("goods_name:"+goods_name);
		            
		                 //label = new jxl.write.Label(excelCol++, row+1, (i+1)+"", wc);
		           // sheet.addCell(label);      
		            //获得第一个单元格对象
		            label = new jxl.write.Label(excelCol++, row, orderMap.get("order_id").toString());
			        sheet.addCell(label);
		            
		            label = new jxl.write.Label(excelCol++, row, consignee);
			        sheet.addCell(label);  
		            
		            label = new jxl.write.Label(excelCol++, row, telephone);
			        sheet.addCell(label);  
		 	         
		            
		            label = new jxl.write.Label(excelCol++, row, mobile);
			        sheet.addCell(label);  
		             
		             label = new jxl.write.Label(excelCol++, row, area_attr+orderMap.get("buy_address").toString());
				     sheet.addCell(label);  
		            
		            label = new jxl.write.Label(excelCol++, row, buy_real_name);
			        sheet.addCell(label);  
		            
			        label = new jxl.write.Label(excelCol++, row, goods_no);
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, goods_name);
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, orderMap.get("goods_num").toString());
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, orderMap.get("goods_amount").toString());
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, orderMap.get("tatal_amount").toString());
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, "");
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, "");
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, "");
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, "");
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, "");
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, "电商特惠");
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, "月结");
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, goods_sets);
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, "");
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, "");
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, "");
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, "人民币");
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, "");
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, "");
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, "");
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, "否");
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, "");
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, "");
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, orderMap.get("order_id").toString());
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, "");
			        sheet.addCell(label);  
			        
			        label = new jxl.write.Label(excelCol++, row, "");
			        sheet.addCell(label);  
	            }
		        wwb.write();
        } catch (Exception e) {
	  	        e.printStackTrace();
	  	        
	  	} finally{
	  	      wwb.close();  
	  	      os.flush();  
		  	  os.close();  
		  	  os=null;  
	  	      response.flushBuffer();  
	  	}
	}

	public void comboorderout(Map map) {
	}

	public int getOrderCount(Map map) {
		return goodsorderDao.getOrderCount(map);
	}

	public int getRefundCount(Map map) {
		return goodsorderDao.getRefundCount(map);
	}

	public String iCvs(String iname) {
		String ordertb="";
		try {
			        BufferedReader reader = new BufferedReader(new FileReader(iname));//换成你的文件名
		            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
		            String line = null;
		            String order_id,sforder_id,goods_name,num,send_time,send_num="";
		            while((line=reader.readLine())!=null){
		                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
		                order_id=item[33].replace("\"", "").trim();//订单号
		                sforder_id=item[2].replace("\"", "").trim();//顺风订单号
		                send_num=item[3].replace("\"", "").trim();//顺风运单号
		                goods_name=item[4].replace("\"", "").trim();//商品名称
		                num=item[7].replace("\"", "").trim();//数量
		                send_time=item[24].replace("\"", "").trim();//时间
		                if(order_id!=null&&!"".equals(order_id)&&send_num!=null&&!"".equals(send_num)){
		                	Goodsorder gorder = new Goodsorder();
		        			gorder = goodsorderDao.get(order_id);
		        			if(gorder!=null&&gorder.getOrder_state().equals("2")){
		        			// 物流公司 获取顺风快递 shunfeng 
		        			Sendmode sendmode=new Sendmode();
		        			sendmode=sendmodeDao.getByEnname("shunfeng");
		        			if(sendmode!=null&&sendmode.getSmode_id()!=null){
		        				gorder.setSend_mode(sendmode.getSmode_id());
		        			}
		        			// 快递单号
		        			gorder.setSend_num(send_num);
		        			//发货时间
		        			if(send_time==null||"".equals(send_time)){
		        				//如果发货时间为空，就系统自动获取当前时间
		        				send_time=DateUtil.getCurrentTime();
		        			}
		        			gorder.setSend_time(send_time);
		        			goodsorderDao.update(gorder);
		        			// 修改订单状态
		        			updateOrderState(order_id, "3");
		        			MessageAltFuc mesalt = new MessageAltFuc();
		        			mesalt.messageAutoSend("2", gorder.getBuy_cust_id(), gorder);
		        			// 插入订单异动信息表
		        			insertOrderTrans(order_id, "顺丰快递自动发货，运单号为"+send_num, "3");
		                	ordertb+="<tr>"+
		                	"<td align='center'>"+sforder_id+"</td>"+
		                	"<td align='center'>"+order_id+"</td>"+
		                	"<td align='center'>"+goods_name+"</td>"+
		                	"<td align='center'>"+num+"</td>"+
		                	"<td align='center'>"+send_num+"</td>"+
		                	"<td align='center'><font color='grenn'>成功</font></td>"+
		                	"<td align='center'></td>"+
		                	"</tr>";
		        			}else{
		        			   String reason="";
		        			   if(gorder==null){
		        					reason="订单不存在";
		        			   }else if(gorder.getOrder_state().equals("3")){
		        				   reason="商品已发货";
		        			   }else{
		        				   reason="其他";
		        			   }
			                	ordertb+="<tr>"+
			                	"<td align='center'>"+sforder_id+"</td>"+
			                	"<td align='center'>"+order_id+"</td>"+
			                	"<td align='center'>"+goods_name+"</td>"+
			                	"<td align='center'>"+num+"</td>"+
			                	"<td align='center'>"+send_num+"</td>"+
			                	"<td align='center'><font color='red'>失败</font></td>"+
			                	"<td align='center'>"+reason+"</td>"+
			                	"</tr>";
		        			}
		                	
		                }else {
		                	ordertb+="<tr>"+
		                	"<td align='center'>"+sforder_id+"</td>"+
		                	"<td align='center'>"+order_id+"</td>"+
		                	"<td align='center'>"+goods_name+"</td>"+
		                	"<td align='center'>"+num+"</td>"+
		                	"<td align='center'>"+send_num+"</td>"+
		                	"<td align='center'><font color='red'>失败</font></td>"+
		                	"<td align='center'>订单不存在></td>"+
		                	"</tr>";
						}
		            }
		            reader.close(); 
		        } catch (Exception e) {
		            e.printStackTrace();
		            
		        }
		        return ordertb;
				
			}


/**
 * @author : HXK 方法描述：会员修改订单状态
 * @return
 * @throws Exception
 */
public void updateOrderState(String s_order_id, String s_order_state)
		throws Exception {
	// 插入订单异动信息表
	String reason = CommparaFuc.getReason(s_order_state, null);
	updateOrderState(s_order_id, s_order_state, reason);
}

/**
 * @author : HXK 方法描述：会员修改订单状态
 * @return
 * @throws Exception
 */
public void updateOrderState(String s_order_id, String s_order_state,
		String order_reason) throws Exception {
	// 订单状态表示：0：订单取消 1：未付款 2：已付款 3：已发货 4：退款中 5：退款成功 6：退款失败：7：交易成功 8：已评价
	Goodsorder goods_order = new Goodsorder();
	goods_order.setOrder_id(s_order_id);
	goods_order.setOrder_state(s_order_state);
	Map stateMap = new HashMap();
	stateMap.put("order_state", s_order_state);
	stateMap.put("order_id", s_order_id);
	stateMap.put("order_state", s_order_state);
	if (s_order_state.equals("2")) {
		stateMap.put("pay_time", "pay_time");
	}
	if (s_order_state.equals("3")) {
		stateMap.put("send_time", "send_time");
	}
	if (s_order_state.equals("7")) {
		stateMap.put("sure_time", "sure_time");
	}
	goodsorderDao.update(stateMap);
	String reason = CommparaFuc.getReason(s_order_state, order_reason);
	// 插入订单异动信息表
	insertOrderTrans(s_order_id, reason, s_order_state);
}
	
	/**
	 * 方法描述：修改订单的时候，插入订单异动表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void insertOrderTrans(String order_id, String reason,
			String order_state) {
		Ordertrans ordertrans = new Ordertrans();
		ordertrans.setCust_id("0");
		ordertrans.setOrder_state(order_state);
		ordertrans.setUser_id("105");
		ordertrans.setReason(reason);
		ordertrans.setOrder_id(order_id);
		OrdertransDao.insert(ordertrans);
	}

	/**
	 * @author : QJY
	 * @date : Apr 19, 2014 11:05:33 AM
	 * @Method Description :批量发货
	 */
    public boolean updateOrderWeight(String field_id,String field_sort,String ids,String vals)throws Exception{
		if(ids==null || vals==null){
			return false;
		}
		String[] order_id= ids.split(",");
		String[] order_weight= vals.split(",");
		List goodsorderList=new ArrayList();
		if(order_id.length>0){
			for(int i=0;i<order_id.length;i++){
				Map weightMap = new HashMap();
				if(order_id[i].trim().equals("")){
					continue;
				}
				weightMap.put(field_id,order_id[i].trim());
				//如果文本框为空则返回field_id
				if(ValidateUtil.isRequired(order_weight[i])){
					weightMap.put(field_sort,"0");
				}else{
					weightMap.put(field_sort,order_weight[i]);
				}
				goodsorderList.add(weightMap);
			}
		}
		goodsorderDao.updateOrderWeight(goodsorderList);
    	return true;
    }
	
  //通过交易流水号
	public Goodsorder getByTrxID(String paytrxID){
		return goodsorderDao.getByTrxID(paytrxID);
	}
	
	 /**
	 * @Method Description :物理删除订单信息 包括有关订单一并删除
	 * @author: HXK
	 * @date : Oct 23, 2015 4:19:17 PM
	 * @param Goodsorder 订单对象
	 * @return return_type
	 */
	public boolean deleteReOrder(Goodsorder goodsorder) {
		boolean flag = false; 
		if(goodsorder != null && !ValidateUtil.isRequired(goodsorder.getOrder_id())) {
			flag=DeleteOrderInfo(goodsorder.getOrder_id());
		}
		return flag;
	}
	
	 /**
	 * @Method Description :逻辑删除订单信息
	 * @author: HXK
	 * @date : Oct 23, 2015 4:19:17 PM
	 * @param map 对象
	 * @return return_type
	 */
	public void recycleorderout(Map map) {
		//获取回收订单集合
		List goodsorderlist = goodsorderDao.getList(map);
        //判断订单集合不为空
		if(goodsorderlist!=null&&goodsorderlist.size()>0){
		  for (int i = 0; i < goodsorderlist.size(); i++) {
			HashMap maps = (HashMap) goodsorderlist.get(i);
			String order_id = "";// 获取订单号
			order_id = maps.get("order_id").toString();
			Goodsorder goodsorder = this.goodsorderDao.get(order_id);
			if(goodsorder != null && !ValidateUtil.isRequired(goodsorder.getOrder_id())&&"0".equals(goodsorder.getOrder_state())) {
				HashMap gmaps =new HashMap();
				gmaps.put("order_state", "0");
				gmaps.put("is_del", "0");//0为删除 1不是
				gmaps.put("order_id",order_id);
				goodsorderDao.updateState(gmaps);
			}
		  }
		}
	}
	 /**
	 * @Method Description :物理删除订单信息 包括有关订单一并删除
	 * @author: HXK
	 * @date : Oct 23, 2015 4:19:17 PM
	 * @param Map 对象
	 * @return return_type
	 */
	public void recycleByOrderList(Map map) {
		//获取回收订单集合
		List goodsorderlist = goodsorderDao.getList(map);
		String goodsorderStr=""; //获取订单号
        //判断订单集合不为空
		if(goodsorderlist!=null&&goodsorderlist.size()>0){
		  for (int i = 0; i < goodsorderlist.size(); i++) {
			HashMap maps = (HashMap) goodsorderlist.get(i);
			if(maps.get("order_id")!=null){
				goodsorderStr =goodsorderStr+maps.get("order_id").toString()+",";
			}
		 }
		  if(!ValidateUtil.isRequired(goodsorderStr)){
			  goodsorderStr=goodsorderStr.substring(0,goodsorderStr.length()-1);
		  }
		  DeleteOrderInfo(goodsorderStr);
		}
	}
	 /**
	 * @Method Description :物理删除订单信息 包括有关订单一并删除
	 * @author: HXK
	 * @date : Oct 23, 2015 4:19:17 PM
	 * @param goodsorderlist 列表
	 * @return return_type
	 */
	public void recycleByOrderMap(List goodsorderlist) {
		//获取回收订单集合
		String goodsorderStr=""; //获取订单号
        //判断订单集合不为空
		if(goodsorderlist!=null&&goodsorderlist.size()>0){
		  for (int i = 0; i < goodsorderlist.size(); i++) {
			HashMap maps = (HashMap) goodsorderlist.get(i);
			String order_id = "";// 获取订单号
			if(maps.get("order_id")!=null){
				goodsorderStr = maps.get("order_id").toString()+",";
			}
		 }
		  if(!ValidateUtil.isRequired(goodsorderStr)){
			  goodsorderStr=goodsorderStr.substring(0,goodsorderStr.length()-1);
		  }
		  DeleteOrderInfo(goodsorderStr);
		}
	}
	
	/**
	 * @Method Description :定时器检查快递单物流状态
	 * @author: HXK
	 * @date : Jul 21, 2015 5:25:41 PM
	 * @param 
	 * @return return_type
	 */
     public void updateSignExpressState(){
    	 
     }
     /**
	 * @Method Description :物理删除订单信息 包括有关订单一并删除
	 * @author: HXK
	 * @date : Oct 23, 2015 4:19:17 PM
	 * @param 
	 * @return return_type
	 */
     public boolean DeleteOrderInfo(String order_str){
    	 boolean fage=true;
    	 if(!ValidateUtil.isRequired(order_str)){
    		 String orders[]=order_str.split(",");
    		 if(orders.length>0){
    			 for(int i=0;i<=orders.length-1;i++){
    				 String order_id=orders[i];
    				 Goodsorder goodsorder=new Goodsorder();
    				 goodsorder=goodsorderDao.get(order_id);
    				 //判断传过来的订单ID是否存在
    				 if(goodsorder!=null&&!ValidateUtil.isRequired(goodsorder.getOrder_id())){
    					 //删除cancelorder
    					 cancelorderDao.deleteByOrderIds(order_id);
        				 //删除cancelordertrans
    					 cancleordertransDao.deleteByOrderIds(order_id);
        				 //删除consumer
    					 comsumerDao.deleteByOrderIds(order_id);
        				 //删除exchange
    					 exchangeDao.deleteByOrderIds(order_id);
        				 //删除goodseval
    					 goodsevalDao.deleteByOrderIds(order_id);
        				 //删除kjtrecall
    					 kjtrecallDao.deleteByOrderIds(order_id);
        				 //删除orderinvoice
    					 orderinvoiceDao.deleteByOrderIds(order_id);
        				 //删除ordertrans
    					 OrdertransDao.deleteByOrderIds(order_id);
        				 //删除redsumer
    					 redsumerDao.deleteByOrderIds(order_id);
        				 //删除refundapp
    					 refundappDao.deleteByOrderIds(order_id);
        				 //删除orderdetail
    					 orderdetailDao.deleteByOrderIds(order_id);
        				 //删除goodsoder
    					 goodsorderDao.deleteByOrderIds(order_id);
    				 }
    				
    			 }
    			 
    		 }
    	 }
    	 return fage;
     }
     /**
 	 * @author QJY
 	 * @function 商量销售量更新
 	 * @date 2015-08-24
 	 * @throws Exception
 	 */
 	public void updateGoodsSales(String order_id)throws Exception{
 		Map detailMap = new HashMap();
 		detailMap.put("order_id_s", order_id);
 		List orderdetailList = this.orderdetailDao.getList(detailMap);
 		if(orderdetailList !=null && orderdetailList.size()>0){
 			Map salesMap = new HashMap();
 			Map volumeMap =new HashMap();
 			String goods_id = "",sale_num="0";
 			Integer sales_volume = 0;
 			Goods goods = new Goods();
 			for(int i=0;i<orderdetailList.size();i++){
 				salesMap = (Map) orderdetailList.get(i);
 				if(salesMap!=null && salesMap.get("goods_id")!=null){
 					goods_id = salesMap.get("goods_id").toString();
 					goods = this.goodsDao.get(goods_id);
 					if(goods.getSale_num()!=null&&!"".equals(goods.getSale_num())){
 						sale_num = goods.getSale_num();
 					}
 				}
 				if(salesMap!=null && salesMap.get("order_num")!=null){
 					sales_volume = Integer.valueOf(sale_num)+Integer.valueOf(salesMap.get("order_num").toString());
 				}else {
 					sales_volume =0;
 				}
 				volumeMap.put("goods_id", goods_id);
 				volumeMap.put("sales_volume", sales_volume+"");
 				this.goodsDao.updateSalesVolume(volumeMap);//订单中每个商品的销售数量的更新
 			}
 		}
 	}
	 /**
	  * 
	  */
     public List getAreaOrderList(Map map)throws Exception{
    	return this.goodsorderDao.getAreaOrderList(map);
    	 
     }
     
     public int getAreaCount(Map map)throws Exception{
    	 return this.goodsorderDao.getAreaCount(map);
     }
     
     /**
  	 * 备货清单
  	 * @param goodsOrderList 
  	 * @throws IOException 
  	 * @throws WriteException 
  	 */
  	public boolean prepareGoodsExport(List exList, HttpServletResponse response) throws Exception {
  		boolean flag = false;
  		if(exList != null && exList.size()> 0) {
	  		response.reset();
	  	    response.setContentType("application/vnd.ms-excel");
	  	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	  	    String nowtime = df.format(new Date());
	  	    
	  	    String fileName = "备货清单"+nowtime+".xls";  
	  	    try {  
	  	        fileName = new String(fileName.getBytes(), "iso8859-1");//解决中文 文件名问题  
	  	    } catch (UnsupportedEncodingException e1) {  
	  	        e1.printStackTrace();  
	  	    }
	  	    
	  	    response.setHeader("Content-Disposition", "filename="+fileName);//attachment // WritableWorkbook是JexcelApi的一个类。
	  	    // 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
	  	    OutputStream os = response.getOutputStream();//取得输出流
	  	    WritableWorkbook workbook = Workbook.createWorkbook(os);
	  	    WritableSheet sheet = workbook.createSheet("商品信息", 0); // 组织excel文件的内容
	  	    jxl.write.Label label = null;
	  	    int excelCol = 0;
	  	    int row = 0;
	  	    try {
	  	    	WritableCellFormat wc = new WritableCellFormat();
	  	    	wc.setVerticalAlignment(VerticalAlignment.CENTRE); 
	  	        wc.setAlignment(Alignment.CENTRE); 
	  	     	wc.setBorder(Border.ALL, BorderLineStyle.THIN);
	  	        jxl.write.DateTime dateTime;
	  	        jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat("yyyy-MM-dd HH:mm:ss");//时间格式
	  	        WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);  
	  	        
	  	        WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.WHITE);
	  	        WritableCellFormat wc2 = new WritableCellFormat();
	  	    	wc2.setVerticalAlignment(VerticalAlignment.CENTRE); 
	  	        wc2.setAlignment(Alignment.CENTRE); 
	  	        wc2.setBackground(jxl.format.Colour.OCEAN_BLUE);
	  	        wc2.setFont(font2);
	  	        wc2.setBorder(Border.ALL, BorderLineStyle.THIN);
	  	        
	  	        String[] param_key = new String[]{"goods_no","kjt_id","barcode","goods_name","goods_name_en","goods_price","order_num","goods_num"};
	  	        String[] param_value = new String[]{"商品编号","跨境通ID","商品条码","商品名称（中文）","商品名称（英文）","商品原价","订单数","总订购数量"};
	  	        int[] col_width = new int[]{15,20,20,60,60,15,10,20};
	  	        //表格Title
	  	        for(int j=0;j<param_key.length;j++){
	  	        	label = new jxl.write.Label(excelCol++, row, param_value[j], wc2);
	  	  	        sheet.setColumnView(j,col_width[j]);
	  	  	        sheet.setRowView(row, 400);
	  	  	        sheet.addCell(label); 
	            }
	  	        //表格内容
	  	        for(int i = 0; i < exList.size(); i ++){
	  	            Map orderMap = (Map)exList.get(i);
	  	            excelCol = 0;//列数
	  	            row = i + 1;//行数
	  	            sheet.setRowView(row, 400);
	  	            for(int j=0;j<param_key.length;j++){
	  	            	String para_str = "";
	  	            	Object para_obj = orderMap.get(param_key[j]);
	  	            	if(para_obj != null){
	  	            		para_str = para_obj.toString();
	  	  	            }else {
	  	  	            	para_str = "";
	  	  				}
	  	            	label = new jxl.write.Label(excelCol++, row, para_str, wc);
	  	  	            sheet.addCell(label);  
	  	            }
	  	        }
	  	        flag = true; 
	  		    workbook.write();//生成excel文件
	  	    }catch (Exception e) {
	  	    	flag=false;
	  	    }finally{
		        workbook.close();  
		  	    os.flush();  
			  	os.close();  
			  	os=null;  
		  	    response.flushBuffer(); 
		    }
	    }
		return flag;
	}
  	/**
  	 * 发货清单
  	 * @param goodsOrderList 
  	 * @throws IOException 
  	 * @throws WriteException 
  	 */
  	public boolean deliverGoodsExport(List exList, Map orderstate_map, HttpServletResponse response) throws Exception {
  	  		boolean flag = false;
  	  		if(exList != null && exList.size()> 0) {
  	  			response.reset();
		  	    response.setContentType("application/vnd.ms-excel");
		  	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		  	    String nowtime = df.format(new Date());
  	  			
	  	  		String fileName = "发货清单"+nowtime+".xls";  
		  	    try {  
		  	        fileName = new String(fileName.getBytes(), "iso8859-1");//解决中文 文件名问题  
		  	    } catch (UnsupportedEncodingException e1) {  
		  	        e1.printStackTrace();  
		  	    }
  		  		
  		  	    response.setHeader("Content-Disposition", "filename="+fileName);//attachment // WritableWorkbook是JexcelApi的一个类。
  		  	    // 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
  		  	    OutputStream os = response.getOutputStream();//取得输出流
  		  	    WritableWorkbook workbook = Workbook.createWorkbook(os);
  		  	    WritableSheet sheet = workbook.createSheet("订单信息", 0); // 组织excel文件的内容
  		  	    jxl.write.Label label = null;
  		  	    try {
  		  	    	WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
  		  	    	WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.WHITE);
  		  	    	WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.DARK_RED);
  		  	    	 
  		  	    	/**Excel准备工作*/
  		  	    	WritableCellFormat wc1 = new WritableCellFormat();
  		  	    	wc1.setVerticalAlignment(VerticalAlignment.CENTRE); 
  		  	        wc1.setAlignment(Alignment.CENTRE); 
  		  	        wc1.setFont(font1);
  		  	        wc1.setBorder(Border.ALL, BorderLineStyle.THIN);
  		  	        
  		  	        WritableCellFormat wc2 = new WritableCellFormat();
		  	    	wc2.setVerticalAlignment(VerticalAlignment.CENTRE); 
		  	        wc2.setAlignment(Alignment.CENTRE); 
		  	        wc2.setBackground(jxl.format.Colour.OCEAN_BLUE);
		  	        wc2.setFont(font2);
		  	        wc2.setBorder(Border.ALL, BorderLineStyle.THIN);
		  	        
		  	        WritableCellFormat wc3 = new WritableCellFormat();
		  	    	wc3.setVerticalAlignment(VerticalAlignment.CENTRE); 
		  	        wc3.setAlignment(Alignment.CENTRE); 
		  	        wc3.setBackground(jxl.format.Colour.SKY_BLUE);
		  	        wc3.setFont(font3);
		  	        wc3.setBorder(Border.ALL, BorderLineStyle.THIN);
		  	      
  		  	        jxl.write.DateTime dateTime;
  		  	        jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat("yyyy-MM-dd HH:mm:ss");//时间格式
  		  	        WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);      
  		  	        //标题键值对
  		  	        String[] param_order_key = new String[]{"order_id","order_state","order_time","pay_time","goods_amount","ship_free","total_amount","buy_address","consignee","goods_list"};
  		  	        String[] param_order_value = new String[]{"订单编号","订单状态","下单时间","付款时间","商品总金额","配送费用","订单总金额","收货地址","收货人","商品列表"};
  		  	        String[] param_goods_key = new String[]{"goods_no","goods_name","goods_name_en","goods_attr","goods_num"};
  		  	        String[] param_goods_value = new String[]{"商品编号","商品名称（中文）","商品名称（英文）","行邮税号","商品数量"};
  		  	        //单元格宽度
  		  	        int[] col_width = new int[]{30,15,20,20,15,15,15,50,10,0};
  		  	        int[] col_goods_width = new int[]{20,60,50,30,10};
  		  	        
  		  	        /**表格标题*/
  		  	        for(int j=0;j<param_order_key.length;j++){
  		  	        	if(j==param_order_key.length-1){
  		  	        		sheet.mergeCells(j, 0, j+param_goods_key.length-1, 0);//合并单元格：商品信息
  		  	        	}else{
  		  	        		sheet.mergeCells(j, 0, j, 1);//合并单元格：订单信息
  		  	        	}
  		  	        	//为标题赋值（订单）
  		  	        	label = new jxl.write.Label(j, 0, param_order_value[j], wc2);
  		  	  	        sheet.setColumnView(j,col_width[j]);
  		  	  	        sheet.addCell(label); 
  		            }
  		  	        //为标题赋值（商品二级标题）
  		  	        for(int i=0;i<param_goods_key.length;i++){
  		  	        	label = new jxl.write.Label(i+param_order_key.length-1, 1, param_goods_value[i], wc3);
		  	  	        sheet.setColumnView(i+param_order_key.length-1,col_goods_width[i]);
		  	  	        sheet.addCell(label); 
  		  	        }
  		  	        sheet.setRowView(0, 400);
  		  	        sheet.setRowView(1, 400);
  		  	        /**表格内容*/
  		  	        int row = 2;//起始行数
  		  	        int goods_row = 2;//累计行数，内容开始的时候空两行
  		  	        /**开始循环*/
  		  	        //订单循环
  		  	        for(int i = 0; i < exList.size(); i ++){
  		  	        	int size = 0;//一个订单的商品数
  		  	            Map orderMap = (Map)exList.get(i);
  		  	            //订单列循环
  		  	            for(int j=0;j<param_order_key.length;j++){
  		  	            	String para_str = "";
  		  	            	Object para_obj = orderMap.get(param_order_key[j]);
  		  	            	//商品列表
	  	            		List goods_list = (List) orderMap.get("goods_list");
	  	            		size = goods_list.size();
  		  	            	if(j==param_order_key.length-1){
  		  	            		//商品循环
  		  	            		for (int k = 0; k < size; k++) {
  		  	            			Map goods_map = (Map) goods_list.get(k);
  		  	            			sheet.setRowView(goods_row+k, 400);
  		  	            			//商品列循环
  		  	            			for (int h = 0; h < param_goods_key.length; h++) {
  		  	            				Object goods_obj = goods_map.get(param_goods_key[h]);
	  		  	            			if(goods_obj != null){
	  		  		  	            		para_str = goods_obj.toString();
	  		  		  	  	            }else {
	  		  		  	  	            	para_str = "";
	  		  		  	  				}
										label = new jxl.write.Label(j+h, goods_row+k, para_str, wc1);
			  		  	  	            sheet.addCell(label);  
									}
								}
  		  	            	}else{
  		  	            		if(goods_row>0){
  		  	            			sheet.mergeCells(j, goods_row, j, goods_row+size-1);//合并单元格:订单信息
  		  	            		}
	  		  	            	if(para_obj != null){
	  		  	            		para_str = para_obj.toString();
	  		  	  	            }else {
	  		  	  	            	para_str = "";
	  		  	  				}
	  		  	            	//订单状态替换
	  		  	            	if(param_order_key[j].equals("order_state")){
	  		  	            		para_str = (String) orderstate_map.get(para_str);
	  		  	            	}
	  		  	            	label = new jxl.write.Label(j, goods_row, para_str, wc1);
	  		  	  	            sheet.addCell(label);  
  		  	            	}
  		  	            }
  		  	            goods_row += size;
  		  	        }
  		  	        flag = true; 
  		  		    workbook.write();//生成excel文件
  		  	    }catch (Exception e) {
  		  	    	flag=false;
  		  	    }finally{
  			        workbook.close();  
  			  	    os.flush();  
  				  	os.close();  
  				  	os=null;  
  			  	    response.flushBuffer(); 
  			    }
  		    }
  			return flag;
  		}
  	/**
	 * 自动确认收货
	 */
	public List getConfirmReceiptOrderList(Map map){
		return this.goodsorderDao.getConfirmReceiptOrderList(map); 
	}
	
	public int getConfirmReceiptOrderCount(Map map)throws Exception{
		return this.goodsorderDao.getConfirmReceiptOrderCount(map);
	}
}