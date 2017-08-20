/*
  
 
 * Package:com.rbt.action
 * FileName: MemberAction.java 
 */
package com.rbt.webaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Message;
import com.rbt.service.IMessageService;


/**
 * @function 功能 会员action类
 * @author 创建人 CYC
 * @date 创建日期 Wed sep 16 08:48:07 CST 2014
 */
@Controller
public class WebmessageAction extends WebbaseAction {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -1547317091988473836L;
	/*******************实体层********************/
	public Message webmessage;
	/*******************业务层接口****************/
	@Autowired
	public IMessageService webmessageService;
	
	/*********************集合********************/
	 public List webmessageList;
	
	/*********************字段********************/
   
    public String message;
	
    public String title;//留言主题
    public String content;//留言内容
    public String name1;//联系人
    public String phone;//联系电话
    public String email;//电子邮件
    public String qq;//QQ
    public String msn;//MSN
    public String commentrand;//验证码
    public String skype;//skype
	private String sysrand;//存储验证码
	
	
	//绑定关于我们
	public String execute() throws Exception {
		
		Map pageMap=new HashMap();
		//根据页面提交的条件找出信息总数
		int count=this.webmessageService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		webmessageList=this.webmessageService.getList(pageMap);
		return goUrl("webmessage");
	}

	//插入留言
	public String insertmessage() throws Exception {
		execute();
		// 获取系统生成的验证码
		if (getSession().getAttribute("sysrand") != null) {
			sysrand = getSession().getAttribute("sysrand").toString();
		}
		if(ValidateUtil.isRequired(this.title)){
			this.addFieldError("title", "对不起，留言标题不能为空或者为空格");
			return goUrl("webmessage");
		}
		if(ValidateUtil.isRequired(this.content)){
			this.addFieldError("content", "对不起，留言内容不能为空或者为空格");
			return goUrl("webmessage");
		}
		if(ValidateUtil.isRequired(this.name1)){
			this.addFieldError("name", "对不起，联系人不能为空或者为空格");
			return goUrl("webmessage");
		}
		if(ValidateUtil.isRequired(this.phone)){
			this.addFieldError("phone", "对不起，联系方式不能为空或者为空格");
			return goUrl("webmessage");
		}else if(ValidateUtil.isMobile(this.phone)){
			this.addFieldError("phone", "对不起，手机号码格式不正确");
			return goUrl("webmessage");
		}
		if(ValidateUtil.isRequired(this.email)){
			this.addFieldError("email", "对不起，邮件不能为空或者为空格");
			return goUrl("webmessage");
		}else if(ValidateUtil.isEmail(this.email)){
			this.addFieldError("email", "对不起，您输入的邮件格式不正确");
			return goUrl("webmessage");
		}
		if(!sysrand.equals(commentrand)){
			this.addFieldError("commentrand", "对不起，您输入的验证码有错");
			return goUrl("webmessage");
		}
		webmessage=new Message();
		webmessage.setTitle(title);
		content=content.replace("<", "").replace(">", "");
		webmessage.setContent(content);
		webmessage.setName(name1);
		webmessage.setPhone(phone);
		webmessage.setEmail(email);
		webmessage.setQq(qq);
		webmessage.setMsn(msn);
		webmessage.setSkype(skype);
        this.webmessageService.insert(webmessage);
        webmessage = null;
        execute();
        return goUrl("webmessage");
	}
	//验证码
	@Action(value = "insertfrom")
	public void valfrom() throws Exception{
		PrintWriter out = getResponse().getWriter();
		HttpServletRequest request = getRequest();
		//获取url参数
		if (getSession().getAttribute("sysrand") != null) {
			sysrand = getSession().getAttribute("sysrand").toString();
		}
		String c_code = request.getParameter("c_code");
		if("".equals(c_code)){
			out.write("2");
		}else if(sysrand!=null&&sysrand.equals(c_code)){
			out.write("1");
		}else{
			out.write("0");
		}
	}
	
	public void insertMessage() throws IOException{
		//Ajax插入留言信息
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		PrintWriter out = response.getWriter();
		//验证码
		//获取url参数
		if (getSession().getAttribute("sysrand") != null) {
			sysrand = getSession().getAttribute("sysrand").toString();
		}
		String c_code = request.getParameter("rands");
		if("".equals(c_code)){
			out.write("2");
		}else if(sysrand!=null&&!sysrand.equals(c_code)){
			out.write("1");
		}else{
			String mess = URLDecoder.decode(request.getParameter("mess"), "UTF-8");
			if(mess!=null){
				String str[]=mess.split(",");
				webmessage=new Message();
				webmessage.setTitle(str[0].toString());
				webmessage.setContent(str[1].toString());
				webmessage.setName(str[2].toString());
				webmessage.setPhone(str[3].toString());
				webmessage.setEmail(str[4].toString());
				webmessageService.insert(webmessage);
				out.write("0");
			}
		}
		
	}

	public Message getWebmessage() {
		return webmessage;
	}

	public void setWebmessage(Message webmessage) {
		this.webmessage = webmessage;
	}

	public List getWebmessageList() {
		return webmessageList;
	}

	public void setWebmessageList(List webmessageList) {
		this.webmessageList = webmessageList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getSysrand() {
		return sysrand;
	}

	public void setSysrand(String sysrand) {
		this.sysrand = sysrand;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getCommentrand() {
		return commentrand;
	}

	public void setCommentrand(String commentrand) {
		this.commentrand = commentrand;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
