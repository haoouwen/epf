/*
 
 * Package:com.rbt.action
 * FileName: EmailhistoryAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.SysconfigFuc;
import com.rbt.message.MailInter;
import com.rbt.model.Emailhistory;
import com.rbt.model.Sysconfig;
import com.rbt.service.IEmailhistoryService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录邮件发送历史记录action类
 * @author 创建人 HZX
 * @date 创建日期 Wed Jan 09 12:52:35 CST 2014
 */
@Controller
public class EmailhistoryAction extends AdminBaseAction implements Preparable{

	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Emailhistory emailhistory;
	private Sysconfig sysconfig;
	public SysconfigFuc sysconfigFuc;

	/*******************业务层接口****************/
	@Autowired
	private IEmailhistoryService emailhistoryService;

	/*********************集合******************/
	public List emailhistoryList;//记录邮件发送历史记录信息集合
	public List sysconfigList;//系统设置信息集合


	/*********************字段******************/
	private String get_email_s;//电子邮箱
	private String send_name_s;//发送人
	private String user_name_s;//收件人
	private String var_value;//内容


	
	/**
	 * 方法描述：返回新增记录邮件发送历史记录页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		var_value=sysconfigFuc.getSysValue("cfg_smtp_usermail");
		return goUrl(ADD);
	}

	//获取系统用户邮件
	
	
	/**
	 * 方法描述：新增记录邮件发送历史记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.emailhistory.setUser_id(this.session_user_name);
		var_value=sysconfigFuc.getSysValue("cfg_smtp_usermail");
		super.commonValidateField(emailhistory);
		if(super.ifvalidatepass){
			return add();
		}
	    this.emailhistory.setSend_email(var_value);
		this.emailhistoryService.insert(emailhistory);
		this.addActionMessage("新增记录邮件发送历史记录成功！");
		this.emailhistory = null;
		return list();
	}
	
    /**
	 * @author：XBY
	 * @date：Nov 14, 2014 10:24:43 AM
	 * @Method Description：发送邮件
	 */
	public String sendEmail() throws Exception {
		super.commonValidateField(emailhistory);
		if(super.ifvalidatepass){
			return add();
		}
		String email=emailhistory.getGet_email();
		String emails[]=email.split(",");
		for(int i=0;i<emails.length;i++){
		if(ValidateUtil.isRequired(emails[i])|| ValidateUtil.isEmail(emails[i].trim())){
			this.addFieldError("emailhistory.get_email","请输入正确邮箱！");
			return add();
		}
		}
		String title=emailhistory.getTitle();
		String content=emailhistory.getContent();
		new MailInter().sendBatchMail(title,content, email);
		this.emailhistory.setUser_id(this.session_user_name);
		var_value=sysconfigFuc.getSysValue("cfg_smtp_usermail");
	    this.emailhistory.setSend_email(var_value);
		this.emailhistoryService.insert(emailhistory);
		this.addActionMessage("发送邮件成功！");
		this.emailhistory = null;
		return list();
	}
	
	/**
	 * 方法描述：修改记录邮件发送历史记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		super.commonValidateField(emailhistory);
		if(super.ifvalidatepass){
			return view();
		}
		this.emailhistoryService.update(emailhistory);
		this.addActionMessage("修改记录邮件发送历史记录成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录邮件发送历史记录信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.emailhistoryService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录邮件发送历史记录成功");
		}else{
			this.addActionMessage("删除记录邮件发送历史记录失败");
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

		if (get_email_s != null && !get_email_s.equals("")) {
			pageMap.put("get_email", get_email_s);
		}
		if (send_name_s != null && !send_name_s.equals("")) {
			pageMap.put("send_name", send_name_s);
		}
		if (user_name_s != null && !user_name_s.equals("")) {
			pageMap.put("user_name", user_name_s);
		}
		//根据页面提交的条件找出信息总数
		int count=this.emailhistoryService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		emailhistoryList = this.emailhistoryService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录邮件发送历史记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		var_value=sysconfigFuc.getSysValue("cfg_smtp_usermail");
		String id = this.emailhistory.getTrade_id();
		if(id==null || id.equals("")){
			emailhistory = new Emailhistory();
		}else{
			emailhistory = this.emailhistoryService.get(id);
		}
		return goUrl(VIEW);
	}
	
	/**
	 * @return the emailhistory
	 */
	public Emailhistory getEmailhistory() {
		return emailhistory;
	}
	/**
	 * @param Emailhistory
	 *            the emailhistory to set
	 */
	public void setEmailhistory(Emailhistory emailhistory) {
		this.emailhistory = emailhistory;
	}
	/**
	 * @return the EmailhistoryList
	 */
	public List getEmailhistoryList() {
		return emailhistoryList;
	}
	/**
	 * @param emailhistoryList
	 *  the EmailhistoryList to set
	 */
	public void setEmailhistoryList(List emailhistoryList) {
		this.emailhistoryList = emailhistoryList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(emailhistory == null){
			emailhistory = new Emailhistory();
		}
		String id = this.emailhistory.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			emailhistory = this.emailhistoryService.get(id);
		}
	}
	public String getGet_email_s() {
		return get_email_s;
	}
	public void setGet_email_s(String get_email_s) {
		this.get_email_s = get_email_s;
	}
	public String getSend_name_s() {
		return send_name_s;
	}
	public void setSend_name_s(String send_name_s) {
		this.send_name_s = send_name_s;
	}
	public String getUser_name_s() {
		return user_name_s;
	}
	public void setUser_name_s(String user_name_s) {
		this.user_name_s = user_name_s;
	}
	public Sysconfig getSysconfig() {
		return sysconfig;
	}
	public void setSysconfig(Sysconfig sysconfig) {
		this.sysconfig = sysconfig;
	}
	public List getSysconfigList() {
		return sysconfigList;
	}
	public void setSysconfigList(List sysconfigList) {
		this.sysconfigList = sysconfigList;
	}
	public String getVar_value() {
		return var_value;
	}
	public void setVar_value(String var_value) {
		this.var_value = var_value;
	}
	public SysconfigFuc getSysconfigFuc() {
		return sysconfigFuc;
	}
	public void setSysconfigFuc(SysconfigFuc sysconfigFuc) {
		this.sysconfigFuc = sysconfigFuc;
	}

}

