/*
 
 * Package:com.rbt.action
 * FileName: SearchfilterAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.model.Searchfilter;
import com.rbt.service.ISearchfilterService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 前台搜索过滤规则action类
 * @author 创建人 HXK
 * @date 创建日期 Fri Apr 03 11:11:49 CST 2015
 */
@Controller
public class SearchfilterAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 前台搜索过滤规则对象
	 */
	private Searchfilter searchfilter;
	/*******************业务层接口****************/
	/*
	 * 前台搜索过滤规则业务层接口
	 */
	@Autowired
	private ISearchfilterService searchfilterService;
	
	/*********************集合*******************/
	/*
	 * 前台搜索过滤规则信息集合
	 */
	public List searchfilterList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	
		
	/**
	 * 方法描述：返回新增前台搜索过滤规则页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增前台搜索过滤规则
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(searchfilter);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.searchfilterService.insert(searchfilter);
		this.addActionMessage("新增前台搜索过滤规则成功！");
		this.searchfilter = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改前台搜索过滤规则信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(searchfilter);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.searchfilterService.update(searchfilter);
		this.addActionMessage("修改前台搜索过滤规则成功！");
		return list();
	}
	/**
	 * 方法描述：删除前台搜索过滤规则信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除前台搜索过滤规则信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.searchfilterService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除前台搜索过滤规则信息成功!");
		}else{
			this.addActionMessage("删除前台搜索过滤规则信息失败!");
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
		int count=this.searchfilterService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		searchfilterList = this.searchfilterService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出前台搜索过滤规则信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.searchfilter.getSearch_filter_id();
		if(id==null || id.equals("")){
			searchfilter = new Searchfilter();
		}else{
			searchfilter = this.searchfilterService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the SearchfilterList
	 */
	public List getSearchfilterList() {
		return searchfilterList;
	}
	/**
	 * @param searchfilterList
	 *  the SearchfilterList to set
	 */
	public void setSearchfilterList(List searchfilterList) {
		this.searchfilterList = searchfilterList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(searchfilter == null){
			searchfilter = new Searchfilter();
		}
		String id = this.searchfilter.getSearch_filter_id();
		if(!this.validateFactory.isDigital(id)){
			searchfilter = this.searchfilterService.get(id);
		}
	}
	/**
	 * @return the searchfilter
	 */
	public Searchfilter getSearchfilter() {
		return searchfilter;
	}
	/**
	 * @param Searchfilter
	 *            the searchfilter to set
	 */
	public void setSearchfilter(Searchfilter searchfilter) {
		this.searchfilter = searchfilter;
	}
}

