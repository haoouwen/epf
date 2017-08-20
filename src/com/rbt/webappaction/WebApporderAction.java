/*
  
 
 * Package:com.rbt.webaction
 * FileName: WeborderAction.java 
 */
package com.rbt.webappaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryParser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.browseengine.bobo.api.BrowseException;
import com.rbt.common.Md5;
import com.rbt.common.util.DateUtil;
import com.rbt.common.util.JsonUtil;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.function.CommparaFuc;
import com.rbt.function.CouponFuc;
import com.rbt.function.LogisticsFuc;
import com.rbt.function.MessageAltFuc;
import com.rbt.function.SalegoodsFuc;
import com.rbt.function.SaleorderFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Buyeraddr;
import com.rbt.model.Goods;
import com.rbt.model.Goodseval;
import com.rbt.model.Goodsshare;
import com.rbt.model.Malllevelset;
import com.rbt.model.Member;
import com.rbt.model.Memberuser;
import com.rbt.model.Goodsorder;
import com.rbt.model.Orderdetail;
import com.rbt.model.Orderinvoice;
import com.rbt.model.Ordertrans;
import com.rbt.model.Memberfund;
import com.rbt.model.Fundhistory;
import com.rbt.model.Refundapp;
import com.rbt.model.Saleorder;
import com.rbt.model.Sendmode;
import com.rbt.model.Shopconfig;
import com.rbt.service.IBuyeraddrService;
import com.rbt.service.IComsumerService;
import com.rbt.service.IDirectorderdetailService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsevalService;
import com.rbt.service.IGoodsshareService;
import com.rbt.service.IMalllevelsetService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberinterService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.IOrdertransService;
import com.rbt.service.IPaymentService;
import com.rbt.service.IRedsumerService;
import com.rbt.service.IRefundappService;
import com.rbt.service.ISaleorderService;
import com.rbt.service.ISendmodeService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IShopconfigService;
import com.rbt.service.ISysfundService;

/**
 * @author : WXP
 * @date Mar 5, 2014 1:31:22 PM
 * @Method Description :订单管理
 */
@Controller
public class WebApporderAction extends WebAppbaseAction {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 4188151694946837175L;
	/** *****************实体层******************* */
	public Member buyMember;// 买家对象
	public Goodsorder goodsorder; // 订单对象
	public Orderdetail orderdetail;// 订单详情对象
	public Ordertrans ordertrans;// 订单异动记录
	public Buyeraddr buyeraddr;// 收货地址对象
	public Memberfund memberfund;// 会员余额对象
	public Fundhistory fundhistory;// 余额流水
	public Goodseval goodseval;// 商品评价
	public Refundapp refundapp;
	public Memberuser memberuser;
	public Memberuser memberuser_seller;
	private Orderinvoice orderinvoice;
	public Saleorder saleorder;//订单促销对象
	
	/** *****************业务层接口*************** */
	@Autowired
	public IPaymentService paymentService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IMemberuserService memberuserService;
	@Autowired
	public IBuyeraddrService buyeraddrService;
	@Autowired
	public IGoodsorderService goodsorderService;
	@Autowired
	public IOrderdetailService orderdetailService;
	@Autowired
	public IOrdertransService ordertransService;
	@Autowired
	public ISendmodeService sendmodeService;
	@Autowired
	public IMemberfundService memberfundService;
	@Autowired
	public IFundhistoryService fundhistoryService;
	@Autowired
	public IShopconfigService shopconfigService;
	@Autowired
	public IGoodsevalService goodsevalService;
	@Autowired
	public IRefundappService refundappService;
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IMemberinterService memberinterService;
	@Autowired
	private ISysfundService sysfundService;
	@Autowired
	private IGoodsshareService goodsshareService;
	@Autowired
	private IDirectorderdetailService directorderdetailService;
	@Autowired
	private IMalllevelsetService malllevelsetService;
	@Autowired
	private IComsumerService comsumerService;
	@Autowired
	private IRedsumerService redsumerService;
	@Autowired
	private ISaleorderService saleorderService;

	/** *******************集合******************* */
	public List goodsList;// 商品
	public List orderList = new ArrayList();// 订单商品列表
	public List shopList = new ArrayList();// 店铺列表
	public Map shopMap=new HashMap();
	public List addrList;// 收货地列表
	public List orderPayList;// 待付款订单列表
	public int orderPayNum;// 待付款订单个数
	public List detailList;// 订单详细列表信息
	public List paymentList;
	public List goodsladderList;// 商品阶梯价格
	public List sendmodeList;// 物流公司
	public List p_contentList;
	public List z_contentList;
	public List comsumerList;//优惠券列表
	public List redsumerList;//红包列表

	/** *******************字段******************* */
	// 订单相关数据
	public String cust_id_str;// 卖家标识串
	public String goods_id_str;// 卖家标识串
	public String goods_length_str;// 店铺商品个数串
	public String trade_id_str;// 流水号标识串
	public String cookie_id_str;// cookie_id标识串
	public String goods_name_str;// 商品名称串
	public String goods_cat_str;// 商品分类串
	public String goods_img_str;// 商品图片串
	public String spec_id_str;// 规格值标识串
	public String spec_name_str;// 规格值名称串
	public String sale_price_str;// 商品单价串
	public String give_inter_str;// 商品获赠积分串
	public String order_num_str;// 订单个数串
	public String shop_name_str;// 店铺名称串
	public String shop_qq_str;// 店铺QQ
	public String radom_no_str;// 店铺随机数
	public String end_area_attr;// 物流目的地
	public String smode_id_str;// 配送方式ID
	public String ship_str;// 配送方式 方式+价格
	public String ship_name_str;// 配送方式名称
	public String ship_price_str;// 配送价格
	public String use_integral_str;//是否使用积分
	public String integral_state = "0";//积分购买状态
	public double integral_total_amount;//积分总数
	public String goods_amount_str;// 商品总价格串
	public String shop_total_amount_str;// 店铺总价格串（含运费）
	public String all_total;//订单总价
	public String ship_free_str;// 运费
	public String addr_id;// 收货地址标识
	public double total_amount;// 订单总价
	public double total_balance;//账户余额付款
	public String cust_name;// 会员名称
	public String mem_remark_str;// 买家订单备注
	public String trade_id;// 主键（更新库存用）
	public String order_type;// 订单类型
	public String is_group;// 是否团购商品
	public String group_id;// 团购标识
	public String is_direct;// 是否预售商品
	public String direct_id;// 预售标识
	public String spike_id;// 秒杀标识
	public String combo_id;// 套餐标识
	public String loc;// 跳回登录前的位置
	// 订单支付相关
	public String sub_total_price;// 待支付订单金额
	public Double use_num_pay;// 使用账户余额支付金额
	public String pay_password;// 支付密码
	public String flag;// 判断是否立即购买
	public String order_id_str;// 临时存储订单号
	public String order_id;// 订单号
	public Integer orderdetaiCount = 0;
	public String sell_cust_id; // 卖家标识
	public Shopconfig shopconfig;// 店铺信息
	public String order_goods_id_str;// 商品评价的商品ID串
	public String order_goods_feng_str;// 商品评价的商品分数
	public String order_goods_content_str;// 商品评价的内容
	public String order_goods_sharepic_str;//商品晒单图片
	public String order_service_attitude;// 卖家服务态度
	public String order_delivery_speed;// 卖家发货速度
	public String order_desc;// 描述相符
	public String order_area;// 地区
	public String v_area_attr;
	// 退款******
	public String refundapp_id;// 退款id
	public String is_get;// 是否收到货0未收到1收到
	public String is_return;// 是否退货0不退1退
	public String imgString;// 图片凭证
	public String need_refund;// 退款金额
	public String buy_refund_type;// 申请退款类型
	public boolean is_deny_num = false;
	public String buy_refund_reason;// 退款说明
	public String seller_refund_reason;// 拒绝退款说明
	public String refundDealtime;// 卖家处理时间
	public String refund_sendtime;// 买家退货时间
	public String refund_suretime;// 卖家确认收货时间
	public String sell_remark;// 卖家给买家退货地址时的留言
	public String send_mode;// 退货物流公司
	public String send_num;// 退货运单号
	public int cfg_Refund_deny_num = Integer.parseInt(SysconfigFuc
			.getSysValue("cfg_Refund_deny_num"));// 卖家拒绝退款最多次数
	public boolean is_buyer = false;// 判断是否买家
	// *******退款
	public String logistics_query;// 快递查询结果
	public String kuai_number;// 快递号
	public String kuai_company;// 快递公司
	public String kuai_company_code;// 快递公司代码
	public String seller_area_name;
	public String is_virtual;// 是否虚拟
	public String cartId_str;// 购物车商品流水号
	public String isSpike;// 判断是否是秒杀
	private String cfg_sc_pointsrule = SysconfigFuc.getSysValue("cfg_sc_pointsrule");//积分规则
	public String gotourl;//比较用于团购买自己商品时不让跳到商品详细页，而是团购详细页
	//晒单对象
	private Goodsshare goodsshare;
	
