/*
  
 
 * Package:com.rbt.action
 * FileName: CollectAction.java 
 */
package com.rbt.webappaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Collect;
import com.rbt.model.Goods;
import com.rbt.model.Member;
import com.rbt.service.ICollectService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMembercatService;
import com.rbt.service.IShopconfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * @function 功能 记录会员商机收藏信息action类
 * @author 创建人 CYC
 * @date 创建日期 Wed Jul 20 14:27:09 CST 2014
 */
@Controller
public class WebAppcollectionAction extends WebAppbaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	public Collect collect;
	public Member member;
	private Goods goods;

	/*******************业务层接口****************/
	@Autowired
	public ICollectService collectService;
    @Autowired
	public IMemberService memberService;
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IMembercatService membercatService;
	@Autowired
	private IShopconfigService shopconfigService;

	/*********************集合******************/
	public List collectionList;//记录会员商机收藏信息信息集合
	public List goodsList;//记录商品表信息信息集合
	public List membercatList;// 会员产品自定义分类List

	/*********************字段******************/
	public String page_url;//url值
	public String starttime_s;//收藏时间开始
	public String endtime_s;//收藏时间结束
	public String title_s;//收藏标题
	public String cust_name_s;//商家
	public String coll_type_s="0";//收藏类型
	public String cat_id_s;//按照自定义分类搜索
	public String cust_id_s;//分组列表页标记，用于详细列表页
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String webappCollection() throws Exception {
		webappcommonList();
		return goUrl("mbappCollection");
	}
	
	/**
	 * 公共查询方法
	 */
	public void webappcommonList(){
		//页面搜索提交的参数
		Map pageMap = new HashMap();
		// 操作者为会员则默认加入搜索条件
		pageMap.put("cust_id", this.session_cust_id);
		//过滤地区
		//pageMap=super.areafilter(pageMap);
		
		//根据页面提交的条件找出信息总数
		int count=this.collectService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		collectionList = this.collectService.getList(pageMap);
	}
	
	
	/**
	 * 方法描述：删除记录会员商机收藏信息信息
	 * @return
	 * @throws Exception
	 */
	public String webappdelete() throws Exception {
		boolean flag = this.collectService.delete(chb_id);
		if(flag){
			this.addActionMessage("取消收藏成功");
		}else{
			this.addActionMessage("取消收藏失败");
		}
		return webappCollection();
	}
	
	
	
	public void setPlatType(){
		mall_type =Constants.MALL_TYPE_B2C;
	}

	//为操作准备会员自定义分类下拉框
	public void setMemberCatList(){
		Map map = new HashMap();
		// 操作者为会员则默认加入搜索条件，绑定会员自定义产品分类下拉框的绑定
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			map.put("cust_id", this.session_cust_id);
			//cat_type  0：产品 1：收藏 2：商友
			map.put("cat_type","1");
		}
		membercatList = this.membercatService.getList(map);
	}
	
	/**
	 * 方法描述：返回新增记录会员商机收藏信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//为操作准备会员自定义分类下拉框
		setMemberCatList();
		//发布信息数量控制
		//if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
		//	controlInfoNum();
		//}
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录会员商机收藏信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		//collect.setLink_url(page_url);
		//获取客户标识
//		setPlatType();
		this.collect.setCust_id(this.session_cust_id);
//		collect.setPlat_type(mall_type);
		//发布信息数量控制
		//if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
		//	if(controlInfoNum()){
			//	return add();
			//}
		//}
		//字段验证
		super.commonValidateField(collect);
		if(super.ifvalidatepass){
			return view();
		}
		this.collectService.insert(collect);
		this.addActionMessage("收藏成功");
		this.collect = null;
//		is_crotorl=true;
		return add();
	}
	
	public void ajxinsert() throws IOException{
		
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//colltype 0标识商品  1标识店铺
		//String colltype=request.getParameter("colltype");
		
		String title =  URLDecoder.decode(request.getParameter("title"), "UTF-8");
		String link_url = URLDecoder.decode(request.getParameter("link_url"), "UTF-8");
		String goods_id=request.getParameter("goods_id");
		String price=request.getParameter("price");
		String radom_no=request.getParameter("radom_no");
		String coll_type="";
		String id=null;
		if(ValidateUtil.isRequired(goods_id) ){
			coll_type="1";
			Map map=new HashMap();
			map.put("radom_no", radom_no);
			link_url="/shopsIndex.action?radom_no="+radom_no;
			List shopList=this.shopconfigService.getList(map);
			if (shopList != null && shopList.size()>0) {
				Map shopMap=(HashMap)shopList.get(0);
				id = shopMap.get("cust_id").toString();
			}else{
		    	out.write("1");//异常情况
		    }
			
		}else{
			coll_type="0";
		    id=goodsService.get(goods_id).getCust_id();
		}
		setPlatType();
		Map map=new HashMap();
		if(id.equals(this.session_cust_id)){
			out.write("3");
		}else if(this.session_cust_id!=null && !"".equals(this.session_cust_id)){
			map.put("cust_id",this.session_cust_id);
			if(link_url!=null && !"".equals(link_url))
				map.put("link_url",link_url);
				List list=this.collectService.getList(map);
			if(list.size()>0){
				out.write("2");
			}else{
				collect.setTitle(title);
				collect.setLink_url(link_url);
				collect.setCust_id(this.session_cust_id);
				collect.setGoods_id(goods_id);
				collect.setColl_type(coll_type);
				collect.setRemark(price);
				collectService.insert(collect);
				out.write("0");
			}
		}else{
			out.write("1");
		}
		
	}

	/**
	 * 方法描述：修改记录会员商机收藏信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(collect.getColl_type().equals("1")){
			collect.setGoods_id(null);
		}
		//字段验证
		super.commonValidateField(collect);
		if(super.ifvalidatepass){
			return view();
		}
		this.collectService.update(collect);
		this.addActionMessage("修改收藏成功");
		return list();
	}
	/**
	 * 方法描述：删除记录会员商机收藏信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.collectService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除收藏成功");
		}else{
			this.addActionMessage("删除收藏失败");
		}
		return list();
	}
	/**
	 * 方法描述：删除记录会员商机收藏信息信息
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception{
		boolean flag = this.collectService.delete(collect.getColl_id());
		if(flag){
			this.addActionMessage("删除收藏成功");
		}else{
			this.addActionMessage("删除收藏失败");
		}
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		commonList();
		return goUrl(INDEXLIST);
	}
	/**
	 * @Method Description :
	 * @author : HZX
	 * @throws IOException 
	 * @date : Apr 22, 2015 1:54:41 PM
	 */
	public void pageList() throws IOException{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String currentPage = "1";
		StringBuffer sb = new StringBuffer();
		if (request.getParameter("cp") != null) {
			currentPage = request.getParameter("cp");
		}
		int startRow = (Integer.parseInt(currentPage) - 1) * pageSize_s;
		Map pageMap = new HashMap();
		pageMap.put("start", startRow);
		pageMap.put("limit", pageSize_s);
		setMemberCatList();
		setPlatType();
		//页面搜索提交的参数
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}else{
			if (!ValidateUtil.isRequired(cust_id_s)) {
				pageMap.put("cust_id", cust_id_s);
			}
		}
	    if(!ValidateUtil.isRequired(coll_type_s))pageMap.put("coll_type", coll_type_s);
		if(title_s!=null && !title_s.equals("")) pageMap.put("title", title_s);
		if(cust_name_s!=null && !cust_name_s.equals("")) pageMap.put("cust_name", cust_name_s);
		if(starttime_s!=null && !starttime_s.equals("")) pageMap.put("starttime", starttime_s);
		if(endtime_s!=null && !endtime_s.equals("")) pageMap.put("endtime", endtime_s);
		if(cat_id_s!=null && !cat_id_s.equals("")) pageMap.put("cat_id", cat_id_s);
		//过滤地区
		pageMap=super.areafilter(pageMap);
		collectList = this.collectService.getList(pageMap);
		for(int i=0;i<collectList.size();i++){
			Map cMap=(Map) collectList.get(i);
			sb.append("<div class='hoDiv'>"
	 		  +"<input type='hidden' name='collection.coll_id' value="+cMap.get("coll_id").toString()+"/>"
	 		 +" <table>"
	 		+" <tr>"
	 		+"<td>"
	 		+" <div class='imgDiv'>"
			               
	 		+"   <a href='/webapp/goodsdetail/"+cMap.get("goods_id").toString()+".html' >"
	 		+"   <img src="+cMap.get("list_img").toString()+"/></a>"   
	 		+"   </div>"
	 		+"  </td>"
	 		+"  <td>"
	 		+"  <p><a href=/webapp/goodsdetail/"+cMap.get("goods_id").toString()+".htmls>"+cMap.get("goods_name").toString()+"</a></p>"
	 		+"  <p><b>￥"+cMap.get("sale_price").toString()+"</b></p>"
	 		+"  <p><a  href=\"javascript:void(0);\" onclick='webappdelOneInfo(\"/webapp/collection!webappdelete.action\",\"chb_id\",\""+cMap.get("coll_id").toString()+"\");' class=\"mBut\">取消收藏</a></p>"
	 		+" </td>"
	 		+"  </tr>"
	 		+" </table>"
	 		+" </div>");
		}
		PrintWriter out = response.getWriter();
		// 如果列表存在数据则输出，否则则输出空
		out.write(sb.toString());
		
		
		
		
	}
	
	/**
	 * @author:HXM
	 * @date:May 30, 20149:49:50 AM
	 * @param:
	 * @Description:通过对用户cust_id进行分组查询得到信息
	 */
	public String groupList() throws Exception {
		Map pageMap = new HashMap();
		if (!ValidateUtil.isRequired(cust_name_s)) {
			pageMap.put("cust_name", cust_name_s.trim());
		}
		if(!ValidateUtil.isRequired(coll_type_s)){
			pageMap.put("coll_type", coll_type_s);
		}
		// 根据页面提交的条件找出信息总数
		int count = this.collectService.getGroupCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		collectList = this.collectService.getListByGroup(pageMap);
		return goUrl("groupindex");
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String shopList() throws Exception {
		commonList();
		return goUrl("shopindex");
	}
	/**
	 * 公共查询方法
	 */
	public void commonList(){
		setMemberCatList();
		setPlatType();
		//页面搜索提交的参数
		Map pageMap = new HashMap();
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}else{
			if (!ValidateUtil.isRequired(cust_id_s)) {
				pageMap.put("cust_id", cust_id_s);
			}
		}
	    if(!ValidateUtil.isRequired(coll_type_s))pageMap.put("coll_type", coll_type_s);
		if(title_s!=null && !title_s.equals("")) pageMap.put("title", title_s);
		if(cust_name_s!=null && !cust_name_s.equals("")) pageMap.put("cust_name", cust_name_s);
		if(starttime_s!=null && !starttime_s.equals("")) pageMap.put("starttime", starttime_s);
		if(endtime_s!=null && !endtime_s.equals("")) pageMap.put("endtime", endtime_s);
		if(cat_id_s!=null && !cat_id_s.equals("")) pageMap.put("cat_id", cat_id_s);
		
		//过滤地区
		pageMap=super.areafilter(pageMap);
		
		//根据页面提交的条件找出信息总数
		int count=this.collectService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		collectList = this.collectService.getList(pageMap);
	}
	
	/**
	 * 方法描述：根据主键找出记录会员商机收藏信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(collect.getCust_id()!=null){
			if(accessControl(collect.getCust_id())){
				return list();
			}
		}
		setMemberCatList();
		String id = collect.getColl_id();
		if(!ValidateUtil.isDigital(id)){
			collect = this.collectService.get(id);
			String goods_id=collect.getGoods_id();
			if(goods_id!=null&&!goods_id.equals("")){
				goods=this.goodsService.get(goods_id);
			}
			
			
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the CollectList
	 */
	public List getCollectList() {
		return collectList;
	}
	/**
	 * @param collectList
	 *  the CollectList to set
	 */
	public void setCollectList(List collectList) {
		this.collectList = collectList;
	}
	public String getPage_url() {
		return page_url;
	}
	public void setPage_url(String page_url) {
		this.page_url = page_url;
	}

	public String getStarttime_s() {
		return starttime_s;
	}


	public void setStarttime_s(String starttime_s) {
		this.starttime_s = starttime_s;
	}


	public String getEndtime_s() {
		return endtime_s;
	}


	public void setEndtime_s(String endtime_s) {
		this.endtime_s = endtime_s;
	}


	public String getTitle_s() {
		return title_s;
	}
	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}
	public String getCust_name_s() {
		return cust_name_s;
	}
	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}
	public List getMembercatList() {
		return membercatList;
	}
	public void setMembercatList(List membercatList) {
		this.membercatList = membercatList;
	}
	public String getCat_id_s() {
		return cat_id_s;
	}
	public void setCat_id_s(String cat_id_s) {
		this.cat_id_s = cat_id_s;
	}
	
	/**
	 * @return the collect
	 */
	public Collect getCollect() {
		return collect;
	}
	/**
	 * @param Collect
	 *            the collect to set
	 */
	public void setCollect(Collect collect) {
		this.collect = collect;
	}

	public void prepare() throws Exception { 
		super.saveRequestParameter();
		if(collect == null){
			collect = new Collect();
		}
		String id = collect.getColl_id();
		if(!ValidateUtil.isDigital(id)){
			collect = this.collectService.get(id);
		}
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public List getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List goodsList) {
		this.goodsList = goodsList;
	}
}

