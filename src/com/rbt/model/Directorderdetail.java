/*
  
 
 * Package:com.rbt.model
 * FileName: Directorderdetail.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 预售订单商品详细信息实体
 * @author 创建人 HZX
 * @date 创建日期 Wed Jul 17 13:26:38 CST 2014
 */
public class Directorderdetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	private Double goods_price;
	public Double getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(Double goods_price) {
		this.goods_price = goods_price;
	}
	
	private String order_num;
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	
	private String goods_attr;
	public String getGoods_attr() {
		return goods_attr;
	}
	public void setGoods_attr(String goods_attr) {
		this.goods_attr = goods_attr;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	private String endpay_time;
	public String getEndpay_time() {
		return endpay_time;
	}
	public void setEndpay_time(String endpay_time) {
		this.endpay_time = endpay_time;
	}
	
	private String pay_state;
	public String getPay_state() {
		return pay_state;
	}
	public void setPay_state(String pay_state) {
		this.pay_state = pay_state;
	}
	private Double earnest;
	public Double getEarnest() {
		return earnest;
	}
	public void setEarnest(Double earnest) {
		this.earnest = earnest;
	}
	
	private String direct_id;
	public String getDirect_id() {
		return direct_id;
	}
	public void setDirect_id(String direct_id) {
		this.direct_id = direct_id;
	}
	
	private String end_time;
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	
	private String temporary_title;
	public String getTemporary_title() {
		return temporary_title;
	}
	public void setTemporary_title(String temporary_title) {
		this.temporary_title = temporary_title;
	}
	
	private String temporary_img;
	public String getTemporary_img() {
		return temporary_img;
	}
	public void setTemporary_img(String temporary_img) {
		this.temporary_img = temporary_img;
	}

	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Directorderdetail[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", order_id=");
		builder.append(this.order_id);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", goods_price=");
		builder.append(this.goods_price);
		builder.append(", order_num=");
		builder.append(this.order_num);
		builder.append(", goods_attr=");
		builder.append(this.goods_attr);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", endpay_time=");
		builder.append(this.endpay_time);
		builder.append(", pay_state=");
		builder.append(this.pay_state);
		builder.append(", earnest=");
		builder.append(this.earnest);
		builder.append(", direct_id=");
		builder.append(this.direct_id);
		builder.append(", end_time=");
		builder.append(this.end_time);
		builder.append(", temporary_title=");
		builder.append(this.temporary_title);
		builder.append(", temporary_img=");
		builder.append(this.temporary_img);
		builder.append("]");
		return builder.toString();
	}
	
}

