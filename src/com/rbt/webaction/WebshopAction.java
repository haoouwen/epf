/*
 * Package:com.rbt.action
 * FileName: WebmallshopAction.java 
 */
package com.rbt.webaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.function.FilterWordFuc;
import com.rbt.function.PageTipFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Collect;
import com.rbt.model.Goods;
import com.rbt.model.Goodsattr;
import com.rbt.model.Member;
import com.rbt.model.Memberuser;
import com.rbt.model.Shopconfig;
import com.rbt.model.Shopmsg;
import com.rbt.model.Shopreplymsg;
import com.rbt.service.IAboutusService;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.IBusimesService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsattrService;
import com.rbt.service.IGoodsevalService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberchannelService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.INavService;
import com.rbt.service.IShopconfigService;
import com.rbt.service.IShopreplymsgService;

/**
 * @function 功能 商城店铺信息action类
 * @author 创建人 LHY
 * @date 创建日期 05 10 15:55:21 CST 2014
 */
@Controller
public class WebshopAction extends WebbaseAction implements Preparable {

	private static final long serialVersionUID = -4371080487351098292L;
	/*******************实体层********************/
	public Shopconfig shopconfig;
	public Memberuser memberuser;
	public Goods goods;
	public Member member;
	public Collect collect;
	private AreaFuc areaFuc;
	public Goodsattr goodsattr;
	public Shopmsg shopmsg;
	public Shopreplymsg shopreplymsg;

	/*******************业务层接口****************/
	@Autowired
	public IShopconfigService shopconfigService;
	@Autowired
	public IAdvinfoService advinfoService;
	@Autowired
	public IMemberuserService memberuserService;
	@Autowired
	public IMemberchannelService memberchannelService;
	@Autowired
	public INavService navService;
	@Autowired
	public IAboutusService aboutusService;
	@Autowired
	public IGoodsService goodsService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IGoodsevalService goodsevalService;
	@Autowired
	public IBusimesService busimesService;
	@Autowired
	public IGoodsattrService goodsattrService;
	@Autowired
	public IShopreplymsgService shopreplymsgService;
	
	/*********************集合********************/
	public List shopconfigList;
	public List memberlinkList;
	public List memberuserList;
	public List bottomnavList;
	public List aboutusIndexList;
	public List goodsList;
	public List goodsRecomList;
	public List membercatRecomList;
	public List newgoodsLsit;
	public List custCatList;
	public List populList;
	public List saleList;
	public List shopmsgList;
	public List replyList;
	public List advinfoList;
	
	/*********************字段********************/
	public List buy_goodsevalList;// 买家的评价
	public List sell_goodsServlList;// 买家评价
	public String sysconfig_cfg_powerby;
	public String sysconfig_cfg_beian;
	public String shop_id;// 商店ID
	// 用户名称
	public String para_temp_code;
	public String shop_cust_id;// 商店客户ID
	public int membercatrecomcount;
	public int count;
	public String lab;// 商品标签
	public String selName;// 搜索条件
	public String uppri;
	public String downpri;
	public String selValue;
	public String type;// 排序，大图小图
	public String show;
	public String viewtype;
	public String littleimg;
	public String bigimg;
	public String msg_content;// 商品店铺信息
	public String shopName;
	public String shopId;
	public String shopQQ;
	public int number;
	public String read_val;// 店铺介绍评价的标识
	public String level;
	public String inro_code;// 评论菜单标识
	public String goods_cust_id;// 商品所属会员cust_id;
	public String rad_num;// 验证码的随机数
	public String commentrand;// 输入框输入的随机数
	public String area_attr;
	public String temp_loc;// 定义模板代码
	public String shopMessage_null_page;// 没有店铺留言时的显示内容
	public String serach_shopGoods_null_page;// 为搜到店铺商品时的显示内容
	public Map indexMap=new HashMap();//取店铺首页数据
	public String user_name;
	public String radom_no;
	
	/**
	 * @author : LHY
	 * @throws IOException
	 * @Method Description :店铺首页绑定
	 */
	@Action(value = "shopsIndex", results = { @Result(name = "index", location = "/WEB-INF/template/shop/${temp_loc}/index.ftl") })
	public String index() throws Exception {
		getRadom();
		if (shop_cust_id != null && !"".equals(shop_cust_id)) {
			shopconfig = this.shopconfigService.getByCustID(shop_cust_id);
			if (shopconfig == null) {
				shopconfig = new Shopconfig();
			}
			verifyShop(shopconfig);
			indexMap=this.shopconfigService.getIndexMap(shopconfig,temp_loc);
			// 热门收藏排行
			collectList = this.goodsService.getHotCollectList(shopconfig
					.getCust_id());
			// 热门销售
			hotsaleList = this.goodsService.getHotSaleList(shopconfig.getCust_id(), "");
		}
		return "index";
	}

