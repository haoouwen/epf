/*
  
 
 * Package:com.rbt.action
 * FileName: WebMemberAction.java 
 */
package com.rbt.webaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.rbt.common.Constants;
import com.rbt.common.Md5;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.message.MailInter;
import com.rbt.model.Advinfo;
import com.rbt.model.Comsumer;
import com.rbt.model.Expressfund;
import com.rbt.model.Member;
import com.rbt.model.Membercredit;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.model.Memberuser;
import com.rbt.model.Msgcheck;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.ICommparaService;
import com.rbt.service.IComsumerService;
import com.rbt.service.IEmailhistoryService;
import com.rbt.service.IEmailtemplateService;
import com.rbt.service.IExpressfundService;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMembercreditService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberinterService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IMsgcheckService;
import com.rbt.service.ISysmenuService;
import com.rbt.function.MessageAltFuc;
import com.rbt.function.PageTipFuc;
import com.rbt.function.SysconfigFuc;

/**
 * @function 功能 会员action类
 * @author 创建人 QJY
 * @date 创建日期 Wed Jul 13 08:48:07 CST 2014
 */
@Controller
public class WebmemberAction extends WebbaseAction {
	/*
	 * 序列化
	 */
	private static final long serialVersionUID = 7214704723619945814L;
	private static final String CFG_MB_NOTALLOW = "cfg_mb_notallow";//不允许注册的登陆名
	private static final String CFG_MB_ISAUDIT = "cfg_mb_isaudit";//注册是否需要人工审核
	private static final String CFG_WEBNAME = "cfg_webname";//网站名称
	private static final String CFG_BASEHOST = "cfg_basehost";
	private final static String DEFAULT_SYSCODE_VALUE = "com";//后台类型
	/*******************实体层********************/
	public Member member;
	public Memberuser memberuser;
	public Memberfund memberfund;
	public Memberinter memberinter;
	private Expressfund expressfund;
	
	/*******************业务层接口****************/
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IAdvinfoService advinfoService;
	@Autowired
	public ISysmenuService sysmenuService;
	@Autowired
	public IMemberfundService memberfundService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public IMemberuserService memberuserService;
	@Autowired
	public IEmailtemplateService emailtemplateService;
	@Autowired
	public IEmailhistoryService emailhistoryService;
	@Autowired
	public IFundhistoryService fundhistoryService;
	@Autowired
	public IMsgcheckService msgcheckService;
	@Autowired
	public IMemberinterService memberinterService;
	@Autowired
	private IMembercreditService membercreditService;
	@Autowired
	private IComsumerService comsumerService; 
	
	/*********************集合********************/
	public List memberList;//会员信息集合
	public List commparaList;//参数集合
	public List clientStrList;//经营模式信息
	public List cust_typeList;//企业类型集合
	public List memberinterList;//会员积分数
	public List memberfundList;//会员余额信息
	public List advposlist;//广告
	
	/*********************字段********************/
	public String user_name;//注册用户名
	public String url;
	public String img;
	public String u_email;// 邮箱
	public String passwd; //注册密码
	public String psw_strong;//密码强度
	public Advinfo advinfo;//广告
	public String username;//用户名
	public String email;//邮箱
	public String checkcode;//验证码
	public String password;
	public String rep_pass;
	public String serviceterms;
	public String deflag;// 标识
	public String commentrand;//验证码
	public String phone;// 电话
	public String card;
	public String realname;//真实姓名
	public String emailurl;//各大邮箱的登录地址
	public String sl;
	public String cat_attr;//分类的字符串
	public String area_attr;//地区的字符串
	public String hiddenvalue;//存入所属分类串的隐藏域
	public String hidden_up_level;//v定义隐藏的等级
	public String hidden_up_cate_id;//定义所属分类串的上一级ID
	public String hidden_area_value;//定义所属地区的隐藏域
	public String hidden_up_area_id;//定义所属地区的上一级ID
	public String select_cat_id;
	public boolean mb_isaudit=false;//会员注册是否需审核
	public String selway;//注册方式
	public String i_u;
	public String i_b;
	public String cc;
	public String c_t;
	public String p;
	public String rp;
	public List areaList;
	public String membernum;
	public String registertype;
	public String i_c;
	public String is_phone_email;
	public String commentrand_phone;
	/**
	 * 方法描述：检查用户名是否已经注册
	 * 
	 * @return
	 * @throws Exception
	 */
	public void checkUserName() throws Exception {
		PrintWriter out = getResponse().getWriter();
		getRequest().setCharacterEncoding("UTF-8");
		getResponse().setCharacterEncoding("UTF-8");
		if (user_name != null
				&& existsTitle(user_name, "", "memberuser", "user_name")) {
			out.write("1");
		} else {
			if(this.memberService.isAllow(user_name,CFG_MB_NOTALLOW)){
				this.addFieldError("user_name", "本网站不允许注册此类用户名");
				out.write("2");
			}
		}
	}
	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Sep 20, 2016 4:47:10 PM
	 * @Method Description :优惠券生成10位随机数
	 */
	public String str(){
		 Random rrr =new Random();
		 long num = Math.abs(rrr.nextLong() % 10000000000L);
	        String s = String.valueOf(num);
	        return s;
	        
	};
	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Sep 20, 2014 4:47:10 PM
	 * @Method Description :验证手机的注册的唯一性
	 */
	public void checkPhone() throws IOException {
		PrintWriter out = getResponse().getWriter();
		getRequest().setCharacterEncoding("UTF-8");
		getResponse().setCharacterEncoding("UTF-8");
		Map pMap = new HashMap();
		pMap.put("u-cellphone", phone);
		List list = this.memberuserService.getList(pMap);
		if (phone != null && list != null && list.size() > 0) {
			Map map = (HashMap)list.get(0);
			String user_id= map.get("user_id").toString();
			if(this.session_user_id.equals(user_id)){
				out.write("2");
			}else{
				out.write("1");
			}
		} else {
			out.write("2");
		}
	}

	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Oct 10, 2014 4:18:53 PM
	 * @Method Description :验证手机验证码的正确性
	 */
	@SuppressWarnings("unchecked")
	public void checkPhoneCode() throws IOException {
		// 定义request对象
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		String cp_phone = request.getParameter("phone");
		String cp_check = request.getParameter("cp_check");
		boolean ckfage=msgcheckService.checkMsgListToUse(cp_phone, cp_check);
		PrintWriter out = getResponse().getWriter();
		getResponse().setCharacterEncoding("UTF-8");
		getResponse().setContentType("text/html;charset=UTF-8;");
		if(ckfage==false){
			out.write("1");
		}else {
			out.write("3");
		}
	}

