/*
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: AsysuserAction.java 
 */
package com.rbt.action;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
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

import org.apache.http.Consts;
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
import com.rbt.function.AreaFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.function.CommparaFuc;
import com.rbt.function.MemberuserFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.message.MailInter;
import com.rbt.model.Area;
import com.rbt.model.Asysuser;
import com.rbt.model.Emailtemplate;
import com.rbt.model.Goods;
import com.rbt.model.Goodsbrand;
import com.rbt.model.Goodsorder;
import com.rbt.model.Member;
import com.rbt.model.Memberuser;
import com.rbt.model.Msgcheck;
import com.rbt.model.Pagetip;
import com.rbt.model.Shiptemplate;
import com.rbt.model.Sysuser;
import com.rbt.service.IAreaService;
import com.rbt.service.IAsysuserService;
import com.rbt.service.IAutoupgoodsService;
import com.rbt.service.IBackupService;
import com.rbt.service.IDirectorderdetailService;
import com.rbt.service.IEmailtemplateService;
import com.rbt.service.IFreegoodsService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsattrService;
import com.rbt.service.IGoodsbrandService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IInfocountService;
import com.rbt.service.ILogsService;
import com.rbt.service.IMalllevelsetService;
import com.rbt.service.IMsgcheckService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.IOrdertransService;
import com.rbt.service.IPagetipService;
import com.rbt.service.IParagroupService;
import com.rbt.service.IParavalueService;
import com.rbt.service.IPaymentService;
import com.rbt.service.IPrintstyleService;
import com.rbt.service.IRoleService;
import com.rbt.service.ISelfspecnameService;
import com.rbt.service.ISelfspecvalueService;
import com.rbt.service.ISendmodeService;
import com.rbt.service.IShiptemplateService;
import com.rbt.service.ISpecnameService;
import com.rbt.service.IStoreservceService;
import com.rbt.service.ISysuserService;
import com.rbt.service.impl.GoodsbrandService;

/**
 * @function 功能 代理商action类
 * @author 创建人 HZX
 * @date 创建日期 Wed Aug 05 14:52:52 CST 2015
 */
