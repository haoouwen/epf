/*
  
 
 * Package:com.rbt.action
 * FileName: GoodsorderAction.java 
 */
package com.rbt.webappaction;

import java.io.IOException;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rbt.common.Constants;
import com.rbt.common.util.DateUtil;
import com.rbt.common.util.JsonUtil;
import com.rbt.common.util.PropertiesUtil;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.function.CommparaFuc;
import com.rbt.function.CouponFuc;
import com.rbt.function.LogisticsFuc;
import com.rbt.function.MessageAltFuc;
import com.rbt.function.NumberFormateFuc;
import com.rbt.function.PaymentFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.function.EmsFuc;
import com.rbt.model.Cancelorder;
import com.rbt.model.Cancleordertrans;
import com.rbt.model.Comsumer;
import com.rbt.model.Coupon;
import com.rbt.model.Fundhistory;
import com.rbt.model.Goodsorder;
import com.rbt.model.Interhistory;
import com.rbt.model.Invoice;
import com.rbt.model.Member;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.model.Memberuser;
import com.rbt.model.Orderinvoice;
import com.rbt.model.Ordertrans;
import com.rbt.model.Payment;
import com.rbt.model.Printstyle;
import com.rbt.model.Redpacket;
import com.rbt.model.Redsumer;
import com.rbt.model.Sendbox;
import com.rbt.model.Sendmode;
import com.rbt.model.Shopconfig;
import com.rbt.pay.alipay.config.AlipayConfig;
import com.rbt.pay.alipay.util.AlipaySubmit;
import com.rbt.service.ICancelorderService;
import com.rbt.service.ICancleordertransService;
import com.rbt.service.IComboService;
import com.rbt.service.ICommparaService;
import com.rbt.service.IComsumerService;
import com.rbt.service.ICouponService;
import com.rbt.service.IDirectorderdetailService;
import com.rbt.service.IFreegoodsService;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IInterhistoryService;
import com.rbt.service.IInvoiceService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberinterService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.IOrderinvoiceService;
import com.rbt.service.IOrdertransService;
import com.rbt.service.IPaymentService;
import com.rbt.service.IPrintstyleService;
import com.rbt.service.IRedpacketService;
import com.rbt.service.IRedsumerService;
import com.rbt.service.ISendboxService;
import com.rbt.service.ISendmodeService;
import com.rbt.service.IShopconfigService;
import com.rbt.service.ISysfundService;
import com.yeepay.DigestUtil;
import com.yeepay.PaymentForOnlineService;
import com.yeepay.RefundResult;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 商品订单action类
 * @author 创建人 HXK
 * @date 创建日期 Fri Feb 01 16:00:36 CST 2014
 */
@Controller
public class WebAppgoodsorderAction extends WebAppbaseAction implements Preparable {

	private static final long serialVersionUID = 1L;

	/** *****************实体层*************** */
	private Goodsorder goodsorder;
	public Member member;
	public Memberuser memberuser;
	public Memberuser memberuser_seller;
	public Ordertrans ordertrans;
	public Printstyle printstyle;
	public Shopconfig shopconfig;
	public Cancleordertrans cancleordertrans;
	public Cancelorder cancelorder;
	public Sendmode sendmode;
	public Sendbox sendbox;
	public Orderinvoice orderinvoice;

	/** *****************业务层接口*************** */
	@Autowired
	private IGoodsorderService goodsorderService;
	@Autowired
	private IOrderdetailService orderdetailService;
	@Autowired
	private ICommparaService commparaService;
	@Autowired
	private IPaymentService paymentService;
	@Autowired
	private ISendmodeService sendmodeService;
	@Autowired
	private IMemberService memberService;
	@Autowired
	private IMemberuserService memberuserService;
	@Autowired
	private IOrdertransService ordertransService;
	@Autowired
	private IPrintstyleService printstyleService;
	@Autowired
	private IShopconfigService shopconfigService;
	@Autowired
	private IDirectorderdetailService directorderdetailService;
	@Autowired
	private IMemberinterService memberinterService;
	@Autowired
	private IInterhistoryService interhistoryService;
	@Autowired
	private IInvoiceService invoiceService;
	@Autowired
	private IComboService comboService;
	@Autowired
	private ICancleordertransService cancleordertransService;
	@Autowired
	private ICancelorderService cancelorderService;
	@Autowired
	private ISysfundService sysfundService;
	@Autowired
	private IMemberfundService memberfundService;
	@Autowired
	private IFundhistoryService fundhistoryService;
	@Autowired
	private ISendboxService sendboxService;
	@Autowired
	private IComsumerService comsumerService;
	@Autowired
	private IRedsumerService redsumerService;
	@Autowired
	private IFreegoodsService freegoodsService;
	@Autowired
	private ICouponService couponService;
	@Autowired
	private IRedpacketService redpacketService;
	@Autowired
	private IOrderinvoiceService orderinvoiceService;

	/** *******************集合***************** */
	public List goodsorderList;// 商品订单信息集合
	public List orderdetaiList;// 商品订单信息集合
	public List commparaList;// 参数
	public List commstateparaList;// 订单状态参数
	public List paymentList;// 支付
	public List sendmodeList;// 配送方式
	public List memberuserList;// 会员
	public List ordertransList;// 订单异动
	public List printstyleList;// 打印样式
	public List shopconfigList;// 店铺配置
	public List detailList;// 详细
	public List directDetaiList;// 预售详细
	public List invoiceList; // 票据打印
	public List cancelorderList;//取消订单异动
	public List orderdetailList;
	public List kjtcommparaList;
	public List freegoodsList;

	/** *******************字段***************** */
	public String order_area;// 订单地区
	public String print_content;// 打印页面显示的内容
	private String print_temp_gouwu = "gouwu";// 购物单模版代码
	private String print_temp_fahuo = "fahuo";// 发货模版代码
	private String print_temp_ems = "ems_self";//
	private String send_mode_enname = "'ems_self','sf_self'";
	private String print_shopname = "${shopname}";// 店铺名称
	private String print_shopurl = "${shopurl}";// 店铺链接地址
	private String print_kefu = "${kefu}";// 客服
	private String print_phone = "${phone}";// 电话
	private String print_order_num = "${order_num}";// 订单号
	private String print_order_date = "${order_date}";// 下单时间
	private String print_goods_allprice = "${goods_allprice}";// 商品总金额
	private String print_send_price = "${send_price}";// 配送费用
	private String print_insured_price = "${insured_price}";// 保价费用
	private String print_pay_charge = "${pay_charge}";// 支付手续费
	private String print_note = "${buyer_message}";// 买家备注
	private String print_total_all_money = "${total_all_money}";// 订单总金额
	private String print_Powered = "${Powered}";// 技术支持
	private String print_send_distribution = "${send_distribution}";// 配送
	private String print_send_consignee = "${send_consignee}";// 收货人
	private String print_send_telphone = "${send_telphone}";// 收货人电话
	private String print_send_mobile = "${send_mobile}";// 手机
	private String print_send_address = "${send_address}";// 收获地址
	private String print_send_zipcode = "${send_zipcode}";// 邮编
	public String order_id_s = "";// 搜索订单号
	public String order_state_s = "";// 搜索订单状态
	public String starttime_s = "";// 搜索订单开始时间
	public String endtime_s = "";// 搜索订单结束时间
	public String goods_order_id = "";// 商品订单编号
	public String goods_order_state = "";// 商品订单状态
	public String order_send_mode;// 配送物流
	public String order_send_num;// 物流单号
	public String order_mem_mark;// 商家备注
	public String str_today;// 今日订单
	public String str_ydate;// 昨日订单
	public String pay_today;// 今日已付款
	public String y_pay_day;// 昨日已付款
	public String logistics_query;// 快递查询结果
	public String kuai_number;// 快递号
	public String kuai_company;// 快递公司
	public String kuai_company_code;// 快递公司代码
	public String seller_order_cancel;// 卖家取消理由
	public String buy_order_cancel;// 买家取消理由
	public String order_type_s;// 订单类型
	public String mark_id;// 消费码
	public String is_virtual;// 是否虚拟商品
	public String order_state;// 订单状态
	public Integer order_detai_count = 0;// 订单详细数
	public int cfg_Refund_deny_num = Integer.parseInt(SysconfigFuc
			.getSysValue("cfg_Refund_deny_num"));// 卖家拒绝退款最多次数
	public String newship_free;
	public String oldship_free;
	public String ship_oid;
	public String datenow;
	public String threedate;
	public String starttime;
	public String sell_cust_s;// 搜索卖家
	public String buy_cust_s;// 搜索买家
	public String flag_invoice_id;// 标记票据打印编号
	public String order_type;
	public String combo_name = "-";
	public String iname;
	public String is_import;
	public boolean is_page = false;
	public String shopconfig_area_attr;
	public String re_o;
	public boolean is_buyer = false;
	public String ogoods_name;
	public HashMap mapvalue;
	public String paymethod;
	public String ordertb;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
	private String timeString = df.format(new Date());
	public String sendModeState;// 发货方式 1：普通发货 2：EMS热敏发货
	public String jsontotal;
	public String goods_order_num;// 订单的数量
	public String goods_order_ids;// 订单串
	public String billnos;// EMS运单串
	public String businessType;// EMS自助系统快递业务类型：1标准快递 4经济快递
	//临时存储确认出口订单信息
	public String outGoodsInfo="";
	//扫描订单条形码
    public String hidden_goods_order_codenumber="";
    public String cancel_state;
    public String cancel_reject_reason;
    public String cancel_state_s;
    public String orderweight;
    public String is_vip_s;
    public String is_sfexpress_s;
    public String print_template_code;
    public String order_cancel_reason;
    public String order_cancel_date;
    public String refund_code; 
    public String refund_message;
    
    //订单商品数量
    public Integer waitPayNunm;//待付款订单数量
	public Integer waitDeliveryNum;//待发货订单数量
	public Integer waitReceiptNum;//待收货订单数量
	public Integer waitEvaluationNum;//待评价数量

	public String date_s;//时间标识
	public String lessdate;//30日内的订单
	public String greatdate;//30天以前的订单
	public String refund_s;//退款订单标识
	public String receipt_flag;//确认收货标识
	public String send_cust_name_member;
	public String send_cust_id_member;
	public String is_sea;
	public String state_text="全部订单";
	
	
	/**
	 * @Method Description :触屏版网站全部订单
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
	 * @return
	 * @throws Exception
	 */
	public String webappAllOrder() throws Exception {
		
		if(this.session_cust_id ==null || this.session_cust_id.equals("")){
			return goUrl("mbLogin");
		}
		Map map = new HashMap();
		map.put("para_code", "buy_cancel_order");
		List list = commparaService.getList(map);
		jsontotal=JsonUtil.list2json(list);
		webappcommonList();
		//待付款
		Map waitPayMap=new HashMap();
		waitPayMap.put("order_state", "1");
		waitPayMap.put("order_type","1,2");
		waitPayMap.put("buy_cust_id",session_cust_id);
		waitPayNunm = this.goodsorderService.getCount(waitPayMap);
		//待发货
		Map waitDeliveryMap=new HashMap();
		waitDeliveryMap.put("order_state", "2");
		waitDeliveryMap.put("order_type","1,2");
		waitDeliveryMap.put("buy_cust_id",session_cust_id);
		waitDeliveryNum = this.goodsorderService.getCount(waitDeliveryMap);
		//待收货
		Map waitReceiptMap=new HashMap();
		waitReceiptMap.put("order_state", "3");
		waitReceiptMap.put("order_type","1,2");
		waitReceiptMap.put("buy_cust_id",session_cust_id);
		waitReceiptNum = this.goodsorderService.getCount(waitReceiptMap);
		
		//待评价
		Map waitEvaluationMap = new HashMap();
		waitEvaluationMap.put("order_state", "7");
		waitEvaluationMap.put("order_type","1,2");
		waitEvaluationMap.put("buy_cust_id",session_cust_id);
		// 根据页面提交的条件找出信息总数
		waitEvaluationNum = this.goodsorderService.getCount(waitEvaluationMap);
		
		
		return goUrl("mbappAllOrder");
	}
    /**
     * 
     * @return
     * @throws Exception
     */
    public String backWaitPay() throws Exception {
		
		if(this.session_cust_id ==null || this.session_cust_id.equals("")){
			return goUrl("mbLogin");
		}
		order_state_s="1";
		webappcommonList();
		
		return goUrl("mbappAllOrder");
	}
	
	/**
	 * @Method Description :触屏版网站订单详细页
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
	 * @return
	 * @throws Exception
	 */
	public String webappOrderDetail() throws Exception{
		
		if(this.session_cust_id ==null || this.session_cust_id.equals("")){
			return goUrl("mbLogin");
		}
		//获取系统订单
		gCommparaList("order_state");
		//获取跨境通订单状态
		kjtOrderCommparaState("kjtorder_state");
		
		String order_id = this.goodsorder.getOrder_id();
		goodsorder = this.goodsorderService.get(order_id);
		orderinvoice = orderinvoiceService.getByInvoid(goodsorder.getInvoice_id());
		order_area = AreaFuc.getAreaNameByMap(goodsorder.getArea_attr());
		
		Map detailMap = new HashMap();
		detailMap.put("order_id", order_id);
		detailList = this.orderdetailService.getList(detailMap);
		
		//支付方式
		gOrderPayment();
		//物流公司
		gOrderSendmode();
		//取消订单原因
		gCancelOrderReason();
		
		//物流跟踪查询：第三方快递100
		webappOrderLogistics(goodsorder);
		//赠品信息
		getFreeGoodsInfo(goodsorder.getFg_ids());
		
		return goUrl("mbappOrderDetail");
	}
	
	/**
	 * @Method Description :触屏版网站物流订单
	 * @author :QJY
	 * @date : Jan 6, 2015 15:52:14 AM
	 * @return
	 * @throws Exception
	 */
	public String webappLogisticsOrder(){
		
		if(this.session_cust_id ==null || this.session_cust_id.equals("")){
			return goUrl("mbLogin");
		}
		
		order_state_s = "3";
		webappcommonList();
		return goUrl("mbappLogisticsOrder");
	}
	
	
	/**
	 * @Method Description :触屏版网站物流订单
	 * @author :QJY
	 * @date : Jan 6, 2015 15:52:14 AM
	 * @return
	 * @throws Exception
	 */
	public void webappcommonList() {
		Map pageMap = new HashMap();
		
		if(this.session_cust_id !=null && !"".equals(this.session_cust_id)){
			pageMap.put("buy_cust_id", this.session_cust_id);
		}
	    
		if(!ValidateUtil.isRequired(order_state_s)){
			pageMap.put("order_state", order_state_s);
		}
		
		if(!ValidateUtil.isRequired(goods_order_id)){
			pageMap.put("order_id", goods_order_id);
		}
		
		if(!ValidateUtil.isRequired(date_s)){
			if(date_s.equals("1")){
				pageMap.put("lessdate", "1");//30天内的订单
			}else if(date_s.equals("2")){
				pageMap.put("greatdate", "2");//30天以上的订单
			}
		}
		//退款
	    if(!ValidateUtil.isRequired(refund_s)){
	    	pageMap.put("order_state", "4,5,6");
	    }
		
		//订单类型：2普通订单
		pageMap.put("order_type", "1,2");
		
        //是否逻辑删除   0:是  1：否
	    pageMap.put("is_del", "1");
	    
		//是否为虚拟商品  0:是  1：否 
		pageMap.put("is_virtual", "1");
		
		// 根据页面提交的条件找出信息总数
		int count = this.goodsorderService.getCount(pageMap);
			// 分页插件
		pageMap = super.webAppPageTool(count, pageMap);
		
		getOrder(pageMap);
		
		gOrderCommparaState("order_state");
		
	}
	
	/**
	 * @Method Description :触屏版网站物流订单
	 * @author :QJY
	 * @date : Jan 6, 2015 15:52:14 AM
	 * @return
	 * @throws Exception
	 */
    public void gCancelOrderReason(){
    	Map map = new HashMap();
    	map.put("para_code", "buy_cancel_order");
    	List list = commparaService.getList(map);
    	jsontotal=JsonUtil.list2json(list);
    }
	
