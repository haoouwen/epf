/*
 
 * Package:com.rbt.action
 * FileName: CancelorderAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.model.Cancelorder;
import com.rbt.service.ICancelorderService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 取消订单理由action类
 * @author 创建人 XBY
 * @date 创建日期 Sat Jan 10 13:47:37 CST 2015
 */
@Controller
public class CancelorderAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 取消订单理由对象
	 */
	private Cancelorder cancelorder;
	/*******************业务层接口****************/
	/*
	 * 取消订单理由业务层接口
	 */
	@Autowired
	private ICancelorderService cancelorderService;
	
	/*********************集合*******************/
	/*
	 * 取消订单理由信息集合
	 */
	public List cancelorderList;
	/*********************字段*******************/
	
	
		
	/**
	 * 方法描述：返回新增取消订单理由页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增取消订单理由
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(cancelorder);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.cancelorderService.insert(cancelorder);
		this.addActionMessage("新增取消订单理由成功！");
		this.cancelorder = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改取消订单理由信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(cancelorder);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.cancelorderService.update(cancelorder);
		this.addActionMessage("修改取消订单理由成功！");
		return list();
	}
	/**
	 * 方法描述：删除取消订单理由信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除取消订单理由信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.cancelorderService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除取消订单理由信息成功!");
		}else{
			this.addActionMessage("删除取消订单理由信息失败!");
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
		int count=this.cancelorderService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		cancelorderList = this.cancelorderService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出取消订单理由信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.cancelorder.getTrade_id();
		if(id==null || id.equals("")){
			cancelorder = new Cancelorder();
		}else{
			cancelorder = this.cancelorderService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the CancelorderList
	 */
	public List getCancelorderList() {
		return cancelorderList;
	}
	/**
	 * @param cancelorderList
	 *  the CancelorderList to set
	 */
	public void setCancelorderList(List cancelorderList) {
		this.cancelorderList = cancelorderList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(cancelorder == null){
			cancelorder = new Cancelorder();
		}
		String id = this.cancelorder.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			cancelorder = this.cancelorderService.get(id);
		}
	}
	/**
	 * @return the cancelorder
	 */
	public Cancelorder getCancelorder() {
		return cancelorder;
	}
	/**
	 * @param Cancelorder
	 *            the cancelorder to set
	 */
	public void setCancelorder(Cancelorder cancelorder) {
		this.cancelorder = cancelorder;
	}
}

