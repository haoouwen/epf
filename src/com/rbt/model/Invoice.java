/*
  
 
 * Package:com.rbt.model
 * FileName: Invoice.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;

import com.rbt.common.util.ValidateUtil;
/**
 * @function 功能 发票打印实体
 * @author 创建人 HZX 
 * @date 创建日期 Thu May 22 13:06:59 CST 2014
 */
public class Invoice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String invoice_id;
	public String getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}
	
	private String invoice_name;
	public String getInvoice_name() {
		return invoice_name;
	}
	public void setInvoice_name(String invoice_name) {
		this.invoice_name = invoice_name;
	}
	
	private String print_param;
	public String getPrint_param() {
		return print_param;
	}
	public void setPrint_param(String print_param) {
		this.print_param = print_param;
	}
	
	private String show_content;
	public String getShow_content() {
		return show_content;
	}
	public void setShow_content(String show_content) {
		this.show_content = show_content;
	}
	
	private String print_content;
	public String getPrint_content() {
		return print_content;
	}
	public void setPrint_content(String print_content) {
		this.print_content = print_content;
	}
	
	/**
	 * @author:HXM
	 * @date:May 26, 20149:20:13 AM
	 * @param:
	 * @Description:将显示代码替换成打印代码
	 */
	public void replacePrintCode() {
		String str = this.show_content;
		if (!ValidateUtil.isRequired(this.show_content)) {
			str=str.replace("合计（小写）", "${invoice_pay}");
			str=str.replace("合计（大写）", "${invoice_biypay}");
			str=str.replace("开票日期", "${invoice_date}");
			str=str.replace("行业分类", "${invoice_category}");
			str=str.replace("客户名称", "${invoice_cust}");
			str=str.replace("开票人", "${invoice_man}");
			str=str.replace("项目/单价/数量/金额", "${invoice_content}");
			this.print_content=str;
		} else {
			this.print_content = "";
		}
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Invoice[");
		builder.append(", invoice_id=");
		builder.append(this.invoice_id);
		builder.append(", invoice_name=");
		builder.append(this.invoice_name);
		builder.append(", print_param=");
		builder.append(this.print_param);
		builder.append(", show_content=");
		builder.append(this.show_content);
		builder.append(", print_content=");
		builder.append(this.print_content);
		builder.append("]");
		return builder.toString();
	}

}

