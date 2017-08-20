/*
  
 
 * Package:com.rbt.model
 * FileName: Attrvalue.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:21 CST 2014
 */
public class Attrvalue implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String attr_id;
	public String getAttr_id() {
		return attr_id;
	}
	public void setAttr_id(String attr_id) {
		this.attr_id = attr_id;
	}
	
	private String attr_value;
	public String getAttr_value() {
		return attr_value;
	}
	public void setAttr_value(String attr_value) {
		this.attr_value = attr_value;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Attrvalue[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", attr_id=");
		builder.append(this.attr_id);
		builder.append(", attr_value=");
		builder.append(this.attr_value);
		builder.append("]");
		return builder.toString();
	}

}

