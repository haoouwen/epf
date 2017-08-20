/*
 
 * Package:com.rbt.action
 * FileName: BuyAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.GridTreeUtil;
import com.rbt.function.BatchListFuc;
import com.rbt.function.GoodsDetailToAppDetailFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.index.CreateIndex;
import com.rbt.solr.*;
import com.rbt.service.IAreaService;
import com.rbt.service.IAssociationkeywordsService;
import com.rbt.service.ICategoryService;
import com.rbt.service.IChannelService;
import com.rbt.service.ICommparaService;
import com.rbt.service.IGenericService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsbrandService;
import com.rbt.service.INavigationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 表索引的创建
 * @author 创建人 LJQ
 * @date 创建日期 Aug 24, 2014 1:50:56 PM
 */
@Controller
public class LuceneAction extends AdminBaseAction {

	private static final long serialVersionUID = 1L;

	/** *****************实体层*************** */

	/** *****************业务层接口*************** */
	@Autowired
	private ICommparaService commparaService;
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IGoodsbrandService goodsbrandService;
	@Autowired
	private IAreaService areaService;
	@Autowired
	private IChannelService channelService;
	@Autowired
	private IAssociationkeywordsService associationkeywordsService;
	@Autowired
    private INavigationService navigationService;

	/** *******************集合***************** */
	public List commparalist;// 参数表数据列表
	public List channelList;// 记录网站栏目信息信息集合
	public List channelWebAppList;// 触屏版栏目信息集合

	/** *******************字段***************** */
	private String index_code = "index_code";// 索引编码
	private static String index_flag;// 更新进度的标识
	private static String index_list_size;// 索引文件的长度
	private static String is_update_index;// 判断是否在执行索引中
	private static String is_stop;// 是否停止索引 0:已停止
	public String jsonMenu;// json传值
	public String jsonWebAppMenu;
	public String menu_code;//
	public String  pic_goods_id="";

	/**
	 * @author : LJQ
	 * @date : Apr 8, 2014 2:20:03 PM
	 * @Method Description :获取参数列表
	 */
	public void getCommparaList() {
		Map pageMap = new HashMap();
		pageMap.put("para_code", index_code);
		commparalist = this.commparaService.getList(pageMap);
	}

