/*
 
 * Package:com.rbt.model
 * FileName: Navigation.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;

import com.hp.hpl.sparta.xpath.ThisNodeTest;
/**
 * @function 功能 导航列表信息实体
 * @author 创建人 ZMS
 * @date 创建日期 Thu Aug 13 11:37:53 CST 2015
 */
public class Navigation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String n_id;
	public String getN_id() {
		return n_id;
	}
	public void setN_id(String n_id) {
		this.n_id = n_id;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	private String tab_id;
	public String getTab_id() {
		return tab_id;
	}
	public void setTab_id(String tab_id) {
		this.tab_id = tab_id;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
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
		builder.append("Navigation[");
		builder.append(", n_id=");
		builder.append(this.n_id);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", tab_id=");
		builder.append(this.tab_id);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", tab_number=");
		builder.append(this.tab_number);
		builder.append("]");
		return builder.toString();
	}

}

