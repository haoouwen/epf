/*
  
 
 * Package:com.rbt.model
 * FileName: Combo.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 套餐表实体
 * @author 创建人 LHY
 * @date 创建日期 Mon Mar 25 15:09:17 CST 2014
 */
public class Combo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String goods_str;
	public String getGoods_str() {
		return goods_str;
	}
	public void setGoods_str(String goods_str) {
		this.goods_str = goods_str;
	}
	
	private String combo_price;
	
	
	private String combo_center;
	public String getCombo_center() {
		return combo_center;
	}
	public void setCombo_center(String combo_center) {
		this.combo_center = combo_center;
	}
	
	private String combo_img;
	public String getCombo_img() {
		return combo_img;
	}
	public void setCombo_img(String combo_img) {
		this.combo_img = combo_img;
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
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	private String stock;
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	private String combo_name;
	public String getCombo_name() {
		return combo_name;
	}
	public void setCombo_name(String combo_name) {
		this.combo_name = combo_name;
	}
	
	private String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String list_img;
	public String getList_img() {
		return list_img;
	}
	public void setList_img(String list_img) {
		this.list_img = list_img;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Combo[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", goods_str=");
		builder.append(this.goods_str);
		builder.append(", combo_price=");
		builder.append(this.combo_price);
		builder.append(", combo_center=");
		builder.append(this.combo_center);
		builder.append(", combo_img=");
		builder.append(this.combo_img);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", stock=");
		builder.append(this.stock);
		builder.append(", combo_name=");
		builder.append(this.combo_name);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", list_img=");
		builder.append(this.list_img);
		builder.append("]");
		return builder.toString();
	}
	public String getCombo_price() {
		return combo_price;
	}
	public void setCombo_price(String combo_price) {
		this.combo_price = combo_price;
	}

}

