/*
 
 * Package:com.rbt.action
 * FileName: GroupladderAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Groupladder;
import com.rbt.service.IGroupladderService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 团购阶梯价格action类
 * @author 创建人 HZX
 * @date 创建日期 Mon Apr 15 17:26:40 CST 2014
 */
@Controller
public class GroupladderAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Groupladder groupladder;

	/*******************业务层接口****************/
	@Autowired
	private IGroupladderService groupladderService;

	/*********************集合******************/
	public List groupladderList;//团购阶梯价格信息集合
	
	/**
	 * 方法描述：返回新增团购阶梯价格页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增团购阶梯价格
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		 //字段验证
	    if(commonCheck())return add();
		this.groupladderService.insert(groupladder);
		this.addActionMessage("新增团购阶梯价格成功！");
		this.groupladder = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改团购阶梯价格信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
        //字段验证
	    if(commonCheck())return view();
		this.groupladderService.update(groupladder);
		this.addActionMessage("修改团购阶梯价格成功！");
		return list();
	}
	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 4:45:22 PM
	 * @Method Description：公共数据验证
	 */
	public boolean commonCheck(){
		super.commonValidateField(groupladder);
		if(super.ifvalidatepass){
			return true;
		}
		return false;
	}
	
	/**
	 * 方法描述：删除团购阶梯价格信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.groupladderService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除团购阶梯价格成功");
		}else{
			this.addActionMessage("删除团购阶梯价格失败");
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
		int count=this.groupladderService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		groupladderList = this.groupladderService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出团购阶梯价格信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.groupladder.getTrade_id();
		if(id==null || id.equals("")){
			groupladder = new Groupladder();
		}else{
			groupladder = this.groupladderService.get(id);
		}
		return goUrl(VIEW);
	}
	
	/**
	 * @return the groupladder
	 */
	public Groupladder getGroupladder() {
		return groupladder;
	}
	/**
	 * @param Groupladder
	 *            the groupladder to set
	 */
	public void setGroupladder(Groupladder groupladder) {
		this.groupladder = groupladder;
	}
	/**
	 * @return the GroupladderList
	 */
	public List getGroupladderList() {
		return groupladderList;
	}
	/**
	 * @param groupladderList
	 *  the GroupladderList to set
	 */
	public void setGroupladderList(List groupladderList) {
		this.groupladderList = groupladderList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(groupladder == null){
			groupladder = new Groupladder();
		}
		String id = this.groupladder.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			groupladder = this.groupladderService.get(id);
		}
	}

}

