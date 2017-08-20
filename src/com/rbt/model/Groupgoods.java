/*
  
 
 * Package:com.rbt.model
 * FileName: Groupgoods.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 团购商品信息实体
 * @author 创建人 HZX
 * @date 创建日期 Thu Mar 28 14:55:26 CST 2014
 */
public class Groupgoods implements Serializable {

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
	
	private String group_title;
	public String getGroup_title() {
		return group_title;
	}
	public void setGroup_title(String group_title) {
		this.group_title = group_title;
	}
	
	private String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	private Double bond;
	public Double getBond() {
		return bond;
	}
	public void setBond(Double bond) {
		this.bond = bond;
	}
	
	private String group_type;
	public String getGroup_type() {
		return group_type;
	}
	public void setGroup_type(String group_type) {
		this.group_type = group_type;
	}
	
	private String cust_maxbuy;
	public String getCust_maxbuy() {
		return cust_maxbuy;
	}
	public void setCust_maxbuy(String cust_maxbuy) {
		this.cust_maxbuy = cust_maxbuy;
	}
	
	private String min_buy;
	public String getMin_buy() {
		return min_buy;
	}
	public void setMin_buy(String min_buy) {
		this.min_buy = min_buy;
	}
	
	private String max_buy;
	public String getMax_buy() {
		return max_buy;
	}
	public void setMax_buy(String max_buy) {
		this.max_buy = max_buy;
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
	
	private String group_desc;
	public String getGroup_desc() {
		return group_desc;
	}
	public void setGroup_desc(String group_desc) {
		this.group_desc = group_desc;
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
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String list_img;
	public String getList_img() {
		return list_img;
	}
	public void setList_img(String list_img) {
		this.list_img = list_img;
	}
	private String apply_state;
	private String apply_time;
	
	public String getApply_state() {
		return apply_state;
	}
	public void setApply_state(String apply_state) {
		this.apply_state = apply_state;
	}
	public String getApply_time() {
		return apply_time;
	}
	public void setApply_time(String apply_time) {
		this.apply_time = apply_time;
	}
	private String is_recom;
	
	public String getIs_recom() {
		return is_recom;
	}
	public void setIs_recom(String is_recom) {
		this.is_recom = is_recom;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Groupgoods[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", group_title=");
		builder.append(this.group_title);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", bond=");
		builder.append(this.bond);
		builder.append(", group_type=");
		builder.append(this.group_type);
		builder.append(", cust_maxbuy=");
		builder.append(this.cust_maxbuy);
		builder.append(", min_buy=");
		builder.append(this.min_buy);
		builder.append(", max_buy=");
		builder.append(this.max_buy);
		builder.append(", start_date=");
		builder.append(this.start_date);
		builder.append(", end_date=");
		builder.append(this.end_date);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", group_desc=");
		builder.append(this.group_desc);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", stock=");
		builder.append(this.stock);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", list_img=");
		builder.append(this.list_img);
		builder.append(", apply_state=");
		builder.append(this.apply_state);
		builder.append(", apply_time=");
		builder.append(this.apply_time);
		builder.append(", is_recom=");
		builder.append(this.is_recom);
		builder.append("]");
		return builder.toString();
	}

}

