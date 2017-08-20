/*
 
 * Package:com.rbt.action
 * FileName: AudithistoryAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Audithistory;
import com.rbt.service.IAudithistoryService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录模块审核历史action类
 * @author 创建人 LJQ
 * @date 创建日期 Tue Nov 15 10:35:16 CST 2014
 */
@Controller
public class AudithistoryAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	public Audithistory audithistory;

	/*******************业务层接口****************/
	@Autowired
	private IAudithistoryService audithistoryService;

	/*********************集合******************/
	public List audithistoryList;//记录模块审核历史
	
	/**
	 * 方法描述：返回新增记录模块审核历史页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录模块审核历史
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.audithistoryService.insert(audithistory);
		this.addActionMessage("新增审核历史成功");
		this.audithistory = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录模块审核历史信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.audithistoryService.update(audithistory);
		this.addActionMessage("修改审核历史成功");
		return list();
	}
	/**
	 * 方法描述：删除记录模块审核历史信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.audithistoryService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除审核历史成功");
		}else{
			this.addActionMessage("删除审核历史失败");
		}
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.audithistoryService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		audithistoryList = this.audithistoryService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录模块审核历史信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.audithistory.getInfo_id();
		if(ValidateUtil.isRequired(id)){
			audithistory = new Audithistory();
		}else{
			audithistory = this.audithistoryService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the AudithistoryList
	 */
	public List getAudithistoryList() {
		return audithistoryList;
	}
	/**
	 * @param audithistoryList
	 *  the AudithistoryList to set
	 */
	public void setAudithistoryList(List audithistoryList) {
		this.audithistoryList = audithistoryList;
	}
	
	/**
	 * @return the audithistory
	 */
	public Audithistory getAudithistory() {
		return audithistory;
	}
	/**
	 * @param Audithistory
	 *            the audithistory to set
	 */
	public void setAudithistory(Audithistory audithistory) {
		this.audithistory = audithistory;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(audithistory == null){
			audithistory = new Audithistory();
		}
		String id = this.audithistory.getInfo_id();
		if(!ValidateUtil.isDigital(id)){
			audithistory = this.audithistoryService.get(id);
		}
	}

}

