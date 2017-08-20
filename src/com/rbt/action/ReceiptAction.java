/*
 
 * Package:com.rbt.action
 * FileName: ReceiptAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Receipt;
import com.rbt.service.IReceiptService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录单据模板信息action类
 * @author 创建人 HZX
 * @date 创建日期 Wed Jan 30 11:12:44 CST 2014
 */
@Controller
public class ReceiptAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Receipt receipt;
	
	/*******************业务层接口****************/
	@Autowired
	private IReceiptService receiptService;
	
	/*********************集合********************/
	
	public List receiptList;//单据模板信息
	/*********************字段********************/
	
	/**
	 * 方法描述：返回新增记录单据模板信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录单据模板信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(receipt);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.receiptService.insert(receipt);
		this.addActionMessage("新增记录单据模板信息成功！");
		this.receipt = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录单据模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(receipt);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.receiptService.update(receipt);
		this.addActionMessage("修改记录单据模板信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录单据模板信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.receiptService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录单据模板信息成功");
		}else{
			this.addActionMessage("删除记录单据模板信息失败");
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
		int count=this.receiptService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		receiptList = this.receiptService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录单据模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.receipt.getReceipt_code();
		if(id==null || id.equals("")){
			receipt = new Receipt();
		}else{
			receipt = this.receiptService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the ReceiptList
	 */
	public List getReceiptList() {
		return receiptList;
	}
	/**
	 * @param receiptList
	 *  the ReceiptList to set
	 */
	public void setReceiptList(List receiptList) {
		this.receiptList = receiptList;
	}
	/**
	 * @return the receipt
	 */
	public Receipt getReceipt() {
		return receipt;
	}
	/**
	 * @param Receipt
	 *            the receipt to set
	 */
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(receipt == null){
			receipt = new Receipt();
		}
		String id = this.receipt.getReceipt_code();
		if(!this.validateFactory.isDigital(id)){
			receipt = this.receiptService.get(id);
		}
	}

}

