/*
 
 * Package:com.rbt.action
 * FileName: OrderinvoiceAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.model.Orderinvoice;
import com.rbt.service.IOrderinvoiceService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 发票action类
 * @author 创建人 HZX
 * @date 创建日期 Thu Aug 13 13:00:29 CST 2015
 */
@Controller
public class OrderinvoiceAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 发票对象
	 */
	private Orderinvoice orderinvoice;
	/*******************业务层接口****************/
	/*
	 * 发票业务层接口
	 */
	@Autowired
	private IOrderinvoiceService orderinvoiceService;
	
	/*********************集合*******************/
	/*
	 * 发票信息集合
	 */
	public List orderinvoiceList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	
		
	/**
	 * 方法描述：返回新增发票页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增发票
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(orderinvoice);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.orderinvoiceService.insert(orderinvoice);
		this.addActionMessage("新增发票成功！");
		this.orderinvoice = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改发票信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(orderinvoice);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.orderinvoiceService.update(orderinvoice);
		this.addActionMessage("修改发票成功！");
		return list();
	}
	/**
	 * 方法描述：删除发票信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除发票信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.orderinvoiceService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除发票信息成功!");
		}else{
			this.addActionMessage("删除发票信息失败!");
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
		int count=this.orderinvoiceService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		orderinvoiceList = this.orderinvoiceService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出发票信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.orderinvoice.getInvoice_id();
		if(id==null || id.equals("")){
			orderinvoice = new Orderinvoice();
		}else{
			orderinvoice = this.orderinvoiceService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the OrderinvoiceList
	 */
	public List getOrderinvoiceList() {
		return orderinvoiceList;
	}
	/**
	 * @param orderinvoiceList
	 *  the OrderinvoiceList to set
	 */
	public void setOrderinvoiceList(List orderinvoiceList) {
		this.orderinvoiceList = orderinvoiceList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(orderinvoice == null){
			orderinvoice = new Orderinvoice();
		}
		String id = this.orderinvoice.getInvoice_id();
		if(!this.validateFactory.isDigital(id)){
			orderinvoice = this.orderinvoiceService.get(id);
		}
	}
	/**
	 * @return the orderinvoice
	 */
	public Orderinvoice getOrderinvoice() {
		return orderinvoice;
	}
	/**
	 * @param Orderinvoice
	 *            the orderinvoice to set
	 */
	public void setOrderinvoice(Orderinvoice orderinvoice) {
		this.orderinvoice = orderinvoice;
	}
}

