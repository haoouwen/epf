package com.rbt.webaction;

import it.unimi.dsi.fastutil.objects.Reference2FloatMap.Entry;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.function.GoodsSpreadFuc;
import com.rbt.function.PageTipFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Goods;
import com.rbt.model.Goodsattr;
import com.rbt.model.Shopconfig;
import com.rbt.model.Tryapply;
import com.rbt.model.Trygoods;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.IBuyeraddrService;
import com.rbt.service.ICategoryService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IShopconfigService;
import com.rbt.service.ITryapplyService;
import com.rbt.service.ITrygoodsService;

public class WebtrygoodsAction extends goodsModelAction implements Preparable {

	/** *****************实体层*************** */
	public Trygoods trygoods;
	/** *****************业务层接口*************** */
	@Autowired
	private ITrygoodsService trygoodsService;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private ITryapplyService tryapplyService;

	@Autowired
	private IShopconfigService shopconfigService;

	@Autowired
	private IBuyeraddrService buyeraddrService;
	
	@Autowired
	private IAdvinfoService advinfoService;
	
	@Autowired
	private IGoodsorderService goodsorderService;
	
	

	public String cat_attr;
	public String tryid;
	public List freetrygoodsList;
	public List postagetrygoodsList;
	public List categoryList;
	public List weiboList;
	public List tryapplyList;
	public List buyaddList;
	public List advinfoList;
	public Goods goods;
	public Goodsattr goodsattr;
	public Shopconfig shopconfig;
	public String cfg_weibo_url = SysconfigFuc.getSysValue("cfg_weibo_url");
	public int count;
	public String datenow;
	public String freeuse_agreement_page;
	public List goodsspreadList;
	

