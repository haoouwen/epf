/*
  
 
 * Package:com.rbt.action
 * FileName: WebBaseAction.java 
 */
package com.rbt.webaction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.rbt.action.BaseAction;
import com.rbt.common.util.PageUtil;
import com.rbt.common.Constants;
import com.rbt.createHtml.SpecialField;
import com.rbt.function.AreaFuc;
import com.rbt.function.KeyWordInsertFuc;
import com.rbt.function.SeosetFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.index.ParaModel;
import com.rbt.model.Category;
import com.rbt.model.Goods;
import com.rbt.model.Member;
import com.rbt.model.Seoset;
import com.rbt.model.Shopconfig;
import com.rbt.service.IAreaService;
import com.rbt.service.ICategoryService;
import com.rbt.service.ICategoryattrService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IMembercatService;
import com.rbt.service.IGoodsbrandService;
import com.rbt.service.IMemberchannelService;


/**
 * @function 功能 前台列表页通用父类
 * @author  创建人 HXK
 * @date  创建日期 2014-10-20
 */
@SuppressWarnings("static-access")
@Controller
public class WebbaseAction extends BaseAction {
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 6601865002767423743L;

	/*******************实体层****************/
	public Category category;

	/*******************业务层接口****************/
	@Autowired
	public ICategoryService categoryService;	
	@Autowired
	public IAreaService areaService;
	@Autowired
	public ICategoryattrService categoryattrService;	
	@Autowired
	public IGoodsService goodsService;
	@Autowired
	public IMembercatService membercatService;
	@Autowired
	public IGoodsbrandService goodsbrandService;
	@Autowired
	private IMemberchannelService memberchannelService;

	/*********************集合******************/
	public List membercatList;//会员自定义分类列表
	public List hotsaleList;//热门销售
	public List categoryList;//分类
	public List collectList;//热门收藏
	public List goodsBrandList;//品牌列表
	public List memberchanneList;//店铺头部导航

	/*********************字段******************/
	public String web_basehost=SysconfigFuc.getSysValue("cfg_basehost");//网站地址
	public String cfg_powerby=SysconfigFuc.getSysValue("cfg_powerby");//商城底部版权信息
	public String cfg_beian=SysconfigFuc.getSysValue("cfg_beian");//商城ICP备案号
	public String cfg_lazyPic=SysconfigFuc.getSysValue("cfg_lazyPic");//延时加载默认图片
	private static final String SESSION_AREA_ID = "session_area_id";//静态变量地区
	public String page_position;//位置
	public String cat_id;//分类ID
	public String area_id;//地区ID
	public String searchText;//搜索文本
	public boolean is_souch=false;//是否更新
	public String  seo_title,seo_keyword,seo_desc;//动态页SEO标题、关键字、描述

	

	/**
	 * @author : LJQ
	 * @date : Mar 13, 2014 1:52:11 PM
	 * @Method Description :动态列表页seo的设置
	 */
	public void setSeoPage(String seo_code,Map map){
		Seoset sset = SeosetFuc.getSeosetModel(seo_code,map);
		seo_title=sset.getSeo_title();
		seo_keyword=sset.getSeo_keyword();
		seo_desc=sset.getSeo_decri();
	}
	
	/**
	 * @author : LJQ
	 * @date : Feb 26, 2014 9:46:47 AM
	 * @Method Description :设置列表页的导航位置
	 */
	public void setPage_position(String module_type) {
		Map map = new HashMap();
		map.put("area_attr", this.area_id);
		map.put("cat_attr", this.cat_id);
		map.put("module_type", module_type);
		SpecialField sf = new SpecialField();
		this.page_position = sf.getListPosition(map);
		//设置列表页seo
		setListPageSeo(module_type);
	}	
	
	/**
	 * @author : LJQ
	 * @date : Feb 26, 2014 9:44:44 AM
	 * @Method Description :设置前台SEO的设置
	 */
	public void setListPageSeo(String module_type){
		StringBuffer sb = new StringBuffer();
		//list_seo_title = sb.toString();
	}
	
	/**
	 * @author : LJQ
	 * @date : Feb 26, 2014 9:40:54 AM
	 * @Method Description :设置session中的区域标识
	 */
	public void setSessionAreaId(String area_id){
		this.getSession().setAttribute(SESSION_AREA_ID, area_id);
	}
    
	/**
	 * @author : LJQ
	 * @date : Feb 26, 2014 9:40:29 AM
	 * @Method Description :先从session中取，如果取不到，在根据IP定位找出地区标识
	 */
	public String getSessionAreaId(){
		String area_id ="";
		if(!this.getSessionFieldValue(SESSION_AREA_ID).equals("")){
			area_id = this.getSessionFieldValue(SESSION_AREA_ID);
		}else{
			area_id = AreaFuc.getAreaidByIpaddr(this.getRequest());
			this.setSessionAreaId(area_id);
		}
		return area_id;
	}
	
