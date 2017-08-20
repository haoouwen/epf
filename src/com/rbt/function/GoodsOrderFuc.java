package com.rbt.function;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.compiler.ast.NewExpr;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.rbt.common.util.DateUtil;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Cancelorder;
import com.rbt.model.Cancleordertrans;
import com.rbt.model.Directorderdetail;
import com.rbt.model.Fundhistory;
import com.rbt.model.Goodsorder;
import com.rbt.model.Interhistory;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.model.Memberuser;
import com.rbt.model.Ordertrans;
import com.rbt.model.Pointsgoods;
import com.rbt.model.Refundapp;
import com.rbt.model.Tryapply;
import com.rbt.model.Trygoods;
import com.rbt.service.ICancelorderService;
import com.rbt.service.ICancleordertransService;
import com.rbt.service.IDirectorderdetailService;
import com.rbt.service.IDirectsellService;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberinterService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.IOrdertransService;
import com.rbt.service.IPointsgoodsService;
import com.rbt.service.IRefundappService;
import com.rbt.service.ISysfundService;
import com.rbt.service.ITryapplyService;
import com.rbt.service.ITrygoodsService;

/**
 * @function 功能 用于加载系统基本参数
 * @author 创建人 LJQ
 * @date 创建日期 Jul 29, 2014 1:10:56 PM
 */
public class GoodsOrderFuc extends CreateSpringContext {
	/**
	 * @author : HXK
	 * @param :
	 * @throws Exception
	 * @throws Exception
	 * @date Mar 11, 2014 2:28:08 PM
	 * @Method Description :在线支付/充值
	 */
	public static void useOnlinePay(String order_id_str, String actual_amount,String pay_trxid,
			String pay_id, String cust_str) throws Exception {
		IDirectorderdetailService directorderdetailService = (IDirectorderdetailService) getContext()
				.getBean("directorderdetailService");
		IGoodsorderService goodsorderService = (IGoodsorderService) getContext()
				.getBean("goodsorderService");
		IOrderdetailService orderdetailService = (IOrderdetailService) getContext()
				.getBean("orderdetailService");
		ITrygoodsService trygoodsService = (ITrygoodsService) getContext()
				.getBean("trygoodsService");
		ITryapplyService tryapplyService = (ITryapplyService) getContext()
				.getBean("tryapplyService");
		IDirectsellService directsellService = (IDirectsellService) getContext()
				.getBean("directsellService");
		HttpServletResponse response = ServletActionContext.getResponse();
		String[] order_id = order_id_str.split(",");
		// cust_str 的格式为："cust_id,user_id,pay_type"
		String custs[] = cust_str.split("\\-");
		if (custs != null && cust_str.length() > 0) {
			String buy_cust_id = custs[0].toString();
			String buy_user_id = custs[1].toString();
			String o_type = custs[2].toString();
			Double use_num_pay = 0.0;
			String l_o = "";
			if (buy_cust_id != null && !buy_cust_id.equals("")) {
				for (int i = 0; i < order_id.length; i++) {
					l_o = order_id[i];
					/** ***预售订单的付款处理开始，分为两部分，定金和尾款** */
					if ("1".equals(o_type)) {
						order_id[i] = order_id[i].substring(0, order_id[i]
								.length() - 1);
						Directorderdetail directorderdetail = directorderdetailService
								.getByOrderId(order_id[i]);
						String direct_id = directorderdetail.getDirect_id();
						String order_num_str = directorderdetail.getOrder_num();
						if (directsellService.get(direct_id) != null) {
							// 获取购买人数
							int ordernum = 0, max_buy = 0;
							HashMap mMap = new HashMap();
							mMap.put("trade_id", direct_id);
							List dList = directsellService.getList(mMap);
							Map dMap=new HashMap();
							if(dList!=null&&dList.size()>0){
							   dMap = (Map) dList.get(0);
							}
							if (dMap.get("buynum") == null) {
								ordernum = 0;
							} else {
								ordernum = Integer.parseInt(dMap.get("buynum")
										.toString());
							}
							max_buy = Integer.parseInt(directsellService.get(
									direct_id).getMax_buy());
							if (max_buy - ordernum
									- Integer.parseInt(order_num_str) <= 0) {
								response
										.sendRedirect("/mall/directOrder!payFinal.action?order_id_str="
												+ order_id[i]);
							}

						}

						String direPayState = directorderdetail.getPay_state();
						if (direPayState.equals("0")) {

							use_num_pay = Integer.parseInt(directorderdetail
									.getOrder_num())
									* directorderdetail.getEarnest();
						} else {
							if (l_o.endsWith("a")) {
								response
										.sendRedirect("/bmall_Goodsorder_buyorderlist.action");
							}
							use_num_pay = Integer.parseInt(directorderdetail
									.getOrder_num())
									* (directorderdetail.getGoods_price() - directorderdetail
											.getEarnest());
						}
						// 预售订单付款处理 a 和 b 段
						directorderdetailService
								.onlinePay(order_id, buy_cust_id, buy_user_id,
										use_num_pay, response);
					}
					/** ***预售订单的付款处理结束，分为两部分，定金和尾款** */

					// 更新订单状态，交易成功操作
					Goodsorder goodsorder = new Goodsorder();
					goodsorder = goodsorderService.get(order_id[i]);
					if (goodsorder != null
							&& goodsorder.getOrder_state().equals("1")) {
						// 若是虚拟商品，生成消费码
						if (goodsorder.getOrder_type() != null
								&& goodsorder.getOrder_type().equals("0")) {
							// 生成消费码
							String mark_id = RandomStrUtil.getRand("12");
							goodsorder.setMark_id(mark_id);
						}
						// 若是试用订单,减掉剩余库存
						if (goodsorder.getOrder_type() != null
								&& goodsorder.getOrder_type().equals("7")) {
							Map detailMap = new HashMap();
							detailMap.put("order_id", goodsorder.getOrder_id());
							List list = orderdetailService.getList(detailMap);
							if (list != null && list.size() > 0) {
								Map tryMap = (HashMap) list.get(0);
								String goods_id = tryMap.get("goods_id")
										.toString();
								if (!ValidateUtil.isRequired(goods_id)) {
									Map trygoodsMap = new HashMap();
									// 商品ID
									trygoodsMap.put("goods_id", goods_id);
									List trygoodsList = trygoodsService
											.getList(trygoodsMap);
									if (trygoodsList != null
											&& trygoodsList.size() > 0) {
										// 获取试用商品
										Map trymap = (HashMap) trygoodsList
												.get(0);
										Trygoods trygoods = trygoodsService
												.get(trymap.get("try_id")
														.toString());
										// 试用商品剩余量-1
										String surplus = (Integer
												.valueOf(trygoods.getSurplus()) - 1)
												+ "";
										trygoods.setSurplus(surplus);
										trygoodsService.update(trygoods);
										// 获取试用申请对象
										Map tryapplyMap = new HashMap();
										tryapplyMap.put("order_id", goodsorder
												.getOrder_id());
										List tryapplyList = tryapplyService
												.getList(tryapplyMap);
										if (tryapplyList != null
												&& tryapplyList.size() > 0) {
											// 修改试用状态
											Map tryapMap = (HashMap) tryapplyList
													.get(0);
											Tryapply tryapply = tryapplyService
													.get(tryapMap.get(
															"tryapply_id")
															.toString());
											tryapply.setStatus("1");
											tryapplyService.update(tryapply);
										}
									}
								}
							}
						}
						// 获取当前时间
						String cur_date_str = "";
						SimpleDateFormat format = new java.text.SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						Calendar cal = Calendar.getInstance();
						cur_date_str = format.format(cal.getTime());
						goodsorder.setOrder_state("2");// 更改订单状态为已付款
						goodsorder.setPay_time(cur_date_str);
						goodsorder.setPay_id(pay_id);
						goodsorder.setPay_trxid(pay_trxid);
						goodsorderService.update(goodsorder);
						//减少库存
						orderdetailService.updateStockByOnlinepayment(goodsorder.getOrder_id());
						//更新商品销售量, 销量记录 在付款之后就加销量
						goodsorderService.updateGoodsSales(goodsorder.getOrder_id());
						// 在线处理资金及流水
						if (!"1".equals(o_type)) {
							fundOnlineManage(order_id[i],actual_amount, buy_cust_id,buy_user_id);
						}
						// 若是虚拟商品
						if (goodsorder.getOrder_type() != null
								&& goodsorder.getOrder_type().equals("0")) {
							// 发送信息提醒
							 mestipByBuyer("6",order_id[i]);
						} else {
							// 发送信息提醒
							 mestipByBuyer("4",order_id[i]);
						}
						Memberuser memberuser=new Memberuser();
						memberuser=MemberuserFuc.getMemberuserById(buy_cust_id);
						insertOrdertrans(order_id[i], buy_cust_id, buy_user_id,memberuser.getUser_name());
					}
				}
			}
		}

	}