	/**
	 * @author : LJQ
	 * @throws UnsupportedEncodingException
	 * @date : Oct 10, 2014 4:20:57 PM
	 * @Method Description : 注册发送验证码的方法
	 */
	public void sendPhoneCode() throws Exception {
		// 定义request对象
		HttpServletRequest request = getRequest();
		PrintWriter out = getResponse().getWriter();
		request.setCharacterEncoding("UTF-8");
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String confirm_pwd = request.getParameter("confirm_pwd");
		String membernum = request.getParameter("membernum");
		String commentrand = request.getParameter("commentrand");
		String cphone = request.getParameter("phone");
		String cp_check = RandomStrUtil.getRand("6");
		
		// 发送短信的方法
		if(!ValidateUtil.isRequired(user_name) && !ValidateUtil.isRequired(password) && !ValidateUtil.isRequired(confirm_pwd) && !ValidateUtil.isRequired(membernum) && !ValidateUtil.isRequired(commentrand)){
			if(!ValidateUtil.isMobile(cphone)){
				
				String sysrand = "";
				// 获取系统生成的验证码
				if (getSession().getAttribute("sysrand") != null) {
					sysrand = getSession().getAttribute("sysrand").toString();
				}
				// 验证验证码是否正确
				if (sysrand.equals(commentrand)) {
					Msgcheck mc = new Msgcheck();
					mc.setCp_phone(cphone);
					mc.setCp_check(cp_check);
					if (request.getParameter("c_type") != null&& !request.getParameter("c_type").equals("")) {
						String cp_type = request.getParameter("c_type");
						mc.setCp_type(cp_type);
					}
					this.msgcheckService.insert(mc);
					// 通过手机发送验证码
					String tem_con = "亲爱的用户，您的手机验证码是${check_code}，此验证码30分钟内有效，请尽快完成验证。";
					tem_con = tem_con.replace("${check_code}", cp_check);
					SendMsg(cphone,"发送验证码", tem_con);
					out.write("1");
				}else{
					out.write("0");
				}
				
			}else{
				out.write("0");
			}
		}else{
			out.write("0");
		}
	}
	
	
	public void sendPhoneCodePasswd() throws Exception {
		HttpServletRequest request = getRequest();
		PrintWriter out = getResponse().getWriter();
		request.setCharacterEncoding("UTF-8");
		String cphone = request.getParameter("phone");
		String cp_check = RandomStrUtil.getRand("6");
		String commentrand = request.getParameter("commentrand");
		if(!ValidateUtil.isMobile(cphone)){
			String sysrand = "";
			// 获取系统生成的验证码
			if (getSession().getAttribute("sysrand") != null) {
				sysrand = getSession().getAttribute("sysrand").toString();
			}
			// 验证验证码是否正确
			if (sysrand.equals(commentrand)) {
				Msgcheck mc = new Msgcheck();
				mc.setCp_phone(cphone);
				mc.setCp_check(cp_check);
				if (request.getParameter("c_type") != null&& !request.getParameter("c_type").equals("")) {
					String cp_type = request.getParameter("c_type");
					mc.setCp_type(cp_type);
				}
				this.msgcheckService.insert(mc);
				// 通过手机发送验证码
				String tem_con = "亲爱的用户，您的手机验证码是${check_code}，此验证码30分钟内有效，请尽快完成验证。";
				tem_con = tem_con.replace("${check_code}", cp_check);
				SendMsg(cphone,"发送验证码", tem_con);
				out.write("1");
		}else{
			out.write("2");
		}
		}else{
			out.write("0");
		}
	}
	
