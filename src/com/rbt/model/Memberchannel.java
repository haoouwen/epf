/*
  
 
 * Package:com.rbt.model
 * FileName: Memberchannel.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Memberchannel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String ch_id;
	public String getCh_id() {
		return ch_id;
	}
	public void setCh_id(String ch_id) {
		this.ch_id = ch_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String ch_name;
	public String getCh_name() {
		return ch_name;
	}
	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}
	
	private String ch_code;
	public String getCh_code() {
		return ch_code;
	}
	public void setCh_code(String ch_code) {
		this.ch_code = ch_code;
	}
	
	private String ch_type;
	public String getCh_type() {
		return ch_type;
	}
	public void setCh_type(String ch_type) {
		this.ch_type = ch_type;
	}
	
	private String is_dis;
	public String getIs_dis() {
		return is_dis;
	}
	public void setIs_dis(String is_dis) {
		this.is_dis = is_dis;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String ch_num;
	public String getCh_num() {
		return ch_num;
	}
	public void setCh_num(String ch_num) {
		this.ch_num = ch_num;
	}
	
	private String sys_ch;
	public String getSys_ch() {
		return sys_ch;
	}
	public void setSys_ch(String sys_ch) {
		this.sys_ch = sys_ch;
	}
	
	private String meta_keyword;
	public String getMeta_keyword() {
		return meta_keyword;
	}
	public void setMeta_keyword(String meta_keyword) {
		this.meta_keyword = meta_keyword;
	}
	
	private String meta_desc;
	public String getMeta_desc() {
		return meta_desc;
	}
	public void setMeta_desc(String meta_desc) {
		this.meta_desc = meta_desc;
	}
	
	private String ch_content;
	public String getCh_content() {
		return ch_content;
	}
	public void setCh_content(String ch_content) {
		this.ch_content = ch_content;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Memberchannel[");
		builder.append(", ch_id=");
		builder.append(this.ch_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", ch_name=");
		builder.append(this.ch_name);
		builder.append(", ch_code=");
		builder.append(this.ch_code);
		builder.append(", ch_type=");
		builder.append(this.ch_type);
		builder.append(", is_dis=");
		builder.append(this.is_dis);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", ch_num=");
		builder.append(this.ch_num);
		builder.append(", sys_ch=");
		builder.append(this.sys_ch);
		builder.append(", meta_keyword=");
		builder.append(this.meta_keyword);
		builder.append(", meta_desc=");
		builder.append(this.meta_desc);
		builder.append(", ch_content=");
		builder.append(this.ch_content);
		builder.append("]");
		return builder.toString();
	}

}