	/**
	 * @author : QJY		
	 * @param :
	 * @throws Exception
	 * @throws Exception
	 * @date Mar 11, 2015 20:28:08 PM
	 * @Method Description :手机网站在线支付
	 */
	public static void alipaywap(String order_id_str,String pay_trxid,String pay_id) throws Exception {
		IDirectorderdetailService directorderdetailService = (IDirectorderdetailService) getContext().getBean("directorderdetailService");
		IGoodsorderService goodsorderService = (IGoodsorderService) getContext().getBean("goodsorderService");
		IOrderdetailService orderdetailService = (IOrderdetailService) getContext().getBean("orderdetailService");
		ITrygoodsService trygoodsService = (ITrygoodsService) getContext().getBean("trygoodsService");
		ITryapplyService tryapplyService = (ITryapplyService) getContext().getBean("tryapplyService");
		IDirectsellService directsellService = (IDirectsellService) getContext().getBean("directsellService");
		HttpServletResponse response = ServletActionContext.getResponse();
		String[] order_id = order_id_str.split(",");
		for (int i = 0; i < order_id.length; i++) {
			String buy_cust_id ="",buy_user_id = "",o_type ="";
			Double use_num_pay = 0.0;
			String l_o = "";
			// 更新订单状态，交易成功操作
			l_o = order_id[i];
			Goodsorder goodsorder = new Goodsorder();
			goodsorder = goodsorderService.get(order_id[i]);
			if(goodsorder!=null){
				o_type=goodsorder.getOrder_type();
				buy_cust_id=goodsorder.getBuy_cust_id();
				Memberuser memberuser=new Memberuser();
				memberuser=MemberuserFuc.getMemberuserById(buy_cust_id);
				buy_user_id=memberuser.getUser_id();
				/** ***预售订单的付款处理开始，分为两部分，定金和尾款** */
				if ("6".equals(o_type)) {
					order_id[i] = order_id[i].substring(0, order_id[i].length() - 1);
					Directorderdetail directorderdetail = directorderdetailService.getByOrderId(order_id[i]);
					String direct_id = directorderdetail.getDirect_id();
					String order_num_str = directorderdetail.getOrder_num();
					if (directsellService.get(direct_id) != null) {
						// 获取购买人数
						int ordernum = 0, max_buy = 0;
						HashMap mMap = new HashMap();
						mMap.put("trade_id", direct_id);
						List dList = directsellService.getList(mMap);
						Map dMap =new HashMap(); 
						if(dList!=null&&dList.size()>0){
							 dMap = (Map) dList.get(0);
						}
						if (dMap.get("buynum") == null) {
							ordernum = 0;
						} else {
							ordernum = Integer.parseInt(dMap.get("buynum").toString());
						}
						max_buy = Integer.parseInt(directsellService.get(
								direct_id).getMax_buy());
						if (max_buy - ordernum- Integer.parseInt(order_num_str) <= 0) {
							response.sendRedirect("/mall/directOrder!payFinal.action?order_id_str="+ order_id[i]);
						}

					}

					String direPayState = directorderdetail.getPay_state();
					if (direPayState.equals("0")) {

						use_num_pay = Integer.parseInt(directorderdetail.getOrder_num())* directorderdetail.getEarnest();
					} else {
						if (l_o.endsWith("a")) {
							response.sendRedirect("/bmall_Goodsorder_buyorderlist.action");
						}
						use_num_pay = Integer.parseInt(directorderdetail.getOrder_num())* (directorderdetail.getGoods_price() - directorderdetail.getEarnest());
					}
					// 预售订单付款处理 a 和 b 段
					directorderdetailService.onlinePay(order_id, buy_cust_id, buy_user_id,use_num_pay, response);
				}
				/** ***预售订单的付款处理结束，分为两部分，定金和尾款** */
				if (goodsorder != null&& goodsorder.getOrder_state().equals("1")) {
					// 若是虚拟商品，生成消费码
					if (goodsorder.getOrder_type() != null&& goodsorder.getOrder_type().equals("0")) {
						// 生成消费码
						String mark_id = RandomStrUtil.getRand("12");
						goodsorder.setMark_id(mark_id);
					}
					// 若是试用订单,减掉剩余库存
					if (goodsorder.getOrder_type() != null&& goodsorder.getOrder_type().equals("7")) {
						Map detailMap = new HashMap();
						detailMap.put("order_id", goodsorder.getOrder_id());
						List list = orderdetailService.getList(detailMap);
						if (list != null && list.size() > 0) {
							Map tryMap = (HashMap) list.get(0);
							String goods_id = tryMap.get("goods_id").toString();
							if (!ValidateUtil.isRequired(goods_id)) {
								Map trygoodsMap = new HashMap();
								// 商品ID
								trygoodsMap.put("goods_id", goods_id);
								List trygoodsList = trygoodsService
										.getList(trygoodsMap);
								if (trygoodsList != null
										&& trygoodsList.size() > 0) {
									// 获取试用商品
									Map trymap = (HashMap) trygoodsList
											.get(0);
									Trygoods trygoods = trygoodsService
											.get(trymap.get("try_id")
													.toString());
									// 试用商品剩余量-1
									String surplus = (Integer
											.valueOf(trygoods.getSurplus()) - 1)
											+ "";
									trygoods.setSurplus(surplus);
									trygoodsService.update(trygoods);
									// 获取试用申请对象
									Map tryapplyMap = new HashMap();
									tryapplyMap.put("order_id", goodsorder
											.getOrder_id());
									List tryapplyList = tryapplyService
											.getList(tryapplyMap);
									if (tryapplyList != null
											&& tryapplyList.size() > 0) {
										// 修改试用状态
										Map tryapMap = (HashMap) tryapplyList.get(0);
										Tryapply tryapply = tryapplyService.get(tryapMap.get("tryapply_id").toString());
										tryapply.setStatus("1");
										tryapplyService.update(tryapply);
									}
								}
							}
						}
					}
					// 获取当前时间
					String cur_date_str = "";
					SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();
					cur_date_str = format.format(cal.getTime());
					goodsorder.setOrder_state("2");// 更改订单状态为已付款
					goodsorder.setPay_time(cur_date_str);
					goodsorder.setPay_id(pay_id);
					goodsorder.setPay_trxid(pay_trxid);
					goodsorderService.update(goodsorder);
					//减少库存
					orderdetailService.updateStockByOnlinepayment(goodsorder.getOrder_id());
					//更新商品销售量, 销量记录 在付款之后就加销量
					goodsorderService.updateGoodsSales(goodsorder.getOrder_id());
					// 在线处理资金及流水
					if (!"1".equals(o_type)) {
						Double actual_amount = goodsorder.getTatal_amount()-Double.valueOf(goodsorder.getBalance_use())-Double.valueOf(goodsorder.getIntegral_use());
						fundOnlineManage(order_id[i],actual_amount+"",buy_cust_id, buy_user_id);
					}
					// 若是虚拟商品
					if (goodsorder.getOrder_type() != null
							&& goodsorder.getOrder_type().equals("0")) {
						// 发送信息提醒
						// mestipByBuyer("6",order_id[i]);
					} else {
						// 发送信息提醒
						mestipByBuyer("4",order_id[i]);
					}
					insertOrdertrans(order_id[i], buy_cust_id, buy_user_id,memberuser.getUser_name());
				}
				
				
			}
			
			
			
		}
	}