	/**
	 * @author : LJQ
	 * @throws UnsupportedEncodingException
	 * @date : Oct 10, 2014 4:20:57 PM
	 * @Method Description : 找回密码发送验证码的方法
	 */
	public void sendPhoneCodePWD() throws Exception {
		// 定义request对象
		HttpServletRequest request = getRequest();
		PrintWriter out = getResponse().getWriter();
		request.setCharacterEncoding("UTF-8");
		String cphone = request.getParameter("phone");
		String cp_check = RandomStrUtil.getRand("6");
		// 发送短信的方法
			if(!ValidateUtil.isRequired(cphone)){
				String sysrand = "";
				// 获取系统生成的验证码
				if (getSession().getAttribute("sysrand") != null) {
					sysrand = getSession().getAttribute("sysrand").toString();
				}
				// 验证验证码是否正确
				if (sysrand!=null&&!"".equals(sysrand)) {
					Msgcheck mc = new Msgcheck();
					mc.setCp_phone(cphone);
					mc.setCp_check(cp_check);
					if (request.getParameter("c_type") != null&& !request.getParameter("c_type").equals("")) {
						String cp_type = request.getParameter("c_type");
						mc.setCp_type(cp_type);
					}
					this.msgcheckService.insert(mc);
					// 通过手机发送验证码
					String tem_con = "亲爱的用户，您的手机验证码是${check_code}，此验证码30分钟内有效，请尽快完成验证。";
					tem_con = tem_con.replace("${check_code}", cp_check);
					SendMsg(cphone,"发送验证码", tem_con);
					out.write("1");
				}else{
					out.write("0");
				}
				
			}else{
				out.write("0");
			}
	}
	

