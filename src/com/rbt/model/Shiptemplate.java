/*
  
 
 * Package:com.rbt.model
 * FileName: Shiptemplate.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Shiptemplate implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String ship_id;
	public String getShip_id() {
		return ship_id;
	}
	public void setShip_id(String ship_id) {
		this.ship_id = ship_id;
	}
	
	private String ship_name;
	public String getShip_name() {
		return ship_name;
	}
	public void setShip_name(String ship_name) {
		this.ship_name = ship_name;
	}
	
	private String start_area;
	public String getStart_area() {
		return start_area;
	}
	public void setStart_area(String start_area) {
		this.start_area = start_area;
	}
	
	private String valuation_mode;
	public String getValuation_mode() {
		return valuation_mode;
	}
	public void setValuation_mode(String valuation_mode) {
		this.valuation_mode = valuation_mode;
	}
	
	private String smode_attr;
	public String getSmode_attr() {
		return smode_attr;
	}
	public void setSmode_attr(String smode_attr) {
		this.smode_attr = smode_attr;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Shiptemplate[");
		builder.append(", ship_id=");
		builder.append(this.ship_id);
		builder.append(", ship_name=");
		builder.append(this.ship_name);
		builder.append(", start_area=");
		builder.append(this.start_area);
		builder.append(", valuation_mode=");
		builder.append(this.valuation_mode);
		builder.append(", smode_attr=");
		builder.append(this.smode_attr);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append("]");
		return builder.toString();
	}

}

