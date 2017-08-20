/*
  
 
 * Package:com.rbt.model
 * FileName: Cartgoods.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 购物车实体
 * @author 创建人 WXP
 * @date 创建日期 Mon May 13 14:10:06 CST 2014
 */
public class Cartgoods implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String buy_num;
	public String getBuy_num() {
		return buy_num;
	}
	public void setBuy_num(String buy_num) {
		this.buy_num = buy_num;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String cookie_id;
	public String getCookie_id() {
		return cookie_id;
	}
	public void setCookie_id(String cookie_id) {
		this.cookie_id = cookie_id;
	}
	
	private String shop_name;
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	
	private String shop_qq;
	public String getShop_qq() {
		return shop_qq;
	}
	public void setShop_qq(String shop_qq) {
		this.shop_qq = shop_qq;
	}
	
	private String shop_cust_id;
	public String getShop_cust_id() {
		return shop_cust_id;
	}
	public void setShop_cust_id(String shop_cust_id) {
		this.shop_cust_id = shop_cust_id;
	}
	
	private String user_name;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	private String img_path;
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	private String goods_name;
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	
	private String spec_name;
	public String getSpec_name() {
		return spec_name;
	}
	public void setSpec_name(String spec_name) {
		this.spec_name = spec_name;
	}
	
	private String spec_id;
	public String getSpec_id() {
		return spec_id;
	}
	public void setSpec_id(String spec_id) {
		this.spec_id = spec_id;
	}
	
	private Double sale_price;
	public Double getSale_price() {
		return sale_price;
	}
	public void setSale_price(Double sale_price) {
		this.sale_price = sale_price;
	}

	private Double integral;
	public Double getIntegral() {
		return integral;
	}
	public void setIntegral(Double integral) {
		this.integral = integral;
	}
	
	private String privilege_way;
	public String getPrivilege_way() {
		return privilege_way;
	}
	public void setPrivilege_way(String privilege_way) {
		this.privilege_way = privilege_way;
	}
	
	private String is_virtual;
	public String getIs_virtual() {
		return is_virtual;
	}
	public void setIs_virtual(String is_virtual) {
		this.is_virtual = is_virtual;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	private String radom_no;
	public String getRadom_no() {
		return radom_no;
	}
	public void setRadom_no(String radom_no) {
		this.radom_no = radom_no;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cartgoods[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", buy_num=");
		builder.append(this.buy_num);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", cookie_id=");
		builder.append(this.cookie_id);
		builder.append(", shop_name=");
		builder.append(this.shop_name);
		builder.append(", shop_qq=");
		builder.append(this.shop_qq);
		builder.append(", shop_cust_id=");
		builder.append(this.shop_cust_id);
		builder.append(", user_name=");
		builder.append(this.user_name);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", goods_name=");
		builder.append(this.goods_name);
		builder.append(", spec_name=");
		builder.append(this.spec_name);
		builder.append(", spec_id=");
		builder.append(this.spec_id);
		builder.append(", sale_price=");
		builder.append(this.sale_price);
		builder.append(", integral=");
		builder.append(this.integral);
		builder.append(", privilege_way=");
		builder.append(this.privilege_way);
		builder.append(", is_virtual=");
		builder.append(this.is_virtual);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", radom_no=");
		builder.append(this.radom_no);
		builder.append("]");
		return builder.toString();
	}
}

