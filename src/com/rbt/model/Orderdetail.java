/*
  
 
 * Package:com.rbt.model
 * FileName: Orderdetail.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Orderdetail implements Serializable {

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
	
	private String remark;
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String spec_id;
	
	public String getSpec_id() {
		return spec_id;
	}
	public void setSpec_id(String spec_id) {
		this.spec_id = spec_id;
	}
	
	private String temporary_goodsname;
	public String getTemporary_goodsname() {
		return temporary_goodsname;
	}
	public void setTemporary_goodsname(String temporary_goodsname) {
		this.temporary_goodsname = temporary_goodsname;
	}
	
	private String temporary_goodsimg;
	public String getTemporary_goodsimg() {
		return temporary_goodsimg;
	}
	public void setTemporary_goodsimg(String temporary_goodsimg) {
		this.temporary_goodsimg = temporary_goodsimg;
	}
	private String orderdetail_state;
	
	public String getOrderdetail_state() {
		return orderdetail_state;
	}
	public void setOrderdetail_state(String orderdetail_state) {
		this.orderdetail_state = orderdetail_state;
	}
	
	private String is_eval;
	public String getIs_eval() {
		return is_eval;
	}
	public void setIs_eval(String is_eval) {
		this.is_eval = is_eval;
	}
	private String tax_rate;
	public String getTax_rate() {
		return tax_rate;
	}
	public void setTax_rate(String tax_rate) {
		this.tax_rate = tax_rate;
	}
	
	private String apply_num;
	public String getApply_num() {
		return apply_num;
	}
	public void setApply_num(String apply_num) {
		this.apply_num = apply_num;
	}
	
	private String final_apply_num;
	public String getFinal_apply_num() {
		return final_apply_num;
	}
	public void setFinal_apply_num(String final_apply_num) {
		this.final_apply_num = final_apply_num;
	}
	
	private String coupon_id;
	private String subtotal;
	private String coupon_money;
	public String getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	public String getCoupon_money() {
		return coupon_money;
	}
	public void setCoupon_money(String coupon_money) {
		this.coupon_money = coupon_money;
	}
	
	private String use_coupon;
	public String getUse_coupon() {
		return use_coupon;
	}
	public void setUse_coupon(String use_coupon) {
		this.use_coupon = use_coupon;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Orderdetail[");
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
		builder.append(", spec_id=");
		builder.append(this.spec_id);
		builder.append(", temporary_goodsname=");
		builder.append(this.temporary_goodsname);
		builder.append(", temporary_goodsimg=");
		builder.append(this.temporary_goodsimg);
		builder.append(", orderdetail_state=");
		builder.append(this.orderdetail_state);
		builder.append(", is_eval=");
		builder.append(this.is_eval);
		builder.append(", tax_rate=");
		builder.append(this.tax_rate);
		builder.append(", coupon_id=");
		builder.append(this.coupon_id);
		builder.append(", subtotal=");
		builder.append(this.subtotal);
		builder.append(", coupon_money=");
		builder.append(this.coupon_money);
		builder.append(", apply_num=");
		builder.append(this.apply_num);
		builder.append(", final_apply_num=");
		builder.append(this.final_apply_num);
		builder.append(", use_coupon=");
		builder.append(this.use_coupon);
		builder.append("]");
		return builder.toString();
	}
	
}

