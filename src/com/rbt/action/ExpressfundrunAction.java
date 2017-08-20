/*
 
 * Package:com.rbt.action
 * FileName: ExpressfundrunAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.common.Constants;
import com.rbt.common.Md5;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Expressfund;
import com.rbt.model.Expressfundrun;
import com.rbt.model.Fundhistory;
import com.rbt.model.Memberfund;
import com.rbt.model.Payment;
import com.rbt.model.Sysfund;
import com.rbt.service.IExpressfundService;
import com.rbt.service.IExpressfundrunService;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IPaymentService;
import com.rbt.service.ISysfundService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录直通车资金流动信息action类
 * @author 创建人 HZX
 * @date 创建日期 Wed Jan 23 16:31:46 CST 2014
 */
@Controller
public class ExpressfundrunAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Expressfundrun expressfundrun;
	private Expressfund expressfund;
	private Memberfund  memberfund;
	private Payment payment;
	public Fundhistory fundhistory;

	/*******************业务层接口****************/
	@Autowired
	private IExpressfundrunService expressfundrunService;
	@Autowired
	private IExpressfundService expressfundService;
	@Autowired
	private IMemberfundService memberfundService;
	@Autowired
	private IPaymentService paymentService;
	@Autowired
	private IFundhistoryService fundhistoryService;
	@Autowired
	private ISysfundService sysfundService;

	/*********************集合******************/
	public List expressfundrunList;//直通车
	public List paymentList;//支付
	public List memberfundList;//会员余额
    public String user_fund;//可用余额
	/*********************字段******************/
	public String oldpasswd;//支付密码
	
	
	
	/**
	 * 方法描述：返回充值直通车资金流动信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		user_fund=this.memberfundService.get(this.session_cust_id).getUse_num();
		return goUrl("recharge");
	}
	
	public List topayment(){
		Map pageMap = new HashMap();
		paymentList = this.paymentService.getList(pageMap);
		return paymentList;
	}
	
	/**
	 * @author HZX
	 * @Method Description : 返回提现直通车资金流动信息页面
	 * @date : Jan 24, 2014 1:05:51 PM
	 */
	public String addwithdrawcash() throws Exception {
		expressfund=expressfundService.get(this.session_cust_id);
		return goUrl("withdrawcash");
	}
	
	/**
	 * 方法描述：新增记录直通车资金流动信息
	 * 
	 * @return
	 * @throws Exception
	 */
	
	public String insert() throws Exception {
		this.expressfundrun.setCust_id(this.session_cust_id);
		if (ValidateUtil.isRequired(this.getOldpasswd())) {
			this.addFieldError("oldpasswd", "支付密码不能为空");
			return add();
		}
		Memberfund memberfund = this.memberfundService.get(this.session_cust_id);
		if(memberfund.getPay_passwd()==null){
			this.addActionMessage("您还未设置支付密码！请到账户管理设置密码");
			return add();
		}
		Map pageMap = new HashMap();
		// 查找密码
		oldpasswd = Md5.getMD5(oldpasswd.getBytes());
		if (!oldpasswd.equals(memberfund.getPay_passwd())) {
			this.addFieldError("oldpasswd", "支付密码不正确，请重新输入");
			return add();
		}
		if(expressfundrun.getIncome()==null||expressfundrun.getIncome().equals("")||expressfundrun.getIncome()==0)
		{
			this.addActionMessage("请输入大于0的转入金额！");
			return add();
		}
		double income=expressfundrun.getIncome();
		if(income>payMoneyDouble)
		{
			this.addActionMessage("每次转入的最大金额为10000000.00元！");
			expressfundrun.setIncome(null);
			return add();
		}
		//计算当前余额
		expressfund=expressfundService.get(this.session_cust_id);
		double balance=expressfund.getSummoney();
		memberfund = memberfundService.get(this.session_cust_id);
		double fund_num=Double.parseDouble(memberfund.getFund_num());
		double use_num=Double.parseDouble(memberfund.getUse_num());
			if(income>use_num){
				this.addActionMessage("您的余额不足！");
				expressfundrun.setIncome(null);
				return add();
			}
			//计算余额总额和可用余额总额
			fund_num=fund_num-income;
			use_num=use_num-income;
			memberfund.setFund_num((fund_num+"").trim());
			memberfund.setUse_num((use_num+"").trim());
			//记录余额流水
			Fundhistory fundhistory=new Fundhistory();
			fundhistory.setFund_in(0.00);
			fundhistory.setFund_out(income);
			fundhistory.setBalance(use_num);
			fundhistory.setCust_id(this.session_cust_id);
			fundhistory.setUser_id(this.session_user_id);
			fundhistory.setReason("直通车余额转入："+income+"元");
			fundhistory.setAction_type("2");
			this.fundhistoryService.insert(fundhistory);
		double sumbalance=income+balance;
		if(sumbalance>accountMaxMoneyDouble)
		{
			this.addActionMessage("帐户的最大金额1000000000.00元！");
			expressfundrun.setIncome(null);
			return add();
		}
		String sumbString=sumbalance+"";
		this.expressfundrun.setBalance(Double.parseDouble(sumbString));
		//计算资金表金额
		this.expressfund.setSummoney(Double.parseDouble(sumbString));
		
		super.commonValidateField(expressfundrun);
		if(super.ifvalidatepass){
			return add();
		}
		this.expressfundService.update(expressfund);
		this.memberfundService.update(memberfund);
		this.sysfundService.outAndInNum(income,0);
		this.expressfundrunService.insert(expressfundrun);
		this.addActionMessage("转入成功！");
		this.expressfundrun = null;
		return list();
	}
