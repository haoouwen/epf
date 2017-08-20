package com.rbt.webaction;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Area;
import com.rbt.model.Directladder;
import com.rbt.model.Directorderdetail;
import com.rbt.model.Directsell;
import com.rbt.model.Goods;
import com.rbt.model.Member;
import com.rbt.model.Shopconfig;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.IArticleService;
import com.rbt.service.ICategoryService;
import com.rbt.service.IDirectladderService;
import com.rbt.service.IDirectorderdetailService;
import com.rbt.service.IPointsgoodsService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IMemberService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.IShopconfigService;
/**
 * @author : CYC
 * @date : Jul 4, 2014 3:46:49 PM
 * @Method Description :积分商城
 */
public class WebpointsgoodsAction extends goodsModelAction implements Preparable{

	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	public Goods goods;
	public Area area;
	public Directsell directsell;
	public Member member;
	public Shopconfig shopconfig;
	public Directladder directladder;
	public Directorderdetail directorderdetail;

	/*******************业务层接口****************/
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	public IAdvinfoService advinfoService;
	@Autowired
	private IPointsgoodsService pointgoodsService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IMemberService memberService;
	@Autowired
	private IShopconfigService shopconfigService;
	@Autowired
	private IOrderdetailService orderdetailService;
	@Autowired
	private IDirectladderService directladderService;
	@Autowired
	private IDirectorderdetailService directorderdetailService;
	@Autowired
	private IArticleService articleService;

	/*********************集合******************/
	public List advinfoList;//广告
	public List pointgoodsList;//预售商品
	public List directladderList;//
	public List one_categoryList;//一级分类
	public List two_categoryList;//二级分类
	public List goodsList;//商品
	public List areaList;//地区
	public List areacharList;//地区
	public List shopconfigList;//店铺
	public List imageList;//图片
	public List catgroupList;//购物车
	public List scoreList;//评价
	public List directorderdetailList;//预售预定订单表
	public Map pointsgoodsMap;//预售

	/*********************字段******************/
	public long difftime;//倒计时
	public String timeout="0";//倒计时
	public String cat_attr;//分类
	public int count;//分页
	public String pointsgoods_id;//积分ID
	public String directprice;//预售价格
	public String pricediff;//差价
	public String pricepercent;//折扣
	public int ordernum;//订单数
	public String oneimg;//一张图片
	public String gid;//商品标识
	public String min_sale_price;//原价即促销最低价
	public String en_name;//获取地区关键字
	public String searchtype;//获取排序参数
	public String searchname;//获取搜索关键字
	public String cssstatus;
	public String sele_area_id;
	public String earnest;//定金
	public String direct_rule;//预售规则
	public String isBuySelf;//是否自己商品

	/**
	 * @author : CYC
	 * @date : Jul 5, 2014 11:04:44 AM
	 * @Method Description :更多积分商品（列表页面）
	 */
	public String moreIndex() throws Exception{
		Map auMap=new HashMap();
		// 获取搜索条件
		String keyword = "";
		if (getRequest().getParameter("wd") != null
				&& !getRequest().getParameter("wd").equals("")) {
			keyword = URLDecoder.decode(getRequest().getParameter("wd"),
					"UTF-8");
			auMap.put("goods_name", keyword);
		}

		// 获取搜索分类条件
		String catEn_name = "";
		if (getRequest().getParameter("catEn_name") != null
				&& !getRequest().getParameter("catEn_name").equals("")) {
			catEn_name = URLDecoder.decode(getRequest().getParameter(
					"catEn_name"), "UTF-8");
			auMap.put("cat_attr", catEn_name);
		}
		auMap.put("difftime","1");//即将上架
		if (!ValidateUtil.isRequired(cat_attr))
			auMap.put("cat_attr", cat_attr);
		count = this.pointgoodsService.getCount(auMap);
		auMap = (HashMap) super.webPageTool(count,auMap);
		pointgoodsList=this.pointgoodsService.getList(auMap);
		return goUrl("integralList");
	}
	
	/**
	 * @author : CYC
	 * @date : Jul 19, 2014 3:02:25 PM
	 * @Method Description :积分详细页
	 */
	public String detail() throws Exception{
		if(pointsgoods_id!=null&&!pointsgoods_id.equals("")){
			pointsgoodsMap =(HashMap)this.pointgoodsService.detail(pointsgoods_id);
			//自定义规格和值
			String goods_id=pointsgoodsMap.get("gid").toString();
			getSepcValueList(goods_id);
			//获取规格值与商品规格比对是否属于规格库中
			getSpecstr(goods_id);
			//direct_rule = this.articleService.get("104").getContent();
			return goUrl("pointsgoodsDetail");
		}else{
			return null;
		}
	}
	public void prepare() throws Exception {
		
	}
}
