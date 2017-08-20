/*
 
 * Package:com.rbt.model
 * FileName: Taxrate.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 税率信息实体
 * @author 创建人 ZMS
 * @date 创建日期 Tue Aug 18 16:12:24 CST 2015
 */
public class Taxrate implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String tax_id;
	public String getTax_id() {
		return tax_id;
	}
	public void setTax_id(String tax_id) {
		this.tax_id = tax_id;
	}
	
	private String up_tax_id;
	public String getUp_tax_id() {
		return up_tax_id;
	}
	public void setUp_tax_id(String up_tax_id) {
		this.up_tax_id = up_tax_id;
	}
	
	private String tax_name;
	public String getTax_name() {
		return tax_name;
	}
	public void setTax_name(String tax_name) {
		this.tax_name = tax_name;
	}
	
	private String tax_number;
	public String getTax_number() {
		return tax_number;
	}
	public void setTax_number(String tax_number) {
		this.tax_number = tax_number;
	}
	
	private String tax_en_name;
	public String getTax_en_name() {
		return tax_en_name;
	}
	public void setTax_en_name(String tax_en_name) {
		this.tax_en_name = tax_en_name;
	}
	
	private String tax_level;
	public String getTax_level() {
		return tax_level;
	}
	public void setTax_level(String tax_level) {
		this.tax_level = tax_level;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String tax_unit;
	public String getTax_unit() {
		return tax_unit;
	}
	public void setTax_unit(String tax_unit) {
		this.tax_unit = tax_unit;
	}
	
	private String tax_price;
	public String getTax_price() {
		return tax_price;
	}
	public void setTax_price(String tax_price) {
		this.tax_price = tax_price;
	}
	
	private String tax_rate;
	public String getTax_rate() {
		return tax_rate;
	}
	public void setTax_rate(String tax_rate) {
		this.tax_rate = tax_rate;
	}
	
	private String tax_unit_remark;
	public String getTax_unit_remark() {
		return tax_unit_remark;
	}
	public void setTax_unit_remark(String tax_unit_remark) {
		this.tax_unit_remark = tax_unit_remark;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Taxrate[");
		builder.append(", tax_id=");
		builder.append(this.tax_id);
		builder.append(", up_tax_id=");
		builder.append(this.up_tax_id);
		builder.append(", tax_name=");
		builder.append(this.tax_name);
		builder.append(", tax_number=");
		builder.append(this.tax_number);
		builder.append(", tax_en_name=");
		builder.append(this.tax_en_name);
		builder.append(", tax_level=");
		builder.append(this.tax_level);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", tax_unit=");
		builder.append(this.tax_unit);
		builder.append(", tax_price=");
		builder.append(this.tax_price);
		builder.append(", tax_rate=");
		builder.append(this.tax_rate);
		builder.append(", tax_unit_remark=");
		builder.append(this.tax_unit_remark);
		builder.append("]");
		return builder.toString();
	}

}