	/**
	 * @author : LJQ
	 * @date : Feb 26, 2014 9:38:52 AM
	 * @Method Description :前台列表页区域过滤
	 */
	public Map portalAreaFilter(Map map){
		if(this.cfg_area_filter!=null&&!this.cfg_area_filter.equals("")&&this.cfg_area_filter.equals("0")){
			String area_id = getSessionAreaId();
			map.put("area_attr", area_id);
		}
		return map;
	}
	
	/**
	 * @author : LJQ
	 * @date : Feb 26, 2014 9:39:21 AM
	 * @Method Description :前台列表页区域过滤,针对搜索lucene索引库
	 */
	public List portalAreaFilterList(List filtList){
		if(this.cfg_area_filter!=null&&!this.cfg_area_filter.equals("")&&this.cfg_area_filter.equals("0")){
			String area_id = getSessionAreaId();
			filtList=normalSearch("area_attr",area_id,filtList);
		}
		return filtList;
	}
	
	/**
	 * @Method Description : list列表Map的赋值
	 * @author : LJQ
	 * @date : 1,1,2014 1:26:03 PM
	 */
	public Map setMapValue(String fieldName,String fieldValue,Map map){
		if(map == null) return map;
		if(fieldName == null) return map;
		if(fieldValue!=null&&!fieldValue.equals("")){
			map.put(fieldName, fieldValue);
			is_souch = true;
		}
		return map;
	}
	
	/**
	 * @author : LJQ
	 * @date : May 10, 2014 10:13:20 AM
	 * @Method Description :前台分页统一处理
	 */
	@SuppressWarnings("unchecked")
	public Map webPageTool(int count, Map pageMap) {
		PageUtil page = new PageUtil();
		// 判断是两个页面间的跳转则给page页面赋值
		if (two_pages_link != null && two_pages_link.equals("no")) {
			page.setCurPage(1);
			page.setPageSize(40);
		} else {
			page.setCurPage(pages_s);
			page.setPageSize(webPageSize_s);
		}
		page.setTotalRow(count);
		webPageBar = page.getWebToolsMenu();
		pageMap.put("start", page.getStart());
		pageMap.put("limit", page.getEnd());
		return pageMap;
	}
	
	/**
	 * @author : LJQ
	 * @date : Jul 25, 2014 10:15:33 AM
	 * @Method Description : lucene索引分页数据
	 */
	public void indexPageTool(int count, ModifiableSolrParams map) {
		map.add(CommonParams.START, String.valueOf((pages_s-1) * webPageSize_s));  
        map.add(CommonParams.ROWS, String.valueOf(webPageSize_s)); 
		PageUtil page = new PageUtil();
		page.setCurPage(pages_s);
		page.setPageSize(webPageSize_s);
		page.setTotalRow(count);
		webPageBar = page.getWebToolsMenu();
	}
	
	/**
	 * @author : LJQ
	 * @date : Jul 25, 2014 10:20:30 AM
	 * @Method Description : lucene普通搜索条件的赋值------------------------更换sorl索引时删掉
	 */
	public List normalSearch(String fieldName,String fieldValue,List list){
		if(fieldName==null || fieldName.equals("")) return list;
		if(fieldValue!=null&&!fieldValue.equals("")){
			ParaModel pm = new ParaModel(fieldName,fieldValue);
			list.add(pm);
			is_souch = true;
		}
		return list;
	}
	
	/**
	 * @author : LJQ
	 * @date : Jul 25, 2014 2:44:11 PM
	 * @Method Description : lucene 范围搜索条件的赋值------------------------更换sorl索引时删掉
	 */
	public List rangeSearch(String fieldName,String start,String end,List list){
		if(fieldName==null || fieldName.equals("")) return list;
		if(start!=null && start.equals("") && end!=null && end.equals("")){
			return list;
		}else{
			ParaModel pm = new ParaModel(fieldName,start,end);
			list.add(pm);
			System.out.println(fieldName+"======================="+start+","+end);
			is_souch = true;			
		}
		return list;
	}



	/**
	 * @author : LHY
	 * @throws IOException 
	 * @Method Description :获取会员的商品分类
	 */
	public void getMembercat(String cust_id){
		Map map=new HashMap();
		map.put("cust_id",cust_id);
		membercatList=this.membercatService.getList(map);
	}
	

	/**
	 * 取出相关的分类
	 * @author LHY
	 */
	public void getRalateCat(String cat_id){
		if(cat_id==null || cat_id.length()<=0){
			return;
		}
		//取得当前级别的id
		String cat_attr=cat_id.substring(cat_id.lastIndexOf(",")+1,cat_id.length());
		category=this.categoryService.get(cat_attr);
		Map catMap=new HashMap();
		//存入当时分类的上级分类
		if(category!=null){
			catMap.put("up_cat_id", category.getUp_cat_id());
			categoryList=this.categoryService.getList(catMap);
			//取出同分类的相关品牌
			Map branmap=new HashMap();
			branmap.put("goods_attr", category.getCat_id());
			goodsBrandList=this.goodsbrandService.getList(branmap);
		}
		//平台的热门销售
		hotsaleList=this.goodsService.getHotSaleList("", cat_attr);
	}
	
