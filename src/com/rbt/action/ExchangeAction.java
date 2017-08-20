/*
 
 * Package:com.rbt.action
 * FileName: ExchangeAction.java 
 */
package com.rbt.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.rbt.common.util.DateUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.function.CommparaFuc;
import com.rbt.function.LogisticsFuc;
import com.rbt.function.MemberuserFuc;
import com.rbt.function.MessageAltFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.message.MailInter;
import com.rbt.model.Buyeraddr;
import com.rbt.model.Exchange;
import com.rbt.model.Goodsorder;
import com.rbt.model.Memberuser;
import com.rbt.model.Orderdetail;
import com.rbt.model.Ordertrans;
import com.rbt.model.Sendmode;
import com.rbt.model.Shopconfig;
import com.rbt.service.IBuyeraddrService;
import com.rbt.service.IExchangeService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.IOrdertransService;
import com.rbt.service.IRefundappService;
import com.rbt.service.ISendmodeService;
import com.rbt.service.IShopconfigService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 换货action类
 * @author 创建人 HZX
 * @date 创建日期 Mon Jan 12 13:50:08 CST 2015
 */
@Controller
public class ExchangeAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 换货对象
	 */
	private Exchange exchange;
	public Sendmode sendmode;
	public Orderdetail orderdetail;
	public Goodsorder goodsorder;
	public Shopconfig shopconfig;
	public Ordertrans ordertrans;// 订单异动记录
	public Buyeraddr buyeraddr;// 收货地址对象
	/*******************业务层接口****************/
	/*
	 * 换货业务层接口
	 */
	@Autowired
	private IExchangeService exchangeService;
	@Autowired
	public ISendmodeService sendmodeService;
	@Autowired
	public IOrderdetailService orderdetailService;
	@Autowired
	public IOrdertransService ordertransService;
	@Autowired
	public IBuyeraddrService buyeraddrService;
	@Autowired
	private IRefundappService refundappService;
	@Autowired
	private IGoodsorderService goodsorderService;
	@Autowired
	public IShopconfigService shopconfigService;
	/*********************集合*******************/
	/*
	 * 换货信息集合
	 */
	public List exchangeList;
	public List detailList;// 订单详细列表信息
	public List buyeraddrList;
	public List addrList;// 收货地列表
	public List sendmodeList;// 物流公司
	
	//运营商地址集合信息
	public List shopconfigList;
	/*********************字段*******************/
	public String buy_cust_name_s;//买家
	public String seller_name;//卖家
	public String buy_name; //买家
	public String order_count;//订单总金额
	public String order_id_s;//订单号
	public String refund_state_s;//换货状态
	public String info_state_s;//处理状态
	public String seller_cust_name_s;//卖家
	public String buy_cust_id;//买家标识
	public String seller_cust_id; //卖家标识
	public String seller_cust_id_no; //卖家标识
	public String goods_order;//订单编号
	public String order_id;// 订单号
	public String logistics_query;// 快递查询结果
	public String kuai_number;// 快递号
	public String kuai_company;// 快递公司
	public String kuai_company_code;// 快递公司代码
	// 退款******
	public String exchange_id;//换货id
	public String imgString;// 图片凭证
	public boolean is_deny_num = false;
	public String buy_refund_reason;// 退款说明
	public String seller_refund_reason;// 拒绝退款说明
	public String refundDealtime;// 卖家处理时间
	public String refund_sendtime;// 买家退货时间
	public String refund_suretime;// 卖家确认收货时间
	public String sell_remark;// 卖家给买家退货地址时的留言
	public String send_mode;// 退货物流公司
	public String send_num;// 退货运单号
	public int cfg_Refund_deny_num = Integer.parseInt(SysconfigFuc.getSysValue("cfg_Refund_deny_num"));// 卖家拒绝退款最多次数
	public boolean is_buyer = false;// 判断是否买家
	public Integer orderdetaiCount = 0;
	public String order_area;// 地区
	public String addr_id;// 收货地址标识
	public String shopconfig_id;
	public String return_no_s;
	public String is_return_str;
	public String refund_goods_id_str;//换货选择的商品ID
	public String order_send_mode;// 配送物流
	public String order_send_num;// 物流单号
	public String refund_reason;
	public String refund_type;
	public String cancel_trade_id;
	public String exchange_seller_reason;
	public String starttime_s;
	public String endtime_s;
		
	/**
	 * 方法描述：返回新增换货页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增换货
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(exchange);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.exchangeService.insert(exchange);
		this.addActionMessage("新增换货成功！");
		this.exchange = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改换货信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(exchange);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.exchangeService.update(exchange);
		this.addActionMessage("修改换货成功！");
		return list();
	}
	/**
	 * 方法描述：删除换货信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除换货信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.exchangeService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除换货信息成功!");
		}else{
			this.addActionMessage("删除换货信息失败!");
		}
	}
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出换货信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.exchange.getTrade_id();
		if(id==null || id.equals("")){
			exchange = new Exchange();
		}else{
			exchange = this.exchangeService.get(id);
			Goodsorder goodsorder=new Goodsorder();
			if(this.goodsorderService.get(exchange.getOrder_id())!=null){
				goodsorder=this.goodsorderService.get(exchange.getOrder_id());
				order_count=goodsorder.getTatal_amount().toString();
			}
		}
		gOrderCommparaState("buy_refund");
		buy_name=this.memberService.getCustName(exchange.getBuy_cust_id());
		seller_name=this.memberService.getCustName("0");
		return goUrl(VIEW);
	}
	
	public void commonView(){
		String id = this.exchange.getTrade_id();
		String sell_cust_id="0";
		if(id==null || id.equals("")){
			exchange = new Exchange();
		}else{
			exchange = this.exchangeService.get(id);
		}
		gOrderCommparaState("buy_exchange");
		if(exchange.getOrder_id()!=null){
			goodsorder=goodsorderService.get(exchange.getOrder_id());
			Map detailMap = new HashMap();
			if(exchange.getDetail_id_str()!=null&&!"".equals( exchange.getDetail_id_str())){
				detailMap.put("trade_id_s", exchange.getDetail_id_str());
				detailList = this.orderdetailService.getList(detailMap);
			}
			// 获取卖家会员信息
			sell_cust_id = goodsorder.getSell_cust_id();
		}
		//获取买家名称
		Memberuser muser=new Memberuser();
		muser=MemberuserFuc.getuserName(exchange.getBuy_cust_id());
		buy_cust_name_s = muser.getUser_name();
		buy_name=muser.getUser_name();
		//将收货地址转换为中文
		if(exchange.getArea_attr()!=null&&!"".equals(exchange.getArea_attr())){
			exchange.setArea_attr(AreaFuc.getAreaNameByMap(exchange.getArea_attr()));
		}
		if(exchange.getMarea_attr()!=null&&!"".equals(exchange.getMarea_attr())){
			exchange.setMarea_attr(AreaFuc.getAreaNameByMap(exchange.getMarea_attr()));
		}
		//获取物流公司
		gOrderSendmode();
		//获取物流信息
		if(exchange.getSend_mode()!=null&&!"".equals(exchange.getSend_num())){
			getLogistics(exchange.getSend_mode(),exchange.getSend_num());
		}
		//获取商家退货地址
		buyeraddr=new Buyeraddr();
		buyeraddr=buyeraddrService.getbuyerByCust_id(buyeraddr, sell_cust_id);
		
	}
	/**
	 * @author : HZX
	 * @date : Nov 8, 2014 8:43:19 AM
	 * @Method Description :获取快递信息
	 */
	public void getLogistics(String smode_id, String number) {
		Sendmode sendmode = new Sendmode();
		if (smode_id != null && !"".equals(smode_id)) {
			sendmode = sendmodeService.get(smode_id);
		}
		if (sendmode != null) {
			// 获取快递公司代码
			kuai_company_code = sendmode.getEn_name();
			// 获取快递公司名称
			kuai_company = sendmode.getSmode_name();
		}
		kuai_number = number;
		// 查询快递信息
		logistics_query = LogisticsFuc.hundredTrace(kuai_company_code,
				kuai_number);
	}

	
	
		/**
		 * 方法描述：获取配送方式
		 * 
		 * @return
		 * @throws Exception
		 */
		public void gOrderSendmode() {
			Map orderMap = new HashMap();
			sendmodeList = sendmodeService.getList(orderMap);
		}
		public String exView()throws Exception{
				commonView();
				return goUrl("exGoods");
		}
		
		public String exGoodsView()throws Exception{
			commonView();
			return goUrl("exGoodsDetails");
		}
		
		/**
		 * @author: HXK
		 * @date : Apr 20, 2014 11:32:11 AM
		 * @Method Description : 查看退货详情
		 */

		public String buygoodsview() throws Exception {
			commonView();
			return goUrl("buygoodsview");
		}
		
		public String sellDelivery() throws Exception {
			if (exchange.getTrade_id() != null && !"".equals(exchange.getTrade_id())) {
				if(exchange.getRefund_state().equals("6")){
					// 物流公司
					if (order_send_mode != null && !"".equals(order_send_mode)) {
						exchange.setSend_mode(order_send_mode);
					}
					// 快递单号
					if (order_send_num != null && !"".equals(order_send_num)) {
						exchange.setSend_num(order_send_num);
					} else {
						this.addFieldError("order_send_num_tip", "请填写运单号码");
						return exView();
					}
					//设置已经发货
					exchange.setRefund_state("7");
					SimpleDateFormat df = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");// 设置日期格式
					exchange.setSend_time(df.format(new Date()));
					exchangeService.update(exchange);
					// 修改订单状态
					this.addActionMessage("发货成功");
				}
				else{
					// 修改订单状态
					this.addActionMessage("发货失败");
				}
			}
			getResponse().sendRedirect("/admin_Exchange_exGoodsList.action");
			return null;
		}
		/**
		 * @author : HXK 退货 买家发货20150112 14.16
		 * @return
		 * @throws Exception
		 */
		public String buyDelivery() throws Exception {
			if (exchange.getTrade_id() != null && !"".equals(exchange.getTrade_id())) {
				if(exchange.getRefund_state().equals("3")){
					// 物流公司
					if (order_send_mode != null && !"".equals(order_send_mode)) {
						exchange.setMsend_mode(order_send_mode);
					}
					// 快递单号
					if (order_send_num != null && !"".equals(order_send_num)) {
						exchange.setMsend_num(order_send_num);
					} else {
						this.addFieldError("order_send_num_tip", "请填写运单号码");
						return buygoodsview();
					}
					//设置已经发货
					exchange.setRefund_state("4");
					SimpleDateFormat df = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");// 设置日期格式
					exchange.setMsend_time(df.format(new Date()));
					exchangeService.update(exchange);
					// 修改订单状态
					this.addActionMessage("发货成功");
				}
				else{
					// 修改订单状态
					this.addActionMessage("发货失败");
				}
			}
			getResponse().sendRedirect("/bmall_Exchange_buylist.action");
			return NONE;
		}
		/**
		 * 方法描述：根据搜索条件列出信息列表
		 * @return
		 * @throws Exception
		 */
		@SuppressWarnings("unchecked")
		public String buylist() throws Exception {
			buy_cust_id=this.session_cust_id;
			commonList();
			return goUrl("exList");
		}
		public void commonList(){
			Map pageMap = new HashMap();
			if(order_id_s!=null&&!"".equals(order_id_s)){
				pageMap.put("order_id", order_id_s);
			}
			if(buy_cust_name_s!=null&&!"".equals(buy_cust_name_s)){
				pageMap.put("buy_cust_name", buy_cust_name_s);
			}
			if(seller_cust_name_s!=null&&!"".equals(seller_cust_name_s)){
				pageMap.put("seller_cust_name", seller_cust_name_s);
			}
			if(refund_state_s!=null&&!"".equals(refund_state_s)&&!refund_state_s.equals("999")){
				pageMap.put("refund_state", refund_state_s);
			}
			if(info_state_s!=null&&!"".equals(info_state_s)&&!info_state_s.equals("")){
				pageMap.put("info_state", info_state_s);
			}
			if(!validateFactory.isRequired(seller_cust_id)){
				pageMap.put("seller_cust_id", seller_cust_id);
			}
			if(!validateFactory.isRequired(seller_cust_id_no)){
				pageMap.put("seller_cust_id_no", seller_cust_id_no);
			}
			if(!validateFactory.isRequired(return_no_s)){
				pageMap.put("return_no", return_no_s);
			}
			if(!ValidateUtil.isRequired(starttime_s)){
				pageMap.put("starttime", starttime_s);
			}
			
	        if(!ValidateUtil.isRequired(endtime_s)){
	        	pageMap.put("endtime", endtime_s);
			}
			if(!validateFactory.isRequired(buy_cust_id)){
				pageMap.put("buy_cust_id", buy_cust_id);
			}
			//根据页面提交的条件找出信息总数
			int count=this.exchangeService.getCount(pageMap);
			//分页插件
			pageMap = super.pageTool(count,pageMap);
			exchangeList = this.exchangeService.getList(pageMap);
			String orderidStr="";
			for (int i = 0; i < exchangeList.size(); i++) {
				HashMap listMap = (HashMap) (exchangeList).get(i);
				if (listMap.get("detail_id_str") != null) {
					orderidStr += listMap.get("detail_id_str").toString() + ",";
				}
			}
			if(orderidStr!=null&&!"".equals(orderidStr)){
				orderidStr=orderidStr.substring(0, orderidStr.length()-1);
				Map detailMap = new HashMap();
				detailMap.put("trade_ids_s", orderidStr);
				detailList = this.orderdetailService.getList(detailMap);
			}
			
			gOrderCommparaState("buy_refund");
		}
		public String exGoodsList() throws Exception{
			commonList();
			return goUrl("exList");
		}
		
		/**不同意退款操作
		 * 
		 * @return
		 * @throws Exception
		 */
		public String disAgreeRefundGoods() throws Exception{
			
			if(!validateFactory.isRequired(exchange.getSeller_state())){
				this.addFieldError("exchange.seller_state","请选择处理意见！");
				return exView();
			}
			
			if(!validateFactory.isRequired(exchange.getSeller_reason())){
				this.addFieldError("exchange.seller_reason","请输入拒接理由！");
				return exView();
			}
			
			String goods_id_str = exchange.getDetail_id_str();
			if(goods_id_str !=null && !goods_id_str.equals("")){
				String goods_ids[] = goods_id_str.replace(" ", "").split(",");
				Orderdetail orderdetail = new Orderdetail();
				for(int i=0;i<goods_ids.length;i++){
					orderdetail = this.orderdetailService.get(goods_ids[i]);

						if(orderdetail.getApply_num() !=null && orderdetail.getFinal_apply_num()!=null){
							Integer apply_num = Integer.valueOf(orderdetail.getApply_num());
							Integer final_apply_num = Integer.valueOf(orderdetail.getFinal_apply_num());
							final_apply_num = final_apply_num - apply_num;
							orderdetail = this.orderdetailService.get(goods_ids[i]);
							orderdetail.setFinal_apply_num(String.valueOf(final_apply_num));
							this.orderdetailService.update(orderdetail);
						}
						
				}
				
			}
			refundappService.updateOrderDetailState(goods_id_str, "2");
			exchange.setSeller_state(exchange.getSeller_state());
			exchange.setSeller_reason(exchange.getSeller_reason());
			SimpleDateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");// 设置日期格式
			exchange.setSeller_date(df.format(new Date()));
			exchange.setRefund_state("2");//退款失败
			this.exchangeService.update(exchange);//更新完成执行邮件发送
			refundappService.updateOrderDetailState(exchange.getDetail_id_str(), "8");
			Memberuser buyMemberuser= this.memberuserService.get(exchange.getBuy_user_id());
			String email=buyMemberuser.getEmail();
			if(!validateFactory.isRequired(email)){
				String title="商家拒绝换货申请",content="商家拒绝订单号："+exchange.getOrder_id()+"换货申请。理由："+exchange.getSeller_reason();
				sendEmail(title,content,email);
			}
			//拒接邮件模板
			//mestipByBuyer("15", exchange.getOrder_id());
			this.addActionMessage("处理换货申请成功！");
			getResponse().sendRedirect("/admin_Exchange_exGoodsList.action");
			return NONE;
		
		}
		/**同意退款操作
		 *  
		 * @return
		 * @throws Exception
		 */
		public String agreeRefundGoods() throws Exception{
					String goods_id_str = exchange.getDetail_id_str();
					refundappService.updateOrderDetailState(goods_id_str, "3");
					exchange.setRefund_state("3");
					SimpleDateFormat df = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");// 设置日期格式
					exchange.setSeller_date(df.format(new Date()));
					this.exchangeService.update(exchange);//退款成功后执行邮件发送
					// 同意 消息提醒
					//mestipByBuyer("12", order_id);
					Memberuser buyMemberuser= this.memberuserService.get(exchange.getBuy_user_id());
					String email=buyMemberuser.getEmail();
					if(!validateFactory.isRequired(email)){
						String title="商家同意换货申请",content="商家同意订单号："+exchange.getOrder_id()+"换货申请。";
						sendEmail(title,content,email);
					}
					// 插入订单异动记录
					this.ordertransService.inserOrderTran(order_id, session_cust_id, 
							session_user_id, CommparaFuc.getReason("1", "同意换货!"), "5", session_user_name);
	                String message = "";
					this.addActionMessage("同意换货申请!");
				
					getResponse().sendRedirect("/admin_Exchange_exGoodsList.action");
					return NONE;
		}
		public void sendEmail(String title,String content, String email){
			new MailInter().sendBatchMail(title, content, email);
		}
		/**
		 * @Method Description :确认收货
		 * @author : HZX
		 * @date : Jan 15, 2015 3:15:29 PM
		 */
		public String confirmGoods() throws Exception{
			exchange=this.exchangeService.get(exchange.getTrade_id());
			exchange.setRefund_state("6");
			exchangeService.update(exchange);
			this.addActionMessage("已确认收货,请尽快发货!");
			getResponse().sendRedirect("/admin_Exchange_exGoodsList.action");
			return NONE;
		}
		
		/**
		 * @Method Description :会员确认收货
		 * @author : HZX
		 * @date : Jan 15, 2015 3:15:29 PM
		 */
		public String mConfirmGoods() throws Exception{
			exchange=this.exchangeService.get(exchange.getTrade_id());
			exchange.setRefund_state("1");
			exchangeService.update(exchange);
			this.addActionMessage("换货成功!");
			//改变详细订单状态
			refundappService.updateOrderDetailState(exchange.getDetail_id_str(), "9");
			getResponse().sendRedirect("/bmall_Exchange_buylist.action");
			return NONE;
		}
		
		public String nConfirmGoods() throws Exception{
			exchange=this.exchangeService.get(exchange.getTrade_id());
			exchange.setRefund_state("8");
			SimpleDateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");// 设置日期格式
			exchange.setSeller_date(df.format(new Date()));
			exchangeService.update(exchange);
			this.addActionMessage("已拒绝收货!");
			getResponse().sendRedirect("/bmall_Exchange_buylist.action");
			return NONE;
		}
		
		/**
		 * 
		 * @return
		 * @throws Exception
		 */
		public String cancelRefund() throws Exception {
				if (order_id != null) {
					// 获取订单信息
					exchange = new Exchange();
					exchange = this.exchangeService.get(order_id);
					if (exchange!=null&&exchange.getRefund_state().equals("0")) {
						//退款关闭
						exchange.setRefund_state("5");
						exchange.setBuy_reason(cancel_trade_id);
						exchange.setInfo_date(DateUtil.getCurrentTime());
						//更新订单详情里面的商品状态
						String goods_id_str = exchange.getDetail_id_str();
						if(goods_id_str !=null && !goods_id_str.equals("")){
							String goods_ids[] = goods_id_str.replace(" ", "").split(",");
							Orderdetail orderdetail = new Orderdetail();
							for(int i=0;i<goods_ids.length;i++){
								orderdetail = this.orderdetailService.get(goods_ids[i]);

									if(orderdetail.getApply_num() !=null && orderdetail.getFinal_apply_num()!=null){
										Integer apply_num = Integer.valueOf(orderdetail.getApply_num());
										Integer final_apply_num = Integer.valueOf(orderdetail.getFinal_apply_num());
										final_apply_num = final_apply_num - apply_num;
										orderdetail = this.orderdetailService.get(goods_ids[i]);
										orderdetail.setFinal_apply_num(String.valueOf(final_apply_num));
										this.orderdetailService.update(orderdetail);
									}
									
							}
							
						}
						refundappService.updateOrderDetailState(exchange.getDetail_id_str(),"2");
						this.exchangeService.update(exchange);
						this.addActionMessage("已撤销申请！");
						//改变详细订单状态
						refundappService.updateOrderDetailState(exchange.getDetail_id_str(), "8");
					}
				}
				getResponse().sendRedirect("/bmall_Exchange_buylist.action");
				return NONE;
		}
		/**
		 * 
		 * @return
		 * @throws Exception
		 */
		public String agreeReturnGoods() throws Exception{
			exchange.setSeller_state(exchange.getSeller_state());
			exchangeService.update(exchange);
			Map addressMap = new HashMap();
			addressMap.put("cust_id", this.session_cust_id);
			buyeraddrList =  this.buyeraddrService.getList(addressMap);
			buyeraddrList = ToolsFuc.replaceList(buyeraddrList, "");
			return goUrl("sendReturnAddress");
		}
		/**
		 * 
		 * @return
		 * @throws Exception
		 */
		public String goOver() throws Exception{
			exchange.setRefund_state(exchange.getRefund_state());
			String state=exchange.getRefund_state();
			if("a".equals(state)){
				state="9";
				exchange.setDeny_num((Integer.parseInt(exchange.getDeny_num())+1)+"");
			}else if("9".equals(state)) {
				state="8";
			}else if("b".equals(state)) {
				state="9";
				exchange.setDeny_num((Integer.parseInt(exchange.getDeny_num())+1)+"");
			}
			exchange.setSeller_reason(exchange_seller_reason);
			exchangeService.update(exchange);
			this.addActionMessage("已处理！");
			//改变详细订单状态
			refundappService.updateOrderDetailState(exchange.getDetail_id_str(), state);
			getResponse().sendRedirect("/admin_Exchange_exGoodsList.action");
			return NONE;
		}
		
		/**
		 * 
		 * @return
		 * @throws Exception
		 */
		public String disAgreeReturnGoods() throws Exception{
			exchange.setSeller_state(exchange.getSeller_state());
			exchange.setRefund_state("2");
			exchange.setDeny_num((Integer.parseInt(exchange.getDeny_num())+1)+"");
			exchangeService.update(exchange);

			String goods_id_str = exchange.getDetail_id_str();
			if(goods_id_str !=null && !goods_id_str.equals("")){
				String goods_ids[] = goods_id_str.replace(" ", "").split(",");
				Orderdetail orderdetail = new Orderdetail();
				for(int i=0;i<goods_ids.length;i++){
					orderdetail = this.orderdetailService.get(goods_ids[i]);

						if(orderdetail.getApply_num() !=null && orderdetail.getFinal_apply_num()!=null){
							Integer apply_num = Integer.valueOf(orderdetail.getApply_num());
							Integer final_apply_num = Integer.valueOf(orderdetail.getFinal_apply_num());
							final_apply_num = final_apply_num - apply_num;
							orderdetail = this.orderdetailService.get(goods_ids[i]);
							orderdetail.setFinal_apply_num(String.valueOf(final_apply_num));
							this.orderdetailService.update(orderdetail);
						}
						
				}
				
			}
			Memberuser buyMemberuser= this.memberuserService.get(exchange.getBuy_user_id());
			String email=buyMemberuser.getEmail();
			if(!validateFactory.isRequired(email)){
				String title="商家拒绝换货申请",content="商家拒绝订单号："+exchange.getOrder_id()+"换货申请。理由："+exchange.getSeller_reason();
				sendEmail(title,content,email);
			}
			//改变详细订单状态
			refundappService.updateOrderDetailState(exchange.getDetail_id_str(), "8");
			this.addActionMessage("已拒绝！");
			getResponse().sendRedirect("/admin_Exchange_exGoodsList.action");
			return NONE;
			
		}
		
		/**发送地址给申请换货的会员
		 * 
		 * @return
		 * @throws Exception
		 */
		public String sendAddressToMember() throws Exception{
			if(!validateFactory.isRequired(exchange_id) && !validateFactory.isRequired(addr_id)){
				buyeraddr = this.buyeraddrService.get(addr_id);
				exchange = this.exchangeService.get(exchange_id);
				if(buyeraddr !=null && exchange != null){
					exchange.setArea_attr(buyeraddr.getArea_attr());
					exchange.setSell_address(buyeraddr.getAddress());
					exchange.setConsignee(buyeraddr.getConsignee());
					exchange.setMobile(buyeraddr.getCell_phone());
					SimpleDateFormat df = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");// 设置日期格式
					exchange.setSeller_date(df.format(new Date()));
					exchange.setRefund_state("3");//等待会员发货
					exchangeService.update(exchange);
				}
				this.addActionMessage("发送换货地址成功，等待收货");
				Memberuser buyMemberuser= this.memberuserService.get(exchange.getBuy_user_id());
				String email=buyMemberuser.getEmail();
				if(!validateFactory.isRequired(email)){
					String title="商家同意换货申请",content="商家同意订单号："+exchange.getOrder_id()+"换货申请。";
					sendEmail(title,content,email);
				}
			}else{
				this.addActionMessage("发送换货地址失败，请重新处理");
			}
			getResponse().sendRedirect("/admin_Exchange_exGoodsList.action");
			return NONE;

		}
		
		/**
		 * @author : HXK
		 * @param :mes_id：消息提醒模版
		 *            order_id：订单ID
		 * @date Mar 11, 2014 2:35:04 PM
		 * @Method Description :订单提醒-发送给会员
		 */
		public void mestipByBuyer(String mes_id, String order_id)
				throws UnsupportedEncodingException {
			if(order_id!=null&&!"".equals(order_id)){
				Goodsorder gorder = new Goodsorder();
				gorder = goodsorderService.get(order_id);
				if (gorder != null) {
					MessageAltFuc mesalt = new MessageAltFuc();
					if(gorder.getBuy_cust_id()!=null&&!"".equals(gorder.getBuy_cust_id())){
						mesalt.messageAutoSend(mes_id, gorder.getBuy_cust_id(), gorder);
					}
				}
			}
		}
		
		/**
		 * 方法描述：获取订单状态参数
		 * 
		 * @return
		 * @throws Exception
		 */
		@SuppressWarnings("unchecked")
		public void gOrderCommparaState(String com_value) {
			Map orderMap = new HashMap();
			orderMap.put("para_code", com_value);
			commparaList = commparaService.getList(orderMap);
		}
	/**
	 * @return the ExchangeList
	 */
	public List getExchangeList() {
		return exchangeList;
	}
	/**
	 * @param exchangeList
	 *  the ExchangeList to set
	 */
	public void setExchangeList(List exchangeList) {
		this.exchangeList = exchangeList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(exchange == null){
			exchange = new Exchange();
		}
		String id = this.exchange.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			exchange = this.exchangeService.get(id);
		}
	}
	/**
	 * @return the exchange
	 */
	public Exchange getExchange() {
		return exchange;
	}
	/**
	 * @param Exchange
	 *            the exchange to set
	 */
	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}

	public String getExchange_seller_reason() {
		return exchange_seller_reason;
	}

	public void setExchange_seller_reason(String exchange_seller_reason) {
		this.exchange_seller_reason = exchange_seller_reason;
	}
	
	
	
	
}

