/*
 
 * Package:com.rbt.action
 * FileName: SysuserAction.java 
 */

package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import com.rbt.function.*;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.Md5;
import com.rbt.common.util.DbUtil;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.ToolsFuc;
import com.rbt.message.MailInter;
import com.rbt.model.Emailtemplate;
import com.rbt.model.Memberuser;
import com.rbt.model.Msgcheck;
import com.rbt.model.Organize;
import com.rbt.model.Sysuser;
import com.rbt.service.IBackupService;
import com.rbt.service.ICertificationService;
import com.rbt.service.IConsultationService;
import com.rbt.service.IEmailtemplateService;
import com.rbt.service.IFundtocashService;
import com.rbt.service.IGoodsevalService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.ILogsService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IMsgcheckService;
import com.rbt.service.IOrganizeService;
import com.rbt.service.IRefundappService;
import com.rbt.service.IRoleService;
import com.rbt.service.ISysuserService;

/**
 * @function 功能 系统管理员action类
 * @author 创建人 HXK
 * @date 创建日期 Jun 13, 2014
 */
@Controller
public class SysuserAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = -1223097111722934440L;
	/*******************实体层********************/
	private Memberuser memberuser;
	private Organize organize;
	private	Sysuser sysuser;
	
	/*******************业务层接口****************/
	@Autowired
	public ISysuserService sysuserService;
	@Autowired
	public IRoleService roleService;
	@Autowired
	public ILogsService logsService;
	@Autowired
	public IOrganizeService organizeService;
	@Autowired
	public IMemberuserService memberuserService;
	@Autowired
	public IBackupService backupService;
	@Autowired
	public IFundtocashService fundtocashService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public ICertificationService certificationService;
	@Autowired
	public IMsgcheckService msgcheckService;
	@Autowired
	public IEmailtemplateService emailtemplateService;
	@Autowired
	private IGoodsorderService goodsorderService;	
	@Autowired
	private IRefundappService refundappService;
	@Autowired
	private IConsultationService consultationService;
	@Autowired
	private IGoodsevalService goodsevalService;
	
	/*********************集合********************/
	public List userList;//系统用户
	public List roleList;//角色
	public List countlist;//未审核与今日新增条数
	public List todayList;
	public List loglist;//日志
	public List memList;//会员
	
	/*********************字段********************/
	
	public String user_name_s;//用户名
	public String nike_name_s;//匿名
	public String role_id_s;//角色
	public String adminStr;
	public int member_num;
	public String is_update;//是否更新
	public String install_date;//安装时间
	public String database_product_version;//数据库产品版本信息
	public String database_name;//数据库名称
	public String server_datetime;//服务器时间
	public String server_info;//服务器信息
	public String server_ip;//服务器IP
	public String OS_name;//操作系统名称
	public String OS_arch;//操作系统架构
	public String userrand;//登陆页面输入的验证码
	public String org_attr;//所属部门串
	public String org_hidden_value;//所属部门隐藏值
	public String up_id = SysconfigFuc.getSysValue("cfg_toporgid");//顶级部门ID
	public String oldusername;//原用户名
	public String login_time = "";//定义上次登陆时间字段
	public String login_IP = "";// 定义上次登陆IP字段
	public String remusername;
	public String opername;//操作人
	public String lastIp;//上一次登陆IP
	public String lastlogintime;//上一次登陆时间
	public String logintimes;//登陆次数
	public String login_txt ="管理员登录";
	public String info="0";//账号维护标识
	public String oldpasswd;//旧密码
	public String newpasswd;//新密码
	public String confirmpasswd;//确认密码
	public String grade;
	public String ids="";
	public int count=0;
	public String today="1";//今天
	public int todayOrder;//今日订单
	public int aOrder;//总订单
	public int sevelCount;	//待评价商品
	public int rCount;	//待处理退款
	public int coCount;//咨询待回复
	public int gehCount;//好评	
	public int gezCount;//中评
	public int gecCount;//差评
	public int goCount;//待发货
	public int acoCount;//总咨询
	public int bsorder=0;//保税订单
	public int yzorder=0;//直邮订单
	public int qxorder=0;//取消订单
	public int hszorder=0;//回收站订单
	

