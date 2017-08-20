/*
  
 
 * Package:com.rbt.webaction
 * FileName: WebmemberuserAction.java 
 */
package com.rbt.webaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;
import com.rbt.common.Constants;
import com.rbt.common.Md5;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.MemberuserFuc;
import com.rbt.function.PageTipFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Advinfo;
import com.rbt.model.Malllevelset;
import com.rbt.model.Member;
import com.rbt.model.Membercredit;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.model.Memberuser;
import com.rbt.model.Memrole;
import com.rbt.model.Msgcheck;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.IMalllevelsetService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMembercreditService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberinterService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IMemroleService;
import com.rbt.service.IMsgcheckService;
import com.rbt.service.IReceiveboxService;
import com.rbt.service.IRoleService;
import com.rbt.service.IShopconfigService;
import com.rbt.service.ISysmenuService;

/**
 * @function 功能 找回用户名和密码模块的操作action类
 * @author 创建人 CYC
 * @date 创建日期 Sep 17, 2014 15:50:13 PM
 */
@Controller
public class WebmemberuserAction extends WebbaseAction {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 5535005240249968461L;
	/** *****************实体层******************* */
	public Memberuser memberuser;
	public Member member;
	public Memrole memrole;
	public Malllevelset malllevelset;

	/** *****************业务层接口*************** */
	@Autowired
	public IMemberuserService memberuserService;
	@Autowired
	public IMemberinterService memberinterService;
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
	private IShopconfigService shopconfigService;
	@Autowired
	private IMembercreditService membercreditService;
	@Autowired
	private IMemberfundService memberfundService;
	@Autowired
	private IReceiveboxService receiveboxService;

	/** *******************集合******************* */
	public List emailtemplateList;// 会员邮件发送模板信息
	public List malllevelsetsellList;
	public List malllevelsetbuyList;
	public List msgcheckList;
	public List areaList;

	/** *******************字段******************* */

	public String username;// 用户名
	public String commentrand;// 验证码
	public Advinfo advinfo;// 广告对象
	private static final String TEMP_CODE = "forget_passwd";// 密码模板代码
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
	public String open_id;//用户id
	public String nickName;//呢称
	public String weixinName;//微信呢称
	public String state;//参数
	public String qq_pic;//qq头像
	public String come_here;//来至
	public String regist_type;//注册类型
	public String membernum;//地区
	public String regist_phoen;//注册手机
	public String serviceterms;//注册条款