	/**
	 * 通过邮箱发送验证码
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public void sendEmailCode() throws Exception {
		// 定义request对象
		HttpServletRequest request = getRequest();
		PrintWriter out = getResponse().getWriter();
		request.setCharacterEncoding("UTF-8");
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String confirm_pwd = request.getParameter("confirm_pwd");
		String membernum = request.getParameter("membernum");
		String commentrand = request.getParameter("commentrand");
		String email = request.getParameter("email");
		String cp_check = RandomStrUtil.getRand("6");
		// 发送短信的方法
		if(!ValidateUtil.isRequired(user_name) && !ValidateUtil.isRequired(password) && !ValidateUtil.isRequired(confirm_pwd) && !ValidateUtil.isRequired(membernum) && !ValidateUtil.isRequired(commentrand)){
			if(!ValidateUtil.isRequired(email)){
				String sysrand = "";
				// 获取系统生成的验证码
				if (getSession().getAttribute("sysrand") != null) {
					sysrand = getSession().getAttribute("sysrand").toString();
				}
				// 验证验证码是否正确
				if (sysrand.equals(commentrand)) {
					Msgcheck mc = new Msgcheck();
					mc.setCp_phone(email);
					mc.setCp_check(cp_check);
					mc.setCp_type("4");
					this.msgcheckService.insert(mc);
					String title = "用户验证码";
					String content = "尊敬的会员您好,您的验证码是：" + cp_check+ ",请在30分钟内进行验证，否则验证码将失效！";
					new MailInter().sendBatchMail(title, content, email);
					out.write("1");
				}else{
					out.write("0");
				}
			}else{
				out.write("0");
			}
		}else{
			out.write("0");
		}
		
	}
	
	
	/**
	 * 通过邮箱发送验证码
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public void sendEmailCodePasswd() throws Exception {
		// 定义request对象
		HttpServletRequest request = getRequest();
		PrintWriter out = getResponse().getWriter();
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String cp_check = RandomStrUtil.getRand("6");
		// 发送短信的方法
			if(!ValidateUtil.isRequired(email)){
					Msgcheck mc = new Msgcheck();
					mc.setCp_phone(email);
					mc.setCp_check(cp_check);
					mc.setCp_type("4");
					this.msgcheckService.insert(mc);
					String title = "用户验证码";
					String content = "尊敬的会员您好,您的验证码是：" + cp_check+ ",请在30分钟内进行验证，否则验证码将失效！";
					new MailInter().sendBatchMail(title, content, email);
					out.write("1");
		}else{
			out.write("0");
		}
	}
	
	/**
	 * 通过邮箱发送验证码
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public void sendEmailCodePWD() throws Exception {
		// 定义request对象
		HttpServletRequest request = getRequest();
		PrintWriter out = getResponse().getWriter();
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String cp_check = RandomStrUtil.getRand("6");
		// 发送短信的方法
			if(!ValidateUtil.isRequired(email)){
				String sysrand = "";
				// 获取系统生成的验证码
				if (getSession().getAttribute("sysrand") != null) {
					sysrand = getSession().getAttribute("sysrand").toString();
				}
				// 验证验证码是否正确
				if (sysrand!=null&&!"".equals(sysrand)) {
					Msgcheck mc = new Msgcheck();
					mc.setCp_phone(email);
					mc.setCp_check(cp_check);
					mc.setCp_type("4");
					this.msgcheckService.insert(mc);
					String title = "用户验证码";
					String content = "尊敬的会员您好,您的验证码是：" + cp_check+ ",请在30分钟内进行验证，否则验证码将失效！";
					new MailInter().sendBatchMail(title, content, email);
					out.write("1");
				}else{
					out.write("0");
				}
			}else{
				out.write("0");
			}
		
	}
	
	/**
	 * 通过邮箱发送验证码
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public void sendEmailCodeCheck() throws Exception {
		// 定义request对象
		HttpServletRequest request = getRequest();
		PrintWriter out = getResponse().getWriter();
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String commentrand = request.getParameter("commentrand");
		String cp_check = RandomStrUtil.getRand("6");
		// 发送短信的方法
			if(!ValidateUtil.isRequired(email) && !ValidateUtil.isRequired(commentrand)){
				String sysrand = "";
				// 获取系统生成的验证码
				if (getSession().getAttribute("sysrand") != null) {
					sysrand = getSession().getAttribute("sysrand").toString();
				}
				// 验证验证码是否正确
				if (sysrand.equals(commentrand)) {
					Msgcheck mc = new Msgcheck();
					mc.setCp_phone(email);
					mc.setCp_check(cp_check);
					mc.setCp_type("4");
					this.msgcheckService.insert(mc);
					String title = "用户验证码";
					String content = "尊敬的会员您好,您的验证码是：" + cp_check+ ",请在30分钟内进行验证，否则验证码将失效！";
					new MailInter().sendBatchMail(title, content, email);
					out.write("1");
				}else{
					out.write("0");
				}
			}else{
				out.write("0");
			}
		
	}
	
	/**
	 * 通过邮箱发送验证码
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public void sendPhoneCodeCheck() throws Exception {
		// 定义request对象
		HttpServletRequest request = getRequest();
		PrintWriter out = getResponse().getWriter();
		request.setCharacterEncoding("UTF-8");
		String cphone = request.getParameter("phone");
		String commentrand = request.getParameter("commentrand");
		String cp_check = RandomStrUtil.getRand("6");
		// 发送短信的方法
		if(!ValidateUtil.isRequired(cphone) && !ValidateUtil.isRequired(commentrand)){
				String sysrand = "";
				// 获取系统生成的验证码
				if (getSession().getAttribute("sysrand") != null) {
					sysrand = getSession().getAttribute("sysrand").toString();
				}
				// 验证验证码是否正确
				if (sysrand.equals(commentrand)) {
					Msgcheck mc = new Msgcheck();
					mc.setCp_phone(cphone);
					mc.setCp_check(cp_check);
					if (request.getParameter("c_type") != null&& !request.getParameter("c_type").equals("")) {
						String cp_type = request.getParameter("c_type");
						mc.setCp_type(cp_type);
					}
					this.msgcheckService.insert(mc);
					// 通过手机发送验证码
					String tem_con = "亲爱的用户，您的手机验证码是${check_code}，此验证码30分钟内有效，请尽快完成验证。";
					tem_con = tem_con.replace("${check_code}", cp_check);
					SendMsg(cphone,"发送验证码", tem_con);
					out.write("1");
				}else{
					out.write("0");
				}
				
			}else{
				out.write("0");
			}
	}
	
	
	
	/**
	 * 方法描述：检查是否已存在邮箱
	 * 
	 * @return
	 * @throws Exception
	 */
	public void checkEmail() throws Exception {
		PrintWriter out = getResponse().getWriter();
		getRequest().setCharacterEncoding("UTF-8");
		getResponse().setCharacterEncoding("UTF-8");
		String email = getRequest().getParameter("email");
		if (email != null) {
			Map map = new HashMap();
			map.put("u-email", email);
			List list = this.memberuserService.getList(map);
			if (email != null && list.size() > 0) {
				out.write("1");
			}
		}
	}

	/**
	 * 方法描述：验证码是否正确
	 * 
	 * @return
	 * @throws Exception
	 */
	public void validateCode() throws Exception {
		getRequest().setCharacterEncoding("UTF-8");
		getResponse().setCharacterEncoding("UTF-8");
		getResponse().setContentType("text/html;charset=UTF-8;");
		PrintWriter out = getResponse().getWriter();
		String sysrand = "";
		// 获取系统生成的验证码
		if (getSession().getAttribute("sysrand") != null) {
			sysrand = getSession().getAttribute("sysrand").toString();
			if (!sysrand.equals(commentrand)) {
				out.write("1");
			} else {
				out.write("3");
			}
		}

	}

