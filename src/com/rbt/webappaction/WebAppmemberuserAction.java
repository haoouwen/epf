/*
 * Package:com.rbt.webaction
 * FileName: WebmemberuserAction.java 
 */
package com.rbt.webappaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.rbt.common.Constants;
import com.rbt.common.Md5;
import com.rbt.common.util.DateUtil;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.MessageAltFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.message.MailInter;
import com.rbt.model.Advinfo;
import com.rbt.model.Comsumer;
import com.rbt.model.Emailtemplate;
import com.rbt.model.Fundrecharge;
import com.rbt.model.Malllevelset;
import com.rbt.model.Member;
import com.rbt.model.Membercredit;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.model.Memberuser;
import com.rbt.model.Memrole;
import com.rbt.model.Msgcheck;
import com.rbt.model.Rechargeablecards;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.IComsumerService;
import com.rbt.service.IEmailtemplateService;
import com.rbt.service.IFundrechargeService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IMalllevelsetService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMembercreditService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberinterService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IMemroleService;
import com.rbt.service.IMsgcheckService;
import com.rbt.service.IPaymentService;
import com.rbt.service.IReceiveboxService;
import com.rbt.service.IRechargeablecardsService;
import com.rbt.service.IRoleService;
import com.rbt.service.ISysmenuService;
/**
 * @function 功能 会员前台管理操作类
 * @author 创建人 QJY
 * @date 创建日期 Sep 17, 2014 15:50:13 PM
 */
@Controller
public class WebAppmemberuserAction extends WebAppbaseAction {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 5535005240249968461L;
	/** *****************实体层******************* */
	public Memberuser memberuser;
	public Member member;
	public Memrole memrole;
	public Malllevelset malllevelset;
	public Memberfund memberfund;
	public Rechargeablecards rechargeablecards;

	/** *****************业务层接口*************** */
	@Autowired
	public IMemberuserService memberuserService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IMalllevelsetService malllevelsetService;
	@Autowired
	public IRoleService roleService;
	@Autowired
	public ISysmenuService sysmenuService;
	@Autowired
	public IAdvinfoService advinfoService;
	@Autowired
	public IMemroleService memroleService;
	@Autowired
	public IMsgcheckService msgcheckService;
	@Autowired
	private IGoodsorderService goodsorderService;
	@Autowired
	public IEmailtemplateService emailtemplateService;
	@Autowired
	public IMemberfundService memberfundService;
	@Autowired
	public IMembercreditService membercreditService;
	@Autowired
	public IPaymentService  paymentService;
	@Autowired
	public IFundrechargeService fundrechargeService;
	@Autowired
	private IMemberinterService memberinterService;
	@Autowired
	private IMalllevelsetService mallvelsetService;
	@Autowired
	private IRechargeablecardsService rechargeablecardsService;
	@Autowired
	private IReceiveboxService receiveboxService;
	@Autowired
	private IComsumerService comsumerService;
	/** *******************集合******************* */
	public List emailtemplateList;// 会员邮件发送模板信息
	public List malllevelsetsellList;
	public List malllevelsetbuyList;
	public List msgcheckList;
	public List paymentList;
	public List fundrechargeList;
	public List recomList;//推荐商品
	/** *******************字段******************* */

	public String username;// 用户名
	public String commentrand;// 验证码
	public Advinfo advinfo;// 广告对象
	public Msgcheck msgcheck;// 密码的验证信息
	public SysconfigFuc sysconfigFuc;// 获取安全设置参数
	public String lastlogintime;// 上次登录时间
	public String login_txt = "会员登录";
	public String success;// 提交状态
	public String email;// 邮箱
	public String mallusername;
	public String i_username;
	public String i_password;
	public String i_c;
	public String refloc;// 返回的路径
	public String message;//提示信息
	public String oldpasswd;//重置密码
	public String newpasswd;//新密码
	public String newphone;//修改手机
	public String confirmpasswd;//确认密码
	public String newemail;//修改邮箱
	public String pass_strength;//密码强度
	public String login_passwd;//登录密码
	public String login_state = "1";//登录标识
	public boolean isok=false;
	public String  level_url;//等级图片
	public String new_mobile;
	public String cardskey;
	
	
	/***触屏版相关字段*****/
	private static final String CFG_MB_NOTALLOW = "cfg_mb_notallow";//不允许注册的登陆名
	private static final String MESSAGE_VALUE = "1";
	private static final String CFG_MB_ISAUDIT = "cfg_mb_isaudit";//注册是否需要人工审核
	private final static String DEFAULT_SYSCODE_VALUE = "com";//后台类型
	private static final String CFG_WEBNAME = "cfg_webname";//网站名称
	private static final String CFG_BASEHOST = "cfg_basehost";
	public boolean mb_isaudit=false;//会员注册是否需审核
	public String realname;//真实姓名
	public String user_name;//注册用户名—规则：
	public String u_email;// 邮箱
	public String passwd;//注册密码-规则：
	public String phone;//注册手机号码-规则：
	public String step_flag;//注册步骤
	
	public Integer waitPayNunm;//待付款订单数量
	public Integer waitDeliveryNum;//待发货订单数量
	public Integer waitReceiptNum;//待收货订单数量
	public Integer waitEvaluationNum;//待评价数量
	public String rep_pass;
	//控制显示提示
	public String i_u;
	public String i_b;
	public String cc;
	public String c_t;
	public String p;
	public String rp;
	public String checkcode;//验证码
	public String deflag;// 标识
	public String password;
	public String r_psw_num;
	
	//自动登陆标识
	public String isAutoLogin;
	
	public List areaList;
	public String membernum;
	public String registertype;//注册方式：0线上注册   1线下扫码方式
	public String area_number;//区域号
	public String custnum;//门店号
	public String cfg_defaultpaymentMobile=SysconfigFuc.getSysValue("cfg_defaultpaymentMobile");//PC端默认充值方式;
	public String recharge_amount;
	
	public String total_balance;//账户总余额
	public String total_integral;//账号总积分

	public String member_icon;//会员头像路径
	public String servicetel;
	public Integer total_news=0;//获取会员消息数量
	
	/**
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
	 * @Method Description :跳转到触屏版网站注册页面
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
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
				out.write("2");
			}
		}
	}

	/**
	 * @Method Description :跳转到触屏版网站注册页面
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
	 * @return
	 * @throws Exception
	 */
	public void checkPhone() throws IOException {
		PrintWriter out = getResponse().getWriter();
		getRequest().setCharacterEncoding("UTF-8");
		getResponse().setCharacterEncoding("UTF-8");
		
		if (phone != null
				&& existsTitle(phone, "", "memberuser", "user_name")) {
			out.write("1");
		} else {
				out.write("2");
		}
		
	}

