/*
  
 
 * Package:com.rbt.model
 * FileName: Advpos.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:21 CST 2014
 */
public class Advpos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String pos_id;
	public String getPos_id() {
		return pos_id;
	}
	public void setPos_id(String pos_id) {
		this.pos_id = pos_id;
	}
	
	private String pos_name;
	public String getPos_name() {
		return pos_name;
	}
	public void setPos_name(String pos_name) {
		this.pos_name = pos_name;
	}
	
	private String pos_desc;
	public String getPos_desc() {
		return pos_desc;
	}
	public void setPos_desc(String pos_desc) {
		this.pos_desc = pos_desc;
	}
	
	private String pos_type;
	public String getPos_type() {
		return pos_type;
	}
	public void setPos_type(String pos_type) {
		this.pos_type = pos_type;
	}
	
	private String p_width;
	public String getP_width() {
		return p_width;
	}
	public void setP_width(String p_width) {
		this.p_width = p_width;
	}
	
	private String p_height;
	public String getP_height() {
		return p_height;
	}
	public void setP_height(String p_height) {
		this.p_height = p_height;
	}
	
	private String module_type;
	public String getModule_type() {
		return module_type;
	}
	public void setModule_type(String module_type) {
		this.module_type = module_type;
	}
	
	private String price;
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	private String default_code;
	public String getDefault_code() {
		return default_code;
	}
	public void setDefault_code(String default_code) {
		this.default_code = default_code;
	}
	
	private String isshow;
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String sys_adv;
	public String getSys_adv() {
		return sys_adv;
	}
	public void setSys_adv(String sys_adv) {
		this.sys_adv = sys_adv;
	}
	
	private String img_path;
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	private String adv_pos;
	public String getAdv_pos() {
		return adv_pos;
	}
	public void setAdv_pos(String adv_pos) {
		this.adv_pos = adv_pos;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Advpos[");
		builder.append(", pos_id=");
		builder.append(this.pos_id);
		builder.append(", pos_name=");
		builder.append(this.pos_name);
		builder.append(", pos_desc=");
		builder.append(this.pos_desc);
		builder.append(", pos_type=");
		builder.append(this.pos_type);
		builder.append(", p_width=");
		builder.append(this.p_width);
		builder.append(", p_height=");
		builder.append(this.p_height);
		builder.append(", module_type=");
		builder.append(this.module_type);
		builder.append(", price=");
		builder.append(this.price);
		builder.append(", default_code=");
		builder.append(this.default_code);
		builder.append(", isshow=");
		builder.append(this.isshow);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", sys_adv=");
		builder.append(this.sys_adv);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", adv_pos=");
		builder.append(this.adv_pos);
		builder.append("]");
		return builder.toString();
	}

}

