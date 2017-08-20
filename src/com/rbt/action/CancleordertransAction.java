/*
 
 * Package:com.rbt.action
 * FileName: CancleordertransAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.model.Cancleordertrans;
import com.rbt.service.ICancleordertransService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 取消申请进度action类
 * @author 创建人 XBY
 * @date 创建日期 Thu Sep 25 10:55:07 CST 2014
 */
@Controller
public class CancleordertransAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Cancleordertrans cancleordertrans;
	/*******************业务层接口****************/
	@Autowired
	private ICancleordertransService cancleordertransService;
	
	/*********************集合*******************/
	public List cancleordertransList;//取消申请进度信息
	/*********************字段*******************/
	
	
		
	/**
	 * 方法描述：返回新增取消申请进度页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增取消申请进度
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(cancleordertrans);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.cancleordertransService.insert(cancleordertrans);
		this.addActionMessage("新增取消申请进度成功！");
		this.cancleordertrans = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改取消申请进度信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		super.commonValidateField(cancleordertrans);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.cancleordertransService.update(cancleordertrans);
		this.addActionMessage("修改取消申请进度成功！");
		return list();
	}
	/**
	 * 方法描述：删除取消申请进度信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除取消申请进度信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.cancleordertransService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除取消申请进度信息成功!");
		}else{
			this.addActionMessage("删除取消申请进度信息失败!");
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
		int count=this.cancleordertransService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		cancleordertransList = this.cancleordertransService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出取消申请进度信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.cancleordertrans.getTrans_id();
		if(id==null || id.equals("")){
			cancleordertrans = new Cancleordertrans();
		}else{
			cancleordertrans = this.cancleordertransService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the CancleordertransList
	 */
	public List getCancleordertransList() {
		return cancleordertransList;
	}
	/**
	 * @param cancleordertransList
	 *  the CancleordertransList to set
	 */
	public void setCancleordertransList(List cancleordertransList) {
		this.cancleordertransList = cancleordertransList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(cancleordertrans == null){
			cancleordertrans = new Cancleordertrans();
		}
		String id = this.cancleordertrans.getTrans_id();
		if(!this.validateFactory.isDigital(id)){
			cancleordertrans = this.cancleordertransService.get(id);
		}
	}
	/**
	 * @return the cancleordertrans
	 */
	public Cancleordertrans getCancleordertrans() {
		return cancleordertrans;
	}
	/**
	 * @param Cancleordertrans
	 *            the cancleordertrans to set
	 */
	public void setCancleordertrans(Cancleordertrans cancleordertrans) {
		this.cancleordertrans = cancleordertrans;
	}
}

