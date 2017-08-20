package com.rbt.webaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import com.browseengine.bobo.api.BrowseException;
import com.opensymphony.xwork2.Preparable;
import com.rbt.model.Buyeraddr;
import com.rbt.model.Directorderdetail;
import com.rbt.model.Directsell;
import com.rbt.model.Fundhistory;
import com.rbt.model.Goods;
import com.rbt.model.Goodseval;
import com.rbt.model.Goodsorder;
import com.rbt.model.Interhistory;
import com.rbt.model.Member;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.model.Memberuser;
import com.rbt.model.Orderdetail;
import com.rbt.model.Ordertrans;
import com.rbt.model.Refundapp;
import com.rbt.model.Sellerscore;
import com.rbt.model.Sendmode;
import com.rbt.model.Shopconfig;
import com.rbt.service.IBuyeraddrService;
import com.rbt.service.IDirectladderService;
import com.rbt.service.IDirectorderdetailService;
import com.rbt.service.IDirectsellService;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IGoodsevalService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IInterhistoryService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberinterService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.IOrdertransService;
import com.rbt.service.IPaymentService;
import com.rbt.service.IRefundappService;
import com.rbt.service.ISendmodeService;
import com.rbt.service.IShopconfigService;
import com.rbt.service.ISysfundService;
import com.rbt.common.Md5;
import com.rbt.common.util.JsonUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.function.CommparaFuc;
import com.rbt.function.LogisticsFuc;
import com.rbt.function.MembercreditFuc;
import com.rbt.function.MessageAltFuc;
import com.rbt.function.SysconfigFuc;

/**
 * @author : HZX
 * @date : Jul 4, 2014 3:46:49 PM
 * @Method Description :预售订单
 */
