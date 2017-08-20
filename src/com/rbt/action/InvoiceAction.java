/*
 
 * Package:com.rbt.action
 * FileName: InvoiceAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.model.Invoice;
import com.rbt.service.IInvoiceService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 发票打印action类
 * @author 创建人 HZX
 * @date 创建日期 Thu May 22 13:06:59 CST 2014
 */
@Controller
public class InvoiceAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 发票打印对象 
	 */
	private Invoice invoice;
	/*******************业务层接口****************/
	/*
	 * 发票打印业务层接口
	 */
	@Autowired
	private IInvoiceService invoiceService;
	
	/*********************集合*******************/
	/*
	 * 发票打印信息集合
	 */
	public List invoiceList;
	public List commparaList;
	public String [] printCommparaString;
	/*********************字段*******************/
	
	
		
	/**
	 * 方法描述：返回新增发票打印页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		commparaList=com.rbt.function.CommparaFuc.getEnabledList("invoice_param");
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增发票打印
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(invoice);
		if(super.ifvalidatepass){
			return add();
		}
		this.invoiceService.insert(invoice);
		this.addActionMessage("新增发票打印成功！");
		this.invoice = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改发票打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		super.commonValidateField(invoice);
		if(super.ifvalidatepass){
			return view();
		}
		invoice.replacePrintCode();
		this.invoiceService.update(invoice);
		this.addActionMessage("修改发票打印成功！");
		return list();
	}
	/**
	 * 方法描述：删除发票打印信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除发票打印信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.invoiceService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除发票打印信息成功!");
		}else{
			this.addActionMessage("删除发票打印信息失败!");
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
		int count=this.invoiceService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		invoiceList = this.invoiceService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出发票打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.invoice.getInvoice_id();
		if(id==null || id.equals("")){
			invoice = new Invoice();
		}else{
			invoice = this.invoiceService.get(id);
			commparaList=com.rbt.function.CommparaFuc.getEnabledList("invoice_param");
			if(invoice.getPrint_param()!=null){
				String print_param=invoice.getPrint_param();
				printCommparaString = print_param.split(",");
			}		
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the InvoiceList
	 */
	public List getInvoiceList() {
		return invoiceList;
	}
	/**
	 * @param invoiceList
	 *  the InvoiceList to set
	 */
	public void setInvoiceList(List invoiceList) {
		this.invoiceList = invoiceList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(invoice == null){
			invoice = new Invoice();
		}
		String id = this.invoice.getInvoice_id();
		if(!this.validateFactory.isDigital(id)){
			invoice = this.invoiceService.get(id);
		}
	}
	/**
	 * @return the invoice
	 */
	public Invoice getInvoice() {
		return invoice;
	}
	/**
	 * @param Invoice
	 *            the invoice to set
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
}

