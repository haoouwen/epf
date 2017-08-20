/*
 
 * Package:com.rbt.model
 * FileName: Navtab.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;

import com.hp.hpl.sparta.xpath.ThisNodeTest;
/**
 * @function 功能 导航标签信息实体
 * @author 创建人 ZMS
 * @date 创建日期 Wed Aug 12 20:56:05 CST 2015
 */
public class Navtab implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String tab_id;
	public String getTab_id() {
		return tab_id;
	}
	public void setTab_id(String tab_id) {
		this.tab_id = tab_id;
	}
	
	private String tab_name;
	public String getTab_name() {
		return tab_name;
	}
	public void setTab_name(String tab_name) {
		this.tab_name = tab_name;
	}
	
	private String tab_remark;
	public String getTab_remark() {
		return tab_remark;
	}
	public void setTab_remark(String tab_remark) {
		this.tab_remark = tab_remark;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String tab_url;
	public String getTab_url() {
		return tab_url;
	}
	public void setTab_url(String tab_url) {
		this.tab_url = tab_url;
	}
	
	private String start;
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	private String touch;
	public String getTouch(){
		return touch;
	}
	public void setTouch(String touch){
		this.touch=touch;
	}
	
	private String tab_number;
	
	
	
	public String getTab_number() {
		return tab_number;
	}
	public void setTab_number(String tab_number) {
		this.tab_number = tab_number;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Navtab[");
		builder.append(", tab_id=");
		builder.append(this.tab_id);
		builder.append(", tab_name=");
		builder.append(this.tab_name);
		builder.append(", tab_remark=");
		builder.append(this.tab_remark);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", tab_url=");
		builder.append(this.tab_url);
		builder.append(", start=");
		builder.append(this.start);
		builder.append(", touch=");
        builder.append(this.touch);
        builder.append(", tab_number=");
		builder.append(this.tab_number);
        builder.append("]");
		return builder.toString();
	}

}

