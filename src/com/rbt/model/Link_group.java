/*
  
 
 * Package:com.rbt.model
 * FileName: Link_group.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Link_group implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Link_group[");
		builder.append(", id=");
		builder.append(this.id);
		builder.append(", name=");
		builder.append(this.name);
		builder.append("]");
		return builder.toString();
	}

}

