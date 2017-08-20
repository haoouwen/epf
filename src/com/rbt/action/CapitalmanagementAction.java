/*
 
 * Package:com.rbt.action
 * FileName: CapitalmanagementAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.common.Md5;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Capitalmanagement;
import com.rbt.model.Fundhistory;
import com.rbt.model.Memberfund;
import com.rbt.service.ICapitalmanagementService;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IMemberfundService;
import com.opensymphony.xwork2.Preparable;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 运营商资金管理action类
 * @author 创建人 HZX
 * @date 创建日期 Fri Aug 23 14:17:23 CST 2014
 */
@Controller
public class CapitalmanagementAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Capitalmanagement capitalmanagement;
	public Memberfund memberfund;
	public Fundhistory fundhistory;

	/*******************业务层接口****************/
	@Autowired
	private ICapitalmanagementService capitalmanagementService;
	@Autowired
	private IMemberfundService memberfundService;
	@Autowired
	private IFundhistoryService fundhistoryService;

	/*********************集合******************/
	public List capitalmanagementList;//运营商资金管理信息集合
	public List memberfundList;//会员余额信息集合

	/*********************字段******************/
	public String special_id;//资金表里的运营商cust_id 默认0
	public String radiochecked;//选择收入支出字段
	public String fund_num;//余额字段
	public String reason;//事由字段
	public String remark;//备注字段 
	private String oldpasswd;//旧密码
	private String newpasswd;//新密码
	private String confirmpasswd;//确认密码
	public String message;//消息
	private String answer;//密保答案
	private String old_answer;//旧密保答案
	private String question;//密保问题
	private String passwordA;//通过人A密码
	private String passwordB;//通过人B密码
	private String passwordC;//通过人C密码
	public String passMessage="";//密码提示信息
	public String istoAdjus;//来自拦截器传参将去调整资金
	public boolean trueToAdjus=false;//判断是否非法操作
	public String ssuccessToAdjus;//成功调整资金

	/**
	 * 方法描述：返回新增运营商资金管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增运营商资金管理
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(capitalmanagement);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.capitalmanagementService.insert(capitalmanagement);
		this.addActionMessage("新增运营商资金管理成功！");
		this.capitalmanagement = null;
		return INPUT;
	}

	
	/**
	 * @author : HZX
	 * @date : Aug 23, 2014 5:36:45 PM
	 * @Method Description :修改通过人密码
	 */
	public String update() throws Exception {
		String id=capitalmanagement.getTrade_id();
		if(id==null||id.equals("")){
			this.addFieldError("oldpasswd", "旧密码不正确，请重新输入");
			return goUrl("update"); 
		}
		// 查找密码	
		Map pageMap = new HashMap();
		String opwd=this.getNewpasswd();
		oldpasswd = Md5.getMD5(this.getOldpasswd().getBytes());
		pageMap.put("password", oldpasswd);
			pageMap.put("trade_id", id);
		capitalmanagementList = this.capitalmanagementService.getList(pageMap);
		if (capitalmanagementList == null || capitalmanagementList.size() == 0) {
			this.addFieldError("oldpasswd", "旧密码不正确，请重新输入");
			return goUrl("update"); 
		}
		if (ValidateUtil.isRequired(this.getNewpasswd())) {
			this.addFieldError("newpasswd", "新密码不能为空");
			return goUrl("update"); 
		}
		if (ValidateUtil.isRequired(this.getConfirmpasswd())) {
			this.addFieldError("confirmpasswd", "确认新密码不能为空");
			return goUrl("update"); 
		}
		if (ValidateUtil.isAlphasLimtLength(this.getNewpasswd())) {
			this.addFieldError("newpasswd", "密码只能由数字、字母或者下划线组成6-20位，请重新输入");
			return goUrl("update"); 
		}
		if (!this.getNewpasswd().equals(this.getConfirmpasswd())) {
			this.addFieldError("confirmpasswd", "确认密码与新密码不一致，请重新输入");
			return goUrl("update"); 
		}
		capitalmanagement = this.capitalmanagementService.get(id);
		// 对密码加密
		if (this.getNewpasswd()!= null && !this.getNewpasswd().equals("")) {
			newpasswd = Md5.getMD5(this.getNewpasswd().getBytes());
		} else {
			newpasswd = null;
		}
		capitalmanagement.setPassword(newpasswd);
		this.capitalmanagementService.update(capitalmanagement);
			super.commonValidateField(capitalmanagement);
		if(super.ifvalidatepass){
			return goUrl("update"); 
		}
		this.capitalmanagementService.update(capitalmanagement);
		this.addActionMessage(capitalmanagement.getPass_men()+"修改密码成功！");
		return list();
	}
	/**
	 * @author : HZX
	 * @date : Aug 23, 2014 5:37:17 PM
	 * @Method Description :找回密码
	 */
	public String forgetUpdate() throws Exception {
		String id=capitalmanagement.getTrade_id();
		if(id==null||id.equals("")){
			this.addFieldError("answer", "非法进入找回密码！");
			return goUrl("forgetPass");
		}
		if(answer==null||answer.equals("")){
			this.addFieldError("answer", "请输入密保答案！");
			return goUrl("forgetPass");
		}else{
			// 验证密保
			Map pageMap = new HashMap();
			pageMap.put("answer", answer);
				pageMap.put("trade_id", id);
			capitalmanagementList = this.capitalmanagementService.getList(pageMap);
			if (capitalmanagementList == null || capitalmanagementList.size() == 0) {
				this.addFieldError("answer", "密保答案不正确，请重新输入");
				return goUrl("forgetPass");
			}else{
				this.capitalmanagementService.update(capitalmanagement);
				this.addActionMessage(capitalmanagement.getPass_men()+"恭喜你回答正确！");
				return goUrl("setNewPass");
			}
		}
	}
	/**
	 * @author : HZX
	 * @date : Aug 26, 2014 8:53:04 AM
	 * @Method Description :重设密码
	 */
	public String setNewPass() throws Exception {
		String id=capitalmanagement.getTrade_id();
		if(id==null||id.equals("")){
			this.addFieldError("oldpasswd", "非法进入重设密码！");
		}
		if (ValidateUtil.isRequired(this.getNewpasswd())) {
			this.addFieldError("newpasswd", "新密码不能为空");
		}
		if (ValidateUtil.isRequired(this.getConfirmpasswd())) {
			this.addFieldError("confirmpasswd", "确认新密码不能为空");
		}
		if (ValidateUtil.isAlphasLimtLength(this.getNewpasswd())) {
			this.addFieldError("newpasswd", "密码只能由数字、字母或者下划线组成6-20位，请重新输入");
		}
		if (!this.getNewpasswd().equals(this.getConfirmpasswd())) {
			this.addFieldError("confirmpasswd", "确认密码与新密码不一致，请重新输入");
		}
		if(super.ifvalidatepass){
			return goUrl("setNewPass");
		}
		capitalmanagement = this.capitalmanagementService.get(id);
		// 对密码加密
		if (this.getNewpasswd()!= null && !this.getNewpasswd().equals("")) {
			newpasswd = Md5.getMD5(this.getNewpasswd().getBytes());
		} else {
			newpasswd = null;
		}
		capitalmanagement.setPassword(newpasswd);
		this.capitalmanagementService.update(capitalmanagement);
		this.addActionMessage(capitalmanagement.getPass_men()+"重设密码成功！");
		return list();
	}
	/**
	 * @author : HZX
	 * @date : Aug 26, 2014 9:17:28 AM
	 * @Method Description :重设密保
	 */
	public String setNewSecretSecurity() throws Exception {
		String id=capitalmanagement.getTrade_id(),opwd=this.getNewpasswd();
		if(id==null||id.equals("")){
			this.addFieldError("oldpasswd", "非法进入重设密保！");
			return goUrl("setNewSecretSecurity");
		}
		if(ValidateUtil.isRequired(old_answer)){
			this.addFieldError("old_answer", "请输入密保答案！");
			return goUrl("setNewSecretSecurity");
		}
		// 验证密码	
		Map pageMap = new HashMap();
		oldpasswd = Md5.getMD5(this.getOldpasswd().getBytes());
		pageMap.put("password", oldpasswd);
			pageMap.put("trade_id", id);
		capitalmanagementList = this.capitalmanagementService.getList(pageMap);
		if (capitalmanagementList == null || capitalmanagementList.size() == 0) {
			this.addFieldError("oldpasswd", "密码不正确，请重新输入");
			return goUrl("setNewSecretSecurity");
		}
		// 验证密保
		Map aMap = new HashMap();
		aMap.put("answer", old_answer);
		aMap.put("trade_id", id);
		capitalmanagementList = this.capitalmanagementService.getList(aMap);
		if (capitalmanagementList == null || capitalmanagementList.size() == 0) {
			this.addFieldError("old_answer", "密保答案不正确，请重新输入");
			return goUrl("setNewSecretSecurity");
		}
		if (ValidateUtil.isRequired(question)) {
			this.addFieldError("question", "新密保问题不能为空");
			return goUrl("setNewSecretSecurity");
		}
		if (ValidateUtil.isRequired(answer)) {
			this.addFieldError("answer", "新密保答案不能为空");
			return goUrl("setNewSecretSecurity");
		}
		capitalmanagement.setQuestion(question);
		capitalmanagement.setAnswer(answer);
		this.capitalmanagementService.update(capitalmanagement);
		this.addActionMessage(capitalmanagement.getPass_men()+"修改密保成功！");
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
		istoAdjus=(String) getSession().getAttribute("istoAdjus");
		getSession().removeAttribute("istoAdjus");
		passMessage=(String)getSession().getAttribute("passMessage");
		getSession().removeAttribute("passMessage");
		//根据页面提交的条件找出信息总数
		int count=this.capitalmanagementService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		capitalmanagementList = this.capitalmanagementService.getList(pageMap);
		getSession().removeAttribute(Constants.PASSWORD_A);
		getSession().removeAttribute(Constants.PASSWORD_B);
		getSession().removeAttribute(Constants.PASSWORD_C);
		getSession().removeAttribute("trueToAdjus");
		return goUrl(INDEXLIST);
	}
	/**
	 * @author : HZX
	 * @date : Aug 23, 2014 5:12:30 PM
	 * @Method Description :跳转到修改通过密码
	 */
	public String view() throws Exception {
		commonView();
		return goUrl(VIEW);
	}
	/**
	 * @author : HZX
	 * @date : Aug 23, 2014 4:48:37 PM
	 * @Method Description :跳转到找回通过密码
	 */
	public String forgetPassView() throws Exception {
		commonView();
		return goUrl("forgetPass");
	}
	/**
	 * @author : HZX
	 * @date : Aug 23, 2014 5:09:44 PM
	 * @Method Description :跳转到重设密保
	 */
	public String setSecretSecurityView() throws Exception {
        commonView();
		return goUrl("setNewSecretSecurity");
	}
	/**
	 * @author：XBY
	 * @date：Feb 19, 2014 2:52:35 PM
	 * @Method Description：跳转公共方法
	 */
	public void commonView(){
		String id = this.capitalmanagement.getTrade_id();
		if(id==null || id.equals("")){
			capitalmanagement = new Capitalmanagement();
		}else{
			capitalmanagement = this.capitalmanagementService.get(id);
		}
	}
	
	/**
	 * @author : HZX
	 * @date : Aug 26, 2014 2:21:32 PM
	 * @Method Description :调整资金前检验是否有权限（即3层密码是否正确）
	 */
	public void  IsPass() throws Exception {
		int countA,countB,countC;
		// 查找密码	
		Map pageMapA = new HashMap();
		Map pageMapB = new HashMap();
		Map pageMapC = new HashMap();
		passwordA = Md5.getMD5(this.getPasswordA().getBytes());
		passwordB = Md5.getMD5(this.getPasswordB().getBytes());
		passwordC = Md5.getMD5(this.getPasswordC().getBytes());
		pageMapA.put("passwordA", passwordA);
		pageMapB.put("passwordB", passwordB);
		pageMapC.put("passwordC", passwordC);
		countA=this.capitalmanagementService.getCount(pageMapA);
		countB=this.capitalmanagementService.getCount(pageMapB);
		countC=this.capitalmanagementService.getCount(pageMapC);
		if(countA+countB+countC==3){
			getSession().setAttribute(Constants.PASSWORD_A, passwordA);
			getSession().setAttribute(Constants.PASSWORD_B, passwordB);
			getSession().setAttribute(Constants.PASSWORD_C, passwordC);
			getSession().setMaxInactiveInterval(90);
		}else{
			if(countA==0){
				passMessage+= "通过人A密码不正确";
			}
			if(countB==0){
				passMessage+= " 通过人B密码不正确";
			}
			if(countC==0){
				passMessage+= " 通过人C密码不正确";
			}
			getSession().setAttribute("passMessage",passMessage);
		}
		ServletActionContext.getResponse();
		getResponse().sendRedirect("/adminAdjustedFundView.action");
	}
	/**
	 * @author : HZX
	 * @date : Oct 30, 2014 8:48:48 AM
	 * @Method Description :合法操作才能进入资金调整
	 */
	public String toAdjuste() throws Exception {
		if(getSession().getAttribute("trueToAdjus")!=null&&getSession().getAttribute("trueToAdjus").equals("0")){
			trueToAdjus=true;
			getSession().setAttribute("trueToAdjus", "0");
		}
		if(getSession().getAttribute("ssuccessToAdjus")!=null&&getSession().getAttribute("ssuccessToAdjus").equals("0")){
			ssuccessToAdjus=(String) getSession().getAttribute("ssuccessToAdjus");
			getSession().setAttribute("ssuccessToAdjus", "1");
		}
		
		if(getSession().getAttribute("trueToAdjus")!=null){
			int truevalue=Integer.parseInt(getSession().getAttribute("trueToAdjus").toString())+1;
			getSession().setAttribute("trueToAdjus",truevalue);
		}
		return goUrl("adjustedFunds");
	}
	
	/**
	 * @author : HZX
	 * @date : Aug 23, 2014 2:45:17 PM
	 * @Method Description :跳转到运营商资金调整
	 */
	public String fundView() throws Exception {
		// 当前登录的用户是管理员的时候，才执行审核动作
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
				memberfund = this.memberfundService.get("0");
				return "adjustedFunds";
		}		
		return "adminAdjusLogin";
	}
	/**
	 * @author : HZX
	 * @date : Aug 23, 2014 3:10:32 PM
	 * @Method Description :调整运营商资金
	 */
	public String fundUpdate() throws Exception {

		if (ValidateUtil.isRequired(fund_num) || ValidateUtil.isRmb(fund_num)) {
			this.addFieldError("fund_num", "不能为空且只能输入数字");
			return "adjustedFunds";
		}
		if (ValidateUtil.isRequired(reason)) {
			this.addFieldError("reason", "事由不能为空");
			return "adjustedFunds";
		}
		//调整运营商资金
		String actionMessage=this.capitalmanagementService.fundUpdate(radiochecked, fund_num, session_user_id, reason, remark);
		this.addActionMessage(actionMessage);
		getSession().removeAttribute(Constants.PASSWORD_A);
		getSession().removeAttribute(Constants.PASSWORD_B);
		getSession().removeAttribute(Constants.PASSWORD_C);
		getSession().removeAttribute("trueToAdjus");
		getSession().setAttribute("ssuccessToAdjus","0");
		return "adjustedFunds";
	}
	/**
	 * @return the CapitalmanagementList
	 */
	public List getCapitalmanagementList() {
		return capitalmanagementList;
	}
	/**
	 * @param capitalmanagementList
	 *  the CapitalmanagementList to set
	 */
	public void setCapitalmanagementList(List capitalmanagementList) {
		this.capitalmanagementList = capitalmanagementList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(capitalmanagement == null){
			capitalmanagement = new Capitalmanagement();
		}
		String id = this.capitalmanagement.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			capitalmanagement = this.capitalmanagementService.get(id);
		}
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
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * @return the capitalmanagement
	 */
	public Capitalmanagement getCapitalmanagement() {
		return capitalmanagement;
	}
	/**
	 * @param Capitalmanagement
	 *            the capitalmanagement to set
	 */
	public void setCapitalmanagement(Capitalmanagement capitalmanagement) {
		this.capitalmanagement = capitalmanagement;
	}

	public String getPasswordA() {
		return passwordA;
	}

	public void setPasswordA(String passwordA) {
		this.passwordA = passwordA;
	}

	public String getPasswordB() {
		return passwordB;
	}

	public void setPasswordB(String passwordB) {
		this.passwordB = passwordB;
	}

	public String getPasswordC() {
		return passwordC;
	}

	public void setPasswordC(String passwordC) {
		this.passwordC = passwordC;
	}
	public String getPassMessage() {
		return passMessage;
	}

	public void setPassMessage(String passMessage) {
		this.passMessage = passMessage;
	}

	public String getOld_answer() {
		return old_answer;
	}

	public void setOld_answer(String old_answer) {
		this.old_answer = old_answer;
	}
	
}