	/**
	 * @Method Description :跳转到触屏版网站注册页面
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
	 * @return
	 * @throws Exception
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
		if (ckfage==false) {
			out.write("1");
		}else {
			out.write("3");
		}
	}

	/**
	 * @Method Description :跳转到触屏版网站注册页面
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
	 * @return
	 * @throws Exception
	 */
	public void sendPhoneCode() throws Exception {
		// 定义request对象
		HttpServletRequest request = getRequest();
		PrintWriter out = getResponse().getWriter();
		request.setCharacterEncoding("UTF-8");
		String cphone = request.getParameter("phone");
		String password = request.getParameter("password");
		String confirm_pwd = request.getParameter("confirm_pwd");
		String commentrand = request.getParameter("commentrand");
		String cp_check = RandomStrUtil.getRand("6");
		
		if(!ValidateUtil.isRequired(cphone) && !ValidateUtil.isRequired(password) && !ValidateUtil.isRequired(confirm_pwd) && !ValidateUtil.isRequired(commentrand)){
			if(!ValidateUtil.isMobile(cphone)){
				// 发送短信的方法
				Msgcheck mc = new Msgcheck();
				mc.setCp_phone(cphone);
				mc.setCp_check(cp_check);
				if (request.getParameter("c_type") != null&& !request.getParameter("c_type").equals("")) {
					String cp_type = request.getParameter("c_type");
					mc.setCp_type(cp_type);
				}
				this.msgcheckService.insert(mc);
				// 通过手机发送验证码
				Emailtemplate ep = this.emailtemplateService.getEmailtemplateByTempcode("cp_code");
				String tem_con = ep.getTemp_con();
				tem_con = tem_con.replace("${check_code}", cp_check);
				SendMsg(cphone,ep.getTemp_name(), tem_con);
				out.write("1");
				
			}else{
				out.write("0");
			}
			
		}else{
			out.write("0");
		}
		
	}
	