	public String goods_id;//普通商品ID
    public String is_check_mobile;//判断是否手机验证
    public String is_check_email; //判断是否邮箱验证
    public String pay_pass;//判断支付密码
    public String cfg_order_allfund = SysconfigFuc.getSysValue("cfg_order_allfund");
    public String cfg_freight = SysconfigFuc.getSysValue("cfg_freight");
    public String order_pay_tip;
    public String hasfree="0";
	public String custnum;
	public String registertype;
    public double cfg_sc_exchscale=Integer.parseInt(SysconfigFuc.getSysValue("cfg_sc_exchscale")); //积分兑换
    public double cfg_maxpay=Integer.parseInt(SysconfigFuc.getSysValue("cfg_maxpay"));  
	public String allinter;//总
	public String available_inter;//可用
	public String paytype_id; //是否使用余额
	public String is_use_inter;
	public String use_paynum;
	public double discount=0.0;
	public String inter_sub;
	//优惠券参数
	public String coupon_id;//优惠券ID
	public String coupon_goods_id;//商品ID
	public String coupon_money;//优惠金额
	public String subtotal_str;//小计
	public String comsumer_id;//
	public String coupon_cust_id;//贸易方式ID
	//红包参数
	public String red_id;//红包ID
	public String redsumer_id;
	public String red_money;//优惠金额
	public String is_ship_free;//是否包邮
	public double order_money = 0.0;//订单优惠券金额
	public String order_names; //订单活动名称
	public double shoptotal_norate;//打折后商品总价
	public String type;
	public String pay_terminal;//付款终端H5或者APP
	public String order_sign_str="";
	/**
	 * @author : WXP
	 * @param :
	 * @date Mar 14, 2014 10:01:36 AM
	 * @Method Description :跳转至实物订单页面
	 */
	public String goOrder() throws Exception {
		HttpServletRequest request = getRequest();
		// 判断是否登录
		if (this.session_cust_id.equals("") || this.session_cust_id.equals("0")) {
			getResponse().sendRedirect("/webappLogin.html?loc=" + loc+"&custnum="+custnum+"&registertype="+registertype);
			return null;
		}
		if(ValidateUtil.isRequired(goods_id_str)){
			getResponse().sendRedirect("/webapp/goods!mallcart.action");
			return null;
		}
		Memberuser mu = new Memberuser();
		if (spec_id_str!=null&&spec_id_str.endsWith(",")) {
			spec_id_str = spec_id_str + " ";
		}
		if (spec_name_str!=null&&spec_name_str.endsWith(",")) {
			spec_name_str = spec_name_str + " ";
		}
		if (goods_img_str!=null&&goods_img_str.endsWith(",")) {
			goods_img_str = goods_img_str + " ";
		}
		String[] cust_id = cust_id_str.split(",");
		String[] goods_id = goods_id_str.split(",");
		String[] goods_length = goods_length_str.split(",");
		String[] goods_name = goods_name_str.split(",");
		String[] goods_cat = goods_cat_str.split("#");
		String[] goods_img = goods_img_str.split(",");
		String[] spec_id = spec_id_str.split(",");
		String[] spec_name = spec_name_str.split(",");
		String[] sale_price = sale_price_str.split(",");
		String[] order_num = order_num_str.split(",");
		String[] shop_name = shop_name_str.split(",");
		String[] shop_qq = shop_qq_str.split(",");
		String[] radom_no = radom_no_str.split(",");
		String[] use_integral = use_integral_str.split(",");
		//商品总价
		double alltotal = 0.0;
		//获取会员对象
		Member member = this.memberService.get(this.session_cust_id);
		// 判断是否购买自己的商品
		if (isSpike != null && isSpike.equals("1")) {
			if (cust_id_str != null && !cust_id_str.equals("")
					&& cust_id_str.contains(this.session_cust_id)) {
				if(goods_id_str.contains(",")){
					getResponse().sendRedirect(
							"/webapp/goods!mallcart.action?&isBuySelf=0");
				}else
				getResponse().sendRedirect(
						"/webapp/goods!detail.action?gid=" + goods_id_str
								+ "&isBuySelf=0");
				return null;
			}
		} else {
			if (cust_id_str != null && !cust_id_str.equals("")
					&& cust_id_str.contains(this.session_cust_id)) {
				if(goods_id_str.contains(",")){
					getResponse().sendRedirect(
							"/webapp/goods!mallcart.action?&isBuySelf=0");
				}else{
					if(gotourl!=null&&!"".equals(gotourl)){
						getResponse().sendRedirect(gotourl+ "&isBuySelf=0");
					}else{
						getResponse().sendRedirect(
								"/webapp/goods!detail.action?gid=" + goods_id_str
								+ "&isBuySelf=0");
					}
				}
				return null;
			}
		}
		int x = 0, y = 0;
    	for (int i = 0; i < cust_id.length; i++) {
			Map shopMap = new HashMap();
			shopMap.put("shop_cust_id", cust_id[i].trim());
			shopMap.put("shop_name", shop_name[i].trim());
			shopMap.put("shop_qq", shop_qq[i].trim());
			shopMap.put("goods_length", goods_length[i]);
			shopMap.put("radom_no", radom_no[i].trim());
			shopList.add(shopMap);
			shopList = shopList;
			if (i == 0) {
				x = 0;
			} else {
				x += Integer.parseInt(goods_length[i - 1].trim());//
			}
			y = Integer.parseInt(goods_length_str.trim()) + x;//
			for (int j = x; j < y; j++) {
				if (spec_id[j] == null || "".equals(spec_id[j])) {
					spec_id[j] = " ";
				}
				if (spec_name[j] == null || "".equals(spec_name[j])) {
					spec_name[j] = " ";
				}
				if (goods_img[j] == null || "".equals(goods_img[j])) {
					goods_img[j] = " ";
				}
				Goods goods = this.goodsService.get(goods_id[j]);
				if(goods.getIs_ship().equals("0")){
					hasfree="1";
				}
				Map orderMap = new HashMap();
				Double tax_rate=0.0;
				if(goods.getTax_rate()!=null){
					tax_rate=Double.parseDouble(goods.getTax_rate());
				}
				orderMap.put("cust_id", cust_id[i].trim());
				orderMap.put("goods_id", goods_id[j].trim());
				orderMap.put("tax_rate", tax_rate);
				orderMap.put("goods_name", goods_name[j].trim());
				orderMap.put("goods_cat", goods_cat[j].trim());
				orderMap.put("goods_img", goods_img[j].trim());
				orderMap.put("spec_id", spec_id[j].trim());
				orderMap.put("spec_name", spec_name[j].trim());
				orderMap.put("sale_price", sale_price[j].trim());
				orderMap.put("use_integral", use_integral[j].trim());
				give_inter_str= Double.toString(Double.parseDouble(cfg_sc_pointsrule) * Double.parseDouble(sale_price[j].trim())/100);
				orderMap.put("give_inter", give_inter_str);
				orderMap.put("order_num", order_num[j].trim());;
				if (order_type != null && order_type.equals("5")) {
					orderMap.put("order_type", "5");
					orderMap.put("group_id", group_id);

				}
				alltotal += Double.valueOf(sale_price[j].trim()) * Double.valueOf(order_num[j].trim());
				orderList.add(orderMap);
				orderList = orderList;
			}

		}
    	//商品促销
		orderList = SalegoodsFuc.replaceCartgoodsList(orderList, "1", member.getBuy_level());

		//获取会员优惠券
		Map comMap = new HashMap();
		comMap.put("cust_id", this.session_cust_id);
		comMap.put("use_state", "0");
		comMap.put("now", "1");
		comMap.put("member_level", member.getBuy_level());
		comsumerList = this.comsumerService.getList(comMap);
		//判断购买商品是否有可以用的优惠券
		comsumerList = CouponFuc.couponList(comsumerList, goods_id_str, sale_price_str, order_num_str, member.getBuy_level());
		//获取会员红包
        redsumerList = this.redsumerService.getList(comMap);    	
		// 获取买家收货地址
		getBuyerAddrList();
		Map cMap = new HashMap();
		cMap.put("para_code", "z_content");
		z_contentList = commparaService.getList(cMap);
		cMap.put("para_code", "p_content");
		p_contentList = commparaService.getList(cMap);
		allinter=memberinterService.get(this.session_cust_id).getFund_num().toString();
		available_inter=Double.parseDouble(allinter)/cfg_sc_exchscale+"";
		memberfund=this.memberfundService.get(this.session_cust_id);
		String level = member.getBuy_level().trim();	
		Malllevelset malllevelset=new Malllevelset();
		malllevelset=this.malllevelsetService.get(level);
		discount=Double.parseDouble(malllevelset.getDiscount())/100;
		//订单促销
		String sale_id = SaleorderFuc.getSaleorderId("1", member.getBuy_level());
		//免运费ID
		String ship_sale_id = "";
		if(!ValidateUtil.isRequired(sale_id)) {
		//订单促销
		String[] sale_ids = sale_id.trim().split(",");
		//优惠条件
		String low_price = "";
		for(int k = 0; k < sale_ids.length; k ++ ) {
			//部分商品总价
			double goodsall = 0.0;
			saleorder = this.saleorderService.get(sale_ids[k]);
			if(saleorder != null) {
				//计算指定商品总价
				if(saleorder.getTerm_state().equals("3")) {
					for(int i = 0; i < goods_id.length; i ++ ) {
						if(ifcontansInfo(goods_id[i].trim(),saleorder.getTerm())==true) {
							goodsall += Double.valueOf(sale_price[i].trim()) * Double.valueOf(order_num[i].trim());
						}
					}
				}
				//当订单总价满X时,给予优惠
				if(saleorder.getTerm_state().equals("1")){
					if(saleorder.getCoupon_state().equals("3")) {
						//找出免运费最低条件
						if(ValidateUtil.isRequired(ship_sale_id)) {
							ship_sale_id = saleorder.getSale_id();
							low_price = saleorder.getNeed_money();
						}else if(Double.valueOf(saleorder.getNeed_money()) < Double.valueOf(low_price)){
							ship_sale_id = saleorder.getSale_id();
							low_price = saleorder.getNeed_money();
						}
					}

				}else if(saleorder.getTerm_state().equals("2") ) {
					//判断免运费优惠方案 
					if(saleorder.getCoupon_state().equals("3")) {
						ship_sale_id = saleorder.getSale_id();
						low_price = saleorder.getNeed_money();
				    }else {
				    	//其他活动名称
						if(saleorder.getCoupon_state().equals("5")){
							//计算订单优惠金额
							order_money +=  Double.valueOf(saleorder.getCoupon_plan());
						}
						//获取订单活动名称
						if(ValidateUtil.isRequired(order_names)) {
							order_names = saleorder.getSale_name();
						}else {
						    order_names += "," + saleorder.getSale_name();
						}
				    }
					
				}else if(saleorder.getTerm_state().equals("3")) {//判断免运费条件
					//判断免运费优惠方案 
					if(saleorder.getCoupon_state().equals("3")) {
						if(ValidateUtil.isRequired(ship_sale_id)) {
							ship_sale_id = saleorder.getSale_id();
							low_price = saleorder.getNeed_money();
						}else if(Double.valueOf(saleorder.getNeed_money()) < Double.valueOf(low_price)){
							ship_sale_id = saleorder.getSale_id();
							low_price = saleorder.getNeed_money();
						}
				    }else if((goodsall* discount) >= Double.valueOf(saleorder.getNeed_money())){
				    	//其他活动名称
						if(saleorder.getCoupon_state().equals("5")){
							//计算订单优惠金额
							order_money +=  Double.valueOf(saleorder.getCoupon_plan());
						}
						//获取订单活动名称
						if(ValidateUtil.isRequired(order_names)) {
							order_names = saleorder.getSale_name();
						}else {
						    order_names += "," + saleorder.getSale_name();
						}
				    }

				}else if(saleorder.getTerm_state().equals("4")) {//判断免运费条件
					//判断免运费优惠方案 
					if(saleorder.getCoupon_state().equals("3")) {
						if(ValidateUtil.isRequired(ship_sale_id)) {
							ship_sale_id = saleorder.getSale_id();
							low_price = saleorder.getNeed_money();
						}else if(Double.valueOf(saleorder.getNeed_money()) < Double.valueOf(low_price)){
							ship_sale_id = saleorder.getSale_id();
							low_price = saleorder.getNeed_money();
						}
				    }else if((alltotal* discount) >= Double.valueOf(saleorder.getNeed_money())){
				    	//其他活动名称
						if(saleorder.getCoupon_state().equals("5")){
							//计算订单优惠金额
							order_money +=  Double.valueOf(saleorder.getCoupon_plan());
						}
						//获取订单活动名称
						if(ValidateUtil.isRequired(order_names)) {
							order_names = saleorder.getSale_name();
						}else {
						    order_names += "," + saleorder.getSale_name();
						}
				    }
				}		
			}

		}
		}
		//获取免费订单活动
		if(!ValidateUtil.isRequired(ship_sale_id)) {
			saleorder = this.saleorderService.get(ship_sale_id);
		}else{
			saleorder = null;
		}
		order_sign_str=RandomStrUtil.generateString(18);
		return goUrl("mbSubmitOrder");
	}
	 public static boolean ifcontansInfo(String gid,String gstr){
			boolean  fage=false;
			if(gid!=null&&!"".equals(gid)&&gstr!=null&&!"".equals(gstr)){
				String gstrs[]=gstr.split(",");
				if(gstrs!=null){
					for(int i=0;i<=gstrs.length-1;i++){
						if(gstrs[i]!=null){
							if(gid.equals(gstrs[i].toString())){
								fage=true;
							}
						}
					}
				}
			}
			return fage;
	}