	/**
	 * @author : HXK
	 * @param :
	 * @throws Exception
	 * @throws Exception
	 * @date Mar 11, 2014 2:28:08 PM
	 * @Method Description :在线支付/充值
	 */
	public static void useWXOnlinePay(String order_id_str,String actual_amount, String pay_trxid,String pay_id,String final_pay_method) throws Exception {
		IDirectorderdetailService directorderdetailService = (IDirectorderdetailService) getContext().getBean("directorderdetailService");
		IGoodsorderService goodsorderService = (IGoodsorderService) getContext().getBean("goodsorderService");
		IOrderdetailService orderdetailService = (IOrderdetailService) getContext().getBean("orderdetailService");
		ITrygoodsService trygoodsService = (ITrygoodsService) getContext().getBean("trygoodsService");
		ITryapplyService tryapplyService = (ITryapplyService) getContext().getBean("tryapplyService");
		IDirectsellService directsellService = (IDirectsellService) getContext().getBean("directsellService");
		HttpServletResponse response = ServletActionContext.getResponse();
		String[] order_id = order_id_str.split(",");
		for (int i = 0; i < order_id.length; i++) {
			String buy_cust_id ="",buy_user_id = "",o_type ="";
			Double use_num_pay = 0.0;
			String l_o = "";
			// 更新订单状态，交易成功操作
			l_o = order_id[i];
			Goodsorder goodsorder = new Goodsorder();
			goodsorder = goodsorderService.get(order_id[i]);
			if(goodsorder!=null){
				o_type=goodsorder.getOrder_type();
				buy_cust_id=goodsorder.getBuy_cust_id();
				Memberuser memberuser=new Memberuser();
				memberuser=MemberuserFuc.getMemberuserById(buy_cust_id);
				buy_user_id=memberuser.getUser_id();
				/** ***预售订单的付款处理开始，分为两部分，定金和尾款** */
				if ("6".equals(o_type)) {
					order_id[i] = order_id[i].substring(0, order_id[i].length() - 1);
					Directorderdetail directorderdetail = directorderdetailService.getByOrderId(order_id[i]);
					String direct_id = directorderdetail.getDirect_id();
					String order_num_str = directorderdetail.getOrder_num();
					if (directsellService.get(direct_id) != null) {
						// 获取购买人数
						int ordernum = 0, max_buy = 0;
						HashMap mMap = new HashMap();
						mMap.put("trade_id", direct_id);
						List dList = directsellService.getList(mMap);
						Map dMap=new HashMap(); 
						if(dList!=null&&dList.size()>0){
						   dMap = (Map) dList.get(0);
						}
						if (dMap.get("buynum") == null) {
							ordernum = 0;
						} else {
							ordernum = Integer.parseInt(dMap.get("buynum").toString());
						}
						max_buy = Integer.parseInt(directsellService.get(
								direct_id).getMax_buy());
						if (max_buy - ordernum- Integer.parseInt(order_num_str) <= 0) {
							response.sendRedirect("/mall/directOrder!payFinal.action?order_id_str="+ order_id[i]);
						}

					}

					String direPayState = directorderdetail.getPay_state();
					if (direPayState.equals("0")) {

						use_num_pay = Integer.parseInt(directorderdetail.getOrder_num())* directorderdetail.getEarnest();
					} else {
						if (l_o.endsWith("a")) {
							response.sendRedirect("/bmall_Goodsorder_buyorderlist.action");
						}
						use_num_pay = Integer.parseInt(directorderdetail.getOrder_num())* (directorderdetail.getGoods_price() - directorderdetail.getEarnest());
					}
					// 预售订单付款处理 a 和 b 段
					directorderdetailService.onlinePay(order_id, buy_cust_id, buy_user_id,use_num_pay, response);
				}
				/** ***预售订单的付款处理结束，分为两部分，定金和尾款** */
				if (goodsorder != null&& goodsorder.getOrder_state().equals("1")) {
					// 若是虚拟商品，生成消费码
					if (goodsorder.getOrder_type() != null&& goodsorder.getOrder_type().equals("0")) {
						// 生成消费码
						String mark_id = RandomStrUtil.getRand("12");
						goodsorder.setMark_id(mark_id);
					}
					// 若是试用订单,减掉剩余库存
					if (goodsorder.getOrder_type() != null&& goodsorder.getOrder_type().equals("7")) {
						Map detailMap = new HashMap();
						detailMap.put("order_id", goodsorder.getOrder_id());
						List list = orderdetailService.getList(detailMap);
						if (list != null && list.size() > 0) {
							Map tryMap = (HashMap) list.get(0);
							String goods_id = tryMap.get("goods_id").toString();
							if (!ValidateUtil.isRequired(goods_id)) {
								Map trygoodsMap = new HashMap();
								// 商品ID
								trygoodsMap.put("goods_id", goods_id);
								List trygoodsList = trygoodsService
										.getList(trygoodsMap);
								if (trygoodsList != null
										&& trygoodsList.size() > 0) {
									// 获取试用商品
									Map trymap = (HashMap) trygoodsList
											.get(0);
									Trygoods trygoods = trygoodsService
											.get(trymap.get("try_id")
													.toString());
									// 试用商品剩余量-1
									String surplus = (Integer
											.valueOf(trygoods.getSurplus()) - 1)
											+ "";
									trygoods.setSurplus(surplus);
									trygoodsService.update(trygoods);
									// 获取试用申请对象
									Map tryapplyMap = new HashMap();
									tryapplyMap.put("order_id", goodsorder
											.getOrder_id());
									List tryapplyList = tryapplyService
											.getList(tryapplyMap);
									if (tryapplyList != null
											&& tryapplyList.size() > 0) {
										// 修改试用状态
										Map tryapMap = (HashMap) tryapplyList.get(0);
										Tryapply tryapply = tryapplyService.get(tryapMap.get("tryapply_id").toString());
										tryapply.setStatus("1");
										tryapplyService.update(tryapply);
									}
								}
							}
						}
					}
					// 获取当前时间
					String cur_date_str = "";
					SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();
					cur_date_str = format.format(cal.getTime());
					goodsorder.setOrder_state("2");// 更改订单状态为已付款
					goodsorder.setPay_time(cur_date_str);
					goodsorder.setPay_id(pay_id);
					goodsorder.setPay_trxid(pay_trxid);
					goodsorder.setFinal_pay_method(final_pay_method);
					goodsorderService.update(goodsorder);
					//减少库存
					orderdetailService.updateStockByOnlinepayment(goodsorder.getOrder_id());
					//更新商品销售量, 销量记录 在付款之后就加销量
					goodsorderService.updateGoodsSales(goodsorder.getOrder_id());
					// 在线处理资金及流水
					if (!"1".equals(o_type)) {
						fundOnlineManage(order_id[i],actual_amount,buy_cust_id, buy_user_id);
					}
					// 若是虚拟商品
					if (goodsorder.getOrder_type() != null
							&& goodsorder.getOrder_type().equals("0")) {
						// 发送信息提醒
						// mestipByBuyer("6",order_id[i]);
					} else {
						// 发送信息提醒
						mestipByBuyer("4",order_id[i]);
					}
					insertOrdertrans(order_id[i], buy_cust_id, buy_user_id,memberuser.getUser_name());
				}
				
				
			}
			
			
			
		}
	}
	