	/**
	 * 方法描述：跳转到注册页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String joinus() throws Exception {
		if(!ValidateUtil.isRequired(this.session_cust_id)){
		    	return goUrl("/");
		}
		selway = SysconfigFuc.getSysValue("cfg_selectCheckWay");
		// 注册条款
		serviceterms = PageTipFuc.getPageCon("register_agreement_page");
		Map map = new HashMap();
		map.put("area_level", "2");
	    areaList = areaService.getList(map);
		return goUrl("registerFirst");
	}

	/**
	 * 用户同意协议并注册，跳转到验证账号信息
	 * 
	 * @author LHY
	 * @throws IOException
	 * @throws ServletException
	 */
	public String validateReg()throws Exception {
		// 注册选择验证方式0:手机验证  1:邮箱验证  2:不验证
	//	selway = SysconfigFuc.getSysValue("cfg_selectCheckWay");
		//if(selway.equals("2")){
			return mallregister();
		//}else{
		//	return goUrl("registerSecond");
		//}
		
	}

	/**
	 * 方法描述：商城会员注册
	 * 
	 * @return
	 * @throws Exception
	 */
	public String malljoinus() throws Exception {
		sl = this.getRequest().getParameter("sl");
		return goUrl("joinus");
	}

	/**
	 * 方法描述：商城后台注册
	 * 
	 * @return
	 * @throws Exception
	 */
	public String mallregister() throws Exception {
		
		if (ValidateUtil.isRequired(membernum)) {
			this.addFieldError("areaError", "请选择地区");
			return joinus();
		}
		
		if (ValidateUtil.isRequired(user_name)) {
			this.addFieldError("user_name", "用户名不能为空");
			return joinus();
		}
		// 需要过滤的会员注册名
		if(this.memberService.isAllow(user_name,CFG_MB_NOTALLOW)){
			this.addFieldError("user_name", "本网站不允许注册此类用户名");
			return joinus();
		}
		if (existsTitle(user_name, "", "memberuser", "user_name")) {
			this.addFieldError("user_name", "该用户名已经存在,请重新输入");
			return joinus();
		}
		if (ValidateUtil.isRequired(passwd)) {
			this.addFieldError("passwd", "密码不能为空");
			return joinus();
		}
		
		String checkrand = "";
		if(!ValidateUtil.isRequired(is_phone_email)){
			if(is_phone_email.equals("0")){
				checkrand = commentrand_phone;
			}else if(is_phone_email.equals("1")){
				checkrand = commentrand;
			}
			
			// 验证验证码不能为空
			if (ValidateUtil.isRequired(checkrand)) {
				this.addFieldError("commentrand", "请输入验证码");
				i_c = "1";
				return joinus();
			}else{
				// 随机验证码
				String sysrand = "";
				// 获取系统生成的验证码
				if (getSession().getAttribute("sysrand") != null) {
					sysrand = getSession().getAttribute("sysrand").toString();
				}
				// 验证验证码是否正确
				if (!sysrand.equals(checkrand)) {
					this.addFieldError("commentrand", "验证码输入不正确");
					i_c = "1";
					return joinus();
				}
			}
				
		}
		
		member = new Member();
 		member.setMem_type("1");// 会员类型 1:个人会员
		member.setCust_name(user_name);
		member.setLogo_path(SysconfigFuc.getSysValue("cfg_memberlogo"));
		//member.setArea_attr(area_attr);
		// 设置会员注册后的买家级别
		member.setBuy_level(SysconfigFuc.getSysValue("cfg_buy_member"));
		// 会员状态
		// 注册是否需要人工审核
		String isaduit = SysconfigFuc.getSysValue(CFG_MB_ISAUDIT);
		if ("0".equals(isaduit)) {
			member.setInfo_state("0");// 0:未审核
		} else {
			member.setInfo_state("1");// 1:审核通过
		}
		Map amap = new HashMap();
		amap.put("area_level", "2");
		amap.put("area_number", membernum);
	    areaList = areaService.getList(amap);
	    if(areaList!=null && areaList.size()>0){
	    	Map areaMap = (Map) areaList.get(0);
	    	if(areaMap != null){
	    		member.setArea_attr(areaMap.get("area_id").toString());
	    	}
	    }
	    member.setGrowthvalue("0");//初始化成长值为0
		// 插入一条会员信息后返回该会员的ID
		String cust_id = this.memberService.insertGetPk(member);
		// 获取刚插入的会员对象
		Member mem = this.memberService.get(cust_id);
		// 初始化会员账号对象
		Memberuser user = new Memberuser();
		user.setCust_id(cust_id);
		user.setSex("2");
		user.setReal_name(realname);
		user.setUser_type("1");
		user.setUser_name(getUser_name());
		user.setPass_strength(psw_strong);
		passwd = Md5.getMD5(passwd.getBytes());
		user.setPasswd(passwd);
		if (phone != null && !phone.equals("")) {
			user.setCellphone(phone);
			user.setIs_check_mobile("0");
		} else {
			user.setCellphone(phone);
			user.setIs_check_mobile("1");
		}
		if (u_email != null && !u_email.equals("")) {
			user.setEmail(u_email);
			user.setIs_check_email("0");
		} else {
			user.setEmail(u_email);
			user.setIs_check_email("1");
		}
		user.setPhone("");
		//处理客户编号:registertype:注册方式：1：线下扫码 0：线上注册
		//用户直接从线上注册，会员号规则：区域号+000+会员注册号	
		Map map = new HashMap();
		membernum = membernum+"000";
		map.put("citystorenum", membernum);
		List memberuserList = memberuserService.getList(map);
		if (memberuserList != null && memberuserList.size() > 0) {
			HashMap mapvalue = new HashMap();
			mapvalue = (HashMap) memberuserList.get(0);
			String numvalue = mapvalue.get("membernum").toString();
			numvalue = numvalue.substring(6, numvalue.length());
			int len = numvalue.length();// 取得字符串的长度
			int index = 0;// 预定义第一个非零字符串的位置

			char strs[] = numvalue.toCharArray();// 将字符串转化成字符数组
			for (int i = 0; i < len; i++) {
				if ('0' != strs[i]) {
					index = i;// 找到非零字符串并跳出
					break;
				}
			}
			String strLast = numvalue.substring(index, len);// 截取字符串
			int numLast = Integer.parseInt(strLast) + 1;
			String str = String.format("%08d", numLast);
			membernum = membernum + str;
		} else {
			membernum = membernum + "00000001";
		}
		user.setMembernum(membernum);
		String user_id = this.memberuserService.insertGetPk(user);
		
		//新会员插入券--cjy--
		Comsumer comsumer = new Comsumer();
		comsumer.setCoupon_id("23");
		comsumer.setCust_id(cust_id);
		comsumer.setUse_state("0");
	    comsumer.setComsumer_no(str());
		this.comsumerService.insert(comsumer);
		comsumer.setCoupon_id("24");
		comsumer.setCust_id(cust_id);
		comsumer.setUse_state("0");
		comsumer.setComsumer_no(str());
	    this.comsumerService.insert(comsumer);
	    comsumer.setCoupon_id("25");
		comsumer.setCust_id(cust_id);
		comsumer.setUse_state("0");
		comsumer.setComsumer_no(str());
	    this.comsumerService.insert(comsumer);
	    comsumer.setCoupon_id("26");
		comsumer.setCust_id(cust_id);
		comsumer.setUse_state("0");
		comsumer.setComsumer_no(str());
		this.comsumerService.insert(comsumer);
		//=====cjy=====
		
		//初始化会员信誉对象
		Membercredit membercredit=new Membercredit();
		membercredit.setCust_id(cust_id);
		membercredit.setUser_id(user_id);
		membercredit.setBuy_num("0");
		membercredit.setSell_num("0");
		this.membercreditService.insert(membercredit);
		if(SysconfigFuc.getSysValue("cfg_mb_isaudit").toString().equals("0")){
        	mb_isaudit=true;
        	getSession().invalidate();
    		getSession().setAttribute(Constants.USER_NAME_NEW, user_name);
    		getSession().setAttribute(Constants.CUST_ID, cust_id);
    		getSession().setAttribute(Constants.USER_ID_NEW, user_id);
    		getSession().setAttribute(Constants.USER_ID, user_id);
    		//getSession().setAttribute(Constants.USER_NAME, user_name);
        	
        }else{
        	this.memberService.webInit(cust_id);
    		getSession().invalidate();
    		getSession().setAttribute(Constants.CUST_TYPE, Constants.MEMBER_TYPE);
    		getSession().setAttribute("syscode", DEFAULT_SYSCODE_VALUE);
    		getSession().setAttribute(Constants.USER_NAME_NEW, user_name);
    		getSession().setAttribute(Constants.CUST_ID, cust_id);
    		getSession().setAttribute(Constants.USER_ID_NEW, user_id);
        }
		mem = null;
		user = null;
		//短信消息提醒
		MessageAltFuc mesalt=new MessageAltFuc();
		mesalt.messageAutoSend("8",cust_id,null);
		getSession().removeAttribute("is_joinus");//避开敏感词相关
		return goUrl("registerThird");
	}

