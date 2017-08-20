/*
 
 * Package:com.rbt.action
 * FileName: MemberfundAction.java 
 */
package com.rbt.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.Md5;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Fundhistory;
import com.rbt.model.Fundrecharge;
import com.rbt.model.Member;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberfundrun;
import com.rbt.model.Memberuser;
import com.rbt.model.Sysuser;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IFundrechargeService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberfundrunService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IMsgcheckService;
import com.rbt.service.ISysfundService;
import com.rbt.service.ISysuserService;

/**
 * @function 功能 会员余额action类
 * @author 创建人 CYC
 * @date 创建日期 Tue Jul 12 09:26:58 CST 2014
 */
@Controller
public class MemberfundAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_VALUE = "1";//隐藏字段message默认值
	/*******************实体层********************/
	public Memberfund memberfund;
	public Member member;
	public Fundhistory fundhistory;
	public Fundrecharge fundrecharge;
	public Memberfundrun memberfundrun;
	public Sysuser sysuser;
	public Memberuser memberuser;
	
	/*******************业务层接口****************/
	@Autowired
	public IMemberfundService memberfundService;
	@Autowired
	public IFundhistoryService fundhistoryService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IFundrechargeService fundrechargeService;
	@Autowired
	public IMemberfundrunService memberfundrunService;
	@Autowired
	private ISysfundService sysfundService;
	@Autowired
	private ISysuserService sysuserService;
	@Autowired
	public IMemberuserService memberuserService;
	@Autowired
	public IMsgcheckService msgcheckService;
	
	/*********************集合********************/
	public List memberfundList;//会员余额信息
	public List smemberfundList;//会员余额信息
	
	/*********************字段********************/
	public String cust_id;//用户id
	public String memberfund_name_s;//搜索字段
	public String radiochecked;//选择收入支出字段
	public String fund_num;//余额字段
	public String reason;//事由字段
	public String remark;//备注字段 
	public String oldpasswd;//旧密码
	public String newpasswd;//新密码
	public String confirmpasswd;//确认密码
	public String message;//消息
	public String cust_name;//会员余额字段
	public String enter;// 页面跳转标识符
	public String allfund;//平台总资金
	public String allusedfund;//平台总可用资金
	public String allfreezefund;//平台总冻结资金
	public HashMap fundMap;
	public String email;//邮箱
	public String phone;//手机
	public String email_code;//邮箱验证码
	public String phone_code;//手机验证码
    public String check_way;//验证方式
    public String is_set="0";//设置支付密码

	/**
	 * 方法描述：返回新增会员余额页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		if (!"".equals(this.memberfund.getCust_id())) {
			memberfund = this.memberfundService.get(this.memberfund.getCust_id());
			member=this.memberService.get(this.memberfund.getCust_id());
		}
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增会员余额
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
        if(ValidateUtil.isRmb(this.fund_num)){
        	this.addFieldError("fundrecharge.fund_num", "请输入金额");
        	return add();
        }
        if(fund_num.length()>8){
        	this.addFieldError("fundrecharge.fund_num", "充值的金额不能超过8位数！");
        	return add();
        }
        fundrecharge=new Fundrecharge();
        this.fundrecharge.setCust_id(memberfund.getCust_id());
        this.fundrecharge.setFund_num(Double.parseDouble(fund_num));
        this.fundrecharge.setUser_id(this.session_user_id);
        this.fundrecharge.setPayplat("goldpay");
        String tips="";
        //充值是否审核 0:审核 1：不审核
		String is_chongzhi=SysconfigFuc.getSysValue("cfg_IsAuditChonzhi");
		 //插入十位数的随机数订单号
		String fund_order = RandomStrUtil.getNumberRand();
        this.fundrecharge.setOrder_id(fund_order);
		if(is_chongzhi.equals("0")){//开启审核
			//审核
			fundrecharge.setOrder_state("0");
			this.fundrechargeService.insert(fundrecharge);
			//放入平台的冻结资金中，同时总资金也要增加，审核通过后，进行解冻
			this.sysfundService.addNumByOnLion(fundrecharge.getFund_num());
			tips="会员余额充值成功，请等待审核！";
			
		}else {//关闭审核
			//不审核
			fundrecharge.setOrder_state("1");
			String trade_id=this.fundrechargeService.insertGetPk(fundrecharge);
			fundrecharge=this.fundrechargeService.get(trade_id);
			//运营商资金操作
			this.sysfundService.outAndInNum(fundrecharge.getFund_num(), 1);
			//充值会员的资金操作
			this.memberfundService.outAndInNum(fundrecharge.getCust_id(),fundrecharge.getFund_num(),1);
			//余额系统充值流水
			fundhistoryManage(fundrecharge.getCust_id(),fund_order,fundrecharge.getFund_num());
			tips="会员余额充值成功！";
		}
		
		this.addActionMessage(tips);
		return list();
	}
    
	/**
	 * 会员资金流水记录
	 * @param memberfund
	 * @param fund_order
	 * @param fund
	 */
	public void fundhistoryManage(String cust_id,String fund_order,double fund){
		
		Memberfund md=this.memberfundService.get(cust_id);
		//买家资金处理
		Double buy_use_num = 0.0;//可用金额
		
		buy_use_num =  Double.valueOf(md.getUse_num());
		//充值的资金异动
		Fundhistory buy_fh = new Fundhistory();
		buy_fh.setBalance(buy_use_num);
		buy_fh.setCust_id(cust_id);
		buy_fh.setFund_in(fund);
		buy_fh.setFund_out(0.00);
		buy_fh.setUser_id(this.session_user_id);
		buy_fh.setReason("平台为订单号:"+fund_order+",余额充值"+fund+"元");
		fundhistoryService.insert(buy_fh);
		
	} 
	
	/**
	 * 方法描述：修改会员余额信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if (ValidateUtil.isRequired(fund_num) || ValidateUtil.isRmb(fund_num)) {
			this.addFieldError("fund_num", "不能为空且只能输入数字");
			return view();
		}
		if (ValidateUtil.isRequired(reason)) {
			this.addFieldError("reason", "事由不能为空");
			return view();
		}
		String cust_id = this.memberfund.getCust_id();
		// 实例化fundhistory
		fundhistory = new Fundhistory();
		if (!"".equals(cust_id)) {
			memberfund = this.memberfundService.get(cust_id);
			Double use_num=Double.parseDouble(memberfund.getUse_num());
			if(radiochecked.equals("1")){
				if(Double.parseDouble(fund_num)>use_num){
					this.addFieldError("fund_num", "支出不能大于用户可用余额");
					return view();
				}
			}
			
		}
		String isaudit=SysconfigFuc.getSysValue("cfg_IsAuditMemberfund");
		double amount = Double.valueOf(fund_num);
		//判断余额调整是否审核 0:是   1：否
		if(isaudit.equals("0")){
		   //调整的余额插入余额审核信息表中
		   memberfundrun=new Memberfundrun();
		   memberfundrun.setCust_id(cust_id);
		   memberfundrun.setInfo_state("0");
		   memberfundrun.setFund_num(amount);
		   memberfundrun.setFund_type(radiochecked);
		   memberfundrun.setReason(reason);
		   memberfundrun.setApply_user_name(session_user_name);
		   this.memberfundrunService.insert(memberfundrun);
		   this.addActionMessage("余额调整成功,请等待审核！");
		}else{
			// 判断收入 或者 支出  "0"表示收入  "1"表示支出
			if (radiochecked.equals("0")) {
				//会员入金操作
				double value_usefund = memberfundService.outAndInNum(cust_id,amount, 1);
				//平台入金操作
				sysfundService.outAndInNum(amount, 1);
				//流水记录
				fundhistory.setFund_in(amount);
				fundhistory.setFund_out(0.00);
				fundhistory.setBalance(value_usefund);
			} else {
				//会员出金操作
				double value_usefund = memberfundService.outAndInNum(cust_id,amount, 0);
				//平台出金操作
				sysfundService.outAndInNum(amount, 0);
				//流水记录
				fundhistory.setFund_in(0.00);
				fundhistory.setFund_out(amount);
				fundhistory.setBalance(value_usefund);
			}
			fundhistory.setCust_id(cust_id);
			fundhistory.setUser_id(this.session_user_id);
			fundhistory.setReason(reason);
			fundhistory.setRemark(remark);
			//字段验证
			super.commonValidateField(memberfund);
			if(super.ifvalidatepass){
				return view();
			}
			//更新余额表
			this.fundhistoryService.insert(fundhistory);
			this.addActionMessage("余额调整成功!");
		}
		return list();
	}

	/**
	 * 方法描述：删除会员余额信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.memberfundService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除会员余额成功");
		}else{
			this.addActionMessage("删除会员余额失败");
		}

		return list();
	}
	//修改支付密码
	public void updatePass() throws UnsupportedEncodingException{
		HttpServletRequest request=this.getRequest();
		HttpServletResponse response=this.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String password=request.getParameter("pawod");
		memberfund=this.memberfundService.get(this.session_cust_id);
		if(memberfund!=null){
			memberfund.setPay_passwd(password);
			memberfundService.update(memberfund);
		}
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {

		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (memberfund_name_s != null && !memberfund_name_s.equals("")){
			pageMap.put("cust_name", memberfund_name_s);
		}
		if(cust_id!=null && !cust_id.equals("")){
			pageMap.put("cust_id", cust_id);
		}
		pageMap.put("fund_cust_id", "0");
		//过滤地区
		pageMap=super.areafilter(pageMap); 
		// 根据页面提交的条件找出信息总数
		int count = this.memberfundService.getCount(pageMap);
 
		//分页插件
		pageMap = super.pageTool(count, pageMap);

		memberfundList = this.memberfundService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出会员余额信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(memberfund.getCust_id()!=null){
			if(accessControl(memberfund.getCust_id())){
				return list();
			}
		}
		// 当前登录的用户是管理员的时候，才执行审核动作
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			if (!"".equals(this.memberfund.getCust_id())) {
				memberfund = this.memberfundService.get(this.memberfund.getCust_id());
			}
			Map map = new HashMap();
			map.put("cust_id", this.memberfund.getCust_id());
			smemberfundList = this.memberfundService.getList(map);
			if (smemberfundList.size() > 0 && smemberfundList != null) {
				Map getmap = (HashMap) smemberfundList.get(0);
				if(getmap!=null&&getmap.get("cust_name")!=null){
					cust_name = getmap.get("cust_name").toString();
				}
			}
		} else {
			if (!this.session_cust_id.equals("")) {
				memberfund = this.memberfundService.get(this.session_cust_id);
			}
		}
		return goUrl(VIEW);
	}
	/**
	 * 查看圈币
	 * @author LHY
	 * @return
	 */
	public String virtual(){
		return goUrl("virtual");
	}
	/**
	 * 方法描述：支付宝接口代码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String Izhifubao() throws Exception {
		if (ValidateUtil.isRequired(String.valueOf((this.memberfund.getFund_num())))) {
			this.addFieldError("fundrecharge.fund_num", "支付金额不能为空");
			return add();
		}
		return "";
	}
	/**
	 * 判断是跳转到修改支付密码页面还是跳转到设置页面
	 * @author LHY
	 * @return
	 * @throws Exception 
	 */
	public String isNotSetUp() throws Exception{
		memberfund=this.memberfundService.get(this.session_cust_id);
		if(memberfund.getPay_passwd()==null){
			return PayPass();
		}else{
			return uppaypasswd();
		}
	}
	/**
	 * @function 功能 跳转到支付密码修改页
	 * @author 创建人 CYC
	 * @date 创建日期 Nov 4, 2014 11:25:22 AM
	 */
	public String uppaypasswd() throws Exception {
		return goUrl("updatepasswd");
	}

	/**
	 * @function 功能 设置支付密码
	 * @author 创建人 CYC
	 * @date 创建日期 Nov 4, 2014 11:20:22 AM
	 */
	public String updatepasswd() throws Exception {
		
		if (ValidateUtil.isRequired(this.getOldpasswd())) {
			this.addFieldError("oldpasswd", "旧密码不能为空");
			return uppaypasswd();
		}
		Map pageMap = new HashMap();
		// 查找密码
		oldpasswd = Md5.getMD5(oldpasswd.getBytes());
		pageMap.put("pay_passwd", oldpasswd);
		pageMap.put("cust_id", this.session_cust_id);
		memberfundList = this.memberfundService.getList(pageMap);
		if (memberfundList == null || memberfundList.size() == 0) {
			this.addFieldError("oldpasswd", "旧密码不正确，请重新输入");
			return uppaypasswd();
		}
		if (ValidateUtil.isRequired(this.getNewpasswd())) {
			this.addFieldError("newpasswd", "新密码不能为空");
			return uppaypasswd();
		}
		if (ValidateUtil.isRequired(this.getConfirmpasswd())) {
			this.addFieldError("confirmpasswd", "确认密码不能为空");
			return uppaypasswd();
		}
		if (ValidateUtil.isAlphas(this.getNewpasswd())) {
			this.addFieldError("newpasswd", "密码只能由数字、字母或者下划线组成，请重新输入");
			return uppaypasswd();
		}
		if (!this.getNewpasswd().equals(this.getConfirmpasswd())) {
			this.addFieldError("confirmpasswd", "确认密码与新密码不一致，请重新输入");
			return uppaypasswd();
		}
		memberfund = this.memberfundService.get(this.session_cust_id);
		// 对密码加密
		if (newpasswd != null && !newpasswd.equals("")) {
			newpasswd = Md5.getMD5(newpasswd.getBytes());
			Memberuser memberuser =this.memberuserService.get(this.session_user_id);
			String psd=memberuser.getPasswd();
			if(newpasswd.equals(psd)){
				this.addFieldError("newpasswd", "支付密码不能和登录密码一样！");
				return uppaypasswd();
			}
		} else {
			newpasswd = null;
		}
		memberfund.setPay_passwd(newpasswd);
		this.memberfundService.update(memberfund);
		this.addActionMessage("设置密码成功");
		return goUrl("updatepasswdSuccess");
		//return uppaypasswd();
	}
	
	//忘记支付密码
	public String goPass(){
		memberuser = this.memberuserService.get(this.session_user_id);
		return goUrl("goPass");
	}
	
	
	
	/**
	 * 邮箱验证码
	 * @return
	 */
	public String checkEmailCode(){
		check_way="0";
		if(commonCheckCode(email, email_code, "email_check")){
			return PayPass();
		}else{
			return goPass();
		}
	}
	
	/**
	 * 手机验证码
	 * @return
	 */
	public String checkPhoneCode(){
	    check_way="1";
       if(commonCheckCode(phone, phone_code, "phone_check")){
    	   return PayPass();
       }else{
    	   return goPass();
       }
	}
	
	
	public boolean commonCheckCode(String cp_phone,String cp_check,String phone_error){
		if(ValidateUtil.isRequired(cp_check)){
			this.addFieldError(phone_error, "验证码不能为空！");
			return false;
		}else{
			boolean ckfage=msgcheckService.checkMsgListToUse(cp_phone, cp_check);
			 if(ckfage==true){
				return true;
			}else{
				this.addActionMessage("验证码过期或错误，请重获取验证码！");
				return false;
			}
		}
	}
	/**
	 * 跳转到设置密码
	 * @author LHY
	 * @return
	 */
	public String PayPass(){
		is_set="1";
		return goUrl("setPaypasswd");
	}
	/**
	 *设置支付密码
	 *@author LHY 
	 * @throws Exception 
	 */
	public String setPayPassword() throws Exception{
		if (ValidateUtil.isRequired(this.getNewpasswd())) {
			this.addFieldError("newpasswd", "密码不能为空");
			return PayPass();
		}
		if (ValidateUtil.isRequired(this.getConfirmpasswd())) {
			this.addFieldError("confirmpasswd", "确认密码不能为空");
			return PayPass();
		}
		if (ValidateUtil.isAlphas(this.getNewpasswd())) {
			this.addFieldError("newpasswd", "密码只能由数字、字母或者下划线组成，请重新输入");
			return PayPass();
		}
		if (!this.getNewpasswd().equals(this.getConfirmpasswd())) {
			this.addFieldError("confirmpasswd", "确认密码与新密码不一致，请重新输入");
			return PayPass();
		}
		memberfund = this.memberfundService.get(this.session_cust_id);
		// 对密码加密
		if (newpasswd != null && !newpasswd.equals("")) {
			newpasswd = Md5.getMD5(newpasswd.getBytes());
			Memberuser memberuser =this.memberuserService.get(this.session_user_id);
			String psd=memberuser.getPasswd();
			if(newpasswd.equals(psd)){
				this.addFieldError("newpasswd", "支付密码不能和登陆密码一样！");
				HttpServletResponse response=getResponse();
				return PayPass();
			}
		} else {
			newpasswd = null;
		}
		if(memberfund!=null){
			memberfund.setPay_passwd(newpasswd);
			this.memberfundService.update(memberfund);
		}
		//return uppaypasswd();
		return goUrl("setPaypasswdSuccess");
	}
    
	/**
	 * @Method Description :
	 * @author : HZX
	 * @date : May 26, 2014 3:24:14 PM
	 */
	
	 public void isSame()throws Exception{
			HttpServletResponse response=getResponse();
			HttpServletRequest request=getRequest();
			getRequest().setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out=response.getWriter();
			String outstring="0";
			newpasswd=request.getParameter("newpasswd");
			if(!ValidateUtil.isRequired(newpasswd)){
				newpasswd = Md5.getMD5(newpasswd.getBytes());
				Memberuser memberuser =this.memberuserService.get(this.session_user_id);
				String psd=memberuser.getPasswd();
				if(newpasswd.equals(psd)){
					outstring="1";
				}
			}
			out.write(outstring);
		 }
	/**
	 * 运营商平台余额
	 * @return
	 * @throws Exception
	 */
	public String count()throws Exception{
	    //运营商的余额信息
		memberfund = this.memberfundService.get("0");
		return goUrl("count");
	}
	
	/**
	 * 整个平台的资金总额（即余额总数）包括：总资金、可用总资金、冻结总资金
	 */
	public String allfund() throws Exception{
		fundMap = this.memberfundService.getTotalFund();
		
		return goUrl("allfund");
		
	}
	
	
	/**
	 * 支付密码跳转页面
	 * @return
	 * @throws Exception
	 */
	public String payView()throws Exception{
		sysuser = this.sysuserService.get(this.session_user_id);
		return goUrl("updatepay");
	}
	
	/**
	 * 修改支付密码
	 * @return
	 * @throws Exception
	 */
	public String updatePay()throws Exception{
		if (ValidateUtil.isRequired(oldpasswd)) {
			this.addFieldError("oldpasswd", "登陆密码不能为空");
			return payView();
		}
		if (ValidateUtil.isRequired(this.getNewpasswd())) {
			this.addFieldError("newpasswd", "密码不能为空");
			return payView();
		}
		if (ValidateUtil.isRequired(this.getConfirmpasswd())) {
			this.addFieldError("confirmpasswd", "确认密码不能为空");
			return payView();
		}
		if (ValidateUtil.isAlphas(this.getNewpasswd())) {
			this.addFieldError("newpasswd", "密码只能由数字、字母或者下划线组成，请重新输入");
			return payView();
		}
		if (!this.getNewpasswd().equals(this.getConfirmpasswd())) {
			this.addFieldError("confirmpasswd", "确认密码与新密码不一致，请重新输入");
			return payView();
		}
		memberfund = this.memberfundService.get(this.session_cust_id);
		// 对密码加密
		if (newpasswd != null && !newpasswd.equals("")) {
			String oldpay=  Md5.getMD5(oldpasswd.getBytes());
			newpasswd = Md5.getMD5(newpasswd.getBytes());
			Sysuser sysuser =this.sysuserService.get(this.session_user_id);
			String psd=sysuser.getPasswd();
			if(!oldpay.equals(psd)){
				this.addFieldError("oldpasswd", "登陆密码错误！");
				return payView();
			}
			if(newpasswd.equals(psd)){
				this.addFieldError("newpasswd", "支付密码不能和登陆密码一样！");
				return payView();
			}
		}
		memberfund.setPay_passwd(newpasswd);
		this.memberfundService.update(memberfund);
		this.addActionMessage("修改支付密码成功！");
		oldpasswd=null;
		newpasswd=null;
		confirmpasswd=null;
		return payView();
	}
	
	/**
	 * @return the MemberfundList
	 */
	public List getMemberfundList() {
		return memberfundList;
	}

	/**
	 * @param memberfundList
	 *            the MemberfundList to set
	 */
	public void setMemberfundList(List memberfundList) {
		this.memberfundList = memberfundList;
	}

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	public String getMemberfund_name_s() {
		return memberfund_name_s;
	}

	public void setMemberfund_name_s(String memberfund_name_s) {
		this.memberfund_name_s = memberfund_name_s;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public Fundhistory getFundhistory() {
		return fundhistory;
	}

	public void setFundhistory(Fundhistory fundhistory) {
		this.fundhistory = fundhistory;
	}

	public String getRadiochecked() {
		return radiochecked;
	}

	public void setRadiochecked(String radiochecked) {
		this.radiochecked = radiochecked;
	}

	public String getFund_num() {
		return fund_num;
	}

	public void setFund_num(String fund_num) {
		this.fund_num = fund_num;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOldpasswd() {
		return oldpasswd;
	}

	public void setOldpasswd(String oldpasswd) {
		this.oldpasswd = oldpasswd;
	}

	public String getNewpasswd() {
		return newpasswd;
	}

	public void setNewpasswd(String newpasswd) {
		this.newpasswd = newpasswd;
	}

	public String getConfirmpasswd() {
		return confirmpasswd;
	}

	public void setConfirmpasswd(String confirmpasswd) {
		this.confirmpasswd = confirmpasswd;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	/**
	 * @return the memberfund
	 */
	public Memberfund getMemberfund() {
		return memberfund;
	}

	/**
	 * @param Memberfund
	 *            the memberfund to set
	 */
	public void setMemberfund(Memberfund memberfund) {
		this.memberfund = memberfund;
	}

	/**
	 * @Method Description : 当进入方法后初始化对象
	 * @author : LJQ
	 * @date : Nov 8, 2014 2:36:50 PM
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if (memberfund == null) {
			memberfund = new Memberfund();
		}
		if (!ValidateUtil.isDigital(this.session_cust_id)) {
			memberfund = this.memberfundService.get(this.session_cust_id);
		}
	}
	
}
