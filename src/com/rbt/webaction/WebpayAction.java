/*
  
 
 * Package:com.rbt.webaction
 * FileName: WeborderAction.java 
 */
package com.rbt.webaction;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rbt.common.util.XmlUtil;
import com.rbt.model.Fundhistory;
import com.rbt.model.Fundrecharge;
import com.rbt.model.Goodsorder;
import com.rbt.model.Member;
import com.rbt.model.Memberuser;
import com.rbt.model.Orderdetail;
import com.rbt.model.Ordertrans;
import com.rbt.model.Trygoods;
import com.rbt.pay.wxpay.WXPay;
import com.rbt.pay.wxpay.protocol.scan_pay_protocol.WXScanPayReturnData;
import com.rbt.service.IBuyeraddrService;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IFundrechargeService;
import com.rbt.service.IGoodsevalService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.IOrdertransService;
import com.rbt.service.IPaymentService;
import com.rbt.service.IRefundappService;
import com.rbt.service.ISendmodeService;
import com.rbt.service.IShopconfigService;
/**
 * @author : HXK	
 * @date Mar 5, 2015 1:31:22 PM
 * @Method Description :支付管理
 */
@Controller
public class WebpayAction extends WebbaseAction {

	/** *****************实体层******************* */
	public Member buyMember;// 买家对象
	public Goodsorder goodsorder; // 订单对象
	public Orderdetail orderdetail;// 订单详情对象
	public Ordertrans ordertrans;// 订单异动记录
	public Fundhistory fundhistory;// 余额流水
	public Memberuser memberuser;
	public Memberuser memberuser_seller;
	public Trygoods trygoods;
	public Fundrecharge  fundrecharge;
	
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
	public IFundrechargeService fundrechargeService;

	/** *******************集合******************* */
	public List goodsList;// 商品
	public List orderList = new ArrayList();// 订单商品列表
	public List orderPayList;// 待付款订单列表
	public int orderPayNum;// 待付款订单个数
	public List detailList;// 订单详细列表信息
	public List paymentList;

	/** *******************字段******************* */
	// 订单相关数据
	public String cust_id_str;// 卖家标识串
	public String goods_id_str;// 卖家标识串
	public String goods_length_str;// 店铺商品个数串
	public String trade_id_str;// 流水号标识串
	public String goods_name_str;// 商品名称串
	public String order_num_str;// 订单个数串
	public String goods_amount_str;// 商品总价格串
	public String total_amount;// 订单总价
	public String order_type;// 订单类型
	// 订单支付相关
	public String sub_total_price;// 待支付订单金额
	public String flag;// 判断是否立即购买
	public String order_id_str;// 临时存储订单号
	public String order_id;// 订单号
	public String sell_cust_id; // 卖家标识
	public String goods_id;//普通商品ID
    public String order_pay_tip;
	public String loc;// 跳回登录前的位置
	//微信支付
	public String wxpay_scan;
	//消费 或者 购买
	public String is_recharge;
    /**
	 * @Method Description :微信支付-扫码支付，发起请求
	 * @author: HXK
	 * @date : Apr 10, 2015 1:54:58 PM
	 * @param 
	 * @return return_type
     * @throws Exception 
	 */
	public String wxScanPayreqData() throws Exception{
		// 判断是否登录
		if (this.session_cust_id.equals("") || this.session_cust_id.equals("0")) {
			getResponse().sendRedirect("/login.html");
			return null;
		}
		
		return goUrl("wxpayshow");
		
	}
	/**
	 * @Method Description :微信支付-扫码支付 接收到服务区成功标志，返回给微信服务起
	 * @author: HXK
	 * @date : Apr 10, 2015 1:54:58 PM
	 * @param 
	 * @return return_type
     * @throws Exception 
	 */
	public void wxScanPayRespone() throws Exception{
		WXScanPayReturnData  wxScanPayResData=new WXScanPayReturnData();
		wxScanPayResData.setReturn_code("SUCCESS");
		wxScanPayResData.setReturn_msg("OK");
	   	String getXmlString=WXPay.responeScanPayService(wxScanPayResData);
	   	System.out.println(getXmlString);
	}
	
    /**
	 * @Method Description :微信支付-扫码支付，支付成功跳转
	 * @author: HXK
	 * @date : Apr 10, 2015 1:54:58 PM
	 * @param 
	 * @return return_type
     * @throws Exception 
	 */
	public String wxScanPaySuccess() throws Exception{
		// 订单号
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
	    order_id_str = request.getParameter("order_id_str");
	    total_amount = request.getParameter("total_amount");
	    is_recharge = request.getParameter("is_recharge");
		
		return goUrl("wxpayshowsuccess");
	}
	
	/**
	 * @author : HXK
	 * @param :order_id_str订单号串
	 * @date Mar 27, 2015 9:40:37 AM
	 * @Method Description :刷新订单支付页面
	 */
	public void refreshWXPayTip() throws IOException {
		String retflag="0";
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			// 订单号
			String order_id_str = request.getParameter("order_id_str");
			if(order_id_str!=null&&!"".equals(order_id_str)){
			  goodsorder=this.goodsorderService.get(order_id_str);
			  if(goodsorder!=null&&goodsorder.getOrder_state()!=null){
				  if(goodsorder.getOrder_state().equals("2")){
					  retflag="1";//已经支付
				  }
			  }
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		out.write(retflag);
	}
	
	/**
	 * @author Administrator QJY      
	 * @method 判断充值订单是否已经存在
	 * @date 2015-09-14
	 * @throws Exception
	 */
	public void refreshWXRechargeTip()throws Exception{
		String retflag="0";
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			// 订单号
			String order_id_str = request.getParameter("order_id_str");
			if(order_id_str!=null&&!"".equals(order_id_str)){
			  fundrecharge=this.fundrechargeService.get(order_id_str);
			  if(fundrecharge!=null){
					  retflag="1";//已经充值成功。
			  }
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		out.write(retflag);
	}
	
	
	public void wxScanPayData() throws Exception{
		HttpServletResponse respons=ServletActionContext.getResponse();
		HttpServletRequest request =ServletActionContext.getRequest();
		System.out.println("########################in  BackServlet########################");
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		respons.setCharacterEncoding("UTF-8");
		// 调用核心业务类接收消息、处理消息
		try {
			XmlUtil.parseXml(request);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
	
	public String getSub_total_price() {
		return sub_total_price;
	}
	public void setSub_total_price(String sub_total_price) {
		this.sub_total_price = sub_total_price;
	}
	
	public String getOrder_id_str() {
		return order_id_str;
	}
	public void setOrder_id_str(String order_id_str) {
		this.order_id_str = order_id_str;
	}
	
	public String getWxpay_scan() {
		return wxpay_scan;
	}
	public void setWxpay_scan(String wxpay_scan) {
		this.wxpay_scan = wxpay_scan;
	}
	
	public String getIs_recharge() {
		return is_recharge;
	}
	public void setIs_recharge(String is_recharge) {
		this.is_recharge = is_recharge;
	}

	
	
}
