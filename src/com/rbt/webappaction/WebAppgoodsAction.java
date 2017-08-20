package com.rbt.webappaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.JsonUtil;
import com.rbt.common.util.PageUtil;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.function.FilterWordFuc;
import com.rbt.function.PageTipFuc;
import com.rbt.function.SalegoodsFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.index.SearchIndex;
import com.rbt.solr.*;
import com.rbt.model.Comsumer;
import com.rbt.model.Coupon;
import com.rbt.model.Goods;
import com.rbt.model.Goodsbrand;
import com.rbt.model.Member;
import com.rbt.model.Aftersales;
import com.rbt.model.Consultation;
import com.rbt.model.Navtab;
import com.rbt.model.Redpacket;
import com.rbt.model.Redsumer;
import com.rbt.model.Salegoods;
import com.rbt.model.Shopconfig;
import com.rbt.service.IAssociationkeywordsService;
import com.rbt.service.ICollectService;
import com.rbt.service.IComsumerService;
import com.rbt.service.ICouponService;
import com.rbt.service.IGoodsService;
import com.rbt.service.ICartgoodsService;
import com.rbt.service.IGoodsevalService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.INavigationService;
import com.rbt.service.INavtabService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.IRedpacketService;
import com.rbt.service.IRedsumerService;
import com.rbt.service.ISalegoodsService;
import com.rbt.service.IShopconfigService;
import com.rbt.service.IMemberService;
import com.rbt.service.IConsultationService;

