/*
 
 * Package:com.rbt.action
 * FileName: FundrechargeAction.java 
 */
package com.rbt.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Fundhistory;
import com.rbt.model.Fundrecharge;
import com.rbt.model.Memberfund;
import com.rbt.model.Payment;
import com.rbt.model.Rechargeablecards;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IFundrechargeService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IPaymentService;
import com.rbt.service.IRechargeablecardsService;
import com.rbt.service.ISysfundService;
/**
 * @function 功能 会员资金充值记录action类
 * @author 创建人 CYC
 * @date 创建日期 Tue Jul 12 13:10:48 CST 2014
 */
@Controller
public class FundrechargeAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	public Fundrecharge fundrecharge;
	public Payment payment;
	public Memberfund memberfund;
	public Fundhistory fundhistory;
	public Rechargeablecards rechargeablecards;

	/*******************业务层接口****************/
	@Autowired
	public IFundrechargeService fundrechargeService;
	@Autowired
	public IPaymentService paymentService;
	@Autowired
	public IMemberfundService memberfundService;
	@Autowired
	public IFundhistoryService fundhistoryService;
	@Autowired
	private ISysfundService sysfundService;
	@Autowired
	private IRechargeablecardsService rechargeablecardsService;

	/*********************集合******************/
	public List memberfundList;//会员余额信息集合
	public List fundrechargeList;//会员余额充值记录信息集合
	public List paymentList;//支付平台信息集合

	/*********************字段******************/
	public String order_state_s;//审核状态
	public String cuts_name_s;//会员名称
	public String payplat_s;//支付平台
	public String starttime_s;//支付时间开始
	public String endtime_s;//支付时间结束
	public String cust_name;//客户名字
	public String Date;//时间
	public String Billno;//产生订单号
	public String url;//地址
	public String pay_name;//支付人
	public String order_id_s;//订单号
	public String cfg_defaultpaymentPC = SysconfigFuc.getSysValue("cfg_defaultpaymentPC");//PC端默认充值方式
    public String cardskey;//卡号
	/**
	 * 方法描述：返回新增会员余额充值记录页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
        
		HttpServletRequest request =getRequest();
		request.setCharacterEncoding("gb2312");
		HashMap map=new HashMap();
		map.put("enabled", "0");
		paymentList=this.paymentService.getList(map);
		for(int i=0;i<paymentList.size();i++){
			HashMap listMap = new HashMap();
			listMap=(HashMap)paymentList.get(i);
			if(listMap.get("pay_code")!=null && listMap.get("pay_code").equals("goldpay")){
				paymentList.remove(i);
			}
		}
		
		//获取系统时间
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
		java.util.Date currentTime = new java.util.Date();//得到当前系统时间 
	    Date = formatter.format(currentTime); //将日期时间格式化
	    
	    
	    //产生订单号
	    java.text.SimpleDateFormat formatter2 = new java.text.SimpleDateFormat("yyyyMMddHHmmss"); 
	    Billno = formatter2.format(currentTime) + this.session_cust_id + "2";
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增会员余额充值记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		
		if("0".equals(fundrecharge.getPayplat())){
			this.addFieldError("fundrecharge.payplat", "请选择支付平台");
		}
		String payvalue=String.valueOf(fundrecharge.getFund_num());
		//获取支付平台对象
		String payplat = fundrecharge.getPayplat();
		//插入十位数的随机数订单号
		fundrecharge.setOrder_id(RandomStrUtil.getNumberRand());
		//字段验证
		super.commonValidateField(fundrecharge);
		if(super.ifvalidatepass){
			return add();
		}
		//跳转到相关平台支付  支付后返回触发一个方法对fundrecharge进行插入一条未审核的数据 ，等待管理员审核后才生效
		getResponse().sendRedirect("/include/components/payment/"+payplat+"/index.jsp?cust_id="+ this.session_cust_id +"&payvalue="+ payvalue+"");
		return add();
	}
	/**
	 * @author HZX
	 * @Method Description : 非支付接口，普通新增
	 * @date : Jan 28, 2014 2:26:22 PM
	 */
	public String tryinsert() throws Exception {

		this.fundrecharge.setCust_id(this.session_cust_id);
		this.fundrecharge.setUser_id(this.session_user_id);
			super.commonValidateField(fundrecharge);
			if(super.ifvalidatepass){
				return add();
			}
			//插入十位数的随机数订单号
			fundrecharge.setOrder_id(RandomStrUtil.getNumberRand());
			Memberfund memberfund = new Memberfund();
			memberfund=this.memberfundService.get(this.session_cust_id);
			Double fund_num=Double.parseDouble(memberfund.getFund_num());
			Double allfun=fundrecharge.getFund_num()+fund_num;
			memberfund.setFund_num(allfun+"");
			memberfund.setUse_num(allfun+"");
			this.memberfundService.update(memberfund);
			this.fundrechargeService.insert(fundrecharge);
			this.addActionMessage("账户充值成功！");
			this.fundrecharge = null;
			return add();
		}
	/**
	 * 方法描述：修改会员余额充值记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(ValidateUtil.isRequired(fundrecharge.getOrder_state())||fundrecharge.getOrder_state().equals("0")){
			this.addFieldError("fundrecharge.order_state", "请选择审核状态！");
			return view();
		}
		if(fundrecharge.getTrade_id()!=null&&!"".equals(fundrecharge.getTrade_id())){
			if(fundrecharge.getOrder_state().equals("1")){
				//审核通过后，将充值的资金 从 平台的的冻结资金中 解冻到可用资金中
				this.sysfundService.freezeNum(fundrecharge.getFund_num(),1);
				//审核通过会员资金操作
				this.memberfundService.outAndInNum(fundrecharge.getCust_id(), fundrecharge.getFund_num(), 1);
				this.fundrechargeService.update(fundrecharge);
				fundOnlineManage(fundrecharge.getOrder_id(),fundrecharge.getCust_id(),this.session_user_id,fundrecharge.getFund_num().toString());
				this.addActionMessage("会员余额充值审核通过！");
				
			}else{
				this.fundrechargeService.update(fundrecharge);
				this.addActionMessage("会员余额充值已审核！");
			}
		}
		return auditlist();
	}
	
	/**
	 * 
	 * @param order_id
	 * @param buy_cust_id
	 * @param buy_user_id
	 * @param fund_num
	 */
	private void fundOnlineManage(String order_id,String  buy_cust_id,String sell_user_id,String fund_num){
 		Double buy_fund_num = Double.parseDouble(fund_num);
 		//买家资金处理
 		Double buy_use_num = 0.0;//可用金额
 		Memberfund buy_mf = memberfundService.get(buy_cust_id);
 		
 		if(buy_mf != null){
 			buy_use_num = Double.parseDouble(buy_mf.getUse_num());
 		}
 		//买家的资金异动
 		Fundhistory buy_fh = new Fundhistory();
 		buy_fh.setBalance(buy_use_num);
 		buy_fh.setCust_id(buy_cust_id);
 		buy_fh.setFund_in(buy_fund_num);
 		buy_fh.setFund_out(0.00);
 		buy_fh.setUser_id(sell_user_id);
 		buy_fh.setReason("会员为充值订单号:"+order_id+",在线充值"+fund_num+"元");
 		fundhistoryService.insert(buy_fh);
 	    
 	}
	
	
	/**
	 * 方法描述：删除会员余额充值记录信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.fundrechargeService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除余额充值记录成功");
		}else{
			this.addActionMessage("删除余额充值记录失败");
		}

		return list();
	}
	/**
	 * 方法描述：删除会员余额充值记录信息
	 * @return
	 * @throws Exception
	 */
	public String auditdelete() throws Exception {
		String id = this.fundrecharge.getTrade_id();
		id = id.replace(" ", "");
		this.fundrechargeService.delete(id);
		this.addActionMessage("删除余额充值记录成功");
		return auditlist();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	
	public String list() throws Exception {
		commonList("1");
		return goUrl(INDEXLIST);
	}
	/**
	 * @author LHY
	 * 充值审核列表
	 * @return
	 * @throws Exception
	 */
	public String auditlist() throws Exception {
		commonList("2");
		return goUrl("auditindex");
	}
	/**
	 * @author：XBY
	 * @date：Feb 11, 2014 5:07:18 PM
	 * @Method Description：查询核心方法
	 */
	public void commonList(String order_type){
		//绑定下拉列表
		HashMap map=new HashMap();
		map.put("enabled", "0");
		paymentList=this.paymentService.getList(map);
		//页面搜索提交的参数
		Map pageMap = new HashMap();
		if(order_state_s!=null && !order_state_s.equals("")) pageMap.put("order_state", order_state_s);
		else if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("order_state","0,1,2");
		}else if("1".equals(order_type)){
			pageMap.put("order_state", "1,2");
		}else if("2".equals(order_type)){
			pageMap.put("order_state", "0");
		}
		if(cuts_name_s!=null && !cuts_name_s.equals("")) pageMap.put("cust_name", cuts_name_s);
		if(payplat_s!=null && !payplat_s.equals("")&&!payplat_s.equals("0")) pageMap.put("payplat", payplat_s);
		if(starttime_s!=null && !starttime_s.equals("")&&!starttime_s.equals("0")) pageMap.put("starttime", starttime_s);
		if(endtime_s!=null && !endtime_s.equals("")&&!endtime_s.equals("0")) pageMap.put("endtime", endtime_s);
		//搜索订单
        if(!validateFactory.isRequired(order_id_s)){
        	pageMap.put("order_id", order_id_s);
        }
		
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		//去除掉运营商在fundrecharge表中的cust_id
		pageMap.put("charge_cust_id", "0");
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.fundrechargeService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		fundrechargeList = this.fundrechargeService.getList(pageMap);
		memberfund=this.memberfundService.get(this.session_cust_id);
	}
	/**
	 * 方法描述：根据主键找出会员余额充值记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		Map map=new HashMap();
		map.put("cust_id", fundrecharge.getCust_id());
		fundrechargeList=this.fundrechargeService.getList(map);
		Map mapvalue=new HashMap();
		if(fundrechargeList!=null&&fundrechargeList.size()>0){
			mapvalue=(HashMap)fundrechargeList.get(0);
		}
		if(mapvalue!=null&&mapvalue.get("cust_name")!=null){
			cust_name=mapvalue.get("cust_name").toString();
		}
		paymentList=paymentService.getList(new HashMap());
		return goUrl(VIEW);
	}
	
	
	public String recardkey() throws Exception{
		if(cardskey!=null&&!"".equals(cardskey)){
			rechargeablecards = rechargeablecardsService.getCardkey(cardskey);
			if(rechargeablecards==null){
				this.addActionMessage("卡号不存在");
				return list();
			}else if(rechargeablecards!=null&&"1".equals(rechargeablecards.getCardsstate())){
				//卡号已经充值过
				this.addActionMessage("卡号已经充值过");
				return list();
			}else if(rechargeablecards!=null&&"0".equals(rechargeablecards.getCardsstate())){
				//查看充值卡是否到期
				Date nowdate=new Date(); 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00", Locale.CHINA);
				Date d = sdf.parse(rechargeablecards.getCardsdate());
				boolean flag = d.before(nowdate);
				if(flag){
					//充值卡过期
					this.addActionMessage("充值卡过期");
					return list();
				}else{
					//卡号正常，可以进行充值
					//修改充值卡状态
					rechargeablecardsService.recharge(rechargeablecards,this.session_cust_id,this.session_user_id);
					this.addActionMessage("充值成功");
					return list();
				}
				
			}
		}else{
			this.addActionMessage("系统异常");
			return list();
		}
		return list();
	}
	
	/**
	 * @author : CYC
	 * @param :
	 * @date Mar 6, 2014 2:36:29 PM
	 * @Method Description :判断充值卡是否使用过或者已经过期
	 */
	public void checkcardkey() throws Exception {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String cardkey=this.getRequest().getParameter("cardkey");
		if(cardkey!=null&&!"".equals(cardkey)){
			rechargeablecards = rechargeablecardsService.getCardkey(cardkey);
			if(rechargeablecards==null){
				//卡号不存在
				out.write("0");
				return;
			}else if(rechargeablecards!=null&&"1".equals(rechargeablecards.getCardsstate())){
				//卡号已经充值过
				out.write("1");
				return;
			}else if(rechargeablecards!=null&&"0".equals(rechargeablecards.getCardsstate())){
				//查看充值卡是否到期
				Date nowdate=new Date(); 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00", Locale.CHINA);
				Date d = sdf.parse(rechargeablecards.getCardsdate());
				boolean flag = d.before(nowdate);
				if(flag){
					//充值卡过期
					out.write("3");
				}else{
					//卡号正常，可以进行充值
					out.write("2");
					
				}
				
			}
		}else{
			//系统异常
			out.write("4");
		}
	}
	
	/**
	 * @return the FundrechargeList
	 */
	public List getFundrechargeList() {
		return fundrechargeList;
	}
	/**
	 * @param fundrechargeList
	 *  the FundrechargeList to set
	 */
	public void setFundrechargeList(List fundrechargeList) {
		this.fundrechargeList = fundrechargeList;
	}
	public String getOrder_state_s() {
		return order_state_s;
	}
	public void setOrder_state_s(String order_state_s) {
		this.order_state_s = order_state_s;
	}
	public String getCuts_name_s() {
		return cuts_name_s;
	}
	public void setCuts_name_s(String cuts_name_s) {
		this.cuts_name_s = cuts_name_s;
	}
	public String getPayplat_s() {
		return payplat_s;
	}
	public void setPayplat_s(String payplat_s) {
		this.payplat_s = payplat_s;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	
	public String getStarttime_s() {
		return starttime_s;
	}

	public void setStarttime_s(String starttime_s) {
		this.starttime_s = starttime_s;
	}

	public String getEndtime_s() {
		return endtime_s;
	}

	public void setEndtime_s(String endtime_s) {
		this.endtime_s = endtime_s;
	}

	/**
	 * @return the fundrecharge
	 */
	public Fundrecharge getFundrecharge() {
		return fundrecharge;
	}
	/**
	 * @param Fundrecharge
	 *            the fundrecharge to set
	 */
	public void setFundrecharge(Fundrecharge fundrecharge) {
		this.fundrecharge = fundrecharge;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(fundrecharge == null){
			fundrecharge = new Fundrecharge();
		}
		String id = fundrecharge.getTrade_id();
		if(!ValidateUtil.isDigital(id)){
			fundrecharge = this.fundrechargeService.get(id);
		}
		System.out.println(fundrecharge);
	}
    
	/**
	 * 导出充值记录列表
	 */
	public String listExport() throws Exception{
		if(this.fundrechargeService.fundchargeExport(getResponse())) {
		   this.addActionMessage("导出订单成功！");	
		}else {
		   this.addActionMessage("导出订单失败！");
		}
		return null;
	}
	
	
	
	
	
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Memberfund getMemberfund() {
		return memberfund;
	}

	public void setMemberfund(Memberfund memberfund) {
		this.memberfund = memberfund;
	}

	public List getMemberfundList() {
		return memberfundList;
	}

	public void setMemberfundList(List memberfundList) {
		this.memberfundList = memberfundList;
	}

	public List getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List paymentList) {
		this.paymentList = paymentList;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getBillno() {
		return Billno;
	}

	public void setBillno(String billno) {
		Billno = billno;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