	/**
	 * @author : LHY
	 * @throws IOException 
	 * @Method Description :绑定店铺头部信息
	 */
	public void getIndexTop(String cust_id){
		HashMap mchannelMap =new HashMap ();
		mchannelMap .put("cust_id", cust_id);
		mchannelMap.put("is_dis", 0);
		memberchanneList=memberchannelService.getList(mchannelMap);
	}
	
	/**	
	 * @author : WXP
	 * @param :shopconfig
	 * @throws IOException 
	 * @date Apr 2, 2014 4:22:30 PM
	 * @Method Description :检验店铺状态是否正常
	 */
	public boolean verifyShop(Shopconfig shopconfig) throws IOException{
		if(shopconfig != null){
			if(!this.session_cust_id.equals(shopconfig.getCust_id())){//自己的店铺不验证
				if(!shopconfig.getInfo_state().equals("1") || !shopconfig.getIs_colse().equals("1")){
					getResponse().sendRedirect(Constants.ILLEGAL_SHOP_PAGE);
					return true;
				}
			}
		}else{
			getResponse().sendRedirect(Constants.ILLEGAL_SHOP_PAGE);
			return true;
		}
		return false;
	}
	
	/**	
	 * @author : WXP
	 * @param :goods
	 * @Method Description :校验商品状态是否正常(判断是否上架，是否审核通过，是否删除了)
	 * @date Apr 3, 2014 9:54:51 AM
	 */
	public boolean verifyGoods(Goods goods) throws IOException{
		if(goods != null&&(!goods.getInfo_state().equals("1") || !goods.getIs_del().equals("1") || !goods.getIs_up().equals("0"))){
			getResponse().sendRedirect(Constants.ILLEGAL_GOODS_PAGE);
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @author : LJQ
	 * @date : May 14, 2014 3:52:39 PM
	 * @Method Description :通过cust_id转换企业名称
	 */
	public String turnCustidChangeName(String cust_id){
		Member member = this.memberService.get(cust_id);
		String cust_name="";
		if(member != null){
			cust_name = member.getCust_name();
		}
		return cust_name;
	}
	
	/**
	 * @author : LJQ
	 * @date : May 9, 2014 3:20:40 PM
	 * @Method Description : 获取商品详情
	 */
	public void detailMsg(){
		
	}
	
	/**
	 * @Method Description : 返回按关键字查找列表的次数，插入关键字表
	 * @author : LJQ
	 * @throws UnsupportedEncodingException 
	 * @date : 1,1,2014 1:26:03 PM
	 */
	public Boolean reqKeyword(String fieldName,String module_type,Map map) throws UnsupportedEncodingException{
		HttpServletRequest request = super.getRequest();
		if (request.getParameter("wd") != null && !request.getParameter("wd").equals("")) {
			String keyword = URLDecoder.decode(request.getParameter("wd"), "UTF-8");
			String en_name ="noen";
			if(request.getParameter("en_wd")!=null&&!"".equals(request.getParameter("en_wd"))){
				en_name=request.getParameter("en_wd");
			}
			setMapValue(fieldName, keyword,map);
			searchText = keyword;
			// 插入关键字表中
			try {
				KeyWordInsertFuc.wdInsert(keyword,en_name,module_type);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			is_souch = true;
		}
		return is_souch;
	}
	
	/**
	 * @author : LJQ
	 * @date : Jul 27, 2014 9:42:07 AM
	 * @Method Description : 补足17位方法
	 */
	public String fullBit(String n){
		  return fillBitChar(n,17);
	}
	
	/**
	 * @author : LJQ
	 * @date : Jul 27, 2014 9:42:07 AM
	 * @Method Description : 补位的方法
	 */
	public String fillBitChar(String n,int clen){
		if(n==null || n.equals("")){
			  return null;
		  }
		  int in = Integer.parseInt(n);
		  NumberFormat formatter = NumberFormat.getNumberInstance();   
		  formatter.setMinimumIntegerDigits(clen);   
		  formatter.setGroupingUsed(false);   
		  String s = formatter.format(in);   
		  System.out.println(s+"=============");
		  return s;
	}
	
	/**
	 * @author : LJQ
	 * @date : Jul 27, 2014 9:59:16 AM
	 * @Method Description : 过滤不符合RMB格式的字符串
	 */
	public String isRmb(String s){
		if(s==null || s.equals("")){
			return null;
		}else{
			if(s.indexOf(".")==-1){
				s=s+".00";
			}
		}
		return s;
	}
	
	/**
	 * @author : LJQ
	 * @date : Jul 25, 2014 10:15:33 AM
	 * @Method Description : lucene索引分页数据
	 */
	public void lucenePageTool(int count) {
		PageUtil page = new PageUtil();
		page.setCurPage(pages_s);
		page.setPageSize(webPageSize_s);
		page.setTotalRow(count);
		webPageBar = page.getWebToolsMenu();
	}
	
}