	/**
	 * @author : WXP
	 * @param :cust_id
	 * @date Mar 6, 2014 1:46:29 PM
	 * @Method Description :获取用户的收货地址
	 */
	public void getBuyerAddrList() {
		Map addrMap = new HashMap();
		addrMap.put("cust_id", this.session_cust_id);
		addrList = this.buyeraddrService.getList(addrMap);
		addrList = ToolsFuc.replaceList(addrList, "");
	}

	/**
	 * @author : QJY
	 * @param :
	 * @date Mar 6, 2014 2:36:29 PM
	 * @Method Description :新增收货地址
	 */
	public String addressList(){
		return goUrl("mbappAddress");
	}
	
	
	
	/**
	 * @author : WXP
	 * @param :
	 * @date Mar 6, 2014 2:36:29 PM
	 * @Method Description :新增收货地址
	 */
	public void addAddr() throws Exception {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// 商城保存收货地址最大数
		if (this.session_cust_id != null && !this.session_cust_id.equals("")) {
			String addr_id = request.getParameter("addr_id");
			Map map = new HashMap();
			map.put("cust_id", this.session_cust_id);
			int count = this.buyeraddrService.getCount(map);
			if ((count >= cfg_maxaddressnumber)&&validateFactory.isRequired(addr_id)) {
				out.write("1");
			} else {
				// 获取地区html
				String addrDiv = this.orderdetailService.getAddrDiv(request,
						this.session_cust_id, this.session_user_name);//修改成插入用户名
				out.write(addrDiv);
			}
		} else {
			out.write("0");
		}

	}
	
	/**
	 * @Method Description：修改收货地址
	 * @author : HZX
	 * @throws IOException 
	 * @date : Jul 18, 2014 8:54:00 AM
	 */
	public void auAddr() throws IOException{
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String id=request.getParameter("id");
		if(id!=null&&!"".equals(id)){
			buyeraddr=this.buyeraddrService.get(id);
		}
		String outStr = JsonUtil.object2json(buyeraddr);
		out.print(outStr);
		
	}
	/**
	 * @Method Description :初始化收货地址
	 * @author : HZX
	 * @throws IOException 
	 * @date : Jul 21, 2014 10:02:34 AM
	 */
	public void initAddr() throws IOException{
		HttpServletResponse response = getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		Map pageMap = new HashMap();
		pageMap.put("cust_id", this.session_cust_id);
		List buyaddrList= this.buyeraddrService.getList(pageMap);
		buyaddrList = ToolsFuc.replaceList(buyaddrList, "");
		String outStr = JsonUtil.list2json(buyaddrList);
		out.print(outStr);
	}
	
	
	/**
	 * @author : WXP
	 * @param :
	 * @date Mar 22, 2014 10:10:29 AM
	 * @Method Description :删除收货地址
	 */
	public void delAddr() throws IOException {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			if (this.session_cust_id != null
					&& !this.session_cust_id.equals("")) {
				// 获取主键
				String addr_id = request.getParameter("addr_id");
				this.buyeraddrService.delete(addr_id);
				out.write("1");
			} else {
				out.write("0");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			out.write("3");
		}

	}