	/**
	 * @Method Description :跳转到触屏版网站注册页面
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
	 * @return
	 * @throws Exception
	 */
	public void sendCheckPhone() throws Exception {
		// 定义request对象
		HttpServletRequest request = getRequest();
		PrintWriter out = getResponse().getWriter();
		request.setCharacterEncoding("UTF-8");
		String cphone = request.getParameter("phone");

		String cp_check = RandomStrUtil.getRand("6");
		
		if(!ValidateUtil.isRequired(cphone) && !ValidateUtil.isMobile(cphone)){
				// 发送短信的方法
				Msgcheck mc = new Msgcheck();
				mc.setCp_phone(cphone);
				mc.setCp_check(cp_check);
				if (request.getParameter("c_type") != null&& !request.getParameter("c_type").equals("")) {
					String cp_type = request.getParameter("c_type");
					mc.setCp_type(cp_type);
				}
				this.msgcheckService.insert(mc);
				// 通过手机发送验证码
				Emailtemplate ep = this.emailtemplateService.getEmailtemplateByTempcode("cp_code");
				String tem_con = ep.getTemp_con();
				tem_con = tem_con.replace("${check_code}", cp_check);
				SendMsg(cphone,ep.getTemp_name(), tem_con);
				out.write("1");
				
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
	 * @Method Description :跳转到触屏版网站注册页面
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
	 * @return
	 * @throws Exception
	 */
	public void sendEmailCode() throws UnsupportedEncodingException {
		// 定义request对象
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String cp_check = RandomStrUtil.getRand("6");
		Msgcheck mc = new Msgcheck();
		mc.setCp_phone(email);
		mc.setCp_check(cp_check);
		mc.setCp_type("4");
		this.msgcheckService.insert(mc);
		String title = "用户验证码";
		String content = "尊敬的会员您好,您的验证码是：" + cp_check+ ",请在30分钟内进行验证，否则验证码将失效！";
		new MailInter().sendBatchMail(title, content, email);
	}

	/**
	 * @Method Description :跳转到触屏版网站注册页面
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
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
	 * @Method Description :跳转到触屏版网站注册页面
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
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
	 * @Method Description :跳转到触屏版网站注册页面
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
	 * @return
	 * @throws Exception
	 */
	public String webappRegister() throws Exception {
		
		if(!ValidateUtil.isRequired(this.session_cust_id)){
			getResponse().sendRedirect("/mindex.html");
	    	return NONE;
	    }
		
		// 注册条款
		//serviceterms = PageTipFuc.getPageCon("register_agreement_page");
		step_flag = "1";
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		if (request.getParameter("custnum") != null
				&& !"".equals(request.getParameter("custnum"))
				&& request.getParameter("registertype") != null
				&& !"".equals(request.getParameter("registertype"))) {
			
			custnum = request.getParameter("custnum").toString();
			registertype = request.getParameter("registertype").toString();
			if(registertype.equals("1")){
				Map areamap = new HashMap();
				areamap.put("area_level", "2");
				areamap.put("area_number", custnum.substring(0, 3));
				List now_areaList = areaService.getList(areamap);
				if(now_areaList != null && now_areaList.size()>0){
					Map areanameMap=(Map)now_areaList.get(0);
					area_attr=areanameMap.get("area_name").toString();
				}
			}
		}else{
			Map map = new HashMap();
			map.put("area_level", "2");
		    areaList = areaService.getList(map);
		}
		
		return goUrl("mbRegister");
	}
	
	
	/**
	 * @Method Description :注册第二步
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
	 * @return
	 * @throws Exception
	 */
	public String registerFirst()throws Exception {
		
		if(!"".equals(registertype) && registertype!=null){
			if(registertype.equals("1")){
				Map areamap = new HashMap();
				areamap.put("area_level", "2");
				areamap.put("area_number",  custnum.substring(0, 3));
				List now_areaList = areaService.getList(areamap);
				if(now_areaList != null && now_areaList.size()>0){
					Map areanameMap=(Map)now_areaList.get(0);
					area_attr=areanameMap.get("area_name").toString();
				}
			}
		}else{
			Map map = new HashMap();
			map.put("area_level", "2");
		    areaList = areaService.getList(map);
		}
		
		return goUrl("mbRegister");
	}


	/**
	 * @Method Description :注册最后一步
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
	 * @return
	 * @throws Exception
	 */
	public String registerSecond() throws Exception {
		user_name=phone;
		if (ValidateUtil.isRequired(registertype)){
			if (ValidateUtil.isRequired(membernum)) {
				this.addFieldError("areaError", "请选择地区");
				step_flag = "1";
				return registerFirst();
			}
		}
		/*if(ValidateUtil.isRequired(membernum)){
			this.addFieldError("areaError", "请选择地区");
			step_flag = "1";
			return registerFirst();
		}*/
		// 需要过滤的会员注册名
		if(this.memberService.isAllow(user_name,CFG_MB_NOTALLOW)){
			this.addFieldError("user_name", "本网站不允许注册此类用户名");
			step_flag = "1";
			return registerFirst();
		}
		if (existsTitle(user_name, "", "memberuser", "user_name")) {
			this.addFieldError("user_name", "该用户名已经存在,请重新输入");
			step_flag = "1";
			return registerFirst();
		}
		login_passwd =  passwd;
		if (ValidateUtil.isRequired(passwd)) {
			this.addFieldError("passwd", "密码不能为空");
			step_flag = "1";
			return registerFirst();
		}
		
		// 验证验证码不能为空
		if (ValidateUtil.isRequired(commentrand)) {
			this.addFieldError("commentrand", "请输入图形验证码");
			step_flag = "1";
			return registerFirst();
		}else{
			// 随机验证码
			String sysrand = "";
			// 获取系统生成的验证码
			if (getSession().getAttribute("sysrand") != null) {
				sysrand = getSession().getAttribute("sysrand").toString();
			}
			// 验证验证码是否正确
			if (!sysrand.equals(commentrand)) {
				this.addFieldError("commentrand", "验证码输入不正确");
				step_flag = "1";
				return registerFirst();
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
		member.setGrowthvalue("0");//初始化成长值为0	
		if ("1".equals(registertype)){
			Map amap = new HashMap();
			amap.put("area_level", "2");
			amap.put("area_number", custnum.substring(0, 3));
		    areaList = areaService.getList(amap);
		    if(areaList!=null && areaList.size()>0){
		    	Map areaMap = (Map) areaList.get(0);
		    	if(areaMap != null){
		    		member.setArea_attr(areaMap.get("area_id").toString());
		    	}
		    }
		}else if("2".equals(registertype)){
			//todo
		}else{
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
		}
		
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
		user.setUser_name(user_name);
		user.setPass_strength(pass_strength);
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
		// 处理客户编号:registertype:注册方式：1：线下扫码 0：线上注册
		// 用户直接从线上注册，会员号规则：区域号+000+会员注册顺序号
		// 用户在线下实体店扫描注册两种：门店会员号规则：区域号+门店号+会员注册顺序号
		// 直营店会员号规则：000+直营店号+会员注册顺序号
		if ("1".equals(registertype)|| "2".equals(registertype)) {
			user.setMembernum(getMmeberCode(custnum));
		}else{//线下手机扫描二维码:门店号：区域号+000组成
			membernum = membernum+"000";
			user.setMembernum(getMmeberCode(membernum));
		}
		
		String user_id = this.memberuserService.insertGetPk(user);
		
		//新会员插入券
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
		comsumer.setCoupon_id("27");
	    comsumer.setCust_id(cust_id);
		comsumer.setUse_state("0");
		comsumer.setComsumer_no(str());
		this.comsumerService.insert(comsumer);
			 
		
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
    		// 将值存入session中
    		getSession().invalidate();
    		getSession().setAttribute(Constants.CUST_TYPE, Constants.MEMBER_TYPE);
    		// syscode sys:运营商 com:会员
    		getSession().setAttribute("syscode", DEFAULT_SYSCODE_VALUE);
    		// 会员级别
    		//避开系统根据Constants.USER_ID的各种拦截（目前只用于邮件发送历史）
    		getSession().setAttribute(Constants.USER_NAME_NEW, user_name);
    		getSession().setAttribute(Constants.CUST_ID, cust_id);
    		getSession().setAttribute(Constants.USER_ID_NEW, user_id);
        }
		String web_name = "";
		String domain_name = "";
		// 获取网站名称
		web_name = SysconfigFuc.getSysValue(CFG_WEBNAME);
		// 获取网站域名
		domain_name = SysconfigFuc.getSysValue(CFG_BASEHOST);
		mem = null;
		user = null;
		if(email!=null){
			//消息提醒
			MessageAltFuc mesalt=new MessageAltFuc();
			mesalt.messageAutoSend("8",cust_id,null);
		}
		//getSession().removeAttribute("is_joinus");//避开敏感词相关

		member = null;
		user = null;
		step_flag = "3";
		return goUrl("mbRegister");
	}
	
	/**
	 *  获取会员的编号，直营店，门店，线上
	 * @return
	 */
	public String getMmeberCode(String codePara){
		String member_code = "";
		Map map = new HashMap();
		map.put("citystorenum", codePara);
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
			codePara = codePara + str;
		} else {
			codePara = codePara + "00000001";
		}
		member_code = codePara;
		return member_code;
	}
	
	/**
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
	 * @Method Description :跳转到触屏版网站登录页面
	 */
	public String webappLogin() throws Exception {
		if(getRequest().getParameter("registertype")!=null && !"".equals(getRequest().getParameter("registertype"))){
			registertype = getRequest().getParameter("registertype").toString();
		}
		if(getRequest().getParameter("custnum")!=null && !"".equals(getRequest().getParameter("custnum"))){
			custnum = getRequest().getParameter("custnum").toString();
		}
		
		return goUrl("mbLogin");
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 12:19:52 PM
	 * @Method Description : 获取COKES的值
	 */
	public String readcokes() {
		HttpServletRequest request = getRequest();
		return this.memberuserService.getCookieValue(request, "mallLoginName");
	}

	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 12:19:40 PM
	 * @Method Description :保存COKES的值
	 */
	public void savecokes(String username) {
		HttpServletResponse response = getResponse();
		this.memberuserService.savecokes(response, username, mallusername);
	}
    
	/**
	 * 方法描述：商城会员登录方法判断
	 * 
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String webappUserLogin() throws Exception {
		// 返回的路径
		String loc = "";
		if (getRequest().getParameter("loc") != null) {
			loc = getRequest().getParameter("loc");
			if (!ValidateUtil.isRequired(loc)) {
				refloc = loc;
			} else if (!ValidateUtil.isRequired(refloc)) {
				loc = refloc;
			}
		}
		// 用户名
		String user_name = "";
		if (memberuser != null && memberuser.getUser_name() != null) {
			user_name = memberuser.getUser_name();
		}
		// 密码
		String passwd = "";
		if (memberuser != null && memberuser.getPasswd() != null) {
			passwd = memberuser.getPasswd();
		}
		// 随机验证码
		String sysrand = "";
		if(login_state.equals("1")) {
			// 获取系统生成的验证码
			if (getSession().getAttribute("sysrand") != null) {
				sysrand = getSession().getAttribute("sysrand").toString();
			}
		}
		// 判断用户名是否为空
		if (ValidateUtil.isRequired(user_name)) {
			this.addFieldError("memberuser.user_name", "请输入用户名/手机号!");
			i_username = "1";
			commentrand="";
			return webappLogin();
		}
		// 判断密码是否为空
		if (ValidateUtil.isRequired(passwd)) {
			this.addFieldError("memberuser.passwd", "请输入密码!");
			i_password = "1";
			commentrand="";
			return webappLogin();
		}
		
		//判断是否为自动登陆
		if(ValidateUtil.isRequired(isAutoLogin) && !"yes".equals(isAutoLogin) &&  login_state.equals("1")){
			
			// 验证验证码不能为空
			if (ValidateUtil.isRequired(commentrand)) {
				this.addFieldError("commentrand", "请输入验证码");
				i_c = "1";
				commentrand="";
				return webappLogin();
			}
			// 验证验证码是否正确
			if (!sysrand.equals(commentrand)) {
				this.addFieldError("commentrand", "验证码输入不正确");
				i_c = "1";
				commentrand="";
				return webappLogin();
			}
			
		}
		
		// 查询出30分钟内是否存在一个 cp_type=0,ce_phone=xiaoge的用户登录次数是否大于三
		String login_limittime, login_limitnum;
		// 获取系统设置的时间
		login_limittime = SysconfigFuc.getSysValue("cfg_login_limittime");
		// 获取系统登录的次数
		login_limitnum = SysconfigFuc.getSysValue("cfg_login_limitnum");
		// 将接收的时间为字符串转化成整形
		int login_limitnum_int = Integer.parseInt(login_limitnum);
		Map msgckeckMap = new HashMap();
		msgckeckMap.put("cp_phone", user_name);
		msgckeckMap.put("cp_type", "0");
		// 获取登录时间在30分钟内
		msgckeckMap.put("login_limittime", login_limittime);
		msgcheckList = this.msgcheckService.getList(msgckeckMap);
		int leave_times = login_limitnum_int - (msgcheckList.size());
		if (leave_times + 1 == 0) {
			this.addFieldError("memberuser.passwd", "帐户被冻结,稍后"
					+ login_limittime + "分钟后重新登录!");
			i_username = "1";
			commentrand="";
			return webappLogin();
		}
		// 判断是否存在该用户名，邮箱，手机号
		List loginuserList = this.memberService.is_exist_user(user_name);
		if (loginuserList != null && loginuserList.size() == 0) {
			this.addFieldError("memberuser.user_name","用户名/手机号不存在!");
			i_username = "1";
			commentrand="";
			return webappLogin();
		}
		if (loginuserList != null && loginuserList.size() > 0) {
			// 判断cust_id是否等于0,0代表运营商
			Map map = (HashMap) loginuserList.get(0);
			if (map != null && map.get("cust_id") != null) {
				String cust_id = map.get("cust_id").toString();
				if (cust_id.equals("0")) {
					this.addFieldError("memberuser.user_name", "请登录运营商后台！");
					i_username = "1";
					return "adminlogin";
				}
			}
		}
		// 通过用户名与密码查找用户
		passwd = Md5.getMD5(passwd.getBytes());
		Map pageMap = new HashMap();
		Map uMap = (Map) loginuserList.get(0);
		user_name = uMap.get("user_name").toString();
		pageMap.put("user_name", user_name);
		pageMap.put("passwd", passwd);
		loginuserList = this.memberuserService.getList(pageMap);
		// 如果密码错误，则提示
		if (loginuserList == null || loginuserList.size() == 0) {
			// 密码验证错误后，应该插入的数据
			Msgcheck msgcheck = new Msgcheck();
			msgcheck.setCp_phone(user_name);
			msgcheck.setCp_type("0");
			msgcheck.setCp_check("");
			this.msgcheckService.insert(msgcheck);
			if (leave_times == 0) {
				this.addFieldError("memberuser.passwd", "帐户被冻结,请于"
						+ login_limittime + "分钟后重新登录!");
				i_username = "2";
			} else {
				i_password = "1";
				this.addFieldError("memberuser.passwd", "密码输入错误,您还有"
						+ leave_times + "次登录机会!");
			}
			commentrand="";
			return webappLogin();
		}

		// 会员属性
		String user_id = "", cust_id = "", role_code = "", is_active = "", info_state = "";
		// mem_type 0：商家 1：个人
		String mem_type = "";
		// user_type 1：会员管理员 2：会员子账号
		String user_type = "";
		if (loginuserList != null && loginuserList.size() > 0) {
			Map loginMap = (HashMap) loginuserList.get(0);
			if (loginMap.get("user_id") != null) {
				user_id = loginMap.get("user_id").toString();
			}
			if (loginMap.get("cust_id") != null) {
				cust_id = loginMap.get("cust_id").toString();
			}
			if (loginMap.get("user_type") != null) {
				user_type = loginMap.get("user_type").toString();
			}
			if (loginMap.get("is_active") != null) {
				is_active = loginMap.get("is_active").toString();
			}
			if (loginMap.get("mem_type") != null) {
				mem_type = loginMap.get("mem_type").toString();
			}
			if (loginMap.get("info_state") != null) {
				info_state = loginMap.get("info_state").toString();
			}
			if (loginMap.get("role_code") != null) {
				role_code = loginMap.get("role_code").toString();
			}
			// 判断用户的cust_id是否等于0，0代表运营商
			if (cust_id.equals("0")) {
				this.addFieldError("memberuser.user_name", "请登录运营商后台！");
				i_username = "1";
				return "adminlogin";
			}
			if (!info_state.equals("1")) {
				this.addFieldError("memberuser.user_name", "该账号未审核或审核不通过！");
				i_username = "1";
				commentrand="";
				return webappLogin();
			}
		}

		// 系统设置是否允许未审核会员登录，如果系统参数等于1却会员的状态等于0就是不能登录
		if (cfg_mb_isaudit.equals("1")) {
			if (info_state.equals("0")) {
				this.addFieldError("memberuser.user_name", "用户未审核");
				i_username = "2";
				commentrand="";
				return webappLogin();
			}
		}
		//清除所有session值
		getSession().removeAttribute("menu_right");
		getSession().removeAttribute("oper_right");
		member = this.memberService.get(cust_id);
		StringBuffer sb = this.memberuserService.getMentRight(member,
				role_code, mem_type, user_type);
		// 获取上次登录时间或是第一次登录时间
		get_Last_Login_Time(user_id);
		// B2C菜单串
		String menu_right = sb.toString();
		// 将值存入session中
		getSession().setAttribute(Constants.CUST_ID, cust_id);
		getSession().setAttribute(Constants.CUST_NAME, member.getCust_name());
		getSession().setAttribute(Constants.CUST_TYPE, Constants.MEMBER_TYPE);
		getSession().setAttribute(Constants.USER_NAME, user_name);
		getSession().setAttribute(Constants.CUST_LOGO, member.getLogo_path());
		getSession().setAttribute(Constants.USER_ID, user_id);
		// NEW发送邮件用
		getSession().setAttribute(Constants.USER_ID_NEW, user_id);
		getSession().setAttribute(Constants.USER_NAME_NEW, user_name);
		
		// 会员类型: 0:商家 1:买家
		getSession().setAttribute(Constants.MEM_TYPE, mem_type);
		getSession().setAttribute(Constants.LEVEL_CODE,member.getBuy_level() );
		// 会员用户类型 1：管理员 2：子账户
		getSession().setAttribute(Constants.USER_TYPE, user_type);
		getSession().setAttribute(Constants.ROLE_ID, role_code);
		// syscode sys:运营商 b2c:会员
		getSession().setAttribute("syscode", "b2c");
		// 会员的菜单权限和操作权限
		getSession().setAttribute("menu_right", menu_right);
		// 插入登录日志记录
		insertLogs(login_txt, cust_id, user_id, user_name);
		// 获取购物车商品个数，并插入cookie
		// getCartgoodsNum(cust_id);
		// 返回跳转地址
		if (!loc.equals("")) {
			getResponse().sendRedirect(loc);
			return null;
		}
		Map delmsgcheckMap = new HashMap();
		delmsgcheckMap.put("cp_phone", user_name);
		delmsgcheckMap.put("cp_type", "0");
		msgcheckList = this.msgcheckService.getList(delmsgcheckMap);
		if (msgcheckList != null && msgcheckList.size() >= 0) {
			this.msgcheckService.deleteMsgcheck(delmsgcheckMap);
		}
		// 记住用户名
		savecokes(user_name);
		if(login_state.equals("1")) {
			// 定向会员中心
			getResponse().sendRedirect("/webappmembercenter.html");
		}else {
			// 首页
			getResponse().sendRedirect("/mindex.html");
		}
		
		return goUrl("mbLogin");
	}
    
	
	
	
	/**
	 * @Method Description :登陆成功跳转到触屏版网站会员中心首页
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
	 * @return
	 * @throws Exception
	 */
	public String webappMemberCenter()throws Exception{
		
		if(ValidateUtil.isRequired(this.session_cust_id)){
			return webappLogin();
		}
		
		//获取memberuser实体
		memberuser = this.memberuserService.getPKByCustID(this.session_cust_id);
		member = this.memberService.get(this.session_cust_id);
		
		Map waitPayMap=new HashMap();
		waitPayMap.put("order_state", "1");
		waitPayMap.put("order_type","1,2");
		waitPayMap.put("buy_cust_id",session_cust_id);
		waitPayNunm = this.goodsorderService.getCount(waitPayMap);
		//待发货
		Map waitDeliveryMap=new HashMap();
		waitDeliveryMap.put("order_state", "2");
		waitDeliveryMap.put("order_type","1,2");
		waitDeliveryMap.put("buy_cust_id",session_cust_id);
		waitDeliveryNum = this.goodsorderService.getCount(waitDeliveryMap);
		//待收货
		Map waitReceiptMap=new HashMap();
		waitReceiptMap.put("order_state", "3");
		waitReceiptMap.put("order_type","1,2");
		waitReceiptMap.put("buy_cust_id",session_cust_id);
		waitReceiptNum = this.goodsorderService.getCount(waitReceiptMap);
		//待评价
		Map waitEvaluationMap = new HashMap();
		waitEvaluationMap.put("order_state", "7");
		waitEvaluationMap.put("order_type","1,2");
		waitEvaluationMap.put("buy_cust_id",session_cust_id);
		// 根据页面提交的条件找出信息总数
		waitEvaluationNum = this.goodsorderService.getCount(waitEvaluationMap);
		// 获取会员优选商品
		Map recomMap = new HashMap();
		recomMap.put("is_del", "1");
		recomMap.put("is_up", "0");
		recomMap.put("lab", "4");//推荐
		recomMap.put("start", 0);
		recomMap.put("limit", 6);
		recomList = this.goodsService.getWebList(recomMap);
		//获取消息条数
		Map pageMap = new HashMap();
		//0：表示逻辑删  1：没有删除  2：物理删除
		pageMap.put("is_get_del", "1");
		//显示未读消息 0已读 1未读
		pageMap.put("is_read", "1");
		//会员显示自己的
		pageMap.put("cust_id", this.session_cust_id);
		//根据页面提交的条件找出信息总数
		total_news=this.receiveboxService.getCount(pageMap);
		return goUrl("mbappMember");
	}
	
	
	/**
	 * 方法描述：触屏版商城用户退出
	 * 
	 * @return
	 * @throws Exception
	 */
	public void webappExit() throws Exception {
		getSession().invalidate();
		getResponse().sendRedirect(Constants.WEBAPP_MEMBER_SIGNIN);
	}
	
	/**
	 * @Method Description :跳转到触屏版网站账号管理页面
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
	 * @return
	 * @throws Exception
	 */
	public String webappAccount()throws Exception{
		if(ValidateUtil.isRequired(this.session_cust_id)){
			return webappLogin();
		}
		memberuser = this.memberuserService.getPKByCustID(this.session_cust_id);
		Memberfund memberfund = this.memberfundService.get(this.session_cust_id);
		Memberinter memberinter = this.memberinterService.get(this.session_cust_id);
		total_balance = memberfund.getUse_num();
		total_integral = memberinter.getFund_num();
		member=this.memberService.get(this.session_cust_id);
		if(member==null){
			member=new Member();
		}
		if(member.getBuy_level()!=null){
			Malllevelset mls=this.mallvelsetService.get(member.getBuy_level());
			level_url=mls.getImg_url();
		}
		return goUrl("mbappAccount");
	}
	
	/**
	 * @Method Description :跳转到触屏版网站验证手机页面
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
	 * @return
	 * @throws Exception
	 */
    public String webappCheckMobile()throws Exception{
    	step_flag = "1";
    	return goUrl("mbappCheckMobile");
    }
	
    /**
	 * @Method Description :跳转到触屏版网站验证手机页面
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
	 * @return
	 * @throws Exception
	 */
	public String webappVerifyMobile() throws Exception{
		step_flag = "2";
		String cp_phone = msgcheck.getCp_phone();
		String cp_check = msgcheck.getCp_check();
		boolean ckfage=msgcheckService.checkMsgListToUse(cp_phone, cp_check);
		if(ckfage==true){
			memberuser =this.memberuserService.getPKByCustID(this.session_cust_id);
			memberuser.setIs_check_mobile("0");
			memberuser.setCellphone(cp_phone);
			memberuserService.update(memberuser);
		}
		return goUrl("mbappCheckMobile");
	}
	
	/**
	 * @Method Description :跳转到触屏版网站修改手机页面
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
	 * @return
	 * @throws Exception
	 */
	public String webappModifyPhoneFirst()throws Exception{
		if(ValidateUtil.isRequired(this.session_cust_id)){
			return webappLogin();
		}
		memberuser = this.memberuserService.getPKByCustID(this.session_cust_id);
		step_flag = "1";
		return goUrl("mbappModifyPhone");
	}
	
	/**
	 * @Method Description :跳转到触屏版网站修改手机页面
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
	 * @return
	 * @throws Exception
	 */
	public String webappModifyPhoneSecond()throws Exception{
		if(ValidateUtil.isRequired(this.session_cust_id)){
			return webappLogin();
		}
		memberuser = this.memberuserService.getPKByCustID(this.session_cust_id);
		step_flag = "2";
		return goUrl("mbappModifyPhone");
	}
	
	/**
	 * @Method Description :跳转到触屏版网站修改手机页面
	 * @author :QJY
	 * @date : Jan 6, 2015 9:52:14 AM
	 * @return
	 * @throws Exception
	 */
	public String webappModifyPhoneThird()throws Exception{
		if(ValidateUtil.isRequired(this.session_cust_id)){
			return webappLogin();
		}
		memberuser = this.memberuserService.getPKByCustID(this.session_cust_id);
		memberuser.setCellphone(new_mobile);
		memberuserService.update(memberuser);
		step_flag = "3";
		return goUrl("mbappModifyPhone");
	}
	
	/**
	 * 方法描述：跳转到找回密码页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String executepwd() throws Exception {
		super.saveRequestParameter();
		return goUrl("forgot_password");
	}

	/**
	 * @Method Description :跳转修改密码
	 * @author : HZX
	 * @date : Apr 14, 2015 4:33:15 PM
	 */
	public String goUpPass(){
		isok=false;
		return goUrl("mbappModifyPsw");
	}
	
	/**
	 * @author HZX
	 * @throws UnsupportedEncodingException
	 * @Method Description : 修改密码
	 * @date : Jan 14, 2014 5:28:29 PM
	 */

	public String updatePassword() throws UnsupportedEncodingException {

		// 查找密码
		Map pageMap = new HashMap();
		String opwd = newpasswd;
		oldpasswd = Md5.getMD5(oldpasswd.getBytes());
		pageMap.put("passwd", oldpasswd);
		pageMap.put("user_id", this.session_user_id);
		List memberuserList = this.memberuserService.getList(pageMap);
		if (memberuserList == null || memberuserList.size() == 0) {
			this.addFieldError("oldpasswd", "旧密码不正确");
			return goUrl("mbappModifyPsw");
		}
		if (ValidateUtil.isRequired(this.getNewpasswd())) {
			this.addFieldError("newpasswd", "新密码不能为空");
			return goUrl("mbappModifyPsw");
		}
		if (ValidateUtil.isRequired(this.getConfirmpasswd())) {
			this.addFieldError("confirmpasswd", "确认新密码不能为空");
			return goUrl("mbappModifyPsw");
		}
		if (ValidateUtil.isAlphasLimtLength(this.getNewpasswd())) {
			this.addFieldError("newpasswd", "密码只能由数字、字母或者下划线组成6-20位");
			return goUrl("mbappModifyPsw");
		}
		if (!this.getNewpasswd().equals(this.getConfirmpasswd())) {
			this.addFieldError("confirmpasswd", "确认密码与新密码不一致");
			return goUrl("mbappModifyPsw");
		}

		// //获取密码强度
		String pswstrong = memberuser.getPass_strength();
		memberuser = this.memberuserService.get(this.session_user_id);
		// 对密码加密
		if (newpasswd != null && !newpasswd.equals("")) {
			newpasswd = Md5.getMD5(newpasswd.getBytes());
		} else {
			newpasswd = null;
		}

		memberuser.setPasswd(newpasswd);
		memberuser.setPass_strength(pswstrong);

		this.memberuserService.update(memberuser);
		// 信息提醒
		MessageAltFuc mesalt = new MessageAltFuc();
		mesalt.messageAutoSend("9", memberuser.getCust_id().toString());
		this.addActionMessage("设置密码成功");
		this.setMessage(MESSAGE_VALUE);
		isok=true;
		getSession().invalidate();
		return goUrl("mbappModifyPsw");

	}
	/**
	 * @author : LHY
	 * @date : Feb 26, 2014 10:04:30 AM
	 * @Method Description :注册协议页面
	 */
	public String treaty() {
		return goUrl("treaty");
	}
	
	/**
	 * 方法描述：跳转到找回密码页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String pwd() throws Exception {
		r_psw_num="1";
		return goUrl("mbForgetPsw");
	}
	public String goCheckCode() throws Exception {
		r_psw_num="2";
		return goUrl("mbForgetPsw");
	}
	/**
	 * 返回获取验证码的页面重置页面
	 * 
	 * @return
	 */
	public String retPassCheck() {
		r_psw_num="3";
		return goUrl("mbForgetPsw");
	}
	/**
	 * 返回设置密码的页面成功页面
	 * 
	 * @return
	 */
	public String retuUpdatPas() {
		r_psw_num="4";
		return goUrl("mbForgetPsw");
	}
	/**
	 * 跳转到验证码验证页面
	 * 
	 * @throws Exception
	 */
	public String checkCode() throws Exception {
		if(commonCheckCode()){
			return pwd();
		}
		return goCheckCode();
	}

	/**
	 * @author : HZX
	 * @date : Feb 17, 2014 11:25:40 AM
	 * @Method Description :通用校验验证码和用户名
	 */
	public boolean commonCheckCode(){
		String sysrand = "";
		if (ValidateUtil.isRequired(username)) {
			this.addFieldError("username", "请输入手机号!");
			i_u="0";
			return true;
		} else if (username.equals("手机号")) {
			this.addFieldError("username", "请输入手机号!");
			i_u="0";
			return true;
		}
		
		// memberuser=this.memberuserService.getMemberuserByusername(username);
		// 判断是否存在该用户名，邮箱，手机号
		List loginuserList=this.memberService.is_exist_user(username);
		if(loginuserList!=null&&loginuserList.size() == 0){
			this.addFieldError("username", "手机号不存在或者手机号未验证!");
			i_u="0";
			return true;
		}
		// 如果loginuserList不为空就通过map取值
		Iterator it = loginuserList.iterator();
		while (it.hasNext()) {
			Map map = (Map) it.next();
			phone = (String) map.get("cellphone");
			username=(String) map.get("user_name");
		}
		i_u="1";
		i_b="1";
		return false;
	}

	/**
	 * 判断验证码是否正确，跳转到密码重置页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String passReset() throws Exception {
		if (ValidateUtil.isRequired(checkcode)) {
			this.addFieldError("checkcode", "请输入验证码！");
			return goCheckCode();
		}
		boolean ckfage=msgcheckService.checkMsgListToUse(phone, checkcode);
		if(ckfage==false){
			cc="0";
			this.addFieldError("checkcode", "验证码过期或错误，请重获取验证码！");
			return goCheckCode();
		}else {
			cc="1";
		}
		return retPassCheck();
	}




	/**
	 * 修改密码成功
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String updatePass() throws UnsupportedEncodingException {
		if(commonCheckPass()){
			return retPassCheck();
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
				return retPassCheck();
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
		//mesalt.messageAutoSend("9",memberuser.getCust_id(),null,"0",passwd);
		getSession().invalidate();
		return  retuUpdatPas();
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
		
		p="1";
		rp="1";
		return false;
	}
	
	/**
	 * @author QJY
	 * 
	 * @return 在线充值
	 * @throws Exception
	 */
    public String memberRecharge()throws Exception{
    	if(ValidateUtil.isRequired(this.session_user_id)){
			return webappLogin();
		}else{
			servicetel= SysconfigFuc.getSysValue("cfg_phone");
			memberuser = this.memberuserService.get(this.session_user_id);
			
			paymentList=this.paymentService.getList(new HashMap());
			return goUrl("mbTopup");
		}
    }
    
    
    public void recardkey() throws Exception{
    	HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String cardkey=this.getRequest().getParameter("cardskey");
		if(cardskey!=null&&!"".equals(cardskey)){
			rechargeablecards = rechargeablecardsService.getCardkey(cardskey);
			if(rechargeablecards==null){
				//卡号不存在
				out.write("0");
			}else if(rechargeablecards!=null&&"1".equals(rechargeablecards.getCardsstate())){
				//卡号已经充值过
				out.write("1");
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
					//修改充值卡状态
					rechargeablecardsService.recharge(rechargeablecards,this.session_cust_id,this.session_user_id);
					out.write("2");
				}
				
			}
		}else{
			//系统异常
			out.write("4");
		}
	}
    
    
	/**
	 * @author QJY
	 * 
	 * @return 充值卡充值
	 * @throws Exception
	 */
    public String membercardRecharge()throws Exception{
    	if(ValidateUtil.isRequired(this.session_user_id)){
			return webappLogin();
		}else{
			servicetel= SysconfigFuc.getSysValue("cfg_phone");
			memberuser = this.memberuserService.get(this.session_user_id);
			//设置开通的
			paymentList=this.paymentService.getList(new HashMap());
			return goUrl("mbcardTopup");
		}
    }
	
    /**
     * 
     * @throws Exception
     */
    public void createFundRecharge()throws Exception{
    	
    	PrintWriter out = getResponse().getWriter();
		getRequest().setCharacterEncoding("UTF-8");
		getResponse().setCharacterEncoding("UTF-8");
		Fundrecharge fundrecharge=new Fundrecharge();
    	if(getRequest().getParameter("recharge_amount")!=null && !"".equals(getRequest().getParameter("recharge_amount"))){
    		fundrecharge.setFund_num(Double.valueOf(getRequest().getParameter("recharge_amount")));
    	}
		fundrecharge.setCust_id(this.session_cust_id);
		fundrecharge.setUser_id(this.session_user_id);
		
		//插入十位数的随机数订单号
		String order_id = DateUtil.getOrderNum();
		fundrecharge.setOrder_id(order_id);
		String is_chongzhi=SysconfigFuc.getSysValue("cfg_IsAuditChonzhi");
		if(is_chongzhi.equals("0")){
			//未审核
			fundrecharge.setOrder_state("0");
		}else {
			//已审核
			fundrecharge.setOrder_state("1");
		}
		fundrecharge.setRecharge_state("0");//待充值
		fundrechargeService.insert(fundrecharge);
		out.write(order_id);
		
    }
    
    
    /**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	
	public String mbGoldCoins() throws Exception {
		//绑定下拉列表
		HashMap map=new HashMap();
		map.put("enabled", "0");
		paymentList=this.paymentService.getList(map);
		//页面搜索提交的参数
		Map pageMap = new HashMap();
		
		pageMap.put("cust_id", this.session_cust_id);
		
		//根据页面提交的条件找出信息总数
		int count=this.fundrechargeService.getCount(pageMap);
		//分页插件
		pageMap = super.webAppPageTool(count,pageMap);
		fundrechargeList = this.fundrechargeService.getList(pageMap);
		return goUrl("mbGoldCoins");
	}

	/**
	 * 方法描述：删除会员余额充值记录信息
	 * @return
	 * @throws Exception
	 */
	public String webappdelete() throws Exception {
		boolean flag = this.fundrechargeService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除余额充值记录成功");
		}else{
			this.addActionMessage("删除余额充值记录失败");
		}

		return mbGoldCoins();
	}
	/**
	 * @author Administrator QJY
	 * @method 上传头像页面
	 * @date 2015-09-23
	 * @return
	 * @throws Exception
	 */
	public String updateMemberIcon()throws Exception{
		memberuser = this.memberuserService.getPKByCustID(this.session_cust_id);
		return goUrl("mbMemberIcon");
	}
	
	
	/**
	 * @author Administrator QJY
	 * @method 上传头像
	 * @date 2015-09-23
	 * @return
	 * @throws Exception
	 */
	public String uploadIcon()throws Exception{
		if(ValidateUtil.isRequired(this.session_cust_id)){
			return goUrl("mbLogin");
		}else{
			if(!ValidateUtil.isRequired(member_icon)){
				member = this.memberService.get(this.session_cust_id);
				member.setLogo_path(member_icon);
				this.memberService.update(member);
			}
			
		}
		return webappMemberCenter();
	}
	
	
	
	
	/**
	 * 方法描述：判断手机端自动登录
	 * 
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void webappUserAutoLogin() throws Exception {
		
		// 定义request对象
		HttpServletRequest request = getRequest();
		PrintWriter out = getResponse().getWriter();
		request.setCharacterEncoding("UTF-8");
		String userReturn="0";//默认表示自动登录
		// 用户名
		String autoUserName = "";
		if (request.getParameter("user_name") != null && !"".equals(request.getParameter("user_name"))) {
			autoUserName = request.getParameter("user_name");
		}else {
			userReturn="1";
		}
		// 密码
		String autoPasswd = "";
		if (request.getParameter("passwd") != null && !"".equals(request.getParameter("passwd"))) {
			autoPasswd = request.getParameter("passwd");
		}else {
			userReturn="1";
		}
		if (request.getParameter("getisautologin") == null || "".equals(request.getParameter("getisautologin"))||!"yes".equals(request.getParameter("getisautologin"))) {
			userReturn="1";
		}
		
		if(!"1".equals(userReturn)&&ValidateUtil.isRequired(session_user_id)){
			userReturn=autoAppLogin(autoUserName,autoPasswd);
		}
		out.write(userReturn);
	}
	/**
	 * @Method Description :自动登录
	 * @author: 胡惜坤
	 * @date : Jan 25, 2016 5:43:41 PM
	 * @param 
	 * @return return_type
	 */

	private String autoAppLogin(String autoUserName,String autoPasswd){
		// 判断是否存在该用户名，邮箱，手机号
		List loginuserList = this.memberService.is_exist_user(autoUserName);
		if (loginuserList != null && loginuserList.size() == 0) {
			return "1";
		}
		if (loginuserList != null && loginuserList.size() > 0) {
			// 判断cust_id是否等于0,0代表运营商
			Map map = (HashMap) loginuserList.get(0);
			if (map != null && map.get("cust_id") != null) {
				String cust_id = map.get("cust_id").toString();
				if (cust_id.equals("0")) {
					return "1";
				}
			}
		}
		// 通过用户名与密码查找用户
		autoPasswd = Md5.getMD5(autoPasswd.getBytes());
		Map pageMap = new HashMap();
		Map uMap = (Map) loginuserList.get(0);
		autoUserName = uMap.get("user_name").toString();
		pageMap.put("user_name", autoUserName);
		pageMap.put("passwd", autoPasswd);
		loginuserList = this.memberuserService.getList(pageMap);
		// 如果密码错误，则提示
		if (loginuserList == null || loginuserList.size() == 0) {
			// 密码验证错误后，应该插入的数据
			return "1";
		}
		// 会员属性
		String user_id = "", cust_id = "", role_code = "", is_active = "", info_state = "";
		// mem_type 0：商家 1：个人
		String mem_type = "";
		// user_type 1：会员管理员 2：会员子账号
		String user_type = "";
		if (loginuserList != null && loginuserList.size() > 0) {
			Map loginMap = (HashMap) loginuserList.get(0);
			if (loginMap.get("user_id") != null) {
				user_id = loginMap.get("user_id").toString();
			}
			if (loginMap.get("cust_id") != null) {
				cust_id = loginMap.get("cust_id").toString();
			}
			if (loginMap.get("user_type") != null) {
				user_type = loginMap.get("user_type").toString();
			}
			if (loginMap.get("is_active") != null) {
				is_active = loginMap.get("is_active").toString();
			}
			if (loginMap.get("mem_type") != null) {
				mem_type = loginMap.get("mem_type").toString();
			}
			if (loginMap.get("info_state") != null) {
				info_state = loginMap.get("info_state").toString();
			}
			if (loginMap.get("role_code") != null) {
				role_code = loginMap.get("role_code").toString();
			}
			// 判断用户的cust_id是否等于0，0代表运营商
			if (cust_id.equals("0")) {
				return "1";
			}
			if (!info_state.equals("1")) {
				return "1";
			}
		}

		// 系统设置是否允许未审核会员登录，如果系统参数等于1却会员的状态等于0就是不能登录
		if (cfg_mb_isaudit.equals("1")) {
			if (info_state.equals("0")) {
				return "1";
			}
		}
		//清除所有session值
		getSession().removeAttribute("menu_right");
		getSession().removeAttribute("oper_right");
		member = this.memberService.get(cust_id);
		StringBuffer sb = this.memberuserService.getMentRight(member,role_code, mem_type, user_type);
		// 获取上次登录时间或是第一次登录时间
		get_Last_Login_Time(user_id);
		// B2C菜单串
		String menu_right = sb.toString();
		// 将值存入session中
		getSession().setAttribute(Constants.CUST_ID, cust_id);
		getSession().setAttribute(Constants.CUST_NAME, member.getCust_name());
		getSession().setAttribute(Constants.CUST_TYPE, Constants.MEMBER_TYPE);
		getSession().setAttribute(Constants.USER_NAME, user_name);
		getSession().setAttribute(Constants.CUST_LOGO, member.getLogo_path());
		getSession().setAttribute(Constants.USER_ID, user_id);
		// NEW发送邮件用
		getSession().setAttribute(Constants.USER_ID_NEW, user_id);
		getSession().setAttribute(Constants.USER_NAME_NEW, user_name);
		
		// 会员类型: 0:商家 1:买家
		getSession().setAttribute(Constants.MEM_TYPE, mem_type);
		getSession().setAttribute(Constants.LEVEL_CODE,member.getBuy_level() );
		// 会员用户类型 1：管理员 2：子账户
		getSession().setAttribute(Constants.USER_TYPE, user_type);
		getSession().setAttribute(Constants.ROLE_ID, role_code);
		// syscode sys:运营商 b2c:会员
		getSession().setAttribute("syscode", "b2c");
		// 会员的菜单权限和操作权限
		getSession().setAttribute("menu_right", menu_right);
		// 插入登录日志记录
		insertLogs(login_txt, cust_id, user_id, user_name);
		return user_name;
	}
	
	
	
	
	
	
	public Memberuser getMemberuser() {
		return memberuser;
	}

	public void setMemberuser(Memberuser memberuser) {
		this.memberuser = memberuser;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Malllevelset getMalllevelset() {
		return malllevelset;
	}

	public void setMalllevelset(Malllevelset malllevelset) {
		this.malllevelset = malllevelset;
	}

	public Memrole getMemrole() {
		return memrole;
	}

	public void setMemrole(Memrole memrole) {
		this.memrole = memrole;
	}

	public Msgcheck getMsgcheck() {
		return msgcheck;
	}

	public void setMsgcheck(Msgcheck msgcheck) {
		this.msgcheck = msgcheck;
	}

	public List getMsgcheckList() {
		return msgcheckList;
	}

	public void setMsgcheckList(List msgcheckList) {
		this.msgcheckList = msgcheckList;
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

	public String getArea_number() {
		return area_number;
	}

	public void setArea_number(String area_number) {
		this.area_number = area_number;
	}

}
