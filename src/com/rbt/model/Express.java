/*
  
 
 * Package:com.rbt.model
 * FileName: Express.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Express implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String spread_name;
	public String getSpread_name() {
		return spread_name;
	}
	public void setSpread_name(String spread_name) {
		this.spread_name = spread_name;
	}
	
	private String image_path;
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	
	private String spread_link;
	public String getSpread_link() {
		return spread_link;
	}
	public void setSpread_link(String spread_link) {
		this.spread_link = spread_link;
	}
	
	private Double click_charges;
	public Double getClick_charges() {
		return click_charges;
	}
	public void setClick_charges(Double click_charges) {
		this.click_charges = click_charges;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String use_id;
	public String getUse_id() {
		return use_id;
	}
	public void setUse_id(String use_id) {
		this.use_id = use_id;
	}
	
	private String application_time;
	public String getApplication_time() {
		return application_time;
	}
	public void setApplication_time(String application_time) {
		this.application_time = application_time;
	}
	
	private Double put_price;
	public Double getPut_price() {
		return put_price;
	}
	public void setPut_price(Double put_price) {
		this.put_price = put_price;
	}
	
	private String put_starttime;
	public String getPut_starttime() {
		return put_starttime;
	}
	public void setPut_starttime(String put_starttime) {
		this.put_starttime = put_starttime;
	}
	
	private String put_endtime;
	public String getPut_endtime() {
		return put_endtime;
	}
	public void setPut_endtime(String put_endtime) {
		this.put_endtime = put_endtime;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	private String is_enable;
	public String getIs_enable() {
		return is_enable;
	}
	public void setIs_enable(String is_enable) {
		this.is_enable = is_enable;
	}
	private Double last_price;
	public Double getLast_price() {
		return last_price;
	}
	public void setLast_price(Double last_price) {
		this.last_price = last_price;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Express[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", spread_name=");
		builder.append(this.spread_name);
		builder.append(", image_path=");
		builder.append(this.image_path);
		builder.append(", spread_link=");
		builder.append(this.spread_link);
		builder.append(", click_charges=");
		builder.append(this.click_charges);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", use_id=");
		builder.append(this.use_id);
		builder.append(", application_time=");
		builder.append(this.application_time);
		builder.append(", put_price=");
		builder.append(this.put_price);
		builder.append(", put_starttime=");
		builder.append(this.put_starttime);
		builder.append(", put_endtime=");
		builder.append(this.put_endtime);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", is_enable=");
		builder.append(this.is_enable);
		builder.append(", last_price=");
		builder.append(this.last_price);
		builder.append("]");
		return builder.toString();
	}

}