	/**
	 * 方法描述：跳转到找回密码页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String executepwd() throws Exception {
		super.saveRequestParameter();
		return goUrl("passInputName");
	}


	/**
	 * 跳转到验证码验证页面
	 * 
	 * @throws Exception
	 */
	public String checkCode() throws Exception {
		if(commonCheckCode()){
			return executepwd();
		}
		return goUrl("passCheckCode");
	}

	/**
	 * @author : HZX
	 * @date : Feb 17, 2014 11:25:40 AM
	 * @Method Description :通用校验验证码和用户名
	 */
	public boolean commonCheckCode(){
		String sysrand = "";
		// 获取系统生成的验证码
		if (getSession().getAttribute("sysrand") != null) {
			sysrand = getSession().getAttribute("sysrand").toString();
		}
		if (ValidateUtil.isRequired(username)) {
			this.addFieldError("username", "请输入用户名/邮箱/手机号!");
			i_u="0";
			return true;
		} else if (username.equals("用户名/邮箱/手机号")) {
			this.addFieldError("username", "请输入用户名/邮箱/手机号!");
			i_u="0";
			return true;
		}
		// 验证码
		if (commentrand.equals("")) {
			this.addFieldError("trand", "请输入验证码");
			i_b="0";
			return true;
		}
		if (!sysrand.equals(commentrand)) {
			this.addFieldError("trand", "验证码输入错误");
			i_b="0";
			return true;
		}
		// memberuser=this.memberuserService.getMemberuserByusername(username);
		// 判断是否存在该用户名，邮箱，手机号
		List loginuserList=this.memberService.is_exist_user(username);
		if(loginuserList!=null&&loginuserList.size() == 0){
			this.addFieldError("username", "用户名/邮箱/手机号不存在!");
			i_u="0";
			return true;
		}
		// 如果loginuserList不为空就通过map取值
		Iterator it = loginuserList.iterator();
		while (it.hasNext()) {
			Map map = (Map) it.next();
			email = (String) map.get("email");
			phone = (String) map.get("cellphone");
			username = (String) map.get("user_name");
		}
		i_u="1";
		i_b="1";
		return false;
	}
	/**
	 * 返回获取验证码的页面
	 * 
	 * @return
	 */
	public String retPassCheck() {
		return goUrl("passCheckCode");
	}

