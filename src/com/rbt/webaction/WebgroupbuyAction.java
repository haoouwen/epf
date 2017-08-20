package com.rbt.webaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.Preparable;
import com.rbt.model.Area;
import com.rbt.model.Goods;
import com.rbt.model.Groupgoods;
import com.rbt.model.Member;
import com.rbt.model.Shopconfig;
import com.rbt.service.ICategoryService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGroupgoodsService;
import com.rbt.service.IGroupladderService;
import com.rbt.service.IMemberService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.IShopconfigService;
import com.rbt.common.util.JsonUtil;
import com.rbt.common.util.PageUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;

/**
 * @author : CYC
 * @date :  2014.04.15 11:33:28 AM
 * @Method Description :团购
 */
public class WebgroupbuyAction extends goodsModelAction implements Preparable{

	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	public Area area;
	public Groupgoods groupgoods;
	public Goods goods;
	public Member member;
	public Shopconfig shopconfig;
	
	/*******************业务层接口****************/
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IGroupgoodsService groupgoodsService;
	@Autowired
	private IGroupladderService groupladderService;
	@Autowired
	private IMemberService memberService;
	@Autowired
	private IShopconfigService shopconfigService;
	@Autowired
	private IOrderdetailService orderdetailService;
	
	
	/*********************集合********************/
	public List one_categoryList;
	public List two_categoryList;
	public List two_groupcategoryList;
	public List goodsList;
	public List groupgoodsList;
	public List areaList;
	public List areacharList;
	public List groupladderList;
	public List shopconfigList;
	public List imageList;
	public List catgroupList;
	public List catList;
	public List twocatList;
	/*********************字段********************/
	public List scoreList;
	public String gid;//商品标识
	public Map groupGoodsMap;
	//团购列表页定义隐藏字段
	public String en_name;//英文名
	public String cat_attr;//分类
	public String searchtype;
	public String searchname;
	public String cssstatus;
	public String sele_area_id;
	public int count;
	//团购详细页定义变量
	public String max_sale_price;//最大售价
	public String groupprice;//团购价格
	public String pricediff;//价格差
	public String pricepercent;//折扣
	public long difftime;//时间差
	public int ordernum;
	public String timeout="0";
	public String oneimg;
	public String stock;//库存
	public String cust_maxbuy;//同一会员购买上限
	public String is_illegal;//目前用于是否非法注入购买数量
	public List<Map<String, String>> recomList;
	public char price;
	public String one_cat_attr;
	/**
	 * @author : CYC
	 * @throws IOException 
	 * @Method Description :团购首页绑定
	 */
	public String groupBuyIndex() throws Exception{
		String cat_id ="";
		//获取分类参数
		HttpServletRequest request = getRequest();
		cat_attr = request.getParameter("cat_attr");
		//获取排序参数
	    searchtype = request.getParameter("searchtype");
		//获取搜索关键字
	    searchname = request.getParameter("searchname");
	    //获取地区关键字
	    en_name = request.getParameter("en_name");
	    
	    //前台搜索团购商品标题
	    String str=request.getParameter("searchGroupName");
	    if(str!=null && !"".equals(str)){
	    	searchname=URLDecoder.decode(str, "UTF-8");
	    }
	    recomList=getRecomList(3,null);
	    Map catMap=new HashMap();
	    catMap.put("is_display", "1");
	    catMap.put("cat_level", "1");
	    catMap.put("module_type", "group");
	    catList=this.groupgoodsService.getCatList(catMap);
	    catMap.remove("cat_level");
	    catMap.put("cat_level", "2");
	    catMap.put("up_cat_id", one_cat_attr);
	    twocatList=this.groupgoodsService.getCatList(catMap);
	    try {
	    	//绑定导航条下的团购分类
			//首先判断是否有分类级别，如果没有显示第一级分类，如有显示下一级分类
		    groupGoodsMap=this.groupgoodsService.groupList(cat_attr, en_name, sele_area_id, searchtype, searchname,price);
		} catch (Exception e) {
			return goUrl("groupBuylist");
		}
		//分页插件
		count = groupgoodsService.getCount((HashMap)groupGoodsMap.get("groupgoodsMap"));
		Map groupgoodsMap = (HashMap) super.webPageTool(count,(HashMap)groupGoodsMap.get("groupgoodsMap"));
		groupgoodsList = groupgoodsService.getList((HashMap)groupGoodsMap.get("groupgoodsMap"));
		return goUrl("groupBuylist");
	}
	/**
	 * @Method Description :
	 * @author : HZX
	 * @date : Jul 8, 2014 9:35:43 AM
	 */
	public List getRecomList(int limit,String trade_id){
		Map recoMap=new HashMap();
	    recoMap.put("defaultSort", "1");
	    recoMap.put("isrecom", "0");
	    recoMap.put("start",0);
	    recoMap.put("limit",limit);
	    recoMap.put("info_state","1");
	    recoMap.put("difftime", "difftime");
	    if(!ValidateUtil.isRequired(trade_id))
	    recoMap.put("trade_id_no",trade_id);
	    recomList=this.groupgoodsService.getList(recoMap);
		return recomList;
	}	
	/**
	 * @author : CYC
	 * @throws IOException 
	 * @Method Description :地区绑定
	 */
	public void getarea()throws Exception{
		HashMap map = new HashMap();
		map.put("is_city", "1");
		map.put("up_area_id", "1111111111");
		areaList = areaService.getAreaCityList(map);
		areacharList = areaService.getCharacterList(map);
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 兼容火狐文本输出
		response.setContentType("text/html;chartset=UTF-8;");
		ArrayList array = new ArrayList();
		array.add(areacharList);
		array.add(areaList);
		String jsonStr =JsonUtil.list2json(array);
		PrintWriter out = response.getWriter();
		out.write(jsonStr);
	}
	
	/**
	 * @author : CYC
	 * @throws ParseException 
	 * @throws IOException 
	 * @Method Description :绑定团购详细页面
	 */
	public String groupDetail() throws Exception{
		HttpServletRequest request = getRequest();
		String trade_id = request.getParameter("groupid");
		is_illegal=request.getParameter("illegal");
		groupGoodsMap=this.groupgoodsService.detail(trade_id);
		/*//自定义规格和值
		getSepcValueList(groupGoodsMap.get("gid").toString());
		//获取规格值与商品规格比对是否属于规格库中
		getSpecstr(groupGoodsMap.get("gid").toString());*/
		recomList=getRecomList(5,trade_id);
		return goUrl("groupBuyDetail");
	}
	
	public void prepare() throws Exception {
		
	}
}
