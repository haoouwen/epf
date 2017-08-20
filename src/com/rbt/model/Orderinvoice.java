/*
 
 * Package:com.rbt.model
 * FileName: Orderinvoice.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 发票实体
 * @author 创建人 HZX
 * @date 创建日期 Thu Aug 13 13:00:29 CST 2015
 */
public class Orderinvoice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String invoice_id;
	public String getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}
	
	private String look_up;
	public String getLook_up() {
		return look_up;
	}
	public void setLook_up(String look_up) {
		this.look_up = look_up;
	}
	
	private String p_content;
	public String getP_content() {
		return p_content;
	}
	public void setP_content(String p_content) {
		this.p_content = p_content;
	}
	
	private String invoice_type;
	public String getInvoice_type() {
		return invoice_type;
	}
	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
	}
	
	private String company_name;
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	
	private String identifier;
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	private String re_address;
	public String getRe_address() {
		return re_address;
	}
	public void setRe_address(String re_address) {
		this.re_address = re_address;
	}
	
	private String re_phone;
	public String getRe_phone() {
		return re_phone;
	}
	public void setRe_phone(String re_phone) {
		this.re_phone = re_phone;
	}
	
	private String bank_name;
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	
	private String bank_id;
	public String getBank_id() {
		return bank_id;
	}
	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}
	
	private String z_content;
	public String getZ_content() {
		return z_content;
	}
	public void setZ_content(String z_content) {
		this.z_content = z_content;
	}
	
	private String ticket_name;
	public String getTicket_name() {
		return ticket_name;
	}
	public void setTicket_name(String ticket_name) {
		this.ticket_name = ticket_name;
	}
	
	private String ticket_cell;
	public String getTicket_cell() {
		return ticket_cell;
	}
	public void setTicket_cell(String ticket_cell) {
		this.ticket_cell = ticket_cell;
	}
	
	private String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	private String certificate;
	private String license;
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Orderinvoice[");
		builder.append(", invoice_id=");
		builder.append(this.invoice_id);
		builder.append(", look_up=");
		builder.append(this.look_up);
		builder.append(", p_content=");
		builder.append(this.p_content);
		builder.append(", invoice_type=");
		builder.append(this.invoice_type);
		builder.append(", company_name=");
		builder.append(this.company_name);
		builder.append(", identifier=");
		builder.append(this.identifier);
		builder.append(", re_address=");
		builder.append(this.re_address);
		builder.append(", re_phone=");
		builder.append(this.re_phone);
		builder.append(", bank_name=");
		builder.append(this.bank_name);
		builder.append(", bank_id=");
		builder.append(this.bank_id);
		builder.append(", z_content=");
		builder.append(this.z_content);
		builder.append(", ticket_name=");
		builder.append(this.ticket_name);
		builder.append(", ticket_cell=");
		builder.append(this.ticket_cell);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", address=");
		builder.append(this.address);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", certificate=");
		builder.append(this.certificate);
		builder.append(", license=");
		builder.append(this.license);
		
		builder.append("]");
		return builder.toString();
	}
	
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}

}