	/**
	 * @author : HXK
	 * @param :
	 * @date Mar 12, 2014 1:33:55 PM
	 * @Method Description :资金处理
	 */
	private static void fundOnlineManage(String oid,String actual_amount,String buy_cust_id,String buy_user_id) {
		IGoodsorderService goodsorderService = (IGoodsorderService) getContext()
				.getBean("goodsorderService");
		IMemberfundService memberfundService = (IMemberfundService) getContext()
				.getBean("memberfundService");
		IFundhistoryService fundhistoryService = (IFundhistoryService) getContext()
				.getBean("fundhistoryService");
		ISysfundService sysfundService = (ISysfundService) getContext()
				.getBean("sysfundService");

		Goodsorder order = goodsorderService.get(oid);
		// 会员通过第三方支付平台支付完后，将付款金额用等值的余额冻结到平台 的冻结资金中
		// 运营商资金处理
		sysfundService.addNumByOnLion(Double.valueOf(actual_amount));
		// 获取买家的ID
		String order_buy_cust_id = order.getBuy_cust_id();

		// 买家资金处理
		Double buy_use_num = 0.0;// 可用金额
		Memberfund buy_mf = memberfundService.get(order_buy_cust_id);
		if (buy_mf != null) {
			buy_use_num = Double.parseDouble(buy_mf.getUse_num());
		}
		// 买家的资金异动
		Fundhistory buy_fh = new Fundhistory();
		buy_fh.setBalance(buy_use_num);
		buy_fh.setFund_in(0.00);
		buy_fh.setFund_out(0.00);
		buy_fh.setPay_type("1");// 在线支付类型
		buy_fh.setCust_id(buy_cust_id);
		buy_fh.setUser_id(buy_user_id);
		buy_fh.setReason("买家为订单号:" + oid + ",在线支付" + actual_amount+ "元");
		fundhistoryService.insert(buy_fh);

	}

