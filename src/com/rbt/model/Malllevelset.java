/*
  
 
 * Package:com.rbt.model
 * FileName: Malllevelset.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Malllevelset implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String level_code;
	public String getLevel_code() {
		return level_code;
	}
	public void setLevel_code(String level_code) {
		this.level_code = level_code;
	}
	
	private String level_name;
	public String getLevel_name() {
		return level_name;
	}
	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}
	
	private String inter_lower;
	public String getInter_lower() {
		return inter_lower;
	}
	public void setInter_lower(String inter_lower) {
		this.inter_lower = inter_lower;
	}
	
	private String inter_height;
	public String getInter_height() {
		return inter_height;
	}
	public void setInter_height(String inter_height) {
		this.inter_height = inter_height;
	}
	
	private String mem_type;
	public String getMem_type() {
		return mem_type;
	}
	public void setMem_type(String mem_type) {
		this.mem_type = mem_type;
	}
	
	private String img_url;
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	
	private String discount;
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	private String menu_right;
	public String getMenu_right() {
		return menu_right;
	}
	public void setMenu_right(String menu_right) {
		this.menu_right = menu_right;
	}
	
	private String oper_right;
	public String getOper_right() {
		return oper_right;
	}
	public void setOper_right(String oper_right) {
		this.oper_right = oper_right;
	}
	
	private String note;
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	//级别有效期
	private String validity_period;
	public String getValidity_period() {
		return validity_period;
	}
	public void setValidity_period(String validity_period) {
		this.validity_period = validity_period;
	}
	
	//扣除成长值
	private String dedu_growth_value;
	public String getDedu_growth_value() {
		return dedu_growth_value;
	}
	public void setDedu_growth_value(String dedu_growth_value) {
		this.dedu_growth_value = dedu_growth_value;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Malllevelset[");
		builder.append(", level_code=");
		builder.append(this.level_code);
		builder.append(", level_name=");
		builder.append(this.level_name);
		builder.append(", inter_lower=");
		builder.append(this.inter_lower);
		builder.append(", inter_height=");
		builder.append(this.inter_height);
		builder.append(", mem_type=");
		builder.append(this.mem_type);
		builder.append(", img_url=");
		builder.append(this.img_url);
		builder.append(", discount=");
		builder.append(this.discount);
		builder.append(", menu_right=");
		builder.append(this.menu_right);
		builder.append(", oper_right=");
		builder.append(this.oper_right);
		builder.append(", note=");
		builder.append(this.note);
		builder.append(", validity_period=");
		builder.append(this.validity_period);
		builder.append(", dedu_growth_value=");
		builder.append(this.dedu_growth_value);
		builder.append("]");
		return builder.toString();
	}

}

