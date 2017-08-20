/*
 
 * Package:com.rbt.action
 * FileName: OrdertransAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Ordertrans;
import com.rbt.service.IOrdertransService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录商品订单异动信息action类
 * @author 创建人 HXK
 * @date 创建日期 Thu Feb 28 10:02:15 CST 2014
 */
@Controller
public class OrdertransAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Ordertrans ordertrans;
	
	/*******************业务层接口****************/

	@Autowired
	private IOrdertransService ordertransService;
	/*********************集合********************/
	public List ordertransList;//商品订单异动信息
	
	/*********************字段********************/
	
	/**
	 * 方法描述：返回新增记录商品订单异动信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录商品订单异动信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(ordertrans);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.ordertransService.insert(ordertrans);
		this.addActionMessage("新增商品订单异动信息成功！");
		this.ordertrans = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录商品订单异动信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(ordertrans);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.ordertransService.update(ordertrans);
		this.addActionMessage("修改商品订单异动信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录商品订单异动信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.ordertransService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除商品订单异动信息成功");
		}else{
			this.addActionMessage("删除商品订单异动信息失败");
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
		int count=this.ordertransService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		ordertransList = this.ordertransService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录商品订单异动信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.ordertrans.getTrans_id();
		if(id==null || id.equals("")){
			ordertrans = new Ordertrans();
		}else{
			ordertrans = this.ordertransService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the OrdertransList
	 */
	public List getOrdertransList() {
		return ordertransList;
	}
	/**
	 * @param ordertransList
	 *  the OrdertransList to set
	 */
	public void setOrdertransList(List ordertransList) {
		this.ordertransList = ordertransList;
	}
	/**
	 * @return the ordertrans
	 */
	public Ordertrans getOrdertrans() {
		return ordertrans;
	}
	/**
	 * @param Ordertrans
	 *            the ordertrans to set
	 */
	public void setOrdertrans(Ordertrans ordertrans) {
		this.ordertrans = ordertrans;
	}
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(ordertrans == null){
			ordertrans = new Ordertrans();
		}
		String id = this.ordertrans.getTrans_id();
		if(!this.validateFactory.isDigital(id)){
			ordertrans = this.ordertransService.get(id);
		}
	}

}

