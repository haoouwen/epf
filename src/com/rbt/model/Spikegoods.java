/*
  
 
 * Package:com.rbt.model
 * FileName: Spikegoods.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 秒杀商品实体
 * @author 创建人 HZX
 * @date 创建日期 Fri Mar 29 15:32:29 CST 2014
 */
public class Spikegoods implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	private String spike_title;
	public String getSpike_title() {
		return spike_title;
	}
	public void setSpike_title(String spike_title) {
		this.spike_title = spike_title;
	}
	
	private String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	private Double price;
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	private String start_date;
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	
	private String end_date;
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	private String img_path;
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	private String spike_desc;
	public String getSpike_desc() {
		return spike_desc;
	}
	public void setSpike_desc(String spike_desc) {
		this.spike_desc = spike_desc;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	private String stock;
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	
	private String list_img;
	public String getList_img() {
		return list_img;
	}
	public void setList_img(String list_img) {
		this.list_img = list_img;
	}
	
	private String sale_price;
	public String getSale_price() {
		return sale_price;
	}
	public void setSale_price(String sale_price) {
		this.sale_price = sale_price;
	}
	
	private String goods_name;
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Spikegoods[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", spike_title=");
		builder.append(this.spike_title);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", price=");
		builder.append(this.price);
		builder.append(", start_date=");
		builder.append(this.start_date);
		builder.append(", end_date=");
		builder.append(this.end_date);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", spike_desc=");
		builder.append(this.spike_desc);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", stock=");
		builder.append(this.stock);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", list_img=");
		builder.append(this.list_img);
		builder.append(", sale_price=");
		builder.append(this.sale_price);
		builder.append(", goods_name=");
		builder.append(this.goods_name);
		builder.append("]");
		return builder.toString();
	}

}

