/*
 
 * Package:com.rbt.action
 * FileName: GoodsattrAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Goodsattr;
import com.rbt.service.IGoodsattrService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 商品属性信息action类
 * @author 创建人 LJQ
 * @date 创建日期 Tue Jan 29 14:31:37 CST 2014
 */
@Controller
public class GoodsattrAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Goodsattr goodsattr;

	/*******************业务层接口****************/
	@Autowired
	private IGoodsattrService goodsattrService;

	/*********************集合******************/
	public List goodsattrList;//商品属性信息信息集合

	/**
	 * 方法描述：返回新增商品属性信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增商品属性信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		//字段验证
	    if(commonCheck())return add();
		this.goodsattrService.insert(goodsattr);
		this.addActionMessage("新增商品属性信息成功！");
		this.goodsattr = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改商品属性信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
	    //字段验证
	    if(commonCheck())return view();
		this.goodsattrService.update(goodsattr);
		this.addActionMessage("修改商品属性信息成功！");
		return list();
	}
	/**
	 * @author：XBY
	 * @date：Feb 11, 2014 4:01:15 PM
	 * @Method Description：公共数据验证
	 */
	public boolean commonCheck(){
		super.commonValidateField(goodsattr);
		if(super.ifvalidatepass){
			return true;
		}
		return false;	
	}
	/**
	 * 方法描述：删除商品属性信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.goodsattrService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除商品属性信息成功");
		}else{
			this.addActionMessage("删除商品属性信息失败");
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
		
		//根据页面提交的条件找出信息总数
		int count=this.goodsattrService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		goodsattrList = this.goodsattrService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出商品属性信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.goodsattr.getGoods_item();
		if(id==null || id.equals("")){
			goodsattr = new Goodsattr();
		}else{
			goodsattr = this.goodsattrService.get(id);
		}
		return goUrl(VIEW);
	}
	
	/**
	 * @return the goodsattr
	 */
	public Goodsattr getGoodsattr() {
		return goodsattr;
	}
	/**
	 * @param Goodsattr
	 *            the goodsattr to set
	 */
	public void setGoodsattr(Goodsattr goodsattr) {
		this.goodsattr = goodsattr;
	}
	/**
	 * @return the GoodsattrList
	 */
	public List getGoodsattrList() {
		return goodsattrList;
	}
	/**
	 * @param goodsattrList
	 *  the GoodsattrList to set
	 */
	public void setGoodsattrList(List goodsattrList) {
		this.goodsattrList = goodsattrList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(goodsattr == null){
			goodsattr = new Goodsattr();
		}
		String id = this.goodsattr.getGoods_item();
		if(!this.validateFactory.isDigital(id)){
			goodsattr = this.goodsattrService.get(id);
		}
	}

}