    /**
	 * 方法描述：获取支付方式
	 * 
	 * @return
	 * @throws Exception
	 */
	public void gOrderPayment() {
		Map orderMap = new HashMap();
		// 0：是 1：否
		orderMap.put("enabled", "0");
		paymentList = paymentService.getList(orderMap);
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
		orderMap.put("enabled", "0");
		orderMap.put("para_code", com_value);
		commparaList = commparaService.getList(orderMap);
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
    
	
	/**
	 * @author：XBY
	 * @date：Feb 13, 2014 9:18:27 AM
	 * @Method Description：公共查看物流信息
	 */
	public void webappOrderLogistics(Goodsorder goodsorder) {
	    
		Sendmode sendmode = sendmodeService.get(goodsorder.getSend_mode());
		if (sendmode != null) {
			// 获取快递公司代码
			kuai_company_code = sendmode.getEn_name().trim();
			// 获取快递公司名称
			kuai_company = sendmode.getSmode_name().trim();
		}
		kuai_number = goodsorder.getSend_num();
		if (!ValidateUtil.isRequired(kuai_number)) {
			kuai_number = kuai_number.trim();
		}
		// 查询快递信息
		logistics_query = LogisticsFuc.hundredTrace(kuai_company_code,
				kuai_number);

	}
	
	/**
	 * @author Administrator QJY
	 * @method 获取赠品信息
	 * @date 2015-09-30
	 * @throws Exception
	 */
	public void getFreeGoodsInfo(String fd_ids)throws Exception{
		Map freegoodsMap = new HashMap();
		if(fd_ids !=null && !fd_ids.equals("")){
			freegoodsMap.put("fg_ids", fd_ids);
		}else{
			freegoodsMap.put("fg_ids", "0");//
		}
		freegoodsList =  this.freegoodsService.getList(freegoodsMap);
		
	}
	
	/**
	 * @author : HXK
	 * @date : 2014 11:15:15 AM
	 * @Method Description :查找出订单对应商品的公共方法
	 */
	private void getOrder(Map pageMap) {
		Map directMap = new HashMap();
		pageMap.put("de_order", "1");
		goodsorderList = this.goodsorderService.getList(pageMap);
		// 通过解析goodsorderList找出相关的商品信息
		Map map = this.goodsorderService.replaceList(goodsorderList);
		String orderidStr = map.get("orderidStr").toString();
		// 去掉最后的逗号
		if (orderidStr.indexOf(",") > 0) {
			int len = orderidStr.lastIndexOf(",");
			orderidStr = orderidStr.substring(0, len);
		}
		Map detailMap = new HashMap();
		detailMap.put("order_id_s", orderidStr);
		detailList = this.orderdetailService.getList(detailMap);
		order_detai_count = detailList.size();

		if (order_state_s.equals("7,8")) {
			order_state_s = "7";
		}
	}
    
    
    /**
	 * @Method Description :触屏版网站取消订单操作
	 * @author :QJY
	 * @date : Jan 6, 2015 15:52:14 AM
	 * @return
	 * @throws Exception
	 */
	public String webappCancelOrder() throws Exception {
		
		if (this.session_cust_id != null && goods_order_id != null) {
			goodsorder = goodsorderService.get(goods_order_id);
			if(goodsorder.getOrder_state()!=null&&!"".equals(goodsorder.getOrder_state())){
				if(goodsorder.getOrder_state().equals("1")||goodsorder.getOrder_state().equals("2")){
					if (buy_order_cancel != null&& !buy_order_cancel.equals("--请选择取消理由--")) {
						order_state=goodsorder.getOrder_state();//主订单的状态
						SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");// 设置日期格式
						String buy_cust_id = this.session_cust_id;
						cancelorder=new Cancelorder();
						cancelorder.setBuy_reason(buy_order_cancel);
						cancelorder.setBuy_cust_id(buy_cust_id);
						cancelorder.setBuy_user_id(this.session_user_id);
						cancelorder.setBuy_date(df.format(new Date()));
						cancelorder.setOrder_id(goods_order_id);
						
						String message = "";
						String reason="您的取消申请已提交";
						if(order_state.equals("1")){//如果订单未付款时，买家可以直接取消订单
							if(goodsorder.getPay_id()!=null&&!"".equals(goodsorder.getPay_id())){
								String pay_type = this.paymentService.get(goodsorder.getPay_id()).getPay_code();
								if(pay_type.equals("alipay") || pay_type.equals("alipaywap")){
									//把请求参数打包成数组
									Map<String, String> sParaTemp = new HashMap<String, String>();
									sParaTemp.put("service", AlipayConfig.close_service);
							        sParaTemp.put("partner", AlipayConfig.partner);
							        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
									sParaTemp.put("trade_no", goodsorder.getPay_trxid());//支付宝交易
									sParaTemp.put("out_order_no", goods_order_id);//商户订单号
									
									//建立请求
									String sHtmlText = AlipaySubmit.buildRequest("", "", sParaTemp);
									
								}
							}
							
							//红包，优惠券
							CouponFuc recoupon = new CouponFuc();
							recoupon.backCouponAnd(goods_order_id);
							
							String balance_use = goodsorder.getBalance_use();
							//执行退款的时候，需要把使用部分余额付款的退回去。
							sysfundService.freezeNum(Double.parseDouble(balance_use), 1);
							//退回余额
							memberfundService.freezeNum(goodsorder.getBuy_cust_id(),Double.parseDouble(balance_use),1);

							//买家的资金异动
							Memberfund buymf=memberfundService.get(goodsorder.getBuy_cust_id());
							Fundhistory buy_fh = new Fundhistory();
							buy_fh.setBalance(Double.parseDouble(buymf.getUse_num()));
							buy_fh.setCust_id(goodsorder.getBuy_cust_id());
							buy_fh.setFund_in(Double.parseDouble(balance_use));
							buy_fh.setReason("会员收到订单号:"+goods_order_id+" 的订单退款"+balance_use+"元");
							buy_fh.setFund_out(0.0);
							buy_fh.setUser_id(this.session_user_id);
							this.fundhistoryService.insert(buy_fh);
							
							cancelorder.setOrder_state("1");//同意取消
							// 更新订单状态为 取消
							updateOrderState(goods_order_id, "0");//订单直接为取消状态 即：0
							//插入取消订单进度
							insertCancelOrderTrans(goods_order_id, reason,this.session_cust_id,this.session_user_id);
							reason="您的取消订单处理完成";
							insertCancelOrderTrans(goods_order_id, reason,"0","0");
							message="订单成功取消!";
								
						}else if (order_state.equals("2")){//如果订单付款了，买家取消订单，需进行系统审核处理
							
							cancelorder.setOrder_state("0");//处理中
							
							// 更新订单状态为 退款中
							updateOrderState(goods_order_id, "4");//将订单状态变为 退款中 即：4
							//插入取消订单进度
							insertCancelOrderTrans(goods_order_id, "您的订单取消申请成功，正在处理退款，请耐心等待。",this.session_cust_id,this.session_user_id);
							message="订单取消申请成功,等待处理退款";
						}
						cancelorder.setSell_cust_id(goodsorder.getSell_cust_id());
						cancelorderService.insert(cancelorder);
						this.addActionMessage(message);
						return webappOrderDetail();
					
					} else {
						this.addFieldError("buy_order_cancel", "请选择取消理由");
						return webappOrderDetail();
					}
				}
			}
		} 
		return webappAllOrder();

	}
    
	 /**
	 * @Method Description :触屏版网站确定收货操作
	 * @author :QJY
	 * @date : Jan 6, 2015 15:52:14 AM
	 * @return
	 * @throws Exception
	 */
	public String webappConfirmReceipt() throws Exception {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			return goUrl("webappLogin");
		}
		if(goods_order_id!=null&&!"".equals(goods_order_id)){
			goodsorder=goodsorderService.get(goods_order_id);
			//判断订单的状态是不是已发货
			if(goodsorder.getOrder_state()!=null&&goodsorder.getOrder_state().equals("3")){
				/**以上三个方法的操作必须放入service 进行事务管理，待修改*/
				//资金操作
				Payment payment = this.paymentService.get(goodsorder.getPay_id());
				if(payment.getPay_code().equals("goldpay")){
					//资金操作
					this.goodsorderService.sellerFundManage(goods_order_id,this.session_user_id);
				}else if(payment.getPay_code().equals("integral")){
					//TODO
				}else{
					this.goodsorderService.onlinepayManage(goods_order_id,this.session_user_id);
				}
				// 修改订单状态
				updateOrderState(goods_order_id, "7");
				// 赠送积分
				insertOrderInter(goods_order_id);
				//赠送优惠券
				insertCoupon(goods_order_id);
				//赠送红包
				insertRed(goods_order_id);				
				//会员升级
				memberUpgrade(goodsorder);
				//更新商品销售量
				//goodsorderService.updateGoodsSales(goods_order_id);
				
				this.addActionMessage("确认收货成功!");
				
				goods_order_id="";
				if("1".equals(receipt_flag) || "3".equals(receipt_flag)){
					
					return webappOrderDetail();
					
				}
				
				if("2".equals(receipt_flag))
				    
					return webappLogisticsOrder();
				
				}
			}
         return NONE;
	}

	
 /*************************************触屏版*****************************************/   
    
	/**
	 * 方法描述：返回新增商品订单页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		String adders = "editorder";
		return goUrl(adders);
	}

	/**
	 * 方法描述：新增商品订单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 字段验证
		if (commonCheck())
			return add();
		this.goodsorderService.insert(goodsorder);
		this.addActionMessage("新增商品订单成功！");
		this.goodsorder = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改商品订单信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String adminUpdate() throws Exception {

		if (ValidateUtil.isRequired(goodsorder.getConsignee())) {
			this.addFieldError("goodsorder.consignee", "收货人不能为空！");
		}
		if (ValidateUtil.isRequired(goodsorder.getMobile())) {
			this.addFieldError("goodsorder.mobile", "联系手机不能为空！");
		}
		if (ValidateUtil.isRequired(goodsorder.getZip_code())) {
			this.addFieldError("goodsorder.zip_code", "邮政编码不能为空！");
		}
		if (ValidateUtil.isRequired(goodsorder.getBuy_address())) {
			this.addFieldError("goodsorder.buy_address", "收货地址不能为空！");
		}
		if (ValidateUtil.isRequired(area_attr)
				|| area_attr.equals("1111111111")) {
			this.addFieldError("area_attr", "请选择收货地区！");
		} else {
			goodsorder.setArea_attr(area_attr);
		}
		String reason = "运营商修改订单";
		insertOrderTrans(goodsorder.getOrder_id(), reason, goodsorder
				.getOrder_state());
		this.goodsorderService.update(goodsorder);
		this.addActionMessage("修改商品订单成功！");
		return operatorslist();
	}

	/**
	 * 方法描述：修改订单的时候，插入订单异动表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void insertOrderTrans(String order_id, String reason,
			String order_state) {
		ordertrans = new Ordertrans();
		ordertransService.inserOrderTran(order_id, session_cust_id, 
				session_user_id, reason, order_state, session_user_name);
	}

	/**
	 * 方法描述：修改商品订单信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 字段验证
		if (commonCheck())
			return view();
		this.goodsorderService.update(goodsorder);
		this.addActionMessage("修改商品订单成功！");
		return operatorslist();
	}

	
	/**@author QJY
	 * 修改运单号
	 * 2015-01-08
	 */
	public String updateBillnos() throws Exception{
		if(!ValidateUtil.isRequired(billnos)){
			String id = this.goodsorder.getOrder_id();
			goodsorder = this.goodsorderService.get(id);
			goodsorder.setSend_num(billnos);
			this.goodsorderService.update(goodsorder);
		}
		this.addActionMessage("修改商品订单运单号成功！");
		return view();
	}
	
	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 5:00:04 PM
	 * @Method Description：公共数据验证
	 */
	public boolean commonCheck() {
		super.commonValidateField(goodsorder);
		if (super.ifvalidatepass) {
			return true;
		}
		return false;
	}

	/**
	 * 方法描述：删除商品订单信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.goodsorderService.delete(chb_id);
		if (flag) {
			this.addActionMessage("删除商品订单成功");
		} else {
			this.addActionMessage("删除商品订单失败");
		}

		return operatorslist();
	}
	
	/**
	 * 运营商订单列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String operatorslist() throws Exception {
		is_vip_s="0";
		commonList("1", "order_state", "0");
		invoiceList = invoiceService.getList(new HashMap());
		return goUrl("operatorsorderindex");
	}
	
	/**
	 * @Method Description :内部订单
	 * @author : HZX
	 * @date : Feb 9, 2015 1:11:03 PM
	 */
	public String neibuList() throws Exception {
		is_vip_s="1";
		commonList("1", "order_state", "0");
		invoiceList = invoiceService.getList(new HashMap());
		return goUrl("neibuIndex");
	}
	
	/**
	 * @Method Description :顺丰订单
	 * @author : HZX
	 * @date : Sep 10, 2014 4:37:10 PM
	 */
	@SuppressWarnings("unchecked")
	public String sfList() throws Exception {
		order_state_s = "2";
		is_sfexpress_s="1";
		commonList("1", "order_state", "3");
		invoiceList = invoiceService.getList(new HashMap());
		jsontotal = JsonUtil.list2json(goodsorderList);
		return goUrl("sforderindex");
	}

	/**
	 * 方法描述：获取虚拟商品订单 0:虚拟 1:普通 2:积分 3:秒杀 4:套餐 5:团购
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String virtuallist() throws Exception {
		commonList("0", "virtual_order_state", "");
		return goUrl("virtualorder");
	}

	/**
	 * 方法描述：获取虚拟商品订单 0:虚拟 1:普通 2:积分 3:秒杀 4:套餐 5:团购
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String pointsgoodslist() throws Exception {
		commonList("", "order_state", "1");
		return goUrl("pointsgoodslist");
	}

	/**
	 * 付邮订单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String postageList() throws Exception {
		commonPostageList("1");
		Map map = new HashMap();
		map.put("para_code", "buy_cancel_order");
		List list = commparaService.getList(map);
		jsontotal=JsonUtil.list2json(list);
		return goUrl("postagelist");
	}

	/**
	 * 付邮订单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String postagegoodsList() throws Exception {
		commonPostageList("0");
		return goUrl("postagegoodslist");
	}

	public void commonPostageList(String type) {
		Map pageMap = new HashMap();
		// 搜索订单状态
		if (order_state_s != null && !"".equals(order_state_s)
				&& !"999".equals(order_state_s)) {
			if (order_state_s.equals("7")) {
				order_state_s = "7,8";
			}
			pageMap.put("order_state", order_state_s);
		}
		// 搜索订单号
		if (order_id_s != null && !"".equals(order_id_s)) {
			pageMap.put("order_id", order_id_s.trim());
		}
		// 搜索订单开始时间
		if (starttime_s != null && !"".equals(starttime_s)) {
			pageMap.put("starttime", starttime_s);
			// 默认选中全部订单状态
		}
		// 搜索订单结束时间
		if (endtime_s != null && !"".equals(endtime_s)) {
			pageMap.put("endtime", endtime_s);
			// 默认选中全部订单状态
		}
		// 搜索订单类型
		pageMap.put("order_type", "7");
		// 搜索卖家
		if (!ValidateUtil.isRequired(sell_cust_s)) {
			pageMap.put("shop_name", sell_cust_s.trim());
		}
		// 搜索今日订单
		if (!ValidateUtil.isRequired(str_today)) {
			pageMap.put("today", str_today);
		}
		// 搜索今日已付款
		if (!ValidateUtil.isRequired(pay_today)) {
			pageMap.put("paytoday", pay_today);
			pageMap.put("order_state", "2,3,7,8");
		}
		// 搜索昨日订单
		if (!ValidateUtil.isRequired(str_ydate)) {
			pageMap.put("ydate", str_ydate);
		}

		// 搜索昨日已付款
		if (!ValidateUtil.isRequired(y_pay_day)) {
			pageMap.put("ypaydate", y_pay_day);
			pageMap.put("order_state", "2,3,7,8");
		}
		
		//是否删除订单
		pageMap.put("is_del", "1");

		// 搜索买家
		if (!ValidateUtil.isRequired(buy_cust_s)) {
			Map map = new HashMap();
			map.put("user_name", buy_cust_s.trim());
			List list = memberuserService.getList(map);
			if (list!=null&&list.size() > 0) {
				Map mapList = (HashMap) list.get(0);
				Object str = mapList.get("cust_id");
				if (str != null && !ValidateUtil.isRequired(str.toString())) {
					pageMap.put("buy_cust_id", str.toString());
				}
			}
		}
		if (type.equals("0")) {
			pageMap.put("sell_cust_id", this.session_cust_id); // 卖家ID
			pageMap.put("buy_cust_id_no", this.session_cust_id);// 买家ID不等于自己
		} else if (type.equals("1"))
			pageMap.put("buy_cust_id", this.session_cust_id); // 买家ID
		// 根据页面提交的条件找出信息总数
		int count = this.goodsorderService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		getOrder(pageMap);
		gOrderCommparaState("order_state");
	}

	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 5:17:46 PM
	 * @Method Description：公共查询
	 */