/**
 * @author HZX
 * @Method Description : 实现提现
 * @date : Jan 24, 2014 1:08:21 PM
 */
	public String withdrawcashinsert() throws Exception {
		this.expressfundrun.setCust_id(this.session_cust_id);
		if (ValidateUtil.isRequired(this.getOldpasswd())) {
			this.addFieldError("oldpasswd", "支付密码不能为空");
			expressfundrun=null;
			return addwithdrawcash();
		}
		Memberfund memberfund = this.memberfundService.get(this.session_cust_id);
		if(memberfund.getPay_passwd()==null){
			this.addActionMessage("您还未设置支付密码！请到账户管理设置密码");
			expressfundrun=null;
			return addwithdrawcash();
		}
		Map pageMap = new HashMap();
		// 查找密码
		oldpasswd = Md5.getMD5(oldpasswd.getBytes());
		if (!oldpasswd.equals(memberfund.getPay_passwd())) {
			this.addFieldError("oldpasswd", "支付密码不正确，请重新输入");
			expressfundrun=null;
			return addwithdrawcash();
		}
		if(expressfundrun.getPay()==null||expressfundrun.getPay().equals(""))
		{
			this.addActionMessage("请输入转出金额！");
			return addwithdrawcash();
		}
		double pay=expressfundrun.getPay();
		if(pay>payMoneyDouble)
		{
			this.addActionMessage("每次转出的最大金额为10000000.00元！");
			expressfundrun=null;
			return addwithdrawcash();
		}
		//计算当前余额
		expressfund=expressfundService.get(this.session_cust_id);
		double balance=expressfund.getSummoney();
			if(pay>balance){
				this.addActionMessage("您转出的金额大于您的余额，请重新输入！");
				expressfundrun=null;
				return addwithdrawcash();
			}
		double sumbalance=balance-pay;
		String sumbString=sumbalance+"";
		this.expressfundrun.setBalance(Double.parseDouble(sumbString));
		//计算资金表金额
		this.expressfund.setSummoney(Double.parseDouble(sumbString));
		//计算余额总额
		memberfund = memberfundService.get(this.session_cust_id);
		double fund_num=Double.parseDouble(memberfund.getFund_num());
		double use_num=Double.parseDouble(memberfund.getUse_num());
		double pfund_num=fund_num+pay;
		double puse_num=use_num+pay;
		if(pfund_num>accountMaxMoneyDouble)
		{
			this.addActionMessage("余额的最大金额1000000000.00元！");
			return addwithdrawcash();
		}
		String payfund_num=pfund_num+"";
		memberfund.setFund_num(payfund_num.trim());
		memberfund.setUse_num((puse_num+"").trim());
		super.commonValidateField(expressfundrun);
		if(super.ifvalidatepass){
			return addwithdrawcash();
		}
		this.memberfundService.update(memberfund);
		this.sysfundService.outAndInNum(pay,1);
		this.expressfundService.update(expressfund);
		this.expressfundrunService.insert(expressfundrun);
		//新增资金流水
		fundhistory=new Fundhistory();
		fundhistory.setCust_id(this.session_cust_id);
		fundhistory.setFund_out(0.00);
		fundhistory.setFund_in(pay);
		fundhistory.setBalance(puse_num);
		fundhistory.setUser_id(this.session_user_id);
		fundhistory.setReason("直通车转出："+pay+"元");
		this.fundhistoryService.insert(fundhistory);
		this.addActionMessage("转出成功！");
		this.expressfundrun = null;
		return list();
	}
	/**
	 * 方法描述：修改记录直通车资金流动信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(expressfundrun);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.expressfundrunService.update(expressfundrun);
		this.addActionMessage("修改记录直通车资金流动信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录直通车资金流动信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.expressfundrunService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录直通车资金流动信息成功");
		}else{
			this.addActionMessage("删除记录直通车资金流动信息失败");
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
		Map pageMap = new HashMap();
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		
		//根据页面提交的条件找出信息总数
		int count=this.expressfundrunService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		expressfund=expressfundService.get(this.session_cust_id);
		expressfundrunList = this.expressfundrunService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * @author HZX
	 * @Method Description : 资金类型条件list
	 * @date : Jan 25, 2014 10:32:07 AM
	 */
	public String zjlist() throws Exception {
		Map pageMap = new HashMap();
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		String fundtype=expressfundrun.getFundtype();
		if (fundtype == null || fundtype.equals(""))
			return list();
		if (fundtype != null && fundtype.equals("1")) {
			pageMap.put("fundtype", fundtype);
		}
		if (fundtype != null && fundtype.equals("3")) {
			pageMap.put("fundtype", fundtype);
		}
		if (fundtype != null && fundtype.equals("1,3")) {
			pageMap.put("fundtype",fundtype);
		}
		//根据页面提交的条件找出信息总数
		int count=this.expressfundrunService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		expressfund=expressfundService.get(this.session_cust_id);
		expressfundrunList = this.expressfundrunService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录直通车资金流动信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.expressfundrun.getTrade_id();
		if(id==null || id.equals("")){
			expressfundrun = new Expressfundrun();
		}else{
			expressfundrun = this.expressfundrunService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the ExpressfundrunList
	 */
	public List getExpressfundrunList() {
		return expressfundrunList;
	}
	/**
	 * @param expressfundrunList
	 *  the ExpressfundrunList to set
	 */
	public void setExpressfundrunList(List expressfundrunList) {
		this.expressfundrunList = expressfundrunList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(expressfundrun == null){
			expressfundrun = new Expressfundrun();
		}
		String id = this.expressfundrun.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			expressfundrun = this.expressfundrunService.get(id);
		}
	}
	public Expressfund getExpressfund() {
		return expressfund;
	}
	public void setExpressfund(Expressfund expressfund) {
		this.expressfund = expressfund;
	}

	/**
	 * @return the expressfundrun
	 */
	public Expressfundrun getExpressfundrun() {
		return expressfundrun;
	}
	/**
	 * @param Expressfundrun
	 *            the expressfundrun to set
	 */
	public void setExpressfundrun(Expressfundrun expressfundrun) {
		this.expressfundrun = expressfundrun;
	}

	public Memberfund getMemberfund() {
		return memberfund;
	}
	public void setMemberfund(Memberfund memberfund) {
		this.memberfund = memberfund;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getOldpasswd() {
		return oldpasswd;
	}

	public void setOldpasswd(String oldpasswd) {
		this.oldpasswd = oldpasswd;
	}



}

