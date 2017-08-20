/*
 
 * Package:com.rbt.action
 * FileName: InterhistoryAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Fundhistory;
import com.rbt.model.Interhistory;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IInterhistoryService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberinterService;
/**
 * @function 功能 记录会员积分异动历史action类
 * @author 创建人 CYC
 * @date 创建日期 Thu Jul 14 15:01:09 CST 2014
 */
@Controller
public class InterhistoryAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	public Interhistory interhistory;
	public Memberfund memberfund;
	public Fundhistory fundhistory;
	public Memberinter memberinter;

	/*******************业务层接口****************/
	@Autowired
	public IInterhistoryService interhistoryService;
	@Autowired
	public IMemberinterService memberinterService;
	@Autowired
	public IMemberfundService memberfundService;
	@Autowired
	public IFundhistoryService fundhistoryService;

	/*********************集合******************/
	public List interhistoryList;//记录会员积分异动历史信息集合
    public List memberinterList;//会员积分集合
	/*********************字段******************/
	public String cust_name_s;//会员名称
	public String user_name_s;//用户名称
	public String cust_id_s;//会员ID
	public String in_date_s;//发布时间
	public String enddateString;//结束时间
	public String rech_fund;//兑换金额
	public String use_fund;//可用金额
	public String integer_num;//剩余的积分数
	public String last_integer_num;//
	public String rech_value;//积分兑换规矩
	public String gold_value;//
	public String password;//支付密码
	 public String enter;//页面跳转标识符

	
	//商城获取列表
	public String bmalllist() throws Exception{
		return list();
	}
	
	/**
	 * 方法描述：返回新增记录会员积分异动历史页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录会员积分异动历史
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.interhistoryService.insert(interhistory);
		this.addActionMessage("新增积分异动历史记录成功");
		this.interhistory = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录会员积分异动历史信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(ValidateUtil.isDigital(rech_fund)){
			this.addFieldError("rech_fund", "请输入整数");
			return view();
		}
		//获取用户余额对象
		memberfund=this.memberfundService.get(this.session_cust_id);
		//获取用户积分对象
		memberinter=this.memberinterService.get(this.session_cust_id);
		if(Double.parseDouble(rech_fund)>Double.parseDouble(memberinter.getFund_num())){
			this.addFieldError("rech_fund", "您输入兑换的积分超过你拥有的积分数");
			return view();
		}
		if("0".equals(rech_fund)){
			this.addFieldError("rech_fund", "您输入0以上的数");
			return view();
		}
		if(Double.parseDouble(rech_fund)%Double.parseDouble(gold_value)!=0){
			this.addFieldError("rech_fund", "您输入的兑换积分数必须是"+gold_value+"倍数");
			return view();
		}
		
		if(ValidateUtil.isRequired(password)){
			this.addFieldError("password", "支付密码不能为空");
			return view();
		}
		//积分兑换规则
		int rechange_value=Integer.parseInt(SysconfigFuc.getSysValue("cfg_sc_exchscale"));
		//查询用户的可用积分
		String use_num="";
		
		//判断输入的密码是否正确
		String paypasswd=Md5.getMD5(password.getBytes());
		if(memberfund!=null){
			if(memberfund.getPay_passwd()!=null&&!memberfund.getPay_passwd().equals("")){
				String passwd=memberfund.getPay_passwd();
				use_num=memberinter.getFund_num();
				if(Double.parseDouble(use_num)<Double.parseDouble(rech_fund)){
					this.addActionMessage("您的积分数不足");
					return view();
				}else if(!passwd.equals(paypasswd)){
					this.addActionMessage("您的支付密码输入有误");
					return view();
				}else{
					//积分兑换余额
					interhistoryService.optioninter(memberfund,use_num,this.session_cust_id,this.session_user_id,memberinter,rech_fund,Integer.parseInt(gold_value));
					this.addActionMessage("成功兑换 "+Integer.parseInt(rech_fund)/Integer.parseInt(gold_value)+"余额");
					
				}
			}
			else{
				this.addActionMessage("您还未设置支付密码！");
			}
			
		}
		
		//是否兑换金额大于可以用金额，如果是提示可用金额不足，如何否修改相关表
		
		return view();
	}
	/**
	 * 方法描述：删除记录会员积分异动历史信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.interhistoryService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除会员积分异动历史信息成功");
		}else{
			this.addActionMessage("删除会员积分异动历史信息失败");
		}

		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		
		//页面搜索提交的参数
		Map pageMap = new HashMap();
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		if(cust_name_s!=null && !cust_name_s.equals("")) pageMap.put("cust_name", cust_name_s);
		if(user_name_s!=null && !user_name_s.equals("")) pageMap.put("user_name", user_name_s);
		if(integer_num!=null && !integer_num.equals("")) pageMap.put("integer_num",integer_num);
		if(last_integer_num!=null&& !last_integer_num.equals(""))pageMap.put("last_integer_num",last_integer_num);
		if(cust_id_s!=null && !cust_id_s.equals("")) pageMap.put("cust_id", cust_id_s);
		if(in_date_s!=null && !in_date_s.equals("")) pageMap.put("in_date", in_date_s);
		if(enddateString!=null && !enddateString.equals("")) pageMap.put("enddate", enddateString);
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.interhistoryService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		interhistoryList = this.interhistoryService.getList(pageMap);
		memberinter=this.memberinterService.get(this.session_cust_id);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录会员积分异动历史信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {

		//获取用户余额对象
		memberfund=this.memberfundService.get(this.session_cust_id);
		//获取用户积分对象
		memberinter=this.memberinterService.get(this.session_cust_id);
		
		if(memberfund!=null){
			use_fund=String.valueOf(memberfund.getUse_num());
		}
		if(memberinter!=null){
			integer_num=memberinter.getFund_num();
		}
		return goUrl(VIEW);
	}
	
	/**	
	 * @author : WXP
	 * @param :
	 * @date May 6, 2014 4:14:30 PM
	 * @Method Description :获取每日积分
	 */
	public void getIntegral() throws IOException{
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		//积分相关系统参数
		String daily_integral = "",continue_day = "",max_integral = "";
		daily_integral = SysconfigFuc.getSysValue("cfg_daily_integral");
		continue_day = SysconfigFuc.getSysValue("cfg_continue_day");
		max_integral = SysconfigFuc.getSysValue("cfg_max_integral");
		//输出内容
		String outStr = this.interhistoryService.getIntegral(this.session_cust_id, continue_day, max_integral, daily_integral);
		PrintWriter out = response.getWriter();
		System.out.println("-----------------------------------"+outStr);
		out.print(outStr);
	}
	/**
	 * @return the InterhistoryList
	 */
	public List getInterhistoryList() {
		return interhistoryList;
	}
	/**
	 * @param interhistoryList
	 *  the InterhistoryList to set
	 */
	public void setInterhistoryList(List interhistoryList) {
		this.interhistoryList = interhistoryList;
	}
	public String getCust_name_s() {
		return cust_name_s;
	}
	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}
	public String getCust_id_s() {
		return cust_id_s;
	}
	public void setCust_id_s(String cust_id_s) {
		this.cust_id_s = cust_id_s;
	}
	public String getIn_date_s() {
		return in_date_s;
	}
	public void setIn_date_s(String in_date_s) {
		this.in_date_s = in_date_s;
	}
	public String getEnddateString() {
		return enddateString;
	}
	public void setEnddateString(String enddateString) {
		this.enddateString = enddateString;
	}
	
	/**
	 * @return the interhistory
	 */
	public Interhistory getInterhistory() {
		return interhistory;
	}
	/**
	 * @param Interhistory
	 *            the interhistory to set
	 */
	public void setInterhistory(Interhistory interhistory) {
		this.interhistory = interhistory;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(interhistory == null){
			interhistory = new Interhistory();
		}
		String id = interhistory.getTrade_id();
		if(!ValidateUtil.isDigital(id)){
			interhistory = this.interhistoryService.get(id);
		}
		System.out.println(interhistory);
		//积分兑换规则
		gold_value=SysconfigFuc.getSysValue("cfg_sc_exchscale");
	}

	public String getRech_fund() {
		return rech_fund;
	}

	public void setRech_fund(String rech_fund) {
		this.rech_fund = rech_fund;
	}

	public String getUse_fund() {
		return use_fund;
	}

	public void setUse_fund(String use_fund) {
		this.use_fund = use_fund;
	}

	public String getInteger_num() {
		return integer_num;
	}

	public void setInteger_num(String integer_num) {
		this.integer_num = integer_num;
	}

	public String getRech_value() {
		return rech_value;
	}

	public void setRech_value(String rech_value) {
		this.rech_value = rech_value;
	}


	public String getUser_name_s() {
		return user_name_s;
	}


	public void setUser_name_s(String user_name_s) {
		this.user_name_s = user_name_s;
	}

	public List getMemberinterList() {
		return memberinterList;
	}

	public void setMemberinterList(List memberinterList) {
		this.memberinterList = memberinterList;
	}

	public Memberinter getMemberinter() {
		return memberinter;
	}

	public void setMemberinter(Memberinter memberinter) {
		this.memberinter = memberinter;
	}
	

}

