/*
 
 * Package:com.rbt.action
 * FileName: IllegalsearchAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.model.Illegalsearch;
import com.rbt.service.IIllegalsearchService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 前台搜索拦截信息action类
 * @author 创建人 HXK
 * @date 创建日期 Fri Apr 03 12:03:00 CST 2015
 */
@Controller
public class IllegalsearchAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 前台搜索拦截信息对象
	 */
	private Illegalsearch illegalsearch;
	/*******************业务层接口****************/
	/*
	 * 前台搜索拦截信息业务层接口
	 */
	@Autowired
	private IIllegalsearchService illegalsearchService;
	
	/*********************集合*******************/
	/*
	 * 前台搜索拦截信息信息集合
	 */
	public List illegalsearchList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	
		
	/**
	 * 方法描述：返回新增前台搜索拦截信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增前台搜索拦截信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(illegalsearch);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.illegalsearchService.insert(illegalsearch);
		this.addActionMessage("新增前台搜索拦截信息成功！");
		this.illegalsearch = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改前台搜索拦截信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(illegalsearch);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.illegalsearchService.update(illegalsearch);
		this.addActionMessage("修改前台搜索拦截信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除前台搜索拦截信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除前台搜索拦截信息信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.illegalsearchService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除前台搜索拦截信息信息成功!");
		}else{
			this.addActionMessage("删除前台搜索拦截信息信息失败!");
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
		int count=this.illegalsearchService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		illegalsearchList = this.illegalsearchService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出前台搜索拦截信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.illegalsearch.getIllegal_search_id();
		if(id==null || id.equals("")){
			illegalsearch = new Illegalsearch();
		}else{
			illegalsearch = this.illegalsearchService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the IllegalsearchList
	 */
	public List getIllegalsearchList() {
		return illegalsearchList;
	}
	/**
	 * @param illegalsearchList
	 *  the IllegalsearchList to set
	 */
	public void setIllegalsearchList(List illegalsearchList) {
		this.illegalsearchList = illegalsearchList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(illegalsearch == null){
			illegalsearch = new Illegalsearch();
		}
		String id = this.illegalsearch.getIllegal_search_id();
		if(!this.validateFactory.isDigital(id)){
			illegalsearch = this.illegalsearchService.get(id);
		}
	}
	/**
	 * @return the illegalsearch
	 */
	public Illegalsearch getIllegalsearch() {
		return illegalsearch;
	}
	/**
	 * @param Illegalsearch
	 *            the illegalsearch to set
	 */
	public void setIllegalsearch(Illegalsearch illegalsearch) {
		this.illegalsearch = illegalsearch;
	}
}

