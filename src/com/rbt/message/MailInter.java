package com.rbt.message;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CreateSpringContext;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Emailhistory;
import com.rbt.model.Goodsorder;
import com.rbt.model.Memberuser;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.IEmailhistoryService;
import com.rbt.service.IGoodsorderService;
import com.rbt.function.MemberuserFuc;

public class MailInter extends CreateSpringContext {

	
	
	private String fromMailAddr, smtp, user_name, passwd;
	private int port;

	/*
	 * 构造函数
	 */
	public MailInter() {
		fromMailAddr = SysconfigFuc.getSysValue("cfg_smtp_usermail");
		smtp = SysconfigFuc.getSysValue("cfg_smtp_server");
		user_name = SysconfigFuc.getSysValue("cfg_smtp_user");
		passwd = SysconfigFuc.getSysValue("cfg_smtp_password");
		port = Integer.parseInt(SysconfigFuc.getSysValue("cfg_smtp_port"));
	}
	
	//测试方法
	public static void main(String[] args) {
		//new MailInter().sendBatchMail("111", "111", "1990855089@qq.com");
		new MailInter().sendMail("测试邮件发送","测试邮件发送正文","1341603066@qq.com","1","21",new Goodsorder());
	}
	
	/*
	 * 功能：发送邮件 title：邮件标题 content：邮件正文 toMailAddr：接收邮件人地址 mailType：txt：文本邮件
	 * mailcoal：邮件模版编码
	 * html：网页格式邮件
	 */
	public void sendMail(String title, String content, String toMailAddr,
			String mailType,String mailcoal,Goodsorder order) {
		
		if(!ValidateUtil.isRequired(toMailAddr)) {
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		Session session = Session.getInstance(props);
		session.setDebug(true);
		Message msg = new MimeMessage(session);
		try {

			// 发送的邮箱地址
			msg.setFrom(new InternetAddress(fromMailAddr));
			msg.setSubject(title);
			// mailType txt或为空：文本邮件 html：网页格式邮件
			if (mailType.equals("html")) {
				msg.setContent(content, "text/html;charset=UTF-8;");
			} else {
				msg.setText(content);
			}
			Transport transport = session.getTransport();
			// 设置服务器以及账号和密码
			transport.connect(smtp, port, user_name, passwd);
			// 发送到的邮箱地址
			transport.sendMessage(msg, new Address[] { new InternetAddress(
					toMailAddr) });
			transport.close();
			//插入邮件发送历史
			if(order!=null){
				insertEmailHistory(title,content,toMailAddr, mailcoal,order);
			}else{
				insertEmailHistory(title,content,toMailAddr, mailcoal);
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		}
	}
	
	public void insertEmailHistory(String title,String content,String toMailAddr,String mailcoal){
		insertEmailHistory(title,content,toMailAddr,mailcoal,null);
	}
	/*
	 * 功能：发送邮件 title：邮件标题 content：邮件正文 toMailAddr：接收邮件人地址 mailcoal：邮件模版编码,sen_user_name:发送人邮件地址
	 * 插入邮件发送历史
	 * HXK
	 */
	public void insertEmailHistory(String title,String content,String toMailAddr,String mailcoal,Goodsorder order){
		IEmailhistoryService emailhistoryService = (IEmailhistoryService)getContext().getBean("emailhistoryService");
		Emailhistory emailhistory=new Emailhistory();
		String user_name_str="",user_id_str="";
		if(order!=null){
			user_id_str = order.getSell_cust_id();
			Memberuser memberuser = MemberuserFuc.getuserName(user_id_str);
			if(memberuser!=null)
			user_name_str = memberuser.getUser_name();	
		}else{
			HttpServletRequest req = ServletActionContext.getRequest();
			HttpSession session = req.getSession();
			//获取当前的发送人名称USER_NAME
			if (session.getAttribute(Constants.USER_NAME_NEW) != null) {
				user_name_str = session.getAttribute(Constants.USER_NAME_NEW).toString();
			}else if (session.getAttribute(Constants.USER_NAME) != null) {
				user_name_str = session.getAttribute(Constants.USER_NAME).toString();
			}
			//获取发送人的当前用户URSE_ID
			if (session.getAttribute(Constants.USER_ID_NEW) != null) {
				user_id_str = session.getAttribute(Constants.USER_ID_NEW).toString();
			}else if (session.getAttribute(Constants.USER_ID) != null) {
				user_id_str = session.getAttribute(Constants.USER_ID).toString();
			}
		}
		emailhistory.setContent(content);
		emailhistory.setGet_email(toMailAddr);
		emailhistory.setSend_email(this.user_name);
		emailhistory.setSend_name(user_name_str);
		emailhistory.setTemp_code(mailcoal);
		emailhistory.setTitle(title);
		emailhistory.setUser_id(user_id_str);
		//执行插入邮件发送历史
		emailhistoryService.insert(emailhistory);
		
	}
	// 发送HTML邮件
	public void sendMail(String title, String content, String toMailAddr,
			String mailcoal){
		sendMail(title,content,toMailAddr,"html",mailcoal,null);
	}


	/*	
	 *  批量发送邮件
	 * toMailAddr：接收邮件地址,以,号隔开
	 */
	public void sendBatchMail(String title, String content, String toMailAddr) {
		// 创建session
		Properties props = new Properties();
		props.put("mail.smtp.host", smtp);
		props.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(props,
			new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user_name, passwd);
				}
			}
		);
		// 创建Message
		Message msg = new MimeMessage(session);
		
		try {
			msg.setFrom(new InternetAddress(fromMailAddr));
			msg.setSubject(title);
			msg.setContent(content,"text/html;charset=UTF-8");
			Transport.send(msg,InternetAddress.parse(toMailAddr));
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println("邮件发送成功");
	}

	public String getFromMailAddr() {
		return fromMailAddr;
	}

	public void setFromMailAddr(String fromMailAddr) {
		this.fromMailAddr = fromMailAddr;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