public class WebAppgoodsAction extends WebAppgoodsModelAction implements Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5736351986871519325L;
	/*******************实体层****************/
	public Member member;
	public Goods goods;
	public Aftersales aftersales;
	public Shopconfig shopconfig;
	public Salegoods  salegoods;
    public Coupon coupon;
    public Redpacket redpacket;
    public Goodsbrand goodsbrand;
	/*******************业务层接口****************/
	@Autowired
	private IShopconfigService shopconfigService;
	
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IGoodsorderService goodsorderService;
	@Autowired
	private IMemberService memberService;
	@Autowired
	private IGoodsevalService goodsevalService;
	@Autowired
	private IConsultationService consultationService;
	@Autowired
	private ICartgoodsService cartgoodsService;
	@Autowired
	public ICollectService collectService;
	@Autowired
	private IOrderdetailService orderdetailService;
	@Autowired
    private INavigationService navigationService;
	@Autowired
    private INavtabService navtabService;
	@Autowired
	private ISalegoodsService salegoodsService;
	@Autowired
	private IAssociationkeywordsService associationkeywordsService;
	@Autowired
	private ICouponService couponService;
	@Autowired
	private IRedpacketService redpacketService;
	@Autowired
	private IComsumerService comsumerService;
	@Autowired
	private IRedsumerService redsumerService;
	
	/*********************集合******************/
	public List goodsList;//商品
	public List goodsevalList;// 记录商品评价表信息信息集合
	public List relateGoodsList;// 相关商品
	public List specnameList;//规格
	public List specsizevalueList;
	public List cateList;//分类
	public List cartgoodsList;// 购物车列表
	public List shopList;// 店铺列表
	public Map goodsListMap;//商品
	public String tab_number;
	public Navtab navtab;
	public String cat_attr_top_s;
	public List baocartgoodsList;//保税购物车列表
	public List baoshopList;
	public List couponList; //优惠券列表
	public List redpacketList;//红包
	public List getCouponByGoodsList;//商品详细获取可以领取优惠券信息

	/*********************字段******************/
	private String module_type = "goods";//模块
	public String key_word;// 搜索关键字
	public String en_word;//
	public String search_null_page;// 搜索为空页面
	public String catEn_name;//分类英文
	public String catName;//分类名称
	public String catList_id;//分类ID
	public String shop_cust_id;//店铺者ID
	public String selName;//左边部分关键字搜索，和价格搜索
	public String uppri;//最高
	public String downpri;//最低
	public String selValue;//
	public String type;//类型
	public String gid;// 商品标识
	public String imgGroup;//图片组
	public String sort;//排序
	public String min_price;//最小价格
	public String max_price;//最大价格
	public String goods_cat_attr_s;// 列表页分类搜索
	public String brand_id_s;//品牌ID
	public String ship_addr;//送货地址
	public String brand_name;// 品牌名称
	public String goods_place;//产地
	public String specstr_s;// 商品规格相关
	public String cust_name;// 会员相关
	public String user_name;//用户名
	public String goods_id;//商品ID
	public String c_type;
	public String g_eval;
	public String e_type;
	public String indexing;// 系统索引方式0表示solr ; 1 表示lucene
	public String isBuySelf;// 是否购买自己的商品
	public String loc;// 跳回登录前的位置
	public int evalNum;//评价数
	public int count;//总量
	public int start_num;// 起批量
	public int monthSaleNum;// 商品订单相关
	public double start_pri;// 起批单价
	public double start_priall;// 起批价格
	public String flag_active_state;//标记活动状态
	public String active_price;//团购的价格
	public String activeId;//活动编号
	public String active_MaxBuy;//活动购买的最大数
	public int shopCount;
	public String radom_no;//店铺随机数
	public int eval_one;
	public int eval_two;
	public int eval_three;
	public String cfg_freight = SysconfigFuc.getSysValue("cfg_freight");
	public boolean is_collect=false;
	public String order_id_s;
    public String custnum;
    public String registertype;
    public String order_by_s;
  //列表显示方式 0小图显示 ，1大图显示
	public String listshow="0";
	public String sale_id;//商品促销ID
	public String sale_names;//促销活动名称
	public String coupon_tip="";
	public String redpacket_tip="";
	public String coupon_id;
	public String redpacket_id;
	public long difftime=0;//倒计时
	String timeout="0";//时间结束
	public String fg_ids;//赠品ID;
	public String platformType="0";//0表示触屏版端 1表示APP
	public String catID;
	/**
	 * @author : WXP
	 * @throws Exception
	 * @date : Mar 13, 2014 1:26:22 PM
	 * 
	 * @Method Description : 商品列表页
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		
		goodsListMap=this.goodsService.getGoodsList(catEn_name,catID,postsb);
		indexing = SysconfigFuc.getSysValue("cfg_indexing");// 获取系统索引方式0表示solr ;
		// 1 表示lucene
		if (indexing.equals("0")) {
			// toSolrIndex();
			return goUrl("mbProductList");
		} else {
			//判断搜索的关键字是否存在非法字符
			if(FilterWordFuc.reqFilterKeyword()==true){
				toLuceneIndex(goodsListMap);
			}
			return goUrl("mbProductList");
		}

	}
	
	/**
	 * @author : HZX
	 * @throws UnsupportedEncodingException
	 * @date : Jun 24, 2014 10:47:45 AM
	 * @Method Description :solr索引
	 */
	public void toSolrIndex() throws UnsupportedEncodingException {
		//数据封装到Map
		Map map=new HashMap();
		map.put("key_word", key_word);
		map.put("module_type", module_type);
		map.put("brand_id_s",  brand_id_s);
		map.put("specstr_s", specstr_s);
		map.put("goods_cat_attr_s", goods_cat_attr_s);
		map.put("area_attr_s", area_attr_s);
		map.put("max_price", max_price);
		map.put("min_price", min_price);
		map.put("catList_id", catList_id);
		map.put("uppri", uppri);
		map.put("sort", sort);
		map.put("downpri", downpri);
		map.put("shop_cust_id", shop_cust_id);
		map.put("selName", selName);
		//信息处理
		ModifiableSolrParams params=this.goodsService.toSolrIndex(map);
		// 根据页面提交的条件找出信息总数
		SolrUtil su = new SolrUtil();
		// 搜索到的数据
		count = su.getCount(params);
		// 分页插件
		indexPageTool(count, params);
		// 开启高亮
		su.highlight("goods_name", params);
		// 查询列表
		goodsList = su.search(params);
		// 品牌facet查询
		goodsBrandList = su.facetSearch(params, "brand_id", "");
		// 分类facet查询
		cateList = su.facetSearch(params, "cat_attr", "");
		System.out.println();
		System.out.println("====================== params:" + params);
		// ------------------------索引查询结束--------------------------------
		// 搜索为空时
		if (goodsList == null || goodsList.size() == 0) {
			search_null_page = PageTipFuc.getPageCon("serach_null_page");
		}
		// seo列表页的设置
		goods_cat = CategoryFuc.getCateNameByMap(catList_id);// 商品所属分类
		Map seoMap = new HashMap();
		seoMap.put("goods_cat", goods_cat);
		seoMap.put("search_wd", key_word);
		setSeoPage("goodslist", seoMap);

	}

	/**
	 * @author : HZX
	 * @throws Exception
	 * @date : Jun 24, 2014 10:45:47 AM
	 * @Method Description :lucene索引
	 */
	public void toLuceneIndex(Map goodsListMap) throws Exception {
		// 构造list列表搜索条件
		List shList = new ArrayList();
		// 找通过审核的记录
		shList = normalSearch("info_state", "1", shList);
		Sort so = new Sort();
		// 根据条件查找商品
		Map goodsMap1 = new HashMap();
		// 返回按关键字查找列表的次数，插入关键字表
		if (key_word != null && !key_word.equals("")) {
			key_word = URLDecoder.decode(key_word, "UTF-8");
		} else {
			reqKeyword("goods_name", module_type, goodsMap1);
			if(!validateFactory.isRequired(searchText)){
				key_word = searchText;
			}
		}
		if (key_word != null && !"".equals(key_word)) {
			// 搜索商品名称
			key_word=key_word.trim().replace("[", "").replace("]", "").replace("(", "").replace(")", "")
			.replace("*", "").replace("+", "").replace("{", "").replace("}", "");
			shList = normalSearch("goods_name", key_word, shList);

		}
		// 商品品牌
		if (brand_id_s != null && !"".equals(brand_id_s)) {
			shList = normalSearch("brand_id", brand_id_s, shList);
		}
		// 商品规格
		if (specstr_s != null && !"".equals(specstr_s)) {
			shList = normalSearch("specstr", specstr_s, shList);
		}
		// 分类筛选
		if (goodsListMap.get("catList_id") != null && !"".equals(goodsListMap.get("catList_id").toString())) {
			this.goods_cat_attr_s=goodsListMap.get("catList_id").toString();
			shList = normalSearch("cat_attr", goodsListMap.get("catList_id").toString(), shList);
		}
		
		// 下拉联想词分类过滤
		if (cat_attr_top_s != null && !"".equals(cat_attr_top_s)) {
			//如果分类和关键字同时存在的话 以关键字为主要
			if(cat_attr_top_s.contains(",0")){
				cat_attr_top_s=cat_attr_top_s.replace(",0", "");
			}
			cat_attr_top_s=CategoryFuc.getLastCateID(cat_attr_top_s);
			shList = normalSearch("cat_attr", cat_attr_top_s, shList);
		}else {
			//分类筛选-主要是头部搜索
			String ass_cat_atrr="";//通过联想词获得分类
			ass_cat_atrr=associationkeywordsService.associationToCategory(key_word);
			// 默认搜索关键字 先通过联想词获取分类过滤
			if (ass_cat_atrr != null && !"".equals(ass_cat_atrr)) {
				if(ass_cat_atrr.contains(",0")){
					ass_cat_atrr=ass_cat_atrr.replace(",0", "");
				}
				ass_cat_atrr=CategoryFuc.getLastCateID(ass_cat_atrr);
				shList = normalSearch("cat_attr", ass_cat_atrr, shList);
			}
		}
		
		
		// 地区过滤
		if (area_attr_s != null && !"".equals(area_attr_s)) {
			shList = normalSearch("area_attr", area_attr_s, shList);
		}
		// 未被删除商品
		shList = normalSearch("is_del", "1", shList);
		// 上架商品
		shList = normalSearch("is_up", "0", shList);
		
		// 排序
		if (sort != null && !"".equals(sort)) {
			if (sort.equals("0")) {
			} else if (sort.equals("price_asc") && order_by_s.equals("price")) {
				SortField sf = new SortField("lpad_price", SortField.STRING,
						false);// 价格升序
				so.setSort(new SortField[] { sf });
			} else if (sort.equals("price_desc") && order_by_s.equals("price")) {
				SortField sf = new SortField("lpad_price", SortField.STRING,
						true);// 价格降序
				so.setSort(new SortField[] { sf });
			} else if (sort.equals("sale_num_asc") && order_by_s.equals("salenum")) {
				SortField sf = new SortField("order_num", SortField.STRING,
						false);// 销售量升序
				so.setSort(new SortField[] { sf });
			}else if (sort.equals("sale_num_desc") && order_by_s.equals("salenum")) {
				SortField sf = new SortField("order_num", SortField.STRING,
						true);// 销售量降序
				so.setSort(new SortField[] { sf });
			}else if (sort.equals("collNum_asc") && order_by_s.equals("collnum")) {
				SortField sf = new SortField("collNum", SortField.STRING,
						false);// 人气升序
				so.setSort(new SortField[] { sf });
			}else if (sort.equals("collNum_desc") && order_by_s.equals("collnum")) {
				SortField sf = new SortField("collNum", SortField.STRING,
						true);// 人气降序
				so.setSort(new SortField[] { sf });
			}else if (sort.equals("in_date_desc")) {
				SortField sf = new SortField("in_date", SortField.STRING, true);// 订单降序
				so.setSort(new SortField[] { sf });
				
			}

		}
		// 价格搜索
		if (min_price != null && !"".equals(min_price) && max_price != null
				&& !"".equals(max_price)) {
			// 最低价
			String l_price = fullBit(min_price);
			l_price = isRmb(l_price);
			// 最高价
			String h_price = fullBit(max_price);
			h_price = isRmb(h_price);
			shList = rangeSearch("lpad_price", l_price, h_price, shList);
		}

		// 左边部分关键字搜索，和价格搜索
		if (selName != null && !"".equals(selName)) {
			shList = normalSearch("goods_name", selName, shList);
		}
		// 价格搜索
		if (uppri != null && !"".equals(uppri) && downpri != null
				&& !"".equals(downpri)) {
			// 最低
			String l_price = fullBit(uppri);
			l_price = isRmb(l_price);
			// 最高
			String h_price = fullBit(downpri);
			l_price = isRmb(l_price);
			shList = rangeSearch("lpad_price", l_price, h_price,shList);
		}

		// 根据商品的店铺id搜索相关商品
		if (shop_cust_id != null && !"".equals(shop_cust_id)) {
			shList = normalSearch("cust_id", shop_cust_id, shList);
		}
		// 所属分类
		String cat_id = this.getRequest().getParameter("goods_cat_attr_s");
		if (cat_id != null && !cat_id.equals("")) {
			shList = normalSearch("cat_attr", cat_id, shList);
		}
		// 左边的排序
		setmap(so, shList, type);
		// 根据页面提交的条件找出信息总数
		SearchIndex si = new SearchIndex("goods");

		// 分类信息分组
		cateList = si.catInfoNum(shList);
		PageUtil page = new PageUtil();
		// 根据页面提交的条件找出信息总数
		goodsBrandList = si.getGoodsBrand(shList);
		// 搜索到的数据
		count = si.getCount(shList);
		// 分页插件
		lucenePageTool(count);
		goodsList = si.search(shList, so, pages_s, webappPageSize_s);
		String member_level = "";
		if(!ValidateUtil.isRequired(this.session_cust_id)) {
			//获取会员对象
			member = this.memberService.get(this.session_cust_id);
			member_level = member.getBuy_level();
		}
		goodsList = SalegoodsFuc.replaceList(goodsList, "1", member_level);
		// 搜索为空时
		if (goodsList == null || goodsList.size() == 0) {
			search_null_page = PageTipFuc.getPageCon("serach_null_page");
		}
		catList_id=(String) goodsListMap.get("catList_id");
		goods_cat = CategoryFuc.getCateNameByMap(catList_id);// 商品所属分类
		// seo列表页的设置
		Map seoMap = new HashMap();
		seoMap.put("goods_cat", goods_cat);
		seoMap.put("search_wd", key_word);
		setSeoPage("goodslist", seoMap);
		
	}
		
	/**
	 * @Method Description :
	 * @author : HZX
	 * @throws Exception 
	 * @date : Apr 22, 2015 1:54:41 PM
	 */
	public void pageList() throws Exception{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		pages_s = 1;
		StringBuffer sb = new StringBuffer();
		if (request.getParameter("cp") != null&&!"".equals(request.getParameter("cp"))) {
			pages_s = Integer.parseInt(request.getParameter("cp"));
		}
		key_word=request.getParameter("key_word");
		catEn_name=request.getParameter("catEn_name");
		list();
		if(goodsList!=null&&goodsList.size()>0){
			for(int i=0;i<goodsList.size();i++){
				Map cMap=(Map) goodsList.get(i);
				if(cMap!=null&&cMap.get("goods_id")!=null){
					sb.append(
					       "<li><div class=\"bPorCont\">"
					       +" <a href=\"/webapp/goodsdetail/"+cMap.get("goods_id").toString()+".html\"><img src=\""+cMap.get("list_img").toString()+"\"></a>"
					       +" <p><b>￥"+cMap.get("sale_price").toString()+"</b></p>"
					       +"  <p><a href=\"/webapp/goodsdetail/"+cMap.get("goods_id").toString()+".html\">"+cMap.get("goods_name").toString()+"</a></p>"
					       +" </div><br class=\"clear\"/></li>"
					      );
				}
			}
		}
		PrintWriter out = response.getWriter();
		// 如果列表存在数据则输出，否则则输出空
		out.write(sb.toString());
	}
	
	/**
	 * @Method Description :
	 * @author : HZX
	 * @throws Exception 
	 * @date : Apr 22, 2015 1:54:41 PM
	 */
	@SuppressWarnings("unchecked")
	public void pageLists() throws Exception{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		pages_s = 1;
		if (request.getParameter("cp") != null) {
			pages_s = Integer.parseInt(request.getParameter("cp"));
		}
		key_word=request.getParameter("key_word");
		catEn_name=request.getParameter("catEn_name");
		list();
		String jsonStr="";
		if(goodsList!=null&&goodsList.size()>0){
			for(int i=0;i<goodsList.size();i++){
				Map cMap=(Map) goodsList.get(i);
				if(cMap!=null&&cMap.get("goods_id")!=null){
					if(cMap.get("list_img")==null||"".equals(cMap.get("list_img").toString())){
						cMap.put("list_img", cfg_webappdefaultpic);
					}
					if(cMap.get("goods_name")==null||"".equals(cMap.get("goods_name").toString())){
						cMap.put("goods_name", "--");
					}
					if(cMap.get("sale_price")==null||"".equals(cMap.get("sale_price").toString())){
						cMap.put("sale_price", "0");
					}
					if(cMap.get("market_price")==null||"".equals(cMap.get("market_price").toString())){
						cMap.put("market_price", "0");
					}
					if(cMap.get("order_num")==null||"".equals(cMap.get("order_num").toString())){
						cMap.put("order_num", "0");
					}
					if(cMap.get("eval_num")==null||"".equals(cMap.get("eval_num").toString())){
						cMap.put("eval_num", "0");
					}
				}
			}
			jsonStr=JsonUtil.list2json(goodsList);
		}
		PrintWriter out = response.getWriter();
		// 如果列表存在数据则输出，否则则输出空
		out.print(jsonStr.toString());
		
	}
	
	
	/**
	 * @author LHY 设置map值根据页面传入的数据，将数据按升序或降序排序
	 * @param map
	 * @param type
	 */
	public void setmap(Sort sort, List shList, String type) {
		// type传入的类型，如：时间，销售，价格
		if (type != null && !"".equals(type)) {
			if ("sales".equals(type)) {
				// map.put("sale_num_desc","DESC");
				SortField sf = new SortField("lpad_price", SortField.STRING,
						true);// 降序
				sort.setSort(new SortField[] { sf });
			} else if ("price".equals(type)) {
				// map.put("price_desc","DESC");
				SortField sf = new SortField("price_desc", SortField.STRING,
						true);// 降序
				sort.setSort(new SortField[] { sf });
			} else if ("price_asc".equals(type)) {
				// map.put("price_asc","ASC");
				SortField sf = new SortField("price_asc", SortField.STRING,
						false);// 升序
				sort.setSort(new SortField[] { sf });
			} else if ("newgoods".equals(type)) {
				// map.put("time_asc","DESC");
				SortField sf = new SortField("time_asc", SortField.STRING,false);// 升序
				sort.setSort(new SortField[] { sf });
			} else if ("collect".equals(type)) {
				// map.put("collect_desc","DESC");
				SortField sf = new SortField("collect_desc", SortField.STRING,
						true);// 降序
				sort.setSort(new SortField[] { sf });
			}
		}
	}

	/**
	 * @author : WXP
	 * @throws IOException 
	 * @date Feb 20, 2014 9:39:25 AM
	 * @Method Description :
	 */
	public String detail() throws IOException  {
		try {
			if (gid != null && !gid.equals("")) {
				if(getRequest().getParameter("custnum")!=null){
					custnum = getRequest().getParameter("custnum").toString();
				}
				if(getRequest().getParameter("registertype")!=null){
					registertype = getRequest().getParameter("registertype").toString();
				}
				// 获取商品对象
				goods = this.goodsService.get(gid);
				//非法进入的直接跳转到提示页面
				if(goods==null){
					  getResponse().sendRedirect(Constants.WEBAPP_NOT_GOODS_PAGE);
					  return null;
				}
				// 校验商品
				if (verifyGoods(goods)) {
					return null;
				}
				// 会员标识
				String cust_id = goods.getCust_id();
				// 商城名称
				member = this.memberService.get(cust_id);
				if (member != null) {
					cust_name = member.getCust_name();
				}
				// 获取平台导航位置
				goods_cat = goods.getCat_attr();
				if (goods_cat != null && !"".equals(goods_cat)) {
					String postStrID = goods_cat.substring(goods_cat.lastIndexOf(",") + 1, goods_cat.length());postsb.setLength(0);
					getPathUrl(getpostID(postStrID));
				}
				// 获取商品品牌
				goodsbrand = goodsbrandService.get(goods.getBrand_id());
				if(goodsbrand!=null&&goodsbrand.getBrand_name()!=null){
					brand_name =goodsbrand.getBrand_name();
				}else {
					brand_name ="-";
				}
				String member_level = "";
				if(!ValidateUtil.isRequired(this.session_cust_id)) {
					//获取会员对象
					member = this.memberService.get(this.session_cust_id);
					member_level = member.getBuy_level();
				}
				//判断商品是否有做促销活动
				String s_id = SalegoodsFuc.getSaleId(goods, "1", member_level);
				//判断商品是否有做促销活动
				if(!ValidateUtil.isRequired(s_id)){
					String[] s_ids = s_id.trim().split(",");
					salegoods = this.salegoodsService.get(s_ids[0]);
					//是否限时倒计时
					limit_time = salegoods.getLimit_time();
					//获取倒计时时间秒数
					SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if(salegoods.getEnd_time()!=null){
						Date date = sd.parse(salegoods.getEnd_time());
						difftime =date.getTime() - new Date().getTime();
						if(0 > difftime){
							timeout = "1";
						}
					}
					  //获取活动名称和赠品ID
					for(int i = 0; i < s_ids.length; i ++) {
					  salegoods = this.salegoodsService.get(s_ids[i]);
					  if(i == 0) {
						  sale_names = salegoods.getSale_name();
					  }else {
						  sale_names += " " + salegoods.getSale_name();
					  }
					  if(salegoods.getCoupon_state().equals("7")) {
						  if(ValidateUtil.isRequired(fg_ids)) {
							  fg_ids = salegoods.getCoupon_plan();
						  }else {
							  fg_ids += "," + salegoods.getCoupon_plan();
						  }
					  }
					}
				}
				getCouponByGoodsList=couponService.getCouponByGoodsID(goods.getGoods_id(), member_level);
				// 获取最低价、最高价
				getLowerOrHeightPrice(gid, sale_id);
				// 自定义规格和值
				getSepcValueList(gid);
				// 获取规格值
				getSpecstr(gid);
				// 自定义属性
				getSelfAttr(gid);
				// 获取系统参数组与参数值
				getParagroup(gid, goods_cat);
				// 处理商品图片
				if (goods.getImg_path()!= null) {
					imgGroup = goods.getImg_path();
				}
				//获取产地
				if (!ValidateUtil.isRequired(goods.getGoods_place())) {
					goods_place = AreaFuc.getAreaNameByMap(goods.getGoods_place());
				}
				// 获取相关商品
				String relate_goods = goods.getRelate_goods();
				if (relate_goods != null && !relate_goods.equals("")) {
					Map relateMap = new HashMap();
					relateMap.put("sgis", goods.getRelate_goods());
					relateMap.put("is_del", "1");
					relateMap.put("is_up", "0");
					relateMap.put("start", "0");
					relateMap.put("limit", "6");
					relateGoodsList = this.goodsService.getWebList(relateMap);
				}
				// 获取商品评价数
				Map evalMap = new HashMap();
				evalMap.put("goods_id", gid);
				evalNum = this.goodsevalService.getWebCount(evalMap);
				evalMap.put("g_eval", "1");
				eval_one=this.goodsevalService.getWebCount(evalMap);
				evalMap.put("g_eval", "0");
				eval_two=this.goodsevalService.getWebCount(evalMap);
				evalMap.put("g_eval", "-1");
				eval_three=this.goodsevalService.getWebCount(evalMap);
				// 获取商品月销量数
				Map monthMap = new HashMap();
				monthMap.put("goods_id", gid);
				monthMap.put("order_state_not_in", "0,1");
				monthSaleNum = this.goodsorderService.getWebGoodsCount(monthMap);

			}
			// seo详细页的设置
			goodsDetailSeo(goods_cat);
			// 获取商品咨询类型
			commparaList = commparaService.getCommparaList("c_type");
			shopconfig = this.shopconfigService.getByCustID("0");
			// 发货地址
			ship_addr = shopconfig.getArea_attr();
			// 地区ID转换为中文名称
			ship_addr = AreaFuc.getAreaNameByMap(ship_addr);
			ship_addr = ship_addr.replace(",", "");
			Map cMap = new HashMap();
			if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
				cMap.put("cust_id", this.session_cust_id);
				cMap.put("coll_type", "0");
				cMap.put("goods_id", gid);
				count = this.collectService.getCount(cMap);
				if(count>0){
					is_collect=true;
				}
				
			}
			return goUrl("mbGoodsDetail");
		} catch (Exception e) {
			  getResponse().sendRedirect(cfg_mobiledomain);
			  return null;
		}
	}

	/**
	 * @author : LJQ
	 * @date : May 14, 2014 3:46:10 PM
	 * @Method Description :商品详细页的SEO设置
	 */
	@SuppressWarnings("unchecked")
	private void goodsDetailSeo(String goods_cat) {
		goods_cat = CategoryFuc.getCateNameByMap(goods_cat);// 商品所属分类
		Map seoMap = new HashMap();
		seoMap.put("goods_name", goods.getGoods_name());
		seoMap.put("goods_no", goods.getGoods_no());
		seoMap.put("brand", brand_name);
		seoMap.put("goods_wd", goods.getGoods_wd());
		
		seoMap.put("goods_cat", goods_cat);
		seoMap.put("goods_seo_title", goods.getSeo_title());
		seoMap.put("goods_seo_keyword", goods.getSeo_keyword());
		seoMap.put("goods_seo_desc", goods.getSeo_desc());
		setSeoPage("goodsdetail", seoMap);
	}

	/**
	 * @author : WXP
	 * @param :goods_id
	 * @date Feb 22, 2014 9:42:43 AM
	 * @Method Description :商品评价列表
	 */
	public void goodsEvalComment() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Map evalMap = new HashMap();
		if (goods_id != null && !goods_id.equals("")) {
			evalMap.put("goods_id", goods_id);
		}
		if (g_eval != null && !g_eval.equals("")) {
			evalMap.put("g_eval", g_eval);
		}
		//if (e_type != null && !e_type.equals("")) {
		//	evalMap.put("e_type", e_type);
		//}
		
		evalMap = ajaxMap(evalMap);
		int totalNum = this.goodsevalService.getWebCount(evalMap);
		List list = this.goodsevalService.getWebList(evalMap);
		if(list!=null&&list.size()>0){
			//由于前台显示控件，图片用逗号隔开，只能取到第一个，所以需要逗号替换成为##隔开
			for(int i=0;i<list.size();i++){
				Map imgmap = new HashMap();
				imgmap=(HashMap)list.get(i);
				if(imgmap.get("share_pic")!=null){
					String imgmapString=imgmap.get("share_pic").toString().replace(",", "##");
					imgmap.put("share_pic", imgmapString);
				}
			}
		}
		String jsonStr = pageList(list, totalNum);
		PrintWriter out = response.getWriter();
		// 如果列表存在数据则输出，否则则输出空
		out.write(jsonStr);
	}

	/**
	 * @author : WXP
	 * @param :goods_id
	 * @date Feb 22, 2014 16:12:43 PM
	 * @Method Description :商品月成交记录列表
	 */
	public void goodsMonthSaleComment() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Map monthSaleMap = new HashMap();
		if (goods_id != null && !goods_id.equals("")) {
			monthSaleMap.put("goods_id", goods_id);
		}
		monthSaleMap.put("order_state_not_in", "0,1");
		//monthSaleMap.put("month", "month");

		monthSaleMap = ajaxMap(monthSaleMap);
		int totalNum = this.goodsorderService.getWebCount(monthSaleMap);
		List list = this.goodsorderService.getWebList(monthSaleMap);
		String jsonStr = pageList(list, totalNum);
		PrintWriter out = response.getWriter();
		// 如果列表存在数据则输出，否则则输出空
		out.write(jsonStr);
	}

	/**
	 * @author : WXP
	 * @param :goods_id
	 * @date Mar 1, 2014 9:49:05 AM
	 * @Method Description :商品咨询回复记录
	 */
	public void goodsConsultComment() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Map consultMap = new HashMap();
		if (goods_id != null && !goods_id.equals("")) {
			consultMap.put("goods_id", goods_id);
		}
		if (c_type != null && !c_type.equals("")) {
			consultMap.put("c_type", c_type);
		}
		consultMap.put("is_display", "0");
		consultMap = ajaxMap(consultMap);
		int totalNum = this.consultationService.getWebCount(consultMap);
		List list = this.consultationService.getWebList(consultMap);
		String jsonStr = pageList(list, totalNum);
		PrintWriter out = response.getWriter();
		// 如果列表存在数据则输出，否则则输出空
		out.write(jsonStr);
	}

	/**
	 * @author : WXP
	 * @param :specstr
	 *            商品规格串
	 * @date Feb 26, 2014 10:35:17 AM
	 * @Method Description :获取商品属性
	 */
	public void getGoodsAttr() throws IOException {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String specstr = request.getParameter("specstr");
		String goods_id = request.getParameter("goods_id");
		// 获取对应商品属性
		List goodsAttrList = this.goodsService.getGoodsAttr(specstr, goods_id);
		String modepricestr = JsonUtil.list2json(goodsAttrList);
		out.print(modepricestr);

	}

	/**
	 * @author : WXP
	 * @param :request
	 * @date Apr 7, 2014 1:35:59 PM
	 * @Method Description :获取运费(商品详细页)
	 */
	public void getShipPrice() throws IOException {
		// ------------------获取URL参数开始--------------i 
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		// 是否免运费
		String is_ship = request.getParameter("is_ship");
		// 运费模板ID
		String ship_id = request.getParameter("ship_id");
		// 购买数量
		String buy_num = request.getParameter("buy_num");
		// 商品体积
		String volume = request.getParameter("volume");
		// 商品重量
		String logsweight = request.getParameter("logsweight");
		// 到货地区
		String end_area = request.getParameter("end_area");
		// ------------------获取URL参数结束--------------
		PrintWriter out = response.getWriter();
		// 运费列表
		List shipList = this.goodsService.dealShipPrice(is_ship, ship_id, buy_num, volume,
				logsweight, end_area);
		// list2json然后输出到前台
		String outStr = JsonUtil.list2json(shipList);

		System.out.println("-----------------------------------" + outStr);
		out.print(outStr);
	}

	/**
	 * @author : WXP
	 * @param :request
	 * @date Apr 12, 2014 9:35:59 AM
	 * @Method Description :获取运费(提交订单页面)
	 */
	public void getOrderShipPrice() throws IOException {
		// ------------------获取URL参数开始--------------
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		// 商品ID
		String goods_id_str = request.getParameter("goods_id_str").trim();
		// 购买数量
		String buy_num_str = request.getParameter("buy_num_str");
		// 到货地区
		String end_area_attr = request.getParameter("end_area_attr");
		// ------------------获取URL参数结束--------------
		PrintWriter out = response.getWriter();
		// 运费列表要用再改
		// List shipList =this.goodsService.hualFeer(goods_id_str, buy_num_str, end_area_attr);
		// list2json然后输出到前台
		//String outStr = JsonUtil.list2json(shipList);

		//System.out.println("-----------------------------------" + outStr);
		//out.print(outStr);
	}

	/**
	 * @author : WXP
	 * @param :ship_id,smode_id
	 * @date Apr 7, 2014 3:28:28 PM
	 * @Method Description :获取某个配送方式的默认运费计算方式
	 */
	// public List getDefaultAreaSet(String ship_id, String smode_id){
	// Map map = new HashMap();
	// map.put("ship_id", ship_id);//模板id
	// map.put("smode_id", smode_id);//配送方式id
	// map.put("default_ship", "0");//默认运费
	// List list = this.areasetService.getList(map);
	// return list;
	// }
	public void getshiparea() throws IOException {
		// AJAX获取地区
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String uparea = request.getParameter("shiparea");
		String  str=this.goodsService.getshiparea(uparea);
		out.write(str);
	}

	/**
	 * @author : WXP
	 * @param :
	 * @date Mar 1, 2014 11:17:43 AM
	 * @Method Description :AJAX提交商品咨询
	 */
	public void subGoodsConsult() throws IOException {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String ip = request.getRemoteAddr();
		String ccontent = URLDecoder.decode(request
				.getParameter("ccontent"), "UTF-8");
		// 验证码
		String sysrand = "";
		if (getSession().getAttribute("sysrand") != null) {
			sysrand = getSession().getAttribute("sysrand").toString();
		}

		if (this.session_cust_id != null && !"".equals(this.session_cust_id)) {
			String goodsid = request.getParameter("goodsid");
			String c_type = request.getParameter("c_type");
			String rand_code = request.getParameter("rand_code");
			String cust_id = request.getParameter("cust_id");
			if (!sysrand.equals(rand_code)) {
				out.write("2");// 验证码错误
			} else if (cust_id.equals(this.session_cust_id)) {
				out.write("3");
			}else if(FilterWordFuc.isFilterWord(ccontent)){
				out.write("4");//验证敏感字
			}else {
				Consultation consultation = new Consultation();
				consultation.setGoods_id(goodsid);
				consultation.setC_type(c_type);
				consultation.setMem_id(this.session_cust_id);
				consultation.setC_content(ccontent);
				consultation.setRe_men_id(cust_id);
				consultation.setMem_ip(ip);
				consultation.setIs_display("0");
				consultationService.insert(consultation);
				out.write("0");
			}
		} else {
			out.write("1");
		}
	}

	/**
	 * @author : HZX
	 * @date : Dec 4, 2014 4:40:23 PM
	 * @Method Description :是否登录
	 */
	public void isLogin() throws IOException {
		HttpServletResponse response = getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (this.session_cust_id == null || "".equals(this.session_cust_id)) {
			out.write("1");
		}
	}
	
	/**
	 * @author : XBY
	 * @date : Dec 4, 2014 4:40:23 PM
	 * @Method Description :是否存在敏感字
	 */
	public void filterWord() throws IOException {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//敏感字字符串
		String filterwordstr="";
		//获取咨询内容
		String content = URLDecoder.decode(request
				.getParameter("ccontent"), "UTF-8");
        if(!validateFactory.isRequired(content)){
        	filterwordstr=FilterWordFuc.getFilterWord(content);
        }
		if(!validateFactory.isRequired(filterwordstr)){
    		out.write(filterwordstr);
    	}
		
		
	}
	
	/**
	 * @author LHY 购物车
	 * @return
	 */
	public String mallcart() throws Exception {
		loc = "/webapp/goods!mallcart.action";
		// 判断是否登录
		if (this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/webappLogin.html?loc=" + loc+"&custnum="+custnum+"&registertype="+registertype);
			return null;
		}
		// 获取购物车列表
		Map map = new HashMap();
		map.put("cust_id", this.session_cust_id);
		map.put("shop_cust_id", "1");
		cartgoodsList = this.cartgoodsService.getList(map);
		String member_level = "";
		if(!ValidateUtil.isRequired(this.session_cust_id)) {
			//获取会员对象
			member = this.memberService.get(this.session_cust_id);
			member_level = member.getBuy_level();
		}
		cartgoodsList = SalegoodsFuc.replaceCartgoodsList(cartgoodsList, "1", member_level);
		map.put("shop_cust_id", "0");
		//保税购物车列表
		baocartgoodsList = this.cartgoodsService.getList(map);
		baocartgoodsList = SalegoodsFuc.replaceCartgoodsList(baocartgoodsList, "1", member.getBuy_level());
		map.put("group_shop", "group_shop");
		map.put("shop_cust_id", "1");
		shopList = this.cartgoodsService.getList(map);
		map.put("shop_cust_id", "0");
		baoshopList = this.cartgoodsService.getList(map);
		return goUrl("mbCart");
	}
 
	/**
	 * @Method Description :处理购物车
	 * @author : HZX
	 * @date : Apr 11, 2015 10:33:16 AM
	 */
	public void dealCartApp() throws IOException{
		String outs="0";//返回0：购物车需要加1，1:购物车不需要加1
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (this.session_cust_id!=null&&!session_cust_id.equals("")) {
			//获取的购物车的cookid
			String josn = request.getParameter("josnlist");
			if(josn==null||"null".equals(josn)){
				out.write("1");
				return;
			}
			josn=josn.replace("\\\\", "");
			List josnlist=JSONArray.fromObject(URLDecoder.decode(josn, "UTF-8"));
			//购物车处理
			outs=this.goodsService.dealCartApp(josnlist,this.session_cust_id);
		}
		out.write(outs);
	}
	/**
	 * @Method Description :处理加入购物车 没有本地存储机制的情况下
	 * @author : Hxk
	 */
	public void dealCartAppNoStorage() throws IOException{
		String outs="0";//返回0：购物车需要加1，1:购物车不需要加1
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (this.session_cust_id!=null&&!session_cust_id.equals("")) {
			//获取的购物车的cookid
			String josn = request.getParameter("josnlist");
			if(josn==null||"null".equals(josn)){
				out.write("1");
				return;
			}
			josn=josn.replace("\\\\", "");
			List josnlist=JSONArray.fromObject(URLDecoder.decode(josn, "UTF-8"));
			//购物车处理
			outs=this.goodsService.dealCartApp(josnlist,this.session_cust_id);
		}
		out.write(outs);
	}
	
	/**
	 * @Method Description :获取购物车商品的数量
	 * @author: HXK
	 * @date : Jul 17, 2014 1:48:33 PM
	 * @param 
	 * @return return_type
	 */
	public void getCarNumCount() throws IOException {
		int car_num_count = 0;
		HttpServletResponse response = getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if(session_cust_id!=null&&!"".equals(session_cust_id)){
			Map map = new HashMap();
			map.put("cust_id",this.session_cust_id);
			List cartList = this.cartgoodsService.getList(map);
			car_num_count=cartList.size();
			//Iterator<Map> cart_iter = cartList.iterator() ; 
			//while(cart_iter.hasNext()) { 
			//	Map cMap = cart_iter.next(); 
		}
		//	car_num_count+=Integer.parseInt((cMap.get("buy_num")).toString());
		//}
		out.write((car_num_count + "").trim());
	}
	/**
	 * @Method Description :添加购物车的时候，去判断该商品是否已经在会员的购物车里面,这里指的是数据库购物车表里面的数据
	 * @author: HXK
	 * @date : Jul 17, 2014 1:48:33 PM
	 * @param 
	 * @return return_type
	 */
	public void ifExistsCartGoods() throws IOException{
		String outs="0";//返回0：购物车需要加1，1:购物车不需要加1
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (this.session_cust_id!=null&&!session_cust_id.equals("")) {
			//获取的购物车的cookid
			String j_cookie_id = request.getParameter("j_cookie_id");
			Map map = new HashMap();
			map.put("cust_id",session_cust_id);
			map.put("cookie_id",j_cookie_id);
			List cartList = this.cartgoodsService.getList(map);
			if(cartList!=null&&cartList.size()>0){
				//购物车存在该商品
				outs="1";
			}
		}
		out.write(outs);
	}
	
	
	public void getgoodsstack() throws IOException{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String goods_id = request.getParameter("goods_id");
		String current_spec_id = request.getParameter("current_spec_id");
		HashMap map = new HashMap();
		map.put("goods_id", goods_id);
		if(current_spec_id!=null&&!"".equals(current_spec_id)){
			map.put("specstr", current_spec_id);
		}
		List goodslist = goodsService.getWebList(map);
		HashMap mapvalue=new HashMap();
		if(goodslist!=null&&goodslist.size()>0){
			 mapvalue=(HashMap)goodslist.get(0);
		}
		String stock = mapvalue.get("stock").toString();
		out.write(stock);
	}
	/**
	 * @author : HXK
	 * @param :gidstr 商品ID串
	 * @Method Description :通过ID串获取商品信息
	 */
	public void getGoodsInfoByIdStr() throws IOException {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String goods_id = request.getParameter("gidstr");
		// 获取对应商品属性
		HashMap gMap=new HashMap ();
		gMap.put("goods_id_in", goods_id);
		List goodsAttrList = this.goodsService.getWebList(gMap);
		String ginfostr = JsonUtil.list2json(goodsAttrList);
		out.print(ginfostr);
	}
	

	/**会员评价列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditList() throws Exception {
		
		Map pageMap = new HashMap();
		if(!ValidateUtil.isRequired(order_id_s)){
			pageMap.put("order_id", order_id_s);
		}
		pageMap.put("order_state", "7,8");
		pageMap.put("cust_id", this.session_cust_id);
		//pageMap.put("is_virtual", "1");
		pageMap.put("is_eval", "0");
		pageMap.put("orderdetail_state_s", "0");
		// 根据页面提交的条件找出信息总数
		int count = this.orderdetailService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		goodsevalList = this.orderdetailService.getList(pageMap);
		return goUrl("mbappEvaluation");

	}
	
	/**
	 * @Method Description :通过商城标签获取对于的商品列表
	 * @author: HXK
	 * @date : Aug 26, 2015 6:52:42 PM
	 * @param 
	 * @return void
	 */
	public String toMallmarksIndex() throws Exception {
		// 构造list列表搜索条件
		List shList = new ArrayList();
		Sort so = new Sort();
		// 根据条件查找商品
		if (tab_number != null && !"".equals(tab_number)) {
			if(tab_number.length()>10){
				tab_number=tab_number.substring(0,10);
			}
			shList = normalSearch("tab_number", tab_number, shList);
		}
		// 未被删除商品
		shList = normalSearch("is_del", "1", shList);
		// 上架商品
		shList = normalSearch("is_up", "0", shList);
		// 排序
		if (sort != null && !"".equals(sort)) {
			if (sort.equals("0")) {
			} else if (sort.equals("price_asc") && order_by_s.equals("price")) {
				SortField sf = new SortField("lpad_price", SortField.STRING,false);// 价格升序
				so.setSort(new SortField[] { sf });
			} else if (sort.equals("price_desc") && order_by_s.equals("price")) {
				SortField sf = new SortField("lpad_price", SortField.STRING,true);// 价格降序
				so.setSort(new SortField[] { sf });
			} else if (sort.equals("sale_num_asc") && order_by_s.equals("salenum")) {
				SortField sf = new SortField("order_num", SortField.STRING,false);// 销售量升序
				so.setSort(new SortField[] { sf });
			}else if (sort.equals("sale_num_desc") && order_by_s.equals("salenum")) {
				SortField sf = new SortField("order_num", SortField.STRING,true);// 销售量降序
				so.setSort(new SortField[] { sf });
			}else if (sort.equals("collNum_asc") && order_by_s.equals("collnum")) {
				SortField sf = new SortField("in_date", SortField.STRING,false);// 人气升序
				so.setSort(new SortField[] { sf });
			}else if (sort.equals("collNum_desc") && order_by_s.equals("collnum")) {
				SortField sf = new SortField("in_date", SortField.STRING,true);// 人气降序
				so.setSort(new SortField[] { sf });
			}else if (sort.equals("in_date_desc")) {
				SortField sf = new SortField("in_date", SortField.STRING, true);// 时间降序
				so.setSort(new SortField[] { sf });
			}
		
		}
		// 价格搜索
		if (min_price != null && !"".equals(min_price) && max_price != null
				&& !"".equals(max_price)) {
			// 最低价
			String l_price = fullBit(min_price);
			l_price = isRmb(l_price);
			// 最高价
			String h_price = fullBit(max_price);
			h_price = isRmb(h_price);
			shList = rangeSearch("lpad_price", l_price, h_price, shList);
		}
		// 根据页面提交的条件找出信息总数
		SearchIndex si = new SearchIndex("navigation");
		// 搜索到的数据
		count = si.getCount(shList);
		// 分页插件
		webAppLucenePageTool(count);
		goodsList = si.search(shList, so, pages_s, webappPageSize_s);
		// 搜索为空时
		if (goodsList == null || goodsList.size() == 0) {
			search_null_page = PageTipFuc.getPageCon("serach_null_page");
		}
		String member_level = "";
		if(!ValidateUtil.isRequired(this.session_cust_id)) {
			//获取会员对象
			member = this.memberService.get(this.session_cust_id);
			member_level = member.getBuy_level();
		}
		goodsList = SalegoodsFuc.replaceList(goodsList, "1", member_level);
		goodsList=SalegoodsFuc.saleGoodsTimeList(goodsList,"1", member_level);
		if(tab_number!=null&&!"".equals(tab_number)){
			navtab=navtabService.getByTaxNumber(tab_number);
		}else{
			navtab=new Navtab();
		}
			
		return goUrl("mbMarkProductList");
		
	}
	/**
	 * @Method Description :
	 * @author : HZX
	 * @throws Exception 
	 * @date : Apr 22, 2015 1:54:41 PM
	 */
	@SuppressWarnings("unchecked")
	public void pageMarkLists() throws Exception{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		pages_s = 1;
		if (request.getParameter("cp") != null) {
			pages_s = Integer.parseInt(request.getParameter("cp"));
		}
		// 根据条件查找商品
		tab_number =request.getParameter("tab_number");
		toMallmarksIndex();
		String jsonStr="";
		if(goodsList!=null&&goodsList.size()>0){
			for(int i=0;i<goodsList.size();i++){
				Map cMap=(Map) goodsList.get(i);
				if(cMap!=null&&cMap.get("goods_id")!=null){
					if(cMap.get("list_img")==null||"".equals(cMap.get("list_img").toString())){
						cMap.put("list_img", cfg_webappdefaultpic);
					}
					if(cMap.get("goods_name")==null||"".equals(cMap.get("goods_name").toString())){
						cMap.put("goods_name", "--");
					}
					if(cMap.get("lmin_price")==null||"".equals(cMap.get("lmin_price").toString())){
						cMap.put("lmin_price", "0");
					}
					if(cMap.get("goods_market_price")==null||"".equals(cMap.get("goods_market_price").toString())){
						cMap.put("goods_market_price", "0");
					}
					if(cMap.get("goods_sale_num")==null||"".equals(cMap.get("goods_sale_num").toString())){
						cMap.put("goods_sale_num", "0");
					}
					if(cMap.get("eval_num")==null||"".equals(cMap.get("eval_num").toString())){
						cMap.put("eval_num", "0");
					}
				}
			}
			jsonStr=JsonUtil.list2json(goodsList);
		}
		PrintWriter out = response.getWriter();
		// 如果列表存在数据则输出，否则则输出空
		out.print(jsonStr.toString());
		
	}
	/**
	 * ajax手机首页获取倒计时时间
	 * @throws Exception 
	 */
	public void ajaxDJSDate() throws Exception {
		String timenumber="1";
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String req_tab_number = request.getParameter("tab_number");
		Map nMap=new HashMap();
		nMap.put("is_del", "1");
		nMap.put("tab_number", req_tab_number);
		nMap.put("is_up", "0");
		goodsList = this.navigationService.getWebList(nMap);
        if(goodsList!=null&&goodsList.size()>0){
        	Map gMap=new HashMap();
        	gMap=(HashMap)goodsList.get(0);
        	String goods_id="";
        	if(gMap!=null&&!"".equals(gMap.get(""))){
        		goods_id=gMap.get("goods_id").toString();
        		goods=goodsService.get(goods_id);
        		String member_level = "";
    			if(!ValidateUtil.isRequired(this.session_cust_id)) {
    				//获取会员对象
    				member = this.memberService.get(this.session_cust_id);
    				member_level = member.getBuy_level();
    			}
    			//判断商品是否有做促销活动
    			String s_id = SalegoodsFuc.getSaleId(goods, "1", member_level);
    			//判断商品是否有做促销活动
    			if(!ValidateUtil.isRequired(s_id)){
    				String[] s_ids = s_id.trim().split(",");
    				//从商品参加的活动中 提取出倒计时时间
    				for(int i=0;i<s_ids.length;i++){
    					salegoods = this.salegoodsService.get(s_ids[0]);
        				//是否限时倒计时
        				limit_time = salegoods.getLimit_time();
        				if("1".equals(limit_time)){
        					//获取倒计时时间秒数
            				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            				if(salegoods.getEnd_time()!=null){
            					Date date = sd.parse(salegoods.getEnd_time());
            					difftime =date.getTime() - new Date().getTime();
            					if(0 > difftime){
            						timeout = "1";
            					}
            				}
            				break;
        				}
        				
    				}
    				
    			}
        	}
        }
        if(difftime!=0.0){
        	timenumber=String.valueOf(difftime);
        }
	    out.write(timenumber);
	}
	
	
	
	
	/**
	 * 
	 * @throws Exception
	 */
	public void changelistshow()throws Exception{
		String nowlistshow = "";
		if(getRequest().getParameter("listshow") !=null && !"".equals(getRequest().getParameter("listshow"))){
			nowlistshow = getRequest().getParameter("listshow").toString();
		}
		
		
	}
	
	 /**
	    * 优惠券 
	    * @throws IOException
	    */
	public String couponList(){
		Map map = new HashMap();
		map.put("coupon_state", "4");  //4：优惠券
		map.put("is_show", "1");  //是否显示
		map.put("time", "1");  //判断是否过期
		map.put("info_state", "1");  //判断是否禁用
		couponList = couponService.getList(map);
		return goUrl("mbYouHui");
	}
	
	/**
	 * 跳转到领取优惠券 分享页面
	 * @return
	 */
	public String getCouponPage(){
		if(!ValidateUtil.isRequired(getRequest().getParameter("coupon_id"))){
			String id = getRequest().getParameter("coupon_id").toString();
			coupon = couponService.get(id);
		}
		return goUrl("mbYouHuiLq");
	}
	
	
	/**
	 * ajax领取优惠券
	 * @throws IOException
	 */
	public void ajaxCoupon() throws IOException {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String coupon_id = request.getParameter("coupon_id");
		
		// 判断是否登录
		if (this.session_cust_id.equals("") || this.session_cust_id.equals("0")) {
			out.write("1");
		}else {
		   //判断是否领取过优惠券
		   Map map = new HashMap();
		   map.put("coupon_id", coupon_id);
		   map.put("cust_id", session_cust_id);
		   List list = this.comsumerService.getList(map);
		   
		   //获取优惠对象
		   Coupon coupon = this.couponService.get(coupon_id);
		   
		   if(list !=null && list.size() > 0) {
			   out.write("2");
		   }else if(coupon.getCoupon_num().equals("0")) {
			   out.write("3");
		   }else {
			   //插入优惠券
			   Comsumer comsumer = new Comsumer();
			   comsumer.setCust_id(this.session_cust_id);
			   comsumer.setComsumer_no(coupon.getCoupon_no()+""+RandomStrUtil.getRand("10"));
			   comsumer.setCoupon_id(coupon_id);
			   comsumer.setUse_state("0");
			   comsumerService.insert(comsumer);
			   //减掉优惠券数量
			   if(!coupon.getCoupon_num().equals("不限制")) {
				   String count = (Integer.valueOf(coupon.getCoupon_num()) - 1) +"";
				   coupon.setCoupon_num(count);
				   this.couponService.update(coupon);
			   }
			   out.write("0");
		   }
		   
		   
		}
	}
	
	
	
	 /**
	    * 红包
	    * @throws IOException
	    */
	public String redpacketList(){
		Map map = new HashMap();
		map.put("info_state", "1");  //1：正常
		map.put("is_show", "1");  //是否显示
		map.put("time", "1");  //判断是否过期
		redpacketList =redpacketService.getList(map);
		return goUrl("mbRedpacket");
	}
	
	/**
	 * 跳转到领取红包和分享页面
	 * @return
	 */
	public String getRedpacketPage(){
		if(!ValidateUtil.isRequired(getRequest().getParameter("redpacket_id"))){
			String id = getRequest().getParameter("redpacket_id").toString();
			redpacket = this.redpacketService.get(id);
		}
		
		return goUrl("mbRedpacketLq");
	}
	
	/**
	 * ajax领取红包
	 * @throws IOException
	 */
	public void ajaxRedpacket() throws IOException {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String red_id = request.getParameter("red_id");
		
		// 判断是否登录
		if (this.session_cust_id.equals("") || this.session_cust_id.equals("0")) {
			out.write("1");
		}else {
		   //判断是否领取过红包
		   Map map = new HashMap();
		   map.put("red_id", red_id);
		   map.put("cust_id", session_cust_id);
		   List list = this.redsumerService.getList(map);
		   
		   //获取优惠对象
		   Redpacket redpacket = this.redpacketService.get(red_id);
		   
		   if(list !=null && list.size() > 0) {
			   out.write("2");
		   }else if(redpacket.getRed_num().equals("0")) {
			   out.write("3");
		   }else {
			   //插入红包
			   Redsumer redsumer = new Redsumer();
			   redsumer.setCust_id(this.session_cust_id);
			   redsumer.setRedsumer_no(redpacket.getRed_num()+""+RandomStrUtil.getRand("10"));
			   redsumer.setRed_id(red_id);
			   redsumer.setUse_state("0");
			   redsumerService.insert(redsumer);
			   //减掉红包数量
			   if(!redpacket.getRed_num().equals("不限制")) {
				   String count = (Integer.valueOf(redpacket.getRed_num()) - 1) +"";
				   redpacket.setRed_num(count);
				   this.redpacketService.update(redpacket);
			   }
			   out.write("0");
		   }
		   
		   
		}
	}
	public String kefulist() throws Exception {
			return goUrl("mbKefu");
	}
	
	
	public void prepare() throws Exception {
		// 初始化StringBuilder
		postsb = new StringBuilder();
	}

	public String getListshow() {
		return listshow;
	}

	public void setListshow(String listshow) {
		this.listshow = listshow;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public void setRedpacket(Redpacket redpacket) {
		this.redpacket = redpacket;
	}

	public String getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}

	public String getCat_attr_top_s() {
		return cat_attr_top_s;
	}

	public void setCat_attr_top_s(String cat_attr_top_s) {
		this.cat_attr_top_s = cat_attr_top_s;
	}

	public String getCatEn_name() {
		return catEn_name;
	}

	public void setCatEn_name(String catEn_name) {
		this.catEn_name = catEn_name;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getBrand_id_s() {
		return brand_id_s;
	}

	public void setBrand_id_s(String brand_id_s) {
		this.brand_id_s = brand_id_s;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	
	

}
