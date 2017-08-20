/*
 
 * Package:com.rbt.action
 * FileName: GoodsspreadAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.AdminBaseAction;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Goods;
import com.rbt.model.Goodsspread;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsspreadService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录商品推广信息action类
 * @author 创建人 HZX
 * @date 创建日期 Wed Mar 20 13:21:09 CST 2014
 */
@Controller
public class GoodsspreadAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Goodsspread goodsspread;
	private Goods goods;

	/*******************业务层接口****************/
	@Autowired
	private IGoodsspreadService goodsspreadService;
	@Autowired
	private IGoodsService goodsService;

	/*********************集合******************/
	public List goodsspreadList;// 记录商品推广信息信息集合
	public List commparaList;//   商品推广参数列表

	/*********************字段******************/
	public String cat_attr_s;//所属分类搜索
	public String area_attr_s;//所属地区搜索
	public String hidden_goodsname;//商品名称
	public String spread_position_s;//推广位置搜索
	
	/**
	 * 方法描述：返回新增记录商品推广信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//获取商品推广参数的键值
		Map gsMap=new HashMap();
		gsMap.put("para_code", "spread_position");
		gsMap.put("enabled", "0");
		commparaList=this.commparaService.getList(gsMap);
		return goUrl(ADD);
	}

	/**
	 * @author : HZX
	 * @date : Jul 17, 2014 9:15:59 AM
	 * @Method Description :新增更新校验
	 */
	public void commonCheck() throws Exception {
		
		if(goodsspread.getGoods_id()==null || goodsspread.getGoods_id().equals("")){
			this.addFieldError("goodsspread.goods_id","请添加商品！");
		}
		if(goodsspread.getDiscounts()==null||goodsspread.getDiscounts()>10){
			this.addFieldError("goodsspread.discounts","请输入10以内的正数！");
		}
		if(cat_attr==null||cat_attr.equals("")){
			this.addFieldError("goodsspread.cat_attr","请选择分类！");
		}
		super.commonValidateField(goodsspread);
	}
	
	/**
	 * 方法描述：新增记录商品推广信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		//保存地区
		selectArea();
		// 保存分类cat_attr
		//selectCat();
		cat_attr = cat_attr.replace(" ", "");
		commonCheck();
		if(super.ifvalidatepass){
			return add();
		}
		this.goodsspread.setArea_attr(area_attr);
		this.goodsspread.setCat_attr(cat_attr);
		this.goodsspreadService.insert(goodsspread);
		this.addActionMessage("新增记录商品推广信息成功！");
		this.goodsspread = null;
		this.hidden_goodsname=null;
		return add();
	}

	/**
	 * 方法描述：修改记录商品推广信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 保存分类cat_attr
		//selectCat();
		cat_attr = cat_attr.replace(" ", "");
		commonCheck();
		if(super.ifvalidatepass){
			return view();
		}
		//保存地区
		//selectArea();
		area_attr = area_attr.replace(" ", "");
		this.goodsspread.setArea_attr(area_attr);
		this.goodsspread.setCat_attr(cat_attr);
		this.goodsspreadService.update(goodsspread);
		this.addActionMessage("修改记录商品推广信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录商品推广信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.goodsspreadService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录商品推广信息成功");
		}else{
			this.addActionMessage("删除记录商品推广信息失败");
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
		// 获取搜索的所在地区
		if (area_attr_s != null && !area_attr_s.equals("")) {
			pageMap.put("area_attr", area_attr_s);
		}
		// 获取搜索的分类
		if (cat_attr_s != null && !cat_attr_s.equals("")) {
			pageMap.put("cat_attr", cat_attr_s);
		}
		if (spread_position_s != null && !spread_position_s.equals("")) {
			pageMap.put("spread_position", spread_position_s);
		}
		//根据页面提交的条件找出信息总数
		int count=this.goodsspreadService.getCount(pageMap);
		//获取商品推广参数的键值
		Map gsMap=new HashMap();
		gsMap.put("para_code", "spread_position");
		gsMap.put("enabled", "0");
		commparaList=this.commparaService.getList(gsMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		goodsspreadList = this.goodsspreadService.getList(pageMap);
		goodsspreadList = ToolsFuc.replaceList(goodsspreadList, "");
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录商品推广信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.goodsspread.getTrade_id();
		if(id==null || id.equals("")){
			goodsspread = new Goodsspread();
		}else{
			goodsspread = this.goodsspreadService.get(id);
			if(goodsspread.getGoods_id()!=null && !goodsspread.getGoods_id().equals("")){
				goods=this.goodsService.getByPkNoDel(goodsspread.getGoods_id());
				if(goods!=null && goods.getGoods_name()!=null){
					hidden_goodsname = goods.getGoods_name();
				}
			}
		}
		//获取地区分类
		viewArea(goodsspread.getArea_attr());
		//获取所属分类
		viewCat(goodsspread.getCat_attr());
		//获取商品推广参数的键值
		Map gsMap=new HashMap();
		gsMap.put("para_code", "spread_position");
		gsMap.put("enabled", "0");
		commparaList=this.commparaService.getList(gsMap);
		return goUrl(VIEW);
	}
	
	/**
	 * @return the goodsspread
	 */
	public Goodsspread getGoodsspread() {
		return goodsspread;
	}
	/**
	 * @param Goodsspread
	 *            the goodsspread to set
	 */
	public void setGoodsspread(Goodsspread goodsspread) {
		this.goodsspread = goodsspread;
	}
	/**
	 * @return the GoodsspreadList
	 */
	public List getGoodsspreadList() {
		return goodsspreadList;
	}
	/**
	 * @param goodsspreadList
	 *  the GoodsspreadList to set
	 */
	public void setGoodsspreadList(List goodsspreadList) {
		this.goodsspreadList = goodsspreadList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(goodsspread == null){
			goodsspread = new Goodsspread();
		}
		String id = this.goodsspread.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			goodsspread = this.goodsspreadService.get(id);
		}
	}
	public String getCat_attr_s() {
		return cat_attr_s;
	}
	public void setCat_attr_s(String cat_attr_s) {
		this.cat_attr_s = cat_attr_s;
	}
	public String getArea_attr_s() {
		return area_attr_s;
	}
	public void setArea_attr_s(String area_attr_s) {
		this.area_attr_s = area_attr_s;
	}
	public String getSpread_position_s() {
		return spread_position_s;
	}
	public void setSpread_position_s(String spread_position_s) {
		this.spread_position_s = spread_position_s;
	}

}

