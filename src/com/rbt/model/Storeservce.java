/*
 * Package:com.rbt.model
 * FileName: Storeservce.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 门店服务实体
 * @author 创建人 ZMS
 * @date 创建日期 Sat Aug 29 16:01:36 CST 2015
 */
public class Storeservce implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String store_id;
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	
	private String store_name;
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	
	private String store_img;
	public String getStore_img() {
		return store_img;
	}
	public void setStore_img(String store_img) {
		this.store_img = store_img;
	}
	
	private String state;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	private String store_color;
	public String getStore_color() {
		return store_color;
	}
	public void setStore_color(String store_color) {
		this.store_color = store_color;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Storeservce[");
		builder.append(", store_id=");
		builder.append(this.store_id);
		builder.append(", store_name=");
		builder.append(this.store_name);
		builder.append(", store_img=");
		builder.append(this.store_img);
		builder.append(", state=");
		builder.append(this.state);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", store_color=");
		builder.append(this.store_color);
		builder.append("]");
		return builder.toString();
	}

}

