/*
  
 
 * Package:com.rbt.model
 * FileName: Selfextendattr.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商品自定义属性实体
 * @author 创建人 LJQ
 * @date 创建日期 Sat Feb 16 10:59:08 CST 2014
 */
public class Selfextendattr implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String self_attr_id;
	public String getSelf_attr_id() {
		return self_attr_id;
	}
	public void setSelf_attr_id(String self_attr_id) {
		this.self_attr_id = self_attr_id;
	}
	
	private String ex_attr_id;
	public String getEx_attr_id() {
		return ex_attr_id;
	}
	public void setEx_attr_id(String ex_attr_id) {
		this.ex_attr_id = ex_attr_id;
	}
	
	private String ex_attr_alias;
	public String getEx_attr_alias() {
		return ex_attr_alias;
	}
	public void setEx_attr_alias(String ex_attr_alias) {
		this.ex_attr_alias = ex_attr_alias;
	}
	
	private String ex_attr_value;
	public String getEx_attr_value() {
		return ex_attr_value;
	}
	public void setEx_attr_value(String ex_attr_value) {
		this.ex_attr_value = ex_attr_value;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Selfextendattr[");
		builder.append(", self_attr_id=");
		builder.append(this.self_attr_id);
		builder.append(", ex_attr_id=");
		builder.append(this.ex_attr_id);
		builder.append(", ex_attr_alias=");
		builder.append(this.ex_attr_alias);
		builder.append(", ex_attr_value=");
		builder.append(this.ex_attr_value);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append("]");
		return builder.toString();
	}

}

