/*
  
 
 * Package:com.rbt.model
 * FileName: Expresshistory.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Expresshistory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String express_id;
	public String getExpress_id() {
		return express_id;
	}
	public void setExpress_id(String express_id) {
		this.express_id = express_id;
	}
	
	private String trigger_ip;
	public String getTrigger_ip() {
		return trigger_ip;
	}
	public void setTrigger_ip(String trigger_ip) {
		this.trigger_ip = trigger_ip;
	}
	
	private String trigger_time;
	public String getTrigger_time() {
		return trigger_time;
	}
	public void setTrigger_time(String trigger_time) {
		this.trigger_time = trigger_time;
	}
	
	private String trigger_type;
	public String getTrigger_type() {
		return trigger_type;
	}
	public void setTrigger_type(String trigger_type) {
		this.trigger_type = trigger_type;
	}
	
	private Double click_charges;
	public Double getClick_charges() {
		return click_charges;
	}
	public void setClick_charges(Double click_charges) {
		this.click_charges = click_charges;
	}
	private String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Expresshistory[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", express_id=");
		builder.append(this.express_id);
		builder.append(", trigger_ip=");
		builder.append(this.trigger_ip);
		builder.append(", trigger_time=");
		builder.append(this.trigger_time);
		builder.append(", click_charges=");
		builder.append(this.click_charges);
		builder.append(", trigger_type=");
		builder.append(this.trigger_type);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append("]");
		return builder.toString();
	}

}

