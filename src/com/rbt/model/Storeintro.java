/*
 * Package:com.rbt.model
 * FileName: Storeintro.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 门店服务介绍实体
 * @author 创建人 HXK
 * @date 创建日期 Wed Sep 23 13:59:28 CST 2015
 */
public class Storeintro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String sto_id;
	public String getSto_id() {
		return sto_id;
	}
	public void setSto_id(String sto_id) {
		this.sto_id = sto_id;
	}
	
	private String sto_name;
	public String getSto_name() {
		return sto_name;
	}
	public void setSto_name(String sto_name) {
		this.sto_name = sto_name;
	}
	
	private String img_path;
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	private String is_show;
	public String getIs_show() {
		return is_show;
	}
	public void setIs_show(String is_show) {
		this.is_show = is_show;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Storeintro[");
		builder.append(", sto_id=");
		builder.append(this.sto_id);
		builder.append(", sto_name=");
		builder.append(this.sto_name);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", is_show=");
		builder.append(this.is_show);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append("]");
		return builder.toString();
	}

}

