/*
  
 
 * Package:com.rbt.model
 * FileName: Areaset.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:21 CST 2014
 */
public class Areaset implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String areaset_id;
	public String getAreaset_id() {
		return areaset_id;
	}
	public void setAreaset_id(String areaset_id) {
		this.areaset_id = areaset_id;
	}
	
	private String end_area;
	public String getEnd_area() {
		return end_area;
	}
	public void setEnd_area(String end_area) {
		this.end_area = end_area;
	}
	
	private Double first_price;
	public Double getFirst_price() {
		return first_price;
	}
	public void setFirst_price(Double first_price) {
		this.first_price = first_price;
	}
	
	private Double cont_weight;
	public Double getCont_weight() {
		return cont_weight;
	}
	public void setCont_weight(Double cont_weight) {
		this.cont_weight = cont_weight;
	}
	
	private Double cont_price;
	public Double getCont_price() {
		return cont_price;
	}
	public void setCont_price(Double cont_price) {
		this.cont_price = cont_price;
	}
	
	private Double first_weight;
	public Double getFirst_weight() {
		return first_weight;
	}
	public void setFirst_weight(Double first_weight) {
		this.first_weight = first_weight;
	}
	
	private String smode_id;
	public String getSmode_id() {
		return smode_id;
	}
	public void setSmode_id(String smode_id) {
		this.smode_id = smode_id;
	}
	
	private String default_ship;
	public String getDefault_ship() {
		return default_ship;
	}
	public void setDefault_ship(String default_ship) {
		this.default_ship = default_ship;
	}
	
	private String ship_id;
	public String getShip_id() {
		return ship_id;
	}
	public void setShip_id(String ship_id) {
		this.ship_id = ship_id;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Areaset[");
		builder.append(", areaset_id=");
		builder.append(this.areaset_id);
		builder.append(", end_area=");
		builder.append(this.end_area);
		builder.append(", first_price=");
		builder.append(this.first_price);
		builder.append(", cont_weight=");
		builder.append(this.cont_weight);
		builder.append(", cont_price=");
		builder.append(this.cont_price);
		builder.append(", first_weight=");
		builder.append(this.first_weight);
		builder.append(", smode_id=");
		builder.append(this.smode_id);
		builder.append(", default_ship=");
		builder.append(this.default_ship);
		builder.append(", ship_id=");
		builder.append(this.ship_id);
		builder.append("]");
		return builder.toString();
	}

}

