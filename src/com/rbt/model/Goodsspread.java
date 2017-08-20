/*
  
 
 * Package:com.rbt.model
 * FileName: Goodsspread.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 记录商品推广信息实体
 * @author 创建人 HZX
 * @date 创建日期 Wed Mar 20 13:21:09 CST 2014
 */
public class Goodsspread implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String spread_position;
	public String getSpread_position() {
		return spread_position;
	}
	public void setSpread_position(String spread_position) {
		this.spread_position = spread_position;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	private String spread_starttime;
	public String getSpread_starttime() {
		return spread_starttime;
	}
	public void setSpread_starttime(String spread_starttime) {
		this.spread_starttime = spread_starttime;
	}
	
	private String spread_endtime;
	public String getSpread_endtime() {
		return spread_endtime;
	}
	public void setSpread_endtime(String spread_endtime) {
		this.spread_endtime = spread_endtime;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	private String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	private String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	private Double discounts;
	public Double getDiscounts() {
		return discounts;
	}
	public void setDiscounts(Double discounts) {
		this.discounts = discounts;
	}
	private String spread_img;
	public String getSpread_img() {
		return spread_img;
	}
	public void setSpread_img(String spread_img) {
		this.spread_img = spread_img;
	}
	private String slogan;
	public String getSlogan() {
		return slogan;
	}
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Goodsspread[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", spread_position=");
		builder.append(this.spread_position);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", spread_starttime=");
		builder.append(this.spread_starttime);
		builder.append(", spread_endtime=");
		builder.append(this.spread_endtime);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", spread_img=");
		builder.append(this.spread_img);
		builder.append(", slogan=");
		builder.append(this.slogan);
		builder.append(", discounts=");
		builder.append(this.discounts);
		builder.append("]");
		return builder.toString();
	}

}