	/**
	 * 
	 * @param order_id
	 * @param cust_id
	 * @param user_id
	 */
	public static void insertOrdertrans(String order_id, String cust_id,String user_id,String opt_user_name) {
		// 插入订单异动记录
		IOrdertransService ordertransService = (IOrdertransService) getContext()
				.getBean("ordertransService");
		ordertransService.inserOrderTran(order_id, cust_id, user_id, "订单已付款", "2", opt_user_name);
	}

	/**
	 * @author : HXK
	 * @param :mes_id：消息提醒模版
	 *            order_id：订单ID
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :前台订单提醒-发送给买家
	 */
	public static void mestipByBuyer(String mes_id, String order_id)
			throws UnsupportedEncodingException {
		IGoodsorderService goodsorderService = (IGoodsorderService) getContext()
				.getBean("goodsorderService");
		Goodsorder gorder = new Goodsorder();
		gorder = goodsorderService.get(order_id);
		if (gorder != null) {
			MessageAltFuc mesalt = new MessageAltFuc();
			mesalt.messageAutoSend(mes_id, gorder.getBuy_cust_id(), gorder);
		}
	}

	/**
	 * @author : HXK
	 * @param :mes_id：消息提醒模版
	 *            order_id：订单ID
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :前台订单提醒-发送给卖家
	 */
	public void mestipBySeller(String mes_id, String order_id)
			throws UnsupportedEncodingException {
		IGoodsorderService goodsorderService = (IGoodsorderService) getContext()
				.getBean("goodsorderService");
		Goodsorder gorder = new Goodsorder();
		gorder = goodsorderService.get(order_id);
		MessageAltFuc mesalt = new MessageAltFuc();
		mesalt.messageAutoSend(mes_id, gorder.getSell_cust_id(), gorder);
	}

	/**
	 * @author : CYC
	 * @param :mes_id：获取积分商品trade_id
	 *            ：积分ID
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :获取积分商品trade_id
	 */
	public static String getpointstrade_id(String goods_id) {
		IPointsgoodsService pointsgoodsService = (IPointsgoodsService) getContext()
				.getBean("pointsgoodsService");
		Pointsgoods pointsgoods = new Pointsgoods();
		HashMap map = new HashMap();
		map.put("goods_id", goods_id);
		List pointsgoodsList = pointsgoodsService.getList(map);
		String trade_id = "";
		if (pointsgoodsList != null && pointsgoodsList.size() > 0) {
			HashMap mapvalue = new HashMap();
			mapvalue = (HashMap) pointsgoodsList.get(0);
			trade_id = mapvalue.get("trade_id").toString();
		}
		return trade_id;
	}

	/**
	 * @funtion 校验订单是否已经支付了。
	 * @author QJY
	 * @param order_id
	 * @return boolean
	 */
	public static boolean verifyOrderState(String order_id) {
		IGoodsorderService goodsorderService = (IGoodsorderService) getContext().getBean("goodsorderService");
		Goodsorder goodsorder = goodsorderService.get(order_id);
		if(goodsorder!=null){
			String orderstate = goodsorder.getOrder_state();
			// 订单状态不等于2，并且要等于1， 1：未付款 2：已付款
			if ("1".equals(orderstate)) {
				return true;
			} else {
				return false;
			}
		}else {
			return false;
		}
	}

