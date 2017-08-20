/*
 
 * Package:com.rbt.action
 * FileName: TrygoodsAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CategoryFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Goods;
import com.rbt.model.Trygoods;
import com.rbt.service.IGoodsService;
import com.rbt.service.IShopconfigService;
import com.rbt.service.ITryapplyService;
import com.rbt.service.ITrygoodsService;
import com.opensymphony.xwork2.Preparable;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 试用商品action类
 * @author 创建人 CYC
 * @date 创建日期 Tue Jun 17 13:55:37 CST 2014
 */
@Controller
public class TrygoodsAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 试用商品对象
	 */
	private Trygoods trygoods;
	/*******************业务层接口****************/
	/*
	 * 试用商品业务层接口
	 */
	@Autowired
	private ITrygoodsService trygoodsService;
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private ITryapplyService tryapplyService;
	@Autowired
	private IShopconfigService shopconfigService;
	
	
	
	/*********************集合*******************/
	/*
	 * 试用商品信息集合
	 */
	public List trygoodsList;
	/*********************字段*******************/
	public String chb_id;
	public String hidden_goodsname;
	public String title_s;
	public String type_s;
	public String state_s;
	public String cat_attr;
	public String goods_id;
	public String cat_name;
	public String datenow;
	public Goods goods;
	public int countnum;
	public String cfg_weibo_url = SysconfigFuc.getSysValue("cfg_weibo_url");
	public List weiboList;
	public String shop_name;
		
	/**
	 * 方法描述：返回新增试用商品页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//获取试用商品类型
		getCommparea();
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增试用商品
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		trygoods.setCat_attr(cat_attr);
		trygoods.setCust_id(this.session_cust_id);
		trygoods.setSurplus(trygoods.getTrynum());
		if(trygoods.getTry_type().equals("0")&&ValidateUtil.isRequired(trygoods.getPostage())){
			this.addFieldError("trygoods.postage", "邮费不能为空！");
		}
		if(goods_id!=null && !"".equals(goods_id)){
			trygoods.setGoods_id(goods_id);
		}
		
		if(ValidateUtil.isRequired(hidden_goodsname)){
			this.addFieldError("trygoods.postage", "试用标题不能为空！");
		}else {
			trygoods.setTry_title(hidden_goodsname);
		}
		
		
		
		super.commonValidateField(trygoods);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.trygoodsService.insert(trygoods);
		//修改商品的活动状态
		Map map =new HashMap();
		map.put("goods_id", trygoods.getGoods_id());
		map.put("active_state", "5");
		goodsService.updateActiveState(map);
		this.addActionMessage("新增试用商品成功！");
		hidden_goodsname=null;
		this.trygoods = null;
		return add();
	}

	/**
	 * 方法描述：修改试用商品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		trygoods.setCat_attr(cat_attr);
		if("1".equals(trygoods.getInfo_state())){
			if(trygoods.getTry_type().equals("1")&&(trygoods.getWeibo_id()==null || "".equals(trygoods.getWeibo_id()))){
				this.addFieldError("trygoods.weibo_id", "请填写微博ID号！");
			}
		}
		if(trygoods.getTry_type().equals("0")&&ValidateUtil.isRequired(trygoods.getPostage())){
			this.addFieldError("trygoods.postage", "邮费不能为空！");
		}
		
		if(ValidateUtil.isRequired(hidden_goodsname)){
			this.addFieldError("trygoods.postage", "试用标题不能为空！");
		}else {
			trygoods.setTry_title(hidden_goodsname);
		}
		
		super.commonValidateField(trygoods);
		if(super.ifvalidatepass){
			return view();
		}
		this.trygoodsService.update(trygoods);
		//修改商品的活动状态
		Map map =new HashMap();
		map.put("goods_id", trygoods.getGoods_id());
		map.put("active_state", "5");
		goodsService.updateActiveState(map);
		this.addActionMessage("修改试用商品成功！");
		return list();
	}
	/**
	 * 方法描述：删除试用商品信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除试用商品信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.trygoodsService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除试用商品信息成功!");
		}else{
			this.addActionMessage("删除试用商品信息失败!");
		}
	}
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		type_s="0";
		comonList();
		return goUrl(INDEXLIST);
	}
	public String mList() throws Exception {
		type_s="1";
		comonList();
		return goUrl("mList");
	}
	public void comonList(){
		Map pageMap = new HashMap();
		//商品名称搜索
		if(title_s!=null && !"".equals(title_s)){
			pageMap.put("title", title_s);
		}
		if(type_s!=null && !"".equals(type_s)){
			pageMap.put("type", type_s);
		}
		if(state_s!=null && !"".equals(state_s)){
			pageMap.put("state", state_s);
		}
		if(this.session_cust_type.equals(Constants.MEMBER_TYPE)){
			pageMap.put("cust_id", this.session_cust_id);
		}
		//获取系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		datenow = df.format(new Date());
		//根据页面提交的条件找出信息总数
		int count=this.trygoodsService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		trygoodsList = this.trygoodsService.getList(pageMap);
		getCommparea();
		
	}
	/**
	 * 方法描述：根据主键找出试用商品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.trygoods.getTry_id();
		if(id==null || id.equals("")){
			trygoods = new Trygoods();
		}else{
			trygoods = this.trygoodsService.get(id);
			//获取报名人数
			HashMap map = new HashMap();
			map.put("try_id", id);
			countnum = this.tryapplyService.getCount(map);
		}
		
		//获取系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		datenow = df.format(new Date());
		//获取试用商品类型
		hidden_goodsname = trygoods.getTry_title();
		viewCat(trygoods.getCat_attr());
		getCommparea();
		// 分类名称
		String back_sel_cat = trygoods.getCat_attr();
		String trygoods_cat = back_sel_cat.replace(catIdStr + ",", "").replace(" ", "");
		cat_name = CategoryFuc.getCateNameByMap(trygoods_cat);
		
		return goUrl(VIEW);
	}
	
	/**
	 * 方法描述：新增记录商品咨询信息
	 * 
	 */
    public void getCommparea(){
    	Map map=new HashMap();
		map.put("para_code", "try_type");
		commparaList = commparaService.getList(map);
    }
	
	
	/**
	 * 方法描述：批量排序
	 * 
	 * @return
	 * @throws Exception
	 */

	public String updateSort() throws Exception {
		commonSort();
		return list();
	}
	
	
	
	//查看微博转发列表
	public String weibolist(){
		String try_id = getRequest().getParameter("try_id");
		if(try_id!=null && !"".equals("try_id")){
			trygoods = trygoodsService.get(try_id);
			goods = goodsService.get(trygoods.getGoods_id());
			HashMap map = new HashMap();
			map.put("cust_id", goods.getCust_id());
			List shopconfiglist = shopconfigService.getList(map);
			if(shopconfiglist!=null && shopconfiglist.size()>0){
				HashMap mapvalue= (HashMap)shopconfiglist.get(0);
				if(mapvalue.get("shop_name")!=null && !"".equals(mapvalue.get("shop_name")))
			    shop_name = mapvalue.get("shop_name").toString();
			}
		}
		
		return goUrl("weibo");
	}
	
	// ajax获取
	public void ajaxweibo() throws IOException {
		commonAjax();
		StringBuffer buf = new StringBuffer();
		buf.append("<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" class=\"indexTab\"><tr><th width=\"10%\">排名</th><th width=\"10%\">微博头像</th><th width=\"10%\">微博名称</th><th width=\"10%\">转发数</th></tr>");
		for (int i = 0; i < weiboList.size(); i++) {
			int j = i + 1;
			buf.append("<tr><td align=\"center\">" + j + "</td>");
			HashMap mapvalue = (HashMap) weiboList.get(i);
			buf.append("<td align=\"center\"><img src=\"" + mapvalue.get("profile_image_url") + "\"/></td>");
			buf.append("<td align=\"center\">" + mapvalue.get("screen_name") + "</td>");
			buf.append("<td align=\"center\">" + mapvalue.get("reposts_count") + "</td></tr>");
		}
		buf.append("</table>");
		HttpServletResponse response = getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.write(buf.toString());
	}
	
	
	// ajax获取
	public void ajaxWeibo() throws IOException {
		commonAjax();
		StringBuffer buf = new StringBuffer();
		buf.append("<table cellspacing=\"0\" class=\"bmall_list_table\"><tr><th class=\"fthstyle1\">排名</th><th class=\"fthstyle1\">微博头像</th><th class=\"fthstyle1\">微博名称</th><th class=\"fthstyle1\">转发数</th></tr>");
		for (int i = 0; i < weiboList.size(); i++) {
			int j = i + 1;
			buf.append("<tr><td align=\"center\">" + j + "</td>");
			HashMap mapvalue = (HashMap) weiboList.get(i);
			buf.append("<td align=\"center\"><img src=\"" + mapvalue.get("profile_image_url") + "\"/></td>");
			buf.append("<td align=\"center\">" + mapvalue.get("screen_name") + "</td>");
			buf.append("<td align=\"center\">" + mapvalue.get("reposts_count") + "</td></tr>");
		}
		buf.append("</table>");
		HttpServletResponse response = getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.write(buf.toString());
	}
	
	public void commonAjax(){
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
				if (weiboList.size() > 50) {
					break;
				}
			}
			// Collections.sort(weiboList);
			Collections.sort(weiboList, comparator);
		}
	}
	
	
	Comparator<Map> comparator = new Comparator<Map>() {
		public int compare(Map m1, Map m2) {
			// 按转发量排序
			return Integer.parseInt(m2.get("reposts_count").toString())
					- Integer.parseInt(m1.get("reposts_count").toString());
		}
	};
	
	/**
	 * @author : CYC
	 * @date : Apr 19, 2014 12:57:37 PM
	 * @Method Description :排序
	 */
	private void commonSort(){
		boolean flag = this.trygoodsService.updateSort("try_id", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("试用商品排序成功");
		}else{
			this.addActionMessage("试用商品排序失败");
		}
	}
	
	/**
	 * @return the TrygoodsList
	 */
	public List getTrygoodsList() {
		return trygoodsList;
	}
	/**
	 * @param trygoodsList
	 *  the TrygoodsList to set
	 */
	public void setTrygoodsList(List trygoodsList) {
		this.trygoodsList = trygoodsList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(trygoods == null){
			trygoods = new Trygoods();
		}
		String id = this.trygoods.getTry_id();
		if(!this.validateFactory.isDigital(id)){
			trygoods = this.trygoodsService.get(id);
		}
	}
	/**
	 * @return the trygoods
	 */
	public Trygoods getTrygoods() {
		return trygoods;
	}
	/**
	 * @param Trygoods
	 *            the trygoods to set
	 */
	public void setTrygoods(Trygoods trygoods) {
		this.trygoods = trygoods;
	}
}

