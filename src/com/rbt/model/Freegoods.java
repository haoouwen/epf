/*
 * Package:com.rbt.model
 * FileName: Freegoods.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 赠品实体
 * @author 创建人 ZMS
 * @date 创建日期 Tue Sep 29 17:12:09 CST 2015
 */
public class Freegoods implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String fg_id;
	public String getFg_id() {
		return fg_id;
	}
	public void setFg_id(String fg_id) {
		this.fg_id = fg_id;
	}
	
	private String fg_number;
	public String getFg_number() {
		return fg_number;
	}
	public void setFg_number(String fg_number) {
		this.fg_number = fg_number;
	}
	
	private String fg_price;
	public String getFg_price() {
		return fg_price;
	}
	public void setFg_price(String fg_price) {
		this.fg_price = fg_price;
	}
	
	private String fg_date;
	public String getFg_date() {
		return fg_date;
	}
	public void setFg_date(String fg_date) {
		this.fg_date = fg_date;
	}
	
	private String img_path;
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	private String fg_state;
	public String getFg_state() {
		return fg_state;
	}
	public void setFg_state(String fg_state) {
		this.fg_state = fg_state;
	}
	
	private String fg_sort;
	public String getFg_sort() {
		return fg_sort;
	}
	public void setFg_sort(String fg_sort) {
		this.fg_sort = fg_sort;
	}
	
	private String fg_content;
	public String getFg_content() {
		return fg_content;
	}
	public void setFg_content(String fg_content) {
		this.fg_content = fg_content;
	}
	
	private String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	private String fg_no;
	public String getFg_no() {
		return fg_no;
	}
	public void setFg_no(String fg_no) {
		this.fg_no = fg_no;
	}
	
	private String key_no;
	public String getKey_no() {
		return key_no;
	}
	public void setKey_no(String key_no) {
		this.key_no = key_no;
	}
	private String fg_name;
	public String getFg_name(){
		return fg_name;
	}
	public void setFg_name(String fg_name){
		this.fg_name=fg_name;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Freegoods[");
		builder.append(", fg_id=");
		builder.append(this.fg_id);
		builder.append(", fg_number=");
		builder.append(this.fg_number);
		builder.append(", fg_price=");
		builder.append(this.fg_price);
		builder.append(", fg_date=");
		builder.append(this.fg_date);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", fg_state=");
		builder.append(this.fg_state);
		builder.append(", fg_sort=");
		builder.append(this.fg_sort);
		builder.append(", fg_content=");
		builder.append(this.fg_content);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", fg_no=");
		builder.append(this.fg_no);
		builder.append(", key_no=");
		builder.append(this.key_no);
		builder.append(", fg_name=");
		builder.append(this.fg_name);
		builder.append("]");
		return builder.toString();
	}

}