	public void commonList(String is_virtual, String state, String type) {
		Map pageMap = new HashMap();
		// 搜索订单状态
		if (order_state_s != null && !"".equals(order_state_s)
				&& !"999".equals(order_state_s)) {
			if (order_state_s.length() > 1
					&& ",".endsWith(order_state_s.substring(order_state_s
							.length() - 1)))
				order_state_s = order_state_s.substring(0, order_state_s
						.length() - 1);
			pageMap.put("order_state", order_state_s);
		}
		// 搜索商品名称
		if (ogoods_name != null && !"".equals(ogoods_name)) {
			pageMap.put("ogoods_name", ogoods_name);
		}
		if (is_vip_s != null && !"".equals(is_vip_s)) {
			pageMap.put("is_vip", is_vip_s);
		}
		//是否为顺丰热敏打印订单
		if (is_sfexpress_s != null && !"".equals(is_sfexpress_s)) {
			pageMap.put("is_sfexpress", is_sfexpress_s);
		}
		// 搜索订单号
		if (order_id_s != null && !"".equals(order_id_s)) {
			pageMap.put("order_id", order_id_s.trim());
		}
		if (chb_id != null && !"".equals(chb_id)) {
			pageMap.put("goodsorder_id", chb_id.trim());
		}
		
		// 搜索订单开始时间
		if (starttime_s != null && !"".equals(starttime_s)) {
			pageMap.put("starttime", starttime_s);
			// 默认选中全部订单状态
		}
		// 搜索订单结束时间
		if (endtime_s != null && !"".equals(endtime_s)) {
			pageMap.put("endtime", endtime_s);
			// 默认选中全部订单状态
		}
		// 搜索订单类型
		if (order_type_s != null && !"".equals(order_type_s)
				&& !"999".equals(order_type_s)) {
			pageMap.put("order_type", order_type_s);
		} else if (is_virtual.equals("")) {
			pageMap.put("order_type_no", "2");
			is_virtual = "1";
		} else if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("order_type_no", "2,7");
		}
		// 搜索卖家
		if (!ValidateUtil.isRequired(sell_cust_s)) {
			pageMap.put("shop_name", sell_cust_s.trim());
		}
		// 搜索今日订单
		if (!ValidateUtil.isRequired(str_today)) {
			pageMap.put("today", str_today);
		}
		// 搜索今日已付款
		if (!ValidateUtil.isRequired(pay_today)) {
			pageMap.put("paytoday", pay_today);
			pageMap.put("order_state", "2,3,7,8");
		}
		// 搜索昨日订单
		if (!ValidateUtil.isRequired(str_ydate)) {
			pageMap.put("ydate", str_ydate);
		}

		// 搜索昨日已付款
		if (!ValidateUtil.isRequired(y_pay_day)) {
			pageMap.put("ypaydate", y_pay_day);
			pageMap.put("order_state", "2,3,7,8");
		}
		if (is_buyer) {
			if (!validateFactory.isRequired(re_o)) {
				pageMap.put("is_del", "0");
			} else {
				pageMap.put("is_del", "1");
			}

		}
		// 搜索买家
		if (!ValidateUtil.isRequired(buy_cust_s)) {
			Map map = new HashMap();
			map.put("user_name", buy_cust_s.trim());
			List list = memberuserService.getList(map);
			if (list!=null&&list.size() > 0) {
				Map mapList = (HashMap) list.get(0);
				Object str = mapList.get("cust_id");
				if (str != null && !ValidateUtil.isRequired(str.toString())) {
					pageMap.put("buy_cust_id", str.toString());
				}
			}
		}

