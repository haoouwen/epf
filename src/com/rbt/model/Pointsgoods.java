/*
  
 
 * Package:com.rbt.model
 * FileName: Pointsgoods.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 记录积分商品信息实体
 * @author 创建人 HZX
 * @date 创建日期 Mon Mar 25 16:00:03 CST 2014
 */
public class Pointsgoods implements Serializable {

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
	
	private String buy_points;
	public String getBuy_points() {
		return buy_points;
	}
	public void setBuy_points(String buy_points) {
		this.buy_points = buy_points;
	}
	
	private Double buy_sum;
	public Double getBuy_sum() {
		return buy_sum;
	}
	public void setBuy_sum(Double buy_sum) {
		this.buy_sum = buy_sum;
	}
	
	private String inter_image;
	public String getInter_image() {
		return inter_image;
	}
	public void setInter_image(String inter_image) {
		this.inter_image = inter_image;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	private String description;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String stock;
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	
	public String lab;
	public String getLab() {
		return lab;
	}
	public void setLab(String lab) {
		this.lab = lab;
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
		builder.append("Pointsgoods[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", buy_points=");
		builder.append(this.buy_points);
		builder.append(", buy_sum=");
		builder.append(this.buy_sum);
		builder.append(", inter_image=");
		builder.append(this.inter_image);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", description=");
		builder.append(this.description);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", stock=");
		builder.append(this.stock);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", lab=");
		builder.append(this.lab);
		builder.append(", list_img=");
		builder.append(this.list_img);
		builder.append("]");
		return builder.toString();
	}

}

