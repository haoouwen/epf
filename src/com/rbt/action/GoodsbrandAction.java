/*
 
 * Package:com.rbt.action
 * FileName: GoodsbrandAction.java 
 */
package com.rbt.action;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Autofck;
import com.rbt.model.Goodsbrand;
import com.rbt.model.Goodsorder;
import com.rbt.service.IGoodsbrandService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 商品品牌表action类
 * @author 创建人 HXK
 * @date 创建日期 Mon Feb 27 12:42:32 CST 2014
 */
@Controller
public class GoodsbrandAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Goodsbrand goodsbrand;
	private Autofck autofck;

	/*******************业务层接口****************/
	@Autowired
	private IGoodsbrandService goodsbrandService;

	/*********************集合******************/
	public List goodsbrandList;//商品品牌表信息集合
	public List cat_attr_list;//分类信息集合

	/*********************字段******************/
	public String cat_attr_str;//商品品牌商品关系信息串
	public String title_s;//品牌标题搜索
	public String goodsbrand_sortno_id;//品牌排序
	public String isort_no;//排序

	
	/**
	 * 方法描述：返回新增商品品牌表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		if(cat_attr_str!=null&&!"".equals(cat_attr_str))
		{
			cat_attr_list=this.goodsbrandService.cat_attr_list(cat_attr_str);
		}
		return goUrl(ADD);
	}
	/**
	 * 方法描述：批量排序
	 * @return
	 * @throws Exception
	 */
	public String updateSort() throws Exception { 
		boolean flag = this.goodsbrandService.updateSort("brand_id", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("商品品牌排序成功");
		}else{
			this.addActionMessage("商品品牌排序失败");
		}
		return list();
	}
	/**
	 * 方法描述：新增商品品牌表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		//字段验证
		if(commonCheck("0"))return add();
		goodsbrand.setInfo_state("1");
		this.goodsbrandService.insertGetPk(goodsbrand);
		this.addActionMessage("新增品牌成功！");
		this.goodsbrand = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改商品品牌表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id=goodsbrand.getBrand_id();
		//判断实体ID是否存在,若不存在该实体，返回到列表页，不进行任何操作
		if(ValidateUtil.isRequired(id)){
			return list();
		}
		//字段验证
		if(commonCheck("1"))return view();
		this.goodsbrandService.update(goodsbrand);
		this.addActionMessage("修改品牌成功！");
		return list();
	}
	/**
	 * @author：XBY
	 * @date：Feb 11, 2014 1:41:48 PM
	 * @Method Description：公共数据验证
	 * @param type：0新增 1为修改
	 */
	public boolean commonCheck(String type){
		super.commonValidateField(goodsbrand);
		if(super.ifvalidatepass){
			return true;
		}
		if(cat_attr_str!=null &&!"".equals(cat_attr_str)){
			goodsbrand.setGoods_attr(cat_attr_str);
		}
		else {
			goodsbrand.setGoods_attr("");
		}
		if("0".equals(type)){
			//新增
			if(!checkBrandName("",goodsbrand.getBrand_name())){
				this.addFieldError("goodsbrand.brand_name", "该品牌已经存在,请勿重复添加");
				return true;
			}
		}else {
			//修改
			if(!checkBrandName(goodsbrand.getBrand_id(),goodsbrand.getBrand_name())){
				this.addFieldError("goodsbrand.brand_name", "该品牌已经存在,请勿重复添加");
				return true;
			}
		}
		return false;
	}
	
	public boolean checkBrandName(String b_id,String b_name){
		boolean  fage=true;
		if(!ValidateUtil.isRequired(b_name)){
			Map map=new HashMap();
			map.put("brand_name_all",b_name);
	        List blist=this.goodsbrandService.getList(map);
	        if(blist!=null&&blist.size()>0){
	           //存在品牌名称
	           if(!ValidateUtil.isRequired(b_id)){
	        	   //继续验证 是更新的情况，因为更新的本身就存在品牌名称 需要过滤，通过品牌id来判别
	        	   for(int i=0;i<blist.size();i++){
	        		   Map mapbrand=new HashMap();
	        		   mapbrand=(HashMap)blist.get(i);
	        		   if(mapbrand!=null&&mapbrand.get("brand_id")!=null&&!b_id.equals(mapbrand.get("brand_id").toString())){
	        			   //判断获取到列表中 是否有不等于自己的品牌ID的数据
	        			   fage=false;
	        			   break;
	        		   }
	        	   }
	           }else {
	        	   //新增的模式，存在直接判断为重复
	        	   fage=false;
			   }
	        }
		}
		return fage;
	}
	/**
	 * @author Administrator 异步判断品牌名称是否重复
	 * @throws Exception
	 */
	public void ajaxBrandNameCopy() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String b_id = request.getParameter("b_id");//品牌ID
		String b_name = request.getParameter("b_name");//品牌名称
		String b_type = request.getParameter("b_type");//类型 0新增 1修改
		PrintWriter out = response.getWriter();
		String rString="0";//表示不重复,1表示重复
		if("0".equals(b_type)){
			//新增
			if(!checkBrandName("",b_name)){
				rString="1";
			}
		}else {
			//修改
			if(!checkBrandName(b_id,b_name)){
				rString="1";
			}
		}
		out.write(rString);
	}
	
	
	/**
	 * 方法描述：删除商品品牌表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.goodsbrandService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除品牌成功");
		}else{
			this.addActionMessage("删除品牌失败");
		}

		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		if(title_s!=null&&!"".equals(title_s))
		{
			title_s = title_s.trim();
			pageMap.put("brand_name", title_s);
		}
		if (!ValidateUtil.isRequired(cat_attr_s)) {
			pageMap.put("goods_attr", cat_attr_s);
			//回选分类
			viewCat(cat_attr_s);
		}
		//根据页面提交的条件找出信息总数
		int count=this.goodsbrandService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		goodsbrandList = this.goodsbrandService.getList(pageMap);
		goodsbrandList = ToolsFuc.replaceList(goodsbrandList, "");
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出商品品牌表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.goodsbrand.getBrand_id();
		if(id==null || id.equals("")){
			goodsbrand = new Goodsbrand();
		}else{
			goodsbrand = this.goodsbrandService.get(id);
			loadFckData(id,"goodsbrand");
		}
		cat_attr_str=goodsbrand.getGoods_attr();
		cat_attr_list=this.goodsbrandService.cat_attr_list(cat_attr_str);
		return goUrl(VIEW);
	}
	/**
	 * @Method Description : 导出统计信息
	 * @param 
	 * @return return_type
	 */
	public String exportbrandInfo() throws Exception{
		Map pageMap = new HashMap();
		goodsbrandList = this.goodsbrandService.getList(pageMap);
		goodsbrandList = ToolsFuc.replaceList(goodsbrandList, "");
		if(this.goodsbrandService.exportbrandExcel(goodsbrandList, getResponse())) {
			   this.addActionMessage("数据导出品牌成功！");	
			}else {
			   this.addActionMessage("数据导出品牌成功！");
			}
	   
	    return list();
	}
	/**
	 * @return the goodsbrand
	 */
	public Goodsbrand getGoodsbrand() {
		return goodsbrand;
	}
	/**
	 * @param Goodsbrand
	 *            the goodsbrand to set
	 */
	public void setGoodsbrand(Goodsbrand goodsbrand) {
		this.goodsbrand = goodsbrand;
	}
	
	/**
	 * @return the GoodsbrandList
	 */
	public List getGoodsbrandList() {
		return goodsbrandList;
	}
	/**
	 * @param goodsbrandList
	 *  the GoodsbrandList to set
	 */
	public void setGoodsbrandList(List goodsbrandList) {
		this.goodsbrandList = goodsbrandList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(goodsbrand == null){
			goodsbrand = new Goodsbrand();
		}
		String id = this.goodsbrand.getBrand_id();
		if(!this.validateFactory.isDigital(id)){
			goodsbrand = this.goodsbrandService.get(id);
		}
	}
	public String getCat_attr_str() {
		return cat_attr_str;
	}
	public void setCat_attr_str(String cat_attr_str) {
		this.cat_attr_str = cat_attr_str;
	}
	public List getCat_attr_list() {
		return cat_attr_list;
	}
	public void setCat_attr_list(List cat_attr_list) {
		this.cat_attr_list = cat_attr_list;
	}
	public String getTitle_s() {
		return title_s;
	}
	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}
	public String getGoodsbrand_sortno_id() {
		return goodsbrand_sortno_id;
	}
	public void setGoodsbrand_sortno_id(String goodsbrand_sortno_id) {
		this.goodsbrand_sortno_id = goodsbrand_sortno_id;
	}
	public String getIsort_no() {
		return isort_no;
	}
	public void setIsort_no(String isort_no) {
		this.isort_no = isort_no;
	}
	public Autofck getAutofck() {
		return autofck;
	}
	public void setAutofck(Autofck autofck) {
		this.autofck = autofck;
	}
	public IGoodsbrandService getGoodsbrandService() {
		return goodsbrandService;
	}
	public void setGoodsbrandService(IGoodsbrandService goodsbrandService) {
		this.goodsbrandService = goodsbrandService;
	}
}