	/**
	 * 判断验证码是否正确，跳转到密码重置页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String passReset() throws Exception {
		if (ValidateUtil.isRequired(checkcode)) {
			this.addFieldError("checkcode", "请输入验证码！");
			return goUrl("passCheckCode");
		}
		String c_phone="";
		if (deflag.equals("1")) {
			c_phone=phone;
		} else if (deflag.equals("2")) {
			c_phone=email;
		}
		boolean ckfage=msgcheckService.checkMsgListToUse(c_phone, checkcode);
		if (ckfage==false) {
			cc="0";
			this.addFieldError("checkcode", "验证码过期或错误，请重获取验证码！");
			return goUrl("passCheckCode");
		}else {
			cc="1";
		}
		return goUrl("passReset");
	}


	/**
	 * 返回设置密码的页面
	 * 
	 * @return
	 */
	public String retuUpdatPas() {
		return goUrl("passReset");
	}


	/**
	 * 修改密码
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String updatePass() throws UnsupportedEncodingException {
		if(commonCheckPass()){
			return retuUpdatPas();
		}
		memberuser = this.memberuserService.getMemberuserByusername(username);
		String passwd=rep_pass;
		rep_pass = Md5.getMD5(rep_pass.getBytes());
		memberfund =this.memberfundService.get(memberuser.getCust_id());
		if(memberfund!=null){
			String pay_psw = "";
			if(memberfund.getPay_passwd() != null) {
				pay_psw =memberfund.getPay_passwd();
			}
			if(pay_psw.equals(rep_pass)){
				this.addFieldError("password", "登录密码不能和支付密码一样！");
				return retuUpdatPas();
			}else {
				memberuser.setPasswd(rep_pass);
			}
		}else {
			memberuser.setPasswd(rep_pass);
		}
		this.memberuserService.update(memberuser);
		getSession().invalidate();
		getSession().setAttribute(Constants.CUST_ID, memberuser.getCust_id());
		getSession().setAttribute(Constants.USER_ID, memberuser.getUser_id());
		getSession().setAttribute(Constants.USER_NAME, username);
		//消息提醒
		MessageAltFuc mesalt=new MessageAltFuc();
		mesalt.messageAutoSend("9",memberuser.getCust_id(),null,"0",passwd);
		getSession().invalidate();
		return goUrl("passSuccess");
	}
	/**
	 * @author : HZX
	 * @date : Feb 17, 2014 11:30:27 AM
	 * @Method Description :通用校验密码修改
	 */
	public boolean commonCheckPass(){
		if (ValidateUtil.isRequired(password)) {
			this.addFieldError("password", "请输入密码！");
			p="0";
			return true;
		}
		if (ValidateUtil.isRequired(rep_pass)) {
			this.addFieldError("rep_pass", "请输入确认密码！");
			rp="0";
			return true;
		}
		if (!password.equals(rep_pass)) {
			this.addFieldError("rep_pass", "您输入的密码不一致，请重新输入！");
			rp="0";
			return true;
		}
		/*advinfo = this.advinfoService.getImg("143");//一张广告图
		if (advinfo != null) {
			url = advinfo.getLink_url();
			img = advinfo.getImg_path();
		}*/
		p="1";
		rp="1";
		return false;
	}
	
	/**
	 * 
	 * @throws Exception
	 */
    public  void  generateRandomNumbers()throws Exception{
    	HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		request.getSession().setAttribute("random_numbers", "123456");
    }               
	
