/*
 
 * Package:com.rbt.action
 * FileName: RefundappAction.java 
 */
package com.rbt.webappaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rbt.common.Constants;
import com.rbt.common.Md5;
import com.rbt.common.util.DateUtil;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.function.CommparaFuc;
import com.rbt.function.LogisticsFuc;
import com.rbt.function.MemberuserFuc;
import com.rbt.function.MessageAltFuc;
import com.rbt.function.RefundappFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Buyeraddr;
import com.rbt.model.Exchange;
import com.rbt.model.Fundhistory;
import com.rbt.model.Goodsorder;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberuser;
import com.rbt.model.Orderdetail;
import com.rbt.model.Ordertrans;
import com.rbt.model.Refundapp;
import com.rbt.model.Sendmode;
import com.rbt.service.IBuyeraddrService;
import com.rbt.service.IExchangeService;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.IOrdertransService;
import com.rbt.service.IPaymentService;
import com.rbt.service.IRefundappService;
import com.rbt.service.ISendmodeService;
import com.rbt.service.IShopconfigService;
import com.rbt.service.ISysfundService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * @function 功能 退款申请表action类ceshi
 * @author 创建人 HXK
 * @date 创建日期 Fri Mar 29 16:04:49 CST 2014
 */
@Controller
public class WebApprefundappAction extends WebAppbaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	public Refundapp refundapp;
	public Goodsorder goodsorder;
	public Memberfund memberfund;
	public Sendmode sendmode;
	public Orderdetail orderdetail;
	/*******************业务层接口****************/
	@Autowired
	private IRefundappService refundappService;
	@Autowired
	private IGoodsorderService goodsorderService;
	@Autowired
	public ISendmodeService sendmodeService;
	@Autowired
	public IOrderdetailService orderdetailService;
	@Autowired
	public IOrdertransService ordertransService;
	@Autowired
	public IBuyeraddrService buyeraddrService;
	@Autowired
	public IMemberfundService memberfundService;
	@Autowired
	private ISysfundService sysfundService;
	@Autowired
	public IFundhistoryService fundhistoryService;
	@Autowired
	public IShopconfigService shopconfigService;
	@Autowired
	private IExchangeService exchangeService;
	@Autowired
	private IPaymentService paymentService;
	/*********************集合********************/
	//退款申请表信息
	public List refundappList;
	//订单状态
	public List commparaStateList;
	public List hcommparaList;
	//运营商地址集合信息
	public List shopconfigList;
	public List paymentList;
	//
	public List buyeraddrList;
	/*********************字段********************/
	public String seller_name;//卖家
	public String buy_name; //买家
	public String order_count;//订单总金额
	public String order_id_s;//订单号
	public String buy_cust_name_s;//买家
	public String refund_state_s;//退款状态
	public String info_state_s;//处理状态
	public String seller_cust_name_s;//卖家
	public String buy_cust_id;//买家标识
	public String seller_cust_id; //卖家标识
	public String seller_cust_id_no; //卖家标识
	public String goods_order;//订单编号
	public String order_id;// 订单号
	public List detailList;// 订单详细列表信息
	public Ordertrans ordertrans;// 订单异动记录
	public List addrList;// 收货地列表
	public String logistics_query;// 快递查询结果
	public String kuai_number;// 快递号
	public String kuai_company;// 快递公司
	public String kuai_company_code;// 快递公司代码
	// 退款******
	public String refundapp_id;// 退款id
	public String is_get;// 是否收到货0未收到1收到
	public String is_return;// 是否退货0不退1退
	public String imgString;// 图片凭证
	public String imgStrings;// 图片凭证imgStrings
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
	public int cfg_Refund_deny_num = Integer.parseInt(SysconfigFuc.getSysValue("cfg_Refund_deny_num"));// 卖家拒绝退款最多次数
	public boolean is_buyer = false;// 判断是否买家
	public Integer orderdetaiCount = 0;
	public String order_area;// 地区
	public List sendmodeList;// 物流公司
	public String addr_id;// 收货地址标识
	public Buyeraddr buyeraddr;// 收货地址对象
	public String pay_password;// 支付密码
	public String is_return_s;
	public String shopconfig_id;
	public String return_no_s;
	public String is_return_str;
	public String refund_goods_id_str;//退货/退款选择的商品ID
	public String order_send_mode;// 配送物流
	public String order_send_num;// 物流单号
	public Exchange exchange;
	public String refund_reason;
	public String refund_type;
    public String cancel_trade_desc;//买家撤销申请的理由
    public int if_refund;//商品是否支持退货 1：支持:0不支持
    public int if_regoods;//商品是否支持退货1：支持，0不支持
    public int if_exchangegoods;//商品是否支持换货 1：支持 0不支持
    public int if_appinfo;//是否能申请售后 操作时间是无法申请售后 1：支持 0不支持
    public String pay_type;
    public String trade_id;
    public String member_icon_refund;
    public String member_icon_ex;
    public String apply_num_str;
    public String back_goods_num;

	/**
	 * 方法描述：返回新增退款申请表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增退款申请表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(refundapp);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.refundappService.insert(refundapp);
		this.addActionMessage("新增退款申请表成功！");
		this.refundapp = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改退款申请表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String update() throws Exception {
		if(refundapp.getAdmin_reason()==null||refundapp.getAdmin_reason().equals("")){
			this.addFieldError("refundapp.admin_reason","请输入处理理由！");
			return view();
		}
		if(refundapp.getRefund_amount()==null||refundapp.getRefund_amount().equals("")){
			this.addFieldError("refundapp.refund_amount","请输入退款金额！");
			return view();
		}
		if(goods_order==null||this.goodsorderService.get(goods_order)==null){
			return view();
		}
		String type=this.getRequest().getParameter("type");
		goodsorder=this.goodsorderService.get(goods_order);
		Map refundMap=new HashMap();
		refundMap.put("goodsorder", goodsorder);
		refundMap.put("refundapp",refundapp);
		refundMap.put("type", type);
		refundMap.put("session_user_id",this.session_user_id);
		//处理退款	
		this.refundappService.dealRefund(refundMap);
		this.addActionMessage("修改退款申请表成功！");
		return list();
	}
	/**
	 * 方法描述：删除退款申请表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.refundappService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除退款申请表成功");
		}else{
			this.addActionMessage("删除退款申请表成功失败");
		}
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		commonList("");
		return goUrl(INDEXLIST);
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String buylist() throws Exception {
		buy_cust_id=this.session_cust_id;
		commonList("");
		return goUrl("mbRefund");
	}
	/**
	 * @author : HZX
	 * @date : Feb 11, 2014 12:34:10 PM
	 * @Method Description :通用列表
	 */
	@SuppressWarnings("unchecked")
	public void commonList(String returnType){
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
		if(!validateFactory.isRequired(is_return_s)&&!"999".equals(is_return_s)){
			pageMap.put("is_return", is_return_s);
		}
		if(!validateFactory.isRequired(return_no_s)){
			pageMap.put("return_no", return_no_s);
		}
		
		if(!validateFactory.isRequired(buy_cust_id)){
			pageMap.put("buy_cust_id", buy_cust_id);
		}
		if(!validateFactory.isRequired(returnType)){
			pageMap.put("is_return", returnType);
		}
		//根据页面提交的条件找出信息总数
		int count=this.refundappService.getCount(pageMap);
		//分页插件
		pageMap = super.webAppPageTool(count,pageMap);
		refundappList = this.refundappService.getList(pageMap);
		String orderidStr="";
		for (int i = 0; i < refundappList.size(); i++) {
			HashMap listMap = (HashMap) (refundappList).get(i);
			if (listMap.get("goods_id_str") != null) {
				orderidStr += listMap.get("goods_id_str").toString() + ",";
			}
		}
		if(orderidStr!=null&&!"".equals(orderidStr)){
			orderidStr=orderidStr.substring(0, orderidStr.length()-1);
			Map detailMap = new HashMap();
			detailMap.put("trade_ids_s", orderidStr);
			detailList = this.orderdetailService.getList(detailMap);
		}
		
		gOrderCommparaState("mbbuy_refund");
	}
	
	
	/**
	 * 方法描述：根据主键找出退款申请表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.refundapp.getTrade_id();
		if(id==null || id.equals("")){
			refundapp = new Refundapp();
		}else{
			refundapp = this.refundappService.get(id);
			Goodsorder goodsorder=new Goodsorder();
			if(this.goodsorderService.get(refundapp.getOrder_id())!=null){
				goodsorder=this.goodsorderService.get(refundapp.getOrder_id());
				order_count=goodsorder.getTatal_amount().toString();
			}
		}
		gOrderCommparaState("buy_refund");
		buy_name=this.memberService.getCustName(refundapp.getBuy_cust_id());
		if(refundapp.getSeller_cust_id()!=null&&!refundapp.getSeller_cust_id().equals("")){
			seller_name=this.memberService.getCustName(refundapp.getSeller_cust_id());
		}
		return goUrl(VIEW);
	}
	/**
	 * @author: HXK
	 * @date : Apr 20, 2014 11:32:11 AM
	 * @Method Description : 查看退款详情
	 */

	public String buyfundview() throws Exception {
		commonView();
		return goUrl("mbRefundDetail");
	}
	/**
	 * @author: HXK
	 * @date : Apr 20, 2014 11:32:11 AM
	 * @Method Description : 查看退货详情
	 */

	public String buygoodsview() throws Exception {
		commonView();
		return goUrl("mbReturnGoodDetail");
	}
	
	/**
	 * @author : HZX
	 * @date : Feb 11, 2014 12:43:25 PM
	 * @Method Description :通用查看
	 */
	public void commonView(){
		String id = this.refundapp.getTrade_id();
		String sell_cust_id="0";
		if(id==null || id.equals("")){
			refundapp = new Refundapp();
		}else{
			refundapp = this.refundappService.get(id);
		}
		gOrderCommparaState("buy_refund");
		if(refundapp.getOrder_id()!=null){
			goodsorder=goodsorderService.get(refundapp.getOrder_id());
			Map detailMap = new HashMap();
			if(refundapp.getGoods_id_str()!=null&&!"".equals( refundapp.getGoods_id_str())){
				detailMap.put("trade_id_s", refundapp.getGoods_id_str());
				detailList = this.orderdetailService.getList(detailMap);
			}
			// 获取卖家会员信息
			sell_cust_id = goodsorder.getSell_cust_id();
		}
		//获取买家名称
		Memberuser muser=new Memberuser();
		muser=MemberuserFuc.getuserName(refundapp.getBuy_cust_id());
		buy_name=muser.getUser_name();
		//将收货地址转换为中文
		if(refundapp.getArea_attr()!=null&&!"".equals(refundapp.getArea_attr())){
			refundapp.setArea_attr(AreaFuc.getAreaNameByMap(refundapp.getArea_attr()));
		}
		//获取支付信息
		gOrderPayment();
		//获取物流公司
		gOrderSendmode();
		//获取物流信息
		if(refundapp.getSend_mode()!=null&&!"".equals(refundapp.getSend_num())){
			getLogistics(refundapp.getSend_mode(),refundapp.getSend_num());
		}
		//获取商家退货地址
		buyeraddr=new Buyeraddr();
		buyeraddr=buyeraddrService.getbuyerByCust_id(buyeraddr, sell_cust_id);
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
	
	/**退款业务开始*/
	/**
	 * @author : HXK
	 * @throws Exception
	 * @date Mar 28, 2014 2:35:04 PM
	 * @Method Description :前台买家退款申请-操作
	 */
	public String orderBuyRefundment() throws Exception {
		
		
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/webapplogin.html");
			return NONE;
		} else {
			if (order_id != null) {
				
				String trade_ids[] = refund_goods_id_str.split(",");
				String[] apply_num = apply_num_str.split(",");
				if("2".equals(is_return_str)){
					goodsorder = new Goodsorder();
					goodsorder = goodsorderService.get(order_id);
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
					String buy_cust_id = this.session_cust_id;
					if (refund_type != null && !refund_type.equals("--请选择理由--")) {
						exchange.setRefund_type(refund_type);
					} else {
						this.addFieldError("refund_type", "请选择理由");
					}
					if (refund_reason != null && !refund_reason.equals("")) {
						if (refund_reason.indexOf(">") > -1) {
							this.addFieldError("refund_reason", "系统检测到非法字符，请填写问题描述");
						}
						if (refund_reason.length() > 201) {
							this.addFieldError("refund_reason", "字数太多，请重新修改问题描述");
						}
					} else {
						this.addFieldError("refund_reason", "请填写问题描述");
					}
					String tk_no="";
					tk_no="RH"+RandomStrUtil.getNumberRand();
					//生成退款流水号
					exchange.setReturn_no(tk_no);
					imgString = imgStrings;
					// 校验图片
					boolean is_save = checkImg();
					if (is_save) {
						exchange.setImg_path(imgStrings);
					}
					if(refund_goods_id_str==null||"".equals(refund_goods_id_str)){
						this.addFieldError("re_goods_id_str", "请选择要换货的商品");
					}else {
						exchange.setDetail_id_str(refund_goods_id_str);
					}
					if(area_attr==null||"".equals(area_attr)||"0".equals(area_attr)){
						this.addFieldError("areaDiv", "请选择地区");
					}
					if(exchange.getBuy_address()==null||"".equals(exchange.getBuy_address())){
						this.addFieldError("exchange.buy_address", "请填写收货地址");
					}
					if(exchange.getMconsignee()==null||"".equals(exchange.getMconsignee())){
						this.addFieldError("exchange.mconsignee", "请填写收货人姓名");
					}
					if(exchange.getMmobile()==null||"".equals(exchange.getMmobile())){
						this.addFieldError("exchange.mmobile", "请填写收货人姓名");
					}
					
					if (super.ifvalidatepass) {
						selectArea();
						return orderBuyRefundmentView();
					}
					exchange.setMarea_attr(area_attr);
					exchange.setBuy_reason( "["+ df.format(new Date()) + "]</br>"+refund_reason+"</br>" );
					
					exchange.setBuy_cust_id(buy_cust_id);
					exchange.setBuy_user_id(this.session_user_id);
					exchange.setBuy_date(goodsorder.getOrder_time());
					exchange.setOrder_id(order_id);
					exchange.setSeller_state("");
					// 0:等待卖家处理，1：换货成功，2：换货失败3:等待买家发货4等待商家确认收货5等待卖家发货6等待买家确认收货
					exchange.setRefund_state("0");
					exchange.setDeny_num("0");
					exchange.setEx_goods_num(back_goods_num);
					exchangeService.insert(exchange);
					for(int i=0;i<trade_ids.length;i++){
						orderdetail = this.orderdetailService.get(trade_ids[i]);
						Integer final_apply_num = Integer.valueOf(orderdetail.getFinal_apply_num())+Integer.valueOf(apply_num[i]);
						orderdetail.setFinal_apply_num(final_apply_num+"");
						this.orderdetailService.update(orderdetail);
					}
					// 更新订单状态为 换货中
					updateOrderDetailState(order_id,refund_goods_id_str,"7", "4");
					// 消息提醒
					//mestipBySeller("5", order_id);
					this.addActionMessage("已提交换货申请,请等待商家处理!");
					getResponse().sendRedirect("/webapp/exchange!buylist.action");
					return null;
				}else{
					goodsorder = new Goodsorder();
					goodsorder = goodsorderService.get(order_id);
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
					String buy_cust_id = this.session_cust_id;
					Refundapp refundapp = new Refundapp();
					refundapp = commonCheck(goodsorder, refundapp);
					refundapp.setBuy_reason(buy_refund_reason + "["+ df.format(new Date()) + "]");
					if (super.ifvalidatepass) {
						return orderBuyRefundmentView();
					}
					refundapp.setBuy_cust_id(buy_cust_id);
					refundapp.setBuy_user_id(this.session_user_id);
					refundapp.setBuy_date(df.format(new Date()));
					refundapp.setOrder_id(order_id);
					// 0:退款中，1：退款成功，2：退款失败
					refundapp.setRefund_state("0");
					refundapp.setInfo_state("");
					refundapp.setSeller_cust_id(goodsorder.getSell_cust_id());
					refundapp.setBack_goods_num(back_goods_num);
					refundappService.insert(refundapp);
					for(int i=0;i<trade_ids.length;i++){
						orderdetail = this.orderdetailService.get(trade_ids[i]);
						Integer final_apply_num = Integer.valueOf(orderdetail.getFinal_apply_num())+Integer.valueOf(apply_num[i]);
						orderdetail.setFinal_apply_num(final_apply_num+"");
						this.orderdetailService.update(orderdetail);
					}
					String orderdetai_states="";
					if(refundapp.getIs_return().equals("0")){
						orderdetai_states="1";
					}else if(refundapp.getIs_return().equals("1")){
						orderdetai_states="4";
					}
					// 更新订单详情商品状态为 详情商品状态：0：正常，1，退款中，2：退款关闭，3：退款成功,4退货中，5退货关闭，6，退货成功，7，换货中，8换货关闭，9换货成功
					updateOrderDetailState(order_id,refundapp.getGoods_id_str(),orderdetai_states, "4");
					// 消息提醒
				//	mestipBySeller("5", order_id);
					this.addActionMessage("已提交申请,请等待商家处理!");
					return buylist();
			}
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
	public void updateOrderDetailState(String s_order_id, String s_goods_str,String orderdetai_state ,String s_order_state)throws Exception {
		// 订单状态表示：0：订单取消 1：未付款 2：已付款 3：已发货 4：退款中 5：退款成功 6：退款失败：7：交易成功 8：已评价
		// orderdetail_state订单详情商品状态表示0：正常，1，退款中，2：退款关闭，3：退款成功,4换货中5换货成功6 换货失败
		refundappService.updateOrderDetailState(s_goods_str, orderdetai_state);
		//插入订单异动信息表
		String reason=CommparaFuc.getReason(s_order_state,null);
		insertOrderTrans(s_order_id, reason, s_order_state);
	}
	
	/**
	 * @author : HXK
	 * @param :
	 * @throws ParseException 
	 * @date 2015-01-22
	 * @Method Description :会员申请售后选择商品
	 */
	public String appRefundGoods() throws IOException, ParseException {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/webapplogin.html");
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
				//获取订单状态
				Map map = new HashMap();
				map.put("para_code", "order_state");
				commparaStateList = commparaService.getList(map);
				if_appinfo=comparerefundapp(goodsorder.getSure_time(),cfg_exchange_limt_date);		
				return goUrl("mbapplyGoods");
			} else {
				return null;
			}
		}
	}
	
	
	
	/**
	 * @author : HXK
	 * @param :
	 * @throws ParseException 
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :前台买家退款申请-查看
	 */
	public String orderBuyRefundmentView() throws IOException, ParseException {
		
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/webapplogin.html");
			return NONE;
		} else {
			if (order_id != null && refund_goods_id_str!=null) {
				String trade_ids[] = refund_goods_id_str.split(",");
				String[] apply_num = apply_num_str.split(",");
				for(int i=0;i<trade_ids.length;i++){
					orderdetail = this.orderdetailService.get(trade_ids[i]);
					orderdetail.setApply_num(apply_num[i]);
					this.orderdetailService.update(orderdetail);
				}
				
				// 获取订单信息
				goodsorder = new Goodsorder();
				goodsorder = this.goodsorderService.get(order_id);
				// 获取商品信息
				Map detailMap = new HashMap();
				detailMap.put("order_id", order_id);
				detailList = this.orderdetailService.getList(detailMap);
				
				
				//过滤去掉优惠券红包
				for(int i=0;i<detailList.size();i++){
					Map gdMap=new HashMap();
					gdMap=(HashMap)detailList.get(i);
					//保留原来的小计价格
					gdMap.put("old_subtotal", gdMap.get("subtotal"));
					String subtotal=RefundappFuc.getGoodsTotal(order_id,gdMap.get("trade_id").toString());//
					subtotal =String.format("%.2f", (Double.valueOf(subtotal) * (Double.valueOf(gdMap.get("apply_num").toString())/Double.valueOf(gdMap.get("order_num").toString()))))+"";
					gdMap.put("subtotal", subtotal);
				}
				
				orderdetaiCount = detailList.size();
				// 获取店铺信息
				gCommparaList("buy_refund");
				Map hmap = new HashMap();
				hmap.put("para_code", "buy_exchange");
				hcommparaList = commparaService.getList(hmap);
				//获取订单状态
				Map map = new HashMap();
				map.put("para_code", "order_state");
				commparaStateList = commparaService.getList(map);
				// if_refund;//商品是否支持退货 1：支持:0不支持
               if_refund=comparerefundapp(goodsorder.getSure_time(),cfg_refund_limt_date);		
               //  if_regoods;//商品是否支持退货1：支持，0不支持
               if_regoods=comparerefundapp(goodsorder.getSure_time(),cfg_regoods_limt_date);		
			   // if_exchangegoods;//商品是否支持换货 1：支持 0不支持
               if_exchangegoods=comparerefundapp(goodsorder.getSure_time(),cfg_exchange_limt_date);		
               return goUrl("mbapplyRefund");
			} else {
				return null;
			}
		}
	}
	
	//时间比较  if_refund 大于返回1 小于返回-1 异常返回0
	public int comparerefundapp(String order_date,int datenumber) throws ParseException{
		// if_refund;//商品是否支持退货 1：支持:0不支持
	    //  if_regoods;//商品是否支持退货1：支持，0不支持
	    // if_exchangegoods;//商品是否支持换货 1：支持 0不支持
		 int ret=0;
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = sdf.parse(order_date);
		 int if_refund=DateUtil.compare_date(DateUtil.getCurrentTime().toString(),DateUtil.nDaysAftertoday(date,datenumber).toString());	
		 if(if_refund==1){
			 ret=0;//不支持
		 }else if(if_refund==-1){
			 ret=1;//支持
		 }
		 return ret;
	}
	
	public String orderBuyRefundmentUpdate() throws Exception {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (order_id != null) {
				
				String trade_ids[] = refund_goods_id_str.split(",");
				String[] apply_num = apply_num_str.split(",");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				goodsorder = new Goodsorder();
				goodsorder = goodsorderService.get(order_id);
				if("2".equals(is_return_str)){
					exchange = this.exchangeService.getByOrderId(order_id);
					if (refund_type != null && !refund_type.equals("--请选择理由--")) {
						exchange.setRefund_type(refund_type);
					} else {
						this.addFieldError("refund_type", "请选择理由");
					}
					if (refund_reason != null && !refund_reason.equals("")) {
						if (refund_reason.indexOf(">") > -1) {
							this.addFieldError("refund_reason", "系统检测到非法字符，请填写问题描述");
						}
						if (refund_reason.length() > 201) {
							this.addFieldError("refund_reason", "字数太多，请重新修改问题描述");
						}
					}
					/*String tk_no="";
					tk_no="rh"+RandomStrUtil.getNumberRand();
					//生成退款流水号
					exchange.setReturn_no(tk_no);*/
					// 校验图片
					boolean is_save = checkImg();
					if (is_save) {
						exchange.setImg_path(imgStrings);
					}
					if(refund_goods_id_str==null||"".equals(refund_goods_id_str)){
						this.addFieldError("re_goods_id_str", "请选择要换货的商品");
					}else {
						exchange.setDetail_id_str(refund_goods_id_str);
					}
					selectArea();
					if(area_attr==null||"".equals(area_attr)||"0".equals(area_attr)){
						this.addFieldError("areaDiv", "请选择地区");
					}
					if(exchange.getBuy_address()==null||"".equals(exchange.getBuy_address())){
						this.addFieldError("exchange.buy_address", "请填写收货地址");
					}
					if(exchange.getMconsignee()==null||"".equals(exchange.getMconsignee())){
						this.addFieldError("exchange.mconsignee", "请填写收货人姓名");
					}
					if(exchange.getMmobile()==null||"".equals(exchange.getMmobile())){
						this.addFieldError("exchange.mmobile", "请填写收货人手机号码");
					}
					
					exchange.setBuy_reason(exchange.getBuy_reason() + "  ["
							+ df.format(new Date()) + "]</br>  " + refund_reason+"</br>");
					
					if (cfg_Refund_deny_num <= Integer.parseInt(exchange
							.getDeny_num())) {
						is_deny_num = true;
						this.addFieldError("refund_type", "您已经申请过"
								+ cfg_Refund_deny_num + "次，已达上限");
					}
					if (super.ifvalidatepass) {
						return orderBuyRefundmentView();
					}
					exchange.setRefund_state("0");
					exchange.setMarea_attr(area_attr);
					exchange.setEx_goods_num(back_goods_num);
					exchangeService.update(exchange);
					for(int i=0;i<trade_ids.length;i++){
						orderdetail = this.orderdetailService.get(trade_ids[i]);
						Integer final_apply_num = Integer.valueOf(orderdetail.getFinal_apply_num())+Integer.valueOf(apply_num[i]);
						orderdetail.setFinal_apply_num(final_apply_num+"");
						this.orderdetailService.update(orderdetail);
					}
					this.addActionMessage("已提交退款申请,请等待卖家处理!");
					// 更新订单详情商品状态为 换货中
					updateOrderDetailState(order_id,exchange.getDetail_id_str(),"7", "4");
					// 消息提醒
					mestipBySeller("5", order_id);
				}else {
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
					for(int i=0;i<trade_ids.length;i++){
						orderdetail = this.orderdetailService.get(trade_ids[i]);
						Integer final_apply_num = Integer.valueOf(orderdetail.getFinal_apply_num())+Integer.valueOf(apply_num[i]);
						orderdetail.setFinal_apply_num(final_apply_num+"");
						this.orderdetailService.update(orderdetail);
					}
					this.addActionMessage("已提交退款申请,请等待卖家处理!");
					// 更新订单详情商品状态为 退款中
					updateOrderDetailState(order_id,refundapp.getGoods_id_str(),"1", "4");
					// 消息提醒
					mestipBySeller("5", order_id);
				}
				
				return buylist();
			} else {
				return null;
			}
		}
	}

	
	
	/**
	 * @Method Description :通过选择要退款的商品，订单详情获取统计退款的上限金额
	 * @author : HZX
	 * @throws IOException 
	 * @date : Jan 9, 2015 2:35:28 PM
	 */
	public String allRefund() throws IOException{
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String orderde_id=request.getParameter("orderde_id");
		String[] ids=orderde_id.split(",");
		double  all_return=0;
		for(int i=0; i< ids.length;i++){
			Orderdetail od=this.orderdetailService.get(ids[i]);
			double p=od.getGoods_price();
			String n=od.getOrder_num();
			all_return=(p*Double.parseDouble(n))+all_return;
		}
		//double 四舍五入 取2位小数
		BigDecimal b=new BigDecimal(all_return);  
		all_return = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
		out.print(all_return);
		return null;
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
	 * @author : HXK 方法描述：会员修改订单状态
	 * @return
	 * @throws Exception
	 */
	public void updateOrderState(String s_order_id, String s_order_state)throws Exception {
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
		ordertransService.inserOrderTran(order_id, session_cust_id,
				session_user_id, reason, order_state, session_user_name);
	}
	/**
	 * @author : HXK 退货 买家发货20150112 14.16
	 * @return
	 * @throws Exception
	 */
	public String buyDelivery() throws Exception {
		if (refundapp.getTrade_id() != null && !"".equals(refundapp.getTrade_id())) {
			refundapp = this.refundappService.get(refundapp.getTrade_id());
			if(refundapp!=null && refundapp.getRefund_state().equals("3")){
				// 物流公司
				if (order_send_mode != null && !"".equals(order_send_mode)) {
					refundapp.setSend_mode(order_send_mode);
				}
				// 快递单号
				if (order_send_num != null && !"".equals(order_send_num)) {
					refundapp.setSend_num(order_send_num);
				} else {
					this.addFieldError("order_send_num_tip", "请填写运单号码");
					return buygoodsview();
				}
				//设置已经发货
				refundapp.setRefund_state("4");
				refundappService.update(refundapp);
				// 修改订单状态
				this.addActionMessage("发货成功");
			}
			else{
				// 修改订单状态
				this.addActionMessage("发货失败");
			}
		}
		return buylist();
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
	 * @Method Description :新增修改退款申请公共校验
	 * @author: HXK
	 * @date : Jan 10, 2015 12:57:41 PM
	 * @param 
	 * @return return_type
	 */
	public Refundapp commonCheck(Goodsorder goodsorder, Refundapp refundapp) {
		if (buy_refund_type != null && !buy_refund_type.equals("--请选择理由--")) {
			refundapp.setBuy_type(buy_refund_type);
		} else {
			this.addFieldError("buy_refund_type", "请选择理由");
		}
		if (buy_refund_reason != null && !buy_refund_reason.equals("")) {
			if (buy_refund_reason.indexOf(">") > -1) {
				this.addFieldError("buy_refund_reason", "系统检测到非法字符，请填写问题描述");
			}
			if (buy_refund_reason.length() > 201) {
				this.addFieldError("buy_refund_reason", "字数太多，请重新修改问题描述");
			}
		} else {
			this.addFieldError("buy_refund_reason", "请填写问题描述");
		}
		if (is_return_str != null && !is_return_str.equals("")) {
			refundapp.setIs_return(is_return_str);
			//is_return:1:退货0：退款
			String tk_no="";
			if(is_return_str.equals("0")){
				//退款编号 格式为：rf+十位随机数
				tk_no="RF"+RandomStrUtil.getNumberRand();
			}else if(is_return_str.equals("1")){
				//退货编号 格式为：rg+十位随机数
				tk_no="RG"+RandomStrUtil.getNumberRand();
			}
			//生成退款流水号
			refundapp.setReturn_no(tk_no);
		} else {
			this.addFieldError("is_return", "请选择退款或者退货");
		}
		// 校验图片
		boolean is_save = checkImg();
		if (is_save) {
			refundapp.setImg_path(imgString);
		}
		if (need_refund != null && !need_refund.equals("")&&Double.parseDouble(need_refund)>0) {
			if (Double.parseDouble(need_refund) > goodsorder.getTatal_amount()) {
				this.addFieldError("need_refund", "申请金额超过已付款金额");
			} else {
				refundapp.setRefund_amount((Double.parseDouble(need_refund) + "").trim());
			}
		} else {
			this.addFieldError("need_refund", "请填写需退款金额");
		}
		if(refund_goods_id_str==null||"".equals(refund_goods_id_str)){
			this.addFieldError("re_goods_id_str", "请选择要退款/退货的商品");
		}else {
			refundapp.setGoods_id_str(refund_goods_id_str);
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
		if (member_icon_ex != null && !member_icon_ex.equals("")) {
			if (member_icon_ex.indexOf(",") > -1) {
				String[] imgStrings = member_icon_ex.split(",");
				if (imgStrings.length >= 3) {
					this.addFieldError("refundapp.img_path", "最多3张图片");
					is_save = false;
				}
			}

		}
		return is_save;
	}
	
	/**
	 * @author : HZX
	 * @throws Exception 
	 * @date : Nov 5, 2014 9:47:34 AM
	 * @Method Description :取消退款
	 */
	public String cancelRefund() throws Exception {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/webapplogin.html");
			return goUrl("login");
		} else {
			if (trade_id != null) {
				// 获取订单信息
				refundapp = this.refundappService.get(trade_id);
				if (refundapp!=null&&refundapp.getRefund_state().equals("0")) {
					//申请售后类型，1：退货，0退款
					//详情商品状态：0：正常，1，退款中，2：退款关闭，3：退款成功,4退货中，5退货关闭，6，退货成功，7，换货中，8换货关闭，9换货成功
					String s_refund_state="2";
					if(refundapp.getIs_return()!=null&&"1".equals(refundapp.getIs_return())){
						s_refund_state="5";
					}else if(refundapp.getIs_return()!=null&&"0".equals(refundapp.getIs_return())) {
						s_refund_state="2";
					}
					this.refundappService.cancelRefund(trade_id,cancel_trade_desc,s_refund_state);
					String goods_id_str = refundapp.getGoods_id_str();
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
				}
			}
			cancel_trade_desc="";
			return buylist();
		}
	}
	
	public void commonBuyResponse(String order_id, String order_state)throws IOException {
		getResponse().sendRedirect("/webapp/goodsorder!buyOrderView.action?goodsorder.order_id="+ order_id + "&order_state=" + order_state);
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
    
	
/***************************以下为最新修改的退款/退货处理*********************************************************/
	
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
	
	/**退货
	 * @author QJY
	 * @return
	 * @throws Exception
	 */
	public String returnGoodsList() throws Exception{
		commonList("1");
		return goUrl("mbreturnGoodsList");
	}
	
	/**退款
	 * @author QJY
	 * @return
	 * @throws Exception
	 */
	public String refundGoodsList() throws Exception{
		commonList("0");
		return goUrl("mbrefundGoodsList");
	}
	
	/************退款开始*********退款处理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String refundGoodsView()throws Exception{
		commonView();
		return goUrl("mbrefundGoods");
	}
	
	/**退款详细查看
	 * 
	 * @return
	 * @throws Exception
	 */
	public String refundView()throws Exception{
		commonView();
		return goUrl("mbrefundGoodsDetails");
		
	}
	
	/**同意退款操作
	 *  
	 * @return
	 * @throws Exception
	 */
	public String agreeRefundGoods() throws Exception{
		
			//判断是 退货完退款 或者 退款 操作
			String is_return = this.refundapp.getIs_return();
			String seller_state = this.refundapp.getRefund_state();
				// 卖家退款给买家
				buyFundManage(refundapp.getTrade_id());
				
				String goods_id_str = refundapp.getGoods_id_str();
				refundappService.updateOrderDetailState(goods_id_str, "3");
				refundapp.setRefund_state("1");
				if(is_return.equals("0")){
					refundapp.setSeller_state(seller_state);
				}
				SimpleDateFormat df = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");// 设置日期格式
		        refundapp.setSeller_date(df.format(new Date()));
				this.refundappService.update(refundapp);//退款成功后执行邮件发送
				// 同意 消息提醒
				mestipByBuyer("12", order_id);
				// 插入订单异动记录
				ordertransService.inserOrderTran(order_id, session_cust_id, session_user_id,
						CommparaFuc.getReason("1", "同意退款,且已将资金退款给会员!"), "5", session_user_name);
                String message = "";
				if(is_return.equals("0")){
					message ="退款";
				}else if(is_return.equals("1")){
					message ="退货";
				}
				this.addActionMessage("同意"+ message+"申请,且已将资金退款给会员!");
				if(is_return.equals("0")){
					return refundGoodsList();
				}else if(is_return.equals("1")){
					return returnGoodsList();
				}
			
		return NONE;
	}
	
	/**不同意退款操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String disAgreeRefundGoods() throws Exception{
		
		if(ValidateUtil.isRequired(refundapp.getSeller_state())){
			this.addFieldError("refundapp.seller_state","请选择处理意见！");
			return refundGoodsView();
		}
		
		if(ValidateUtil.isRequired(refundapp.getSeller_reason())){
			this.addFieldError("refundapp.seller_reason","请输入拒接理由！");
			return refundGoodsView();
		}
		
		String goods_id_str = refundapp.getGoods_id_str();
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
		refundapp.setSeller_state(refundapp.getSeller_state());
		refundapp.setSeller_reason(refundapp.getSeller_reason());
		SimpleDateFormat df = new SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss");// 设置日期格式
        refundapp.setSeller_date(df.format(new Date()));
		refundapp.setRefund_state("2");//退款失败
		this.refundappService.update(refundapp);//更新完成执行邮件发送
		//拒接邮件模板
		mestipByBuyer("15", refundapp.getOrder_id());
		this.addActionMessage("处理退款申请成功！");
		return refundGoodsList();
	
	}
	
	/************退货开始**************退货处理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String returnGoodsView()throws Exception{
		commonView();
		return goUrl("mbreturnGoods");
	}
	
	/**退货详情查看
	 * 
	 * @return
	 * @throws Exception
	 */
	public String returnView()throws Exception{
		commonView();
		return goUrl("mbreturnGoodsDetails");
		
	}
	
	/**同意退货操作 1： 发送地址 2，收到货后-->退款
	 * 
	 * @return
	 * @throws Exception
	 */
	public String agreeReturnGoods() throws Exception{
		
		refundapp.setSeller_state(refundapp.getSeller_state());
		refundappService.update(refundapp);
		Map addressMap = new HashMap();
		addressMap.put("cust_id", this.session_cust_id);
		buyeraddrList =  this.buyeraddrService.getList(addressMap);
		buyeraddrList = ToolsFuc.replaceList(buyeraddrList, "");
		return goUrl("mbsendReturnAddress");
	
	}
	
	/**发送地址给申请退货的会员
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendAddressToMember() throws Exception{
		if(!ValidateUtil.isRequired(refundapp_id) && !ValidateUtil.isRequired(addr_id)){
			buyeraddr = this.buyeraddrService.get(addr_id);
			refundapp = this.refundappService.get(refundapp_id);
			if(buyeraddr !=null && refundapp != null){
				refundapp.setArea_attr(buyeraddr.getArea_attr());
				refundapp.setSell_address(buyeraddr.getAddress());
				refundapp.setConsignee(buyeraddr.getConsignee());
				refundapp.setTelephone(buyeraddr.getPhone());
				refundapp.setMobile(buyeraddr.getCell_phone());
				refundapp.setZip_code(buyeraddr.getPost_code());
				SimpleDateFormat df = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");// 设置日期格式
		        refundapp.setSeller_date(df.format(new Date()));
				refundapp.setRefund_state("3");//等待会员发货
				refundappService.update(refundapp);
			}
			this.addActionMessage("发送退货地址成功，等待收货");
		}else{
			this.addActionMessage("发送退货地址失败，请重新处理");
		}
		
		return returnGoodsList();
	}
	
	
	/**不同意退货操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String disAgreeReturnGoods() throws Exception{
		
		if(ValidateUtil.isRequired(refundapp.getSeller_state())){
			this.addFieldError("refundapp.seller_state","请选择处理意见！");
			return returnGoodsView();
		}
		
		if(ValidateUtil.isRequired(refundapp.getSeller_reason())){
			this.addFieldError("refundapp.seller_reason","请输入拒接理由！");
			return returnGoodsView();
		}
		
		String goods_id_str = refundapp.getGoods_id_str();
		refundappService.updateOrderDetailState(goods_id_str, "2");
		refundapp.setSeller_state(refundapp.getSeller_state());
		refundapp.setSeller_reason(refundapp.getSeller_reason());
		refundapp.setRefund_state("2");//退货失败
		SimpleDateFormat df = new SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss");// 设置日期格式
        refundapp.setSeller_date(df.format(new Date()));
		this.refundappService.update(refundapp);//更新完成执行邮件发送
		//拒接邮件模板
		mestipByBuyer("15", refundapp.getOrder_id());
		this.addActionMessage("处理退货申请成功！");
		return returnGoodsList();
	
	}
	
	
	/**
	 * @author : HXK
	 * @param :
	 * @date Mar 28, 2014 1:33:55 PM
	 * @Method Description :将资金从运营转入会员,平台冻结资金解冻可用资金
	 * @suggest：（建议）后期必须得移动Service 进行事务处理
	 */
	private void buyFundManage(String trade_id) {
		refundapp = refundappService.get(trade_id);
		Goodsorder order = this.goodsorderService.get(refundapp.getOrder_id());
		if (order != null) {
			Double refund_money = 0.0;
			// 退款金额
			refund_money = Double.parseDouble(refundapp.getRefund_amount());
			// 获取会员的ID
			String buy_cust_id = order.getBuy_cust_id();
			//获取卖家的ID
			String seller_cust_id = order.getSell_cust_id();
			/****以下处理方法必须移到Service 进行事务控制******/
			//买家资金处理（入金）
			Double i1= memberfundService.outAndInNum(buy_cust_id, refund_money, 1);
			//卖家资金处理（出金）
			Double i2= memberfundService.outAndInNum(seller_cust_id, refund_money, 0);
			//买家的资金异动
			Fundhistory buy_fh = new Fundhistory();
			buy_fh.setBalance(i1);
			buy_fh.setCust_id(buy_cust_id);
			buy_fh.setFund_in(refund_money);
			buy_fh.setFund_out(0.0);
			buy_fh.setUser_id(this.session_user_id);
			buy_fh.setReason("买家收到订单号:" + order.getOrder_id() + " 退款" + refund_money + "元");
			this.fundhistoryService.insert(buy_fh);
			//卖家的资金异动
			Fundhistory seller_fh = new Fundhistory();
			seller_fh.setBalance(i2);
			seller_fh.setCust_id(seller_cust_id);
			seller_fh.setFund_in(0.0);
			seller_fh.setFund_out(refund_money);
			seller_fh.setUser_id(this.session_user_id);
			seller_fh.setReason("已处理订单号："+ order.getOrder_id() +" 退款申请 " + refund_money+"元");
			this.fundhistoryService.insert(seller_fh);
			
		}
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
		Goodsorder gorder = new Goodsorder();
		gorder = goodsorderService.get(order_id);
		if (gorder != null) {
			MessageAltFuc mesalt = new MessageAltFuc();
			mesalt.messageAutoSend(mes_id, gorder.getBuy_cust_id(), gorder);
		}
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
		String psw="",pay_password="";
		String cust_id = getSession().getAttribute(Constants.CUST_ID).toString();
        if(!ValidateUtil.isRequired(cust_id) && !ValidateUtil.isRequired(request.getParameter("psw"))){
        	psw = request.getParameter("psw").toString();
        	memberfund = memberfundService.get(cust_id);
            pay_password = memberfund.getPay_passwd();
            psw = Md5.getMD5(psw.getBytes());
            if(!pay_password.equals(psw)){
            	out.write("0");
            }
        }

	}
	
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(refundapp == null){
			refundapp = new Refundapp();
		}
		String id = this.refundapp.getTrade_id();
		if(!ValidateUtil.isDigital(id)){
			refundapp = this.refundappService.get(id);
		}
		if(exchange == null){
			exchange = new Exchange();
		}
		String exchangeid = this.exchange.getTrade_id();
		if(!ValidateUtil.isDigital(exchangeid)){
			exchange = this.exchangeService.get(exchangeid);
		}
	}
	
	/**
	 * @return the RefundappList
	 */
	public List getRefundappList() {
		return refundappList;
	}
	/**
	 * @param refundappList
	 *  the RefundappList to set
	 */
	public void setRefundappList(List refundappList) {
		this.refundappList = refundappList;
	}

	public Goodsorder getGoodsorder() {
		return goodsorder;
	}
	public void setGoodsorder(Goodsorder goodsorder) {
		this.goodsorder = goodsorder;
	}
	public IGoodsorderService getGoodsorderService() {
		return goodsorderService;
	}
	public void setGoodsorderService(IGoodsorderService goodsorderService) {
		this.goodsorderService = goodsorderService;
	}
	/**
	 * @return the refundapp
	 */
	public Refundapp getRefundapp() {
		return refundapp;
	}
	/**
	 * @param Refundapp
	 *            the refundapp to set
	 */
	public void setRefundapp(Refundapp refundapp) {
		this.refundapp = refundapp;
	}

	public String getIs_return_str() {
		return is_return_str;
	}

	public void setIs_return_str(String is_return_str) {
		this.is_return_str = is_return_str;
	}

	public String getRefund_goods_id_str() {
		return refund_goods_id_str;
	}

	public void setRefund_goods_id_str(String refund_goods_id_str) {
		this.refund_goods_id_str = refund_goods_id_str;
	}
	
	
}

