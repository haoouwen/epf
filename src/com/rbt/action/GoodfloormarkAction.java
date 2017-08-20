/*
 
 * Package:com.rbt.action
 * FileName: GoodfloormarkAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.model.Goodfloormark;
import com.rbt.service.IGoodfloormarkService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 商品楼层信息action类
 * @author 创建人 HXK
 * @date 创建日期 Mon Aug 10 11:00:14 CST 2015
 */
@Controller
public class GoodfloormarkAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 商品楼层信息对象
	 */
	private Goodfloormark goodfloormark;
	/*******************业务层接口****************/
	/*
	 * 商品楼层信息业务层接口
	 */
	@Autowired
	private IGoodfloormarkService goodfloormarkService;
	
	/*********************集合*******************/
	/*
	 * 商品楼层信息信息集合
	 */
	public List goodfloormarkList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	
		
	/**
	 * 方法描述：返回新增商品楼层信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增商品楼层信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(goodfloormark);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.goodfloormarkService.insert(goodfloormark);
		this.addActionMessage("新增商品楼层信息成功！");
		this.goodfloormark = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改商品楼层信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(goodfloormark);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.goodfloormarkService.update(goodfloormark);
		this.addActionMessage("修改商品楼层信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除商品楼层信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除商品楼层信息信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.goodfloormarkService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除商品楼层信息信息成功!");
		}else{
			this.addActionMessage("删除商品楼层信息信息失败!");
		}
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
		int count=this.goodfloormarkService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		goodfloormarkList = this.goodfloormarkService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出商品楼层信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.goodfloormark.getGm_id();
		if(id==null || id.equals("")){
			goodfloormark = new Goodfloormark();
		}else{
			goodfloormark = this.goodfloormarkService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the GoodfloormarkList
	 */
	public List getGoodfloormarkList() {
		return goodfloormarkList;
	}
	/**
	 * @param goodfloormarkList
	 *  the GoodfloormarkList to set
	 */
	public void setGoodfloormarkList(List goodfloormarkList) {
		this.goodfloormarkList = goodfloormarkList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(goodfloormark == null){
			goodfloormark = new Goodfloormark();
		}
		String id = this.goodfloormark.getGm_id();
		if(!this.validateFactory.isDigital(id)){
			goodfloormark = this.goodfloormarkService.get(id);
		}
	}
	/**
	 * @return the goodfloormark
	 */
	public Goodfloormark getGoodfloormark() {
		return goodfloormark;
	}
	/**
	 * @param Goodfloormark
	 *            the goodfloormark to set
	 */
	public void setGoodfloormark(Goodfloormark goodfloormark) {
		this.goodfloormark = goodfloormark;
	}
}