	/**
	 * @author : LJQ
	 * @date : Aug 27, 2014 2:45:26 PM
	 * @Method Description : 更新索引页面
	 */
	public String view() {
		if (menu_code == null || menu_code.equals("")) {
			menu_code = "first";
		}

		// 更新索引
		getCommparaList();
		 
		// 更新栏目HTML
		Map PCchanelMap = new HashMap();
		PCchanelMap.put("is_webapp", "0");//PC端栏目
		channelList = this.channelService.getList(PCchanelMap);
		jsonMenu = GridTreeUtil.getJsonStr(channelList);
		
		Map webAppchanelMap = new HashMap();
		webAppchanelMap.put("is_webapp", "1");//触屏版栏目
		channelWebAppList= this.channelService.getList(webAppchanelMap);
		jsonWebAppMenu = GridTreeUtil.getJsonStr(channelList);
		
		return goUrl("update");
	}

	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Apr 10, 2014 9:08:09 PM
	 * @Method Description :停止更新索引
	 */
	public synchronized void stopIndex() throws IOException {
		this.index_flag = null;
		this.index_list_size = null;
		this.is_update_index = null;
		if(is_stop==null)
		this.is_stop="0";
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("1");
	}

	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Apr 8, 2014 11:00:29 AM
	 * @Method Description : 判断是否更新的标识
	 */
	public synchronized void isup() throws IOException {
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (is_update_index != null) {
			out.print("-1");// 更新中
		} else {
			out.print("0");// 开始更新
			is_update_index = "1";
			index_flag = "0";// 索引进度初值
		}
	}

	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Apr 11, 2014 3:42:07 PM
	 * @Method Description : 判断停止的进度
	 */
	public void stoprocess() throws IOException {
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (is_stop != null) {
			out.print(is_stop);
		} else {
			out.print("-1");
		}
	}

	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Apr 8, 2014 11:00:29 AM
	 * @Method Description :AJAX获取处理模块长度
	 */
	public synchronized void dealListSize() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Map map = new HashMap();
		// 获取模块名称
		String modelName = request.getParameter("mod");
		modelName = modelName.replace(" ", "");
		String[] modelNames = modelName.split(",");
		if (index_list_size != null) {
			out.print(index_list_size);
			return;
		}
		for (int i = 0; i < modelNames.length; i++) {
			if (modelNames[i].equals("category")) {// 分类
				commondealListSize(this.categoryService, map);
			} else if (modelNames[i].equals("area")) {// 地区
				commondealListSize(this.areaService, map);
			} else if (modelNames[i].equals("goods")) {// 商品
				commondealListSize(this.goodsService, map);
			} else if (modelNames[i].equals("goodsbrand")) {// 商品品牌
				commondealListSize(this.goodsbrandService, map);
			}
		}
		index_list_size = modelNames.length + "";
		out.write(index_list_size);
	}

	/**
	 * @author：XBY
	 * @date：Feb 13, 2014 11:20:41 AM
	 * @Method Description：公共处理模块长度
	 */
	public void commondealListSize(IGenericService g, Map map) throws Exception {
		int count = g.getCount(map);
		List list = BatchListFuc.batchList(count);
		index_list_size = String.valueOf(list.size());
	}

	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Sep 10, 2014 2:50:47 PM
	 * @Method Description : 返回更新索引进度的长度
	 */
	public void loadlength() throws IOException {
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String flag_state = index_flag;
		if (flag_state != null && index_list_size == null) {
			out.print("-1");// 停止更新索引时状态
		} else if (flag_state != null && index_list_size.equals(index_flag)) {
			out.print(index_list_size);
		} else if (index_flag == null) {
			out.print(0);// 初始长度
		} else {
			out.print(index_flag);// 实际进度
		}
		System.out.println(index_flag + "=====" + index_list_size);
		// 清除数据
		if (index_list_size == null || index_list_size.equals(index_flag)) {
			if (index_flag == null && index_list_size == null) {
				is_update_index = null;
			}
		}
	}

	/**
	 * @author：XBY
	 * @date：Mar 3, 2014 3:34:39 PM
	 * @Method Description：获取系统索引方式0表示solr ; 1 表示lucene
	 */
	public void updateIndex() throws Exception {
		if(!validateFactory.isRequired(this.session_cust_id)){
			// 获取系统索引方式0表示solr; 1 表示lucene
			String indexing = SysconfigFuc.getSysValue("cfg_indexing");
			if (indexing.equals("0")) {
				updateIndexBySolr();
			} else {
				updateLuceneIndex();
			}
		}else {
			//判断session时间过期就重新登录
			HttpServletResponse response=this.getResponse();
			getResponse().sendRedirect("/adminindex.action");
		}
		
	}
	/**
	 * @author：XBY
	 * @date：Mar 3, 2014 3:34:39 PM
	 * @Method Description：获取系统索引方式0表示solr ; 1 表示lucene
	 */
	public void isAddIndex() throws Exception {
		
		if(!validateFactory.isRequired(this.session_cust_id)){
			String indexing = SysconfigFuc.getSysValue("cfg_indexing");// 获取系统索引方式0表示solr; 1表示lucene
			if (indexing.equals("0")) {
				updateAddIndexBySolr();
			} else {
				updateAddIndex();
			}
		}else {
			//判断session时间过期就重新登录
			HttpServletResponse response=this.getResponse();
			getResponse().sendRedirect("/adminindex.action");
		}
	}
	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Aug 27, 2014 3:18:42 PM
	 * @Method Description : 更新对应的模块索引
	 */
	public synchronized void updateLuceneIndex() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 实例化建立索引对象
		CreateIndex ci = new CreateIndex();
		// 获取模块名称
		String modelName = request.getParameter("mod");
		modelName = modelName.replace(" ", "");
		String[] modelNames = modelName.split(",");
		for (int i = 0; i < modelNames.length; i++) {
			if (modelNames[i].equals("category")) {// 分类
				createIndex(categoryService, ci, 1);
			} else if (modelNames[i].equals("area")) {// 地区
				createIndex(areaService, ci, 2);
			} else if (modelNames[i].equals("goods")) {// 商品
				createIndex(goodsService, ci, 3);
			} else if (modelNames[i].equals("goodsbrand")) {// 商品品牌
				createIndex(goodsbrandService, ci, 4);
			}else if (modelNames[i].equals("association")) {// 联系搜索词
				createIndex(associationkeywordsService, ci, 5);
			}
			else if (modelNames[i].equals("navigation")) {// 商城标签商品
				createIndex(navigationService, ci, 6);
			}
		}

	}

	/**
	 * @author：XBY
	 * @date：Feb 13, 2014 11:15:27 AM
	 * @Method Description：公共方法
	 */
	public void createIndex(IGenericService g, CreateIndex ci, int cat_type)
			throws Exception {
		Map map = new HashMap();
		int count = g.getCount(map);
		List list = BatchListFuc.batchList(count);
		for (int i = 0; i < list.size(); i++) {
			if (index_list_size == null) {
				is_stop = "0";
				break;
			}
			Map listMap = (HashMap) list.get(i);
			String start = listMap.get("start").toString();
			String limit = listMap.get("limit").toString();
			switch (cat_type) {
			case 1:
				if (i == 0) {
					ci.createCatIndex(" limit " + start + "," + limit, "4");
				} else {
					ci.createCatIndex(" limit " + start + "," + limit, "1");
				}
				break;
			case 2:
				if (i == 0) {
					ci.createAreaIndex(" limit " + start + "," + limit, "4");
				} else {
					ci.createAreaIndex(" limit " + start + "," + limit, "1");
				}
				break;
			case 3:
				if (i == 0) {
					ci.CreateGoodsIndex(" limit " + start + "," + limit, "4");
				} else {
					ci.CreateGoodsIndex(" limit " + start + "," + limit, "1");
				}
				break;
			case 4:
				if (i == 0) {
					ci.createGoodsBrandIndex(" limit " + start + "," + limit,"4");
				} else {
					ci.createGoodsBrandIndex(" limit " + start + "," + limit,"1");
				}
				break;
			case 5:
				if (i == 0) {
					ci.createAssociationIndex(" limit " + start + "," + limit,"4");
				} else {
					ci.createAssociationIndex(" limit " + start + "," + limit,"1");
				}
				break;
			case 6:
				if (i == 0) {
					ci.createMarkGoodsIndex(" limit " + start + "," + limit,"4");
				} else {
					ci.createMarkGoodsIndex(" limit " + start + "," + limit,"1");
				}
				break;
			}
		}
			index_flag = (Integer.valueOf(index_flag) + 1) + "";
	}

	/**
	 * @author : LHY
	 * @throws IOException
	 * @date : Aug 28, 2014 11:26:58 AM
	 * @Method Description : 增量更新索引
	 */
	public void updateAddIndex() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 实例化建立索引对象
		CreateIndex ci = new CreateIndex();
		// 获取模块名称
		String modelName = request.getParameter("mod");
		modelName = modelName.replace(" ", "");
		String[] modelNames = modelName.split(",");
		for (int i = 0; i < modelNames.length; i++) {
			if (modelNames[i].equals("goods")) {// 商品
				ci.createGoodsIncreIndex();
			} else if (modelNames[i].equals("goodsbrand")) {
				ci.createGoodsBrandIncreIndex();
			} else if (modelNames[i].equals("category")) {
				ci.createCatIncreIndex();
			} else if (modelNames[i].equals("area")) {
				ci.createAreaIncreIndex();
			}
		}
		out.print("0");
	}

	/**
	 * @author : WXP
	 * @param :
	 * @date Apr 25, 2014 10:19:40 AM
	 * @Method Description :solr更新对应的模块索引
	 */
	public synchronized void updateIndexBySolr() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 实例化建立索引对象
		SolrCreateIndex sci = new SolrCreateIndex();
		// 获取模块名称
		String modelName = request.getParameter("mod");
		modelName = modelName.replace(" ", "");
		String[] modelNames = modelName.split(",");
		for (int i = 0; i < modelNames.length; i++) {
			if (modelNames[i].equals("category")) {// 分类
				commonUpdateIndexBySolr(this.areaService, sci, 1);
			} else if (modelNames[i].equals("area")) {// 地区
				commonUpdateIndexBySolr(this.areaService, sci, 2);
			} else if (modelNames[i].equals("goods")) {// 商品
				commonUpdateIndexBySolr(this.goodsService, sci, 3);
			} else if (modelNames[i].equals("goodsbrand")) {// 商品品牌
				commonUpdateIndexBySolr(this.goodsbrandService, sci, 4);
			}
		}

	}

	/**
	 * @author：XBY
	 * @date：Feb 13, 2014 12:39:39 PM
	 * @Method Description：公共solr更新对应的模块索引
	 */
	public void commonUpdateIndexBySolr(IGenericService g, SolrCreateIndex sci,
			int solr_type) throws Exception {
		Map map = new HashMap();
		int count = g.getCount(map);
		List list = BatchListFuc.batchList(count);
		for (int i = 0; i < list.size(); i++) {
			if (index_list_size == null) {
				is_stop = "0";
				break;
			}
			Map listMap = (HashMap) list.get(i);
			String start = listMap.get("start").toString();
			String limit = listMap.get("limit").toString();
			switch (solr_type) {
			case 1:
				sci.createCatIndex(" limit " + start + "," + limit);
				break;
			case 2:
				sci.createAreaIndex(" limit " + start + "," + limit);
				break;
			case 3:
				sci.CreateGoodsIndex(" limit " + start + "," + limit);
				break;
			case 4:
				sci.createGoodsBrandIndex(" limit " + start + "," + limit);
				break;
			}
			index_flag = String.valueOf(i + 1);
		}
	}

	/**
	 * @author : WXP
	 * @param :
	 * @date Apr 25, 2014 10:19:40 AM
	 * @Method Description :solr增量更新对应的模块索引
	 */
	public void updateAddIndexBySolr() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 实例化建立索引对象
		SolrCreateIndex sci = new SolrCreateIndex();
		// 获取模块名称
		String modelName = request.getParameter("mod");
		modelName = modelName.replace(" ", "");
		String[] modelNames = modelName.split(",");
		for (int i = 0; i < modelNames.length; i++) {
			if (modelNames[i].equals("goods")) {// 商品
				sci.createGoodsIncreIndex();
				out.print("0");
			} else if (modelNames[i].equals("goodsbrand")) {
				sci.createGoodsBrandIncreIndex();// 品牌
				out.print("0");
			} else if (modelNames[i].equals("category")) {
				sci.createCatIncreIndex();// 分类
				out.print("0");
			} else if (modelNames[i].equals("area")) {
				sci.createAreaIncreIndex();// 地区
				out.print("0");
			}
		}
	}
	
	public String goodsnum="0";
	public int yesgoodsnum=0;
	public int nogoodsnum=0;
	public String goodsid="";//全部商品
	public String yesgoodsid="";//已存在手机详细页的商品
	public String nogoodsid="";//还没有生成手机详情的商品
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String goodstoappdetail() throws Exception {
		
		List glist=new ArrayList();
		glist=GoodsDetailToAppDetailFuc.getGoodList();
		if(glist!=null&&glist.size()>0){
		  //获取商品个数
		  goodsnum=String.valueOf(glist.size());
		  for(int i=0;i<glist.size();i++){
			  HashMap gMap=new HashMap();
			  gMap=(HashMap)glist.get(i);
		  	  if(gMap!=null&&gMap.get("goods_id")!=null){
		  		//获取全部商品ID
		  	    goodsid+=gMap.get("goods_id").toString()+",";
		  	    if(gMap.get("phone_goods_detail")!=null&&!"".equals(gMap.get("phone_goods_detail").toString().trim())){
		  	    	//已经存在手机详情
		  	    	yesgoodsnum++;
		  	    }else {
		  	    //已经存在手机详情
		  	    	nogoodsid+=gMap.get("goods_id").toString()+",";
		  	    	nogoodsnum++;
				}
		  	  }
		  }
		  if(goodsid!=null&&!"".equals(goodsid)){
		    goodsid=goodsid.substring(0,goodsid.length()-1);
		  }
		  if(nogoodsid!=null&&!"".equals(nogoodsid)){
			  nogoodsid=nogoodsid.substring(0,nogoodsid.length()-1);
		  }
		}
		return goUrl("goodsappdetail");
	}
	
	/**
	 * @Method Description :导出图片异常的商品
	 * @author : HZX
	 * @date : Mar 14, 2014 2:22:22 PM
	 */
	public String exportPIC() throws Exception {
		
		HashMap gMap=new HashMap();
		gMap.put("sgis", pic_goods_id);
		List gList=new ArrayList();
		gList=goodsService.getList(gMap);
		goodsService.exportGoodsPic(gList, getResponse());
		return goodstoappdetail();
	}
	
	

	public String getGoodsnum() {
		return goodsnum;
	}

	public void setGoodsnum(String goodsnum) {
		this.goodsnum = goodsnum;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	/**
	 * @return the commparalist
	 */
	public List getCommparalist() {
		return commparalist;
	}

	/**
	 * @param commparalist
	 *            the commparalist to set
	 */
	public void setCommparalist(List commparalist) {
		this.commparalist = commparalist;
	}
}
