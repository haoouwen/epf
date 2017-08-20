/*
 
 * Package:com.rbt.action
 * FileName: PaymentAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Payment;
import com.rbt.service.IPaymentService;

/**
 * @function 功能 记录平台支付方式信息action类
 * @author 创建人 CYC
 * @date 创建日期 Mon Oct 24 10:57:44 CST 2014
 */
@Controller
public class PaymentAction extends AdminBaseAction implements Preparable {
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	public Payment payment;
	
	/*******************业务层接口****************/
	@Autowired
	public IPaymentService paymentService;
	
	/*********************集合********************/
	public List paymentList;//平台支付方式信息
	
	/*********************字段********************/
	public String pay_code_s;//对应的编码调用相应的支付接口
	public String pay_name_s;//支付方式名称
	public String pay_account_s;//支付账号
	public String enabled_s;//是否可用
	public String passwd;//新密钥
	public String oldpasswd;//旧密钥
    public String newAppID;
    public String newAppSecret;
	
	
	/**
	 * 方法描述：返回新增记录平台支付方式信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录平台支付方式信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		
		//验证支付编码是否存在
		String paycode = this.payment.getPay_code();
		Map pageMap = new HashMap();
		pageMap.put("pay_code", paycode);

		// 通过用户名找出用户信息
		List userList = this.paymentService.getList(pageMap);
		if (userList != null && userList.size() > 0) {
			this.addFieldError("payment.pay_code", "支付接口编码已存在,请重新输入");
		}
		//字段验证
		super.commonValidateField(payment);
		if(super.ifvalidatepass){
			return add();
		}
		this.paymentService.insert(payment);
		this.addActionMessage("新增平台支付方式成功");
		this.payment = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录平台支付方式信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//获取新密钥
		if(passwd!=null && !"".equals(passwd)){
			this.payment.setPasswd(passwd);
		}
		else{
			this.addFieldError("passwd", "请输入新密钥");
		}
		if(newAppID ==null && "".equals(newAppID)){
			this.addFieldError("newAppID", "请输入新的应用ID");
			return view();
		}else{
			payment.setAppID(newAppID);
		}
		
		if(newAppSecret == null && "".equals(newAppSecret)){
			this.addFieldError("newAppSecret", "请输入新的应用密钥");
			return view();
		}else{
			payment.setAppSecret(newAppSecret);
		}
		//字段验证
		super.commonValidateField(payment);
		if(super.ifvalidatepass){
			return view();
		}
		this.paymentService.update(payment);
		this.addActionMessage("修改平台支付方式成功");
		return list();
	}
	/**
	 * 方法描述：删除记录平台支付方式信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.paymentService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除平台支付方式成功");
		}else{
			this.addActionMessage("删除平台支付方式失败");
		}
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		if(pay_code_s!=null && !pay_code_s.equals("")) pageMap.put("pay_code", pay_code_s);
		if(pay_name_s!=null && !pay_name_s.equals("")) pageMap.put("pay_name", pay_name_s);
		if(pay_account_s!=null && !pay_account_s.equals("")) pageMap.put("pay_account", pay_account_s);
		if(enabled_s!=null && !enabled_s.equals("")) pageMap.put("enabled", enabled_s);
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.paymentService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		paymentList = this.paymentService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录平台支付方式信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		return goUrl(VIEW);
	}
	
	/**
	 * @author : LJQ
	 * @date : May 3, 2014 3:48:23 PM
	 * @Method Description :批量启用
	 */
	public String updateOn() throws Exception {
		updateenabled();
		return list();
	}
	
	/**
	 * @author : LJQ
	 * @date : May 3, 2014 3:48:23 PM
	 * @Method Description :批量禁用
	 */
	public String updateDown() throws Exception {
		updateenabled();
		return list();
	}
	
	/**
	 * @author : LJQ
	 * @date : May 3, 2014 3:48:44 PM
	 * @Method Description :是否启用支付方式公共方法
	 */
	private void updateenabled(){
		boolean flag = this.paymentService.updateBatchState(chb_id, state_val, "pay_id", "enabled");
		if(flag){
			if (state_val.equals("0")) {
				this.addActionMessage("启用成功");
			} else if (state_val.equals("1")) {
				this.addActionMessage("禁用成功");
			}
		}else{
			this.addActionMessage("操作支付方式失败");
		}
	}
	
	
	/**
	 * @return the PaymentList
	 */
	public List getPaymentList() {
		return paymentList;
	}
	/**
	 * @param paymentList
	 *  the PaymentList to set
	 */
	public void setPaymentList(List paymentList) {
		this.paymentList = paymentList;
	}
	public String getPay_code_s() {
		return pay_code_s;
	}
	public void setPay_code_s(String pay_code_s) {
		this.pay_code_s = pay_code_s;
	}
	public String getPay_name_s() {
		return pay_name_s;
	}
	public void setPay_name_s(String pay_name_s) {
		this.pay_name_s = pay_name_s;
	}
	public String getPay_account_s() {
		return pay_account_s;
	}
	public void setPay_account_s(String pay_account_s) {
		this.pay_account_s = pay_account_s;
	}
	public String getEnabled_s() {
		return enabled_s;
	}
	public void setEnabled_s(String enabled_s) {
		this.enabled_s = enabled_s;
	}
	/**
	 * @return the payment
	 */
	public Payment getPayment() {
		return payment;
	}
	/**
	 * @param Payment
	 *            the payment to set
	 */
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public void prepare() throws Exception { super.saveRequestParameter();
	if(payment == null){
		payment = new Payment();
	}
	String id = payment.getPay_id();
	if(!ValidateUtil.isDigital(id)){
		payment = this.paymentService.get(id);
	}
}
}