	/**
	 * @author : LHY
	 * @throws IOException
	 * @Method Description :店铺列表页绑定
	 */
	@Action(value = "shopList", results = { @Result(name = "getlist", location = "/WEB-INF/template/shop/${temp_loc}/goodslist.ftl") })
	public String goodslist() throws Exception {
		getRadom();
		HttpServletRequest request = getRequest();
		// 显示的样式
		if (show == null || show.equals("")) {
			viewtype = "img";
			bigimg = "/shoptemplate/default/images/termbhBut_15.gif";
			littleimg = "/shoptemplate/default/images/termsaBut_15.gif";
		} else {
			viewtype = show;
			if (viewtype.equals("img")) {
				bigimg = "/shoptemplate/default/images/termbhBut_15.gif";
				littleimg = "/shoptemplate/default/images/termsaBut_15.gif";
			} else {
				bigimg = "/shoptemplate/default/images/termbaBut_15.gif";
				littleimg = "/shoptemplate/default/images/termshBut_15.gif";
			}
		}
		if (shop_cust_id != null && !"".equals(shop_cust_id)) {
			Map map = new HashMap();
			// 取出店铺信息
			shopconfig = shopconfigService.getByCustID(shop_cust_id);
			// 获取商品标签
			lab = request.getParameter("lab");
			// 传入当前的店铺会员id
			map.put("cust_id", shopconfig.getCust_id());
			// 商店左边部分关键字搜索，和价格搜索
			if (selName != null && !"".equals(selName)) {
				map.put("goods_name", selName);
			}
			if (uppri != null && !"".equals(uppri)) {
				map.put("usale_price", uppri);
			}
			if (downpri != null && !"".equals(downpri)) {
				map.put("dsale_price", downpri);
			}
			if (lab != null && !"".equals(lab)) {
				if (lab.equals("1")) {
					// 新品商品
					map.put("lab", "1");
				} else if (lab.equals("2")) {
					// 推荐商品
					map.put("lab", "2");
				} else if (lab.equals("3")) {
					// 人气商品
					map.put("lab", "3");
				}
				// 排序
				setmap(map, type);
			} else {
				// 排序
				setmap(map, type);
			}
			// 所属分类
			String cat_id = this.getRequest().getParameter("cat_id");
			if (cat_id != null && !cat_id.equals("")) {
				map.put("self_cat", cat_id);
			}
			// 是否删除，状态为审核
			map.put("info_state", "1");
			map.put("is_del", "1");
			map.put("is_up", "0");
			// 根据页面提交的条件找出信息总数
			count = this.goodsService.getWebCount(map);
			// 分页插件
			map = super.webPageTool(count, map);
			goodsList = goodsService.getWebList(map);
			// 热门收藏排行
			// 热门收藏排行
			collectList = this.goodsService.getHotCollectList(shopconfig
					.getCust_id());
			// 热门销售
			hotsaleList = this.goodsService.getHotSaleList(shopconfig
					.getCust_id(), "");
		}
		// 搜索为空时
		if (goodsList == null || goodsList.size() == 0) {
			serach_shopGoods_null_page = PageTipFuc
					.getPageCon("serach_shopGoods_null_page");
		}
		return "getlist";
	}

	/**
	 * @author : LHY
	 * @date : Feb 26, 2014 11:21:00 AM
	 * @Method Description : 店铺商品详细页面
	 */
	@Action(value = "detail", results = { @Result(name = "detail", location = "/WEB-INF/template/shop/${temp_loc}/goodsdetail.ftl") })
	public String goodsdetail() throws Exception {
		getRadom();
		HttpServletRequest request = getRequest();
		// 获取商品id
		String goods_id = request.getParameter("goods_id");
		Shopconfig sf = null;
		if (goods_id != null && !"".equals(goods_id)) {
			goods = this.goodsService.get(goods_id);
			goods_cust_id = goods.getCust_id();
			Map map = new HashMap();
			map.put("goods_id", goods.getGoods_id());

			// 根据id找到对应的商铺信息
			sf = this.shopconfigService.getByCustID(goods.getCust_id());
			if (sf != null) {
				shopName = sf.getShop_name();
				shopQQ = sf.getQq();
			}
		}
		number = 1;
		// 热门收藏排行
		collectList = this.goodsService.getHotCollectList(shopconfig
				.getCust_id());
		// 热门销售
		hotsaleList = this.goodsService.getHotSaleList(shopconfig.getShop_id(),
				"");
		return "detail";
	}