	/**
	 * @author : LJQ
	 * @date : Jan 6, 2014 9:52:14 AM
	 * @Method Description :跳转到登录页面
	 */
	public String login() throws Exception {
		//生成随机数并保存在session中
		state = Md5.getMD5(RandomStrUtil.getRand("11").getBytes());
		getSession().setAttribute("state", state);
		advinfo = this.advinfoService.getImg("11");
		if (advinfo == null) {
			return goUrl("login");
		}
		return goUrl("login");
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
	public String loginUser() throws Exception {
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
		String mem_cust_id = "";
		if (memberuser != null && memberuser.getCust_id() != null) {
			mem_cust_id = memberuser.getCust_id();
		}
		// 密码
		String passwd = "";
		if (memberuser != null && memberuser.getPasswd() != null) {
			passwd = memberuser.getPasswd();
		}
		// 随机验证码
		String sysrand = "";
		// 获取系统生成的验证码
		if (getSession().getAttribute("sysrand") != null) {
			sysrand = getSession().getAttribute("sysrand").toString();
		}
		// 判断用户名是否为空
		if (ValidateUtil.isRequired(user_name)) {
			this.addFieldError("memberuser.user_name", "请输入用户名/邮箱/手机号!");
			i_username = "1";
			return login();
		}
		// 判断密码是否为空
		if (ValidateUtil.isRequired(passwd)) {
			this.addFieldError("memberuser.passwd", "请输入密码!");
			i_password = "1";
			return login();
		}
		
		if(ValidateUtil.isRequired(open_id)) {
			// 验证验证码不能为空
			if (ValidateUtil.isRequired(commentrand)) {
				this.addFieldError("commentrand", "请输入验证码");
				i_c = "1";
				return login();
			}
			// 验证验证码是否正确
			if (!sysrand.equals(commentrand)) {
				this.addFieldError("commentrand", "验证码输入不正确");
				i_c = "1";
				return login();
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
			return login();
		}
		// 判断是否存在该用户名，邮箱，手机号
		List loginuserList = this.memberService.is_exist_user(user_name);
		if (loginuserList != null && loginuserList.size() == 0) {
			this.addFieldError("memberuser.user_name",
					"用户名/邮箱/手机号不存在或者手机邮箱未验证!");
			i_username = "1";
			return login();
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
		Map pageMap = new HashMap();
		Map uMap = (Map) loginuserList.get(0);
		user_name = uMap.get("user_name").toString();
	    if(ValidateUtil.isRequired(open_id)) {
			  passwd = Md5.getMD5(passwd.getBytes());
			  pageMap.put("user_name", user_name);
		}else{
			 pageMap.put("user_name", memberuser.getUser_name());
		}
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

			return login();
		}
		getSession().removeAttribute("menu_right");
		getSession().removeAttribute("oper_right");
		getSession().removeAttribute("first_menu_id");
		getSession().setAttribute("first_menu_id","");
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
				return login();
			}
		}

		// 系统设置是否允许未审核会员登录，如果系统参数等于1却会员的状态等于0就是不能登录
		if (cfg_mb_isaudit.equals("1")) {
			if (info_state.equals("0")) {
				this.addFieldError("memberuser.user_name", "用户未审核");
				i_username = "2";
				return goUrl("login");
			}
		}
		member = this.memberService.get(cust_id);
		StringBuffer sb = this.memberuserService.getMentRight(member,
				role_code, mem_type, user_type);
		// 获取店铺随机数
		Map map = new HashMap();
		map.put("cust_id", cust_id);
		// map.put("user_id",user_id); 因为现在user_id 不在是用编号是用user_name了，所以这个地方注释掉，
		List shopList = shopconfigService.getList(map);
		String radom_no = "";
		if (shopList != null && shopList.size() > 0) {
			Map shopMap = (HashMap) shopList.get(0);
			if (shopMap.get("radom_no") != null)
				radom_no = shopMap.get("radom_no").toString();
		}
		//清除所有session值
		getSession().removeAttribute("menu_right");
		getSession().removeAttribute("oper_right");
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
		getSession().setAttribute(Constants.RADOM_NO, radom_no);
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
		// 定向会员中心
		String retIndex = MemberuserFuc.logoinIndex(menu_right);
		getResponse().sendRedirect(retIndex);
		return goUrl("login");
	}
    
	
	/**
	 * qq重定向
	 * @throws QQConnectException
	 */
	public void  qqlogin() throws QQConnectException {
		try {
			getResponse().sendRedirect(new Oauth().getAuthorizeURL(getRequest()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 微信跳转
	 * @throws Exception
	 */
	public String loginweixin() throws Exception {
		
		String code = getSession().getAttribute("code").toString();
		String access_token ="";
	    //获取open_id 和 access_token
		if(!ValidateUtil.isRequired(code)) {
			Map map = getMap(code);
		 	if(map.get("open_id") != null && map.get("access_token") != null) {
		       open_id = map.get("open_id").toString();
				//来自
				come_here = "微信";
				//注册类型
				regist_type = "1";
		       System.out.println("open_id:" + open_id);
		       access_token = map.get("access_token").toString();
		       //获取微信用户呢称
		        getWeixinName(open_id, access_token);
		        getSession().setAttribute("weixinName",weixinName);
		        //用户创建
				if(commonLogin()){
					//用户登录
					loginUser();
				}else {
					//合作登录注册
					return goUrl("registerOther");
				}
			}
		}
		return goUrl("login");
	}
	
	
	/**
	 * 通过code获取微信用户openid
	 * @param code
	 * @return
	 * @throws Exception
	 */
	private Map getMap(String code) throws Exception {
	    //拼写url地址
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxee8fbbeaa95e7dd3&secret=00d8dec0f8cc3659d4914cada8be27d9&code="+code+"&grant_type=authorization_code";
		//获取json数据
		String json=JsonDate(url);
        //读取json数据
        JSONObject jsonObj = JSONObject.fromObject(json);
        //获取微信用户openid
        String open_id = jsonObj.getString("openid");
        //获取access_token
        String access_token = jsonObj.getString("access_token");
        Map map = new HashMap();
        map.put("open_id", open_id);
        map.put("access_token", access_token);
        return map;   
	}
	
	
	/**
	 * 获取微信用户呢称
	 * @param open_id
	 * @param access_token
	 * @return
	 */
	public void getWeixinName(String open_id, String access_token) throws Exception {
		//拼写url地址
		String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+open_id;
		//获取json数据
		String json=JsonDate(url);
        //读取json数据
        JSONObject jsonObj = JSONObject.fromObject(json);
        //获取微信用户呢称
         weixinName=jsonObj.getString("nickname");
         nickName = weixinName;
         qq_pic=jsonObj.getString("headimgurl");
	}
	
	/**
	 * 通过url获取json数据
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String JsonDate(String url) throws Exception {
		StringBuilder json = new StringBuilder();  
        try {  
            URL urlObject = new URL(url);  
            URLConnection uc = urlObject.openConnection();  
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));  
            String inputLine = null;  
            while ( (inputLine = in.readLine()) != null) {  
                json.append(inputLine);  
            }  
            in.close();  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
        return json.toString();
	}
	
	
	/**
	 * qq登录
	 * @return
	 * @throws Exception
	 */
	public String loginqq() throws Exception {
		// 用户授权ID
		open_id = getSession().getAttribute("openid").toString();
		// 用户呢称
		nickName = getSession().getAttribute("nickName").toString();
		// 用户头像
		qq_pic = getSession().getAttribute("qqPic").toString();
		//来自
		come_here = "QQ";
		//注册类型
		regist_type = "0";
		//用户创建
		if(commonLogin()){	
			//用户登录
			loginUser();
		}
		//合作登录注册
		return goUrl("registerOther"); 		
	}
	
   /**
    * qq、微信等用户创建
    * @param open_id
    */
	public boolean commonLogin() {
		//定义返回值
		boolean flag = true;
		
		//获取用户信息
		Map map = new HashMap();
		map.put("open_id", open_id);
		List list = memberuserService.getList(map);
		
	    memberuser = new Memberuser();
		if(list != null && list.size() > 0) {
		   Map loginMap = (HashMap) list.get(0);
		   memberuser.setUser_name(loginMap.get("user_name").toString());
		   memberuser.setPasswd(loginMap.get("passwd").toString());
		}else {
			if (ValidateUtil.isRequired(membernum)) {
				this.addFieldError("areaError", "请选择地区");
				flag = false;
			}
			// 注册条款
			serviceterms = PageTipFuc.getPageCon("register_agreement_page");
			Map areamap = new HashMap();
			areamap.put("area_level", "2");
		    areaList = areaService.getList(areamap);
			flag = false;
		}
		
		return flag;
	}
	
	/**
	 * 合作登录注册
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String regist() throws Exception {
		//注册新用户
		String user_name = "";
		if(regist_type.equals("0")){
			 user_name ="QQ_" +open_id.substring(25, 32);
		}else{
			 user_name ="WX_" +open_id.substring(21, 28);
		}
		
		member = new Member();
 		member.setMem_type("1");// 会员类型 1:个人会员
		member.setCust_name(user_name);
		member.setLogo_path(SysconfigFuc.getSysValue("cfg_memberlogo"));
		//member.setArea_attr(area_attr);
		// 设置会员注册后的买家级别
		member.setBuy_level(SysconfigFuc.getSysValue("cfg_buy_member"));
		member.setInfo_state("1");// 1:审核通过
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
		// 初始化会员账号对象
		Memberuser user = new Memberuser();
		user.setCust_id(cust_id);
		user.setSex("2");
		user.setUser_type("1");
		user.setUser_name(user_name);
		String passwd = "111111";
		passwd = Md5.getMD5(passwd.getBytes());
		user.setPasswd(passwd);
		user.setIs_check_mobile("1");
		user.setIs_check_email("1");
		user.setOpen_id(open_id);
		user.setCellphone(regist_phoen);
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
		//初始化会员信誉对象
		Membercredit membercredit=new Membercredit();
		membercredit.setCust_id(cust_id);
		membercredit.setUser_id(user_id);
		membercredit.setBuy_num("0");
		membercredit.setSell_num("0");
		this.membercreditService.insert(membercredit);
		//初始化会员积分
		Memberinter memberinter = new Memberinter();
		memberinter.setCust_id(cust_id);
		memberinter.setFund_num("0");
		this.memberinterService.insert(memberinter);
		Memberfund  memberfund = new Memberfund();
		memberfund.setCust_id(cust_id);
		memberfund.setFreeze_num("0");
		memberfund.setFund_num("0");
		memberfund.setUse_num("0");
		this.memberfundService.insert(memberfund);
		memberuser = new Memberuser();
	    memberuser.setUser_name(user_name);
	    memberuser.setPasswd(passwd);
	     //用户登录
		loginUser();
		//合作登录注册
		return goUrl("login"); 		
	}
	
	
	/**
	 * @author : LJQ
	 * @date : Jan 6, 2014 9:52:00 AM
	 * @Method Description :商城会员登出
	 */
	public String exit() throws Exception {
		getSession().invalidate();
		return Constants.MEMBER_SIGNIN;
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
	 * @author : LHY
	 * @date : Feb 26, 2014 10:04:30 AM
	 * @Method Description :注册协议页面
	 */
	public String treaty() {
		return goUrl("treaty");
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
	//获取未读信件数
	public void getMsgNum() throws Exception{
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String cust_id="0";
		if(null!=this.session_cust_id && !"".equals(this.session_cust_id)){
			cust_id = this.session_cust_id;
		}
		//获取未读信息
		Map infoMap = new HashMap();
		//0：表示逻辑删  1：没有删除  2：物理删除
		infoMap.put("is_get_del", "1");
		//会员显示自己的
		infoMap.put("cust_id", cust_id);
    	infoMap .put("is_read", "1");
		//根据页面提交的条件找出信息总数
    	String count = "0";
    	Object o = this.receiveboxService.getCount(infoMap);
    	if(null!=o && !"".equals(o.toString())){
    		count=o.toString();
    	}
		out.print(count);
	}
}