	/**
	 * @Method Description :验证订单签名
	 */
	public void checkOrderSing() throws IOException {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			if (this.session_cust_id != null&& !this.session_cust_id.equals("")) {
				// 获取主键
				String order_sign = request.getParameter("order_sign_str");
				List oList=new ArrayList();
				HashMap oMap=new HashMap ();
				oMap.put("order_sign", order_sign);
				oList=this.goodsorderService.getConfirmReceiptOrderList(oMap);
				if(oList!=null&&oList.size()>0){
					out.write("0");
				}else {
					out.write("1");
				}
				
			} else {
				out.write("2");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			out.write("0");
		}

	}
	/**
	 * ajax获取地址信息
	 * @throws IOException
	 */
    public void getAddr()throws IOException{
    	HttpServletRequest request=getRequest();
    	HttpServletResponse response=getResponse();
    	response.setCharacterEncoding("UTF-8");
    	request.setCharacterEncoding("UTF-8");
    	PrintWriter out= response.getWriter();
    	try{
    		if(!ValidateUtil.isRequired(this.session_cust_id)){
    			String addr_id =request.getParameter("addr_id");
    			Map map=new HashMap();
    			map.put("addr_id", addr_id);
    			List list =this.buyeraddrService.getList(map);
    			String jsonStr=JsonUtil.list2json(list);
    			out.write(jsonStr);
    		}else{
    			out.write("0");
    		}
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	/**
	 * @author : WXP
	 * @param :
	 * @date Mar 6, 2014 2:36:29 PM
	 * @Method Description :新增收货地址
	 */
	public void updateAddr() throws Exception {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (this.session_cust_id != null && !this.session_cust_id.equals("")) {
				// 获取地区html
				String addrDiv = this.orderdetailService.updateAddrDiv(request,
						this.session_cust_id, this.session_user_id);
				out.write(addrDiv);
		} else {
			out.write("0");
		}

	}
   
	/**
	 * 判断收货地址是否为空
	 * @throws Exception
	 */
	public void is_address() throws Exception {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map payMap = new HashMap();
		String cust_id ="";
        if(!ValidateUtil.isRequired(request.getParameter("cust_id"))){
        	cust_id = request.getParameter("cust_id").toString();
        	HashMap adressMap = new HashMap();
        	adressMap.put("cust_id",cust_id);
        	List buyadressList =  buyeraddrService.getList(adressMap);
        	if(buyadressList == null || buyadressList.size()==0){
                out.write("0");
        	}
        	
        }

	}
	
	/**
	 * 
	 * @throws IOException
	 */
	public void isLimit() throws IOException{
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		goods_id_str=	request.getParameter("goods_id_str");
		order_num_str=	request.getParameter("order_num_str");
		PrintWriter out = response.getWriter();
		String[] goods_id = goods_id_str.split(",");
		String[] order_num = order_num_str.split(",");
		Goods goods=new Goods();
		int limit_num=0,now_buy=0,buied =0,l_b=0,stock=0;
		for(int i=0;i<goods_id.length;i++ ){
			 goods =this.goodsService.getByPkNoDel(goods_id[i]);
			 if(goods==null||goods.getIs_up().equals("1")){
				 out.write("2");//商品已下架
			 }
			 stock=Integer.parseInt(goods.getTotal_stock());
			 //limit_num=Integer.parseInt(goods.getLimit_num());
			 now_buy=Integer.parseInt(order_num[i]);
			 if(0==stock||now_buy>stock){
				 out.write("3");//库存不足
			 }
		}
		//out.write("1");
	}
	
	/**
	 * @author : WXP
	 * @param :
	 * @date Mar 7, 2014 2:57:49 PM
	 * @Method Description :提交订单（实物商品）
	 */
	@SuppressWarnings("unchecked")
	public String subOrder() throws Exception, ParseException, BrowseException {
		
		if (this.session_cust_id != null && !this.session_cust_id.equals("")) {
			//验证订单签名不为空 且数据库不存在该签名
			if(order_sign_str!=null&&!"".equals(order_sign_str)){
				List oList=new ArrayList();
				HashMap oMap=new HashMap ();
				oMap.put("order_sign", order_sign_str);
				oList=this.goodsorderService.getConfirmReceiptOrderList(oMap);
				if(oList!=null&&oList.size()>0){
					//调整到订单中心
					getResponse().sendRedirect("/webapp/goodsorder!webappAllOrder.action");
					return NONE;
				}
			}else {
				//调整到订单中心
				getResponse().sendRedirect("/webapp/goodsorder!webappAllOrder.action");
				return NONE;
			}
			
			String[] cust_id = cust_id_str.split(",");
			String[] goods_id = goods_id_str.replaceAll(" ", "").split(",");
			String[] inter_subs = inter_sub.split(",");
			String[] goods_name = goods_name_str.split(",");//商品名称
			String[] goods_img = goods_img_str.split(",");//商品主图
			String[] goods_length = goods_length_str.split(",");
			String[] spec_id = spec_id_str.split(",");
			String[] spec_name = spec_name_str.split(",");
			String[] sale_price = new String[goods_id.length];
			String[] give_inter = give_inter_str.split(",");
			String[] order_num = order_num_str.split(",");
			String[] goods_amount = new String[cust_id.length];
			String[] shop_total_amount = new String[cust_id.length];
			String[] ship_free = new String[goods_id.length];
			String[] smode_id = new String[goods_id.length];
			String[] subtotal = subtotal_str.split(",");
			//String[] mem_remark = mem_remark_str.split(",");
			
			Map order_varMap = new HashMap();
			order_varMap.put("cust_id", cust_id);
			order_varMap.put("goods_id", goods_id);
			order_varMap.put("goods_name", goods_name);
			order_varMap.put("goods_img", goods_img);
			order_varMap.put("goods_length", goods_length);
			order_varMap.put("spec_id", spec_id);
			order_varMap.put("spec_name", spec_name);
			order_varMap.put("sale_price", sale_price);
			order_varMap.put("give_inter", give_inter);
			order_varMap.put("order_num", order_num);
			order_varMap.put("goods_amount", goods_amount);
			order_varMap.put("shop_total_amount", shop_total_amount);
			order_varMap.put("ship_free", ship_free);
			order_varMap.put("smode_id", smode_id);
			//order_varMap.put("mem_remark", mem_remark);
			order_varMap.put("order_id_str", order_id_str);
			order_varMap.put("addr_id", addr_id);
			order_varMap.put("order_type", order_type);
			order_varMap.put("goods_id_str", goods_id_str);
			order_varMap.put("goods_name_str", goods_name_str);
			order_varMap.put("goods_img_str", goods_img_str);
			order_varMap.put("order_num_str", order_num_str);
			order_varMap.put("group_id", group_id);
			order_varMap.put("spike_id", spike_id);
			order_varMap.put("combo_id", combo_id);
			order_varMap.put("trade_id", trade_id);
			order_varMap.put("session_cust_id", session_cust_id);
			order_varMap.put("session_user_id", session_user_id);
			order_varMap.put("session_level_code", session_level_code);
			order_varMap.put("is_webapp_order", "1");//PC端订单或者是手机触屏版订单
			orderinvoice.setArea_attr(v_area_attr);
			orderinvoice.setCust_id(this.session_cust_id);
			orderinvoice.setUser_id(this.session_user_id);
			order_varMap.put("orderinvoice", orderinvoice);
			order_varMap.put("paytype_id", paytype_id);
			order_varMap.put("is_use_inter", is_use_inter);
			order_varMap.put("use_paynum", use_paynum);
			order_varMap.put("inter_subs", inter_subs);
			order_varMap.put("coupon_id", coupon_id);
			order_varMap.put("coupon_goods_id", coupon_goods_id);
			order_varMap.put("coupon_money", coupon_money);
			order_varMap.put("subtotal", subtotal);
			order_varMap.put("comsumer_id", comsumer_id);
			order_varMap.put("coupon_cust_id", coupon_cust_id);
			order_varMap.put("red_id", red_id);
			order_varMap.put("redsumer_id", redsumer_id);
			order_varMap.put("red_money", red_money);
			order_varMap.put("is_ship_free", is_ship_free);
			order_varMap.put("all_total", total_amount);
			order_varMap.put("integral_state", integral_state);
			order_varMap.put("shoptotal_norate", shoptotal_norate);
			order_varMap.put("order_sign", order_sign_str);
			//获取会员对象
			Member member = this.memberService.get(this.session_cust_id);
			//获取会员级别
			String level=member.getBuy_level().trim();	
			Malllevelset malllevelset=new Malllevelset();
			malllevelset=this.malllevelsetService.get(level);
			discount=Double.parseDouble(malllevelset.getDiscount())/100;
			order_varMap.put("discount", discount);
			if("on".equals(paytype_id)){
				String buy_cust_id = this.session_cust_id;
				// 校验支付密码
				Map payMap = new HashMap();
				payMap.put("cust_id", buy_cust_id);
				String pwd = Md5.getMD5(pay_password.getBytes());
				payMap.put("pay_passwd", pwd);
				List list = this.memberfundService.getList(payMap);
				if (list != null && list.size() > 0) {
					
				} else {
					this.addFieldError("pay_password", "支付密码不正确,重新输入!");
					return goOrder();
				}
				
			}
			order_id_str = this.orderdetailService.addOrder(order_varMap,
					getResponse());
			
			return  goUrl("topay");
		} else {
			getResponse().sendRedirect("/webappLogin.html");
			return goUrl("mbLogin");
		}
	}
	/**
	 * @author : WXP
	 * @param :
	 * @date Mar 8, 2014 10:32:47 AM
	 * @Method Description :跳转至支付页面
	 */
	public String goPay() throws Exception {
		if (!ValidateUtil.isRequired(order_id_str)) {
			// 获取订单信息
			goodsorder = new Goodsorder();
			String[] ids=null;
			String ido;
			integral_total_amount = total_amount;
			total_amount=0;
			total_balance = 0;
			ids=order_id_str.replace(" ","").split(",");
			for(int i=0;i<ids.length;i++){
				goodsorder = this.goodsorderService.get(ids[i]);
				if (!goodsorder.getOrder_state().equals("1")) {
					
					commonBuyResponse(goodsorder.getOrder_id(), goodsorder
							.getOrder_state());
					return null;
				} 
				total_amount+=(goodsorder.getTatal_amount()-Double.parseDouble(goodsorder.getBalance_use())-Double.parseDouble(goodsorder.getIntegral_use()));
				total_balance += Double.parseDouble(goodsorder.getBalance_use());
		        
				//账户是否使用了账户余额
				if(goodsorder.getBalance_use()!=null && !goodsorder.getBalance_use().equals("0")){
					
					fundGoldManage(ids[i]);
						
				}
			}
				// 获取订单列表
				Map orderMap = new HashMap();
				orderMap.put("order_id_in", order_id_str);
				// 待付款订单个数
				orderPayNum = this.goodsorderService.getWebCount(orderMap);
				// 待付款订单列表
				orderPayList = this.goodsorderService.getWebList(orderMap);
				// 获取买家对象
				buyMember = new Member();
				buyMember = this.memberService.get(this.session_cust_id);
				// 获取买家的余额情况
				memberfund = memberfundService.get(this.session_cust_id);
				// 获取支付方式
				Map paymap = new HashMap();
				paymap.put("enabled", "0");
				paymentList = paymentService.getList(paymap);
				order_type=goodsorder.getOrder_type();
				memberuser=memberuserService.get(this.session_user_id);
				//判断支付密码
				if(memberuser.getCust_id()!=null && !"".equals(memberuser.getCust_id())){
					memberfund=this.memberfundService.get(memberuser.getCust_id());
					if(memberfund!=null){
						pay_pass=memberfund.getPay_passwd();
					}
					
				}
				//判断是否已手机验证
				if(!"".equals(memberuser.getIs_check_mobile())){
					is_check_mobile=memberuser.getIs_check_mobile();
				}
				//判断是否已邮箱验证
				if(!"".equals(memberuser.getIs_check_email())){
					is_check_email=memberuser.getIs_check_email();
				}
				//需支付的订单金额
				if(total_amount == 0 || "".equals(String.valueOf(total_amount)) || integral_state.equals("1")){
					// 发送信息提醒
					for(int i=0;i<ids.length;i++){
						if(!ValidateUtil.isRequired(ids[i])){
							Goodsorder gorder = new Goodsorder();
							gorder=goodsorderService.get(ids[i]);
							if(gorder!=null&&"1".equals(gorder.getOrder_state())){
								
								if("1".equals(integral_state)){
									//积分付款
									if("2".equals(gorder.getOrder_type())){
										mestipByBuyer("4",ids[i]);
										updateOrderState(ids[i],"2");
										//更新库存
										orderdetailService.updateStockBypayment(ids[i]);
									}else {
										return goUrl("mbOrderPay");
									}
								}else {
									//其他付款
									mestipByBuyer("4",ids[i]);
									updateOrderState(ids[i],"2");
									//更新库存
									orderdetailService.updateStockBypayment(ids[i]);
									//更新商品销售量, 销量记录 在付款之后就加销量
									goodsorderService.updateGoodsSales(ids[i]);
								}
							}
						}
					}
					return goUrl("mPaymentResult");
				}else{
					
					return goUrl("mbOrderPay");
				}
				
				}
			else {
			return goUrl("mbOrderPay");
		}
	}

	/**
	 * @author : WXP
	 * @param :
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :跳转至确认支付页面
	 */
	public String confirmPay() throws Exception {
		// 获取订单
		goPay();
		return goUrl("confirmPay");
	}

	/**
	 * @author : HXK
	 * @param :
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :跳转至确认在线支付页面
	 */
	public String confirmOnlinePay() throws Exception {
		// 获取订单
		goPay();
		return goUrl("onlineconfirmPay");
	}

	
	/**
	 * 支付密码是否输入正确
	 * @throws Exception
	 */
	public void is_PayPasswordRight() throws Exception {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map payMap = new HashMap();
		String cust_id ="",psw="",pay_password="";
        if(!ValidateUtil.isRequired(request.getParameter("cust_id")) && !ValidateUtil.isRequired(request.getParameter("psw"))){
        	cust_id = request.getParameter("cust_id").toString();
        	psw = request.getParameter("psw").toString();
        	memberfund = memberfundService.get(cust_id);
            pay_password = memberfund.getPay_passwd();
            psw = Md5.getMD5(psw.getBytes());
            if(!pay_password.equals(psw)){
            	out.write("0");
            }
        }

	}
	
	/**
	 * @author : WXP
	 * @param :
	 * @throws Exception
	 * @date Mar 11, 2014 2:28:08 PM
	 * @Method Description :账户余额支付
	 */
	public String useNumPay() throws Exception {

		if (pay_password == null || pay_password.equals("")) {
			this.addFieldError("pay_password", "支付密码不能为空!");
			return goPay();
		}
		if (order_id_str == null || order_id_str.equals("")) {
			return goPay();
		}
		String[] order_id = order_id_str.split(",");
		//判断订单是否已经支付过了
		if(order_id!=null && !"".equals(order_id[0])){
			goodsorder = goodsorderService.get(order_id[0]);
			if(goodsorder!=null){
				if("2".equals(goodsorder.getOrder_state())){
					this.addFieldError("pay_password", "该订单已经支付过了，请勿重复支付!");
					return goPay();
				}
			}
		}
		
		String buy_cust_id = this.session_cust_id;
		if (buy_cust_id != null && !buy_cust_id.equals("")) {
			// 校验支付密码
			Map payMap = new HashMap();
			payMap.put("cust_id", buy_cust_id);
			String pwd = Md5.getMD5(pay_password.getBytes());
			payMap.put("pay_passwd", pwd);
			List list = this.memberfundService.getList(payMap);
			if (list != null && list.size() > 0) {
				String lengthString = this.orderdetailService.useNumPay(
						session_user_id, buy_cust_id, order_id);
				int go = lengthString.length();
				switch (go) {
				case 1:
					getResponse().sendRedirect("/bmall_Memberfund_view.action");
					return "";
				case 2:
					break;
				default:
					String oid_gid[] = lengthString.split("#");
					String oid = oid_gid[0];
					this.addFieldError("pay_password", "很抱歉，订单中有商品已下架或删除!请重拍");
					return goPay();
				}

			} else {
				this.addFieldError("pay_password", "支付密码不正确,重新输入!");
				return goPay();
			}
			if(goodsorder.getOrder_type().equals("7")){
				getResponse().sendRedirect(
				"/bmall_Goodsorder_postageList.action");
			}else if (is_virtual.equals("0")) {
				getResponse().sendRedirect(
						"/bmall_Goodsorder_buyVirtualList.action");
			} else
				getResponse().sendRedirect(
						"/bmall_Goodsorder_buyorderlist.action");
		} else {
			getResponse().sendRedirect("/login.html");
		}
		return null;
	}
	/**
	 * @param :order_id_str
	 *            订单号串
	 * @Method Description :支付的时候验证 库存是否够
	 */
	public void checkGoodsNum() throws IOException {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		boolean fage=true;
		try {
			if(request.getParameter("order_id_str")!=null&&!"".equals(request.getParameter("order_id_str"))){
				// 订单号
				String order_id_str = request.getParameter("order_id_str");
				fage=orderdetailService.checkGoodsNum(order_id_str);
				if(fage==true){
					out.write("1");
				}else {
					out.write("0");
				}
			}else {
				out.write("0");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			out.write("0");
		}
	}
	/**
	 * @author : WXP
	 * @param :order_id_str
	 *            订单号串
	 * @date Mar 27, 2014 9:40:37 AM
	 * @Method Description :刷新订单价格
	 */
	public void refreshPrice() throws IOException {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			// 订单号
			String order_id_str = request.getParameter("order_id_str");
			// 获取订单列表
			Map orderMap = new HashMap();
			orderMap.put("order_id_in", order_id_str);
			// 待付款订单列表
			orderPayList = this.goodsorderService.getWebList(orderMap);
			// list2json
			String orderStr = JsonUtil.list2json(orderPayList);
			out.write(orderStr);
		} catch (RuntimeException e) {
			e.printStackTrace();
			out.write("0");
		}
	}

	// //////////////////////////////////////以下是订单处理信息/////////////////////////////////////
	/**
	 * @author : HXK
	 * @param :
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :前台商品评价-查看
	 */
	public String orderEvaluateView() throws IOException {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (order_id != null) {
				// 获取订单信息
				goodsorder = new Goodsorder();
				goodsorder = this.goodsorderService.get(order_id);
				order_type=goodsorder.getOrder_type();
				if (!"8".equals(goodsorder.getOrder_state())) {
					// 获取商品信息
					Map detailMap = new HashMap();
					detailMap.put("order_id", order_id);
					if("6".equals(order_type)){
						detailList = this.directorderdetailService.getList(detailMap);
					}else {
						detailList = this.orderdetailService.getList(detailMap);
					}
					orderdetaiCount = detailList.size();
					// 获取店铺信息
					shopconfig = shopconfigService.getByCustID(goodsorder
							.getSell_cust_id());
					return goUrl("order_evaluate");
				} else {
					getResponse().sendRedirect(
							"/mall-order-success-" + order_id + ".html");
				}
				return null;
			} else {
				return null;
			}
		}
	}

	/**
	 * @author : HXK 方法描述：会员修改订单状态
	 * @return
	 * @throws Exception
	 */
	public void updateOrderState(String s_order_id, String s_order_state)
			throws Exception {
		// 订单状态表示：0：订单取消 1：未付款 2：已付款 3：已发货 4：退款中 5：退款成功 6：退款失败：7：交易成功 8：已评价
		Goodsorder goods_order = new Goodsorder();
		goods_order.setOrder_id(s_order_id);
		goods_order.setOrder_state(s_order_state);
		Map stateMap = new HashMap();
		stateMap.put("order_state", s_order_state);
		stateMap.put("order_id", s_order_id);
		if (s_order_state.equals("2")) {
			stateMap.put("pay_time", "pay_time");
			if(integral_state.equals("1")){
				stateMap.put("pay_id", "13");
				stateMap.put("order_type", "2");//订单类型为积分订单
			}else {
				stateMap.put("pay_id", "4");
			}
			stateMap.put("pay_trxid", DateUtil.getFormatLong()+DateUtil.getSix());
		}
		if (s_order_state.equals("3")) {
			stateMap.put("send_time", "send_time");
		}
		if (s_order_state.equals("7")) {
			stateMap.put("sure_time", "sure_time");
		}
		goodsorderService.update(stateMap);
		//插入订单异动信息表
		String reason=CommparaFuc.getReason(s_order_state,null);
		insertOrderTrans(s_order_id, reason, s_order_state);
	}


	/**
	 * @author : HXK
	 * @param :
	 * @date Mar 12, 2014 1:33:55 PM
	 * @Method Description :资金处理
	 */
	private void fundGoldManage(String oid) {

		Goodsorder order = goodsorderService.get(oid);
		// 获取买家的ID
		String order_buy_cust_id = order.getBuy_cust_id();
		
		//会员账户资金
		//Double buy_use_num = this.memberfundService.outAndInNum(this.session_cust_id, Double.parseDouble(order.getBalance_use()), 0);
		//运营商总账户资金，冻结
		this.sysfundService.freezeNum(Double.parseDouble(order.getBalance_use()), 0);
		
		// 买家资金处理
		Double use_num = 0.0;//总资金
		Memberfund buy_mf = memberfundService.get(order_buy_cust_id);
		if (buy_mf != null) {
			use_num = Double.parseDouble(buy_mf.getUse_num());
		}
		
		// 买家的资金异动
		Fundhistory buy_fh = new Fundhistory();
		buy_fh.setBalance(use_num);
		buy_fh.setFund_in(0.00);
		buy_fh.setFund_out(Double.parseDouble(order.getBalance_use()));
		buy_fh.setPay_type("0");//余额支付
		buy_fh.setCust_id(order_buy_cust_id);
		buy_fh.setUser_id(this.session_user_id);
		buy_fh.setReason("会员为订单号:" + oid + ",余额支付" + order.getBalance_use()
				+ "元");
		fundhistoryService.insert(buy_fh);

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
		ordertransService.inserOrderTran(order_id, session_cust_id, session_user_id, reason, order_state, session_user_name);
	}

	/**
	 * @author : HXK
	 * @throws Exception
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :前台商品评价
	 * @修改人：QJY
	 * @date         2014 10:45:59 AM
	 * @add 商品晒单
	 */
	public String orderEvaluate() throws Exception {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (order_id != null) {
				if (orderdetaiCount != null && !orderdetaiCount.equals("0")) {
					String order_goods_id_strs[] = order_goods_id_str
							.split(",");
					String order_goods_feng_strs[] = order_goods_feng_str
							.split(",");
					String order_goods_content_strs[] = order_goods_content_str
							.split("##########");
					String order_goods_sharepic_strs[] = order_goods_sharepic_str
					        .split("##########");
					for (int i = 0; i < orderdetaiCount; i++) {
						String explan_cust_id = null;// 卖家标识
						// 插入商品评论表
						goodseval = new Goodseval();
						goodseval.setCust_id(this.session_cust_id);
						goodseval.setExplan_content("");
						if("6".equals(order_type)){
							goodseval.setE_type("1");
						}else {
							goodseval.setE_type("0");
						}

						// 截取评论字数为200
						String str_comment = "";
						if (order_goods_content_strs[i] != null) {
							if (order_goods_content_strs[i].length() < 200) {
								str_comment = order_goods_content_strs[i]
										.toString();
							} else {
								str_comment = order_goods_content_strs[i]
										.substring(0, 199);
							}

						}
						goodseval.setG_comment(str_comment);
						goodseval.setG_eval(order_goods_feng_strs[i]);
						goodseval.setGoods_id(order_goods_id_strs[i]);
						goodseval.setIs_enable("0");
						goodseval.setIs_two("0");
						goodseval.setUser_id(this.session_user_id);
						if (this.goodsService.get(order_goods_id_strs[i]) != null) {
							explan_cust_id = this.goodsService.get(
									order_goods_id_strs[i]).getCust_id();
						}
						goodseval.setExplan_cust_id(explan_cust_id);
						String eval_id = goodsevalService
						.insertGetPk(goodseval);
						
						//插入商品晒单图
						goodsshare =  new Goodsshare();
						goodsshare.setEval_id(eval_id);
						goodsshare.setCust_id(this.session_cust_id);
						goodsshare.setUser_id(this.session_user_id);
						goodsshare.setGoods_id(order_goods_id_strs[i]);
						String sharepicStr = "";
						// 由于前台商采用论采用插件原因，把插入路径片包装成html格式<img
						// src=''/>，方便前台晒图直接取值
						if (!ValidateUtil
								.isRequired(order_goods_sharepic_strs[i])) {// 判断晒图图片内容是否为空
							StringBuffer sharepicSB = new StringBuffer();
							if (order_goods_sharepic_strs[i].indexOf(",") > 0) {// 判断晒图是否是多张图片

								String[] sharepicArray = order_goods_sharepic_strs[i]
										.split(",");
								for (int j = 0; j < sharepicArray.length; j++) {
									sharepicSB.append("<img src='");
									sharepicSB.append(sharepicArray[j]);
									sharepicSB
											.append("' width='50px' height='50px' style='border:1px solid #dcdcdc;'/>&nbsp;");
								}
							} else {
								sharepicSB.append("<img src='");
								sharepicSB.append(order_goods_sharepic_strs[i]);
								sharepicSB
										.append("' width='50px' height='50px' style='border:1px solid #dcdcdc;'/>");
							}
							sharepicStr = sharepicSB.toString();
						} else {
							sharepicStr = "暂无晒图";
						}
						goodsshare.setShare_pic(sharepicStr);
						goodsshareService.insert(goodsshare);
					}
					// 更新订单状态为 已评价
					updateOrderState(order_id, "8");
					if (is_virtual != null && !is_virtual.equals("")) {
						getResponse().sendRedirect(
								"/bmall_Goodsorder_buyVirtualList.action");
					} else {
						getResponse().sendRedirect(
								"/bmall_Goodsorder_buyorderlist.action");
					}

				}
			}
			return NONE;
		}
	}

