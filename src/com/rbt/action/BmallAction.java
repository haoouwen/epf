package com.rbt.action;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.JsonUtil;
import com.rbt.function.AreaFuc;
import com.rbt.model.Advinfo;
import com.rbt.model.Article;
import com.rbt.model.Busimes;
import com.rbt.model.Goods;
import com.rbt.model.Goodsask;
import com.rbt.model.Goodseval;
import com.rbt.model.Goodsorder;
import com.rbt.model.Malllevelset;
import com.rbt.model.Member;
import com.rbt.model.Membercredit;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.model.Memberuser;
import com.rbt.model.Memprotect;
import com.rbt.model.Orderdetail;
import com.rbt.model.Refundapp;
import com.rbt.model.Sellerscore;
import com.rbt.model.Shopconfig;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.IArticleService;
import com.rbt.service.ICollectService;
import com.rbt.service.IConsultationService;
import com.rbt.service.IDirectorderdetailService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsaskService;
import com.rbt.service.IGoodsevalService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IGroupgoodsService;
import com.rbt.service.ILogsService;
import com.rbt.service.IMalllevelsetService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMembercreditService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberinterService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IMemprotectService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.IPaymentService;
import com.rbt.service.IPointsgoodsService;
import com.rbt.service.IRefundappService;
import com.rbt.service.IShopconfigService;
import com.rbt.service.ISpikegoodsService;
import com.rbt.service.ITryapplyService;

@Controller
public class BmallAction  extends AdminBaseAction implements Preparable{	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
    private Memberuser memberuser;
    private Sellerscore sellerscore;  
    public Member member;
	private Memprotect memprotect;
	public Refundapp refundapp;
	public Shopconfig shopconfig;
	public AreaFuc areaFuc;
	public Busimes busimes;
	public  Goodsask  goodsask;
	public Advinfo advinfo;
	private Article article;
	public Goods goods;
	public  Goodsorder goodsorder;
	public Memberfund  memberfund;
	public Memberinter memberinter;
	public Goodseval  goodseval;
	public 	Orderdetail orderdetail;
	public Membercredit membercredit; 

	/*******************业务层接口****************/
	@Autowired
	private IMemberuserService menberuserService;
	@Autowired
	private IAdvinfoService advinfoService;
	@Autowired
	private IRefundappService refundappService;
	@Autowired
	private IMemprotectService memprotectService;
	@Autowired
	private IMalllevelsetService mallvelsetService;
	@Autowired
	private IMemberService memberService;
	@Autowired
	private IShopconfigService shopconfigService;
	@Autowired
	private IGroupgoodsService groupgoodsService;
	@Autowired
	private IArticleService articleService;
	@Autowired
	private IGoodsorderService goodsorderService;	
	@Autowired
	private IGoodsaskService goodsaskService;
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IMemberfundService memberfundService;
	@Autowired
	private IMemberinterService memberinterService;
	@Autowired
    private ISpikegoodsService spikegoodsService;
	@Autowired
	private IPointsgoodsService pointsgoodsService;
	@Autowired
	public  IOrderdetailService orderdetailService;
	@Autowired
	public ILogsService logsService;
	@Autowired
	public IGoodsevalService goodsevalService;
	@Autowired
	public IMembercreditService membercreditService;
	@Autowired
	private IPaymentService paymentService;
	@Autowired
	private IDirectorderdetailService directorderdetailService;
	@Autowired
	private ICollectService collectService;
	@Autowired
	private IConsultationService consultationService;
	/*
	 * 申请试用业务层接口
	 */
	@Autowired
	private ITryapplyService tryapplyService;
	