	/**
	 * 店铺介绍
	 * 
	 * @author LHY
	 * @return
	 */
	@Action(value = "shopIntroduce", results = { @Result(name = "introduce", location = "/WEB-INF/template/shop/${temp_loc}/shopIntroduce.ftl") })
	public String shopIntroduce() {
		getRadom();
		String fag = "";
		shopconfig = shopconfigService.getByCustID(shop_cust_id);
		shopconfig.setArea_attr(areaFuc.getAreaNameByMap(shopconfig
				.getArea_attr()));
		Map buyMap = new HashMap();
		Map sellMap = new HashMap();
		// 获取买家评价信息，2获取全部，1好评，0中评，-1差评
		if (read_val != null && !"".equals(read_val)) {
			if (read_val.equals("2")) {
			} else if (read_val.equals("1")) {
				buyMap.put("g_eval", "1");
			} else if (read_val.equals("0")) {
				buyMap.put("g_eval", "0");
			} else if (read_val.equals("-1")) {
				buyMap.put("g_eval", "-1");
			}
		} else {
			// 默认选择全部
			read_val = "2";
		}
		buyMap.put("explan_cust_id", shopconfig.getCust_id());
		// 根据页面提交的条件找出信息总数
		count = this.goodsevalService.getWebCount(buyMap);
		buyMap = super.webPageTool(count, buyMap);
		buy_goodsevalList = this.goodsevalService.getWebList(buyMap);
		return "introduce";
	}

	/**
	 * 获取店铺的留言信息
	 * 
	 * @author LHY
	 * @return
	 */
	@Action(value = "shopMessage", results = { @Result(name = "message", location = "/WEB-INF/template/shop/${temp_loc}/shopMessage.ftl") })
	public String getShopMessage() {
		getRadom();
		shopconfig = shopconfigService.getByCustID(shop_cust_id);
		// 搜索为空时
		if (shopmsgList == null || shopmsgList.size() == 0) {
			shopMessage_null_page = PageTipFuc
					.getPageCon("shopMessage_null_page");
		}
		// 取出留言回复表的对应信息
		Map map = new HashMap();
		replyList = this.shopreplymsgService.getList(map);
		return "message";
	}

	/**
	 * 添加留言前台页面的店铺
	 * 
	 * @author LHY
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@Action(value = "addmsg", results = { @Result(name = "message", location = "/WEB-INF/template/shop/${temp_loc}/shopMessage.ftl") })
	public String insertWebMsg() throws IOException, ServletException {
		getRadom();
		// 从session里获取验证码
		rad_num = (String) this.getRequest().getSession().getAttribute(
				"sysrand");
		// 比较输入的和生成的验证码是否一致
		if (!commentrand.equals(rad_num)) {
			this.addActionMessage("验证码不一致！");
			return getShopMessage();
		}
		if (msg_content == null || msg_content.equals("")) {
			this.addActionMessage("请输入留言内容！");
			return getShopMessage();
		}
		if(msg_content.indexOf(">")>-1){
			this.addActionMessage("不能输入标签！");
			return getShopMessage();
		}
		getIndexTop(shop_cust_id);
		// 获取头部信息
		commentrand = "";
		getResponse().sendRedirect("/shopMessage.action?radom_no="+radom_no);
		return getShopMessage();
	}

	/**
	 * @author LHY 设置map值根据页面传入的数据，将数据按升序或降序排序
	 * @param map
	 * @param type
	 */
	public void setmap(Map map, String type) {
		// type传入的类型，如：时间，销售，价格
		if (type != null && !"".equals(type)) {
			if ("sales".equals(type)) {
				map.put("sale_num_desc", "DESC");
			} else if ("price".equals(type)) {
				map.put("price_desc", "DESC");
			} else if ("price_asc".equals(type)) {
				map.put("price_asc", "ASC");
			} else if ("newgoods".equals(type)) {
				map.put("time_asc", "DESC");
			} else if ("collect".equals(type)) {
				map.put("collect_desc", "DESC");
			}
		}
	}