//	 会员 
	public int mtodayOrder;//今日订单
	public int maOrder;//总订单
	public int msevelCount;	//待评价商品
	public int mrCount;	//待处理退款
	public int mcoCount;//咨询待回复
	public int mgehCount;//好评	
	public int mgezCount;//中评
	public int mgecCount;//差评
	public int mgoCount;//待发货
	public int macoCount;//总咨询
	
	/**
	 * 方法描述：返回新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		Map map = new HashMap();
		map.put("user_id",this.session_user_id);
		map.put("is_system","0");
		roleList = this.roleService.getList(map);
		return goUrl(ADD);
	}

	/**
	 * 方法描述：保存所属部门的隐藏域
	 * 
	 * @return
	 * @throws Exception
	 */
	private void loadOrg() {
		// 保存所属部门的隐藏域
		if (org_attr != null) {
			if (org_hidden_value != null && !org_hidden_value.equals("")) {
				org_hidden_value = up_id + "," + org_attr.replace(" ", "").replace(",0", "");
			} else {
				org_hidden_value = up_id;
			}
			String org_id = org_attr.replace(" ", "");
			this.sysuser.setOrg_id(org_id);
			org_hidden_value=null;
		}
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 11:20:55 AM
	 * @Method Description : 添加，修改公共验证方法
	 */
	private void commonCheck(){
		// 保存所属部门的隐藏域
		loadOrg();
		// 通过用户名找出用户信息
		if (!ValidateUtil.isRequired(sysuser.getUser_name()) && existsTitle(sysuser.getUser_name(), "", "sysuser", "user_name")) {
			this.addFieldError("sysuser.user_name", "用户名已存在,请重新输入");
		}
		// 验证密码
		if (ValidateUtil.isRequired(sysuser.getPasswd())) {
			this.addFieldError("sysuser.passwd", "密码不能为空");
		}
		// 验证确认密码
		if (ValidateUtil.isRequired(confirmpasswd)) {
			this.addFieldError("confirmpasswd", "确认密码不能为空");
		}else if(!sysuser.getPasswd().equals(confirmpasswd)){
			this.addFieldError("confirmpasswd", "密码不一致");
		}
		if (!ValidateUtil.isRequired(sysuser.getPasswd()) && !ValidateUtil.isAlphasLimtLength(sysuser.getPasswd())) {
			String passwd = sysuser.getPasswd();
			passwd = Md5.getMD5(passwd.getBytes());
			sysuser.setPasswd(passwd);
		}
		//Map checkMap = new HashMap();
		//checkMap.put("user_name", sysuser.getUser_name());
		//checkMap.put("cust_id", "0");
		// 根据输入的系统管理员名字和cust_id等于0判断memberuser表里是否已经有存在，如果有存在就返回不做添加动作
		//List list = this.memberuserService.getList(checkMap);
		//if (list.size() > 0) {
		//	this.addFieldError("sysuser.user_name", "用户名已存在,请重新输入");
		//}
		sysuser.setUser_type("4");// 子账户
		sysuser.setLoginnum("0");
		// 字段验证
		super.commonValidateField(sysuser);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 11:11:30 AM
	 * @Method Description :新增管理员信息
	 */
	public String insert() throws Exception {
		commonCheck();
		grade=this.sysuserService.get(this.session_user_id).getGrade();
		sysuser.setGrade((Integer.parseInt(grade)+1)+"");
		sysuser.setSubjection(this.session_user_id);
		sysuser.setIs_del("0");
		if (super.ifvalidatepass) {
			return add();
		}
		this.sysuserService.insert(sysuser);
		this.addActionMessage("新增系统管理员信息成功");
		this.sysuser = null;
		return add();
	}
	/**
	 * @author : LJQ 
	 * @throws IOException 
	 * @date : Mar 21, 2014 10:33:14 AM
	 * @Method Description :更新缓存
	 */
	public void reload() throws IOException {
		HttpServletResponse response = getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (this.session_cust_id != null && this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			AreaFuc.areaMap = null;
			CategoryFuc.catMap = null;
			CommparaFuc.commparaMap = null;
			OrganizeFuc.orgMap = null;
			PageTipFuc.conMap = null;
			SysconfigFuc.sysMap = null;
			MalltemplateFuc.default_mall_template = null;
			out.println("1");
		}else {
			out.println("2");
		}
		
	}
	/**
	 * @function 功能 系统后台默认首页信息
	 * @author 创建人 QJY
	 * @date 创建日期 Aug 25, 2014 2:13:08 PM
	 */
	public String main() throws Exception {
		// 获取操作日志列表
		Map logMap = new HashMap();
		opername = this.session_user_name;
		logMap.put("user_name", opername);
		logMap.put("start", 0);
		logMap.put("limit", 5);
		loglist = logsService.getList(logMap);
		// 获取登录的次数
		logintimes =String.valueOf(MemberuserFuc.getLoginTimes(this.session_user_id,login_txt));
		// 获取会员上一次登陆时间
		HashMap  lastLogMap = (HashMap) MemberuserFuc.get_Last_Sysdata(this.session_user_id,login_txt);
		if (lastLogMap.get("in_date") != null) {
			lastlogintime = lastLogMap.get("in_date").toString();
		}
		if (lastLogMap.get("ip") != null) {
			lastIp = lastLogMap.get("ip").toString();
		}
		// 获取服务器IP地址
		InetAddress localhost = InetAddress.getLocalHost();
		server_ip = localhost.getHostAddress();
		// 获取服务器信息
		server_info = ServletActionContext.getServletContext().getServerInfo();
		// 获取安装时间
		Date date = new Date();
		DateFormat formatter = DateFormat.getDateTimeInstance(); // 得到一个指定时区(中国是东8区的)的
		TimeZone timezone = TimeZone.getTimeZone("GMT+08:00 "); // 实例化时区对象
		formatter.setTimeZone(timezone);
		install_date = formatter.format(date);
		DbUtil dbutil = new DbUtil();
		// 获取数据库系统版本信息
		database_product_version = this.backupService.getDatabaseversion();
		// 获取数据库系统名称
		database_name = dbutil.getDbName();
		// 获取服务器时间
		server_datetime = new java.sql.Date(System.currentTimeMillis()).toString();
		// 获取操作系统相关参数
		Properties props = System.getProperties(); // 系统属性
		OS_name = props.getProperty("os.name");// 操作系统名称
		OS_arch = props.getProperty("os.arch");// 操作系统架构
		
		
		
		// 交易信息
		//今天订单
		Map orMap=new HashMap();
		orMap.put("today", this.today);
		orMap.put("sell_cust_id","0");
		todayOrder = this.goodsorderService.getCount(orMap);
		
		//总订单
		Map aorMap=new HashMap();
		aorMap.put("sell_cust_id","0");
		aOrder = this.goodsorderService.getCount(aorMap);
		
		//待评价商品
		Map sevelMap=new HashMap();
		sevelMap.put("order_state","7");
		sevelMap.put("sell_cust_id","0");
		sevelCount = this.goodsorderService.getCount(sevelMap);
		
		//待发货
		Map goMap=new HashMap();
		goMap.put("order_state", "2");
		goMap.put("sell_cust_id","0");
	    goCount = this.goodsorderService.getCount(goMap);
	    
		//待处理退款
		Map rMap=new HashMap();
		rMap.put("info_state", "2");
		rMap.put("sell_cust_id","0");
		rCount = this.refundappService.getCount(rMap);
		
		//待回复咨询
		Map coMap=new HashMap();
		coMap.put("re_date","0");
		coMap.put("cust_id","0");
		coCount=this.consultationService.getCount(coMap);
		
		//总咨询
		Map acoMap=new HashMap();
		acoMap.put("cust_id","0");
		acoCount=this.consultationService.getCount(acoMap);
		
		//好评
		Map gehMap=new HashMap();
		gehMap.put("g_eval", "1");
		gehCount=this.goodsevalService.getCount(gehMap);
		
		//中评
		Map gezMap=new HashMap();
		gezMap.put("g_eval", "0");
		gezCount=this.goodsevalService.getCount(gezMap);
		
		//差评
		Map gecMap=new HashMap();
		gecMap.put("g_eval", "-1");
		gecCount=this.goodsevalService.getCount(gecMap);
		
		//保税订单
		Map bsorderMap=new HashMap();
		bsorderMap.put("sell_cust_id","0");
		bsorderMap.put("is_sea", "0");
		bsorderMap.put("is_vip", "0");
		bsorderMap.put("is_del", "1");
		bsorder = this.goodsorderService.getCount(bsorderMap);
		//直邮订单
		Map yzorderMap=new HashMap();
		yzorderMap.put("sell_cust_id","0");
		yzorderMap.put("is_sea", "1");
		yzorderMap.put("is_vip", "0");
		yzorderMap.put("is_del", "1");
		yzorder = this.goodsorderService.getCount(yzorderMap);
		return SUCCESS;
	}


	/**
	 * 方法描述：运营商后台重构未审核与今日新增条数LIST
	 * 
	 * @return
	 * @throws Exception
	 */
	private void createList(List list, List modlist) {
		countlist = new ArrayList();
		countlist=this.sysuserService.getcountList(list,modlist,this.session_user_type);
	}


	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 12:19:52 PM
	 * @Method Description : 获取COKES的值
	 */
	public String readcokes() {
		HttpServletRequest request = getRequest();
		return this.sysuserService.getCookieValue( request,"loginName");
	}

	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 12:19:40 PM
	 * @Method Description :保存COKES的值
	 */
	public void savecokes() {
		HttpServletResponse response = getResponse();
		this.sysuserService.savecokes(response,sysuser,remusername);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 12:21:11 PM
	 * @Method Description :管理员登陆
	 */
	@Action(value = "adminlogin", results = { @Result(name = "input", location = "/${adminStr}/index.jsp") })
	public String login() throws Exception {
		setAdminStr("admin");
		String sysrand = "";
		// 获取系统生成的验证码
		if (getSession().getAttribute("sysrand") != null) {
			sysrand = getSession().getAttribute("sysrand").toString();
		}
		// 验证用户名
		if (ValidateUtil.isRequired(this.sysuser.getUser_name())) {
			this.addFieldError("adminloginMessage", "请输入用户名");
			return INPUT;
		}
		// 验证密码
		if (ValidateUtil.isRequired(this.sysuser.getPasswd())) {
			this.addFieldError("adminloginMessage", "请输入密码");
			return INPUT;
		}
		// 验证验证码
		if (ValidateUtil.isRequired(sysrand)) {
			this.addFieldError("adminloginMessage", "请输入验证码");
			return INPUT;
		}
		if (!sysrand.equals(userrand)) {
			this.addFieldError("adminloginMessage", "验证码输入不正确");
			return INPUT;
		}
		//获取用户名称
		String user_name = this.sysuser.getUser_name();

		Map pageMap = new HashMap();
		pageMap.put("login_name", user_name);
		// 通过用户名找出用户信息
		List userList = this.sysuserService.getList(pageMap);
		if (userList == null || userList.size() == 0) {
			this.addFieldError("adminloginMessage", "用户名不存在");
			return INPUT;
		} else {
			HashMap userMap = (HashMap) userList.get(0);
			String state = "";
			if (userMap.get("state") != null) {
				state = userMap.get("state").toString();
			}
			if (state.equals("") || state.equals("1")) {
				this.addFieldError("adminloginMessage", "该用户已禁用，不允许登陆");
				return INPUT;
			}
			if (userMap.get("is_del")==null||userMap.get("is_del").equals("") || userMap.get("is_del").equals("1")) {
				this.addFieldError("adminloginMessage", "不存在该用户！");
				return INPUT;
			}
		}
		//获取密码
		String passwd = this.sysuser.getPasswd();

		passwd = Md5.getMD5(passwd.getBytes());
		pageMap.put("passwd", passwd);
		// 通过用户名和密码找出用户信息
		userList = this.sysuserService.getList(pageMap);
		if (userList == null || userList.size() == 0) {
			this.addFieldError("adminloginMessage", "密码输入不正确");
			return INPUT;
		}
		//清除所有session值
		getSession().removeAttribute("menu_right");
		getSession().removeAttribute("oper_right");
		getSession().removeAttribute("first_menu_id");
		getSession().setAttribute("first_menu_id","");
		// 获取该用户id及其它信息
		String user_id = "", role_id = "", user_type = "", area_id = "",menu_right="",oper_right="";
		Map userMap=this.sysuserService.getUserMap(userList);
		user_id=userMap.get("user_id").toString();
		role_id=userMap.get("role_id").toString();
		user_type=userMap.get("user_type").toString();
		area_id=userMap.get("area_id").toString();
		menu_right=userMap.get("menu_right").toString();
		oper_right=userMap.get("oper_right").toString();
		// 商城地区子站，管理登录后台的时候，获取该管理对应组织部门的地区ID,屏蔽
		//loginSysUserArea(area_id, user_type);
		// 把值存放在session中
		getSession().setAttribute(Constants.CUST_ID, "0"); // 运营商的默认cust_id为0，member表必须有cust_id为0与其对应
		// 系统会员类型
		getSession().setAttribute(Constants.CUST_TYPE, Constants.ADMIN_TYPE);
		// 会员名称
		getSession().setAttribute(Constants.USER_NAME, user_name);
		// 用户标识
		getSession().setAttribute(Constants.USER_ID, user_id);
		// 角色ID
		getSession().setAttribute(Constants.ROLE_ID, role_id);
		// 角色的菜单权限和操作权限
		getSession().setAttribute("menu_right", menu_right);
		getSession().setAttribute("oper_right", oper_right);
		// 运营商用户类型 3：管理员 4：子账户
		getSession().setAttribute(Constants.USER_TYPE, user_type);
		// 将用户名存入cokes中
		savecokes();
		//管理员后台登陆记录
		this.insertLogs(login_txt,"0",user_id,user_name);
		getResponse().sendRedirect("/adminindex.action");
		return INPUT;
	}

	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 12:40:30 PM
	 * @Method Description :商城地区子站，管理登录后台的时候，获取该管理对应组织部门的地区ID
	 */
	public void loginSysUserArea(String org_area_id, String sys_user_type) {
		// 是否开启系统后台地区过滤，0：是，1：否
		String cfg_area_shop = SysconfigFuc.getSysValue("cfg_area_shop");
		 if(cfg_area_shop!=null &&"0".equals(cfg_area_shop)){
			// 非系统管理员
			if (!sys_user_type.equals("3")) {
				// 组织部门地区ID不能为空值
				if (org_area_id != null && !org_area_id.equals("")) {
					getSession().setAttribute(Constants.ORG_AREA_ID, org_area_id);
				}
			}
		}
	}

	/**
	 * 方法描述：管理员登出
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		getSession().invalidate();
		return Constants.ADMIN_LOGIN;
	}

	/**
	 * 方法描述：修改系统管理员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String user_id = sysuser.getUser_id();
		if (user_id == null || user_id.equals("")) {
			return list();
		}
		String user_name = this.sysuser.getUser_name().trim();
		// 保存所属部门的隐藏域
		loadOrg();
		// oldusername:用于保存原用户名
		if (!ValidateUtil.isRequired(sysuser.getUser_name()) && existsTitle(sysuser.getUser_name(), oldusername, "sysuser", "user_name")) {
			this.addFieldError("sysuser.user_name", "该用户名已经存在，请重新输入");
		}
		// 字段验证
		super.commonValidateField(sysuser);
		if (super.ifvalidatepass) {
			if(info.equals("1"))
			    return infoView();
			else
			    return view();
		}
		this.sysuserService.update(sysuser);
		this.addActionMessage("修改系统管理员成功");
		if(info.equals("1"))
		    return goUrl("updateInfo");
		else
		    return list();
	}

	/**
	 * 方法描述：删除系统管理员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String user_id = this.sysuser.getUser_id();
		String user_name = this.sysuser.getUser_name();
		if (user_id == null || user_id.equals("")) {
			return list();
		}
		if (user_name == null || user_name.equals("")) {
			return list();
		}
		user_id = user_id.replace(" ", "");
		Map map=new HashMap();
		map.put("subjection", user_id);
		List sysuserList=this.sysuserService.getList(map);
		if(sysuserList!=null&&sysuserList.size()>0){
			this.addActionMessage("删除系统管理员失败！原因：该管理员存在子账号");
		}else {
			sysuser = this.sysuserService.get(user_id);
			sysuser.setIs_del("1");
			sysuserService.update(sysuser);
			this.addActionMessage("删除系统管理员成功");
		}
		
		return list();
	}

	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 1:57:02 PM
	 * @Method Description :启用管理员
	 */
	public String updateOnState() throws Exception {
		updateState();
		return list();
	}

	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 1:52:38 PM
	 * @Method Description :禁用管理员
	 */
	public String updateDownState() throws Exception {
		updateState();
		return list();
	}
	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 1:52:20 PM
	 * @Method Description :修改管理员状态
	 */
	public void updateState() {
		//系统管理员不能被禁用和启用
		boolean is_admin=false;
		Map sysMap=new HashMap();
		sysMap.put("user_ids",chb_id);
		List sysuseList=this.sysuserService.getList(sysMap);
		if(sysuseList!=null&&sysuseList.size()>0){
			Iterator sysIterator=sysuseList.iterator();
			while (sysIterator.hasNext()) {
				Map sysuserMap = (Map) sysIterator.next();
				if(sysuserMap.get("user_type").equals("3")){
					is_admin=true;
					break;
				}
				
			}
		}
		
		if(is_admin){
			this.addActionMessage("勾选管理员中含有系统管理员,系统管理员不能被禁用和启用!");
		}else{
			int count=0;
			boolean flag = this.sysuserService.updateBatchState(chb_id, state_val, "user_id", "state");
			if(!flag){
				count++;
			}
			if("1".equals(state_val)){
				count+=this.sysuserService.close(chb_id);
			}
			if(count==0){
				if (state_val.equals("0")) {
					this.addActionMessage("启用管理员成功");
				} else if (state_val.equals("1")) {
					this.addActionMessage("禁用管理员成功");
				}
			}else{
				this.addActionMessage("操作失败");
			}
		}
		
	}
	public String getUser_ids(String pid){
		Map sMap=new HashMap();
		sMap.put("subjection_in", pid);
		List sysuserList=this.sysuserService.getList(sMap);
		if(sysuserList.size()>0){
			for(int i=0;i<sysuserList.size();i++){
				Map soMap=(Map) sysuserList.get(i);
				ids+=soMap.get("user_id").toString()+",";
				getUser_ids(soMap.get("user_id").toString());
			}
		}
		return ids;
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
		if (user_name_s != null && !user_name_s.equals("")) {
			pageMap.put("user_name", user_name_s);
		}
		if (nike_name_s != null && !nike_name_s.equals("")) {
			pageMap.put("nike_name", nike_name_s);
		}
		if (role_id_s != null && !role_id_s.equals("")) {
			pageMap.put("role_id", role_id_s);
		}
		String user_ids=getUser_ids(this.session_user_id);
		if(user_ids.length()>0){
			user_ids=user_ids.substring(0, ids.length()-1);
		}
		if (user_ids.equals("")) {
			user_ids="999999999999999";
		}
		pageMap.put("user_ids", user_ids);
		if (org_attr != null&&!org_attr.equals("0")) {
			if (org_hidden_value != null && !org_hidden_value.equals("")) {
				org_hidden_value = up_id + "," + org_attr.replace(" ", "").replace(",0", "");
			} else {
				org_hidden_value = up_id;
			}
			String org_id = org_attr.replace(" ", "");
			pageMap.put("org_id", org_id);
		}
		
		pageMap.put("is_del", "0");
		sysuser = sysuserService.get(session_user_id);
		if("3".equals(sysuser.getUser_type())){
			pageMap.put("supper","3");
		}
		// 过滤地区
		pageMap = super.areafilter(pageMap);
        //过滤自己
		pageMap.put("not_user_id",this.session_user_id);
		// 根据页面提交的条件找出信息总数
		int count = this.sysuserService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		// 找出信息列表，放入list
		userList = this.sysuserService.getList(pageMap);
		userList = ToolsFuc.replaceList(userList, "");
		//加载角色列表
		HashMap rolesMap = new HashMap();
		if (!this.session_cust_id.equals("")) {
			rolesMap.put("user_id", this.session_user_id);
			//是否为系统 1为系统默认 0 不是
			rolesMap.put("is_system", "0");
		}
		roleList = this.roleService.getList(rolesMap);
		grade=this.sysuserService.get(this.session_user_id).getGrade();
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出管理员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		commonView();
		return goUrl(VIEW);
	}

	
	
	/**
	 * 方法描述：根据主键找出管理员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String resterView() throws Exception {
		commonView();
		return goUrl("resterpasswd");
	}
	
	/**
	 * 方法描述：根据主键找出管理员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String roleView() throws Exception {
		commonView();
		return goUrl("updaterole");
	}
	
	
	/**
	 * 方法描述：根据主键找出管理员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String infoView() throws Exception {
		sysuser = this.sysuserService.get(this.session_user_id);
		return goUrl("updateInfo");
	}
	
	/**
	 * 公共方法
	 */
	public void commonView(){
		String user_id=sysuser.getUser_id();
		String p_id=this.sysuserService.get(user_id).getSubjection();
		if(p_id.equals(this.session_user_id)){
			p_id=this.session_user_id;
		}
		Map map = new HashMap();
		map.put("user_id",p_id);
		map.put("is_system","0");
		roleList = this.roleService.getList(map);
		org_hidden_value = Constants.UP_ORG_ID + "," + sysuser.getOrg_id();
	}
	
	
	/**
	 * @author : LJQ
	 * @throws UnsupportedEncodingException
	 * @date : Oct 10, 2014 4:20:57 PM
	 * @Method Description : 注册发送验证码的方法
	 */
	public String sendPwd() throws Exception {
		String email =sysuser.getEmail();
		String cp_check = RandomStrUtil.getRand("6");
		Msgcheck mc = new Msgcheck();
		mc.setCp_phone(email);
		mc.setCp_check(cp_check);
		mc.setCp_type("4");
		this.msgcheckService.insert(mc);
		// 通过手机发送验证码
		Emailtemplate ep = this.emailtemplateService
				.getEmailtemplateByTempcode("forget_passwd");
		if(ep!=null){
			String title = "用户密码";
			String tem_con = ep.getTemp_con();
			tem_con = tem_con.replace("{user_name}", sysuser.getUser_name()).replace("{passwd}", cp_check);
			new MailInter().sendBatchMail(title, tem_con, email);
		}
		sysuser = this.sysuserService.get(sysuser.getUser_id());
		// 对密码加密
		cp_check = Md5.getMD5(cp_check.getBytes());
		sysuser.setPasswd(cp_check);
		this.sysuserService.update(sysuser);
		this.addActionMessage("密码已重置，请查看邮箱！");
		return list();
	}
	
	
	/**
	 * @author：XBY
	 * @date：Nov 29, 2014 9:51:42 AM
	 * @Method Description：跳转到密码修改页
	 */
	public String pwdview() throws Exception {
		sysuser = this.sysuserService.get(this.session_user_id);
		return goUrl("updatepassword");
	}
    /**
	 * @author：XBY
	 * @date：Nov 29, 2014 10:07:36 AM
	 * @Method Description：修改登录密码
	 */
	public String updatepwd() throws Exception {
		if (ValidateUtil.isRequired(oldpasswd)) {
			this.addFieldError("oldpasswd", "旧密码不能为空");
			return goUrl("updatepassword");
		}
		if (ValidateUtil.isRequired(newpasswd)) {
			this.addFieldError("newpasswd", "新密码不能为空");
			return goUrl("updatepassword");
		}
		if (ValidateUtil.isRequired(confirmpasswd)) {
			this.addFieldError("confirmpasswd", "确认密码不能为空");
			return goUrl("updatepassword");
		}
		Map pageMap = new HashMap();
		// 查找密码
		oldpasswd = Md5.getMD5(oldpasswd.getBytes());
		pageMap.put("passwd", oldpasswd);
		pageMap.put("user_id", this.session_user_id);
		List sysuserList = this.sysuserService.getList(pageMap);
		if (sysuserList == null || sysuserList.size() == 0) {
			this.addFieldError("oldpasswd", "旧密码不正确，请重新输入");
			return goUrl("updatepassword");
		}
		if (!this.newpasswd.equals(confirmpasswd)) {
			this.addFieldError("confirmpasswd", "确认密码与新密码不一致，请重新输入");
			return goUrl("updatepassword");
		}
		sysuser = this.sysuserService.get(this.session_user_id);
		// 对密码加密
		if (newpasswd != null && !newpasswd.equals("")) {
			newpasswd = Md5.getMD5(newpasswd.getBytes());
		} else {
			newpasswd = null;
		}
		sysuser.setPasswd(newpasswd);
		this.sysuserService.update(sysuser);
		this.addActionMessage("设置密码成功");
	    return logout();
	}
	
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (sysuser == null) {
			sysuser = new Sysuser();
		}
		String id = sysuser.getUser_id();
		if (!ValidateUtil.isDigital(id)) {
			sysuser = this.sysuserService.get(id);
		}
	}


	public String getAdminStr() {
		return adminStr;
	}

	public void setAdminStr(String adminStr) {
		this.adminStr = adminStr;
	}

	public Memberuser getMemberuser() {
		return memberuser;
	}

	public void setMemberuser(Memberuser memberuser) {
		this.memberuser = memberuser;
	}

	public Organize getOrganize() {
		return organize;
	}

	public void setOrganize(Organize organize) {
		this.organize = organize;
	}

	public Sysuser getSysuser() {
		return sysuser;
	}

	public void setSysuser(Sysuser sysuser) {
		this.sysuser = sysuser;
	}
}