public class WebdirectOrderAction extends goodsModelAction implements
		Preparable {

	private static final long serialVersionUID = 1L;

	/** *****************实体层*************** */
	public Member buyMember;
	public Goodsorder goodsorder;
	public Orderdetail orderdetail;
	public Ordertrans ordertrans;
	public Buyeraddr buyeraddr;
	public Memberfund memberfund;
	public Fundhistory fundhistory;
	public Directorderdetail directorderdetail;
	public Goodseval goodseval;
	public Sellerscore sellerscore;
	public Refundapp refundapp;
	private Directsell directsell;
	public Shopconfig shopconfig;
	public Memberuser memberuser;
	public Memberuser memberuser_seller;

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
	private IDirectorderdetailService directorderdetailService;
	@Autowired
	private IDirectsellService directsellService;
	@Autowired
	private IMemberinterService memberinterService;
	@Autowired
	private IDirectladderService directladderService;
	@Autowired
	private IInterhistoryService interhistoryService;
	@Autowired
	private ISysfundService sysfundService;

	private String cfg_sc_pointsrule = SysconfigFuc.getSysValue("cfg_sc_pointsrule");//积分规则
	

	/** *******************集合***************** */
	public List directladderList;// 预售阶梯价格
	public List orderList = new ArrayList();// 订单商品列表
	public List shopList = new ArrayList();// 店铺列表
	public List addrList;// 收货地列表
	public List orderPayList;// 待付款订单列表
	public List detailList;// 订单详细列表信息
	public Map createListMap;// 店铺列表和订单商品列表
	public List paymentList;

	/** *******************字段***************** */
	public String cust_id_str;// 卖家标识串
	public String goods_id_str;// 卖家标识串
	public String goods_length_str;// 店铺商品个数串
	public String goods_name_str;// 商品名称串
	public String goods_img_str;// 商品图片串
	public String spec_id_str;// 规格值标识串
	public String spec_name_str;// 规格值名称串
	public String sale_price_str;// 商品单价串
	public String give_inter_str;// 商品获赠积分串
	public String order_num_str;// 订单个数串
	public String shop_name_str;// 店铺名称串
	public String shop_qq_str;// 店铺QQ
	public String smode_id_str;// 配送方式ID
	public String ship_str;// 配送方式 方式+价格
	public String ship_name_str;// 配送方式名称
	public String ship_price_str;// 配送价格
	public String goods_amount_str;// 商品总价格串
	public String shop_total_amount_str;// 店铺总价格串（含运费）
	public String ship_free_str;// 运费
	public String addr_id;// 收货地址标识
	public String total_amount;// 订单总价
	public String cust_name;// 会员名称
	public String mem_remark_str;// 买家订单备注
	public String trade_id;// 主键（更新库存用）
	public String order_type;// 订单类型
	public String loc;// 跳回登录前的位置
	public String end_area_attr;// 物流目的地
	public String sub_total_price;// 待支付订单金额
	public String use_num_pay;// 使用账户余额支付金额
	public String pay_password;// 支付密码
	public String endpay_time;// 尾款支付截止时间
	public String end_time;// 定金支付截止时间
	public String earnest;// 预付定金
	public String sale_title;// 预售主题
	public String direct_id;// 预售标识（预售详细订单用，会员后台跳转到预售商品）
	public String order_id_str;// 临时存储订单号
	public Double combo_price = 0.0;// 套餐价格
	public Integer orderdetaiCount = 0;
	public String sell_cust_id;
	public int orderPayNum;// 待付款订单个数
	public String order_id;// 订单号
	public String order_goods_id_str;// 商品评价的商品ID串
	public String order_goods_feng_str;// 商品评价的商品分数
	public String order_goods_content_str;// 商品评价的内容
	public String order_service_attitude;// 卖家服务态度
	public String order_delivery_speed;// 卖家发货速度
	public String order_desc;// 描述相符
	public String order_area;// 地区
	public String buy_refund_type;// 申请退款类型
	public String buy_refund_reason;// 退款说明
	public String seller_refund_reason;// 拒绝退款说明
	public String logistics_query;// 快递查询结果
	public String kuai_number;// 快递号
	public String kuai_company;// 快递公司
	public String kuai_company_code;// 快递公司代码
	public String seller_area_name;// 地区名
	public String is_virtual;// 是否虚拟
    public String radom_no_str;//店铺随机数‘
    public String  can_buy;
    public String order_pay_tip="0";//支付页面提示，如果值为0表示，下单调整的支付页面，如果为1表示是后台点击跳转支付的
	/**
	 * @author : HZX
	 * @date : Jul 23, 2014 9:24:31 AM
	 * @Method Description :跳转至实物订单页面
	 */
	public String goOrder() throws Exception {
		// 判断是否登录
		if (this.session_cust_id.equals("") || this.session_cust_id.equals("0")) {
			getResponse().sendRedirect("/login.html?loc=" + loc);
			return NONE;
		}
		if(direct_id!=null&&!"".equals(direct_id)){
			int ordernum=0,max_buy=0;
			HashMap mMap = new HashMap();
			mMap.put("trade_id", direct_id);
			List dList=this.directsellService.getList(mMap);
			Map dMap=(Map) dList.get(0);
			if(dMap.get("buynum")==null){
				ordernum=0;
			}else {
				ordernum =Integer.parseInt(dMap.get("buynum").toString()) ;
			}
			max_buy=Integer.parseInt(this.directsellService.get(direct_id).getMax_buy());
			if(max_buy-ordernum-Integer.parseInt(order_num_str)<0){
				can_buy="库存不足！";
		//return NONE;
	}
			
			
		}
		
		// 判断是否购买自己的商品
		if (cust_id_str != null && !cust_id_str.equals("")
				&& cust_id_str.contains(this.session_cust_id)) {
			getResponse().sendRedirect(
					"/mall/directSell!detail.action?direct_id=" + direct_id
							+ "&isBuySelf=0");
			return NONE;
		}
		
		// 构建店铺列表和商品列表
		Map map = new HashMap();
		map.put("cust_id_str", cust_id_str);
		map.put("goods_id_str", goods_id_str);
		map.put("goods_length_str", goods_length_str);
		map.put("goods_name_str", goods_name_str);
		map.put("goods_img_str", goods_img_str);
		map.put("spec_id_str", spec_id_str);
		map.put("spec_name_str", spec_name_str);
		map.put("sale_price_str", sale_price_str);
		map.put("give_inter_str", give_inter_str);
		map.put("order_num_str", order_num_str);
		map.put("shop_name_str", shop_name_str);
		map.put("shop_qq_str", shop_qq_str);
		map.put("direct_id", direct_id);
		map.put("earnest", earnest);
		map.put("radom_no_str", radom_no_str);
		map.put("session_cust_id", this.session_cust_id);
		createListMap = this.directorderdetailService.createList(map);
		return goUrl("preGoodsOrder");
	}

	/**
	 * @author : WXP
	 * @param :
	 * @date Apr 16, 2014 10:56:53 AM
	 * @Method Description :
	 */
	public String goVirtualOrder() throws Exception {
		// 判断是否登录
		if (this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
		}
		// 判断是否购买自己的商品
		if (cust_id_str != null && !cust_id_str.equals("")
				&& cust_id_str.contains(this.session_cust_id)) {
			getResponse().sendRedirect(
					"/mall/directSell!detail.action?direct_id=" + direct_id
							+ "&isBuySelf=0");
			return null;
		}
		// 店铺信息
		Map shopMap = new HashMap();
		shopMap.put("shop_cust_id", cust_id_str);
		shopMap.put("shop_name", shop_name_str);
		shopMap.put("shop_qq", shop_qq_str);
		shopMap.put("goods_length", goods_length_str);
		shopMap.put("radom_no", radom_no_str);
		shopList.add(shopMap);
		// 订单信息
		Map orderMap = new HashMap();
		orderMap.put("cust_id", cust_id_str);
		orderMap.put("goods_id", goods_id_str.trim());
		orderMap.put("goods_name", goods_name_str);
		orderMap.put("goods_img", goods_img_str);
		orderMap.put("spec_id", spec_id_str);
		orderMap.put("spec_name", spec_name_str);
		orderMap.put("sale_price", sale_price_str);
		orderMap.put("give_inter", give_inter_str);
		orderMap.put("order_num", order_num_str);
		orderList.add(orderMap);

		return goUrl("virtualGoodsOrder");
	}

	/**
	 * @author : HZX
	 * @date : Jul 23, 2014 9:25:05 AM
	 * @Method Description :新增收货地址
	 */
	public void addAddr() throws IOException {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 获取参数
		String consignee = request.getParameter("consignee");
		String de_consignee = URLDecoder.decode(consignee, "UTF-8");
		String address = request.getParameter("address");
		String de_address = URLDecoder.decode(address, "UTF-8");
		String post_code = request.getParameter("post_code");
		String phone = request.getParameter("phone");
		String cell_phone = request.getParameter("cell_phone");
		String area_attr = request.getParameter("area_attr");
		// 构造参数
		Map map = new HashMap();
		map.put("session_cust_id", session_cust_id);
		map.put("session_user_name", session_user_name);
		map.put("area_attr", area_attr);
		map.put("phone", phone);
		map.put("de_address", de_address);
		map.put("de_consignee", de_consignee);
		map.put("post_code", post_code);
		map.put("cell_phone", cell_phone);
		String outStr = this.directorderdetailService.addAddr(map);
		out.write(outStr);
	}

	/**
	 * @author : HZX
	 * @date : Jul 23, 2014 9:25:27 AM
	 * @Method Description :删除收货地址
	 */
	public void delAddr() throws IOException {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			if (this.session_cust_id != null&& !this.session_cust_id.equals("")) {
				// 获取主键
				String addr_id = request.getParameter("addr_id");
				this.buyeraddrService.delete(addr_id);
				out.write("1");
			} else {
				out.write("0");
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.write("3");
		}

	}
	
	public void isLimit() throws IOException{
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		direct_id=	request.getParameter("direct_id");
		order_num_str=	request.getParameter("order_num_str");
		PrintWriter out = response.getWriter();
		String[] direct_ids = direct_id.split(",");
		String[] order_num = order_num_str.split(",");
		Goods goods=new Goods();
		int limit_num=0,now_buy=0,buied =0,l_b=0;
		for(int i=0;i<direct_ids.length;i++ ){
			 directsell =this.directsellService.get(direct_ids[i]);
			 limit_num=Integer.parseInt(directsell.getLimit_num());
			 now_buy=Integer.parseInt(order_num[i]);
			 Map bMap=new HashMap();
			 bMap.put("direct_id",direct_ids[i]);
			 bMap.put("cust_id",this.session_cust_id);
			 List bList=this.directorderdetailService.getList(bMap);
			 String b_num="";
			  for(int j=0;j<bList.size();j++){
				  Map bnMap=new HashMap();
				  bnMap=(Map) bList.get(j);
				  b_num=bnMap.get("order_num").toString();
				  l_b=Integer.parseInt(b_num) ;
				  buied=l_b+buied;
			  }
			 if((buied+now_buy)>limit_num){
				 out.write("0");
				 return ;
			 }
			 
		}
		out.write("1");
		 return ;
	}
	
	/**
	 * @author : HZX
	 * @throws Exception 
	 * @date : Jul 23, 2014 9:25:40 AM
	 * @Method Description :提交订单（实物商品）预售是一对一的关系即一个订单只有一个详细订单时间仓促以下代码需整改
	 */
	public String subPreOrder() throws Exception {
		if (this.session_cust_id != null && !this.session_cust_id.equals("")) {
			// 构造参数
			Map map = new HashMap();
			int ordernum=0,max_buy=0;
			map.put("cust_id_str", cust_id_str);
			map.put("goods_id_str", goods_id_str);
			map.put("goods_name_str", goods_name_str);
			map.put("goods_img_str", goods_img_str);
			map.put("goods_length_str", goods_length_str);
			map.put("spec_id_str", spec_id_str);
			map.put("spec_name_str", spec_name_str);
			map.put("order_id_str", order_id_str);
			map.put("order_num_str", order_num_str);
			map.put("mem_remark_str", mem_remark_str);
			map.put("end_area_attr", end_area_attr);
			map.put("earnest", earnest);
			map.put("direct_id", direct_id);
			map.put("addr_id", addr_id);
			map.put("session_cust_id", session_cust_id);
			map.put("session_user_id", session_user_id);
			map.put("endpay_time", endpay_time);
			map.put("end_time", end_time);
			map.put("trade_id", trade_id);
			
			String returnvalue=this.directorderdetailService.subOrder(map);
			if("1".equals(returnvalue)){
				return goUrl("login");
			} else {
				if(order_id_str==null || "".equals(order_id_str)){
					order_id_str = returnvalue;
				}
				return payFinal();
			}
		} else {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		}
	}
    
	/**
	 * @Method Description :判断付款是否超过定金支付时间
	 * @author: HXK
	 * @date : Dec 11, 2014 2:21:36 PM
	 * @return return_type flagEndTime=0能付定金，flagEndTime=1不能付定金
	 * @throws ParseException 
	 */
	private String ifEndPayDeposit() throws ParseException{
    	//获取倒计时时间秒数
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	 String flagEndTime="0";//标记付定金时间
			if(directsell != null && directsell.getEnd_time()!=null){
				Date date = sd.parse(directsell.getEnd_time());
				//付定金结束时间-当前时间，结果大于0表示还能继续付定金，结果小于0表示不能继续付定金
				long difftime =date.getTime() - new Date().getTime();
				if(0 > difftime){
					//flagEndTime=0能付定金，flagEndTime=1不能付定金
					flagEndTime = "1";
				}
			}
		return flagEndTime;
    }
	/**
	 * @Method Description :判断支付是否超过支付尾款的最后期限
	 * @author: HXK
	 * @date : Dec 11, 2014 2:38:18 PM
	 * @param 
	 * @return flagTailTime=0能付尾款，flagTailTime=1不能付尾款
	 */
	private String ifEndPayFinalPayment() throws ParseException{
		 Integer cfg_yushouendpay=1;//预售尾款结束时间
    	 if(cfg_yushouendpaytime!=null&&!"".equals(cfg_yushouendpaytime)){
    		 //取系统默认配置预售支付尾款结束时间
    		 cfg_yushouendpay=Integer.parseInt(cfg_yushouendpaytime);
    	 }
		//获取倒计时时间秒数
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String flagTailTime="0";//标记付尾款时间
		if(directsell != null && directsell.getTail_time()!=null){
			Date date = sd.parse(directsell.getTail_time());
			Calendar calendar = Calendar.getInstance();  
			calendar.setTime(date);   
			//加上系统设置支付尾款有限时间--20141208
			calendar.add(Calendar.DATE, +cfg_yushouendpay);
			//获取三天前的日期
			long threedate = calendar.getTime().getTime();
			long difftime =threedate - new Date().getTime();
			if(0 > difftime){
				//flagTailTime=0能付尾款，flagTailTime=1不能付尾款
				flagTailTime = "1";
			}
		}
		return flagTailTime;
	}
	
	/**
	 * @Method Description :
	 * @author: HXK
	 * @date : Dec 11, 2014 2:52:56 PM
	 * @param order_type:表示传入需要处理的取消订单类型，0表示未付款，1表示已付定金
	 * @return return_type
	 */
     private void dealTimeOutPayOrder(String order_type){
    	//需要将定金转入卖家账户 待做
    	 //1:取消订单 将状态变为3
    	 //判断类型 0不管，1：将买家支付余额打入卖家账户中
    	 if("0".equals(order_type)){
    		 directorderdetail.setPay_state("3");
    		 this.directorderdetailService.update(directorderdetail);
    	 }else if("1".equals(order_type)){
    		String order_id = directorderdetail.getOrder_id();
    		String trade_id = directorderdetail.getTrade_id();
    		//获取定金金额
			Double earnest = 0.0,totalearnest=0.0;
			Integer num=0;
			earnest = directorderdetail.getEarnest();
		    num = Integer.parseInt(directorderdetail.getOrder_num());
			totalearnest = earnest*num;
    		//定金支付给卖家
			Goodsorder goodsorder = goodsorderService.get(order_id);
			if(goodsorder!=null){
				String buy_cust_id = goodsorder.getBuy_cust_id();
				directorderdetailService.sellerFundManage(order_id,totalearnest,buy_cust_id,"1",trade_id);
			}
    	 }
    	 
     }
     
     
	/**
	 * @method
	 * @throws Exception
	 */
	public void pretimeout() throws Exception {
 		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
	    if(!ValidateUtil.isRequired(session_cust_id) && !ValidateUtil.isRequired(order_id_str)){
			if (order_id_str.endsWith("a") || order_id_str.endsWith("b")) {
				order_id_str = order_id_str.substring(0,
						order_id_str.length() - 1);
			}
			directorderdetail = this.directorderdetailService
					.getByOrderId(order_id_str);
			if(directorderdetail !=null){
				directsell = this.directsellService.get(directorderdetail.getDirect_id());

				// 判断是否超过定金支付时间
				if ("0".endsWith(directorderdetail.getPay_state())
						&& ifEndPayDeposit().equals("1")) {
					// 处理订单超市取消订单的操作
					dealTimeOutPayOrder("0");
					out.write("0");// 超过支付定金的时间
					// 处理取消订单
				}
				// 判断是否超过尾款支付时间
				if ("1".equals(directorderdetail.getPay_state())
						&& ifEndPayFinalPayment().equals("1")) {
					// 处理订单超市取消订单的操作
					dealTimeOutPayOrder("1");
					out.write("1");
				}
			}
			
			out.write("2");
		}
	}
     
     
	 
	/**
	 * @author : HZX
	 * @date : Jul 23, 2014 2:01:37 PM
	 * @Method Description :支付定金/尾款
	 */
	public String payFinal() throws IOException, ParseException,BrowseException {
		if (this.session_cust_id != null && !this.session_cust_id.equals("")&& order_id_str != null) {
			if(order_id_str.endsWith("a")||order_id_str.endsWith("b")){
				order_id_str=order_id_str.substring(0, order_id_str.length()-1);
			}
			orderPayNum = 1;
			directorderdetail = this.directorderdetailService.getByOrderId(order_id_str);
			goodsorder = this.goodsorderService.get(order_id_str);
			
			//判断传过来需要付款的订单是不是状态为0或者为2的 0：表示没有定，1：已付定金没有付尾款
			if("0".equals(directorderdetail.getPay_state())|| "1".equals(directorderdetail.getPay_state())){
				
				double ear = directorderdetail.getEarnest();
				double pri = directorderdetail.getGoods_price();
				double num = Double.parseDouble(directorderdetail.getOrder_num());
				if (directorderdetail.getPay_state().equals("0")) {
					//支付订单处理订单
					goodsorder.setTatal_amount((pri * num));
					total_amount = (ear * num) + "";
					order_id_str=order_id_str+"a";
				} else if (directorderdetail.getPay_state().equals("1")) {
					//支持尾款
					goodsorder.setTatal_amount(pri * num+ goodsorder.getShip_free());
					total_amount = (pri - ear) * num + goodsorder.getShip_free() + "";
					order_id_str=order_id_str+"b";
				}
				this.goodsorderService.update(goodsorder);	
				return goPay();
			}else {
				getResponse().sendRedirect("/bmall_Goodsorder_buyorderlist.action");
				return null;
			}
			
		} else {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		}
	}

	/**
	 * @author : WXP
	 * @param :
	 * @date Apr 16, 2014 11:22:36 AM
	 * @Method Description :提交虚拟订单
	 */
	public String subVirtualOrder() throws IOException, ParseException,
			BrowseException {
		if (this.session_cust_id != null && !this.session_cust_id.equals("")) {
			// 构造参数
			Map map = new HashMap();
			map.put("cust_id_str", cust_id_str);
			map.put("goods_amount_str", goods_amount_str);
			map.put("session_cust_id", session_cust_id);
			map.put("mem_remark_str", mem_remark_str);
			map.put("session_user_id", session_user_id);
			map.put("spec_name_str", spec_name_str);
			map.put("sale_price_str", sale_price_str);
			map.put("goods_id_str", goods_id_str);
			map.put("order_num_str", order_num_str);
			map.put("spec_id_str", spec_id_str);
			// 用于传递至支付页面
			order_id_str = this.directorderdetailService.subVirtualOrder(map);
			return goPay();
		} else {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		}
	}

	/**
	 * @author : HZX
	 * @date : Jul 23, 2014 9:26:14 AM
	 * @Method Description :跳转至支付页面
	 */
	public String goPay() {
		String l_o=order_id_str;
		// 获取订单列表
		Map orderMap = new HashMap();
		if(order_id_str.endsWith("a")||order_id_str.endsWith("b")){
			order_id_str=order_id_str.substring(0, order_id_str.length()-1);
		}
		directorderdetail = this.directorderdetailService
				.getByOrderId(order_id_str);
		direct_id=directorderdetail.getDirect_id();
		String order_num_str=directorderdetail.getOrder_num();
		if(this.directsellService.get(direct_id)!=null){
			//获取购买人数
			int ordernum=0,max_buy=0;
			HashMap mMap = new HashMap();
			mMap.put("trade_id", direct_id);
			List dList=this.directsellService.getList(mMap);
			Map dMap=(Map) dList.get(0);
			if(dMap.get("buynum")==null){
				ordernum=0;
			}else {
				ordernum =Integer.parseInt(dMap.get("buynum").toString()) ;
			}
			max_buy=Integer.parseInt(this.directsellService.get(direct_id).getMax_buy());
			if("0".equals(directorderdetail.getPay_state())){
				if(max_buy-ordernum-Integer.parseInt(order_num_str)<0){
					can_buy="库存不足,请重新选择其他商品";
					//this.addFieldError("pay_password", can_buy);
					return goPay();
					//return NONE;
				}
			}
        }
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
		 Map paymap=new HashMap();
		 paymap.put("enabled", "0");
		 paymentList = paymentService.getList(paymap);
		 order_id_str=l_o;
		 order_type=goodsorder.getOrder_type();
		return goUrl("prePayment");
	}

	/**
	 * @author : HZX
	 * @date : Jul 23, 2014 9:26:34 AM
	 * @Method Description :跳转至确认支付页面
	 */
	public String confirmPay() {
		// 获取订单
		goPay();
		return goUrl("directConfirmPay");
	}

	/**
	 * @author : HZX
	 * @throws ParseException:日期格式转化成long整型报错
	 * @date : Jul 23, 2014 9:27:01 AM
	 * @Method Description :账户余额支付这个方法只用于预售，有要调用请注释
	 * @update 2014.11.20 将验证不通的结果返回，方法直接跳转到当前页面。
	 */
	public String useNumPay() throws Exception {

		if (pay_password == null || pay_password.equals("")) {
			this.addFieldError("pay_password", "支付密码不能为空!");
			return goPay();
		}
		if (order_id_str == null || order_id_str.equals("")) {
			return goPay();
		}
		if(order_id_str.endsWith("a")||order_id_str.endsWith("b")){
			order_id_str=order_id_str.substring(0, order_id_str.length()-1);
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
				orderPayNum = 1;
				directorderdetail = this.directorderdetailService
						.getByOrderId(order_id_str);
				direct_id=directorderdetail.getDirect_id();
				directsell =  this.directsellService.get(direct_id);
				String order_num_str=directorderdetail.getOrder_num();
				if(this.directsellService.get(direct_id)!=null){
					//获取购买人数
					int ordernum=0,max_buy=0;
					HashMap mMap = new HashMap();
					mMap.put("trade_id", direct_id);
					List dList=this.directsellService.getList(mMap);
					Map dMap=(Map) dList.get(0);
					if(dMap.get("buynum")==null){
						ordernum=0;
					}else {
						ordernum =Integer.parseInt(dMap.get("buynum").toString()) ;
					}
					max_buy=Integer.parseInt(this.directsellService.get(direct_id).getMax_buy());
					if("0".equals(directorderdetail.getPay_state())){
						if(max_buy-ordernum-Integer.parseInt(order_num_str)<0){
							can_buy="库存不足";
							//this.addFieldError("pay_password", can_buy);
							return goPay();
							//return NONE;
						}
					}
					
				}
				
				goodsorder = this.goodsorderService.get(order_id_str);
				double ear = directorderdetail.getEarnest();
				double pri = directorderdetail.getGoods_price();
				double num = Double.parseDouble(directorderdetail.getOrder_num());
				if (directorderdetail.getPay_state().equals("0")) {
					use_num_pay = (ear * num) + "";
				} else if (directorderdetail.getPay_state().equals("1")) {
					goodsorder.setTatal_amount(pri * num
							+ goodsorder.getShip_free());
					use_num_pay = (pri - ear) * num + goodsorder.getShip_free() + "";
				}
				
				// 判断是否超过定金支付时间
				if ("0".endsWith(directorderdetail.getPay_state())
						&& ifEndPayDeposit().equals("1")) {
					//超过支付定金的时间
					dealTimeOutPayOrder("0");
					this.addFieldError("pay_password", "亲爱的会员,很抱歉,您的订单已经超过定金支付时间!");
					return goPay();
				}
				// 判断是否超过尾款支付时间
				if ("1".equals(directorderdetail.getPay_state())
						&& ifEndPayFinalPayment().equals("1")) {
					//超过支付尾款的时间
					dealTimeOutPayOrder("1");
					this.addFieldError("pay_password", "亲爱的会员,很抱歉,您的订单已经超过尾款支付时间!");
					return goPay();
				}else{
					this.directorderdetailService.useNumPay(order_id,
							session_cust_id, session_user_id, Double.parseDouble(use_num_pay));
				}
			} else {
				this.addFieldError("pay_password", "支付密码不正确,重新输入!");
				return goPay();
			}
			getResponse().sendRedirect("/bmall_Goodsorder_buyorderlist.action");
		} else {
			getResponse().sendRedirect("/login.html");
		}
		return null;
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
			if(order_id_str.endsWith("a")||order_id_str.endsWith("b")){
				order_id_str=order_id_str.substring(0, order_id_str.length()-1);
			}
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
	/**
	 * @author : CYC
	 * @param :direct_id     预售标示      
	 * @date Mar 27, 2014 9:40:37 AM
	 * @Method Description :刷新订单价格
	 */
	public void stockstatus() throws IOException {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			// 获取预售标识
			String pointsgoods_id = request.getParameter("pointsgoods_id");
			// 获取购买数量
			String goodsnum = request.getParameter("goodsnum");
			Directsell dircetsell = directsellService.get(direct_id);
			String stock = "";
			//获取库存数
			if(dircetsell!=null&&!"1".equals(dircetsell.getIs_del())){
				stock = dircetsell.getStock();
				if(Integer.parseInt(stock) - Integer.parseInt(goodsnum)<0){
					out.write("1");
					 return ;
				}
			}else{
				out.write("0");
				return ;
			}
			String[] direct_ids = direct_id.split(",");
			String[] order_num = goodsnum.split(",");
			Goods goods=new Goods();
			int limit_num=0,now_buy=0,buied =0,l_b=0;
			for(int i=0;i<direct_ids.length;i++ ){
				 directsell =this.directsellService.get(direct_ids[i]);
				 String is_limit= directsell.getIs_limit();
				 if(is_limit==null||"1".equals(is_limit)){
					 out.write("4");
					 return ;
				 }
				 limit_num=Integer.parseInt(directsell.getLimit_num());
				 now_buy=Integer.parseInt(order_num[i]);
				 Map bMap=new HashMap();
				 bMap.put("direct_id",direct_ids[i]);
				 bMap.put("cust_id",this.session_cust_id);
				 List bList=this.directorderdetailService.getList(bMap);
				 String b_num="";
				  for(int j=0;j<bList.size();j++){
					  Map bnMap=new HashMap();
					  bnMap=(Map) bList.get(j);
					  b_num=bnMap.get("order_num").toString();
					  l_b=Integer.parseInt(b_num) ;
					  buied=l_b+buied;
				  }
				 if((buied+now_buy)>limit_num){
					 out.write("2");
					 return ;
				 }else {
					 out.write("3");
					 return ;
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			out.write("4");
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
				// 获取商品信息
				Map detailMap = new HashMap();
				detailMap.put("order_id", order_id);
				detailList = this.orderdetailService.getList(detailMap);
				orderdetaiCount = detailList.size();
				// 获取店铺信息
				shopconfig = shopconfigService.getByCustID(goodsorder.getSell_cust_id());
				return goUrl("order_evaluate");
			} else {
				return null;
			}
		}
	}

	/**
	 * @author : HXK
	 * @throws Exception
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :前台商品评价
	 */
	public String orderEvaluate() throws Exception {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			// 构造参数
			Map map = new HashMap();
			map.put("order_id", order_id);
			map.put("orderdetaiCount", orderdetaiCount);
			map.put("order_goods_id_str", order_goods_id_str);
			map.put("order_goods_feng_str", order_goods_feng_str);
			map.put("order_goods_content_str", order_goods_content_str);
			map.put("session_cust_id", this.session_cust_id);
			map.put("session_user_id", this.session_user_id);
			map.put("order_delivery_speed", order_delivery_speed);
			map.put("order_desc", order_desc);
			map.put("order_service_attitude", order_service_attitude);
			map.put("sell_cust_id", sell_cust_id);
			// 操作处理
			this.directorderdetailService.orderEvaluate(map);
			return null;
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
				// 获取商品信息
				Map detailMap = new HashMap();
				detailMap.put("order_id", order_id);
				detailList = this.orderdetailService.getList(detailMap);
				orderdetaiCount = detailList.size();
				// 获取店铺信息
				shopconfig = shopconfigService.getByCustID(goodsorder
						.getSell_cust_id());
				order_area = AreaFuc
						.getAreaNameByMap(goodsorder.getArea_attr());
				return goUrl("order_confirm_receipt");
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
			if (this.directorderdetailService
					.orderConfirmReceipt(order_id, pay_password,
							session_cust_id, session_user_id, Double.parseDouble(use_num_pay))) {
				this.addFieldError("pay_password", "支付密码不正确,重新输入!");
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
					return orderConfirmReceiptView();
				} else {
					return null;
				}
				
			} else {
				return null;
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
	 * @Method Description :插入积分：目前默认购买多少钱 送多少积分
	 * @author: HXK
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
			coutinter = (Double.parseDouble(cfg_sc_pointsrule) * Double.parseDouble(god.getTatal_amount().toString()))/100
					+ Double.parseDouble(meminter.getFund_num());
			meminter.setFund_num(coutinter.toString());
			memberinterService.update(meminter);
			// 插入积分流
			Interhistory interhistory = new Interhistory();
			interhistory.setCust_id(b_cust_id);
			interhistory.setInter_in(god.getTatal_amount().toString());
			interhistory.setInter_out("0");
			interhistory.setThisinter(coutinter.toString());
			interhistory.setUser_id(this.session_user_id);
			interhistory.setReason("购买预售商品获取积分,商品订单号为:" + goodsorder_id);
			interhistoryService.insert(interhistory);

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
	 * 方法描述：修改订单的时候，插入订单异动表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void insertOrderTrans(String order_id, String reason,
			String order_state) {
		ordertrans = new Ordertrans();
		ordertransService.inserOrderTran(order_id, session_cust_id, session_user_id,
				reason, order_state, session_user_name);
	}
	/**
	 * @author : HXK
	 * @param :
	 * @date Mar 28, 2014 1:33:55 PM
	 * @Method Description :将资金从运营转入卖家
	 */
	private void sellerFundManage(String oid) {
		Goodsorder order = this.goodsorderService.get(oid);
		if (order != null) {
			double comm_free = order.getComm_free();// 手续费
			Double order_momey = 0.0;
			// 订单总金额
			order_momey = order.getTatal_amount();
			// 获取卖家的ID
			String seller_cust_id = order.getSell_cust_id();

			// 运营商资金处理,将金额从运营商中扣除
			sysfundService.freezeNum( order_momey, 1);

			// 卖家资金处理
			double j1= memberfundService.freezeNum(seller_cust_id, (order_momey - comm_free), 1);
			// 卖家的资金异动
			Fundhistory buy_fh = new Fundhistory();
			buy_fh.setBalance(j1);
			buy_fh.setCust_id(seller_cust_id);
			buy_fh.setFund_in(order_momey);
			buy_fh.setFund_out(comm_free);
			buy_fh.setUser_id(this.session_user_id);
			buy_fh.setReason("卖家收到订单号:" + oid + "支付" + order_momey + "元,扣除手续费"
					+ comm_free + "元");
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
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");// 设置日期格式
				String buy_cust_id = this.session_cust_id;
				Refundapp refundapp = new Refundapp();
				refundapp.setBuy_cust_id(buy_cust_id);
				refundapp.setBuy_user_id(this.session_user_id);
				refundapp.setBuy_date(df.format(new Date()));
				refundapp.setOrder_id(order_id);
				if (buy_refund_type != null
						&& !buy_refund_type.equals("--请选择退款理由--")) {
					refundapp.setBuy_type(buy_refund_type);
				} else {
					this.addFieldError("buy_refund_type", "请选择退款理由");
					return orderBuyRefundmentView();
				}
				if (buy_refund_reason != null && !buy_refund_reason.equals("")) {
					refundapp.setBuy_reason(buy_refund_reason);
				} else {
					this.addFieldError("buy_refund_reason", "请填写退款说明");
					return orderBuyRefundmentView();
				}
				this.directorderdetailService.orderBuyRefundment(order_id,
						refundapp, session_cust_id, session_user_id);
				this.addActionMessage("已提交退款申请,请等待卖家处理!");
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
				return orderSellerRefundmentView();
			}
			if (order_id != null) {
				// 信息处理操作
				if (this.directorderdetailService.sellerAgreeRefundment(
						order_id, session_cust_id, session_user_id,
						pay_password, Double.parseDouble(use_num_pay))) {
					this.addFieldError("pay_password", "支付密码不正确,重新输入!");
					return orderSellerRefundmentView();
				}
				this.addActionMessage("已提交同意退款,且已将资金退款给买家!");
				return orderRefundmentView();
			} else {
				return null;
			}
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
				}
				// 操作退款
				this.directorderdetailService.sellerDisAgreeRefundment(
						order_id, seller_refund_reason, session_cust_id,
						session_user_id);
				this.addActionMessage("已提交拒绝退款处理!");
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
				gCommparaList("buy_refund");
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

	
}