		// 是否为虚拟商品 0:是 1：否 商品是否虚拟
		if ("0".equals(is_virtual)) {
			pageMap.put("is_virtual", is_virtual);
		} else {
			pageMap.put("is_virtual", is_virtual);
		}
		if (type.equals("0")) {// 找出卖家是运营商的订单，运营商默认会员ID 为0
			pageMap.put("sell_cust_id", this.session_cust_id); // 卖家ID
			pageMap.put("buy_cust_id_no", this.session_cust_id);// 买家ID不等于自己
		} else if (type.equals("1")) {
			pageMap.put("buy_cust_id", this.session_cust_id); // 买家ID
		} else if (type.equals("2")) {// 找出卖家不是运营商的订单
			pageMap.put("sell_cust_id_no", this.session_cust_id);
		}
		// 根据页面提交的条件找出信息总数
		int count = this.goodsorderService.getCount(pageMap);
		if (!is_page) {
			// 分页插件
			pageMap = super.pageTool(count, pageMap);
		}
		getOrder(pageMap);
		gOrderCommparaState(state);
	}

	/**
	 * 方法描述：虚拟商品详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String virtualview() throws Exception {
		String id = this.goodsorder.getOrder_id();
		if (id == null || id.equals("")) {
			goodsorder = new Goodsorder();
		} else {
			goodsorder = this.goodsorderService.get(id);
			// 获取订单商品详细信息
			gOrderDetaiInfo(id, goodsorder.getOrder_type());
			memberuser = memberuserService.getPKByCustID(goodsorder
					.getBuy_cust_id());
		}
		gOrderPayment();
		gOrderCommparaState("virtual_order_state");
		gordertransInfo(goodsorder.getOrder_id());
		return goUrl("virtualdetail");
	}

	public String gdate() {
		// 获取当前时间
		String cur_date_str = "";
		SimpleDateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cur_date_str = format.format(cal.getTime());
		return cur_date_str;
	}

	/**
	 * 方法描述：虚拟商品消费
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String adminVirtualUpdate() throws Exception {
		if (commonVirtualUpdate())
			return virtualview();
		return virtuallist();
	}

	/**
	 * 方法描述：卖家虚拟商品消费
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String sellerVirtualUpdate() throws Exception {
		if (commonVirtualUpdate())
			return sellerVirtualView();
		return sellerVirtualList();
	}

	/**
	 * @author：XBY
	 * @date：Feb 13, 2014 9:25:48 AM
	 * @Method Description：
	 */
	public boolean commonVirtualUpdate() throws Exception {
		if (this.session_cust_id != null) {
			if (ValidateUtil.isRequired(mark_id)) {
				this.addFieldError("mark_id", "消费码不能为空！");
				return true;
			} else {
				// 获取判断消费码是否有效
				Map markMap = new HashMap();
				markMap.put("order_state", "2");
				markMap.put("is_virtual", "0");
				markMap.put("order_id", goodsorder.getOrder_id());
				markMap.put("mark_id", mark_id);
				List gList = new ArrayList();
				gList = goodsorderService.getList(markMap);
				if (gList == null || gList.size() == 0) {
					this.addFieldError("mark_id", "无效的消费码！");
					return true;
				}
				goodsorder.setSure_time(gdate());
				String reason = "消费码消费商品";
				updateOrderState(goodsorder.getOrder_id(), "7", reason);
				// 赠送积分
				insertOrderInter(goodsorder.getOrder_id());
				// 信息提醒
				MessageAltFuc mesalt = new MessageAltFuc();
				mesalt.messageAutoSend("13", goodsorder.getBuy_cust_id(),
						goodsorder);
				this.addActionMessage("消费成功！");
			}
			goodsorder.setSure_time(gdate());
			String reason = "消费码消费商品";
			updateOrderState(goodsorder.getOrder_id(), "7", reason);
			// 信息提醒
			MessageAltFuc mesalt = new MessageAltFuc();
			mesalt.messageAutoSend("13", goodsorder.getBuy_cust_id(),
					goodsorder);
			this.addActionMessage("消费成功！");
		}
		return false;
	}

	/**
	 * 方法描述：卖家订单列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String sellerorderlist() throws Exception {
		commonList("1", "order_state", "0");
		return goUrl("sellerorderindex");
	}

	/**
	 * 方法描述：卖家虚拟订单列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String sellerVirtualList() throws Exception {
		commonList("0", "virtual_order_state", "0");
		return goUrl("sellervirtualindex");
	}

	/**
	 * 方法描述：买家订单列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String buyorderlist() throws Exception {
		is_buyer = true;
		commonList("1", "order_state", "1");
		Map map = new HashMap();
		map.put("para_code", "buy_cancel_order");
		List list = commparaService.getList(map);
		jsontotal=JsonUtil.list2json(list);
		return goUrl("buyorderindex");
	}

	/**
	 * @Method Description :
	 * @author : HZX
	 * @throws Exception
	 * @date : Oct 29, 2014 10:53:20 AM
	 */
	public String reList() throws Exception {
		re_o = "1";
		is_buyer = true;
		commonList("", "order_state", "1");
		Map map = new HashMap();
		map.put("para_code", "buy_cancel_order");
		List list = commparaService.getList(map);
		jsontotal=JsonUtil.list2json(list);
		return goUrl("recycleindex");

	}

	/**
	 * 方法描述：买家虚拟订单列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String buyVirtualList() throws Exception {
		commonList("0", "virtual_order_state", "1");
		return goUrl("buyvirtualindex");
	}

	/**
	 * @Method Description :
	 * @author : HZX
	 * @throws Exception
	 * @date : Oct 29, 2014 10:05:23 AM
	 */
	public String deleteOrder() throws Exception {
		commonDelete();
		return buyorderlist();
	}
	
	
	/**
	 * 试用订单删除
	 * @return
	 * @throws Exception
	 */
	public String tryDel()throws Exception{
		commonDelete();
		return postageList();
	}
	
	public void commonDelete(){
		String id = this.goodsorder.getOrder_id();
		goodsorder = this.goodsorderService.get(id);
		if (goodsorder != null&&goodsorder.getIs_del().equals("1")) {
			String buy_cust_id = goodsorder.getBuy_cust_id();
			if (!this.session_cust_id.equals(buy_cust_id)) {
				this.addActionMessage("删除失败!");
			}
			goodsorder.setIs_del("0");
			this.goodsorderService.update(goodsorder);
			this.addActionMessage("删除成功!");
		}
	}

	/**
	 * @Method Description :
	 * @author : HZX
	 * @throws Exception
	 * @date : Oct 29, 2014 10:34:06 AM
	 */
	public String reOrder() throws Exception {
		String id = this.goodsorder.getOrder_id();
		goodsorder = this.goodsorderService.get(id);
		if (goodsorder != null &&goodsorder.getIs_del().equals("0")) {
			String buy_cust_id = goodsorder.getBuy_cust_id();
			if (!this.session_cust_id.equals(buy_cust_id)) {
				this.addActionMessage("还原失败!");
				return buyorderlist();
			}
			goodsorder.setIs_del("1");
			this.goodsorderService.update(goodsorder);
			this.addActionMessage("还原成功!");
		}
		return reList();

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
		goodsorderService.update(stateMap);
		String reason = CommparaFuc.getReason(s_order_state, order_reason);
		// 插入订单异动信息表
		insertOrderTrans(s_order_id, reason, s_order_state);
	}

	/**
	 * @author : HXK 方法描述：买家付款
	 * @return
	 * @throws Exception
	 */
	public String buyPayed() throws Exception {
		// 修改订单状态
		updateOrderState(goods_order_id, goods_order_state);
		return buyorderlist();
	}

	/**
	 * @author : HXK 方法描述：买家取消订单-查看
	 * @return
	 * @throws Exception
	 */
	public String buyCancelOrderView() throws Exception {
	       if(!ValidateUtil.isRequired(goods_order_id)){
	    	   goodsorder=this.goodsorderService.get(goods_order_id);
	    	   cancelorder=this.cancelorderService.getByOrderId(goods_order_id);
	    	   Map pageMap=new HashMap();
	    	   pageMap.put("order_id", goods_order_id);
	    	   cancelorderList=this.cancleordertransService.getList(pageMap);
		   	   Map detailMap = new HashMap();
			   detailMap.put("order_id", goods_order_id);
			   detailList = this.orderdetailService.getList(detailMap);
	    	   gOrderPayment();
	       }else{
	    	   return cancelOrderList();
	       }
	       return goUrl("mbCancelOrderDetail");
	}

	/**
	 * @author : CYC 方法描述：买家取消积分订单
	 * @return
	 * @throws Exception
	 */
	public String pointsCancelOrderView() throws Exception {
		String id = this.goodsorder.getOrder_id();
		if (id == null || id.equals("")) {
			goodsorder = new Goodsorder();
		} else {
			goodsorder = this.goodsorderService.get(id);
			// 获取订单商品详细信息
			gOrderDetaiInfo(id, goodsorder.getOrder_type());
			Map gMap = new HashMap();
			gMap.put("goodsorder_id", id);
			getOrder(gMap);
			gCommparaList("buy_cancel_order");
		}
		return goUrl("pointsCancelOrder");
	}

	/**
	 * @author : HXK
	 * @param :para_code
	 * @date Mar 28, 2014 1:14:33 PM
	 * @Method Description :获取参数表相应para_code的列表
	 */
	public void gCommparaList(String para_code) {
		Map map = new HashMap();
		map.put("para_code", para_code);
		commparaList = commparaService.getList(map);
	}

	/**
	 * 取消订单记录列表
	 * @return
	 */
	public String cancelOrderList() throws Exception{
		Map pageMap=new HashMap();
		if(this.session_cust_type.equals(Constants.ADMIN_TYPE)){
			if(!ValidateUtil.isRequired(cancel_state_s)){
				if(cancel_state_s.equals("3")){//临时标识 即过滤条件cancel_order中1，2
					pageMap.put("cancel_state","1,2");
				}else{
					pageMap.put("cancel_state",cancel_state_s);
				}
					
			}else{
				pageMap.put("cancel_state","0");
			}
		
			if(!ValidateUtil.isRequired(order_state_s)){
				pageMap.put("order_state",order_state_s);
			}
		}
          
		if(this.session_cust_type.equals(Constants.MEMBER_TYPE)){
			pageMap.put("cancel_buy_cust_id", this.session_cust_id);
		}
 		pageMap.put("is_virtual", "1");
 		
		pageMap.put("is_del", "1");
		// 根据页面提交的条件找出信息总数
		int count = this.goodsorderService.getCount(pageMap);
		// 分页插件
		pageMap = super.webAppPageTool(count, pageMap);
 		getOrder(pageMap);
 		gOrderPayment();
		return goUrl("mbCancelOrder");
	}
	
	/**订单取消详情
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cancelOrderDetails() throws Exception{
		String id = this.goodsorder.getOrder_id();
		if (id == null || id.equals("")) {
			goodsorder = new Goodsorder();
		} else {
			goodsorder = this.goodsorderService.get(id);
			// 获取订单商品详细信息
			gOrderDetaiInfo(id, goodsorder.getOrder_type());
			memberuser = memberuserService.getPKByCustID(goodsorder
					.getBuy_cust_id());
			cancelorder=this.cancelorderService.getByOrderId(id);
			cancel_state = cancelorder.getOrder_state();
			cancel_reject_reason = cancelorder.getReject_reason();
			order_cancel_reason = cancelorder.getBuy_reason();//客户取消订单原因
			order_cancel_date = cancelorder.getBuy_date();
			refund_code = cancelorder.getRefund_code();
			refund_message = cancelorder.getRefund_message();//退款申请标识信息
		}
		gOrderPayment();
		gOrderSendmode();
		gOrderCommparaState("order_state");
		gordertransInfo(goodsorder.getOrder_id());
		order_area = AreaFuc.getAreaNameByMap(goodsorder.getArea_attr());
		Payment payment = this.paymentService.get(goodsorder.getPay_id());
		if(payment.getPay_code().equals("yeepay")){
			return goUrl("cancelOrderDetails");
		}else if(payment.getPay_code().equals("alipay")){
			return goUrl("cancelOrderForAlipay");
		}
		return NONE;
		
	}
	
	/**
	 * @author QJY
	 * @method 方法描述：会员取消订单
	 * @return
	 * @throws Exception
	 */
	public String buyCancelOrder() throws Exception {
		
		if (this.session_cust_id != null && goods_order_id != null) {
			goodsorder = goodsorderService.get(goods_order_id);
			if(goodsorder.getOrder_state()!=null&&!"".equals(goodsorder.getOrder_state())){
				if(goodsorder.getOrder_state().equals("1")||goodsorder.getOrder_state().equals("2")){
					if (buy_order_cancel != null&& !buy_order_cancel.equals("--请选择取消理由--")) {
						order_state=goodsorder.getOrder_state();//主订单的状态
						SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");// 设置日期格式
						String buy_cust_id = this.session_cust_id;
						cancelorder=new Cancelorder();
						cancelorder.setBuy_reason(buy_order_cancel);
						cancelorder.setBuy_cust_id(buy_cust_id);
						cancelorder.setBuy_user_id(this.session_user_id);
						cancelorder.setBuy_date(df.format(new Date()));
						cancelorder.setOrder_id(goods_order_id);
						
						String message = "";
						String reason="您的取消申请已提交";
						if(order_state.equals("1")){//如果订单未付款时，买家可以直接取消订单
							if(goodsorder.getPay_id()!=null&&!"".equals(goodsorder.getPay_id())){
								String pay_type = this.paymentService.get(goodsorder.getPay_id()).getPay_code();
								if(pay_type.equals("alipay") || pay_type.equals("alipaywap")){
									//把请求参数打包成数组
									Map<String, String> sParaTemp = new HashMap<String, String>();
									sParaTemp.put("service", AlipayConfig.close_service);
							        sParaTemp.put("partner", AlipayConfig.partner);
							        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
									sParaTemp.put("trade_no", goodsorder.getPay_trxid());//支付宝交易
									sParaTemp.put("out_order_no", goods_order_id);//商户订单号
									
									//建立请求
									String sHtmlText = AlipaySubmit.buildRequest("", "", sParaTemp);
									
								}
							}
							
							//红包，优惠券
							CouponFuc recoupon = new CouponFuc();
							recoupon.backCouponAnd(goods_order_id);
							
							String balance_use = goodsorder.getBalance_use();
							//执行退款的时候，需要把使用部分余额付款的退回去。
							sysfundService.freezeNum(Double.parseDouble(balance_use), 1);
							//退回余额
							memberfundService.freezeNum(goodsorder.getBuy_cust_id(),Double.parseDouble(balance_use),1);

							//买家的资金异动
							Memberfund buymf=memberfundService.get(goodsorder.getBuy_cust_id());
							Fundhistory buy_fh = new Fundhistory();
							buy_fh.setBalance(Double.parseDouble(buymf.getUse_num()));
							buy_fh.setCust_id(goodsorder.getBuy_cust_id());
							buy_fh.setFund_in(Double.parseDouble(balance_use));
							buy_fh.setReason("会员收到订单号:"+goods_order_id+" 的订单退款"+balance_use+"元");
							buy_fh.setFund_out(0.0);
							buy_fh.setUser_id(this.session_user_id);
							this.fundhistoryService.insert(buy_fh);
							
							cancelorder.setOrder_state("1");//同意取消
							// 更新订单状态为 取消
							updateOrderState(goods_order_id, "0");//订单直接为取消状态 即：0
							//插入取消订单进度
							insertCancelOrderTrans(goods_order_id, reason,this.session_cust_id,this.session_user_id);
							reason="您的取消订单处理完成";
							insertCancelOrderTrans(goods_order_id, reason,"0","0");
							message="订单成功取消!";
								
						}else if (order_state.equals("2")){//如果订单付款了，买家取消订单，需进行系统审核处理
							
							cancelorder.setOrder_state("0");//处理中
							
							// 更新订单状态为 退款中
							updateOrderState(goods_order_id, "4");//将订单状态变为 退款中 即：4
							//插入取消订单进度
							insertCancelOrderTrans(goods_order_id, "您的订单取消申请成功，正在处理退款，请耐心等待。",this.session_cust_id,this.session_user_id);
							message="订单取消申请成功,等待处理退款";
						}
						cancelorder.setSell_cust_id(goodsorder.getSell_cust_id());
						cancelorderService.insert(cancelorder);
						this.addActionMessage(message);
						return buyorderlist();
					
					} else {
						this.addFieldError("buy_order_cancel", "请选择取消理由");
						return buyCancelOrderView();
					}
				}
			}
		} 
		return buyorderlist();
	}

	/**
	 * 运营商处理取消订单 的 退款
	 * @return
	 * @throws Exception
	 */
	public String dealCancelOrder()throws Exception{
		goods_order_id = this.goodsorder.getOrder_id();
		if(!ValidateUtil.isRequired(goods_order_id)){
			cancelorder=this.cancelorderService.getByOrderId(goods_order_id);
            cancelorder.setOrder_state(cancel_state);//0：处理中  1：同意取消  2：拒接取消
			goodsorder = this.goodsorderService.get(goods_order_id);			
			String goodsorder_state = "",pay_id="";
			double total_amount=0.00;
			if(goodsorder!=null){
				goodsorder_state = goodsorder.getOrder_state();
				pay_id = goodsorder.getPay_id();
				total_amount = goodsorder.getTatal_amount();
			}
			
			String reason = "";
	        if("4".equals(goodsorder_state)){//订单已付款执行，需对资金进行操作，在线退款
	        	
	        	Payment payment = this.paymentService.get(pay_id);
	        	if(payment.getPay_code().equals("yeepay")){//易宝退款
		        		String trxNum="",refundAmount="";
			        	trxNum = goodsorder.getPay_trxid();//支付交易流水号
			        	refundAmount = goodsorder.getTatal_amount().toString();//申请退款的金额
			    		
			    		Payment yeepay = PaymentFuc.getPayment("yeepay");
			    		String merID = yeepay.getPay_account();//商户编号
			    		String keyValue = yeepay.getPasswd();//密钥
			    		
			    		String refund_cmd = "RefundOrd";  //退款请求，固定值“RefundOrd”.
			    	    String currency = "CNY";      //交易币种。固定值“CNY”.
			    	    String refundDesc = "";      //退款说明
			    	    //列表中的参数值按照签名顺序拼接所产生的字符串，注意null要转换为 ””，并确保无乱码
			    	    String hmac = DigestUtil.getHmac(new String[]{refund_cmd,merID,trxNum,refundAmount,currency,refundDesc},keyValue);
			            //返回结果  
			    		RefundResult rr = PaymentForOnlineService.refundByTrxId(trxNum,refundAmount,currency,refundDesc);// 调用后台外理查询方法
			    		refund_code = rr.getR1_Code();
			    		if("1".equals(refund_code)){//退款申请成功
			    			//校验交易签名
			    			String newHmac = "";
				    		newHmac = DigestUtil.getHmac(new String[] { rr.getR0_Cmd(), rr.getR1_Code(), rr.getR2_TrxId(),
				    				  rr.getR3_Amt(), rr.getR4_Cur()}, keyValue);
				    		if (!newHmac.equals(rr.getHmac())) {
				    			refund_message = "抱歉,交易签名无效。";
				    			return cancelOrderDetails();
				    		}
			    			
			    			//在线退款成功后，处理平台资金
			    			sysfundService.reduceNumByrefund(Double.valueOf(refundAmount));
			    			reason="您的订单退款已提交受理，请耐心等待。";
							//插入取消订单进度
							insertCancelOrderTrans(goods_order_id, reason,"0","0");
							//买家的资金异动
							Memberfund buymf=memberfundService.get(goodsorder.getBuy_cust_id());
							Fundhistory buy_fh = new Fundhistory();
							buy_fh.setBalance(Double.parseDouble(buymf.getFund_num()));
							buy_fh.setCust_id(goodsorder.getBuy_cust_id());
							buy_fh.setFund_in(goodsorder.getTatal_amount());
							buy_fh.setReason("买家收到订单号:"+goods_order_id+" 取消订单在线退款"+goodsorder.getTatal_amount()+"元");
							buy_fh.setFund_out(0.0);
							buy_fh.setUser_id(session_user_id);
							this.fundhistoryService.insert(buy_fh);
							// 更新订单状态为 退款中
							updateOrderState(goods_order_id, "0");//将订单状态变为 退款成功 后直接 置为取消订单
							reason="您的订单退款处理成功，正在按原路退回，请耐心等待。";
							insertCancelOrderTrans(goods_order_id, reason,"0","0");
							refund_message = "退款申请成功";
			    		}else if("2".equals(refund_code)){
			    			refund_message = "账号状态无效";
			    		}else if("7".equals(refund_code)){
			    			refund_message = "该订单不支持退款";
			    		}else if("10".equals(refund_code)){
			    			refund_message = "退款金额超限";
			    		}else if("18".equals(refund_code)){
			    			refund_message = "商家余额不足";
			    		}else if("50".equals(refund_code)){
			    			refund_message = "订单不存在";
			    		}
			    		
			    		this.cancelorderService.update(cancelorder);
			        	if("1".equals(refund_code)){
			        		this.addActionMessage("同意会员取消订单的申请，且订单退款申请成功");
			        		return cancelOrderList();
			        	}else{
			        		this.addActionMessage("退款操作失败，请查看原因");
			        		return cancelOrderDetails();
			        	}
	        		
	        	}else if(payment.getPay_code().equals("alipay")){
	        		//支付宝退款
	        	}
	        	
	        	
        		/*//余额退款流程资金流处理
				//执行退款的时候，需要把平台总资金的冻结订单金额 解冻到可用资金里面
				sysfundService.freezeNum(total_amount, 1);
				//退款给买家
				memberfundService.outAndInNum(goodsorder.getBuy_cust_id(),total_amount,1);*/

			}
		}
		return NONE;
	}
	
	/**拒接取消订单 操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String rejectCancleOrder()throws Exception{
		goods_order_id = this.goodsorder.getOrder_id();
		cancelorder=this.cancelorderService.getByOrderId(goods_order_id);
		cancelorder.setOrder_state(cancel_state);
		cancelorder.setReject_reason(cancel_reject_reason);
		this.cancelorderService.update(cancelorder);
		// 更新订单状态为 退款失败
		updateOrderState(goods_order_id, "6");//将订单状态变为 退款失败 即：6
		this.addActionMessage("不同意会员取消订单的申请！");
		return cancelOrderList();
	}
	
	/**
	 * 插入取消订单异动表信息
	 * @param order_id
	 * @param reason
	 */
	public void insertCancelOrderTrans(String order_id,String reason,String cust_id,String user_id){
		cancleordertrans=new Cancleordertrans();
		cancleordertrans.setCust_id(cust_id);
		cancleordertrans.setUser_id(user_id);
		cancleordertrans.setReason(reason);
		cancleordertrans.setOrder_id(order_id);
		cancleordertransService.insert(cancleordertrans);
	}
	

	/**
	 * @Method Description: 会员完成交易后可获得的积分：一个订单中所有 商品价格*单价
	 * @author: QJY
	 * @date : May 16, 2014 11:26:45 AM
	 * @param
	 * @return return_type
	 */
	public void insertOrderInter(String goodsorder_id) {

		if (!validateFactory.isRequired(goodsorder_id)) {
			Goodsorder god = new Goodsorder();
			// 获取订单的买家会员
			god = goodsorderService.get(goodsorder_id);
			String b_cust_id = "";
			b_cust_id = god.getBuy_cust_id();
			Memberinter meminter = new Memberinter();
			// 获取会员当前的积分
			meminter = memberinterService.get(b_cust_id);
			Double coutinter = 0.0;
			// 统计当前订单的总额数为获得积分总额数+当前积分数
			coutinter = Double.parseDouble(god.getPresent_integral().toString())
					+ Double.parseDouble(meminter.getFund_num());
			meminter.setFund_num(coutinter.toString());
			memberinterService.update(meminter);
			// 插入积分流
			Interhistory interhistory = new Interhistory();
			interhistory.setCust_id(b_cust_id);
			interhistory.setInter_in(god.getPresent_integral().toString());
			interhistory.setInter_out("0");
			interhistory.setThisinter(coutinter.toString());
			interhistory.setUser_id(this.session_user_id);
			interhistory.setReason("购买商品获取积分,商品订单号为:" + goodsorder_id);
			interhistoryService.insert(interhistory);

		}
	}

	/**
	 * 赠送优惠券
	 * @param goodsorder_id
	 */
	public void insertCoupon(String goodsorder_id) {

		if (!validateFactory.isRequired(goodsorder_id)) {
			Goodsorder god = new Goodsorder();
			// 获取订单的买家会员
			god = goodsorderService.get(goodsorder_id);
			String b_cust_id = "";
			b_cust_id = god.getBuy_cust_id();
			String coupon_id = god.getSend_coupon_id();
			if(!ValidateUtil.isRequired(coupon_id)){
               String[] coupon_ids = coupon_id.trim().split(",");
               for(int i = 0; i < coupon_ids.length ; i ++ ) {
            	   Coupon coupon = this.couponService.get(coupon_ids[i]);
            	   Comsumer comsumer = new Comsumer();
            	   comsumer.setCoupon_id(coupon_ids[i]);
            	   comsumer.setCust_id(b_cust_id);
            	   comsumer.setUse_state("0");
            	   comsumer.setComsumer_no(coupon.getCoupon_no()+""+RandomStrUtil.getRand("10"));
            	   comsumerService.insert(comsumer);
               }
			}
		}
	}
	
	
	/**
	 * 赠送优红包
	 * @param goodsorder_id
	 */
	public void insertRed(String goodsorder_id) {

		if (!validateFactory.isRequired(goodsorder_id)) {
			Goodsorder god = new Goodsorder();
			// 获取订单的买家会员
			god = goodsorderService.get(goodsorder_id);
			String b_cust_id = "";
			b_cust_id = god.getBuy_cust_id();
			String red_id = god.getSend_red_id();
			if(!ValidateUtil.isRequired(red_id)){
               String[] red_ids = red_id.trim().split(",");
               for(int i = 0; i < red_ids.length ; i ++ ) {
            	   Redpacket redpacket = redpacketService.get(red_ids[i]);
            	   Redsumer redsumer = new Redsumer();
            	   redsumer.setRed_id(red_ids[i]);
            	   redsumer.setCust_id(b_cust_id);
            	   redsumer.setUse_state("0");
            	   redsumer.setRedsumer_no(redpacket.getRed_no()+""+RandomStrUtil.getRand("10"));
            	   redsumerService.insert(redsumer);
               }
			}
		}
	}
	
	
	/**
	 * @author QJY
	 * @function 会员升级
	 * @date 2015-08-19
	 * @throws Exception
	 */
	public void memberUpgrade(Goodsorder goodsorder)throws Exception{
		if(goodsorder !=null){
			
			Member member = this.memberService.get(goodsorder.getBuy_cust_id());
			//会员下单--交易成功，获取相应的成长值，取整
			Integer add_grow_value = goodsorder.getTatal_amount().intValue();
			//会员原来的成长值
			Integer old_grow_value = Integer.valueOf(member.getGrowthvalue());
			//交易成功后最新的成长值
			Integer total_grow_value = add_grow_value+old_grow_value;
			//更新该会员的成长值以及判断会员是否需要升级
			member.setGrowthvalue(String.valueOf(total_grow_value));
			String level_code="";
			List levelList = this.malllevelsetService.getList(new HashMap());
			if(levelList !=null && levelList.size()>0){
				for(int i=0;i<levelList.size();i++){
					Map levelMap = (Map) levelList.get(i);
					if(levelMap!=null && levelMap.get("inter_lower")!=null&& levelMap.get("inter_height")!=null){
						Integer inter_lower = Integer.valueOf(levelMap.get("inter_lower").toString());
						Integer inter_upper = Integer.valueOf(levelMap.get("inter_height").toString());
						//注册会员默认成长值上下限为0，故升级规则如下
						if((total_grow_value>= inter_lower) && (total_grow_value<= inter_upper)){
							level_code = levelMap.get("level_code").toString();
							break;
						}
					}
				}
				
			}
			member.setBuy_level(level_code);
			
			this.memberService.update(member);	
		}

	}

	/**
	 * @author : HXK 方法描述：买家评价
	 * @return
	 * @throws Exception
	 */
	public String buyEvaluation() throws Exception {
		// 修改订单状态
		updateOrderState(goods_order_id, goods_order_state);
		return buyorderlist();
	}

	/**
	 * @author : HXK 方法描述：买家申请退款
	 * @return
	 * @throws Exception
	 */
	public String buyRefund() throws Exception {
		// 修改订单状态
		updateOrderState(goods_order_id, goods_order_state);
		return buyorderlist();
	}

	/**
	 * @author : HXK 方法描述：卖家发货--查看
	 * @return
	 * @throws Exception
	 */
	public String sellerDeliveryView() throws Exception {
		String id = this.goodsorder.getOrder_id();
		if (id == null || id.equals("")) {
			goodsorder = new Goodsorder();
		} else {
			goodsorder = this.goodsorderService.get(id);
			if (goodsorder.getOrder_state().equals("2")) {
				// 获取订单商品详细信息
				gOrderDetaiInfo(id, goodsorder.getOrder_type());
				order_area = AreaFuc
						.getAreaNameByMap(goodsorder.getArea_attr());
				// 获取买家会员信息
				memberuser = memberuserService.getPKByCustID(goodsorder
						.getBuy_cust_id());
				Map gMap = new HashMap();
				gMap.put("goodsorder_id", id);
				getOrder(gMap);
			} else {
				commonSellResponse(goodsorder.getOrder_id(), goodsorder
						.getOrder_state());
				return null;
			}
		}
		// 获取卖家会员信息
		memberuser_seller = memberuserService
				.getPKByCustID(this.session_cust_id);
		// 获取卖家店铺信息
		shopconfig = shopconfigService.getByCustID(this.session_cust_id);
		gOrderSendmode();
		gOrderCommparaState("order_state");

		return goUrl("deliveryGoods");
	}

	/**
	 * @author : HXK 方法描述：卖家发货--修改订单配送信息
	 * @return
	 * @throws Exception
	 */
	public String sellerDelivery() throws Exception {
		// 订单状态表示：0：订单取消 1：未付款 2：已付款 3：已发货 4：退款中 5：退款成功 6：退款失败：7：交易成功 8：已评价
		if (goods_order_id != null && !"".equals(goods_order_id)) {
			Goodsorder gorder = new Goodsorder();
			gorder = goodsorderService.get(goods_order_id);
			if (gorder.getOrder_state().equals("2")) {
				// 物流公司
				if (order_send_mode != null && !"".equals(order_send_mode)) {
					gorder.setSend_mode(order_send_mode);
				}
				// 快递单号
				if (order_send_num != null && !"".equals(order_send_num)) {
					if (ValidateUtil.isEXPRESS(order_send_num)) {
						this.addFieldError("order_send_num_tip", "请输入正确的运单号码");
						return sellerDeliveryView();
					}
					gorder.setSend_num(order_send_num);
				} else {
					this.addFieldError("order_send_num_tip", "请填写运单号码");
					return sellerDeliveryView();
				}
				// 商家备注
				if (order_mem_mark != null && !"".equals(order_mem_mark)) {
					gorder.setMem_remark(order_mem_mark);
				}
				goodsorderService.update(gorder);
				// 修改订单状态
				updateOrderState(goods_order_id, goods_order_state);
				this.addActionMessage("发货成功");
			}
		}
		return sellerorderlist();
	}

	/**
	 * @author : HXK 方法描述：卖家取消订单
	 * @return
	 * @throws Exception
	 */
	public String sellerCancelOrder() throws Exception {
		if (this.session_cust_id != null && goods_order_id != null) {
			goodsorder = goodsorderService.get(goods_order_id);
			if (seller_order_cancel != null
					&& !seller_order_cancel.equals("--请选择关闭理由--")) {
				updateOrderState(goods_order_id, "0", seller_order_cancel);
				this.addActionMessage("关闭订单成功!");
				// 信息提醒
				MessageAltFuc mesalt = new MessageAltFuc();

				if (is_virtual != null && !is_virtual.equals("")) {
					mesalt.messageAutoSend("13", goodsorder.getBuy_cust_id(),
							goodsorder);
					return sellerVirtualList();
				} else if (!ValidateUtil.isRequired(goodsorder.getOrder_type())
						&& "7".equals(goodsorder.getOrder_type())) {
					mesalt.messageAutoSend("10", goodsorder.getSell_cust_id(),
							goodsorder);
					return postagegoodsList();
				} else {
					mesalt.messageAutoSend("10", goodsorder.getSell_cust_id(),
							goodsorder);
					return sellerorderlist();
				}
			} else {
				this.addFieldError("seller_order_cancel", "请选择关闭理由");
				return sellerCancelOrderView();
			}
		} else {
			return sellerCancelOrderView();
		}
	}

	/**
	 * @author : HXK 方法描述：卖家取消订单
	 * @return
	 * @throws Exception
	 */
	public String sellerCancelOrderView() throws Exception {
		String id = this.goodsorder.getOrder_id();
		if (id == null || id.equals("")) {
			goodsorder = new Goodsorder();
		} else {
			goodsorder = this.goodsorderService.get(id);
			// 获取订单商品详细信息
			gOrderDetaiInfo(id, goodsorder.getOrder_type());
			Map gMap = new HashMap();
			gMap.put("goodsorder_id", id);
			getOrder(gMap);
			gCommparaList("seller_cancel_order");
		}
		return goUrl("sellerCancelOrder");
	}

	/**
	 * @author : HXK 方法描述：卖家同意退款
	 * @return
	 * @throws Exception
	 */
	public String sellerConfirmRefund() throws Exception {
		// 修改订单状态
		updateOrderState(goods_order_id, goods_order_state);
		return sellerorderlist();
	}

	/**
	 * @author : HXK 方法描述：卖家拒绝退款
	 * @return
	 * @throws Exception
	 */
	public String sellerRefusedRefund() throws Exception {
		// 修改订单状态
		updateOrderState(goods_order_id, goods_order_state);
		return sellerorderlist();
	}

	/**
	 * 选择发货方式 1:普通发货 2：EMS发货 3:顺丰发货
	 * 
	 * @return
	 * @throws Exception
	 */
	public String chooseSendMode() throws Exception {
		if (!ValidateUtil.isRequired(chb_id)) {
			String[] chb_id_Str = chb_id.split(",");
			if(chb_id_Str.length == 1){
				goodsorder = this.goodsorderService.get(chb_id);
			}
			//普通物流方式
			Map orderMap = new HashMap();
			orderMap.put("en_name_s",send_mode_enname);
			sendmodeList = sendmodeService.getList(orderMap);
			goods_order_ids = chb_id;// 把订单串赋值给goods_order_ids
			goods_order_num = String.valueOf(chb_id_Str.length);// 需请求运单号的数量
		}
		return goUrl("chooseSendMode");
	}

	
	/**
	 * EMS热敏发货 第一步：请求运单号，获取运单号后给系统中的发货的订单分配运单号
	 * 
	 * @param goods_orders
	 * @param goods_order_num
	 * @throws Exception
	 */
	public String emsDeliveryView() throws Exception {
		if (!ValidateUtil.isRequired(goods_order_ids)
				&& !ValidateUtil.isRequired(goods_order_num)
				&& !ValidateUtil.isRequired(businessType)&& !ValidateUtil.isRequired(orderweight)) {
			// 根据不同业务类型获取EMS运单号：1标准快递 4经济快递，一次只能请求1-100个数量的运单号
			billnos = EmsFuc.getBillnos(businessType, goods_order_num);// 参数说明：快递业务类型，请求运单号数量
			if (!ValidateUtil.isRequired(billnos)) {// 有运单号才执行以下操作
				goodsorderList = new ArrayList();// 初始化
				String sendmode_id =  this.sendmodeService.getByEnname(print_temp_ems).getSmode_id();
				Integer billnonum = Integer.valueOf(goods_order_num);
				String[] billnosArray = billnos.split(",");
				String[] goodsorderArray = goods_order_ids.split(",");
				for (int i = 0; i < goodsorderArray.length; i++) {
					goodsorder = this.goodsorderService.get(goodsorderArray[i]);
					goodsorder.setSend_num(billnosArray[i]);// 把取得的运单号存入系统订单表中
					goodsorder.setBusiness_type(businessType);// 把选择的快递业务类型存入系统订单表中
					goodsorder.setSend_mode(sendmode_id);//默认物流公司EMS ID 存入订单表
					goodsorder.setOrder_weight(orderweight);//将用电子称的重量存入系统订单表中（KG）
					goodsorderService.update(goodsorder);
					goodsorderList.add(goodsorder);
				}
				// 上传选中的订单数据backDataToEMS(goods_order_ids, businessType);
				if ("1".equals(goods_order_num)) {// 运单号数量为一个时执行的操作
					goodsorder = this.goodsorderService.get(goods_order_ids);
					// 获取订单商品详细信息
					//gOrderDetaiInfo(goods_order_ids, goodsorder.getOrder_type());
					order_area = AreaFuc.getAreaNameByMap(goodsorder
							.getArea_attr());
					// 获取买家会员信息
					memberuser = memberuserService.getPKByCustID(goodsorder
							.getBuy_cust_id());
					Map gMap = new HashMap();
					gMap.put("goodsorder_id", goods_order_ids);
					getOrder(gMap);
					gOrderSendmode();
					// 获取卖家会员信息
					memberuser_seller = memberuserService
							.getPKByCustID(goodsorder.getSell_cust_id());
					// 获取卖家店铺信息
					shopconfig = shopconfigService.getByCustID(goodsorder
							.getSell_cust_id());
					gOrderCommparaState("order_state");
					if (shopconfig.getArea_attr() != null
							&& !shopconfig.getArea_attr().equals("")) {
						AreaFuc areaFuc = new AreaFuc();
						shopconfig_area_attr = areaFuc
								.getAreaNameByMap(shopconfig.getArea_attr());
					}
					return goUrl("emsdeliveryGoods");
				} else {// 批量发货
					return goUrl("oerderInfoConfirm");
				}

			} else {
				this.addFieldError("emsorder", "未能从EMS自助系统获取到运单号，请从新获取");
				return chooseSendMode();
			}

		}
		return chooseSendMode();

	}
	
	/**EMS热敏发货 第二步，检查信息，上传数据，确定发货
	 * 
	 * @return
	 * @throws Exception
	 */
	public String emsConfirmDelivery()throws Exception{
		if(!ValidateUtil.isRequired(goods_order_ids) && !ValidateUtil.isRequired(businessType)){
			//上传数据到EMS系统
			backDataToEMS(goods_order_ids,businessType);
			goodsorder = this.goodsorderService.get(goods_order_ids);
			// 获取订单商品详细信息
			gOrderDetaiInfo(goods_order_ids, goodsorder.getOrder_type());
			order_area = AreaFuc.getAreaNameByMap(goodsorder
					.getArea_attr());
			// 获取买家会员信息
			memberuser = memberuserService.getPKByCustID(goodsorder
					.getBuy_cust_id());
			Map gMap = new HashMap();
			gMap.put("goodsorder_id", goods_order_ids);
			getOrder(gMap);
			gOrderSendmode();
			//获取快递单打印样式模板
			sendmode = this.sendmodeService.get(goodsorder.getSend_mode());
			print_template_code = this.printstyleService.get(sendmode.getTrade_id()).getTemplate_code();
			// 获取卖家会员信息
			//memberuser_seller = memberuserService.getPKByCustID(goodsorder.getSell_cust_id());
			// 获取卖家店铺信息
			shopconfig = shopconfigService.getByCustID(goodsorder
					.getSell_cust_id());
			if (shopconfig.getArea_attr() != null
					&& !shopconfig.getArea_attr().equals("")) {
				AreaFuc areaFuc = new AreaFuc();
				shopconfig_area_attr = areaFuc
						.getAreaNameByMap(shopconfig.getArea_attr());
			}
			// 修改订单状态
			updateOrderState(goods_order_id, "3");
			MessageAltFuc mesalt = new MessageAltFuc();
			mesalt.messageAutoSend("2", goodsorder.getBuy_cust_id(), goodsorder);
			this.addActionMessage("发货成功");
		}
		
		return goUrl("emsConfirmDelivery");
	}
	
	/**批量发货
	 * 
	 * @return
	 * @throws Exception
	 */
	public String batchConfirmDelivery()throws Exception{
		boolean flag = this.goodsorderService.updateOrderWeight("order_id", "order_weight",chb_id, orderweight);
		if(flag){
		    goodsorderList = new ArrayList();// 初始化
			String[] chb_id_Str = chb_id.split(",");
			for (int i = 0; i < chb_id_Str.length; i++) {
				goodsorder = this.goodsorderService.get(chb_id_Str[i]);
				goodsorder.setOrder_state("3");//已发货
				goodsorderService.update(goodsorder);
				goodsorderList.add(goodsorder);
			}
			this.addActionMessage("发货成功");
		}else{
			this.addActionMessage("发货失败");
		}
		return goUrl("oerderConfirmDelivery");
	}
	
	/**
	 * EMS热敏发货 第三步：回传数据，修改订单状态：已发货
	 * 
	 * @return
	 * @throws Exception
	 */
	public void backDataToEMS(String goods_order_ids, String businessType)
			throws Exception {
		if (!ValidateUtil.isRequired(goods_order_ids)
				&& !ValidateUtil.isRequired(businessType)) {
			String[] goods_order_idArray = goods_order_ids.split(",");
			for (int i = 0; i < goods_order_idArray.length; i++) {
				goodsorder = this.goodsorderService.get(goods_order_idArray[i]);
				shopconfig = shopconfigService.getByCustID(goodsorder
						.getSell_cust_id());
				String resultflag = EmsFuc.uploadDataToEMS(goodsorder,
						shopconfig, businessType);
				if ("1".equals(resultflag)) {
					System.out.println("=============================================成功上传数据");
				} 
			}

		}

	}

	/**
	 * @author : HXK 方法描述：卖家发货--查看
	 * @return
	 * @throws Exception
	 */
	public String DeliveryView() throws Exception {
		if(ValidateUtil.isRequired(goods_order_ids)){
			goodsorder = new Goodsorder();
		} else {
			goodsorder = this.goodsorderService.get(goods_order_ids);
			if(!ValidateUtil.isRequired(orderweight)){
				//订单商品重量
				goodsorder.setOrder_weight(orderweight);
			}
			if(!ValidateUtil.isRequired(order_send_mode)){
				// 物流公司
				goodsorder.setSend_mode(order_send_mode);
			}
			this.goodsorderService.update(goodsorder);
			// 获取订单商品详细信息
			gOrderDetaiInfo(goods_order_ids, goodsorder.getOrder_type());
			order_area = AreaFuc.getAreaNameByMap(goodsorder.getArea_attr());
			// 获取买家会员信息
			memberuser = memberuserService.getPKByCustID(goodsorder
					.getBuy_cust_id());
			Map gMap = new HashMap();
			gMap.put("goodsorder_id", goods_order_ids);
			getOrder(gMap);
			// 获取卖家会员信息
			memberuser_seller = memberuserService.getPKByCustID(goodsorder
					.getSell_cust_id());
			// 获取卖家店铺信息
			shopconfig = shopconfigService
					.getByCustID(goodsorder.getSell_cust_id());
			gOrderSendmode();
			gOrderCommparaState("order_state");
			if (shopconfig.getArea_attr() != null
					&& !shopconfig.getArea_attr().equals("")) {
				AreaFuc areaFuc = new AreaFuc();
				shopconfig_area_attr = areaFuc.getAreaNameByMap(shopconfig
						.getArea_attr());
			}
			
		}
		
		return goUrl("deliveryGoods");
	}

	/**
	 * 确定发货，打印快递单
	 * @return
	 * @throws Exception
	 */
	public String confirmDeliveryGoods()throws Exception{
		if(ValidateUtil.isRequired(goods_order_ids)){
			goodsorder = new Goodsorder();
		} else {
			goodsorder = this.goodsorderService.get(goods_order_ids);
			// 快递单号
			if (order_send_num != null && !"".equals(order_send_num)) {
				if (ValidateUtil.isEXPRESS(order_send_num)) {
					this.addFieldError("order_send_num_tip", "请输入正确的运单号码");
					return DeliveryView();
				}
				goodsorder.setSend_num(order_send_num);
			} else {
				this.addFieldError("order_send_num_tip", "请填写运单号码");
				return DeliveryView();
			}
			goodsorderService.update(goodsorder);
			// 获取订单商品详细信息
			gOrderDetaiInfo(goods_order_ids, goodsorder.getOrder_type());
			order_area = AreaFuc.getAreaNameByMap(goodsorder.getArea_attr());
			// 获取买家会员信息
			memberuser = memberuserService.getPKByCustID(goodsorder
					.getBuy_cust_id());
			Map gMap = new HashMap();
			gMap.put("goodsorder_id", goods_order_ids);
			getOrder(gMap);
			// 获取卖家会员信息
			memberuser_seller = memberuserService.getPKByCustID(goodsorder
					.getSell_cust_id());
			//获取快递单打印样式模板
			sendmode = this.sendmodeService.get(goodsorder.getSend_mode());
			print_template_code = this.printstyleService.get(sendmode.getTrade_id()).getTemplate_code();
			
			// 获取卖家店铺信息
			shopconfig = shopconfigService
					.getByCustID(goodsorder.getSell_cust_id());
			gOrderSendmode();
			gOrderCommparaState("order_state");
			if (shopconfig.getArea_attr() != null
					&& !shopconfig.getArea_attr().equals("")) {
				AreaFuc areaFuc = new AreaFuc();
				shopconfig_area_attr = areaFuc.getAreaNameByMap(shopconfig
						.getArea_attr());
			}
			// 修改订单状态
			updateOrderState(goods_order_id, "3");
			MessageAltFuc mesalt = new MessageAltFuc();
			mesalt.messageAutoSend("2", goodsorder.getBuy_cust_id(), goodsorder);
			this.addActionMessage("发货成功");
			
		}
		
		return goUrl("confirmDeliveryGoods");
	}

	/**
	 * 顺丰热敏发货
	 * @return
	 * @throws Exception
	 */
	public String sfexpressDelivery()throws Exception{
		if(ValidateUtil.isRequired(goods_order_ids)){
			goodsorder = new Goodsorder();
		} else {
			goodsorder = this.goodsorderService.get(goods_order_ids);
			goodsorder.setIs_sfexpress("1");//标志为顺丰发货订单
			goodsorder.setOrder_weight(orderweight);
			this.goodsorderService.update(goodsorder);
		}
		return sfList();
	}
	
	/**
	 * @author : HXK 方法描述：卖家取消订单
	 * @return
	 * @throws Exception
	 */
	public String adminCancelOrder() throws Exception {

		goodsorder = goodsorderService.get(goods_order_id);
		updateOrderState(goods_order_id, "0", "运营商关闭订单");
		this.addActionMessage("关闭订单成功!");
		// 信息提醒
		MessageAltFuc mesalt = new MessageAltFuc();
		mesalt.messageAutoSend("10", goodsorder.getSell_cust_id(), goodsorder);
		return operatorslist();
	}

	/**
	 * @author : HXK 方法描述：买家查看物流信息
	 * @return
	 * @throws Exception
	 */
	public String buy_order_Logistics() throws Exception {
		commonOrder_Logistics();
		return goUrl("buy_Logistics");
	}

	/**
	 * @author : HXK 方法描述：卖家查看物流信息
	 * @return
	 * @throws Exception
	 */
	public String seller_order_Logistics() throws Exception {
		commonOrder_Logistics();
		return goUrl("seller_Logistics");
	}

	/**
	 * @author : HXK 方法描述：运营商查看物流信息
	 * @return
	 * @throws Exception
	 */
	public String admin_order_Logistics() throws Exception {
		commonOrder_Logistics();
		return goUrl("logistics");
	}

	/**
	 * @author：XBY
	 * @date：Feb 13, 2014 9:18:27 AM
	 * @Method Description：公共查看物流信息
	 */
	public void commonOrder_Logistics() {
		String id = this.goodsorder.getOrder_id();
		String sell_cust_id;
		if (id == null || id.equals("")) {
			goodsorder = new Goodsorder();
		} else {
			goodsorder = this.goodsorderService.get(id);
			// 获取订单商品详细信息
			gOrderDetaiInfo(id, goodsorder.getOrder_type());
			order_area = AreaFuc.getAreaNameByMap(goodsorder.getArea_attr());
			// 获取买家会员信息
			memberuser = memberuserService.getPKByCustID(goodsorder
					.getBuy_cust_id());
			Sendmode sendmode = new Sendmode();
			sendmode = sendmodeService.get(goodsorder.getSend_mode());
			if (sendmode != null) {
				// 获取快递公司代码
				kuai_company_code = sendmode.getEn_name().trim();
				// 获取快递公司名称
				kuai_company = sendmode.getSmode_name().trim();
			}
			kuai_number = goodsorder.getSend_num();
			if (!validateFactory.isRequired(kuai_number)) {
				kuai_number = kuai_number.trim();
			}
			// 查询快递信息
			logistics_query = LogisticsFuc.hundredTrace(kuai_company_code,
					kuai_number);
			Map gMap = new HashMap();
			gMap.put("goodsorder_id", id);
			getOrder(gMap);
			// 获取卖家会员信息
			sell_cust_id = goodsorder.getSell_cust_id();
			memberuser_seller = memberuserService.getPKByCustID(sell_cust_id);
			// 获取卖家店铺信息
			shopconfig = shopconfigService.getByCustID(sell_cust_id);
		}
		//获取配送方式
		gOrderSendmode();
		//获取支付方式
		gOrderPayment();
	}

	/**
	 * @author : HXK 方法描述：卖家同意退款
	 * @return
	 * @throws Exception
	 */
	public String adminConfirmRefund() throws Exception {
		// 修改订单状态
		updateOrderState(goods_order_id, goods_order_state);
		return operatorslist();

	}

	/**
	 * @author : HXK 方法描述：卖家拒绝退款
	 * @return
	 * @throws Exception
	 */
	public String adminRefusedRefund() throws Exception {
		// 修改订单状态
		updateOrderState(goods_order_id, goods_order_state);
		return operatorslist();
	}
	
	/**
	 * 方法描述：根据主键找出商品订单信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.goodsorder.getOrder_id();
		if (id == null || id.equals("")) {
			goodsorder = new Goodsorder();
		} else {
			goodsorder = this.goodsorderService.get(id);
			// 获取订单商品详细信息
			gOrderDetaiInfo(id, goodsorder.getOrder_type());
			memberuser = memberuserService.getPKByCustID(goodsorder
					.getBuy_cust_id());
			orderinvoice = orderinvoiceService.getByInvoid(goodsorder.getInvoice_id());
		}
		gOrderPayment();
		gOrderSendmode();
		gOrderCommparaState("order_state");
		gordertransInfo(goodsorder.getOrder_id());
		order_area = AreaFuc.getAreaNameByMap(goodsorder.getArea_attr());
		return goUrl("updateorder");
	}

	/**
	 * 方法描述：买家查看订单详情
	 * 
	 * @return
	 * @throws Exception
	 */
	public String buyOrderView() throws Exception {
		String id = this.goodsorder.getOrder_id();
		if (id.endsWith("a") || id.endsWith("b")) {
			id = id.substring(0, id.length() - 1);
		}
		if (id == null || id.equals("")) {
			goodsorder = new Goodsorder();
			return buyorderlist();
		} else {
			goodsorder = this.goodsorderService.get(id);
			// 获取订单商品详细信息
			gOrderDetaiInfo(id, goodsorder.getOrder_type());
		}
		gOrderPayment();
		gOrderSendmode();
		gOrderCommparaState("order_state");
		gordertransInfo(goodsorder.getOrder_id());
		order_area = AreaFuc.getAreaNameByMap(goodsorder.getArea_attr());
		// 获取预售订单详情
		if (goodsorder != null && "6".equals(goodsorder.getOrder_type())) {
			HashMap map = new HashMap();
			map.put("order_id", goodsorder.getOrder_id());
			directDetaiList = this.directorderdetailService.getList(map);
			if (directDetaiList != null) {
				for (int i = 0; i < directDetaiList.size(); i++) {
					// 获取预售开始支付时间
					mapvalue = new HashMap();
					mapvalue = (HashMap) directDetaiList.get(i);
					starttime = mapvalue.get("endpay_time").toString();
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(sdf.parse(starttime));
					calendar.add(Calendar.DATE, +3);
					// 获取三天后的日期
					threedate = sdf.format(calendar.getTime()).toString();
				}
			}

		}
		Sendmode sendmode = new Sendmode();
		sendmode = sendmodeService.get(goodsorder.getSend_mode());
		if (sendmode != null) {
			// 获取快递公司代码
			if (!ValidateUtil.isRequired(sendmode.getEn_name())) {
				kuai_company_code = sendmode.getEn_name().trim();
			}
			// 获取快递公司名称
			if (!ValidateUtil.isRequired(sendmode.getSmode_name())) {
				kuai_company = sendmode.getSmode_name().trim();
			}
		}
		kuai_number = goodsorder.getSend_num();
		if (!ValidateUtil.isRequired(kuai_number)) {
			kuai_number = kuai_number.trim();
		}
		// 查询快递信息
		logistics_query = LogisticsFuc.hundredTrace(kuai_company_code,kuai_number);
		return goUrl("buyorderdetail");
	}

	/**
	 * 方法描述：买家查看积分订单详情
	 * 
	 * @return
	 * @throws Exception
	 */
	public String pointsOrderView() throws Exception {
		String id = this.goodsorder.getOrder_id();
		if (id == null || id.equals("")) {
			goodsorder = new Goodsorder();
			return buyorderlist();
		} else {
			goodsorder = this.goodsorderService.get(id);
			// 获取订单商品详细信息
			gOrderDetaiInfo(id, goodsorder.getOrder_type());
			memberuser = memberuserService.getPKByCustID(goodsorder
					.getBuy_cust_id());
			memberuser_seller = memberuserService.getPKByCustID(goodsorder
					.getSell_cust_id());
		}
		gOrderPayment();
		gOrderSendmode();
		gOrderCommparaState("order_state");
		gordertransInfo(goodsorder.getOrder_id());
		order_area = AreaFuc.getAreaNameByMap(goodsorder.getArea_attr());
		return goUrl("pointsorderdetail");
	}

	/**
	 * 方法描述：卖家查看订单详情
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sellerOrderView() throws Exception {
		String id = this.goodsorder.getOrder_id();
		if (id == null || id.equals("")) {
			goodsorder = new Goodsorder();
			return sellerorderlist();
		} else {
			goodsorder = this.goodsorderService.get(id);
			// 获取订单商品详细信息
			gOrderDetaiInfo(id, goodsorder.getOrder_type());
			memberuser = memberuserService.getPKByCustID(goodsorder
					.getBuy_cust_id());
		}
		gOrderPayment();
		gOrderSendmode();
		gOrderCommparaState("order_state");
		gordertransInfo(goodsorder.getOrder_id());
		order_area = AreaFuc.getAreaNameByMap(goodsorder.getArea_attr());
		return goUrl("sellerorderdetail");
	}

	/**
	 * 方法描述：买家查看订单详情
	 * 
	 * @return
	 * @throws Exception
	 */
	public String buyVirtualView() throws Exception {
		String id = this.goodsorder.getOrder_id();
		if (id == null || id.equals("")) {
			goodsorder = new Goodsorder();
		} else {
			goodsorder = this.goodsorderService.get(id);
			// 获取订单商品详细信息
			gOrderDetaiInfo(id, goodsorder.getOrder_type());
			memberuser = memberuserService.getPKByCustID(goodsorder
					.getBuy_cust_id());
			memberuser_seller = memberuserService.getPKByCustID(goodsorder
					.getSell_cust_id());
		}
		gOrderCommparaState("virtual_order_state");
		gordertransInfo(goodsorder.getOrder_id());
		return goUrl("buyvirtualdetail");
	}

	/**
	 * 方法描述：卖家查看订单详情
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sellerVirtualView() throws Exception {
		String id = this.goodsorder.getOrder_id();
		if (id == null || id.equals("")) {
			goodsorder = new Goodsorder();
		} else {
			goodsorder = this.goodsorderService.get(id);
			// 获取订单商品详细信息
			gOrderDetaiInfo(id, goodsorder.getOrder_type());
			memberuser = memberuserService.getPKByCustID(goodsorder
					.getBuy_cust_id());
		}
		gOrderCommparaState("virtual_order_state");
		gordertransInfo(goodsorder.getOrder_id());
		return goUrl("sellervirtualdetail");
	}

	/**
	 * @Method Description :
	 * @author : HZX
	 * @date : Aug 22, 2014 9:46:58 AM
	 */
	public String comboName(String goodsid) {
		if ("4".equals(order_type)) {
			Map pageMap = new HashMap();
			pageMap.put("sigs", goodsid);
			List comboList = this.comboService.getList(pageMap);
			Iterator cIterator = comboList.iterator();
			while (cIterator.hasNext()) {
				Map cMap = (Map) cIterator.next();
				combo_name = cMap.get("combo_name").toString();
			}
		}
		return combo_name;
	}

	/**
	 * 方法描述：购物单打印
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "orderPrint", results = { @Result(name = "orderPrint", location = "/WEB-INF/template/manager/admin/Goodsorder/orderprint.ftl") })
	public String orderPrint() throws Exception {
		String id = this.goodsorder.getOrder_id();
		if (id == null || id.equals("")) {
			goodsorder = new Goodsorder();
		} else {
			goodsorder = this.goodsorderService.get(id);
			order_type = goodsorder.getOrder_type();
			// 获取订单商品详细信息
			gOrderDetaiInfo(id, order_type);
			memberuser = memberuserService.getPKByCustID(goodsorder
					.getBuy_cust_id());
			member = memberService.get(goodsorder.getSell_cust_id());
			shopconfig = shopconfigService.getByCustID(goodsorder
					.getSell_cust_id());
			// 获取打印样式信息
			gOrderPrintStyle("0", print_temp_gouwu);
			if (print_content != null && !"".equals(print_content)) {
				// 替换打印内容标签
				print_content = replaceOrderPrint(print_content, shopconfig,
						goodsorder);
			}
		}
		return "orderPrint";
	}

	/**
	 * ajax 获取打印购物模板
	 * 
	 * @throws Exception
	 */
	public void getOrderPrint() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("order_id");
		String type = request.getParameter("type");
		if (id == null || id.equals("")) {
			goodsorder = new Goodsorder();
		} else {
			goodsorder = this.goodsorderService.get(id);
			order_type = goodsorder.getOrder_type();
			// 获取订单商品详细信息
			gOrderDetaiInfo(id, order_type);
			memberuser = memberuserService.getPKByCustID(goodsorder
					.getBuy_cust_id());
			member = memberService.get(goodsorder.getSell_cust_id());
			shopconfig = shopconfigService.getByCustID(goodsorder
					.getSell_cust_id());
			// 获取打印样式信息
			if (type.equals("1"))
				gOrderPrintStyle("0", print_temp_gouwu);
			else
				gOrderPrintStyle("0", print_temp_fahuo);
			if (print_content != null && !"".equals(print_content)) {
				// 替换打印内容标签
				print_content = replaceOrderPrint(print_content, shopconfig,
						goodsorder);
				// 标识订单已打印
				Map map = new HashMap();
				map.put("order_id", id);
				if (type.equals("1")) {
					map.put("print_gouwu", "0");
				} else {
					map.put("print_fahuo", "0");
				}
				this.goodsorderService.updateState(map);
			}
		}
		out.write(print_content);
	}

	/**
	 * 修改打印状态
	 * 
	 * @throws IOException
	 */
	public void updateState() throws IOException {
		HttpServletRequest request = getRequest();
		String id = request.getParameter("order_id");
		String type = request.getParameter("type");
		// 标识订单已打印
		Map map = new HashMap();
		map.put("order_id", id);
		if (type.equals("1")) {
			map.put("print_gouwu", "0");
		} else if (type.equals("2")) {
			map.put("print_fahuo", "0");
		} else {
			map.put("print_kuaidi", "0");
		}
		this.goodsorderService.updateState(map);
	}

	/**
	 * 方法描述：配送单打印
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "sendPrint", results = { @Result(name = "sendPrint", location = "/WEB-INF/template/manager/admin/Goodsorder/sendprint.ftl") })
	public String sendPrint() throws Exception {
		String id = this.goodsorder.getOrder_id();
		if (id == null || id.equals("")) {
			goodsorder = new Goodsorder();
		} else {
			goodsorder = this.goodsorderService.get(id);
			order_type = goodsorder.getOrder_type();
			order_id_s = goodsorder.getOrder_id();
			// 获取订单商品详细信息
			gOrderDetaiInfo(id, order_type);
			memberuser = memberuserService.getPKByCustID(goodsorder
					.getBuy_cust_id());
			member = memberService.get(goodsorder.getSell_cust_id());
			shopconfig = shopconfigService.getByCustID(goodsorder
					.getSell_cust_id());
			// 获取打印样式信息
			gOrderPrintStyle("0", print_temp_fahuo);
			if (print_content != null && !"".equals(print_content)) {
				// 替换打印内容标签
				print_content = replaceOrderPrint(print_content, shopconfig,
						goodsorder);
			}
		}
		return "sendPrint";
	}

	/**
	 * 方法描述：联合打印购物单和配送单
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "combinedPrint", results = { @Result(name = "combinedPrint", location = "/WEB-INF/template/manager/admin/Goodsorder/combinedprint.ftl") })
	public String combinedPrint() throws Exception {
		// 联合打印最总输出打印内容
		String combPrintContent = "";
		String id = this.goodsorder.getOrder_id();
		if (id == null || id.equals("")) {
			goodsorder = new Goodsorder();
		} else {
			goodsorder = this.goodsorderService.get(id);
			order_type = goodsorder.getOrder_type();
			// 获取订单商品详细信息
			gOrderDetaiInfo(id, order_type);
			memberuser = memberuserService.getPKByCustID(goodsorder
					.getBuy_cust_id());
			member = memberService.get(goodsorder.getSell_cust_id());
			shopconfig = shopconfigService.getByCustID(goodsorder
					.getSell_cust_id());

			// 获取购物单打印样式信息
			gOrderPrintStyle("0", print_temp_gouwu);
			if (print_content != null && !"".equals(print_content)) {
				// 替换打印内容标签
				print_content = replaceOrderPrint(print_content, shopconfig,
						goodsorder);
			}
			// 获取购物单内容
			if (print_content != null && !"".equals(print_content)) {
				combPrintContent = print_content;
				// 清空之前购物单样式
				print_content = "";
			}
			// 获取配送单打印样式信息
			gOrderPrintStyle("0", print_temp_fahuo);
			if (print_content != null && !"".equals(print_content)) {
				// 替换打印内容标签
				print_content = replaceOrderPrint(print_content, shopconfig,
						goodsorder);
			}
			// 获取购物单内容
			if (print_content != null && !"".equals(print_content)) {
				combPrintContent = combPrintContent + "<br/><br/>"
						+ print_content;
				// 清空之前购物单样式
				print_content = "";
			}
			// 将获取的购物单和配送单打印输出
			print_content = combPrintContent;

		}
		return "combinedPrint";
	}

	/**
	 * 方法描述：快递单打印
	 * 
	 * @return
	 * @throws Exception
	 */
	public String expressPrint() throws Exception {
		expressView();
		if (!ValidateUtil.isRequired(order_send_mode)) {
			printstyle = printstyleService.get(order_send_mode);
			if (printstyle != null
					&& !ValidateUtil.isRequired(printstyle.getPrint_content()))
				print_content = replaceExpress(shopconfig, goodsorder,
						printstyle.getPrint_content());
		} else {
			this.addFieldError("order_send_mode", "请选择物流公司");
		}
		return goUrl("expressprint");
	}

	/**
	 * @author:HXM
	 * @date:May 26, 201411:19:15 AM
	 * @param:
	 * @Description:打印票据，功能填充打印内容,使用ajax访问这个地方
	 */
	public void printInvoice() throws Exception {
		HttpServletResponse response = getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if (!ValidateUtil.isRequired(flag_invoice_id)) {
			shopconfig = shopconfigService.getByCustID(this.session_cust_id);
			gOrderDetaiInfo(goodsorder.getOrder_id(), goodsorder
					.getOrder_type());
			Goodsorder gorder = this.goodsorderService.get(goodsorder
					.getOrder_id());
			Invoice invoice = invoiceService.get(flag_invoice_id);
			if (invoice != null
					&& !ValidateUtil.isRequired(invoice.getPrint_content())) {
				print_content = replaceInvoicePrint(invoice.getPrint_content(),
						shopconfig, gorder);
				PrintWriter out = response.getWriter();
				out.println(print_content);
			}
		}
	}

	/**
	 * @author:HXM
	 * @date:May 16, 20141:01:43 PM
	 * @Description:打印确认信息
	 */
	public String expressView() throws Exception {
		if (ValidateUtil.isRequired(goods_order_ids)) {
			goodsorder = new Goodsorder();
		} else {
			goodsorder = this.goodsorderService.get(goods_order_ids);
			// 获取订单商品详细信息
			gOrderDetaiInfo(goods_order_ids, goodsorder.getOrder_type());
			order_area = AreaFuc.getAreaNameByMap(goodsorder
					.getArea_attr());
			// 获取买家会员信息
			memberuser = memberuserService.getPKByCustID(goodsorder
					.getBuy_cust_id());
			Map gMap = new HashMap();
			gMap.put("goodsorder_id", goods_order_ids);
			getOrder(gMap);
			gOrderSendmode();
			//获取快递单打印样式模板
			sendmode = this.sendmodeService.get(goodsorder.getSend_mode());
			print_template_code = this.printstyleService.get(sendmode.getTrade_id()).getTemplate_code();
			// 获取卖家店铺信息
			shopconfig = shopconfigService.getByCustID(goodsorder
					.getSell_cust_id());
			if (shopconfig.getArea_attr() != null
					&& !shopconfig.getArea_attr().equals("")) {
				AreaFuc areaFuc = new AreaFuc();
				shopconfig_area_attr = areaFuc
						.getAreaNameByMap(shopconfig.getArea_attr());
			}
		}
		return goUrl("expressprint");
	}

	/**
	 * @author:HXM
	 * @date:May 16, 20142:37:05 PM
	 * @param:shopconfig:店铺信息，goodsorder:订单信息，replaceString:打印订单代码信息
	 * @throws ParseException
	 * @Description:替换(填写)快递单信息
	 */
	private String replaceExpress(Shopconfig shopconfig, Goodsorder goodsorder,
			String replaceString) throws ParseException {
		if (!ValidateUtil.isRequired(replaceString)) {
			// 卖家信息
			if (shopconfig != null) {
				String send_name = "", send_mobile = "",send_phone="", send_area = "", send_address = "", send_postcode = "", send_compiny = "";
				if (!ValidateUtil.isRequired(shopconfig.getContant_man())) {
					send_name = shopconfig.getContant_man();
				}
				//寄件人手机号码
				if (!ValidateUtil.isRequired(shopconfig.getMobile())) {
					send_mobile = shopconfig.getMobile();
				}
				//寄件人固定电话
				if (!ValidateUtil.isRequired(shopconfig.getPhone())) {
					send_phone = shopconfig.getPhone();
				}
				if (!ValidateUtil.isRequired(shopconfig.getAddress())) {
					// 寄件人详细地址
					send_address = shopconfig.getAddress().replace(" ", "");
				}
				if (!ValidateUtil.isRequired(shopconfig.getArea_attr())) {
					// 寄件人地区
					send_area = AreaFuc.getAreaNameByMap(
							shopconfig.getArea_attr()).replace(" ", "");
				}
                
				//寄件人邮编
				if (!ValidateUtil.isRequired(shopconfig.getPostcode())) {
					send_postcode = shopconfig.getPostcode();
				}
				
				replaceString = replaceString
						.replace("${send_name}", send_name);
				replaceString = replaceString.replace("${send_phone}",
						send_phone);
				replaceString = replaceString.replace("${send_address}",
						send_address);
				
				replaceString = replaceString.replace("${send_postcode}",send_postcode);//邮政编码
				replaceString = replaceString.replace("${signature_date}", DateUtil.getCurrentTime());// 寄件人签名时间
				replaceString = replaceString.replace("${send_signature}",send_name);// 寄件人签名
				
				replaceString = replaceString
						.replace("${send_area}", send_area);// 寄件人签名
				replaceString = replaceString.replace("${date_time}",
						timeString);// 寄件人签名s

			}
			// 基本上是买家信息
			if (goodsorder != null) {
				String send_remark = "", order_id = "",order_weight="", receive_name = "", receive_phone = "",receive_county="", receive_address = "", receive_postcode = "", receive_area = "", send_num = "", o_info = "", o_num = "", business_type = "", business_type_str = "";
				if (!ValidateUtil.isRequired(goodsorder.getSell_remark())) {
					send_remark = goodsorder.getSell_remark();
				}
				if (!ValidateUtil.isRequired(goodsorder.getOrder_id())) {
					order_id = goodsorder.getOrder_id();
				}
				if (!ValidateUtil.isRequired(goodsorder.getConsignee())) {
					receive_name = goodsorder.getConsignee();
				}
				if (!ValidateUtil.isRequired(goodsorder.getMobile())) {
					receive_phone = goodsorder.getMobile();
				}

				if (!ValidateUtil.isRequired(goodsorder.getZip_code())) {
					receive_postcode = goodsorder.getZip_code();
				}
				if (!ValidateUtil.isRequired(goodsorder.getSend_num())) {
					send_num = goodsorder.getSend_num();
				}
				//订单商品总重量：
				if (!ValidateUtil.isRequired(goodsorder.getOrder_weight())) {
					order_weight = goodsorder.getOrder_weight();
				}
				
				if (!ValidateUtil.isRequired(goodsorder.getBusiness_type())) {
					business_type = goodsorder.getBusiness_type();
					if ("1".equals(business_type)) {
						business_type_str = "标准快递";
					} else if ("4".equals(business_type)) {
						business_type_str = "经济快递";
					}
				}

				Map oMap = new HashMap();
				oMap.put("order_id", order_id);
				orderdetaiList = this.orderdetailService.getList(oMap);
				Iterator oIterator = orderdetaiList.iterator();
				int o_nums = 0;
				while (oIterator.hasNext()) {
					Map dMap = (Map) oIterator.next();
					if (dMap.get("goods_attr") != null
							&& !dMap.get("goods_attr").toString().equals("")) {
						o_info += dMap.get("goods_attr").toString() + " X"
								+ dMap.get("order_num").toString();
					}
					o_nums = Integer.parseInt(dMap.get("order_num").toString())
							+ o_nums;
				}
				o_num = o_nums + "";
				// 卖家备注
				replaceString = replaceString.replace("${send_remark}",
						send_remark);
				if (!ValidateUtil.isRequired(goodsorder.getBuy_address())) {
					receive_address = goodsorder.getBuy_address().replace(" ",
							"");
				}
				if (!ValidateUtil.isRequired(goodsorder.getArea_attr())) {
					receive_area = AreaFuc.getAreaNameByMap(
							goodsorder.getArea_attr()).replace(" ", "");
					receive_county = AreaFuc.getLastAreaName(goodsorder.getArea_attr());
					
				}
				replaceString = replaceString.replace("${order_id}", order_id);
				replaceString = replaceString.replace("${receive_name}",
						receive_name);
				replaceString = replaceString.replace("${receive_phone}",
						receive_phone);
				replaceString = replaceString.replace("${receive_address}",
						receive_address);
				replaceString = replaceString.replace("${receive_postcode}",
						receive_postcode);
				replaceString = replaceString.replace("${receive_area}",
						receive_area);
				replaceString = replaceString.replace("${receive_county}",
						receive_county);
				replaceString = replaceString.replace("${send_num}", send_num);
				replaceString = replaceString.replace("${order_weight}", order_weight);
				replaceString = replaceString.replace("${o_info}", o_info);
				replaceString = replaceString.replace("${o_num}", o_num);
				replaceString = replaceString.replace("${business_type}",
						business_type_str);

			}
		}

		return replaceString;
	}

	/**
	 * 方法描述：购物单替换打印内容
	 * 
	 * @return
	 * @throws Exception
	 */
	public String replaceOrderPrint(String repConten,
			Shopconfig prt_Shopconfig, Goodsorder prt_goodsorder)
			throws Exception {
		String str_shopname = "", str_shopurl = "", str_kefu = "", str_phone = "-", str_order_num = "-", str_order_date = "-", str_goods_allprice = "0.0", str_send_price = "0.0", str_insured_price = "0.0", str_pay_charge = "0.0", str_buy_remark = "-", str_sell_remark = "-", str_total_good_money = "0.0", str_Powered = "", str_send_distribution = "-", str_send_consignee = "-", str_send_telphone = "-", str_send_mobile = "-", str_send_address = "-", str_send_zipcode = "-", str_seller_nick = "", str_buyer_nick = "", str_total_pay_money = "0.0";

		if (repConten != null) {
			// 店铺信息
			if (prt_Shopconfig != null) {
				// 获取店铺名称
				if (prt_Shopconfig.getShop_name() != null) {
					str_shopname = prt_Shopconfig.getShop_name();
				}
				// 获取店铺URL地址
				if (prt_Shopconfig.getShop_site() != null) {
					str_shopurl = prt_Shopconfig.getShop_site();
				}
				// 获取店铺QQ
				if (prt_Shopconfig.getQq() != null) {
					str_kefu = prt_Shopconfig.getQq();
				}
				// 获取店铺电话
				if (prt_Shopconfig.getPhone() != null) {
					str_phone = prt_Shopconfig.getPhone();
				}
			}

			int num = 0;// 计算购买数量
			String goods_id = "",goods_no="", goods_name = "", price = "", buy_num = "", filedrowlists = "", item_meal_name1 = "", goods_price = "";
			int begin = repConten.indexOf("${filedrowlist}");
			int end = repConten.indexOf("${/filedrowlist}");
			if (begin > -1) {
				String x1, x2, x3;
				if (orderdetaiList != null && orderdetaiList.size() > 0) {
					String filedrowlist = repConten.substring(begin, end + 16);
					filedrowlist = filedrowlist.replace("${filedrowlist}", "");
					filedrowlist = filedrowlist.replace("${/filedrowlist}", "");
					// 循环替换商品详细信息
					for (int i = 0; i < orderdetaiList.size(); i++) {
						String strfiled = filedrowlist;
						Map taoreMap = (Map) orderdetaiList.get(i);
						goods_id = dealrepalceNull(taoreMap.get("goods_id"));
						comboName(goods_id);
						goods_no = dealrepalceNull(taoreMap.get("goods_no"));//商品编号
						goods_name = dealrepalceNull(taoreMap.get("goods_name"));// 商品名称

						if(taoreMap.get("goods_attr")!=null&&!"".equals(taoreMap.get("goods_attr").toString().trim())){
							//如果商品有规格，就加上选择的规格
							goods_name=goods_name+"["+taoreMap.get("goods_attr")+"]";
						}
						price = dealrepalceNull(taoreMap.get("goods_price"));// 单价
						buy_num = dealrepalceNull(taoreMap.get("order_num"));
						num += Integer.valueOf(buy_num);// 计算购买数量
						goods_price = dealrepalceNull(Float.parseFloat(price)*Float.parseFloat(buy_num));
						strfiled = strfiled.replace("${goods_no}",// 货号
								goods_no);
						strfiled = strfiled.replace("${goods_name}",// 商品名称
								goods_name);
						strfiled = strfiled.replace("${goods_price}",// 单价
								price);
						strfiled = strfiled.replace("${buy_number}",// 购买数量数量
								buy_num);
						strfiled = strfiled.replace("${goods_allprice}",
								goods_price); // 成交金额
						strfiled = strfiled.replace("${item_meal_name}",
								combo_name);
						filedrowlists += strfiled;
					}
				}
				// 拼装打印内容
				x1 = repConten.substring(0, begin);
				x2 = filedrowlists;
				x3 = repConten.substring(end + 16, repConten.length());
				repConten = x1 + x2 + x3;
			}

			// 订单信息
			if (prt_goodsorder != null) {
				// 卖家昵称
				if (!ValidateUtil.isRequired(prt_goodsorder.getBuy_cust_id())) {
					Memberuser seller = memberuserService
							.getPKByCustID(prt_goodsorder.getSell_cust_id());
					if (seller != null)
						str_seller_nick = seller.getUser_name();
				}
				// 买家昵称
				if (!ValidateUtil.isRequired(prt_goodsorder.getBuy_cust_id())) {
					Memberuser buyer = memberuserService
							.getPKByCustID(prt_goodsorder.getBuy_cust_id());
					if (buyer != null)
						str_buyer_nick = buyer.getUser_name();
				}
				// 获取订单号
				if (prt_goodsorder.getOrder_id() != null) {
					str_order_num = prt_goodsorder.getOrder_id();
				}
				// 获取下订单日期
				if (prt_goodsorder.getOrder_time() != null) {
					str_order_date = prt_goodsorder.getOrder_time();
				}
				// 获取商品总额
				if (prt_goodsorder.getGoods_amount() != null) {
					str_goods_allprice = prt_goodsorder.getGoods_amount()
							.toString();
				}
				// 获取配送费用
				if (prt_goodsorder.getShip_free() != null) {
					str_send_price = prt_goodsorder.getShip_free().toString();
				}
				// 获取订单保价费用
				if (prt_goodsorder.getInsured() != null) {
					str_insured_price = prt_goodsorder.getInsured().toString();
				}
				// 获取手续费
				if (prt_goodsorder.getComm_free() != null) {
					str_pay_charge = prt_goodsorder.getComm_free().toString();
				}
				// 获取买家备注
				if (prt_goodsorder.getMem_remark() != null) {
					str_buy_remark = prt_goodsorder.getMem_remark();
				}
				// 获取卖家备注
				if (prt_goodsorder.getSell_remark() != null) {
					str_sell_remark = prt_goodsorder.getSell_remark();
				}
				// 商品原价值
				if (prt_goodsorder.getTatal_amount() != null) {
					str_total_good_money = prt_goodsorder.getTatal_amount()
							.toString();
				}
				// 会员实际付款值
				if (prt_goodsorder.getTatal_amount() != null) {
					str_total_pay_money = prt_goodsorder.getTatal_amount()
							.toString();
				}
				// 物流公司
				if (prt_goodsorder.getSend_mode() != null) {
					String send_mode_name = "";
					Sendmode gSendmode = new Sendmode();
					if (prt_goodsorder.getSend_mode() != null
							&& !"".equals(prt_goodsorder.getSend_mode())) {
						gSendmode = sendmodeService.get(prt_goodsorder
								.getSend_mode());
						if (gSendmode != null) {
							send_mode_name = gSendmode.getSmode_name()
									.toString();
						}
					}
					str_send_distribution = send_mode_name;
				}
				// 收货人名称
				if (prt_goodsorder.getConsignee() != null) {
					str_send_consignee = prt_goodsorder.getConsignee()
							.toString();
				}
				// 收货人电话
				if (prt_goodsorder.getTelephone() != null) {
					str_send_telphone = prt_goodsorder.getTelephone()
							.toString();
				}
				// 收货人手机
				if (prt_goodsorder.getMobile() != null) {
					str_send_mobile = prt_goodsorder.getMobile().toString();
				}
				// 收货人地址
				if (prt_goodsorder.getBuy_address() != null) {
					String send_area_name = AreaFuc
							.getAreaNameByMap(prt_goodsorder.getArea_attr());
					str_send_address = send_area_name + "--"
							+ prt_goodsorder.getBuy_address().toString();
				}
				// 邮编
				if (prt_goodsorder.getZip_code() != null) {
					str_send_zipcode = prt_goodsorder.getZip_code().toString();
				}

			}
			str_Powered = cfg_webname;
			// 执行替换参数
			repConten = repConten.replace(print_shopname, str_shopname);// 店铺名称
			repConten = repConten.replace(print_shopurl, str_shopurl);// 店铺链接地址
			repConten = repConten.replace(print_kefu, str_kefu); // 客服
			repConten = repConten.replace(print_phone, str_phone); // 店铺电话
			repConten = repConten.replace(print_order_num, str_order_num);// 订单号
			repConten = repConten.replace(print_order_date, str_order_date);// 下单时间
			repConten = repConten.replace(print_goods_allprice,
					str_goods_allprice); // 商品总金额
			repConten = repConten.replace(print_send_price, str_send_price);// 配送费用
			repConten = repConten.replace(print_insured_price,
					str_insured_price); // 保价费用
			repConten = repConten.replace(print_pay_charge, str_pay_charge);// 支付手续费
			repConten = repConten.replace(print_note, str_buy_remark); // 买家备注
			repConten = repConten.replace(print_total_all_money, // 订单总金额
					str_total_good_money);
			repConten = repConten.replace(print_Powered, str_Powered);// 技术支持
			repConten = repConten.replace(print_send_distribution, // 配送
					str_send_distribution);
			repConten = repConten.replace(print_send_consignee, // 收货人
					str_send_consignee);
			repConten = repConten.replace(print_send_telphone, // 收货人电话
					str_send_telphone);
			repConten = repConten.replace(print_send_mobile, str_send_mobile);// 收货人手机
			repConten = repConten.replace(print_send_address, str_send_address);// 收货地址
			repConten = repConten.replace(print_send_zipcode, str_send_zipcode);// 收货人邮编
			repConten = repConten.replace("${seller_nick}", str_seller_nick);// 卖家昵称
			repConten = repConten.replace("${buyer_nick}", str_buyer_nick);// 买家昵称
			repConten = repConten.replace("${payment}", // 订单实付款
					str_total_pay_money);
			repConten = repConten.replace("${num}", num + "");// 订单购买总数
			repConten = repConten.replace("${seller_memo}", str_sell_remark);// 卖家备注
		}
		return repConten;
	}

	/**
	 * @Method Description :处理判断统一方法，如果为空直接返回空值
	 * @author: HXK
	 * @date : Dec 12, 2014 11:14:09 AM
	 * @param
	 * @return return_type
	 */
	private String dealrepalceNull(Object Str) {
		String reString = "";
		if (Str != null && !"".equals(Str)) {
			reString = Str.toString();
		}
		return reString;
	}

	/**
	 * 方法描述：购物单替换打印内容
	 * 
	 * @return
	 * @throws Exception
	 */
	public String replaceInvoicePrint(String repConten,
			Shopconfig prt_Shopconfig, Goodsorder prt_goodsorder)
			throws Exception {
		String invoice_cust = "", invoice_pay = "", invoice_biypay = "", invoice_man = "";

		if (repConten != null) {
			// 店铺信息
			if (prt_Shopconfig != null) {
				// 获取店铺名称
				if (prt_Shopconfig.getContant_man() != null) {
					invoice_man = prt_Shopconfig.getContant_man();
				}
			}

			String invoice_content = "";
			if (orderdetaiList != null && orderdetaiList.size() > 0) {
				String goods_name = "", price = "", buy_num = "", goods_price = "", goods_id = "";
				// 循环替换商品详细信息
				for (int i = 0; i < orderdetaiList.size(); i++) {
					Map taoreMap = (Map) orderdetaiList.get(i);
					goods_id = dealrepalceNull(taoreMap.get("goods_id"));
					comboName(goods_id);
					goods_name = dealrepalceNull(taoreMap.get("goods_name"));
					price = dealrepalceNull(taoreMap.get("goods_price"));
					buy_num = dealrepalceNull(taoreMap.get("order_num"));
					goods_price = dealrepalceNull(taoreMap.get("goods_price"));
					invoice_content += goods_name + " "; // 商品名称
					invoice_content += price + " "; // 单价
					invoice_content += buy_num + " "; // 购买数量数量
					invoice_content += goods_price + " "; // 成交金额
					invoice_content += combo_name + " ";

				}
			}
			// 订单信息
			if (prt_goodsorder != null) {
				// 收货人名称
				if (prt_goodsorder.getConsignee() != null) {
					invoice_cust = prt_goodsorder.getConsignee().toString();
				}
				// 会员实际付款值
				if (prt_goodsorder.getTatal_amount() != null) {
					invoice_pay = prt_goodsorder.getTatal_amount().toString();
					// 处理合计(大写)问题
					NumberFormateFuc nf = new NumberFormateFuc(invoice_pay);
					invoice_biypay = nf.Convert();
				}
			}
			// 处理开票时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String invoice_date = sdf.format(new Date());

			// 执行替换参数
			repConten = repConten.replace("${invoice_pay}", invoice_pay);// 合计（小写）
			repConten = repConten.replace("${invoice_biypay}", invoice_biypay); // 合计（大写）
			repConten = repConten.replace("${invoice_date}", invoice_date); // 开票日期
			repConten = repConten.replace("${invoice_category}", "商业"); // 行业分类
			repConten = repConten.replace("${invoice_cust}", invoice_cust);// 客户名称
			repConten = repConten.replace("${invoice_man}", invoice_man); // 开票人
			repConten = repConten
					.replace("${invoice_content}", invoice_content);// 项目/单价/数量/金额
		}
		return repConten;
	}

	/**
	 * 方法描述：获取订单相关商品信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void gOrderDetaiInfo(String order_id, String order_type) {
		Map orderdetailMap = new HashMap();
		orderdetailMap.put("order_id", order_id);
		// order_type 0:虚拟 1:普通 2:积分 3:秒杀 4:套餐 5:团购6:预售
		if (order_type.equals("6")) {
			// 预售订单详情
			orderdetaiList = this.directorderdetailService
					.getList(orderdetailMap);
		} else {
			// 普通订单详情
			orderdetaiList = this.orderdetailService.getList(orderdetailMap);
		}
	}

	/**
	 * @author : HXK
	 * @throws IOException
	 * @Method Description :AJAX获取商品详情
	 */
	public void ajaxgoodsdetaillist() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 兼容火狐文本输出
		response.setContentType("text/html;chartset=UTF-8;");
		Map orderdetailMap = new HashMap();
		orderdetailMap.put("order_id", order_id_s);
		orderdetaiList = this.orderdetailService.getList(orderdetailMap);
		orderdetaiList = ToolsFuc.replaceList(orderdetaiList, "");
		String jsonStr = JsonUtil.list2json(orderdetaiList);
		PrintWriter out = response.getWriter();
		out.write(jsonStr);
	}

	/**
	 * @author : HXK
	 * @throws IOException
	 * @Method Description :AJAX获取订单物流信息
	 */
	public void ajaxlogistics() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String jsonStr = "";
		String o_id = request.getParameter("o_id");
		if (o_id != null && !"".equals(o_id)) {
			goodsorder = this.goodsorderService.get(o_id);
			Sendmode sendmode = new Sendmode();
			if (sendmode != null) {
				// 获取快递公司代码
				if(!validateFactory.isRequired(sendmode.getEn_name())){
					kuai_company_code = sendmode.getEn_name().trim();
				}
				// 获取快递公司名称
				if(!validateFactory.isRequired(sendmode.getSmode_name())){
					kuai_company = sendmode.getSmode_name().trim();
				}
			}
			kuai_number = goodsorder.getSend_num();
			if (!validateFactory.isRequired(kuai_number)) {
				kuai_number = kuai_number.trim();
			}
			// 查询快递信息
			if(!validateFactory.isRequired(kuai_company_code)&&!validateFactory.isRequired(kuai_number)){
				jsonStr = LogisticsFuc.hundredTrace(kuai_company_code, kuai_number);
			}
		}
		PrintWriter out = response.getWriter();
		out.write(jsonStr);
	}
	/**
	 * @author : HXK
	 * @throws IOException
	 * @Method Description :AJAX获取订单物流信息
	 */
	public void ajaxlogisticsJson() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String jsonStr = "";
		String o_id = request.getParameter("o_id");
		if (o_id != null && !"".equals(o_id)) {
			goodsorder = this.goodsorderService.get(o_id);
			Sendmode sendmode = new Sendmode();
			sendmode = sendmodeService.get(goodsorder.getSend_mode());
			if (sendmode != null) {
				// 获取快递公司代码
				kuai_company_code = sendmode.getEn_name().trim();
				// 获取快递公司名称
				kuai_company = sendmode.getSmode_name().trim();
			}
			kuai_number = goodsorder.getSend_num();
			if (!validateFactory.isRequired(kuai_number)) {
				kuai_number = kuai_number.trim();
			}
			if(!ValidateUtil.isRequired(kuai_number)&&!ValidateUtil.isRequired(kuai_number)){
				// 查询快递信息
				jsonStr = LogisticsFuc.hundredTraceAPI(kuai_company_code, kuai_number,"0");
			}
		}
		PrintWriter out = response.getWriter();
		out.write(jsonStr);
	}

	/**
	 * 方法描述：获取打印模版
	 * 
	 * @return
	 * @throws Exception
	 */
	public void gOrderPrintStyle(String cust_id, String template_code) {
		Map orderMap = new HashMap();
		orderMap.put("cust_id", cust_id);
		orderMap.put("template_code", template_code);
		printstyleList = printstyleService.getList(orderMap);
		if (printstyleList != null&&printstyleList.size()>0) {
			HashMap pstyle = new HashMap();
			pstyle = (HashMap) printstyleList.get(0);
			if (pstyle != null && pstyle.get("print_content") != null) {
				// 获取打印模版内容
				print_content = pstyle.get("print_content").toString();
			}
		}
	}

	/**
	 * 方法描述：获取订单异动信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void gordertransInfo(String order_id) {
		Map orderMap = new HashMap();
		orderMap.put("order_id", order_id);
		ordertransList = ordertransService.getList(orderMap);
	}

	
	/**
	 * 方法描述：获取打印模版
	 * 
	 * @return
	 * @throws Exception
	 */
	public void gsendPrintstyle() {
		Map orderMap = new HashMap();
		printstyleList = printstyleService.getList(orderMap);
	}

	
	/**
	 * @Method Description :修改运费
	 * @author : HZX
	 * @throws Exception
	 * @date : May 7, 2014 2:02:19 PM
	 */
	public String updateShip() throws Exception {
		order_id_s = ship_oid;
		goodsorder = this.goodsorderService.get(ship_oid);
		if (validateFactory.isRequired(ship_oid) || goodsorder == null) {
			this.addActionMessage("运费修改失败！");
			return isMember();
		}
		if (validateFactory.isRequired(newship_free)) {
			this.addFieldError("newship_free", "新运费不能为空！");
			return isMember();
		}
		goodsorder.setShip_free(Double.parseDouble(newship_free));
		double tatal_amount = goodsorder.getTatal_amount();
		tatal_amount += Double.parseDouble(newship_free)
				- Double.parseDouble(oldship_free);
		goodsorder.setTatal_amount(tatal_amount);
		this.goodsorderService.update(goodsorder);
		this.addActionMessage("订单号:" + ship_oid + "运费修改成功！");
		return isMember();
	}

	/**
	 * @Method Description :
	 * @author : HZX
	 * @throws Exception
	 * @date : May 7, 2014 4:54:47 PM
	 */
	public String isMember() throws Exception {
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			return sellerorderlist();
		} else {
			return operatorslist();
		}
	}

	/**
	 * 买家跳转到详细页
	 */
	public void commonSellResponse(String order_id, String order_state)
			throws IOException {
		getResponse().sendRedirect(
				"/bmall_Goodsorder_sellerOrderView.action?goodsorder.order_id="
						+ order_id + "&order_state=" + order_state);
	}

	/**
	 * @Method Description :导出订单
	 * @author : HZX
	 * @date : Sep 10, 2014 3:15:41 PM
	 */
	public String export() throws Exception {
		commonList("1", "order_state", "");
		this.goodsorderService.exportExcel(goodsorderList, getResponse());
		this.addActionMessage("订单导出成功!");
		return operatorslist();
	}

	/**
	 * @Method Description :
	 * @author : HZX
	 * @date : Sep 10, 2014 4:45:12 PM
	 */
	public String sfExport() throws Exception {
		is_page = true;
		sfList();
		this.goodsorderService.exportOrderExcel(goodsorderList, getResponse());
		this.addActionMessage("订单导出成功!");
		is_page = false;
		return sfList();
	}

	/**
	 * @Method Description :
	 * @author : HZX
	 * @throws Exception
	 * @date : Sep 16, 2014 1:27:30 PM
	 */
	public String iCvs() throws Exception {
		if (iname == null || "".equals(iname)) {
			this.addActionMessage("请选择要导入的文件!");
		} else {
			String rootPath = PropertiesUtil.getRootpath();// 获取项目的根目录
			iname = rootPath + "" + iname;
			ordertb = this.goodsorderService.iCvs(iname);
			if (!ordertb.equals("")) {
				this.addActionMessage("导入成功!");
			} else {
				this.addActionMessage("导入失败!请检查文件格式。");
			}

		}
		is_import = "1";
		return sfList();
	}

	 /**
		 * @Method Description :扫码枪扫描出库
		 * @author: HXK
		 * @date : Aug 28, 2014 9:55:45 AM
		 * @return return_type
		 */
		public String scanorder() throws Exception {
			//获取销售平台列表
			return goUrl("confirmationsend");
		}
		/**
		 * 方法描述：扫码枪新增商品出库
		 * 
		 * @return
		 * @throws Exception
		 */
		public String scaniupdateorder() throws Exception {
			String tempout="",mesout="",stateout="",h_order_id="";
		    if(hidden_goods_order_codenumber!=null&&!"".equals(hidden_goods_order_codenumber)){
		    	h_order_id=hidden_goods_order_codenumber;
		    	goodsorder=goodsorderService.get(hidden_goods_order_codenumber);
		    	if(goodsorder!=null){
		    		//Print_fahuo:0是1否2配送单已出库
		    		if(goodsorder.getPrint_fahuo()!=null&&goodsorder.getPrint_fahuo().equals("2")){
		    			//该订单已经出库
			    		stateout="<font color='red'>出库失败</font>";
			    		mesout="该订单已经扫描确认出库,无需重复扫描操作!";
		    		}else if(goodsorder.getPrint_fahuo()!=null&&goodsorder.getPrint_fahuo().equals("1")){
		    			//该订单尚未打印出库单
			    		stateout="<font color='red'>出库失败</font>";
			    		mesout="该订单尚未打印出库单,请先打印出库单操!";
		    		}else{
		    			
		    			//执行更新操作
		    			goodsorder.setPrint_fahuo("2");
		    			goodsorderService.update(goodsorder);
		    			//执行确认扫描出库操作
			    		stateout="<font color='green'>出库成功</font>";
			    		mesout="出库成功!";
		    		}
		    	}else {
		    		//找不到该商品
		    		stateout="<font color='red'>出库失败</font>";
		    		mesout="找不到该订单号,请确认扫描是否正确!";
				}
		    }else {
		    	//找不到该商品
		    	h_order_id="请扫描输入";
	    		stateout="<font color='red'>出库失败</font>";
	    		mesout="请扫描输入订单号!";
			}
		  //找不到该商品
    		tempout+="<tr><td>"+h_order_id+"</td>"
    		 			+"<td>"+stateout+"</td>"
    		 			+"<td>"+mesout+"</td></tr>";
		    outGoodsInfo=tempout+outGoodsInfo;
			this.goodsorder = null;
			return scanorder();
		}
	 
	 
	 
	/**
	 * @author Administrator 修改人：QJY 修改日期：2014-12-24 平安夜
	 * @throws Exception
	 */
	public void ajaxGetDaYin() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 兼容火狐文本输出
		response.setContentType("text/html;chartset=UTF-8;");
		PrintWriter out = response.getWriter();
		String oid = request.getParameter("oid");
		String print_template_code = request.getParameter("print_template_code");
		goodsorder = this.goodsorderService.get(oid);
		shopconfig = shopconfigService
				.getByCustID(goodsorder.getSell_cust_id());
		printstyle = printstyleService.getTemplate_code(print_template_code);
		if (printstyle != null
				&& !ValidateUtil.isRequired(printstyle.getPrint_content()))
			print_content = replaceExpress(shopconfig, goodsorder, printstyle
					.getPrint_content());
		out.write(print_content);
		
	}


	/**
	 * 
	 * @throws Exception
	 */
	public void ajaxPrintkuaidi() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String oid = request.getParameter("oid");
		goodsorder = this.goodsorderService.get(oid);
		if(goodsorder != null){
			goodsorder.setPrint_kuaidi("0");//已打印
			this.goodsorderService.update(goodsorder);
			PrintWriter out = response.getWriter();
			out.write("0");
		}
		
	}
	
	
	/**
	 * 方法描述：提醒发货
	 * 
	 * @return
	 * @throws Exception
	 */
	public void remindDelivery() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		PrintWriter out = response.getWriter();
		String order_id = "",title = "",content = "";
		if(request.getParameter("order_id")!=null && !"".equals(request.getParameter("order_id"))){
			order_id = request.getParameter("order_id").toString();
		}	
		Member memberadmin= this.memberService.get("0");
		if(memberadmin!=null){
			send_cust_name_member=memberadmin.getCust_name();
		}
		Member buy_member = this.memberService.get(this.session_cust_id);
		title = "会员 "+buy_member.getCust_name()+" 提醒订单号 "+order_id+" 发货";
		sendbox = new Sendbox();
		sendbox.setTitle(title);
		sendbox.setContent(title);
		
		sendbox.setRecevie_name(send_cust_name_member);//收件人
		sendbox.setSend_cust_id(this.session_cust_id);//发件人ID
		
		sendbox.setIs_send_del("1");//是否删除 0：是 1：否
		if(sendbox.getIs_draft()==null){
			String is_draft_s="0";
		    sendbox.setIs_draft(is_draft_s);
		}
		
		String send_id = this.sendboxService.insertGetPk(sendbox);
		if(send_id !=null && !"".equals(send_id)){
			this.sendboxService.setReceivebox(this.session_cust_id, send_id);
			out.write("success");
		}
	    
	}
	
	/**
	 * @return the goodsorder
	 */
	public Goodsorder getGoodsorder() {
		return goodsorder;
	}

	/**
	 * @param Goodsorder
	 *            the goodsorder to set
	 */
	public void setGoodsorder(Goodsorder goodsorder) {
		this.goodsorder = goodsorder;
	}

	/**
	 * @return the GoodsorderList
	 */
	public List getGoodsorderList() {
		return goodsorderList;
	}

	/**
	 * @param goodsorderList
	 *            the GoodsorderList to set
	 */
	public void setGoodsorderList(List goodsorderList) {
		this.goodsorderList = goodsorderList;
	}
	

	
	/**
	 * 方法描述：获取跨境通订单状态参数
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
		Map kjtorderMap = new HashMap();
	public void kjtOrderCommparaState(String com_value) {
		kjtorderMap.put("enabled", "0");
		kjtorderMap.put("para_code", com_value);
		kjtorderMap.put("start", "0");
		kjtorderMap.put("limit", "15");
		kjtcommparaList = commparaService.getList(kjtorderMap);
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (goodsorder == null) {
			goodsorder = new Goodsorder();
		}
		String id = this.goodsorder.getOrder_id();
		if (!this.validateFactory.isDigital(id)) {
			goodsorder = this.goodsorderService.get(id);
		}
	}
	public String getState_text() {
		return state_text;
	}
	public void setState_text(String state_text) {
		this.state_text = state_text;
	}

}