	/**
	 * @author AdministratorQJY
	 * @method 生成随机数，用于前台                                                             
	 * 
	 * @throws Exception
	 */
	public void compareRandomNumbers()throws Exception{
		// 定义request对象
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = getResponse().getWriter();
		String random_numbers = "";
		if(request.getSession().getAttribute("random_numbers")!=null && !"".equals(request.getSession().getAttribute("random_numbers"))){
		    
			random_numbers= request.getSession().getAttribute("random_numbers").toString();
			if(random_numbers.equals("123456")){
				out.write("1");
			}
			request.getSession().removeAttribute("random_numbers");
		}
		
	}
	
	
	
	
	/**
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * @param Member
	 *            the member to set
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * @return the MemberList
	 */
	public List getMemberList() {
		return memberList;
	}

	/**
	 * @param memberList
	 *            the MemberList to set
	 */
	public void setMemberList(List memberList) {
		this.memberList = memberList;
	}

	/**
	 * @return the commparaList
	 */
	public List getCommparaList() {
		return commparaList;
	}

	/**
	 * @param commparaList
	 *            the commparaList to set
	 */
	public void setCommparaList(List commparaList) {
		this.commparaList = commparaList;
	}

	/**
	 * @return the cat_attr
	 */
	public String getCat_attr() {
		return cat_attr;
	}

	/**
	 * @param cat_attr
	 *            the cat_attr to set
	 */
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}

	/**
	 * @return the area_attr
	 */
	public String getArea_attr() {
		return area_attr;
	}

	/**
	 * @param area_attr
	 *            the area_attr to set
	 */
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}

	/**
	 * @return the hiddenvalue
	 */
	public String getHiddenvalue() {
		return hiddenvalue;
	}

	/**
	 * @param hiddenvalue
	 *            the hiddenvalue to set
	 */
	public void setHiddenvalue(String hiddenvalue) {
		this.hiddenvalue = hiddenvalue;
	}

	/**
	 * @return the hidden_up_level
	 */
	public String getHidden_up_level() {
		return hidden_up_level;
	}

	/**
	 * @param hidden_up_level
	 *            the hidden_up_level to set
	 */
	public void setHidden_up_level(String hidden_up_level) {
		this.hidden_up_level = hidden_up_level;
	}

	/**
	 * @return the hidden_up_cate_id
	 */
	public String getHidden_up_cate_id() {
		return hidden_up_cate_id;
	}

	/**
	 * @param hidden_up_cate_id
	 *            the hidden_up_cate_id to set
	 */
	public void setHidden_up_cate_id(String hidden_up_cate_id) {
		this.hidden_up_cate_id = hidden_up_cate_id;
	}

	/**
	 * @return the hidden_area_value
	 */
	public String getHidden_area_value() {
		return hidden_area_value;
	}

	/**
	 * @param hidden_area_value
	 *            the hidden_area_value to set
	 */
	public void setHidden_area_value(String hidden_area_value) {
		this.hidden_area_value = hidden_area_value;
	}

	/**
	 * @return the hidden_up_area_id
	 */
	public String getHidden_up_area_id() {
		return hidden_up_area_id;
	}

	/**
	 * @param hidden_up_area_id
	 *            the hidden_up_area_id to set
	 */
	public void setHidden_up_area_id(String hidden_up_area_id) {
		this.hidden_up_area_id = hidden_up_area_id;
	}

	/**
	 * @param commentrand
	 *            the commentrand to set
	 */
	public void setCommentrand(String commentrand) {
		this.commentrand = commentrand;
	}

	/**
	 * @return the memberuser
	 */
	public Memberuser getMemberuser() {
		return memberuser;
	}

	/**
	 * @param memberuser
	 *            the memberuser to set
	 */
	public void setMemberuser(Memberuser memberuser) {
		this.memberuser = memberuser;
	}

	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param user_name
	 *            the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd
	 *            the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * @return the select_cat_id
	 */
	public String getSelect_cat_id() {
		return select_cat_id;
	}

	/**
	 * @param select_cat_id
	 *            the select_cat_id to set
	 */
	public void setSelect_cat_id(String select_cat_id) {
		this.select_cat_id = select_cat_id;
	}

	/**
	 * @return the emailurl
	 */
	public String getEmailurl() {
		return emailurl;
	}

	/**
	 * @param emailurl
	 *            the emailurl to set
	 */
	public void setEmailurl(String emailurl) {
		this.emailurl = emailurl;
	}

	public List getClientStrList() {
		return clientStrList;
	}

	public void setClientStrList(List clientStrList) {
		this.clientStrList = clientStrList;
	}

	public Memberfund getMemberfund() {
		return memberfund;
	}

	public void setMemberfund(Memberfund memberfund) {
		this.memberfund = memberfund;
	}

	public IMemberfundService getMemberfundService() {
		return memberfundService;
	}

	public void setMemberfundService(IMemberfundService memberfundService) {
		this.memberfundService = memberfundService;
	}

	

	public Memberinter getMemberinter() {
		return memberinter;
	}

	public void setMemberinter(Memberinter memberinter) {
		this.memberinter = memberinter;
	}

	public Expressfund getExpressfund() {
		return expressfund;
	}

	public void setExpressfund(Expressfund expressfund) {
		this.expressfund = expressfund;
	}

}