	/**
	 * @author : CYC
	 * @throws Exception
	 * @date : Mar 13, 2014 1:26:22 PM
	 * 
	 * @Method Description : 商品列表页
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		// 获取免费试用列表信息
		HashMap mapfree = new HashMap();
		mapfree.put("type", "1");
		mapfree.put("info_state","1");
		mapfree.put("viewtime","1");
		freetrygoodsList = trygoodsService.getList(mapfree);
		
		Map advMap = new HashMap();
		advMap.put("pos_id", "38");
		// 取出正在播放的广告信息
		advMap.put("isshow", "0");
		advinfoList = this.advinfoService.getList(advMap);

		// 获取邮费试用列表信息
		HashMap mappostage = new HashMap();
		mappostage.put("info_state","1");
		mappostage.put("viewtime","1");
		mappostage.put("type", "0");
		postagetrygoodsList = trygoodsService.getList(mappostage);

		// 获取试用商品分类
		HashMap goodstrmap = new HashMap();
		goodstrmap.put("module_type", "trygoods");
		categoryList = categoryService.getList(goodstrmap);
		return goUrl("trygoodsList");
	}

	public String detail() {
		tryid = getRequest().getParameter("tryid");
		if (tryid != null && !"".equals(tryid))
			trygoods = trygoodsService.get(tryid);
		if (trygoods != null) {
			goods = goodsService.get(trygoods.getGoods_id());
		}
		// 获取试用商品名称
		if (goods != null) {
			HashMap map = new HashMap();
			map.put("goods_item", goods.getGoods_no());
			List goodsattrList = goodsattrService.getList(map);
			if (goodsattrList != null && goodsattrList.size() > 0) {
				HashMap mapvalue = new HashMap();
				mapvalue = (HashMap) goodsattrList.get(0);
				goodsattr = goodsattrService.get(mapvalue.get("goods_item")
						.toString());
			}
		}

		// 获取申请人数
		HashMap mapcount = new HashMap();
		mapcount.put("try_id", tryid);
		count = this.tryapplyService.getCount(mapcount);
		// 获取试用商品评论
		HashMap map = new HashMap();
		map.put("try_id", tryid);
		//comment评论不为空
		map.put("comment","0");
		tryapplyList = tryapplyService.getList(map);
		
		shopconfig = this.shopconfigService.getByCustID(goods.getCust_id());
		// 获取店铺地址
		if (shopconfig.getArea_attr() != null
				&& !"".equals(shopconfig.getArea_attr()))
			area_attr = AreaFuc.getAreaNameByMap(shopconfig.getArea_attr());
		// 获取买家收货地址
		getBuyerAddrList();
		//获取系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		datenow = df.format(new Date());
		// 试用条款
		freeuse_agreement_page = PageTipFuc.getPageCon("freeuse_agreement_page");
		goodsspreadList = GoodsSpreadFuc.getGoodsSpreadList("7", false);
		return goUrl("trygoodsDetail");
	}

	// ajax获取
	public void ajaxweibo() throws IOException {
		String data = getRequest().getParameter("jsondata");
		JSONObject jsonObj = JSONObject.fromObject(data);
		JSONArray repostsArray = jsonObj.getJSONArray("reposts");
		weiboList = new ArrayList();
		if (repostsArray.size() > 0) {
			for (int i = 0; i < repostsArray.size(); i++) {
				String repoststStr = repostsArray.getString(i);
				JSONObject repostsObj = JSONObject.fromObject(repoststStr);
				String reposts_count = repostsObj.getString("reposts_count");
				// 获取转发条数
				String user = repostsObj.getString("user"); // 获取位置3的值
				JSONObject userObj = JSONObject.fromObject(user); // 将其转化为JSONObject
				boolean flag = true;
				for (int j = 0; j < weiboList.size(); j++) {
					HashMap mapvalue = (HashMap) weiboList.get(j);
					if (mapvalue.get("idstr")
							.equals(userObj.getString("idstr"))) {
						String count = mapvalue.get("reposts_count").toString();
						flag = false;
						if (Integer.parseInt(count) < Integer
								.parseInt(reposts_count)) {
							mapvalue.put("reposts_count", reposts_count);
							weiboList.set(j, mapvalue);
						}
					}
				}
				if (flag) {
					// map
					Map map = new HashMap();
					map.put("idstr", userObj.getString("idstr"));
					map.put("screen_name", userObj.getString("screen_name"));
					map.put("profile_image_url", userObj
							.getString("profile_image_url"));
					map.put("reposts_count", reposts_count);
					weiboList.add(map);
				}
				if (weiboList.size() > 20) {
					break;
				}
			}
			// Collections.sort(weiboList);
			Collections.sort(weiboList, comparator);
		}
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < weiboList.size(); i++) {
			int j = i + 1;
			buf.append("<tr><th><span>" + j + "</span><img src=\"");
			HashMap mapvalue = (HashMap) weiboList.get(i);
			buf.append(mapvalue.get("profile_image_url"));
			buf.append("\" align=\"absmiddle\" width=\"25\" height=\"25\">");
			buf.append(mapvalue.get("screen_name"));
			buf
					.append("</th><td><img src=\"/malltemplate/bmall/images/wbNum_18.gif\" align=\"absmiddle\">");
			buf.append(mapvalue.get("reposts_count"));
			buf.append("</td></tr>");
		}
		HttpServletResponse response = getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.write(buf.toString());
	}

	Comparator<Map> comparator = new Comparator<Map>() {
		public int compare(Map m1, Map m2) {
			// 按转发量排序
			return Integer.parseInt(m2.get("reposts_count").toString())
					- Integer.parseInt(m1.get("reposts_count").toString());
		}
	};

	// map转换成lisy
	public static List mapTransitionList(Map map) {
		List list = new ArrayList();
		Iterator iter = map.entrySet().iterator(); // 获得map的Iterator
		while (iter.hasNext()) {
			Entry entry = (Entry) iter.next();
			list.add(entry.getValue());
		}
		return list;
	}

	/**
	 * 判断是否登录
	 */
	public void isLogin() throws IOException {
		PrintWriter out = getResponse().getWriter();
		// 判断是否登录
		if (this.session_cust_id.equals("")) {
			out.write("1");
		} else {
			out.write("2");
		}
	}

	/**
	 * @author : XBY
	 * @param :
	 * @date Mar 6, 2014 2:36:29 PM
	 * @Method Description :新增收货地址
	 */
	public void addAddr() throws Exception {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 商城保存收货地址最大数
		if (this.session_cust_id != null && !this.session_cust_id.equals("")) {
			Map map = new HashMap();
			map.put("cust_id", this.session_cust_id);
			int count = this.buyeraddrService.getCount(map);
			if (count >= cfg_maxaddressnumber) {
				out.write("1");
			} else {
				// 获取地区html
				String addrDiv = this.trygoodsService.getAddrDiv(request,
						this.session_cust_id, this.session_user_name);// 修改成插入用户名
				out.write(addrDiv);
			}
		} else {
			out.write("0");
		}

	}