	/**
	 * @author : HXK
	 * @param :
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :前台商品确认收货-查看
	 */
	public String orderConfirmReceiptView() throws IOException {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (order_id != null) {
				// 获取订单信息
				goodsorder = new Goodsorder();
				goodsorder = this.goodsorderService.get(order_id);
				order_type=goodsorder.getOrder_type();
				if (goodsorder.getOrder_state().equals("3")) {
					// 获取商品信息
					Map detailMap = new HashMap();
					detailMap.put("order_id", order_id);
					if("6".equals(order_type)){
						detailList = this.directorderdetailService.getList(detailMap);
					}else {
						detailList = this.orderdetailService.getList(detailMap);
					}
					
					orderdetaiCount = detailList.size();
					// 获取店铺信息
					shopconfig = shopconfigService.getByCustID(goodsorder
							.getSell_cust_id());
					order_area = AreaFuc.getAreaNameByMap(goodsorder
							.getArea_attr());
					return goUrl("order_confirm_receipt");
				} else {
					commonBuyResponse(goodsorder.getOrder_id(), goodsorder
							.getOrder_state());
					return null;
				}
			} else {
				return null;
			}
		}
	}

	/**
	 * @author : HXK
	 * @throws Exception
	 * @date Mar 28, 2014 2:35:04 PM
	 * @Method Description :前台商品确认收货
	 */
	public String orderConfirmReceipt() throws Exception {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (pay_password == null || pay_password.equals("")) {
				this.addFieldError("pay_password", "支付密码不能为空!");
				return orderConfirmReceiptView();
			}
			if (order_id != null) {
				String buy_cust_id = this.session_cust_id;
				// 校验支付密码
				Map payMap = new HashMap();
				payMap.put("cust_id", buy_cust_id);
				String pwd = Md5.getMD5(pay_password.getBytes());
				payMap.put("pay_passwd", pwd);
				List list = this.memberfundService.getList(payMap);
				if (list != null && list.size() > 0) {
					// 付款到卖家
					sellerFundManage(order_id);
					// 更新订单状态为 已评价
					updateOrderState(order_id, "7");
					// 插入积分
					insertOrderInter(order_id);
					// 消息提醒
					mestipByBuyer("1", order_id);
				} else {
					this.addFieldError("pay_password", "支付密码不正确,重新输入!");
					return orderConfirmReceiptView();
				}
				getResponse().sendRedirect(
						"/mall-goodsevaluate-" + order_id + ".html");
				return null;
			} else {
				return null;
			}
		}
	}

	/**
	 * @Method Description :插入积分：目前默认购买多少钱 送多少积分
	 * @author: HXK
	 * @date : May 16, 2014 11:26:45 AM
	 * @param
	 * @return return_type
	 */
	public void insertOrderInter(String goodsorder_id) {
		goodsorderService.insertOrderInter(goodsorder_id, cfg_sc_pointsrule, this.session_user_id);
	}

	/**
	 * @author : HXK
	 * @param :
	 * @date Mar 28, 2014 1:33:55 PM
	 * @Method Description :将资金从运营转入卖家
	 */
	private void sellerFundManage(String oid) {
		goodsorderService.sellerFundManage(oid, this.session_user_id);
	}

	/**
	 * @author : HXK
	 * @param :
	 * @date Mar 28, 2014 1:33:55 PM
	 * @Method Description :将资金从运营转入买家
	 */
	private void buyFundManage(String oid) {
		Goodsorder order = this.goodsorderService.get(oid);
		refundapp = refundappService.getByOrderId(order_id);
		if (order != null) {
			Double refund_momey = 0.0;
			// 退款金额
			refund_momey = Double.parseDouble(refundapp.getRefund_amount());
			// 获取卖家的ID
			String buy_cust_id = order.getBuy_cust_id();
			//处理平台总资金
			sysfundService.freezeNum(refund_momey, 1);
			// 买家资金处理
			Double i1= memberfundService.outAndInNum(buy_cust_id, refund_momey, 1);
			// 买家的资金异动
			Fundhistory buy_fh = new Fundhistory();
			buy_fh.setBalance(i1);
			buy_fh.setCust_id(buy_cust_id);
			buy_fh.setFund_in(refund_momey);
			buy_fh.setFund_out(0.0);
			buy_fh.setUser_id(this.session_user_id);
			buy_fh.setReason("买家收到订单号:" + oid + " 退款支付" + refund_momey + "元");
			this.fundhistoryService.insert(buy_fh);
		}
	}

	/**
	 * @author : HXK
	 * @param :
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :前台买家退款申请-查看
	 */
	public String orderBuyRefundmentView() throws IOException {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (order_id != null) {
				// 获取订单信息
				goodsorder = new Goodsorder();
				goodsorder = this.goodsorderService.get(order_id);
				if (this.refundappService.getByOrderId(order_id) != null) {
					refundapp = this.refundappService.getByOrderId(order_id);
				}
				// 获取商品信息
				Map detailMap = new HashMap();
				detailMap.put("order_id", order_id);
				detailList = this.orderdetailService.getList(detailMap);
				orderdetaiCount = detailList.size();
				// 获取店铺信息
				shopconfig = shopconfigService.getByCustID(goodsorder
						.getSell_cust_id());
				gCommparaList("buy_refund");
				return goUrl("order_buy_refundment");
			} else {
				return null;
			}
		}
	}

	/**
	 * @author : HXK
	 * @throws Exception
	 * @date Mar 28, 2014 2:35:04 PM
	 * @Method Description :前台买家退款申请-操作
	 */
	public String orderBuyRefundment() throws Exception {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (order_id != null) {
				if (refundappService.getByOrderId(order_id) != null) {
					return orderRefundmentView();
				}
				goodsorder = new Goodsorder();
				goodsorder = goodsorderService.get(order_id);
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");// 设置日期格式
				String buy_cust_id = this.session_cust_id;
				Refundapp refundapp = new Refundapp();
				refundapp = commonCheck(goodsorder, refundapp);
				refundapp.setBuy_reason(buy_refund_reason + "["
						+ df.format(new Date()) + "]");
				if (super.ifvalidatepass) {
					return orderBuyRefundmentView();
				}
				refundapp.setBuy_cust_id(buy_cust_id);
				refundapp.setBuy_user_id(this.session_user_id);
				refundapp.setBuy_date(df.format(new Date()));
				refundapp.setOrder_id(order_id);
				// 0:退款中，1：退款成功，2：退款失败
				refundapp.setRefund_state("0");
				// refundapp.setInfo_state("0");
				refundapp.setInfo_state("");
				refundapp.setSeller_cust_id(goodsorder.getSell_cust_id());
				refundappService.insert(refundapp);
				// 更新订单状态为 退款中
				updateOrderState(order_id, "4");
				// 消息提醒
				mestipBySeller("5", order_id);
				this.addActionMessage("已提交退款申请,请等待卖家处理!");
				return orderRefundmentView();
			} else {
				return null;
			}
		}
	}

	/**
	 * @author : HZX
	 * @date : Nov 5, 2014 9:14:15 AM
	 * @Method Description :更新退款申请
	 */
	public String orderBuyRefundmentUpdate() throws Exception {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (order_id != null) {
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");// 设置日期格式
				goodsorder = new Goodsorder();
				goodsorder = goodsorderService.get(order_id);
				refundapp = this.refundappService.get(refundapp_id);
				refundapp = commonCheck(goodsorder, refundapp);
				refundapp.setBuy_reason(refundapp.getBuy_reason() + "  ["
						+ df.format(new Date()) + "]  " + buy_refund_reason);
				refundapp.setIs_treated("1");// 卖家当前是否需要处理 1： 是 0：否
				refundapp.setRefund_state("0");
				if (cfg_Refund_deny_num <= Integer.parseInt(refundapp
						.getDeny_num())) {
					is_deny_num = true;
					this.addFieldError("buy_refund_type", "您已经申请过"
							+ cfg_Refund_deny_num + "次，已达上限，您还可以要求官方介入");
				}
				if (super.ifvalidatepass) {
					return orderBuyRefundmentView();
				}
				refundappService.update(refundapp);
				// 更新订单状态为 已评价
				updateOrderState(order_id, "4");
				// 消息提醒
				mestipBySeller("5", order_id);
				this.addActionMessage("已提交退款申请,请等待卖家处理!");
				return orderRefundmentView();
			} else {
				return null;
			}
		}
	}

	/**
	 * @author : HZX
	 * @date : Nov 5, 2014 2:23:33 PM
	 * @Method Description :新增修改退款申请公共校验
	 */
	public Refundapp commonCheck(Goodsorder goodsorder, Refundapp refundapp) {

		if (buy_refund_type != null && !buy_refund_type.equals("--请选择退款理由--")) {
			refundapp.setBuy_type(buy_refund_type);
		} else {
			this.addFieldError("buy_refund_type", "请选择退款理由");
		}
		if (buy_refund_reason != null && !buy_refund_reason.equals("")) {
			if (buy_refund_reason.indexOf(">") > -1) {
				this.addFieldError("buy_refund_reason", "系统检测到非法字符，请填写退款说明");
			}
			if (buy_refund_reason.length() > 10000) {
				this.addFieldError("buy_refund_reason", "字数太多，请重新修改退款说明");
			}
		} else {
			this.addFieldError("buy_refund_reason", "请填写退款说明");
		}
		if (goodsorder.getOrder_state().equals("3")) {
			if (is_get != null && is_get != "") {
				boolean is_save;
				if (is_get.equals("0")) {// 0:未收到货
					refundapp.setIs_get("0");
					refundapp.setRefund_amount(goodsorder.getTatal_amount()
							.toString());
					is_save = checkImg();// 校验图片
					if (is_save) {
						refundapp.setImg_path(imgString);
					}
				} else if (is_get.equals("1")) {
					refundapp.setIs_get("1");
					is_save = checkImg();// 校验图片
					if (is_save) {
						refundapp.setImg_path(imgString);
					}
					if (need_refund != null && !need_refund.equals("")) {
						if (Double.parseDouble(need_refund) > goodsorder
								.getTatal_amount()) {
							this.addFieldError("need_refund", "退款金额超过已付款金额");
						} else {
							refundapp.setRefund_amount((Double
									.parseDouble(need_refund) + "").trim());
						}
					} else {
						this.addFieldError("need_refund", "请填写需退款金额");
					}
					if (is_return != null && !is_return.equals("")) {
						refundapp.setIs_return(is_return);
					} else {
						this.addFieldError("is_return", "请选择是否需要退货");
					}

				}
			} else {
				this.addFieldError("is_get", "请确认是否已收到货");
			}
		} else if (goodsorder.getOrder_state().equals("2")) {
			refundapp.setRefund_amount(goodsorder.getTatal_amount().toString());
		}
		return refundapp;
	}

	/**
	 * @author : HZX
	 * @date : Nov 4, 2014 4:17:23 PM
	 * @Method Description :公共校验图片
	 */
	public boolean checkImg() {
		boolean is_save = true;
		if (imgString != null && !imgString.equals("")) {
			if (imgString.indexOf(",") > -1) {
				String[] imgStrings = imgString.split(",");
				if (imgStrings.length >= 4) {
					this.addFieldError("refundapp.img_path", "最多3张图片");
					is_save = false;
				}
			}

		}
		return is_save;
	}

	/**
	 * @author : HZX
	 * @throws IOException
	 * @throws java.text.ParseException 
	 * @date : Nov 5, 2014 9:47:34 AM
	 * @Method Description :取消退款
	 */
	public String cancelRefund() throws IOException, java.text.ParseException {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (order_id != null) {
				// 获取订单信息
				refundapp = new Refundapp();
				refundapp = this.refundappService.get(order_id);
				if (refundapp!=null&&refundapp.getRefund_state().equals("0")) {
					//申请售后类型，1：退货，0退款
					//详情商品状态：0：正常，1，退款中，2：退款关闭，3：退款成功,4退货中，5退货关闭，6，退货成功，7，换货中，8换货关闭，9换货成功
					String s_refund_state="2";
					if(refundapp.getIs_return()!=null&&"1".equals(refundapp.getIs_return())){
						s_refund_state="5";
					}else if(refundapp.getIs_return()!=null&&"0".equals(refundapp.getIs_return())) {
						s_refund_state="2";
					}
					this.refundappService.cancelRefund(order_id,"",s_refund_state);
				}
			}
			return null;
		}
	}

	/**
	 * @author : HZX
	 * @date : Dec 7, 2014 3:58:14 PM
	 * @Method Description :申请官方介入
	 */
	public void getInvolved() throws IOException {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 订单号
		String order_id = request.getParameter("order_id");

		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			out.write("0");
		} else {
			if (order_id != null) {
				refundapp = refundappService.getByOrderId(order_id);
				if(!ValidateUtil.isRequired(refundapp.getInfo_state())){
					out.write("3");
				}else{
					refundapp.setInfo_state("2");
					this.refundappService.update(refundapp);
					out.write("1");
				}
			} else {
				out.write("2");
			}
		}
	}

	/**
	 * @author : HZX
	 * @date : Nov 6, 2014 5:26:10 PM
	 * @Method Description :卖家同意退款后向买家提供退货地址
	 */
	public String setRefundAddr() throws IOException {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (order_id != null) {
				// 获取订单信息
				goodsorder = this.goodsorderService.get(order_id);
				refundapp = refundappService.getByOrderId(order_id);
				//判断当前状态是否为3 退款处理0:退款中，1：退款成功，2：退款失败3:等待买家发货4：买家已发货5：退款关闭
				//如果退款状态为3话，那就可能出现刷新重新进来 要捕获
				if("0".equals(refundapp.getRefund_state())){
					// 实例化收货地址对象
					if (addr_id != null && !addr_id.equals("")) {
						buyeraddr = this.buyeraddrService.get(addr_id);
						SimpleDateFormat df = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");// 设置日期格式
						refundapp.setSeller_date(df.format(new Date()));
						refundapp.setConsignee(buyeraddr.getConsignee());
						refundapp.setMobile(buyeraddr.getCell_phone());
						refundapp.setTelephone(buyeraddr.getPhone());
						refundapp.setSell_address(buyeraddr.getAddress());
						refundapp.setArea_attr(buyeraddr.getArea_attr());
						refundapp.setZip_code(buyeraddr.getPost_code());
						refundapp.setSell_remark(sell_remark);
						//设置退款状态为 3等待买家发货---HXK2014-11-19 0:退款中，1：退款成功，2：退款失败3:等待买家发货4：买家已发货5：退款关闭
						refundapp.setRefund_state("3");
						this.refundappService.update(refundapp);
					}
				} 
				return orderRefundmentView();
			} else {
				return NONE;
			}
		}
	}

	/**
	 * @author : HXK
	 * @param :mes_id：消息提醒模版
	 *            order_id：订单ID
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :前台订单提醒-发送给买家
	 */
	public void mestipByBuyer(String mes_id, String order_id)
			throws UnsupportedEncodingException {
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
		Goodsorder gorder = new Goodsorder();
		gorder = goodsorderService.get(order_id);
		MessageAltFuc mesalt = new MessageAltFuc();
		mesalt.messageAutoSend(mes_id, gorder.getSell_cust_id(), gorder);
	}

	/**
	 * @author : HXK
	 * @param :
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :前台卖家退款申请-查看
	 */
	public String orderSellerRefundmentView() throws IOException {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (order_id != null) {
				// 获取订单信息
				goodsorder = new Goodsorder();
				goodsorder = this.goodsorderService.get(order_id);
				// 获取商品信息
				Map detailMap = new HashMap();
				detailMap.put("order_id", order_id);
				detailList = this.orderdetailService.getList(detailMap);
				orderdetaiCount = detailList.size();
				// 获取店铺信息
				shopconfig = shopconfigService.getByCustID(goodsorder
						.getSell_cust_id());
				refundapp = refundappService.getByOrderId(order_id);
				if (refundapp.getSeller_state() != null
						&& refundapp.getConsignee() == null) {
					if (refundapp.getIs_return() != null
							&& refundapp.getIs_return().equals("1")
							&& refundapp.getSeller_state().equals("0")
							&& refundapp.getRefund_state().equals("0")) {
						getBuyerAddrList();// 获取卖家地址
					}
				}

				gCommparaList("buy_refund");
				return goUrl("order_seller_refundment");
			} else {
				return null;
			}
		}
	}

	/**
	 * @author : HXK
	 * @throws Exception
	 * @date Mar 28, 2014 2:35:04 PM
	 * @Method Description :前台卖家退款申请-同意退款操作
	 */
	public String sellerAgreeRefundment() throws Exception {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (pay_password == null || pay_password.equals("")) {
				this.addFieldError("pay_password", "支付密码不能为空!");
				return sellerAgreeIsneedReturn();
			}
			if (order_id != null) {
				String seller_cust_id = this.session_cust_id;
				// 校验支付密码
				Map payMap = new HashMap();
				payMap.put("cust_id", seller_cust_id);
				String pwd = Md5.getMD5(pay_password.getBytes());
				payMap.put("pay_passwd", pwd);
				List list = this.memberfundService.getList(payMap);
				if (list != null && list.size() > 0) {
					if (this.refundappService.getByOrderId(order_id) != null) {
						Refundapp refundapp = this.refundappService
								.getByOrderId(order_id);
						String is_return = refundapp.getIs_return();
						if (is_return != null && is_return.equals("1")
								&& refundapp.getSend_time() == null) {
							SimpleDateFormat df = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");// 设置日期格式
							refundapp.setSeller_date(df.format(new Date()));
							refundapp.setSeller_user_id(this.session_user_id);
							refundapp.setSeller_state("0");
							refundapp.setInfo_state("0");
							this.refundappService.update(refundapp);
							return sellerAgreeIsneedReturn();
						}
					}
					// 操作退款
					updateRefund(order_id, "", "0", "0");
					// 付款到买家
					buyFundManage(order_id);
					// 更新订单状态为 退款成功
					updateOrderState(order_id, "5");
					// 消息提醒
					mestipByBuyer("12", order_id);
				} else {
					this.addFieldError("pay_password", "支付密码不正确,重新输入!");
					return sellerAgreeIsneedReturn();
				}
				this.addActionMessage("已提交同意退款,且已将资金退款给买家!");
				// 插入订单异动记录
				ordertransService.inserOrderTran(order_id, session_cust_id, session_user_id,
						CommparaFuc.getReason("1", "同意退款,且已将资金退款给买家!"), "5", session_user_name);
				return orderRefundmentView();
			} else {
				return null;
			}
		}
	}

	/**
	 * @author : HZX
	 * @throws IOException
	 * @date : Nov 8, 2014 11:07:14 AM
	 * @Method Description :卖家没收到货，要求官方介入
	 */
	public String noGetRefund() throws IOException {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (order_id != null) {
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");// 设置日期格式
				refundapp = refundappService.getByOrderId(order_id);
				if (refundapp != null && refundapp.getInfo_state() != null
						&& refundapp.getInfo_state().equals("1")) {
					return sellerAgreeIsneedReturn();
				}
				refundapp.setInfo_state("2");
				refundapp.setSeller_date(df.format(new Date()));
				refundapp.setSeller_user_id(this.session_user_id);
				this.refundappService.update(refundapp);
				return orderRefundmentView();
			} else {
				return null;
			}
		}
	}

	/**
	 * @author : HXK--更新退款操作
	 * @param order_id
	 *            订单号
	 * @param reason
	 *            处理退款理由
	 * @param seller_state
	 *            0：同意退款，1：不同意退款
	 * @param refund_state
	 *            0:退款中，1：退款成功，2：退款失败
	 */
	public void updateRefund(String order_id, String reason,
			String seller_state, String refund_state) {
		Refundapp refundapp = new Refundapp();
		refundapp = refundappService.getByOrderId(order_id);
		if (refundapp != null && refundapp.getInfo_state() != null
				&& refundapp.getInfo_state().equals("1")) {
			return;
		}
		if (refundapp != null) {
			this.orderdetailService.updateRefund(refundapp, seller_state,
					this.session_user_id, refund_state, reason);
		}
	}

	/**
	 * @author : HXK
	 * @throws Exception
	 * @date Mar 28, 2014 2:35:04 PM
	 * @Method Description :前台卖家退款申请-不同意退款操作
	 */
	public String sellerDisAgreeRefundment() throws Exception {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (order_id != null) {
				if (seller_refund_reason == null
						|| seller_refund_reason.equals("")) {
					this.addFieldError("seller_refund_reason", "请填写拒绝说明!");
					return orderSellerRefundmentView();
				} else {
					if (seller_refund_reason.indexOf(">") > -1) {
						this.addFieldError("seller_refund_reason",
								"系统检测到非法字符，请重新修改拒绝说明");
						return orderSellerRefundmentView();
					}
					if (seller_refund_reason.length() > 3000) {
						this.addFieldError("seller_refund_reason",
								"字数太多，请重新修改拒绝说明");
						return orderSellerRefundmentView();
					}
				}
				// 操作退款
				updateRefund(order_id, seller_refund_reason, "1", "0");
				// 更新订单状态为 退款
				goodsorder = this.goodsorderService.get(order_id);
				String sendtime = goodsorder.getSend_time();
				String paytime = goodsorder.getPay_time();
				String tips="";
				if (sendtime != null && !sendtime.equals("")) {
					goodsorder.setOrder_state("3");
					tips="卖家拒绝退货!";
				} else if (paytime != null && !paytime.equals("")) {
					goodsorder.setOrder_state("2");
					tips="卖家拒绝退款!";
				} else {
					goodsorder.setOrder_state("1");
				}
				this.goodsorderService.update(goodsorder);
				// 拒绝
				mestipByBuyer("15", order_id);
				this.addActionMessage("已提交"+tips+"!");
				// 插入订单异动记录
				ordertransService.inserOrderTran(order_id, session_cust_id, session_user_id,
						CommparaFuc.getReason("1", tips), goodsorder.getOrder_state(),session_user_name);
				return orderRefundmentView();

			} else {
				return null;
			}
		}
	}

	/**
	 * @author : HXK
	 * @param :
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :前台退款申请-查看
	 */
	public String orderRefundmentView() throws IOException {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (order_id != null) {
				// 获取订单信息
				goodsorder = new Goodsorder();
				goodsorder = this.goodsorderService.get(order_id);
				// 获取商品信息
				Map detailMap = new HashMap();
				detailMap.put("order_id", order_id);
				detailList = this.orderdetailService.getList(detailMap);
				orderdetaiCount = detailList.size();
				// 获取退款信息
				refundapp = refundappService.getByOrderId(order_id);
				refundDealtime = SysconfigFuc.getSysValue("cfg_RefundDealtime");
				refund_suretime = SysconfigFuc
						.getSysValue("cfg_Refund_receipts_time");
				refund_sendtime = SysconfigFuc
						.getSysValue("cfg_Refundsend_goods_time");
				gCommparaList("buy_refund");
				if (this.session_cust_id.equals(refundapp.getBuy_cust_id())) {// 买家的操作页面
					order_area = AreaFuc.getAreaNameByMap(goodsorder
							.getArea_attr());
					gOrderSendmode();// 获取物流公司
					is_buyer = true;
				}
				if (refundapp.getSend_time() != null
						&& !refundapp.getSend_time().equals("")) {
					getLogistics(refundapp.getSend_mode(), refundapp
							.getSend_num());// 获取物流
				}
				return goUrl("order_refundment_success");
			} else {
				return null;
			}
		}
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
	 * @author : HXK
	 * @param :
	 * @throws Exception
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description ：前台查看订单交易成功信息
	 */
	public String orderSuccessView() throws Exception {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (order_id != null) {
				// 获取订单信息
				goodsorder = new Goodsorder();
				goodsorder = this.goodsorderService.get(order_id);
				// 获取商品信息
				Map detailMap = new HashMap();
				detailMap.put("order_id", order_id);
				detailList = this.orderdetailService.getList(detailMap);
				orderdetaiCount = detailList.size();
				order_area = AreaFuc
						.getAreaNameByMap(goodsorder.getArea_attr());
				// 获取买家会员信息
				memberuser = memberuserService.getPKByCustID(goodsorder
						.getBuy_cust_id());
				memberuser_seller = memberuserService.getPKByCustID(goodsorder
						.getSell_cust_id());
				shopconfig = shopconfigService.getByCustID(goodsorder
						.getSell_cust_id());
				seller_area_name = AreaFuc.getAreaNameByMap(shopconfig
						.getArea_attr());
				Sendmode sendmode = new Sendmode();
				if (goodsorder.getSend_mode() != null
						&& !"".equals(goodsorder.getSend_mode())) {
					sendmode = sendmodeService.get(goodsorder.getSend_mode());
				}
				if (sendmode != null) {
					// 获取快递公司代码
					if (sendmode.getEn_name() != null) {
						kuai_company_code = sendmode.getEn_name();
					}
					// 获取快递公司名称
					if (sendmode.getSmode_name() != null) {
						kuai_company = sendmode.getSmode_name();
					}
				}
				kuai_number = goodsorder.getSend_num();
				// 查询快递信息
				logistics_query = LogisticsFuc.hundredTrace(kuai_company_code,
						kuai_number);
				return goUrl("order_success");
			} else {
				return null;
			}
		}
	}

	/**
	 * @author : HXK
	 * @throws Exception
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description ：前台查看订单交易关闭信息
	 */
	public String orderCloseView() throws Exception {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (order_id != null) {
				// 获取订单信息
				goodsorder = new Goodsorder();
				goodsorder = this.goodsorderService.get(order_id);
				// 获取商品信息
				Map detailMap = new HashMap();
				detailMap.put("order_id", order_id);
				detailList = this.orderdetailService.getList(detailMap);
				orderdetaiCount = detailList.size();
				order_area = AreaFuc
						.getAreaNameByMap(goodsorder.getArea_attr());
				// 获取买家会员信息
				memberuser = memberuserService.getPKByCustID(goodsorder
						.getBuy_cust_id());
				memberuser_seller = memberuserService.getPKByCustID(goodsorder
						.getSell_cust_id());
				shopconfig = shopconfigService.getByCustID(goodsorder
						.getSell_cust_id());
				seller_area_name = AreaFuc.getAreaNameByMap(shopconfig
						.getArea_attr());
				return goUrl("order_close");
			} else {
				return null;
			}
		}
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
	 * @author : HZX
	 * @throws IOException
	 * @date : Nov 7, 2014 4:00:40 PM
	 * @Method Description :买家向卖家发送宝贝即退货
	 */
	public String toSendRefund() throws IOException {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (order_id != null) {
				boolean is_save = false;
				
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");// 设置日期格式
				if (send_mode == null || send_mode.equals("")) {
					this.addFieldError("send_mode", "请选择物流公司!");
				}
				if (send_num == null || send_num.equals("")) {
					this.addFieldError("send_num", "请填写运单号!");
				}
				is_save = checkImg();// /校验图片3张上限
				if (super.ifvalidatepass) {
					return orderRefundmentView();
				}
				// 获取退款信息
				refundapp = refundappService.getByOrderId(order_id);
				if(refundapp.getRefund_state().equals("3")){
					if (is_save) {
						refundapp.setImg_path(imgString);
					}
					refundapp.setSend_time(df.format(new Date()));
					refundapp.setSend_num(send_num);
					refundapp.setSend_mode(send_mode);
					//设置退款状态为 4等待买家发货---HXK2014-11-19 0:退款中，1：退款成功，2：退款失败3:等待买家发货4：买家已发货5：退款关闭
					refundapp.setRefund_state("4");
					this.refundappService.update(refundapp);
				}
				return orderRefundmentView();

			} else {
				return null;
			}
		}
	}

	/**
	 * @author : HZX
	 * @date : Nov 8, 2014 8:43:19 AM
	 * @Method Description :获取退款快递信息
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
	 * @author : HZX
	 * @throws IOException
	 * @date : Nov 11, 2014 10:55:38 AM
	 * @Method Description :卖家同意退款，判断是否需要退货跳转到对应页面
	 */
	public String sellerAgreeIsneedReturn() throws IOException {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (order_id != null) {
				// 获取订单信息
				goodsorder = new Goodsorder();
				goodsorder = this.goodsorderService.get(order_id);
				// 获取商品信息
				Map detailMap = new HashMap();
				detailMap.put("order_id", order_id);
				detailList = this.orderdetailService.getList(detailMap);
				orderdetaiCount = detailList.size();
				gCommparaList("buy_refund");
				// 获取店铺信息
				shopconfig = shopconfigService.getByCustID(goodsorder
						.getSell_cust_id());
				refundapp = refundappService.getByOrderId(order_id);
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");// 设置日期格式
				// refundapp.setSeller_cust_id(this.session_cust_id);
				refundapp.setSeller_date(df.format(new Date()));
				refundapp.setSeller_user_id(this.session_user_id);
				refundapp.setSeller_state("0");
				// refundapp.setIs_treated("0");
				refundapp.setInfo_state("0");
				this.refundappService.update(refundapp);
				//refund_state 0:退款中，1：退款成功，2：退款失败3:等待买家发货4：买家已发货5：退款关闭
				//seller_state 0：同意退款，1：不同意退款
				//is_return 1:需要退货0：无需退货
				if (refundapp.getIs_return() != null&& refundapp.getIs_return().equals("1")
						&& refundapp.getSeller_state().equals("0")&& refundapp.getRefund_state().equals("0")) {
					getBuyerAddrList();// 获取卖家地址
					return goUrl("order_seller_refundagreereturn");

				} else {
					return goUrl("order_seller_refundagree");
				}

			}
		}
		return goUrl("login");
	}
	
	/**
	 * 买家跳转到详细页
	 */
	public void commonBuyResponse(String order_id, String order_state)
			throws IOException {
		getResponse().sendRedirect(
				"/bmall_Goodsorder_buyOrderView.action?goodsorder.order_id="
						+ order_id + "&order_state=" + order_state);
	}

	public Orderinvoice getOrderinvoice() {
		return orderinvoice;
	}

	public void setOrderinvoice(Orderinvoice orderinvoice) {
		this.orderinvoice = orderinvoice;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

}