@Controller
public class AsysuserAction extends GoodsBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Pagetip pagetip;
	/*
	 * 代理商对象
	 */
	private Asysuser asysuser;
	/*******************业务层接口****************/
	/*
	 * 代理商业务层接口
	 */
	@Autowired
	private IAsysuserService asysuserService;
	@Autowired
	public IRoleService roleService;
	@Autowired
	public IMsgcheckService msgcheckService;
	@Autowired
	public IEmailtemplateService emailtemplateService;
	@Autowired
	public ILogsService logsService;
	@Autowired
	public IBackupService backupService;
	@Autowired
	private IAreaService areaService;
	@Autowired
	private IInfocountService infocountService;
	@Autowired
	private IGoodsorderService goodsorderService;
	@Autowired
    private IOrderdetailService orderdetailService;
	@Autowired
	private IDirectorderdetailService directorderdetailService;
	@Autowired
	private IPaymentService paymentService;
	@Autowired
	private ISendmodeService sendmodeService;
	@Autowired
	private IOrdertransService ordertransService;
	@Autowired
	private IMalllevelsetService malllevelsetservice;
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private ISpecnameService specnameService;
	@Autowired
	private IGoodsattrService goodsattrService;
	@Autowired
	private ISelfspecnameService selfspecnameService;
	@Autowired
	private ISelfspecvalueService selfspecvalueService;
	@Autowired
	private IShiptemplateService shiptemplateService;
	@Autowired
	private IAutoupgoodsService autoupgoodsService;
	@Autowired
	private IParavalueService paravalueService;
	@Autowired
	private IParagroupService paragroupService;
	@Autowired
	private IStoreservceService storeservceService; 
	@Autowired
	private IPagetipService pagetipService;
	@Autowired
	private IFreegoodsService freegoodsService;
	@Autowired
	private IPrintstyleService printstyleService;
	@Autowired
	private IGoodsbrandService goodsbrandService;
	/*********************集合*******************/
	/*
	 * 代理商信息集合
	 */
	public List asysuserList;
	public List userList;//系统用户
	public List roleList;//角色
	public List loglist;//日志
	public List areaList;//地区
	public List storeservceList;//门店服务
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String grade;
	public String confirmpasswd;//确认密码
	public String org_attr;//所属部门串
	public String org_hidden_value;//所属部门隐藏值
	public String up_id = SysconfigFuc.getSysValue("cfg_toporgid");//顶级部门ID	
	public String user_name_s;//用户名
	public String nike_name_s;//匿名
	public String agent_type_s;//匿名
	public String ids="";
	public String role_id_s;//角色
	public String remusername;
	public String userrand;//登陆页面输入的验证码
	public String login_txt =" 代理商登录";
	public String oldusername;//原用户名
	public String info="0";//账号维护标识
	public String oldpasswd;//旧密码
	public String newpasswd;//新密码
	public String adminStr;
	public String install_date;//安装时间
	public String database_product_version;//数据库产品版本信息
	public String database_name;//数据库名称
	public String server_datetime;//服务器时间
	public String server_info;//服务器信息
	public String server_ip;//服务器IP
	public String OS_name;//操作系统名称
	public String OS_arch;//操作系统架构
	public String lastIp;//上一次登陆IP
	public String lastlogintime;//上一次登陆时间
	public String opername;//操作人
	public String logintimes;//登陆次数
	
	public Goodsorder goodsorder;
	public Member member;
	public Memberuser memberuser;
	public List agentMemberList;
	public String totalAreaMember;
	public Map totalAreaAmountMap = new HashMap();
	public List areaOrderList;
	
	public List orderStateList;
	public List deliverStateList;
	public List paymentList;
	public List sendmodeList;
	public List ordertransList;
	public List orderdetaiList;
	
	public List malllevelsetsellList;
	public List malllevelsetbuyList;
	public List freegoodsList;
	
	public String order_id;
	public String order_area;
	public String cust_id;
	
	public String order_id_s = "";// 搜索订单号
	public String order_state_s = "";// 搜索订单状态
	public String starttime_s = "";// 搜索订单开始时间
	public String endtime_s = "";// 搜索订单结束时间
	public String area_number = "";
	public String buy_cust_s ="";//搜索会员名
	public String order_type_s ="";//搜索订单类型
	
	public String goods_no_s;//商品编号
	public String info_state_s;//审核状态
	public String goods_name_s;//商品名称
	public String brand_name_s;//品牌名称
	public String tab_s;//
	
	public List goodsList;
	public List specnameList;
	public List goodsattrList;
	public List specselfNameList;
	public List specselfValueList;
	public List updownList;
	public List ralateList;
	public List autoupList;
	public List paragroupList;
	public List paravalueList;
	public Goods goods;
	public String back_sel_cat;//回选分类
	public String back_sel_cat_name;//回选分类名
	public String cat_id;//分类ID
	public String cat_name;//分类名称
	public String is_more_attr;// 是否多属性
	public String shipname;//运费名称
	public String goods_id;
	public String store_name;
	public String dedu_amount;
	public String detail_area_attr;
	public String pwd_state;//密码重置返回标识
	public String print_content;
	
	public String store_name_s="";//门店名称
	public String cellphone_s="";//手机号
	public String email_s="";//邮箱
	public String membernum_s="";//会员编号
	public String user_id_s;
	public String areaAttr_s;
	/**
	 * 方法描述：返回新增代理商页面
	 * 
	 * @return
	 * @throws Exception
	 */
	/**
	 * 方法描述：返回新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		Map areamap = new HashMap();
        if(!ValidateUtil.isRequired(area_attr_s)){
        	areamap.put("area_number", area_attr_s);
		}
		areamap.put("area_level", "2");
		areaList = areaService.getList(areamap);
		if(areaList !=null && areaList.size()>0){
			Map areanameMap=(Map)areaList.get(0);
			area_attr=areanameMap.get("area_name").toString();
		}
		stateList();
		Asysuser auser = new Asysuser();
		auser = this.asysuserService.get(session_user_id);
		if(auser != null && auser.getArea_attr()!=null&&!"".equals(auser.getArea_attr())){
			getAgentAreaId(auser.getArea_attr());
		}
		
		return goUrl(ADD);
	}
    /**
	 * @Method Description :获取代理地区
	 * @author: HXK
	 * @date : Sep 2, 2015 9:26:24 AM
	 * @param 
	 * @return return_type
	 */
	public void getAgentAreaId(String area_number){
		List<Map<String, String>> areaList=new ArrayList<Map<String,String>>();
		Map<String, String> aMap=new HashMap<String, String>();
		aMap.put("area_number", area_number);
		aMap.put("area_level", "2");
		areaList=areaService.getList(aMap);
		if(areaList!=null&&areaList.size()>0){
			Map<String, String> gMap=new HashMap<String, String>();
			gMap=areaList.get(0);
			detail_area_attr=gMap.get("area_id").toString();
		}
	}
	
	
	
	/**
	 * 方法描述：新增代理商
	 * 
	 * @return
	 * @throws Exception
	 */
	
	public String insert() throws Exception {
		List mendianuserList=null,zhiyinguserList=null;
		String nike_name="";
		int  numLast = 0;
		commonCheck();
		asysuser.setSubjection(this.session_user_id);
		Asysuser fasysuser=new Asysuser();
		fasysuser=this.asysuserService.get(this.session_user_id);
		if(fasysuser!=null&&fasysuser.getArea_attr()!=null){
			asysuser.setArea_attr(fasysuser.getArea_attr());
		}
		Map aMap = new HashMap(),maxMap=new HashMap();
		if(fasysuser!=null&&fasysuser.getArea_attr()!=null){
			aMap.put("area_attr", fasysuser.getArea_attr());
		}
		aMap.put("agent_type", "0");
		mendianuserList = this.asysuserService.getList(aMap);
		if(("1").equals(asysuser.getAgent_type())){
			asysuser.setRole_id("6");//代理
			//判断代理区域不能重复
			Map areamap = new HashMap();
			areamap.put("agent_type", "1");
			areamap.put("area_attr", asysuser.getArea_attr());
			List now_areaList = asysuserService.getList(areamap);
			if(now_areaList!=null && now_areaList.size()>0){
				this.addFieldError("asysuser.area_attr", "该地区已经被代理");
			}
			asysuser.setNike_name(asysuser.getArea_attr()+"000");
		}else if (("0").equals(asysuser.getAgent_type())) {
			if(mendianuserList.size()==0){
				asysuser.setNike_name(fasysuser.getArea_attr()+"001");
			}else {
				maxMap=(Map) mendianuserList.get(0);
				nike_name=maxMap.get("nike_name").toString();
				asysuser.setNike_name(String.format("%06d", (Integer.parseInt(nike_name)+1)));
			}
		}
		stateList();
		asysuser.setIs_del("0");
		asysuser.setState("0");
		if(asysuser.getStore_img()==null||"".equals(asysuser.getStore_img())){
			asysuser.setStore_img(cfg_nopic);
		}
		if(asysuser.getStore_name()==null||"".equals(asysuser.getStore_name())){
			asysuser.setStore_name("--");
		}
		if (conn()) {
			return add();
		}
		if(area_attr!=null&&!"".equals(area_attr)){
			area_attr=area_attr.replaceAll(" ", "");
		}
		asysuser.setDetai_area_attr(area_attr);
		this.asysuserService.insert(asysuser);
		if("1".equals(asysuser.getAgent_type())){
			this.addActionMessage("新增省级代理信息成功");	
		}
		if("0".equals(asysuser.getAgent_type())){
			this.addActionMessage("新增门店信息成功");	
		}
		return add();
	}
	
	/**
	 * 方法描述：根据主键找出门店信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewStore() throws Exception {
		String id = this.asysuser.getUser_id();
		if(id==null || id.equals("")){
			asysuser = new Asysuser();
		}else{
			asysuser = this.asysuserService.get(id);
		}
		stateList();
		Map areamap = new HashMap();
		areamap.put("area_level", "2");
		areaList = areaService.getList(areamap);
		areamap.put("area_number", asysuser.getArea_attr());
		List now_areaList = areaService.getList(areamap);
		if(now_areaList!=null && now_areaList.size()>0){
			Map areanameMap=(Map)now_areaList.get(0);
			area_attr=areanameMap.get("area_name").toString();
		}
		
		if(!ValidateUtil.isRequired(asysuser.getDetai_area_attr()) && !asysuser.getDetai_area_attr().equals("0")) {
			detail_area_attr =  asysuser.getDetai_area_attr();
			String area_str[] = detail_area_attr.split(",");
			String area_id = area_str[0];
			Area area = this.areaService.get(area_id);
			if(area!=null){
				detail_area_attr = area.getUp_area_id()+","+detail_area_attr;
			}
			
		}
		return goUrl("updateStore");
	}
	/**
	 * 修改门店信息
	 * @return
	 * @throws Exception
	 */
	public String updateStore()throws Exception{
		String user_id = asysuser.getUser_id();
		if (user_id == null || user_id.equals("")) {
			return list();
		}
		String user_name = this.asysuser.getUser_name().trim();
		if(area_attr!=null&&!"".equals(area_attr)&&!area_attr.equals("0")){
			area_attr=area_attr.replaceAll(" ", "");
		}else{
			this.addFieldError("area_attr", "请选择地区");
			return viewStore();
		}
		// 字段验证
		if (conn()) {
			return viewStore();
		}
		asysuser.setDetai_area_attr(area_attr);
		this.asysuserService.update(asysuser);
		this.addActionMessage("修改门店信息成功");
		return list();
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 11:20:55 AM
	 * @Method Description : 添加，修改公共验证方法
	 */
	private void commonCheck(){
		// 保存所属部门的隐藏域
		//loadOrg();
		// 通过用户名找出用户信息
		if (!ValidateUtil.isRequired(asysuser.getUser_name()) && existsTitle(asysuser.getUser_name(), "", "asysuser", "user_name")) {
			this.addFieldError("asysuser.user_name", "用户名已存在,请重新输入");
		}
		// 验证密码
		if (ValidateUtil.isRequired(asysuser.getPasswd())) {
			this.addFieldError("asysuser.passwd", "密码不能为空");
		}
		// 验证确认密码
		if (ValidateUtil.isRequired(confirmpasswd)) {
			this.addFieldError("confirmpasswd", "确认密码不能为空");
		}else if(!asysuser.getPasswd().equals(confirmpasswd)){
			this.addFieldError("confirmpasswd", "密码不一致");
		}
		if (!ValidateUtil.isRequired(asysuser.getPasswd()) && !ValidateUtil.isAlphasLimtLength(asysuser.getPasswd())) {
			String passwd = asysuser.getPasswd();
			passwd = Md5.getMD5(passwd.getBytes());
			asysuser.setPasswd(passwd);
		}
		asysuser.setUser_type("4");// 子账户
		asysuser.setLoginnum("0");
		// 字段验证
		super.commonValidateField(asysuser);
	}
	/**
	 * 方法描述：公用删除代理商信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.asysuserService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除代理商/门店信息成功!");
		}else{
			this.addActionMessage("删除代理商/门店信息失败!");
		}
	}
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
			// 页面搜索提交的参数
			Map pageMap = new HashMap();
			if (user_name_s != null && !user_name_s.equals("")) {
				pageMap.put("user_name", user_name_s);
			}
			if (nike_name_s != null && !nike_name_s.equals("")) {
				pageMap.put("nike_name", nike_name_s);
			}
			if(email_s!=null && !email_s.equals("")){
				pageMap.put("email", email_s);
			}
			if(cellphone_s!=null && !cellphone_s.equals("")){
				pageMap.put("cellphone", cellphone_s);
			}
			if(store_name_s!=null &&!store_name_s.equals("")){
				pageMap.put("store_name", store_name_s);
			}
			if (role_id_s != null && !role_id_s.equals("")) {
				pageMap.put("role_id", role_id_s);
			}
			if (agent_type_s != null && !agent_type_s.equals("")) {
				pageMap.put("agent_type", agent_type_s);
			}
			if(Constants.AGENT_TYPE.equals(this.session_cust_type)){
				String user_ids=getUser_ids(this.session_user_id);
				if(user_ids.length()>0){
					user_ids=user_ids.substring(0, ids.length()-1);
				}
				if (user_ids.equals("")) {
					user_ids="999999999999999";
				}
				pageMap.put("user_ids", user_ids);
			}
			pageMap.put("is_del", "0");
			 //过滤自己
			pageMap.put("not_user_id",this.session_user_id);
			// 过滤地区
			pageMap = super.areafilter(pageMap);
			// 根据页面提交的条件找出信息总数
			int count = this.asysuserService.getCount(pageMap);
			// 分页插件
			pageMap = super.pageTool(count, pageMap);
			// 找出信息列表，放入list
			userList = this.asysuserService.getList(pageMap);
			userList = ToolsFuc.replaceList(userList, "");
		    return goUrl(INDEXLIST);
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表查看二维码
	 */
	public String listcode()throws Exception{
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
		if (agent_type_s != null && !agent_type_s.equals("")) {
			pageMap.put("agent_type", agent_type_s);
		}
		String user_ids=getUser_ids(this.session_user_id);
		if(user_ids.length()>0){
			user_ids=user_ids.substring(0, ids.length()-1);
		}
		if (user_ids.equals("")) {
			user_ids="999999999999999";
		}
		pageMap.put("user_ids", user_ids);
		
		pageMap.put("is_del", "0");
		// 过滤地区
		pageMap = super.areafilter(pageMap);
        //过滤自己
		pageMap.put("not_user_id",this.session_user_id);
		// 根据页面提交的条件找出信息总数
		int count = this.asysuserService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		// 找出信息列表，放入list
		userList = this.asysuserService.getList(pageMap);
		userList = ToolsFuc.replaceList(userList, "");
		return goUrl("codeindex");
	}
	
	/**
	 * 查看二维码
	 * 
	 */
	
	public String codeview() throws Exception {
		String id = this.asysuser.getUser_id();
		if(id==null || id.equals("")){
			asysuser = new Asysuser();
		}else{
			asysuser = this.asysuserService.get(id);
		}
		stateList();
		if(asysuser.getDetai_area_attr()!=null){
			asysuser.setDetai_area_attr(AreaFuc.getAreaNameByMap(asysuser.getDetai_area_attr()));
		}
		return goUrl("codeview");
	}
	
	/**
	 * 方法描述：门店信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String doorshoplist() throws Exception {
			// 页面搜索提交的参数
			Map pageMap = new HashMap();
			pageMap.put("agent_type", "0");
			//搜索用户
			if (user_name_s != null && !user_name_s.equals("")) {
				pageMap.put("user_name", user_name_s);
			}
			//搜索门店
			if (nike_name_s != null && !nike_name_s.equals("")) {
				pageMap.put("nike_name", nike_name_s);
			}
			
			if(area_attr_s != null && !area_attr_s.equals("")){
				pageMap.put("area_attr", area_attr_s);
			}
			
			// 过滤地区
			pageMap = super.areafilter(pageMap);
			// 根据页面提交的条件找出信息总数
			int count = this.asysuserService.getCount(pageMap);
			// 分页插件
			pageMap = super.pageTool(count, pageMap);
			// 找出信息列表，放入list
			userList = this.asysuserService.getList(pageMap);
			userList = ToolsFuc.replaceList(userList, "");
		return goUrl("storeindex");
	}
	
	/**运营商后台添加门店
	 * 
	 * @return
	 * @throws Exception
	 */
	public String storeadd() throws Exception{
		Map areamap = new HashMap();
        if(!ValidateUtil.isRequired(area_attr_s)){
        	areamap.put("area_number", area_attr_s);
		}
		areamap.put("area_level", "2");
		areaList = areaService.getList(areamap);
		if(areaList !=null && areaList.size()>0){
			Map areanameMap=(Map)areaList.get(0);
			area_attr=areanameMap.get("area_name").toString();
		}
		stateList();
		if(!ValidateUtil.isRequired(user_id_s)){
			Asysuser auser = new Asysuser();
			auser = this.asysuserService.get(user_id_s);
			if(auser != null && auser.getArea_attr()!=null&&!"".equals(auser.getArea_attr())){
				getAgentAreaId(auser.getArea_attr());
			}
		}
		return goUrl("storeinsert");
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String storeinsert()throws Exception{
		List mendianuserList=null,zhiyinguserList=null;
		String nike_name="";
		int  numLast = 0;
		commonCheck();
		Asysuser fasysuser=new Asysuser();
		if(!ValidateUtil.isRequired(user_id_s)){
			asysuser.setSubjection(user_id_s);
			fasysuser=this.asysuserService.get(user_id_s);
		}

		if(fasysuser!=null&&fasysuser.getArea_attr()!=null){
			asysuser.setArea_attr(fasysuser.getArea_attr());
		}
		Map aMap = new HashMap(),maxMap=new HashMap();
		if(fasysuser!=null&&fasysuser.getArea_attr()!=null){
			aMap.put("area_attr", fasysuser.getArea_attr());
		}
		aMap.put("agent_type", "0");
		mendianuserList = this.asysuserService.getList(aMap);
		if(("1").equals(asysuser.getAgent_type())){
			asysuser.setRole_id("6");//代理
			//判断代理区域不能重复
			Map areamap = new HashMap();
			areamap.put("agent_type", "1");
			areamap.put("area_attr", asysuser.getArea_attr());
			List now_areaList = asysuserService.getList(areamap);
			if(now_areaList!=null && now_areaList.size()>0){
				this.addFieldError("asysuser.area_attr", "该地区已经被代理");
			}
			asysuser.setNike_name(asysuser.getArea_attr()+"000");
		}else if (("0").equals(asysuser.getAgent_type())) {
			if(mendianuserList.size()==0){
				asysuser.setNike_name(fasysuser.getArea_attr()+"001");
			}else {
				maxMap=(Map) mendianuserList.get(0);
				nike_name=maxMap.get("nike_name").toString();
				asysuser.setNike_name(String.format("%06d", (Integer.parseInt(nike_name)+1)));
			}
		}
		stateList();
		asysuser.setIs_del("0");
		asysuser.setState("0");
		if(asysuser.getStore_img()==null||"".equals(asysuser.getStore_img())){
			asysuser.setStore_img(cfg_nopic);
		}
		if(asysuser.getStore_name()==null||"".equals(asysuser.getStore_name())){
			asysuser.setStore_name("--");
		}
		
		if(area_attr!=null&&!"".equals(area_attr)&&!area_attr.equals("0")){
			area_attr=area_attr.replaceAll(" ", "");
		}else{
			this.addFieldError("area_attr", "请选择地区");
			return storeadd();
		}
		
		if (conn()) {
			return storeadd();
		}
		
		asysuser.setDetai_area_attr(area_attr);
		this.asysuserService.insert(asysuser);
		if("1".equals(asysuser.getAgent_type())){
			this.addActionMessage("新增省级代理信息成功");	
		}
		if("0".equals(asysuser.getAgent_type())){
			this.addActionMessage("新增门店信息成功");	
		}
		return storeadd();
	}
	
	/**
	 * 方法描述：根据主键找出门店信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String Storeview() throws Exception {
		String id = this.asysuser.getUser_id();
		if(id==null || id.equals("")){
			asysuser = new Asysuser();
		}else{
			asysuser = this.asysuserService.get(id);
		}
		stateList();
	 	
		Map areamap = new HashMap();
		areamap.put("area_level", "2");
		areaList = areaService.getList(areamap);
		areamap.put("area_number", asysuser.getArea_attr());
		List now_areaList = areaService.getList(areamap);
		if(now_areaList!=null && now_areaList.size()>0){
			Map areanameMap=(Map)now_areaList.get(0);
			area_attr=areanameMap.get("area_name").toString();
		}
		
		if(!ValidateUtil.isRequired(asysuser.getDetai_area_attr()) && !asysuser.getDetai_area_attr().equals("0")) {
			detail_area_attr =  asysuser.getDetai_area_attr();
			String area_str[] = detail_area_attr.split(",");
			String area_id = area_str[0];
			Area area = this.areaService.get(area_id);
			detail_area_attr = area.getUp_area_id()+","+detail_area_attr;
		}

		return goUrl("storeupdate");
	}
	
	/**
	 * 修改门店信息
	 * @return
	 * @throws Exception
	 */
	public String storeupdate()throws Exception{
		String user_id = asysuser.getUser_id();
		if (user_id == null || user_id.equals("")) {
			return list();
		}
		String user_name = this.asysuser.getUser_name().trim();
		if(area_attr!=null&&!"".equals(area_attr)&&!area_attr.equals("0")){
			area_attr=area_attr.replaceAll(" ", "");
		}else{
			this.addFieldError("area_attr", "请选择地区");
			return Storeview();
		}
		// 字段验证
		if (conn()) {
			return Storeview();
		}
		asysuser.setDetai_area_attr(area_attr);
		this.asysuserService.update(asysuser);
		this.addActionMessage("修改门店信息成功");
		return doorshoplist();
	}
	  	
	
	/**
	 * 公共验证
	 */
	public boolean conn()throws Exception{
		if (!ValidateUtil.isRequired(asysuser.getUser_name()) && existsTitle(asysuser.getUser_name(), oldusername, "asysuser", "user_name")) {
			this.addFieldError("sysuser.user_name", "该用户名已经存在，请重新输入");
		}
		
		if(ValidateUtil.isRequired(asysuser.getCellphone())){
			
			this.addFieldError("asysuser.cellphone", "手机号码不能为空");
		}
		if(ValidateUtil.isphone(asysuser.getCellphone())){
			this.addFieldError("asysuser.cellphone", "手机格式不正确");
		}
		if(ValidateUtil.isRequired(asysuser.getEmail())){
			this.addFieldError("asysuser.email","邮箱不能为空");
		}
		if(ValidateUtil.isEmail(asysuser.getEmail())){
			this.addFieldError("asysuser.email", "邮箱格式不正确");
		}
		if(ValidateUtil.isRequired(asysuser.getReal_name())){
			this.addFieldError("asysuser.real_name", "联系人名称不能为空");
		}
		if(ValidateUtil.isRequired(asysuser.getAddress())){
			this.addFieldError("asysuser.address", "详细地址不能为空");
		}
		if(ValidateUtil.isRequired(asysuser.getStore_img())){
			this.addFieldError("asysuser.store_img", "门店图片不能为空");
		}
		if(ValidateUtil.isRequired(asysuser.getStore_name())){
			this.addFieldError("asysuser.Store_name", "门店名称不能为空");
		}
		if(asysuser!=null&&asysuser.getStore_servce()!=null){
			asysuser.setStore_servce(asysuser.getStore_servce().replaceAll(" ", ""));
		}
		super.commonValidateField(asysuser);
		if (super.ifvalidatepass) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * 方法描述：直营店信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String shoplist() throws Exception {
			// 页面搜索提交的参数
			Map pageMap = new HashMap();
			pageMap.put("agent_type", "2");
			//搜索用户名
			if (user_name_s != null && !user_name_s.equals("")) {
				pageMap.put("user_name", user_name_s);
			}
			//搜索直营店号
			if (nike_name_s != null && !nike_name_s.equals("")) {
				pageMap.put("nike_name", nike_name_s);
			}
			
			// 过滤地区
			pageMap = super.areafilter(pageMap);
	        // 根据页面提交的条件找出信息总数
			int count = this.asysuserService.getCount(pageMap);
			// 分页插件
			pageMap = super.pageTool(count, pageMap);
			// 找出信息列表，放入list
			userList = this.asysuserService.getList(pageMap);
			userList = ToolsFuc.replaceList(userList, "");
		return goUrl("shopindex");
	}
	
	/**
	 * 添加直营店
	 */
     	
	public String shopadd() throws Exception {
		Map map = new HashMap();
		Map areamap = new HashMap();
		areamap.put("area_level", "2");
		areaList = areaService.getList(areamap);
		stateList();
		getAgentAreaId("000");
		return goUrl("shopinsert");
	}

	/**
	 * 方法描述：新增直营店
	 * 
	 * @return
	 * @throws Exception
	 */
	
	public String shopinsert() throws Exception {
		List zhiyinguserList=null;
		String nike_name="";
		int  numLast = 0;
		commonCheck();
		Map aMap = new HashMap(),maxMap=new HashMap();
		aMap.put("agent_type", "2");
		zhiyinguserList = this.asysuserService.getList(aMap);
	    if(("2").equals(asysuser.getAgent_type())) {
			asysuser.setRole_id("8");//直营店
			if(zhiyinguserList.size()==0){
				asysuser.setNike_name("000001");
			}else {
				maxMap=(Map) zhiyinguserList.get(0);
				nike_name=maxMap.get("nike_name").toString();
				asysuser.setNike_name(String.format("%06d", (Integer.parseInt(nike_name)+1)));
			}
		}
	    stateList();
		asysuser.setIs_del("0");
		if (conn()){
			return shopadd();
		}
		if(area_attr!=null&&!"".equals(area_attr)){
			area_attr=area_attr.replaceAll(" ", "");
		}
		asysuser.setDetai_area_attr(area_attr);
		this.asysuserService.insert(asysuser);
		this.addActionMessage("新增直营店信息成功");
	
		return shoplist();
	}
	
	
	/**
	 * 方法描述：根据主键找出直营店信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String Shopview() throws Exception {
		String id = this.asysuser.getUser_id();
		if(id==null || id.equals("")){
			asysuser = new Asysuser();
		}else{
			asysuser = this.asysuserService.get(id);
		}
		stateList();
		Map areamap = new HashMap();
		areamap.put("area_level", "2");
		areaList = areaService.getList(areamap);
		areamap.put("area_number", asysuser.getArea_attr());
		List now_areaList = areaService.getList(areamap);
		if(now_areaList!=null && now_areaList.size()>0){
			Map areanameMap=(Map)now_areaList.get(0);
			area_attr=areanameMap.get("area_name").toString();
			if(!ValidateUtil.isRequired(area_attr)){
				area_attr_names=AreaFuc.getAreaNameByMap(area_attr);
			}
		}
		
		viewArea(asysuser.getDetai_area_attr());
		return goUrl("shopupdate");
	}
	/**
	 * 修改直营店信息
	 * @return
	 * @throws Exception
	 */
	public String shopupdate()throws Exception{
		String user_id = asysuser.getUser_id();
		if (user_id == null || user_id.equals("")) {
			return list();
		}
		String user_name = this.asysuser.getUser_name().trim();
		if (conn()) {
			return Shopview();
		}
		if(area_attr!=null&&!"".equals(area_attr)){
			area_attr=area_attr.replaceAll(" ", "");
		}
		asysuser.setDetai_area_attr(area_attr);
		this.asysuserService.update(asysuser);
		this.addActionMessage("修改直营店信息成功");
		return shoplist();
	}	
	/**
	 * @author : ZMS
	 * @Method Description :省级启用
	 */
	public String updateIsshow() throws Exception {
		updatestate();
		return list();
	}

	/**
	 * @author : ZMS
	 * @Method Description :省级禁用
	 */
	public String updateUnshow() throws Exception {
		updatestate();
		return list();
	}
	/**
	 * @author : ZMS
	 * @Method Description :门店启用
	 */
	public String updatedoor() throws Exception {
		updatestate();
		return doorshoplist();
	}

	/**
	 * @author : ZMS
	 * @Method Description :门店禁用
	 */
	public String updateUndoor() throws Exception {
		updatestate();
		return doorshoplist();
	}
	/**
	 * @author : ZMS
	 * @Method Description :直营店启用
	 */
	public String updateshop() throws Exception {
		updatestate();
		return list();
	}

	/**
	 * @author : ZMS
	 * @Method Description :直营店禁用
	 */
	public String updateupshop() throws Exception {
		updatestate();
		return shoplist();
	}
	
	/**
	 * @author : ZMS
	 * @Method Description :公共方法
	 */
	public void updatestate(){
		boolean flag = this.asysuserService.updateBatchState(chb_id, state_val, "user_id", "state");
		if(flag){
			if (state_val.equals("0")) {
				this.addActionMessage("启用成功");
			} else if (state_val.equals("1")) {
				this.addActionMessage("禁用成功");
			}
		}else{
			this.addActionMessage("操作失败");
		}
	}
	
	
	
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String storelist() throws Exception {
			// 页面搜索提交的参数
			Map pageMap = new HashMap();
			if (nike_name_s != null && !nike_name_s.equals("")) {
				pageMap.put("nike_name", nike_name_s);
			}
			String user_ids=getUser_ids(this.session_user_id);
			if(user_ids.length()>0){
				user_ids=user_ids.substring(0, ids.length()-1);
			}
			if (user_ids.equals("")) {
				user_ids="999999999999999";
			}
			pageMap.put("user_ids", user_ids);
			
			pageMap.put("is_del", "0");
	        //过滤自己
			pageMap.put("not_user_id",this.session_user_id);
			// 根据页面提交的条件找出信息总数
			int count = this.asysuserService.getCount(pageMap);
			// 分页插件
			pageMap = super.pageTool(count, pageMap);
			// 找出信息列表，放入list
			userList = this.asysuserService.getList(pageMap);
			userList = ToolsFuc.replaceList(userList, "");
			//grade=this.asysuserService.get(this.session_user_id).getGrade();
			
			//区域消费总金额
			Map agentMap = new HashMap();
			agentMap.put("order_state", "7,8");//交易成功
			
			asysuser = this.asysuserService.get(this.session_user_id);
			
			String area_number = asysuser.getNike_name().substring(0, 3);
	           
				
			agentMap.put("area_number", area_number);//代理的地区编码
			
			totalAreaAmountMap =  this.infocountService.getTotalAreaAmount(agentMap);
			
		return goUrl("storelist");
	}
	
	
	
	
	public String getUser_ids(String pid){
		Map sMap=new HashMap();
		sMap.put("subjection_in", pid);
		List sysuserList=this.asysuserService.getList(sMap);
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
	 * 方法描述：根据主键找出代理商信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.asysuser.getUser_id();
		if(id==null || id.equals("")){
			asysuser = new Asysuser();
		}else{
			asysuser = this.asysuserService.get(id);
		}
		Map areamap = new HashMap();
		areamap.put("area_level", "2");
		areaList = areaService.getList(areamap);
		areamap.put("area_number", asysuser.getArea_attr());
		List now_areaList = areaService.getList(areamap);
		if(now_areaList!=null && now_areaList.size()>0){
			Map areanameMap=(Map)now_areaList.get(0);
			area_attr=areanameMap.get("area_name").toString();
		}
		getAgentAreaId(asysuser.getArea_attr());
		return goUrl(VIEW);
	}
	
	public String viewSelf() throws Exception{
		this.asysuser.setUser_id(this.session_user_id);
		view();
		return goUrl("updateself");
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 12:19:52 PM
	 * @Method Description : 获取COKES的值
	 */
	public String readcokes() {
		HttpServletRequest request = getRequest();
		return this.asysuserService.getCookieValue( request,"loginName");
	}

	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 12:19:40 PM
	 * @Method Description :保存COKES的值
	 */
	public void savecokes() {
		HttpServletResponse response = getResponse();
		this.asysuserService.savecokes(response,asysuser,remusername);
	}

	@Action(value = "agent", results = { @Result(name = "input", location = "/${adminStr}/index.jsp") })
	public String agent()throws Exception{
		setAdminStr("agent");
		return INPUT;
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 16, 2015  PM
	 * @Method Description :代理登陆
	 */
	@Action(value = "agentlogin", results = { @Result(name = "input", location = "/${adminStr}/index.jsp") })
	public String login() throws Exception {
		setAdminStr("agent");
		String sysrand = "";
		// 获取系统生成的验证码
		if (getSession().getAttribute("sysrand") != null) {
			sysrand = getSession().getAttribute("sysrand").toString();
		}
		// 验证用户名
		if (ValidateUtil.isRequired(this.asysuser.getUser_name())) {
			this.addFieldError("adminloginMessage", "请输入用户名");
			return INPUT;
		}
		// 验证密码
		if (ValidateUtil.isRequired(this.asysuser.getPasswd())) {
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
		String user_name = this.asysuser.getUser_name();
		Map pageMap = new HashMap();
		pageMap.put("login_name", user_name);
		// 通过用户名找出用户信息
		List userList = this.asysuserService.getList(pageMap);
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
			if (userMap.get("is_del")==null||userMap.get("is_del").equals("")|| userMap.get("is_del").equals("1")){
				this.addFieldError("adminloginMessage", "不存在该用户！");
				return INPUT;
			}
		}
		//获取密码
		String passwd = this.asysuser.getPasswd();
		passwd = Md5.getMD5(passwd.getBytes());
		pageMap.put("passwd", passwd);
		// 通过用户名和密码找出用户信息
		userList = this.asysuserService.getList(pageMap);
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
		Map userMap=this.asysuserService.getUserMap(userList);
		user_id=userMap.get("user_id").toString();
		role_id=userMap.get("role_id").toString();
		user_type=userMap.get("user_type").toString();
		area_id=userMap.get("area_id").toString();
		menu_right=userMap.get("menu_right").toString().replace(" ","");
		oper_right=userMap.get("oper_right").toString().replace(" ","");
		// 商城地区子站，管理登录后台的时候，获取该管理对应组织部门的地区ID,屏蔽
		//loginSysUserArea(area_id, user_type);
		// 把值存放在session中
		getSession().setAttribute(Constants.CUST_ID, "0"); // 运营商的默认cust_id为0，member表必须有cust_id为0与其对应
		// 系统会员类型
		getSession().setAttribute(Constants.CUST_TYPE, Constants.AGENT_TYPE);
		// 会员名称
		getSession().setAttribute(Constants.USER_NAME, user_name);
		// 用户标识
		getSession().setAttribute(Constants.USER_ID, user_id);
		// 角色ID
		getSession().setAttribute(Constants.ROLE_ID, role_id);
		// 角色的菜单权限和操作权限
		getSession().setAttribute("menu_right", menu_right);
		getSession().setAttribute("oper_right", oper_right);
		// 运营商用户类型 3：代理 4：子账户
		getSession().setAttribute(Constants.USER_TYPE, user_type);
		// 将用户名存入cokes中
		savecokes();
		//代理后台登陆记录
		this.insertLogs(login_txt,"0",user_id,user_name);
		String detai_area="";
	    detai_area=AreaFuc.getAreaNameByMap(asysuser.getDetai_area_attr())+asysuser.getAddress();
	    if(detai_area!=null&&!"".equals(detai_area)){
	    	asysuser.setAddress(detai_area);
	    }
		getResponse().sendRedirect("/agentindex.action");
		return INPUT;
	}

	/**
	 * @function 功能 系统后台默认首页信息
	 * @author 创建人 QJY
	 * @date 创建日期 Aug 25, 2011 2:13:08 PM
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
		Map areamap = new HashMap();
		areamap.put("area_level", "2");
		areamap.put("area_number", asysuserService.get(this.session_user_id).getArea_attr());
		areaList = areaService.getList(areamap);
		if(areaList !=null && areaList.size()>0){
			Map areanameMap=(Map)areaList.get(0);
			area_attr=areanameMap.get("area_name").toString();
		}
		asysuser = this.asysuserService.get(this.session_user_id);
		if(asysuser!= null){
			if(asysuser.getAgent_type().equals("0")){
				store_name = "门店";
			}else if(asysuser.getAgent_type().equals("1")){
				store_name = "区域代理";
			}else if(asysuser.getAgent_type().equals("2")){
				store_name = "直营店";
			}
		}
		getSession().setAttribute("store_name", store_name);
		return SUCCESS;
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
			// 非系统代理
			if (!sys_user_type.equals("3")) {
				// 组织部门地区ID不能为空值
				if (org_area_id != null && !org_area_id.equals("")) {
					getSession().setAttribute(Constants.ORG_AREA_ID, org_area_id);
				}
			}
		}
	}

	/**
	 * 方法描述：代理登出
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		getSession().invalidate();
		return Constants.AGENT_LOGIN;
	}

	/**
	 * 方法描述：修改系统代理信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String user_id = asysuser.getUser_id();
		if (user_id == null || user_id.equals("")) {
			return list();
		}
		String user_name = this.asysuser.getUser_name().trim();
		// 字段验证
		if (conn()) {
			return view();
		}
		this.asysuserService.update(asysuser);
		this.addActionMessage("修改账户信息成功");
		if(!ValidateUtil.isRequired(this.session_cust_id)){
			agent_type_s = "1";
			return list();
		}else {
			return viewSelf();
		}
	}

	/**
	 * 方法描述：删除系统代理信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String user_id = this.asysuser.getUser_id();
		String user_name = this.asysuser.getUser_name();
		if (user_id == null || user_id.equals("")) {
			return list();
		}
		if (user_name == null || user_name.equals("")) {
			return list();
		}
		user_id = user_id.replace(" ", "");
		Map map=new HashMap();
		map.put("subjection", user_id);
		List sysuserList=this.asysuserService.getList(map);
		if(sysuserList!=null&&sysuserList.size()>0){
			this.addActionMessage("删除系统代理商失败！原因：该代理商存在门店");
		}else {
			asysuser = this.asysuserService.get(user_id);
			asysuser.setIs_del("1");
			asysuserService.update(asysuser);
			this.addActionMessage("删除系统代理商成功");
		}
		
		return list();
	}

	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 1:57:02 PM
	 * @Method Description :启用代理
	 */
	public String updateOnState() throws Exception {
		updateState();
		return list();
	}

	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 1:52:38 PM
	 * @Method Description :禁用代理
	 */
	public String updateDownState() throws Exception {
		updateState();
		return list();
	}
	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 1:52:20 PM
	 * @Method Description :修改代理状态
	 */
	public void updateState() {
		//系统管理员不能被禁用和启用
		boolean is_admin=false;
		Map sysMap=new HashMap();
		sysMap.put("user_ids",chb_id);
		List sysuseList=this.asysuserService.getList(sysMap);
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
			this.addActionMessage("勾选updateOnState中含有系统管理员,系统管理员不能被禁用和启用!");
		}else{
			int count=0;
			boolean flag = this.asysuserService.updateBatchState(chb_id, state_val, "user_id", "state");
			if(!flag){
				count++;
			}
			if("1".equals(state_val)){
				count+=this.asysuserService.close(chb_id);
			}
			if(count==0){
				if (state_val.equals("0")) {
					this.addActionMessage("启用代理成功");
				} else if (state_val.equals("1")) {
					this.addActionMessage("禁用代理成功");
				}
			}else{
				this.addActionMessage("操作失败");
			}
		}
		
	}
	
	/**
	 * 方法描述：根据主键找出代理信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String resterView() throws Exception {
		String user_id=asysuser.getUser_id();
		String p_id=this.asysuserService.get(user_id).getSubjection();
		if(p_id!=null&&!"".equals(p_id)){
			commonView();
		}
		return goUrl("resterpasswd");
	}
	
	/**
	 * 方法描述：根据主键找出代理信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String roleView() throws Exception {
		commonView();
		return goUrl("updaterole");
	}
	
	
	/**
	 * 方法描述：根据主键找出代理信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String infoView() throws Exception {
		asysuser = this.asysuserService.get(this.session_user_id);
		return goUrl("updateInfo");
	}
	
	/**
	 * 公共方法
	 */
	public void commonView(){
		String user_id=asysuser.getUser_id();
		String p_id=this.asysuserService.get(user_id).getSubjection();
		if(p_id.equals(this.session_user_id)){
			p_id=this.session_user_id;
		}
		Map map = new HashMap();
		map.put("user_id",p_id);
		roleList = this.roleService.getList(map);
		org_hidden_value = Constants.UP_ORG_ID + "," + asysuser.getOrg_id();
	}
	
	
	/**
	 * @author : LJQ
	 * @throws UnsupportedEncodingException
	 * @date : Oct 10, 2012 4:20:57 PM
	 * @Method Description : 注册发送验证码的方法
	 */
	public String sendPwd() throws Exception {
		String email =asysuser.getEmail();
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
			tem_con = tem_con.replace("{user_name}", asysuser.getUser_name()).replace("{passwd}", cp_check);
			new MailInter().sendBatchMail(title, tem_con, email);
		}
		asysuser = this.asysuserService.get(asysuser.getUser_id());
		// 对密码加密
		cp_check = Md5.getMD5(cp_check.getBytes());
		asysuser.setPasswd(cp_check);
		this.asysuserService.update(asysuser);
		this.addActionMessage("密码已重置，请查看邮箱！");
		
		if(pwd_state.equals("1")) {
			agent_type_s = "1";
			return list();
		}else if(pwd_state.equals("2")) {
			return doorshoplist();
		}else {
			return shoplist();
		}
	}
	
	
	/**
	 * @author：XBY
	 * @date：Nov 29, 2014 9:51:42 AM
	 * @Method Description：跳转到密码修改页
	 */
	public String pwdview() throws Exception {
		asysuser = this.asysuserService.get(this.session_user_id);
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
		List sysuserList = this.asysuserService.getList(pageMap);
		if (sysuserList == null || sysuserList.size() == 0) {
			this.addFieldError("oldpasswd", "旧密码不正确，请重新输入");
			return goUrl("updatepassword");
		}
		if (!this.newpasswd.equals(confirmpasswd)) {
			this.addFieldError("confirmpasswd", "确认密码与新密码不一致，请重新输入");
			return goUrl("updatepassword");
		}
		asysuser = this.asysuserService.get(this.session_user_id);
		// 对密码加密
		if (newpasswd != null && !newpasswd.equals("")) {
			newpasswd = Md5.getMD5(newpasswd.getBytes());
		} else {
			newpasswd = null;
		}
		asysuser.setPasswd(newpasswd);
		this.asysuserService.update(asysuser);
		this.addActionMessage("设置密码成功");
		
	    return logout();
	}
	
	
	/**
	 * @author Administrator QJY
	 * @date 2015-08-11
	 * @function 销售统计信息
	 * @return
	 * @throws Exception
	 */
	public String salesAnalysis()throws Exception{
		
		Map areaMap = new HashMap();
		areaMap.put("area_level", "2");
		areaMap.put("area_number", asysuserService.get(this.session_user_id).getArea_attr());
		areaList = areaService.getList(areaMap);
		Map memMap = new HashMap();
		String area_attr="";
		if(areaList != null && areaList.size()>0){
			Map attrMap = new HashMap();
			attrMap = (Map) areaList.get(0);
			if(attrMap != null && attrMap.get("area_id")!=null){
				
				area_attr = attrMap.get("area_id").toString();
				
				memMap.put("area_attr", area_attr);
				
			}
		}
		
		if(user_name_s !=null && !"".equals(user_name_s)){
			memMap.put("user_name", user_name_s);
		}
		if(membernum_s !=null && !"".equals(membernum_s)){
			memMap.put("membernumall", membernum_s);
		}
		
		//区域消费总金额
		Map agentMap = new HashMap();
		agentMap.put("order_state", "7,8");//交易成功
		agentMap.put("area_attr",area_attr);
		
		asysuser = this.asysuserService.get(this.session_user_id);
		// 代理类型agent_type： 0门店 1代理 2直营店
		if (asysuser != null && !asysuser.getAgent_type().equals("1")) {

			memMap.put("membernum", asysuser.getNike_name());//所属地区的门店编码或者直营店编码
			
			agentMap.put("membernum", asysuser.getNike_name());//所属地区的门店编码或者直营店编码
		}else{
			String area_number = asysuser.getNike_name().substring(0, 3);
            memMap.put("area_number", area_number);//代理的地区编码
			
			agentMap.put("area_number", area_number);//代理的地区编码
		}
		totalAreaAmountMap =  this.infocountService.getTotalAreaAmount(agentMap);
		// 根据页面提交的条件找出信息总数
		int count = this.memberService.getCount(memMap);
		
		totalAreaMember = String.valueOf(count);
		
		// 分页插件
		memMap = super.pageTool(count, memMap);
		agentMemberList = this.memberService.getList(memMap);
		
		return goUrl("salesAnalysis");
	}
	
	
	/**
	 * @author Administrator QJY
	 * @date 2015-08-11
	 * @function 会员统计信息
	 * @return
	 * @throws Exception
	 */
	public String memberAnalysis()throws Exception{
		Map areaMap = new HashMap();
		areaMap.put("area_level", "2");
		areaMap.put("area_number", asysuserService.get(this.session_user_id).getArea_attr());
		areaList = areaService.getList(areaMap);
		Map memMap = new HashMap();
		String area_attr="";
		if(areaList != null && areaList.size()>0){
			Map attrMap = new HashMap();
			attrMap = (Map) areaList.get(0);
			if(attrMap != null && attrMap.get("area_id")!=null){
				
				area_attr = attrMap.get("area_id").toString();
				
				memMap.put("area_attr", area_attr);
				
			}
		}
		
		if(user_name_s !=null && !"".equals(user_name_s)){
			memMap.put("user_name", user_name_s);
		}
		//搜索会员编号
		if(membernum_s !=null && !membernum_s.equals("")){
			memMap.put("membernumall", membernum_s);
		}
		//搜索邮箱
		if(email_s!=null && !email_s.equals("")){
			memMap.put("email", email_s);
		}
		//搜索手机
		if(cellphone_s!=null && !"".equals(cellphone_s)){
			memMap.put("cellphone", cellphone_s);
		}
		// 搜索订单开始时间
		if (starttime_s != null && !"".equals(starttime_s)) {
			memMap.put("starttime", starttime_s);
		}
		// 搜索订单结束时间
		if (endtime_s != null && !"".equals(endtime_s)) {
			memMap.put("endtime", endtime_s);
		}
		//区域消费总金额
		Map agentMap = new HashMap();
		agentMap.put("order_state", "7,8");//交易成功
		agentMap.put("area_attr",area_attr);
		asysuser = this.asysuserService.get(this.session_user_id);
		// 代理类型agent_type： 0门店 1代理 2直营店
		if (asysuser != null && !asysuser.getAgent_type().equals("1")) {

			memMap.put("membernum", asysuser.getNike_name());//所属地区的门店编码或者直营店编码
			
			agentMap.put("membernum", asysuser.getNike_name());//所属地区的门店编码或者直营店编码
		}else{
			String area_number = asysuser.getNike_name().substring(0, 3);
            memMap.put("area_number", area_number);//代理的地区编码
			
			agentMap.put("area_number", area_number);//代理的地区编码
		}
		
		totalAreaAmountMap =  this.infocountService.getTotalAreaAmount(agentMap);
		
		// 根据页面提交的条件找出信息总数
		int count = this.memberService.getCount(memMap);
		
		totalAreaMember = String.valueOf(count);
		
		// 分页插件
		memMap = super.pageTool(count, memMap);
		agentMemberList = this.memberService.getList(memMap);
		//agentMemberList = this.asysuserService.getList(memMap);
		agentMemberList = ToolsFuc.replaceList(agentMemberList, "");
		return goUrl("memberAnalysis");
	}
	
	/**
	 * 方法描述：根据主键找出会员信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String memberdetails() throws Exception {
		// 根据cust_id找出memberuser对象
		if (cust_id != null && !"".equals(cust_id)) {
			member = this.memberService.get(cust_id);
			memberuser = this.memberuserService.getPKByCustID(cust_id);
		}
		if (memberuser == null) {
			memberuser = new Memberuser();
		}
		if(member==null){
			member = new Member();
		}
		// 0为卖家s
		malllevelsetsellList = this.malllevelsetservice.getMemLevelList("0");
		// 1为买家
		malllevelsetbuyList =this.malllevelsetservice.getMemLevelList("1");
		// 获取审核列表
		searchAuditList(cust_id, "member");
		return goUrl("memberdetails");
	}
	
	/**
	 * @author Administrator QJY
	 * @date 2015-08-11
	 * @function 区域订单统计信息
	 * @return
	 * @throws Exception
	 */
	public String orderAnalysis() throws Exception {

		Map areaMap = new HashMap();
		areaMap.put("area_level", "2");
		areaMap.put("area_number", asysuserService.get(this.session_user_id)
				.getArea_attr());
		areaList = areaService.getList(areaMap);
		Map memMap = new HashMap();
		if (areaList != null && areaList.size() > 0) {
			Map attrMap = new HashMap();
			attrMap = (Map) areaList.get(0);
			if (attrMap != null && attrMap.get("area_id") != null) {

				memMap.put("area_attr", attrMap.get("area_id").toString());

			}
		}

		// 搜索订单号
		if (order_id_s != null && !"".equals(order_id_s)) {
			memMap.put("order_id", order_id_s.trim());
		}
		
		// 搜索订单开始时间
		if (starttime_s != null && !"".equals(starttime_s)) {
			memMap.put("starttime", starttime_s);
		}
		// 搜索订单结束时间
		if (endtime_s != null && !"".equals(endtime_s)) {
			memMap.put("endtime", endtime_s);
		}
		//会员名
		if (buy_cust_s != null && !"".equals(buy_cust_s)) {
			Map map = new HashMap();
			map.put("user_name", buy_cust_s.trim());
			List list = memberuserService.getList(map);
			if (list.size() > 0) {
				Map mapList = (HashMap) list.get(0);
				Object str = mapList.get("cust_id");
				if (str != null && !ValidateUtil.isRequired(str.toString())) {
					memMap.put("buy_cust_id", str.toString());
				}
			}
		}
		//订单状态
		if (order_state_s != null && !"".equals(order_state_s)) {
			memMap.put("order_state_s", order_state_s);
		}
		//订单类型 1 直邮，2 保税
		if (order_type_s != null && !"".equals(order_type_s)) {
			memMap.put("is_sea", order_type_s);
		}
		memMap.put("cust_id", "0");// 排除默认
		asysuser = this.asysuserService.get(this.session_user_id);
		// 代理类型agent_type： 0门店 1代理 2直营店
		if (asysuser != null && !asysuser.getAgent_type().equals("1")) {

			memMap.put("membernum", asysuser.getNike_name());//所属地区的门店编码或者直营店编码
		}else{
			String area_number = asysuser.getNike_name().substring(0, 3);
            memMap.put("area_number", area_number);//代理的地区编码

		}

		// 根据页面提交的条件找出信息总数
		int count = this.goodsorderService.getAreaCount(memMap);
		// 分页插件
		memMap = super.pageTool(count, memMap);
		areaOrderList = this.goodsorderService.getAreaOrderList(memMap);
		areaOrderList = ToolsFuc.replaceList(areaOrderList, "");
		orderStateList = CommparaFuc.getEnabledList("order_state");
		deliverStateList = CommparaFuc.getEnabledList("deliver_state");
		gOrderPayment();

		return goUrl("orderAnalysis");
	}
	
	
	/**
	 * @author Administrator QJY
	 * @date 2015-08-11
	 * @function 订单详情
	 * @return
	 * @throws Exception
	 */
	public String orderdetails()throws Exception{
		
		if (order_id == null || order_id.equals("")) {
			goodsorder = new Goodsorder();
		} else {
			goodsorder = this.goodsorderService.get(order_id);
			//积分抵扣
			Double integral_use = Double.valueOf(goodsorder.getIntegral_use());
			Double sc_exchscale = Double.valueOf(SysconfigFuc.getSysValue("cfg_sc_exchscale"));//1个余额兑换的积分数
			dedu_amount =String.valueOf(integral_use/sc_exchscale);
			// 获取订单商品详细信息
			gOrderDetaiInfo(order_id, goodsorder.getOrder_type());
			memberuser = memberuserService.getPKByCustID(goodsorder
					.getBuy_cust_id());
		}
		gOrderPayment();
		gOrderSendmode();
		gOrderCommparaState("order_state");
		gordertransInfo(order_id);
		getFreeGoodsInfo(goodsorder.getFg_ids());
		order_area = AreaFuc.getAreaNameByMap(goodsorder.getArea_attr());
		
		return goUrl("orderdetails");
		
	}
	
	/**
	 * 方法描述：获取订单相关商品信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void gOrderDetaiInfo(String order_id, String order_type) {
		Map orderdetailMap = new HashMap();
		orderdetailMap.put("order_id", order_id);
		if (order_type.equals("6")) {
			// 预售订单详情
			orderdetaiList = this.directorderdetailService
					.getList(orderdetailMap);
		} else {
			// 普通订单详情
			orderdetaiList = this.orderdetailService.getList(orderdetailMap);
		}
	}
	
	/**
	 * 方法描述：获取支付方式
	 * 
	 * @return
	 * @throws Exception
	 */
	public void gOrderPayment() {
		Map orderMap = new HashMap();
		// 0：是 1：否
		//orderMap.put("enabled", "0");
		paymentList = paymentService.getList(orderMap);
	}
	
	/**
	 * 方法描述：获取配送方式
	 * 
	 * @return
	 * @throws Exception
	 */
	public void gOrderSendmode() {
		Map orderMap = new HashMap();
		sendmodeList = sendmodeService.getList(orderMap);
	}
	
	
	
	public void gOrderCommparaState(String com_value) {
		Map orderMap = new HashMap();
		orderMap.put("enabled", "0");
		orderMap.put("para_code", com_value);
		commparaList = commparaService.getList(orderMap);
	}
	
	/**
	 * 方法描述：获取订单异动信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void gordertransInfo(String order_id) {
		Map orderMap = new HashMap();
		orderMap.put("order_id", order_id);
		ordertransList = ordertransService.getList(orderMap);
	}
	
	/**
	 * @author Administrator QJY
	 * @method 获取赠品信息
	 * @date 2015-09-30
	 * @throws Exception
	 */
	public void getFreeGoodsInfo(String fd_ids)throws Exception{
		Map freegoodsMap = new HashMap();
		if(fd_ids !=null && !fd_ids.equals("")){
			freegoodsMap.put("fg_ids", fd_ids);
		}else{
			freegoodsMap.put("fg_ids", "0");//
		}
		freegoodsList =  this.freegoodsService.getList(freegoodsMap);
		
	}
	
	/**
	 * @author QJY
	 * @function 商品列表
	 * @return
	 * @throws Exception
	 */
	public String goodslist()throws Exception{
			Map pageMap = new HashMap();
			//搜索条件
			commonMapSearchGoodsList(pageMap);
			// 审核状态：1表示审核通过，3表示审核禁用,0表示未审核，2表示审核未通过
			if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
				pageMap.put("info_state_in", "1,3");
			}else if(info_state_s!=null&&!"".equals(info_state_s)){
				pageMap.put("info_state", info_state_s);
			}
			pageMap = commonList(pageMap);
			// 上架商品: is_up: 0是 1否
			pageMap.put("is_up", "0");
			//逻辑删除商品：is_del:0是 1否
			pageMap.put("is_del", "1");
			// 根据页面提交的条件找出信息总数
			int count = this.goodsService.getCount(pageMap);
			// 分页插件
			pageMap = super.pageTool(count, pageMap);
			goodsList = this.goodsService.getList(pageMap);
			goodsList = ToolsFuc.replaceList(goodsList, "");
			gCommparaList("goods_lable");
			// 获取商品品牌
			getALLBrandList();
			//获取贸易方式
			getTradeWay();
			return goUrl("goodslist");
	}
	
	/**
	 * 方法描述：根据主键找出记录商品表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String goodsdetails() throws Exception {
		if (!commonGoodsView()) {
			return goodslist();
		}
		return goUrl("goodsdetails");
	}
	
	
	
	
	/**
	 * @author ZMS
	 * @function 代理商品列表
	 * @return
	 * @throws Exception
	 */
	public String dailigoods()throws Exception{
			Map pageMap = new HashMap();
			//搜索条件
			commonMapSearchGoodsList(pageMap);
			// 审核状态：1表示审核通过，3表示审核禁用,0表示未审核，2表示审核未通过
			if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
				pageMap.put("info_state_in", "1,3");
			}else if(info_state_s!=null&&!"".equals(info_state_s)){
				pageMap.put("info_state", info_state_s);
			}
			pageMap = commonList(pageMap);
			// 上架商品: is_up: 0是 1否
			pageMap.put("is_up", "0");
			//逻辑删除商品：is_del:0是 1否
			pageMap.put("is_del", "1");
			// 根据页面提交的条件找出信息总数
			int count = this.goodsService.getCount(pageMap);
			// 分页插件
			pageMap = super.pageTool(count, pageMap);
			goodsList = this.goodsService.getList(pageMap);
			goodsList = ToolsFuc.replaceList(goodsList, "");
			gCommparaList("goods_lable");
			// 获取商品品牌
			getALLBrandList();
			//获取贸易方式
			getTradeWay();
			return goUrl("dailigoods");
	}
	
	/**
	 * 方法描述：根据主键找出记录商品表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String dailigoodsview() throws Exception {
		if (!commonGoodsView()) {
			return dailigoods();
		}
		return goUrl("dailigoodsview");
	}
	
	
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 9:14:42 PM
	 * @Method Description : 公共pageMap
	 */
	private Map commonList(Map pageMap) {
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		if (!ValidateUtil.isRequired(goods_no_s)) {
			pageMap.put("goods_no", goods_no_s);
		}

		if (!ValidateUtil.isRequired(info_state_s)) {
			pageMap.put("info_state", info_state_s);
		}
		if (!ValidateUtil.isRequired(goods_name_s)) {
			pageMap.put("goods_name", goods_name_s);
		}
		if (!ValidateUtil.isRequired(cat_attr_s)) {
			pageMap.put("cat_attr", cat_attr_s);
			//回选分类
			viewCat(cat_attr_s);
		}
		if (!ValidateUtil.isRequired(brand_name_s)) {
			pageMap.put("brand_name", brand_name_s);
		}
		// 未逻辑删除商品
		pageMap.put("is_del", "1");
		// 实物商品
		pageMap.put("gd_is_virtual", "1");
		return pageMap;
	}
	
	public void gCommparaList(String para_code) {
		Map map = new HashMap();
		map.put("para_code", para_code);
		commparaList = commparaService.getList(map);
	}
	
	public void stateList() {
		Map map = new HashMap();
		map.put("state", "0");
		storeservceList = storeservceService.getList(map);
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 9:29:29 PM
	 * @Method Description :查看详情
	 */
	private boolean commonGoodsView() {
		if (goods_id == null || goods_id.equals("")) {
			return false;
		} else {
			goods = this.goodsService.get(goods_id);
			asysuser = this.asysuserService.get(this.session_user_id);
		}
		if(goods_cat==null||"".equals(goods_cat)){
			// 分类名称
			back_sel_cat = goods.getCat_attr();
			goods_cat = back_sel_cat.replace(catIdStr + ",", "").replace(" ", "");
			cat_name = CategoryFuc.getCateNameByMap(goods_cat);
		}
		if (goods.getSelf_cat() != null && !goods.getSelf_cat().equals("")) {
			viewSelfCat(goods.getSelf_cat());
		}
		// 获取商品分类拥有的规格列表
		Map specMap = new HashMap();
		specMap.put("cat_attr", goods_cat);
		specnameList = this.specnameService.getList(specMap);
		// 获取商品的标签
		getGoodsLabel();
		// 获取扩展属性列表
		getExtendAttr(goods_id);
		// 获得参数组列表
		getParagroup(goods_id);
		// 获取商品品牌
		getBrandList(goods_cat);
		// 查找出商品属性的列表
		Map attrMap = new HashMap();
		attrMap.put("goods_id", goods_id);
		goodsattrList = this.goodsattrService.getList(attrMap);
		if(goodsattrList!=null&&goodsattrList.size()>0){
			Map mspecstr=(HashMap)goodsattrList.get(0);
			//spestr是为了判断是多属性还是单属性
			if (mspecstr.get("specstr")!=null && !"".equals(mspecstr.get("specstr").toString())) {
				//获取商品自定义规格名称列表
				Map ssnMap = new HashMap();
				ssnMap.put("goods_id", goods_id);
				ssnMap.put("id_asc", "id_asc");
				specselfNameList = this.selfspecnameService.getList(ssnMap);
				if (specselfNameList != null && specselfNameList.size() > 0) {
					is_more_attr = "0";
					String flagId="";
					for(int i=0;i<specselfNameList.size();i++){
						Map map=(HashMap)specselfNameList.get(i);
						if (map.get("spec_serial_id") != null) {
							if(!flagId.equals("")){
								flagId = flagId +","+ map.get("spec_serial_id").toString();
							}else{
								flagId = map.get("spec_serial_id").toString();
							}
						}
					}
					if(!ValidateUtil.isRequired(flagId)){
						//获取自定义规格值列表
						Map ssvMap = new HashMap();
						ssvMap.put("id_asc", "id_asc");
						ssvMap.put("spec_serial_id", flagId);
						specselfValueList = this.selfspecvalueService.getList(ssvMap);
					}
				}
			}
		}
		
		// 加载FCK数据
		loadFckData(goods_id, "goods");
		// 加载自动上下架管理数据
		Map updownMap = new HashMap();
		updownMap.put("goods_id", goods_id);
		updownList = this.autoupgoodsService.getList(updownMap);
		// 获取商品的相关商品串
		String sgis = goods.getRelate_goods();
		if (sgis != null && !sgis.equals("")) {
			Map sgisMap = new HashMap();
			sgisMap.put("sgis", sgis);
			ralateList = this.goodsService.getList(sgisMap);
		}
		// 根据商品的模板ID,查询出模板名称
		if (goods.getShip_id() != null && !goods.getShip_id().equals("")) {
			if (this.shiptemplateService.get(goods.getShip_id()) != null) {
				Shiptemplate stp = this.shiptemplateService.get(goods.getShip_id());
				shipname = stp.getShip_name();
			} else {
				shipname = "该模板已删除";
			}
		}
		// 获取审核列表
		searchAuditList(goods_id, "goods");
		// 获取自动上下架商品的列表
		Map autoMap = new HashMap();
		autoMap.put("goods_id", goods_id);
		autoupList = this.autoupgoodsService.getList(autoMap);
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			// 加载卖家等级信息
			getSellLevel("0");
		}
		// 获取审核列表
		searchAuditList(goods.getGoods_id(), "goods");
		return true;
	}
	
	 /**
	 * ZMS
	 * @return
	 * @throws Exception:word/pdf文档
	 */
	 public String wordpdffileview() throws Exception{
		 String id = "word_pdffile_page";
		 pagetip =this.pagetipService.get(id);
		 return goUrl("wordpdffile");
	 }
	 
	 public String wordpdfupdate() throws Exception{
		 if(pagetip.getPage_code()==null){
				return  wordpdffileview();
			}else {
				this.pagetipService.update(pagetip);
				this.addActionMessage("修改"+pagetip.getRemark()+"信息成功,请点击右上角更新缓存！","修改"+pagetip.getRemark()+"信息成功");
				return goUrl("wordpdffile");
			}
	 }
	
	/**
	 * @author : LJQ
	 * @date : Feb 5, 2014 1:42:36 PM
	 * @Method Description : 获取参数组与参数值表数据
	 */
	public void getParagroup(String goods_id) {
		// 参数组list
		Map pgMap = new HashMap();
		pgMap.put("cat_attr", goods_cat);
		paragroupList = this.paragroupService.getList(pgMap);
		// 参数组值表
		Map pgvMap = new HashMap();
		pgvMap.put("cat_attr", goods_cat);
		if (goods_id != null && !goods_id.equals("")) {
			pgvMap.put("goods_id", goods_id);
			paravalueList = this.paravalueService.getParaValList(pgvMap);
		} else {
			paravalueList = this.paravalueService.getList(pgvMap);
		}
	}

	
	/**
	 * ajax 获取 商品二维码打印模板
	 * 
	 * @throws Exception
	 */
	public void getGoodsQRcode() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("goods_id");
		if (id == null || id.equals("")) {
			goods = new Goods();
		} else {
			Asysuser asysuser = this.asysuserService.get(this.session_user_id);
			Goods goods = this.goodsService.get(id);
			getQRcodePrintStyle("0", "goods_qrcode");
				
			if (print_content != null && !"".equals(print_content)) {
				// 替换打印内容标签
				print_content = replaceGoodsQRcodePrint(print_content,asysuser,goods);
			}
		}
		out.write(print_content);
	}
	
	/**
	 * 方法描述：商品二维码标签替换
	 * 
	 * @return
	 * @throws Exception
	 */
	public String replaceGoodsQRcodePrint(String repConten,Asysuser store,Goods goods)throws Exception {
		
		String cfg_mobiledomain =  SysconfigFuc.getSysValue("cfg_mobiledomain");//手机触屏版域名
		String str_goods_id = "";//商品ID
		String str_goods_no = "";//商品货号
		String str_custnum = "";//门店码
		String str_goods_brand="";//商品品牌
		String str_goods_name="";//商品名称
		String str_goods_place="";//产地
		String str_goods_en_name="";//英文名称
		String str_bar_code="";//条形码
		String str_goods_depot="";//贸易方式 1：直邮，2保税
		String str_goods_producer="";//生产厂商
		String str_goods_unit="";//计量单位
		String str_registertype = "";//代理类型___代理 1，门店 0，直营店 2

		if (repConten != null) {
		    if(store !=null){
		    	if(store.getAgent_type().equals("0")){
		    		str_registertype = "1";//代理
		    	}else if(store.getAgent_type().equals("2")){
		    		str_registertype = "2";//直营店
		    	}
		    	str_custnum = store.getNike_name();//门店编码
		    }
		    if(goods!=null){
		    	if(!ValidateUtil.isRequired(goods.getGoods_id())){
		    		str_goods_id = goods.getGoods_id();
		    	}
		    	if(!ValidateUtil.isRequired(goods.getGoods_no())){
		    		str_goods_no = goods.getGoods_no();
		    	}
		    	if(!ValidateUtil.isRequired(goods.getBrand_id())){
		    		Goodsbrand goodsbrand=new Goodsbrand();
		    		goodsbrand=goodsbrandService.get(goods.getBrand_id());
		    		if(goodsbrand!=null&&goodsbrand.getBrand_name()!=null){
		    			str_goods_brand = goodsbrand.getBrand_name();
		    		}
		    	}
		    	if(!ValidateUtil.isRequired(goods.getGoods_name())){
		    		str_goods_name = goods.getGoods_name();
		    	}
		    	if(!ValidateUtil.isRequired(goods.getGoods_place())){
		    		Area area =new Area ();
		    		area=areaService.get(goods.getGoods_place());
		    		if(area!=null&&area.getArea_name()!=null)
		    		str_goods_place =area.getArea_name();
		    	}
		    	if(!ValidateUtil.isRequired(goods.getGoods_en_name())){
		    		str_goods_en_name = goods.getGoods_en_name();
		    	}
		    	if(!ValidateUtil.isRequired(goods.getBar_code())){
		    		str_bar_code = goods.getBar_code();
		    	}
		    	if(!ValidateUtil.isRequired(goods.getDepot_id())){
		    		if(!"1".equals(goods.getDepot_id())){
		    			str_goods_depot="直邮发货";
		    		}else {
		    			str_goods_depot="保税发货";
					}
		    	}
		    	if(!ValidateUtil.isRequired(goods.getProducer())){
		    		str_goods_producer = goods.getProducer();
		    	}
		    	if(!ValidateUtil.isRequired(goods.getUnit())){
		    		str_goods_unit = goods.getUnit();
		    	}
		    }
            String mobile_url = cfg_mobiledomain+"/webapp/goods!detail.action?gid="+str_goods_id+"&custnum="+str_custnum+"&registertype="+str_registertype;
			repConten = repConten.replace("${goods_no}", str_goods_no);//选择的商品货号
            repConten = repConten.replace("${mobile_url}", mobile_url);//代理类型
            repConten = repConten.replace("${goods_en_name}", str_goods_en_name);//英文名称
            repConten = repConten.replace("${goods_place}", str_goods_place);//产地
            repConten = repConten.replace("${goods_brand}", str_goods_brand);//商品品牌
            repConten = repConten.replace("${goods_name}", str_goods_name);//商品品牌
            repConten = repConten.replace("${goods_bar_code}", str_bar_code);//商品条形码
            repConten = repConten.replace("${goods_depot}", str_goods_depot);//贸易方式
            repConten = repConten.replace("${goods_producer}", str_goods_producer);//生产厂商
            repConten = repConten.replace("${goods_unit}", str_goods_unit);//计量单位
            
		}
		return repConten;
	}
	
	
	/**
	 * ajax 获取 门店二维码打印模板
	 * 
	 * @throws Exception
	 */
	public void getStoreQRcode() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("store_id");
		if (id == null || id.equals("")) {
			asysuser = new Asysuser();
		} else {
			asysuser = this.asysuserService.get(this.session_user_id);
			getQRcodePrintStyle("0", "store_qrcode");
				
			if (print_content != null && !"".equals(print_content)) {
				// 替换打印内容标签
				print_content = replaceStoreQRcodePrint(print_content,asysuser);
			}
		}
		out.write(print_content);
	}
	
	/**
	 * 方法描述：门店二维码标签替换
	 * 
	 * @return
	 * @throws Exception
	 */
	public String replaceStoreQRcodePrint(String repConten,Asysuser store)throws Exception {
		
		String cfg_mobiledomain =  SysconfigFuc.getSysValue("cfg_mobiledomain");//手机触屏版域名
		String str_area_store = "";//所属区域
		String str_custnum = "";//门店码
		String str_registertype = "";//代理类型__代理 1，门店 0，直营店 2
		String str_area_name = "";

		if (repConten != null) {

		    if(store !=null){
		    	
		    	if(store.getAgent_type().equals("0")){
		    		
		    		str_registertype = "1";//代理
		    		
		    	}else if(store.getAgent_type().equals("2")){
		    		
		    		str_registertype = "2";//直营店
		    		
		    	}
		    	
		    	str_custnum = store.getNike_name();//门店编码
		    	
		    	Map areamap = new HashMap();
				areamap.put("area_level", "2");
				areamap.put("area_number", store.getArea_attr());
				areaList = areaService.getList(areamap);
				if(areaList !=null && areaList.size()>0){
					Map areanameMap=(Map)areaList.get(0);
					str_area_name=areanameMap.get("area_name").toString();
				}
		    	
		    }
		    
            String mobile_url = cfg_mobiledomain+"/webapp/memberuser!webappRegister.action?custnum="+str_custnum+"&registertype="+str_registertype;
		    //repConten = repConten.replace("${cfg_mobiledomain}", cfg_mobiledomain);//手机触屏版域名
		    repConten = repConten.replace("${store_code}", str_custnum);//门店编码
		    repConten = repConten.replace("${area_name}", str_area_name);//区域名称
			//repConten = repConten.replace("${registertype}", str_registertype);//代理类型
            repConten = repConten.replace("${mobile_url}", mobile_url);//代理类型
		}
		return repConten;
	}
	
	
	/**
	 * 方法描述：获取打印模版
	 * 
	 * @return
	 * @throws Exception
	 */
	public void getQRcodePrintStyle(String cust_id, String template_code) {
		Map goodsMap = new HashMap();
		goodsMap.put("cust_id", cust_id);
		goodsMap.put("template_code", template_code);
		List printstyleList = printstyleService.getList(goodsMap);
		if (printstyleList != null) {
			HashMap pstyle = new HashMap();
			pstyle = (HashMap) printstyleList.get(0);
			if (pstyle != null && pstyle.get("print_content") != null) {
				// 获取打印模版内容
				print_content = pstyle.get("print_content").toString();
			}
		}
	}
	
	
	
	/**
	 * @return the AsysuserList
	 */
	public List getAsysuserList() {
		return asysuserList;
	}
	/**
	 * @param asysuserList
	 *  the AsysuserList to set
	 */
	public void setAsysuserList(List asysuserList) {
		this.asysuserList = asysuserList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(asysuser == null){
			asysuser = new Asysuser();
		}
		String id = this.asysuser.getUser_id();
		if(!this.validateFactory.isDigital(id)){
			asysuser = this.asysuserService.get(id);
		}
	}
	/**
	 * @return the asysuser
	 */
	public Asysuser getAsysuser() {
		return asysuser;
	}
	/**
	 * @param Asysuser
	 *            the asysuser to set
	 */
	public void setAsysuser(Asysuser asysuser) {
		this.asysuser = asysuser;
	}

	public String getAdminStr() {
		return adminStr;
	}

	public void setAdminStr(String adminStr) {
		this.adminStr = adminStr;
	}

	public List getStoreservceList() {
		return storeservceList;
	}

	public void setStoreservceList(List storeservceList) {
		this.storeservceList = storeservceList;
	}
	public Pagetip getPagetip() {
		return pagetip;
	}
	public void setPagetip(Pagetip pagetip) {
		this.pagetip = pagetip;
	}
	public String getPrint_content() {
		return print_content;
	}
	public void setPrint_content(String print_content) {
		this.print_content = print_content;
	}
	
	
	
	
}