	/**
	 * @author : XBY
	 * @param :cust_id
	 * @date Mar 6, 2014 1:46:29 PM
	 * @Method Description :获取用户的收货地址
	 */
	public void getBuyerAddrList() {
		Map addrMap = new HashMap();
		addrMap.put("cust_id", this.session_cust_id);
		buyaddList = this.buyeraddrService.getList(addrMap);
		buyaddList = ToolsFuc.replaceList(buyaddList, "");
	}

	/**
	 * ajax添加试用申请
	 */
	public void addTryApply() throws Exception {
		
		// 创建servlet对象
		PrintWriter out = getResponse().getWriter();
		
		//获取用户绑定的微博ID号
		String weiboid = getRequest().getParameter("weiboid");
		//获取商品ID
		String try_id = getRequest().getParameter("try_id");
		//获取收货地址ID
		String add_id = getRequest().getParameter("add_id");
		//获取试用类型
		String try_type =getRequest().getParameter("try_type");
        //订单状态
		String order_state="1";
		
		//获取申请试用集合
		Map map = new HashMap();
		map.put("user_id", this.session_user_id);
		map.put("try_id", try_id);
		tryapplyList = this.tryapplyService.getList(map);
		
		//获取试用商品集合
		Map tryMap=new HashMap();
		tryMap.put("try_id", try_id);
		tryMap.put("cust_id",this.session_cust_id );
		List trygoodsList=trygoodsService.getList(tryMap);
		
		//判断是否为付邮试用且申请试用列表不为空
		if(try_type.equals("0")&&tryapplyList != null && tryapplyList.size() > 0){
			
			//获取订单编号
			Map orderMap=(HashMap)tryapplyList.get(0);
			String try_order_id=orderMap.get("order_id").toString();
			
	        //判断订单编号不为空
			if(!ValidateUtil.isRequired(try_order_id)){
			
				 //通过订单编号查询整条订单，并获取订单状态
				Map sorderMap=new HashMap();			
				sorderMap.put("order_id", try_order_id);
			    List list=goodsorderService.getList(sorderMap);
			    
			    //判断list集合不为空
			    if(list!=null&&list.size()>0){
			    	
			    	//获取订单状态
			    	Map gorderMap=(HashMap)list.get(0);
			    	order_state=gorderMap.get("order_state").toString();
			    }
			}
		}
		
		//判断自己不能申请自己的使用商品
		if(trygoodsList!=null&&trygoodsList.size()>0){
			
			out.write("3");
		
		}else if (tryapplyList == null || tryapplyList.size() == 0 || order_state.equals("0")) {//判断是否有申请过
			
			//申请试用表对象赋值
			Tryapply tryapply = new Tryapply();
			tryapply.setUser_id(this.session_user_id);
			tryapply.setTry_id(try_id);
			tryapply.setAdd_id(add_id);
			tryapply.setWebboid(weiboid);
			tryapply.setStatus("0");
			
			//判断是否为付邮试用
			if(!ValidateUtil.isRequired(try_type)&&try_type.equals("0")){
				
				//获取订单号
				Map orderMap=new HashMap();
				orderMap.put("addr_id", add_id);
				orderMap.put("session_cust_id", this.session_cust_id);
				orderMap.put("session_user_id", this.session_user_id);
				orderMap.put("try_id", try_id);
				orderMap.put("session_level_code",this.session_level_code);
				String order_id=this.trygoodsService.addOrder(orderMap);
				
			    //申请试用表对象赋值订单编号
				tryapply.setOrder_id(order_id);
			}
			
			//新增申请试用表
			this.tryapplyService.insert(tryapply);
			
			out.write("1");
		
		} else {
			
			out.write("2");
		
		}
	}

	public void prepare() throws Exception {
		// 初始化StringBuilder
		postsb = new StringBuilder();
	}

}