	/**
	 * @funtion 交易退款数据集拼装 QJY
	 * @return
	 */
	public static String dealRefundInfo(String refund_type,
			String goods_order_str, String refund_no_str) {
		IGoodsorderService goodsorderService = (IGoodsorderService) getContext()
				.getBean("goodsorderService");

		StringBuffer refundSB = new StringBuffer();
		StringBuffer refundSB_all = new StringBuffer();
		String pay_trxid = "", refund_amount = "", reund_reason = "";
		if (refund_type.equals("1")) {// 取消订单 退款

			ICancelorderService cancelorderService = (ICancelorderService) getContext()
					.getBean("cancelorderService");

			if (goods_order_str != null && !"".equals(goods_order_str)) {
				String[] refund_order_str = goods_order_str.split(",");
				Goodsorder goodsorder = null;
				Cancelorder cancelorder = null;
				if (refund_order_str.length == 1) {// 单笔-交易退款数据集-格式：原付款支付宝交易号^退款总金额^退款理由
					for (int i = 0; i < refund_order_str.length; i++) {
						goodsorder = goodsorderService.get(refund_order_str[i]);
						cancelorder = cancelorderService
								.getByOrderId(refund_order_str[i]);
						pay_trxid = goodsorder.getPay_trxid();
						refund_amount = goodsorder.getTatal_amount().toString();
						if(cancelorder!=null){
							reund_reason = cancelorder.getBuy_reason();
						}else{
							reund_reason="人工处理取消订单退款";
							goodsorder.setOrder_state("4");
							goodsorderService.update(goodsorder);
						}
						refundSB_all.append(pay_trxid).append("^");
						refundSB_all.append(refund_amount).append("^");
						refundSB_all.append(reund_reason);
					}
				} else if (refund_order_str.length > 1) {// 多笔_交易推看数据集-格式：第一笔交易退款数据集#第二笔交易退款数据集#第三笔交易退款数据集…#第
															// N 笔交易退款数据集
					for (int i = 0; i < refund_order_str.length; i++) {
						goodsorder = goodsorderService.get(refund_order_str[i]);
						cancelorder = cancelorderService
								.getByOrderId(refund_order_str[i]);
						pay_trxid = goodsorder.getPay_trxid();
						refund_amount = goodsorder.getTatal_amount().toString();
						reund_reason = cancelorder.getBuy_reason();
						refundSB.append(pay_trxid).append("^");
						refundSB.append(refund_amount).append("^");
						refundSB.append(reund_reason);
						refundSB_all.append(refundSB.toString());
						refundSB_all.append("#");
					}
					refundSB_all.deleteCharAt(refundSB_all.length() - 1);
				}

			}

		} else if (refund_type.equals("2")) {// 单个商品或者多个商品退款

			IRefundappService refundappService = (IRefundappService) getContext()
					.getBean("refundappService");

			if (refund_no_str != null && !"".equals(refund_no_str)) {
				String[] refund_order_array = refund_no_str.split(",");
				Goodsorder goodsorder = null;
				Refundapp refundapp = null;

				if (refund_order_array.length == 1) {// 单笔-交易退款数据集-格式：原付款支付宝交易号^退款总金额^退款理由
					for (int i = 0; i < refund_order_array.length; i++) {
						refundapp = refundappService
								.getByRefundNo(refund_order_array[i]);
						goodsorder = goodsorderService.get(refundapp
								.getOrder_id());
						pay_trxid = goodsorder.getPay_trxid();// 交易流水号
						refund_amount = refundapp.getRefund_amount();
						reund_reason = refundapp.getBuy_reason();
						refundSB_all.append(pay_trxid).append("^");
						refundSB_all.append(refund_amount).append("^");
						refundSB_all.append(reund_reason);
					}
				} else if (refund_order_array.length > 1) {// 多笔_交易推看数据集-格式：第一笔交易退款数据集#第二笔交易退款数据集#第三笔交易退款数据集…#第
															// N 笔交易退款数据集
					for (int i = 0; i < refund_order_array.length; i++) {
						refundapp = refundappService
								.getByRefundNo(refund_order_array[i]);
						goodsorder = goodsorderService.get(refundapp
								.getOrder_id());
						pay_trxid = goodsorder.getPay_trxid();// 交易流水号
						refund_amount = refundapp.getRefund_amount();
						reund_reason = refundapp.getBuy_reason();
						refundSB.append(pay_trxid).append("^");
						refundSB.append(refund_amount).append("^");
						refundSB.append(reund_reason);
						refundSB_all.append(refundSB.toString());
						refundSB_all.append("#");
					}
					refundSB_all.deleteCharAt(refundSB_all.length() - 1);
				}

			}
		}
		return refundSB_all.toString();
	}

	/**
	 * 退款的笔数
	 * 
	 * QJY
	 */
	public static String dealRefundNum(String refund_type, String order_id_str,
			String refund_no_str) {
		String refund_num = "";
		if (refund_type.equals("1")) {// 取消订单退款
			if (order_id_str != null && !"".equals(order_id_str)) {
				String[] refund_order_str = order_id_str.split(",");
				refund_num = String.valueOf(refund_order_str.length);
			}
		} else if (refund_type.equals("2")) {// 单个商品或者多个商品退款
			if (refund_no_str != null && !"".equals(refund_no_str)) {
				String[] refund_no_array = refund_no_str.split(",");
				refund_num = String.valueOf(refund_no_array.length);
			}
		}

		return refund_num;
	}

	/**
	 * 退款批次号
	 * 
	 * @return
	 */
	public static String dealRefundBatchNo(String refund_type, String refund_no) {
		String refundBatchNo = "";
		if (refund_type.equals("1")) {// 取消订单 的退款批次号

			refundBatchNo = DateUtil.getDate() + DateUtil.getSix();

		} else if (refund_type.equals("2")) {// //单个商品或者多个商品的退款批次号

			refundBatchNo = DateUtil.getDate() + refund_no;
			IRefundappService refundappService = (IRefundappService) getContext()
					.getBean("refundappService");
			Refundapp refundapp = refundappService.getByRefundNo(refund_no);
			refundapp.setBatch_no(refundBatchNo);
			refundappService.update(refundapp);
		}

		return refundBatchNo;
	}

	/**退款通知
	 * @throws Exception
	 * 
	 */
	public static void dealRefund_notify(String batch_no, String result_details,String front_back_tip)
			throws Exception {
		IRefundappService refundappService = (IRefundappService) getContext()
				.getBean("refundappService");
		Refundapp refundapp = null;
		if (batch_no != null && !"".equals(batch_no)) {
			refundapp = refundappService.getByBatchNo(batch_no);// 是否能在退款表中找到支付宝退款服务器返回的批次号
		}
		if (refundapp != null) {

			String[] result_details_str = result_details.split("\\#");
			for (int i = 0; i < result_details_str.length; i++) {
				String[] refund_details_str = result_details_str[i]
						.split("\\^");
				if (refund_details_str != null
						&& !"".equals(refund_details_str)) {
					dealGoodsRefund(batch_no, refund_details_str[2]);
				}
			}

		} else {
			String[] result_details_str = result_details.split("\\#");
			for (int i = 0; i < result_details_str.length; i++) {
				String[] refund_details_str = result_details_str[i]
						.split("\\^");
				if (refund_details_str != null
						&& !"".equals(refund_details_str)) {
					dealOrderRefund(refund_details_str[0],
							refund_details_str[2],front_back_tip);
				}
			}
		}

	}

