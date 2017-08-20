/*
  
 
 * Package:com.rbt.model
 * FileName: Member.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String cust_name;
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	
	private String logo_path;
	public String getLogo_path() {
		return logo_path;
	}
	public void setLogo_path(String logo_path) {
		this.logo_path = logo_path;
	}
	
	private String mem_type;
	public String getMem_type() {
		return mem_type;
	}
	public void setMem_type(String mem_type) {
		this.mem_type = mem_type;
	}
	
	private String sell_level;
	public String getSell_level() {
		return sell_level;
	}
	public void setSell_level(String sell_level) {
		this.sell_level = sell_level;
	}
	
	private String buy_level;
	public String getBuy_level() {
		return buy_level;
	}
	public void setBuy_level(String buy_level) {
		this.buy_level = buy_level;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	private String is_active;
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	private String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	private String growthvalue;
	public String getGrowthvalue() {
		return growthvalue;
	}
	public void setGrowthvalue(String growthvalue) {
		this.growthvalue = growthvalue;
	}
	
	private String growthtime;
	public String getGrowthtime() {
		return growthtime;
	}
	public void setGrowthtime(String growthtime) {
		this.growthtime = growthtime;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Member[");
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", cust_name=");
		builder.append(this.cust_name);
		builder.append(", logo_path=");
		builder.append(this.logo_path);
		builder.append(", mem_type=");
		builder.append(this.mem_type);
		builder.append(", sell_level=");
		builder.append(this.sell_level);
		builder.append(", buy_level=");
		builder.append(this.buy_level);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", is_active=");
		builder.append(this.is_active);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", growthvalue=");
		builder.append(this.growthvalue);
		builder.append(", growthtime=");
		builder.append(this.growthtime);
		builder.append("]");
		return builder.toString();
	}

}