	/*********************集合******************/
	public List articleList;//记录文章管理表信息信息集合
	public List avgscoreList;//平均分
	public List  goodsorderList;//商品订单
	public List detailList;//详细
	public List loglist;//操作记录
	public List shopconfigList;//店铺记录
	public List memprotectList;//记录会员密保信息信息集合
	public List eOrderList;//前日数据
	public List lOrderList;//上周同期数据
	public List yOrderList;//昨日数据
	public List logsList;//定义系统日志列表
	public List paymentList;//支付方式
	public List directDetaiList;//预售列表
	public List tryapplyList;//试用列表
	/*********************字段******************/
	public String user_name=""; //定义账号字段
    public String email="";//定义邮箱字段
    public String telphoneStr="";//定于手机
    public String isque="0";//定义是否设置密保
    public String pass_str=""; //定义密码强度字段
    public String is_check_mobile;//判断是否手机验证
    public String is_check_email; //判断是否邮箱验证
    public String opername; //日志类表操作人名
	public String area_String=""; 	//定义所在地区
	public String today="1";//今天
	public String ydate="1";//昨天
	public String pay_pass;//判断支付密码
	public String url;//广告图片链接路径
	public String img;//图片路径
	public String login_time="";//上次登录时间
	public String  level_url;//等级图片
	public int countNow=0;//今日留言条数
	public int notcount=0;//今日留言条数
	public int  countSask=0;//今日咨询条数
	public int notSask=0;//待处理咨询条数
	public int goodsCount;//待上架的商品
	public int stockCount;//库存不足的商品
	public int labelCount;//推荐的商品
	public int lbCount;//促销中的商品
	public int daiorderCount;//待发货订单
	public int orderCount;	//未处理订单
	public int todayOrder;//今日订单
	public int yOrder;//昨日订单
	public int payCount;//待付款订单
	public int todaypayCount;//今日已付款订单
	public int ypayCount;//昨日已付款订单
	public int erefund;//前日退款订单数
	public int lrefund;//上周同期退款订单数
	public int yrefund;//昨日退款订单数
	public int eOrder;//前日订单
	public int lOrder;//上周同期订单
	public int epayCount;//前日已付款订单
	public int lpayCount;//上周同期已付款订单
	public int membercount;//今日新增会员
	public int ymembercount;//昨日新增会员
	public int allmembercount;//总会员
	public int allgoodscount;//商品总数
	public int upgoodscount;//已上架商品总数
	public int downgdscount;//下架商品总数
	public int group_count;//团购商品总数
	public int spike_count;//秒杀商品总数
	public int points_count;//积分商品总数
	public int couponCount;//优惠券
	public int buyerOrder;//我是买家-订单提醒-待处理订单
	public int sevelCount;	//待评价商品
	public int skCount;//我是买家咨询
	public String current_hour;//当前小时
    public int reOrder=0;//等确认收货
    public int comsumerCount=0;//优惠券数量
    public String datenow;
	public String jsontotal;//取消订单原因
	public int tryorder=0;//试用订单
	public int yushouorder=0;//预售订单
	public int refundapporder=0;//退款退货
	public String nickName;//qq呢称
	public String weixinName;//微信名称
    public int collCount=0;//收藏
    public int consulCount=0;//咨询
	/**
	 * @function 功能 商城后台首页
	 * @author 创建人 HZX
	 */
	public String index() throws Exception {
		//判断session时间过期就重新登录
		HttpServletResponse response=this.getResponse();
		if(this.session_cust_id==null||this.session_cust_id.equals("")){
			response.sendRedirect("/login.html");
		}
		String cust_id = this.session_cust_id;
		//查找会员名称
		if(cust_id.equals("")){
			return SUCCESS;
		}
		member=this.memberService.get(cust_id);
		if(member==null){
			member=new Member();
		}
		if(!member.getInfo_state().equals("1")){
			this.addFieldError("memberuser.user_name", "该账号未审核或审核不通过！");
			response.sendRedirect("/login.html");
		}
		if(member.getSell_level()!=null){
			Malllevelset mls=this.mallvelsetService.get(member.getSell_level());
			level_url=mls.getImg_url();
		}
		//查找店铺名称、地址、邮箱
		shopconfig=this.shopconfigService.getByCustID(cust_id);
		if(shopconfig==null){
			if(member.getMem_type().equals("1")){
				getResponse().sendRedirect("/bmall-buyer.action");
			}else{
				getResponse().sendRedirect("/bmall_Shopconfig_bmallactive.action");
			}
		}
		//得到店铺字符串
		String area_id = shopconfig.getArea_attr();
		//转化为具体的地区
		area_String = areaFuc.getAreaNameByMap(area_id);		
		//查找信誉数
		membercredit=this.membercreditService.getByCustId(cust_id);
		//订单管理
		//今天订单
		Map orMap=new HashMap();
		orMap.put("sell_cust_id", cust_id);	
		orMap.put("buy_cust_id_no", cust_id);
		orMap.put("is_virtual", "1");//不是虚拟商品
		orMap.put("today", this.today);
		todayOrder = this.goodsorderService.getCount(orMap);
		//昨日订单
		Map yorMap=new HashMap();
		yorMap.put("sell_cust_id", cust_id);
		yorMap.put("buy_cust_id_no", cust_id);
		yorMap.put("is_virtual", "1");//不是虚拟商品
		yorMap.put("ydate", this.ydate);
		yOrder = this.goodsorderService.getCount(yorMap);
		//今日付款订单
		Map payMap=new HashMap();
		payMap.put("sell_cust_id", cust_id);
		payMap.put("buy_cust_id_no", cust_id);
		payMap.put("order_state", "2,3,7,8");
		payMap.put("is_virtual", "1");//不是虚拟商品
		payMap.put("paytoday", this.today);
		todaypayCount = this.goodsorderService.getCount(payMap);
		//昨日付款订单
		Map ypayMap=new HashMap();
		ypayMap.put("sell_cust_id", cust_id);
		ypayMap.put("buy_cust_id_no", cust_id);
		ypayMap.put("order_state", "2,3,7,8");
		ypayMap.put("is_virtual", "1");//不是虚拟商品
		ypayMap.put("ypaydate", this.ydate);
		ypayCount = this.goodsorderService.getCount(ypayMap);
		//今日新增会员
		Map mcMap = new HashMap();
		mcMap.put("todayin", this.today);
		membercount = this.memberService.getCount(mcMap);
		//昨日新增会员
		Map ymcMap = new HashMap();
		ymcMap.put("ydatein", this.ydate);
		ymembercount = this.memberService.getCount(ymcMap);
		//总会员
		Map allmcMap = new HashMap();
		allmembercount = this.memberService.getCount(allmcMap);
		//商品总数
		Map goodsMap = new HashMap();
		goodsMap.put("cust_id", cust_id);
		goodsMap.put("is_del", "1");
		goodsMap.put("gd_is_virtual", "1");//不是虚拟商品
		allgoodscount = this.goodsService.getCount(goodsMap);
		//下架商品
		Map downgdsMap = new HashMap();
		downgdsMap.put("cust_id", cust_id);
		downgdsMap.put("is_up", "1");
		downgdsMap.put("is_del", "1");
		downgdsMap.put("gd_is_virtual", "1");//不是虚拟商品
		downgdscount = this.goodsService.getCount(downgdsMap);
		//已上架架商品
		Map upMap = new HashMap();
		upMap.put("cust_id", cust_id);
		upMap.put("is_up", "0");
		upMap.put("is_del", "1");
		upMap.put("gd_is_virtual", "1");//不是虚拟商品
		upgoodscount = this.goodsService.getCount(upMap);
		//团购商品总数
		Map gcMap = new HashMap();
		gcMap.put("cust_id", cust_id);
		group_count = this.groupgoodsService.getCount(gcMap);
		//秒杀商品总数
		Map scMap = new HashMap();
		scMap.put("cust_id", cust_id);
		spike_count = this.spikegoodsService.getCount(scMap);
		//积分商品总数
		Map pcMap = new HashMap();
		pcMap.put("cust_id", cust_id);
		points_count = this.pointsgoodsService.getCount(pcMap);
		//获取文章列表
		Map articleMap =new HashMap();
		articleMap.put("start", 0);
		articleMap.put("limit", 10);
		articleMap.put("is_display", "0");
		articleList = this.articleService.getList(articleMap);
		// 获取操作日志列表
		Map logMap = new HashMap();		
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			logMap.put("cust_id", this.session_cust_id);
			opername = this.session_user_name;
			logMap.put("user_name", opername);
			logMap.put("start", 0);
			logMap.put("limit", 10);
			loglist = logsService.getList(logMap);
		}
		//前日数据
		Map eorMap=new HashMap();
		eorMap.put("eover", cust_id);		
		eOrderList = this.goodsorderService.getoverList(eorMap);
		Map ereMap= new HashMap ();
		ereMap.put("erefund", cust_id);
		erefund = this.refundappService.getCount(ereMap);
		//昨日数据
		Map yyorMap=new HashMap();
		yyorMap.put("yover", cust_id);		
		yOrderList = this.goodsorderService.getoverList(yyorMap);
		Map yreMap= new HashMap ();
		yreMap.put("yrefund", cust_id);
		yrefund = this.refundappService.getCount(yreMap);
		//上周同期数据
		Map lorMap=new HashMap();
		lorMap.put("lover", cust_id);		
		lOrderList = this.goodsorderService.getoverList(lorMap);
		Map lreMap= new HashMap ();
		lreMap.put("lrefund", cust_id);
		lrefund = this.refundappService.getCount(lreMap);
		return SUCCESS;				
	}

	//我是买家
	public String buyer() throws Exception
	{
		String cust_id = this.session_cust_id;
		//查找会员名称
		if(cust_id.equals("")){
			return SUCCESS;
		}
		memberuser=menberuserService.get(this.session_user_id);
		//判断支付密码
		if(memberuser.getCust_id()!=null && !"".equals(memberuser.getCust_id())){
			memberfund=this.memberfundService.get(memberuser.getCust_id());
			if(memberfund!=null){
				pay_pass=memberfund.getPay_passwd();
			}
			
		}
		//给定密码强度提示
		if(!"".equals(memberuser.getPass_strength())){
			pass_str=memberuser.getPass_strength();
		}
		//判断是否已手机验证
		if(!"".equals(memberuser.getIs_check_mobile())){
			is_check_mobile=memberuser.getIs_check_mobile();
		}
		//判断是否已邮箱验证
		if(!"".equals(memberuser.getIs_check_email())){
			is_check_email=memberuser.getIs_check_email();
		}
		
		//查找会员
		member=this.memberService.get(cust_id);
		if(member==null){
			member=new Member();
		}
		if(member.getBuy_level()!=null){
			Malllevelset mls=this.mallvelsetService.get(member.getBuy_level());
			level_url=mls.getImg_url();
		}
		//查找信誉数
		membercredit=this.membercreditService.getByCustId(cust_id);
		//订单提醒
		//待处理订单
		Map buyerOrderMap=new HashMap();
		buyerOrderMap.put("buy_cust_id", cust_id);		
		buyerOrderMap.put("order_state","1");
		buyerOrder = this.goodsorderService.getCount(buyerOrderMap);
		//待评价商品
		Map sevelMap=new HashMap();
		sevelMap.put("buy_cust_id", cust_id);		
		sevelMap.put("order_state","7");
		sevelCount = this.goodsorderService.getCount(sevelMap);
		
		//等确认收货
		Map reOrderMap=new HashMap();
		reOrderMap.put("buy_cust_id", cust_id);		
		reOrderMap.put("order_state","3");
		reOrder = this.goodsorderService.getCount(reOrderMap);
		//试用订单
		Map trypageMap = new HashMap();
		trypageMap.put("buy_cust_id", cust_id);
		trypageMap.put("order_type","7");
		tryorder = this.goodsorderService.getCount(trypageMap);
		//退款/退货
		Map refundpageMap = new HashMap();
		refundpageMap.put("buy_cust_id", cust_id);
		refundapporder=refundappService.getCount(refundpageMap);
		//预售订单
		Map yushoupageMap = new HashMap();
		yushoupageMap.put("buy_cust_id", cust_id);
		yushoupageMap.put("order_type","6");
		yushouorder = this.goodsorderService.getCount(yushoupageMap);
		//收藏
		Map collMap =new HashMap();
		collMap.put("cust_id", cust_id);
		collCount = this.collectService.getCount(collMap);
		//咨询
		Map consulMap = new HashMap();
		consulMap.put("buy_cust_id",cust_id);
		consulCount = this.consultationService.getCount(consulMap);
		//余额
		memberfund=this.memberfundService.get(cust_id);		
		//积分		
		memberinter=this.memberinterService.get(cust_id);	
		//获取当前所在小时（24小时制）
		Calendar calendar = Calendar.getInstance();
		current_hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));

		advinfo=this.advinfoService.getImg("141");
		if(advinfo!=null){
			url=advinfo.getLink_url();
			img=advinfo.getImg_path();
		}
		//一个月内订单
		Map pageMap = new HashMap();
		pageMap.put("buy_cust_id", cust_id);	
		pageMap.put("start", "0");
		pageMap.put("limit", "5");
		pageMap.put("is_del","1");//加入过滤条件，未逻辑删除的订单
		pageMap.put("de_order", "time");//按时间降序
		goodsorderList = this.goodsorderService.getList(pageMap);
		// 通过解析goodsorderList找出相关的商品信息
		Map map = this.goodsorderService.replaceList(goodsorderList);
		String directOidStr = map.get("directOidStr").toString();
		String orderidStr = map.get("orderidStr").toString();
		// 去掉最后的逗号
		if (orderidStr.indexOf(",") > 0) {
			int len = orderidStr.lastIndexOf(",");
			orderidStr = orderidStr.substring(0, len);
		}
		if (directOidStr != "") {
			// 去掉最后的逗号
			if (directOidStr.indexOf(",") > 0) {
				int len = directOidStr.lastIndexOf(",");
				directOidStr = directOidStr.substring(0, len);
				Map directDetailMap = new HashMap();
				directDetailMap.put("order_id_str", directOidStr);
				directDetaiList = this.directorderdetailService
						.getList(directDetailMap);
			}
		}
		// 获取系统当前时间
		SimpleDateFormat df = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		datenow = df.format(new Date());
		Map detailMap = new HashMap();
		detailMap.put("order_id_s", orderidStr);
		detailList = this.orderdetailService.getList(detailMap);
		//支付方式
		Map orderMap = new HashMap();
		// 0：是 1：否
		orderMap.put("enabled", "0");
		paymentList = paymentService.getList(orderMap);
		//取消订单原因
		Map commparaMap = new HashMap();
		commparaMap.put("para_code", "buy_cancel_order");
		commparaList = commparaService.getList(commparaMap);
		jsontotal=JsonUtil.list2json(commparaList);
		//显示qq呢称
		if(getSession().getAttribute("nickName") != null) {
			nickName = getSession().getAttribute("nickName").toString();
		}
		//显示微信呢称
		if(getSession().getAttribute("weixinName") != null) {
			weixinName = getSession().getAttribute("weixinName").toString();
		}
		return SUCCESS;	
		
	}
	
	/**
	 * @function 功能 商城后台首页  账户中心页面
	 * @author 创建人 HZX
	 */
	public String accountCenter() throws Exception {	
		user_name=this.session_user_name;
		//查找会员名称
		if(user_name.equals("")){
			return SUCCESS;
		}
		memberuser=menberuserService.get(this.session_user_id);
		//判断支付密码
		if(memberuser.getCust_id()!=null && !"".equals(memberuser.getCust_id())){
			memberfund=this.memberfundService.get(memberuser.getCust_id());
			if(memberfund.getPay_passwd()!=null) {
				pay_pass=memberfund.getPay_passwd();
			}
		}
		
		//判断是否已手机验证
		if(!"".equals(memberuser.getIs_check_mobile())){
			is_check_mobile=memberuser.getIs_check_mobile();
		}
		//判断是否已邮箱验证
		if(!"".equals(memberuser.getIs_check_email())){
			is_check_email=memberuser.getIs_check_email();
		}
		
		//显示目前使用的邮箱
		if(memberuser.getEmail()!=null&&!memberuser.getEmail().equals("")){
			email=memberuser.getEmail();
			email=email.substring(0,4) + "****" +email.substring(email.lastIndexOf('@'));
		}
		
		
		
		//显示目前使用的邮箱 格式为159*****124
		if(memberuser.getCellphone()!=null&&!"".equals(memberuser.getCellphone())){
			telphoneStr=memberuser.getCellphone();
			telphoneStr=telphoneStr.substring(0,3) + "*****" +telphoneStr.substring(8,telphoneStr.length());
		}
		
		
		//修改密码问题状态
		//if(memberuser.getPasswd_ques()!=null && !"".equals(memberuser.getPasswd_ques())){
		//	isque="1";
		//}
		//给定密码强度提示
		if(!"".equals(memberuser.getPass_strength())){
			pass_str=memberuser.getPass_strength();
		}
		//判断是否设置密保
		memprotect=this.memprotectService.getByCustID(this.session_cust_id);
		Map isactiveshopmap=new HashMap();
		isactiveshopmap.put("cust_id", this.session_cust_id);
		shopconfigList=shopconfigService.getList(isactiveshopmap);
		
		return SUCCESS;
	}
	/**
	 * @Method Description :商城后台首页 数据统计主页面
	 * @author : HZX
	 * @date : Mar 18, 2014 12:49:53 PM
	 */
	public String datacount() throws Exception {	
		goUrl("datacount");
		
		return SUCCESS;
	}
	/**
	 * @Method Description :商城后台首页没有找到页面
	 */
	public String nopageIndex() throws Exception {	
		goUrl("nopageIndex");
		return SUCCESS;
	}
	/**
	 * @Method Description :查看店铺
	 * @author : HZX
	 * @throws IOException 
	 * @date : May 8, 2014 10:03:37 AM
	 */
	public void lookShop() throws IOException{
		getResponse().sendRedirect("/shopsIndex.action?radom_no="+session_radom_no);
	}
	
	public List getLoglist() {
		return loglist;
	}
	public void setLoglist(List loglist) {
		this.loglist = loglist;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();

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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsque() {
		return isque;
	}

	public void setIsque(String isque) {
		this.isque = isque;
	}

	public String getPass_str() {
		return pass_str;
	}

	public void setPass_str(String pass_str) {
		this.pass_str = pass_str;
	}

	public Shopconfig getShopconfig() {
		return shopconfig;
	}

	public void setShopconfig(Shopconfig shopconfig) {
		this.shopconfig = shopconfig;
	}

	public AreaFuc getAreaFuc() {
		return areaFuc;
	}

	public void setAreaFuc(AreaFuc areaFuc) {
		this.areaFuc = areaFuc;
	}

	public String getArea_String() {
		return area_String;
	}

	public void setArea_String(String area_String) {
		this.area_String = area_String;
	}

	public List getScoreList() {
		return scoreList;
	}

	public void setScoreList(List scoreList) {
		this.scoreList = scoreList;
	}

	public List getAvgscoreList() {
		return avgscoreList;
	}

	public void setAvgscoreList(List avgscoreList) {
		this.avgscoreList = avgscoreList;
	}

	public Busimes getBusimes() {
		return busimes;
	}

	public void setBusimes(Busimes busimes) {
		this.busimes = busimes;
	}

	public Goodsask getGoodsask() {
		return goodsask;
	}

	public void setGoodsask(Goodsask goodsask) {
		this.goodsask = goodsask;
	}

	public Advinfo getAdvinfo() {
		return advinfo;
	}

	public void setAdvinfo(Advinfo advinfo) {
		this.advinfo = advinfo;
	}

	public int getCountNow() {
		return countNow;
	}

	public void setCountNow(int countNow) {
		this.countNow = countNow;
	}

	public int getNotcount() {
		return notcount;
	}

	public void setNotcount(int notcount) {
		this.notcount = notcount;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public int getCountSask() {
		return countSask;
	}

	public void setCountSask(int countSask) {
		this.countSask = countSask;
	}

	public int getNotSask() {
		return notSask;
	}

	public void setNotSask(int notSask) {
		this.notSask = notSask;
	}

	public String getPay_pass() {
		return pay_pass;
	}

	public void setPay_pass(String pay_pass) {
		this.pay_pass = pay_pass;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public IGoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(IGoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public int getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}

	public int getStockCount() {
		return stockCount;
	}

	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}

	public int getLabelCount() {
		return labelCount;
	}

	public void setLabelCount(int labelCount) {
		this.labelCount = labelCount;
	}

	public int getLbCount() {
		return lbCount;
	}

	public void setLbCount(int lbCount) {
		this.lbCount = lbCount;
	}

	public Goodsorder getGoodsorder() {
		return goodsorder;
	}

	public void setGoodsorder(Goodsorder goodsorder) {
		this.goodsorder = goodsorder;
	}

	public int getDaiorderCount() {
		return daiorderCount;
	}

	public void setDaiorderCount(int daiorderCount) {
		this.daiorderCount = daiorderCount;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public int getTodayOrder() {
		return todayOrder;
	}

	public void setTodayOrder(int todayOrder) {
		this.todayOrder = todayOrder;
	}

	public int getPayCount() {
		return payCount;
	}

	public void setPayCount(int payCount) {
		this.payCount = payCount;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Memberfund getMemberfund() {
		return memberfund;
	}

	public void setMemberfund(Memberfund memberfund) {
		this.memberfund = memberfund;
	}

	public Memberinter getMemberinter() {
		return memberinter;
	}

	public void setMemberinter(Memberinter memberinter) {
		this.memberinter = memberinter;
	}

	public int getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(int couponCount) {
		this.couponCount = couponCount;
	}

	public int getBuyerOrder() {
		return buyerOrder;
	}

	public void setBuyerOrder(int buyerOrder) {
		this.buyerOrder = buyerOrder;
	}

	public int getSevelCount() {
		return sevelCount;
	}

	public void setSevelCount(int sevelCount) {
		this.sevelCount = sevelCount;
	}

	public Goodseval getGoodseval() {
		return goodseval;
	}

	public void setGoodseval(Goodseval goodseval) {
		this.goodseval = goodseval;
	}

	public int getSkCount() {
		return skCount;
	}

	public void setSkCount(int skCount) {
		this.skCount = skCount;
	}

	public Orderdetail getOrderdetail() {
		return orderdetail;
	}

	public void setOrderdetail(Orderdetail orderdetail) {
		this.orderdetail = orderdetail;
	}

	public List getLogsList() {
		return logsList;
	}

	public void setLogsList(List logsList) {
		this.logsList = logsList;
	}

	public String getLogin_time() {
		return login_time;
	}

	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}

	public List getGoodsorderList() {
		return goodsorderList;
	}

	public void setGoodsorderList(List goodsorderList) {
		this.goodsorderList = goodsorderList;
	}

	public List getDetailList() {
		return detailList;
	}

	public void setDetailList(List detailList) {
		this.detailList = detailList;
	}
	public String getIs_check_mobile() {
		return is_check_mobile;
	}
	public void setIs_check_mobile(String is_check_mobile) {
		this.is_check_mobile = is_check_mobile;
	}
	public String getIs_check_email() {
		return is_check_email;
	}
	public void setIs_check_email(String is_check_email) {
		this.is_check_email = is_check_email;
	}
	public String getOpername() {
		return opername;
	}
	public void setOpername(String opername) {
		this.opername = opername;
	}
	public List getShopconfigList() {
		return shopconfigList;
	}
	public void setShopconfigList(List shopconfigList) {
		this.shopconfigList = shopconfigList;
	}
	public Sellerscore getSellerscore() {
		return sellerscore;
	}
	public void setSellerscore(Sellerscore sellerscore) {
		this.sellerscore = sellerscore;
	}
	public Memprotect getMemprotect() {
		return memprotect;
	}
	public void setMemprotect(Memprotect memprotect) {
		this.memprotect = memprotect;
	}
	public List getMemprotectList() {
		return memprotectList;
	}
	public void setMemprotectList(List memprotectList) {
		this.memprotectList = memprotectList;
	}
	public int getTodaypayCount() {
		return todaypayCount;
	}
	public void setTodaypayCount(int todaypayCount) {
		this.todaypayCount = todaypayCount;
	}
	public String getYdate() {
		return ydate;
	}
	public void setYdate(String ydate) {
		this.ydate = ydate;
	}
	public int getYOrder() {
		return yOrder;
	}
	public void setYOrder(int order) {
		yOrder = order;
	}
	public int getYpayCount() {
		return ypayCount;
	}
	public void setYpayCount(int ypayCount) {
		this.ypayCount = ypayCount;
	}
	public int getMembercount() {
		return membercount;
	}
	public void setMembercount(int membercount) {
		this.membercount = membercount;
	}
	public int getYmembercount() {
		return ymembercount;
	}
	public void setYmembercount(int ymembercount) {
		this.ymembercount = ymembercount;
	}
	public int getAllgoodscount() {
		return allgoodscount;
	}
	public void setAllgoodscount(int allgoodscount) {
		this.allgoodscount = allgoodscount;
	}
	public int getDowngdscount() {
		return downgdscount;
	}
	public void setDowngdscount(int downgdscount) {
		this.downgdscount = downgdscount;
	}
	public int getAllmembercount() {
		return allmembercount;
	}
	public void setAllmembercount(int allmembercount) {
		this.allmembercount = allmembercount;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public List getArticleList() {
		return articleList;
	}
	public void setArticleList(List articleList) {
		this.articleList = articleList;
	}
	public IMalllevelsetService getMallvelsetService() {
		return mallvelsetService;
	}
	public void setMallvelsetService(IMalllevelsetService mallvelsetService) {
		this.mallvelsetService = mallvelsetService;
	}

	public int getCollCount() {
		return collCount;
	}

	public void setCollCount(int collCount) {
		this.collCount = collCount;
	}

	public int getConsulCount() {
		return consulCount;
	}

	public void setConsulCount(int consulCount) {
		this.consulCount = consulCount;
	}
	
}