	/**
	 * 检查会员是否登录才可进行留言，
	 * 
	 * @author LHY
	 * @throws IOException
	 */
	public void checkCustId() throws IOException {
		PrintWriter out = getResponse().getWriter();
		getRequest().setCharacterEncoding("UTF-8");
		getResponse().setCharacterEncoding("UTF-8");
		getResponse().setContentType("text/html;charset=UTF-8;");
		//获取留言内容
		String content = URLDecoder.decode(getRequest()
				.getParameter("ccontent"), "UTF-8");
		// 判断session里是否有值
		if (this.session_cust_id.equals("") || this.session_cust_id == null) {
			out.write("1");
		} else {
			// 判断是否卖家给自己留言
			String sell_cust_id = getRequest().getParameter("sell_cust_id");
			if (sell_cust_id.equals(this.session_cust_id)) {
				out.write("3");
			}else if(FilterWordFuc.isFilterWord(content)){
				out.write("4");//验证存在敏感字
			}else {
				out.write("2");
			}
		}
	}

	/**
	 * @author : LHY
	 * @throws IOException
	 * @Method Description :绑定店铺底部信息
	 */
	public void getIndexBottom() {
		// 获取商城底部信息
		HashMap navMap = new HashMap();
		navMap.put("isshow", "0");
		navMap.put("nav_post", "3");
		bottomnavList = navService.getList(navMap);
		// 获取商城底部帮助中心信息
		gethelplist();
	}

	/**
	 * @author : LHY
	 * @throws IOException
	 * @Method Description :获取帮助中心信息列表
	 */
	public void gethelplist() {
		// 获取商城底部帮助中心信息
		HashMap aboutusMap = new HashMap();
		aboutusMap.put("para_code", "help_type");
		aboutusIndexList = aboutusService.getList(aboutusMap);
	}

	public void getRadom(){
		this.radom_no=(String) ActionContext.getContext().get("radom_no");
	}
	
	/**
	 * @author : LHY
	 * @throws IOException
	 * @Method Description :url获取cust_id信息
	 */
	public String getCustId() throws Exception {
		// 根据user_name查询cust_id
		String cust_id = "";
		HttpServletRequest request = getRequest();
		HttpSession session=request.getSession();
		// 获取url参数
		if(!ValidateUtil.isRequired(request.getParameter("radom_no"))){
			radom_no=new String(getRequest().getParameter("radom_no").getBytes("iso-8859-1"),"UTF-8");
			ActionContext ctx = ActionContext.getContext();
			ctx.put("radom_no", radom_no);
		}
		
		this.para_temp_code = request.getParameter("para_temp_code");
		Map map=new HashMap();
		map.put("radom_no", radom_no);
		List shopList=this.shopconfigService.getList(map);
		if (shopList != null && shopList.size()>0) {
			Map shopMap=(HashMap)shopList.get(0);
			cust_id = shopMap.get("cust_id").toString();
		}
		return cust_id;
	}

	/**
	 * @author : LHY
	 * @date : Feb 26, 2014 11:33:28 AM
	 * @Method Description :初始化加载数据
	 */
	@SuppressWarnings("static-access")
	public void prepare() throws Exception {
		// 通过会员名称获取cust_id
		this.shop_cust_id = getCustId();
		temp_loc = SysconfigFuc.getSysValue("cfg_mem_default_tem");
		// 获取shopconfig配置信息
		if (shop_cust_id != null && !"".equals(shop_cust_id)) {
			// 取出店铺信息
			shopconfig = shopconfigService.getByCustID(shop_cust_id);
			if (shopconfig != null && shopconfig.getArea_attr() != null) {
				// 转换店铺地址名称
				shopconfig.setArea_attr(areaFuc.getAreaNameByMap(shopconfig
						.getArea_attr()));
				// 地址数字串
				area_attr = shopconfig.getArea_attr();
				// 绑定店铺头部信息
				getIndexTop(shopconfig.getCust_id());
				// 绑定店铺底部信息
				getIndexBottom();
				// 获取商品分类
				getMembercat(shopconfig.getCust_id());
				// 获取店铺模板
				if (shopconfig.getTemplate_code() != null
						&& !shopconfig.getTemplate_code().equals("")) {
					// 获取用户相应的模板
					temp_loc = shopconfig.getTemplate_code();
				} else {
					// 系统配置的默认模板
					temp_loc = SysconfigFuc.getSysValue("cfg_mem_default_tem");
				}
			}
		}
		this.setTemp_loc(temp_loc);
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Shopmsg getShopmsg() {
		return shopmsg;
	}

	public void setShopmsg(Shopmsg shopmsg) {
		this.shopmsg = shopmsg;
	}

	public String getTemp_loc() {
		return temp_loc;
	}

	public void setTemp_loc(String temp_loc) {
		this.temp_loc = temp_loc;
	}
	
}