	/**
	 * 
	 * @param batch_no
	 * @param refundappService
	 * @param ordertransService
	 * @throws Exception
	 */
	public static void dealGoodsRefund(String batch_no, String refund_flag)
			throws Exception {
		if (refund_flag.equals("SUCCESS")) {
			IRefundappService refundappService = (IRefundappService) getContext()
					.getBean("refundappService");
			IOrdertransService ordertransService = (IOrdertransService) getContext()
					.getBean("ordertransService");
			ISysfundService sysfundService = (ISysfundService) getContext()
					.getBean("sysfundService");
			IGoodsorderService goodsorderService = (IGoodsorderService) getContext()
			.getBean("goodsorderService");
			IMemberuserService memberuserService = (IMemberuserService) getContext()
			.getBean("memberuserService");
			Refundapp refundapp = refundappService.getByBatchNo(batch_no);
			// 判断是 退货完退款 或者 退款 操作
			String order_id = refundapp.getOrder_id();
			String is_return = refundapp.getIs_return();
			String goods_id_str = refundapp.getGoods_id_str();
			String refund_amount = refundapp.getRefund_amount();
			refundappService.updateOrderDetailState(goods_id_str, "3");
			refundapp.setRefund_state("1");
			if (is_return.equals("0")) {
				refundapp.setSeller_state("0");// 同意退款
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			refundapp.setSeller_date(df.format(new Date()));
			refundappService.update(refundapp);// 退款成功后执行邮件发送
			// 在线退款成功后，处理平台资金
			sysfundService.reduceNumByrefund(Double.valueOf(refund_amount));
			
			//红包，优惠券
			CouponFuc recoupon = new CouponFuc();
			recoupon.cancelCouponAnd(order_id);
			
			// 同意 消息提醒
			mestipByBuyer("12", order_id);
			
			Goodsorder goodsorder = goodsorderService.get(order_id);
			//会员
			String buy_cust_id = goodsorder.getBuy_cust_id();
			String sell_cust_id = goodsorder.getSell_cust_id();
			Memberuser adminmemberuser = memberuserService.getPKByCustID(sell_cust_id);
			String sell_user_id = adminmemberuser.getUser_id();
			ordertransService.inserOrderTran(order_id, buy_cust_id, sell_user_id, CommparaFuc.getReason("1", "同意退款,且已将资金退款给会员!"), "5", adminmemberuser.getUser_name());
		}

	}

	/**
	 * 处理退款异步通知业务
	 */
	public static void dealOrderRefund(String paytrxID, String refund_flag,String front_back_tip) {
		if (refund_flag.equals("SUCCESS")) {
			IGoodsorderService goodsorderService = (IGoodsorderService) getContext().getBean("goodsorderService");
			ICancelorderService cancelorderService = (ICancelorderService) getContext().getBean("cancelorderService");
			ISysfundService sysfundService = (ISysfundService) getContext().getBean("sysfundService");
			IMemberfundService memberfundService = (IMemberfundService) getContext().getBean("memberfundService");
			IMemberuserService memberuserService = (IMemberuserService) getContext().getBean("memberuserService");
			IOrderdetailService orderdetailService=(IOrderdetailService) getContext().getBean("orderdetailService");
			// 通过支付交易流水号取得对应的订单信息，订单号与支付交易流水号是一一对应的
			Goodsorder goodsorder = goodsorderService.getByTrxID(paytrxID);
			if (goodsorder.getOrder_state().equals("4")) {// 处于退款中才做处理
				String goods_order_id = goodsorder.getOrder_id();
				double refund_amount = goodsorder.getTatal_amount();
				double refund_balance = Double.valueOf(goodsorder.getBalance_use());
				//会员
				String buy_cust_id = goodsorder.getBuy_cust_id();
				Memberuser memberuser = memberuserService.getPKByCustID(buy_cust_id);
				String buy_user_id = memberuser.getUser_id();
				
				String sell_cust_id = goodsorder.getSell_cust_id();
				Memberuser adminmemberuser = memberuserService.getPKByCustID(sell_cust_id);
				String sell_user_id = adminmemberuser.getUser_id();
					
				String integral_use = goodsorder.getIntegral_use();
				String reason = "";
				// 在线退款成功后，处理平台资金
				sysfundService.reduceNumByrefund(refund_amount);
				// 会员余额
				insertFundhistory(buy_cust_id,buy_user_id,goods_order_id,refund_amount);
				if(refund_balance!=0){
					memberfundService.outAndInNum(buy_cust_id,refund_balance,1);
					insertFundhistory(buy_cust_id,buy_user_id,goods_order_id,refund_balance);
				}
				
				if(integral_use!=null&&!"".equals(integral_use) && !integral_use.equals("0.0")&&!integral_use.equals("0")){
					insertInterhistory(buy_cust_id,buy_user_id,Double.valueOf(integral_use));
				}
				
				//front_back_tip:  0 前台会员取消订单  1运营商管理员取消订单
				if(front_back_tip.equals("0")){
					// 取消订单表中的状态
					Cancelorder cancelorder=new Cancelorder();
					 cancelorder = cancelorderService.getByOrderId(goods_order_id);
					cancelorder.setOrder_state("1");// 0：处理中 1：同意取消 2：拒接取消
					cancelorderService.update(cancelorder);

					// 置为取消订单
					reason = "您的订单退款处理成功，正在按原路退回，请耐心等待。";
					insertCancelOrderTrans(goods_order_id, reason,buy_cust_id,sell_user_id);
				 }else {
					    Cancelorder cancelorder=new Cancelorder();
						cancelorder = cancelorderService.getByOrderId(goods_order_id);
						if(cancelorder!=null&&cancelorder.getOrder_state()!=null&&!"".equals(cancelorder.getOrder_state())){
							cancelorder.setOrder_state("1");// 0：处理中 1：同意取消 2：拒接取消
							cancelorderService.update(cancelorder);
							// 置为取消订单
							reason = "您的订单退款处理成功，正在按原路退回，请耐心等待。";
							insertCancelOrderTrans(goods_order_id, reason,buy_cust_id,sell_user_id);
						}
				}
				 //更新订单状态为退款成功
				 updateOrderState(goods_order_id, "5",buy_cust_id,sell_user_id,adminmemberuser.getUser_name());
				 // 更新订单状态为 取消成功
				 updateOrderState(goods_order_id, "0",buy_cust_id,sell_user_id,adminmemberuser.getUser_name());// 将订单状态变为 退款成功 后直接
				 //更新库存
				//更新库存 把库存新增回去
		    	 orderdetailService.addStockByCancleOrder(goods_order_id);
				
			}
		}

	}

	
	public static void insertFundhistory(String  buy_cust_id,String buy_user_id,String goods_order_id,double amount){
		IMemberfundService memberfundService = (IMemberfundService) getContext()
		.getBean("memberfundService");
		IFundhistoryService fundhistoryService = (IFundhistoryService) getContext()
		.getBean("fundhistoryService");
		// 买家的资金异动
		Memberfund buymf = memberfundService.get(buy_cust_id);
		Fundhistory buy_fh = new Fundhistory();
		buy_fh.setBalance(Double.parseDouble(buymf.getFund_num()));
		buy_fh.setCust_id(buy_cust_id);
		buy_fh.setFund_in(amount);
		buy_fh.setReason("会员收到订单号:" + goods_order_id + " 取消订单在线退款"
				+ amount + "元");
		buy_fh.setFund_out(0.0);
		buy_fh.setUser_id(buy_user_id);
		fundhistoryService.insert(buy_fh);
	}
	
	public static void insertInterhistory(String buy_cust_id,String buy_user_id,double integral_use){
		IMemberinterService memberinterService = (IMemberinterService) getContext()
		.getBean("memberinterService");
		//退回 积分给会员
		Memberinter memberinter = memberinterService.get(buy_cust_id);
		int cfg_sc_exchscale=Integer.parseInt(SysconfigFuc.getSysValue("cfg_sc_exchscale"));
		double re_integral = integral_use*cfg_sc_exchscale;
		Double total_inter = Double.valueOf(memberinter.getFund_num())+re_integral;
		memberinter.setFund_num(total_inter.toString());
		//积分异动
		Interhistory interhistory = new Interhistory();
		interhistory.setInter_in(String.valueOf(integral_use));
		interhistory.setInter_out("0");
		Double value_fund = Double.valueOf(memberinter.getFund_num())
				+ Double.valueOf(String.valueOf(re_integral));
		interhistory.setThisinter(String.valueOf(value_fund));
		interhistory.setCust_id(buy_cust_id);
		interhistory.setUser_id(buy_user_id);
		interhistory.setReason("会员取消订单");
		interhistory.setRemark("");
		
		//更新积分表和积分异动表
		memberinterService.updateinter(interhistory, memberinter); 
		
	}
	
	/**
	 * 插入取消订单异动表信息
	 * 
	 * @param order_id
	 * @param reason
	 */
	public static void insertCancelOrderTrans(String order_id,String reason,String cust_id,String user_id) {
		ICancleordertransService cancleordertransService = (ICancleordertransService) getContext()
				.getBean("cancleordertransService");
		Cancleordertrans cancleordertrans = new Cancleordertrans();
		cancleordertrans.setCust_id(cust_id);
		cancleordertrans.setUser_id(user_id);
		cancleordertrans.setReason(reason);
		cancleordertrans.setOrder_id(order_id);
		cancleordertransService.insert(cancleordertrans);
	}

	/**
	 * @author : HXK 方法描述：会员修改订单状态
	 * @return
	 * @throws Exception
	 */
	public static void updateOrderState(String s_order_id, String s_order_state,String cust_id,String user_id,String opt_user_name) {
		// 插入订单异动信息表
		String reason = CommparaFuc.getReason(s_order_state, null);
		updateOrderState(s_order_id, s_order_state, reason,cust_id,user_id,opt_user_name);
	}

	/**
	 * @author : HXK 方法描述：会员修改订单状态
	 * @return
	 * @throws Exception
	 */
	public static void updateOrderState(String s_order_id,String s_order_state, String order_reason,String cust_id,String user_id,String opt_user_name) {
		IGoodsorderService goodsorderService = (IGoodsorderService) getContext()
				.getBean("goodsorderService");
		// 订单状态表示：0：订单取消 1：未付款 2：已付款 3：已发货 4：退款中 5：退款成功 6：退款失败：7：交易成功 8：已评价
		Goodsorder goods_order = new Goodsorder();
		goods_order.setOrder_id(s_order_id);
		goods_order.setOrder_state(s_order_state);
		Map stateMap = new HashMap();
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
		goodsorderService.update(stateMap);
		String reason = CommparaFuc.getReason(s_order_state, order_reason);
		// 插入订单异动信息表
		insertOrderTrans(s_order_id, reason, s_order_state,cust_id,user_id,opt_user_name);
	}

	/**
	 * 方法描述：修改订单的时候，插入订单异动表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static void insertOrderTrans(String order_id, String reason,
			String order_state,String cust_id,String user_id,String opt_user_name) {
		IOrdertransService ordertransService = (IOrdertransService) getContext()
				.getBean("ordertransService");
		ordertransService.inserOrderTran(order_id, cust_id, user_id, reason, order_state, opt_user_name);
	}

	/**
	 * 方法描述：修改订单的时候，插入订单异动表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Goodsorder getGoodsOrder(String order_id)throws Exception{
		IGoodsorderService goodsorderService = (IGoodsorderService) getContext()
		.getBean("goodsorderService");
		Goodsorder goodsorder = goodsorderService.get(order_id);
		return goodsorder;
	}
	
}