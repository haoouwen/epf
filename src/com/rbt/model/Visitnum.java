/*
  
 
 * Package:com.rbt.model
 * FileName: Visitnum.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Visitnum implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	private String day_pv;
	public String getDay_pv() {
		return day_pv;
	}
	public void setDay_pv(String day_pv) {
		this.day_pv = day_pv;
	}
	
	private String day_time;
	public String getDay_time() {
		return day_time;
	}
	public void setDay_time(String day_time) {
		this.day_time = day_time;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Visitnum[");
		builder.append(", id=");
		builder.append(this.id);
		builder.append(", day_pv=");
		builder.append(this.day_pv);
		builder.append(", day_time=");
		builder.append(this.day_time);
		builder.append("]");
		return builder.toString();
	}

}

